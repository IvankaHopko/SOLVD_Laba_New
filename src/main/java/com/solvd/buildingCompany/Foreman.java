package com.solvd.buildingCompany;

import com.solvd.buildingCompany.enums.Units;
import com.solvd.buildingCompany.exceptions.NotImplementedMethodException;
import com.solvd.buildingCompany.exceptions.NotReadyToStartException;
import com.solvd.buildingCompany.interfaces.IApproveBuildingMaterials;
import com.solvd.buildingCompany.interfaces.IApproveDesign;
import com.solvd.buildingCompany.interfaces.IProvideServices;
import com.solvd.buildingCompany.interfaces.IUpgradeQualification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Foreman extends CompanyEmployee implements IProvideServices, IApproveBuildingMaterials, IApproveDesign,
        IUpgradeQualification, Comparable<Foreman> {

    private static final Logger LOGGER = LogManager.getLogger(Foreman.class);

    private boolean readyToStart;

    public Foreman(String firstName, String lastName, String occupation, double salary, int experience,
                   boolean readyToStart) {
        super(firstName, lastName, occupation, salary, experience, Units.BUILDING);
        this.readyToStart = readyToStart;
    }

    public static boolean checkingForAllNecessaryToStart(boolean readyToStart) throws NotReadyToStartException {
        if (!readyToStart) {
            throw new NotReadyToStartException("Cannot start to work on a project");
        } else {
            LOGGER.info("The project has been successfully started");
        }
        return readyToStart;
    }

    public static void organizeBuildingTeam(List<BuildingCrew> builders) {
        Optional<Double> minCostPerHour = builders.stream()
                .map(BuildingCrew::getMinCostPerHour)
                .min(Double::compare);

        minCostPerHour.ifPresent(minCost -> {
            List<BuildingCrew> buildersCostPerHour = builders.stream()
                    .filter(builder -> builder.getMinCostPerHour() == minCost)
                    .collect(Collectors.toList());

            buildersCostPerHour.forEach(builder -> {
                LOGGER.info(builder.getMinCostPerHour() + "$ - is a builder's minimum cost per hour\n");
            });
        });
    }

    @Override
    public void provideServices() {
        LOGGER.info("I lead the building crew");
    }

    @Override
    public void buildingMaterialsApproval() {
        LOGGER.info("I give my approval about building materials");
    }

    public final void crewLeading() {
        LOGGER.info("I lead the building crew during our work");
    }

    @Override
    public void designApproval() {
        LOGGER.info("I give my approval about the project design");
    }

    @Override
    public void qualificationUpgrading() {
        LOGGER.info("I constantly upgrade my knowledge in building and team managing");
    }

    @Override
    public void documentMaintenance() throws NotImplementedMethodException {
    }

    public boolean getReadyToStart() {
        return this.readyToStart;
    }

    public void setReadyToStart(boolean readyToStart) {
        this.readyToStart = readyToStart;
    }


    @Override
    public String toString() {
        return "Foreman{" +
                "firstName=" + getFirstName() + " " +
                "lastName=" + getLastName() + " " +
                "occupation=" + getOccupation() + " " +
                "salary=" + getSalary() + " " +
                "experience=" + getExperience() + " " +
                "readyToStart=" + getReadyToStart() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Foreman)) return false;
        if (!super.equals(o)) return false;
        Foreman foreman = (Foreman) o;
        return readyToStart == foreman.readyToStart;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, experience);
    }

    @Override
    public int compareTo(Foreman otherForeman) {
        return Integer.compare(this.experience, otherForeman.experience);
    }
}


