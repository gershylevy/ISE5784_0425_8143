package lighting;

import primitives.*;

/**
 * Class to implement a PointLight
 */

public class PointLight extends Light implements LightSource {

    /**
     * Position of the Point
     */
    Point position;

    /**
     * Narrow beam
     */
    protected double NarrowBeam=1;

    /**
     * Setter for Narrow beam
     * @param narrowBeam New Narrow beam val
     */
    public PointLight setNarrowBeam(double narrowBeam) {
        NarrowBeam = narrowBeam;
        return this;
    }

    /**
     * Factors for attenuation with distance
     */

    private double kC=1.0,kL=0.0,kQ=0.0;

    /**
     * Constructor for PointLight
     * @param newIntensity Our new intensity
     * @param newPosition Our new position
     */

    public PointLight(Color newIntensity,Point newPosition){
        super(newIntensity);
        this.position=newPosition;
    }

    /**
     * Setter for position
     * @param position Our new position
     * @return Return this for chained setters
     */

    public PointLight setPoisition(Point position) {
        this.position = position;
        return this;
    }

    /**
     * Setter for kC
     * @param kC Our new kC
     * @return Return this for chained setters
     */

    public PointLight setkC(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * Setter for kL
     * @param kL Our new kL
     * @return Return this for chained setters
     */

    public PointLight setkL(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * Setter for kQ
     * @param kQ Our new kQ
     * @return Return this for chained setters
     */

    public PointLight setkQ(double kQ) {
        this.kQ = kQ;
        return this;
    }

    @Override
    public Color getIntensity(Point point) {
        return intensity.scale(1/(kC+kL*position.distance(point)+kQ*position.distanceSquared(point)));
    }

    /**
     * Helper function to not go over DRY
     */




    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }
}
