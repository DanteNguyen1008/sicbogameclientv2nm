package sicbo.components;

public class BetComponent {
	public int betPatternID;
	public double betAmount;
	
	public BetComponent(int betPatternID,double betAmount)
	{
		this.betAmount = betAmount;
		this.betPatternID = betPatternID;
	}
}
