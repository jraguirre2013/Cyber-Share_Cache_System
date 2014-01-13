import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Dirty_Search
{
	public ArrayList<String> searchDirtyRecords(Database_Connection connect)
	{
		ResultSet results;
		ArrayList<String> dirty=new ArrayList<String>();
		results=connect.queryDirty(connect);
		try
		{
			while(true)
			{
				dirty.add(results.getString("URL"));
				results.next();
			}
		}
		catch(SQLException e)
		{
			e.getMessage();
		}
		return dirty;
	}
}
