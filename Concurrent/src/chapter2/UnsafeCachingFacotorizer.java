package chapter2;

import javax.servlet.http.HttpServlet;
import java.math.BigInteger;
import java.util.Random;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;
public class UnsafeCachingFacotorizer  extends HttpServlet
{
    private final AtomicReference<BigInteger> lastNumber=new AtomicReference<BigInteger>();
    private final AtomicReference<BigInteger[]> lastFactors=new AtomicReference<BigInteger[]>();
    private static byte[] longToByte(long number) { 

        long temp = number; 

        byte[] b = new byte[8]; 

        for (int i = 0; i < b.length; i++) { 

            b[i] = new Long(temp & 0xff).byteValue();// �����λ���������λ 

            temp = temp >> 8; // ������8λ 
        } 

        return b; 

    } 
    private BigInteger extractFromRequest(ServletRequest req)
    {
    	Random rg=new Random();
    	long r=rg.nextLong();
    	BigInteger bg=new BigInteger(longToByte(r)) ;
    	return bg;
    }
    private void encodeIntoResponse(ServletResponse resp,BigInteger[] fators)
    {
    	//code for generating htmls output;
    }
    public  BigInteger[] factor(BigInteger i)
    {
    	return null;
    }
    public void service(ServletRequest req,ServletResponse resp)
    {
    	BigInteger i=extractFromRequest(req);
    	if (i.equals(this.lastNumber.get()))
    	{
    		encodeIntoResponse(resp,this.lastFactors.get());
    	}
    	else
    	{
    		BigInteger[] factors=factor(i);
    		this.lastNumber.set(i);
    		this.lastFactors.set(factors);
    		encodeIntoResponse(resp,factors);
    	}
    	
    }
    		
}
