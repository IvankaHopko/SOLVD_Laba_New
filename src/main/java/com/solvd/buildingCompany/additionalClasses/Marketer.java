package com.solvd.buildingCompany.additionalClasses;

import java.util.Objects;

public class Marketer {

    @Name(value = "Sarah")
    private String firstName;

    private String lastName;

    private boolean background;

    private int project;

    public Marketer(String firstName, String lastName, boolean background, int project) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.background = background;
        this.project = project;
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

    public boolean isBackground() {
        return background;
    }

    public void setBackground(boolean background) {
        this.background = background;
    }

    public int getProject() {
        return project;
    }

    public void setProject(int project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Marketer marketer = (Marketer) o;
        return Objects.equals(firstName, marketer.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName);
    }
}

