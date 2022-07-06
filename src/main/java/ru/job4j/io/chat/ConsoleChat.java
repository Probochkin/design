package ru.job4j.io.chat;

import java.io.*;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        boolean run = true;
       Scanner scanner = new Scanner(System.in);
        List<String> log = new LinkedList<>();
        System.out.println("Введите вопрос :");
        String question = scanner.nextLine();
        List<String> phrases = new LinkedList<>();
        while (run) {
            if (question.equals(STOP)) {
                System.out.println("Бот остановлен, если хотите продолжить напишите : "+ CONTINUE);
                while (!question.equals(CONTINUE) && !question.equals(OUT)) {
                    log.add(question);
                   question = scanner.nextLine();
                }
                if (question.equals(OUT)){
                    System.out.println("Бот закончил работу");
                    log.add(question);
                    run = false;
                    saveLog(log);
                }
                log.add(question);
                System.out.println("Бот запущен напишите вопрос :");
                question = scanner.nextLine();
            }
            if (OUT.equals(question)) {
                System.out.println("Бот закончил работу");
                log.add(question);
                saveLog(log);
                run = false;
            } else {
                    int max = phrases.size() - 1;
                    phrases = readPhrases();
                    log.add(question);
                    String answer = phrases.get((int) (Math.random() * max));
                    log.add(answer);
                    System.out.println(answer);
                    question = scanner.nextLine();
            }
        }
    }
    private List<String> readPhrases() {

        List<String> result = new LinkedList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers, Charset.forName("UTF-8")))) {
            result = in.lines().collect(Collectors.toList());

    }
     catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(path, Charset.forName("UTF-8"), true))) {
            pw.println(log);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("C:\\projects\\job4j_design\\data\\log.txt", "C:\\projects\\job4j_design\\data\\ansers.txt");
        cc.run();
    }
}
