package primitives;

import org.junit.jupiter.api.Test;

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
}