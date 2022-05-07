package com.friendlygeek.goldenrental.model.business.manager;

import com.friendlygeek.goldenrental.model.domain.Customer;
import com.friendlygeek.goldenrental.model.domain.RentalCart;
import com.friendlygeek.goldenrental.model.domain.Reservation;
import com.friendlygeek.goldenrental.model.services.exception.ServiceLoadException;
import com.friendlygeek.goldenrental.model.services.factory.ServiceFactory;
import com.friendlygeek.goldenrental.model.services.manager.PropertyManager;
import com.friendlygeek.goldenrental.model.services.repository.RepositoryWrapper;
import com.friendlygeek.goldenrental.model.services.reservationservice.ReservationService;
import junit.framework.TestCase;

import static org.easymock.EasyMock.*;

public class GoldenBikeShopManagerTest extends TestCase {
    GoldenBikeShopManager uut;

    // MockDependencies
    RepositoryWrapper mockRepository;
    PropertyManager mockPropManager;
    ServiceFactory mockServiceFactory;

    public void setUp() {
        mockRepository = mock(RepositoryWrapper.class);
        mockPropManager = mock(PropertyManager.class);
        mockServiceFactory = mock(ServiceFactory.class);

        uut = new GoldenBikeShopManager(mockPropManager, mockRepository, mockServiceFactory);
    }

    public void testPerformActionSucceeds() throws ServiceLoadException {
        ReservationService mockReservationService = mock(ReservationService.class);
        expect(mockServiceFactory.getService("IReservationService")).andReturn(mockReservationService);
        replay(mockServiceFactory);

        RentalCart cart = new RentalCart();
        Customer customer = new Customer();
        cart.setCustomer(customer);
        expect(mockReservationService.confirmReservation(cart)).andReturn(mock(Reservation.class));
        replay(mockReservationService);

        assertTrue(uut.performAction("IReservationService", cart));

        reset(mockReservationService);
        reset(mockServiceFactory);
    }

    public void testPerformActionFailsWithRecognizedService() throws ServiceLoadException {
        ReservationService mockReservationService = mock(ReservationService.class);
        expect(mockServiceFactory.getService("IReservationService")).andReturn(mockReservationService);
        replay(mockServiceFactory);

        RentalCart cart = new RentalCart();
        Customer customer = new Customer();
        cart.setCustomer(customer);
        expect(mockReservationService.confirmReservation(cart)).andReturn(null);
        assertFalse(uut.performAction("IReservationService", null));
    }

    public void testPerformActionFailsWithUnrecognizedService() {
        assertFalse(uut.performAction("fakeCommand", null));
    }
}