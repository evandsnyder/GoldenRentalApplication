package com.friendlygeek.goldenrental.model.services.repository;

import com.friendlygeek.goldenrental.model.domain.Bike;

import java.util.ArrayList;

public class BikeRepository implements IRepository<Bike> {
    private final ArrayList<Bike> allBikes;

    public BikeRepository() {
        allBikes = new ArrayList<>();
        allBikes.add(new Bike(1, 9.99f, "Mongoose", "Sport", true));
        allBikes.add(new Bike(2, 9.99f, "Schwinn", "Street", true));
        allBikes.add(new Bike(3, 9.99f, "BMX", "Sport", false));
        allBikes.add(new Bike(4, 9.99f, "Mongoose", "Street", true));
        allBikes.add(new Bike(5, 9.99f, "Schwinn", "Special", true));
    }

    @Override
    public ArrayList<Bike> getAll() {
        return allBikes;
    }

    @Override
    public Bike getById(int id) {
        return allBikes.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void Update(Bike entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void Delete(String id) {
        // TODO Auto-generated method stub

    }
}
