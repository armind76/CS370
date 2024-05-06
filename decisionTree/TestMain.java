import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestMain {

    public static void main(String[] args) {
        List<DataPoint> dataPoints = new ArrayList<>();
        // Existing data points
        dataPoints.add(new DataPoint(new double[]{70}, new String[]{"Sunny"}, 1));
        dataPoints.add(new DataPoint(new double[]{80}, new String[]{"Sunny"}, 1));
        dataPoints.add(new DataPoint(new double[]{85}, new String[]{"Overcast"}, 1));
        dataPoints.add(new DataPoint(new double[]{72}, new String[]{"Rainy"}, 0));
        dataPoints.add(new DataPoint(new double[]{68}, new String[]{"Rainy"}, 0));
        dataPoints.add(new DataPoint(new double[]{65}, new String[]{"Overcast"}, 1));
        dataPoints.add(new DataPoint(new double[]{64}, new String[]{"Sunny"}, 0));

        // New data points
        dataPoints.add(new DataPoint(new double[]{75}, new String[]{"Overcast"}, 1));
        dataPoints.add(new DataPoint(new double[]{60}, new String[]{"Rainy"}, 0));
        dataPoints.add(new DataPoint(new double[]{90}, new String[]{"Sunny"}, 1));
        dataPoints.add(new DataPoint(new double[]{85}, new String[]{"Rainy"}, 0));
        dataPoints.add(new DataPoint(new double[]{55}, new String[]{"Overcast"}, 1));
        dataPoints.add(new DataPoint(new double[]{88}, new String[]{"Sunny"}, 1));
        dataPoints.add(new DataPoint(new double[]{50}, new String[]{"Rainy"}, 0));
        dataPoints.add(new DataPoint(new double[]{95}, new String[]{"Sunny"}, 1));
        dataPoints.add(new DataPoint(new double[]{70}, new String[]{"Rainy"}, 0));
        dataPoints.add(new DataPoint(new double[]{60}, new String[]{"Overcast"}, 1));

        List<DataPoint>[] splitData = splitData(dataPoints, 0.7);
        List<DataPoint> trainingData = splitData[0];
        List<DataPoint> testingData = splitData[1];

        decisionTree decisionTree = new decisionTree();
        Node root = decisionTree.buildDecisionTree(trainingData);

        printTree(root, 0);
        double accuracy = calculateAccuracy(root, testingData);
        System.out.println("Accuracy: " + accuracy * 100 + "%");
    }
    @SuppressWarnings("unchecked")
    private static List<DataPoint>[] splitData(List<DataPoint> dataPoints, double trainSizeRatio) {
        Collections.shuffle(dataPoints);
        int trainSize = (int) (dataPoints.size() * trainSizeRatio);
        List<DataPoint> trainingData = new ArrayList<>(dataPoints.subList(0, trainSize));
        List<DataPoint> testingData = new ArrayList<>(dataPoints.subList(trainSize, dataPoints.size()));
    
        List<DataPoint>[] result = (List<DataPoint>[]) Array.newInstance(List.class, 2);
        result[0] = trainingData;
        result[1] = testingData;
        return result;
    }
    
    private static double calculateAccuracy(Node root, List<DataPoint> testData) {
        int correctCount = 0;
        for (DataPoint dp : testData) {
            int predicted = predict(root, dp);
            if (predicted == dp.classLabel) {
                correctCount++;
            }
        }
        return correctCount / (double) testData.size();
    }

    private static int predict(Node node, DataPoint dataPoint) {
        while (!node.isLeaf) {
            if (node.isContinuous) {
                node = dataPoint.attributes[node.splittingAttribute] <= node.splittingValue ? node.leftChild : node.rightChild;
            } else {
               // System.out.println("Category: " + dataPoint.categories[node.splittingAttribute]);
                //System.out.println("Available categories in node: " + node.categoryToChildIndex);
                int index = node.categoryToChildIndex.get(dataPoint.categories[node.splittingAttribute]);
                node = node.children.get(index);
              

            }
        }
        return node.classLabel;
    }

    private static void printTree(Node node, int level) {
        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < level; i++) {
            prefix.append("   ");
        }
        if (node.isLeaf) {
            System.out.println(prefix + "Leaf: Play = " + node.classLabel);
        } else {
            if (node.isContinuous) {
                System.out.println(prefix + "Node: If Attribute[" + node.splittingAttribute + "] <= " + node.splittingValue);
                printTree(node.leftChild, level + 1);
                System.out.println(prefix + "Node: If Attribute[" + node.splittingAttribute + "] > " + node.splittingValue);
                printTree(node.rightChild, level + 1);
            } else {
                for (Node child : node.children) {
                    printTree(child, level + 1);
                }
            }
        }
    }
}
