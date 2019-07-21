//actually checking update
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

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
		frame = new JFrame("Checking update"); 
		drc= new DrawCheck(frame);
		t = new Timer(10, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//updating variables 
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
		frame.dispose();
		stillqm = false;
	}

	public void popUp(String todis) {
		JOptionPane.showMessageDialog((Component) null, todis,
				"Update", JOptionPane.INFORMATION_MESSAGE);
	}

	public void checkup() throws Exception {

		frame.getContentPane().add(BorderLayout.CENTER, drc); 
		frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent evt) {
				stillqm = false;
				check = false;
				frame.dispose();
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
				URL url = new URL("https://raw.githubusercontent.com/frychicken/PoolTableSimulator/master/CurrentVersion.txt");
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
				if (JOptionPane.showConfirmDialog((Component) null, "New version is available Do you want to update?\n new version:" +c+" your version: "+d,
						"Confirm", JOptionPane.YES_NO_OPTION) ==0) {
					todis = "Updating...";
					System.out.println("Updating");
					wl.writeLog("Updating");
					toRepaint(todis);
					updatedapro();
					
				} else
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

	private void updatedapro() {
		try {
		URL updaterD = new URL("https://raw.githubusercontent.com/frychicken/PoolTableSimulator/master/Updater/Updater.jar");
		ReadableByteChannel ok = Channels.newChannel(updaterD.openStream());
		FileOutputStream okay = new FileOutputStream(System.getProperty("user.dir")+"Updater.jar");
		okay.getChannel().transferFrom(ok, 0, Long.MAX_VALUE);
		okay.close();
		ok.close();
		Runtime r= Runtime.getRuntime();
		r.exec("java -jar Updater.jar");
		System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
			wl.writeLog(e.toString());
			
		}
		
	}
}