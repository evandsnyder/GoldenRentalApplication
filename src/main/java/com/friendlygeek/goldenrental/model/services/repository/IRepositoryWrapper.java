package com.friendlygeek.goldenrental.model.services.repository;

import com.friendlygeek.goldenrental.model.domain.Bike;

public interface IRepositoryWrapper {
    IRepository<Bike> Bikes();

}
