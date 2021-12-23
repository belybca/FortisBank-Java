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


import data.CheckingAccountDB;

public class CheckingAccount extends Account{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private double transactionFees;

	public CheckingAccount() {
		super();
	
		this.transactionFees=0;
		
	}
	
	public CheckingAccount(String aNumber, String aStatus,String aClientNb) {
		super(aNumber, aStatus, aClientNb);

		this.setTransactionFees(0.0125);
		this.setaType(Enum_AccountType.Checking.toString());
	}
	
	public CheckingAccount(String aNumber, int day, int month, int year, String aStatus, Double aBalance,
			String aClientNb) throws ParseException {
		super(aNumber, day, month, year, aStatus, aBalance, aClientNb);
		this.setTransactionFees(0.0125);
		this.setaType(Enum_AccountType.Checking.toString());
	}


	public CheckingAccount(String aNumber, String aType, LocalDate aOpenedDate,String  aStatus,Double aBalance, String aClientNb,Double transactionFees) {
		super(aNumber, aOpenedDate, aStatus, aBalance, aClientNb);
		this.setTransactionFees(0.0125);
		this.setaType(Enum_AccountType.Checking.toString());
	}
	public CheckingAccount(String aNumber, LocalDate aOpenedDate, String aStatus, Double aBalance,
			String aClientNb) {
		super(aNumber, aOpenedDate, aStatus, aBalance, aClientNb);
		this.setTransactionFees(0.0125);
		this.setaType(Enum_AccountType.Checking.toString());
	}


	public double getTransactionFees() {
		return transactionFees;
	}
	public void setTransactionFees(double transactionFees) {
		this.transactionFees = transactionFees;
	}
	@Override
	public String toString() {
		return super.toString()+"CheckingAccount [transactionFees=" + transactionFees + "]";
	}

	public static boolean add(CheckingAccount ac) throws SQLException {
		return CheckingAccountDB.insert(ac);
	}
	public static boolean update(CheckingAccount ac) throws SQLException {
		return CheckingAccountDB.updateBalance(ac);
	}
	public static boolean remove(String accNumber) throws SQLException {
		return CheckingAccountDB.delete(accNumber);
	}
	public static CheckingAccount search(String accNumber) throws SQLException, RaiseException, InputMismatchException {
		return CheckingAccountDB.search(accNumber);
	}
	public static ArrayList<CheckingAccount>getCheckingAccounts() throws NumberFormatException, SQLException, RaiseException, InputMismatchException{
		
		return CheckingAccountDB.select();
	}

	public static  CheckingAccount searchAcccountByCustomer(String userId) throws SQLException, RaiseException, InputMismatchException {
		return CheckingAccountDB.searchAcccountByCustomer(userId);
	}
}
