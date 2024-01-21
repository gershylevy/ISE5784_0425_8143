package renderer;

import primitives.*;
import geometries.*;

/**
 * Camera class to represent a Camera in a Scene
 */

public class Camera implements Cloneable {


    /**
     * Point that the Camera is at
     */
    private Point p0;

    /**
     * Direction that the Camera is Pointing
     */
    private Vector vTo;

    /**
     * Vector facing upwards from the Camera
     */
    private Vector vUp;

    /**
     * Vector facing rightwards from the Camera
     */
    private Vector vRight;

    /**
     * Width of the View Plane
     */
    private double viewPlaneWidth=0;

    /**
     * Width of the View Plane
     */
    private double viewPlaneHeight=0;

    /**
     * Distance of the View Plane from the Camera
     */
    private double viewPlaneDistance=0;

    /**
     * Getter for p0
     */
    public Point getP0() {return p0;}

    /**
     * Getter for vTo
     */
    public Vector getVTo(){return vTo;}

    /**
     * Getter for vUp
     */
    public Vector getVUp(){return vUp;}

    /**
     * Getter for vRight
     */
    public Vector getVRight(){return vRight;}

    /**
     * Getter for viewPlaneWidth
     */
    public double getViewPlaneWidth(){return viewPlaneWidth;}

    /**
     * Getter for viewPlaneHeight
     */
    public double getViewPlaneHeight(){return viewPlaneHeight;}

    /**
     * Getter for viewPlaneDistance
     */
    public double getViewPlaneDistance(){return viewPlaneDistance;}

    /**
     * Default constructor for Camera (private because we are using Builder Design Pattern)
     */
    private Camera() {

    }

    /**
     * Getter for Constructor (because we are using Builder Design Pattern)
     */

    public static Builder getBuilder() {return new Builder();}

    public Ray constructRay(int nX, int nY, int j, int i) {
        return null;
    }


    /**
     * Builder class because we are using Builder Design Pattern
     */
    public static class Builder{
        private final Camera camera = new Camera();

        public Builder setLocation(Point location) {
            camera.p0 = location;
            return this;
        }

        public Builder setDirection(Vector to, Vector up) {
            if(to.dotProduct(up)==0)
                throw new IllegalArgumentException ("to and up Vectors not orthogonal");
            camera.vTo = to.normalize();
            camera.vUp = up.normalize();
            return this;
        }

        public Builder setVpSize(double width, double height) {
            camera.viewPlaneWidth = width;
            camera.viewPlaneHeight = height;
            return this;
        }

        public Builder setViewPlaneDistance(double distance) {
            camera.viewPlaneDistance = distance;
            return this;
        }



    }


}
