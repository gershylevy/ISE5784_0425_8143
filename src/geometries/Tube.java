package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

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
    protected Vector getNormal(Point val) {
        return (val.subtract(this.axis.head)).normalize();
    }
}