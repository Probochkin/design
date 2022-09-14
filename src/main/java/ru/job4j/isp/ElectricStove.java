package ru.job4j.isp;
/*
Пример нарушения принципа ISP.
Класс ElectricStove не использует метод giveGas по этому его пришлось загулишть.


 */
public class ElectricStove implements Stove {
    @Override
    public void on() {
        System.out.println("Stove is on");
    }

    @Override
    public int maxTemperature() {
        return 200;
    }

    @Override
    public void giveGas() {
        throw new IllegalArgumentException("There is no gas in electric stove");
    }

    @Override
    public String infoPanel(String info) {
        return info;
    }

    @Override
    public void off() {
        System.out.println("Stove is off");
    }
}
