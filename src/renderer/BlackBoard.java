package renderer;
import primitives.*;
import geometries.*;


public class BlackBoard{

    public double gridWidth;
    public double gridHeight;
    public int pixelXIndex;
    public int pixelYIndex;
    Camera c1;

    public BlackBoard setCamera(Camera camera){
        c1=camera;
        return this;
    }


    public BlackBoard setX(int x){
        this.pixelXIndex=x;
        return this;
    }

    public BlackBoard setY(int Y){
        this.pixelYIndex=Y;
        return this;
    }

    public BlackBoard setSize(double size){
        this.gridHeight=size;
        this.gridWidth=size;
        return this;
    }

    public BlackBoard setGridHeight(double gridHeight) {
        this.gridHeight = gridHeight;
        return this;
    }

    public BlackBoard setGridWidth(double gridWidth) {
        this.gridWidth = gridWidth;
        return this;
    }

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