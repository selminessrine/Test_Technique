package org.NE;

import static org.NE.builders.DateCreator.date;
import static org.NE.domain.Amount.amountOf;

import org.NE.domain.Account;
import org.NE.domain.Statement;

public class StartApp {
	
	public static void main(String[] args) {
		Account account = new Account(new Statement());
		// DEPOSIT 1000
		account.deposit(amountOf(1000), date("10/01/2012"));
		// DEPOSIT 2000
		account.deposit(amountOf(2000), date("13/01/2012"));
		// WITHDRAWAL 500
		account.withdrawal(amountOf(500), date("14/01/2012"));
		
		account.printStatement(System.out);
	}

}
