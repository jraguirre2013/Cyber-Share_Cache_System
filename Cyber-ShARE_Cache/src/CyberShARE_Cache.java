import java.util.ArrayList;
public class CyberShARE_Cache 
{
	public static void main(String[] args) 
	{
		Timer_Thread timer=new Timer_Thread();
		timer.start();
		//TODO this line is used as test place holder, in the future urls will come in from args
		String[] urls={"http://www.ask.com", "http://www.google.com", "http://www.yahoo.com"};
		Cache cache=new Cache();
		//TODO this result would need to be transmitted back to web viz page.
		String encodedResult=cache.searchCache(urls);
	}
}
