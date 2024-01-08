package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Plane class represents two-dimensional plane in 3D Cartesian coordinate
 * system
 */
public class Plane extends Geometry {

    /**
     * the point from which we calculate the Plane
     */
    private final Point q;

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
        normal=null;
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

    public Vector getNormal() {
        return normal;
    }

    /**
     * Getter function that returns the normal of the Plane with the input of a Point
     * @param val not used for now
     * @return the normal of the Plane (Vector)
     */

    public Vector getNormal(Point val) {
        return normal;
    }


}