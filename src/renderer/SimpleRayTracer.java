package renderer;

import geometries.Intersectable;
import primitives.*;
import scene.*;
import java.util.List;
import lighting.*;

import static primitives.Util.*;

/**
 * Class to calculate a Ray tracer
 */

public class SimpleRayTracer extends RayTracerBase {

    /**
     * C-tor for simple Ray tracer
     *
     * @param newScene The new Scene
     */

    public SimpleRayTracer(Scene newScene) {
        super(newScene);
    }


    @Override
    public Color traceRay(Ray ray) {
        List<Intersectable.GeoPoint> scenePoints
                = scene.geometries.findGeoIntersections(ray);
        if (scenePoints == null)
            return scene.background;
        Intersectable.GeoPoint closePoint = ray.findClosestGeoPoint(scenePoints);
        return this.calcColor(closePoint, ray);
    }

    private Color calcColor(Intersectable.GeoPoint intersection, Ray ray) {
        return scene.ambientLight.getIntensity().add(intersection.geometry.getEmission());
    }
}

 /*   private Color calcColor(Intersectable.GeoPoint intersection, Ray ray) {
        return scene.ambientLight.getIntensity()
                .add(calcLocalEffects(intersection, ray));
    }




    private Color calcLocalEffects(Intersectable.GeoPoint gp, Ray ray) {
        Vector n = gp.geometry.getNormal(gp.point);
        Vector v = ray.direction;
        double nv = alignZero(n.dotProduct(v));
        Color color = gp.geometry.getEmission();
        if (nv == 0)
            return color;
        Material mat = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color iL = lightSource.getIntensity(gp.point);
                color = color.add(iL.scale(calcDiffusive(mat, nl).add(calcSpecular(mat, n, l, nl, v))));
            }
        }
        return color;

    }

    public static Double3 calcDiffusive(Material material,double d) {
        return material.kD.scale(d);
    }

    public static Double3 calcSpecular(Material material,Vector n,Vector l,double nl,Vector v){
        return material.kS.scale(Math.pow(Math.max(0,-n.dotProduct(l)),nl));
    }
}
*/