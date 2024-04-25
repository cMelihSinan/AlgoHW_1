import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class MaxHeapMedian {
    public static int[] getMaxHeapMedian(List<Integer> list) {
        int operationCount = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(list);
        operationCount += list.size(); // Adding elements to the heap
        int half = list.size() / 2;
        for (int i = 0; i < half; i++) {
            maxHeap.poll();
            operationCount++; // Removing elements from the heap
        }
        return new int[]{maxHeap.peek(), operationCount};
    }
}