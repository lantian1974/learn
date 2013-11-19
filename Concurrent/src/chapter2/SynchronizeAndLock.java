package chapter2;

public class SynchronizeAndLock 
{
   synchronized private void  methodA()
   {
	   System.out.println("Thread name:"+Thread.currentThread().getName()+" come in  Method A" );
	    System.out.println(" begin invoke method A");
	    int counter=0;
	    String s="";
		for(int i=0;i<100000;i++)
		{
			counter=counter+i;
			s=s+"a";
		
		}
		System.out.println(" over invoke method A");
	    System.out.println("Thread name:"+Thread.currentThread().getName()+" left out Method A" );
   }
   synchronized private void methodB()
   {
	   System.out.println("Thread name:"+Thread.currentThread().getName()+" come in Method  B " );
	   System.out.println(" begin invoke method B");
	   System.out.println(" over invoke method B");
	   System.out.println("Thread name:"+Thread.currentThread().getName()+" left out Method B" );
   }
	private static class ThreadA extends Thread
    {
    	private SynchronizeAndLock lock;
     	ThreadA(SynchronizeAndLock _lock)
    	{
    		this.lock=_lock;
    		this.setName("THREAD_A");
    	}
		public  void run()
    	{
    		
    		this.lock.methodA();
    		 int counter=0;
    		    String s="";
    			for(int i=0;i<10000;i++)
    			{
    				counter=counter+i;
    				s=s+"a";
    			
    			}
    		this.lock.methodB();
    	
    	}
    }
    private static class ThreadB extends Thread
    {
    	
    	private SynchronizeAndLock lock;
    	ThreadB(SynchronizeAndLock _lock)
    	{
    		this.lock=_lock;
    		this.setName("THREAD_B");
    	}
    	public  void run()
    	{	
    		this.lock.methodB();
    		this.lock.methodA();
    		
    	}
    }
    public static void main(String[] args)
    {
    	SynchronizeAndLock lock=new SynchronizeAndLock();
    	ThreadA a=new ThreadA(lock);
    	ThreadB b=new ThreadB(lock);
    	a.start();
    	b.start();
    }
}
