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


import data.SavingAccountDB;




public class SavingAccount extends Account{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double anualInterest;
	private double anualGain;
	private double extraFees;

	public SavingAccount() {
		super();
		this.anualInterest=0;
		this.anualGain=0;
		this.extraFees=0;
		
	}
	public SavingAccount(String aNumber,  int day, int month, int year, String aStatus, Double aBalance,
			String aClientNb) throws ParseException {
		super(aNumber, day, month, year, aStatus, aBalance, aClientNb);
		this.setaType(Enum_AccountType.Saving.toString());
		this.setAnualInterest(0.02);
		this.setAnualGain(0.1);
		this.setExtraFees(0.05);
	}


	public SavingAccount(String aNumber, String aStatus, String aClientNb) {
		super(aNumber, aStatus, aClientNb);
		this.setaType(Enum_AccountType.Saving.toString());
		this.setAnualInterest(0.02);
		this.setAnualGain(0.1);
		this.setExtraFees(0.05);
	}
	public SavingAccount(String aNumber,  LocalDate aOpenedDate, String aStatus, Double aBalance,
			String aClientNb) {
		super(aNumber,  aOpenedDate, aStatus, aBalance, aClientNb);
		this.setaType(Enum_AccountType.Saving.toString());
		this.setAnualInterest(0.02);
		this.setAnualGain(0.1);
		this.setExtraFees(0.05);
	}

	
	public SavingAccount(String aNumber, String aType, LocalDate aOpenedDate,String  aStatus,Double aBalance, String aClientNb,Double anualInterest,Double anualGain, Double extraFees) {
		super(aNumber, aType, aOpenedDate, aStatus, aBalance, aClientNb);
		this.setaType(Enum_AccountType.Saving.toString());
		this.setAnualInterest(0.02);
		this.setAnualGain(0.1);
		this.setExtraFees(0.05);
	}
	public SavingAccount(String aNumber, String aType, LocalDate aOpenedDate,String  aStatus,Double aBalance, String aClientNb) {
		super(aNumber, aType, aOpenedDate, aStatus, aBalance, aClientNb);
		this.setaType(Enum_AccountType.Saving.toString());
		this.setAnualInterest(0.02);
		this.setAnualGain(0.1);
		this.setExtraFees(0.05);
	}
	public double getAnualInterest() {
		return anualInterest;
	}
	public void setAnualInterest(double anualInterest) {
		this.anualInterest = anualInterest;
	}
	public double getAnualGain() {
		return anualGain;
	}
	public void setAnualGain(double anualGain) {
		this.anualGain = anualGain;
	}
	public double getExtraFees() {
		return extraFees;
	}
	public void setExtraFees(double extraFees) {
		this.extraFees = extraFees;
	}


	@Override
	public String toString() {
		return super.toString()+"SavingAccount [anualInterest=" + anualInterest + ", anualGain=" + anualGain + ", extraFees=" + extraFees
				+ "]";
	}
	public static boolean add(SavingAccount ac) throws SQLException {
		return SavingAccountDB.insert(ac);
	}
	public static boolean update(SavingAccount ac) throws SQLException {
		return SavingAccountDB.updateBalance(ac);
	}
	public static boolean remove(String accNumber) throws SQLException {
		return SavingAccountDB.delete(accNumber);
	}
	public static SavingAccount search(String accNumber) throws SQLException, RaiseException, InputMismatchException {
		return SavingAccountDB.search(accNumber);
	}
	public static ArrayList<SavingAccount>getCheckingAccounts() throws NumberFormatException, SQLException, RaiseException, InputMismatchException{
		
		return SavingAccountDB.select();
	}
	public static SavingAccount searchAcccountByCustomer(String userId) throws RaiseException, InputMismatchException {
		return SavingAccountDB.searchAcccountByCustomer(userId);
	}
	public static boolean updateBalancewithInterest(SavingAccount aNewSavingAccount) throws SQLException
	{
		return SavingAccountDB.updateBalancewithInterest(aNewSavingAccount);
	}
	
}
