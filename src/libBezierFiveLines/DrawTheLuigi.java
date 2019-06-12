package libBezierFiveLines;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import javax.swing.*;

public class DrawTheLuigi  extends JPanel implements MouseListener, MouseMotionListener {
	/**
	 * 
	 */
	JFrame frame;
	public DrawTheLuigi(JFrame frame) {
		this.frame = frame;
	    addMouseListener(this);
	    addMouseMotionListener(this);
	}
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
    int count =0;
    public void paint(Graphics g) {

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
              g.drawString("Use arrow keys or drag the screen up/down to change its height, width", 250,650);
              g.drawString("type \"a\" to add more lines, \"s\" to remove lines and \"c\" to close the window", 250, 670);
              g.drawString(" type \"t\" or click once to toggle changing height", 280,700);

            
              g.setColor(Color.BLACK);
              g.drawLine(200, 400, 400, 100);
              g.drawLine(400, 100, 600, 400);
              
              Graphics2D g2 = (Graphics2D) g; 
             double i =top; 
             double j =bottom;
             
             while (i <bottom && j>top) {
                       g2.draw( new Line2D.Double( -1*(halfw*(i-400)/h) + 200+(-1)*(w/2-200), i+=DrawAsk.dis,  (halfw*(j-400)/h)+400+(400-(200+(-1)*(w/2-200))), j-=DrawAsk.dis));
            	       count ++;
             }
             g.setColor(Color.BLUE); 
             g.drawString("Number of Lines: "+count, 10, 45);
                  count = 0;

    }
int initial =0;
int initialY=0;
	@Override

	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	  if (arg0.getX() - initial <0) {
		  w += 5;
		  frame.repaint();
	  } else {
		  w -= 5;
		  frame.repaint();
	  }
	  
	  if (arg0.getY() - initialY >0) {
		  if (DrawAsk.toogle_top)  top =400-h;
		  h-=5;
		  frame.repaint();
	  } else {
		  if (DrawAsk.toogle_top)  top =400-h;
		  h+=5;
		  frame.repaint(); 
	  }

	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
          DrawAsk.toogle_top = !DrawAsk.toogle_top;
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		initial = arg0.getX();
		initialY = arg0.getY();
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
    

}
