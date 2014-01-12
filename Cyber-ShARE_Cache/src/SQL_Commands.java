import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SQL_Commands
{
	private Statement statement;
	private ResultSet results;
	private Output_Writer writer=new Output_Writer();
	public ResultSet submitSQLQuery(Database_Connection connection, String url)
	{
		results = null;
		String sql="select * from CacheDB where URL='"+url+"';";
		writer.writeOutput("Queying database for: "+url);
		try
		{
			Statement statement=(connection.getDBConnection()).createStatement();
			results=statement.executeQuery(sql);
		}
		catch(SQLException err)
		{
			writer.writeOutput(err.getMessage());
		}
		return results;
	}
	public int submitSQLUpdate(Database_Connection connection, String column_Name, String value, String id)
	{
		int result=0;
		String sql="update CacheDB set "+column_Name+"='"+value+"' where ID='"+id+"';";
		writer.writeOutput("Updating column: "+column_Name+"="+value+" where ID="+id);
		try
		{
			statement=(connection.getDBConnection()).createStatement();
			result=statement.executeUpdate(sql);
		}
		catch(SQLException err)
		{
			writer.writeOutput(err.getMessage());
		}
		return result;
	}
	public void submitSQLInsert(Database_Connection connection, String[] data)
	{
		String sql="insert into CacheDB (URL, VIZ, Num_Accessed, Hash, Dirty, Unreachable) values('"+data[0]+"', '"+data[1]+"', '"+data[2]+"', '"+data[3]+"', '"+data[4]+"', '"+data[5]+"');";
		writer.writeOutput("Inserting into database (URL, VIZ, Number Accessed, Hash, Dirty, Unreachable) values('"+data[0]+"', '"+data[1]+"', '"+data[2]+"', '"+data[3]+"', '"+data[4]+"', '"+data[5]+"')");
		try
		{
			statement=(connection.getDBConnection()).createStatement();
			statement.execute(sql);
		}
		catch(SQLException err)
		{
			writer.writeOutput(err.getMessage());
		}
	}
	public ResultSet submitSQLDirtyQuery(Database_Connection connection)
	{
		results=null;
		String sql="select * from CacheDB where Dirty=1;";
		writer.writeOutput("Querying records marked dirty");
		try
		{
			statement=(connection.getDBConnection()).createStatement();
			results=statement.executeQuery(sql);
		}
		catch(SQLException err)
		{
			writer.writeOutput(err.getMessage());
		}
		return results;
	}
	public ResultSet submitSQLUnreachableQuery(Database_Connection connection)
	{
		results=null;
		String sql="select * from CacheDB where Unreachable=1;";
		writer.writeOutput("Querying records marked unreachable");
		try
		{
			statement=(connection.getDBConnection()).createStatement();
			results=statement.executeQuery(sql);
		}
		catch(SQLException err)
		{
			writer.writeOutput(err.getMessage());
		}
		return results;
	}
	public ResultSet submitSQLHashQuery(Database_Connection connection, String url)
	{
		results=null;
		String sql="select * from CacheDB where URL=\""+url+"\";";
		try
		{
			statement=(connection.getDBConnection()).createStatement();
			results=statement.executeQuery(sql);
			writer.writeOutput("Querying records for the hash values");
		}
		catch(SQLException err)
		{
			writer.writeOutput(err.getMessage());
		}
		return results;
	}
	public ResultSet submitSQLAllRecordQuery(Database_Connection connection)
	{
		results=null;
		String sql="select * from CacheDB;";
		writer.writeOutput("Querying all records");
		try
		{
			statement=(connection.getDBConnection()).createStatement();
			results=statement.executeQuery(sql);
		}
		catch(SQLException e)
		{
			writer.writeOutput(e.getMessage());
		}
		return results;
	}
	public int submitSQLDirtyMarkUpdate(Database_Connection connection, String url, String value)
	{
		int result=0;
		String sql="update CacheDB set Dirty=\""+1+"\";";
		writer.writeOutput("Marking records dirty.");
		try
		{
			statement=(connection.getDBConnection()).createStatement();
			result=statement.executeUpdate(sql);
		}
		catch(SQLException err)
		{
			writer.writeOutput(err.getMessage());
		}
		return result;
	}
	public boolean submitSQLInsert(Database_Connection connection, String url, String hash, String viz)
	{
		boolean result=false;
		String sql="insert into CacheDB (URL, VIZ, Hash) values('"+url+"', '"+viz+"', '"+hash+"');";
		writer.writeOutput("Inserting into database (URL, VIZ, Hash) values('"+url+"', '"+viz+"', '"+hash+"')");
		try
		{
			statement=(connection.getDBConnection()).createStatement();
			result=statement.execute(sql);
		}
		catch(SQLException e)
		{
			writer.writeOutput(e.getMessage());
		}
		return result;
	}
	public boolean submitSQLMarksClear(Database_Connection connect)
	{
		boolean result=false;
		String sql="update cachedb set dirty='0';";
		writer.writeOutput("Resetting the records marked as dirty");
		try
		{
			statement=(connect.getDBConnection()).createStatement();
			result=statement.execute(sql);
		}
		catch(SQLException e)
		{
			writer.writeOutput(e.getMessage());
		}
		return result;	
	}
	public boolean submitSQLMaintenance_Update(Database_Connection connect, String url, String viz, String hash)
	{
		boolean result=false;
		String sql="update cachedb set URL='"+url+"', VIZ='"+viz+"', hash='"+hash+"' where url='"+url+"';";
		String sql2="update cachedb set last_updated=current_time where url='"+url+"';";
		writer.writeOutput("Updating cache URL='"+url+"', VIZ='"+viz+"', hash='"+hash+"' where url='"+url+"'");
		try
		{
			statement=(connect.getDBConnection()).createStatement();
			result=statement.execute(sql);
			statement.execute(sql2);
		}
		catch(SQLException e)
		{
			writer.writeOutput(e.getMessage());
		}
		return result;
	}
}
