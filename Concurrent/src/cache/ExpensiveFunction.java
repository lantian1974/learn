package cache;

import java.math.BigInteger;

public class ExpensiveFunction implements Computable<String ,BigInteger>
{

	@Override
	public BigInteger compute(String arg) throws InterruptedException 
	{
		BigInteger result=new BigInteger(arg);
		for(long i=1;i<9999999;i++)
		{
			result=result.multiply(new BigInteger("1"));
			Long l=new Long(i);
			result=result.add(new BigInteger(l.toString()));
		}
		return result;
	}
   
}
