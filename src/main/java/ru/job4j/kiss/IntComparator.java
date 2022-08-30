package ru.job4j.kiss;

import java.util.Comparator;
import java.util.*;

public class IntComparator implements Comparator<Integer> {
    public int compare(Integer a, Integer b) {
        int result = 0;
        if (a != b) {
            result = a < b ? -1 : 1;
        }
        return  result;
    }
}
