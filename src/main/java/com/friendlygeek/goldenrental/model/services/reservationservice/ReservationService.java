package com.friendlygeek.goldenrental.model.services.reservationservice;

import com.friendlygeek.goldenrental.model.domain.Bike;
import com.friendlygeek.goldenrental.model.domain.RentalCart;
import com.friendlygeek.goldenrental.model.domain.Reservation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class ReservationService implements IReservationService {

    private static final Logger logger = LogManager.getLogger("com.friendlygeek.goldenrental");

    public Reservation confirmReservation(RentalCart cart) {
        if(cart.getBikes().isEmpty()){
            logger.error("Invalid cart submitted for checkout");
            return null;
        }

        Reservation reservation = new Reservation(new Random().nextInt(10000), cart.getCustomer(), cart.getBikes());
        for (Bike bike : reservation.getReservedBikes()) {
            bike.setAvailable(false);
        }

        // In a more "production" environment, this will actually need to update the bike repository.
        // Since everything is in memory right now, these are all references anyhow.

        logger.info("Created new reservation: " + reservation.getId());

        return reservation;
    }
}
