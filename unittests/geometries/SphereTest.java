package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.List;
import static java.lang.Math.sqrt;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Spheres
 */
class SphereTest {

    /**
     * Test method for
     * {@link SphereTest#getNormal()}
     */
    @Test
    void getNormal() {
        // Assuming a sphere centered at (1,1,1) with radius 1
        Sphere sphere = new Sphere(new Point(1, 1, 1), 1);
        // ============ Equivalence Partitions Tests ==============
       //Tc01: testCalculateNormal classic
            Point newPoint = new Point(1, 2, 1);
            assertEquals(new Vector(0, 1 ,0),
                    sphere.getNormal(newPoint),
                    "failed test calculate normal classic sphere");
            //TC02: test getNormal sphere returns normalized vector
            assertEquals(1.0,(sphere.getNormal(newPoint)).length(),
                    "failed test getNormal sphere returns normalized vector");

    }


    /**
     * Test method for
     * {@link Sphere#findIntersections(Ray)}
     */

    @Test
    void findIntersectionsTest() {
        // ============ Equivalence Partitions Tests ==============

        //TC01: Ray passes through Sphere with 2 intersections

        Point center=new Point(0,0,1);
        Sphere s1=new Sphere(center,2);
        Ray r1=new Ray(new Point(0,0,-3),new Vector(0,0,1));
        List<Point> expectedResult1=s1.findIntersections(r1);

        //Check that we have 2 intersections
        assertEquals(2,expectedResult1.size(),
                "Amount of intersections incorrect for basic Ray-Sphere intersection");

        //Check that the List has both expected Points
        assertTrue(expectedResult1.contains(new Point(0,0,3))
                && expectedResult1.contains(new Point(0,0,-1)),
                "Intersection Points not as expected for basic Ray-Sphere intersection");


        //TC02: Ray begins in Sphere (has 1 intersection)

        Ray r2=new Ray(new Point(0,0,0.1),new Vector(0,0,-0.1));
        List<Point> expectedResult2=s1.findIntersections(r2);

        //Check that we have 1 intersection
        assertEquals(1,expectedResult2.size(),
                "Amount of intersections incorrect for 1 Point Ray-Sphere intersection");

        //Check that the List has the expected Point
        assertTrue(expectedResult2.contains(new Point(0,0,-1)),
                "Intersection Points not as expected for 1 Point Ray-Sphere intersection");


        //TC03: Ray begins after Sphere (0 intersections)

        Ray r3=new Ray(new Point(0.5,0,4),new Vector(0.5,0,4));
        List<Point> expectedResult3=s1.findIntersections(r3);

        //Check that we have 0 intersections
        assertNull(expectedResult3,
                "Amount of intersections incorrect for 0 Point Ray-Sphere intersection (Ray after Sphere)");


        //TC04: Ray has no intersections with Sphere

        Ray r4=new Ray(new Point(10,0,0),new Vector(10,10,10));
        List<Point> expectedResult4=s1.findIntersections(r4);

        //Check that we have 0 intersections
        assertNull(expectedResult4,
                "Amount of intersections incorrect for 0 Point Ray-Sphere intersection");


        // =============== Boundary Values Tests ==================

        // **** Group: Ray starts on the Sphere (but does not cross the center) (2 TC)
        // TC10: Ray starts on Sphere and goes inside (1 point)

        Ray r5=new Ray(new Point(0,0,-1),new Vector(1,0,1));
        List<Point> expectedResult5=s1.findIntersections(r5);

        //Check that we have 1 intersection
        assertEquals(1,expectedResult5.size(),
                "Amount of intersections incorrect for Ray starts on Sphere and goes inside (no center)");

        //Check that the List has the expected Point
        assertTrue(expectedResult5.contains(new Point(2,0,1)),
                "Intersection Point not as expected for Ray starts on Sphere and goes inside (no center)" );


        //TC11: Ray starts on Sphere and goes outside (0 points)

        Ray r6=new Ray(new Point(0,0,-1),new Vector(-1,0,-1));
        List<Point> expectedResult6=s1.findIntersections(r6);

        //Check that we have 0 intersection
        assertNull(expectedResult6,
                "Amount of intersections incorrect for Ray starts on Sphere and goes outside (no center)");


        // **** Group: Ray's line goes through the center (6 TC)
        //TC12: Ray starts on Sphere and goes outside (0 points)

        Ray r7=new Ray(new Point(0,0,-1),new Vector(0,0,-1));
        List<Point> expectedResult7=s1.findIntersections(r7);

        //Check that we have 0 intersections
        assertNull(expectedResult7,
                "Amount of intersections incorrect for Ray starts on Sphere and goes outside (on center)");


        //TC13: Ray starts after Sphere

        Ray r8=new Ray(new Point(0,0,-2),new Vector(0,0,-1));
        List<Point> expectedResult8=s1.findIntersections(r8);

        //Check that we have 0 intersections
        assertNull(expectedResult8,
                "Amount of intersections incorrect for Ray starts after Sphere (on center)");


        //TC14: Ray starts in Sphere but after center

        Ray r9=new Ray(new Point(0,0,-0.5),new Vector(0,0,-1));
        List<Point> expectedResult9=s1.findIntersections(r9);

        //Check that we have 1 intersection
        assertEquals(1,expectedResult9.size(),
                "Amount of intersections incorrect for Ray in Sphere but after center");

        //Check that the List has the expected Point
        assertTrue(expectedResult9.contains(new Point(0,0,-1)),
                "Intersection Point not as expected for Ray in Sphere but after center");


        //TC15: Ray starts on center

        Ray r10=new Ray(new Point(0,0,1),new Vector(0,0,-1));
        List<Point> expectedResult10=s1.findIntersections(r10);

        //Check that we have 1 intersection
        assertEquals(1,expectedResult10.size(),
                "Amount of intersections incorrect for Ray starts on center of Sphere");

        //Check that the List has the expected Point
        assertTrue(expectedResult10.contains(new Point(0,0,-1)),
                "Intersection Point not as expected for Ray starts on center of Sphere");


        //TC16: Ray starts on Sphere but goes through center

        Ray r11=new Ray(new Point(0,0,-1),new Vector(0,0,1));
        List<Point> expectedResult11=s1.findIntersections(r11);

        //Check that we have 1 intersection
        assertEquals(1,expectedResult11.size(),
                "Amount of intersections incorrect for Ray starts on Sphere but goes through center");

        //Check that the List has the expected Point
        assertTrue(expectedResult11.contains(new Point(0,0,3)),
                "Intersection Point not as expected for Ray starts on Sphere but goes through center");


        //TC17: Ray starts before Sphere but goes through center

        Ray r12=new Ray(new Point(0,0,-2),new Vector(0,0,1));
        List<Point> expectedResult12=s1.findIntersections(r12);

        //Check that we have 2 intersections
        assertEquals(2,expectedResult12.size(),
                "Amount of intersections incorrect for Ray starts before Sphere but goes through center");

        //Check that the List has the expected Points
        assertTrue(expectedResult12.contains(new Point(0,0,3))
                        &&expectedResult12.contains(new Point(0,0,-1)),
                "Intersection Point not as expected for Ray starts before Sphere but goes through center");


        //**** Group: Ray's line is tangent to the sphere (3 TC)
        //TC18: Ray starts at the tangent Point

        Ray r13=new Ray(new Point(0,0,-1),new Vector(0,1,0));
        List<Point> expectedResult13=s1.findIntersections(r13);

        //Check that we have 0 intersections
        assertNull(expectedResult13,
                "Amount of intersections incorrect for Ray starts at tangent Point");


        //TC19: Ray starts before tangent Point

        Ray r14=new Ray(new Point(0,-1,-1),new Vector(0,1,0));
        List<Point> expectedResult14=s1.findIntersections(r14);

        //Check that we have 0 intersections
        assertNull(expectedResult14,
                "Amount of intersections incorrect for Ray starts before tangent Point");


        //TC20: Ray starts after tangent Point

        Ray r15=new Ray(new Point(0,-2,-1),new Vector(0,-1,0));
        List<Point> expectedResult15=s1.findIntersections(r15);

        //Check that we have 0 intersections
        assertNull(expectedResult15,
                "Amount of intersections incorrect for Ray starts after tangent Point");


        //**** Group: Special cases
        //TC21: Ray's line is outside, Ray is orthogonal to Ray start to Sphere's center line

        Ray r16=new Ray(new Point(0,-3,1),new Vector(1,0,0));
        List<Point> expectedResult16=s1.findIntersections(r16);

        //Check that we have 0 intersections
        assertNull(expectedResult16,
                "Amount of intersections incorrect for Ray's line is outside, Ray is orthogonal to Ray start to Sphere's center line");


        //TC22: Ray's line is inside, Ray is orthogonal to Ray start to Sphere's center line

        Ray r17=new Ray(new Point(0,1,1),new Vector(1,0,0));
        List<Point> expectedResult17=s1.findIntersections(r17);

        //Check that we have 1 intersection
        assertEquals(1,expectedResult17.size(),
                "Amount of intersections incorrect for Ray's line is inside, Ray is orthogonal to Ray start to Sphere's center line");

        //Check that the list has the expected Points
        assertTrue(expectedResult17.contains(new Point(sqrt(3),1,1)),
                "Intersection Point not as expected for Ray's line is inside, Ray is orthogonal to Ray start to Sphere's center line");


    }

}