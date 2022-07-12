package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key)  {

          if (values.get(key) == null) {
              throw new IllegalArgumentException("Не корректный ключ " + key);
        }
        return values.get(key);
    }

    private void validate(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("Отсутствуют параметры");
        }
        for (String arg : args) {
            int index = arg.indexOf("=");
            if (index == -1) {
                throw new IllegalArgumentException("Отсутствует символ =");
            }
            String key;
            String value = arg.substring(index + 1);
            if (arg.startsWith("-")) {
                key = arg.substring(1,index);
            } else {
                throw new IllegalArgumentException("Ключ должен начинаться с символа -");
            }
            if (value.isBlank() || key.isBlank()) {
                throw new IllegalArgumentException("Ключ/значение не заполнены");
            }
        }
    }
    private void parse(String[] args) {
            validate(args);
         for (String arg : args) {
            int index= arg.indexOf("=");
            String key = arg.substring(1,index);
            String value = arg.substring(index + 1);
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
