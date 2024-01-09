package primitives;
import static java.lang.Math.sqrt;
import static primitives.Util.isZero;
import static primitives.Double3.ZERO;
import static primitives.Vector.*;

/**
 * This class represents a Point in a 3D Cartesian coordinate system
 */
public class Point {

    /**
     * Coordinates for the Point in Double3 form, such that d1 is the x value, d2 is the y value, d3 is z value
     */
    protected final Double3 xyz;


    /**
     * Constructor to initialize Point with a Double3
     * @param Coordinates Double3 that we put its values into a Point
     */
    public Point(Double3 Coordinates) {
        xyz = new Double3(Coordinates.d1,
                Coordinates.d2,Coordinates.d3);
    }


    /**
     * Constructor to initialize Point with 3 doubles
     * @param x double that becomes the x value in the point
     * @param y double that becomes the y value in the point
     * @param z double that becomes the z value in the point
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x,y,z);
    }
    @Override
    public boolean equals(Object compare) {
        if (this == compare) return true;
        return (compare instanceof Point other) && this.xyz.equals(other.xyz);
    }

    /**
     * Function to return Point with the zero triad (0,0,0) as its values
     */
    public static final Point ZERO = new Point(0, 0, 0);

    @Override
    public String toString() {
        return xyz.toString();
    }

    /**
     * Function to add a Vector to a Point by adding their corresponding coordinates
     * and return the resulting Point
     * @param vector1 Vector that's being added to the Point
     * @return result of add (Point)
     * hi
     */
    public Point add(Vector vector1){
        return new Point ((vector1.xyz.d1+this.xyz.d1),(vector1.xyz.d2+this.xyz.d2),(vector1.xyz.d3+this.xyz.d3));
    }

    /**
     * Function to subtract 2 Points by subtracting their corresponding coordinates
     * and return the resulting Vector
     * @param point1 Point that's being subtracted from our Point
     * @return result of subtract (Vector)
     */
    public Vector subtract(Point point1){
        return new Vector ((this.xyz.d1-point1.xyz.d1),(this.xyz.d2-point1.xyz.d2),(this.xyz.d3-point1.xyz.d3));
    }



    /**
     * Function to calculate the distance squared between 2 Points
     * @param point1 Point that its distance squared from our Point is being measured
     * @return the distance between the 2 Points squared (double)
     */
    public double distanceSquared(Point point1) {
        return ((this.xyz.d1 - point1.xyz.d1) * (this.xyz.d1 - point1.xyz.d1)
                + (this.xyz.d2 - point1.xyz.d2) * (this.xyz.d2 - point1.xyz.d2)
                + (this.xyz.d3 - point1.xyz.d3) * (this.xyz.d3 - point1.xyz.d3));
    }
    /**
     * Function to calculate the distance between 2 Points
     * @param point1 Point that its distance from our Point is being measured
     * @return the distance between the 2 Points (double)
     */
    public Double distance(Point point2) {
        return sqrt(this.distanceSquared(point2));
    }


}
