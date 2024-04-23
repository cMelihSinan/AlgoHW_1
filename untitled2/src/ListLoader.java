import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class ListLoader {

    // Helper method to read a list from a text file
    public static List<Integer> readListFromFile(String filePath) throws IOException {
        List<Integer> list = new ArrayList<>();
        Path path = Paths.get(filePath);

        if (Files.notExists(path)) {
            throw new FileNotFoundException("File not found: " + filePath);
        }

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(Integer.parseInt(line.trim()));
            }
        }

        return list;
    }

    // Main method to read all 16 files and generate lists
    public static List<List<Integer>> loadLists(String srcDirectory) throws IOException {
        List<List<Integer>> allLists = new ArrayList<>();

        // Subdirectories and filenames for each category
        String[] categories = {"Duplicated", "Random", "ReverseSorted", "Sorted"};
        String[] extensions = {"_duplicated_var", "_rand_var", "_reverseSorted_var", "_sorted_var"};
        String[] sizes = {"10", "100", "1,000", "10,000"};

        // Iterate over categories and sizes to construct the file paths
        for (int categoryIndex = 0; categoryIndex < categories.length; categoryIndex++) {
            String category = categories[categoryIndex];
            for (int sizeIndex = 0; sizeIndex < sizes.length; sizeIndex++) {
                String size = sizes[sizeIndex];
                String extension = extensions[categoryIndex];
                String fileName = size + extension;
                String filePath = srcDirectory + "/" + category + "/" + fileName;

                try {
                    List<Integer> list = readListFromFile(filePath);
                    allLists.add(list); // Add the generated list to the collection
                } catch (FileNotFoundException e) {
                    System.err.println("Error loading lists: " + e.getMessage());
                }
            }
        }

        return allLists; // Return all generated lists
    }
}
