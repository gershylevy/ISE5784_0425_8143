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

    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> pointList;
         pointList = plane.findIntersections(ray);
         if (pointList==null) return null;

         Point point = pointList.get(0);
        // check if in triangle
        Vector v1 = vertices.get(0).subtract(ray.head);
        Vector v2 = vertices.get(1).subtract(ray.head);
        Vector v3 = vertices.get(2).subtract(ray.head);

        Vector n1 = v1.crossProduct(v2).normalize();
        Vector n2 = v2.crossProduct(v3).normalize();
        Vector n3 = v3.crossProduct(v1).normalize();

        double a= alignZero(ray.direction.dotProduct(n1));
        double b= alignZero(ray.direction.dotProduct(n2));
        double c= alignZero(ray.direction.dotProduct(n3));
        if(a<0&&b<0&&c<0 || a>0&&b>0&&c>0)
            return pointList;

        return null;
    }
}
