package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for Triangles
 */

class TriangleTest {
    //for rounding
    private final double DELTA = 0.000001;

    /**
     * Test method for
     * {@link }
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here - using a quad
        Point p1 = new Point(0, 0, 1);
        Point p2 = new Point(1, 0, 0);
        Point p3 = new Point(0, 1, 0);

        Triangle pol = new Triangle(p1, p2, p3);
        // ensure there are no exceptions
        assertDoesNotThrow(() -> pol.getNormal(p1), "");
        // generate the test result
        Vector result = pol.getNormal(p1);
        // ensure |result| = 1
        assertEquals(1, result.length(), DELTA,
                "triangle's normal is not a unit vector");
        // ensure the result is orthogonal to all the edges
        assertEquals(0, result.dotProduct(p1.subtract(p2)),
                "Normal is not orthogonal to the triangle");
        assertEquals(0, result.dotProduct(p1.subtract(p3)),
                "Normal is not orthogonal to the triangle");
        assertEquals(0, result.dotProduct(p2.subtract(p3)),
                "Normal is not orthogonal to the triangle");
    }


    /**
     * Test method for
     * {@link geometries.Triangle#findIntersections(Ray)}
     */
    public void testFindIntersections(Ray ray){

     // ============ Equivalence Partitions Tests ==============

     //TC01: Ray intersects in Triangle

     Triangle t1=new Triangle(new Point(0,0,1),new Point(0,2,1),new Point(2,0,1));
     Ray r1=new Ray(new Point(0.5,1,0),new Vector(0,0,1));
     List<Point> expectedResult1=t1.findIntersections(r1);

     //Check that we have 1 intersection
     assertEquals(1,expectedResult1.size(),
             "Amount of intersections incorrect for basic Ray-Triangle intersection");

     //Check that the List has expected Points
     assertTrue(expectedResult1.contains(new Point(0.5,1,1)),
             "Intersection Points not as expected for basic Ray-Triangle intersection");


     //TC02: Ray intersects between the continuation of 2 of the Triangles lines

     Ray r2=new Ray(new Point(-1,4,0), new Vector(-1,4,1));
     List<Point> expectedResult2=t1.findIntersections(r2);

     //Check that we have 0 intersections
     assertNull(expectedResult2,
             "Amount of intersections incorrect for Ray intersects between the continuation of 2 of the Triangles lines");


     //TC03: Ray does not intersect Triangle

     Ray r3=new Ray(new Point(-1,1,0),new Vector(-1,1,1));
     List<Point> expectedResult3=t1.findIntersections(r3);

     //Check that we have 0 intersections
     assertNull(expectedResult3,
             "Amount of intersections incorrect for Ray does not intersect Triangle");


     // =============== Boundary Values Tests ==================

     //TC10: Ray intersects continuation of the Triangles line

     Ray r4=new Ray(new Point(-2,4,0),new Vector(0,0,1));
     List<Point> expectedResult4=t1.findIntersections(r4);
     //Check that we have 0 intersections
     assertNull(expectedResult4,
             "Amount of intersections incorrect for Ray intersects continuation of Triangles line");


     //TC11: Ray intersects Triangles Point

     Ray r5=new Ray(new Point(0,2,0),new Vector(0,0,1));
     List<Point> expectedResult5=t1.findIntersections(r5);

     //Check that we have 1 intersection
     assertEquals(1,expectedResult5.size(),
             "Amount of intersections incorrect for Ray intersection with Triangle Point");

     //Check that the List has expected Point
     assertTrue(expectedResult5.contains(new Point(0,2,1)),
             "Intersection Points not as expected for Ray intersection with Triangle Point");


     //TC12: Ray intersects with Triangles line

     Ray r6=new Ray(new Point(0,1,0),new Vector(0,0,1));
     List<Point> expectedResult6=t1.findIntersections(r6);

     //Check that we have 1 intersection
     assertEquals(1,expectedResult6.size(),
             "Amount of intersections incorrect for Ray intersection with Triangle line");

     //Check that the List has expected Point
     assertTrue(expectedResult6.contains(new Point(0,1,1)),
             "Intersection Points not as expected for Ray intersection with Triangle line");

 }
}