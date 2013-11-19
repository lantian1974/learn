package chapter4and5;

public class SafePoint 
{
    private int x,y;
	private SafePoint(int[] a) 
    {
    	this(a[0],a[1]);
    }
    public SafePoint(SafePoint p)
    {
    	this(p.get());
    }
    public SafePoint(int x,int y)
    {
    	 this.x=x;
    	 this.y=y;
    }
    public synchronized int[] get()
    {
    	return new int[]{this.x,this.y};
    }
    public synchronized void set(int x,int y)
    {
    	this.x=x;
    	this.y=y;
    }
} 
