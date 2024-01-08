package geometries;

import primitives.Point;

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

}
