package com.solvd.buildingCompany.Reflection;

import com.solvd.buildingCompany.additionalClasses.Marketer;
import com.solvd.buildingCompany.additionalClasses.Name;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Runner {

    private static final Logger LOGGER = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {

        try {
            Class<Marketer> marketerClass = Marketer.class;
            Constructor<Marketer> marketerConstructor = marketerClass.getDeclaredConstructor(String.class, String.class,
                    boolean.class, int.class);
            Marketer marketer = marketerConstructor.newInstance("Samantha", "Goodwin", true, 3);

            Field firstNameField = marketerClass.getDeclaredField("firstName");
            firstNameField.setAccessible(true);
            firstNameField.set(marketer, "Sarah");

            for (Field declaredField : marketerClass.getDeclaredFields()) {
                if (declaredField.isAnnotationPresent(Name.class)) {
                    Name name = declaredField.getDeclaredAnnotation(Name.class);
                    String tempValue = name.value();

                    declaredField.setAccessible(true);
                    declaredField.set(marketer, tempValue);
                }
            }

            for (Field declaredField : marketerClass.getDeclaredFields()) {
                declaredField.setAccessible(true);
                Object fieldValue = declaredField.get(marketer);
                LOGGER.info("Fields: " + declaredField.getName() + ", Value: " + fieldValue);
            }

        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}
