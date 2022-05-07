package com.friendlygeek.goldenrental.model.services.reservationservice;

import com.friendlygeek.goldenrental.model.domain.RentalCart;
import com.friendlygeek.goldenrental.model.domain.Reservation;
import com.friendlygeek.goldenrental.model.services.IService;

public interface IReservationService extends IService {
    Reservation confirmReservation(RentalCart cart);

}
