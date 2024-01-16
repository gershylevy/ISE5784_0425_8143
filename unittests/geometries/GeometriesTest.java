package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    @Test
    void add() {
    }

    @Test
    void findIntersections() {

        // =============== Boundary Values Tests ==================

        Geometries testGeometries=new Geometries();
        //TC10: empty collection of geometries

        Ray ray=new Ray(new Point(1,7000,50000),new Vector(1,1,1));
        assertNull(testGeometries.findIntersections(ray),
                "failed empty collection of geometries findIntersections test");

        //TC11: no shape intersects

        Vector vectorHelper=new Vector(1,1,1);
        Point pointHelper=new Point(1,1,1);
        Point pointHelper1=new Point(1,1,2);
        Point pointHelper2=new Point(1,1,3);
        Point pointHelper3=new Point(1,1,4);
        Ray rayHelper=new Ray(pointHelper,vectorHelper);
        testGeometries=new Geometries(
                new Plane(pointHelper,vectorHelper),
                new Sphere(pointHelper,1),
                new Triangle(pointHelper,pointHelper1,pointHelper2));
        assertNull(testGeometries.findIntersections(ray),
                "failed no shape intersects test find intersections");


        //TC12: One shape intersects

        testGeometries=new Geometries(
                new Plane(new Point(1,0,1),new Point(2,0,2),new Point(3,0,3)),
                new Sphere(new Point(1,1,1),0.5),
                new Triangle(new Point(1,1,1),new Point(2,2,2),new Point(1.5,3,3)));

        assertEquals(1,testGeometries.findIntersections(new Ray(new Point(0,-1,0),new Vector(0,1,0))).size());

    }
}