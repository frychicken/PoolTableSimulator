// main but is it?
package libBezierFiveLines;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class libBeTheMain  {
	public static long startTime;
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException  {
		startTime = System.currentTimeMillis();

		System.out.println("Log file is stored at: "+System.getProperty("user.dir")+"/log.txt");
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		CheckUpdate ccu= new CheckUpdate();
		try {
			ccu.checkup();
		} catch (Exception e) {
			e.printStackTrace();
			ccu.popUp("Error occurs while loading update");
			return;
		}
		DrawAsk da = new DrawAsk(); 
		da.daw();
	}
}
