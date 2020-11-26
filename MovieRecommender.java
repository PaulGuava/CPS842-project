import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MovieRecommender {
	
	public static void main(String[]args) throws FileNotFoundException
	{
		String[] movie = new String[100];
		int[][] userRating = new int[30][100]; // [user][movie]
		userRating[0] = new int[]{1,2,3,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		
		UserInterface window = new UserInterface();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//UserInterface window = new UserInterface();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		Scanner scanner = new Scanner(new File(System.getProperty("user.dir")+"\\src\\MovieList.txt"));
		for(int i = 0;i<100;i++)
		{   
			if(scanner.hasNextLine())
				movie[i] = scanner.nextLine();
		}
		
		for(int i = 0;i<100;i++)
		{
			System.out.println(i+". "+movie[i]);
		}
		
	}
}
