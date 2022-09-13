package ru.job4j.lsp.storage;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private  List<Store> storages;

    public ControlQuality(List<Store> storages) {
        this.storages = storages;
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
