package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * Class Intersectable to calculate intersections
 */

public interface Intersectable {

    /**
     * Function to find a shapes intersections with a Ray
     * @param ray Ray that we find intersections with
     * @return Intersection Points
     */
    List<Point> findIntersections(Ray ray);

}
