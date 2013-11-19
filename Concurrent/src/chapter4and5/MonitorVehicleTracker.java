package chapter4and5;
import java.util.HashMap;
import java.util.Map;
public class MonitorVehicleTracker
{
   private final Map<String,MutablePoint> locations;
   public MonitorVehicleTracker(Map<String,MutablePoint> locations)
   {
	   this.locations=deepCopy(locations);
   }
   private static Map<String,MutablePoint> deepCopy(Map<String,MutablePoint> m)
   {
	   Map<String,MutablePoint> result=new HashMap<String,MutablePoint>();
	   for(String id:m.keySet())
	   {
		   result.put(id,new MutablePoint(m.get(id)));
	   }
	   return result;
   }
   public synchronized MutablePoint getLocation(String id)
   {
	   MutablePoint loc=this.locations.get(id);
	   return loc==null? null:new MutablePoint(loc);
   }
   public synchronized void setLocation(String id,int x,int y)
   {
	   MutablePoint loc=this.getLocation(id);
	   if (loc==null)
	   {
           throw new RuntimeException("No such ID: "+id);
       }
	   loc.x=x;
	   loc.y=y;
   }
   
}
