import java.io.*;
import java.util.*;
public class preprocess{
	public static void main(String args[]){
		String strLineinput;		
		int i=0;
		int[] hour = new int[24];//24 hours ,per variable per hour
		String[] big_tokens = new String[10];//a row will split into 10 token
		String[] mid_tokens = new String[10];//a row will split into 10 token
		String[] sma_tokens = new String[10];//a row will split into 10 token
		try{
			//------------------------------Open file-----------------------------
			FileInputStream fin = new FileInputStream("access_log_7.1~7.28.txt");//read from the txt file
			DataInputStream in = new DataInputStream(fin);// Get the obiect of DataInputStream
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			//------------------------------Open file-----------------------------
			PrintWriter out = new PrintWriter(new FileWriter("outputfile.txt")); 
			//---------------------------------Test-------------------------------		
			/*strLineinput = br.readLine();
			big_tokens = strLineinput.split("\\s",-1);
			mid_tokens = big_tokens[3].split("/",-1);
			sma_tokens = mid_tokens[2].split(":",-1);			
			for(i=0;i<big_tokens.length;i++){
				System.out.print("big_tokens[" + i + "] = " + big_tokens[i] +"\n");
			}
			for(i=0;i<mid_tokens.length;i++){
				System.out.print("mid_tokens[" + i + "] = " + mid_tokens[i] +"\n");
			}
			for(i=0;i<sma_tokens.length;i++){
				System.out.print("sma_tokens[" + i + "] = " + sma_tokens[i] +"\n");
			}*/
			//--------------------------------------------------------------------	
			
			while ((strLineinput = br.readLine()) != null){//Read File Line By Line
				big_tokens = strLineinput.split("\\s",-1);
				mid_tokens = big_tokens[3].split("/",-1);
				sma_tokens = mid_tokens[2].split(":",-1);
				mid_tokens[0] = mid_tokens[0].replace("[","");
				// || mid_tokens[0].equals("15") || mid_tokens[0].equals("16") || mid_tokens[0].equals("17") || mid_tokens[0].equals("18") || mid_tokens[0].equals("19") || mid_tokens[0].equals("20") || mid_tokens[0].equals("21")  
				if( mid_tokens[0].equals("01") || mid_tokens[0].equals("02") || mid_tokens[0].equals("03") || mid_tokens[0].equals("04") || mid_tokens[0].equals("05") || mid_tokens[0].equals("06") || mid_tokens[0].equals("07") || mid_tokens[0].equals("08") || mid_tokens[0].equals("09") || mid_tokens[0].equals("10") || mid_tokens[0].equals("11") || mid_tokens[0].equals("12") || mid_tokens[0].equals("13") || mid_tokens[0].equals("14") || mid_tokens[0].equals("15") || mid_tokens[0].equals("16") || mid_tokens[0].equals("17") || mid_tokens[0].equals("18") || mid_tokens[0].equals("19") || mid_tokens[0].equals("20") || mid_tokens[0].equals("21")  ){
					for(i=0;i<big_tokens.length-1;i++){
						out.print(big_tokens[i]+" ");
					}
					out.print(big_tokens[big_tokens.length-1]+"\n");
				}
			}
			out.close();
		}
		catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
}