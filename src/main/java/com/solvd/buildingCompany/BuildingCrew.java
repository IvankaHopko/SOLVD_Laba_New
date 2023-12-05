package com.solvd.buildingCompany;

import com.solvd.buildingCompany.enums.NonWorkingPeriod;
import com.solvd.buildingCompany.exceptions.OutOfTimeException;
import com.solvd.buildingCompany.interfaces.IUpgradeQualification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BuildingCrew implements IUpgradeQualification {

    private static final Logger LOGGER = LogManager.getLogger(BuildingCrew.class);

    private int totalExperience;
    private double minCostPerHour;
    private int workingHours;
    private static int deadlineInMonths;
    private int monthsToBuild;
    private final NonWorkingPeriod schedule;


    public BuildingCrew(int totalExperience, double minCostPerHour, int workingHours, int deadlineInMonths,
                        int monthsToBuild, NonWorkingPeriod schedule) {
        this.totalExperience = totalExperience;
        this.minCostPerHour = minCostPerHour;
        this.workingHours = workingHours;
        BuildingCrew.deadlineInMonths = deadlineInMonths;
        this.monthsToBuild = monthsToBuild;
        this.schedule = schedule;
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
        LOGGER.info("We build a new building according to its design");
    }

    @Override
    public void qualificationUpgrading() {
        LOGGER.info("We regularly have trainings to upgrade our qualification");
    }

    public void buildingWork() {
        LOGGER.info("We provide building services according to engineered plan");
    }

    public static int buildingProcess(int monthsToBuild) throws OutOfTimeException {
        int finalWorksTime = 0;
        if (monthsToBuild > deadlineInMonths) {
            throw new OutOfTimeException("Out of limited time");
        } else {
            LOGGER.info("Project is finished in time");
            finalWorksTime = (deadlineInMonths - monthsToBuild);
        }
        return finalWorksTime;
    }

    public int getTotalExperience() {
        return this.totalExperience;
    }

    public void setTotalExperience(int totalExperience) {
        this.totalExperience = totalExperience;
    }

    public double getMinCostPerHour() {
        return this.minCostPerHour;
    }

    public void setMinCostPerHour(double minCostPerHour) {
        this.minCostPerHour = minCostPerHour;
    }

    public int getWorkingHours() {
        return this.workingHours;
    }

    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }

    public int getDeadlineInMonths() {
        return BuildingCrew.deadlineInMonths;
    }

    public void setDeadline(int deadlineInMonths) {
        BuildingCrew.deadlineInMonths = deadlineInMonths;
    }

    public int getMonthsToBuild() {
        return this.monthsToBuild;
    }

    public void setMonthsToBuild(int monthsToBuild) {
        this.monthsToBuild = monthsToBuild;
    }

    public NonWorkingPeriod getSchedule() {
        return this.schedule;
    }
}
