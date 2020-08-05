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
}
