package renderer;

import geometries.Intersectable;
import geometries.Plane;
import geometries.*;
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


    /**
     * Int to stop in recursion
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10;

    /** initial recursion k */
    private static final Double3 INITIAL_K = Double3.ONE;

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
//        List<GeoPoint> list = scene.geometries.findGeoIntersections(ray);
//        if(list == null){return scene.background;}
        GeoPoint closestPoint = findClosestIntersection(ray);
        if(closestPoint == null){return scene.background;}
        return calcColor(closestPoint, ray);
    }
    private Intersectable.GeoPoint findClosestIntersection(Ray ray) {
        List<Intersectable.GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections == null) {
            return null;
        }
        return ray.findClosestGeoPoint(intersections);
    }

    /**
     * Calculates the Color of a Shape using a Ray
     * @param gp Intersection Point and Shape to find with Ray
     * @param ray Ray to find intersection with Shape
     * @return Color of Shape at the intersection Point
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray ray,int level, Double3 k) {
        Vector n = gp.geometry.getNormal(gp.point);
        Material material= gp.geometry.getMaterial();
        return calcGlobalEffect(constructRefractedRay(gp.point, ray.direction, n), material.kT, level, k)
                .add(calcGlobalEffect(constructReflectedRay(gp, ray.direction, n), material.kR, level, k));
    }
    /**
     * calculate the global effect of an object
     * @param ray ray
     * @param kx kx test
     * @param level recursion level
     * @param k our k val
     * @return color effect
     */
    private Color calcGlobalEffect(Ray ray, Double3 kx, int level, Double3 k) { Double3 kkx= kx.product(k);
        if (kkx.lowerThan(MIN_CALC_COLOR_K)) {
            return Color.BLACK;
        }
        GeoPoint gp= findClosestIntersection(ray);
        return (gp == null ? scene.background: calcColor(gp, ray,level -1, kkx)) .scale(kx);
    }


    private Color calcColor(GeoPoint gp, Ray ray) { return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.ambientLight.getIntensity()); }
    /**
     * calculate color at given point using phong (kA*iA(iE + sum((kD*abs(lI*n)+kS*(max(0, -v*r))^nSh)*iLi)))
     * @param gp point to calculate at
     * @param ray to help with phong
     * @return Color at point
     */
    private Color calcColor(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(gp, ray, k);
        return 1 == level ? color : color.add(calcGlobalEffects(gp, ray, level, k));
    }


    private Ray constructRefractedRay(Point point, Vector v, Vector n) {
        double vn = v.dotProduct(n);
        if (isZero(alignZero(vn))) {
            return null;
        }
        return new Ray(point, n, v);
    }

    private Ray constructReflectedRay(GeoPoint gp, Vector v, Vector n) {
        //r = v - 2.(v.n).n
        double vn = v.dotProduct(n);

        if (vn == 0) {
            return null;
        }

        Vector r = v.subtract(n.scale(2 * vn));
        return new Ray(gp.point,n,r);
    }

    private Double3 transparency(LightSource lightSource, Vector l, Vector n, GeoPoint gp) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Point point = gp.point;
        Ray lightRay = new Ray(point, n, lightDirection);

        double maxdistance = lightSource.getDistance(point);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, maxdistance);

        if (intersections == null)
            return Double3.ONE;

        Double3 ktr = Double3.ONE;
//        loop over intersections and for each intersection which is closer to the
//        point than the light source multiply ktr by 𝒌𝑻 of its geometry.
//        Performance:
//        if you get close to 0 –it’s time to get out( return 0)
        for (var geo : intersections) {
            ktr = ktr.product(geo.geometry.getMaterial().kT);
            if (ktr.lowerThan(MIN_CALC_COLOR_K)) {
                return Double3.ZERO;
            }
        }
        return ktr;
    }

    /**
     * Function to calculate Phong model
     * @param gp Intersection Point and Shape with Ray
     * @param ray Ray to find intersection with Shape
     * @return Reflection Color
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray, Double3 k) {
        //less access time
        Geometry geometry = gp.geometry;
        Point point = gp.point;
        Color intensity;
        // +iE
        Color color = geometry.getEmission();
        Vector n = geometry.getNormal(gp.point);
        Vector v = ray.direction.normalize();
        double nv= alignZero(n.dotProduct(v));
        if (nv== 0)
            return color;
        Material material = geometry.getMaterial();
        //sum((kD*abs(lI*n)+kS*(max(0, -v*r))^nSh)*iLi)
        for (LightSource lightSource: scene.lights) {
            //less access time
            Vector l = lightSource.getL(point);
            //lI*n
            double nl= alignZero(n.dotProduct(l));
            if ((nl* nv> 0) && unshaded(gp, l, n, lightSource)){
                // sign(nl) == sign(nv)
                Double3 ktr = transparency(lightSource, l, n, gp);
                if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {
                    Color iL = lightSource.getIntensity(point).scale(ktr);
                    // +iLi * (kD*abs(lI*n)+kS*(max(0, -v*r))^nSh)
                    color = color.add(iL.scale(calcDiffusive(material, nl)
                            .add(calcSpecular(material, n, l, nl, v))));
                }
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



    private boolean unshaded(GeoPoint gp, Vector l, Vector n, LightSource lightSource) {
        if(gp.geometry.getMaterial().kT == Double3.ZERO){
            return true;
        }
        Vector lightDirection = l.scale(-1); // from point to light source


        Ray ray= new Ray(gp.point, n, lightDirection);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray, lightSource.getDistance(ray.head));
        if (intersections == null){
            return true;
        }

        // check if the transparant level is too small to matter
        for (var item : intersections){
            if (item.geometry.getMaterial().kT.lowerThan(MIN_CALC_COLOR_K)){
                return false;
            }
        }

        return true;
    }


}