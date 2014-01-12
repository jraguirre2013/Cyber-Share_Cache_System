import java.util.ArrayList;


public class Maintenance 
{
	private Web_Connection web=new Web_Connection();
	private Search_Controller search=new Search_Controller();
	private VISKO_Query visko=new VISKO_Query();
	public void maintainDatabase(Database_Connection connect)
	{
		ArrayList<String> all=search.getAllRecords(connect);
		for(int i=0;i<all.size();i++)
		{
			web.compareHashs(connect, all.get(i));
		}
		ArrayList<String> dirty=search.getDirtyRecords(connect);
		for(int j=0;j<dirty.size();j++)
		{
			visko.createVISKORequest(dirty.get(j), connect,"maintain");
		}
		connect.clearMarks(connect);
	}
}
