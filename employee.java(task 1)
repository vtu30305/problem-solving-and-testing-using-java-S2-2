import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

class Employee {
    private int id;
    private String name;
    private double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format(
            "ID: %d, Name: %s, Salary: %.2f",
            id, name, salary
        );
    }
}

public class SalaryTransformationSystem {

    public static void main(String[] args) {

        // Employee data
        List<Employee> employees = Arrays.asList(
            new Employee(101, "Arun", 30000),
            new Employee(102, "Priya", 45000),
            new Employee(103, "Karthik", 60000),
            new Employee(104, "Divya", 75000),
            new Employee(105, "Rahul", 90000)
        );

        System.out.println("===== ORIGINAL SALARIES =====");
        employees.forEach(System.out::println);

        // Functional interface to calculate revised salary
        Function<Employee, Double> salaryTransformation = employee -> {
            double salary = employee.getSalary();

            if (salary < 40000) {
                return salary * 1.10;       // 10% increment
            } else if (salary < 70000) {
                return salary * 1.15;       // 15% increment
            } else {
                return salary * 1.20;       // 20% increment
            }
        };

        // Transform salaries using Stream API
        employees.forEach(employee ->
            employee.setSalary(salaryTransformation.apply(employee))
        );

        System.out.println("\n===== TRANSFORMED SALARIES =====");
        employees.forEach(System.out::println);

        // Predicate to find employees with salary >= 60000
        Predicate<Employee> highSalary = employee ->
            employee.getSalary() >= 60000;

        List<Employee> highSalaryEmployees = employees.stream()
                .filter(highSalary)
                .collect(Collectors.toList());

        System.out.println("\n===== EMPLOYEES WITH SALARY >= 60000 =====");
        highSalaryEmployees.forEach(System.out::println);

        // Calculate total salary using reduce()
        double totalSalary = employees.stream()
                .map(Employee::getSalary)
                .reduce(0.0, Double::sum);

        System.out.println("\nTotal Salary: " + totalSalary);

        // Calculate average salary
        double averageSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);

        System.out.printf("Average Salary: %.2f%n", averageSalary);

        // Sort employees by salary
        List<Employee> sortedEmployees = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary))
                .collect(Collectors.toList());

        System.out.println("\n===== EMPLOYEES SORTED BY SALARY =====");
        sortedEmployees.forEach(System.out::println);
    }
}
