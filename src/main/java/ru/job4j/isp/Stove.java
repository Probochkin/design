package ru.job4j.isp;

public interface Stove {

    void on();

    int maxTemperature();

    void giveGas();

    String infoPanel(String info);

    void off();
}
