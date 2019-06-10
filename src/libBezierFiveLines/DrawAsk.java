package libBezierFiveLines;

import java.awt.event.*;
import javax.swing.*;

public class DrawAsk extends JFrame  implements ActionListener {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
static JFrame jf;
 JButton button= new JButton("OK");
 JPanel panel = new JPanel();
 JLabel lw = new JLabel("W:");
 JLabel lh = new JLabel("h:");
 JLabel instru = new JLabel("W: Width of the curve (default: 400); h: the height of the curve (vertex) (default: 300).");
 JLabel dbl = new JLabel("Distance between lines (default: 10)");

 static JTextField fw;  	
 static JTextField fh;  	
 static JTextField dist;  
 public static double w =400;
 public static double h =300;
 public static double dis =10;
 
 public void daw() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	 jf = new JFrame("Setup");
	 fw = new JTextField("400", 16);  
	 fh = new JTextField("300", 16); 
	 dist = new JTextField("10", 16); 
	 DrawAsk oka = new DrawAsk();
	 panel.add(instru);
	 panel.add(lw);
	 panel.add(fw);
	 panel.add(lh);
	 panel.add(fh);
	 panel.add(dbl);
	 panel.add(dist);
	 button.addActionListener(oka);
	 panel.add(button);
     jf.add(panel);
	 jf.setSize(480,150);
     jf.setResizable(false);
     jf.setLocationRelativeTo(null);
	 jf.setVisible(true);
	 
 }
 
@Override
public void actionPerformed(ActionEvent e) {
	String s = e.getActionCommand(); 
	if (s.equals("OK") ) {
		w = Double.parseDouble(fw.getText());
		h = Double.parseDouble(fh.getText());
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

public void doit() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	JFrame frame = new JFrame("Result");
	DrawTheLuigi dl = new DrawTheLuigi(); 
	frame.setResizable(false);
	frame.setSize(800, 800); 
	frame.setFocusable(true); 
	frame.setLocationRelativeTo(null);
	frame.setVisible(true); 
	frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	frame.addWindowListener(new WindowAdapter() {
		   public void windowClosing(WindowEvent evt) {
             try {
				daw();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
		  });
	frame.add(dl);
	
}
	
}
 
 
 
