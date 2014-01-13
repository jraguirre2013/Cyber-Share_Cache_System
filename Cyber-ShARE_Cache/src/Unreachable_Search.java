import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Unreachable_Search
{
	public ArrayList<String> searchUnreachableRecords(Database_Connection connect)
	{
		ResultSet results;
		ArrayList<String> unreach=new ArrayList<String>();
		results=connect.queryUnreachable(connect);
		try
		{
			while(true)
			{
				unreach.add(results.getString("URL"));
				results.next();
			}
		}
		catch(SQLException e)
		{
			e.getMessage();
		}
		return unreach;
	}
}
