package scene;

import geometries.*;
import primitives.*;
import lighting.*;
import renderer.*;

/**
 * Class to represent a Scene
 */

public class Scene {

    /**
     * Name of the Scene
     */

    public String name;

    /**
     * Background Color of the Scene
     */

    public Color background;

    /**
     * Ambient Light in the Scene (default Black)
     */


    public AmbientLight ambientLight=AmbientLight.NONE;

    /**
     * List of Geometries in the Scene
     */

    public Geometries geometries=new Geometries();

    /**
     * Constructor for a Scene (only gets name)
     * @param newName The new name for the Scene
     */

    public Scene(String newName) {
        this.name=newName;
    }

    /**
     * Setter for Background
     * @param background The new Background
     */

    public void setBackground(Color background) {
        this.background = background;
    }

    /**
     * Setter for Ambient Light
     * @param ambientLight The new Ambient Light
     */

    public void setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
    }

    /**
     * Setter for List of Geometries
     * @param geometries The new list of Geometries
     */

    public void setGeometries(Geometries geometries) {
        this.geometries = geometries;
    }

    public Scene Builder(){
        return this;
    }



}
