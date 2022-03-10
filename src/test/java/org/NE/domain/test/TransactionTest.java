package org.NE.domain.test;

import static org.NE.builders.DateCreator.date;
import static org.NE.domain.Amount.amountOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import java.io.PrintStream;
import java.util.Date;

import org.NE.domain.Amount;
import org.NE.domain.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TransactionTest {
	
	@Mock PrintStream printer;
	
	@Test 
	should_print_credit_trasanction() {
		Transaction transaction = new Transaction(amountOf(1000), date("10/01/2012"));
		
		transaction.printTo(printer, amountOf(1000));
		
		verify(printer).println("10/01/2012 | 1000.00  |          | 1000.00");
	}
	
	@Test 
	should_print_debit_trasanction() {
		Transaction transaction = new Transaction(amountOf(-1000), date("10/01/2012"));
		
		transaction.printTo(printer, amountOf(-1000));
		
		verify(printer).println("10/01/2012 |          | 1000.00  | -1000.00");
	}
	
	@Test 
	should_calculate_current_balance_after_deposit() {
		Transaction transaction = new Transaction(amountOf(1000), date("10/01/2012"));
		
		Amount currentValue = transaction.balanceAfterTransaction(amountOf(100));
		
		assertThat(currentValue, is(amountOf(1100)));
	}

	@Test 
	should_calculate_current_balance_after_withdrawal() {
		Transaction transaction = new Transaction(amountOf(-1000), date("10/01/2012"));
		
		Amount currentValue = transaction.balanceAfterTransaction(amountOf(100));
		
		assertThat(currentValue, is(amountOf(-900)));
	}
	
	
	
}
