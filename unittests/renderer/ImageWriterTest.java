package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {

    /**
     * Test case for method
     * {@link ImageWriter#writeToImage()}
     */
    @Test
    void writeToImage() {

        Color col=new Color(30,0,100);
        ImageWriter img=new ImageWriter("Image1",500,800);

        for(int i=0;i<500;i++)
            for(int j=0;j<800;j++)
                img.writePixel(i,j,col);

        img.writeToImage();




    }
}