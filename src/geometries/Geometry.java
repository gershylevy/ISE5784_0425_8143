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
     * The material of the Geometry
     */

    private Material material=new Material();

    /**
     * Getter for material
     * @return The material of the Shape
     */

    public Material getMaterial() {
        return material;
    }

    /**
     * Getter for emission
     * @return The emission for the Shape
     */

    public Color getEmission() {
        return emission;
    }

    /**
     * Setter for Material
     * @param material Our new material
     * @return Return this for chained setters
     */

    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /**
     * Setter for emission
     * @param newEmission Our new emission
     */



    public Geometry setEmission(Color newEmission) {
        this.emission= newEmission;
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