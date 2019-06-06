package libBezierFiveLines;

import java.awt.*;
import java.awt.geom.Line2D;

import javax.swing.*;

public class DrawTheLuigi  extends JPanel  {
    int w =400;
    int h = 300;
    double halfw = w/2;
    double startpoint = 200;
    int top =400-h;
    int bottom =400;
    public void paint(Graphics g) {
              g.drawLine(10, 400, 790, 400);
              g.drawLine(400, 10, 400, 790);
              
              //y (i) = 3/2(x + 200) + 400; y' (j) = -3/2(x-200) + 400 
              // w = 400; h = 300
              g.drawLine(200, 400, 400, 100);
              g.drawLine(400, 100, 600, 400);
              
              Graphics2D g2 = (Graphics2D) g; 
             int i =top; 
             int j =bottom;
                while (i <bottom && j>top)
                      g2.draw( new Line2D.Double( (-1*halfw*(i-w)/h) + startpoint, i++,  (halfw*(j-w)/h)+400+(400-startpoint), j--));
                    
                  

    }
    

}
