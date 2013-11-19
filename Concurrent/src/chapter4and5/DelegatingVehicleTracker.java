package chapter4and5;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class DelegatingVehicleTracker 
{
   private final ConcurrentMap<String,Point> locations;
   private final Map<String,Point> unmodifiableMap;
   public DelegatingVehicleTracker(Map<String,Point> points)
   {
	   this.locations=new ConcurrentHashMap<String,Point>(points);
	   this.unmodifiableMap=Collections.unmodifiableMap(this.locations);	
   }
}
