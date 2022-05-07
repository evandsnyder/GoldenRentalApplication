package com.friendlygeek.goldenrental.model.domain;

import java.io.Serializable;

public class Bike implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1366435554741994703L;

    private Integer id;
    private float rentalRate;
    private String manufacturer;
    private String type;
    private boolean isAvailable;

    public Bike() {
    }

    public Bike(Integer id, float rentalRate, String manufacturer, String type, boolean isAvailable) {
        super();
        this.id = id;
        this.rentalRate = rentalRate;
        this.manufacturer = manufacturer;
        this.type = type;
        this.isAvailable = isAvailable;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(float rentalRate) {
        this.rentalRate = rentalRate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String toString() {
        return String.format("%d.\t%s\t%s: %f.2", id, manufacturer, type, rentalRate);
    }
}
