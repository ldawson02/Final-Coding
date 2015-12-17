package ch.makery.address.model;

import domain.RateDomainModel;
import org.apache.poi.ss.formula.functions.FinanceLib;

import base.RateDAL;


public class Rate extends RateDomainModel {
	

	private double houseCost;
	private int NPayments;
	private double interestRate;
	

	public Rate(int credit, double house, int pmtTerm) {
		super(); 
		this.houseCost = house;
		this.NPayments = pmtTerm;
		this.interestRate = RateDAL.getRate(credit);
	}

	@Override
	public double getInterestRate() {
		return interestRate;
	}
	
	public void sethouseCost(int cost) {
		this.houseCost = cost;
	}
	
	public double getPayment()
	{		
		//	Normally this kind of method would be in a BLL, but alas...
		
		//	Figure out payment based on:
		//	Interest rate
		//	PV
		//	FV (make FV = 0, unless you want a balloon payment
		//	Compounding = True
		//	Number of Payments (passed in)
		double payment;
		// Convert rate into monthly rate decimal
		double rate = interestRate/1200.0;
		
		payment = FinanceLib.pmt(rate, NPayments, -1*houseCost, 0, true);

		return payment;
	}
}
