package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Cylinder class represents a Cylinder in 3D Cartesian coordinate
 * system
 */
 public class Cylinder extends Tube{

    /**
     * The height of the Cylinder
     */
    private final double height;

    /**
     * Constructor to initialize Cylinder
     * with a height, axis, Radius
     * @param newHeight the new value for height (double)
     * @param newAxis the new value for axis (Ray)
     * @param newRadius the new value for radius (double)
     */

    public Cylinder(double newHeight,Ray newAxis,double newRadius){
        super(newAxis,newRadius);
        this.height=newHeight;
    }

    @Override
    protected Vector getNormal(Point val) {
        return null;
    }
}
