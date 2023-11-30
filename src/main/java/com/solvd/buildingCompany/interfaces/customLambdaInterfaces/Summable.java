package com.solvd.buildingCompany.interfaces.customLambdaInterfaces;

@FunctionalInterface
public interface Summable<T> {
    T experienceSummary(T employee1, T employee2, T employee3, T employee4);
}
