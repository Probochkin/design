package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size = 0;

    private int modCount = 0;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size() >= container.length) {
            this.container = grow();
        }
        container[size] = value;
        size++;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
       Objects.checkIndex(index, container.length);
       T result =  container[index];
       container[index] = newValue;
       return result;
    }

    private T[] grow() {
        return Arrays.copyOf(container, container.length + 7);
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T result = container[index];
        System.arraycopy(
                container,
                index + 1,
                container,
                index,
                size - index - 1
        );
        container[size - 1] = null;
        this.size--;
        modCount++;
        return result;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return this.size;
    }
    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {
            private int point = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (T) container[point++];
            }
        };
        return it;
    }
}

