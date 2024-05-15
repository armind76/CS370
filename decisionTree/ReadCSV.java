import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;  

public class ReadCSV {
	public ReadCSV(){}
	public List<MatchData> scanFIle() throws FileNotFoundException {
			List<MatchData> toReturn = new ArrayList<>();
			Scanner sc = new Scanner(new File("leaguestats.csv"));  
			sc.useDelimiter(",");
			Random rand = new Random();
	        int randInt = rand.nextInt(1000);
	        
	        for (int i = 0; i < randInt * 10 ; i++ ) //randomly select 10 matches (from the same player)
			{
				if(sc.hasNextLine()){
					sc.nextLine();
				}
			}
			for (int i = 0 ; i <10 ; i++) {
				MatchData m = new MatchData();
				sc.next();
				m.winLoss = sc.next(); //win loss
				sc.next();sc.next();sc.next();sc.next();sc.next();sc.next();sc.next();
				m.playerRole = sc.next(); //role
				toReturn.add(m);
				sc.nextLine();
			}
			sc.close();
			return toReturn;
	}
}
