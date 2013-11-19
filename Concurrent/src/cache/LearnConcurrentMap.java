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
    	  *ConcurrentMap �����putIfAbsent�����ķ���ֵ�ص����£�
    	  *������ ĳkey  �洢ĳ valueʱ���ǿ��Դ洢��ȥ����������ֵΪnull 
    	  * 
    	  */
    	 VO v=cache.putIfAbsent("1",v1);
    	 if (v==null)
    	 {
    		 System.out.println("������'1'Ϊ��������ֵVO[value=1],�鿴������Map�иü���ӦֵΪ��" +cache.get("1").toString());
    	 }
    	 v=cache.putIfAbsent("1",v2);
    	 System.out.println("�ڶ�����ͼ��'1'Ϊ���洢��ֵͬVO[value=2]���鿴������Map�иü���ӦֵΪ:"+cache.get("1").toString());
    	 if (v!=null)
    	 {
    		  System.out.println("�ڶ��β������ķ���ֵ��"+v);
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