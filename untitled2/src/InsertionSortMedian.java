import java.util.ArrayList;
import java.util.List;

public class InsertionSortMedian {

    // Method to perform insertion sort on a list of integers
    public static void insertionSort(List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            int key = list.get(i);
            int j = i - 1;

            // Move elements of list[0..i-1], that are greater than key, to one position ahead of their current position
            while (j >= 0 && list.get(j) > key) {
                list.set(j + 1, list.get(j));
                j = j - 1;
            }
            list.set(j + 1, key);
        }
    }

    // Method to get the median after sorting the list with insertion sort
    public static int getMedian(List<Integer> list) {
        insertionSort(list); // Sort the list
        int n = list.size();
        int medianIndex = (n % 2 == 0) ? (n / 2) : ((n / 2) + 1); // ⌈𝑛/2⌉
        return list.get(medianIndex - 1); // Get the median (subtract 1 for zero-based index)
    }
}
