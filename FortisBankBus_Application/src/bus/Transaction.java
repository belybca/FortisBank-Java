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


import data.TransactionDB;


public class Transaction {
	private int transNumber;
	private String clienNb;
	private String transDesc;
	private LocalDate transDate;
	private String transType;
	private Double transAmount;
	private String accountType;
	private int clientID;


	public Transaction() {
		
	}
	public Transaction(int transNumber,String transDesc, LocalDate transDate, String transType, Double transAmount,String clienNb,
			float addCharge, String addChargeDesc) {
		
		this.setTransNumber(transNumber);
		this.setclienNb(clienNb);
		this.setTransDesc(transDesc);
		this.setTransDate(transDate);
		this.setTransType(transType);
		this.setTransAmount(transAmount);
		
		
	}
	public Transaction(int transNumber, String transDesc, int day,int month, int year, String transType, Double transAmount,
			float addCharge, String addChargeDesc) throws ParseException {
		
		this.setTransNumber(transNumber);
		this.setTransDesc(transDesc);
		this.setTransDate(LocalDate.of(year, month, day));
		this.setTransType(transType);
		this.setTransAmount(transAmount);
		
		
	}
	public Transaction(int transNumber, String transDesc, String transType, Double transAmount) {

		this.setTransNumber(transNumber);
		this.setTransDesc(transDesc);
		this.setTransDate( LocalDate.now());
		this.setTransType(transType);
		this.setTransAmount(transAmount);
		
		}
	public Transaction(int transNumber, String transDesc,LocalDate date, String transType, Double transAmount,String accountType,String clienNb, int clientID) {

		
		this.setTransNumber(transNumber);
		this.setclienNb(clienNb);
		this.setTransDesc(transDesc);
		this.setTransDate(date);
		this.setTransType(transType);
		this.setTransAmount(transAmount);
		this.setAccountType(accountType);
		this.setclienNb(clienNb);
		this.setclientID(clientID);

		}
	
	public int  getTransNumber() {
		return transNumber;
	}
	public void setTransNumber(int  transNumber) {
		this.transNumber = transNumber;
	}
	
	public String getclienNb() {
		return clienNb;
	}
	public void setclienNb(String clienNb) {
		this.clienNb = clienNb;
	}
	public String getTransDesc() {
		return transDesc;
	}
	public void setTransDesc(String transDesc) {
		this.transDesc = transDesc;
	}
	public LocalDate getTransDate() {
		return transDate;
	}
	public void setTransDate(LocalDate transDate) {
		this.transDate = transDate;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public Double getTransAmount() {
		return transAmount;
	}
	public void setTransAmount(Double transAmount) {
		this.transAmount = transAmount;
	}
	
	
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public int getclientID() {
		return clientID;
	}
	public void setclientID(int clientID) {
		this.clientID = clientID;
	}
	
	
	
	@Override
	public String toString() {
		return "Transaction [transNumber=" + transNumber + ", clienNb=" + clienNb + ", transDesc=" + transDesc
				+ ", transDate=" + transDate + ", transType=" + transType + ", transAmount=" + transAmount
				+ ", clientID=" + clientID + "]";
	}
	public static boolean add(Transaction tr) throws SQLException, RaiseException, InputMismatchException {
		return TransactionDB.addTransaction(tr);
	}
	public static boolean update(Transaction tr,String custNumber) throws SQLException {
		return TransactionDB.updateTransactionDesc(tr,custNumber);
	}
	public static boolean remove(String accNumber) throws SQLException {
		return TransactionDB.removeTransaction(accNumber);
	}
	public static Transaction search(int trNumber) throws SQLException {
		return TransactionDB.searchOneTransactionByNumber(trNumber);
	}
	public static ArrayList<Transaction>getTransactions(int cusID) throws NumberFormatException, SQLException{
		
		return TransactionDB.getAllTransactionList(cusID);
	}
	public static ArrayList<Transaction> searchbyCustomerNumber(String cusNb) throws SQLException {
		return TransactionDB.searchOneTransactionByCustomer(cusNb);
	}
	
	
}
