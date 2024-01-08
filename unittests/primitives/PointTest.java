package primitives;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Point
 */

class PointTest {
   /** Delta value for accuracy when comparing the numbers of type 'double' in
    * assertEquals
    */

    private final double DELTA = 0.000001;

    /**
     * Test method for
     * {@link primitives.Point#equals(Object)}
     */

    @org.junit.jupiter.api.Test
    void testEquals() {
        // ============ Equivalence Partitions Tests ==============

        //TC01: 2 identical Points

        Point p1=new Point(1,2,3);
        Point p2=new Point(1,2,3);
        assertFalse(p1.equals(p2), "2 identical Points are not equal");

        //TC02: 2 Points that are not identical

        Point p3=new Point(1,2,3);
        Point p4=new Point(1,2,4);
        assertTrue(p3.equals(p4), "2 non identical Points are equal");

        //TC03: 2 Points






    }

    /**
     * Test method for
     * {@link primitives.Point#toString()}
     */

    @org.junit.jupiter.api.Test
    void testToString() {
        // ============ Equivalence Partitions Tests ==============

        //TC01: Test that toString returns expected String

        Point p1=new Point(1,2,3);
        String expectedString1="(1,2,3)";
        assertEquals(p1.toString(),expectedString1,"toString doesn't return expected String");


        //TC02: Test that toString returns same value for identical Strings

        Point p2=new Point(1,2,3);
        assertEquals(p1.toString(),p2.toString(),"2 identical Strings don't return same toString");


        // =============== Boundary Values Tests ==================

        //TC10: Test for zero triad (0,0,0)

        Point zeroPoint=new Point(0,0,0);
        String expectedString2="(0,0,0)";
        assertEquals(zeroPoint.toString(),expectedString2,"toString for Zero triad doesn't work");


        //TC11: Test for max value of double

        Point maxPoint=new Point(Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE);
        assertNotNull(zeroPoint.toString(),"toString for max value doesn't work");

        //TC12: Test for min value of double

        Point minPoint=new Point(Double.MIN_VALUE,Double.MIN_VALUE,Double.MIN_VALUE);
        assertNotNull(zeroPoint.toString(),"toString for min value doesn't work");
    }

    /**
     * Test method for
     * {@link primitives.Point#add(Vector)}
     */
    @org.junit.jupiter.api.Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============

        //TC01: Simple addition of Vector

        Point p1=new Point(1,2,3);
        Vector v1=new Vector(4,5,6);
        Point expectedResult1=new Point(5,7,9);
        assertEquals(p1.add(v1),expectedResult1,"Addition doesn't work");


        //TC02: Test that addition of different Vectors returns different results

        Vector v2=new Vector(1,2,3);
        assertNotEquals(p1.add(v1),p1.add(v2),"The same Point added to different Vectors returns same result");


        //TC03: Test adding Vector with negative values

        Vector v3=new Vector(-4,-5,-6);
        Point expectedResult2=new Point(-3,-3,-3);
        assertEquals(p1.add(v3),expectedResult2,"Addition of Vector with negative numbers doesn't work");

        // =============== Boundary Values Tests ==================


        //TC10: Addition of zero Vector

        Vector zeroVector=new Vector(0,0,0);
        assertEquals(p1,p1.add(zeroVector),"Addition of zero Vector doesn't return same Point");


        //TC11: Addition of Vector with negative coordinates to the Point

        Vector negativeVector=new Vector(-1,-2,-3);
        Point zeroPoint=new Point(0,0,0);
        assertEquals(p1.add(negativeVector),zeroPoint,"Addition with Vector with negative coordinates to the Point doesn't work");


        //TC12: Addition of zero Point

        Point expectedResult3=new Point(4,5,6);
        assertEquals(zeroPoint.add(v1),expectedResult3,"Addition of zero Point doesn't work");

    }

    /**
     * Test method for
     * {@link primitives.Point#subtract(Point)}
     */

    @org.junit.jupiter.api.Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============

        //TC01: Simple subtraction of Point

        Point p1=new Point(1,2,3);
        Point p2=new Point(4,5,6);
        Vector expectedResult1=new Vector(3,3,3);
        assertEquals(p2.subtract(p1),expectedResult1,"Subtraction doesn't work");


        //TC02: Test that Subtraction of different Points from the same Point returns different results

        Point p3=new Point(1,1,1);
        assertNotEquals(p2.subtract(p1),p2.subtract(p3),"Subtracting 2 different Points from the same Point returns same result");


        //TC03: Test subtracting same Point from different Points returns different results

        assertNotEquals(p2.subtract(p1),p3.subtract(p1),"subtracting same Point from different Points returns same results");


        //TC04: Test subtracting Point with negative values

        Point p4=new Point(-4,-5,-6);
        Vector expectedResult2=new Vector(5,7,9);
        assertEquals(p1.subtract(p4),expectedResult2,"Subtraction of Point with negative values doesn't work");


        // =============== Boundary Values Tests ==================


        //TC10: Subtraction of zero Point

        Point zeroPoint=new Point(0,0,0);
        Vector expectedResult=new Vector(1,2,3);
        assertEquals(expectedResult,p1.subtract(zeroPoint),"Subtraction of zero Point doesn't return Vector with same coordinates");


        //TC11: Subtraction of Point with negative coordinates to the Point

        Point negativePoint=new Point(-1,-2,-3);
        Vector zeroVector=new Vector(0,0,0);
        assertEquals(p1.subtract(negativePoint),zeroVector,"Subtraction with Point with negative coordinates to the Point doesn't return 0 Vector");


        //TC12: Subtraction from zero Point

        Vector expectedResult3=new Vector(-1,-2,-3);
        assertEquals(zeroPoint.subtract(p1),expectedResult3,"Subtraction from zero Point doesn't work");
    }

    /**
     * Test method for
     * {@link primitives.Point#distanceSquared(Point)}
     */

    @org.junit.jupiter.api.Test
    void testDistanceSquared() {

        // ============ Equivalence Partitions Tests ==============

        //TC01: Test for if distance squared of Point from itself is 0

        Point p1=new Point(1,2,3);
        assertEquals(p1.distanceSquared(p1),0,DELTA,"Distance squared of Point from itself is not 0");


        //TC02: Test if distance squared between 2 random Points is as expected

        Point p3=new Point(1,2,1);
        assertEquals(p1.distanceSquared(p3),4,DELTA,"Distance squared between 2 Points is not as expected");


        //TC03: Test if the distance squared between 2 Points is the same both ways

        assertEquals(p1.distanceSquared(p3),p3.distanceSquared(p1),DELTA,
                "Distance squared between 2 Points isn't the same both ways");


        //TC04: Test to see if the distance squared works with negative numbers

        Point p4=new Point(-1,-2,-3);
        assertEquals(p1.distanceSquared(p4),56,DELTA,"Distance squared doesn't work with negative numbers");


        // =============== Boundary Values Tests ==================



        //TC10: Test to check the distance squared from the origin Point (0,0,0)

        Point p5=new Point(0,0,0);
        assertEquals(p1.distanceSquared(p5),14,DELTA,"Distance from origin doesn't work");
    }

    /**
     * Test method for
     * {@link primitives.Point#distance(Point)}
     */

    @org.junit.jupiter.api.Test
    void testDistance() {

        // ============ Equivalence Partitions Tests ==============

        //TC01: Test for if distance of Point from itself is 0

        Point p1=new Point(1,4,9);
        assertEquals(p1.distance(p1),0,DELTA,"Distance of Point from itself is not 0");


        //TC02: Test if distance squared between 2 random Points is as expected

        Point p3=new Point(1,4,1);
        assertEquals(p1.distance(p3),8,DELTA,"Distance between 2 Points is not as expected");


        //TC03: Test if the distance between 2 Points is the same both ways

        assertEquals(p1.distance(p3),p3.distance(p1),DELTA,
                "Distance between 2 Points isn't the same both ways");


        //TC04: Test to see if the distance works with negative numbers

        Point p4=new Point(0,-4,5);
        assertEquals(p1.distance(p4),56,DELTA,"Distance doesn't work with negative numbers");


        // =============== Boundary Values Tests ==================



        //TC10: Test to check the distance squared from the origin Point (0,0,0)

        Point p5=new Point(0,0,0);
        assertEquals(p1.distance(p5),14,DELTA,"Distance from origin doesn't work");
    }
}