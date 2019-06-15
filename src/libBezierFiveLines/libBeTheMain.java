// main but is it?
package libBezierFiveLines;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class libBeTheMain  {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException  {
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
