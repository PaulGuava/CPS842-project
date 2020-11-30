//package recommender;


import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MovieRecommender {
	private static HashMap<Integer,HashMap<String,Integer>> ratings = new HashMap<Integer,HashMap<String,Integer>>();
	private static HashMap<Integer,HashMap<String,Integer>> allRatings = new HashMap<Integer,HashMap<String,Integer>>();
	private static 		HashMap<Integer,Double>  userSim = new HashMap<Integer,Double>();
	private static 		String[] movie = new String[100];
	private static HashMap<String,Double> recommendedMovies= new HashMap<String,Double>();
	private static HashMap<String,Double> recMov= new HashMap<String,Double>();
	
	
	MovieRecommender()
	{
ratings = new HashMap<Integer,HashMap<String,Integer>>();
 allRatings = new HashMap<Integer,HashMap<String,Integer>>();
 userSim = new HashMap<Integer,Double>();
recommendedMovies= new HashMap<String,Double>();
recMov= new HashMap<String,Double>();
	}
	

	public static void main(String[]args) throws FileNotFoundException
	{

		int[][] userRating = new int[30][100]; // [user][movie]
		
		
		
		
		
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
		
		Scanner scanner = new Scanner(new File("MovieList.txt"));
		for(int i = 0;i<100;i++)
		{   
			if(scanner.hasNextLine())
				movie[i] = scanner.nextLine();
		}
		
		for(int i = 0;i<100;i++)
		{
			//System.out.println(i+". "+movie[i]);
			
		}


		
	}
	
	public static String[] reccomend(HashMap<String,Integer>newUserRating) throws FileNotFoundException
	{
		
	ratingParser(newUserRating);
	calculateSim(newUserRating);
	getPredictedRatings(newUserRating);
		//ArrayList<String> recMovies = new ArrayList<String>();

	int counter=0;
	boolean add = true;
	HashMap<String,Double>recoMovi = new HashMap<String,Double>();
	for(Map.Entry rM : recMov.entrySet())
	{
		//recMovies.add((String) rM.getKey());
add =true;
		for(Map.Entry nUR : newUserRating.entrySet())
		{
			if(nUR.getKey().equals(rM.getKey()))
			{
				add=false;
			}
			
		}
		if(add==true)
		{
			recoMovi.put((String)rM.getKey(), (double)rM.getValue());
		}
		
	}
	String[] recMovies = new String[recoMovi.size()];
	for(Map.Entry rMM : recoMovi.entrySet())
	{	
	recMovies[counter]=(String) rMM.getKey();
	counter++;
	}
	
	
		return recMovies;
		
	}
	
	public static void ratingParser(HashMap<String,Integer>newUserRating) throws FileNotFoundException
	{
		Scanner scannerf = new Scanner(new File("MovieRatings.txt"));
		while(scannerf.hasNextLine())
		{
			String line;
			int user=0;
			int rating = -1;
			line =scannerf.nextLine();
			if(!line.contains("User:"))
			{
				for(Map.Entry cM : newUserRating.entrySet())
				{
					if(line.equals(cM.getKey()))
					{
						line =scannerf.nextLine();
						while(line.contains("User:")&&scannerf.hasNextLine())
						{

						
						String[]currentLine = line.split("\\W+");
						user = Integer.parseInt(currentLine[1]);
						rating = Integer.parseInt(currentLine[3]);

						if(rating<6)
						{
							//System.out.println(user+" "+rating);

						
							if(ratings.containsKey(user))
							{
								HashMap<String,Integer> ur = ratings.get(user);
								ur.put((String) cM.getKey(), rating);
								ratings.put(user,ur);
							}
							else
							{
								HashMap<String,Integer> ur = new HashMap<String, Integer>();
								ur.put((String) cM.getKey(), rating);
								ratings.put(user,ur);
							}
						}
						
						line = scannerf.nextLine();
						
						
						} 
						break;
					}
				}
			}

		}
		scannerf.close();
		
		Scanner scannerfr = new Scanner(new File("MovieRatings.txt"));
		while(scannerfr.hasNextLine())
		{
			String line;
			int user=0;
			int rating = -1;
			line =scannerfr.nextLine();
			if(!line.contains("User:"))
			{
				String title= line;
						line =scannerfr.nextLine();
						while(line.contains("User:")&&scannerfr.hasNextLine())
						{

						
						String[]currentLine = line.split("\\W+");
						user = Integer.parseInt(currentLine[1]);
						rating = Integer.parseInt(currentLine[3]);


							if(allRatings.containsKey(user))
							{
								HashMap<String,Integer> ur = allRatings.get(user);
								ur.put((String) title, rating);
								allRatings.put(user,ur);
							}
							else
							{
								HashMap<String,Integer> ur = new HashMap<String, Integer>();
								ur.put((String) title, rating);
								allRatings.put(user,ur);
							}
						
						
						line = scannerfr.nextLine();
						
						
						} 
	
					
				
			}

		}
		scannerfr.close();
		/*for(Map.Entry aR : allRatings.entrySet())
		{
			System.out.println(aR.getKey());
			HashMap<String,Integer> arInside = (HashMap<String, Integer>) aR.getValue();
			for(Map.Entry aRr : arInside.entrySet())
			{
				System.out.println(aRr.getKey()+"\t"+aRr.getValue());	
			}
		}*/
		

	}
	
	public static void calculateSim(HashMap<String,Integer>newUserRating)
	{
		int currentUserID=0;



		
		for(Map.Entry rat : ratings.entrySet())
		{
			double sim=0;
			currentUserID=(int) rat.getKey();
			HashMap<String,Integer> pC = (HashMap<String, Integer>) rat.getValue();
			
			ArrayList<Integer> userSameMovies = new ArrayList<Integer>();
			ArrayList<Integer> pastClientsSameMovies = new ArrayList<Integer>();
			int counter=0;
			for(Map.Entry pastClientRat : pC.entrySet())
			{
				counter++;
				for(Map.Entry userRat : newUserRating.entrySet())
				{
					
		
					if(pastClientRat.getKey().equals(userRat.getKey()))
					{

						userSameMovies.add((Integer) userRat.getValue());
						pastClientsSameMovies.add((Integer)pastClientRat.getValue());
					}
				}
			}
		
		
			double averageUser=0;
			double averagePastClient=0;
		
			for(int i=0; i<userSameMovies.size();i++)
			{
				averageUser+=userSameMovies.get(i);
				averagePastClient+=pastClientsSameMovies.get(i);
			
			}
			averageUser= averageUser/userSameMovies.size();
			averagePastClient = averagePastClient/userSameMovies.size();
		
			double num =0, pcNum=0,unum=0;
			double denUser =0,denPc=0,totalDen=0;
		
			for(int e=0;e<userSameMovies.size();e++)
			{
				//unum+=(userSameMovies.get(e)-averageUser);
				//System.out.println((userSameMovies.get(e)-averageUser)*(pastClientsSameMovies.get(e)-averagePastClient));
				num+= ((userSameMovies.get(e)-averageUser)*(pastClientsSameMovies.get(e)-averagePastClient));
			}
			
			for(int f=0; f<userSameMovies.size();f++)
			{
				denUser+=(userSameMovies.get(f)-averageUser)*(userSameMovies.get(f)-averageUser);
				denPc+=(pastClientsSameMovies.get(f)-averagePastClient)*(pastClientsSameMovies.get(f)-averagePastClient);
			}
			
			totalDen = Math.sqrt(denUser*denPc);
			
			if(totalDen!=0)
			{
				sim = num/totalDen;
				if(sim>0)
				{
					userSim.put(currentUserID,sim);
				}
			}

		//System.out.println(currentUserID+ " averageU: "+averageUser+"averageP: " +averagePastClient+" num: "+num+"  den: "+totalDen+"   "+sim);
		}
		for(Map.Entry userSimilarity : userSim.entrySet())
		{
		//System.out.println(userSimilarity.getKey()+"      "+ userSimilarity.getValue());
		}
	}
		

	public static void getPredictedRatings(HashMap<String,Integer>newUserRating)
	{

		double rate=0;
		for(int i =0;i<movie.length;i++)
		{		
		int user1=-1;
		int user2=-1;
		int user1r=0;
		double user2r=0;
		double user1Sim=-1;
		double user2Sim=-1;
			int counter =0;
			if(!newUserRating.containsKey(movie[i]))
			{
				for(Map.Entry userSimilarity : userSim.entrySet())
				{
					HashMap<String,Integer>checkMovie = allRatings.get(userSimilarity.getKey());
					
					for(Map.Entry cM: checkMovie.entrySet())
					{

						//System.out.println(userSimilarity.getKey()+"    "+cM.getKey()+movie[i]);
							if(cM.getKey().equals(movie[i])&&(int)checkMovie.get(movie[i])<6)
							{
								if(user1Sim<=(double)userSimilarity.getValue())
								{
							counter++;
							user2 = user1;
							user2r = user1r;
							user2Sim= user1Sim;
							user1 = (int) userSimilarity.getKey();
							user1r= (int)cM.getValue();
							user1Sim= (double)userSimilarity.getValue();
							
							
							}
						
							
						}
					/*	if(checkMovie.containsKey(movie[i])&&(int)checkMovie.get(movie[i])<6)
						{
						counter++;
						if(user1>-1)
						{
						user2=(int)userSimilarity.getKey();
						user2r= (int)checkMovie.get(movie[i]);
						user2Sim= (double)userSimilarity.getValue();
						}
						else
						{
							user1=(int)userSimilarity.getKey();		
							user1r= (int)checkMovie.get(movie[i]);
							user1Sim= (double)userSimilarity.getValue();
						}
						}*/
						
					}
					

				
				}
				/////
				
				//(sim(B,A)*r(A,B3) + sim(B,D)*r(D,B3))/(sim(B,A) + sim(B,D)
				if(counter>=2)
				{
				rate= (((user1Sim*user1r)+(user2Sim*user2r))/(user1Sim+user2Sim));
				//System.out.println(user1Sim+"  " +user1r+"    "+"    "+user2Sim+"   "+user2r+"   "+rate+"   "+counter);
				recommendedMovies.put(movie[i], rate);
				}
				else
				{
					recommendedMovies.put(movie[i], (double) 0);
				}
				/////
				
			}
			else
			{
				recommendedMovies.put(movie[i], (double) 0);

			}

		}
		
		for(Map.Entry rM : recommendedMovies.entrySet())
		{
			if((double)rM.getValue()>4&&(double)rM.getValue()<6)
			{
				System.out.println(rM.getKey()+"      "+rM.getValue());
				recMov.put((String) rM.getKey(),(double)rM.getValue());
			}
		}
	}
	
	
}