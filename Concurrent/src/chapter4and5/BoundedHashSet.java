package chapter4and5;


import java.util.concurrent.Semaphore;
import java.util.HashSet;
import java.util.Collections;
import java.util.Set;

public class BoundedHashSet<T>
{
    private final Set<T> set;
    private final Semaphore sem;
    public BoundedHashSet(int bound)
    {
    	 this.set=Collections.synchronizedSet(new HashSet<T>());
    	 this.sem=new Semaphore(bound);
    }
    public boolean add(T o) throws InterruptedException
    {
        sem.acquire();
        boolean wasAdded=false;
        try
        {
        	wasAdded=this.set.add(o);
        	return wasAdded;
        }
        finally
        {
           if (!wasAdded)
           {
        	   sem.release();
           }
        }
    }  
    public boolean remove(Object o)
    {
       boolean wasRemoved=set.remove(o);
       if (wasRemoved)
    	   sem.release();
       return wasRemoved;
    }
}
