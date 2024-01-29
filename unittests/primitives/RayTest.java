package primitives;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {


    /**
     * Test method for
     * {@link Ray#getPoint(double)}
     */
    @Test
    void getPoint() {

        // ============ Equivalence Partitions Tests ==============

        //TC01: Positive number

        Ray r1=new Ray(new Point(0,0,1),new Vector(0,1,0));
        assertEquals(new Point(0,2,1),r1.getPoint(2),
                "Failed positive number test");


        //TC02: Negative number

        assertEquals(new Point(0,-2,1),r1.getPoint(-2),
                "Failed negative number test");


        // =============== Boundary Values Tests ==================

        //TC10: T=0

        assertEquals(new Point(0,0,1),r1.getPoint(0),
                "Failed t=0 test");


    }
    /**
     * Test method for
     * {@link Ray#findClosestPoint(List)}
     */


    @Test
    void findClosestPointTest() {

        // ============ Equivalence Partitions Tests ==============

        //TC01: Closest Point in the middle of the list

        Ray r1=new Ray(new Point(Double3.ZERO),new Vector(0,0,1));

        List<Point> pointList1=new LinkedList<>();
        pointList1.add(new Point(10,10,10));
        pointList1.add(new Point(0,0,1));
        pointList1.add(new Point(20,20,20));

        assertEquals(new Point(0,0,1),r1.findClosestPoint(pointList1),
                "Basic find closest Point test failed");


        // =============== Boundary Values Tests ==================


        //TC10: Empty list

        List<Point> pointList2=new LinkedList<>();
        assertNull(r1.findClosestPoint(pointList2),
                "Find closest Point for empty list doesn't work");


        //TC11: Closest Point in the beginning of the list

        List<Point> pointList3=new LinkedList<>();
        pointList3.add(new Point(0,0,1));
        pointList3.add(new Point(10,10,10));
        pointList3.add(new Point(20,20,20));

        assertEquals(new Point(0,0,1),r1.findClosestPoint(pointList3),
                "Find closest Point for beginning of list failed");


        //TC12: Closest Point in the end of the list

        List<Point> pointList4=new LinkedList<>();
        pointList4.add(new Point(10,10,10));
        pointList4.add(new Point(20,20,20));
        pointList4.add(new Point(0,0,1));

        assertEquals(new Point(0,0,1),r1.findClosestPoint(pointList4),
                "Find closest Point for end of list failed");


    }
}