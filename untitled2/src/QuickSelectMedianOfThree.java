import java.util.List;

public class QuickSelectMedianOfThree {

    // Method to find the median of three elements (first, middle, last)
    private static int medianOfThree(List<Integer> list, int low, int high, int operationCount) {
        int mid = low + (high - low) / 2;
        int first = list.get(low);
        int middle = list.get(mid);
        int last = list.get(high);

        if (first > middle) {
            operationCount++; // Increment operation count for comparison
            if (first < last) {
                operationCount++; // Increment operation count for comparison
                return low; // First is the median
            } else if (middle > last) {
                operationCount++; // Increment operation count for comparison
                return mid; // Middle is the median
            } else {
                return high; // Last is the median
            }
        } else {
            operationCount++; // Increment operation count for comparison
            if (first > last) {
                operationCount++; // Increment operation count for comparison
                return low; // First is the median
            } else if (middle < last) {
                operationCount++; // Increment operation count for comparison
                return mid; // Middle is the median
            } else {
                return high; // Last is the median
            }
        }
    }

    // Partition function using median-of-three pivot selection
    private static int[] partition(List<Integer> list, int low, int high, int operationCount) {
        int pivotIndex = medianOfThree(list, low, high, operationCount);
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

        return new int[]{j, operationCount}; // Return pivot index and operation count
    }

    // QuickSelect function to find the `k`-th smallest element using median-of-three pivot
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

    // Method to get the median using QuickSelect with median-of-three pivot selection
    public static int[] getMedianAndOperationCount(List<Integer> list) {
        int n = list.size();
        int medianIndex = (n % 2 == 0) ? (n / 2) : ((n / 2) + 1); // ⌈𝑛/2⌉
        return quickSelect(list, 0, n - 1, medianIndex - 1, 0); // Get the median and operation count
    }
}