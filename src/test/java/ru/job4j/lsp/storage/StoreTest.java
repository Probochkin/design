package ru.job4j.lsp.storage;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


class StoreTest {
    @Test
    public void whenProductToWarehouse() {
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        List<Store> storages = List.of(warehouse, shop, trash);
        ControlQuality controllQuality = new ControlQuality(storages);
        LocalDate now = LocalDate.now();
        Date created = java.sql.Date.valueOf(now.minusDays(2));
        Date expaired = java.sql.Date.valueOf(now.plusDays(8));
        Food food = new Food("Milk", expaired, created, 56.0, 0);
        controllQuality.inventory(food);
        assertThat(food, is(warehouse.get(0)));
    }

    @Test
    public void whenProductToShop() {
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        List<Store> storages = List.of(warehouse, shop, trash);
        ControlQuality controllQuality = new ControlQuality(storages);
        LocalDate now = LocalDate.now();
        Date created = java.sql.Date.valueOf(now.minusDays(4));
        Date expaired = java.sql.Date.valueOf(now.plusDays(6));
        Food food = new Food("Milk", expaired, created, 56.0, 0);
        controllQuality.inventory(food);
        assertThat(food, is(shop.get(0)));
    }

    @Test
    public void whenProducttoShopDiscount() {
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        List<Store> storages = List.of(warehouse, shop, trash);
        ControlQuality controllQuality = new ControlQuality(storages);
        LocalDate now = LocalDate.now();
        Date created = java.sql.Date.valueOf(now.minusDays(8));
        Date expaired = java.sql.Date.valueOf(now.plusDays(2));
        Food food = new Food("Milk", expaired, created, 56.0, 0);
        controllQuality.inventory(food);
        assertThat(food, is(shop.get(0)));
    }

    @Test
    public void whenProductToTrash() {
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        List<Store> storages = List.of(warehouse, shop, trash);
        ControlQuality controllQuality = new ControlQuality(storages);
        LocalDate now = LocalDate.now();
        Date created = java.sql.Date.valueOf(now.minusDays(10));
        Date expaired = java.sql.Date.valueOf(now.minusDays(2));
        Food food = new Food("Milk", expaired, created, 56.0, 0);
        controllQuality.inventory(food);
        assertThat(food, is(trash.get(0)));
    }

}