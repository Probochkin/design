package ru.job4j.generics;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class UserStoreTest {

    @Test
    void whenAddAndFindThenUsernameIsIvan() {
        UserStore store = new UserStore();
        store.add(new User("1", "Ivan"));
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Ivan");
    }

    @Test
    void whenAddAndFindThenUserIsNull() {
        UserStore store = new UserStore();
        store.add(new User("1", "Ivan"));
        User result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindUsernameIsIvan() {
        UserStore store = new UserStore();
        store.add(new User("1", "Ivan"));
        store.add(new User("1", "Maxim"));
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Ivan");
    }

    @Test
    void whenReplaceThenUsernameIsMaxim() {
        UserStore store = new UserStore();
        store.add(new User("1", "Ivan"));
        store.replace("1", new User("1", "Maxim"));
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Maxim");
    }

    @Test
    void whenNoReplaceUserThenNoChangeUsername() {
        UserStore store = new UserStore();
        store.add(new User("1", "Ivan"));
        store.replace("10", new User("10", "Maxim"));
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Ivan");
    }

    @Test
    void whenDeleteUserThenUserIsNull() {
        UserStore store = new UserStore();
        store.add(new User("1", "Ivan"));
        store.delete("1");
        User result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteUserThenUsernameIsIvan() {
        UserStore store = new UserStore();
        store.add(new User("1", "Ivan"));
        store.delete("10");
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Ivan");
    }

    @Test
    void whenReplaceOkThenTrue() {
        UserStore store = new UserStore();
        store.add(new User("1", "Ivan"));
        boolean rsl = store.replace("1", new User("1", "Maxim"));
        assertThat(rsl).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        UserStore store = new UserStore();
        store.add(new User("1", "Ivan"));
        boolean rsl = store.replace("10", new User("10", "Maxim"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        UserStore store = new UserStore();
        store.add(new User("1", "Ivan"));
        boolean rsl = store.delete("1");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        UserStore store = new UserStore();
        store.add(new User("1", "Ivan"));
        boolean rsl = store.delete("2");
        assertThat(rsl).isFalse();
    }
}