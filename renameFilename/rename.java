import java.io.*;
public class rename{
	public static void main(String args[]) {
		File oldfile =new File("before.txt");
		File newfile =new File("after.txt");
		if(oldfile.renameTo(newfile)){ //use renameTo function
			System.out.println("Rename succesful");
		}else{
			System.out.println("Rename failed");
		}
	}
}
