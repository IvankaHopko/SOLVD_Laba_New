package com.solvd.buildingCompany;

import com.solvd.buildingCompany.enums.Units;
import com.solvd.buildingCompany.enums.SupplierCountries;
import com.solvd.buildingCompany.exceptions.InsufficientFundsException;
import com.solvd.buildingCompany.interfaces.IApproveBuildingMaterials;
import com.solvd.buildingCompany.interfaces.IMaintainDocumentation;
import com.solvd.buildingCompany.interfaces.IProvideServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PurchasingManager extends CompanyEmployee
        implements IProvideServices, IMaintainDocumentation, IApproveBuildingMaterials {

    private static final Logger LOGGER = LogManager.getLogger(PurchasingManager.class);

    private static double projectBudget;
    private double requiredCost;
    private final SupplierCountries departurePoint;

    public PurchasingManager(String firstName, String lastName, String occupation, double salary,
                             int experience, double projectBudget, double requiredCost, SupplierCountries departurePoint) {
        super(firstName, lastName, occupation, salary, experience, Units.PURCHASING);
        PurchasingManager.projectBudget = projectBudget;
        this.requiredCost = requiredCost;
        this.departurePoint = departurePoint;
    }

    @Override
    public void provideServices() {
        LOGGER.info("I search for suppliers to purchase the needed materials");
    }

    @Override
    public void documentMaintenance() {
        LOGGER.info("I maintain documentation about purchases");
    }

    @Override
    public void buildingMaterialsApproval() {
        LOGGER.info("I give my approval about building materials");
    }

    public void communication() {
        LOGGER.info("I communicate with suppliers about the quality of needed materials");
    }

    public static double makingAPurchase(double requiredCost) throws InsufficientFundsException {
        double fundsLeft;
        if (projectBudget < requiredCost) {
            throw new InsufficientFundsException("The purchase price exceeds the allowable budget");
        } else {
            LOGGER.info("All needed materials are successfully purchased");
            fundsLeft = (projectBudget - requiredCost);
        }
        return fundsLeft;
    }

    public double getProjectBudget() {
        return PurchasingManager.projectBudget;
    }

    public void setProjectBudget(double projectBudget) {
        PurchasingManager.projectBudget = projectBudget;
    }

    public double getRequiredCost() {
        return this.requiredCost;
    }

    public void setRequiredCost(double requiredCost) {
        this.requiredCost = requiredCost;
    }

    public SupplierCountries getDeparturePoint() {
        return this.departurePoint;
    }

    @Override
    public String toString() {
        return "PurchasingManager{" +
                "firstName='" + getFirstName() +
                ", lastName=" + getLastName() + '\'' +
                ", occupation=" + getOccupation() +
                ", salary=" + getSalary() + '\'' +
                ", experience=" + getExperience() + '\'' +
                ", projectBudget=" + getProjectBudget() +
                '}';
    }
}


