package com.friendlygeek.goldenrental.model.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Reservation implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -1936624015883458751L;
    private Integer id;
    private Customer customer;
    private ArrayList<Bike> reservedBikes;

    public Reservation() {
    }

    public Reservation(Integer id, Customer customer, ArrayList<Bike> reservedBikes) {
        super();
        this.id = id;
        this.customer = customer;
        this.reservedBikes = reservedBikes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ArrayList<Bike> getReservedBikes() {
        return reservedBikes;
    }

    public void setReservedBikes(ArrayList<Bike> reservedBikes) {
        this.reservedBikes = reservedBikes;
    }


}
