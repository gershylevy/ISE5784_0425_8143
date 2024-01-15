package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;

/**
 * Tube class represents tube in 3D Cartesian coordinate
 */
public class Tube extends RadialGeometry{
    /**
     * axis telling the direction of the tube
     */
    protected final Ray axis;

    /**
     * constructor initiating a new axis and a new radius
     * @param newAxis the new axis
     * @param newRadius the new radius
     */
    public Tube(Ray newAxis, double newRadius) {
        super(newRadius);
        this.axis=newAxis;
    }

    @Override
    public Vector getNormal(Point val) {
        double t = axis.direction.dotProduct(val.subtract(axis.head));
        if(isZero(t))
            return ((val.subtract(axis.head)).normalize());

        Point o=this.axis.head.add(this.axis.direction.scale(t));
        return (val.subtract(o)).normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}