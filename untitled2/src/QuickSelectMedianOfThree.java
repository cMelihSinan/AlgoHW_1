import java.util.List;

public class QuickSelectMedianOfThree {

    // Method to find the median of three elements (first, middle, last)
    private static int medianOfThree(List<Integer> list, int low, int high) {
        int mid = low + (high - low) / 2;
        int first = list.get(low);
        int middle = list.get(mid);
        int last = list.get(high);

        if (first > middle) {
            if (first < last) {
                return low; // First is the median
            } else if (middle > last) {
                return mid; // Middle is the median
            } else {
                return high; // Last is the median
            }
        } else {
            if (first > last) {
                return low; // First is the median
            } else if (middle < last) {
                return mid; // Middle is the median
            } else {
                return high; // Last is the median
            }
        }
    }

    // Partition function using median-of-three pivot selection
    private static int partition(List<Integer> list, int low, int high) {
        int pivotIndex = medianOfThree(list, low, high);
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

    // QuickSelect function to find the `k`-th smallest element using median-of-three pivot
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

    // Method to get the median using QuickSelect with median-of-three pivot selection
    public static int getMedian(List<Integer> list) {
        int n = list.size();
        int medianIndex = (n % 2 == 0) ? (n / 2) : ((n / 2) + 1); // ⌈𝑛/2⌉
        return quickSelect(list, 0, n - 1, medianIndex - 1); // Get the median
    }
}
