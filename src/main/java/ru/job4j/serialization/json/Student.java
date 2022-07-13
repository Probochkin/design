package ru.job4j.serialization.json;

import ru.job4j.serialization.Contact;

import java.util.Arrays;

public class Student {
    private final boolean sex;
    private final int age;
    private final ContactSt contactSt;
    private final String[] optional;
    private final String FIO;
    public Student(boolean sex, int age, ContactSt contactSt, String[] optional,String FIO) {
        this.sex = sex;
        this.age = age;
        this.contactSt = contactSt;
        this.optional = optional;
        this.FIO = FIO;
    }

    @Override
    public String toString() {
        return "{"
                + "\"sex\":" + sex
                + ",\"age\":" +  age
                + ",\"contactSt\":"  + contactSt
                + ",\"optional\":" +  Arrays.toString(optional)
                + ",\"FIO\":" +  "\"" + FIO + "\""
                + '}';
    }
}
