import java.net.*;//  The java.net package contains the basics needed for network operations. 
import java.io.*;// The java.io package contains the basics needed for IO operations. 
import java.io.InputStreamReader;

// The SocketClient class is a simple example of a TCP/IP Socket Client.
public class SocketClient {//put class SocketClient into package bdn
	static StringBuffer process;
	public static void main(String[] args) {
		// Define server ip address & port
		String ipaddress = args[0];
		int port = Integer.parseInt(args[1]);//convert string to int
		
		System.out.println("Welcome to use this chat room[Client]");
		//read from the user command - Console object
		Console c = System.console(); // invoking console 
		// If the Console object is available, this method returns it. otherwise it return NULL 
		if (c == null) {
			System.err.println("No console.");
			System.exit(1);
		}
		String username = c.readLine("Enter your chat name : ");
		String otherusername;
		try {
			int character;
			//------------------------connect to server-------------------------
			System.out.println("SocketClient initialized");
			Socket connection = new Socket(ipaddress, port);	
			//---------------------Writing to the server------------------------
			BufferedOutputStream bos = new BufferedOutputStream(connection.getOutputStream());// Instantiate a BufferedOutputStream object
			OutputStreamWriter osw = new OutputStreamWriter(bos, "UTF-8");// Instantiate an OutputStreamWriter object with the optional character encoding.
			//----------------------Reading from server-----------------------
			BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());// Instantiate a BufferedInputStream object for reading mincoming socket streams.
			InputStreamReader isr = new InputStreamReader(bis, "UTF-8");// Instantiate an InputStreamReader with the optional character encoding.
			process = new StringBuffer();//StringBuffer can modify string at anytime by append or insert
			//-----------------write own username to other user-----------------
			osw.write(username + (char) 13);
			osw.flush();
			//-----------------------read otherusername-------------------------
			while((character = isr.read()) != 13){//We make 13 as the end of client message
				process.append((char)character);
			}
			otherusername = process.toString();//convert Stringbuffer to string
			process.delete(0, process.length());//clear the Stringbuffer content
			
			while(true){
				String clientmessage = c.readLine(username + " : ");
				// Write across the socket connection and flush the buffer */
				osw.write(clientmessage + (char) 13);//use char 13 let server know when read 13 then you can stop read
				osw.flush(); //flushing the buffer
				
				//Read the socket's InputStream and append to a StringBuffer
				while((character = isr.read()) != 13){
					process.append((char)character);
				}
				System.out.println(otherusername + " : " + process);
			}	
			connection.close();// Close the socket connection.
		}
		catch (IOException f) {//處理"IO"例外狀況
		  System.out.println("IOException: " + f);
		}
		catch (Exception g) {//處理"所有"例外狀況
		  System.out.println("Exception: " + g);
		}
	}//main end 
}
