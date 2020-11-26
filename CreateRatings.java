import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Random;
public class CreateRatings {
	
	public static Map<Integer,Boolean[]> userMovies = new HashMap<Integer,Boolean[]>();//<User, ArrayList of movies rated>
	public static Random rand = new Random();
	
	public static void main(String[]args) throws IOException
	{
		String[] movie = new String[100];
		int[][] userRating = new int[30][100]; // [user][movie]
		
		HashMap<String,HashMap<Integer,Integer>> ratings = new HashMap<String,HashMap<Integer,Integer>>();
		
		
		assignUsersMovies();
		
		Scanner scanner = new Scanner(new File("MovieList.txt"));
		for(int i = 0;i<100;i++)
		{   
			if(scanner.hasNextLine())
				movie[i] = scanner.nextLine();
		}
		
		FileWriter fw = new FileWriter("MovieRatings.txt");

		for(int i = 0;i<100;i++)
		{
			HashMap<Integer,Integer>  perMovie = new HashMap<Integer,Integer>();
			fw.write(movie[i]+"\n");
			for(int e=0;e<30;e++)
			{
	
				Boolean[] watched = userMovies.get(e);
				if(watched[i] == true) {
					int rating= 0;
					int r = rand.nextInt(10000000);
					int m = r % 10;
					
					if (m < 4)
					{
						rating = rand.nextInt((5-4)+1)+4;
		
					}
					else if(m<6)
					{
						rating = rand.nextInt((3-2)+1)+2;
					}
					else if(m<7)
					{
						rating = 1;
					}
					
					perMovie.put(e, rating);
					fw.write("User: "+(e+1)+"\t\tRating: "+rating+"\n");
				}else {

					fw.write("User: "+(e+1)+"\t\tRating: "+ 6 +"\n");
				}
			}
			ratings.put(movie[i], perMovie);
			fw.write("\n");
		}
		fw.close();
		
	}
	/**
	 * Assigns what movies the user has watched if the user has not watched assign -1 as rating (40-50 ratings per user)
	 */
	public static void assignUsersMovies() {
		for(int i=0; i<30;i++) { // 30 users
			Boolean[] watched = new Boolean[100];
			Arrays.fill(watched, Boolean.FALSE);
			
			int num = rand.nextInt(10)+45;
			for(int j=0 ; j < num;j++) {
				int movie =  rand.nextInt(99);
				if(watched[movie] == true) {
					watched[repeater(watched)] = true;
				}else {
					watched[movie] = true;
				}
			}
			
			userMovies.put( i , watched);

		}
	}
	public static int repeater(Boolean[] x) {
		int randomMovie = rand.nextInt(99);
		if(x[randomMovie] == true)
			repeater(x);
		return randomMovie;
	}
}
