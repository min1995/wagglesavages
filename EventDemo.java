
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EventDemo {
	JLabel jlab;
	EventDemo () {
		JFrame jfrm = new JFrame("An Event Example");
		jfrm.setLayout(new FlowLayout());
		jfrm.setSize(400, 400);
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton jbtnAlpha = new JButton("Alpha");
		JButton jbtnBeta = new JButton("Beta");
		
		//Add action listener for Alpha.
		jbtnAlpha.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SecondFrame fr = new SecondFrame();
				fr.setVisible(true);
				fr.setSize(400, 400);
				fr.setTitle("My Second Frame");
			}
		});
		
		//Add action listener for Beta.
		jbtnBeta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jlab.setText("Beta was pressed.");
			}
		});
		
		jfrm.add(jbtnBeta);
		jfrm.add(jbtnAlpha);
		
		
		// Create a text-based label.
		//jlab = new JLabel("Press a button");
		
		// Add the label to the content pane.
		jfrm.add(jlab);
		jfrm.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new EventDemo();
			}
		});
	}
}
