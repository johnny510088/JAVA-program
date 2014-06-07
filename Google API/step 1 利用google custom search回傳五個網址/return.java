import java.io.*;
import java.net.*;
public class multithread{
	public static void main(String args[]) throws IOException  {
        String key="AIzaSyC1FcOgDrkEVWwNVRYJbk36z4IygD3B_rg";
		String searchid="001458187984977449536:z-9t4djfzne";
		String qry=args[0];// search key word

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
            }
        }
        conn.disconnect();   
    }
}