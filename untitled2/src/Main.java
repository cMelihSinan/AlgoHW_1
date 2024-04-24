import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String srcDirectory = "/Users/melihsinancubukcuoglu/IdeaProjects/AlgoHW_1/untitled2/src"; // Adjust this path to your source directory

        // Define a structure to hold the results
        List<AlgorithmResult> results = new ArrayList<>();

        // Define the list names for each ID
        String[] listNames = {
                "Duplicated_10", "Duplicated_100", "Duplicated_1000", "Duplicated_10000",
                "Random_10", "Random_100", "Random_1000", "Random_10000",
                "Sorted_10", "Sorted_100", "Sorted_1000", "Sorted_10000",
                "ReverseSorted_10", "ReverseSorted_100", "ReverseSorted_1000", "ReverseSorted_10000"
        };

        try {
            // Load all lists using ListLoader
            List<List<Integer>> lists = ListLoader.loadLists(srcDirectory);

            // Record results for each algorithm
            recordResults("InsertionSort", lists, results, InsertionSortMedian::getMedian, listNames);
            recordResults("MergeSort", lists, results, MergeSortMedian::getMedian, listNames);
            recordResults("QuickSort", lists, results, QuickSortMedian::getMedian, listNames);
            recordResults("MaxHeapMedian", lists, results, MaxHeapMedian::getMaxHeapMedian, listNames);
            recordResults("QuickSelectMedian", lists, results, QuickSelectMedian::getMedian, listNames);
            recordResults("QuickSelectMedianOfThree", lists, results, QuickSelectMedianOfThree::getMedian, listNames);
            recordResults("QuickSelectMedianOfMedians", lists, results, QuickSelectMedianOfMedians::getMedian, listNames);

        } catch (IOException e) {
            System.err.println("Error loading lists: " + e.getMessage());
        }

        // Output formatted table
        System.out.println("Algorithm\tList ID\tList Name\tMedian\tExecution Time (ns)");
        for (AlgorithmResult result : results) {
            System.out.printf("%-20s\t%-10d\t%-20s\t%-10d\t%-15d\n",
                    result.algorithm,
                    result.listId,
                    result.listName,
                    result.median,
                    result.executionTime);
        }

        // Analyze results
        analyzeResults(results);
    }

    // Helper method to record results for a specific algorithm
    public static void recordResults(String algorithmName, List<List<Integer>> lists, List<AlgorithmResult> results,
                                      AlgorithmFunction algorithmFunction, String[] listNames) {

        for (int i = 0; i < lists.size(); i++) {
            List<Integer> list = new ArrayList<>(lists.get(i)); // Use a copy to avoid modifying the original list
            long startTime = System.nanoTime(); // Start measuring time
            int median = algorithmFunction.findMedian(list);
            long endTime = System.nanoTime(); // End measuring time

            results.add(new AlgorithmResult(algorithmName, i + 1, listNames[i], median, (endTime - startTime)));
        }
    }

    // Method to analyze and discuss the results
    public static void analyzeResults(List<AlgorithmResult> results) {
        System.out.println("\nAnalysis of Results:");

        // Find the shortest execution time
        AlgorithmResult shortestResult = results.get(0); // Start with the first result
        for (AlgorithmResult result : results) {
            if (result.executionTime < shortestResult.executionTime) {
                shortestResult = result; // Update if a shorter execution time is found
            }
        }

        // Print the algorithm name and list name with the shortest execution time
        System.out.printf("Algorithm with the shortest execution time: %s (List: %s, Execution time: %d ns)\n",
                shortestResult.algorithm,
                shortestResult.listName,
                shortestResult.executionTime);
    }

    // Functional interface for median-finding functions
    @FunctionalInterface
    interface AlgorithmFunction {
        int findMedian(List<Integer> list);
    }
}
