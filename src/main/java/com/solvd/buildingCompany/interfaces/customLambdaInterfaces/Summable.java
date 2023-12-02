package com.solvd.buildingCompany.interfaces.customLambdaInterfaces;

@FunctionalInterface
public interface Summable<T> {
    T experienceSummary(T accountant, T purchasingManager, T receptionist, T engineer);
}
