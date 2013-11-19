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
	    	 * FileInputStream����ʹ�õ�����ļ����ǶԹ�������λ�ö��ԣ�
	    	 * ������������ڵ�λ�ã�����ͻᱩ¶����ϵͳ�Ҳ���ָ���ļ������쳣�� 
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
