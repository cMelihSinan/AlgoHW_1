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
                "10 Duplicated", "100 Duplicated", "1,000Duplicated", "10,000 Duplicated",
                "10 Random", "100 Random", "1,000 Random", "10,000 Random",
                "10 Sorted", "100 Sorted", "1,000 Sorted", "10,000 Sorted",
                "10 ReverseSorted", "100 ReverseSorted", "1,000 ReverseSorted", "10,000 ReverseSorted"
        };

        try {
            // Load all lists using ListLoader
            List<List<Integer>> lists = ListLoader.loadLists(srcDirectory);

            // Record results for each algorithm
            recordResults("InsertionSort", lists, results, InsertionSortMedian::getMedianAndOperationCount, listNames);
            recordResults("MergeSort", lists, results, MergeSortMedian::getMedianAndOperationCount, listNames);
            recordResults("QuickSort", lists, results, QuickSortMedian::getMedianAndOperationCount, listNames);
            recordResults("MaxHeapMedian", lists, results, MaxHeapMedian::getMaxHeapMedian, listNames);
            recordResults("QuickSelectMedian", lists, results, QuickSelectMedian::getMedianAndOperationCount, listNames);
            recordResults("QuickSelectMedianOfThree", lists, results, QuickSelectMedianOfThree::getMedianAndOperationCount, listNames);
            recordResults("QuickSelectMedianOfMedians", lists, results, QuickSelectMedianOfMedians::getMedianAndOperationCount, listNames);

        } catch (IOException e) {
            System.err.println("Error loading lists: " + e.getMessage());
        }

        //Table of Algorithm, list id, list name, median, operation count and execution time
        System.out.println("Algorithm\t\t\t\t\t  List ID\t\tList Name\t\t\t  Median\t  Operation Count\t  Execution Time (ns)");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        for (AlgorithmResult result : results) {
            System.out.printf("%-30s\t%-10d\t%-20s\t%-10d\t%-15d\t%-15d\n",
                    result.algorithm,
                    result.listId,
                    result.listName,
                    result.median,
                    result.operationCount,
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
            int[] result = algorithmFunction.findMedianAndOperationCount(list);
            int median = result[0];
            int operationCount = result[1];
            long endTime = System.nanoTime(); // End measuring time

            results.add(new AlgorithmResult(algorithmName, i + 1, listNames[i], median, operationCount, (endTime - startTime)));        }
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
        int[] findMedianAndOperationCount(List<Integer> list);
    }
}