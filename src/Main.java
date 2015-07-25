import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main
{
	
	//main function
	
	public static void main(String[] args)
	{	
		//Set count object
		Count count = null;
		
		
		//create local variables for menu choice and manual add
		char choice;
		int number;
		String input;
		
		//setup menu
		boolean start=true;
		boolean menu = true;
		String gameName;
		
		while (start)
		{
			System.out.print("Do you want to load a previous session? (Y/N)");
			choice = input().charAt(0);
		
			if (Character.toUpperCase(choice) == 'Y')
			{
				
				System.out.print("Input game: ");
				gameName = input();
				count = new Count(gameName, gameName + ".csv");
				start = false;
				
			}
			else if (Character.toUpperCase(choice) == 'N')
			{
				System.out.print("Input game: ");
				gameName = input();
				start = false;
				count = new Count(gameName);
			}
			else
				System.out.println("Please chose Y or N");
		
		}
		
		
		//loop
		while (menu)
		{
		
			//print deaths, menu, ask for choice
			count.printDeaths();
			System.out.print("Chose a death (X to E[x]it, M to set [M]anually): ");
			input = input();
					
					
			if (input != null)
			{
			
			choice = input.charAt(0);
			
			
			try
			{
				//check if character is a special chioce
				if (Character.toUpperCase(choice) != 'X' && Character.toUpperCase(choice) != 'M' && Character.toUpperCase(choice) != '0' )
				{
					
					count.addDeath(choice);
				
				}
				//check for manual edit
				else if (Character.toUpperCase(choice) == 'M')
				{
				
					System.out.print("Chose type: ");
					choice = input().charAt(0);
					System.out.print("Input value to add: ");
					number = Integer.parseInt(input());
					count.addDeaths(choice,number);
			
				}
				//if X, set menu to false
				else
				{
			
					menu = false;
			
				}
			
			} catch (IndexOutOfBoundsException e){
			
				System.out.println("Invalid Death Chocie\n");
		
			}
			
			//save to text file in case of crash or premature app close for recovery of values.
			count.savetxt();
			
		}
			else
			{
				System.out.println("No choice selected\n");
			}
		}
		//save to csv for future auto recovery of values
		count.save();
		
		
	}
	
	
	public static String input()
	{
		String s = "0";
		
		try
		{
		
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		    s = bufferRead.readLine();
	 
		}
		catch(IOException e)
		{
			
			e.printStackTrace();
		
		}
		if (!s.isEmpty())
			
			return s;
		
		else
		
			return null;
	}
		
	
	
	
}