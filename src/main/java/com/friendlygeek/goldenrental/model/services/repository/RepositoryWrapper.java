package com.friendlygeek.goldenrental.model.services.repository;

import com.friendlygeek.goldenrental.model.domain.Bike;

public class RepositoryWrapper implements IRepositoryWrapper {
    public IRepository<Bike> bikes;

    public RepositoryWrapper() {
        bikes = new BikeRepository();
    }

    @Override
    public IRepository<Bike> Bikes() {
        return bikes;
    }

}
