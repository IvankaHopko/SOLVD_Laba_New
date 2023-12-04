package com.solvd.buildingCompany;

import com.solvd.buildingCompany.exceptions.NotInStockException;
import com.solvd.buildingCompany.interfaces.IMaintainDocumentation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Provider implements IMaintainDocumentation {

    private static final Logger LOGGER = LogManager.getLogger(Provider.class);

    private String firstName;
    private String lastName;
    private String address;
    protected boolean haveAllNeeded;

    public Provider(String firstName, String lastName, String address, boolean haveAllNeeded) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.haveAllNeeded = haveAllNeeded;
    }

    protected void sellsNeededMaterial() {
        if (haveAllNeeded) {
            LOGGER.info("Your order is fully complected");
        } else {
            LOGGER.error("Not enough items in store");
        }
    }

    public static void ordersValidation() {
        Runnable validationProcess = () -> LOGGER.info("For every order I check if there is enough materials in stock");
        validationProcess.run();
    }

    @Override
    public void documentMaintenance() {
        LOGGER.info("I maintain orders documentation");
    }

    public static boolean inStockChecking(boolean haveAllNeeded) throws NotInStockException {
        if (!haveAllNeeded) {
            throw new NotInStockException("Not enough goods in stock");
        } else {
            LOGGER.info("Ready to ship the order");
        }
        return haveAllNeeded;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean getHaveAllNeeded() {
        return this.haveAllNeeded;
    }

    public void setHaveAllNeeded(boolean haveAllNeeded) {
        this.haveAllNeeded = haveAllNeeded;
    }
}

