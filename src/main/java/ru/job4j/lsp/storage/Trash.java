package ru.job4j.lsp.storage;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {
    List<Food> foods = new ArrayList<>();
    @Override
    public void add(Food food) {
        foods.add(food);
    }

    @Override
    public boolean accept(Food food) {
        boolean bool = false;
        int shelLife = food.shellLife();
        if (shelLife >= 100) {
            bool = true;
        }
        return bool;
    }

    @Override
    public Food get(int index) {
        return foods.get(index);
    }
}
