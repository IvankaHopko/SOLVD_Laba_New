package com.solvd.buildingCompany.enums;

public enum Units {
    ACCOUNTING(40, 80),
    ENGINEERING(40, 90),
    PURCHASING(36, 70),
    BUILDING(40, 100),
    ADVERTISING(30, 70);

    private final int hoursPerWeek;
    private final int busyness;

    Units(int hoursPerWeek, int busyness) {
        this.hoursPerWeek = hoursPerWeek;
        this.busyness = busyness;
    }

    public int getHoursPerWeek() {
        return hoursPerWeek;
    }

    public int getBusyness() {
        return busyness;
    }
}
