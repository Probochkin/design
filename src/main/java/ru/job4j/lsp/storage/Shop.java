package ru.job4j.lsp.storage;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {
    List<Food> foods = new ArrayList<>();
    @Override
    public void add(Food food) {
        foods.add(food);
    }

    @Override
    public boolean accept(Food food) {
        boolean bool = false;
        int shelLife = food.shellLife();
        if (shelLife >= 25 && shelLife <= 75) {
            bool = true;
        }
     else if (shelLife > 75 & shelLife < 100) {
         Double price =  food.getPrice();
            food.setPrice(price -(price *  food.getDiscount()));
            bool = true;
    }
        return bool;
    }

    @Override
    public Food get(int index) {
       return foods.get(index);
    }
}
