import java.util.*;

 

public class decisionTree {

    private double calculateGini(List<DataPoint> data) {
        Map<Integer, Integer> labelCounts = new HashMap<>();
        for (DataPoint point : data) {
            labelCounts.merge(point.classLabel, 1, Integer::sum);
        }
        double impurity = 1.0;
        int total = data.size();
        for (int count : labelCounts.values()) {
            double probability = count / (double) total;
            impurity -= probability * probability;
        }
        return impurity;
    }

    public Node buildDecisionTree(List<DataPoint> data) {
        if (isPure(data) || data.size() <= 1) {
            Node leaf = new Node();
            leaf.isLeaf = true;
            leaf.classLabel = data.get(0).classLabel;
            return leaf;
        }
    
        BestSplit bestSplit = findBestSplit(data);
    
        if (bestSplit.leftSplit.isEmpty() || bestSplit.rightSplit.isEmpty()) {
            Node leaf = new Node();
            leaf.isLeaf = true;
            leaf.classLabel = majorityClassLabel(data);
            return leaf;
        }
    
        Node node = new Node();
        node.splittingAttribute = bestSplit.attribute;
        node.isContinuous = bestSplit.isContinuous;
    
        if (bestSplit.isContinuous) {
            node.splittingValue = bestSplit.value;
            node.leftChild = buildDecisionTree(bestSplit.leftSplit);
            node.rightChild = buildDecisionTree(bestSplit.rightSplit);
        } else {
            node.categoryToChildIndex = new HashMap<>();
            node.children = new ArrayList<>();
            int index = 0;
            for (String category : bestSplit.categorySplits.keySet()) {
                node.categoryToChildIndex.put(category, index);
                node.children.add(buildDecisionTree(bestSplit.categorySplits.get(category)));
                index++;
            }
        }
    
        return node;
    }
    
    private boolean isPure(List<DataPoint> data) {
        int firstLabel = data.get(0).classLabel;
        for (DataPoint dp : data) {
            if (dp.classLabel != firstLabel) {
                return false;
            }
        }
        return true;
    }

    public BestSplit findBestSplit(List<DataPoint> data) {
        BestSplit bestSplit = new BestSplit();
        double bestImpurity = Double.MAX_VALUE;
    
        // Continuous attributes
        for (int attribute = 0; attribute < data.get(0).attributes.length; attribute++) {
            final int currentAttribute = attribute; // final variable for use in lambda
            data.sort(Comparator.comparingDouble(dp -> dp.attributes[currentAttribute]));
    
            List<DataPoint> leftSplit = new ArrayList<>();
            List<DataPoint> rightSplit = new ArrayList<>(data);
    
            for (int i = 1; i < data.size(); i++) {
                leftSplit.add(data.get(i - 1));
                rightSplit.remove(0);
    
                if (data.get(i).attributes[currentAttribute] > data.get(i - 1).attributes[currentAttribute]) {
                    double splitPoint = (data.get(i).attributes[currentAttribute] + data.get(i - 1).attributes[currentAttribute]) / 2;
                    double leftGini = calculateGini(leftSplit);
                    double rightGini = calculateGini(rightSplit);
                    double weightedGini = (leftGini * leftSplit.size() + rightGini * rightSplit.size()) / data.size();
    
                    if (weightedGini < bestImpurity) {
                        bestImpurity = weightedGini;
                        bestSplit.attribute = currentAttribute;
                        bestSplit.isContinuous = true;
                        bestSplit.value = splitPoint;
                        bestSplit.leftSplit = new ArrayList<>(leftSplit);
                        bestSplit.rightSplit = new ArrayList<>(rightSplit);
                    }
                }
            }
        }
    
        // Categorical attributes
        for (int attribute = 0; attribute < data.get(0).categories.length; attribute++) {
            Map<String, List<DataPoint>> currentSplits = new HashMap<>();
            for (DataPoint dp : data) {
                currentSplits.computeIfAbsent(dp.categories[attribute], k -> new ArrayList<>()).add(dp);
            }
    
            double currentImpurity = 0.0;
            for (List<DataPoint> splitList : currentSplits.values()) {
                currentImpurity += calculateGini(splitList) * splitList.size();
            }
            currentImpurity /= data.size();
                
    
            if (currentImpurity < bestImpurity) {
                bestImpurity = currentImpurity;
                bestSplit.isContinuous = false;
                bestSplit.attribute = attribute;
                bestSplit.categorySplits = new HashMap<>(currentSplits);
            }
        }
    
        return bestSplit;
    }
    private int majorityClassLabel(List<DataPoint> data) {
        Map<Integer, Integer> labelCounts = new HashMap<>();
        for (DataPoint point : data) {
            labelCounts.merge(point.classLabel, 1, Integer::sum);
        }
        return Collections.max(labelCounts.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
    

    class BestSplit {
        int attribute;
        boolean isContinuous;
        double value;  // Threshold for continuous splits
        List<DataPoint> leftSplit;
        List<DataPoint> rightSplit;
        Map<String, List<DataPoint>> categorySplits;
    }
}