package com.bottega.function.E06;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

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

    private record GenderPosition(Gender gender, Position position) {
        GenderPosition(Employee employee) {
            this(employee.gender, employee.position);
        }
    }

    private record SallariesSum(Integer sum, Integer count) {
        SallariesSum(Employee employee) {
            this(employee.salary, 1);
        }

        SallariesSum add(SallariesSum sallariesSum) {
            return new SallariesSum(sum + sallariesSum.sum, count + sallariesSum.count);
        }

        Integer avg() {
            return sum / count;
        }
    }

    private static class AveragingCollector implements Collector<Employee, Map<GenderPosition, SallariesSum>, List<AvgSalary>> {

        @Override
        public Supplier<Map<GenderPosition, SallariesSum>> supplier() {
            return todo();
        }

        @Override
        public BiConsumer<Map<GenderPosition, SallariesSum>, Employee> accumulator() {
            return todo();
        }

        @Override
        public BinaryOperator<Map<GenderPosition, SallariesSum>> combiner() {
            return todo();
        }

        @Override
        public Function<Map<GenderPosition, SallariesSum>, List<AvgSalary>> finisher() {
            return todo();
        }

        @Override
        public Set<Characteristics> characteristics() {
            return todo();
        }
    }

}
