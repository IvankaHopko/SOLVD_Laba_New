package com.solvd.buildingCompany.enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum BuildingType {

    BLOCK_OF_FLATS(200, 5, "downtown", true),
    COTTAGE(80, 2, "suburbs", false),
    TOWNHOUSE(150, 2, "district", true),
    ECO_HOUSE(50, 1, "forest", false);

    private static final Logger LOGGER = LogManager.getLogger(BuildingType.class);

    private final double square;
    private final int floors;
    private final String location;
    private final boolean rentFree;

    {
        System.out.println("Initializing " + this.name());
    }

    BuildingType(double square, int floors, String location, boolean rentFree) {
        this.square = square;
        this.floors = floors;
        this.location = location;
        this.rentFree = rentFree;
    }

    public boolean checkIfRentFree() {
        if (!rentFree) {
            LOGGER.info("Rental not available");
        } else {
            LOGGER.info("This building is rent free");
        }
        return rentFree;
    }

    public double getSquare() {
        return square;
    }

    public int getFloors() {
        return floors;
    }

    public String getLocation() {
        return location;
    }
}
