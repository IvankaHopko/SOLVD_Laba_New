package com.solvd.buildingCompany;

import com.solvd.buildingCompany.additionalClasses.*;
import com.solvd.buildingCompany.customLinkedList.CustomLinkedList;
import com.solvd.buildingCompany.enums.*;
import com.solvd.buildingCompany.exceptions.*;
import com.solvd.buildingCompany.interfaces.customLambdaInterfaces.Countable;
import com.solvd.buildingCompany.interfaces.customLambdaInterfaces.Displayable;
import com.solvd.buildingCompany.interfaces.customLambdaInterfaces.Approvable;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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

        double discountedPrice = CostCalculating.applyDiscountToTotalPrice(10, desiredBuilding, buildingCrew);
        LOGGER.info("10% discounted price is: " + discountedPrice + "\n");

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

        Provider.ordersValidation();

        Employee worker1 = new Employee("Bruce", "Brown", "October");
        Employee worker2 = new Employee("Jensen", "Harris", "April");
        Employee worker3 = new Employee("Felicity", "Day", "July");
        Employee worker4 = new Employee("Rick", "Owen", "September");
        List<Employee> workersDepartment1 = List.of(worker1, worker2, worker3, worker4);

        Department department1 = new Department();
        department1.setName("Ukrainian");
        department1.setAddress("Kyiv");
        department1.setStaff(350);
        department1.setEmployees(workersDepartment1);


        Employee worker5 = new Employee("Robert", "Heard", "March");
        Employee worker6 = new Employee("Patrick", "Cruz", "June");
        Employee worker7 = new Employee("Olivia", "Night", "February");
        Employee worker8 = new Employee("Olivia", "Night", "February");
        Employee worker9 = new Employee("Olivia", "Night", "February");
        List<Employee> workersDepartment2 = List.of(worker5, worker6, worker7, worker8, worker9);

        Department department2 = new Department();
        department2.setName("Ukrainian");
        department2.setAddress("Kharkiv");
        department2.setStaff(250);
        department2.setEmployees(workersDepartment2);


        Employee worker10 = new Employee("Robert", "Heard", "March");
        Employee worker11 = new Employee("Patrick", "Cruz", "June");
        Employee worker12 = new Employee("Olivia", "Night", "February");
        List<Employee> workersDepartment3 = List.of(worker10, worker11, worker12);

        Department department3 = new Department();
        department3.setName("Polish");
        department3.setAddress("Warsaw");
        department3.setStaff(250);
        department3.setEmployees(workersDepartment3);

        List<Department> departments = List.of(department1, department2, department3);

        BuildingCompany buildingCompany = new BuildingCompany();
        buildingCompany.setDepartments(departments);

        buildingCompany.getDepartments().stream()
                .filter(department -> department.getAddress().contains("Kharkiv"))
                .map(department -> department.getAddress())
                .collect(Collectors.toList());

        List<Department> departmentsCollection = buildingCompany.getDepartments().stream()
                .filter(department -> department.getAddress().contains("Kharkiv"))
                .collect(Collectors.toList());
        LOGGER.info("All company departments: " + departmentsCollection);

        List<String> employeeLastNames = buildingCompany.getDepartments().stream()
                .filter(department -> department.getAddress().contains("Kyiv"))
                .flatMap(department -> department.getEmployees().stream())
                .map(employee -> employee.getLastName())
                .filter(employeeLastName -> employeeLastName.startsWith("S"))
                .peek(employeeLastName -> LOGGER.info(employeeLastName))
                .collect(Collectors.toList());
        LOGGER.info("All employees last names: " + employeeLastNames);

        boolean stores = buildingCompany.getDepartments().stream()
                .filter(department -> department.getAddress().contains("Kyiv"))
                .flatMap(department -> department.getEmployees().stream())
                .map(employee -> employee.getLastName())
                .allMatch(lastName -> lastName.startsWith("B"));
        LOGGER.info("All last names that start with 'B': " + stores);

        boolean stores1 = buildingCompany.getDepartments().stream()
                .filter(department -> department.getAddress().contains("Kyiv"))
                .flatMap(department -> department.getEmployees().stream())
                .map(employee -> employee.getLastName())
                .anyMatch(lastName -> lastName.startsWith("B"));
        LOGGER.info("All last names that start with 'B': " + stores1);

        Optional<Department> firstFoundedDept = buildingCompany.getDepartments().stream()
                .filter(department -> department.getAddress().contains("Warsaw"))
                .findFirst();

        firstFoundedDept.get();

        buildingCompany.getDepartments().stream()
                .filter(department -> department.getAddress().contains("Kyiv"))
                .flatMap(department -> department.getEmployees().stream())
                .map(Employee::getLastName)
                .filter(employeeLastName -> employeeLastName.startsWith("S"))
                .forEach(lastName -> LOGGER.info("Employee with letter 'S' at the beginning of the last name: " + lastName));

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

            Predicate<String> containsUkraine = word -> word.toLowerCase().equals("Ukraine");

            boolean containsWordUkraine = false;
            for (String word : words) {
                if (containsUkraine.test(word)) {
                    containsWordUkraine = true;
                    break;
                }
            }

            if (containsWordUkraine) {
                LOGGER.info("Provided file contains word 'Ukraine'.");
            } else {
                LOGGER.info("Provided file does not contain word 'Ukraine'.");
            }
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
        LOGGER.info("\n\nTotal foremen salary is " + totalSalary + "\n");

        List<BuildingCrew> builders = new ArrayList<>();
        builders.add(new BuildingCrew(10, 100, 40,
                12, 10, NonWorkingPeriod.CHRISTMAS));
        builders.add(new BuildingCrew(8, 90, 36,
                16, 12, NonWorkingPeriod.CHRISTMAS));
        builders.add(new BuildingCrew(14, 120, 40,
                10, 8, NonWorkingPeriod.CHRISTMAS));

        Foreman.organizeBuildingTeam(builders);

        Displayable<String, Integer> displayAccount = (occupation, salary) ->
                LOGGER.info("Accountant occupation is: " + occupation + " and the salary is: " + salary + "$\n");

        displayAccount.displayAccountantInfo("'Junior Accountant'", 18000);

        Approvable<String, String, Boolean> approveProject = (occupation, lastName, enoughInfo) -> {
            LOGGER.info("Occupation: " + occupation + ", Last Name: " + lastName + ", Enough information: " + enoughInfo);

            return occupation.equals("Senior Engineer") && enoughInfo;
        };

        boolean readyToStart = approveProject.projectStart("Senior Engineer", "Jones", true);
        LOGGER.info("Engineer has enough information and is ready to start working on a project: " + readyToStart);

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
        LOGGER.info("Advertising department has: " + Units.ADVERTISING.getBusyness() + " % of busyness");
        LOGGER.info("Building department has: " + Units.BUILDING.getBusyness() + " % of busyness");
        LOGGER.info("Accounting department has: " + Units.ACCOUNTING.getBusyness() + " % of busyness");
        LOGGER.info("Accounting department has: " + Units.ACCOUNTING.getHoursPerWeek() + " working hours per week");
        LOGGER.info("Engineering department has: " + Units.ENGINEERING.getBusyness() + " % of busyness");
        LOGGER.info("Purchasing department has: " + Units.PURCHASING.getHoursPerWeek() + " working hours per week");

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