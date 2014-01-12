import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Web_Connection 
{
	private Search_Controller search=new Search_Controller();
	
	public String convertWebToString(String urlread, Database_Connection connect)
	{
		URL url;
		HttpURLConnection connection;
		BufferedReader reader;
		String line;
		String result="";
		try
		{
			url=new URL(urlread);
			connection=(HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while((line=reader.readLine())!=null)
			{
				result+=line;
			}
			reader.close();
		}
		catch(ConnectException e)
		{
			ResultSet query=connect.queryDatabase(connect, urlread);
			try
			{
				query.first();
				connect.updateDatabase(connect, "Unreachable", "1", query.getString("ID"));
			}
			catch(SQLException err)
			{
				err.getMessage();
			}
			return null;
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return result;
	}
	public String convertStringToHash(String urlString)
	{
		String digest="";
		try
		{
			MessageDigest md=MessageDigest.getInstance("MD5");
			byte[]hash=md.digest(urlString.getBytes("UTF-8"));
			StringBuilder sb=new StringBuilder(2*hash.length);
			for(byte b:hash)
			{
				sb.append(String.format("%02x",b&0xff));
			}
			digest=sb.toString();
		}
		catch(UnsupportedEncodingException ex)
		{
			ex.printStackTrace();
		}
		catch(NoSuchAlgorithmException ex)
		{
			ex.printStackTrace();
		}
		return digest;
	}
	public void compareHashs(Database_Connection connect, String url)
	{
		ResultSet hashResult=search.getHashRecords(connect, url);
		String streamedURL=convertWebToString(url, connect);
		if(streamedURL!=null)
		{
			String newHash=convertStringToHash(streamedURL);
			try
			{
				hashResult.first();
				String oldHash=hashResult.getString("Hash");
				if(oldHash.equals(newHash)==false)
				{
					while(hashResult.isLast()!=false)
					{
						connect.updateDatabase(connect, "Dirty", "1", hashResult.getString("ID"));
						hashResult.next();
					}
				}
			}
			catch(SQLException e)
			{
				System.out.println(e.getMessage());
			}
		}
		else
		{
			try
			{
				hashResult.first();
				while(hashResult.isLast()!=false)
				{
					connect.updateDatabase(connect, "Unreachable", "1", hashResult.getString("ID"));
					hashResult.next();
				}
			}
			catch(SQLException e)
			{
				System.out.println(e.getMessage());
			}
		}
	}
}
