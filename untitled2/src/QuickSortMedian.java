import java.util.ArrayList;
import java.util.List;

public class QuickSortMedian {

    // Partition function for QuickSort
    private static int partition(List<Integer> list, int low, int high) {
        int pivot = list.get(low); // Choose the first element as pivot
        int i = low + 1;
        int j = high;

        while (i <= j) {
            // Move elements smaller than pivot to the left
            while (i <= high && list.get(i) <= pivot) {
                i++;
            }

            // Move elements larger than pivot to the right
            while (j >= low && list.get(j) > pivot) {
                j--;
            }

            if (i < j) {
                // Swap elements if they're in the wrong section
                int temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }

        // Place pivot in the correct position
        list.set(low, list.get(j));
        list.set(j, pivot);

        return j; // Return pivot index
    }

    // Recursive QuickSort function
    public static void quickSort(List<Integer> list, int low, int high) {
        if (low < high) {
            // Partition and get the pivot index
            int pivotIndex = partition(list, low, high);

            // Recursively sort elements before and after the pivot
            quickSort(list, low, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, high);
        }
    }

    // Method to get the median after sorting the list with QuickSort
    public static int getMedian(List<Integer> list) {
        quickSort(list, 0, list.size() - 1); // Sort the list
        int n = list.size();
        int medianIndex = (n % 2 == 0) ? (n / 2) : ((n / 2) + 1); // ⌈𝑛/2⌉
        return list.get(medianIndex - 1); // Return the median (subtract 1 for zero-based indexing)
    }
}
