package com.umu.samtube405;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class LocalizationResponse implements Response
{
   private Map<String, Object> data;

   public void setData(Map<String, Object> data)
   {
      this.data = data;
   }

   public double[] getOrientation()
   {
      Map<String, Object> pose = (Map<String, Object>)data.get("Pose");
      Map<String, Object> orientation = (Map<String, Object>)pose.get("Orientation");

      double w =  (Double)orientation.get("W");      
      double x =  (Double)orientation.get("X");
      double y =  (Double)orientation.get("Y");
      double z =  (Double)orientation.get("Z");
      
      return new double[] {w, x, y, z};
   }

   public double[] getPosition()
   {
      Map<String, Object> pose = (Map<String, Object>)data.get("Pose");
      Map<String, Object> position = (Map<String, Object>)pose.get("Position");
      
      double x =  (Double)position.get("X");
      double y =  (Double)position.get("Y");
      double z =  (Double)position.get("Z");

      return new double[] {x, y, z};
   }

   public double getHeadingAngle()
   {
      double e[] = getOrientation();

      Quaternion q = new Quaternion(e);
      double[] v = q.bearing();

      return Math.atan2(v[1], v[0]);
   }

   public int getStatus()
   {
      return (Integer)data.get("Status");
   }
   public String getPath()
   {
      return "/lokarria/localization";
   }

   public long getTimestamp()
   {
      return (Long)data.get("TimeStamp");
   }

}
