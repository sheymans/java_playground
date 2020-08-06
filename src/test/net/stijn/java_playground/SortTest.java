package net.stijn.java_playground;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SortTest {

    @Test
    void isSorted() {
        int[] a = {1, 2, 3, 6, 8};
        assertTrue(Sort.isSorted(a));
        int[] b = {1, 2, 3, 6, 5};
        assertFalse(Sort.isSorted(b));
    }

    @Test
    void bubbleSort() {
        int[] a = {1, 2, 3, 6, 8};
        Sort.bubbleSort(a);
        assertTrue(Sort.isSorted(a));
        int[] b = {1, 2, 3, 6, 5};
        assertFalse(Sort.isSorted(b));
        Sort.bubbleSort(b);
        assertTrue(Sort.isSorted(b));
        int[] c = {5, 4, 3, 2, 1};
        Sort.bubbleSort(c);
        assertTrue(Sort.isSorted(c));
    }

    @Test
    void selectionSort() {
        int[] a = {1, 2, 3, 6, 8};
        Sort.selectionSort(a);
        assertTrue(Sort.isSorted(a));
        int[] b = {1, 2, 3, 6, 5};
        assertFalse(Sort.isSorted(b));
        Sort.selectionSort(b);
        assertTrue(Sort.isSorted(b));
        int[] c = {5, 4, 3, 2, 1};
        Sort.selectionSort(c);
        assertTrue(Sort.isSorted(c));
    }

    @Test
    void mergeSort() {
        int[] a = {1, 2, 3, 6, 8};
        Sort.mergeSort(a);
        assertTrue(Sort.isSorted(a));
        int[] b = {1, 2, 3, 6, 5};
        assertFalse(Sort.isSorted(b));
        Sort.mergeSort(b);
        assertTrue(Sort.isSorted(b));
        int[] c = {5, 4, 3, 2, 1};
        Sort.mergeSort(c);
        assertTrue(Sort.isSorted(c));
    }

    @Test
    void mergeSortShort() {
        int[] a = {2, 1};
        Sort.mergeSort(a);
        assertTrue(Sort.isSorted(a));
    }

    @Test
    void mergeSortLong() {
        int[] a = new Random().ints(100, 0, 1000).toArray();
        Sort.mergeSort(a);
        assertTrue(Sort.isSorted(a));
    }
}
