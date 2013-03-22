package sicbo.components;

import java.util.ArrayList;

import com.example.sicbogameexample.GameEntity.PatternType;

public class GameComponent {
	public int dice1;
	public int dice2;
	public int dice3;
	public boolean isWin;
	public double newBalance;
	public double totalBetAmount;
	public double totalWinAmount;
	public ArrayList<PatternType> winPatterns;

	public void setGame(boolean isWin, int dice1, int dice2, int dice3,
			double newBalance, double totalBetAmount, double totalWinAmount, String strWinPatterns) {
		this.isWin = isWin;
		this.dice1 = dice1;
		this.dice2 = dice2;
		this.dice3 = dice3;
		this.newBalance = newBalance;
		this.totalBetAmount = totalBetAmount;
		this.totalWinAmount = totalWinAmount;
		if(!strWinPatterns.equals(""))
		{
			this.winPatterns = convertStringtoArrayList(strWinPatterns);
		}else 
		{
			this.winPatterns = new ArrayList<PatternType>();
		}
		
		
		
	}
	
	private ArrayList<PatternType> convertStringtoArrayList(String arg)
	{
		ArrayList<PatternType> result = new ArrayList<PatternType>();
		
		String[] str = arg.split("\\|");
        for (int i = 0; i < str.length; i++) {
            if(!str[i].equals("|") && !str[i].equals(""))
            	result.add(PatternType.values()[Integer.parseInt(str[i]) - 1]);
        }
		
		return result;
	}
}
