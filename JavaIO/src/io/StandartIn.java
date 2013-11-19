package io;

import java.io.IOException;
import java.io.InputStreamReader;

public class StandartIn
{
	public static void main(String[] args) throws IOException
	{
	   InputStreamReader cin = new InputStreamReader(System.in);
	   char[] ca;
	   ca=new char[5];
	   try
	   {
	      while( cin.read(ca)!=-1)
	      {
		      String s=String.valueOf(ca);
	    	  System.out.println(s); 
	      }
	   }
	   finally
	   {
           cin.close();
	   }
	}
}
