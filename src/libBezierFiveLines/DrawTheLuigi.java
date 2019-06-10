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
    double halfw = (w%200!=0) ? 200-( w/2-200) : w/2;
    double startpoint = 200;
    double top =400-h;
    double bottom =400;
    
    
    public void paint(Graphics g) {
              g.drawLine(10, 400, 790, 400);
              g.drawLine(400, 10, 400, 790);
              
              //y (i) = 3/2(x + 200) + 400; y' (j) = -3/2(x-200) + 400 
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
             	// the flip
             	if(w > 400)
                     g2.draw( new Line2D.Double( -1*(halfw*(j-w)/h) + startpoint, i+=DrawAsk.dis,  (halfw*(i-w)/h)+400+(400-startpoint), j-=DrawAsk.dis));

                  else
                   g2.draw( new Line2D.Double( -1*(halfw*(i-w)/h) + startpoint, i+=DrawAsk.dis,  (halfw*(j-w)/h)+400+(400-startpoint), j-=DrawAsk.dis));
             }

                    
                  

    }
    

}
