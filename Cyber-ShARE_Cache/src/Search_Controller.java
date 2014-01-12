import java.sql.ResultSet;
import java.util.ArrayList;
public class Search_Controller 
{
	private Unreachable_Search unreach=new Unreachable_Search();
	private Dirty_Search dirty=new Dirty_Search();
	private VIZ_Search viz=new VIZ_Search();
	private Hash_Search hash=new Hash_Search();
	private All_Search all=new All_Search();
	private ArrayList<String> results;
	public ArrayList<String> getUnreachableRecords(Database_Connection connect)
	{
		results=unreach.searchUnreachableRecords(connect);
		return results;
	}
	public ArrayList<String> getDirtyRecords(Database_Connection connect)
	{
		results=dirty.searchDirtyRecords(connect);
		return results;
	}
	public ArrayList<String> getViz(Database_Connection connect, String[] url)
	{
		results=viz.searchVizRecords(connect, url);
		return results;
	}
	public ResultSet getHashRecords(Database_Connection connect, String url)
	{
		ResultSet result=hash.searchHash(connect, url);
		return result;
	}
	public ArrayList<String> getAllRecords(Database_Connection connect)
	{
		results=all.searchAllRecords(connect);
		return results;
	}
}
