import java.io.*;
public class rename{
	public static void main(String args[]) 
	{
		File oldfile =new File("oldfile.jpg");
		File newfile =new File("newfile.jpg");
		if(oldfile.renameTo(newfile)){ //use renameTo function
			System.out.println("Rename succesful");
		}else{
			System.out.println("Rename failed");
		}
	}
}
