

public class Death
{
	
	private String game;
	private char key;
	private int number;
	
	Death(String game,  char key)
	{
		this.game = game;
		this.key = key;
		this.number = 0;
		
	}
	
	public String getName()
	{
		return game;
	}
	
	public char getKey()
	{
		return key;
	}
	
	public int getNumber()
	{
		return number;
	}
	
	public void addNumber(int x)
	{
		number = number + x;
	}
	
	public void setNumber(int x)
	{
		number = x;
	}
	
	public float percent(int total)
	{
		if (total != 0){
			float percentage = ((number*100)/total);
		
			return percentage;
		}
		else
			return 0;
	}
	
}