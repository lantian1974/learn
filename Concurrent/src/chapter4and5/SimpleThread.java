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
			//�����Forѭ�����ڲ����ж��쳣��ѡ�����ִ�У����̻߳��ӡ��ȫ����Ϣ�ġ�
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
			  // ���߳���1000����ʱ��ȴ��̵߳�tִ����ϣ�Wait maximum of 1 second  for MessageLoop thread  to finish.
			  t.join(1000);
			  //���̵߳ȴ��߳�tִ��1�������߳�t��ûִ���꣨t.isAlive()==true������ִ�е����ڵ�ʱ�䳬�����趨�����͵�ʱ�� �����߳�t�жϡ�
			  if (((System.currentTimeMillis() - startTime) > patience)
	                  && t.isAlive()) 
	          {
	                threadMessage("Tired of waiting!");
	                t.interrupt();
	                //���߳������ڵȴ��߳�t��ִ����ϣ�ʵ�����߳�t�Ѿ��жϣ�Ӧ�ò���ȴ�̫���� Shouldn't be long now   wait indefinitely
	                t.join();
	            }
	     }
	     threadMessage("Finally!");
	    }

}
