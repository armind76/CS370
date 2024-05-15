import java.util.*;

public class DecisionTreeTest {

    public static void main(String[] args) {
        decisionTree decisionTree = new decisionTree();
        testContinuousSplit(decisionTree);
        testCategoricalSplit(decisionTree);
   
    }

    private static void testContinuousSplit(decisionTree decisionTree) {
        List<DataPoint> data = Arrays.asList(
            new DataPoint(new double[]{1}, 1),
            new DataPoint(new double[]{2}, 1),
            new DataPoint(new double[]{3}, 1),
            new DataPoint(new double[]{4}, 1),
            new DataPoint(new double[]{5}, 0),
            new DataPoint(new double[]{6}, 0),
            new DataPoint(new double[]{7}, 0),
            new DataPoint(new double[]{8}, 0),
            new DataPoint(new double[]{9}, 0),
            new DataPoint(new double[]{10}, 0)
        );

        decisionTree.BestSplit splitResult = decisionTree.findBestSplit(data);
        System.out.println("Continuous Split Test:");
        System.out.println("Expected Threshold: 4.5"); // Assuming the split point between 4 and 5
        System.out.println("Calculated Threshold: " + splitResult.value);
        System.out.println("Expected Left Split Size: 4, Right Split Size: 6"); // Adjusted to match the data
        System.out.println("Actual Left Split Size: " + splitResult.leftSplit.size() + ", Right Split Size: " + splitResult.rightSplit.size());
        System.out.println();
    }

    private static void testCategoricalSplit(decisionTree decisionTree) {
        List<DataPoint> data = Arrays.asList(
            new DataPoint(new String[]{"cat"}),
            new DataPoint(new String[]{"cat"}),
            new DataPoint(new String[]{"dog"}),
            new DataPoint(new String[]{"dog"}),
            new DataPoint(new String[]{"rabbit"}),
            new DataPoint(new String[]{"rabbit"})
        );

        var splitResult = decisionTree.findBestSplit(data);
        System.out.println("Categorical Split Test:");
        System.out.println("Expected Number of Categories: 3");
        System.out.println("Calculated Number of Categories: " + splitResult.categorySplits.size());
        System.out.println("Expected Sizes: Cat: 2, Dog: 2, Rabbit: 2");
        splitResult.categorySplits.forEach((key, value) -> 
            System.out.println("Category: " + key + ", Actual Size: " + value.size())
        );
        System.out.println();
    }

}
