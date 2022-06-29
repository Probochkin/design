package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DuplicatesFinder {

    private static void collector(DuplicatesVisitor visitor) {
        var map = visitor.getMap();
        if (!map.isEmpty()) {
            map.entrySet().stream().filter(k -> k.getValue().size() > 1)
                    .forEach(filePropertyListEntry -> {
                        System.out.println(filePropertyListEntry.getKey().toString());
                        filePropertyListEntry.getValue().forEach(System.out::println);
                    });
        }
    }
    public static void main(String[] args)  {
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        try {
            Files.walkFileTree(Paths.get("./data"),
                    visitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        collector(visitor);
    }
    }

