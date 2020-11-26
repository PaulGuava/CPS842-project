//package recommender;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextArea;
import javax.swing.JLabel;

public class UserInterface {

	private JFrame frame;
	private String currentMovie="";
	private HashMap<String,Integer> newUserRating = new HashMap<String, Integer>();
	private ArrayList<String> recommendedMovies = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	/*
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
	*/

	/**
	 * Create the application.
	 * @throws FileNotFoundException 
	 */
	public UserInterface() throws FileNotFoundException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws FileNotFoundException 
	 */
	private void initialize() throws FileNotFoundException {
		String[] movie = new String[100];

		int[] userRange = {1,2,3,4,5};
		Scanner scanner = new Scanner(new File("MovieList.txt"));
		for(int i = 0;i<100;i++)
		{   
			if(scanner.hasNextLine())
				movie[i] = scanner.nextLine();
		}
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 528, 465);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		


		

		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(305, 38, 196, 226);
		getFrame().getContentPane().add(textArea);
		
		JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
		spinner.setBounds(227, 122, 34, 26);
		getFrame().getContentPane().add(spinner);

		

		
		JLabel lblNewLabel = new JLabel("Movie List");
		lblNewLabel.setBounds(73, 10, 82, 16);
		getFrame().getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Recommended Movies");
		lblNewLabel_1.setBounds(335, 10, 166, 16);
		getFrame().getContentPane().add(lblNewLabel_1);
		

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(19, 303, 240, 114);
		getFrame().getContentPane().add(textArea_1);
		
		JScrollPane scrollPane_1 = new JScrollPane(textArea_1);
		scrollPane_1.setBounds(19, 303, 240, 114);
		getFrame().getContentPane().add(scrollPane_1);
		
		JLabel lblNewLabel_2 = new JLabel("Rated Movies");
		lblNewLabel_2.setBounds(91, 281, 107, 16);
		getFrame().getContentPane().add(lblNewLabel_2);
		
		JList list = new JList(movie);
		getFrame().getContentPane().add(list);
		list.setBounds(0, 0, 176, 222);

		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						if(event.getValueIsAdjusting())
						{
							currentMovie = movie[list.getSelectedIndex()];
						}


					}

				}
				);
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(19, 38, 180, 226);
		getFrame().getContentPane().add(scrollPane);

		JButton btnNewButton = new JButton("RATE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {


				if(!currentMovie.equals(" "))
				{			
					int value = (int) spinner.getValue();
					newUserRating.put(currentMovie, value);
					
					textArea_1.setText("");
					
					for(Map.Entry cM : newUserRating.entrySet())
					{
					textArea_1.append(cM.getKey() +" "+cM.getValue()+"\n");
					}
				}

			}
		});
		btnNewButton.setBounds(205, 161, 76, 29);
		getFrame().getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("RECOMMEND");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!newUserRating.isEmpty())
				{
					MovieRecommender mr = new MovieRecommender();
					recommendedMovies = mr.reccomend(newUserRating);
					
				}
			}
		});
		btnNewButton_1.setBounds(344, 276, 127, 29);
		getFrame().getContentPane().add(btnNewButton_1);
		
		

	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
