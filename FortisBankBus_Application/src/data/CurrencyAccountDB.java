package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;


import bus.CurrencyAccount;
import bus.Customer;
import bus.InputMismatchException;
import bus.RaiseException;

public class CurrencyAccountDB {

	static private Connection myConnection;
	static private String mySQLStatement = null;	
	static private String mySQLQuery = null;
	static private Statement myStatemnt = null;
	static private ResultSet myResultSet = null;
	static private CurrencyAccount aCurrencyAccount = null;
	/*
	 * return 1 if added successfully otherwise 0
	 */

	public static boolean insert(CurrencyAccount aNewCurrencyAccount) throws SQLException {
		myConnection =DBConnection.getMyConnection();
		 int accID = 0;
			String sql = "select seq_currency_acc.nextval from DUAL";
			PreparedStatement ps = myConnection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
			    accID = rs.getInt(1);}
		
		String insertQuery="INSERT into CurrencyAccount values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement prepareStatement = null;
		
		try {
			
			prepareStatement = myConnection.prepareStatement(insertQuery);
			
			prepareStatement.setInt(1,accID );
			prepareStatement.setString(2, aNewCurrencyAccount.getaNumber());
			prepareStatement.setString(3, aNewCurrencyAccount.getaType());
			prepareStatement.setDate(4, java.sql.Date.valueOf(aNewCurrencyAccount.getaOpenedDate()));
			prepareStatement.setDouble(5, 0);
			prepareStatement.setString(6, aNewCurrencyAccount.getaStatus());
			prepareStatement.setDouble(7, aNewCurrencyAccount.getAcComision());
			prepareStatement.setDouble(8, aNewCurrencyAccount.getConvertionRate());
			int custID = CustomerDB.searchCustomerID(aNewCurrencyAccount.getaClientNb());
			prepareStatement.setInt(9, custID);
			
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
	
	public static boolean updateBalance(CurrencyAccount aNewCurrencyAccount) throws SQLException {
		
		myConnection = DBConnection.getMyConnection();
		
		mySQLStatement = "update CurrencyAccount set curr_acc_balance =  "    
				
			                               +   aNewCurrencyAccount.getaBalance()     +    "WHERE  curr_acc_no = '"
			                  
			                  +  aNewCurrencyAccount.getaNumber()+"'";
		
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
		
		mySQLStatement = "Delete FROM CurrencyAccount WHERE curr_acc_no = '" +  acNumber+"'";
				
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
	
	public static CurrencyAccount search(String acNumber) throws SQLException, SQLException, ParseException, RaiseException, InputMismatchException{
		
		
		myConnection = DBConnection.getMyConnection();
		
		mySQLQuery = "SELECT * FROM CurrencyAccount WHERE  curr_acc_no = '" + acNumber+"'";
		
		
		String aNumber,aType,aStatus;
		LocalDate aOpenedDate;
		Double aBalance,acComision, convertionRate;
		@SuppressWarnings("unused")
		int aID,aClientNb;
		
		
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
				acComision=myResultSet.getDouble(7);
				convertionRate=myResultSet.getDouble(8);
				aClientNb=myResultSet.getInt(9);
				Customer cust =CustomerDB.searchOneCustomerByID(aClientNb);
				aCurrencyAccount = new CurrencyAccount(aNumber,aType,aOpenedDate, aStatus,aBalance, cust.getcId(),acComision, convertionRate);
				//System.out.println(aCheckingAccount.toString());
				
			}
			return aCurrencyAccount;
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("Failure");
			return aCurrencyAccount;
		}
	
	}
	
public static CurrencyAccount searchAcccountByCustomer(String custId) throws SQLException, SQLException, ParseException, RaiseException, InputMismatchException{
		
		
		myConnection = DBConnection.getMyConnection();
		int custID = CustomerDB.searchCustomerID(custId);
		mySQLQuery = "SELECT * FROM CurrencyAccount WHERE CUST_ID = " + custID;
		
		
		String aNumber,aType,aStatus;
		LocalDate aOpenedDate;
		Double aBalance,acComision, convertionRate;
		@SuppressWarnings("unused")
		int aID,aClientNb;
		
		
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
				acComision=myResultSet.getDouble(7);
				convertionRate=myResultSet.getDouble(8);
				aClientNb=myResultSet.getInt(9);
				Customer cust =CustomerDB.searchOneCustomerByID(aClientNb);
				aCurrencyAccount = new CurrencyAccount(aNumber,aType,aOpenedDate, aStatus,aBalance, cust.getcId(),acComision, convertionRate);
				//System.out.println(aCheckingAccount.toString());
				
			}
			return aCurrencyAccount;
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("Failure");
			return aCurrencyAccount;
		}
	
	}

	public static ArrayList<CurrencyAccount> select() throws SQLException, NumberFormatException, SQLException, ParseException, RaiseException, InputMismatchException{
		
		
		String aNumber,aType,aStatus;
		LocalDate aOpenedDate;
		Double aBalance,acComision, convertionRate;
		@SuppressWarnings("unused")
		int aID,aClientNb;
		ArrayList<CurrencyAccount> myCurrencyAccountList = new ArrayList<CurrencyAccount>();
		
		myConnection = DBConnection.getMyConnection();
		
		mySQLQuery = "SELECT * FROM CurrencyAccount";
		
		
		
		try {
			
			myStatemnt = myConnection.createStatement();
			myResultSet = myStatemnt.executeQuery(mySQLQuery);
			
			
			
			while(myResultSet.next())
			{
				aID=myResultSet.getInt(1);
				aNumber=myResultSet.getString(2);
				aType=myResultSet.getString(3);
				aOpenedDate=myResultSet.getDate(4).toLocalDate();
				aBalance=myResultSet.getDouble(5);
				aStatus=myResultSet.getString(6);
				acComision=myResultSet.getDouble(7);
				convertionRate=myResultSet.getDouble(8);
				aClientNb=myResultSet.getInt(9);
				Customer cust =CustomerDB.searchOneCustomerByID(aClientNb);
				aCurrencyAccount = new CurrencyAccount(aNumber,aType,aOpenedDate, aStatus,aBalance, cust.getcId(),acComision, convertionRate);
				
				myCurrencyAccountList.add(aCurrencyAccount);
			}
			return myCurrencyAccountList;
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("Failure");
			return myCurrencyAccountList;
		}
		
	
	}
}
