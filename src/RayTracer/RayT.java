
package RayTracer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import static java.lang.Math.abs;
import static java.lang.Math.round;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class RayT extends JPanel {
    
    private BufferedImage canvas;
    
    public  RayT(int width, int height, Point3D a,Point3D l, ArrayList<Geometria> b) {
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
         
        fillCanvas(Color.DARK_GRAY);
        Color colorCerca=Color.BLACK;
        Point3D pointHit=new Point3D();
        Point3D pointCenter=new Point3D();
        int number=0;
        int color3=0;
        for (int x = 0; x < canvas.getWidth(); x++) {
            for (int y = 0; y < canvas.getHeight(); y++) {
                boolean hit=false;
                
                double min =Double.MAX_VALUE;
                Vector3D direccion=vectorDireccion(a,new Point3D (x,y,0.0));
                VectorRay vect=new VectorRay(a,direccion); 
                
                for(int i=0;i<b.size();i++){
                    double distancia=b.get(i).hit(vect);
                    
                    if (distancia !=0.0&& distancia <min){
                        min=distancia;
                        hit=true;
                        colorCerca=b.get(i).color;
                        pointHit= vect.origin.add(vect.direction.star(distancia));
                        pointCenter=b.get(i).getCenter();
                        number=b.get(i).getType();                        
                    }
                }
                if (hit){
                    double cosTiata=0.0;
                    if (number==1){
                        color3=colorCerca.getRGB();
                        Vector3D u= vectorDireccion(pointHit,l);
                        Vector3D v=new Vector3D(0.0,-1.0,.0);
                        cosTiata=u.dot(v)/(u.magnitude()*v.magnitude());
                        int reducirIntensidad=(int) ((cosTiata-1)/2*255);
                            
                        int red=colorCerca.getRed()+reducirIntensidad;
                        int green=colorCerca.getGreen()+reducirIntensidad;
                        int blue=colorCerca.getBlue()+reducirIntensidad;
                        if (red<0) red=0;
                        if (green<0) green=0;
                        if (blue<0) blue=0;
                        color3=getIntFromColor(red, green,blue);
                                                
                    }else{
                        Vector3D u= vectorDireccion(pointHit,l);
                        Vector3D v=vectorDireccion(pointCenter,pointHit);
                        cosTiata=u.dot(v)/(u.magnitude()*v.magnitude());
                        int reducirIntensidad=(int) ((cosTiata-1)/2*255);
                            
                        int red=colorCerca.getRed()+reducirIntensidad;
                        int green=colorCerca.getGreen()+reducirIntensidad;
                        int blue=colorCerca.getBlue()+reducirIntensidad;
                        if (red<0) red=0;
                        if (green<0) green=0;
                        if (blue<0) blue=0;
                        color3=getIntFromColor(red, green,blue);
                    }
                    
                    
                    canvas.setRGB(x, y, color3);
                    Vector3D u= vectorDireccion(pointHit,l);
                    VectorRay vect2=new VectorRay(pointHit,u);
                    for(int i=0;i<b.size();i++){
                        double distancia=b.get(i).hit(vect2);
                        if (distancia !=0.0&& distancia <min){
                            canvas.setRGB(x,y, Color.GRAY.getRGB());                        
                        }
                    }
                }
                    
            }
        }
        
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(canvas.getWidth(), canvas.getHeight());
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(canvas, null, null);
    }
    public void fillCanvas(Color c) {
        int color = c.getRGB();
        for (int x = 0; x < canvas.getWidth(); x++) {
            for (int y = 0; y < canvas.getHeight(); y++) {
                canvas.setRGB(x, y, color);
            }
        }
        repaint();
    }
  
    public int getIntFromColor(int Red, int Green, int Blue){
    Red = (Red << 16) & 0x00FF0000; 
    Green = (Green << 8) & 0x0000FF00;
    Blue = Blue & 0x000000FF;

    return 0xFF000000 | Red | Green | Blue;
}
    
    public Vector3D vectorDireccion (Point3D inicio, Point3D fin){
        Vector3D vectorUnitario= fin.sub(inicio);
        vectorUnitario.normalize();
        return vectorUnitario;
    }
    public static void main(String[] args) {
        int width = 1400;
        int height = 900;
        Point3D camara=new Point3D(700.0,350.0,-600.0);
        //                                     -100
        Point3D light=new Point3D(700.0,150.0,-50.0);
        ArrayList<Geometria> objeto = new ArrayList();
        Color colorSphere = Color.MAGENTA;
        Color colorSphere2 = Color.CYAN;
        Color colorSphere3 = Color.YELLOW;
        Color colorPlane = Color.white;
        //                                   400   350   46    55
        Sphere sphere=new Sphere(new Point3D(200.0,350.0,200.0),100.0,colorSphere);
        //                                    650   350   -20  80
        Sphere sphere2=new Sphere(new Point3D(700.0,350.0, 0.0),100.0,colorSphere2);
        //                                    900    350   45   55
        Sphere sphere3=new Sphere(new Point3D(900.0,350.0,-200.0),100.0,colorSphere3);
        Plane plane=new Plane(new Point3D(100.0,450,45.0),light,new Vector3D(0.0,1.0,.0),colorPlane);
        objeto.add(plane);
        objeto.add(sphere);
        objeto.add(sphere2);
        objeto.add(sphere3);
        JFrame frame = new JFrame("RayTracing");

        RayT panel = new RayT(width, height,camara,light,objeto);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
