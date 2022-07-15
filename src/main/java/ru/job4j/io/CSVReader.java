package ru.job4j.io;

import ru.job4j.filefinder.ArgsName;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    private static List<Integer> columns = new ArrayList<>();

    public static void handle(ArgsName argsName) throws Exception {
        String[] filter = getFilters(argsName);
        String delimiter = argsName.get("delimiter");
        var scanner = new Scanner((new File (argsName.get("path"))));
        if (argsName.get("out").equals("stdout")) {
            String[] line = scanner.nextLine().split(delimiter);
            columns = columnsfinder(line,filter);
            System.out.println(joiner(line));
            while (scanner.hasNext()) {
                  String out = joiner(scanner.nextLine().split(delimiter)).toString();
                  if (!out.isBlank()) {
                      System.out.println(out);
                }}
        } else {
            try (PrintWriter pw = new PrintWriter(new FileWriter(argsName.get("out"), Charset.forName("UTF-8")))) {
                String[] line = scanner.nextLine().split(delimiter);
                columns = columnsfinder(line,filter);
                pw.write(joiner(line).toString());
                pw.write(System.lineSeparator());
                while (scanner.hasNext()) {
                    String out = joiner(scanner.nextLine().split(delimiter)).toString();
                    if (!out.isBlank()) {
                        pw.write(out);
                        pw.write(System.lineSeparator());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Integer> columnsfinder(String[] line , String[] filter) {
        for (int index = 0; index < line.length; index++) {
            for (int i = 0; i < filter.length; i ++) {
                if (line[index].equals(filter[i])) {
                    columns.add(index);
                }
            }
        }
        return columns;
    }

    public static String[] getFilters (ArgsName argsName) {
        return  argsName.get("filter").split(",");
    }
    public static StringJoiner joiner (String [] line) {
        StringJoiner joiner = new StringJoiner(";");
        if (line.length > 0) {

            columns.forEach(column -> joiner.add(line[column]));
        }
        return joiner;
    }
    public static void validate(ArgsName argsName) {

        if (argsName.get("path").matches("^[A-Z]{1}:[\\a-zA-Za-яА-Я0-9]*$") ||!Files.exists(Paths.get(argsName.get("path")))) {
            throw new IllegalArgumentException("Файла не существует или указан не корректный путь");
        }
        if (argsName.get("filter").isBlank()) {
            throw new IllegalArgumentException("Столбцы не указаны");
        }
        if (argsName.get("delimiter").isBlank()) {
            throw new IllegalArgumentException("Разделитель не указан");
        }
        if (argsName.get("out").isBlank()) {
            throw new IllegalArgumentException("Путь к файлу файлу вывода");
        }
    }
    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException("Аргументы не заполнены.Аргументы должны иметь вид : -path= -delimiter=  -out=stdout= -filter=");
        }
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
          handle(argsName);

    }
}