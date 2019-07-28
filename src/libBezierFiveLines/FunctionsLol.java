package libBezierFiveLines;
//basically all functions that both DrawAsk and DrawTheLuigi need
public class FunctionsLol {
	WriteLogF wl = new WriteLogF();
	public void clickArrowLeft() {
		System.out.println("Clicked left arrow");
		wl.writeLog("Clicked left arrow");
		if(DrawTheLuigi.toggle_pro && !DrawAsk.toogle_top)
			DrawTheLuigi.toX-=7;
		else
			DrawTheLuigi.w -=  10;
	}
	public void clickArrowRight() {
		System.out.println("Clicked right arrow");
		wl.writeLog("Clicked right arrow");
		if(DrawTheLuigi.toggle_pro && !DrawAsk.toogle_top)
			DrawTheLuigi.toX+=7;
		else
			DrawTheLuigi.w +=  10;
	}
	public void clickArrowDown() {
		if (DrawAsk.toogle_top) {
			DrawTheLuigi.h -=  10;
			DrawTheLuigi.top = 400 - DrawTheLuigi.h;	
		} else if(DrawTheLuigi.toggle_pro) {
			DrawTheLuigi.toY+=7;
			 if (DrawTheLuigi.toY > 400) DrawTheLuigi.toY = 400;
		}else{
			DrawTheLuigi.h -=  10;
		}
		System.out.println("Clicked down arrow while toggle top = "+ DrawAsk.toogle_top);
		wl.writeLog("Clicked down arrow while toggle top = "+ DrawAsk.toogle_top);

	}
	public void clickArrowUp() {
		if (DrawAsk.toogle_top) {
			DrawTheLuigi.h +=  10;
			DrawTheLuigi.top = 400 - DrawTheLuigi.h;	
		} else 	if(DrawTheLuigi.toggle_pro) {
			DrawTheLuigi.toY-=7;  
		}
		else {
			DrawTheLuigi.h +=  10;
		}
		System.out.println("Clicked up arrow while toggle top = "+ DrawAsk.toogle_top + " toggle pro="+DrawTheLuigi.toggle_pro);
		wl.writeLog("Clicked up arrow while toggle top = "+ DrawAsk.toogle_top+ " toggle pro="+DrawTheLuigi.toggle_pro);
	}
	public void clickg() {
		System.out.println("Clicked g");
		wl.writeLog("Clicked g");
		DrawTheLuigi.ball_go = true;
		DrawTheLuigi.timer.start();
	}
	public void clickp() {
		DrawTheLuigi.toggle_pro = !DrawTheLuigi.toggle_pro;
		DrawAsk.toogle_top = false;
		System.out.println("Clicked p makes toggle_pro = "+DrawTheLuigi.toggle_pro);
		wl.writeLog("Clicked p makes toggle_pro = "+DrawTheLuigi.toggle_pro);
	}
	public void clickt() {
		DrawAsk.toogle_top = !DrawAsk.toogle_top;
		System.out.println("Clicked t makes toggle top = "+ DrawAsk.toogle_top);
		wl.writeLog("Clicked t makes toggle top = "+DrawAsk.toogle_top);
	}

	public void clicks() {
		DrawAsk.dis +=1;
		System.out.println("Clicked s makes dis = " + DrawAsk.dis);
		wl.writeLog("Clicked s makes dis =" + DrawAsk.dis);
	}
	public void clicka() {
		DrawAsk.dis -=1;
		if (DrawAsk.dis < 1) DrawAsk.dis = 1;
		System.out.println("Clicked a makes dis = " + DrawAsk.dis);
		wl.writeLog("Clicked a makes dis = " + DrawAsk.dis);
	}

	public void clickd() {
		DrawTheLuigi.toggle_perl = !DrawTheLuigi.toggle_perl;
		System.out.println("Clicked d makes debug mode = " + DrawTheLuigi.toggle_perl);
		wl.writeLog("Clicked d makes debug mode = "+ DrawTheLuigi.toggle_perl);
	}
	public void clickr() {
		DrawTheLuigi.w = 400;
		DrawTheLuigi.h=300;
		System.out.println("Clicked r makes w, h = " + DrawTheLuigi.w + ", "+DrawTheLuigi.h);
		wl.writeLog("Clicked r makes w, h = " + DrawTheLuigi.w + ", "+DrawTheLuigi.h);
	}
	public void clickh() {
		DrawTheLuigi.hideHelpBox = !DrawTheLuigi.hideHelpBox;
	}
	public void clickm() {
		DrawAsk.mousechange = !DrawAsk.mousechange;
		System.out.println("Clicked m makes mousechange = "+ DrawAsk.mousechange);
		wl.writeLog("Clicked m makes mousechange = "+ DrawAsk.mousechange);
	}
	public boolean isgclicked(int x, int y) {
		if(((Math.abs(400 - x) <=5)&&(Math.abs(400 - y) <=5)) || (  ((x > 680) && (x  < 680 +50)) && ((y  > 560) && (y < 560 +50))    )) {
			return true;
		}
		return false;
	}
	public boolean isupclicked(int x, int y) {
		if(((x > 680) && (x < 680 +50)) && ((y > 430) && (y < 430 +50))) {
			return true;
		}
		return false;
	}
	public boolean isdownclicked(int x, int y) {
		if(((x > 680) && (x< 680 +50)) && ((y> 480) && (y < 480 +50))) {
			return true;
		}
		return false;
	}
	public boolean isleftclicked(int x, int y) {
		if(((x > 630) && (x < 630 +50)) && ((y > 480) && (y < 480 +50))) {
			return true;
		}
		return false;
	}
	public boolean isrightclicked(int x, int y) {
		if(((x > 730) && (x < 730 +50)) && ((y> 480) && (y < 480 +50))) {
			return true;
		}
		return false;
	}
	public boolean isaclicked(int x, int y) {
		if(y< 50 && y> 10 && x < 50 && x > 0) {
			return true;
		}
		
		return false;
	}
	
	public boolean issclicked(int x, int y) {
		if(y < 50 && y> 10 && x > 50 && x < 100) {
			return true;
		}
		return false;
	}
	
	public boolean ispclicked(int x, int y) {
		if((Math.abs(x-150) <=15) && (Math.abs(y - 455) <=10)) {
			return true;
		}
		return false;
	}
	
	public boolean isdclicked(int x, int y) {
		if((Math.abs(x -150) <=15) && (Math.abs(y - 515) <=10)) {
			return true;
		}
		return false;
	}
	public boolean istclicked(int x, int y) {
		if((Math.abs(x -150) <=15) && (Math.abs(y - 500) <=10)) {
			return true;
		}
		return false;
	}
	public boolean isrclicked(int x, int y) {
		if((Math.abs(x -150) <=15) && (Math.abs(y - 560) <=10)) {
			return true;
		}
		return false;
	}
	public boolean ishclicked(int x, int y) {
		if((Math.abs(x -150) <=15) && (Math.abs(y - 575) <=10)) {
			return true;
		}
		return false;
	}
	
}
