package com.company;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Samurdhi on 2016-07-18.
 */
public class ArrayTest {
    @Test
    public void add() throws Exception {
        Array a=new Array();
        a.add(1);
        a.add(2);
        a.add(3);
        int b[]=new int[]{1,2,3};
        assertArrayEquals(b,a.getArray());
    }

    @Test
    public void add1() throws Exception {
        Array a=new Array();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(1,10);
        int b[]=new int[]{1,10,2,3};
        assertArrayEquals(b,a.getArray());
    }

    @Test
    public void size() throws Exception {
        Array a=new Array();
        a.add(1);
        a.add(2);
        a.add(3);
        assertEquals(3,a.size());
    }

    @Test
    public void replace() throws Exception {
        Array a=new Array();
        a.add(1);
        a.add(2);
        a.add(3);
        a.replace(1,5);
        int b[]=new int[]{1,5,3};
        assertArrayEquals(b,a.getArray());
    }

    @Test
    public void remove() throws Exception {
        Array a=new Array();
        a.add(1);
        a.add(2);
        a.add(3);
        a.remove(1);
        int b[]=new int[]{1,3};
        assertArrayEquals(b,a.getArray());
    }

    @Test
    public void isEmpty() throws Exception {
        Array a=new Array();
        assertTrue(a.isEmpty());
        a.add(1);
        assertFalse(a.isEmpty());
    }

    @Test
    public void contains() throws Exception {
        Array a=new Array();
        a.add(1);
        assertTrue(a.contains(1));
        assertFalse(a.contains(5));
    }

    @Test
    public void trimToSize() throws Exception {
        Array a=new Array();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        a.trimToSize(3);
        int b[]=new int[]{1,2,3};
        assertArrayEquals(b,a.getArray());
    }

    @Test
    public void clear() throws Exception {
        Array a=new Array();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        a.clear();
        int b[]=new int[0];
        assertArrayEquals(b,a.getArray());
    }

    @Test
    public void toString1() throws Exception {
        Array a=new Array();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        assertEquals("[1, 2, 3, 4]",a.toString());
    }

}