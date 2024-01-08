package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void testEquals() {
        //============ Equivalence Partitions Tests ==============
        //testEqualsStandard
            Point headA = new Point(1.0, 2.0, 3.0);
            Vector directionA = new Vector(4.0, 5.0, 6.0);
            Ray rayA = new Ray(headA, directionA);

            Point headB = new Point(1.0, 2.0, 3.0);
            Vector directionB = new Vector(4.0, 5.0, 6.0);
            Ray rayB = new Ray(headB, directionB);
            assertTrue(rayA.equals(rayB),
                    "failed testEqualsStandard");
        // testEqualsWithDifferentRays
            Point headX = new Point(1.0, 2.0, 3.0);
            Vector directionX = new Vector(4.0, 5.0, 6.0);
            Ray rayX = new Ray(headX, directionX);
            Point headY = new Point(3.0, 2.0, 1.0);
            Vector directionY = new Vector(6.0, 5.0, 4.0);
            Ray rayY = new Ray(headY, directionY);
            assertFalse(rayX.equals(rayY),"failed testEqualsWithDifferentRays");
        // testEqualsWithSelf
            Point head = new Point(1.0, 2.0, 3.0);
            Vector direction = new Vector(4.0, 5.0, 6.0);
            Ray ray = new Ray(head, direction);
            assertTrue(ray.equals(ray),"failed testEqualsWithSelf");

    }

    @Test
    void testToString() {
        //testToStringStandard
            Point head = new Point(1.0, 2.0, 3.0);
            Vector direction = new Vector(4.0, 5.0, 6.0);
            Ray ray = new Ray(head, direction);
            String expectedString = "Ray{head=(1.0, 2.0, 3.0), direction=(4.0, 5.0, 6.0)}";
            assertEquals(expectedString, ray.toString(),
                    "failed testToStringStandard");
    }
}