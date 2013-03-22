package sicbo.components;

public class HistoryComponent {
	
	public HistoryComponent(boolean isWin,String betDate,double balance,String dices)
	{
		this.isWin = isWin;
		this.betDate = betDate;
		this.balance = balance;
		this.dices=dices;
	}
	
	public boolean isWin;
	public String betDate;
	public String dices;
	public double balance;
}
