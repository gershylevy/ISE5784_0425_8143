package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * Class Intersectable to calculate intersections
 */

public abstract class Intersectable {

    /**
     * Function to find a shapes intersections with a Ray
     * @param ray Ray that we find intersections with
     * @return Intersection Points
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }


    /**
     * "Public" function to find a shapes intersections with a Ray (NVI- Non-Virtual Interface)
     * @param ray Ray that we find intersections with
     * @return Intersection GeoPoints
     */

    public List<GeoPoint> findGeoIntersections(Ray ray){
        return findGeoIntersectionsHelper(ray);
    }

    /**
     * "Hidden" function to find a shapes intersections with a Ray (NVI- Non-Virtual Interface)
     * @param ray Ray that we find intersections with
     * @return Intersection GeoPoints
     */

    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);


    /**
     * Helper class GeoPoint (PDS- Passive Data Structure)
     */

    public static class GeoPoint {

        /**
         * Helper field geometry
         */
        public Geometry geometry;

        /**
         * Helper field point
         */
        public Point point;


        /**
         * Constructor for GeoPoint
         * @param newGeometry New value for geometry
         * @param newPoint new value for point
         */

        public GeoPoint(Geometry newGeometry,Point newPoint) {
            this.geometry=newGeometry;
            this.point=newPoint;
        }

        /**
         * Function to compare 2 GeoPoints
         * @param obj GeoPoint that we are going to compare with ours
         * @return If the GeoPoints are equal (T/F)
         */

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            return (obj instanceof GeoPoint other)
                    && this.geometry.equals(other.geometry)
                    && this.point.equals(other.point);
        }

        /**
         * Function to return the GeoPoint in String form
         * @return GeoPoint in String form
         */

        @Override
        public String toString() {
            return geometry.toString()+point.toString();
        }


    }

}
