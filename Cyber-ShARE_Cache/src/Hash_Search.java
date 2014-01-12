import java.sql.ResultSet;


public class Hash_Search 
{
	public ResultSet searchHash(Database_Connection connection, String url)
	{
		ResultSet results;
		results=connection.queryHash(connection, url);
		return results;
	}
}
