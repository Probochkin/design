package ru.job4j.lsp.parking;

public class Truck implements Transport {
    int size;
    String number;
    @Override
    public int getSize() {
        return size;
    }
   public Truck(String number, int size) {
      this.size = size;
      this.number = number;
   }
}
