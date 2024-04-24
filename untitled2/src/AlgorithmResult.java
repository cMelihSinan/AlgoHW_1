public class AlgorithmResult {
    public String algorithm;
    public int listId;
    public String listName;
    public int median;
    public long executionTime; // Execution time in nanoseconds

    public AlgorithmResult(String algorithm, int listId, String listName, int median, long executionTime) {
        this.algorithm = algorithm;
        this.listId = listId;
        this.listName = listName;
        this.median = median;
        this.executionTime = executionTime;
    }
}