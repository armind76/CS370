import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;

public class DataContainer {
    // Lists to store data extracted from CSV
    private List<Integer> matchID;
    private List<String> matchOutcome;
    private List<Integer> kills;
    private List<Integer> deaths;
    private List<Integer> assists;
    private List<Integer> goldEarned;
    private List<Integer> damageDealt;
    private List<Integer> damageTaken;
    private List<Integer> wardsPlaced;
    private List<Integer> playerChampionID;
    private List<String> playerChampion;
    private List<String> playerRole;

    // Constructor to initialize lists
    public DataContainer() {
        matchID = new ArrayList<>();
        matchOutcome = new ArrayList<>();
        kills = new ArrayList<>();
        deaths = new ArrayList<>();
        assists = new ArrayList<>();
        goldEarned = new ArrayList<>();
        damageDealt = new ArrayList<>();
        damageTaken = new ArrayList<>();
        wardsPlaced = new ArrayList<>();
        playerChampionID = new ArrayList<>();
        playerChampion = new ArrayList<>();
        playerRole = new ArrayList<>();
    }

    // Method to read data from CSV file
    public void readDataFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean headerSkipped = false;
            while ((line = br.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue; // Skip header row
                }
                String[] data = line.split(",");
                
             
                    matchID.add(Integer.parseInt(data[0]));
                    matchOutcome.add(data[1].equals("0") ? "Defeat" : "Victory");
                    kills.add(Integer.parseInt(data[2]));
                    deaths.add(Integer.parseInt(data[3]));
                    assists.add(Integer.parseInt(data[4]));
                    damageDealt.add(Integer.parseInt(data[5]));
                    damageTaken.add(Integer.parseInt(data[6]));
                    goldEarned.add(Integer.parseInt(data[7]));
                    wardsPlaced.add(Integer.parseInt(data[8]));
                    playerRole.add(data[9]);
                    playerChampionID.add(data[10]);
                 
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
    

    // Method to perform bootstrapping
    public List<DataContainer> bootStrap(int numSamples) {
        List<DataContainer> bootstrapSamples = new ArrayList<>();
        Random random = new Random();

     // Determine the smallest size among all lists
        int minSize = Integer.MAX_VALUE;
        minSize = Math.min(minSize, matchID.size());
        minSize = Math.min(minSize, matchOutcome.size());
        minSize = Math.min(minSize, kills.size());
        minSize = Math.min(minSize, deaths.size());
        minSize = Math.min(minSize, assists.size());
        minSize = Math.min(minSize, goldEarned.size());
        minSize = Math.min(minSize, damageDealt.size());
        minSize = Math.min(minSize, damageTaken.size());
        minSize = Math.min(minSize, wardsPlaced.size());
        minSize = Math.min(minSize, playerChampionID.size());
        minSize = Math.min(minSize, playerRole.size());
        
        // Perform bootstrapping for the specified number of samples
        for (int i = 0; i < numSamples; i++) {
            DataContainer bootstrapSample = new DataContainer();

            // Generate random indices with replacement
            for (int j = 0; j < numData(); j++) {
                int randomIndex = random.nextInt(numData());
                // Add data corresponding to the randomly selected index to the bootstrap sample
                bootstrapSample.matchID.add(matchID.get(randomIndex));
                bootstrapSample.matchOutcome.add(matchOutcome.get(randomIndex));
                bootstrapSample.kills.add(kills.get(randomIndex));
                bootstrapSample.deaths.add(deaths.get(randomIndex));
                bootstrapSample.assists.add(assists.get(randomIndex));
                bootstrapSample.goldEarned.add(goldEarned.get(randomIndex));
                bootstrapSample.damageDealt.add(damageDealt.get(randomIndex));
                bootstrapSample.damageTaken.add(damageTaken.get(randomIndex));
                bootstrapSample.wardsPlaced.add(wardsPlaced.get(randomIndex));
                bootstrapSample.playerChampionID.add(playerChampionID.get(randomIndex));
                bootstrapSample.playerRole.add(playerRole.get(randomIndex));
            }

            // Add the bootstrap sample to the list of bootstrap samples
            bootstrapSamples.add(bootstrapSample);
        }

        return bootstrapSamples;
    }

    // Method to get the number of data points
    public int numData() {
        // Assuming all lists have the same size
        return matchID.size();
    }
    
 // Method to match champion IDs to names
    public void matchChampionNames(Map<Integer, String> championMap) {
        for (Integer championId : playerChampionID) {
            String championName = championMap.get(championId);
            playerChampion.add(championName);
        }
    }
    
 
    // Method to fill missing data
    public void fillMissingData() {
        // Empty for now
    }

    
    
    // Getters for data lists
    // Add setters if needed 

    public List<Integer> getMatchID() {
        return matchID;
    }


    public List<String> getMatchOutcome() {
        return matchOutcome;
    }

    public List<Integer> getKills() {
        return kills;
    }

    public List<Integer> getDeaths() {
        return deaths;
    }

    public List<Integer> getAssists() {
        return assists;
    }

    public List<Integer> getGoldEarned() {
        return goldEarned;
    }

    public List<Integer> getDamageDealt() {
        return damageDealt;
    }

    public List<Integer> getDamageTaken() {
        return damageTaken;
    }

    public List<Integer> getWardsPlaced() {
        return wardsPlaced;
    }

    public List<String> getplayerChampion() {
        return playerChampion;
    }

    public List<String> getPlayerRole() {
        return playerRole;
    }
}
