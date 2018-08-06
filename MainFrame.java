import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class MainFrame extends JFrame implements ActionListener {
	JTextField text;
	JButton button;
	
	public MainFrame() {
		// TODO Auto-generated constructor stub
		text = new JTextField(20);
		button = new JButton("okay");
		add(text);
		add(button);
		
		setLayout(new FlowLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		button.addActionListener(this); // Parameters : l the ActionListener to be added
	}
	
	public static void main(String[] args) {
		MainFrame mf = new MainFrame();
		mf.setVisible(true);
		mf.setSize(400, 400);
		mf.setTitle("My Frame");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("okay")) {
			SecondFrame fr = new SecondFrame();
			fr.setVisible(true);
			fr.setSize(400, 400);
			fr.setTitle("My Second Frame");
			fr.setDefaultCloseOperation(EXIT_ON_CLOSE);
			dispose();
		}
	}
}
