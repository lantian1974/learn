package base;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class NetworkInterfaceExample
{
   public static void main(String[] args) throws SocketException
   {
	   Enumeration<NetworkInterface> nis=NetworkInterface.getNetworkInterfaces();
	   while (nis.hasMoreElements())
	   {
		   NetworkInterface ni=nis.nextElement(); 
		   System.out.println(ni.getDisplayName()+"------------------/");
		   
	   }
   }
}
