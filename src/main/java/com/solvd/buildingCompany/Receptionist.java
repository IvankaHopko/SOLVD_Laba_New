package com.solvd.buildingCompany;

import com.solvd.buildingCompany.interfaces.IMaintainDocumentation;
import com.solvd.buildingCompany.interfaces.IProvideServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Receptionist extends CompanyEmployee implements IProvideServices, IMaintainDocumentation {

    private static final Logger LOGGER = LogManager.getLogger(Receptionist.class);

    private int age;

    public Receptionist(String firstName, String lastName, int age, String occupation, double salary, int experience) {
        super(firstName, lastName, occupation, salary, experience);
        this.age = age;
    }

    @Override
    public void provideServices() {
        LOGGER.info("I help customer to define the idea");
    }

    public void passesCustomerRequest() {
        LOGGER.info("I have a new project for you to design");
    }

    @Override
    public void documentMaintenance() {
        LOGGER.info("I maintain general company's documentation");
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Receptionist{" +
                "experience=" + experience +
                '}';
    }
}




