package lighting;

import primitives.*;

/**
 * Interface for all light sources
 */
public interface LightSource {

    /**
     * Function to get intensity at a given Point (returns Color)
     * @param p The given Point to get intensity at
     * @return The intensity at the Point
     */
    public Color getIntensity(Point p);

    /**
     * Function to get L from a given Point (returns Vector)
     * @param p The given Point to get L from
     * @return The L from the Point
     */
    public Vector getL(Point p);
}
