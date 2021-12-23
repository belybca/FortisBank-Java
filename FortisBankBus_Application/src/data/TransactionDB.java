package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import bus.Enum_AccountType;
import bus.InputMismatchException;
import bus.RaiseException;
import bus.SavingAccount;
import bus.Transaction;

public class TransactionDB {

	static private Connection myConnection;
	static private String mySQLStatement = null;	
	static private String mySQLQuery = null;
	static private Statement myStatemnt = null;
	static private ResultSet myResultSet = null;
	static private Transaction aTransaction = null;
	/*
	 * return 1 if added successfully otherwise 0
	 */

	public static boolean addTransaction(Transaction trans) throws SQLException, RaiseException, InputMismatchException {

		myConnection =DBConnection.getMyConnection();
		 int accID = 0;
			String sql = "select seq_transaction.nextval from DUAL";
			PreparedStatement ps = myConnection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
			    accID = rs.getInt(1);}
			 int cusID = 0;
				String sql1 = "select cust_id from customer where cust_number='"+trans.getclienNb()+"'";
				PreparedStatement ps1 = myConnection.prepareStatement(sql1);
				ResultSet rs1 = ps1.executeQuery();
				if(rs1.next()) {
					cusID = rs1.getInt(1);
					}
		//introduce transaction fees if more than four transaction in same month
		if(trans.getAccountType() == Enum_AccountType.Checking.toString())
		{
			int month = trans.getTransDate().getMonthValue();
			int count=0;
			List<Transaction> listOfTrans= getAllTransactionList(cusID);
			for(Transaction t : listOfTrans)
			{
				if(t.getTransDate().getMonthValue() == month)
				{
					count++;
				}
				if(count > 5)
				{
					trans.setTransAmount(trans.getTransAmount()+5);
					break;
				}
			}
		}
		if(trans.getAccountType() == Enum_AccountType.Saving.toString())
		{
			SavingAccount sa = SavingAccountDB.searchAcccountByCustomer(trans.getclienNb());
			double interest = calculateInterest(sa);
			sa.setAnualGain(interest);
			SavingAccountDB.updateBalancewithInterest(sa);
		}
				
		String insertQuery="INSERT into Transaction values(?,?,?,?,?,?,?,?)";
		PreparedStatement prepareStatement = null;
		
		try {
			
			prepareStatement = myConnection.prepareStatement(insertQuery);
			prepareStatement.setInt(1,accID);
			prepareStatement.setString(2, trans.getTransDesc());
			prepareStatement.setDate(3, java.sql.Date.valueOf(trans.getTransDate()));
			prepareStatement.setString(4, trans.getTransType());
			prepareStatement.setDouble(5, trans.getTransAmount());
			prepareStatement.setString(6, trans.getAccountType());
			prepareStatement.setInt(7, cusID);
			prepareStatement.setString(8, trans.getclienNb());
			System.out.println(trans.toString());
			int rowcount=prepareStatement.executeUpdate();
			//System.out.println(rowcount+"row count");
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
	private static double calculateInterest(SavingAccount sa) {
		LocalDate today = LocalDate.now();
		double interest=0;
		if(today.getDayOfMonth() == 12 && today.getDayOfMonth() == 31)
		{
			interest = sa.getaBalance()*sa.getAnualInterest()/12;
		}
		
		
		return interest;
	}
	public static ArrayList<Transaction> getAllTransactionList(int custId) {
	
		String query ="select * from transaction where cust_id="+custId;
		@SuppressWarnings("unused")
		int transNumber,transCustomerId,transAccId;
		String transDesc,transType,transCustomerNb,accountType;
		LocalDate transDate;
		Double transAmount;
		ArrayList<Transaction> transList = new ArrayList<Transaction>();
		try {
			myConnection = DBConnection.getMyConnection();
			myStatemnt = myConnection.createStatement();
			myResultSet = myStatemnt.executeQuery(query);
			transList = new ArrayList<Transaction>();
			System.out.println(myResultSet.getFetchSize());
			while(myResultSet.next())
			{
				transNumber=myResultSet.getInt(1);
				transDesc=myResultSet.getString(2);
				transDate=myResultSet.getDate(3).toLocalDate();
				transType=myResultSet.getString(4);
				transAmount=myResultSet.getDouble(5);
				accountType=myResultSet.getString(6);
				transCustomerId=myResultSet.getInt(7);
				transCustomerNb=myResultSet.getString(8);
				aTransaction= new Transaction(transNumber, transDesc,transDate,transType,transAmount,accountType,transCustomerNb,transCustomerId);
				//System.out.println(aTransaction.toString());
				transList.add(aTransaction);
			}
			return transList;
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("Failure");
			return transList;
		}
	}
	
	public static java.sql.Date convertFromJAVADateToSQLDate(
	        java.util.Date javaDate) {
	    java.sql.Date sqlDate = null;
	    if (javaDate != null) {
	        sqlDate = new Date(javaDate.getTime());
	    }
	    return sqlDate;
	}
	
	public static boolean updateTransactionDesc(Transaction aNewTransaction,String custNumber) throws SQLException {
		
		myConnection = DBConnection.getMyConnection();
		
		mySQLStatement = "update transaction set trans_desc =  \'"    
				
			                               +   aNewTransaction.getTransDesc()   +    "\' WHERE  cust_id = '"
			                  
			                  +   custNumber+"'";
		
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
	
	
	public static boolean removeTransaction(String searchText){
		mySQLQuery="delete from TRANSACTION WHERE trans_id=?";
		PreparedStatement prepareStatement = null;
		
		try {
			myConnection = DBConnection.getMyConnection();
			prepareStatement = myConnection.prepareStatement(mySQLQuery);
			prepareStatement.setString(1, searchText);
			int rowcount=prepareStatement.executeUpdate();	
			myConnection.commit();
			if(rowcount == 0)
				return false;
			else
				System.out.println("Succesfull deleted..");
				return true;
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			//System.out.println("Failure");
			return false;
		}
	
	}
	
	public static Transaction searchOneTransactionByNumber(int searchid){
		
	
		mySQLQuery ="select * from TRANSACTION where trans_id='"+searchid+"'";
		@SuppressWarnings("unused")
		int transNumber,transCustomerId,transAccId;
		String transDesc,transType,transCustomerNb,accountType;
		LocalDate transDate;
		Double transAmount;
	
		try {
			myConnection = DBConnection.getMyConnection();
			myStatemnt = myConnection.createStatement();
			myResultSet = myStatemnt.executeQuery(mySQLQuery);
			while(myResultSet.next())
			{
				transNumber=myResultSet.getInt(1);
				transDesc=myResultSet.getString(2);
				transDate=myResultSet.getDate(3).toLocalDate();
				transType=myResultSet.getString(4);
				transAmount=myResultSet.getDouble(5);
				accountType=myResultSet.getString(6);
				transCustomerId=myResultSet.getInt(7);
				transCustomerNb=myResultSet.getString(8);
				aTransaction= new Transaction(transNumber, transDesc,transDate,transType,transAmount,accountType,transCustomerNb,transCustomerId);
				//System.out.println(aTransaction.toString());
			}
			return aTransaction;
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("Failure");
			return aTransaction;
		}
	}
	public static ArrayList<Transaction> searchOneTransactionByCustomer(String searchCusNumber){
		
		
		mySQLQuery ="select * from TRANSACTION where cust_number='"+searchCusNumber+"'";
		@SuppressWarnings("unused")
		int transNumber,transCustomerId,transAccId;
		String transDesc,transType,transCustomerNb,accountType;
		ArrayList<Transaction> trsList = new ArrayList<Transaction>();
		LocalDate transDate;
		Double transAmount;
	
		try {
			myConnection = DBConnection.getMyConnection();
			myStatemnt = myConnection.createStatement();
			myResultSet = myStatemnt.executeQuery(mySQLQuery);
			while(myResultSet.next())
			{
				transNumber=myResultSet.getInt(1);
				transDesc=myResultSet.getString(2);
				transDate=myResultSet.getDate(3).toLocalDate();
				transType=myResultSet.getString(4);
				transAmount=myResultSet.getDouble(5);
				accountType=myResultSet.getString(6);
				transCustomerId=myResultSet.getInt(7);
				transCustomerNb=myResultSet.getString(8);
				aTransaction= new Transaction(transNumber, transDesc,transDate,transType,transAmount,accountType,transCustomerNb,transCustomerId);
				trsList.add(aTransaction);
			}
			return trsList;
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("Failure");
			return trsList;
		}
	}
	
	
}
