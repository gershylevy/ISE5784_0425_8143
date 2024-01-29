package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.*;
import java.util.List;

public class SimpleRayTracer extends RayTracerBase {

    public SimpleRayTracer(Scene newScene) {
        super(newScene);
    }

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
