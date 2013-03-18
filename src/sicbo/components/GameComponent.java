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
			double newBalance, double totalBetAmount, double totalWinAmount) {
		this.isWin = isWin;
		this.dice1 = dice1;
		this.dice2 = dice2;
		this.dice3 = dice3;
		this.newBalance = newBalance;
		this.totalBetAmount = totalBetAmount;
		this.totalWinAmount = totalWinAmount;
	}
}
