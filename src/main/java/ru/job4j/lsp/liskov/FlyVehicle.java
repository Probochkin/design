package ru.job4j.lsp.liskov;
/*
Пример нарушения нарушение принципа LSP
Предусловия (Preconditions) не могут быть усилены в подклассе
 */
public class FlyVehicle {
    int high;

    public void fly() {
        if (high < 200) {
            throw  new IllegalArgumentException("Helicopter cannot fly So low!!");
        }
        System.out.println("Im flying on a " + high + " metres");
    }

}

class Helicopter extends FlyVehicle {
    int high;

    public void fly() {
        if (high < 1000) { /* условие усилено */
            throw  new IllegalArgumentException("Helicopter cannot fly So low!!");
        }
        System.out.println("Im flying on a " + high + " metres");
    }
}


class TestClass {
    public static void main(String[] args) {
        FlyVehicle fl = new Helicopter();
        fl.high = 999;
        fl.fly();
    }
}
