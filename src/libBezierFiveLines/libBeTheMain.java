package libBezierFiveLines;

import javax.swing.JFrame;

public class libBeTheMain  {
  public static void main(String[] args)  {
	
		JFrame frame = new JFrame();
	
	  DrawTheLuigi dl = new DrawTheLuigi();

		frame.setResizable(false);
		frame.setSize(800, 800); 
		frame.setFocusable(true); 
		frame.setLocationRelativeTo(null);
		frame.setVisible(true); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    	  frame.add(dl);
  }
}
