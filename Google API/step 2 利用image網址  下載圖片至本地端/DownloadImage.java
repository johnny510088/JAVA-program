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
		catch (IOException f) {//處理"IO"例外狀況
			System.out.println("IOException: " + f);
		}
		catch (Exception g) {//處理"所有"例外狀況
			System.out.println("Exception: " + g);
		}
    }
}
