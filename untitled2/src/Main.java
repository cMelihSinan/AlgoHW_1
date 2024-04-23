import java.io.IOException;
import java.util.List;

public class Main {
    //List'ler 4'er 4'er
    // 1. dörtlü duplicated, 2. dörtlü random, 3. dörtlü sorted, 4. dörtlü sorted
    //

    public static void main(String[] args) {
        String srcDirectory = "C:\\Users\\melih\\IdeaProjects\\untitled3\\src"; // Adjust this path to your source directory

        try {
            // Load all lists using ListLoader
            List<List<Integer>> lists = ListLoader.loadLists(srcDirectory);

            // Get the median for each list using InsertionSortMedian
            for (int i = 0; i < lists.size(); i++) {
                List<Integer> list = lists.get(i);
                int median = InsertionSortMedian.getMedian(list);

                System.out.println("Median of list by InsertionSort " + (i + 1) + ": " + median);
            }
        } catch (IOException e) {
            System.err.println("Error loading lists: " + e.getMessage());
        }
        try {
            // Load all lists using ListLoader
            List<List<Integer>> lists = ListLoader.loadLists(srcDirectory);

            // Get the median for each list using MergeSortMedian
            for (int i = 0; i < lists.size(); i++) {
                List<Integer> list = lists.get(i);
                int median = MergeSortMedian.getMedian(list);

                System.out.println("Median of list by MergeSort " + (i + 1) + ": " + median);
            }
        } catch (IOException e) {
            System.err.println("Error loading lists: " + e.getMessage());
        }
        try {
            // Load all lists using ListLoader
            List<List<Integer>> lists = ListLoader.loadLists(srcDirectory);

            // Get the median for each list using QuickSortMedian
            for (int i = 0; i < lists.size(); i++) {
                List<Integer> list = lists.get(i);
                int median = QuickSortMedian.getMedian(list);

                System.out.println("Median of list by QuickSort " + (i + 1) + ": " + median);
            }
        } catch (IOException e) {
            System.err.println("Error loading lists: " + e.getMessage());
        }
        try {
            // Load all lists using ListLoader
            List<List<Integer>> lists = ListLoader.loadLists(srcDirectory);

            // Get the median for each list using MaxHeapMedian
            for (int i = 0; i < lists.size(); i++) {
                List<Integer> list = lists.get(i);
                int median = MaxHeapMedian.getMaxHeapMedian(list);

                System.out.println("Median of list MaxHeapMedian " + (i + 1) + ": " + median);
            }
        } catch (IOException e) {
            System.err.println("Error loading lists: " + e.getMessage());
        }
        try {
            // Load all lists using ListLoader
            List<List<Integer>> lists = ListLoader.loadLists(srcDirectory);

            // Get the median for each list using QuickSelectMedian
            for (int i = 0; i < lists.size(); i++) {
                List<Integer> list = lists.get(i);
                int median = QuickSelectMedian.getMedian(list);

                System.out.println("Median of list QuickSelectMedian " + (i + 1) + ": " + median);
            }
        } catch (IOException e) {
            System.err.println("Error loading lists: " + e.getMessage());
        }
        try {
            // Load all lists using ListLoader
            List<List<Integer>> lists = ListLoader.loadLists(srcDirectory);

            // Get the median for each list using QuickSelectMedianOfThree
            for (int i = 0; i < lists.size(); i++) {
                List<Integer> list = lists.get(i);
                int median = QuickSelectMedianOfThree.getMedian(list);

                System.out.println("Median of list MedianOfThree" + (i + 1) + ": " + median);
            }
        } catch (IOException e) {
            System.err.println("Error loading lists: " + e.getMessage());
        }
        try {
            // Load all lists using ListLoader
            List<List<Integer>> lists = ListLoader.loadLists(srcDirectory);

            // Get the median for each list using QuickSelectMedianOfMedians
            for (int i = 0; i < lists.size(); i++) {
                List<Integer> list = lists.get(i);
                int median = QuickSelectMedianOfMedians.getMedian(list);

                System.out.println("Median of list MedianOfMedians " + (i + 1) + ": " + median);
            }
        } catch (IOException e) {
            System.err.println("Error loading lists: " + e.getMessage());
        }
    }
}
