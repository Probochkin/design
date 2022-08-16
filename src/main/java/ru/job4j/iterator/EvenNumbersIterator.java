package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        if (index > data.length - 1) {
            return result;
        }
        while (index < data.length - 1 && data[index] % 2 != 0) {
            index++;
        }
            if (data[index] % 2 == 0) {
                result = true;
            }

      return result;
    }

    @Override
    public Integer next() {

       if (!hasNext()) {
           throw new NoSuchElementException();
       }
       return data[index++];
    }

}
