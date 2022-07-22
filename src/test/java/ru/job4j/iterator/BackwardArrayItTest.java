package ru.job4j.iterator;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.NoSuchElementException;


public class BackwardArrayItTest {

    @Test
    public void whenMultiCallHasNextThenTrue() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[] {1, 2, 3, 4}
        );
        assertTrue(it.hasNext());
        assertTrue (it.hasNext());
        assertEquals(it.next().intValue(),4);
        assertEquals(it.next().intValue(),3);
        assertTrue(it.hasNext());
    }

    @Test
    public void whenMultiCallHasNextThenNext() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[] {1, 2, 3}
        );
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
        assertEquals(it.next().intValue(),3);

    }

    @Test
    public  void whenReadSequence() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[] {1, 2, 3}
        );
        assertEquals(it.next().intValue(),3);
        assertEquals(it.next().intValue(),2);
        assertEquals(it.next().intValue(),1);
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNextFromEmpty() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[] {}
        );
        it.next();
    }
}