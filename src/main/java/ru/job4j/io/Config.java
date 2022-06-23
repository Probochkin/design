package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().filter(x -> !x.startsWith("#") && !x.startsWith(System.lineSeparator())).forEach(line -> {
                if (line.indexOf("= ") == -1 && line.indexOf(" =") == -1 && line.indexOf("=") != -1 && line.length() > 2 ) {

                    int index = line.indexOf("=");
                    if (index != -1 && !line.startsWith("#")) {
                        values.put(line.substring(0, index), line.substring(index + 1));
                    }
                } else {
                    throw new IllegalArgumentException("The key=value pattern is broken in the file");
                }
            }
        );

            ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);

    }


    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }

}