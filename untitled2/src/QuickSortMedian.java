import java.util.ArrayList;
import java.util.List;

public class QuickSortMedian {

    //partition function for QuickSort
    private static int[] partition(List<Integer> list, int low, int high, int operationCount) {
        int pivot = list.get(low); // Choose the first element as pivot
        int i = low + 1;
        int j = high;

        while (i <= j) {
            // Move elements smaller than pivot to the left
            while (i <= high && list.get(i) <= pivot) {
                i++;
                operationCount++; // Increment operation count for each comparison
            }

            // Move elements larger than pivot to the right
            while (j >= low && list.get(j) > pivot) {
                j--;
                operationCount++; // Increment operation count for each comparison
            }

            if (i < j) {
                // Swap elements if they're in the wrong section
                int temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
                operationCount++; // Increment operation count for each swap
            }
        }

        // Place pivot in the correct position
        list.set(low, list.get(j));
        list.set(j, pivot);
        operationCount++; // Increase operation count for the swap

        return new int[]{j, operationCount}; // Return pivot index and operation count
    }

    // Recursive QuickSort function
    public static int[] quickSort(List<Integer> list, int low, int high, int operationCount) {
        if (low < high) {
            // Partition and get the pivot index
            int[] partitionResult = partition(list, low, high, operationCount);
            int pivotIndex = partitionResult[0];
            operationCount = partitionResult[1];

            // Recursively sort elements before and after the pivot
            operationCount = quickSort(list, low, pivotIndex - 1, operationCount)[1];
            operationCount = quickSort(list, pivotIndex + 1, high, operationCount)[1];
        }
        return new int[]{list.get(getMedianIndex(list)), operationCount}; // Return the median and operation count
    }

    // median index
    public static int getMedianIndex(List<Integer> list) {
        int n = list.size();
        return (n % 2 == 0) ? (n / 2) : ((n / 2) + 1); // ⌈𝑛/2⌉
    }

    //median after sorting the list with QuickSort
    public static int[] getMedianAndOperationCount(List<Integer> list) {
        return quickSort(list, 0, list.size() - 1, 0); //median and operation count
    }
}