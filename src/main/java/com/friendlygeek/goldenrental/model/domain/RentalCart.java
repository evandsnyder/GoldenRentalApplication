package com.friendlygeek.goldenrental.model.domain;

import java.util.ArrayList;

public class RentalCart {
    private Customer customer;
    private final ArrayList<Bike> selectedBikes;

    public RentalCart() {
        selectedBikes = new ArrayList<>();
    }

    public ArrayList<Bike> getBikes() {
        return selectedBikes;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}
