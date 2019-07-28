// just graphic stuff
package libBezierFiveLines;

import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;

public class DrawAsk extends Component  implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static JFrame jf;
	private JFrame frame;
	private JButton button= new JButton("OK");
	private JButton welp= new JButton("HELP");
	private JPanel panel;
	private JLabel lw = new JLabel("W:");
	private JLabel lh = new JLabel("h:");
	private JLabel instru = new JLabel("W: Width of the curve (default: 400); h: the height of the curve (default: 150)");
	private JLabel dbl = new JLabel("Distance between lines (default: 10)");
	private WriteLogF wl = new WriteLogF();
	private static JCheckBox checkBox;
	private static JTextField fw;  	
	private static JTextField fh;  	
	private static JTextField dist;  
	public static double w =400;
	public static double h =300;
	public static double dis =10;
	public static boolean mousechange = false;
	public static boolean toogle_top = false;
	private DrawTheLuigi dl;
	private FunctionsLol fl = new FunctionsLol();
	//create frame, window with button and checkbox, label.
	public void daw() {
		panel = new JPanel();
		jf = new JFrame("Setup");
		fw = new JTextField("400", 16);  
		fh = new JTextField("150", 16); 
		dist = new JTextField("10", 16); 
		checkBox = new JCheckBox("Mouse change");  	

		DrawAsk oka = new DrawAsk();
		panel.add(instru);
		panel.add(checkBox);
		panel.add(lw);
		panel.add(fw);
		panel.add(lh);
		panel.add(fh);
		panel.add(dbl);
		panel.add(dist);
		panel.setLayout(null);

		try {
			jf.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("lolol.png")));
		} catch (Exception e) {
			e.printStackTrace();
			wl.writeLog(e.toString());
		}
		instru.setBounds(2,10,480,10);
		lw.setBounds(100,30,20,20);
		lh.setBounds(300,30,20,20);

		fw.setBounds(150,30,50,20);
		fh.setBounds(350,30,50,20);
		dbl.setBounds(10,50,300,20);
		dist.setBounds(250, 50, 50,20);

		checkBox.setBounds(320, 50, 200,20);

		button.addActionListener(oka);
		welp.addActionListener(oka);

		panel.add(button);
		panel.add(welp);

		button.setBounds(220,80,60,20);
		welp.setBounds(210,110,80,20);

		jf.add(panel);
		jf.setSize(480,200);
		jf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		jf.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent evt) {
				wl.writeLog("Terminated by user");
				System.out.println("Terminated by user");
				long endTime = System.currentTimeMillis();
				long timenee =  ((endTime- libBeTheMain.startTime) /1000);
				System.out.println("Time executed: "+ timenee + " seconds");
				wl.writeLog("Time executed: " + timenee + " seconds");
				System.exit(0);
			}
		});
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);

	}

	//if the user click things on set up screen
	@Override
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand(); 
		if(checkBox.isSelected()) {
			mousechange = true;
		}
		if (s.equals("HELP") ) {
			Help he = new Help();
			he.showHelp();
		}
		if (s.equals("OK") ) {
			w = Double.parseDouble(fw.getText());
			h = Double.parseDouble(fh.getText())*2;
			dis = Double.parseDouble(dist.getText());
			jf.setVisible(false);
			doit();
			System.out.println("Initial mousechange: " +mousechange);
			wl.writeLog("Initial mousechange: " +mousechange);
			System.out.println("initial w: " +w);
			wl.writeLog("initial w: " +w);
			System.out.println("initial h: " +h);
			wl.writeLog("initial h: " +h);
			System.out.println("initial distance between lines: " +dis);
			wl.writeLog("initial w: " +dis);
		}


	}
	//basically creates window and listening to things that are going on
	private void doit()  {
		frame = new JFrame("Result");
		dl = new DrawTheLuigi(frame); 
		frame.setResizable(false);
		try {
			frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("lolol.png")));
		} catch (Exception e) {
			e.printStackTrace();
			wl.writeLog(e.toString());
		}
		frame.setSize(800, 800); 
		frame.setFocusable(true); 
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent evt) {

				mousechange = false;
				DrawTheLuigi.reset();
				frame.dispose();
				System.out.println("drawing screen closed by user");
				wl.writeLog("Drawing screen closed by user");
				daw();
			}
		});

		//listen if the user click anything 
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent event) {
				moveTheTHing(event);
			}
			//probably easy enough to understand
			private void moveTheTHing(KeyEvent event) {
				int key = event.getKeyCode(); 
				int key2 = event.getKeyChar();

				if (key == KeyEvent.VK_LEFT) {
					fl.clickArrowLeft();
					frame.repaint(); 
				}
				else if (key == KeyEvent.VK_RIGHT) {
					fl.clickArrowRight();
					frame.repaint(); 
				}
				else if (key == KeyEvent.VK_UP) {
					fl.clickArrowUp();
					frame.repaint(); 

				}
				else if (key == KeyEvent.VK_DOWN) {
					fl.clickArrowDown();
					frame.repaint(); 
				}
				//g to shoot the ball
				if (key2 == 'g') {
					fl.clickg();
				}
				if (key2 == 'p') {
					fl.clickp();
				}
				//t to toggle changing top
				if (key2 == 't') {
					fl.clickt();
					frame.repaint(); 
				}
				//s to remove lines
				if (key2 == 's') {
					fl.clicks();
					frame.repaint(); 
				}
				// a to add more lines
				if (key2 == 'a') {
					fl.clicka();
					frame.repaint(); 
				}
				if (key2 == 'd') {
					fl.clickd();
					frame.repaint(); 
				}
				if (key2 == 'r') {
					fl.clickr();
					frame.repaint(); 
				}
				if (key2 == 'h') {
					fl.clickh();
					frame.repaint(); 
				}
				if (key2 == 'm') {
					fl.clickm();
					frame.repaint(); 
				}
				if(key2 == 'n') {
					dl.nightmode = !dl.nightmode;
					System.out.println("Clicked n makes nightmode = "+ dl.nightmode);
					wl.writeLog("Clicked n makes nightmode = "+ dl.nightmode);
					if(dl.nightmode) {
						frame.getContentPane().setBackground(Color.BLACK);  
					} else {
						frame.getContentPane().setBackground(Color.WHITE);  

					}
				}
				//c to close window
				if (key2 == 'c') {
					System.out.println("Clicked c");
					wl.writeLog("Clicked c");
					frame.setVisible(false);
					frame.dispose();
					daw();
				}
			}
		});

		frame.add(dl);
		frame.setVisible(true); 

	}


}



