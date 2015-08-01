import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import java.awt.List;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainWindow {

	private JFrame frame;
	private JTextPane txtpnText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 475, 392);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		frame.getContentPane().setLayout(null);
		
		txtpnText = new JTextPane();
		txtpnText.setBounds(10, 11, 250, 279);
		frame.getContentPane().add(txtpnText);
		txtpnText.setText("Text");
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(10, 301, 89, 23);
		frame.getContentPane().add(btnRefresh);
		
		List list = new List();
		list.setBounds(266, 11, 184, 269);
		frame.getContentPane().add(list);
		
		Button button = new Button("New button");
		button.setBounds(266, 289, 142, 35);
		frame.getContentPane().add(button);
		
		Button button_1 = new Button("New button");
		button_1.setBounds(414, 289, 36, 35);
		frame.getContentPane().add(button_1);
	}
	protected JTextPane getTxtpnText() {
		return txtpnText;
	}
}
