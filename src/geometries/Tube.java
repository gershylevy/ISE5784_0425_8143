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
        if(0==axis.direction.dotProduct(val.subtract(axis.head)))
            return ((val.subtract(axis.head)).normalize());

        double t=this.axis.direction.dotProduct(val.subtract(this.axis.head));
        Point o=this.axis.head.add(this.axis.direction.scale(t));
        return (val.subtract(o)).normalize();
    }
}