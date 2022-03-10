package org.NE.domain;

import static org.NE.domain.Amount.amountOf;

import java.io.PrintStream;
import java.util.Date;

public class Account {

	private Statement statement;
	
	private Amount balance = amountOf(0);

	public Account(Statement statement) {
		this.statement = statement;
	}

	// TO DEPOSIT MONEY TO MY ACCOUNT
	public void deposit(Amount value, Date date) {
		recordTransaction(value, date);
	} 
    // TO WITHDRAWAL TO MY ACCOUNT
	public void withdrawal(Amount value, Date date) {
		recordTransaction(value.negative(), date);
	}
    // TO SEE HISTORY OF MY OPERATIONS
	public void printStatement(PrintStream printer) {
		statement.printTo(printer);
	}

	// TO SAVE RECORD OF THE TRANSACTION
	private void recordTransaction(Amount value, Date date) {
		Transaction transaction = new Transaction(value, date);
		Amount balanceAfterTransaction = transaction.balanceAfterTransaction(balance);
		balance = balanceAfterTransaction;
		statement.addLineContaining(transaction, balanceAfterTransaction);
	}
	
}
