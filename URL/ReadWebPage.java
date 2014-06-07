import java.net.*;
import java.io.*;
 
public class ReadWebPage {
    
    public static void main(String[] arg) throws Exception {
        String web = "http://tw.news.yahoo.com/article/url/d/a/100712/8/293ny.html" ;//UTF-8
        read1(web);        
    }
    
    
    public static void read1( String strURL ) {
          int chunksize = 4096;
        byte[] chunk = new byte[chunksize];
        int count;
        try  {    
            URL pageUrl = new URL(strURL );
       
            // read website
            BufferedInputStream bis = new BufferedInputStream(pageUrl.openStream());
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("URL1.txt", false));
            System.out.println("read1() running " );
            while ((count = bis.read(chunk, 0, chunksize)) != -1) {
                bos.write(chunk, 0, count); //write into file
            }
            bos.close();
            bis.close();     
          
          System.out.println("Done");   
         }catch (IOException e) {
             e.printStackTrace();
         }
	}
}