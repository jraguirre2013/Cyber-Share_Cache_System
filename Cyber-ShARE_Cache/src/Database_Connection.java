import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Database_Connection 
{
	protected String host="jdbc:mysql://localhost:3306/Cyber-ShARE_CacheDB";
	private String pass="mypass";
	private String userName="root";
	protected Connection connection;
	private SQL_Commands command=new SQL_Commands();
	private ResultSet result;
	Output_Writer writer=new Output_Writer();
	public void databaseConnect()
	{
		try 
		{
			writer.writeOutput("Connecting to the database");
			this.connection=DriverManager.getConnection(host, userName, pass);
		} 
		catch (SQLException err) 
		{
			writer.writeOutput(err.getMessage());
		}
	}
	public Connection getDBConnection()
	{
		return this.connection;
	}
	public ResultSet queryDatabase(Database_Connection connection, String url)
	{
		result=command.submitSQLQuery(connection, url);
		try 
		{
			result.first();
			this.updateDatabase(connection, "Num_Accessed", Integer.toString(result.getInt("Num_Accessed")+1), Integer.toString(result.getInt("ID")));
		} 
		catch (SQLException e) 
		{
			if(e.getMessage().equals("Illegal operation on empty result set."))
			{
				VISKO_Query query=new VISKO_Query();
				query.createVISKORequest(url, connection,"add");
				result=this.queryDatabase(connection, url);
			}
			writer.writeOutput(e.getMessage());
		}
		return result;
	}
	public void updateDatabase(Database_Connection connection, String column_Name, String value, String id)
	{
		int success;
		success=command.submitSQLUpdate(connection, column_Name, value, id);
		if(success==0)
		{
			writer.writeOutput("Error In Update");
		}
		else
		{
			writer.writeOutput("Record(s) updated");
		}
	}
	public void insertInDatabase(Database_Connection connection, String[] data)
	{
		command.submitSQLInsert(connection, data);
	}
	public ResultSet queryDirty(Database_Connection connection)
	{
		result=command.submitSQLDirtyQuery(connection);
		try 
		{
			result.first();
		} 
		catch (SQLException e) 
		{
			writer.writeOutput(e.getMessage());
		}
		return result;
	}
	public ResultSet queryUnreachable(Database_Connection connection)
	{
		result=command.submitSQLUnreachableQuery(connection);
		try 
		{
			result.first();
		} 
		catch (SQLException e) 
		{
			writer.writeOutput(e.getMessage());
		}
		return result;
	}
	public ResultSet queryHash(Database_Connection connection, String url)
	{
		result=command.submitSQLHashQuery(connection, url);
		try
		{
			result.first();
		}
		catch(SQLException e)
		{
			writer.writeOutput(e.getMessage());
		}
		return result;
	}
	public ResultSet queryAllRecords(Database_Connection connection)
	{
		result=command.submitSQLAllRecordQuery(connection);
		try
		{
			result.first();

		}
		catch(SQLException e)
		{
			writer.writeOutput(e.getMessage());
		}
		return result;
	}
	public void storeToDatabase(Database_Connection connection, String url, String viz, String hash)
	{
		boolean result=command.submitSQLInsert(connection, url, hash, viz);
		if(result==false)
		{
			writer.writeOutput("Store "+url+" Successful");
		}
		else
		{
			writer.writeOutput("Store "+url+" Unsuccessfull");
		}
	}
	public void clearMarks(Database_Connection connection)
	{
		boolean result=command.submitSQLMarksClear(connection);
		if(result==false)
		{
			writer.writeOutput("Marks cleared");
		}
		else
		{
			writer.writeOutput("Marks not cleared");
		}
	}
	public void updateDatabase_Maintenance(Database_Connection connect, String url, String viz, String hash)
	{
		boolean result=command.submitSQLMaintenance_Update(connect, url, viz, hash);
		if(result==false)
		{
			writer.writeOutput("Update "+url+" Successful");
		}
		else
		{
			writer.writeOutput("Update "+url+" Unsuccessfull");
		}
	}
}
