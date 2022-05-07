package com.friendlygeek.goldenrental.model.business.manager;

import com.friendlygeek.goldenrental.model.domain.Bike;
import com.friendlygeek.goldenrental.model.domain.RentalCart;
import com.friendlygeek.goldenrental.model.services.exception.ServiceLoadException;
import com.friendlygeek.goldenrental.model.services.factory.IServiceFactory;
import com.friendlygeek.goldenrental.model.services.manager.IPropertyManager;
import com.friendlygeek.goldenrental.model.services.repository.IRepositoryWrapper;
import com.friendlygeek.goldenrental.model.services.reservationservice.ReservationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class GoldenBikeShopManager extends Manager {
    private static final Logger logger = LogManager.getLogger("com.friendlygeek.goldenrental");
    private final IRepositoryWrapper repository;
    private final IServiceFactory factory;

    public GoldenBikeShopManager(IPropertyManager propertyManager, IRepositoryWrapper repository,
                                 IServiceFactory factory) {
        super(propertyManager);
        this.repository = repository;
        this.factory = factory;
    }

    /**
     * Get all bikes and filter on whether they are available or not...
     *
     * @return
     */
    public ArrayList<Bike> getAvailableBikes() {
        return new ArrayList<Bike>(
                repository.Bikes().getAll().stream().filter(p -> p.isAvailable()).collect(Collectors.toList()));
    }

    public Bike findBike(int id) {
        return repository.Bikes().getById(id);
    }

    @Override
    public boolean performAction(String command, RentalCart cart) {
        switch (command) {
            case "IReservationService":
                return processCreateReservation(command, cart);
        }

        return false;
    }

    private boolean processCreateReservation(String serviceName, RentalCart cart) {
        ReservationService reservationService;

        boolean result = false;
        try {
            reservationService = (ReservationService) factory.getService(serviceName);
            result = reservationService.confirmReservation(cart) != null;
        } catch (ServiceLoadException e) {
            logger.error("Could not load reservation service", e);
        }

        return result;
    }

}
