package cache;

import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import cache.Memoizer2.ExcCompute;

public class Memoizer3<A,V> implements Computable<A,V>{

	private final ConcurrentMap<A,Future<V> > cache=new ConcurrentHashMap<A,Future<V> >();
	private final Computable<A,V> task;
	public Memoizer3(Computable<A,V> _task)
	{
		this.task=_task;
	}
	@Override
	public V compute(final A arg) throws InterruptedException
	{
		Future<V> f=this.cache.get(arg);
		if (f==null)
		{
		    
			Callable<V> eval=new Callable<V>(){

				@Override
				public V call() throws Exception {
					return task.compute(arg);
				}
				
					};
		    FutureTask<V> ft=new FutureTask<V>(eval);
			f=this.cache.putIfAbsent(arg, ft);
			if (f==null) 
			{
				f=ft;
				ft.run();
			}
		}
		try
		{
		   return f.get();
		}
		catch (ExecutionException e)
		{
			this.cache.remove(arg);
			return null;
		}
	}
	
	private static CountDownLatch latch;
	public static void main(String[] arg) throws InterruptedException
	{
		ExpensiveFunction f=new ExpensiveFunction();
		latch=new CountDownLatch(2);
		Memoizer3<String,BigInteger> m=new Memoizer3<String,BigInteger>(f);
	    Thread t1=new Thread(new ExcCompute(m,"2"));
	 
	    Thread t2=new Thread(new ExcCompute(m,"3"));
	    t1.start();
	    t2.start();
	    
	    ////主线程等待上述两个线程执行完毕再执行,因为上述两个线程执行完毕后会减少latch上的计数。
	    latch.await();
	    BigInteger bi=m.compute("2");
	    System.out.println(bi);
    }
	static class ExcCompute implements Runnable
	{
        private final Memoizer3<String,BigInteger> m;
        private final String arg;
        ExcCompute(Memoizer3<String,BigInteger> _m,String arg){
           this.m=_m;
           this.arg=arg;
        }
		@Override
		public void run() 
		{
			try {
				
				BigInteger bi=m.compute(arg);
				System.out.println(bi.toString());
				latch.countDown();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}  	 
	}	
}
