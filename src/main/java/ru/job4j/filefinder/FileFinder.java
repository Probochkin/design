package ru.job4j.filefinder;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileFinder {
    public static void main(String[] args)  {
        if (args.length != 4) {
            throw new IllegalArgumentException("Incorrect arguments. -d=folder path -n=file name, mask, or regular expression " +
                    "-t=search type: \"mask\" search by mask, \"name\" by full name match, \"regex\" by regular expression -o= name of the file in which to write the results");
        }
        ArgsName arguments = ArgsName.of(args);
        validate(arguments);
        Path path = Path.of(arguments.get("d"));
        String fileType = arguments.get("n");
        String searchType = arguments.get("t");
        String resultFile = arguments.get("o");


        FileVisitor fileVisitor = new FileVisitor();
        fileVisitor.setSearchType(searchType);
        fileVisitor.setFileType(fileType);
        search(path, fileVisitor);
        fileVisitor.getList().forEach(System.out::println);
        try (PrintWriter pw = new PrintWriter(new FileWriter(resultFile, Charset.forName("UTF-8")))) {
            fileVisitor.getList().forEach(file -> {
                pw.write(file.toString());
                pw.write(System.lineSeparator());
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static FileVisitor search(Path path, FileVisitor fileVisitor) {
        try {
            Files.walkFileTree(path,
                    fileVisitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileVisitor;
    }

    private static void validate(ArgsName arguments) {
        if (!Files.exists(Path.of(arguments.get("d")))) {
            throw new IllegalArgumentException("The directory does not exist");
        }
        if ("mask".equals(arguments.get("t"))) {
            if (arguments.get("n").contains("<")
                    || arguments.get("n").contains(">")
                    || arguments.get("n").contains("\\")
                    || arguments.get("n").contains("/")
                    || arguments.get("n").contains("|")
                    || arguments.get("n").contains("<")
                    || arguments.get("n").contains("\"")
                    || arguments.get("n").contains(";")) {
                throw new IllegalArgumentException("File mask incorrectly");
            }
        } else if ("name".equals(arguments.get("t"))) {
            if (!arguments.get("n").matches("\\w+[.]\\w+")) {
                throw new IllegalArgumentException("The file name is specified incorrectly");
            }
        } else if (!"regex".equals(arguments.get("t"))) {
            throw new IllegalArgumentException("Type search incorrectly");
        }

        if (!arguments.get("o").endsWith(".txt")) {
            throw new IllegalArgumentException("The name of the file in which to write the results is not specified correctly");
        }
    }
}
