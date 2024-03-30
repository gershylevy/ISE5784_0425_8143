package renderer;

import primitives.*;
import geometries.*;

import java.awt.image.BufferedImage;
import java.util.MissingResourceException;
import java.util.List;

import static primitives.Util.isZero;

/**
 * Camera class to represent a Camera in a Scene
 */

public class Camera implements Cloneable {

    /**
     * Blackboard of the Camera
     */

    private BlackBoard blackBoard=new BlackBoard();

    /**
     * Switch to activate MP1
     */

    private Boolean isGrid=false;

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
    private int viewPlaneWidth=0;

    /**
     * Width of the View Plane
     */
    private int viewPlaneHeight=0;

    /**
     * Distance of the View Plane from the Camera
     */
    private double viewPlaneDistance=0;

    /**
     * Center Point of the VP
     */

    private Point viewPlaneCenter;

    /**
     * ImageWriter field
     */

    private ImageWriter imageWriter;

    /**
     * Getter for image writer
     */


    public ImageWriter getImageWriter() {
        return imageWriter;
    }

    /**
     * RayTracer field
     */

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
    private Camera() {}

    /**
     * Getter for Constructor (because we are using Builder Design Pattern)
     */

    public static Builder getBuilder() {return new Builder();}

    private Point constructRayHelper(int nX, int nY, int j, int i){
        double ry=  (double)  this.viewPlaneHeight/nY;
        double rx=(double) this.viewPlaneWidth/nX;

        double xj=(j-(nX-1)/2.0)*rx;
        double yi=-(i-(nY-1)/2.0)*ry;
        Point pij=viewPlaneCenter;

        if(!isZero(xj))
            pij=pij.add(vRight.scale(xj));
        if(!isZero(yi))
            pij=pij.add(vUp.scale(yi));
        return pij;
    }

    /**
     * Function to construct Ray from Camera
     * @param nX Resolution on the x-axis
     * @param nY Resolution on the y-axis
     * @param j Index of the x-axis
     * @param i Index of the y-axis
     * @return Ray that we constructed
     */
    public Ray constructRay(int nX, int nY, int j, int i) {

        Point pij=constructRayHelper(nX, nY, j, i);
        return new Ray(p0,pij.subtract(p0));

    }

    /**
     * Function to draw a grid on the Image
     * @param interval Distance between the lines of the grid
     * @param color Color of the grid
     */

    public void printGrid(int interval,Color color){
        for(int i=0;i<this.viewPlaneWidth;i+=interval)
            for(int j=0;j<this.viewPlaneHeight;j+=1) {
                this.imageWriter.writePixel(i, j, color);
            }


        for(int i=0;i<this.viewPlaneHeight;i+=interval)
            for(int j=0;j<this.viewPlaneWidth;j+=1)
                this.imageWriter.writePixel(j,i,color);
    }

    /**
     * Function to Render the Image
     */

    public void renderImage() throws CloneNotSupportedException {
        int nX = this.imageWriter.getNx();
        int nY = this.imageWriter.getNy();
        for (int i=0;i<nX;i++)
            for(int j=0;j<nY;j++)
                this.castRay(nX,nY,i,j);
    }

    /**
     * Function to Make Image
     */

    public void writeToImage(){
        this.imageWriter.writeToImage();
    }

    /**
     * Function to cast Ray through the center of a pixel
     * @param Nx Resolution on the x-axis
     * @param Ny Resolution on the y-axis
     * @param indexI Index on the x-axis
     * @param indexT Index on the y-axis
     */

    private void castRay(int Nx,int Ny,int indexI,int indexT) throws CloneNotSupportedException {
        if(!isGrid) {
            Ray ray = this.constructRay(Nx, Ny, indexI, indexT);
            this.imageWriter.writePixel(indexI, indexT, rayTracer.traceRay(ray));
        }
        else {
            blackBoard.setPixelWidth((double) this.viewPlaneWidth/Nx).setPixelHeight((double) this.viewPlaneHeight/Ny)
                    .setGridSize(9);
            Point Pij=constructRayHelper(Nx,Ny,indexI,indexT);
            List<Point> pointList=blackBoard.createGrid(Pij);
            Color color1=new Color(0,0,0);
            for (Point p: pointList) {
                color1=color1.add(rayTracer.traceRay(new Ray(p0, p.subtract(p0))));
            }

            color1.reduce(blackBoard.gridSize*blackBoard.gridSize);

            this.imageWriter.writePixel(indexI, indexT, color1);
        }
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

        /**
         * Function to set location of Camera
         * @param location new location
         */

        public Builder setLocation(Point location) {
            camera.p0 = location;
            return this;
        }

        /**
         * Function to set Direction on Camera
         * @param to New Vto for the Camera
         * @param up New Vup for the Camera
         */

        public Builder setDirection(Vector to, Vector up) {
            camera.vTo = to.normalize();
            camera.vUp = up.normalize();
            return this;
        }

        /**
         * Function to set view plane size
         * @param width New width of the view plane
         * @param height New height of the view plane
         */

        public Builder setVpSize(int width, int height) {
            camera.viewPlaneWidth = width;
            camera.viewPlaneHeight = height;
            return this;
        }

        public Builder setIsGrid(Boolean temp){
            camera.isGrid=temp;
            return this;
        }

        /**
         * Function to set view plane distance
         * @param distance New distance of the view plane
         */

        public Builder setVpDistance(double distance) {
            camera.viewPlaneDistance = distance;
            return this;
        }

        /**
         * Function to set image writer
         * @param imageWriter New image writer
         */

        public Builder setImageWriter(ImageWriter imageWriter) {
            camera.imageWriter = imageWriter;
            return this;
        }

        /**
         * Function to set ray tracer
         * @param rayTracer New ray tracer
         */

        public Builder setRayTracer(RayTracerBase rayTracer) {
            camera.rayTracer = rayTracer;
            return this;
        }

        /**
         * Function to build a Camera (because we are using builder design patter)
         * @return The build Camera
         */

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

                if (!isZero(camera.vRight.dotProduct(camera.vTo)))
                    throw new IllegalArgumentException("To and up Vectors not orthogonal");
                camera.vRight = camera.vTo.crossProduct(camera.vUp).normalize();

            return (Camera) camera.clone();


        }
    }
}
