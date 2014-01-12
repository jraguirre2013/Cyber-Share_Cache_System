import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;


public class Cache 
{
	private Output_Writer writer=new Output_Writer();
	public String searchCache(String[]urls)
	{
		writer.writeOutput("Requesting service on URLs: "+Arrays.toString(urls));
		String result;
		Database_Connection database=new Database_Connection();
		JSON_Objects json=new JSON_Objects();
		database.databaseConnect();
		Search_Controller search=new Search_Controller();
		ArrayList<String> searchResults=search.getViz(database, urls);
		result=json.createJSONLarge(searchResults);
		URLEncoder.encode(result);
		writer.writeOutput("Request fulfilled");
		return result;
	}
}
