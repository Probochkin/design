package ru.job4j.lsp.storage;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements  Store {
    List<Food> foods = new ArrayList<>();
    @Override
    public void add(Food food) {
        foods.add(food);
    }

    @Override
    public boolean accept(Food food) {
        boolean bool = false;
        int shelLife = food.shellLife();
        if (shelLife < 25) {
            bool = true;
        }
        return bool;
    }

    @Override
    public Food get(int index) {
       return foods.get(index);
    }

    @Override
    public List<Food> getFoods() {
        return foods;
    }

    @Override
    public void clear() {
        foods.clear();
    }
}
