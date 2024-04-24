import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class ListLoader {

    //Reading lists from the text file
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

    public static List<List<Integer>> loadLists(String srcDirectory) throws IOException {
        List<List<Integer>> allLists = new ArrayList<>();

        //Names of the lists to be checked
        String[] categories = {"Duplicated", "Random", "ReverseSorted", "Sorted"};
        String[] extensions = {"_duplicated_var", "_rand_var", "_reverseSorted_var", "_sorted_var"};
        String[] sizes = {"10", "100", "1,000", "10,000"};

        //Checking and parsing names of lists
        for (int categoryIndex = 0; categoryIndex < categories.length; categoryIndex++) {
            String category = categories[categoryIndex];
            for (int sizeIndex = 0; sizeIndex < sizes.length; sizeIndex++) {
                String size = sizes[sizeIndex];
                String extension = extensions[categoryIndex];
                String fileName = size + extension;
                String filePath = srcDirectory + "/" + category + "/" + fileName;

                try {
                    List<Integer> list = readListFromFile(filePath);
                    allLists.add(list); //Adding lists to another list
                } catch (FileNotFoundException e) {
                    System.err.println("Error loading lists: " + e.getMessage());
                }
            }
        }

        return allLists; //Returning all lists
    }
}
