import java.util.Arrays;
import java.util.List;

public class DataContainerTest {
    public static void main(String[] args) {
        // Instantiate DataContainer
        DataContainer dataContainer = new DataContainer();
        List<Integer> specificLines = Arrays.asList(2, 5, 7, 10);
      // Read data from CSV file
        dataContainer.readDataFromCSV("stats1.csv", 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, specificLines);
        dataContainer.readDataFromCSV("stats2.csv", 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, specificLines);
        dataContainer.readDataFromCSV("participants.csv", 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, specificLines);
        dataContainer.readDataFromCSV("champs.csv", 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, specificLines);
        dataContainer.readDataFromCSV("matches.csv", 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, specificLines);
        
        /**dataContainer.readDataFromCSV("newleague.csv", 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);**/
        // Access and print some sample data
        System.out.println("Match IDs: " + dataContainer.getMatchID());
        System.out.println("Match Outcomes: " + dataContainer.getMatchOutcome());
        System.out.println("Match Outcomes: " + dataContainer.getMatchDuration());
        // Print other data as needed
        
        // Test other methods if implemented (e.g., bootStrap, fillMissingData)
    }
}