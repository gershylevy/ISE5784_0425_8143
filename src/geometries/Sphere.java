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
    public List<Point> findIntersections(Ray ray) {


        if(this.center.equals(ray.head)) {
            Vector v= (ray.direction.scale(this.radius));
            Point p=this.center.add(v);
            final var pointList=List.of(p);
            return pointList;
        }

        Vector u = this.center.subtract(ray.head);
        double tm = ray.direction.dotProduct(u);
        if (alignZero(u.lengthSquared() - tm * tm) < 0)
            return null;
        double d = sqrt(u.lengthSquared() - (tm * tm));

        if(d>=this.radius)
            return null;
        double th=sqrt(this.radius*this.radius-d*d);
        double t2=alignZero(tm+th);
        double t1=alignZero(tm-th);

        int length;
        if(t1>0&&t2>0) {
            final var pointList=List.of(ray.getPoint(t1),ray.getPoint(t2));
            return pointList;

        }
        else {
            if (t1 > 0){
                final var pointList=List.of(ray.getPoint(t1));
                return pointList;

            }

            if(t2>0){
                final var pointList=List.of(ray.getPoint(t2));
                return pointList;

            }

            if(t1<=0&&t2<=0){
                return null;
            }

        }
        return null;
    }
}
