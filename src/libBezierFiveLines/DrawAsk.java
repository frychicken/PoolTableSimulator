package libBezierFiveLines;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;

public class DrawAsk extends Component  implements ActionListener {
/**
	 * 
	 */
	
private static final long serialVersionUID = 1L;
static JFrame jf;
 JButton button= new JButton("OK");
 JButton welp= new JButton("HELP");
 JPanel panel = new JPanel();
 JLabel lw = new JLabel("W:");
 JLabel lh = new JLabel("h:");
 JLabel instru = new JLabel("W: Width of the curve (default: 400); h: the height of the curve (vertex) (default: 150).");
 JLabel dbl = new JLabel("Distance between lines (default: 10)");
 
 static JCheckBox checkBox;
 static JTextField fw;  	
 static JTextField fh;  	
 static JTextField dist;  
 public static double w =400;
 public static double h =300;
 public static double dis =10;
 
 static boolean mousechange = false;

 
 public void daw() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
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
     jf.setResizable(false);
     jf.setLocationRelativeTo(null);
	 jf.setVisible(true);

 }
 
 
@Override
public void actionPerformed(ActionEvent e) {
	if(checkBox.isSelected()) {
		mousechange = true;
	}
	
	String s = e.getActionCommand(); 
	
	if (s.equals("HELP") ) {
	 Help he = new Help();
	 he.showHelp();
	}
	
	if (s.equals("OK") ) {
		w = Double.parseDouble(fw.getText());
		h = Double.parseDouble(fh.getText())*2;
		dis = Double.parseDouble(dist.getText());
		jf.setVisible(false);
	
		try {
			doit();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
 }
static boolean toogle_top = false;
public void doit() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
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
             try {
            	 mousechange = false;
            	 DrawTheLuigi.ball_y = 395;
     	         DrawTheLuigi.ball_x = 395;
     	         DrawTheLuigi.toX = 400;
     	         DrawTheLuigi.toY = 400;
     	         
				daw();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
		  });

	frame.addKeyListener(new KeyAdapter() {
		public void keyPressed(KeyEvent event) {
			moveTheTHing(event);
		}
		
		private void moveTheTHing(KeyEvent event) {
			// TODO Auto-generated method stub
			int key = event.getKeyCode(); 
			int key2 = event.getKeyChar();
		
			if (key2 == 'g') {
				dl.ball_go = true;
				 dl.timer.start();
				
			}
			
			if (key2 == 't') {
				toogle_top = !toogle_top;
	
		
			    frame.repaint(); 
			}
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
			if (key2 == 's') {
		         dis +=1;
				frame.repaint(); 
			}
			if (key2 == 'a') {
			    dis -=1;
			    if (dis < 1) dis = 1;
			    frame.repaint(); 
			}
			if (key2 == 'c') {
			 frame.setVisible(false);
			 try {
				daw();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
	});
	frame.add(dl);
}

	
}
 
 
 
