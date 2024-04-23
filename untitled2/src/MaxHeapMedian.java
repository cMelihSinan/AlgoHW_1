import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Collections;

public class MaxHeapMedian {

    // Method to build a max-heap and remove the maximum element ⌊𝑛/2⌋ times
    public static int getMaxHeapMedian(List<Integer> list) {
        // Create a max-heap with PriorityQueue, using reverse order to make it a max-heap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Add all elements to the max-heap
        maxHeap.addAll(list);

        // Remove the maximum element ⌊𝑛/2⌋ times
        int half = list.size() / 2;
        for (int i = 0; i < half; i++) {
            maxHeap.poll(); // Remove the max element (the root)
        }

        // Return the next max element, which is now at the root
        return maxHeap.peek();
    }
}
