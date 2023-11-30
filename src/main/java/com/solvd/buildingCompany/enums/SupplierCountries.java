package com.solvd.buildingCompany.enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum SupplierCountries {
    GERMANY(true, 2),
    USA(true, 7),
    RUSSIA(false, 0),
    CHINA(false, 0),
    HUNGARY(true, 1);

    private static final Logger LOGGER = LogManager.getLogger(SupplierCountries.class);
    private final boolean edmissibility;
    private final int deliveryTimeInDays;

    SupplierCountries(boolean edmissibility, int deliveryTimeInDays) {
        this.edmissibility = edmissibility;
        this.deliveryTimeInDays = deliveryTimeInDays;
    }

    public boolean permissionCheck() {
        if (edmissibility) {
            LOGGER.info("Permission to order materials is granted");
        } else {
            LOGGER.info("Not allowed to make order from this supplier country!");
        }
        return edmissibility;
    }

    public int getDeliveryTimeInDays() {
        return deliveryTimeInDays;
    }
}
