/*
Group
Rameswariben Bhoi
You Pan
Albelis Becea
Description: Fortis Bank application
Date: Nov 21 2021


*/
package bus;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;


import data.CreditAccountDB;

public class CreditAccount extends Account{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double interestRate;
	
	
	
	
	public CreditAccount() {
		super();
		this.interestRate=0;
		
	}
	public CreditAccount(String aNumber, int day, int month, int year, String aStatus, Double aBalance,
			String aClientNb) throws ParseException {
		super(aNumber,  day, month, year, aStatus, aBalance, aClientNb);
		this.setaType(Enum_AccountType.Credit.toString());
		this.setInterestRate(0.21);
		
	}


	public CreditAccount(String aNumber,LocalDate aOpenedDate, String aStatus, Double aBalance,
			String aClientNb) {
		super(aNumber, aOpenedDate, aStatus, aBalance, aClientNb);
		this.setaType(Enum_AccountType.Credit.toString());
		this.setInterestRate(0.21);
	}

	

	public CreditAccount(String aNumber, String aType, LocalDate aOpenedDate,String  aStatus,Double aBalance, String aClientNb,Double InterestRate) {
		super(aNumber,aStatus, aClientNb);
		this.setaType(Enum_AccountType.Credit.toString());
		this.setInterestRate(0.21);
	}
	public CreditAccount(String aNumber, String aStatus, String aClientNb) {
		super(aNumber,aStatus, aClientNb);
		this.setaType(Enum_AccountType.Credit.toString());
		this.setInterestRate(0.21);
	}



	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	@Override
	public String toString() {
		return super.toString()+"CreditAccount [interestRate=" + interestRate + "]";
	}
	public static boolean add(CreditAccount ac) throws SQLException {
		return CreditAccountDB.insert(ac);
	}
	public static boolean update(CreditAccount ac) throws SQLException {
		return CreditAccountDB.updateBalance(ac);
	}
	public static boolean remove(String accNumber) throws SQLException {
		return CreditAccountDB.delete(accNumber);
	}
	public static CreditAccount search(String accNumber) throws SQLException, RaiseException, InputMismatchException {
		return CreditAccountDB.search(accNumber);
	}
	public static ArrayList<CreditAccount>getCreditAccounts() throws NumberFormatException, SQLException, RaiseException, InputMismatchException{
		
		return CreditAccountDB.select();
	}
	public static CreditAccount searchAcccountByCustomer(String userId) throws RaiseException, InputMismatchException {
		return CreditAccountDB.searchAcccountByCustomer(userId);
	}
}
