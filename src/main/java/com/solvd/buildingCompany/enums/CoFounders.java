package com.solvd.buildingCompany.enums;

public enum CoFounders {
    IDEA_CREATOR("Chandler", "Bing", true),
    MARKETING_DIRECTOR("Gegina", "Falange", false),
    FINANCIAL_DIRECTOR("Joey", "Tribbiany", false);

    private final String firstName;
    private final String lastName;
    private final boolean soleOwnership;

    CoFounders(String firstName, String lastName, boolean soleOwnership) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.soleOwnership = soleOwnership;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean getSoleOwnership() {
        return soleOwnership;
    }
}
