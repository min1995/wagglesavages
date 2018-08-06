import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class WaggleList {
	JFrame frame = new JFrame("Waggle");
	JList<Waggle> list = new JList<>(); 
	DefaultListModel<Waggle> model = new DefaultListModel<>();

	JLabel label = new JLabel();
	JPanel panel = new JPanel();
	JSplitPane splitPane = new JSplitPane();

	public WaggleList() {

		list.setModel(model);

		ArrayList<Waggle> wagList = getData();
		for (Waggle w : wagList) {
			model.addElement(w);
		}
		
		list.getSelectionModel().addListSelectionListener(e -> {
			Waggle w = list.getSelectedValue();
			String txt = "<html>";
			txt += "ID : ";
			txt += "";
			label.setText("ID : " + w.getWaggle_id() + "\nlocation : "
					+ w.getLocation());
		});

		splitPane.setLeftComponent(new JScrollPane(list));
		panel.add(label);
		splitPane.setRightComponent(panel);

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(splitPane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static ArrayList<Waggle> getData () {
		ArrayList<Waggle> wagList = new ArrayList<>();
		SocketTest st = new SocketTest();
		String result = st.runClient("waggle");
		System.out.println("result is ..." + result);
		StringTokenizer strTok1 = new StringTokenizer(result, "$");
		while (strTok1.hasMoreTokens()) {
			StringTokenizer strTok2  = new StringTokenizer(strTok1.nextToken(), "/");
			int i = 0;
			Waggle w = new Waggle();
			while (strTok2.hasMoreTokens()) {
				switch (i) {
				case 0: w.setWaggle_id(strTok2.nextToken()); break;
				case 1: w.setLongitude(strTok2.nextToken()); break;
				case 2: w.setLatitude(strTok2.nextToken());	break;
				case 3: w.setDate_created(strTok2.nextToken()); break;
				case 4: w.setLocation(strTok2.nextToken()); break;
				default:break;
				}
				i++;
			}
			wagList.add(w);
		}
		return wagList;
	}

	public static void main (String[] args) {
		SwingUtilities.invokeLater(WaggleList::new);
	}
}
