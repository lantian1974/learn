package chapter4and5;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


public class Solver
{
  final int N;
  final int[][]data;
  final CyclicBarrier barrier;
  private boolean done=false;
  public Solver(int[][] matrix) throws InterruptedException, BrokenBarrierException
  {
	 this.data=matrix;
	 N=this.data.length;
	 barrier = new CyclicBarrier(N+1,new BarrierAction());
	 for (int i = 0; i < data.length; ++i) 
	  {
	       Thread workThread=new Thread(new Worker(i));
	       workThread.start();
	  }
	  this.barrier.await();
	  this.done=true;
	  System.out.println("solver ¹¹ÔìÍê±Ï£¡");
  }
  private void processRow(int row)
  {
	  for (int i=0;i<data[row].length;i++)
	  {
		  data[row][i]=i;
		  
	  }	  
  }
  public static void main(String[] args) throws InterruptedException, BrokenBarrierException
  {
	  int[][] data=new int[5][8];
	  Solver s=new Solver(data);
	  for(int i=0;i<s.data.length;i++)
			{
				for(int j=0;j<s.data[i].length;j++)
				{
					System.out.print(s.data[i][j]);
					System.out.print(" ");
				}
				System.out.println();
			}	
	
  }
  private  class BarrierAction implements Runnable
  {

	@Override
	public void run()
	{
		for(int i=0;i<data.length;i++)
		{
			for(int j=0;j<data[i].length;j++)
			{
				data[i][j]=8;
			}
		}	
	} 
  }
    class Worker implements Runnable
  {
    int myrow;
     Worker(int row)
    {
    	this.myrow=row;
    }
	@Override
	public void run() 
	{
		if (!done)
    	{
    	   processRow(myrow);
	       try
	       {
    	       barrier.await(); 
    	    }
	       catch (InterruptedException ex)
	       { 
    	          return; 
    	   }
	       catch (BrokenBarrierException ex) 
    	   { 
    	           return; 
    	   }
    	 }
	}
	  
  }
}
