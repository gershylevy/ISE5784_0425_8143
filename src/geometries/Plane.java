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
public class Plane implements Geometry {

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

    public Vector getNormal(Point val) {
        return normal;
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        if(isZero(this.normal.dotProduct(ray.direction)))
            return null;
        if(this.q.equals(ray.head))
            return null;
        double t=alignZero(this.normal.dotProduct(this.q.subtract(ray.head)))/(this.normal.dotProduct(ray.direction));
        if(t>0) {
            final var pointList=List.of(ray.getPoint(t));
            return pointList;
        }
        return null;
    }

}