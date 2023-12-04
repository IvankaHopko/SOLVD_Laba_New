package com.solvd.buildingCompany.additionalClasses;

public class Marketer {

    @Name(value = "Sarah")
    private String firstName;

    @Name(value = "Green")
    private String lastName;

    @Name(value = "true")
    private boolean background;

    @Name("2")
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
}
