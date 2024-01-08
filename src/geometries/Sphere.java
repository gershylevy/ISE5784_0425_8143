package geometries;

import primitives.Double3;
import primitives.Point;
import primitives.Vector;

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
    protected Vector getNormal(Point val) {
        return null;
    }

}
