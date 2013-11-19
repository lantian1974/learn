package chapter4and5;

public class ThreadWaitVsSleep 
{
   public synchronized void waitThread()
   {
	   System.out.println(Thread.currentThread().getName()+" : I will wait 20√Î!");
	//    Thread.yield();
	    try {
		      this.wait(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   System.out.println(Thread.currentThread().getName()+" : I wait Over!");
   }
   public synchronized  void sleepThread () throws InterruptedException
   {
	   System.out.println(Thread.currentThread().getName()+" : I will Sleep ten senctions!");
	   Thread.sleep(10000);
	   System.out.println(Thread.currentThread().getName()+" I Sleep Over!");   
   }
   public synchronized void lockThread()
   {
	   System.out.println(Thread.currentThread().getName()+" : get the lock!");
	   this.notifyAll();
   }
   
   private static class WaitRun implements Runnable
   {
      private final  ThreadWaitVsSleep _lock;
      public WaitRun( ThreadWaitVsSleep lock)
      {
    	this._lock=lock;
      }
	  @Override
	  public void run()
	  {
		this._lock.waitThread();
	  }
   }
   private static class SleepRun implements Runnable
   {
      private final  ThreadWaitVsSleep _lock;
      public SleepRun( ThreadWaitVsSleep lock)
      {
    	this._lock=lock;
      }
	  @SuppressWarnings("static-access")
	@Override
	  public void run()
	  {
		 try 
		 {
		     this._lock.sleepThread();
		 } 
		 catch (InterruptedException e)
		 {
		     e.printStackTrace();
		 }
	  }
   }
   private static class LockRun implements Runnable
   {
      private final  ThreadWaitVsSleep _lock;
      public LockRun( ThreadWaitVsSleep lock)
      {
    	this._lock=lock;
      }
	  @SuppressWarnings("static-access")
	@Override
	  public void run()
	  {
		
		     this._lock.lockThread();
		
	  }
   }
    public static void main(String[] args) throws InterruptedException
    {
    	ThreadWaitVsSleep monitor=new ThreadWaitVsSleep();
    	Thread waitThread=new Thread(new WaitRun(monitor) );
    	waitThread.setName("Thread-Wait");
    	Thread sleepThread=new Thread(new SleepRun(monitor) );
    	sleepThread.setName("Thread-Sleep");
    	Thread lockThread=new Thread(new LockRun(monitor) );
    	lockThread.setName("Lock-Sleep");
    
  ////////////////////////////////////////////////////////////////  
    	waitThread.start();
        lockThread.start();
  
    	
    	//sleepThread.start();
    	
    	
    }
}
