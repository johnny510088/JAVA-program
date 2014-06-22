import java.net.*;

public class findIPHostname{
	public static void  main(String arg[]) {
		try{
			InetAddress addr = InetAddress.getLocalHost();
			String hostname = addr.getHostName();
			System.out.println(addr.getHostAddress());
			System.out.println(hostname);
		}
		catch(UnknownHostException e){
			//throw Exception
		}
	}
}
