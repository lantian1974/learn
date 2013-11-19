package chapter4and5;

public class MutablePoint
{
    public int x;
    public int y;
    public MutablePoint()
    {
    	this.x=0;
    	this.y=0;
    }
    public MutablePoint(MutablePoint m)
    {
    	this.x=m.x;
    	this.y=m.y;
    }
}
