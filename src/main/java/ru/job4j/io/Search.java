package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args)  {
        if (args[0] == null || args.length != 2) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar search-1.jar ROOT_FOLDER File_Type.");
        }
        if (args[1] == null) {
            throw new IllegalArgumentException("File type is null. Usage java -jar search-1.jar ROOT_FOLDER File_Type.");
        }
        validate(args);
        String path = args[0];
        String fileTipe = args[1];
        Path start = Paths.get(path).toAbsolutePath();
        search(start, p -> p.toFile().getName().endsWith(fileTipe)).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) {
        SearchFiles searcher = new SearchFiles(condition);
        try {
            Files.walkFileTree(root, searcher);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return searcher.getPaths();
    }

    public static void validate(String[] args) {

        if (args[0].matches("^[A-Z]{1}:[\\a-zA-Za-яА-Я0-9]*$") || !Files.exists(Paths.get(args[0]))) {
            throw new IllegalArgumentException("The root folder is specified incorrectly");
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("The file type is specified incorrectly");
        }

    }
}
