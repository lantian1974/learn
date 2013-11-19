package chapter4and5;

public class SimpleThread 
{
	// Display a message, preceded by
    // the name of the current thread
	  static void threadMessage(String message) 
	  {
	        String threadName =Thread.currentThread().getName();
	        System.out.format("%s: %s%n",threadName,message);
	  }
	  private static class MessageLoop implements Runnable
	  {

		@Override
		public void run()
		{
			String importantInfo[] = {
	                "Mares eat oats",
	                "Does eat oats",
	                "Little lambs eat ivy",
	                "A kid will eat ivy too"
	        };
			//如果在For循环体内捕获中断异常并选择继续执行，则线程会打印出全部信息的。
			try
			{
			   for(int i=0;i<importantInfo.length;i++)
			    {
				 
				      Thread.sleep(4000);
				  	  threadMessage(importantInfo[i]);
				  	  Thread.dumpStack();
			    }
			}
			catch(InterruptedException e)
		    {
					threadMessage("I wasn't done!");
			}
	
		}
     }
	 public static void main(String[] args) throws InterruptedException 
	 {
		// Delay, in milliseconds before  we interrupt MessageLoop thread (default one hour).
		long patience = 1000 * 60 * 60;
		// If command line argument  present, gives patience  in seconds.
		
		if (args.length>0 )
		{
		   try
		   {
		      patience=Long.parseLong(args[0])*1000;
		   }
		   catch(NumberFormatException e)
		   {
			   System.err.println("Arguments must be an integer!");
			   System.exit(1);
		   }
	    }
		threadMessage("Starting MessageLoop thread");
		long startTime=System.currentTimeMillis();
		Thread t=new Thread(new MessageLoop());
		t.start();
		threadMessage("Waiting for MessageLoop thread to finish");
		while (t.isAlive())
		{
			  threadMessage("Still waiting...");
			  // 主线程用1000毫秒时间等待线程的t执行完毕，Wait maximum of 1 second  for MessageLoop thread  to finish.
			  t.join(1000);
			  //主线程等待线程t执行1秒后，如果线程t还没执行完（t.isAlive()==true）并且执行到现在的时间超过了设定的忍耐的时间 ，则将线程t中断。
			  if (((System.currentTimeMillis() - startTime) > patience)
	                  && t.isAlive()) 
	          {
	                threadMessage("Tired of waiting!");
	                t.interrupt();
	                //主线程无限期等待线程t的执行完毕（实际上线程t已经中断，应该不会等待太长） Shouldn't be long now   wait indefinitely
	                t.join();
	            }
	     }
	     threadMessage("Finally!");
	    }

}
