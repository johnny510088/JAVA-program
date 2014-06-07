import java.net.*;
import java.io.*;
import java.util.*;

public class SocketServer {
	static ServerSocket socket1;
	protected final static int port = 19999;
	static Socket connection;

	static boolean first;
	static StringBuffer process;
	static String TimeStamp;
	
	public static void main(String[] args) {
		System.out.println("Welcome to use this chat room[Server]");
		//read from the user command - Console object
		Console c = System.console(); // invoking console 
		// If the Console object is available, this method returns it. otherwise it return NULL 
		if (c == null) {
			System.err.println("No console.");
			System.exit(1);
		}
		String username = c.readLine("Enter your chat name : ");
		String otherusername;
		try{
			socket1 = new ServerSocket(port);
			System.out.println("SocketServer Initialized");
			int character;
			
			// listen on a particular port 
			connection = socket1.accept();
			//----------------------Reading from client------------------------
			BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());// Instantiate a BufferedInputStream object for reading mincoming socket streams.
			InputStreamReader isr = new InputStreamReader(bis);// Instantiate an InputStreamReader 
			process = new StringBuffer();//StringBuffer can modify string at anytime by append or insert
			//---------------------Writing to the client------------------------
			BufferedOutputStream bos = new BufferedOutputStream(connection.getOutputStream());// Instantiate a BufferedOutputStream object
			OutputStreamWriter osw = new OutputStreamWriter(bos, "UTF-8");// Instantiate an OutputStreamWriter object with the optional character encoding.
			//-----------------write own username to other user-----------------
			osw.write(username + (char) 13);
			osw.flush();
			//-------------------------read otherusername-----------------------
			while((character = isr.read()) != 13){//We make 13 as the end of client message
				process.append((char)character);
			}
			otherusername = process.toString();//convert Stringbuffer to string
			process.delete(0, process.length());//clear the Stringbuffer content
			
			
			while(true){
				//Read the socket's InputStream and append to a StringBuffer
				while((character = isr.read()) != 13){//We make 13 as the end of client message
					process.append((char)character);
				}
				System.out.println(otherusername + " : " + process);
				
				String servermessage = c.readLine(username + " : ");
				// Write across the socket connection and flush the buffer */
				osw.write(servermessage + (char) 13);
				osw.flush();
			}
		}
		catch (IOException e) {}
		try {
			connection.close();
		}
		catch (IOException e) {}
	}
}