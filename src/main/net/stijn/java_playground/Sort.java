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
}
