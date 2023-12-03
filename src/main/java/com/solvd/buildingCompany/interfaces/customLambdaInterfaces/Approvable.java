package com.solvd.buildingCompany.interfaces.customLambdaInterfaces;

@FunctionalInterface
public interface Approvable<T, E, S> {
    S projectStart(T occupation, E lastName, S enoughInfo);
}
