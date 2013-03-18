package sicbo.components;

public class HistoryComponent {
	
	public HistoryComponent(boolean isWin,String betDate,double balance)
	{
		this.isWin = isWin;
		this.betDate = betDate;
		this.balance = balance;
	}
	
	public boolean isWin;
	public String betDate;
	public double balance;
}
