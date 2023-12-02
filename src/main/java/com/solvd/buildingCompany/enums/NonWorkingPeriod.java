package com.solvd.buildingCompany.enums;

public enum NonWorkingPeriod {
    SUNDAY(1, false, 0),
    THANKSGIVING_DAY(1, false, 0),
    EASTER(2, true, 5),
    CHRISTMAS(2, true, 10);

    private final int duration;
    private final boolean bonus;
    private final int bonusPercent;

    NonWorkingPeriod(int duration, boolean bonus, int bonusPercent) {
        this.duration = duration;
        this.bonus = bonus;
        this.bonusPercent = bonusPercent;
    }

    public int getDuration() {
        return duration;
    }

    public boolean getBonus() {
        return bonus;
    }

    public int getBonusPercent() {
        return bonusPercent;
    }
}
