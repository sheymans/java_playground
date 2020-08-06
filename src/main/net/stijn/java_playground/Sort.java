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

    /**
     * Merge sort on array. Uses O(n log n) in runtime; and O(n) in space as we make a helper copy.
     *
     * @param array the array to merge sort.
     */
    public static void mergeSort(int[] array) {
        int[] helper = new int[array.length]; // we could create this later (in merge, but then we'd have to create it too many times, now
        // we reuse an array that is big enough.
        mergeHelp(array, helper,0, array.length - 1);
    }

    private static void mergeHelp(int[] array, int[] helper, int start, int end) {
        if (end <= start) {
            return;
        }

        int mid = (start + end) / 2;
        mergeHelp(array, helper, start, mid);
        mergeHelp(array, helper,mid + 1, end);
        merge(array, helper, start, mid, end);
    }

    private static void merge(int[] array, int[] helper, int start, int mid, int end) {
        // move everything into helper, so you can overwrite array with the right sorting (the merged sort)
        for (int i = start; i <= end; ++i) {
            helper[i] = array[i];
        }

        // now start merging
        int arrayIndex = start;
        int firstIndex = start;
        int secondIndex = mid + 1;

        // go through first part; once we're done with this, we know that everything remaining in the second part is
        // already sorted.
        while (firstIndex <= mid && secondIndex <= end) {
            if (helper[firstIndex] > helper[secondIndex]) {
                array[arrayIndex] = helper[secondIndex];
                ++secondIndex;
                // leave firstIndex in place
            } else {
                array[arrayIndex] = helper[firstIndex];
                ++firstIndex;
                // leave firstIndex in place
            }
            ++arrayIndex;
        }

        // copy over all the rest
        while (firstIndex <= mid) {
            array[arrayIndex] = helper[firstIndex];
            ++firstIndex;
            ++arrayIndex;
        }

        // The below is never needed as the left part is already in array (recall that we copied array into helper, so helper and array
        // are the same for the left part.
        /*
        while (secondIndex <= end) {
            array[arrayIndex] = helper[secondIndex];
            ++secondIndex;
            ++arrayIndex;
        }
        */
    }
}
