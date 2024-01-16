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
        testGeometries=new Geometries(new Cylinder(1,rayHelper,1)
                ,new Plane(pointHelper,vectorHelper),
                new Polygon(pointHelper,pointHelper1,pointHelper2,pointHelper3),
                new Sphere(pointHelper,1),
                new Triangle(pointHelper,pointHelper1,pointHelper2)
                , new Tube(rayHelper,1));
        assertNull(testGeometries.findIntersections(ray),
                "failed no shape intersects test find intersections");


    }
}