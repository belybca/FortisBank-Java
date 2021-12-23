package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static Connection getMyConnection() {
		Connection myConnection = null;
		String userName,password,service,url;
		userName = "fortisBankDB";
		password="123";
		service="localhost";
		url="jdbc:oracle:thin:";
		try {
			
			myConnection = DriverManager.getConnection(url+userName+"/"+password+"@"+service);
			//System.out.println("Connection Successful!!");
			return myConnection;
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("Failure");
			return myConnection;
		}
		
	}


}
