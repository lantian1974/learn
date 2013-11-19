package chapter4and5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.lang.Long;
public class LearnFutureTask
{
    public void  doConcurrencyCompute() throws InterruptedException, ExecutionException
    {
	   FutureTask<Long> task1=new FutureTask<Long>(new DoSum(1,1900000000));
       FutureTask<Long> task2=new FutureTask<Long>(new DoSum(1,1900000000));
       FutureTask<Long> task3=new FutureTask<Long>(new DoSum(1,1900000000));
       FutureTask<Long> task4=new FutureTask<Long>(new DoSum(1,1900000000));
       long start =System.currentTimeMillis();
       Thread t1=new Thread(task1);
       Thread t2=new Thread(task2);
       Thread t3=new Thread(task3);
       Thread t4=new Thread(task4);
       t1.start();
       t2.start();
       t3.start();
       t4.start();
       System.out.println("任务调度完毕");
       System.out.println(task1.get().longValue()+task2.get().longValue()+task3.get().longValue()+task4.get().longValue());
       long end=System.currentTimeMillis();;
       Long time=Long.valueOf(end-start);
       System.out.println("并行计算结束，用时 ："+time);
    }

    public void  doSerialCompute() throws Exception
    {   
    	  System.out.println("串行计算开始");
    	  DoSum task1=new DoSum(1,1900000000);
    	  DoSum task2=new DoSum(1,1900000000);
    	  DoSum task3=new DoSum(1,1900000000);
    	  DoSum task4=new DoSum(1,1900000000);
    	  long start =System.currentTimeMillis();
    	  long result =task1.call().longValue();
    	  result=result+task2.call().longValue();
    	  result=result+task3.call().longValue();
    	  result=result+task4.call().longValue();
    	  System.out.println(result);
    	  long end=System.currentTimeMillis();
    	  Long time=Long.valueOf(end-start);
    	  System.out.println("串行计算结束，用时 ："+time);
    	  
    }
	public static void main(String[] args) throws Exception
	{
		LearnFutureTask lft=new LearnFutureTask();
		lft.doConcurrencyCompute();
		lft.doSerialCompute();
	}
	static  class DoSum implements Callable<Long>
	{  
	    private final int from,to;
		public DoSum(int  from,int to)
	    {
	    	 if (from>=to)
	   		  {
	   			  throw new RuntimeException("argument to must bigger than from!");
	   		  }
	   		  this.from=from;
	   		  this.to=to;
	    }
		@Override
		public Long call() throws Exception 
		{
            long result=0;			
			for(int i=this.from;i<=this.to;i++)
			{
				result=result+i;
			}
			return Long.valueOf(result);	
		}
	}	
}
