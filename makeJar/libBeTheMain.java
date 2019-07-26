// main but is it?

import java.awt.Component;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class libBeTheMain  {
	public static long startTime;
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException  {
		startTime = System.currentTimeMillis();
        try {
		if (Integer.valueOf(args[0]) == 1) {
        	JOptionPane.showMessageDialog((Component)null, "Update completed",
    				"Update", JOptionPane.INFORMATION_MESSAGE);
        } else {
        	JOptionPane.showMessageDialog((Component)null, "Update failed",
    				"Update", JOptionPane.INFORMATION_MESSAGE);
        }
        } catch( ArrayIndexOutOfBoundsException e ) {
        	System.out.println("No signal from updater");
        }
		System.out.println("Log file is stored at: "+System.getProperty("user.dir")+"/log.txt");
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		CheckUpdate ccu= new CheckUpdate();
		try {
			ccu.checkup();
		} catch (Exception e) {
			e.printStackTrace();
			ccu.popUp("Error occurs while loading update");
			ccu.closeUpWindow();
		}
		DrawAsk da = new DrawAsk(); 
		da.daw();
	}
}
