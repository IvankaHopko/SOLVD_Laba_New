package com.solvd.buildingCompany.interfaces.customLambdaInterfaces;

@FunctionalInterface
public interface Displayable<T> {
    T lastNamesDisplay(T accountant, T receptionist, T foreman, T engineer);
}
