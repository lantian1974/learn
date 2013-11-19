package chapter4and5;

import java.util.Scanner;
import java.lang.Thread;
import java.lang.Runnable;
public class ThreadIntterupt
{
   public static void main(String[] args)
   {
	   Thread t=new Thread(new Runnable()
	   {
		@Override
		public void run()
		{
			 String[] importantInfo= {
			            "Mares eat oats",
			            "Does eat oats",
			            "Little lambs eat ivy",
			            "A kid will eat ivy too"
			        };
			   for(int i=0;i<importantInfo.length;i++)
			   {
			      try
			      {
					Thread.sleep(3000);
				  } catch (InterruptedException e) 
				  {
					  return;
				  }
			      System.out.println(importantInfo[i]);
			      if (Thread.interrupted())
			      {
			    	   return;
			      }
			   } 		
		}
		   
	   });
	   t.start();
	   System.out.print("输入 y,回车后则可终端当前程序");
	   Scanner scanner = new Scanner(System.in);
       String str = scanner.nextLine();
       if (str.equals("y"))
       {
    	   t.interrupt();
       }
   }
  
}
