package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * Class Intersectable to calculate intersections
 */

public abstract class Intersectable {

    /**
     * Function to find a shapes intersections with a Ray
     * @param ray Ray that we find intersections with
     * @return Intersection Points
     */
    public abstract List<Point> findIntersections(Ray ray);

}
