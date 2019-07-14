// just graphic stuff
package libBezierFiveLines;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;

public class DrawAsk extends Component  implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static JFrame jf;
	private JButton button= new JButton("OK");
	private JButton welp= new JButton("HELP");
	private JPanel panel = new JPanel();
	private JLabel lw = new JLabel("W:");
	private JLabel lh = new JLabel("h:");
	private JLabel instru = new JLabel("W: Width of the curve (default: 400); h: the height of the curve (vertex) (default: 150).");
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
	//create frame, window with button and checkbox, label.
	public void daw() {
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
	private void doit() {

		JFrame frame = new JFrame("Result");
		DrawTheLuigi dl = new DrawTheLuigi(frame); 
		frame.setResizable(false);
		try {
			frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("lolol.png")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		frame.setSize(800, 800); 
		frame.setFocusable(true); 
		frame.setLocationRelativeTo(null);

		frame.setVisible(true); 
		frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent evt) {
				mousechange = false;
				DrawTheLuigi.reset();
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
					System.out.println("Clicked left arrow");
					wl.writeLog("Clicked left arrow");
					dl.w -=  10;
					frame.repaint(); 
				}
				else if (key == KeyEvent.VK_RIGHT) {
					System.out.println("Clicked right arrow");
					wl.writeLog("Clicked right arrow");
					dl.w +=  10;
					frame.repaint(); 
				}
				else if (key == KeyEvent.VK_UP) {
					if (toogle_top) {
						dl.h +=  10;
						dl.top = 400 - dl.h;	
					} else {
						dl.h +=  10;
					}
					System.out.println("Clicked up arrow while toggle top = "+ toogle_top);
					wl.writeLog("Clicked up arrow while toggle top = "+ toogle_top);
					frame.repaint(); 

				}
				else if (key == KeyEvent.VK_DOWN) {
					if (toogle_top) {
						dl.h -=  10;
						dl.top = 400 - dl.h;	
					} else {
						dl.h -=  10;
					}
					System.out.println("Clicked down arrow while toggle top = "+ toogle_top);
					wl.writeLog("Clicked down arrow while toggle top = "+ toogle_top);
					frame.repaint(); 
				}
				//g to shoot the ball
				if (key2 == 'g') {
					System.out.println("Clicked g");
					wl.writeLog("Clicked g");
					DrawTheLuigi.ball_go = true;
					DrawTheLuigi.timer.start();
				}
				//t to toggle changing top
				if (key2 == 't') {
					toogle_top = !toogle_top;
					System.out.println("Clicked t makes toggle top = "+ toogle_top);
					wl.writeLog("Clicked t makes toggle top = "+toogle_top);
					frame.repaint(); 
				}
				//s to remove lines
				if (key2 == 's') {
					dis +=1;
					System.out.println("Clicked t]s");
					wl.writeLog("Clicked s");
					frame.repaint(); 
				}
				// a to add more lines
				if (key2 == 'a') {
					dis -=1;
					if (dis < 1) dis = 1;

					System.out.println("Clicked a");
					wl.writeLog("Clicked a");
					frame.repaint(); 
				}
				if (key2 == 'd') {
					dl.toggle_perl = !dl.toggle_perl;
					System.out.println("Clicked d makes debug mode = " + dl.toggle_perl);
					wl.writeLog("Clicked d makes debug mode = "+ dl.toggle_perl);
					frame.repaint(); 
				}
				if (key2 == 'm') {
					mousechange = !mousechange;
					System.out.println("Clicked m makes mousechange = "+ mousechange);
					wl.writeLog("Clicked m makes mousechange = "+ mousechange);
					frame.repaint(); 
				}
				//c to close window
				if (key2 == 'c') {
					System.out.println("Clicked c");
					wl.writeLog("Clicked c");
					frame.setVisible(false);
					daw();
				}
			}
		});
		frame.add(dl);
	}


}



