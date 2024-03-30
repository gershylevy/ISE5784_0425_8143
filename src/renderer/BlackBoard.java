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

    public double getPixelHeight() {
         return pixelHeight;
    }

    public double getPixelWidth() {
        return pixelWidth;
    }

    public int getGridSize() {
        return gridSize;
    }

    public BlackBoard setGridSize(int gridSize) {
        this.gridSize = gridSize;
        return this;
    }

    public BlackBoard setPixelHeight(double pixelHeight) {
        this.pixelHeight = pixelHeight;
        return this;
    }

    public BlackBoard setPixelWidth(double pixelWidth) {
        this.pixelWidth = pixelWidth;
        return this;
    }

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