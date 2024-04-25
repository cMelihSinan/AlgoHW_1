import java.util.List;

public class QuickSelectMedian {

    // Partition function for QuickSelect
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
        operationCount++; // Increment operation count for the swap

        return new int[]{j, operationCount}; // Return pivot index and operation count
    }

    // QuickSelect function to find the `k`-th smallest element
    public static int[] quickSelect(List<Integer> list, int low, int high, int k, int operationCount) {
        if (low <= high) {
            // Partition and get the pivot index
            int[] partitionResult = partition(list, low, high, operationCount);
            int pivotIndex = partitionResult[0];
            operationCount = partitionResult[1];

            // If the pivot index is the desired position, return it
            if (pivotIndex == k) {
                return new int[]{list.get(pivotIndex), operationCount};
            } else if (pivotIndex > k) {
                // If pivot is larger, search in the left section
                return quickSelect(list, low, pivotIndex - 1, k, operationCount);
            } else {
                // If pivot is smaller, search in the right section
                return quickSelect(list, pivotIndex + 1, high, k, operationCount);
            }
        }
        return new int[]{-1, operationCount}; // Fallback case (shouldn't happen)
    }

    // Method to get the median using QuickSelect with the first element as pivot
    public static int[] getMedianAndOperationCount(List<Integer> list) {
        int n = list.size();
        int medianIndex = (n % 2 == 0) ? (n / 2) : ((n / 2) + 1); // ⌈𝑛/2⌉
        return quickSelect(list, 0, n - 1, medianIndex - 1, 0); // Get the median and operation count
    }
}