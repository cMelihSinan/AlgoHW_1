import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuickSelectMedianOfMedians {

    // Method to find the median of a list
    private static int findMedian(List<Integer> list) {
        Collections.sort(list); // Sort the list to find the median
        int mid = list.size() / 2;
        return list.get(mid); // Return the middle element as the median
    }

    // Method to get the median-of-medians pivot
    private static int getMedianOfMedians(List<Integer> list, int low, int high) {
        // Divide the list into groups of 5 elements each
        List<List<Integer>> groups = new ArrayList<>();
        for (int i = low; i <= high; i += 5) {
            int end = Math.min(i + 4, high); // Ensure the last group doesn't exceed bounds
            List<Integer> group = list.subList(i, end + 1); // Get a group of up to 5 elements
            groups.add(group);
        }

        // Find the medians of each group
        List<Integer> medians = new ArrayList<>();
        for (List<Integer> group : groups) {
            medians.add(findMedian(group)); // Find the median of each group
        }

        // Recursively get the median of the medians
        return findMedian(medians);
    }

    // Partition function using median-of-medians as pivot
    private static int partition(List<Integer> list, int low, int high) {
        int medianOfMedians = getMedianOfMedians(list, low, high); // Find the median-of-medians
        int pivotIndex = list.indexOf(medianOfMedians); // Get the index of the pivot
        int pivot = list.get(pivotIndex);

        // Swap the pivot to the beginning for consistency
        list.set(pivotIndex, list.get(low));
        list.set(low, pivot);

        int i = low + 1;
        int j = high;

        while (i <= j) {
            while (i <= high && list.get(i) <= pivot) {
                i++;
            }

            while (j >= low && list.get(j) > pivot) {
                j--;
            }

            if (i < j) {
                int temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }

        list.set(low, list.get(j));
        list.set(j, pivot);

        return j;
    }

    // QuickSelect function to find the `k`-th smallest element using median-of-medians
    public static int quickSelect(List<Integer> list, int low, int high, int k) {
        if (low <= high) {
            int pivotIndex = partition(list, low, high);

            if (pivotIndex == k) {
                return list.get(pivotIndex);
            } else if (pivotIndex > k) {
                return quickSelect(list, low, pivotIndex - 1, k);
            } else {
                return quickSelect(list, pivotIndex + 1, high, k);
            }
        }
        return -1; // Fallback case (shouldn't happen)
    }

    // Method to get the median using QuickSelect with median-of-medians pivot selection
    public static int getMedian(List<Integer> list) {
        int n = list.size();
        int medianIndex = (n % 2 == 0) ? (n / 2) : ((n / 2) + 1); // ⌈𝑛/2⌉
        return quickSelect(list, 0, n - 1, medianIndex - 1); // Get the median
    }
}
