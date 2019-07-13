package libBezierFiveLines;
//actually checking update
import java.awt.BorderLayout;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class CheckUpdate {
	private boolean stillqm = true;
	public void checkup() throws Exception {
		WriteLogF wl = new WriteLogF();
		String todis = "Checking for updates";
		JFrame frame = new JFrame("Checking update"); 
		DrawCheck drc= new DrawCheck(frame);
		frame.getContentPane().add(BorderLayout.CENTER, drc); 
		frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(300, 300); 	
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);  
		while (stillqm) {
			try {
				drc.changec(todis);
				drc.repaint();
			} catch (Exception e) {
				e.printStackTrace();
				wl.writeLog(e.toString());
			}
			int cheee = drc.getVersion(); 
			BufferedReader br = null;
			StringBuilder fromcom = new StringBuilder();
			try {
				URL url = new URL("https://raw.githubusercontent.com/bobdinh139/libBezierFiveLines/master/CurrentVersion.txt");
				br = new BufferedReader(new InputStreamReader(url.openStream()));
				String line;
				while ((line = br.readLine()) != null) {
					fromcom.append(line);
				}
			}catch (Exception e) {
				todis = "Error: Cannot connect to server!";
				System.out.println(todis);
				wl.writeLog(todis);
				JOptionPane.showMessageDialog((Component) null, todis,
						"Update", JOptionPane.INFORMATION_MESSAGE);
				try {
					drc.changec(todis);
					drc.repaint();
					Thread.sleep(1000);
				} catch (Exception er) {
					er.printStackTrace();
					wl.writeLog(er.toString());
				}
				frame.setVisible(false);  
				stillqm = false;
				return ;
			} finally {
				if (br != null) {
					br.close();
				}
			} 
			int c =Integer.valueOf(fromcom.toString());
			int d = cheee;
			System.out.println("Current version: "+ d +"; newest version: "+ c);
			wl.writeLog("Current version: "+ d +"; newest version: "+ c);
			if (c > d) {
				todis = "New version is available";
				System.out.println(todis + "current version: "+ d +"; newest version: "+ c);
				wl.writeLog(todis + "current version: "+ d +"; newest version: "+ c);
				try {
					drc.changec(todis);
					drc.repaint();
					Thread.sleep(1000);
				} catch (Exception er) {
					er.printStackTrace();
					wl.writeLog(er.toString());

				}
				JOptionPane.showMessageDialog((Component) null, "New version is available go to my website to get the lastest version\n new version:" +c+" your version: "+d,
						"Update", JOptionPane.INFORMATION_MESSAGE);
				frame.setVisible(false);
				stillqm = false;
			}
			else {
				System.out.println("no new updates " + "current version: "+ d +"; newest version: "+ c);
				wl.writeLog("no new updates " + "current version: "+ d +"; newest version: "+ c);
				JOptionPane.showMessageDialog((Component) null, "No new updates",
						"Update", JOptionPane.INFORMATION_MESSAGE);
				frame.setVisible(false);
				stillqm = false;

			}

		}

	}
}