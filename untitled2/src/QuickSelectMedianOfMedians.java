import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuickSelectMedianOfMedians {

    // Method to find the median of a list
    private static int[] findMedian(List<Integer> list, int operationCount) {
        Collections.sort(list); // Sort the list to find the median
        operationCount += list.size(); // Increment operation count for sorting
        int mid = list.size() / 2;
        return new int[]{list.get(mid), operationCount}; // Return the middle element as the median and operation count
    }

    // Method to get the median-of-medians pivot
    private static int[] getMedianOfMedians(List<Integer> list, int low, int high, int operationCount) {
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
            int[] medianResult = findMedian(group, operationCount); // Find the median of each group
            medians.add(medianResult[0]);
            operationCount = medianResult[1];
        }

        // Recursively get the median of the medians
        return findMedian(medians, operationCount);
    }

    // Partition function using median-of-medians as pivot
    private static int[] partition(List<Integer> list, int low, int high, int operationCount) {
        int[] medianOfMediansResult = getMedianOfMedians(list, low, high, operationCount); // Find the median-of-medians
        int medianOfMedians = medianOfMediansResult[0];
        operationCount = medianOfMediansResult[1];
        int pivotIndex = list.indexOf(medianOfMedians); // Get the index of the pivot
        int pivot = list.get(pivotIndex);

        // Swap the pivot to the beginning for consistency
        list.set(pivotIndex, list.get(low));
        list.set(low, pivot);
        operationCount++; // Increment operation count for the swap

        int i = low + 1;
        int j = high;

        while (i <= j) {
            while (i <= high && list.get(i) <= pivot) {
                i++;
                operationCount++; // Increment operation count for each comparison
            }

            while (j >= low && list.get(j) > pivot) {
                j--;
                operationCount++; // Increment operation count for each comparison
            }

            if (i < j) {
                int temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
                operationCount++; // Increment operation count for each swap
            }
        }

        list.set(low, list.get(j));
        list.set(j, pivot);
        operationCount++; // Increment operation count for the swap

        return new int[]{j, operationCount};
    }

    // QuickSelect function to find the `k`-th smallest element using median-of-medians
    public static int[] quickSelect(List<Integer> list, int low, int high, int k, int operationCount) {
        if (low <= high) {
            int[] partitionResult = partition(list, low, high, operationCount);
            int pivotIndex = partitionResult[0];
            operationCount = partitionResult[1];

            if (pivotIndex == k) {
                return new int[]{list.get(pivotIndex), operationCount};
            } else if (pivotIndex > k) {
                return quickSelect(list, low, pivotIndex - 1, k, operationCount);
            } else {
                return quickSelect(list, pivotIndex + 1, high, k, operationCount);
            }
        }
        return new int[]{-1, operationCount}; // Fallback case (shouldn't happen)
    }

    // Method to get the median using QuickSelect with median-of-medians pivot selection
    public static int[] getMedianAndOperationCount(List<Integer> list) {
        int n = list.size();
        int medianIndex = (n % 2 == 0) ? (n / 2) : ((n / 2) + 1); // ⌈𝑛/2⌉
        return quickSelect(list, 0, n - 1, medianIndex - 1, 0); // Get the median and operation count
    }
}