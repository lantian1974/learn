package chapter4and5;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PublishingVehicleTracker
{
   private final Map<String,SafePoint> locations;
   private final Map <String,SafePoint> unmodifiableMap;
   public PublishingVehicleTracker(Map<String,SafePoint> locations)
   {
	    this.locations=new ConcurrentHashMap<String,SafePoint>();
	    this.unmodifiableMap=Collections.unmodifiableMap(this.locations);
   }
   public Map<String ,SafePoint> getLocations()
   {
	   return this.unmodifiableMap;
   }
   public SafePoint getLocation(String id)
   {
	   return this.locations.get(id);
   }
   public void setLocation(String id,int x,int y)
   {
	   if (!locations.containsKey(id))
		   throw new RuntimeException("invalid vehicle name: "+"id");
	   this.locations.get(id).set(x, y);
   }
}
