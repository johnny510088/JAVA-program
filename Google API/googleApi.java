import java.io.*;
import java.net.*;
public class googleApi{
	public static void main(String args[]) throws IOException  {
        String key="AIzaSyC1FcOgDrkEVWwNVRYJbk36z4IygD3B_rg";
		String searchid="001458187984977449536:z-9t4djfzne";
		String qry=args[0];// search key word
		String[] imgurl = new String[5];//five image url
		int i=0;
		
		URL url = new URL(                                                                 
			"https://www.googleapis.com/customsearch/v1?key=" + key + "&cx=" + searchid + "&searchType=image&imgSize=huge&fileType=jpg&num=5&q=" + qry + "&alt=json");
        
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

        String output;
        System.out.println(url);
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            if(output.contains("\"link\": \"")){
                String link=output.substring(output.indexOf("\"link\": \"")+("\"link\": \"").length(), output.indexOf("\","));
                System.out.println(link);
				imgurl[i]=link;
				i++;
            }     
        }
		SaveImage(imgurl,qry);
        conn.disconnect();   
    }
	//function :save image into Directory
	public static void SaveImage(String[] imgurl,String imgname){
		int i=0;
		//new a directory
		java.io.File file = new java.io.File("e24999021_詹鎧瑋");
		file.mkdir();
		String userDir = System.getProperty("user.dir");//find the current directory
		try {
			for(i=0;i<5;i++){
				URL url = new URL(imgurl[i]);
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
				FileOutputStream fos = new FileOutputStream(userDir+"\\e24999021_詹鎧瑋\\"+imgname+"_0"+(i+1)+".jpg");//userDir = current directory
				fos.write(response);
				fos.close();
			}
		}
		catch (IOException f) {//處理"IO"例外狀況
			System.out.println("IOException: " + f);
		}
		catch (Exception g) {//處理"所有"例外狀況
			System.out.println("Exception: " + g);
		}
	}
}


	