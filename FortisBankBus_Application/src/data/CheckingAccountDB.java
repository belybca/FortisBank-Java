package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import bus.CheckingAccount;
import bus.Customer;
import bus.InputMismatchException;
import bus.RaiseException;

public class CheckingAccountDB {

	static private Connection myConnection;
	static private String mySQLStatement = null;	
	static private String mySQLQuery = null;
	static private Statement myStatemnt = null;
	static private ResultSet myResultSet = null;
	static private CheckingAccount aCheckingAccount = null;
	/*
	 * return 1 if added successfully otherwise 0
	 */

	public static boolean insert(CheckingAccount aNewCheckingAccount) throws SQLException {
		
		myConnection =DBConnection.getMyConnection();
		 int accID = 0;
			String sql = "select seq_checking_acc.nextval from DUAL";
			PreparedStatement ps = myConnection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
			    accID = rs.getInt(1);}
		
		mySQLQuery="INSERT into checkingAccount values(?,?,?,?,?,?,?,?)";
		PreparedStatement prepareStatement = null;
		
		try {
			myConnection =DBConnection.getMyConnection();
			prepareStatement = myConnection.prepareStatement(mySQLQuery);
			prepareStatement.setInt(1,accID);
			prepareStatement.setString(2, aNewCheckingAccount.getaNumber());
			prepareStatement.setString(3, aNewCheckingAccount.getaType());
			prepareStatement.setDate(4, java.sql.Date.valueOf( aNewCheckingAccount.getaOpenedDate()));
			
			prepareStatement.setDouble(5, 0);
			prepareStatement.setString(6, aNewCheckingAccount.getaStatus());
			prepareStatement.setDouble(7, aNewCheckingAccount.getTransactionFees());
			//System.out.println(aNewCheckingAccount.getaClientNb());
			int custID = CustomerDB.searchCustomerID(aNewCheckingAccount.getaClientNb());
			//System.out.println(custID);
			prepareStatement.setInt(8, custID);		
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
	
	public static boolean updateBalance(CheckingAccount aNewCheckingAccount) throws SQLException {
		
		myConnection = DBConnection.getMyConnection();
		
		mySQLStatement = "update checkingAccount set chk_acc_balance ="    
				
			                               +   aNewCheckingAccount.getaBalance()     +    "WHERE  chk_acc_no = '"
			                  
			                  +  aNewCheckingAccount.getaNumber()+"'";
		
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
	
	
	public static boolean delete(String acNumber) throws SQLException {
		myConnection = DBConnection.getMyConnection();
		
		mySQLStatement = "Delete FROM checkingAccount WHERE chk_acc_no = '" +  acNumber+"'";
				
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
	
	public static CheckingAccount search(String acNumber) throws SQLException, SQLException, RaiseException, InputMismatchException{
		
	
		myConnection = DBConnection.getMyConnection();
		
		mySQLQuery = "SELECT * FROM checkingAccount WHERE  chk_acc_no = '" + acNumber+"'";
		
		String aNumber,aType,aStatus;
		int aClientNb;
		LocalDate aOpenedDate;
		Double aBalance,transactionFees;
		@SuppressWarnings("unused")
		int acID;
		
		//(aNumber,aType,aOpenedDate,aStatus,aBalance,aClientNb,transactionFees)
		try {
		
			myStatemnt = myConnection.createStatement();
			myResultSet = myStatemnt.executeQuery(mySQLQuery);
			
			//System.out.println(myResultSet.getFetchSize());
			while(myResultSet.next())
			{
				acID=myResultSet.getInt(1);
				aNumber=myResultSet.getString(2);
				aType=myResultSet.getString(3);
				aOpenedDate=myResultSet.getDate(4).toLocalDate();
				aBalance=myResultSet.getDouble(5);
				aStatus=myResultSet.getString(6);
				transactionFees=myResultSet.getDouble(7);
				aClientNb=myResultSet.getInt(8);
				Customer cust =CustomerDB.searchOneCustomerByID(aClientNb);
				aCheckingAccount = new CheckingAccount(aNumber,aType,aOpenedDate,aStatus,aBalance,cust.getcId(),transactionFees);
				//System.out.println(aCheckingAccount.toString());
				
			}
			return aCheckingAccount;
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("Failure");
			return aCheckingAccount;
		}
	
	}
	public static CheckingAccount searchAcccountByCustomer(String custId) throws SQLException, SQLException, RaiseException, InputMismatchException{
		
		
		myConnection = DBConnection.getMyConnection();
		int custID = CustomerDB.searchCustomerID(custId);
		mySQLQuery = "SELECT * FROM checkingAccount WHERE  CUST_ID = " + custID;
		
		String aNumber,aType,aStatus;
		int aClientNb;
		LocalDate aOpenedDate;
		Double aBalance,transactionFees;
		@SuppressWarnings("unused")
		int acID;
		
		//(aNumber,aType,aOpenedDate,aStatus,aBalance,aClientNb,transactionFees)
		try {
		
			myStatemnt = myConnection.createStatement();
			myResultSet = myStatemnt.executeQuery(mySQLQuery);
			
			//System.out.println(myResultSet.getFetchSize());
			while(myResultSet.next())
			{
				acID=myResultSet.getInt(1);
				aNumber=myResultSet.getString(2);
				aType=myResultSet.getString(3);
				aOpenedDate=myResultSet.getDate(4).toLocalDate();
				aBalance=myResultSet.getDouble(5);
				aStatus=myResultSet.getString(6);
				transactionFees=myResultSet.getDouble(7);
				aClientNb=myResultSet.getInt(8);
				Customer cust =CustomerDB.searchOneCustomerByID(aClientNb);
				aCheckingAccount = new CheckingAccount(aNumber,aType,aOpenedDate,aStatus,aBalance,cust.getcId(),transactionFees);
				//System.out.println(aCheckingAccount.toString());
				
			}
			return aCheckingAccount;
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("Failure");
			return aCheckingAccount;
		}
	
	}
	
	public static ArrayList<CheckingAccount> select() throws SQLException, NumberFormatException, SQLException, RaiseException, InputMismatchException{
		
		
		String aNumber,aType,aStatus;
		int aClientNb;
		LocalDate aOpenedDate;
		Double aBalance,transactionFees;
		@SuppressWarnings("unused")
		int acID;
		ArrayList<CheckingAccount> myCheckingAccountList = new ArrayList<CheckingAccount>();
		
		myConnection = DBConnection.getMyConnection();
		
		mySQLQuery = "SELECT * FROM checkingAccount";
		
		
		
		try {
			
			myStatemnt = myConnection.createStatement();
			myResultSet = myStatemnt.executeQuery(mySQLQuery);
			
			
			//System.out.println(myResultSet.getFetchSize());
			while(myResultSet.next())
			{
				acID=myResultSet.getInt(1);
				aNumber=myResultSet.getString(2);
				aType=myResultSet.getString(3);
				aOpenedDate=myResultSet.getDate(4).toLocalDate();
				aBalance=myResultSet.getDouble(5);
				aStatus=myResultSet.getString(6);
				transactionFees=myResultSet.getDouble(7);
				aClientNb=myResultSet.getInt(8);
				Customer cust =CustomerDB.searchOneCustomerByID(aClientNb);
				aCheckingAccount = new CheckingAccount(aNumber,aType,aOpenedDate,aStatus,aBalance,cust.getcId(),transactionFees);
				//System.out.println(aCheckingAccount.toString());
				myCheckingAccountList.add(aCheckingAccount);
			}
			return myCheckingAccountList;
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("Failure");
			return myCheckingAccountList;
		}
		
	
	}
}
