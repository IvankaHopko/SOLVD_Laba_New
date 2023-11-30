package com.solvd.buildingCompany.interfaces.customLambdaInterfaces;

@FunctionalInterface
public interface Countable<T> {
    T quarterlySalaryCount(T month1, T month2, T month3);
}
