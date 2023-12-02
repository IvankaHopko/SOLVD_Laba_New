package com.solvd.buildingCompany.interfaces.customLambdaInterfaces;

@FunctionalInterface
public interface Countable<T> {
    T totalSalaryCount(T foreman1, T foreman2, T foreman3, T foreman4);
}
