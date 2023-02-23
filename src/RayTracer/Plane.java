/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RayTracer;

import java.awt.Color;

/**
 *
 * @author PC
 */
public class Plane extends Geometria {
    Point3D point;
    Point3D light;
    Vector3D normal;
    public Color colorGeometria;
    
     
    
   
   public Plane(Point3D point,Point3D light, Vector3D normal,Color color){
       this.point=new Point3D (point);
       this.light=new Point3D (light);        
       this.normal=new Vector3D (normal);
       this.color=color;
       
   }
   public int getType(){
       return 1;
   }
   public Point3D getCenter (){
        return point;
   }
   public double hit(VectorRay ray){
       double t=point.sub(ray.origin).dot(normal)/ray.direction.dot(normal);
       if (t>10E-9){
           return t; 
       }else{
           return 0.0;
       }
       
   }
}
