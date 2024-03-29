package ru.job4j.isp.menu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import static org.assertj.core.api.Assertions.*;


public class SimpleMenuTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;
    private ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        assertThat(new Menu.MenuItemInfo("Сходить в магазин",
                List.of("Купить продукты"), STUB_ACTION, "1."))
                .isEqualTo(menu.select("Сходить в магазин").get());
        assertThat(new Menu.MenuItemInfo(
                "Купить продукты",
                List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."))
                .isEqualTo(menu.select("Купить продукты").get());
        assertThat (new Menu.MenuItemInfo(
                "Покормить собаку", List.of(), STUB_ACTION, "2."))
                .isEqualTo(menu.select("Покормить собаку").get());
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }
    @Test
    public void whenSelectReturnItemThenTrue() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        assertThat(menu.select("Сходить в магазин").get()).isEqualTo(new Menu.MenuItemInfo(
                "Сходить в магазин", List.of("Купить продукты"), STUB_ACTION, "1."
        ));
    }
    @Test
    public void whenSelectNullItem() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        assertThat(menu.select("купить продукты")).isEmpty();
    }

    @Test
    public void whenPrintMenu() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        SimpleMenuPrinter simpleMenuPrinter = new SimpleMenuPrinter();
        simpleMenuPrinter.print(menu);
        assertThat((output.toString())).isEqualTo("1.Сходить в магазин" + System.lineSeparator() +
                "    1.1.Купить продукты" + System.lineSeparator() +
                "        1.1.1.Купить хлеб" + System.lineSeparator() +
                "");

    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }
}