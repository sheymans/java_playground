package net.stijn.java_playground;

import org.apache.commons.lang3.Validate;

public class BinarySearch {

    /**
     * Binary search in a sorted array.
     *
     * @param x the element to search for
     * @param array the sorted array
     * @return the index of the found element or -1 if the element is not present
     * @throws IllegalArgumentException if the array is not sorted
     */
    public static int binarySearch(int x, int[] array) {
        Validate.isTrue(Sort.isSorted(array));
        int length = array.length;
        if (length == 0) {
            return -1;
        }
        return binarySearchHelper(x, array, 0, length - 1);
    }

    private static int binarySearchHelper(int x, int[] array, int start, int end) {
        if (start > end) {
            return -1;
        }
        int mid = (start + end) / 2;
        if (array[mid] == x) {
            return mid;
        }
        if (x < array[mid]) {
            return binarySearchHelper(x, array, start, mid - 1);
        }
        return binarySearchHelper(x, array, mid + 1, end);
    }

    /**
     * Iterative version of {@link BinarySearch#binarySearch(int, int[])}.
     *
     * @param x the element to look for
     * @param array the array to look in
     * @return the index of x if found, or -1 if not
     */
    public static int binarySearchIterative(int x, int[] array) {
        int start = 0;
        int end = array.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            int guess = array[mid];

            if (guess == x) {
                return mid;
            }
            if (guess < x) {
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }
        return -1;
    }
}
