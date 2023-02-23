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
public abstract class Geometria {
    public Color color;
    
    
    public abstract double hit (VectorRay ray);
    public abstract Point3D getCenter ();
    public abstract int getType();
}
