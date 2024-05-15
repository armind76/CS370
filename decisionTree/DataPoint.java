public class DataPoint {
  public double[] attributes;  // Continuous attributes like kills, goldEarned, etc.
  public String[] categories;  // Categorical attributes like matchOutcome, playerRole, etc.
  public int classLabel;       // This could be an integer representation of matchOutcome or any categorical class label.

  // Fully parameterized constructor
  public DataPoint(double[] attributes, String[] categories, int classLabel) {
      this.attributes = attributes != null ? attributes : new double[0];  // Ensure attributes are not null
      this.categories = categories != null ? categories : new String[0];  // Ensure categories are not null
      this.classLabel = classLabel;
  }

  // Constructor for only attributes, initializes categories to an empty array
  public DataPoint(double[] attributes, int classLabel) {
      this.attributes = attributes != null ? attributes : new double[0];  // Ensure attributes are not null
      this.categories = new String[0];  // Initialize to empty array
      this.classLabel = classLabel;  // Default class label, adjust as needed
  }
  public DataPoint(double[] attributes) {
    this.attributes = attributes != null ? attributes : new double[0];  // Ensure attributes are not null
    this.categories = new String[0];  // Initialize to empty array
    this.classLabel = 0;  // Default class label, adjust as needed
}


  // Constructor for only categories, initializes attributes to an empty array
  public DataPoint(String[] categories) {
      this.attributes = new double[0];  // Initialize to empty array
      this.categories = categories != null ? categories : new String[0];  // Ensure categories are not null
      this.classLabel = 0;  // Default class label, adjust as needed
  }

  // Constructor that accepts indices to map from DataContainer
  public DataPoint(dataContainer container, int index) {
      // Assuming that matchOutcome is mapped to a binary class label, Victory: 1, Defeat: 0
      this.classLabel = "Victory".equals(container.getMatchOutcome().get(index)) ? 1 : 0;

      // Map continuous attributes
      this.attributes = new double[]{
          container.getKills().get(index),
          container.getDeaths().get(index),
          container.getAssists().get(index),
          container.getGoldEarned().get(index),
          container.getDamageDealt().get(index),
          container.getDamageTaken().get(index),
          container.getWardsPlaced().get(index)
      };

      // Ensure the role and champion are safely extracted, considering potential null values
      String role = container.getPlayerRole().size() > index ? container.getPlayerRole().get(index) : "";
      String champion = container.getplayerChampion().size() > index ? container.getplayerChampion().get(index) : "";
      this.categories = new String[]{role, champion};
  }

  public double[] getAttributes() {
    return attributes;
}
}
