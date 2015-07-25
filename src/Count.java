import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;


public class Count
{
	
	ArrayList<Death> deaths = new ArrayList<>();
	
	String game;
	ArrayList<String> type = new ArrayList<>();
	//int[] count = new int[50];
	int total;
	
	public Count()
	{
		
		
		
		game = "Generic";
		deaths.add(new Death("[G]ravity", 'G'));
		deaths.add(new Death("[T]rap", 'T'));
		deaths.add(new Death("[E]nemy", 'E'));
		deaths.add(new Death("[B]oss", 'B'));
		deaths.add(new Death("[S]omething Stupid", 'S'));
		
		
		//type.add("[G]ravity");
		//type.add("[T]rap");
		//type.add("[E]nemy");
		//type.add("[B]oss");
		//type.add("[S]omething Stupid");
		
		total = 0;

	}
	
	public Count(String game)
	{
		
		this.game = game;
		deaths.add(new Death("[G]ravity", 'G'));
		deaths.add(new Death("[T]rap", 'T'));
		deaths.add(new Death("[E]nemy", 'E'));
		deaths.add(new Death("[B]oss", 'B'));
		deaths.add(new Death("[S]omething Stupid", 'S'));
		
		//type.add("[G]ravity");
		//type.add("[T]rap");
		//type.add("[E]nemy");
		//type.add("[B]oss");
		//type.add("[S]omething Stupid");
		
		total = 0;

	}
	
	public Count(String game, String filename)
	{
		
		this.game = game;
		deaths.add(new Death("[G]ravity", 'G'));
		deaths.add(new Death("[T]rap", 'T'));
		deaths.add(new Death("[E]nemy", 'E'));
		deaths.add(new Death("[B]oss", 'B'));
		deaths.add(new Death("[S]omething Stupid", 'S'));
		
		//type.add("[G]ravity");
		//type.add("[T]rap");
		//type.add("[E]nemy");
		//type.add("[B]oss");
		//type.add("[S]omething Stupid");
		
		total = 0;

		loadcsv(filename);
	}
	
	public void addDeath(char a)
	{
		
		
		boolean success = false;
	
		for (Death x : deaths)
		{
			if (x.getKey() == Character.toUpperCase(a))
			{
				x.addNumber(1);
				total++;
				success = true;
			}
		}
		
		if (!success)
		{
			System.out.println("Invalid Death");
		}		
	
	}

	public void addDeaths(char a, int c)
	{
		
		boolean success = false;
		
		for (Death x : deaths)
		{
			if (x.getKey() == Character.toUpperCase(a))
			{
				x.addNumber(c);
				total = total + c;
				success = true;
			}
		}
		
		if (!success)
		{
			System.out.println("Invalid Death");
		}
		
	
		
	
	}
	
	public void printDeaths()
	{
		
		for (Death x : deaths)
		{
			System.out.println(x.getName() + ": " + x.getNumber() + " (" + x.percent(total) + ")");
		}
		
		System.out.println();
		System.out.println("Total deaths = " + total);
		System.out.println();
		
	}
	
	
	public void save()
	{
		
		try
		{
	
			FileWriter output = new FileWriter(game + ".csv");
		
		
			for (Death x : deaths)
			{
				output.write(x.getName() + "," + x.getNumber() + "\n");
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
	
			FileWriter output = new FileWriter(game + ".txt");
		
		
			for (Death x : deaths)
			{
				output.write(x.getName() + "," + x.getNumber() + "\n");
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
