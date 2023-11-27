package com.solvd.buildingCompany;

import com.solvd.buildingCompany.interfaces.IApproveDesign;
import com.solvd.buildingCompany.interfaces.IPayWhenWorkIsDone;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class Customer implements IPayWhenWorkIsDone, IApproveDesign {

    private static final Logger LOGGER = LogManager.getLogger(Customer.class);

    private String firstName;
    private String lastName;
    private Building desiredBuilding;

    public Customer(String firstName, String lastName, Building desiredBuilding) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.desiredBuilding = desiredBuilding;
    }

    public final void providesIdea() {
        LOGGER.info("I have an idea about the building. And I want your company to help me");
    }

    @Override
    public void doneWorkPayment() {
        LOGGER.info("I pay money for the work that is done");
    }

    @Override
    public void designApproval() {
        LOGGER.info("I approve design if it is done according to my idea");
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Building getDesiredBuilding() {
        return this.desiredBuilding;
    }

    public void setDesiredBuilding(Building desiredBuilding) {
        this.desiredBuilding = desiredBuilding;
    }

    @Override
    public String toString() {
        return "desiredBuilding{" +
                "floors='" + getDesiredBuilding() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer that = (Customer) o;
        return desiredBuilding == that.desiredBuilding &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}

