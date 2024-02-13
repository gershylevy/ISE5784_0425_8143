package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Point;
import primitives.Vector;
import scene.Scene;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {

    /**
     * Test case for method
     * {@link ImageWriter#writeToImage()}
     */
    @Test
    void writeToImage() throws CloneNotSupportedException {

        Color col=new Color(30,0,100);
        Camera.Builder cameraBuilder = Camera.getBuilder().
                setRayTracer(new SimpleRayTracer(new Scene("Test")))
                .setImageWriter(new ImageWriter("Image1", 500, 800))
                .setLocation(Point.ZERO)
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVpSize(500, 800);
        Camera camera1 = cameraBuilder.setVpDistance(1).build();


        for(int i=0;i<500;i++)
            for(int j=0;j<800;j++)
                camera1.getImageWriter().writePixel(i,j,col);

        for(int i=0;i<camera1.getViewPlaneWidth();i+=50)
            for(int j=0;j<camera1.getViewPlaneHeight();j+=1)
                camera1.getImageWriter().writePixel(i,j,Color.BLACK);

        for(int i=0;i<camera1.getViewPlaneHeight();i+=50)
            for(int j=0;j<camera1.getViewPlaneWidth();j+=1)
                camera1.getImageWriter().writePixel(j,i,Color.BLACK);


        camera1.getImageWriter().writeToImage();
    }
}