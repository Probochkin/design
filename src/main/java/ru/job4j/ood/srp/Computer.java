package ru.job4j.ood.srp;


/**
 * Класс описывает намеренное нарушение первого принципа SOLID
 * SRP (Single Responsibility Principle), для демонстрации
 *
 */
public class Computer {
    private String model;
    private int ssd;
    private int memory;


    public Computer(String model, int ssd, int memory) {
        this.model = model;
        this.ssd = ssd;
        this.memory = memory;
    }

    public void upMemory(int size) {
        memory += size;
    }

    public void upSsd(int size) {
        ssd += size;
    }

    public void createSaveFile(String path) {
        System.out.println("Файл создан и сохранен");
    }
}
