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
    private List<Integer> playerClass = new ArrayList<>(); // New list for champion classes

    public void categorizeChampions(Map<Integer, Integer> championClassMap) {
        playerClass.clear(); // Clear existing data
        for (Integer id : playerChampionID) {
            playerClass.add(championClassMap.getOrDefault(id, 0)); // Default to 0 if champion not mapped
        }}

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

    public static Map<Integer, Integer> createChampionClassMap() {
        Map<Integer, Integer> map = new HashMap<>();
        // Tanks
        map.put(12, 1);   // Alistar
        map.put(111, 1);  // Nautilus
        map.put(54, 1);   // Malphite
        map.put(33, 1);   // Rammus
        map.put(113, 1);  // Sejuani
        map.put(89, 1);   // Leona
        map.put(223, 1);  // Tahm Kench
        map.put(516, 1);  // Ornn
        map.put(201, 1);  // Braum
        map.put(57, 1);   // Maokai
        map.put(3, 1);    // Galio
        map.put(14, 1);   // Sion
        map.put(78, 1);   // Poppy
        map.put(154, 1);  // Zac
    
        // Fighters
        map.put(24, 2);   // Jax
        map.put(64, 2);   // Lee Sin
        map.put(58, 2);   // Renekton
        map.put(77, 2);   // Udyr
        map.put(122, 2);  // Darius
        map.put(86, 2);   // Garen
        map.put(83, 2);   // Yorick
        map.put(5, 2);    // Xin Zhao
        map.put(2, 2);    // Olaf
        map.put(39, 2);   // Irelia
        map.put(240, 2);  // Kled
        map.put(120, 2);  // Hecarim
        map.put(106, 2);  // Volibear
        map.put(92, 2);   // Riven
        map.put(102, 2);  // Shyvana
    
        // Mages
        map.put(45, 3);   // Veigar
        map.put(134, 3);  // Syndra
        map.put(99, 3);   // Lux
        map.put(103, 3);  // Ahri
        map.put(1, 3);    // Annie
        map.put(61, 3);   // Orianna
        map.put(112, 3);  // Viktor
        map.put(69, 3);   // Cassiopeia
        map.put(127, 3);  // Lissandra
        map.put(90, 3);   // Malzahar
        map.put(115, 3);  // Ziggs
        map.put(63, 3);   // Brand
        map.put(43, 3);   // Karma
        map.put(143, 3);  // Zyra
    
        // Assassins
        map.put(238, 4);  // Zed
        map.put(7, 4);    // LeBlanc
        map.put(55, 4);   // Katarina
        map.put(91, 4);   // Talon
        map.put(245, 4);  // Ekko
        map.put(84, 4);   // Akali
        map.put(114, 4);  // Fiora
        map.put(141, 4);  // Kayn
        map.put(38, 4);   // Kassadin
        map.put(105, 4);  // Fizz
        map.put(121, 4);  // Kha'Zix
    
        // Marksmen
        map.put(110, 5);  // Varus
        map.put(21, 5);   // Miss Fortune
        map.put(51, 5);   // Caitlyn
        map.put(222, 5);  // Jinx
        map.put(119, 5);  // Draven
        map.put(22, 5);   // Ashe
        map.put(81, 5);   // Ezreal
        map.put(96, 5);   // Kog'Maw
        map.put(236, 5);  // Lucian
        map.put(15, 5);   // Sivir
        map.put(29, 5);   // Twitch
        map.put(498, 5);  // Xayah
        map.put(18, 5);   // Tristana
    
        // Supports
        map.put(37, 6);   // Sona
        map.put(412, 6);  // Thresh
        map.put(267, 6);  // Nami
        map.put(16, 6);   // Soraka
        map.put(40, 6);   // Janna
        map.put(44, 6);   // Taric
        map.put(497, 6);  // Rakan
        map.put(117, 6);  // Lulu
        map.put(25, 6);   // Morgana
        map.put(432, 6);  // Bard
    
        return map;
    }
    
}
