package url;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class UrlReader
{
	public static void main(String[] args) throws Exception {

        URL oracle = new URL("http://www.oracle.com/");
        InputStreamReader inputReader=  new InputStreamReader(oracle.openStream());
        BufferedReader in = new BufferedReader(inputReader );
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
    }
}
