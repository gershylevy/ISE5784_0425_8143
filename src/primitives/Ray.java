package primitives;

/**this class will represent a line that has direction and a beginning but no end point
 * @author netanel grossman
 */
public class Ray {
    /**beginning of the line
     */
    final Point head;
    /**
     * vector representing direction ov line
     */
    final Vector direction;

    /**
     * constructor initializing head and direction
     * @param newHead the new value for head
     * @param newVector the new value for vector
     */
   public Ray(Point newHead, Vector newVector) {
       this.head=newHead;
       this.direction=newVector.normalize();
   }

    /**
     * function that checks if equal
     * @param dRay different ray that we are checking if the same
     * @return one or zero depending on if equal
     */
   public boolean equals(Ray obj) {

       if (this == obj) return true;
       return (obj instanceof Ray other)
               && this.head.equals(other.head)
               && this.direction.equals(other.direction);
   }

    /**
     * function creating a string out of our ray
     * @return a string with head and direction of ray
     */
   public String toString() {
       return head.toString() + "," + direction.toString();
   }


}
