//Main stuff here
package libBezierFiveLines;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import javax.swing.*;

public class DrawTheLuigi  extends Component implements MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;

	public boolean toggle_perl = true;
	
	public static Timer timer;
	private JFrame frame;
	private ActionListener animation;

	public static double toX =400;
	public static double toY= 400;

	private double mouseXC =0;
	private double mouseYC =0;

	public static boolean ball_go = false;
	private static boolean ball_kgo = false;

	public static int ball_y =395;
	private static int theSub =1;
	public static int ball_x =395;

	private int stick_sx = 500;
	private static int stick_cy = 500;
	private static int the_sub =1;

	public double w = DrawAsk.w;
	public double h = DrawAsk.h;
	double halfw = (w%200!=0) ? 200-(-1)*( w/2-200) : w/2;
	double startpoint = 200;
	double top =400-h;
	double bottom =400;

	private int count =0;

	private double top_curve =0;
	private double top_tri =0;
	private int c =0;

	private int initial =0;
	private int initialY=0;

	public DrawTheLuigi(JFrame frame) {
		this.frame = frame;
		addMouseListener(this);
		addMouseMotionListener(this);
		// for animation
		animation = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				repaint();
			}
		};
		timer = new Timer(50, animation);
	}

	//draw bunch of stuff
	public void paint(Graphics g) {
		getTopCurve();
		drawInformation(g);
		drawCurve(g);
		drawCoords(g);    	
		drawBall(g);
		drawNormalLineAndPathBall(g); 
		drawPathBounceBack(g);       
		drawStick(g);
		drawTangentLine(g);
		ballAndStickLogic();
	
	}
	
	//from here down are logic stuff

	// calculate the slope of the ball and the cue --> find the path of the ball and the cue
	private void ballAndStickLogic() {
		//if you click g
		if(ball_go) {
			// if the cue touch the ball at origin
			if (stick_cy <= 400) {
				// ball starts going; if ball touch the curve
				if ((ball_y) <=toY) {
					ball_go= false;
					ball_kgo = true;
				}else {
					//ball keep going til it hits the curve
					ball_y -= theSub; theSub+=1;
					// equation to find x according to y; shift right 395 and up 395 bc it is the ball's origin (using y = a(x+c)+b)
					ball_x =  (int) (((ball_y-395)*(400-toX))/Math.abs(400-toY)) + 395;
				}    
			} else {stick_cy -= the_sub; the_sub +=2;} // it will keep going until it hits the ball
		} else if (ball_kgo) {
			// if ball passes the origin in y; stops.
			if (ball_y > 400) {
				ball_kgo= false;
			}
			// ball bounce back -- this is the equation to find x; but i am not sure if this is the correct one
			ball_y+=theSub; theSub -=1;
			ball_x =  (int) (((ball_y - toX)/ (Math.pow(w,2))/(8*(h/2))) + toX-4);
		}
	}
    // reset everything, stop the timer -- animation.
	public static void reset() {
		timer.stop();
		ball_go = false;
		ball_kgo = false;
		ball_y = 395;
		ball_x = 395;
		stick_cy = 500;
		the_sub =1;
		theSub =1;
		toX =400;
		toY=400;
	}

	//find the vertex (y)
	private void getTopCurve() {
		if (DrawAsk.toogle_top) {
			top_curve = h/2;
			top_tri = h;
		} else {
			if (c ==0) {
				top_curve = h/2;
				top_tri= h;
				c=1;
			}

		}
	}
	// find y of the curve
	private double the_functino(double x) {
		return ((4)*top_curve/Math.pow(w,2))*Math.pow((x-400),2) + (400 - top_curve);
	}
	//calculate the derivative of the curve
	private double find_derivative(double x) {
		return (4)*(top_curve/Math.pow(w,2))*2*(x-400);
	}
	//calculate the line perpendicular to the tangent line
	private double find_perpend_tan(double x) {
		
		return -1/((4)*(top_curve/Math.pow(w,2))*2*(x-400));
	}
	
	private double find_interse(double x) {
		// 400 = find_perpend_tan(toX) x +b 
		double b = toY;
		return  ((-1)*(400 - b)/find_perpend_tan(toX)  + 400);
	}

	//draw the tangent line to the curve
	private void drawTangentLine(Graphics g) {
		//check if mouse touch the curve
		g.setColor(Color.BLACK);
		double suppose_y = the_functino(toX);
		if ((suppose_y >= (toY-2)) && (suppose_y <= (toY+2))) {
			System.out.println(find_interse(toX));
			((Graphics2D) g).draw(new Line2D.Double(toX,toY, 500000/find_perpend_tan(toX), 500000 ));
			//draw from x,y to infi
		((Graphics2D) g).draw(new Line2D.Double(toX,toY, 500000/find_derivative(toX), 500000 ));
		//draw from x,y to -infi
		((Graphics2D) g).draw(new Line2D.Double(toX,toY, -500000/find_derivative(toX), -500000 ));
		}
	}
	//from here down are graphics stuff
	
	private void drawInformation(Graphics g) {
		g.setColor(Color.RED);
		// equation of the two sides of the triangle
		g.drawString("i = ("+ h/100 +"/"+w/200+")(200-x) +"+w, 10,15 );
		g.drawString("j = ("+ h/100 +"/"+w/200+")(x+600) +"+ w, 10,30 );

		g.drawString("Use arrow keys or drag the screen up/down to change its height, width", 200,550);
		g.drawString("type \"a\" or click near the blue text (add/remove) to add more lines, \"s\" to remove lines and \"c\" to close the window", 50, 570);
		g.drawString(" type \"g\" or click at the origin to animate the ball", 280,585);
		g.drawString(" type \"t\" or click once to toggle changing height", 280,600);
		g.drawString(" type \"p\" or click once to toggle perpendicular line", 280,615);
		
		g.drawString("Top of the triangle (from 400 y): "+top_tri , 10,60);
		g.drawString("Top of the curve (from 400 y): "+ top_curve , 10,80);
		// mouse Coords
		g.drawString("X: "+mouseXC, 700,60);
		g.drawString("Y: "+mouseYC, 700,80);

		//equation of the curve and equation of the line 
		g.drawString("y = " + (4)*top_curve/Math.pow(w,2) + "(x-400)^2 + " + (400 - top_curve), 10,100 );
		g.drawString("y= "+ Math.abs(400-toY)/(400-toX) + "(x-400)" + "+ 400",10,115);
	}

	private void drawCoords(Graphics g) {
		g.setColor(Color.BLACK);
		//x and y axis
		g.drawLine(10, 400, 790, 400);
		g.drawLine(400, 10, 400, 790);
		//Mid triangle
		g.drawLine(200, 400, 400, 100);
		g.drawLine(400, 100, 600, 400);
	}

	//draw the ball
	private void drawBall(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillOval(ball_x,ball_y, 10,10);
	}

	private void drawNormalLineAndPathBall(Graphics g) {
		g.setColor(Color.BLACK);
		//path of the ball
		((Graphics2D) g).draw(new Line2D.Double(400,400,toX,toY));
		g.setColor(Color.GRAY);
		//line that is perpendicular to the path of the ball before it hits the curve -- using very large number to create 90 degree angle ( it will be less 90 if the number goes down)
		if(toggle_perl) {
		((Graphics2D) g).draw(new Line2D.Double(toX,toY, 500000/((-1)*(400 - toX)/Math.abs(400 - toY)) , 500000));
		((Graphics2D) g).draw(new Line2D.Double(toX,toY, -500000/((-1)*(400 - toX)/Math.abs(400 - toY)) , -500000));
		}
	}

	//draw the pink path -- when the ball hits the curve and bounce back
	private void drawPathBounceBack(Graphics g) {
		// this line responsible for the bounce back of the ball, but i am not sure if it is correct
		double the_slop= (toX*Math.pow(w,2))/(8*(h/2));
		g.setColor(Color.pink);
		//check if mouse touch the curve
		double suppose_y = the_functino(toX);
		if ((suppose_y >= (toY-2)) && (suppose_y <= (toY+2)))
		((Graphics2D) g).draw(new Line2D.Double(toX,toY,500000/the_slop , 500000));
	}

	//draw the cue using bunch of lines instead of rect because cant rotate
	private void drawStick(Graphics g) {
		// using the same slope as the path of the ball to the curve 
		g.setColor(Color.ORANGE);
		stick_sx = (int) (((stick_cy-395)*(400-toX))/Math.abs(400-toY)) + 395;
		for (int i=0; i< 5; i++)
			g.drawLine(stick_sx +i, stick_cy+i,  (int) (((stick_sx)*(400-toX))/Math.abs(400-toY)) + 395+i, stick_cy + 200+i);

	}

	// draw the curve using lines
	private void drawCurve(Graphics g) {
		Graphics2D g2 = (Graphics2D) g; 
		double i =top; 
		double j =bottom;
		g.setColor(Color.MAGENTA);
		// generate the curve by generating bunch of line; each line is i and j distance from each other
		while (i <bottom && j>top) {
			
			g2.draw( new Line2D.Double( ((-1)*(halfw*(i-400)/h) + 200+(-1)*(w/2-200))  ,(i+=DrawAsk.dis) , ((halfw*(j-400)/h)+400+(400-(200+(-1)*(w/2-200))))   , 	(j-=DrawAsk.dis) ));
			count ++;
		}
		//count number of lines
		g.setColor(Color.BLUE); 
		g.drawString("Number of Lines: "+count, 10, 45);
		count = 0;

	}
	
    //down here are listeners for the mouse
	@Override
	public void mouseDragged(MouseEvent arg0) {
		//find the coords of the mouse to display on the screen
		mouseXC = arg0.getX();
		mouseYC = arg0.getY();
		//reset everytime the mouse if clicked/dragged
		reset();
		//if user click mouse change
		if(DrawAsk.mousechange) {
			// allow for changing the curve horizontally using mouse
			if (arg0.getX() - initial <0) {
				w += 5;
				frame.repaint();
			} else {
				w -= 5;
				frame.repaint();
			}
            // changing the curve vertically
			if (arg0.getY() - initialY >0) {
				if (DrawAsk.toogle_top)  top =400-h; //if they toggle changing up and down
				h-=5;
			} else {
				if (DrawAsk.toogle_top)  top =400-h; // if not, change how the endpoints are met
				h+=5;
			}
		}	else {			
			//if user does not toggle mousechange --> allow doing simulation
			toX = arg0.getX();
			toY = arg0.getY();
		}
        //refresh the frame
		frame.repaint();
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		//get the coords in realtime
		mouseXC = arg0.getX();
		mouseYC = arg0.getY();
		frame.repaint();
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		//if user toggle mouse change
		if(DrawAsk.mousechange) {
			//click near the blue part to add/remove lines 
			if (arg0.getY() < 50 && arg0.getY()> 10 && arg0.getX() < 50 && arg0.getX() > 0) {
				DrawAsk.dis --;
				if (DrawAsk.dis <1) DrawAsk.dis =1;
			}
			else if (arg0.getY() < 50 && arg0.getY()> 10 && arg0.getX() > 50 && arg0.getX() < 100)
				DrawAsk.dis ++;

			else 
				DrawAsk.toogle_top = !DrawAsk.toogle_top;
		}
		else {
			//reset when mouse is clicked but not on origin and when mousechange is toggle
			if (!((Math.abs(400 - arg0.getX()) <=5)&&(Math.abs(400 - arg0.getY()) <=5)))
			reset();
			if((Math.abs(400 - arg0.getX()) <=5)&&(Math.abs(400 - arg0.getY()) <=5) ) {
				ball_go = true;
				timer.start();
			}else if(arg0.getButton() == MouseEvent.BUTTON1) {
				//if user does not toggle mouse change; if user left-click
				//get the coordinates of the left-click
				toX = arg0.getX();
				toY = arg0.getY();
			} else {
				//if user click something else that is not left click --> reset the coords of the line to the curve back to origin
				toX = 400;
				toY = 400;
			}

		}

		frame.repaint();
	}

	//kinda forget what this does
	@Override
	public void mousePressed(MouseEvent arg0) {
		if(DrawAsk.mousechange) {
			initial = arg0.getX();
			initialY = arg0.getY();
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}




}
