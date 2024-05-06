import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Node {
   public boolean isLeaf;
  public  int classLabel;
   public List<Node> children = new ArrayList<>(); // For multi-way splits (categorical)
  public  Node leftChild; // For binary splits (continuous)
  public  Node rightChild; // For binary splits (continuous)
  public  int splittingAttribute;
  public  boolean isContinuous;
  public double splittingValue; // Only used for continuous attributes
  public Map<String, Integer> categoryToChildIndex; // Maps category to child index for multi-way categorical splits

    // Constructor
    public Node() {
        children = new ArrayList<>();
        categoryToChildIndex = new HashMap<>();
    }
}