
package RayTracer;

import java.awt.Color;


public class Sphere extends Geometria {
    public Point3D center;
    public double radius;
   
    //public Color colorGeometria;
    public Sphere(Point3D center, double radius,Color color){
        this.center=new Point3D(center);
        this.radius=radius;
        this.color=color;
    }
    public Point3D getCenter (){
        return center;
   }
    public int getType(){
       return 2;
   }
    
    public double hit (VectorRay ray){
        double a=ray.direction.dot(ray.direction);
        double b=2*ray.origin.sub(center).dot(ray.direction);
        double c=ray.origin.sub(center).dot(ray.origin.sub(center))-radius*radius;
        double discriminant=b*b-4*a*c;
        if (discriminant<0.0){
            return 0.0;
        }else{
            double t=(-b-Math.sqrt(discriminant))/(2*a);
            if (t>10E-9){
                return t;
            }else{
                return 0.0;
            }
        }
    }

    
}
