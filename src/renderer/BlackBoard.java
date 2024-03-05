package renderer;
import primitives.*;
import geometries.*;

import static primitives.Util.isZero;

public class BlackBoard {

    public double gridWidth;
    public double gridHeight;
    public Camera c1;


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

    public void createGrid(Point center,double pixelWidth,double pixelHeight){
        double rectWidthSize=(double) pixelWidth/gridWidth;
        Vector jumpRight=new Vector(rectWidthSize,0,0);
        double rectHeightSize= (double) pixelHeight/gridHeight;
        Vector jumpUp=new Vector(0,rectHeightSize,0);

        for(Point i=center.add(new Vector(-pixelWidth/2,0,0));!i.equals(center.add(new Vector(pixelWidth/2,0,0)));i=i.add(jumpRight)){
            for(Point j=center.add(new Vector(-pixelHeight/2,0,0));!i.equals(center.add(new Vector(pixelHeight/2,0,0)));i=i.add(jumpUp)){
                Ray r=new Ray(c1.getP0(),c1.getP0().subtract(i.add(j.subtract(Point.ZERO))));
            }

        }

    }


}