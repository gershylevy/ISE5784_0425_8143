package lighting;

import primitives.*;
import geometries.*;

/**
 * abstract class for all light
 */
public abstract class Light {
    /**
     * intensity of light
     */
    protected Color intensity;

    /**
     * c-tor for Light
     */
    protected Light(Color intense) {this.intensity=intense;}
    /**
     *  Getter for get intensity
     */
    public Color getIntensity() {return intensity;}

}