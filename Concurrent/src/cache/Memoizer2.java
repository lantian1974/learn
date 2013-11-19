package cache;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
/**
 * 
 * @author lant
 *
 * @param <A>
 * @param <V>
 */
public class Memoizer2<A, V> implements Computable<A, V>
{
	 
	 private final Map<A,V> cache=new ConcurrentHashMap<A,V>();
	 private final Computable<A,V> c;
	 public Memoizer2(Computable<A,V> c)
	 {
	    	this.c=c;
	 }
		@Override
		public   V compute(A arg) throws InterruptedException 
		{
			V result=cache.get(arg);
			if (result==null)
			{
				result=c.compute(arg);
				cache.put(arg,result);
			}
			return result;
		}
		static CountDownLatch latch;
		public static void main(String[] args) throws InterruptedException
		{
			ExpensiveFunction f=new ExpensiveFunction();
			latch=new CountDownLatch(2);
			Memoizer2<String,BigInteger> m=new Memoizer2<String,BigInteger>(f);
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
            private final Memoizer2<String,BigInteger> m;
            private final String arg;
            ExcCompute(Memoizer2<String,BigInteger> _m,String arg){
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
