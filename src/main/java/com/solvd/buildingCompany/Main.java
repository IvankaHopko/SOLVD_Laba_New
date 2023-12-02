package com.solvd.buildingCompany;

import com.solvd.buildingCompany.customLinkedList.CustomLinkedList;
import com.solvd.buildingCompany.enums.*;
import com.solvd.buildingCompany.exceptions.*;
import com.solvd.buildingCompany.interfaces.customLambdaInterfaces.Countable;
import com.solvd.buildingCompany.interfaces.customLambdaInterfaces.Displayable;
import com.solvd.buildingCompany.interfaces.customLambdaInterfaces.Summable;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }

    public static void main(String[] args) throws NotInStockException, NotReadyToStartException {

        ArrayList<CompanyEmployee> employees = new ArrayList<>();

        employees.add(new Receptionist("Ella", "Wally", 26, "Receptionist",
                20000, 5));
        employees.add(new Engineer("John", "Bambie", "Senior Engineer", 40000,
                10, false, BuildingType.TOWNHOUSE));
        employees.add(new PurchasingManager("Mikaela", "Hanks", "Marketer", 25000,
                7, 500000, 350000, SupplierCountries.USA));
        employees.add(new Accountant("Sarah", "Miles", "Junior Accountant", 18000,
                1, true));
        employees.add(new Foreman("Bill", "Moore", "senior foreman", 60000,
                25, true));
        BuildingCrew buildingCrew = new BuildingCrew(10, 100, 40,
                12, 10, NonWorkingPeriod.CHRISTMAS);
        Building desiredBuilding = new Building(2, 70, "suburbs", "townhouse");
        Customer customer = new Customer("Dean", "Winchester", desiredBuilding);
        Provider provider = new Provider("John", "Storm", "Kyiv", true);

        LOGGER.info("\n\nCustomer: " + customer.getFirstName() + " " + customer.getLastName() + "\n\n" +
                "Desired building: " + "\n" + customer.getDesiredBuilding());

        double totalPrice = CostCalculating.totalPriceCalculation(desiredBuilding, buildingCrew);

        LOGGER.info("\nTotal price: " + totalPrice);

        Receptionist.showCoFoundersInfo();

        Provider.inStockChecking(provider.getHaveAllNeeded());
        LOGGER.info("All in stock: " + Provider.inStockChecking(provider.getHaveAllNeeded()));

        try {
            double fundsLeft = PurchasingManager.makingAPurchase(employees.get(2).getRequiredCost());
            LOGGER.info("Funds left after purchase: " + fundsLeft);
        } catch (InsufficientFundsException e) {
            LOGGER.error(e.getMessage());
        }

        Foreman.checkingForAllNecessaryToStart(employees.get(4).getReadyToStart());
        LOGGER.info("All preparations are done and we are ready to start: " +
                Foreman.checkingForAllNecessaryToStart(employees.get(4).getReadyToStart()));

        try {
            int finalWorksTime = BuildingCrew.buildingProcess(buildingCrew.getMonthsToBuild());
            LOGGER.info("Time for final works on building: " + finalWorksTime + " months");
        } catch (OutOfTimeException e) {
            LOGGER.error(e.getMessage());
        }

        try {
            Engineer.checkingForEnoughInfo(employees.get(1).getEnoughInfo());
        } catch (NotEnoughInfoException e) {
            LOGGER.error(e.getMessage());
        }

        CustomLinkedList<String> buildings = new CustomLinkedList<>();

        buildings.add("villa");
        buildings.add("block of flats");
        buildings.add("cottage");
        buildings.add("eco-house");


        for (int i = 0; i < buildings.getSize(i); i++) {
            LOGGER.info(buildings.getSize(i));
        }

        String buildingToRemove = "cottage";
        boolean removed = buildings.remove(buildingToRemove);

        if (removed) {
            LOGGER.info("\nBuilding has been successfully removed");
        } else {
            LOGGER.info("\nBuilding not found or can not be removed");
        }

        LOGGER.info("\nBuildings after removing:" + "\n");
        for (int i = 0; i < buildings.getSize(i); i++) {
            LOGGER.info(buildings.getSize(i));
        }

        Set<Foreman> foremanHashSet = new HashSet<>();

        foremanHashSet.add(new Foreman("Bill", "Moore", "Lead foreman", 40000,
                25, true));
        foremanHashSet.add(new Foreman("Chris", "Jackson", "Senior foreman", 25000,
                17, true));
        foremanHashSet.add(new Foreman("Denis", "Broom", "Foreman", 20000,
                6, true));
        foremanHashSet.add(new Foreman("Neal", "Gates", "Junior foreman", 16000,
                2, true));

        LOGGER.info("Foremen details: ");
        for (Foreman foreman : foremanHashSet) {
            LOGGER.info("Experience: " + foreman.getExperience() + ", " + "Last Name: " + foreman.getLastName());
        }

        Set<Foreman> foremanLinkedHashSet = new LinkedHashSet<>();

        foremanLinkedHashSet.add(new Foreman("Steve", "Hook", "Lead foreman", 40000,
                19, true));
        foremanLinkedHashSet.add(new Foreman("Joe", "Miles", "Senior foreman", 25000,
                15, true));
        foremanLinkedHashSet.add(new Foreman("Luke", "Drake", "Foreman", 20000,
                9, true));
        foremanLinkedHashSet.add(new Foreman("Sam", "Novak", "Junior foreman", 16000,
                3, true));

        LOGGER.info("\nForemen details: ");
        for (Foreman foreman : foremanLinkedHashSet) {
            LOGGER.info("Experience: " + foreman.getExperience() + ", " + "Last Name: " + foreman.getLastName());
        }

        Set<Foreman> foremanTreeSet = new TreeSet<>();

        foremanTreeSet.add(new Foreman("Jim", "Stain", "Lead foreman", 40000,
                26, true));
        foremanTreeSet.add(new Foreman("Mike", "Anderson", "Senior foreman", 25000,
                15, true));
        foremanTreeSet.add(new Foreman("Will", "Middleton", "Foreman", 20000,
                7, true));
        foremanTreeSet.add(new Foreman("Bobby", "Ringo", "Junior foreman", 16000,
                1, true));

        LOGGER.info("\nForemen details: ");
        for (Foreman foreman : foremanTreeSet) {
            LOGGER.info("Experience: " + foreman.getExperience() + ", " + "Last Name: " + foreman.getLastName());
        }

        File sourceFile = new File("D:\\laba.SOLVD\\buildingCompany\\src\\main\\resources\\AboutUkraine.txt");
        File destinationFile = new File("D:\\laba.SOLVD\\buildingCompany\\src\\main\\resources\\UniqueWordsCount.txt");
        try {
            String content = FileUtils.readFileToString(sourceFile);
            String[] words = StringUtils.split(content);

            Set<String> uniqueWords = new HashSet<>();
            for (String word : words) {
                uniqueWords.add(word.toLowerCase());
            }
            int uniqueWordCount = uniqueWords.size();

            FileUtils.writeStringToFile(destinationFile, "\nUnique words amount from 'AboutUkraine' text: " +
                    uniqueWordCount);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Countable<Double> addFunction = ((foreman1, foreman2, foreman3, foreman4) ->
                foreman1 + foreman2 + foreman3 + foreman4);
        Foreman foreman1 = new Foreman("Jim", "Stain", "Lead foreman", 40000,
                26, true);
        Foreman foreman2 = new Foreman("Mike", "Anderson", "Senior foreman", 25000,
                15, true);
        Foreman foreman3 = new Foreman("Will", "Middleton", "Foreman", 20000,
                7, true);
        Foreman foreman4 = new Foreman("Bobby", "Ringo", "Junior foreman", 16000,
                1, true);

        double totalSalary = addFunction.totalSalaryCount(foreman1.getSalary(), foreman2.getSalary(),
                foreman3.getSalary(), foreman4.getSalary());
        LOGGER.info("\n\nTotal foremen salary is " + totalSalary);

        Displayable<String> displayLastNames = (accountant, receptionist, foreman, engineer) ->
                (accountant + receptionist + foreman + engineer);

        Accountant accountant1 = new Accountant("Sarah", "Miles", "Junior Accountant",
                18000, 1, true);
        Receptionist receptionist1 = new Receptionist("Ella", "Wally", 26,
                "Receptionist", 20000, 5);
        Foreman foreman5 = new Foreman("Jim", "Stain", "Lead foreman", 40000,
                26, true);
        Engineer engineer1 = new Engineer("John", "Bambie", "Senior Engineer", 40000,
                10, false, BuildingType.COTTAGE);

        displayLastNames.lastNamesDisplay(accountant1.getLastName(), receptionist1.getLastName(), foreman5.getLastName(),
                engineer1.getLastName());

        LOGGER.info("\nLast names:");
        LOGGER.info("Accountant: " + accountant1.getLastName() + "; "
                + "Receptionist: " + receptionist1.getLastName() + "; " + "Foreman: " + foreman5.getLastName() + "; "
                + "Engineer: " + engineer1.getLastName());

        Summable<Integer> addExperience = ((accountant, purchasingManager, receptionist, engineer) ->
                accountant + purchasingManager + receptionist + engineer);

        Accountant cashier = new Accountant("Sarah", "Miles", "Junior Accountant",
                18000, 1, true);
        PurchasingManager buyer = new PurchasingManager("Mikaela", "Hanks", "Marketer",
                25000, 7, 500000, 350000, SupplierCountries.GERMANY);
        Receptionist admin = new Receptionist("Ella", "Wally", 26, "Receptionist",
                20000, 5);
        Engineer projectMaker = new Engineer("John", "Bambie", "Senior Engineer", 40000,
                10, false, BuildingType.ECO_HOUSE);

        int totalExperience = addExperience.experienceSummary(cashier.getExperience(), buyer.getExperience(),
                admin.getExperience(), projectMaker.getExperience());
        LOGGER.info("Employees total experience is: " + totalExperience + " years");

        List<String> names = new ArrayList<>();
        names.add("Jack Frost");
        names.add("Santa");
        names.add("Easter Bunny");
        names.add("SandyMan");

        LOGGER.info("\n");
        Consumer<String> printNames = n -> LOGGER.info("Holiday keeper name: " + n);
        names.forEach(printNames);

        Supplier<Double> randomNumber = () -> Math.random();
        double number = randomNumber.get();
        LOGGER.info("Random number: " + number);

        Function<Integer, Integer> squareFunction = x -> x * x;
        int result = squareFunction.apply(7);
        LOGGER.info("Square of 7 is: " + result);

        Function<String, Integer> stringLengthFunction = str -> str.length();
        int length = stringLengthFunction.apply("pneumonoultramicroscopicsilicovolcanoconiosis");
        LOGGER.info("Length is: " + length);

        LOGGER.info("\nBlock of flats:");
        LOGGER.info("Square: " + BuildingType.BLOCK_OF_FLATS.getSquare());
        LOGGER.info("Floors: " + BuildingType.BLOCK_OF_FLATS.getFloors());
        LOGGER.info("Location: " + BuildingType.BLOCK_OF_FLATS.getLocation());
        LOGGER.info("The Block-of-Flats is free to rent: " + BuildingType.BLOCK_OF_FLATS.checkIfRentFree());

        LOGGER.info("\nCottage:");
        LOGGER.info("Square: " + BuildingType.COTTAGE.getSquare());
        LOGGER.info("Floors: " + BuildingType.COTTAGE.getFloors());
        LOGGER.info("Location: " + BuildingType.COTTAGE.getLocation());
        LOGGER.info("The Cottage is free to rent: " + BuildingType.COTTAGE.checkIfRentFree());

        LOGGER.info("\nTownhouse:");
        LOGGER.info("Square: " + BuildingType.TOWNHOUSE.getSquare());
        LOGGER.info("Floors: " + BuildingType.TOWNHOUSE.getFloors());
        LOGGER.info("Location: " + BuildingType.TOWNHOUSE.getLocation());
        LOGGER.info("The Townhouse is free to rent: " + BuildingType.TOWNHOUSE.checkIfRentFree());

        LOGGER.info("\nEco-house:");
        LOGGER.info("Square: " + BuildingType.ECO_HOUSE.getSquare());
        LOGGER.info("Floors: " + BuildingType.ECO_HOUSE.getFloors());
        LOGGER.info("Location: " + BuildingType.ECO_HOUSE.getLocation());
        LOGGER.info("The Eco-house is free to rent: " + BuildingType.ECO_HOUSE.checkIfRentFree());

        LOGGER.info(" ");
        LOGGER.info("Advertising department has: " + Departments.ADVERTISING.getBusyness() + " % of busyness");
        LOGGER.info("Building department has: " + Departments.BUILDING.getBusyness() + " % of busyness");
        LOGGER.info("Accounting department has: " + Departments.ACCOUNTING.getBusyness() + " % of busyness");
        LOGGER.info("Accounting department has: " + Departments.ACCOUNTING.getHoursPerWeek() + " working hours per week");
        LOGGER.info("Engineering department has: " + Departments.ENGINEERING.getBusyness() + " % of busyness");
        LOGGER.info("Purchasing department has: " + Departments.PURCHASING.getHoursPerWeek() + " working hours per week");

        LOGGER.info(" ");
        LOGGER.info("Bonus for Sundays: " + NonWorkingPeriod.SUNDAY.getBonus());
        LOGGER.info("The bonus for Christmas holiday is: " + NonWorkingPeriod.CHRISTMAS.getBonusPercent() + "%");
        LOGGER.info("The amount of days off for Easter is: " + NonWorkingPeriod.EASTER.getDuration());
        LOGGER.info("The amount of days off for Thanksgiving Day is: " + NonWorkingPeriod.THANKSGIVING_DAY.getDuration());

        LOGGER.info(" ");
        LOGGER.info("Country is allowed to make order from: " + SupplierCountries.USA.permissionCheck());
        LOGGER.info("Country is allowed to make order from: " + SupplierCountries.CHINA.permissionCheck());
        LOGGER.info("Country is allowed to make order from: " + SupplierCountries.RUSSIA.permissionCheck());
        LOGGER.info("Country is allowed to make order from: " + SupplierCountries.GERMANY.permissionCheck());
        LOGGER.info("Country is allowed to make order from: " + SupplierCountries.HUNGARY.permissionCheck());
        LOGGER.info("It will take " + SupplierCountries.USA.getDeliveryTimeInDays() + " to ship the order");
        LOGGER.info("It will take " + SupplierCountries.GERMANY.getDeliveryTimeInDays() + " to ship the order");

        LOGGER.info(" ");
        LOGGER.info("This co-founder has the write to run the company: " + CoFounders.FINANCIAL_DIRECTOR.getFirstName() + " "
                + CoFounders.FINANCIAL_DIRECTOR.getLastName() + " - " + CoFounders.FINANCIAL_DIRECTOR.getSoleOwnership());
        LOGGER.info("This co-founder has the write to run the company: " + CoFounders.MARKETING_DIRECTOR.getFirstName() + " "
                + CoFounders.MARKETING_DIRECTOR.getLastName() + " - " + CoFounders.MARKETING_DIRECTOR.getSoleOwnership());
        LOGGER.info("This co-founder has the write to run the company: " + CoFounders.IDEA_CREATOR.getFirstName() + " "
                + CoFounders.IDEA_CREATOR.getLastName() + " - " + CoFounders.IDEA_CREATOR.getSoleOwnership());

    }
}