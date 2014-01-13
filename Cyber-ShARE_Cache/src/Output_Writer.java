import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;


public class Output_Writer 
{
//TODO this line specifies the location and name of the output text file
	protected String path="/Users/joseph304/Documents/workspace/Cyber-ShARE_Cache/Orion Cache-Log.txt";
	public void writeOutput(String message)
	{
		java.util.Date timestamp=new java.util.Date();
		FileWriter output;
		try 
		{
			output = new FileWriter(path,true);
			if(message.equals("\n"))
			{
				output.write("\n");
			}
			else
			{
				output.write(new Timestamp(timestamp.getTime())+"  "+message+"\n");			
			}
			output.close();
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}
	}
}
