package primitives;
import geometries.Intersectable;

import java.util.List;

import static primitives.Util.*;

/**this class will represent a line that has direction and a beginning but no end point
 * @author netanel grossman
 */
public class Ray {
    /**
     * beginning of the line
     */
    public final Point head;
    /**
     * vector representing direction ov line
     */
    public final Vector direction;

    /**
     * constructor initializing head and direction
     * @param newHead the new value for head
     * @param newVector the new value for vector
     */
   public Ray(Point newHead, Vector newVector) {
       this.head=newHead;
       this.direction=newVector.normalize();
   }

    /**
     * function that checks if equal
     * @param obj different ray that we are checking if the same
     * @return one or zero depending on if equal
     */
   @Override
   public boolean equals(Object obj) {

       if (this == obj) return true;
       return (obj instanceof Ray other)
               && this.head.equals(other.head)
               && this.direction.equals(other.direction);
   }

    /**
     * function creating a string out of our ray
     * @return a string with head and direction of ray
     */
   public String toString() {
       return head.toString() + "," + direction.toString();
   }


    /**
     * Function to find a Point a given distance down a Ray
     * @param t The number that we find its distance down the Ray
     * @return The Point that is t down the Ray
     */
   public Point getPoint(double t) {
       if(isZero(t))
           return this.head;
       return (this.head.add(this.direction.scale(t)));
   }

    /**
     * Function to find the closest Point from the head of the Ray
     * @param points List of potential closest Points
     * @return The closest Point
     */

    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new Intersectable.GeoPoint(null, p)).toList()).point;
    }


    /**
     * Function to find the closest GeoPoint from the head of the Ray
     * @param pointList List of potential closest GeoPoints
     * @return The closest GeoPoint
     */


   public Intersectable.GeoPoint findClosestGeoPoint(List<Intersectable.GeoPoint> pointList) {

        if(pointList==null)
            return null;

        Intersectable.GeoPoint closest=pointList.get(0);
        double distance=pointList.getFirst().point.distance(this.head);

        for(int i=0;i<pointList.size();i++) {
            if(pointList.get(i).point.distance(this.head)<distance) {
                closest = pointList.get(i);
                distance=pointList.get(i).point.distance(this.head);
            }
        }
        return closest;
    }

}
