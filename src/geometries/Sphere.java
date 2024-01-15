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
            List<Point> pointList = new ArrayList<>(1);
            Vector v= (ray.direction.scale(this.radius));
            Point p=this.center.add(v);
            pointList.add(p);
            return pointList;
        }

        Vector u = this.center.subtract(ray.head);
        double tm = ray.direction.dotProduct(u);
        if (u.lengthSquared() - tm * tm < 0)
            return null;
        double d = sqrt(u.lengthSquared() - (tm * tm));

        if(d>=this.radius)
            return null;
        double th=sqrt(this.radius*this.radius-d*d);
        double t2=tm+th;
        double t1=tm-th;

        int length;
        if(t1>0&&t2>0)
            length=2;
        else {
            if (t1 > 0 || t2 > 0)
                length = 1;
            else
                return null;
        }


        List<Point> pointList = new ArrayList<>(length);

        if(t1>0) {
            Point p1=ray.head.add(ray.direction.scale(t1));
            pointList.add(p1);
        }

        if(t2>0) {
            Point p2=ray.head.add(ray.direction.scale(t2));
            pointList.add(p2);
        }

        return pointList;


    }
}
