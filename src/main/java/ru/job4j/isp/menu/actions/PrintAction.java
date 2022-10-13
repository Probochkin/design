package ru.job4j.isp.menu.actions;

import ru.job4j.isp.menu.Menu;
import ru.job4j.isp.menu.SimpleMenuPrinter;

public class PrintAction implements AppAction {
    public static final String NAME = "Вывод списка задач.";
    private Menu menu;

    public PrintAction(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean toDoAction() {
        new SimpleMenuPrinter().print(menu);
        return true;
    }
}
