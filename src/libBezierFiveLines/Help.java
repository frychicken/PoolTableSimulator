//just a help window
package libBezierFiveLines;

import com.sun.javafx.application.PlatformImpl;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class Help extends JPanel {

	private static final long serialVersionUID = 1L;
	private Stage stage;  
	private WebView browser;  
	private JFXPanel jfxPanel;  
	private WebEngine webEngine;  
	private JFrame frame;

	public Help(){  
		jfxPanel = new JFXPanel();  
		PlatformImpl.startup(new Runnable() {  
			@Override
			public void run() {  

				stage = new Stage();  

				Group root = new Group();  
				Scene scene = new Scene(root,80,20);  
				stage.setScene(scene);  

				browser = new WebView();
				webEngine = browser.getEngine();
				webEngine.load("https://raw.githubusercontent.com/bobdinh139/libBezierFiveLines/master/README.MD");

				ObservableList<Node> children = root.getChildren();
				children.add(browser);                     

				jfxPanel.setScene(scene);  
			}  
		}); 
		setLayout(new BorderLayout());  
		add(jfxPanel, BorderLayout.CENTER);       
	}  

	public void showHelp() {

		SwingUtilities.invokeLater(new Runnable() {  
			@Override
			public void run() {  
				frame = new JFrame("Help");  
				try {
					frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("lolol.png")));
				} catch (Exception e) {
					e.printStackTrace();
				}

				frame.getContentPane().add(new Help());  
				frame.setMinimumSize(new Dimension(800, 600));  
				frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);  
				frame.setVisible(true);  
			}  
		});    

	}

}
