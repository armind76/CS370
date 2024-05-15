import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RandomForrest {
	List<Node> trees = new LinkedList<>();
	public RandomForrest() throws FileNotFoundException{
		for (int i = 0 ; i < 100; i ++) {
			ReadCSV csv = new ReadCSV();
			DataContainer cont = new DataContainer(csv.scanFIle());
			List<DataPoint> dp = new ArrayList<>();
			cont.conversion(dp);
			decisionTree dt = new decisionTree();
			Node n = dt.buildDecisionTree(dp);
			trees.add(n);
		}
	}
	
	
	public void makePrediction() {
		for(Node t: trees) {
			
		}
	}
	
	
	
}
