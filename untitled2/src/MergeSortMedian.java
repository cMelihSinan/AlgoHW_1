import java.util.ArrayList;
import java.util.List;

public class MergeSortMedian {

    // Merge function for merge sort
    private static int merge(List<Integer> list, int left, int mid, int right, int operationCount) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Create temporary arrays
        List<Integer> leftArray = new ArrayList<>(n1);
        List<Integer> rightArray = new ArrayList<>(n2);

        // Copy data to temporary arrays
        for (int i = 0; i < n1; i++) {
            leftArray.add(list.get(left + i));
        }
        for (int i = 0; i < n2; i++) {
            rightArray.add(list.get(mid + 1 + i));
        }

        // Merge the temporary arrays back into list
        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (leftArray.get(i) <= rightArray.get(j)) {
                list.set(k, leftArray.get(i));
                i++;
            } else {
                list.set(k, rightArray.get(j));
                j++;
            }
            k++;
            operationCount++; // Increment operation count for each comparison and swap
        }

        // Copy remaining elements, if any
        while (i < n1) {
            list.set(k, leftArray.get(i));
            i++;
            k++;
            operationCount++; // Increment operation count for each swap
        }
        while (j < n2) {
            list.set(k, rightArray.get(j));
            j++;
            k++;
            operationCount++; // Increment operation count for each swap
        }

        return operationCount;
    }

    // Recursive merge sort function
    public static int[] mergeSort(List<Integer> list, int left, int right, int operationCount) {
        if (left < right) {
            int mid = (left + right) / 2;

            // Recursively sort the halves
            operationCount = mergeSort(list, left, mid, operationCount)[1];
            operationCount = mergeSort(list, mid + 1, right, operationCount)[1];

            // Merge the sorted halves
            operationCount = merge(list, left, mid, right, operationCount);
        }
        return new int[]{list.get(getMedianIndex(list)), operationCount}; // Return the median and operation count
    }

    // Method to get the median index
    public static int getMedianIndex(List<Integer> list) {
        int n = list.size();
        return (n % 2 == 0) ? (n / 2) : ((n / 2) + 1); // ⌈𝑛/2⌉
    }

    // Method to get the median after sorting the list with merge sort
    public static int[] getMedianAndOperationCount(List<Integer> list) {
        return mergeSort(list, 0, list.size() - 1, 0); // Sort the list and get the median and operation count
    }
}