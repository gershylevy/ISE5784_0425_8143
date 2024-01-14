package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * Class Intersectable to calculate intersections
 */

public interface Intersectable {
    List<Point> findIntersections(Ray ray);

}
