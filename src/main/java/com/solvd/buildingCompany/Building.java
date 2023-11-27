package com.solvd.buildingCompany;

public class Building {
    private int floors;
    private double area;
    private String location;
    private String typeOfBuilding;

    public Building(int floors, double area, String location, String typeOfBuilding) {
        this.floors = floors;
        this.area = area;
        this.location = location;
        this.typeOfBuilding = typeOfBuilding;
    }

    public int getFloors() {
        return this.floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public double getArea() {
        return this.area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTypeOfBuilding() {
        return this.typeOfBuilding;
    }

    public void setTypeOfBuilding(String typeOfBuilding) {
        this.typeOfBuilding = typeOfBuilding;
    }

    @Override
    public String toString() {
        return "floors: " + getFloors() + "\n" +
                "area: " + getArea() + "\n" +
                "location: " + getLocation() + "\n" +
                "typeOfBuilding: " + getTypeOfBuilding() +
                ".";
    }
}