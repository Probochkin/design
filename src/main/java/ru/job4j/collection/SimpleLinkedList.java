package ru.job4j.collection;



import org.w3c.dom.Node;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {
    private int size;
    private Node<E> first;
    private Node<E> last;
    private int modCount;

    @Override
    public void add(E value) {
        if (last == null) {
            first = new Node<>(null, value, null);
            last = first;
        } else {
            final Node<E> temp = new Node<>(last, value, null);
            last.next = temp;
            last = temp;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        E temp = null;
        Iterator<E> it = this.iterator();
        int count = 0;
        while (count <= index) {
            temp = it.next();
            count++;
        }
        return temp;
    }

    @Override
    public Iterator<E> iterator() {
        return new SimpleLinkedIterator();
    }

    private static class Node<T> {
        T item;
        Node<T> next;
        Node<T> prev;

        Node(Node<T> prev, T element, Node<T> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }


    public class SimpleLinkedIterator implements Iterator<E> {
        private SimpleLinkedList.Node<E> temp = null;
        private final int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return temp != last;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (temp == null) {
                temp = first;
            } else {
                temp = temp.next;
            }
            return (E) temp.item;
        }
    }
}