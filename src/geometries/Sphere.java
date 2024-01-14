package geometries;

import primitives.Double3;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;
import java.util.List;

/**
 * Sphere class represents sphere in 3D Cartesian coordinate
 */
public class Sphere extends RadialGeometry{
    /**
     * the center of the sphere
     */
    private final Point center;

    /**
     * constructor initializing value if radius with a new one as well as
     * center
     * @param newCenter the new center
     * @param newRadius the new radius
     */
    public Sphere(Point newCenter, double newRadius){
        super(newRadius);
        this.center=newCenter;
    }

    @Override
    public Vector getNormal(Point val){
        // Calculate the vector from the center to the point on the surface
        Vector vectorToSurface = val.subtract(center);
        return vectorToSurface.normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
