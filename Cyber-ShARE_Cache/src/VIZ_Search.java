import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class VIZ_Search
{
	public ArrayList<String> searchVizRecords(Database_Connection connect, String[]url)
	{
		ResultSet results;
		ArrayList<String> viz=new ArrayList<String>();
		for(int i=0;i<url.length;i++)
		{
			results=connect.queryDatabase(connect,url[i]);
			try
			{
				while(true)
				{
					viz.add(results.getString("VIZ"));
					results.next();
				}
			}
			catch(SQLException e)
			{
				System.out.println(e.getMessage());
			}
		}
		return viz;
	}
}
