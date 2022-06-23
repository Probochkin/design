package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username"),is("postgres"));
        assertThat(config.value("hibernate.connection.driver_class"),is("org.postgresql.Driver"));
        assertThat(config.value("hibernate.connection.password"),is("123"));
    }
    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username"),is("postgres"));
        assertThat(config.value("hibernate.connection.driver_class"),is("org.postgresql.Driver"));
        assertThat(config.value("hibernate.connection.password"),is("123"));
    }
    @Test (expected = IllegalArgumentException.class)
    public void whenPairWithIllegalArgument() {
        String path = "./data/pair_with_illegal_argument.properties";
        Config config = new Config(path);
        config.load();

    }
    @Test (expected = IllegalArgumentException.class)
    public void whenPairWithIllegalArgument2()  {
        String path = "./data/pair_with_illegal_argument2.properties";
        Config config = new Config(path);
        config.load();

    }
    @Test (expected = IllegalArgumentException.class)
    public void whenPairWithIllegalArgument3() {
        String path = "./data/pair_with_illegal_argument3.properties";
        Config config = new Config(path);
        config.load();

    }
    @Test (expected = IllegalArgumentException.class)
    public void whenPairWithIllegalArgument4() {
        String path = "./data/pair_with_illegal_argument4.properties";
        Config config = new Config(path);
        config.load();

    }
}