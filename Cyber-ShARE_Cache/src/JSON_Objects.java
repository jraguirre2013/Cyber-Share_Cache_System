import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

@SuppressWarnings("deprecation")
public class JSON_Objects 
{
	public String createJSON(String url)
	{
		String json="";
		json="{\"source_urls\":[{\"source_url\":"+url+"\"}]}";
		return json;
	}
	public String encodeJSONObj(String json)
	{
		String request="http://iw.cs.utep.edu:8080/vlc-visko-interface/HTMLQueryBatchService?jsonSources=";
		request+=URLEncoder.encode(json);
		return request;
	}
	public String decodeJSONReq(String request)
	{
		int i=0;
		String decoded="";
		while(i<request.length())
		{
			if(request.charAt(i)=='=')
			{
				i+=1;
				break;
			}
			i++;
		}
		decoded=URLDecoder.decode(request.substring(i,request.length()));
		return decoded;
	}
	public String getURL(String json)
	{
		return json.substring(31,json.length()-4);
	}
	public String createJSONLarge(ArrayList<String> urls)
	{
		String json;
		json="{\"source_urls\":[";
		for(int i=0;i<urls.size();i++)
		{
			json+="[{\"source_url\":"+urls.get(i)+"\"}]}";
			if(i+1<urls.size())
			{
				json+=",";
			}
		}
		return json;
	}
}
