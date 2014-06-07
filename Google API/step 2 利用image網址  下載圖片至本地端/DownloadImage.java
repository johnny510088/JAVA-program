import java.io.*;
import java.net.*;
public class DownloadImage{
	public static void main(String args[]){
		try {
			URL url = new URL("http://www.edinburgh-scotland.net/images/ForthBridge03L.jpg");
			InputStream in = new BufferedInputStream(url.openStream());
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int n = 0;
			while (-1!=(n=in.read(buf))){
				out.write(buf, 0, n);
			}
			out.close();
			in.close();
			byte[] response = out.toByteArray();
			//Save the image to disk
			FileOutputStream fos = new FileOutputStream("image.jpg");
			fos.write(response);
			fos.close();
		}
		catch (IOException f) {//�B�z"IO"�ҥ~���p
			System.out.println("IOException: " + f);
		}
		catch (Exception g) {//�B�z"�Ҧ�"�ҥ~���p
			System.out.println("Exception: " + g);
		}
    }
}
