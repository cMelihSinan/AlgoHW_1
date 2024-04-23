import java.util.ArrayList;
import java.util.List;

public class MergeSortMedian {

    // Merge function for merge sort
    private static void merge(List<Integer> list, int left, int mid, int right) {
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
        }

        // Copy remaining elements, if any
        while (i < n1) {
            list.set(k, leftArray.get(i));
            i++;
            k++;
        }
        while (j < n2) {
            list.set(k, rightArray.get(j));
            j++;
            k++;
        }
    }

    // Recursive merge sort function
    public static void mergeSort(List<Integer> list, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            // Recursively sort the halves
            mergeSort(list, left, mid);
            mergeSort(list, mid + 1, right);

            // Merge the sorted halves
            merge(list, left, mid, right);
        }
    }

    // Method to get the median after sorting the list with merge sort
    public static int getMedian(List<Integer> list) {
        mergeSort(list, 0, list.size() - 1); // Sort the list
        int n = list.size();
        int medianIndex = (n % 2 == 0) ? (n / 2) : ((n / 2) + 1); // ⌈𝑛/2⌉
        return list.get(medianIndex - 1); // Return the median (subtract 1 for zero-based indexing)
    }
}
