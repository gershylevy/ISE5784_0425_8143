package renderer;

import primitives.*;
import scene.*;

/**
 * Father Class simple Ray tracer
 */

public abstract class RayTracerBase {

    /**
     * The Scene we are using
     */

    protected Scene scene;

    /**
     * C-tor for Ray tracer base
     * @param newScene Our new Scene
     */
    public RayTracerBase(Scene newScene) {
        this.scene=newScene;
    }

    /**
     * Function to find Color from a Ray
     * @param ray Ray to find Color at the end
     * @return The Color at the end
     */
    public abstract Color traceRay(Ray ray);
}
