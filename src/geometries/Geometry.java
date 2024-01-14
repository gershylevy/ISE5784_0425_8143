package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import primitives.Vector;

/**
 * Abstract class Geometry represents three-dimensional shapes in 3D Cartesian coordinate
 * system
 */

public abstract interface Geometry extends Intersectable {

    /**
     * Abstract function (meant to be overridden by sons) to return the normal
     * (Vector perpendicular to the shape) at a certain Point
     * @param val the Point at which we will find the normal
     * @return the normal that we found (Vector)
     */
    public abstract Vector getNormal(Point val);

}
