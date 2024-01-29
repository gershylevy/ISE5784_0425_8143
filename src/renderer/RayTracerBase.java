package renderer;

import primitives.*;
import scene.*;

public abstract class RayTracerBase {

    protected Scene scene;

    public RayTracerBase(Scene newScene) {
        this.scene=newScene;
    }

    public abstract Color traceRay(Ray ray);
}
