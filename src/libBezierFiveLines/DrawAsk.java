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
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				mousechange = false;
				DrawTheLuigi.reset();
				daw();
			}
		});
        
		//listen if the user click anything 
		frame.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent event) {
				moveTheTHing(event);
			}
            //probably easy enough to understand
			private void moveTheTHing(KeyEvent event) {
				int key = event.getKeyCode(); 
				int key2 = event.getKeyChar();

				if (key == KeyEvent.VK_LEFT) {
					dl.w -=  10;
					frame.repaint(); 
				}
				else if (key == KeyEvent.VK_RIGHT) {
					dl.w +=  10;
					frame.repaint(); 
				}
				else if (key == KeyEvent.VK_UP) {
					if (toogle_top) {
						dl.h +=  10;
						dl.top = 400 - dl.h;	
					} else
						dl.h +=  10;
					frame.repaint(); 

				}
				else if (key == KeyEvent.VK_DOWN) {
					if (toogle_top) {
						dl.h -=  10;
						dl.top = 400 - dl.h;	
					} else
						dl.h -=  10;
					frame.repaint(); 
				}
				//g to shoot the ball
				if (key2 == 'g') {
					DrawTheLuigi.ball_go = true;
					DrawTheLuigi.timer.start();
				}
				//t to toggle changing top
				if (key2 == 't') {
					toogle_top = !toogle_top;
					frame.repaint(); 
				}
				//s to remove lines
				if (key2 == 's') {
					dis +=1;
					frame.repaint(); 
				}
				// a to add more lines
				if (key2 == 'a') {
					dis -=1;
					if (dis < 1) dis = 1;
					frame.repaint(); 
				}
				//c to close window
				if (key2 == 'c') {
					frame.setVisible(false);
					daw();
				}
			}
		});
		frame.add(dl);
	}


}



