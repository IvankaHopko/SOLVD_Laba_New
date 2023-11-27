package com.solvd.buildingCompany;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class CompanyOwner {

    private static final Logger LOGGER = LogManager.getLogger(CompanyOwner.class);

    private final String firstName = "Chandler";
    private final String lastName = "Bing";
    private final boolean soleOwnership;

    public CompanyOwner(boolean soleOwnership) {
        this.soleOwnership = soleOwnership;
    }

    public void runsTheCompany() {
        if (soleOwnership) {
            LOGGER.info("You can make any decision concerning the building company");
        } else {
            LOGGER.info("You need to consult with a lawyer");
        }
    }
}

