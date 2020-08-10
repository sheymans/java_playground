package net.stijn.java_playground;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTest {

    @Test
    void binarySearch() {
        int[] a = {1, 4, 6, 10};
        int x = 6;
        assertEquals(2, BinarySearch.binarySearch(x, a));
        int[] b = {1, 4, 6, 10};
        int y = 10;
        assertEquals(3, BinarySearch.binarySearch(y, b));
        int[] c = {1, 4, 6, 10};
        int z = 11;
        assertEquals(-1, BinarySearch.binarySearch(z, c));
    }

    @Test
    void binarySearchNotSorted() {
        int[] a = {1, 5, 4};
        int x = 1;
        assertThrows(IllegalArgumentException.class, () -> BinarySearch.binarySearch(x, a));
    }

}
