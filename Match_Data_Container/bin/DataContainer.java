import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataContainer {
    // Lists to store data extracted from CSV
    private List<Integer> matchID;
    private List<Integer> matchDuration;
    private List<String> matchOutcome;
    private List<Integer> kills;
    private List<Integer> deaths;
    private List<Integer> assists;
    private List<Integer> goldEarned;
    private List<Integer> damageDealt;
    private List<Integer> damageTaken;
    private List<Integer> wardsPlaced;
    private List<String> playerChampion;
    private List<String> playerRole;

    // Constructor to initialize lists
    public DataContainer() {
        matchID = new ArrayList<>();
        matchDuration = new ArrayList<>();
        matchOutcome = new ArrayList<>();
        kills = new ArrayList<>();
        deaths = new ArrayList<>();
        assists = new ArrayList<>();
        goldEarned = new ArrayList<>();
        damageDealt = new ArrayList<>();
        damageTaken = new ArrayList<>();
        wardsPlaced = new ArrayList<>();
        playerChampion = new ArrayList<>();
        playerRole = new ArrayList<>();
    }

    // Method to read data from CSV file
    public void readDataFromCSV(String filePaths, int matchIdIndex, int matchDurationIndex, int matchOutcomeIndex,
            int killsIndex, int deathsIndex, int assistsIndex, int goldEarnedIndex, int damageDealtIndex,
            int damageTakenIndex, int wardsPlacedIndex, int playerChampionIndex, int playerRoleIndex, List<Integer> specificLines) {
    	
    	List<String> fileList;
        if (filePaths instanceof String) {
            fileList = new ArrayList<>();
            fileList.add((String) filePaths);
        } else if (filePaths instanceof List<?>) {
            fileList = (List<String>) filePaths;
        } else {
            throw new IllegalArgumentException("Invalid argument type for filePaths");
        }
        
    for (String filePath : filePaths) {  
    	try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean headerSkipped = false;
            int lineNumber = 0;
            
            while ((line = br.readLine()) != null) {
            	lineNumber++;
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue; // Skip header row
                }
                
                
             // Check if the current line number is in the list of specific lines
                if (!specificLines.contains(lineNumber)) {
                    continue; // Skip this line if not in the list
                }
                
                String[] data = line.split(",");
               
                // Parse data and add to lists
                matchID.add(Integer.parseInt(data[matchIdIndex]));
                matchDuration.add(Integer.parseInt(data[matchDurationIndex]));
                matchOutcome.add(data[matchOutcomeIndex].equals("0") ? "Defeat" : "Victory");;
                kills.add(Integer.parseInt(data[killsIndex]));
                deaths.add(Integer.parseInt(data[deathsIndex]));
                assists.add(Integer.parseInt(data[assistsIndex]));
                goldEarned.add(Integer.parseInt(data[goldEarnedIndex]));
                damageDealt.add(Integer.parseInt(data[damageDealtIndex]));
                damageTaken.add(Integer.parseInt(data[damageTakenIndex]));
                wardsPlaced.add(Integer.parseInt(data[wardsPlacedIndex]));
                playerChampion.add(data[playerChampionIndex]);
                playerRole.add(data[playerRoleIndex]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            // Handle the case where an invalid number format is encountered
            e.printStackTrace();
        }
        
    }
  }
    
    
    /** Method to read data from multiple CSV files for specific columns
    public void readDataFromCSV(List<String> filePaths, int matchIdIndex, int matchDurationIndex,
            int matchOutcomeIndex, int killsIndex, int deathsIndex, int assistsIndex, int goldEarnedIndex,
            int damageDealtIndex, int damageTakenIndex, int wardsPlacedIndex, int playerChampionIndex,
            int playerRoleIndex) {
        for (String filePath : filePaths) {
            readDataFromCSV(filePath, matchIdIndex, matchDurationIndex, matchOutcomeIndex, killsIndex, deathsIndex,
                    assistsIndex, goldEarnedIndex, damageDealtIndex, damageTakenIndex, wardsPlacedIndex,
                    playerChampionIndex, playerRoleIndex);
        }
    } **/

    // Method to perform bootstrapping
    public List<DataContainer> bootStrap(int numSamples) {
        List<DataContainer> bootstrapSamples = new ArrayList<>();
        Random random = new Random();

     // Determine the smallest size among all lists
        int minSize = Integer.MAX_VALUE;
        minSize = Math.min(minSize, matchID.size());
        minSize = Math.min(minSize, matchDuration.size());
        minSize = Math.min(minSize, matchOutcome.size());
        minSize = Math.min(minSize, kills.size());
        minSize = Math.min(minSize, deaths.size());
        minSize = Math.min(minSize, assists.size());
        minSize = Math.min(minSize, goldEarned.size());
        minSize = Math.min(minSize, damageDealt.size());
        minSize = Math.min(minSize, damageTaken.size());
        minSize = Math.min(minSize, wardsPlaced.size());
        minSize = Math.min(minSize, playerChampion.size());
        minSize = Math.min(minSize, playerRole.size());
        
        // Perform bootstrapping for the specified number of samples
        for (int i = 0; i < numSamples; i++) {
            DataContainer bootstrapSample = new DataContainer();

            // Generate random indices with replacement
            for (int j = 0; j < numData(); j++) {
                int randomIndex = random.nextInt(numData());
                // Add data corresponding to the randomly selected index to the bootstrap sample
                bootstrapSample.matchID.add(matchID.get(randomIndex));
                bootstrapSample.matchDuration.add(matchDuration.get(randomIndex));
                bootstrapSample.matchOutcome.add(matchOutcome.get(randomIndex));
                bootstrapSample.kills.add(kills.get(randomIndex));
                bootstrapSample.deaths.add(deaths.get(randomIndex));
                bootstrapSample.assists.add(assists.get(randomIndex));
                bootstrapSample.goldEarned.add(goldEarned.get(randomIndex));
                bootstrapSample.damageDealt.add(damageDealt.get(randomIndex));
                bootstrapSample.damageTaken.add(damageTaken.get(randomIndex));
                bootstrapSample.wardsPlaced.add(wardsPlaced.get(randomIndex));
                bootstrapSample.playerChampion.add(playerChampion.get(randomIndex));
                bootstrapSample.playerRole.add(playerRole.get(randomIndex));
            }

            // Add the bootstrap sample to the list of bootstrap samples
            bootstrapSamples.add(bootstrapSample);
        }

        return bootstrapSamples;
    }

    // Method to get the number of data points
    private int numData() {
        // Assuming all lists have the same size
        return matchID.size();
    }
    
    
    
 
    // Method to fill missing data
    public void fillMissingData() {
        // Empty for now
    }

    
    
    // Getters for data lists
    // Add setters if needed (I dont think we need to though)

    public List<Integer> getMatchID() {
        return matchID;
    }

    public List<Integer> getMatchDuration() {
        return matchDuration;
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

    public List<String> getPlayerChampion() {
        return playerChampion;
    }

    public List<String> getPlayerRole() {
        return playerRole;
    }
}