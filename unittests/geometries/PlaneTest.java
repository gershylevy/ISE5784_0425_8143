package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing planes
 */

class PlaneTest {

    private final double DELTA = 0.000001;


    /**
     * Test method for
     * {@link geometries.Plane#getNormal(Point)}
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Point p1=new Point(0,0,1);
        Point p2=new Point(1,0,0);
        Point p3=new Point(0,1,0);

        Plane p = new Plane(p1,p2,p3);
        // ensure there are no exceptions
        assertDoesNotThrow(() -> p.getNormal(p1), "");
        // generate the test result
        Vector result = p.getNormal(p1);
        // ensure |result| = 1
        assertEquals(1, result.length(), DELTA, "Plane's normal is not a unit vector");
        // ensure the result is orthogonal to all the edges
        assertEquals(0, result.dotProduct(p1.subtract(p2)),
                "Normal is not orthogonal to the Plane");
        assertEquals(0, result.dotProduct(p1.subtract(p3)),
                "Normal is not orthogonal to the Plane");
        assertEquals(0, result.dotProduct(p2.subtract(p3)),
                "Normal is not orthogonal to the Plane");
    }

    /**
     * Test method for
     * {@link Plane#findIntersections(Ray)}
     */

    @Test
    void findIntersectionsTest()
    {
        // ============ Equivalence Partitions Tests ==============

        //TC01: Ray intersects the Plane (non-orthogonal)

        Plane p1=new Plane(new Point(0,0,1),new Point(0,2,1),new Point(2,0,1));
        Ray r1=new Ray(new Point(1,0,0), new Vector(-1,0,1));
        List<Point> expectedResult1=p1.findIntersections(r1);

        //Check that we have 1 intersection
        assertEquals(1,expectedResult1.size(),
                "Amount of intersections incorrect for basic Ray-Plane intersection");

        //Check that the intersection Point is the correct Point
        assertEquals(new Point(0,0,1),expectedResult1.getFirst(),
                "Intersection Points not as expected for basic Ray-Plane intersection");


        //TC02: Ray doesn't intersect with the Plane (non-parallel)

        Ray r2=new Ray(new Point(0,0,2), new Vector(0,1,1));
        List<Point> expectedResult2=p1.findIntersections(r2);

        //Check that we have 0 intersections
        assertNull(expectedResult2,
                "Amount of intersections incorrect for basic Ray-Plane intersection");



        // =============== Boundary Values Tests ==================


        // **** Group: Ray is parallel to the Plane (2 TC)
        //TC10: Ray is included in the Plane

        Ray r3=new Ray(new Point(0,1,1), new Vector(0,-1,0));
        List<Point> expectedResult3=p1.findIntersections(r3);

        //Check that we have 0 intersections
        assertNull(expectedResult3,
                "Amount of intersections incorrect for Ray included in Plane");


        //TC11: Ray is not included in Plane

        Ray r4=new Ray(new Point(0,1,2), new Vector(0,-1,0));
        List<Point> expectedResult4=p1.findIntersections(r4);

        //Check that we have 0 intersections
        assertNull(expectedResult4,
                "Amount of intersections incorrect for Ray not included in Plane");


        //**** Group: Ray is orthogonal to the Plane (3 TC)
        //TC12: Head of Ray is before the Plane

        Ray r5=new Ray(new Point(0,1,0), new Vector(0,0,1));
        List<Point> expectedResult5=p1.findIntersections(r5);

        //Check that we have 1 intersection
        assertEquals(1,expectedResult5.size(),
                "Amount of intersections incorrect for Ray is orthogonal and before");

        //Check that the intersection Point is the correct Point
        assertEquals(new Point(0,1,1),expectedResult5.getFirst(),
                "Intersection Points not as expected for Ray is orthogonal and before");


        //TC13: Head of Ray is on the Plane

        Ray r6=new Ray(new Point(0,1,1), new Vector(0,0,1));
        List<Point> expectedResult6=p1.findIntersections(r6);

        //Check that we have 0 intersections
        assertNull(expectedResult6,
                "Amount of intersections incorrect for Ray orthogonal and on Plane");


        //TC14: Head of the Ray is past the Plane

        Ray r7=new Ray(new Point(0,1,2), new Vector(0,0,1));
        List<Point> expectedResult7=p1.findIntersections(r7);

        //Check that we have 0 intersections
        assertNull(expectedResult7,
                "Amount of intersections incorrect for Ray orthogonal and past Plane");


        //**** Group: Special cases
        //TC15: Ray is not orthogonal or parallel to Plane but begins on it

        Ray r8=new Ray(new Point(0,1,1), new Vector(1,0,1));
        List<Point> expectedResult8=p1.findIntersections(r8);

        //Check that we have 0 intersections
        assertNull(expectedResult8,
                "Amount of intersections incorrect for not orthogonal or parallel to Plane but begins on it");


        //TC16: Ray begins on the Point q of the Plane

        Ray r9=new Ray(new Point(0,0,1), new Vector(1,1,1));
        List<Point> expectedResult9=p1.findIntersections(r9);

        //Check that we have 0 intersections
        assertNull(expectedResult9,
                "Amount of intersections incorrect for Ray begins on q of the Plane");


    }

}