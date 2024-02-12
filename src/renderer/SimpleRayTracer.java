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
                //color = color.add(iL.scale(calcDiffusive(mat.kD, nl).add(calcSpecular(mat.kS, n, l, nl, v))));
            }
        }
        return color;
    }

    // Calculate diffuse reflection with material's diffuse reflection coefficient (kd) and dot product (N · L)
    public static Color calcDiffusive(Double3 kd, double dotProduct) {
        // Ensure dot product is clamped between 0 and 1
        double cosTheta = Math.max(0, Math.min(1, dotProduct));

        // Calculate diffuse reflection using material's diffuse reflection coefficient (kd) and dot product
        int red = (int) (255 * kd.d1 * cosTheta);
        int green = (int) (255 * kd.d2 * cosTheta);
        int blue = (int) (255 * kd.d3 * cosTheta);

        // Clamp values between 0 and 255
        red = Math.max(0, Math.min(255, red));
        green = Math.max(0, Math.min(255, green));
        blue = Math.max(0, Math.min(255, blue));

        return new Color(red, green, blue);
    }



    // Calculate specular reflection using Phong reflection model
    public static Color calcSpecular(Double3 ks, Vector shininess, Vector dotProductNL, double viewDirection,Vector vector) {
       return new Color(java.awt.Color.YELLOW);
    }
}