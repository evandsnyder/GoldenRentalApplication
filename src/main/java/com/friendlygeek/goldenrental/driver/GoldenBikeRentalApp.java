package com.friendlygeek.goldenrental.driver;

import com.friendlygeek.goldenrental.model.business.manager.GoldenBikeShopManager;
import com.friendlygeek.goldenrental.model.domain.Bike;
import com.friendlygeek.goldenrental.model.domain.Customer;
import com.friendlygeek.goldenrental.model.domain.RentalCart;
import com.friendlygeek.goldenrental.model.services.exception.PropertyFileNotFoundException;
import com.friendlygeek.goldenrental.model.services.factory.IServiceFactory;
import com.friendlygeek.goldenrental.model.services.factory.ServiceFactory;
import com.friendlygeek.goldenrental.model.services.manager.PropertyManager;
import com.friendlygeek.goldenrental.model.services.repository.IRepositoryWrapper;
import com.friendlygeek.goldenrental.model.services.repository.RepositoryWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Scanner;

public class GoldenBikeRentalApp {
    private static final Logger logger = LogManager.getLogger("com.friendlygeek.goldenrental");

    private GoldenBikeShopManager shopManager;
    private RentalCart cart;

    public GoldenBikeRentalApp() {

    }

    public boolean Initialize() {
        String propFileLocation = System.getProperty("prop_location");
        if (propFileLocation == null) {
            logger.error("No prop_location environment variable set.");
        }

        PropertyManager propertyManager = new PropertyManager();
        try {
            propertyManager.loadProperties(propFileLocation);
        } catch (PropertyFileNotFoundException ex) {
            logger.error("Could not load properties file");
            return false;
        }

        IRepositoryWrapper repository = new RepositoryWrapper();
        IServiceFactory factory = new ServiceFactory(propertyManager);

        shopManager = new GoldenBikeShopManager(propertyManager, repository, factory);
        cart = new RentalCart();

        return true;
    }

    public void Run() {
        Scanner inputScanner = new Scanner(System.in);
        String userInput;
        boolean running = true;

        while (running) {
            printMainMenu();
            userInput = inputScanner.nextLine();
            Integer userChoice = null;
            if (isNumeric(userInput)) {
                userChoice = Integer.parseInt(userInput);
            } else {
                System.out.println("Error: you entered an invalid number");
                continue;
            }

            switch (userChoice) {
                case 1:
                    // get available bikes
                    printAvailableBikes(shopManager.getAvailableBikes(), userInput, inputScanner);
                    // do some more processing...
                    break;
                case 2:
                    startCheckoutProcess(inputScanner);
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("The value you entered does not correspond with a valid action");
                    break;
            }

        }

        inputScanner.close();
    }

    private void printMainMenu() {
        System.out.println("Main Menu\n");
        System.out.printf("Reservation Cart: (%d)\n", 0);

        System.out.println("1. View Available Bikes");
        System.out.println("2. Checkout");
        System.out.println("3. Exit");
    }

    private void printAvailableBikes(ArrayList<Bike> bikes, String input, Scanner inputScanner) {
        while (true) {
            System.out.println("Displaying all bikes available to rent within the next month\n");
            for (Bike bike : bikes) {
                System.out.println(bike);
            }
            System.out.println("R: Return");

            System.out.println(
                    "You can add a bike to your cart by entering the number next to the desired bike, or you can enter R to return to the main menu.");

            input = inputScanner.nextLine();
            if (input.equals("R")) {
                return;
            }

            if (isNumeric(input)) {
                // Check if the number entered is an available bike and that it isn't already in
                // the cart...
                addToCart(Integer.parseInt(input));
            } else {
                System.out.println("Invalid value entered");
            }

        }

    }

    private String getInput(String outputMessage, Scanner scanner) {
        String input;

        System.out.println(outputMessage);
        input = scanner.nextLine();

        return input;
    }

    private void startCheckoutProcess(Scanner scanner) {
        Customer customer = new Customer();
        customer.setFirstName(getInput("Please enter your first name: ", scanner));
        customer.setLastName(getInput("Please enter your last name: ", scanner));
        customer.setEmail(getInput("Please enter your email address: ", scanner));
        customer.setPhone(getInput("Please enter your phone number: ", scanner));

        // Read each of these values and tuck them into a customer object..
        cart.setCustomer(customer);
        shopManager.performAction("createReservation", cart);

        System.out.println("Your reservation is being created");
    }

    private boolean isNumeric(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    private boolean addToCart(int bikeId) {
        // Ensure that the bike id is not already in the cart
        if (cart.getBikes().stream().filter(b -> b.getId() == bikeId).count() > 0) {
            System.out.println("That bike is already in your cart");
        } else {
            Bike bike = shopManager.findBike(bikeId);
            if (bike != null) {
                cart.getBikes().add(bike);
                System.out.println("Successfully added bike to your cart");
            } else {
                logger.error("Could not find bike: " + bikeId);
                System.out.println("We couldn't find the bike you were looking for");
            }
        }
        return true;
    }

}
