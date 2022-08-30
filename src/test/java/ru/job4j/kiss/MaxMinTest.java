package ru.job4j.kiss;


import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;


public class MaxMinTest {
    @Test
    public void min() {
        List<Integer> list = Arrays.asList(24, 39, 58);
        MaxMin maxMin = new MaxMin();
        int min = maxMin.min(list, new IntComparator());
        assertThat(min, is(24));
    }

    @Test
    public void max() {
        List<Integer> list = Arrays.asList(24, 39, 58);
        MaxMin maxMin = new MaxMin();
        int max = maxMin.max(list, new IntComparator());
        assertThat(max, is(58));
    }
}