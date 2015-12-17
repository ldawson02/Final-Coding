package base;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.RateDomainModel;

public class Rate_Test {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTables() {
		ArrayList<RateDomainModel> rows = RateDAL.getTable();
		 
		// Assert that there are more than 0 rows in table
		System.out.print(rows);
		assertTrue(rows.size() > 0);
		
		// Assert that a row has RateID, MinCreditScore, and InterestRate
		assertNotNull(rows.get(0).getRateID());
		assertNotNull(rows.get(0).getMinCreditScore());
		assertNotNull(rows.get(0).getInterestRate());
		
	}

	@Test
	public void testValues() {
		//	RateID | MinCreditScore | InterestRate
		//		1		600					5
		//		2		650					4.5
		//		3		700					4
		//		4		750					3.75
		//		5		800					3.5
		
		// For example, if we input a credit score of 630, interest rate should = 5
		assertTrue(RateDAL.getRate(630) == 5);
		
		// Test some more examples
		assertTrue(RateDAL.getRate(670) == 4.5);
		assertTrue(RateDAL.getRate(720) == 4);
		assertTrue(RateDAL.getRate(755) == 3.75);
		assertTrue(RateDAL.getRate(800) == 3.5);

	}
}
