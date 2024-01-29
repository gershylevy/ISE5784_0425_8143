package lighting;

import geometries.*;
import primitives.*;
import renderer.*;


/**
 * Class to represent Ambient Lighting
 */

public class AmbientLight {

    /**
     * Intensity of the Lighting on Point
     */

    private final Color intensity;

    /**
     * Constructor for Ambient Light
     * @param ia Intensity of the Ambient Light source
     * @param ka Ambient Light coefficient, a constant factor that determines the strength of the Ambient Light (Double3)
     */

    public AmbientLight(Color ia,Double3 ka){
        this.intensity=new Color(ia.scale(ka).getColor());
    }

    /**
     * Constructor for Ambient Light
     * @param ia Intensity of the Ambient Light source
     * @param ka Ambient Light coefficient, a constant factor that determines the strength of the Ambient Light (Double)
     */

    public AmbientLight(Color ia, double ka){
        this.intensity=new Color(ia.scale(ka).getColor());
    }

    /**
     * Initializing the Color Black
     */

    public static AmbientLight NONE=new AmbientLight(Color.BLACK,0);

    /**
     * Getter for intensity
     * @return our intensity
     */

    public Color getIntensity() {
        return intensity;
    }
}
