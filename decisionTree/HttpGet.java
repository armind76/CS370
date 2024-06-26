import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpGet {
	public HttpGet() {}
	String token = "RGAPI-f5f70f58-a71e-426b-b424-df7aeb2391eb";
	String getIDURL ="https://americas.api.riotgames.com/riot/account/v1/accounts/by-riot-id/";
	String getMatchHistoryURL = "https://americas.api.riotgames.com/lol/match/v5/matches/by-puuid/";
	String tagLine = "/NA1";
	String detailURL = "https://americas.api.riotgames.com/lol/match/v5/matches/";
	

	
	
	public void getPuid(String gameName) throws IOException{
		URL url = new URL(getIDURL + gameName + tagLine + "?api_key=" + token);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.connect();
	
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String result;
		StringBuffer content = new StringBuffer();
		while ((result = in.readLine()) != null) {
			    content.append(result);
			}
		in.close();
		String ID = "";
		int i =0;
		while(content.charAt(i+10) != '"') {
			
			ID = ID + content.charAt( 10 + i );
			i++;
		}
		this.getHistory(ID);
			
	}
	
	public void getHistory(String puid) throws IOException{
		URL url = new URL(getMatchHistoryURL + puid + "/ids" + "?start=0&count=10&api_key=" + token);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.connect();
	
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String result;
		StringBuffer content = new StringBuffer();
		while ((result = in.readLine()) != null) {
			    content.append(result);
			}
		System.out.println(content); 
			int i = 0;
			int j = 0;
			String[] matchID = {"","","","","","","","","","",""};
			while (j < 9) {
				if(content.charAt(i) != '"' && content.charAt(i) != ',' && content.charAt(i) !='[') {
					matchID[j] = matchID[j] + content.charAt(i);
				}
				if(content.charAt(i) == ',') {
					j++;
					System.out.println(j);
				}
				i++;
			}
			detailHistory(matchID);
	}
	
	public void detailHistory(String[] matchID) throws IOException{
		for (String a: matchID ) {
			if(a == "") {continue;}
			URL url = new URL(detailURL + a + "?api_key=" + token);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.connect();
		
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String result;
			StringBuffer content = new StringBuffer();
			while ((result = in.readLine()) != null) {
				    content.append(result);
				}
			System.out.println(content); 
				in.close();
		}
	}
	
	
	
	
	
	
}
