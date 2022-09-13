package ru.job4j.lsp.storage;


public interface Store {

  public void add(Food food);
  public boolean accept(Food food);
  public Food get(int index);
}
