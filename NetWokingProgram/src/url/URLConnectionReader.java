package url;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public final class URLConnectionReader
{
   public static void main(String[] args) throws IOException
   {
	   URL url = new URL("http://www.oracle.com/");
	   URLConnection urlCon=url.openConnection();
	   InputStream is= urlCon.getInputStream();
	   BufferedReader br=new BufferedReader(new InputStreamReader(is));
	   String line;
	   try
	   {
	      while( (line=br.readLine())!=null)
	      {
		     System.out.println(line);  
	      }
	   }
	   finally
	   {
		   br.close();
	   }
   }
}
