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
                = scene.geometries.findGeoIntersectionsHelper(ray);
        if (scenePoints == null)
            return scene.background;
        return calcColor((ray.findClosestGeoPoint(scenePoints)),ray);
    }


    /**
     * Calculates the Color of a Shape using a Ray
     * @param intersection Intersection Point and Shape to find with Ray
     * @param ray Ray to find intersection with Shape
     * @return Color of Shape at the intersection Point
     */
    private Color calcColor(Intersectable.GeoPoint intersection, Ray ray) {
        return scene.ambientLight.getIntensity()//.add(intersection.geometry.getEmission());
                .add(calcLocalEffects(intersection, ray));
    }

    /**
     * Function to calculate Phong model
     * @param gp Intersection Point and Shape with Ray
     * @param ray Ray to find intersection with Shape
     * @return Reflection Color
     */
    private Color calcLocalEffects(Intersectable.GeoPoint gp, Ray ray) {
        Color color = gp.geometry.getEmission();
        Vector n = gp.geometry.getNormal(gp.point);
        Vector v = ray.direction;
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return color;
        Material mat = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sign(nv)
                Color iL = lightSource.getIntensity(gp.point);
                color = color.add(iL.scale(calcDiffusive(mat, nl).add(calcSpecular(mat, n, l, nl, v))));
            }
        }
        return color;
    }

    /**
     * Calculates diffusion based off of phong model
     * @param material Material of the geometry
     * @param nl Dot product of normal and the Vector connecting the Shape and the Light
     * @return Diffusive reflection change
     */
    private static Double3 calcDiffusive(Material material,double nl)
    {
        return material.kD.scale(Math.abs(nl));
    }

    /**
     * Function to calculate specular reflection
     * @param material Material of the shape
     * @param n Normal of the shape
     * @param l Vector connecting the shape and the light
     * @param nl Dot product of n and l
     * @param v Direction of the ray
     * @return Specular reflection change
     */

    private static Double3 calcSpecular(Material material, Vector n, Vector l, double nl,Vector v) {
        Vector r=l.subtract(n.scale(nl*2));
       Double3 spec=material.kS.scale(Math.max(0,v.scale(-1).dotProduct(r)));

       for(int i=0;i< material.shininess;i++)
           spec=spec.product(spec);

        return spec;
    }
}