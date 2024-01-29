package renderer;

import primitives.*;
import geometries.*;

import java.awt.image.BufferedImage;
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

    private Point viewPlaneCenter;

    private ImageWriter imageWriter;

    public ImageWriter getImageWriter() {
        return imageWriter;
    }

    private RayTracerBase rayTracer;

    public RayTracerBase getRayTracer() {
        return rayTracer;
    }

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

    /**
     * Function to construct Ray from Camera
     * @param nX Resolution on the x-axis
     * @param nY Resolution on the y-axis
     * @param j Index of the x-axis
     * @param i Index of the y-axis
     * @return Ray that we constructed
     */
    public Ray constructRay(int nX, int nY, int j, int i) {

        double ry=this.viewPlaneHeight/nY;
        double rx=this.viewPlaneWidth/nX;

        double xj=(j-(nX-1)/2.0)*rx;
        double yi=-(i-(nY-1)/2.0)*ry;
        Point pij=viewPlaneCenter;

        if(xj!=0)
            pij=pij.add(vRight.scale(xj));
        if(yi!=0)
            pij=pij.add(vUp.scale(yi));

        return new Ray(p0,pij.subtract(p0));

    }

    public void printGrid(int interval,Color color){
        for(int i=0;i<this.viewPlaneWidth;i+=interval)
            for(int j=0;j<this.viewPlaneHeight;j+=1)
                this.imageWriter.writePixel(i,j,color);


        for(int i=0;i<this.viewPlaneHeight;i+=interval)
            for(int j=0;j<this.viewPlaneWidth;j+=1)
                this.imageWriter.writePixel(j,i,color);
    }


    public void renderImage(){
        for (double i=0;i<this.getViewPlaneWidth();i++)
            for(double j=0;j<this.getViewPlaneHeight();j++)
                this.castRay((int) Math.round(getViewPlaneWidth()),(int) Math.round(getViewPlaneHeight()),(int) Math.round(i),(int) Math.round(j));
    }

    public void writeToImage(){
        this.imageWriter.writeToImage();
    }
    public Ray castRay(int Nx,int Ny,int indexI,int indexT)
    {
        Ray ray=this.constructRay(Nx,Ny,indexI,indexT);
        Color color= this.rayTracer.traceRay(ray);
        this.imageWriter.writePixel(indexI,indexT,color);
        return ray;
    }



    /**
     * Builder class because we are using Builder Design Pattern
     */
    public static class Builder{
        private final Camera camera = new Camera();

        public Builder setImageWriter(int Horizontal, int Vertical, String imageName) throws CloneNotSupportedException {
            Camera.Builder cameraBuilder = Camera.getBuilder();
            Camera cam1=cameraBuilder.build();
            cam1.imageWriter=new ImageWriter(imageName,Vertical,Horizontal);
            return this;
        }

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

        public Builder setImageWriter(ImageWriter imageWriter) {
            camera.imageWriter = imageWriter;
            return this;
        }

        public Builder setRayTracer(RayTracerBase rayTracer) {
            camera.rayTracer = rayTracer;
            return this;
        }

        public Camera build() throws CloneNotSupportedException {
            String cam=Camera.class.getName();
            String error="Missing rendering data";
            camera.vRight=camera.vUp.crossProduct(camera.vTo);
            camera.viewPlaneCenter= camera.p0.add(camera.vTo.scale(camera.viewPlaneDistance));



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
                if(camera.imageWriter==null)
                    throw new MissingResourceException(error, cam, "Missing ImageWriter in Camera");
                if(camera.rayTracer==null)
                    throw new MissingResourceException(error, cam, "Missing RayTracer in Camera");

                if (!Util.isZero(camera.vRight.dotProduct(camera.vTo)))
                    throw new IllegalArgumentException("To and up Vectors not orthogonal");
                camera.vRight = camera.vTo.crossProduct(camera.vUp).normalize();

            return (Camera) camera.clone();


        }

    }

}
