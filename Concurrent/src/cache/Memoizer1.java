package cache;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Memoizer1<A,V> implements Computable<A,V>
{
    private final Map<A,V> cache=new HashMap<A,V>();
    private final Computable<A,V> c;
    public Memoizer1(Computable<A,V> c)
    {
    	this.c=c;
    }
	@Override
	public synchronized  V compute(A arg) throws InterruptedException 
	{
		V result=cache.get(arg);
		if (result==null)
		{
			result=c.compute(arg);
			cache.put(arg,result);
		}
		return result;
	}
	public static void main(String[] args) throws InterruptedException
	{
		ExpensiveFunction f=new ExpensiveFunction();
		Memoizer1<String,BigInteger> m=new Memoizer1<String,BigInteger>(f);
		BigInteger bi=m.compute("2");
		System.out.println(bi.toString());
		BigInteger bi2=m.compute("2");
		System.out.println(bi2.toString());
	}
  
}
