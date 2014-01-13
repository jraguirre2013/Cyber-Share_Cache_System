import java.util.Date;


public class Timer_Thread extends Thread
{
//TODO these two lines of code specify the time for the scheduled mantenance
	protected int hours=00;
	protected int minuets=00;
	private Maintenance maintenance=new Maintenance();
	private Database_Connection connection=new Database_Connection();
	private Date date=new Date();
	private Output_Writer writer=new Output_Writer();
	public Timer_Thread(){}
	public void run()
	{
		while(true)
		{
			while((date.getHours()!=hours)||(date.getMinutes()!=minuets)){}
			writer.writeOutput("-----------------Maintenance------------------");
			writer.writeOutput("Begining scheduled maintenance");
			connection.databaseConnect();
			maintenance.maintainDatabase(connection);
			writer.writeOutput("-----------------End of maintenance----------------");
			writer.writeOutput("\n");
		}
	}
}
