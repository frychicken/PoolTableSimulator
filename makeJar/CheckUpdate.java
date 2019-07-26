//actually checking update
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
			checkhash();

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

	private void checkhash() throws NoSuchAlgorithmException, IOException {
		File file[] = new File[2];
		file[0] = new File(getClass().getResource("DrawTheLuigi.class").toString().substring(getClass().getResource("DrawTheLuigi.class").toString().indexOf(":")+1));
		file[1] = new File(getClass().getResource("DrawTheLuigi$1.class").toString().substring(getClass().getResource("DrawTheLuigi$1.class").toString().indexOf(":")+1));

		BufferedReader br = null;
		StringBuilder fromcom[] = new StringBuilder[2];
		fromcom[0] = new StringBuilder();
		fromcom[1] = new StringBuilder();
		URL url[] = new URL[2];

		url[0]= new URL("https://raw.githubusercontent.com/frychicken/PoolTableSimulator/master/hash/DrawTheLuigi.txt");
		url[1]= new URL("https://raw.githubusercontent.com/frychicken/PoolTableSimulator/master/hash/DrawTheLuigi2.txt");
		for (int i =0; i< url.length; i++) {
			long tStart = System.currentTimeMillis();
			br = new BufferedReader(new InputStreamReader(url[i].openStream()));
			String line;
			while ((line = br.readLine()) != null) {	
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
				fromcom[i].append(line);
			}

		}
		MessageDigest shaDigest = MessageDigest.getInstance("SHA-1");
		for (int i=0; i< file.length; i++) {
			if(!getFileChecksum(shaDigest, file[i]).equals(fromcom[i].toString())) {
				todis = "mismatched hash at "+ file[i] +"\n expected: "+fromcom[i]+  "\n reality:" + getFileChecksum(shaDigest, file[i]);
				System.out.println(todis);
				wl.writeLog(todis);
				popUp(todis);
			}
			else {
				todis = "hash matched "+ getFileChecksum(shaDigest, file[i]);
				System.out.println(todis);
				wl.writeLog(todis);
			}

		}
	}

	private String getFileChecksum(MessageDigest digest, File file) throws IOException
	{
		FileInputStream fis = new FileInputStream(file);
		byte[] byteArray = new byte[1024];
		int bytesCount = 0;
		while ((bytesCount = fis.read(byteArray)) != -1) {
			digest.update(byteArray, 0, bytesCount);
		};
		fis.close();
		byte[] bytes = digest.digest();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i< bytes.length ;i++)
		{
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

	/*
	 * private void selfup() throws IOException {
		URL website[] = new URL[4];
		website[0] = new URL("https://raw.githubusercontent.com/frychicken/");
		website[1] = new URL("https://raw.githubusercontent.com/frychicken/");
		website[2] = new URL("https://raw.githubusercontent.com/frychicken/");
		website[3] = new URL("https://raw.githubusercontent.com/frychicken/");
		String af[] = new String[4];
		af[0] = "1.class";
		af[1] = "2.class";
		af[2] = "3.class";
		af[3] = "4.class";

		for(int a=0; a<4; a++) {
			try {

				ReadableByteChannel ok = Channels.newChannel(website[a].openStream());
				FileOutputStream okay = new FileOutputStream(System.getProperty("user.dir")+af[a]);
				okay.getChannel().transferFrom(ok, 0, Long.MAX_VALUE);
				okay.close();
				ok.close();
			} catch (Exception e) {
				JOptionPane.showConfirmDialog((Component) null, "Update failed",
						"Update", JOptionPane.CLOSED_OPTION);
				System.out.println("Update failed");
				wl.writeLog(e.toString());
				wl.writeLog("update failed");

				e.printStackTrace();
			}
			Runtime r= Runtime.getRuntime();
			Process proc  = r.exec("jar uf PoolSimulator.jar 1.class 2$1.class 3.class 4$1.class");
			BufferedReader stdInput = new BufferedReader(new 
					InputStreamReader(proc.getInputStream()));
			BufferedReader stdError = new BufferedReader(new 
					InputStreamReader(proc.getErrorStream()));
			String s1 = null;
			while ((s1 = stdInput.readLine()) != null) {
				todis = s1;
				System.out.println(s1);
			}

			while ((s1 = stdError.readLine()) != null) {
				todis = s1;
				System.out.println(s1);
			}
		}
	}
	 */
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