package ru.job4j.srp;

import java.util.function.Predicate;

public class ReportBooker implements Report{
    private Store store;

    public ReportBooker(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary dollars");
        for (Employee employee : store.findBy(filter)) {
            text.append(System.lineSeparator()).append(employee.getName()).append("; ")
                    .append(employee.getHired()).append("; ")
                    .append(employee.getFired()).append("; ")
                    .append(employee.getSalary() / 60.24  ).append("; ");
        }

        return text.toString();
    }
}
