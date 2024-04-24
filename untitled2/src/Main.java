import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String srcDirectory = "/Users/melihsinancubukcuoglu/IdeaProjects/AlgoHW_1/untitled2/src"; // Adjust this path to your source directory

        // Define a structure to hold the results
        List<AlgorithmResult> results = new ArrayList<>();

        try {
            // Load all lists using ListLoader
            List<List<Integer>> lists = ListLoader.loadLists(srcDirectory);

            // Median by different algorithms
            recordResults("InsertionSort", lists, results, InsertionSortMedian::getMedian);
            recordResults("MergeSort", lists, results, MergeSortMedian::getMedian);
            recordResults("QuickSort", lists, results, QuickSortMedian::getMedian);
            recordResults("MaxHeapMedian", lists, results, MaxHeapMedian::getMaxHeapMedian);
            recordResults("QuickSelectMedian", lists, results, QuickSelectMedian::getMedian);
            recordResults("QuickSelectMedianOfThree", lists, results, QuickSelectMedianOfThree::getMedian);
            recordResults("QuickSelectMedianOfMedians", lists, results, QuickSelectMedianOfMedians::getMedian);

        } catch (IOException e) {
            System.err.println("Error loading lists: " + e.getMessage());
        }

        // Print a formatted output table
        System.out.println("Algorithm\t\t\t List ID\t  Median\t   Execution Time (ns)");
        for (AlgorithmResult result : results) {
            System.out.printf("%-20s\t%-10d\t%-10d\t%-15d\n",
                    result.algorithm,
                    result.listId,
                    result.median,
                    result.executionTime);
        }

        analyzeResults(results);
    }

    // Helper method to record results for a specific algorithm
    private static void recordResults(String algorithmName, List<List<Integer>> lists, List<AlgorithmResult> results,
                                      AlgorithmFunction algorithmFunction) {

        for (int i = 0; i < lists.size(); i++) {
            List<Integer> list = new ArrayList<>(lists.get(i)); // Use a copy to avoid modifying the original list
            long startTime = System.nanoTime(); // Start measuring time
            int median = algorithmFunction.findMedian(list);
            long endTime = System.nanoTime(); // End measuring time

            results.add(new AlgorithmResult(algorithmName, i + 1, median, (endTime - startTime)));
        }
    }

    // Method to analyze and discuss the results
    private static void analyzeResults(List<AlgorithmResult> results) {
        System.out.println("\nAnalysis of Results:");
        // Example analysis logic: fastest and slowest algorithms for each list, average execution times, etc.
    }

    // Functional interface for median-finding functions
    @FunctionalInterface
    interface AlgorithmFunction {
        int findMedian(List<Integer> list);
    }
}

// Helper class to store experiment results
class AlgorithmResult {
    public String algorithm;
    public int listId;
    public int median;
    public long executionTime; // Execution time in nanoseconds

    public AlgorithmResult(String algorithm, int listId, int median, long executionTime) {
        this.algorithm = algorithm;
        this.listId = listId;
        this.median = median;
        this.executionTime = executionTime;
    }
}
