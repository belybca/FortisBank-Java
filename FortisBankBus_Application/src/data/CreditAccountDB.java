package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import bus.CreditAccount;
import bus.Customer;
import bus.InputMismatchException;
import bus.RaiseException;


public class CreditAccountDB {

	static private Connection myConnection;
	static private String mySQLStatement = null;	
	static private String mySQLQuery = null;
	static private Statement myStatemnt = null;
	static private ResultSet myResultSet = null;
	static private CreditAccount aCreditAccount  = null;

	/*
	 * return 1 if added successfully otherwise 0
	 */

	public static boolean insert(CreditAccount aNewCreditAccount) throws SQLException {
		
		myConnection =DBConnection.getMyConnection();
		 int accID = 0;
			String sql = "select seq_credit_acc.nextval from DUAL";
			PreparedStatement ps = myConnection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
			    accID = rs.getInt(1);}
		
		String insertQuery="INSERT into CreditAccount values(?,?,?,?,?,?,?,?)";
		PreparedStatement prepareStatement = null;
		
		try {
			
			prepareStatement = myConnection.prepareStatement(insertQuery);
			prepareStatement.setInt(1,accID );
			prepareStatement.setString(2, aNewCreditAccount.getaNumber());
			prepareStatement.setString(3, aNewCreditAccount.getaType());
			prepareStatement.setDate(4, java.sql.Date.valueOf(aNewCreditAccount.getaOpenedDate()));
			prepareStatement.setDouble(5, 0);
			prepareStatement.setString(6, aNewCreditAccount.getaStatus());	
			prepareStatement.setDouble(7, aNewCreditAccount.getInterestRate());
			int custID = CustomerDB.searchCustomerID(aNewCreditAccount.getaClientNb());
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
	
	public static boolean updateBalance(CreditAccount aNewCreditAccount) throws SQLException {
		
		myConnection = DBConnection.getMyConnection();
		
		mySQLStatement = "update CreditAccount set cr_acc_balance ="    
				
			                               +   aNewCreditAccount.getaBalance()     +    "WHERE  cr_acc_no = '"
			                  
			                  +  aNewCreditAccount.getaNumber()+"'";
		
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
		
		mySQLStatement = "Delete FROM CreditAccount WHERE cr_acc_no = '" +  acNumber+"'";
				
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
	
	public static CreditAccount search(String acNumber) throws SQLException, SQLException, RaiseException, InputMismatchException{
		
		
		myConnection = DBConnection.getMyConnection();
		
		mySQLQuery = "SELECT * FROM CreditAccount WHERE  cr_acc_no = '" + acNumber +"'";
		
		
		String aNumber,aStatus,aType;
		int aClientNb;
		LocalDate aOpenedDate;
		Double aBalance,InterestRate;
		@SuppressWarnings("unused")
		int aID;
	//(aNumber,aType,aOpenedDate,aStatus,aBalance,aClientNb,anualInterest,anualGain,extraFees)	
		try {
		
			myStatemnt = myConnection.createStatement();
			myResultSet = myStatemnt.executeQuery(mySQLQuery);
			
			//System.out.println(myResultSet.getFetchSize());
			while(myResultSet.next())
			{
				aID=myResultSet.getInt(1);
				aNumber=myResultSet.getString(2);
				aType=myResultSet.getString(3);
				aOpenedDate=myResultSet.getDate(4).toLocalDate();
				aBalance=myResultSet.getDouble(5);
				aStatus=myResultSet.getString(6);
				InterestRate=myResultSet.getDouble(7);
				aClientNb=myResultSet.getInt(8);
				Customer cust =CustomerDB.searchOneCustomerByID(aClientNb);
		
				aCreditAccount = new CreditAccount(aNumber,aType,aOpenedDate, aStatus,aBalance, cust.getcId(),InterestRate);
				
				
			}
			return aCreditAccount;
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("Failure");
			return aCreditAccount;
		}
	
	}
	
	public static ArrayList<CreditAccount> select() throws SQLException, NumberFormatException, SQLException, RaiseException, InputMismatchException{
		
		
		String aNumber,aStatus,aType;
		LocalDate aOpenedDate;
		Double aBalance,InterestRate;
		@SuppressWarnings("unused")
		int aID,aClientNb;
		ArrayList<CreditAccount> mySavingAccountList = new ArrayList<CreditAccount>();
		
		myConnection = DBConnection.getMyConnection();
		
		mySQLQuery = "SELECT * FROM CreditAccount";
		
		
		
		try {
			
			myStatemnt = myConnection.createStatement();
			myResultSet = myStatemnt.executeQuery(mySQLQuery);
			
			
			//System.out.println(myResultSet.getFetchSize());
			while(myResultSet.next())
			{
				aID=myResultSet.getInt(1);
				aNumber=myResultSet.getString(2);
				aType=myResultSet.getString(3);
				aOpenedDate=myResultSet.getDate(4).toLocalDate();
				aBalance=myResultSet.getDouble(5);
				aStatus=myResultSet.getString(6);
				InterestRate=myResultSet.getDouble(7);
				aClientNb=myResultSet.getInt(8);
				Customer cust =CustomerDB.searchOneCustomerByID(aClientNb);
				aCreditAccount = new CreditAccount(aNumber,aType,aOpenedDate, aStatus,aBalance, cust.getcId(),InterestRate);
				
				
				mySavingAccountList.add(aCreditAccount);
			}
			return mySavingAccountList;
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("Failure");
			return mySavingAccountList;
		}
		
	
	}
	public static CreditAccount searchAcccountByCustomer(String userId) throws RaiseException, InputMismatchException {

		
		myConnection = DBConnection.getMyConnection();
		int custID = CustomerDB.searchCustomerID(userId);
		mySQLQuery = "SELECT * FROM CreditAccount WHERE  CUST_ID = " + custID;
		
		
		String aNumber,aStatus,aType;
		int aClientNb;
		LocalDate aOpenedDate;
		Double aBalance,InterestRate;
		@SuppressWarnings("unused")
		int aID;
	//(aNumber,aType,aOpenedDate,aStatus,aBalance,aClientNb,anualInterest,anualGain,extraFees)	
		try {
		
			myStatemnt = myConnection.createStatement();
			myResultSet = myStatemnt.executeQuery(mySQLQuery);
			
			//System.out.println(myResultSet.getFetchSize());
			while(myResultSet.next())
			{
				aID=myResultSet.getInt(1);
				aNumber=myResultSet.getString(2);
				aType=myResultSet.getString(3);
				aOpenedDate=myResultSet.getDate(4).toLocalDate();
				aBalance=myResultSet.getDouble(5);
				aStatus=myResultSet.getString(6);
				InterestRate=myResultSet.getDouble(7);
				aClientNb=myResultSet.getInt(8);
				Customer cust =CustomerDB.searchOneCustomerByID(aClientNb);
		
				aCreditAccount = new CreditAccount(aNumber,aType,aOpenedDate, aStatus,aBalance, cust.getcId(),InterestRate);
				
				
			}
			return aCreditAccount;
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("Failure");
			return aCreditAccount;
		}
	
	}
}
