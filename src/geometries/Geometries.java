package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Geometries class to save list of Shapes
 */

public class Geometries extends Intersectable {
    /**
     * List of Shapes
     */
    List<Intersectable> Shapes= new LinkedList<>();

    public Geometries() {}

    /**
     * Constructor with a parameter
     * @param geometries Shapes that are going to be on the list
     */
    public Geometries(Intersectable... geometries) {
      this.add(geometries);
    }

    /**
     * Function to add Shapes to current list
     * @param geometries Shapes that are going to be added on to the list
     */

    public void add(Intersectable...geometries) {
        this.Shapes.addAll(Arrays.stream(geometries).toList() );
    }


    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> result = null;
        for (Intersectable item : Shapes) {
            List<GeoPoint> allIntersections = item.findGeoIntersectionsHelper(ray);
            if (allIntersections != null) {
                if (result == null) {
                    result = new LinkedList<>();  // Initialize the list
                }
                result.addAll(allIntersections);
            }

        }
        return result;
    }
}
