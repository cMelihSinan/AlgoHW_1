import java.util.List;

public class QuickSelectMedian {

    // Partition function for QuickSelect
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

    // QuickSelect function to find the `k`-th smallest element
    public static int quickSelect(List<Integer> list, int low, int high, int k) {
        if (low <= high) {
            // Partition and get the pivot index
            int pivotIndex = partition(list, low, high);

            // If the pivot index is the desired position, return it
            if (pivotIndex == k) {
                return list.get(pivotIndex);
            } else if (pivotIndex > k) {
                // If pivot is larger, search in the left section
                return quickSelect(list, low, pivotIndex - 1, k);
            } else {
                // If pivot is smaller, search in the right section
                return quickSelect(list, pivotIndex + 1, high, k);
            }
        }
        return -1; // Fallback case (shouldn't happen)
    }

    // Method to get the median using QuickSelect with the first element as pivot
    public static int getMedian(List<Integer> list) {
        int n = list.size();
        int medianIndex = (n % 2 == 0) ? (n / 2) : ((n / 2) + 1); // ⌈𝑛/2⌉
        return quickSelect(list, 0, n - 1, medianIndex - 1); // Get the median
    }
}
