import java.net.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;//Timeunit

public class SocketServer  implements Runnable{
	static ServerSocket socket1;
	protected final static int port = 19999;
	public static int i;//for index
	public static int count_nextline;
	//Static variables are initialized only once
	static StringBuffer readrecord;////store read record
	static StringBuffer chatrecord;//store chat record
	
	public static Socket connection;
	public static String username;
	public static String otherusername;
	public int executenum;
		
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
		int character;
		try{
			//------------------receive connect from client--------------------
			socket1 = new ServerSocket(port);
			// listen on a particular port 
			connection = socket1.accept();
			//----------------------Reading from client------------------------
			BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());// Instantiate a BufferedInputStream object for reading mincoming socket streams.
			InputStreamReader isr = new InputStreamReader(bis);// Instantiate an InputStreamReader 
			readrecord = new StringBuffer();//StringBuffer can modify string at anytime by append or insert
			//---------------------Writing to the client------------------------
			BufferedOutputStream bos = new BufferedOutputStream(connection.getOutputStream());// Instantiate a BufferedOutputStream object
			OutputStreamWriter osw = new OutputStreamWriter(bos, "UTF-8");// Instantiate an OutputStreamWriter object with the optional character encoding.
			//-----------------write own username to other user-----------------
			osw.write(username + (char) 13);
			osw.flush();
			//-----------------read otherusername from other--------------------
			while((character = isr.read()) != 13){//We make 13 as the end of client message
				readrecord.append((char)character);
			}
			otherusername = readrecord.toString();//convert Stringbuffer to string
			readrecord.delete(0, readrecord.length());//clear the Stringbuffer content

			System.out.println("----------------------------Welcome to Chat Room----------------------------");
			System.out.println("You can chat to other person ");
			System.out.println("Input 'modify'  can modify your chat name ");
			for(i=0 ; i < 20 ; i++)	System.out.println();
			System.out.println("Press any key to continue...");
			c.readLine();//wait for any input			
			
			//multi thread , set up two thread
			Runnable runnable1 = new SocketServer(connection,username,otherusername,0);
			Runnable runnable2 = new SocketServer(connection,username,otherusername,1);
			Thread thread1 = new Thread(runnable1);
			Thread thread2 = new Thread(runnable2);
			thread1.start();//write to other
			TimeUnit.MILLISECONDS.sleep(1000);//delay one second to start next thread
			thread2.start();//read from other
		}
		catch (IOException f) {//deal with "IO" Exception
		  System.out.println("IOException: " + f);
		}
		catch (Exception g) {//deal with "all" Exception
		  System.out.println("Exception: " + g);
		}
	}//main end	
	SocketServer(Socket connect,String str1,String str2,int i) {//constructor
		this.connection = connect;	
		this.username = str1;
		this.otherusername = str2;
		this.executenum = i;
	}
	public void run() {//when thread.start() then will execute run 
		int character;
		try {
			//read from the user command - Console object
			Console c = System.console(); // invoking console 
			// If the Console object is available, this method returns it. otherwise it return NULL 
			if (c == null) {
				System.err.println("No console.");
				System.exit(1);
			}
			//---------------------Writing to the server------------------------
			BufferedOutputStream bos = new BufferedOutputStream(connection.getOutputStream());// Instantiate a BufferedOutputStream object
			OutputStreamWriter osw = new OutputStreamWriter(bos, "UTF-8");// Instantiate an OutputStreamWriter object with the optional character encoding.
			//----------------------Reading from server-----------------------
			BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());// Instantiate a BufferedInputStream object for reading mincoming socket streams.
			InputStreamReader isr = new InputStreamReader(bis, "UTF-8");// Instantiate an InputStreamReader with the optional character encoding.
			readrecord = new StringBuffer();//StringBuffer can modify string at anytime by append or insert
			chatrecord = new StringBuffer();
			chatrecord.delete(0, chatrecord.length());//clear the Stringbuffer content			
			while (true) {
				System.out.println("\n----------------------------Welcome to Chat Room----------------------------");
				System.out.print(chatrecord.toString());//toString(): convert stringbuffer to string 
				count_nextline = count(chatrecord.toString());
				if( count_nextline < 23){
					for(i=0 ; i < 23 - count_nextline ; i++)	System.out.println();
				}
				else
					System.out.println();
				System.out.print(this.username + " : ");//Show my name at the bottom always			
				if(this.executenum==1){					
					String clientmessage = c.readLine();
					if(clientmessage.equals("modify")){
						System.out.println("Please input your new name : ");
						this.username = c.readLine();
						chatrecord = chatrecord.append("---- You hava modify your name successful ---- \n");
						osw.write("newname" + this.username + (char) 13);//pass new name to client, when they see "new" then they will know username have change
					}
					else{
						// Write across the socket connection and flush the buffer */
						osw.write(clientmessage + (char) 13);//use char 13 let server know when read 13 then you can stop read
						chatrecord = chatrecord.append(this.username + " : " + clientmessage + "\n");//store into chat record 
					}
					osw.flush(); //flushing the buffer
				}
				else{
					String saveyn;
					//Read the socket's InputStream and append to a StringBuffer
					while((character = isr.read()) != 13){
						readrecord.append((char)character);
					}
					if(readrecord.toString().length() > 7 && readrecord.toString().substring(0,7).equals("newname")){//extract the front 7 charfrom string
						chatrecord = chatrecord.append("---- " + this.otherusername + " have change their name into ");//take record
						this.otherusername = readrecord.toString().substring(7);//take the last few char after "newname"
						chatrecord = chatrecord.append(this.otherusername + " ----" + "\n");//take record
					}
					else{
						System.out.println(this.otherusername + " : " + readrecord);
						chatrecord = chatrecord.append(this.otherusername + " : " + readrecord + "\n");//store into chat record 
					}
					readrecord.delete(0, readrecord.length());//clear the Stringbuffer content						
				}
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		finally {
			try {
				connection.close();
			}
			catch (IOException e){}
		}
	}//run end	
	public static int count(String inputstr){//count the '\n' number in the sting
		char[] c = inputstr.toCharArray();
		int frequence= 0;
		for(i=0;i<c.length;i++) {
			if(c[i]=='\n') // looking for '\n' only
				frequence++;
		}
		return frequence;
	}
}