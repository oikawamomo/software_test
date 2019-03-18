package test;

import static org.junit.Assert.*;

import org.junit.Test;

import st.Money;

public class MoneyTest {

	@Test
	public void testGivemoney() {
	    Money money=new Money();
	    money.givemoney(23);
	    assertEquals("50,5,1,1,1",money.givemoney(58));
	    assertEquals("20,1,1,1",money.givemoney(23));
	    assertEquals("don't have enough money",money.givemoney(100));
	    System.out.println("in Test ----givemoney");
	}

}
