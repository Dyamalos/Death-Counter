import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.util.concurrent.TimeUnit;

public class Count
{
	
	ArrayList<Death> deaths = new ArrayList<>();
	
	String game;
	
	int total;
	long lastDeath = 0;
	long timeSinceLast = 0;
	long totalDeathTime = 0;
	boolean newInst = true;
	int session = 0;
	int sessionDeaths = 0;
	
	//store previous last death time for deletion
	long prevLastDeath = 0;
	long prevTimeSinceLast = 0;
	long prevTotalDeathTime = 0;
	boolean prevNewInst;
	Death prevDeath = null;
	
	
	public Count()
	{
		
		game = "Generic";
		deaths.add(new Death("[G]ravity", 'G'));
		deaths.add(new Death("[T]rap", 'T'));
		deaths.add(new Death("[E]nemy", 'E'));
		deaths.add(new Death("[B]oss", 'B'));
		deaths.add(new Death("[R]eading chat", 'R'));
		deaths.add(new Death("[S]omething Stupid", 'S'));
		
		total = 0;

	}
	
	public Count(String game)
	{
		
		this.game = game;
		deaths.add(new Death("[G]ravity", 'G'));
		deaths.add(new Death("[T]rap", 'T'));
		deaths.add(new Death("[E]nemy", 'E'));
		deaths.add(new Death("[B]oss", 'B'));
		deaths.add(new Death("[R]eading chat", 'R'));
		deaths.add(new Death("[S]omething Stupid", 'S'));
		
		session = 1;
		total = 0;

	}
	
	public Count(String game, String filename)
	{
		
		this.game = game;
		deaths.add(new Death("[G]ravity", 'G'));
		deaths.add(new Death("[T]rap", 'T'));
		deaths.add(new Death("[E]nemy", 'E'));
		deaths.add(new Death("[B]oss", 'B'));
		deaths.add(new Death("[R]eading chat", 'R'));
		deaths.add(new Death("[S]omething Stupid", 'S'));
		
		session = 1;
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
				sessionDeaths++;
				success = true;
				deathTime();
				prevDeath = x;
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
	
	public void remDeath()
	{
		if (prevDeath != null)
		{
			sessionDeaths--;
			total--;
			lastDeath = prevLastDeath;
			timeSinceLast = prevTimeSinceLast;
			totalDeathTime = prevTotalDeathTime;
			prevDeath.setNumber(prevDeath.getNumber() - 1);
			prevDeath = null;
			prevNewInst = newInst;
			
		}
		else System.out.println("Failed to remove death\n");
	}
	
	
	public void printDeaths()
	{
		
		for (Death x : deaths)
		{
			System.out.println(x.getName() + ": " + x.getNumber() + " (" + x.percent(total) + ")");
		}
		
		System.out.println();
		System.out.println("Total deaths = " + total + "\n" + 
						   "Time since last Death: " + printTime(timeSinceLast));
		
		if ((total - session) >= 1)
		{
			System.out.println("Average Death Time: " + printTime(totalDeathTime/(total - session)));
		}
		
		printSession();
		System.out.println();
		
	}
	
	public void printSession()
	{
		System.out.println("\nDeaths this session: " + sessionDeaths);
	}
	
	public void printExit()
	{
		System.out.println("\nDeaths this session: " + sessionDeaths
						  +"\nAverage deaths per session: " + total/session);
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
			
			output.write("total," + total + "\n");
			output.write("time," + totalDeathTime + "\n");
			output.write("session," + session);
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
			
			output.write("total," + total + "\n");
			output.write("time," + totalDeathTime);
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
		
		try
		{
			
			input = new FileReader(game);
			success = true;
			
		}
		catch (FileNotFoundException e)
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
					
					
					if (!div[0].equalsIgnoreCase("Total") && !div[0].equalsIgnoreCase("time") && !div[0].equalsIgnoreCase("session"))						
						addDeaths(div[0].charAt(1), Integer.parseInt(div[1]));
					
					if (div[0].equalsIgnoreCase("time"))
						totalDeathTime = Long.parseLong(div[1]);
					
					if (div[0].equalsIgnoreCase("session"))
					{
						
						session = Integer.parseInt(div[1]) + 1;
						System.out.println("Loaded session: " + div[1] + "\nStarting sesson: " + session);
						
					}
					
				}
			}catch (IOException e){
				System.out.println("An error occered proccessing file\n");
			}
		
	}
	
	
	}
	
	private void deathTime()
	{
		
		prevLastDeath = lastDeath;
		prevTimeSinceLast = timeSinceLast;
		prevTotalDeathTime = totalDeathTime;
		prevNewInst = newInst;
		
		if (newInst)
		{
			
			lastDeath = System.currentTimeMillis();
			newInst = false;
			
		}
		else
		{
			
			long currentTime = System.currentTimeMillis();
			
			
			timeSinceLast = currentTime - lastDeath;
			totalDeathTime = totalDeathTime + timeSinceLast;
			lastDeath = currentTime;
			
		}
		
	}
	
	private String printTime(long millis)
	{
		
		
		return  String.format("%d min, %d sec", 
			    	TimeUnit.MILLISECONDS.toMinutes(millis),
			    	TimeUnit.MILLISECONDS.toSeconds(millis) - 
			    	TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
					);
		
	}
	
	
	
}

	

