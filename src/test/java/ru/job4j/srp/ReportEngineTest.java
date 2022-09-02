package ru.job4j.srp;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static ru.job4j.srp.ReportEngine.DATE_FORMAT;
import java.util.Calendar;

public class ReportEngineTest {
    @Test
    public void whenReportJson() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petr", now, now, 200);
        store.add(worker1);
        store.add(worker2);
        Report repJson = new ReportJson(store);
        StringBuilder expect = new StringBuilder()
                .append("[{\"name\":\"").append(worker1.getName())
                .append("\",\"hired\":{\"year\":").append(now.get(Calendar.YEAR))
                .append(",\"month\":").append(now.get(Calendar.MONTH))
                .append(",\"dayOfMonth\":").append(now.get(Calendar.DAY_OF_MONTH))
                .append(",\"hourOfDay\":").append(now.get(Calendar.HOUR_OF_DAY))
                .append(",\"minute\":").append(now.get(Calendar.MINUTE))
                .append(",\"second\":").append(now.get(Calendar.SECOND))
                .append("},\"fired\":{\"year\":").append(now.get(Calendar.YEAR))
                .append(",\"month\":").append(now.get(Calendar.MONTH))
                .append(",\"dayOfMonth\":").append(now.get(Calendar.DAY_OF_MONTH))
                .append(",\"hourOfDay\":").append(now.get(Calendar.HOUR_OF_DAY))
                .append(",\"minute\":").append(now.get(Calendar.MINUTE))
                .append(",\"second\":").append(now.get(Calendar.SECOND))
                .append("},\"salary\":").append(worker1.getSalary())
                .append("},{\"name\":\"").append(worker2.getName())
                .append("\",\"hired\":{\"year\":").append(now.get(Calendar.YEAR))
                .append(",\"month\":").append(now.get(Calendar.MONTH))
                .append(",\"dayOfMonth\":").append(now.get(Calendar.DAY_OF_MONTH))
                .append(",\"hourOfDay\":").append(now.get(Calendar.HOUR_OF_DAY))
                .append(",\"minute\":").append(now.get(Calendar.MINUTE))
                .append(",\"second\":").append(now.get(Calendar.SECOND))
                .append("},\"fired\":{\"year\":").append(now.get(Calendar.YEAR))
                .append(",\"month\":").append(now.get(Calendar.MONTH))
                .append(",\"dayOfMonth\":").append(now.get(Calendar.DAY_OF_MONTH))
                .append(",\"hourOfDay\":").append(now.get(Calendar.HOUR_OF_DAY))
                .append(",\"minute\":").append(now.get(Calendar.MINUTE))
                .append(",\"second\":").append(now.get(Calendar.SECOND))
                .append("},\"salary\":").append(worker2.getSalary()).append("}]");
        assertThat(repJson.generate(em -> true)).isEqualTo(expect.toString());
    }
    @Test
    public void whenXmlGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportXml xml = new ReportXml(store);
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>").append(System.lineSeparator())
                .append("<employers>").append(System.lineSeparator())
                .append("<employer>").append(System.lineSeparator())
                .append("<name>").append(worker.getName()).append("</name>").append(System.lineSeparator())
                .append("<hired>").append(worker.getHired()).append("</hired>").append(System.lineSeparator())
                .append("<fired>").append(worker.getFired()).append("</fired>").append(System.lineSeparator())
                .append("<salary>").append(worker.getSalary()).append("</salary>").append(System.lineSeparator())
                .append("</employer>").append(System.lineSeparator())
                .append("</employers>").append(System.lineSeparator());
        assertThat(xml.generate(em -> true)).isEqualTo(expect.toString());
    }
    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
    @Test
    public void whenProgrammerGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petr", now, now, 110);
        Employee worker3 = new Employee("Sam", now, now, 120);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report engine = new ReportProgrammer(store);
        StringBuilder expect =  new StringBuilder();
        expect.append("<html>").append(System.lineSeparator())
                .append("<head>").append(System.lineSeparator())
                .append("<title>").append("Report Programmer").append("</title>").append(System.lineSeparator())
                .append("</head>").append(System.lineSeparator())
                .append("<body>").append(System.lineSeparator())
                .append("<h1>").append("Name; Hired; Fired; Salary")
                .append("</h1>").append(System.lineSeparator());
        expect.append("<p>").append(worker1.getName()).append("; ")
                    .append(worker1.getHired()).append("; ")
                    .append(worker1.getFired()).append("; ")
                    .append(worker1.getSalary()).append("; ").append("</p>").append(System.lineSeparator());
        expect.append("<p>").append(worker2.getName()).append("; ")
                .append(worker2.getHired()).append("; ")
                .append(worker2.getFired()).append("; ")
                .append(worker2.getSalary()).append("; ").append("</p>").append(System.lineSeparator());
        expect.append("<p>").append(worker3.getName()).append("; ")
                .append(worker3.getHired()).append("; ")
                .append(worker3.getFired()).append("; ")
                .append(worker3.getSalary()).append("; ").append("</p>").append(System.lineSeparator());
        expect.append("</body>").append(System.lineSeparator())
                .append("</html>").append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());

    }
    @Test
    public void whenHRGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker1 = new Employee("Victor", now, now, 300);
        Employee worker2 = new Employee("Petr", now, now, 200);
        store.add(worker);
        store.add(worker1);
        store.add(worker2);
        ReportHR hr = new ReportHR(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary")
                .append(System.lineSeparator())
                .append(worker1.getName()).append("; ")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker2.getName()).append("; ")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker.getName()).append("; ")
                .append(worker.getSalary()).append(";");
        assertThat(hr.generate(em -> true)).isEqualTo(expect.toString());
    }
    @Test
    public void whenBookerGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker1 = new Employee("Victor", now, now, 300);
        Employee worker2 = new Employee("Petr", now, now, 200);
        store.add(worker);
        store.add(worker1);
        store.add(worker2);
        ReportBooker booker = new ReportBooker(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary dollars")
                .append(System.lineSeparator())
                .append(worker.getName()).append("; ")
                .append(worker.getHired()).append("; ")
                .append(worker.getFired()).append("; ")
                .append(worker.getSalary() / 60.24).append("; ")
                .append(System.lineSeparator())
                .append(worker1.getName()).append("; ")
                .append(worker1.getHired()).append("; ")
                .append(worker1.getFired()).append("; ")
                .append(worker1.getSalary() / 60.24).append("; ")
                .append(System.lineSeparator())
                .append(worker2.getName()).append("; ")
                .append(worker2.getHired()).append("; ")
                .append(worker2.getFired()).append("; ")
                .append(worker2.getSalary() / 60.24).append("; ");
        assertThat(booker.generate(em -> true)).isEqualTo(expect.toString());
    }

}