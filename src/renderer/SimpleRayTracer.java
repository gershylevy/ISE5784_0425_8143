package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.*;
import java.util.List;

/**
 * Class to calculate a Ray tracer
 */

public class SimpleRayTracer extends RayTracerBase {

    /**
     * C-tor for simple Ray tracer
     * @param newScene The new Scene
     */

    public SimpleRayTracer(Scene newScene) {
        super(newScene);
    }


    @Override
    public Color traceRay(Ray ray) {
        List<Point> scenePoints
                =scene.geometries.findIntersections(ray);
        if (scenePoints==null)
            return scene.background;
        Point closePoint= ray.findClosestPoint(scenePoints);
        return this.calcColor(closePoint);
    }
    public Color calcColor(Point point){return scene.ambientLight.getIntensity();}
}
