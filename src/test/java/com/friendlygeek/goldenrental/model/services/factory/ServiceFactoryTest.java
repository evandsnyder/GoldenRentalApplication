package com.friendlygeek.goldenrental.model.services.factory;

import com.friendlygeek.goldenrental.model.services.manager.PropertyManager;
import com.friendlygeek.goldenrental.model.services.reservationservice.IReservationService;
import com.friendlygeek.goldenrental.model.services.reservationservice.ReservationService;
import junit.framework.TestCase;
import org.easymock.EasyMock;
import org.easymock.Mock;

import static org.easymock.EasyMock.*;

public class ServiceFactoryTest extends TestCase {
    ServiceFactory serviceFactory;

    @Mock
    private PropertyManager mockPropManager;

    public void setUp() throws Exception {
        // need to create a mock of the propManager
        mockPropManager = mock(PropertyManager.class);
        expect(mockPropManager.getProperty(EasyMock.anyString())).andReturn("com.friendlygeek.goldenrental.model.services.reservationservice.ReservationService");
        replay(mockPropManager);

        serviceFactory = new ServiceFactory(mockPropManager);
    }

    public void testGetReservationService(){
        IReservationService reservationService;

        try {
            reservationService = (IReservationService) serviceFactory.getService("IReservationService");
            assertTrue(reservationService instanceof ReservationService);
        } catch(Exception e){
            e.printStackTrace();
            fail("Expected instance of ReservationService to be returned");
        }

        verify(mockPropManager);
        reset(mockPropManager);
    }
}