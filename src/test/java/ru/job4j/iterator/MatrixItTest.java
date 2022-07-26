package ru.job4j.iterator;


import static org.junit.Assert.*;

import org.junit.Test;
import java.util.NoSuchElementException;

public class MatrixItTest  {

    @Test
    public void when4El() {
        int[][] in = {
                {1}
        };
        MatrixIt it = new MatrixIt(in);
        assertEquals(it.next().intValue(),(1));
    }

    @Test
    public  void whenFirstEmptyThenNext() {
        int[][] in = {
                {}, {1}
        };
        MatrixIt it = new MatrixIt(in);
        assertEquals(it.next().intValue(),(1));
    }

    @Test
    public void whenFirstEmptyThenHashNext() {
        int[][] in = {
                {}, {1}
        };
        MatrixIt it = new MatrixIt(in);
        assertTrue(it.hasNext());
    }

    @Test
    public void whenRowHasDiffSize() {
        int[][] in = {
                {1}, {2, 3}, {}, {}, {4}
        };
        MatrixIt it = new MatrixIt(in);
        assertEquals(it.next().intValue(),(1));
        assertEquals(it.next().intValue(),(2));
        assertEquals(it.next().intValue(),(3));
        assertEquals(it.next().intValue(),(4));
        assertFalse(it.hasNext());
    }

    @Test
    public  void whenFewEmpty() {
        int[][] in = {
                {1}, {}, {}, {}, {2}
        };
        MatrixIt it = new MatrixIt(in);
        assertEquals(it.next().intValue(),(1));
        assertEquals(it.next().intValue(),(2));
        assertFalse(it.hasNext());
    }

    @Test
    public  void whenEmpty() {
        int[][] in = {
                {}
        };
        MatrixIt it = new MatrixIt(in);
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public  void whenEmptyThenNext() {
        int[][] in = {
                {}
        };
        MatrixIt it = new MatrixIt(in);
        it.next();
    }

    @Test
    public  void whenMultiHashNext() {
        int[][] in = {
                {}, {1}
        };
        MatrixIt it = new MatrixIt(in);
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
    }

    @Test
    public void whenNoElements() {
        int[][] in = {
                {}, {}, {}
        };
        MatrixIt it = new MatrixIt(in);
        assertFalse(it.hasNext());
    }
}