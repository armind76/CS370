import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;

public class dataContainer {
    private List<Integer> matchID = new ArrayList<>();
    private List<String> matchOutcome = new ArrayList<>();
    private List<Integer> kills = new ArrayList<>();
    private List<Integer> deaths = new ArrayList<>();
    private List<Integer> assists = new ArrayList<>();
    private List<Integer> goldEarned = new ArrayList<>();
    private List<Integer> damageDealt = new ArrayList<>();
    private List<Integer> damageTaken = new ArrayList<>();
    private List<Integer> wardsPlaced = new ArrayList<>();
    private List<Integer> playerChampionID = new ArrayList<>();
    private List<String> playerChampion = new ArrayList<>();
    private List<String> playerRole = new ArrayList<>();

    public void readDataFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 11) { // Ensure all columns are present
                    matchID.add(Integer.parseInt(data[0].trim()));
                    matchOutcome.add(data[1].trim().equals("0") ? "Defeat" : "Victory");
                    kills.add(Integer.parseInt(data[2].trim()));
                    deaths.add(Integer.parseInt(data[3].trim()));
                    assists.add(Integer.parseInt(data[4].trim()));
                    goldEarned.add(Integer.parseInt(data[5].trim()));
                    damageDealt.add(Integer.parseInt(data[6].trim()));
                    damageTaken.add(Integer.parseInt(data[7].trim()));
                    wardsPlaced.add(Integer.parseInt(data[8].trim()));
                    playerRole.add(data[9].trim());
                    playerChampionID.add(Integer.parseInt(data[10].trim()));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error processing CSV file: " + e.getMessage());
        }
    }

    public List<dataContainer> bootStrap(int numSamples) {
        List<dataContainer> bootstrapSamples = new ArrayList<>();
        Random random = new Random();
        int dataSize = numData();

        for (int i = 0; i < numSamples; i++) {
            dataContainer sample = new dataContainer();
            for (int j = 0; j < dataSize; j++) {
                int index = random.nextInt(dataSize);
                sample.addDataFromIndex(this, index);
            }
            bootstrapSamples.add(sample);
        }
        return bootstrapSamples;
    }

    private void addDataFromIndex(dataContainer source, int index) {
        matchID.add(source.matchID.get(index));
        matchOutcome.add(source.matchOutcome.get(index));
        kills.add(source.kills.get(index));
        deaths.add(source.deaths.get(index));
        assists.add(source.assists.get(index));
        goldEarned.add(source.goldEarned.get(index));
        damageDealt.add(source.damageDealt.get(index));
        damageTaken.add(source.damageTaken.get(index));
        wardsPlaced.add(source.wardsPlaced.get(index));
        playerChampionID.add(source.playerChampionID.get(index));
        playerRole.add(source.playerRole.get(index));
    }

    public int numData() {
        return matchID.size(); // Assuming all lists have the same size after CSV read
    }

    public void matchChampionNames(Map<Integer, String> championMap) {
        playerChampion.clear(); // Clear existing data
        for (Integer id : playerChampionID) {
            playerChampion.add(championMap.getOrDefault(id, "Unknown"));
        }
    }


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
