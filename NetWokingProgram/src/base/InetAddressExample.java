package base;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressExample
{
	public static void main (String [] args) throws UnknownHostException
    {
		String host = "localhost";

		if (args.length == 1)
		host = args [0];
        InetAddress ia =InetAddress.getByName(host);

		System.out.println ("Canonical Host Name = " +ia.getCanonicalHostName ());
		System.out.println ("Host Address = " +ia.getHostAddress ());
	    System.out.println ("Host Name = " +ia.getHostName ());
		System.out.println ("Is Loopback Address = " +
		ia.isLoopbackAddress ());
	}	
}
