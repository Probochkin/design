package ru.job4j.lsp.parking;

public class Car implements Transport {
    String number;
    public Car(String number) {
        this.number = number;
    }
    @Override
    public int getSize() {
        return 1;
    }
}
