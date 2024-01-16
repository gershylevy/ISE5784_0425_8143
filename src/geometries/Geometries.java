package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

public class Geometries implements Intersectable {
    List<Intersectable> Shapes=List.of();

    public Geometries() {}

    public Geometries(Intersectable... geometries) {
      this.add(geometries);
    }

    public void add(Intersectable...geometries) {
        for(int i=0;i<geometries.length ;i++){
            this.Shapes.add(geometries[i]);}
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
