package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    void getNormal() {
        // Assuming a sphere centered at (3,3,3) with radius 1
        Sphere sphere = new Sphere(new Point(3, 3, 3), 1);
        // ============ Equivalence Partitions Tests ==============
       //Tc01: testCalculateNormal classic
            // Both points are the same
            Point samePoint = new Point(4, 4, 5);
            assertEquals(sphere.getNormal(samePoint),
                    new Vector((double) 1 /2, (double) 1 /2 ,1.0),
                    "failed test  calculate normal classic");
            //TC02: test getNormal sphere returns normalized vector
            assertEquals(1.0,(sphere.getNormal(samePoint)).length(),
                    "failed test getNormal sphere returns normalized vector");

    }
}