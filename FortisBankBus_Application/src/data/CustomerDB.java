package data;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bus.Customer;
import bus.InputMismatchException;
import bus.RaiseException;

public class CustomerDB {

	static private Connection myConnection;
	static private String mySQLStatement = null;	
	static private String mySQLQuery = null;
	static private Statement myStatemnt = null;
	static private ResultSet myResultSet = null;
	static private Customer aCustomer = null;
	/*
	 * return 1 if added successfully otherwise 0
	 */

	public static boolean AddOneCustomer(Customer cust)throws SQLException 
	{
		myConnection =DBConnection.getMyConnection();
		 int customerID = 0;
		String sql = "select seq_customer.nextval from DUAL";
		PreparedStatement ps = myConnection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
		    customerID = rs.getInt(1);}
		
		
		mySQLQuery="INSERT into Customer values(?,?,?,?,?)";
		PreparedStatement prepareStatement = null;
		
		try {
			
			prepareStatement = myConnection.prepareStatement(mySQLQuery);
			
			//System.out.println(cust.toString());
			prepareStatement.setInt(1,customerID);
			prepareStatement.setString(2, cust.getcId());
			prepareStatement.setString(3, cust.getcName());
			prepareStatement.setString(4, cust.getcPin());
			prepareStatement.setString(5, cust.getcStatus());
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
	
	public static boolean updateCustomerName(Customer aNewCustomer) throws SQLException {
		
		myConnection = DBConnection.getMyConnection();
		
		mySQLStatement = "update customer set cust_name =  \'"    
				
			                               +   aNewCustomer.getcName()     +    "\' WHERE  CUST_NUMBER = '"
			                  
			                  +  aNewCustomer.getcId()+"'";
		
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
	
	
	public static boolean removeCustomer(String searchText){
		mySQLQuery="delete from CUSTOMER WHERE CUST_NUMBER=?";
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
			System.out.println("Failure");
			return false;
		}
	
	}
	
	public static Customer searchOneCustomerByNumber(String searchText) throws RaiseException, InputMismatchException{
		
	
		mySQLQuery ="select * from CUSTOMER where CUST_NUMBER='"+searchText+"'";
		String cId,cName,cPin,cStatus;
	
		try {
			myConnection = DBConnection.getMyConnection();
			myStatemnt = myConnection.createStatement();
			myResultSet = myStatemnt.executeQuery(mySQLQuery);
			while(myResultSet.next())
			{
				cId=myResultSet.getString(2);
				cName=myResultSet.getString(3);
				cPin=myResultSet.getString(4);
				cStatus=myResultSet.getString(5);
				aCustomer = new Customer(cId,cName,cPin,cStatus);
			}
			return aCustomer;
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("Failure");
			return aCustomer;
		}
	}
	public static int searchCustomerID(String searchText){
		
		
		mySQLQuery ="select cust_id from CUSTOMER where CUST_NUMBER='"+searchText+"'";
		int cId=0;
	
		try {
			myConnection = DBConnection.getMyConnection();
			myStatemnt = myConnection.createStatement();
			myResultSet = myStatemnt.executeQuery(mySQLQuery);
			while(myResultSet.next())
			{
				cId=myResultSet.getInt(1);
				System.out.println(cId);
				return cId;
				
			}
			return cId;
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("Failure");
			return cId;
		}
	}
	public static Customer searchOneCustomerByID(int searchNumber) throws RaiseException, InputMismatchException{
		

		mySQLQuery ="select * from CUSTOMER where CUST_ID="+searchNumber+"";
		String cId,cName,cPin,cStatus;
		
		try {
			myConnection = DBConnection.getMyConnection();
			myStatemnt = myConnection.createStatement();
			myResultSet = myStatemnt.executeQuery(mySQLQuery);
			while(myResultSet.next())
			{
				cId=myResultSet.getString(2);
				cName=myResultSet.getString(3);
				cPin=myResultSet.getString(4);
				cStatus=myResultSet.getString(5);
				aCustomer = new Customer(cId,cName,cPin,cStatus);
			}
			return aCustomer;
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("Failure");
			return aCustomer;
		}
	}
	public static ArrayList<Customer> getAllCustomer() throws RaiseException, InputMismatchException{
		
		mySQLQuery="select * from CUSTOMER";
		String cId,cName,cPin,cStatus;
		ArrayList<Customer> custList = null;
		try {
			myConnection = DBConnection.getMyConnection();
			myStatemnt = myConnection.createStatement();
			myResultSet = myStatemnt.executeQuery(mySQLQuery);
			custList = new ArrayList<Customer>();
			while(myResultSet.next())
			{
				cId=myResultSet.getString(2);
				cName=myResultSet.getString(3);
				cPin=myResultSet.getString(4);
				cStatus=myResultSet.getString(5);
				Customer cust = new Customer(cId,cName,cPin,cStatus);
				custList.add(cust);
			}
			return custList;
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("Failure");
			return custList;
		}
	}

	public static Customer Login(String userId, String password) throws RaiseException, InputMismatchException {
		mySQLQuery ="select * from CUSTOMER where CUST_NUMBER='"+userId+"' and CUST_PIN='"+password+"'";
		String cId,cName,cPin,cStatus;
		
		try {
			myConnection = DBConnection.getMyConnection();
			myStatemnt = myConnection.createStatement();
			myResultSet = myStatemnt.executeQuery(mySQLQuery);
			while(myResultSet.next())
			{
				cId=myResultSet.getString(2);
				cName=myResultSet.getString(3);
				cPin=myResultSet.getString(4);
				cStatus=myResultSet.getString(5);
				aCustomer = new Customer(cId,cName,cPin,cStatus);
				
			}
			return aCustomer;
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("Failure");
			return aCustomer;
		}
	}
}
