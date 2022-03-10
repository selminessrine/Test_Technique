package org.NE.domain.test;

import static org.NE.builders.DateCreator.date;
import static org.NE.builders.TransactionBuilder.aTransaction;
import static org.NE.domain.Amount.amountOf;
import static org.mockito.Mockito.verify;

import java.io.PrintStream;
import java.util.Date;

import org.NE.domain.Account;
import org.NE.domain.Amount;
import org.NE.domain.Statement;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountTest {
	
	@Mock private Statement statement;
	private Account account;

	@Before
	public void initialise() {
		account = new Account(statement);
	}
	
	@Test 
	should_add_deposit_line_to_statement() {
		Date depositDate = date("10/01/2012");
		Amount depositAmount = amountOf(1000);
		
		account.deposit(depositAmount, depositDate);
		
		verify(statement).addLineContaining(aTransaction()
												.with(depositDate)
												.with(depositAmount).build(),
											currentBalanceEqualsTo(depositAmount));
	}
	
	@Test 
	should_add_withdraw_line_to_statement() {
		Date withdrawalDate = date("12/01/2012");
		
		account.withdrawal(amountOf(500), withdrawalDate);
		
		verify(statement).addLineContaining(aTransaction()
											.with(amountOf(-500))
											.with(withdrawalDate).build(), 
											amountOf(-500));
	}
	
	@Test 
	should_print_statement() {
		PrintStream printer = System.out;
		
		account.printStatement(printer);
		
		verify(statement).printTo(printer);
	}
	
	private Amount currentBalanceEqualsTo(Amount amount) {
		return amount;
	}

}
