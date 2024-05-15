import java.util.ArrayList;
import java.util.List;

public class DataContainer {
	private List<String> 	winLoss			= new ArrayList<>();
    private List<String> 	playerChampion 	= new ArrayList<>();
    private List<String> 	playerRole 		= new ArrayList<>();
    
    public DataContainer(List<MatchData> data){
    	for(MatchData d: data) {
    		this.winLoss.add(d.winLoss);
    		this.playerRole.add(d.playerRole);
    		this.playerChampion.add(d.championID);
    	}
    }
    
    public void conversion(List<DataPoint> dp) {
    	for(int i = 0 ; i < this.winLoss.size(); i++) {
    		DataPoint temp = new DataPoint();
    		if(winLoss.get(i) =="0") {
    			temp.classLabel = 0;
    		}
    		else {
    			temp.classLabel = 1;
    		}
    		temp.categories[0] = playerRole.get(i);
    		dp.add(temp); 
    	}
    }
    
    public void print() {
    	for(String a: winLoss) {
    		System.out.println(a);
    	}
    	for(String a: playerRole) {
    		System.out.println(a);
    	}
    	
    }
    
}
