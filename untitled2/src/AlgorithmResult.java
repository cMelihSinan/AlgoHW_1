public class AlgorithmResult {
    String algorithm;
    int listId;
    String listName;
    int median;
    int operationCount;
    long executionTime;
    // Constructor to initialize all attributes of AlgorithmResult
    public AlgorithmResult(String algorithm, int listId, String listName, int median, int operationCount, long executionTime) {
        this.algorithm = algorithm;
        this.listId = listId;
        this.listName = listName;
        this.median = median;
        this.operationCount = operationCount;
        this.executionTime = executionTime;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    } //method to update the list identifier

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public int getMedian() {
        return median;
    }

    public void setMedian(int median) {
        this.median = median;
    }

    public int getOperationCount() {
        return operationCount;
    }

    public void setOperationCount(int operationCount) {
        this.operationCount = operationCount;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    // getters and setters...
}