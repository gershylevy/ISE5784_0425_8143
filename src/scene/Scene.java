package scene;

import geometries.*;
import primitives.*;
import lighting.*;
import renderer.*;

import java.util.LinkedList;
import java.util.List;

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
     * List of all lights
     */

    public List<LightSource> lights=new LinkedList<>();

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

    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * Setter for Ambient Light
     * @param ambientLight The new Ambient Light
     */

    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * Setter for List of Geometries
     * @param geometries The new list of Geometries
     */

    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    /**
     * Setter for Lights
     * @param lights Our new List of all the Lights
     */

    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }

    /**
     * Builder function
     */

    public Scene Builder(){
        return this;
    }

}
