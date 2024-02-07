package renderer;

import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import lighting.AmbientLight;
import primitives.*;
import renderer.*;
import scene.Scene;

/** Test rendering a basic image
 * @author Dan */
public class RenderTests {
   /** Scene of the tests */
   private final Scene          scene  = new Scene("Test scene");
   /** Camera builder of the tests */
   private final Camera.Builder camera = Camera.getBuilder()
      .setRayTracer(new SimpleRayTracer(scene))
      .setLocation(Point.ZERO).setDirection(new Vector(0, 0, -1), new Vector(0,1,0))
      .setVpDistance(100)
      .setVpSize(1000, 1000);



   /** Produce a scene with basic 3D model and render it into a png image with a
    * grid */
   @Test
   public void renderTwoColorTest() throws CloneNotSupportedException {
      scene.geometries.add(new Sphere(new Point(0, 0, -100), 50d),
                           new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100)), // up
                           // left
                           new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100),
                                        new Point(-100, -100, -100)), // down
                           // left
                           new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))); // down
      scene.setAmbientLight(new AmbientLight(new Color(255, 191, 191), Double3.ONE));
      scene.setBackground(new Color(75, 127, 90));

      // right
      camera
         .setImageWriter(new ImageWriter("base render test", 1000, 1000))
         .build().renderImage();
      camera.build().printGrid(100, new Color(YELLOW));
      camera.build().writeToImage();
   }

}

