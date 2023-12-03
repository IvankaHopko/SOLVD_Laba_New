package com.solvd.buildingCompany.interfaces.customLambdaInterfaces;

@FunctionalInterface
public interface Displayable<T, R> {
    void displayAccountantInfo(T occupation, R salary);
}
