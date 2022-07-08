package ru.job4j.io.chat;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
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
        List<String> log = new ArrayList<>();
        System.out.println("Введите сообщение:");
        String message = scanner.nextLine();
        List<String> answers = readPhrases();
        int max = answers.size() - 1;
        while (run) {
            if (STOP.equals(message)) {
                System.out.println("Чат остановлен, если хотите продолжить напишите : "+ CONTINUE);
                while (!CONTINUE.equals(message) && !OUT.equals(message)) {
                    log.add(message);
                    message = scanner.nextLine();
                }
                if (OUT.equals(message)){
                    System.out.println("Чат закончил работу");
                    log.add(message);
                    break;
                }
                log.add(message);
                System.out.println("Чат запущен напишите сообщение :");
                message = scanner.nextLine();
            }
            if (OUT.equals(message)) {
                System.out.println("Чат завершил работу");
                log.add(message);
                run = false;
            } else {
                log.add(message);
                String answer = answers.get((int) (Math.random() * max));
                log.add(answer);
                System.out.println(answer);
                message = scanner.nextLine();
            }
        }
        saveLog(log);
    }
    private List<String> readPhrases() {
        List<String> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers, Charset.forName("UTF-8")))) {
            result = in.lines().collect(Collectors.toList());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(path, Charset.forName("UTF-8"), true))) {
            for (String l : log) {
                pw.println(l);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("G:\\Chupin\\Java\\data\\log.txt", "G:\\Chupin\\Java\\data\\answers.txt");
        cc.run();
    }
}
