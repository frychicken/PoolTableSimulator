import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JFrame;

//basically all functions that both DrawAsk and DrawTheLuigi need
abstract class FunctionsLol extends Component{
	private static final long serialVersionUID = 1L;
	WriteLogF wl = new WriteLogF();
	protected JFrame frame;
	protected static double w =400;
	protected static double h =300;
	protected static double dis =10;
	protected static boolean mousechange = false;
	protected static boolean toogle_top = false;
	protected static boolean toggle_perl = false;
	protected static boolean nightmode = false;
	protected static boolean toggle_pro = false;
	protected static double toX =400;
	protected static double toY= 400;
	protected static double top =400-h;
	protected static boolean hideHelpBox = false;
	protected static boolean ball_go = false;
	protected static double mouseXC =0;
	protected static double mouseYC =0;
	protected boolean clicked = false;	
	
	protected void clickArrowLeft() {
		System.out.println("Clicked left arrow");
		wl.writeLog("Clicked left arrow");
		if(toggle_pro && !toogle_top)
			toX-=7;
		else
			w -=  10;
	}
	protected void clickArrowRight() {
		System.out.println("Clicked right arrow");
		wl.writeLog("Clicked right arrow");
		if(toggle_pro && !toogle_top)
			toX+=7;
		else
			w +=  10;
	}
	protected void clickArrowDown() {
		if (toogle_top) {
			h -=  10;
			top = 400 - h;	
		} else if(toggle_pro) {
			toY+=7;
			 if (toY > 400) toY = 400;
		}else{
			h -=  10;
		}
		System.out.println("Clicked down arrow while toggle top = "+ toogle_top);
		wl.writeLog("Clicked down arrow while toggle top = "+ toogle_top);

	}
	protected void clickArrowUp() {
		if (toogle_top) {
			h +=  10;
			top = 400 - h;	
		} else 	if(toggle_pro) {
			toY-=7;  
		}
		else {
			h +=  10;
		}
		System.out.println("Clicked up arrow while toggle top = "+ toogle_top + " toggle pro="+toggle_pro);
		wl.writeLog("Clicked up arrow while toggle top = "+ toogle_top+ " toggle pro="+toggle_pro);
	}
	protected void clickg() {
		System.out.println("Clicked g");
		wl.writeLog("Clicked g");
		ball_go = true;
		DrawTheLuigi.timer.start();
	}
	protected void clickp() {
		toggle_pro = !toggle_pro;
		toogle_top = false;
		System.out.println("Clicked p makes toggle_pro = "+toggle_pro);
		wl.writeLog("Clicked p makes toggle_pro = "+toggle_pro);
	}
	protected void clickt() {
		toogle_top = !toogle_top;
		System.out.println("Clicked t makes toggle top = "+ toogle_top);
		wl.writeLog("Clicked t makes toggle top = "+toogle_top);
	}

	protected void clicks() {
		dis +=1;
		System.out.println("Clicked s makes dis = " +dis);
		wl.writeLog("Clicked s makes dis =" + dis);
	}
	protected void clicka() {
		dis -=1;
		if (dis < 1) dis = 1;
		System.out.println("Clicked a makes dis = " + dis);
		wl.writeLog("Clicked a makes dis = " + dis);
	}

	protected void clickd() {
	    toggle_perl = !toggle_perl;
		System.out.println("Clicked d makes debug mode = " + toggle_perl);
		wl.writeLog("Clicked d makes debug mode = "+ toggle_perl);
	}
	protected void clickr() {
		w = 400;
		h=300;
		System.out.println("Clicked r makes w, h = " + w + ", "+h);
		wl.writeLog("Clicked r makes w, h = " + w + ", "+h);
	}
	protected void clickh() {
		hideHelpBox = !hideHelpBox;
	}
	protected void clickm() {
		mousechange = !mousechange;
		System.out.println("Clicked m makes mousechange = "+ mousechange);
		wl.writeLog("Clicked m makes mousechange = "+ mousechange);
	}
	protected void clickn() {
		nightmode = !nightmode;
		System.out.println("Clicked n makes nightmode = "+ nightmode);
		wl.writeLog("Clicked n makes nightmode = "+ nightmode);
		if(nightmode) {
			frame.getContentPane().setBackground(Color.BLACK);  
		} else {
			frame.getContentPane().setBackground(Color.WHITE);  

		}
	}
	protected void clickc() {
		System.out.println("Clicked c");
		wl.writeLog("Clicked c");
		frame.setVisible(false);
		frame.dispose();
		
	}
	protected boolean isgclicked(int x, int y) {
		if(((Math.abs(400 - x) <=5)&&(Math.abs(400 - y) <=5)) || (  ((x > 680) && (x  < 680 +50)) && ((y  > 560) && (y < 560 +50))    )) {
			return true;
		}
		return false;
	}
	protected boolean isupclicked(int x, int y) {
		if(((x > 680) && (x < 680 +50)) && ((y > 430) && (y < 430 +50))) {
			return true;
		}
		return false;
	}
	protected boolean isdownclicked(int x, int y) {
		if(((x > 680) && (x< 680 +50)) && ((y> 480) && (y < 480 +50))) {
			return true;
		}
		return false;
	}
	protected boolean isleftclicked(int x, int y) {
		if(((x > 630) && (x < 630 +50)) && ((y > 480) && (y < 480 +50))) {
			return true;
		}
		return false;
	}
	protected boolean isrightclicked(int x, int y) {
		if(((x > 730) && (x < 730 +50)) && ((y> 480) && (y < 480 +50))) {
			return true;
		}
		return false;
	}
	protected boolean isaclicked(int x, int y) {
		if(y< 50 && y> 10 && x < 50 && x > 0) {
			return true;
		}
		
		return false;
	}
	
	protected boolean issclicked(int x, int y) {
		if(y < 50 && y> 10 && x > 50 && x < 100) {
			return true;
		}
		return false;
	}
	
	protected boolean ispclicked(int x, int y) {
		if((Math.abs(x-150) <=15) && (Math.abs(y - 455) <=10)) {
			return true;
		}
		return false;
	}
	
	protected boolean isdclicked(int x, int y) {
		if((Math.abs(x -150) <=15) && (Math.abs(y - 515) <=10)) {
			return true;
		}
		return false;
	}
	protected boolean istclicked(int x, int y) {
		if((Math.abs(x -150) <=15) && (Math.abs(y - 500) <=10)) {
			return true;
		}
		return false;
	}
	protected boolean isrclicked(int x, int y) {
		if((Math.abs(x -150) <=15) && (Math.abs(y - 560) <=10)) {
			return true;
		}
		return false;
	}
	protected boolean ishclicked(int x, int y) {
		if((Math.abs(x -150) <=15) && (Math.abs(y - 575) <=10)) {
			return true;
		}
		return false;
	}
	
	protected boolean isnclicked(int x, int y) {
		if((Math.abs(x -150) <=15) && (Math.abs(y - 545) <=10) ) {
			return true;
		}
		return false;
	}
	
	protected void drawButtons(Graphics g) {
		g.setColor(checkhover(680,430));
		g.fillRect(680, 430, 50, 50);
		g.setColor(checkhover(630,480));
		g.fillRect(630, 480, 50, 50);
		g.setColor(checkhover(680,480));
		g.fillRect(680, 480, 50, 50);
		g.setColor(checkhover(730,480));
		g.fillRect(730, 480, 50, 50);
		g.setColor(checkhover(680,560));
		g.fillOval(680, 560, 50, 50);
		g.setColor(Color.BLACK);
		g.drawRect(680, 430, 50, 50);
		g.drawRect(630, 480, 50, 50);
		g.drawRect(680, 480, 50, 50);
		g.drawRect(730, 480, 50, 50);
		g.drawOval(680, 560, 50, 50);
		g.fillPolygon(new int[] {695 -5, 705 ,715+5}, new int[] {460 +5, 440 , 460+5}, 3);
		g.fillPolygon(new int[] {695 -5, 705 ,715+5}, new int[] {505 -5, 525, 505-5}, 3);
		g.fillPolygon(new int[] {(630+(630+50))/2 -20 +5, (630+(630+50))/2 +5,(630+(630+50))/2 +5}, new int[] {505, 525, 485}, 3);
		g.fillPolygon(new int[] {(730+(730+50))/2 -5, (730+(730+50))/2 -5,(730+(730+50))/2 + 20-5}, new int[] {485, 525, 505}, 3);	
		g.setColor(Color.YELLOW);
		g.fillOval(140,445,20,10);
		g.setColor(Color.BLUE);
		g.fillOval(140,490,20,10);
		g.setColor(Color.GREEN);
		g.fillOval(140,505,20,10);
		g.setColor(Color.ORANGE);
		g.fillOval(140,535,20,10);
		g.setColor(Color.RED);
		g.fillOval(140,550,20,10);
		g.setColor(Color.PINK);
		g.fillOval(140,565,20,10);
	}
	
	//change on-screen button if user hover/click/non-interact
	protected Color checkhover(int x, int y) {
		if (((mouseXC > x) && (mouseXC < x +50)) && ((mouseYC > y) && (mouseYC < y +50))) {
			if (clicked) {
				clicked = false;
				return Color.DARK_GRAY;

			} else
				return Color.LIGHT_GRAY;
		}
		else {
			return  Color.GRAY;
		}
	}
	
	
	
}
