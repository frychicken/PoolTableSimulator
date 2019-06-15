package libBezierFiveLines;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CheckUpdate {
	private boolean stillqm = true;
	public void checkup() throws Exception {

		String todis = "Checking for updates";
		JFrame frame = new JFrame("Checking update"); 
		DrawCheck drc= new DrawCheck(frame);
		frame.getContentPane().add(BorderLayout.CENTER, drc); 
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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
				JOptionPane.showMessageDialog((Component) null, todis,
						"Update", JOptionPane.INFORMATION_MESSAGE);
				try {
					drc.changec(todis);
					drc.repaint();
					Thread.sleep(1000);
				} catch (Exception er) {
					er.printStackTrace();
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
			if (c > d) {
				todis = "New version is available";

				try {
					drc.changec(todis);
					drc.repaint();
					Thread.sleep(1000);
				} catch (Exception er) {
					er.printStackTrace();

				}
				JOptionPane.showMessageDialog((Component) null, "New version is available go to my website to get the lastest version?\n new version:" +c+" your version: "+d,
						"Update", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog((Component) null, "No new updates",
						"Update", JOptionPane.INFORMATION_MESSAGE);
				frame.setVisible(false);
				stillqm = false;

			}

		}

	}
}