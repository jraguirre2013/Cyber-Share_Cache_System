import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class All_Search 
{
	public ArrayList<String> searchAllRecords(Database_Connection connect)
	{
		ResultSet results=connect.queryAllRecords(connect);
		ArrayList<String> all=new ArrayList<String>();
		try
		{
			while(true)
			{
				all.add(results.getString("URL"));
				results.next();
			}
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return all;
	}
}
