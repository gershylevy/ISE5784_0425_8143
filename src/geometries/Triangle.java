package geometries;

import primitives.Point;
import primitives.Point.*;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;
import java.util.ArrayList;
import java.util.List;

/**
 *Triangle class represents two-dimensional triangle in 3D Cartesian coordinate
 */
public class Triangle extends Polygon {
    /**
     * constructor initiating three points of triangle
     * @param point1 first point
     * @param point2 second point
     * @param point3 third point
     */
    public Triangle(Point point1,Point point2, Point point3){
        super(point1,point2,point3);
    }

    /*    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray){
        // check with plane first
        List<Point> planeIntersections = plane.findIntersections(ray);
        if (planeIntersections == null)
            return null;

        double a1 = alignZero(ray.direction.dotProduct(vertices.getFirst().subtract(ray.head).crossProduct(vertices.get(1).subtract(ray.head))));
        double a2 = alignZero(ray.direction.dotProduct(vertices.get(1).subtract(ray.head).crossProduct(vertices.get(2).subtract(ray.head))));
        double a3 = alignZero(ray.direction.dotProduct(vertices.get(2).subtract(ray.head).crossProduct(vertices.getFirst().subtract(ray.head))));

        if(isZero(a1) || isZero(a2) || isZero(a3)){
            return null;
        }

        if((a1 > 0 && a2 > 0 && a3 > 0) || (a1 < 0 && a2 < 0 && a3 < 0)){
            return List.of(new GeoPoint(this, planeIntersections.getFirst()));
        }

        return null;
    }
     */

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double max){
        // check with plane first
        List<Point> planeIntersections = plane.findIntersections(ray);
        if (planeIntersections == null)
            return null;

        double a1 = alignZero(ray.direction.dotProduct(vertices.getFirst().subtract(ray.head).crossProduct(vertices.get(1).subtract(ray.head))));
        double a2 = alignZero(ray.direction.dotProduct(vertices.get(1).subtract(ray.head).crossProduct(vertices.get(2).subtract(ray.head))));
        double a3 = alignZero(ray.direction.dotProduct(vertices.get(2).subtract(ray.head).crossProduct(vertices.getFirst().subtract(ray.head))));

        if(isZero(a1) || isZero(a2) || isZero(a3)){
            return null;
        }

        if((a1 > 0 && a2 > 0 && a3 > 0) || (a1 < 0 && a2 < 0 && a3 < 0)){
            if(planeIntersections.getFirst().distance(ray.head)>max)
                return null;
            return List.of(new GeoPoint(this, planeIntersections.getFirst()));
        }

        return null;
    }


}
