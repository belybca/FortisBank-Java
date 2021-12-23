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


import data.CurrencyAccountDB;


public class CurrencyAccount extends Account{
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private double acComision;
 private double convertionRate;

 public CurrencyAccount() {
	super();
	this.acComision=0;
	this.convertionRate=0;

}


public CurrencyAccount(String aNumber, String aType, int day, int month, int year, String aStatus, Double aBalance,
		String aClientNb) throws ParseException {
	super(aNumber, day, month, year, aStatus, aBalance, aClientNb);
	this.setaType(Enum_AccountType.Currency.toString());
	this.setAcComision(0.10);
	this.setConvertionRate(0.075);
	
}




public CurrencyAccount(String aNumber,LocalDate aOpenedDate, String aStatus, Double aBalance,
		String aClientNb) {
	super(aNumber, aOpenedDate, aStatus, aBalance, aClientNb);
	this.setaType(Enum_AccountType.Currency.toString());
	this.setAcComision(0.10);
	this.setConvertionRate(0.075);
}




public CurrencyAccount(String aNumber, String aStatus, String aClientNb) {
	super(aNumber, aStatus, aClientNb);
	this.setaType(Enum_AccountType.Currency.toString());
	this.setAcComision(0.10);
	this.setConvertionRate(0.075);
}



public CurrencyAccount(String aNumber, String aType, LocalDate aOpenedDate,String  aStatus,Double aBalance, String aClientNb,Double acComision,Double convertionRate) throws ParseException {
	super(aNumber, aOpenedDate, aStatus, aBalance, aClientNb);
	this.setaType(Enum_AccountType.Currency.toString());
	this.setAcComision(0.10);
	this.setConvertionRate(0.075);
}

public CurrencyAccount(String aNumber, int day, int month, int year, String aStatus, Double aBalance,
		String aClientNb) throws ParseException {
	super(aNumber, day, month, year, aStatus, aBalance, aClientNb);
	this.setaType(Enum_AccountType.Currency.toString());
	this.setAcComision(0.10);
	this.setConvertionRate(0.075);
}

public double getAcComision() {
	return acComision;
}

public void setAcComision(double acComision) {
	this.acComision = acComision;
}

public double getConvertionRate() {
	return convertionRate;
}

public void setConvertionRate(double convertionRate) {
	this.convertionRate = convertionRate;
}


@Override
public String toString() {
	return "CurrencyAccount [acComision=" + acComision + ", convertionRate=" + convertionRate + "]";
}

public static boolean add(CurrencyAccount ac) throws SQLException {
	return CurrencyAccountDB.insert(ac);
}
public static boolean update(CurrencyAccount ac) throws SQLException {
	return CurrencyAccountDB.updateBalance(ac);
}
public static boolean remove(String accNumber) throws SQLException {
	return CurrencyAccountDB.delete(accNumber);
}
public static CurrencyAccount search(String accNumber) throws SQLException, ParseException, RaiseException, InputMismatchException {
	return CurrencyAccountDB.search(accNumber);
}
public static ArrayList<CurrencyAccount>getcurrencyAccounts() throws NumberFormatException, SQLException, ParseException, RaiseException, InputMismatchException{
	
	return CurrencyAccountDB.select();
}


public static CurrencyAccount searchAcccountByCustomer(String userId) throws SQLException, ParseException, RaiseException, InputMismatchException {
	return CurrencyAccountDB.searchAcccountByCustomer(userId);
}



}