package lighting;


import primitives.*;

/**
 * Class to implement DirectionalLight
 */

public class DirectionalLight extends Light implements LightSource{

    /**
     * Direction of the Light
     */
    private Vector direction;

    /**
     * Constructor for DirectionalLight
     * @param intensity Our new intensity
     * @param direction Our new direction
     */

    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction=direction;
    }

    @Override
    public Color getIntensity(Point point) {
        return this.intensity;
    }

    @Override
    public Vector getL(Point p) {
        return this.direction;
    }
}
