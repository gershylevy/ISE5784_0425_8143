package renderer;

import static java.awt.Color.*;

import lighting.DirectionalLight;
import lighting.PointLight;
import org.junit.jupiter.api.Test;

import geometries.*;
import lighting.AmbientLight;
import lighting.SpotLight;
import primitives.*;
import renderer.*;
import scene.Scene;

/** Testing basic shadows
 * @author Dan */
public class ShadowTests {
   /** Scene of the tests */
   private final Scene          scene      = new Scene("Test scene");
   /** Camera builder of the tests */
   private final Camera.Builder camera     = Camera.getBuilder()
      .setDirection(new Vector(0,0,-1), new Vector(0,1,0))
      .setLocation(new Point(0, 0, 1000)).setVpDistance(1000)
      .setVpSize(200, 200)
      .setRayTracer(new SimpleRayTracer(scene));

   /** The sphere in the tests */
   private final Intersectable  sphere     = new Sphere(new Point(0, 0, -200), 60d)
      .setEmission(new Color(BLUE))
      .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30));
   /** The material of the triangles in the tests */
   private final Material       trMaterial = new Material().setkD(0.5).setkS(0.5).setShininess(30);

   /** Helper function for the tests in this module
    * @param pictName     the name of the picture generated by a test
    * @param triangle     the triangle in the test
    * @param spotLocation the spotlight location in the test */
   private void sphereTriangleHelper(String pictName, Triangle triangle, Point spotLocation) throws CloneNotSupportedException {
      scene.geometries.add(sphere, triangle.setEmission(new Color(BLUE)).setMaterial(trMaterial));
      scene.lights.add( //
                       new SpotLight(new Color(400, 240, 0), spotLocation, new Vector(1, 1, -3)) //
                          .setkL(1E-5).setkQ(1.5E-7));
      camera.setImageWriter(new ImageWriter(pictName, 400, 400))
         .build()
         .renderImage();
         camera.build().writeToImage();
   }

   /** Produce a picture of a sphere and triangle with point light and shade */
   @Test
   public void sphereTriangleInitial() throws CloneNotSupportedException {
      sphereTriangleHelper("shadowSphereTriangleInitial", //
                           new Triangle(new Point(-70, -40, 0), new Point(-40, -70, 0), new Point(-68, -68, -4)), //
                           new Point(-100, -100, 200));
   }

   /** Sphere-Triangle shading - move triangle up-right */
   @Test
   public void sphereTriangleMove1() throws CloneNotSupportedException {
      sphereTriangleHelper("shadowSphereTriangleMove2", //
                           new Triangle(new Point(-70,-30,0), new Point(-40,-60,0), new Point(-67,-67,-3)), //
                           new Point(-100, -100, 200));
   }

   /** Sphere-Triangle shading - move triangle upper-righter */
   @Test
   public void sphereTriangleMove2() throws CloneNotSupportedException {
      sphereTriangleHelper("shadowSphereTriangleMove1", //
                           new Triangle(new Point(-70,-40,-20), new Point(-41,-71,-20), new Point(-69,-69,-5)), //
                           new Point(-100, -100, 200));
   }

   /** Sphere-Triangle shading - move spot closer */
   @Test
   public void sphereTriangleSpot1() throws CloneNotSupportedException {
      sphereTriangleHelper("shadowSphereTriangleSpot1", //
                           new Triangle(new Point(-70, -40, 0), new Point(-40, -70, 0), new Point(-68, -68, -4)), //
                           new Point(-99,-99,199));
   }

   /** Sphere-Triangle shading - move spot even more close */
   @Test
   public void sphereTriangleSpot2() throws CloneNotSupportedException {
      sphereTriangleHelper("shadowSphereTriangleSpot2", //
                           new Triangle(new Point(-70, -40, 0), new Point(-40, -70, 0), new Point(-68, -68, -4)), //
                           new Point(-101,-101,201));
   }

   /** Produce a picture of a two triangles lighted by a spot light with a Sphere
    * producing a shading */
   @Test
   public void trianglesSphere() throws CloneNotSupportedException {
      scene.geometries.add(
                           new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135),
                                        new Point(75, 75, -150)) //
                              .setMaterial(new Material().setkS(0.8).setShininess(60)), //
                           new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
                              .setMaterial(new Material().setkS(0.8).setShininess(60)), //
                           new Sphere(new Point(0, 0, -11), 30d) //
                              .setEmission(new Color(BLUE)) //
                              .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30)) //
      );
      scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));
      scene.lights.add(
                       new SpotLight(new Color(700, 400, 400), new Point(40, 40, 115), new Vector(-1, -1, -4)) //
                          .setkL(4E-4).setkQ(2E-5));

      camera.setImageWriter(new ImageWriter("shadowTrianglesSphere", 600, 600))
         .build()
         .renderImage();
         camera.build().writeToImage();
   }


   @Test
   public void Elements10TestMP1() throws CloneNotSupportedException {
      scene.geometries.add(
              new Sphere(new Point(0,0,0),100).setEmission(new Color(RED))
                      .setMaterial(new Material().setkS(0.8).setShininess(100)/*.setkR(0.6)*/),
              new Sphere(new Point(-40,45,90),10).setEmission(new Color(BLACK))
                      .setMaterial(new Material().setkS(0.8).setShininess(60).setkR(0.5)),
              new Sphere(new Point(40,45,90),10).setEmission(new Color(BLACK))
                      .setMaterial(new Material().setkS(0.8).setShininess(60).setkR(0.5)),
              new Plane(new Point(0,0,-150),new Point(100,150,0),new Point(-100,50,-150))
                      .setMaterial(new Material().setkS(0.8).setShininess(60)).
                      setEmission(new Color(BLUE)),
              new Sphere(new Point(-20,-20,120),20).setEmission(new Color(YELLOW))
                      .setMaterial(new Material().setkS(0.8).setShininess(60)),
              new Sphere(new Point(20,-20,120),20).setEmission(new Color(YELLOW))
                      .setMaterial(new Material().setkS(0.8).setShininess(60)),
              new Triangle(new Point(50,-50,130),new Point(-50,-50,130),new Point(0,-100,0))
                      .setEmission(new Color(BLACK)).setMaterial(new Material().setkS(0.8).setShininess(60)),
              new Triangle(new Point(30,-80,150),new Point(-30,-80,150),new Point(0,-100,10))
                      .setEmission(new Color(GRAY)).setMaterial(new Material().setkS(0.8).setShininess(60))
      );

      scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));
      scene.lights.add(
              new SpotLight(new Color(700, 400, 400), new Point(40, 40, 115), new Vector(-1, -1, -4)) //
                      .setkL(4E-4).setkQ(2E-5));
      scene.lights.add(new DirectionalLight(new Color(700, 400, 400),new Vector(-1,-1,-1)));
      scene.lights.add(new PointLight(new Color(700, 400, 400), new Point(100,100,300))
              .setkL(0.001).setkQ(0.0002));
      scene.lights.add(
              new SpotLight(new Color(700, 400, 400), new Point(100, 100, 100), new Vector(-102, -50, -15)) //
                      .setkL(4E-4).setkQ(2E-5));
      scene.lights.add(
              new SpotLight(new Color(700, 400, 400), new Point(120, 120, 120), new Vector(-100, -140, -20)) //
                      .setkL(4E-4).setkQ(2E-5));
      scene.lights.add(
              new SpotLight(new Color(700, 400, 400), new Point(-120, 120, 120), new Vector(100, -140, -20)) //
                      .setkL(4E-4).setkQ(2E-5));


      final Camera.Builder camera10     = Camera.getBuilder()
              .setDirection(new Vector(0,0,-1), new Vector(0,1,0))
              .setLocation(new Point(0, 0, 1000)).setVpDistance(1000)
              .setVpSize(200, 200)
              .setRayTracer(new SimpleRayTracer(scene))
              .setIsGrid(true);

      camera10.setImageWriter(new ImageWriter("10ElementsTestMP1", 600, 600))
              .build()
              .renderImage();
      camera10.build().writeToImage();


      camera10.setIsFancy(true);

      camera10.setImageWriter(new ImageWriter("10ElementsTestFancy", 600, 600))
              .build()
              .renderImage();
      camera10.build().writeToImage();

   }

   @Test
   public void Elements10TestMT() throws CloneNotSupportedException {
      scene.geometries.add(
              new Sphere(new Point(0,0,0),100).setEmission(new Color(RED))
                      .setMaterial(new Material().setkS(0.8).setShininess(100)/*.setkR(0.6)*/),
              new Sphere(new Point(-40,45,90),10).setEmission(new Color(BLACK))
                      .setMaterial(new Material().setkS(0.8).setShininess(60).setkR(0.5)),
              new Sphere(new Point(40,45,90),10).setEmission(new Color(BLACK))
                      .setMaterial(new Material().setkS(0.8).setShininess(60).setkR(0.5)),
              new Plane(new Point(0,0,-150),new Point(100,150,0),new Point(-100,50,-150))
                      .setMaterial(new Material().setkS(0.8).setShininess(60)).
                      setEmission(new Color(BLUE)),
              new Sphere(new Point(-20,-20,120),20).setEmission(new Color(YELLOW))
                      .setMaterial(new Material().setkS(0.8).setShininess(60)),
              new Sphere(new Point(20,-20,120),20).setEmission(new Color(YELLOW))
                      .setMaterial(new Material().setkS(0.8).setShininess(60)),
              new Triangle(new Point(50,-50,130),new Point(-50,-50,130),new Point(0,-100,0))
                      .setEmission(new Color(BLACK)).setMaterial(new Material().setkS(0.8).setShininess(60)),
              new Triangle(new Point(30,-80,150),new Point(-30,-80,150),new Point(0,-100,10))
                      .setEmission(new Color(GRAY)).setMaterial(new Material().setkS(0.8).setShininess(60))
      );

      scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));
      scene.lights.add(
              new SpotLight(new Color(700, 400, 400), new Point(40, 40, 115), new Vector(-1, -1, -4)) //
                      .setkL(4E-4).setkQ(2E-5));
      scene.lights.add(new DirectionalLight(new Color(700, 400, 400),new Vector(-1,-1,-1)));
      scene.lights.add(new PointLight(new Color(700, 400, 400), new Point(100,100,300))
              .setkL(0.001).setkQ(0.0002));
      scene.lights.add(
              new SpotLight(new Color(700, 400, 400), new Point(100, 100, 100), new Vector(-102, -50, -15)) //
                      .setkL(4E-4).setkQ(2E-5));
      scene.lights.add(
              new SpotLight(new Color(700, 400, 400), new Point(120, 120, 120), new Vector(-100, -140, -20)) //
                      .setkL(4E-4).setkQ(2E-5));
      scene.lights.add(
              new SpotLight(new Color(700, 400, 400), new Point(-120, 120, 120), new Vector(100, -140, -20)) //
                      .setkL(4E-4).setkQ(2E-5));

      final Camera.Builder camera10     = Camera.getBuilder()
              .setDirection(new Vector(0,0,-1), new Vector(0,1,0))
              .setLocation(new Point(0, 0, 1000)).setVpDistance(1000)
              .setVpSize(200, 200)
              .setRayTracer(new SimpleRayTracer(scene))
              .setIsGrid(true).setMultithreading(4);

      camera10.setImageWriter(new ImageWriter("10ElementsTestMultiThreading", 600, 600))
              .build()
              .renderImage();
      camera10.build().writeToImage();
   }


   @Test
   public void Elements10TestFancy() throws CloneNotSupportedException {
      scene.geometries.add(
              new Sphere(new Point(0,0,0),100).setEmission(new Color(RED))
                      .setMaterial(new Material().setkS(0.8).setShininess(100)),
              new Sphere(new Point(-40,45,90),10).setEmission(new Color(BLACK))
                      .setMaterial(new Material().setkS(0.8).setShininess(60).setkR(0.5)),
              new Sphere(new Point(40,45,90),10).setEmission(new Color(BLACK))
                      .setMaterial(new Material().setkS(0.8).setShininess(60).setkR(0.5)),
              new Plane(new Point(0,0,-150),new Point(100,150,0),new Point(-100,50,-150))
                      .setMaterial(new Material().setkS(0.8).setShininess(60)).
                      setEmission(new Color(BLUE)),
              new Sphere(new Point(-20,-20,120),20).setEmission(new Color(YELLOW))
                      .setMaterial(new Material().setkS(0.8).setShininess(60)),
              new Sphere(new Point(20,-20,120),20).setEmission(new Color(YELLOW))
                      .setMaterial(new Material().setkS(0.8).setShininess(60)),
              new Triangle(new Point(50,-50,130),new Point(-50,-50,130),new Point(0,-100,0))
                      .setEmission(new Color(BLACK)).setMaterial(new Material().setkS(0.8).setShininess(60)),
              new Triangle(new Point(30,-80,150),new Point(-30,-80,150),new Point(0,-100,10))
                      .setEmission(new Color(GRAY)).setMaterial(new Material().setkS(0.8).setShininess(60))
      );

      scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));
      scene.lights.add(
              new SpotLight(new Color(700, 400, 400), new Point(40, 40, 115), new Vector(-1, -1, -4)) //
                      .setkL(4E-4).setkQ(2E-5));
      scene.lights.add(new DirectionalLight(new Color(700, 400, 400),new Vector(-1,-1,-1)));
      scene.lights.add(new PointLight(new Color(700, 400, 400), new Point(100,100,300))
              .setkL(0.001).setkQ(0.0002));
      scene.lights.add(
              new SpotLight(new Color(700, 400, 400), new Point(100, 100, 100), new Vector(-102, -50, -15)) //
                      .setkL(4E-4).setkQ(2E-5));
      scene.lights.add(
              new SpotLight(new Color(700, 400, 400), new Point(120, 120, 120), new Vector(-100, -140, -20)) //
                      .setkL(4E-4).setkQ(2E-5));
      scene.lights.add(
              new SpotLight(new Color(700, 400, 400), new Point(-120, 120, 120), new Vector(100, -140, -20)) //
                      .setkL(4E-4).setkQ(2E-5));

      final Camera.Builder camera10     = Camera.getBuilder()
              .setDirection(new Vector(0,0,-1), new Vector(0,1,0))
              .setLocation(new Point(0, 0, 1000)).setVpDistance(1000)
              .setVpSize(200, 200)
              .setRayTracer(new SimpleRayTracer(scene))
              .setIsGrid(true).setMultithreading(4);

      camera10.setImageWriter(new ImageWriter("10ElementsTestFancy", 600, 600))
              .build()
              .renderImage();
      camera10.build().writeToImage();
   }



}
