import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;


public class Count
{
	
	String game;
	ArrayList<String> type = new ArrayList<>();
	int[] count = new int[50];
	int total;
	
	public Count()
	{
		
		game = "Generic";
		type.add("[G]ravity");
		type.add("[T]rap");
		type.add("[E]nemy");
		type.add("[B]oss");
		type.add("[S]omething Stupid");
		
		total = 0;

	}
	
	public Count(String game)
	{
		
		this.game = game;
		type.add("[G]ravity");
		type.add("[T]rap");
		type.add("[E]nemy");
		type.add("[B]oss");
		type.add("[S]omething Stupid");
		
		total = 0;

	}
	
	public Count(String game, String filename)
	{
		
		this.game = game;
		type.add("[G]ravity");
		type.add("[T]rap");
		type.add("[E]nemy");
		type.add("[B]oss");
		type.add("[S]omething Stupid");
		
		total = 0;

		loadcsv(filename);
	}
	
	public void addDeath(char a)
	{
		
		int test = 0;
		String death = type.get(test);
		
		try{
		while (Character.toUpperCase(a) != death.charAt(1))
		{
			
			test++;
			if (test >= type.size())
				//throw new ArrayOutOfBounds();
				System.out.println("Fail\n");
			else
				death = type.get(test);
			
		}
		} catch (IndexOutOfBoundsException e) {}
		
		finally {
		
		if (test > type.size())
		{
			
			//throw new ArrayOutOfBounds();
			System.out.println("Invalid choice\n");
			
		}
		else
		{
			
			count[test]++;
			total++;
		
		}
		}
	
	}

	public void addDeaths(char a, int c)
	{
		
		int test = 0;
		String death = type.get(test);
		try{
		while (Character.toUpperCase(a) != death.charAt(1))
		{
			
			test++;
			if (test >= type.size())
				//throw new ArrayOutOfBounds();
				System.out.println("Fail\n" + a);
			else
				death = type.get(test);
			
		}
		} finally{
		if (test > type.size())
			//throw new ArrayOutOfBounds();
			System.out.println("Invalid choice\n");
		else
		{
			count[test]=count[test]+c;
			total=total+c;
			
			System.out.println("Added " + c + " to " + death + "\n");
		}
		}
		
	
		
	
	}
	
	public void printDeaths()
	{
		int x = type.size();


		System.out.println("Game: " + game);
		for (int i = 0; i < x; i++ )
		{
			if (total != 0)
			{
				System.out.println(type.get(i) + " = " + count[i] + " " + "(" + percent(i) + "%)" );
				
			}
			else
				System.out.println(type.get(i) + " = " + count[i] + " " + "(" + 0 + "%)" );

		}
		
		System.out.println();
		System.out.println("Total deaths = " + total);
		System.out.println();
		
	}
	
	public float percent(int x)
	{
		
				
		if (total != 0){
			float percentage = ((count[x]*100)/total);
		
			return percentage;
		}
		else
			return 0;
	}
	
	
	public void save()
	{
		
		try
		{
			int length = type.size();
	
			FileWriter output = new FileWriter(game + ".csv");
		
		
			for (int i = 0; i < length; i++)
			{
				output.write(type.get(i) + "," + count[i] + "\n");
			}
			
				output.write("total," + total);
				output.close();
			}
		catch (IOException e)
			{ 
				System.out.println("Failed to write file\n");
			}
		
	}
	
	public void savetxt()
	{
		
		try
		{
			int length = type.size();
	
			FileWriter output = new FileWriter(game + ".txt");
		
		
			for (int i = 0; i < length; i++)
			{
				output.write(type.get(i) + "," + count[i] + "\n");
			}
			
				output.write("total," + total);
				output.close();
			}
		catch (IOException e)
			{ 
				System.out.println("Failed to write file\n");
			}
		
	}
	
	public void loadcsv(String game)
	{
		FileReader input = null;
		boolean success = false;
		try{
			
			input = new FileReader(game);
			success = true;
			
		}catch (FileNotFoundException e)
		{
			System.out.println("File not found: " + game);
		}
		
		if (success)
		{
			BufferedReader buffer = new BufferedReader(input);
			String line;
			String[] div;
			try
			{
				while ((line = buffer.readLine()) != null)
				{
					
					div = line.split(",");
					if (!div[0].equalsIgnoreCase("Total"))						
						addDeaths(div[0].charAt(1), Integer.parseInt(div[1]));
					
				}
			}catch (IOException e){
				System.out.println("An error occered proccessing file\n");
			}
		
	}
	
	
	}
	
}
