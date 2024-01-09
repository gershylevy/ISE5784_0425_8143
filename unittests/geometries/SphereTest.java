package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    void getNormal() {
        // Assuming a sphere centered at (3,3,3) with radius 1
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
}