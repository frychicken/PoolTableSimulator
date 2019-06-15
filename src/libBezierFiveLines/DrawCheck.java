package libBezierFiveLines;

import java.awt.*;
import javax.swing.JFrame;
public class DrawCheck extends Component {
	String todis = "Checking for updates";
	private static final long serialVersionUID = 1L;
	int x = 140;
	String display;
	String msg[] = new String[3];
	public DrawCheck(JFrame frame) {
		try {
			frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("lolol.png")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void paint(Graphics g) {
		g.drawString(todis, 80,150);
		g.setColor(Color.BLACK);
		for (int i =0; i<5; i++) {
			g.drawString(".", x++, 180);
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		drawdouknow(g);
	}
	public void changec(String todis) {
		this.todis = todis;
		if (x == 170) {
			x = 150;
		}
	}
	int i;
	double f2 = Math.random();
	public void drawdouknow(Graphics f) {
		msg[0] = "You can click \"help\" in the setup menu to get help";
		msg[1] = "Sometimes, the program does something not expected";
		if (f2>0.5)
			i =0;
		else 
			i = 1;
		display = msg[i];

		f.setFont(new Font("default", Font.ITALIC, 10));
		f.setColor(Color.BLACK);
		f.drawString(display, 20,120);
	}
	public int getVersion() {
		return 1; 
	}
}
