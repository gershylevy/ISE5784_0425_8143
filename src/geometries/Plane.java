package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.ArrayList;
import java.util.List;
import static primitives.Util.*;

/**
 * Plane class represents two-dimensional plane in 3D Cartesian coordinate
 * system
 */
public class Plane extends Geometry {

    /**
     * the point from which we calculate the Plane
     */
    protected final Point q;

    /**
     * the Vector from which we calculate the Plane
     */
    private final Vector normal;

    /**
     * Constructor to initialize Plane
     * with q (point to calculate the Plane), 2 Points (don't use them for now)
     * @param newQ the new Point q from which we calculate the plane
     * @param newNormal1 not used for now
     * @param newNormal2 not used for now
     */
    public Plane(Point newQ, Point newNormal1, Point newNormal2) {
        this.q=newQ;
        this.normal=((newNormal1.subtract(newQ)).crossProduct(newNormal2.subtract(newQ))).normalize();
    }

    /**
     * Constructor to initialize Plane
     * with q (point to calculate the Plane), normal (Vector to calculate the Plane)
     * @param newQ the new Point q from which we calculate the plane
     * @param newNormal the new Vector normal from which we calculate the plane
     */

    public Plane(Point newQ, Vector newNormal) {
        this.q=newQ;
        this.normal=newNormal.normalize();
    }

    /**
     * Getter function that returns the normal of the Plane
     * @return the normal of the Plane (Vector)
     */


    public Vector getNormal() { return normal; }

    /**
     * Getter function that returns the normal of the Plane with the input of a Point
     * @param val not used for now
     * @return the normal of the Plane (Vector)
     */
    @Override
    public Vector getNormal(Point val) {
        return normal;
    }

/*    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray){


        if(isZero(alignZero(normal.dotProduct(ray.direction)))){
            return null;
        }

        if(ray.head.equals(q)){
            return null;
        }


        if(isZero(alignZero(normal.dotProduct(q.subtract(ray.head))))) {
            return null;
        }

        double t = alignZero((alignZero(normal.dotProduct(q.subtract(ray.head)))/alignZero(normal.dotProduct(ray.direction))));

        if (t<0){
            return null;
        }

        return List.of(new GeoPoint(this, ray.getPoint(t)));
    }
*/

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double max){


        if(isZero(alignZero(normal.dotProduct(ray.direction)))){
            return null;
        }

        if(ray.head.equals(q)){
            return null;
        }


        if(isZero(alignZero(normal.dotProduct(q.subtract(ray.head))))) {
            return null;
        }

        double t = alignZero((alignZero(normal.dotProduct(q.subtract(ray.head)))/alignZero(normal.dotProduct(ray.direction))));

        if (t<0){
            return null;
        }

        if(ray.getPoint(t).distance(ray.head)>max)
            return null;

        return List.of(new GeoPoint(this, ray.getPoint(t)));
    }

}