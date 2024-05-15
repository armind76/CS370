import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Entry {

	public static void main(String[] args) throws IOException {

		// Enter data using BufferReader
		System.out.print("Enter summonerid (no Spaces): ");
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
        String name = reader.readLine();

        
		HttpGet get = new HttpGet();
		
		get.getPuid(name);
		
		ReadCSV csv = new ReadCSV();
		DataContainer cont = new DataContainer(csv.scanFIle());
		
		
		List<DataPoint> dp = new ArrayList<>();
		cont.conversion(dp);
		
		decisionTree dt = new decisionTree();
		Node root = dt.buildDecisionTree(dp);
		
		
		

	}

}
