package renderer;

import primitives.*;
import geometries.*;

import java.awt.image.BufferedImage;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.MissingResourceException;
import java.util.List;
import java.util.stream.*;

import static primitives.Util.isZero;

/**
 * Camera class to represent a Camera in a Scene
 */

public class Camera implements Cloneable {


    private static int threadsCount = 0; // -2 auto, -1 range/stream, 0 no threads, 1+ number of threads
    private static final int SPARE_THREADS = 2; // Spare threads if trying to use all the cores
    private static double printInterval = 0; // printing progress percentage interval

    /** Pixel manager for supporting:
     * <ul>
     * <li>multi-threading</li>
     * <li>debug print of progress percentage in Console window/tab</li>
     * <ul>
     */
    private PixelManager pixelManager;

    /**
     * Blackboard of the Camera
     */

    private BlackBoard blackBoard=new BlackBoard();

    /**
     * Switch to activate MP1
     */

    private Boolean isGrid=false;

    /**
     * Switch to activate MP2
     */
    private Boolean isFancy=false;

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
        pixelManager = new PixelManager(nY, nX, printInterval);


        if(threadsCount==0) {
            for (int i = 0; i < nX; i++)
                for (int j = 0; j < nY; j++)
                    this.castRay(nX, nY, i, j);
        }

        else if (threadsCount==1)
            IntStream.range(0,nY).parallel().forEach(i->IntStream.range(0,nX).parallel()
                    .forEach(j-> {
                        try {
                            castRay(nX,nY,j,i);
                        } catch (CloneNotSupportedException e) {
                            throw new RuntimeException(e);
                        }
                    }));

        else  {
            var threads=new LinkedList<Thread>();
            while (threadsCount-- >0)
                threads.add(new Thread(()->{
                    PixelManager.Pixel pixel;
                    while ((pixel= pixelManager.nextPixel())!=null) {
                        try {
                            castRay(nX,nY,pixel.col(), pixel.row());
                        } catch (CloneNotSupportedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }));
            for(var thread:threads) thread.start();
            try {for(var thread:threads) thread.join();} catch (InterruptedException ignore){}

        }
    }



    /**
     * Function to Make Image
     */

    public void writeToImage(){
        this.imageWriter.writeToImage();
    }

    /**
     * Function to cast Ray through the center of a pixel or multiple Rays through a grid on the pixel
     * @param Nx Resolution on the x-axis
     * @param Ny Resolution on the y-axis
     * @param indexI Index on the x-axis
     * @param indexT Index on the y-axis
     */

    private void castRay(int Nx,int Ny,int indexI,int indexT) throws CloneNotSupportedException {
        if(!isGrid&&!isFancy) {
            Ray ray = this.constructRay(Nx, Ny, indexI, indexT);
            this.imageWriter.writePixel(indexI, indexT, rayTracer.traceRay(ray));
        }
        if(isGrid&&!isFancy){
            blackBoard.setPixelWidth((double) this.viewPlaneWidth/Nx).setPixelHeight((double) this.viewPlaneHeight/Ny)
                    .setGridSize(17).setvUp(this.vUp).setvRight(this.vRight);

            Point center=constructRayHelper(Nx,Ny,indexI,indexT);
            List<Point> pointList=blackBoard.createGrid(center);
            Color color1=new Color(0,0,0);

            for (Point p: pointList) {
                Ray r=new Ray(p0, p.subtract(p0));
                Color temp=rayTracer.traceRay(r);
                color1=color1.add(temp);
            }

            color1=color1.reduce((blackBoard.gridSize+1)*(blackBoard.gridSize+1));

            this.imageWriter.writePixel(indexI, indexT, color1);
        }

        if(isFancy) {
            Point center=constructRayHelper(Nx,Ny,indexI,indexT);
            blackBoard.setPixelWidth((double) this.viewPlaneWidth/Nx).setPixelHeight((double) this.viewPlaneHeight/Ny)
                    .setGridSize(17).setvUp(this.vUp).setvRight(this.vRight);
            Color color1 = isFancyHelper(center,(int)(Math.log(blackBoard.gridSize)/Math.log(2)),(int)(Math.log(blackBoard.gridSize)/Math.log(2)));
            this.imageWriter.writePixel(indexI, indexT, color1);
        }
        pixelManager.pixelDone();

    }

    /**
     * Helper function for recursion
     * @param center Center of the square we look at
     * @param level Level of recursion
     * @return The average Color at the pixel
     */


    private Color isFancyHelper(Point center, int level,int maxlevel){

        List<Point> pointList=blackBoard.corners(center,level,maxlevel);
        Color c1=rayTracer.traceRay(new Ray(p0,pointList.get(0).subtract(p0)));
        Color c2=rayTracer.traceRay(new Ray(p0,pointList.get(1).subtract(p0)));
        Color c3=rayTracer.traceRay(new Ray(p0,pointList.get(2).subtract(p0)));
        Color c4=rayTracer.traceRay(new Ray(p0,pointList.get(3).subtract(p0)));

        if(c1.isCloseEquals(c2)&&c1.isCloseEquals(c3)&&c1.isCloseEquals(c4))
            return c1;

        if(level==0) {
            c1=c1.add(c2).add(c3).add(c4);
            return c1.reduce(4);
        }

        else{
            pointList=blackBoard.centers(center,level,maxlevel);
            c1= (isFancyHelper(pointList.get(0),level-1,maxlevel)).add(isFancyHelper(pointList.get(1),level-1,maxlevel).add(isFancyHelper(pointList.get(2),level-1,maxlevel)).add(isFancyHelper(pointList.get(3),level-1,maxlevel)));
            return c1.reduce(4);
        }

    }




    /**
     * Builder class because we are using Builder Design Pattern
     */
    public static class Builder{
        private final Camera camera = new Camera();

        public Builder setMultithreading(int threads) {
            if (threads < -2) throw new IllegalArgumentException("Multithreading must be -2 or higher");if (threads >= -1) threadsCount = threads;
            else { // == -2
                int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
                threadsCount = cores <= 2 ? 1 : cores;
            }
            return this;
        }
        public Builder setDebugPrint(double interval) {
            printInterval = interval;
            return this;
        }

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

        /**
         * Setter on option to use MP1
         */

        public Builder setIsGrid(Boolean temp){
            camera.isGrid=temp;
            return this;
        }

        /**
         * Setter for option to use MP2
         */

        public Builder setIsFancy(Boolean temp){
            camera.isFancy=temp;
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
