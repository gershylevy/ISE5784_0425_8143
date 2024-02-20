package renderer;

import geometries.Intersectable;
import geometries.Plane;
import geometries.Triangle;
import primitives.*;
import scene.*;
import java.util.List;
import lighting.*;
import geometries.Intersectable.GeoPoint;
import static primitives.Util.*;

/**
 * Class to calculate a Ray tracer
 */

public class SimpleRayTracer extends RayTracerBase {

    /**
     * Double for moving the head of the Ray for shading
     */

    private static final double DELTA = 0.1;

    /**
     * Int to stop in recursion
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10;

    /**
     * Int to stop in recursion
     */
    private static final double MIN_CALC_COLOR_K = 0.001;

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
        List<Intersectable.GeoPoint> pointList = scene.geometries.findGeoIntersections(ray);
        if(pointList == null){return scene.background;}
        return calcColor(ray.findClosestGeoPoint(pointList), ray);
    }


    /**
     * Calculates the Color of a Shape using a Ray
     * @param intersection Intersection Point and Shape to find with Ray
     * @param ray Ray to find intersection with Shape
     * @return Color of Shape at the intersection Point
     */
    private Color calcColor(Intersectable.GeoPoint intersection, Ray ray) {
        return scene.ambientLight.getIntensity()
                .add(calcLocalEffects(intersection, ray));
    }

    /**
     * Function to calculate Phong model
     * @param gp Intersection Point and Shape with Ray
     * @param ray Ray to find intersection with Shape
     * @return Reflection Color
     */
    private Color calcLocalEffects(Intersectable.GeoPoint gp, Ray ray) {
        // +iE
        Color color= gp.geometry.getEmission();
        Vector n = gp.geometry.getNormal(gp.point);
        Vector v = ray.direction;
        double nv= alignZero(n.dotProduct(v));
        if (nv== 0)
            return color;
        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource: scene.lights) {
            Vector l = lightSource.getL(gp.point);
            //lI*n
            double nl= alignZero(n.dotProduct(l));
            if ((nl* nv> 0)&& unshaded(gp,lightSource, l, n,nl)) {
                Color iL= lightSource.getIntensity(gp.point);
                color = color.add(iL.scale(calcDiffusive(material, nl)
                        .add(calcSpecular(material, n, l, nl, v))));
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
    private static Double3 calcDiffusive(Material material,double nl) {
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

    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v){

        return material.kS.scale(
                Math.pow(
                        Math.max(0, v.scale(-1).dotProduct(l.subtract(n.scale(nl*2)))),material.shininess));
    }

    /**
     * Function to check if a Geometry has shade
     * @param gp Shape that we are making the shadow with
     * @param l Vector from Light source to Point
     * @param n Vector normal
     * @return If the shape is unshaded
     */

 /*   private boolean unshaded(Intersectable.GeoPoint gp , Vector l, Vector n) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector DeltaVector = n.scale(DELTA);
        Point point = gp.point.add(DeltaVector);
        Ray ray = new Ray(point, lightDirection);
        List<Intersectable.GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        // Flat geometry can't self-intersect
        if (gp.geometry instanceof Triangle || gp.geometry instanceof Plane)
            if(intersections!=null)
                intersections.remove(gp);

        if(intersections==null)
            return true;

        return intersections.isEmpty();

    }
*/

    /**
     * Function to see if a Shape is shaded
     * @param gp GeoPoint to check if there is shade on
     * @param light Light that shade will come from
     * @param l Vector from Light to Shape
     * @param n Normal of the Shape
     * @param nl Dot product of n and l
     * @return If the Shape is unshaded
     */


    private boolean unshaded(GeoPoint gp, LightSource light,
                             Vector l, Vector n, double nl) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector deltaVector = n.scale(nl < 0 ? DELTA : -DELTA);
        Point point = gp.point.add(deltaVector);
        Ray ray = new Ray(point, lightDirection);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray,light.getDistance(gp.point));
        if (intersections == null) return true;

        for(int i=0;i<intersections.size();i++)
            if(intersections.get(i).point.distance(gp.point)<light.getDistance(gp.point))
                return false;
        return true;

    }

}