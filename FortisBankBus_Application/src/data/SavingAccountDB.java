package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import bus.Customer;
import bus.InputMismatchException;
import bus.RaiseException;
import bus.SavingAccount;

public class SavingAccountDB {

	static private Connection myConnection;
	static private String mySQLStatement = null;	
	static private String mySQLQuery = null;
	static private Statement myStatemnt = null;
	static private ResultSet myResultSet = null;
	static private SavingAccount aSavingAccount = null;
	/*
	 * return 1 if added successfully otherwise 0
	 */

	public static boolean insert(SavingAccount aNewSavingAccount) throws SQLException {
		myConnection =DBConnection.getMyConnection();
		 int accID = 0;
			String sql = "select seq_saving_acc.nextval from DUAL";
			PreparedStatement ps = myConnection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
			    accID = rs.getInt(1);}
		
		String insertQuery="INSERT into SavingAccount values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement prepareStatement = null;
		
		try {
		
			prepareStatement = myConnection.prepareStatement(insertQuery);
			prepareStatement.setInt(1,accID);
			prepareStatement.setString(2, aNewSavingAccount.getaNumber());
			prepareStatement.setString(3, aNewSavingAccount.getaType());
			prepareStatement.setDate(4, java.sql.Date.valueOf(aNewSavingAccount.getaOpenedDate()));
			prepareStatement.setDouble(5,0);
			prepareStatement.setString(6, aNewSavingAccount.getaStatus());
			prepareStatement.setDouble(7, aNewSavingAccount.getAnualInterest());
			prepareStatement.setDouble(8, aNewSavingAccount.getAnualGain());
			prepareStatement.setDouble(9, aNewSavingAccount.getExtraFees());
			int custID = CustomerDB.searchCustomerID(aNewSavingAccount.getaClientNb());
			prepareStatement.setInt(10, custID);
			int rowcount=prepareStatement.executeUpdate();	
						
			myConnection.commit();
			if(rowcount == 0)
				return false;
			else
				return true;
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("Failure");
			return false;
		}
	}
	
	public static boolean updateBalance(SavingAccount aNewSavingAccount) throws SQLException {
		
		myConnection = DBConnection.getMyConnection();
		
		mySQLStatement = "update SavingAccount set saving_acc_balance =  "    
				
			                               +   aNewSavingAccount.getaBalance()     +    " WHERE  saving_acc_no = '"
			                  
			                  +  aNewSavingAccount.getaNumber()+"'";
		
		try {
			
			myStatemnt = myConnection.createStatement();
			
			int rowAffected = myStatemnt.executeUpdate(mySQLStatement);
			
			    myConnection.commit();	
			    
			if(rowAffected > 0) {
				return true;
			}else {
				return false;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 
	 * @param id primary key of vehicle
	 * @return	return 1 if removed successfully otherwise 0
	 * @throws SQLException
	 */
	public static boolean delete(String acNumber) throws SQLException {
		myConnection = DBConnection.getMyConnection();
		
		mySQLStatement = "Delete FROM SavingAccount WHERE saving_acc_no = '" +  acNumber+"'";
				
		try {
			myStatemnt = myConnection.createStatement();
			int rowAffected = myStatemnt.executeUpdate(mySQLStatement);
			
			myConnection.commit();	
			
				if(rowAffected > 0) {
					return true;
				}else {
					return false;
				}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static SavingAccount search(String acNumber) throws SQLException, SQLException, RaiseException, InputMismatchException{
		
		
		myConnection = DBConnection.getMyConnection();
		
		mySQLQuery = "SELECT * FROM SavingAccount WHERE  saving_acc_no = '" + acNumber +"'";
		
		String aNumber,aStatus,aType;
		LocalDate aOpenedDate;
		Double aBalance,anualInterest,anualGain,extraFees;
		@SuppressWarnings("unused")
		int acId,aClientNb;
		
	//(aNumber,aType,aOpenedDate,aStatus,aBalance,aClientNb,anualInterest,anualGain,extraFees)	
		try {
		
			myStatemnt = myConnection.createStatement();
			myResultSet = myStatemnt.executeQuery(mySQLQuery); 
			
			//System.out.println(myResultSet.getFetchSize());
			while(myResultSet.next())
			{
				acId=myResultSet.getInt(1);
				aNumber=myResultSet.getString(2);
				aType=myResultSet.getString(3);
				aOpenedDate=myResultSet.getDate(4).toLocalDate();
				aBalance=myResultSet.getDouble(5);
				aStatus=myResultSet.getString(6);
				anualInterest=myResultSet.getDouble(7);
				anualGain=myResultSet.getDouble(8);
				extraFees=myResultSet.getDouble(9);
				aClientNb=myResultSet.getInt(10);
				Customer cust =CustomerDB.searchOneCustomerByID(aClientNb);
				aSavingAccount = new SavingAccount(aNumber,aType,aOpenedDate,aStatus,aBalance,cust.getcId(),anualInterest,anualGain,extraFees);
				
				
			}
			return aSavingAccount;
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("Failure");
			return aSavingAccount;
		}
	
	}
	
	public static ArrayList<SavingAccount> select() throws SQLException, NumberFormatException, SQLException, RaiseException, InputMismatchException{
		
		String aNumber,aStatus,aType;
		LocalDate aOpenedDate;
		Double aBalance,anualInterest,anualGain,extraFees;
		@SuppressWarnings("unused")
		int acId,aClientNb;
		ArrayList<SavingAccount> mySavingAccountList = new ArrayList<SavingAccount>();
		
		myConnection = DBConnection.getMyConnection();
		
		mySQLQuery = "SELECT * FROM SavingAccount";
		
		
		
		try {
			
			myStatemnt = myConnection.createStatement();
			myResultSet = myStatemnt.executeQuery(mySQLQuery);
			
			
			//System.out.println(myResultSet.getFetchSize());
			while(myResultSet.next())
			{
				acId=myResultSet.getInt(1);
				aNumber=myResultSet.getString(2);
				aType=myResultSet.getString(3);
				aOpenedDate=myResultSet.getDate(4).toLocalDate();
				aBalance=myResultSet.getDouble(5);
				aStatus=myResultSet.getString(6);
				anualInterest=myResultSet.getDouble(7);
				anualGain=myResultSet.getDouble(8);
				extraFees=myResultSet.getDouble(9);
				aClientNb=myResultSet.getInt(10);
				Customer cust =CustomerDB.searchOneCustomerByID(aClientNb);
				aSavingAccount = new SavingAccount(aNumber,aType,aOpenedDate,aStatus,aBalance,cust.getcId(),anualInterest,anualGain,extraFees);
				
				mySavingAccountList.add(aSavingAccount);
			}
			return mySavingAccountList;
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("Failure");
			return mySavingAccountList;
		}
		
	
	}
	public static SavingAccount searchAcccountByCustomer(String userId) throws RaiseException, InputMismatchException {
		myConnection = DBConnection.getMyConnection();
		int custID = CustomerDB.searchCustomerID(userId);
		mySQLQuery = "SELECT * FROM SavingAccount WHERE  CUST_ID = " + custID;
		
		String aNumber,aStatus,aType;
		LocalDate aOpenedDate;
		Double aBalance,anualInterest,anualGain,extraFees;
		@SuppressWarnings("unused")
		int acId,aClientNb;
		
	//(aNumber,aType,aOpenedDate,aStatus,aBalance,aClientNb,anualInterest,anualGain,extraFees)	
		try {
		
			myStatemnt = myConnection.createStatement();
			myResultSet = myStatemnt.executeQuery(mySQLQuery); 
			
			//System.out.println(myResultSet.getFetchSize());
			while(myResultSet.next())
			{
				acId=myResultSet.getInt(1);
				aNumber=myResultSet.getString(2);
				aType=myResultSet.getString(3);
				aOpenedDate=myResultSet.getDate(4).toLocalDate();
				aBalance=myResultSet.getDouble(5);
				aStatus=myResultSet.getString(6);
				anualInterest=myResultSet.getDouble(7);
				anualGain=myResultSet.getDouble(8);
				extraFees=myResultSet.getDouble(9);
				aClientNb=myResultSet.getInt(10);
				Customer cust =CustomerDB.searchOneCustomerByID(aClientNb);
				aSavingAccount = new SavingAccount(aNumber,aType,aOpenedDate,aStatus,aBalance,cust.getcId(),anualInterest,anualGain,extraFees);
				
				
			}
			return aSavingAccount;
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("Failure");
			return aSavingAccount;
		}
		
		
	}
	
	public static boolean updateBalancewithInterest(SavingAccount aNewSavingAccount) throws SQLException {

		myConnection = DBConnection.getMyConnection();
		double totalAmt= aNewSavingAccount.getaBalance() +aNewSavingAccount.getAnualGain();

		mySQLStatement = "update SavingAccount set saving_acc_balance = "

		+ totalAmt + " , ANUALGAIN = "+ aNewSavingAccount.getAnualGain() +" WHERE saving_acc_no = '"

		+ aNewSavingAccount.getaNumber()+"'";

		try {

		myStatemnt = myConnection.createStatement();

		int rowAffected = myStatemnt.executeUpdate(mySQLStatement);

		myConnection.commit();

		if(rowAffected > 0) {
		return true;
		}else {
		return false;
		}
		} catch (SQLException e) {
		e.printStackTrace();
		return false;
		}
		}
}
