package renderer;
import primitives.*;
import geometries.*;

import java.util.LinkedList;
import java.util.List;


public class BlackBoard{


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
     * Function to create a grid around a Point
     * @param pixelCenter Center of the pixel that we create a grid around
     * @return List of Points on the grid (using jitter)
     */

    public List<Point> createGrid(Point pixelCenter) {
        List<Point> pointList=new LinkedList<>();
        Point topLeft=(pixelCenter.add(new Vector(-pixelWidth/2,pixelHeight/2,0)));


        for(int i=0;i<gridSize;i++) {
            for (int j=0;j<gridSize;j++) {
                if(i==0&&j==0)
                    pointList.add(topLeft);
                else
                    pointList.add(topLeft.add(new Vector(i*(pixelWidth/gridSize)/*+(Util.random(0, (pixelWidth / gridSize) / 10))*/, j*(-pixelHeight/gridSize), 0)));
            }
        }

        return pointList;
    }

}