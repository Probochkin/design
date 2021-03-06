package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;




public class Main {
    public static void main(String[] args) {
        final Student student = new Student(false, 30,"Petrov Ivan Nikolaevich", new ContactSt("+79005554784","Penza"),
                new String[] {"English", "History"});


        final Gson gson = new GsonBuilder().create();
       System.out.println(student);
        System.out.println(gson.toJson(student));


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
        /* JSONObject из json-строки строки */
        JSONObject jsonContact = new JSONObject(studentsJson);
        System.out.println(jsonContact);
        System.out.println(jsonContact.toString());

    }
}
