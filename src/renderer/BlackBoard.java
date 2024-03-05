package renderer;
import primitives.*;
import geometries.*;


public class BlackBoard{

    /**
     * Width of the grid
     */
    public double gridWidth;

    /**
     * Height of the grid
     */
    public double gridHeight;

    /**
     * X index of the pixel
     */
    public int pixelXIndex;

    /**
     * Y index of the pixel
     */
    public int pixelYIndex;
    /**
     * Camera for the blackboard
     */
    Camera c1;

    /**
     * Setter for camera
     */

    public BlackBoard setCamera(Camera camera){
        c1=camera;
        return this;
    }

    /**
     * Setter for X index of the pixel
     */


    public BlackBoard setX(int x){
        this.pixelXIndex=x;
        return this;
    }
    /**
     * Setter for Y index of the pixel
     */

    public BlackBoard setY(int Y){
        this.pixelYIndex=Y;
        return this;
    }

    /**
     * Setter for height and width of the grid
     */

    public BlackBoard setSize(double size){
        this.gridHeight=size;
        this.gridWidth=size;
        return this;
    }

    /**
     * Setter for height of the grid
     */

    public BlackBoard setGridHeight(double gridHeight) {
        this.gridHeight = gridHeight;
        return this;
    }

    /**
     * Setter for width of the grid
     */

    public BlackBoard setGridWidth(double gridWidth) {
        this.gridWidth = gridWidth;
        return this;
    }

    /**
     * Function to create grid
     * @param center Center of the pixel
     * @param pixelWidth Width of the pixel
     * @param pixelHeight Height of the pixel
     */

    public void createGrid(Point center,double pixelWidth,double pixelHeight) throws CloneNotSupportedException {
        double rectWidthSize=(double) pixelWidth/gridWidth;
        Vector jumpRight=new Vector(rectWidthSize,0,0);
        double rectHeightSize= (double) pixelHeight/gridHeight;
        Vector jumpUp=new Vector(0,rectHeightSize,0);
        double Rsum=0;
        double Gsum=0;
        double Bsum=0;

        for(Point i=center.add(new Vector(-pixelWidth/2,0,0));!i.equals(center.add(new Vector(pixelWidth/2,0,0)));i=i.add(jumpRight)){
            for(Point j=center.add(new Vector(-pixelHeight/2,0,0));!i.equals(center.add(new Vector(pixelHeight/2,0,0)));i=i.add(jumpUp)){
                Ray r=new Ray(c1.getP0(),c1.getP0().subtract(i.add(j.subtract(Point.ZERO))));
                Rsum+=c1.getRayTracer().traceRay(r).getColor().getRed();
                Gsum+=c1.getRayTracer().traceRay(r).getColor().getGreen();
                Bsum+=c1.getRayTracer().traceRay(r).getColor().getBlue();
            }
        }
        Rsum/=(gridHeight*gridWidth);
        Gsum/=(gridHeight*gridWidth);
        Bsum/=(gridHeight*gridWidth);

        c1.getImageWriter().writePixel(pixelXIndex,pixelYIndex,new Color(Rsum,Gsum,Bsum));
    }


}