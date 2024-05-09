package com.bottega.function.E06;

import java.util.List;
import java.util.function.Function;

import static com.bottega.function.Utils.todo;

class CustomCollectorExercise {

    static Function<List<Employee>, List<AvgSalary>> E01_calculateAverageSalaries() {
        return todo();
    }

    record Employee(String name, Gender gender, Integer salary, Position position) {

    }

    record AvgSalary(Gender gender, Position position, Integer avgSalary) {

    }

    enum Gender {
        MALE, FEMALE
    }

    enum Position {
        JUNIOR, REGULAR, SENIOR, LEAD, STAFF, PRINCIPAL
    }

}
