package com.bottega.function.E06;

import com.bottega.function.E06.CustomCollectorExercise.AvgSalary;
import com.bottega.function.E06.CustomCollectorExercise.Employee;
import org.junit.Test;

import java.util.List;

import static com.bottega.function.E06.CustomCollectorExercise.E01_calculateAverageSalaries;
import static com.bottega.function.E06.CustomCollectorExercise.Gender.FEMALE;
import static com.bottega.function.E06.CustomCollectorExercise.Gender.MALE;
import static com.bottega.function.E06.CustomCollectorExercise.Position.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CustomCollectorExerciseTest {

    @Test
    public void e01_calculateAverageSalaries() {
        checkResults(List.of(new Employee("John", MALE, 100_000, JUNIOR)),
            new AvgSalary(MALE, JUNIOR, 100_000));

        checkResults(List.of(
                new Employee("John", MALE, 100_000, JUNIOR),
                new Employee("Jane", FEMALE, 120_000, JUNIOR)
            ),
            new AvgSalary(MALE, JUNIOR, 100_000),
            new AvgSalary(FEMALE, JUNIOR, 120_000)
        );

        checkResults(List.of(
                new Employee("John", MALE, 100_000, JUNIOR),
                new Employee("Jane", FEMALE, 120_000, JUNIOR),
                new Employee("Luck", MALE, 50_000, JUNIOR),
                new Employee("Lucy", FEMALE, 60_000, JUNIOR),
                new Employee("Sean", MALE, 40_000, JUNIOR),
                new Employee("Sally", FEMALE, 30_000, JUNIOR)
            ),
            new AvgSalary(MALE, JUNIOR, 63_333),
            new AvgSalary(FEMALE, JUNIOR, 70_000)
        );

        checkResults(List.of(
                new Employee("John", MALE, 100_000, JUNIOR),
                new Employee("Jane", FEMALE, 220_000, LEAD),
                new Employee("Luck", MALE, 120_000, REGULAR),
                new Employee("Lucy", FEMALE, 160_000, LEAD),
                new Employee("Sean", MALE, 140_000, SENIOR),
                new Employee("Sally", FEMALE, 230_000, STAFF)
            ),
            new AvgSalary(MALE, JUNIOR, 100_000),
            new AvgSalary(FEMALE, LEAD, 190_000),
            new AvgSalary(MALE, REGULAR, 120_000),
            new AvgSalary(MALE, SENIOR, 140_000),
            new AvgSalary(FEMALE, STAFF, 230_000)
        );
    }

    private void checkResults(List<Employee> employees, AvgSalary... results) {
        assertThat(E01_calculateAverageSalaries().apply(employees)).containsExactlyInAnyOrder(results);
    }

}
