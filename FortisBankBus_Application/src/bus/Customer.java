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
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import data.CustomerDB;

public class Customer  implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String cId;
	private String cName;
	private String cPin;
	private String cStatus;
	private AccountList cAccounts;
	
	
	public Customer() 
	{
		cId=cName=cPin=cStatus="Undefined";
		cAccounts= new AccountList();
		
	}

	public Customer(String cId, String cName, String cPin, AccountList accountList) throws RaiseException, InputMismatchException {
		
		this.setcId(cId);
		this.setcName(cName);
		this.setcPin(cPin);
		this.setcStatus("Active");
		this.setcAccounts(accountList);
		
		
	}
	//If we want to create a client without specifying the acnumber
public Customer(String cId, String cName, String cPin, String cStatus) throws RaiseException, InputMismatchException {
		
	this.setcId(cId);
	this.setcName(cName);
	this.setcPin(cPin);
	this.setcStatus("Active");
	this.cAccounts = new AccountList();
	}


public String getcStatus() {
	return cStatus;
}

public void setcStatus(String cStatus) {
	this.cStatus = cStatus;
}

public String getcId() {
	return cId;
}

public void setcId(String cId) throws RaiseException {
	DataValidator.isNULL(cId);
	this.cId = cId;
}

public String getcName() {
	return cName;
}

public void setcName(String cName) throws InputMismatchException,  RaiseException{
	
	Pattern pattern = Pattern.compile(new String ("^[a-zA-Z\\s]*$")); //this Regex is to consider letters and spaces for a full name type String
	Matcher matcher = pattern.matcher(cName);
	if(String.valueOf(cName).equals("")) {
		throw new InputMismatchException();
	}
	if(!matcher.matches()) {
		throw new RaiseException();
	}
	
	//DataValidator.isNULL(cName);
	this.cName = cName;
}

public String getcPin() {
	return cPin;
}

public void setcPin(String cPin) throws RaiseException {
	DataValidator.isAlphabetic(cPin);
	this.cPin = cPin;
}

public AccountList getcAccounts() {
	return cAccounts;
}

public void setcAccounts(AccountList cAccounts) {
	this.cAccounts = cAccounts;
}

@Override
public String toString() {
	return "Customer [cId=" + cId + ", cName=" + cName + ", cPin=" + cPin + ", cStatus=" + cStatus + "]";
}
	
public static boolean add(Customer cus) throws SQLException {
	return CustomerDB.AddOneCustomer(cus);
}
public static boolean update(Customer ac) throws SQLException {
	return CustomerDB.updateCustomerName(ac);
}
public static boolean remove(String cusNumber) throws SQLException {
	return CustomerDB.removeCustomer(cusNumber);
}
public static Customer search(String cusNumber) throws SQLException, RaiseException, InputMismatchException {
	return CustomerDB.searchOneCustomerByNumber(cusNumber);
}
public static Customer Login(String userId,String password) throws SQLException, RaiseException, InputMismatchException {
	return CustomerDB.Login(userId,password);
}
public static ArrayList<Customer>getCustomerList() throws NumberFormatException, SQLException, RaiseException, InputMismatchException{
	
	return CustomerDB.getAllCustomer();
}
public static int SearchCustomerId(String custNumber) {
	return CustomerDB.searchCustomerID(custNumber);
	
}
public static ArrayList<Customer>sortCustomerListbyName() throws NumberFormatException, SQLException, RaiseException, InputMismatchException{
	
	ArrayList<Customer> sortedList = new  ArrayList<Customer>();
	sortedList = CustomerDB.getAllCustomer();
			Collections.sort(sortedList,new CustomerNameComparator());
	return sortedList;
}
public static ArrayList<Customer>sortCustomerListByID() throws NumberFormatException, SQLException, RaiseException, InputMismatchException{
	
	ArrayList<Customer> sortedList = new  ArrayList<Customer>();
	sortedList = CustomerDB.getAllCustomer();
			Collections.sort(sortedList,new CustomerIDComparator());
	return sortedList;
}
	
}
