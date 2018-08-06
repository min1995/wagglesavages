import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class SecondFrame extends JFrame{
	JLabel label;
	SecondFrame () {
		label = new JLabel("My second screen");
		add(label);
		setLayout(new FlowLayout());
	}
	
}
