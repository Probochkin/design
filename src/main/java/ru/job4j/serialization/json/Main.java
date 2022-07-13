package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class Main {
    public static void main(String[] args) {
        final Student person = new Student(false, 30, new ContactSt("+79005554784","Penza"),
                new String[] {"English", "History"}, "Petrov Ivan Nikolaevich");


        final Gson gson = new GsonBuilder().create();
        System.out.println(person);
        System.out.println(gson.toJson(person));


        final String studentsJson =
                "{"
                        + "sex:true,"
                        + "age:25,"
                        + "\"contactSt\":"
                        + "{"
                        + "\"phone\":\"89205554782\"" + ","
                        + "\"adress\":\"Ivanovo\""
                        + "},"
                        + "\"optional\":"
                        + "[\"English\", \"History\", \"Math\"],"
                        +"\"FIO\":\"Fedorov Petr Nikolaevich\""
                        + "}";
        final Student personMod = gson.fromJson(studentsJson, Student.class);
        System.out.println(personMod);
    }
}
