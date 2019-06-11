package libBezierFiveLines;

import java.awt.*;
import java.awt.geom.Line2D;
import javax.swing.*;

public class DrawTheLuigi  extends JPanel  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	double w = DrawAsk.w;
    double h = DrawAsk.h;
    double halfw = (w%200!=0) ? 200-(-1)*( w/2-200) : w/2;
    double startpoint = 200;
    double top =400-h;
    double bottom =400;
    
    double x = 400, x3 =400+(400-(200+(-1)*(w/2-200))) , x2 = 400,x1 = 200+(-1)*(w/2-200) ,y1 = 400,y2 = 400-h,y3 = 400;
    double t =  (-1*(-2*x1-2*x2) + Math.sqrt(Math.pow((-2*x1-2*x2),2) - 4*(x1-2*x2+x3)*(x1-x)))/2*(x1-2*x2+x3);
    double y = Math.pow((1-t),2)*y1 +2*(1-t)*t*y2 +Math.pow(t,2)*y3;
    
    public void paint(Graphics g) {
    	System.out.println(t);
              g.drawLine(10, 400, 790, 400);
              g.drawLine(400, 10, 400, 790);
              // B(t)=(1-t)^2.P0 + 2(1-t).t.P1 + t^2.P2
              // x = (1-t)^2x1 + 2(1-t)tx2 + t^2x3
              //y = (1-t)^2y1 + 2(1-t)ty2 + t^2y3
              // 400 = ((1-t)^2)*(200+(-1)*(w/2-200)) + 2(1-t)t400 +(t^2)*(400-(200+(-1)*(w/2-200)))
              //t = (-(-2x1-2x2) +- sqrt((-2x1-2x2)^2 - 4(x1-2x2+x3)(x1-x))/2(x1-2x2+x3)
              //y (i) = 3/2(x + 200) + 400; y' (j) = -3/2(x-200) + 400 
              // i = 3/2(x+w/2) + 400
              // w = 400; h = 300
              g.setColor(Color.RED);
              g.drawString("i = ("+ h/100 +"/"+w/200+")(200-x) +"+w, 10,15 );
              g.drawString("j = ("+ h/100 +"/"+w/200+")(x+600) +"+ w, 10,30 );
              g.setColor(Color.BLACK);
              g.drawLine(200, 400, 400, 100);
              g.drawLine(400, 100, 600, 400);
              
              Graphics2D g2 = (Graphics2D) g; 
             double i =top; 
             double j =bottom;
             while (i <bottom && j>top) {
            	 
                       g2.draw( new Line2D.Double( -1*(halfw*(i-400)/h) + 200+(-1)*(w/2-200), i+=DrawAsk.dis,  (halfw*(j-400)/h)+400+(400-(200+(-1)*(w/2-200))), j-=DrawAsk.dis));
            		 

             }

                    
                  

    }
    

}
