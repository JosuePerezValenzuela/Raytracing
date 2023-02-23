
package RayTracer;


public class VectorRay {
    public Point3D origin;
    public Vector3D direction;
    
    public VectorRay(){
        origin=new Point3D();
        direction=new Vector3D();
    }
    
    public VectorRay(Point3D origin, Vector3D direction){
        this.origin=new Point3D(origin);
        this.direction=new Vector3D(direction);
    }
}
