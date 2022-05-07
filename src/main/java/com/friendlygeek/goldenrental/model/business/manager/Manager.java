package com.friendlygeek.goldenrental.model.business.manager;

import com.friendlygeek.goldenrental.model.domain.RentalCart;
import com.friendlygeek.goldenrental.model.services.manager.IPropertyManager;

public abstract class Manager {
    private final IPropertyManager propertyManager;

    public Manager(IPropertyManager manager) {
        propertyManager = manager;
    }

    public String getProperty(String key) {
        return propertyManager.getProperty(key);
    }

    public abstract boolean performAction(String command, RentalCart cart);
}
