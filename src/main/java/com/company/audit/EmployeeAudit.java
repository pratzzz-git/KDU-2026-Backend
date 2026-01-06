package com.company.audit;

import java.util.List;
import java.util.function.Predicate;

public class EmployeeAudit {

    // Reusable filtering method using functional interface
    public static List<Employee> filterEmployees(
            List<Employee> employees,
            Predicate<Employee> condition) {

        return employees.stream()
                .filter(condition)
                .toList();
    }

    public static void main(String[] args) {

        List<Employee> employees = Employee.getSampleData();

        // 1. High-Earning Engineers
        List<Employee> highEarningEngineers =
                filterEmployees(
                        employees,
                        e -> e.getSalary() > 70000 &&
                                e.getDepartment().equals("ENGINEERING")
                );

        System.out.println("High-Earning Engineers:");
        highEarningEngineers.forEach(System.out::println);

        // 2. Standardized Names
        List<String> upperCaseNames =
                employees.stream()
                        .map(e -> e.getName().toUpperCase())
                        .toList();

        System.out.println("\nStandardized Employee Names:");
        upperCaseNames.forEach(System.out::println);

        // 3. Total Salary Budget
        double totalSalary =
                employees.stream()
                        .map(Employee::getSalary)
                        .reduce(0.0, Double::sum);

        System.out.println("\nTotal Annual Salary Budget: $" + totalSalary);
    }
}
