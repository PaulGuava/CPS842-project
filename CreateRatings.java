import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;
public class CreateRatings {

	public static void main(String[]args) throws IOException
	{
		String[] movie = new String[100];
		int[][] userRating = new int[30][100]; // [user][movie]
		
		
		
		HashMap<String,HashMap<Integer,Integer>> ratings = new HashMap<String,HashMap<Integer,Integer>>();
		
		Random rand = new Random();


		Scanner scanner = new Scanner(new File("MovieList.txt"));
		for(int i = 0;i<100;i++)
		{   
			if(scanner.hasNextLine())
				movie[i] = scanner.nextLine();
		}
		
		FileWriter fw = new FileWriter("MovieRatings.txt");

		for(int i = 0;i<100;i++)
		{
			fw.write(movie[i]+"\n");
			for(int e=0;e<30;e++)
			{
			int rating= 0;
			int r = rand.nextInt(10000000);
			int m = r % 10;
			if (m > 6)
			{
				rating = rand.nextInt(5-1+1)+1;

			}
			fw.write("User: "+e+"\t\tRating: "+rating+"\n");
			}
			
			fw.write("\n");
		}
		fw.close();
		
	}
}
