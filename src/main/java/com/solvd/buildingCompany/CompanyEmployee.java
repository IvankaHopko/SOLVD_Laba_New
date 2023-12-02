package com.solvd.buildingCompany;

import com.solvd.buildingCompany.enums.Departments;

import java.util.Objects;

public abstract class CompanyEmployee {
    protected String firstName;
    protected String lastName;
    protected String occupation;
    private double salary;
    protected int experience;
    private final Departments departments;

    public CompanyEmployee(String firstName, String lastName, String occupation, double salary, int experience,
                           Departments departments) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.occupation = occupation;
        this.salary = salary;
        this.experience = experience;
        this.departments = departments;
    }

    public double getRequiredCost() {
        return this.getRequiredCost();
    }

    public boolean getReadyToStart() {
        return this.getReadyToStart();
    }

    public boolean getEnoughInfo() {
        return getEnoughInfo();
    }

    public abstract void provideServices();

    public abstract void documentMaintenance();

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

    public String getOccupation() {
        return this.occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getExperience() {
        return this.experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public Departments getDepartments() {
        return this.departments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyEmployee that = (CompanyEmployee) o;
        return Double.compare(that.salary, salary) == 0 &&
                experience == that.experience &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, salary, experience);
    }
}


