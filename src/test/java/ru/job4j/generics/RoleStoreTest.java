package ru.job4j.generics;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class RoleStoreTest {

    @Test
    void whenAddAndFindThenUsernameIsIvan() {
        RoleStore rolestore = new RoleStore();
        rolestore.add(new Role("1", "Ivan"));
        Role result = rolestore.findById("1");
        assertThat(result.getRole()).isEqualTo("Ivan");
    }

    @Test
    void whenAddAndFindThenUserIsNull() {
        RoleStore rolestore = new RoleStore();
        rolestore.add(new Role("1", "Ivan"));
        Role result = rolestore.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindUsernameIsIvan() {
        RoleStore rolestore = new RoleStore();
        rolestore.add(new Role("1", "Ivan"));
        rolestore.add(new Role("1", "Maxim"));
        Role result = rolestore.findById("1");
        assertThat(result.getRole()).isEqualTo("Ivan");
    }

    @Test
    void whenReplaceThenUsernameIsMaxim() {
        RoleStore rolestore = new RoleStore();
        rolestore.add(new Role("1", "Ivan"));
        rolestore.replace("1", new Role("1", "Maxim"));
        Role result = rolestore.findById("1");
        assertThat(result.getRole()).isEqualTo("Maxim");
    }

    @Test
    void whenNoReplaceUserThenNoChangeUsername() {
        RoleStore rolestore = new RoleStore();
        rolestore.add(new Role("1", "Ivan"));
        rolestore.replace("10", new Role("10", "Maxim"));
        Role result = rolestore.findById("1");
        assertThat(result.getRole()).isEqualTo("Ivan");
    }

    @Test
    void whenDeleteUserThenUserIsNull() {
        RoleStore rolestore = new RoleStore();
        rolestore.add(new Role("1", "Ivan"));
        rolestore.delete("1");
        Role result = rolestore.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteUserThenUsernameIsIvan() {
        RoleStore rolestore = new RoleStore();
        rolestore.add(new Role("1", "Ivan"));
        rolestore.delete("10");
        Role result = rolestore.findById("1");
        assertThat(result.getRole()).isEqualTo("Ivan");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore rolestore = new RoleStore();
        rolestore.add(new Role("1", "Ivan"));
        boolean rsl = rolestore.replace("1", new Role("1", "Maxim"));
        assertThat(rsl).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore rolestore = new RoleStore();
        rolestore.add(new Role("1", "Ivan"));
        boolean rsl = rolestore.replace("10", new Role("10", "Maxim"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        RoleStore rolestore = new RoleStore();
        rolestore.add(new Role("1", "Ivan"));
        boolean rsl = rolestore.delete("1");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        RoleStore rolestore = new RoleStore();
        rolestore.add(new Role("1", "Ivan"));
        boolean rsl = rolestore.delete("2");
        assertThat(rsl).isFalse();
    }

}