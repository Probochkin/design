package ru.job4j.srp;

import com.google.gson.GsonBuilder;


import java.util.function.Predicate;

public class ReportJson implements Report {
    private Store store;

    public ReportJson(Store store) {
        this.store = store;
    }
    @Override
    public String generate(Predicate<Employee> filter) {
       return new GsonBuilder().create().toJson(store.findBy(filter));
    }

}
