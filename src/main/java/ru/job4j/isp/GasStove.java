package ru.job4j.isp;

public class GasStove implements Stove {
    @Override
    public void on() {
        System.out.println("Stove is on");
    }

    @Override
    public int maxTemperature() {
        return 150;
    }

    @Override
    public void giveGas() {
        System.out.println("Stove supplies gas");
    }

    @Override
    public String infoPanel(String info) {
        return String.valueOf(new IllegalArgumentException("Gas stove has no info panel"));
    }

    @Override
    public void off() {
        System.out.println("Stove is off");
    }
}
