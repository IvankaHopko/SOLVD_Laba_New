package com.solvd.buildingCompany.additionalClasses;

public class Employee {
    private String firstName;
    private String lastName;
    private String hireMonth;

    public Employee(String firstName, String lastName, String hireMonth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hireMonth = hireMonth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getHireMonth() {
        return hireMonth;
    }

    public void setHireMonth(String hireMonth) {
        this.hireMonth = hireMonth;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", hireMonth='" + hireMonth + '\'' +
                '}';
    }
}
