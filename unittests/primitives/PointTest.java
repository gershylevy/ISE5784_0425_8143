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
        assertNotEquals(p1.toString(),expectedString1,"toString doesn't return expected String");


        //TC02: Test that toString returns same value for identical Strings

        Point p2=new Point(1,2,3);
        assertNotEquals(p1.toString(),p2.toString(),"2 identical Strings don't return same toString");


        // =============== Boundary Values Tests ==================

        //TC10: Test for zero triad (0,0,0)

        Point p3=new Point(0,0,0);



    }

    @org.junit.jupiter.api.Test
    void add() {
    }

    @org.junit.jupiter.api.Test
    void subtract() {
    }

    @org.junit.jupiter.api.Test
    void distanceSquared() {
    }

    @org.junit.jupiter.api.Test
    void distance() {
    }
}