package cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LearnConcurrentMap
{
     public static void main(String[] args)
     {
    	 ConcurrentMap<String, VO> cache=new ConcurrentHashMap<String, LearnConcurrentMap.VO>();
    	 VO v1=new VO(1);
    	 VO v2=new VO(2);
    	 /**
    	  * 
    	  *ConcurrentMap 对象的putIfAbsent方法的返回值特点如下：
    	  *初次以 某key  存储某 value时，是可以存储进去，函数返回值为null 
    	  * 
    	  */
    	 VO v=cache.putIfAbsent("1",v1);
    	 if (v==null)
    	 {
    		 System.out.println("初次以'1'为键储存入值VO[value=1],查看操作后Map中该键对应值为：" +cache.get("1").toString());
    	 }
    	 v=cache.putIfAbsent("1",v2);
    	 System.out.println("第二次试图以'1'为键存储不同值VO[value=2]，查看操作后Map中该键对应值为:"+cache.get("1").toString());
    	 if (v!=null)
    	 {
    		  System.out.println("第二次操作所的返回值："+v);
    	 }
     }

private static class VO
{
	public final int value;
	public VO(int _value)
	{
		this.value=_value;
	}
	@Override
	public String toString() {
		return "VO [value=" + value + "]";
	}
}
}