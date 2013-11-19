package chapter4and5;
import java.util.concurrent.CountDownLatch;
public class LearningCountDownLatch
{
   public long timeTasks(int nThreads,final Runnable task) throws InterruptedException
   {
      final CountDownLatch startGate=new CountDownLatch(1);
      final CountDownLatch endGate=new CountDownLatch(nThreads);
      for(int i=0;i<nThreads;i++)
      {
    	  Thread t=new Thread()
    	  { 
    		   public void run()
    		   {
    			   try
    			   {
    				   startGate.await();
    				   try
    				   {
    					   task.run();
    				   }
    				   finally
        			   {
        				   endGate.countDown();
        			   }
    			   }
    			   catch(InterruptedException ignored)
    			   {}
    		   }
    	  };
    	  t.start();	  
      }
      long start=System.nanoTime();
      startGate.countDown();
      endGate.await();
      long end = System.nanoTime();
      return end-start;
   }
   public static void main(String[] args) throws InterruptedException
   {
	   LearningCountDownLatch lcdl=new LearningCountDownLatch();
	   DoSum doSum=new DoSum(1,10000000);
	   System.out.println(lcdl.timeTasks(5, doSum));
   }
   
  private static  class DoSum implements Runnable
   {
   	  final int from;
   	  final int to;
   	  public DoSum(int from,int to)
   	  {
   		  if (from>=to)
   		  {
   			  throw new RuntimeException("argument to must bigger than from!");
   		  }
   		  this.from=from;
   		  this.to=to;
   	  }
	  @Override
   	   public void run()
   	   {
   	   	  long result=0;
		  for(int i=this.from;i<=to;i++)
   	   	  {
   	   		  result=result+i;
   	   	  }
   	   	  System.out.println(result);
   	   }
   }

}

