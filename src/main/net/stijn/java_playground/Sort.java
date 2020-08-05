package net.stijn.java_playground;

public class Sort {

    /**
     * Checks whether an array is sorted.
     *
     * @param array the array to check
     * @return true if the array is sorted
     */
    public static boolean isSorted(int[] array) {
        int length = array.length;
        if (length <= 1) {
            return true;
        }

        for (int i = 0; i < length  - 1; ++i) {
            int next = i + 1;
            if (array[i] > array[next]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Bubble sorts an array. With each pass through array, move largest element to back (each pass goes over less and less elements).
     *
     * @param array the array to bubble sort.
     */
    public static void bubbleSort(int[] array) {
        int last = array.length - 1;
        while (last > 0) {
            for (int i = 0; i < last; ++i) {
                int j = i + 1;
                int swap = array[j];
                if (array[i] > array[j]) {
                    array[j] = array[i];
                    array[i] = swap;
                }
            }
            --last;
        }
    }

    /**
     * Selection sort. Linear scan the list for the smallest element and move that to front.
     * Then do the same iteratively for everything but the front.
     * Note that this sorts in place by each time swapping out smallest element with front.
     *
     * @param array the array to sort.
     */
    public static void selectionSort(int[] array) {
        int start = 0;
        while (start < array.length  - 1) {

            // find the smallest element in the list after start.
            int currentMinIndex = start;
            for (int i = start + 1; i < array.length; ++i) {
                if (array[i] < array[currentMinIndex]) {
                    currentMinIndex = i;
                }
            }

            // after that for-loop we have found the smallest element in the array _after_ start, so swap that.
            int swap = array[currentMinIndex];
            array[currentMinIndex] = array[start];
            array[start] = swap;

            // now that we moved the smallest element to start, continue with the remainder of the list (again move smallest to front of that sublist).
            ++start;
        }
    }
}
