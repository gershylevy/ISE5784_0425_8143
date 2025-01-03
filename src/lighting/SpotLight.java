package lighting;

import primitives.*;

/**
 * Class to implement SpotLight
 */

public class SpotLight extends PointLight {

    /**
     * Direction of the Light
     */

    private Vector direction;

    /**
     * Constructor for SpotLight
     * @param intensity Our new intensity
     * @param newPosition Our new Position
     * @param direction Our new direction
     */

    public SpotLight(Color intensity,Point newPosition,Vector direction) {
        super(intensity,newPosition);
        this.direction = direction;
    }

    /**
     * Setter for direction
     * @param direction Our new direction
     * @return Return this for chained setters
     */

    public PointLight setDirection(Vector direction) {
        this.direction = direction;
        return this;
    }


    @Override
    public PointLight setkC(double kC) {
        super.setkC(kC);
        return this;
    }

    @Override
    public PointLight setkL(double kL) {
        super.setkL(kL);
        return this;
    }


    @Override
    public PointLight setkQ(double kQ) {
        super.setkQ(kQ);
        return this;
    }


    @Override
    public Color getIntensity(Point point) {
        double proj=direction.normalize().dotProduct(getL(point));
        double factor=Math.max(0,proj);
        factor=Math.pow(factor,NarrowBeam);
        return super.getIntensity(point).scale(factor);
    }

}