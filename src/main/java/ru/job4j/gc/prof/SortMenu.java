package ru.job4j.gc.prof;

import java.util.Random;
import java.util.Scanner;

public class SortMenu {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        RandomArray randomArray = new RandomArray(new Random());
        boolean run = true;
        while (run) {
            showmenu();

            int select = scanner.nextInt();
            if (select == 1) {
                randomArray.insert(250000);
                System.out.println("Массив создан");
            } else if (select == 2) {
                new BubbleSort().sort(randomArray);
                System.out.println("Массив отсортирован");
            } else if (select == 3) {
                new InsertSort().sort(randomArray);
                System.out.println("Массив отсортирован");
            } else if (select == 4) {
                new MergeSort().sort(randomArray);
                System.out.println("Массив отсортирован");
            } else if (select == 5) {
                run= false;
            }
        }
    }
    public static void showmenu() {
        System.out.println("Menu:");
        System.out.println("1.Создать массив" + System.lineSeparator() +
                "2.Сортировка пузырьком" + System.lineSeparator() +
                "3. Сортировка вставками" + System.lineSeparator() +
                "4. Сортировка слиянием" + System.lineSeparator() +
                "5. Выход");
        System.out.println("Выберете пункт меню:");
    }
}
