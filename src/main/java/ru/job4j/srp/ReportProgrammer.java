package ru.job4j.srp;

import java.util.function.Predicate;

public class ReportProgrammer implements Report{
    private Store store;

    public ReportProgrammer(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<html>").append(System.lineSeparator())
                .append("<head>").append(System.lineSeparator())
                .append("<title>").append("Report Programmer").append("</title>").append(System.lineSeparator())
                .append("</head>").append(System.lineSeparator())
                .append("<body>").append(System.lineSeparator())
                .append("<h1>").append("Name; Hired; Fired; Salary")
                .append("</h1>").append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append("<p>").append(employee.getName()).append("; ")
                    .append(employee.getHired()).append("; ")
                    .append(employee.getFired()).append("; ")
                    .append(employee.getSalary()).append("; ").append("</p>").append(System.lineSeparator());
        }
        text.append("</body>").append(System.lineSeparator())
                .append("</html>").append(System.lineSeparator());
        return text.toString();
    }
}
