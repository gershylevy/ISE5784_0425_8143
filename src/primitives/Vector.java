package primitives;
import static java.lang.Math.sqrt;
import static primitives.Point.*;

/**
 * This class represents a Vector (a line with length and direction)
 * Starting from (0,0,0) in a 3D Cartesian coordinate system
 */
public class Vector extends Point {
    /**
     * Constructor to initialize Vector with a Double3
     * The function will throw an error if the coordinates inputted are the zero triad (0,0,0)
     * @param coordinates Double3 who's values we put in the Vector
     */
    public Vector(Double3 coordinates) {
        super(coordinates);
        if (coordinates.equals(Double3.ZERO))
            throw new IllegalArgumentException ("Vector equals 0");
    }

    /**
     * Constructor to initialize Vector with 3 doubles
     * @param x double that becomes the x coordinate
     * @param y double that becomes the y coordinate
     * @param z double that becomes the z coordinate
     */

    public Vector(double x,double y, double z) {
        super(x,y,z);
        if(x==0&&y==0&&z==0)
            throw new IllegalArgumentException ("Vector equals 0");
    }

    /**
     * Function to add 2 Vectors together by adding their corresponding coordinates
     * @param vector1 Vector that's being added to our Vector
     * @return result of the addition (Vector)
     */

    public Vector add(Vector vector1) {
        return new Vector(this.xyz.add(vector1.xyz));
    }

    /**
     * Function to multiply a Vector by a double
     * @param scalar the number that the Vector will be multiplied by
     * @return result of the multiplication (Vector)
     */

    public Vector scale(double scalar){
    return new Vector(this.xyz.scale(scalar));
    }

    /**
     * Function to dot product 2 Vectors
     * @param vector1 the Vector that we dot product with ours
     * @return the result of the dot product (double)
     */

    public double dotProduct(Vector vector1) {
        return ((vector1.xyz.d1*this.xyz.d1)+(vector1.xyz.d2*this.xyz.d2)+(vector1.xyz.d3*this.xyz.d3));
    }

    /**
     * Function to cross product 2 Vectors
     * @param vector1 the Vector that we cross product with ours
     * @return the result of the cross product (Vector)
     */

    public Vector crossProduct (Vector vector1) {
        return new Vector(((this.xyz.d2*vector1.xyz.d3)-(this.xyz.d3*vector1.xyz.d2)),((this.xyz.d3*vector1.xyz.d1)-(this.xyz.d1*vector1.xyz.d3)),((this.xyz.d1*vector1.xyz.d2)-(this.xyz.d2*vector1.xyz.d1)));
    }

    /**
     * Function to find the length squared of a Vector
     * @return the length of the Vector squared
     */

    public double lengthSquared() {
        return (this.dotProduct(this));
    }

    /**
     * Function to find the length of a Vector
     * @return the length of the Vector
     */

    public double length() {
        return (sqrt(this.lengthSquared()));
    }

    /**
     * Function to normalize (shorten it to length 1) a Vector
     * @return the resulting Vector
     */

    public Vector normalize() {
        return (this.scale(1/this.length()));
    }

    @Override
    public String toString() {
        return this.xyz.toString();
    }

    @Override
    public boolean equals(Object compare) {
        if (this == compare) return true;
        return (compare instanceof Vector other) && super.equals(other);
    }

}