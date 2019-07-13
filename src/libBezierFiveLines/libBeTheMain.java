// main but is it?
package libBezierFiveLines;
import java.awt.Component;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class libBeTheMain  {
	public static long startTime;
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException  {
		startTime = System.currentTimeMillis();
		System.out.println("Log file is stored at: "+System.getProperty("user.dir")+"/log.txt");
		JOptionPane.showMessageDialog((Component)null, "Log file is stored at \n"+System.getProperty("user.dir")+"/log.txt",
				"Log file", JOptionPane.INFORMATION_MESSAGE);
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		CheckUpdate ccu= new CheckUpdate();
		try {
			ccu.checkup();
		} catch (Exception e) {
			e.printStackTrace();
		}

		DrawAsk da = new DrawAsk(); 
		da.daw();
	}
}
