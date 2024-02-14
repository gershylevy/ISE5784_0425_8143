package geometries;

import primitives.Double3;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;
import java.util.List;
import java.util.ArrayList;
import static java.lang.Math.sqrt;

/**
 * Sphere class represents sphere in 3D Cartesian coordinate
 */
public class Sphere extends RadialGeometry{
    /**
     * the center of the sphere
     */
    private final Point center;

    /**
     * constructor initializing value if radius with a new one as well as
     * center
     * @param newCenter the new center
     * @param newRadius the new radius
     */
    public Sphere(Point newCenter, double newRadius){
        super(newRadius);
        this.center=newCenter;
    }

    @Override
    public Vector getNormal(Point val){
        // Calculate the vector from the center to the point on the surface
        Vector vectorToSurface = val.subtract(center);
        return vectorToSurface.normalize();
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray){
        Point rayHead = ray.head;
        Vector v = ray.direction;
        if(rayHead.equals(center)) {
            return List.of(new GeoPoint(this, ray.getPoint(radius)));
        }
        Vector u = center.subtract(rayHead);
        double tm = alignZero(v.dotProduct(u));
        if(Math.sqrt(alignZero(u.lengthSquared()-tm*tm))>=radius){
            return null;
        }

        double th = Math.sqrt(alignZero(radius*radius-alignZero(u.lengthSquared()-tm*tm)));



        if(alignZero(tm + th) <= 0 && alignZero(tm - th) <= 0){
            return null;
        }
        else if (alignZero(tm + th) > 0 && alignZero(tm - th) <= 0){
            return List.of(new GeoPoint(this,ray.getPoint(alignZero(tm + th))));
        }
        else if(alignZero(tm - th) > 0 && alignZero(tm + th) <= 0){
            return List.of(new GeoPoint(this,ray.getPoint(alignZero(tm - th))));
        }
        else if(alignZero(tm - th)>0 && alignZero(tm + th) > 0){
            return List.of(new GeoPoint(this,ray.getPoint(alignZero(tm + th))), new GeoPoint(this, ray.getPoint(alignZero(tm - th))));
        }
        return null;
    }
}
