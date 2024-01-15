package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

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
public boolean isInTriangle(Point point){Vector v1 = subtract(vertices.get(0), plane.q);
    Vector v2 = subtract(vertices.get(1), plane.q);
    Vector v3 = subtract(vertices.get(2), plane.q);

    Vector n1 = normalize(v1.crossProduct(v2));
    Vector n2 = normalize(v2.crossProduct(v3));
    Vector n3 = normalize(v3.crossProduct(v1);
  double a= plane.normal.dotProduct(n1);
    double b= plane.normal.dotProduct(n2);
    double c= plane.normal.dotProduct(n3);
  if (a>0&b>0&c>0)
      return true;
    return a < 0 & b < 0 & c < 0;
}
    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> pointList = new ArrayList<>(1);
         pointList = plane.findIntersections(ray);
        // check if in triangle
        if (this.isInTriangle(suspectIntersections){
            pointList.add(suspectIntersections);
            return pointList;}
        return null;
    }
}
