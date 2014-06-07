import java.io.*;
import java.util.*;
import java.lang.*;
public class mapreduce{
	public static void main(String args[]){				
		String strLineinput;		
		int i=0;
		int[] hour = new int[24];//24 hours ,per variable per hour		
		String[] big_tokens = new String[10];//a row will split into 10 token
		String[] mid_tokens = new String[10];//a row will split into 10 token
		String[] sma_tokens = new String[10];//a row will split into 10 token		
		//initialize
		for(i=0;i<24;i++){
			hour[i]=0;
		}
		try{
			long startTime = System.currentTimeMillis();
			//------------------------------Open file-----------------------------
			FileInputStream fin = new FileInputStream("access_log_7.1~7.7.txt");//read from the txt file
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
				if(sma_tokens[1].equals("00"))
					hour[0] = hour[0] + 1;
				else if(sma_tokens[1].equals("01"))
					hour[1] = hour[1] + 1;
				else if(sma_tokens[1].equals("02"))
					hour[2] = hour[2] + 1;
				else if(sma_tokens[1].equals("03"))
					hour[3] = hour[3] + 1;
				else if(sma_tokens[1].equals("04"))
					hour[4] = hour[4] + 1;
				else if(sma_tokens[1].equals("05"))
					hour[5] = hour[5] + 1;
				else if(sma_tokens[1].equals("06"))
					hour[6] = hour[6] + 1;
				else if(sma_tokens[1].equals("07"))
					hour[7] = hour[7] + 1;
				else if(sma_tokens[1].equals("08"))
					hour[8] = hour[8] + 1;
				else if(sma_tokens[1].equals("09"))
					hour[9] = hour[9] + 1;
				else if(sma_tokens[1].equals("10"))
					hour[10] = hour[10] + 1;
				else if(sma_tokens[1].equals("11"))
					hour[11] = hour[11] + 1;
				else if(sma_tokens[1].equals("12"))
					hour[12] = hour[12] + 1;
				else if(sma_tokens[1].equals("13"))
					hour[13] = hour[13] + 1;
				else if(sma_tokens[1].equals("14"))
					hour[14] = hour[14] + 1;
				else if(sma_tokens[1].equals("15"))
					hour[15] = hour[15] + 1;
				else if(sma_tokens[1].equals("16"))
					hour[16] = hour[16] + 1;
				else if(sma_tokens[1].equals("17"))
					hour[17] = hour[17] + 1;
				else if(sma_tokens[1].equals("18"))
					hour[18] = hour[18] + 1;
				else if(sma_tokens[1].equals("19"))
					hour[19] = hour[19] + 1;
				else if(sma_tokens[1].equals("20"))
					hour[20] = hour[20] + 1;
				else if(sma_tokens[1].equals("21"))
					hour[21] = hour[21] + 1;
				else if(sma_tokens[1].equals("22"))
					hour[22] = hour[22] + 1;
				else if(sma_tokens[1].equals("23"))
					hour[23] = hour[23] + 1;
			}
			
			out.println("00:00~01:00 \tuser number \t" + hour[0]);
			out.println("01:00~02:00 \tuser number \t" + hour[1]);
			out.println("02:00~03:00 \tuser number \t" + hour[2]);
			out.println("03:00~04:00 \tuser number \t" + hour[3]);
			out.println("04:00~05:00 \tuser number \t" + hour[4]);
			out.println("05:00~06:00 \tuser number \t" + hour[5]);
			out.println("06:00~07:00 \tuser number \t" + hour[6]);
			out.println("07:00~08:00 \tuser number \t" + hour[7]);
			out.println("08:00~09:00 \tuser number \t" + hour[8]);
			out.println("09:00~10:00 \tuser number \t" + hour[9]);
			out.println("10:00~11:00 \tuser number \t" + hour[10]);
			out.println("11:00~12:00 \tuser number \t" + hour[11]);
			out.println("12:00~13:00 \tuser number \t" + hour[12]);
			out.println("13:00~14:00 \tuser number \t" + hour[13]);
			out.println("14:00~15:00 \tuser number \t" + hour[14]);
			out.println("15:00~16:00 \tuser number \t" + hour[15]);
			out.println("16:00~17:00 \tuser number \t" + hour[16]);
			out.println("17:00~18:00 \tuser number \t" + hour[17]);
			out.println("18:00~19:00 \tuser number \t" + hour[18]);			
			out.println("19:00~20:00 \tuser number \t" + hour[19]);
			out.println("20:00~21:00 \tuser number \t" + hour[20]);
			out.println("21:00~22:00 \tuser number \t" + hour[21]);
			out.println("22:00~23:00 \tuser number \t" + hour[22]);
			out.println("23:00~24:00 \tuser number \t" + hour[23]);
			
			long endTime = System.currentTimeMillis();
			long timeinternal = endTime-startTime;
			out.println();
			if(timeinternal<1000)
				out.println("Total elapsed time in execution is : "+ timeinternal +" (ms)");
			else
				out.println("Total elapsed time in execution is : "+ timeinternal/1000 +"s " + timeinternal % 1000 +"ms");
			out.close();				
		}
		catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
}