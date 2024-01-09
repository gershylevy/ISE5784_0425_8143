package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============

        //TC01: same value get normal test
        Vector newDirection=new Vector(0,1,0);
        Point newHead=new Point(0,0,0);
        Ray newAxis=new Ray(newHead, newDirection);
        Tube tube = new Tube(newAxis,1);
        Point newPoint= new Point(1,5,0);
        assertEquals(new Vector(1,1,1),tube.getNormal(newPoint),
                "failed get normal sphere classic test");
        //TC02: test getNormal sphere returns normalized vector
        assertEquals(1.0,(tube.getNormal(newPoint)).length(),
                "failed test getNormal sphere returns normalized vector");
        // =============== Boundary Values Tests ==================
        //TC10: point-pointO is orthogonal to axis
        Point newPoint1= new Point(1,-2,1);
        assertEquals(new Vector(1,1,1),tube.getNormal(newPoint1),
                "failed point-pointO is orthogonal to axis test");
    }
}