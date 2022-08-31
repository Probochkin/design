package ru.job4j.tdd;

import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import static org.assertj.core.api.Assertions.*;
import java.util.Calendar;
import java.util.List;


@Disabled
public class CinemaTest {

    @Test
    public void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    @Test
    public void whenFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions).isNull();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenInvalidPlace() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        cinema.buy(account, -1, 1, date);
    }
    /**
     * Тестирование покупки билета на границе вводимых данных. В методе пытаюсь
     * купить 0 ряд и место.
     */
    @Test
    public void buyBorder() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2022, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 0, 0, date);
        assertThat(ticket).is(null);
    }

    /**
     * Тестирование с указанием прошедшей даты.
     */
    @Test(expected = IllegalArgumentException.class)
    public void buyDate() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2005, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).is(null);

    }
    /**
     * Тестирование на отсутствие одного или несколько параметров.
     */
    @Test(expected = IllegalArgumentException.class)
    public void buyNull() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2005, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, null, date);
    }
}
