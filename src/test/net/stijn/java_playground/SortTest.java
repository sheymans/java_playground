package net.stijn.java_playground;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortTest {

    @Test
    void isSorted() {
        int[] a = {1, 2, 3, 6, 8};
        assertTrue(Sort.isSorted(a));
        int[] b = {1, 2, 3, 6, 5};
        assertFalse(Sort.isSorted(b));
    }
}
