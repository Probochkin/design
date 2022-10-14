package ru.job4j.lsp.storage;


import java.util.List;

public interface Store {

  public void add(Food food);

  public Food get(int index);

  public List<Food> getFoods();

  public void clear();

  boolean accept(Food food);
}
