//draw the window for checking update
import java.awt.*;
import javax.swing.JFrame;
public class DrawCheck extends Component {
	private String todis = "Checking for updates";
	private static final long serialVersionUID = 1L;
	public static int x = 10;
	private double f;
	public static double strin=1;
	private String display;
	private String msg[] = new String[3];
	private int i;
	private double f2 = Math.random();;
	public DrawCheck(JFrame frame) {
		try {
			frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("lolol.png")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void paint(Graphics g) {
		g.drawString(todis, 80,150);
		g.setColor(Color.BLACK);

		g.drawString((int)strin + "%", 140, 210);
		g.setColor(Color.BLACK);
		g.drawRect(105, 180, 90, 10);
		g.setColor(Color.BLUE);
		g.fillRect(105, 180, x, 10);

		drawdouknow(g);
	}
	public void changec(String todis) {
		this.todis = todis;
		x++; 
		f = x;
		if (strin >100) {
			x = 10;
		}
		if (strin == 50 ) {
			f2 = Math.random();
		}

		strin = (double)(f/90)*100; 
		strin = Math.ceil(strin);
	
		msg[0] = "You can click \"help\" in the setup menu to get help";
		msg[1] = "Sometimes, the program does something not expected";
		if (f2>0.5)
			i =0;
		else 
			i = 1;
		display = msg[i];
	}

	private void drawdouknow(Graphics f) {


		f.setFont(new Font("default", Font.ITALIC, 10));
		f.setColor(Color.BLACK);
		f.drawString(display, 20,120);
	}
	public int getVersion() {
		return 6; 
	}
}
