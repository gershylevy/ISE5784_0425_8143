package renderer;

import primitives.*;
import geometries.*;
import java.util.MissingResourceException;

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
        Point pc=this.p0.add(this.vTo.scale(this.viewPlaneDistance));

        double ry=this.viewPlaneHeight/nY;
        double rx=this.viewPlaneWidth/nX;

        double xj=(j-(nX-1)/2)*rx;
        double yi=-(i-(nY-1)/2)*ry;
        Point pij=pc;

        if(xj!=0)
            pij=pij.add(vRight.scale(xj));
        if(yi!=0)
            pij=pij.add(vUp.scale(yi));

        return new Ray(p0,pij.subtract(p0));

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
            camera.vTo = to.normalize();
            camera.vUp = up.normalize();
            return this;
        }

        public Builder setVpSize(double width, double height) {
            camera.viewPlaneWidth = width;
            camera.viewPlaneHeight = height;
            return this;
        }

        public Builder setVpDistance(double distance) {
            camera.viewPlaneDistance = distance;
            return this;
        }

        public Camera build() throws CloneNotSupportedException {
            String cam=Camera.class.getName();
            String error="Missing rendering data";
            camera.vRight=camera.vUp.crossProduct(camera.vTo);
                if (camera.p0 == null)
                    throw new MissingResourceException(error, cam, "Camera doesn't have starting Point");
                if (camera.vTo == null)
                    throw new MissingResourceException(error, cam, "Camera doesn't have direction");
                if (camera.vUp == null)
                    throw new MissingResourceException(error, cam, "Camera doesn't have Vector pointing up");
                if (camera.vRight == null)
                    throw new MissingResourceException(error, cam, "Camera doesn't have Vector pointing right");
                if (Util.alignZero(camera.viewPlaneWidth) <= 0)
                    throw new MissingResourceException(error, cam, "View Plane can't have width of 0");
                if (Util.alignZero(camera.viewPlaneHeight) <= 0)
                    throw new MissingResourceException(error, cam, "View Plane can't have height of 0");
                if (Util.alignZero(camera.viewPlaneDistance) <= 0)
                    throw new MissingResourceException(error, cam, "View Plane can't have the distance of 0 from the Camera");

                if (!Util.isZero(camera.vRight.dotProduct(camera.vTo)))
                    throw new IllegalArgumentException("To and up Vectors not orthogonal");
                camera.vRight = camera.vTo.crossProduct(camera.vUp).normalize();

            return (Camera) camera.clone();


        }



    }


}
