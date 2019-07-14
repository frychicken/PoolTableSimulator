
//actually checking update
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class CheckUpdate {
	private boolean stillqm = true;
	private WriteLogF wl = new WriteLogF();
	private String todis = "Checking for updates";
	private JFrame frame; 
	private DrawCheck drc;
	private final Timer t;
	private boolean check = true;
	public CheckUpdate(){
		t = new Timer(10, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//do your updating of your variables here
				drc.changec(todis);
				drc.repaint();
			}
		});
		t.start();
	}

	public void toRepaint(String todis) {
		drc.changec(todis);
		drc.repaint();
	}

	public void closeUpWindow() {
		frame.setVisible(false);  
		stillqm = false;
	}

	public void popUp(String todis) {
		JOptionPane.showMessageDialog((Component) null, todis,
				"Update", JOptionPane.INFORMATION_MESSAGE);
	}

	public void checkup() throws Exception {
		frame = new JFrame("Checking update"); 
		drc= new DrawCheck(frame);
		frame.getContentPane().add(BorderLayout.CENTER, drc); 
		frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent evt) {
				stillqm = false;
				check = false;
			}
		});
		frame.setResizable(false);
		frame.setSize(300, 300); 	
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);  

		while (stillqm) {
			toRepaint(todis);
			int cheee = drc.getVersion(); 
			BufferedReader br = null;
			StringBuilder fromcom = new StringBuilder();
			try {
				long tStart = System.currentTimeMillis();
				URL url = new URL("https://raw.githubusercontent.com/bobdinh139/libBezierFiveLines/master/CurrentVersion.txt");
				br = new BufferedReader(new InputStreamReader(url.openStream()));
				String line;
				while (((line = br.readLine()) != null) &&check) {
					toRepaint(todis);
					long tEnd = System.currentTimeMillis();
					if ((tEnd - tStart)/1000.0 >= (10)) {
						todis = "Error checking update: Timed out!";
						System.out.println(todis);
						wl.writeLog(todis);
						popUp(todis);
						toRepaint(todis);
						closeUpWindow();
						return;
					}
				
					fromcom.append(line);
				}
				if (!check) {
					return;
				}
				t.stop();
				DrawCheck.x =90;
				DrawCheck.strin = 100;
			}catch (Exception e) {
				todis = "Error: Cannot connect to server!";
				System.out.println(todis);
				wl.writeLog(todis);
				popUp(todis);
				toRepaint(todis);
				closeUpWindow();
				return ;
			} finally {
				if (br != null) {
					br.close();
				}
			} 
			int c =Integer.valueOf(fromcom.toString());
			int d = cheee;
			if (c > d) {
				todis = "New version is available";
				System.out.println(todis + "current version: "+ d +"; newest version: "+ c);
				wl.writeLog(todis + "current version: "+ d +"; newest version: "+ c);
				toRepaint(todis);
				popUp("New version is available go to my website to get the lastest version\n new version:" +c+" your version: "+d);
				closeUpWindow();
			}
			else {
				System.out.println("No new updates " + "current version: "+ d +"; newest version: "+ c);
				wl.writeLog("no new updates " + "current version: "+ d +"; newest version: "+ c);
				popUp("No new updates");
				closeUpWindow();

			}

		}

	}
}