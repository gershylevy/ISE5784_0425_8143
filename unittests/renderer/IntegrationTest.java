package renderer;
import org.junit.jupiter.api.Test;
import primitives.*;
import geometries.*;
import renderer.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTest {

    int RayThruPixel(double CameraZ,Intersectable shape) {
        //Assume vp is 3x3 and distance is 1, vTo is (0,0,-1), vUp is (0,1,0), and that the camera is at (0,0,n)
        int intersections=0;
        Ray r1;
        for(int i=-1;i<=1;i++){
            for(int j=-1;j<=1;j++){
                r1 = new Ray(new Point(0, 0, CameraZ), new Vector(i, j, - 1));

                if(shape.findIntersections(r1)!=null)
                    intersections+=shape.findIntersections(r1).size();
            }
        }
        return intersections;
    }

    @Test
    void SphereTest() throws CloneNotSupportedException {
        String badRay = "Bad Ray";


        //TC 01: Sphere with 2 intersections

        Sphere s1 = new Sphere(new Point(0, 0, -3), 1);

        assertEquals(2, RayThruPixel(0, s1), badRay);


        //TC02: Sphere with 18 intersections

        Sphere s2 = new Sphere(new Point(0, 0, -2.5), 2.5);
        assertEquals(18, RayThruPixel(0.5, s2), badRay);


        //TC03: Sphere with 10 intersections

        Sphere s3 = new Sphere(new Point(0, 0, -2), 2);
        assertEquals(10, RayThruPixel(0.5, s3), badRay);


        //TC04: Sphere with 9 intersections

        Sphere s4 = new Sphere(new Point(0, 0, 0), 4);
        assertEquals(9, RayThruPixel(0.5, s4), badRay);


        //TC05: Sphere with 0 intersections

        Sphere s5 = new Sphere(new Point(0, 0, 1), 0.5);
        assertEquals(0, RayThruPixel(0, s5), badRay);


    }


    @Test
    void PlaneTest() throws CloneNotSupportedException {
        String badRay = "Bad Ray";


        //TC06: Plane with 9 intersections (orthogonal)

        Plane p1 = new Plane(new Point(0, 0, -5), new Point(1, 2, -5), new Point(4, 3, -5));
        assertEquals(9, RayThruPixel(0, p1), badRay);


        //TC07: Plane with 9 intersections (non-orthogonal)

        Plane p2 = new Plane(new Point(5, -2, -1), new Point(1, 2, -3), new Point(4, 3, -3));
        assertEquals(9, RayThruPixel(0, p2), badRay);


        //TC08: Plane with 6 intersections

        Plane p3 = new Plane(new Point(0, 0, -30), new Point(0, -3, 0), new Point(100, 0, 2));
        assertEquals(6, RayThruPixel(0, p3), badRay);
    }

    @Test
    void TriangleTest() throws CloneNotSupportedException {
        String badRay = "Bad Ray";


        //TC09: Triangle with 1 intersection

        Triangle t1=new Triangle(new Point(0,1,-2),new Point(1,-1,-2),new Point(-1,-1,-2));
        assertEquals(1,RayThruPixel(0,t1),badRay);


        //TC10: Triangle with 2 intersections

        Triangle t2=new Triangle(new Point(0,20,-2),new Point(1,-1,-2),new Point(-1,-1,-2));
        assertEquals(2,RayThruPixel(0,t2),badRay);

    }
}
