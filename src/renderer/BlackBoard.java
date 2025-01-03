package renderer;
import primitives.*;
import geometries.*;

import java.util.LinkedList;
import java.util.List;


public class BlackBoard{

    /**
     * Right facing Vector of the Camera
     */

    public Vector vRight;

    /**
     * Up facing Vector of the Camera
     */

    public Vector vUp;


    /**
     * Height of the pixel
     */

    public double pixelHeight=0;

    /**
     * Width of the pixel
     */

    public double pixelWidth=0;

    /**
     * Size of grid to create
     */

    public int gridSize=1;

    /**
     * Getter for pixelHeight
     * @return The pixel height
     */


    public double getPixelHeight() {
         return pixelHeight;
    }

    /**
     * Getter for pixelWidth
     * @return The pixel width
     */

    public double getPixelWidth() {
        return pixelWidth;
    }


    /**
     * Getter for gridSize
     * @return The grid size
     */

    public int getGridSize() {
        return gridSize;
    }

    /**
     * Setter for gridSize
     */

    public BlackBoard setGridSize(int gridSize) {
        this.gridSize = gridSize;
        return this;
    }

    /**
     * Setter for pixelHeight
     */

    public BlackBoard setPixelHeight(double pixelHeight) {
        this.pixelHeight = pixelHeight;
        return this;
    }

    /**
     * Setter for pixelWidth
     */

    public BlackBoard setPixelWidth(double pixelWidth) {
        this.pixelWidth = pixelWidth;
        return this;
    }

    /**
     * Setter for vRight
     */

    public BlackBoard setvRight(Vector vRight) {
        this.vRight = vRight;
        return this;
    }

    /**
     * Setter for vUp
     */


    public BlackBoard setvUp(Vector vUp) {
        this.vUp = vUp;
        return this;
    }

    /**
     * Getter for vRight
     */

    public Vector getvRight() {
        return vRight;
    }

    /**
     * Getter for vUp
     */

    public Vector getvUp() {
        return vUp;
    }

    /**
     * Function to create a grid around a Point
     * @param pixelCenter Center of the pixel that we create a grid around
     * @return List of Points on the grid (using jitter)
     */

    public List<Point> createGrid(Point pixelCenter) {
        List<Point> pointList=new LinkedList<>();
        Point topLeft=(pixelCenter.add(vRight.scale(-0.5*pixelWidth).add(vUp.scale(0.5*pixelHeight))));
        double x=(pixelWidth / gridSize) / 10;


        for(int i=0;i<=gridSize;i++) {
            for (int j=0;j<=gridSize;j++) {
                if(i==0&&j==0)
                    pointList.add(topLeft);
                else if (j==0) {
                    pointList.add(topLeft.add(vRight.scale(i*(pixelWidth / gridSize)+(Util.random(-x, x)))));
                }
                else if(i==0){
                    pointList.add(vUp.scale(j*(-pixelHeight / gridSize)));
                }

                if(i!=0&&j!=0){
                    //pointList.add(topLeft.add(new Vector(i * (pixelWidth / gridSize)+(Util.random(-x, x)), j * (-pixelHeight / gridSize), 0)));
                    pointList.add(topLeft.add(vRight.scale(i * (pixelWidth / gridSize)+(Util.random(-x, x))).add(vUp.scale(j * (-pixelHeight / gridSize)))));
                }
            }
        }

        return pointList;
    }

    /**
     * Function to find the corners of the pixel
     * @param pixelCenter Center of the pixel
     * @param level Current level of recursion
     * @param maxlevel Max level of recursion
     * @return List of Points with the corners
     */


    public List<Point> corners(Point pixelCenter,int level,int maxlevel){

        int temp=maxlevel-level+1;
        List<Point> pointList=new LinkedList<>();
        Point topLeft=(pixelCenter.add(vRight.scale(-0.5*(pixelWidth/temp)).add(vUp.scale(0.5*(pixelHeight/temp)))));
        Point topRight=topLeft.add(vRight.scale(pixelWidth/temp));
        Point bottomRight=topRight.add(vUp.scale(-pixelHeight/temp));
        Point bottomLeft=bottomRight.add(vRight.scale(-pixelWidth/temp));

        pointList.add(topLeft);
        pointList.add(topRight);
        pointList.add(bottomRight);
        pointList.add(bottomLeft);

        return pointList;
    }

    /**
     * Function to find the centers of the 4 subPixels
     * @param center Center of the pixel
     * @param level Current level of recursion
     * @param maxlevel Max level of recursion
     * @return A list of Points with the centers of the subPixels
     */

    public List<Point> centers(Point center,int level,int maxlevel){
        int temp=maxlevel-level+1;
        List<Point> pointList=new LinkedList<>();
        Point topLeftCenter=(center.add(vRight.scale(-0.25*(pixelWidth/temp)).add(vUp.scale(0.25*(pixelHeight/temp)))));
        Point topRightCenter=topLeftCenter.add(vRight.scale(0.5*(pixelWidth/temp)));
        Point bottomRightCenter=topRightCenter.add(vUp.scale(-0.5*(pixelHeight/temp)));
        Point bottomLeftCenter=bottomRightCenter.add(vRight.scale(-0.5*(pixelWidth/temp)));
        pointList.add(topLeftCenter);
        pointList.add(topRightCenter);
        pointList.add(bottomLeftCenter);
        pointList.add(bottomRightCenter);
        return pointList;
    }

}