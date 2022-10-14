package ru.job4j.lsp.storage;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private  List<Store> storages;

    public ControlQuality(List<Store> storages) {
        this.storages = storages;
    }

    public  void resort() {
        List<Food> temp = new ArrayList<>();
        for (Store store : storages) {
            temp.addAll(store.getFoods());
            store.clear();
        }
        for (Food food : temp) {
            inventory(food);
        }
    }
    public  void inventory(Food food) {
        for (Store store : storages) {
            if (store.accept(food)) {
                store.add(food);
                break;
            }
        }
    }


}
