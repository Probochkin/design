package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key)  {

          if (values.get(key) == null) {
              throw new IllegalArgumentException("Не корректный ключ");
        }
        return values.get(key);
    }

    private void parse(String[] args){
        for (String arg : args) {
          int index= arg.indexOf("=");
            String key = arg.substring(0,index);
            String value = arg.substring(index + 1);
            if (key.startsWith("-")) {
                key = key.substring(1);
            } else {
                throw new IllegalArgumentException("Аргумент должен начинаться с символа -");
            };

            if (value.startsWith("-")) {
                value = value.substring(1);
            }
            if (value.equals("") || key.equals("")) {
                throw new IllegalArgumentException("Аргументы не заполнены");
}

            values.put(key,value);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {



        ArgsName jvm = ArgsName.of(new String[] {"Xmx=11", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx") + jvm.values);

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
