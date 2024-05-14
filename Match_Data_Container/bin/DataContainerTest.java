import java.util.List;
import java.util.Random;

public class DataContainerTest {
    public static void main(String[] args) {
    	// Instantiate DataContainer
        DataContainer dataContainer = new DataContainer();

        // Generate some sample data
        generateSampleData(dataContainer, 50);

        // Perform bootstrapping
        List<DataContainer> bootstrapSamples = dataContainer.bootStrap(100);

        // Print the sizes of the original data and bootstrap samples
        System.out.println("Original Data Size: " + dataContainer.numData());
        System.out.println("Bootstrap Sample Size: " + bootstrapSamples.size());
     // Print out some sample data
        System.out.println("Sample Data:");
        System.out.println("Match ID: " + dataContainer.getMatchID().subList(0, 10));
        System.out.println("Match Outcome: " + dataContainer.getMatchOutcome().subList(0, 10));
        System.out.println("Kills: " + dataContainer.getKills().subList(0, 10));
        System.out.println("Deaths: " + dataContainer.getDeaths().subList(0, 10));
        System.out.println("Assists: " + dataContainer.getAssists().subList(0, 10));
        System.out.println("Gold Earned: " + dataContainer.getGoldEarned().subList(0, 10));
        System.out.println("Damage Dealt: " + dataContainer.getDamageDealt().subList(0, 10));
        System.out.println("Damage Taken: " + dataContainer.getDamageTaken().subList(0, 10));
        System.out.println("Wards Placed: " + dataContainer.getWardsPlaced().subList(0, 10));
        System.out.println("Player Champion: " + dataContainer.getplayerChampion().subList(0, 10));
        System.out.println("Player Role: " + dataContainer.getPlayerRole().subList(0, 10));

    }

    // Method to generate sample data
    private static void generateSampleData(DataContainer dataContainer, int dataSize) {
        String[] roles = {"Top", "Jungle", "Mid", "ADC", "Support"};
        Random random = new Random();

        for (int i = 0; i < dataSize; i++) {
            // Generate random data and add it to the DataContainer
            dataContainer.getMatchID().add(i);
            dataContainer.getMatchOutcome().add(i % 2 == 0 ? "Victory" : "Defeat");
            dataContainer.getKills().add(i);
            dataContainer.getDeaths().add(i / 2);
            dataContainer.getAssists().add(i / 3);
            dataContainer.getGoldEarned().add(i * 100);
            dataContainer.getDamageDealt().add(i * 50);
            dataContainer.getDamageTaken().add(i * 20);
            dataContainer.getWardsPlaced().add(i / 4);
            dataContainer.getplayerChampion().add("Champion" + i);
            dataContainer.getPlayerRole().add(roles[random.nextInt(roles.length)]);
        }
    }
    

    
}
