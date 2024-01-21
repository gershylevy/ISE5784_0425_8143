package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    @Test
    void add() {
    }

    @Test
    void findIntersections() {

        // ============ Equivalence Partitions Tests ==============

        // TC01: some intersect



        Geometries testGeometries01=new Geometries(new Plane(new Point(1,0,1),new Point(2,0,1),new Point(3,0,3)),
                new Sphere(new Point(0,-1,0),1),
                new Triangle(new Point (0,0,2),new Point (2,0,2),new Point (-1.5,0,3)));

        Point p01=new Point(0,0,0);
        List<Point> pointList1=testGeometries01.findIntersections(new Ray(new Point(0,-1,0),new Vector(0,1,0)));
        final var expectedResult1=List.of(p01,p01);
        assertEquals(expectedResult1,pointList1,"failed some shapes intersect");

        // =============== Boundary Values Tests ==================

        Geometries testGeometries=new Geometries();
        //TC10: empty collection of geometries

        Ray ray=new Ray(new Point(1,7000,50000),new Vector(1,1,1));
        assertNull(testGeometries.findIntersections(ray),
                "failed empty collection of geometries findIntersections test");

        //TC11: no shape intersects

        Vector vectorHelper=new Vector(0,0,1);
        Point pointHelper=new Point(1,1,1);
        Point pointHelper1=new Point(2,2,2);
        Point pointHelper2=new Point(1.5,3,3);
        Ray rayHelper=new Ray(pointHelper,vectorHelper);
        Geometries testGeometries1=new Geometries(new Plane(pointHelper,vectorHelper), new Sphere(pointHelper,1), new Triangle(pointHelper,pointHelper1,pointHelper2));
        assertNull(testGeometries1.findIntersections(ray),
                "failed no shape intersects test find intersections");


        //TC12: One shape intersects

        Geometries testGeometries2=new Geometries(
                new Plane(new Point(1,0,1),new Point(2,0,1),new Point(3,0,3)),
                new Sphere(new Point(1,1,1),0.5),
                new Triangle(new Point(1,1,1),new Point(2,2,2),new Point(1.5,3,3)));

        Point p1=new Point(0,0,0);
        final var pointList12=testGeometries2.findIntersections(new Ray(new Point(0,-1,0),new Vector(0,1,0)));
        final var expectedResult12=List.of(p1);
        assertEquals(expectedResult12,pointList12,"failed one shape intersects");

        //TC13: All of the shapes intersect

        Geometries testGeometries3=new Geometries(
                new Plane(new Point(1,0,1),new Point(2,0,1),new Point(3,0,3)),
                new Sphere(new Point(0,-1,0),1),
                new Triangle(new Point(0,0,-1),new Point(2,0,2),new Point(-1.5,0,3)));


        final var pointList13=testGeometries3.findIntersections(new Ray(new Point(0,-1,0),new Vector(0,1,0)));
        final var expectedResult13=List.of(p1,p1,p1);
        assertEquals(expectedResult13,pointList13,"failed all shapes intersect");




    }
}