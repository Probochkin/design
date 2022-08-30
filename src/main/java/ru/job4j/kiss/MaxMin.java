package ru.job4j.kiss;
import java.util.Comparator;
import java.util.List;

public class MaxMin {

    /**
     * Метод вычисляет максимальное значение.
     * @param value список чисел.
     * @param comparator компаратор для сортировки.
     */
    public <T> T max(List<T> value, Comparator<T> comparator) {
        T max = value.get(0);
        for (T t : value) {
            if (comparator.compare(t, max) > 0) {
                max = t;
            }
        }
        return max;
    }

    /**
     * Метод вычисляет минимальное значение.
     * @param value список чисел.
     * @param comparator компаратор для сортировки.
     */
    public <T> T min(List<T> value, Comparator<T> comparator) {
        T min = value.get(0);
        for (T t : value) {
            if (comparator.compare(t, min) < 0) {
                min = t;
            }
        }
        return min;
    }


}
