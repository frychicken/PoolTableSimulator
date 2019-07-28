//Main stuff here
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import javax.swing.*;

public class DrawTheLuigi  extends FunctionsLol implements MouseListener, MouseMotionListener  {
	private static final long serialVersionUID = 1L;

	private static double coordsx = 400;
	private static double coordsy = 400;
	private double coorx[] = new double[5];
	private double coory[] = new double[5];
	private double dapathx = 400;

	private final int supperBigNumber = 500000;
	private boolean check = true;
	public static Timer timer;

	private ActionListener animation;

	private static boolean ball_kgo = false;

	private static int ball_y =395;
	private static int theSub =1;
	private static int ball_x =395;

	private int stick_sx = 500;
	private static int stick_cy = 500;
	private static int the_sub =1;

	private double halfw = (w%200!=0) ? 200-(-1)*( w/2-200) : w/2;
	private double bottom =400;

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

	//draw on screen button
	private void initComponents(Graphics g) {
	     drawButtons(g);
	}

	//draw the whole interface
	@Override
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
		initComponents(g);
		ballAndStickLogic();
		getPathSlope();
		writedalog();
	}

	// calculate the slope of the ball and the cue --> find the path of the ball and the cue
	private void ballAndStickLogic() {
		//if the ball moves
		if(ball_go) {
			// if the cue touch the ball
			if (stick_cy <= 400) {
				// ball keeps going until touches the curve
				if ((ball_y) <=toY) {
					ball_go= false;
					ball_kgo = true;
				}else {
					ball_y -= theSub; theSub+=1;
					// equation to find x according to y; shift right 395 and up 395 
					ball_x =  (int) (((ball_y-395)*(400-toX))/Math.abs(400-toY)) + 395;
				}    
			} else {stick_cy -= the_sub; the_sub +=2;} 
		} else if (ball_kgo) {
			// if ball passes 400, stops.
			if (ball_y > 400) {
				ball_kgo= false;
			}
			// ball bounce back 
			ball_y+=theSub; theSub -=1;
			//calculate the path using slope-line equation
			ball_x =  (int) (((ball_y - toY)/((coordsy-toY)/(coordsx - toX))) + toX-7);
		}
	}
	// reset setup.
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
		//dapathx = 400;
	}
	//reset the ball
	public void resetB() {
		timer.stop();
		ball_y = 395;
		ball_x = 395;
		ball_go = false;
		ball_kgo = false;
		stick_cy = 500;
		the_sub =1;
		theSub =1;
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
	//write log
	private void writedalog() {
		if (ball_go && check) {
			System.out.println("Equation of i of the curve:"+"i = ("+ (h/100)/(w/200)+")(200-x) +"+w);
			wl.writeLog("Equation of i of the curve:"+"i = ("+ (h/100)/(w/200)+")(200-x) +"+w);
			System.out.println("Equation of j of the curve:"+"j = ("+ (h/100)/(w/200)+")(x+600) +"+ w );
			wl.writeLog("Equation of j of the curve:"+"j = ("+ (h/100)/(w/200)+")(x+600) +"+ w );
			System.out.println("Equation of the curve: y = " + (4)*top_curve/Math.pow(w,2) + "(x-400)^2 + " + (400 - top_curve));
			wl.writeLog("Equation of the curve: y = " + (4)*top_curve/Math.pow(w,2) + "(x-400)^2 + " + (400 - top_curve));
			System.out.println("dapathx appears to be: " + dapathx);
			wl.writeLog("dapathx appears to be: " + dapathx);
			System.out.println("coordsx appears to be: " + coordsx);
			wl.writeLog("coordsx appears to be: " + coordsx);
			System.out.println("coordsy appears to be: " + coordsy);
			wl.writeLog("coordsy appears to be: " + coordsy);
			check = false;
		}
		if (!ball_go) {
			check = true;
		}
	}
	//calculate the cue's x
	private int getCueX() {
		return (int) (((stick_sx)*(400-toX))/Math.abs(400-toY));
	}

	// find y of the bezier curve from its equation y = a(x+c)^2 +b
	private double the_functino(double x) {
		return ((4)*top_curve/Math.pow(w,2))*Math.pow((x-400),2) + (400 - top_curve);
	}
	//calculate the derivative of the curve
	private double find_derivative(double x) {
		return (4)*(top_curve/Math.pow(w,2))*2*(x-400);
	}
	//calculate the line perpendicular to the tangent line to the curve
	private double find_perpend_tan(double x) {
		return -1/((4)*(top_curve/Math.pow(w,2))*2*(x-400));
	}
	// calculate the reflection line by finding the length between perpend line and projected line
	private void getPathSlope() {
		//calculating the intersection of the path and 400 (y)
		dapathx = (395 - toY)/( (supperBigNumber-toY)/((supperBigNumber/find_perpend_tan(toX))-toX)) + toX -4;
		//slope line perpendicular to the path
		double hps = (supperBigNumber-toY)/(supperBigNumber/((-1)*(400 - toX)/Math.abs(400 - toY)) - toX);
		// the slope of the normal line to the tangent line to the curve
		double fpt = ( (supperBigNumber-toY)/((supperBigNumber/find_perpend_tan(toX))-toX));
		// the slope of the tangent line
		double fd = ( (supperBigNumber-toY)/((supperBigNumber/find_derivative(toX))-toX));
		// find the x where they intersect
		double testx = (fpt*(toX-4) - fd*(395) + 395 - toY)/(fpt - fd);
		// find the y where they intersect
		double testy = fd*(testx - 395) + 395;
		double lengthd = 250;
		// calculating the length 400,400 to the normal line
		double length = Math.sqrt( Math.pow( (395 - testx), 2) + Math.pow( (395 - testy), 2));
		// calculate the x and y of the predicted path of the ball knowing the slope, length between points and the starting coords
		// since it gives two x's and y's


		if (dapathx >= 400) {
			// perpendicular to the curve
			coorx[0] = toX - lengthd*Math.sqrt(1/(1+ Math.pow(fpt, 2)));
			coory[0] = toY - fpt*lengthd*Math.sqrt(1/(1+ Math.pow(fpt, 2)));

			//tangent line
			coorx[1] = toX + lengthd*Math.sqrt(1/(1+ Math.pow(fd, 2)));
			coory[1] = toY + fd*lengthd*Math.sqrt(1/(1+ Math.pow(fd, 2)));
			coorx[2] = toX - lengthd*Math.sqrt(1/(1+ Math.pow(fd, 2)));
			coory[2] = toY - fd*lengthd*Math.sqrt(1/(1+ Math.pow(fd, 2)));
			// perpendicular to the path of the ball
			coorx[3] = toX - lengthd*Math.sqrt(1/(1+ Math.pow(hps, 2)));
			coory[3] = toY - hps*lengthd*Math.sqrt(1/(1+ Math.pow(hps, 2)));
			coorx[4] = toX + lengthd*Math.sqrt(1/(1+ Math.pow(hps, 2)));
			coory[4] = toY + hps*lengthd*Math.sqrt(1/(1+ Math.pow(hps, 2)));
			//reflection path
			coordsx = testx + length*Math.sqrt(1/(1+ Math.pow(fd, 2)));
			coordsy = testy + fd*length*Math.sqrt(1/(1+ Math.pow(fd, 2)));
		} 
		else {
			coorx[0] = toX + lengthd*Math.sqrt(1/(1+ Math.pow(fpt, 2)));
			coory[0] = toY + fpt*lengthd*Math.sqrt(1/(1+ Math.pow(fpt, 2)));
			if ((400 - dapathx <= 6.5) && toX >= 400) {
				coorx[0] = toX- lengthd*Math.sqrt(1/(1+ Math.pow(fpt, 2)));
				coory[0] = toY - fpt*lengthd*Math.sqrt(1/(1+ Math.pow(fpt, 2)));
			}
			coorx[1] = toX - lengthd*Math.sqrt(1/(1+ Math.pow(fd, 2)));
			coory[1] = toY - fd*lengthd*Math.sqrt(1/(1+ Math.pow(fd, 2)));
			coorx[2] = toX + lengthd*Math.sqrt(1/(1+ Math.pow(fd, 2)));
			coory[2] = toY + fd*lengthd*Math.sqrt(1/(1+ Math.pow(fd, 2)));

			coorx[3] = toX + lengthd*Math.sqrt(1/(1+ Math.pow(hps, 2)));
			coory[3] = toY + hps*lengthd*Math.sqrt(1/(1+ Math.pow(hps, 2)));
			coorx[4] = toX - lengthd*Math.sqrt(1/(1+ Math.pow(hps, 2)));
			coory[4] = toY - hps*lengthd*Math.sqrt(1/(1+ Math.pow(hps, 2)));

			coordsx = testx - length*Math.sqrt(1/(1+ Math.pow(fd, 2)));
			coordsy = testy - fd*length*Math.sqrt(1/(1+ Math.pow(fd, 2)));	
		}

	}

	//draw the tangent line to the curve
	private void drawTangentLine(Graphics g) {
		//check if mouse touch the curve
		if(nightmode) {
			g.setColor(Color.WHITE);

		}else
			g.setColor(Color.BLACK);
		double suppose_y = the_functino(toX);
		if ((suppose_y >= (toY-2)) && (suppose_y <= (toY+2)) && toggle_perl) {		
			// perpendicular line
			g.setColor(Color.blue);

			((Graphics2D) g).draw(new Line2D.Double(toX,toY, coorx[0], coory[0] ));
			//draw from x,y to infi
			g.setColor(Color.RED);
			((Graphics2D) g).draw(new Line2D.Double(toX,toY, coorx[1], coory[1] ));
			//draw from x,y to -infi
			((Graphics2D) g).draw(new Line2D.Double(toX,toY, coorx[2], coory[2] ));
		}
	}
	//graphics stuff
	private void drawInformation(Graphics g) {
		g.setColor(Color.RED);
		if(!hideHelpBox) {
			g.drawString("Use arrow keys or drag (mousechange) its height, width", 30,440);
			g.drawString(" type \"p\" or click        to toggle changing projected path", 30, 455);
			g.drawString(" type \"a\", \"s\" to add, remove lines and \"c\" to close", 30, 470);
			g.drawString(" type \"g\" or click at the origin to animate the ball", 30,485);
			g.drawString(" type \"t\" or click        to toggle changing height", 30,500);
			g.drawString(" type \"d\" or click        to toggle debug mode", 30,515);
			g.drawString(" type \"m\" to toggle mouse change", 30,530);
			g.drawString(" type \"n\" or click        to toggle nightmode", 30,545);
			g.drawString(" type \"r\" or click        to reset curve", 30,560);
			g.drawString(" type \"h\" or click        to hide help box", 30,575);
		}
		if (toggle_perl) {
			// equation of the two sides of the triangle
			g.drawString("coordsx, coordsy appears to be: " + Math.round(coordsx) +", "+Math.round(coordsy), 10,15 );
			g.drawString("dapathx appears to be: " + Math.round(dapathx), 10,30 );

			g.drawString("Top of the triangle (from 400 y): "+top_tri , 10,60);
			g.drawString("Top of the curve (from 400 y): "+ top_curve , 10,80);
			// mouse Coords
			g.drawString("X: "+mouseXC, 700,60);
			g.drawString("Y: "+mouseYC, 700,80);

			//equation of the curve and equation of the line 
			g.drawString("y = " + (4)*top_curve/Math.pow(w,2) + "(x-400)^2 + " + (400 - top_curve), 10,100 );
			g.drawString("y= "+ Math.abs(400-toY)/(400-toX) + "(x-400)" + "+ 400",10,120);
			
	        g.drawString("i = ("+ (h/100)/(w/200)+")(200-x) +"+w, 10,140 );
            g.drawString("j = ("+ (h/100)/(w/200)+")(x+600) +"+ w, 10,160 );
		}
	}

	private void drawCoords(Graphics g) {
		if(nightmode) {
			g.setColor(Color.GRAY);

		}else
			g.setColor(Color.BLACK);

		//x and y axis
		g.drawLine(10, 400, 790, 400);
		if(toggle_perl) {
			g.drawLine(400, 10, 400, 790);
			//Mid triangle
			g.drawLine(200, 400, 400, 100);
			g.drawLine(400, 100, 600, 400);
		}
	}

	//draw the ball
	private void drawBall(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillOval(ball_x,ball_y, 10,10);
	}

	private void drawNormalLineAndPathBall(Graphics g) {
		if(nightmode) {
			g.setColor(Color.WHITE);

		}else
			g.setColor(Color.BLACK);
		//path of the ball
		((Graphics2D) g).draw(new Line2D.Double(400,400,toX,toY));
		g.setColor(Color.LIGHT_GRAY);
		//line that is perpendicular to the path of the ball before it hits the curve -- using very large number to create 90 degree angle ( it will be less 90 if the number goes down)
		if(toggle_perl) {
			((Graphics2D) g).draw(new Line2D.Double(toX,toY, coorx[3]  , coory[3] ));
			((Graphics2D) g).draw(new Line2D.Double(toX,toY, coorx[4] , coory[4]));
		}
	}

	//draw the pink path -- when the ball hits the curve and bounce back
	private void drawPathBounceBack(Graphics g) {
		// this line responsible for the bounce back of the ball
		//double the_slop= (toX*Math.pow(w,2))/(8*(h/2));
		g.setColor(Color.pink);
		//check if mouse touch the curve
		double suppose_y = the_functino(toX);
		if ((suppose_y >= (toY-2)) && (suppose_y <= (toY+2)))
			((Graphics2D) g).draw(new Line2D.Double(toX,toY, coordsx , coordsy));
		// ((Graphics2D) g).draw(new Line2D.Double(toX,toY,500000/the_slop , 500000));
	}

	//draw the cue using bunch of lines instead of rect because rect cant rotate
	private void drawStick(Graphics g) {
		// using the same slope as the path of the ball to the curve 
		stick_sx = (int) (((stick_cy-395)*(400-toX))/Math.abs(400-toY)) + 395;
		g.setColor(Color.ORANGE);
		for (int i=0; i< 5; i++)
			g.drawLine(stick_sx +i, stick_cy+i,  getCueX() + 395+i, stick_cy + 200+i);

	}

	// draw the curve using lines
	private void drawCurve(Graphics g) {
		Graphics2D g2 = (Graphics2D) g; 
		double i =top; 
		double j =bottom;
		g.setColor(Color.MAGENTA);
		// generate the curve by generating bunch of line; each line is i and j distance from each other
		while (i <bottom && j>top) {

			g2.draw( new Line2D.Double( ((-1)*(halfw*(i-400)/h) + 200+(-1)*(w/2-200))  ,(i+=dis) , ((halfw*(j-400)/h)+400+(400-(200+(-1)*(w/2-200))))   , 	(j-=dis) ));
			count ++;
		}
		//count number of lines
		g.setColor(Color.BLUE); 
		g.drawString("Number of Lines: "+count, 10, 45);
		count = 0;

	}

	//listeners for the mouse
	@Override
	public void mouseDragged(MouseEvent arg0) {
		resetB();
		//find the coords of the mouse to display on the screen
		mouseXC = arg0.getX();
		mouseYC = arg0.getY();

		//if user click mouse change
		if(mousechange) {
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
				if (toogle_top)  top =400-h; //if they toggle changing up and down
				h-=5;
			} else {
				if (toogle_top)  top =400-h; // if not, change how the endpoints are met
				h+=5;
			}
		}	else if ( arg0.getY() <=400){			
			//if user does not toggle mousechange --> allow simulation
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
		resetB();
		clicked = true;
		//if user toggle mouse change
		if(mousechange) {
			//click near the blue part to add/remove lines 
			toogle_top = !toogle_top;
			//reset when mouse is clicked but not on origin and when mousechange is toggle
			if (!((Math.abs(400 - arg0.getX()) <=5)&&(Math.abs(400 - arg0.getY()) <=5)))
				reset();
		}
		else {
			if(isgclicked(arg0.getX(),arg0.getY() )) {clickg();} 
			else if (isupclicked(arg0.getX(), arg0.getY())) {clickArrowUp();}
			else if (isdownclicked(arg0.getX(), arg0.getY())) {clickArrowDown();}
			else if (isleftclicked(arg0.getX(), arg0.getY())) {clickArrowLeft();}
			else if (isrightclicked(arg0.getX(), arg0.getY())) {clickArrowRight();}
			else if (isaclicked(arg0.getX(), arg0.getY())) {clicka();}
			else if (issclicked(arg0.getX(), arg0.getY())) clicks();
			else if((arg0.getButton() == MouseEvent.BUTTON1) && arg0.getY() <=400) {
				//if user does not toggle mouse change; if user left-click
				//get the coordinates of the left-click
				toX = arg0.getX();
				toY = arg0.getY();
			} else if (arg0.getButton() == MouseEvent.BUTTON3){
				//if user click something else that is not left click --> reset the coords of the line to the curve back to origin
				toX = 400;
				toY = 400;
			}

		}

		if(ispclicked(arg0.getX(), arg0.getY())) {clickp();}
		if(isdclicked(arg0.getX(), arg0.getY())) {clickd();}
		if(istclicked(arg0.getX(), arg0.getY())) {clickt();}
		if(isrclicked(arg0.getX(), arg0.getY())) {clickr();}
		if(ishclicked(arg0.getX(), arg0.getY())) {clickh();}
		if(isnclicked(arg0.getX(), arg0.getY())) {clickn();}

		frame.repaint();
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		clicked = true;
		if(mousechange) {
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
