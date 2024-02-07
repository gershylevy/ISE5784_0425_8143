package geometries;
import primitives.*;


/**
 * Abstract class Geometry represents three-dimensional shapes in 3D Cartesian coordinate
 * system
 */

public abstract class Geometry extends Intersectable {

    /**
     * Color emission of the shape
     */
    protected Color emission=Color.BLACK;

    /**
     * Getter for emission
     * @return The emission for the Shape
     */

    public Color getEmission() {
        return emission;
    }

    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;

    }

    /**
     * Abstract function (meant to be overridden by sons) to return the normal
     * (Vector perpendicular to the shape) at a certain Point
     * @param val the Point at which we will find the normal
     * @return the normal that we found (Vector)
     */
    public abstract Vector getNormal(Point val);

}