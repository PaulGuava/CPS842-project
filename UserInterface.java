package recommender;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;

public class UserInterface {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface window = new UserInterface();
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
	public UserInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 528, 465);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JList list = new JList();
		list.setBounds(19, 38, 174, 226);
		frame.getContentPane().add(list);
		
		JButton btnNewButton = new JButton("RATE");
		btnNewButton.setBounds(205, 161, 76, 29);
		frame.getContentPane().add(btnNewButton);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(227, 122, 34, 26);
		frame.getContentPane().add(spinner);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(305, 38, 196, 226);
		frame.getContentPane().add(textArea);
		
		JButton btnNewButton_1 = new JButton("RECOMMEND");
		btnNewButton_1.setBounds(344, 276, 127, 29);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Movie List");
		lblNewLabel.setBounds(73, 10, 82, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Recommended Movies");
		lblNewLabel_1.setBounds(335, 10, 166, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(19, 303, 240, 114);
		frame.getContentPane().add(textArea_1);
		
		JLabel lblNewLabel_2 = new JLabel("Rated Movies");
		lblNewLabel_2.setBounds(91, 281, 107, 16);
		frame.getContentPane().add(lblNewLabel_2);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		

	}
}
