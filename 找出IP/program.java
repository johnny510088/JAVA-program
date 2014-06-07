import java.io.*;
import java.util.*;
public class program{
	public static void main(String args[]){
		String input="";
		String[] tokens = new String[5];
		
		try{
			// Open the file for read
			FileInputStream finstream = new FileInputStream("input.txt");//read from the txt file
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(finstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			// Open the file for write    we don't need DataOutputStream here
			FileWriter foutstream = new FileWriter("output.txt");		
			BufferedWriter bw = new BufferedWriter(foutstream);
			
			String strLineinput;		
			
			while ((strLineinput = br.readLine()) != null){//Read File Line By Line
				tokens = strLineinput.split("\\.");       // dot is the separator insert // is because . is the reserved character 
				strLineinput = tokens[0]+"."+tokens[1]+"."+tokens[2]+"."+tokens[3];
				
				bw.write(strLineinput);//write file to out.txt
				bw.newLine();
				// Print the content on the console
				System.out.println (strLineinput);
			}
			//Close the input stream
			in.close();
			bw.flush();
			bw.close();
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		
	}
}

