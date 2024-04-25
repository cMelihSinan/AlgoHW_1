import java.util.ArrayList;
import java.util.List;

public class InsertionSortMedian {

    // Method to perform insertion sort on a list of integers
    public static int[] insertionSort(List<Integer> list) { //Constructor (unused in this case, but usually needed for instantiation)
        int operationCount = 0;
        for (int i = 1; i < list.size(); i++) {
            int key = list.get(i);
            int j = i - 1;

            // Move elements of list[0..i-1], that are greater than key, to one position ahead of their current position
            while (j >= 0 && list.get(j) > key) {
                list.set(j + 1, list.get(j));
                j = j - 1;
                operationCount++; // Increment operation count for each comparison and swap
            }
            list.set(j + 1, key);
        }
        return new int[]{list.get(getMedianIndex(list)), operationCount}; // Return the median and operation count
    }

    // Method to get the median index
    public static int getMedianIndex(List<Integer> list) {
        int n = list.size();
        return (n % 2 == 0) ? (n / 2) : ((n / 2) + 1); // ⌈𝑛/2⌉
    }

    // Method to get the median after sorting the list with insertion sort
    public static int[] getMedianAndOperationCount(List<Integer> list) {
        return insertionSort(list); // Sort the list and get the median and operation count
    }
}