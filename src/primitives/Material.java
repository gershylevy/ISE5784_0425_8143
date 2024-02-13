package primitives;

import geometries.*;

/**
 * Class to implement Material of the object
 */

public class Material {

    /**
     * The ratio of reflection of the diffuse term of incoming light
     */
    public Double3 kD = new Double3(0);

    /**
     * The ratio of reflection of the specular term of incoming light
     */

    public Double3 kS = new Double3(0);

    /**
     * The shininess of an object
     */

    public int shininess=0;

    /**
     * Setter for kD (Double3)
     * @param kD Our new kD
     * @return Return this for chained setters
     */

    public Material setkD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * Setter for kS (Double3)
     * @param kS Our new kS
     * @return Return this for chained setters
     */

    public Material setkS(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * Setter for kS (Double)
     * @param d Our new kS
     * @return Return this for chained setters
     */

    public Material setkS(Double d) {
        this.kS = new Double3(d);
        return this;
    }

    /**
     * Setter for kD (Double)
     * @param d Our new kD
     * @return Return this for chained setters
     */

    public Material setkD(Double d) {
        this.kD = new Double3(d);
        return this;
    }

    /**
     * Setter for shininess
     * @param shininess Our new shininess
     * @return Return this for chained setters
     */

    public Material setShininess(int shininess) {
        this.shininess=shininess;
        return this;
    }
}