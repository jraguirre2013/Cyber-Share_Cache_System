import java.net.URLEncoder;
import java.util.ArrayList;


public class VISKO_Query 
{
	private Output_Writer writer=new Output_Writer();
	private JSON_Objects x=new JSON_Objects();
	private Web_Connection web=new Web_Connection();
	public void createVISKORequest(String url, Database_Connection connection, String status)
	{
		String json=x.createJSON(url);
		String request=x.encodeJSONObj(json);
		String result=submitVISKORequest(request);
		result=x.decodeJSONReq(result);
		result=x.getURL(result);
		String hash=web.convertStringToHash(web.convertWebToString(url, connection));
		if(status.equals("maintain"))
		{
			connection.updateDatabase_Maintenance(connection, url, result, hash);
		}
		if(status.equals("add"))
		{
			connection.storeToDatabase(connection, url, result, hash);
		}
	}
	public String submitVISKORequest(String encodedURL)
	{
		writer.writeOutput("Submitting VISKO request: "+encodedURL);
		//TODO actual visko connection and result implementation here
		//needs to return the encoded url here
		return URLEncoder.encode("{\"source_urls\":[{\"source_url\":\"http://www.rpgbooster.com/wp-content/uploads/2013/03/Ben-Wootten-Black-dragon-blackfang-Pathfinder.jpg\"}]}");
	}
}
