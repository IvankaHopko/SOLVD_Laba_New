package com.solvd.buildingCompany;

import com.solvd.buildingCompany.enums.BuildingType;
import com.solvd.buildingCompany.enums.Units;
import com.solvd.buildingCompany.exceptions.NotEnoughInfoException;
import com.solvd.buildingCompany.interfaces.IProvideServices;
import com.solvd.buildingCompany.interfaces.IUpgradeQualification;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Engineer extends CompanyEmployee implements IProvideServices, IUpgradeQualification {

    private static final Logger LOGGER = LogManager.getLogger(Engineer.class);
    private static boolean enoughInfo;
    private final BuildingType mostCommonProjects;

    public Engineer(String firstName, String lastName, String occupation, double salary, int experience,
                    boolean enoughInfo, BuildingType mostCommonProjects) {
        super(firstName, lastName, occupation, salary, experience, Units.ENGINEERING);
        Engineer.enoughInfo = enoughInfo;
        this.mostCommonProjects = mostCommonProjects;
    }

    public static void checkingForEnoughInfo(boolean enoughInfo) throws NotEnoughInfoException {
        if (!enoughInfo) {
            throw new NotEnoughInfoException("Cannot execute project. Please, provide more details");
        } else {
            LOGGER.info("Order has been accepted for execution");
        }
    }

    public void passesDesignToProcurementDept() {
        LOGGER.info("Check for the needed materials according to provided design");
    }

    @Override
    public void provideServices() {
        LOGGER.info("I create a design for a new project");
    }

    @Override
    public void documentMaintenance() {
        LOGGER.info("I analyze all documents related to the current project");
    }

    @Override
    public void qualificationUpgrading() {
        LOGGER.info("I regularly upgrade my knowledge and skills in engineering");
    }

    public boolean getEnoughInfo() {
        return Engineer.enoughInfo;
    }

    public void setEnoughInfo(boolean enoughInfo) {
        Engineer.enoughInfo = enoughInfo;
    }
}


