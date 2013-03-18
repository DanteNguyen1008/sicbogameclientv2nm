package sicbo.components;

import java.util.ArrayList;
import java.util.List;


public class UserComponent {
	public enum UserAction {
		INCREASE_BALANCE, DECREASE_BALANCE, UPDATE_BALANCE
	}

	public UserComponent(String username, String email, double balanceAmount) {
		this.username = username;
		this.email = email;
		this.balance = new BalanceHandle();
		this.balance.updateBalance(balanceAmount);
		this.historyList = new ArrayList<HistoryComponent>();
	}
	
	public List<HistoryComponent> historyList;
	
	
	
	public double getBalance()
	{
		return this.balance.balance;
	}

	public double updateBalance(UserAction action, double amount) {
		switch (action) {
		case INCREASE_BALANCE:
			balance.increase(amount);
			break;
		case DECREASE_BALANCE:
			balance.decrease(amount);
			break;
		case UPDATE_BALANCE:
			balance.updateBalance(amount);
			break;
		default:
			break;
		}
		
		return getBalance();
	}

	public String username;
	public String email;
	public BalanceHandle balance;
}
