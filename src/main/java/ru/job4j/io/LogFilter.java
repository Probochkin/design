package ru.job4j.io;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> result = new LinkedList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            result = in.lines().filter(x -> x.contains(" 404 ")).collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            for(String element : log) {
                out.println(element);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        log.forEach(System.out :: println);
        save(log,"404.txt");

    }
}
