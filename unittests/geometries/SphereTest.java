package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
       // testCalculateNormalWithBothPointsSame
            // Assuming a sphere centered at (3,3,3) with radius 0
            Sphere sphere = new Sphere(new Point(3, 3, 3), 0);
            // Both points are the same
            Point samePoint = new Point(3, 3, 3);
            assertEquals(sphere.getNormal(samePoint),
                    new Vector((double) 3 /(27), (double) 3 /27, (double) 3 /(27)),"failed test  Both points are the same");

    }
}