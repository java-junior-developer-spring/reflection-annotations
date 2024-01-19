package com.itmo.container;

import com.itmo.container.reflection.TaskRunner;
import com.itmo.container.reflection.Validator;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Building> buildings = new ArrayList<>(
                Arrays.asList(
                        new Building(2, 5000),
                        new Building(6, 90_000),
                        new Building(10, 80_000),
                        new Building(1, 40_000),
                        new Building(8, 75_000)
                )
        );

        Validator<Building> buildingValidator = new Validator<>(buildings);
        buildingValidator.validate();
        buildingValidator.getValidatedList().forEach(System.out::println);
        buildingValidator.getErrorInfo().forEach(System.out::println);


        List<User> users = new ArrayList<>(
                Arrays.asList(
                        new User(17),
                        new User(33),
                        new User(29),
                        new User(45),
                        new User(120)
                )
        );

        Validator<User> userValidator = new Validator<>(users);
        userValidator.validate();
        userValidator.getValidatedList().forEach(System.out::println);
        userValidator.getErrorInfo().forEach(System.out::println);

        TaskRunner taskRunner = new TaskRunner();
        taskRunner.run(new ArrayList<>(
                Arrays.asList(HelloPrinter.class, DirectoryReader.class)
        ));
    }
}
