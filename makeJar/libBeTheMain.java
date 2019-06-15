import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class libBeTheMain  {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException  {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		DrawAsk da = new DrawAsk(); 
		da.daw();
	}
}
