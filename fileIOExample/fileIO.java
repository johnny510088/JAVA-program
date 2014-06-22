import java.io.*;
import java.util.*;
public class fileIO{
	public static void main(String args[]){
		try{
			//----------------------------declare for writing--------------------------
			//We don't need "DataOutputStream"
			File fout = new File("testFile.txt");
			FileOutputStream fos = new FileOutputStream(fout);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			//----------------------------write into file.txt---------------------------
			bw.write("Line one");
			bw.newLine();
			bw.write("Line two");
			bw.flush();
			//----------------------------declare for reading--------------------------
			FileInputStream fis = new FileInputStream("testFile.txt");//read from the txt file
			DataInputStream dis = new DataInputStream(fis);// Get the object of DataInputStream
			BufferedReader br = new BufferedReader(new InputStreamReader(dis));			
			//-----------------------------read from file.txt--------------------------
			String strLineinput="";
			System.out.println("Have create a testFile.txt and write into file read from file");
			while((strLineinput = br.readLine()) != null){
				System.out.println(strLineinput);
			}
			br.close();//Close the stream
		}catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
}
