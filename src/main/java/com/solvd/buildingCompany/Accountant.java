package com.solvd.buildingCompany;
import com.solvd.buildingCompany.interfaces.IPayWhenWorkIsDone;
import com.solvd.buildingCompany.interfaces.IMaintainDocumentation;
import com.solvd.buildingCompany.interfaces.IProvideServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class Accountant extends CompanyEmployee
        implements IProvideServices, IMaintainDocumentation, IPayWhenWorkIsDone {

    private static final Logger LOGGER = LogManager.getLogger(Accountant.class);
    private boolean enoughMoney;

    protected Accountant(String firstName, String lastName, String occupation, double salary,
                         int experience, boolean enoughMoney) {
        super(firstName, lastName, occupation, salary, experience);
        this.enoughMoney = enoughMoney;
    }

    public void checkingForEnoughMoney() {
        if (enoughMoney) {
            LOGGER.info("There is enough money to pay the purchase invoice");
        } else {
            LOGGER.info("Not enough money");
        }
    }

    @Override
    public void provideServices() {
        LOGGER.info("I pay salary to building staff and I pay for the purchases of materials");
    }

    @Override
    public void doneWorkPayment() {
        LOGGER.info("I pay money for the work that is done");
    }

    @Override
    public void documentMaintenance() {
        LOGGER.info("I maintain accounting documents of the building company");
    }

    public static void taxReporting() throws FileNotFoundException {
        try (FileReader fileReader = new FileReader("Settlement accounting journal.txt");
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            StringBuilder content = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }

            String fileContent = content.toString();
            LOGGER.info(fileContent);

        } catch (IOException e) {
            throw new FileNotFoundException("File not found");
        }
    }

    public boolean getEnoughMoney() {
        return this.enoughMoney;
    }

    public void setEnoughMoney(boolean enoughMoney) {
        this.enoughMoney = enoughMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Accountant)) return false;
        Accountant that = (Accountant) o;
        return Objects.equals(firstName, that.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName);
    }
}