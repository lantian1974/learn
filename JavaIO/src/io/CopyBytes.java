package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyBytes
{
   public static void main(String[] args) throws IOException
   {
	    FileInputStream in=null;
	    FileOutputStream out=null;
	    try 
	    {
	    	/*
	    	 * FileInputStream类所使用的相对文件名是对工程所在位置而言，
	    	 * 不是针对类所在的位置，否则就会暴露出“系统找不到指定文件”的异常。 
	    	 */
	    	in= new FileInputStream("IMG_0122.JPG");
	    	out=new FileOutputStream("IMG_0122_copy.JPG");
	    	int c;
	    	while((c=in.read())!=-1)
	    	{
	    		out.write(c);
	    	}
	    }
	    finally
	    {
	    	if (in!=null)
	    	{
	    		in.close();
	    	}
	    	if(out!=null)
	    	{
	    		out.close();
	    	}
	    }
   }
}
