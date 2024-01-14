package geometries;

/**
 * this class will serve as a parent class to sub category of geometrical shapes
 * that are circular and need a radius
 */
public abstract class RadialGeometry implements Geometry {
    /**
     * this will be the radius
     */
    protected final double radius;

    /**
     * constructor initializing new radius
     * @param newRadius the new radius value
     */
    protected RadialGeometry(double newRadius) {
        this.radius=newRadius;
    }


}
