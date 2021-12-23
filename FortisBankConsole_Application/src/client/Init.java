/*
Group
Rameswariben Bhoi
You Pan
Albelis Becea
Description: Fortis Bank application
Date: Nov 21 2021


*/
package client;

import java.io.IOException;
import java.text.ParseException;

import bus.AccountList;
import bus.CheckingAccount;
import bus.CurrencyAccount;
import bus.SavingAccount;
import bus.Customer;
import bus.CustomerList;
//import bus.DataSource;
import bus.InputMismatchException;
import bus.RaiseException;

public class Init {

	public static CustomerList Init_Customer() throws IOException, RaiseException, InputMismatchException {
		
		CustomerList listOfCust = new CustomerList();
		
		listOfCust.Add( new Customer("cl001" , "Bill Gates" , "windows","Active"));
		listOfCust.Add( new Customer("cl002" , "Steve Jobs" , "apple","Active"));
		listOfCust.Add( new Customer("cl003" , "You Pan" , "poutine","Active"));
		listOfCust.Add( new Customer("cl004" , "Albelis Becea" , "unodostres","Active"));
		return listOfCust;
		//DataSource.writeToSerializedFileCus(listOfCust);
	}
	
	public static AccountList Init_Account() throws IOException, ParseException
	{
		try {
				
				AccountList listOfAccount = new AccountList();
				listOfAccount.Add( new CheckingAccount("ac001" , 14,11,2000, "active",(double) 8200000, "cl001"));
				listOfAccount.Add( new SavingAccount("ac002" ,11,03,2001, "active",(double)34000,"cl001"));
				listOfAccount.Add( new CheckingAccount("ac003" ,7,9,1990, "active",(double)4000000,"cl002"));
				listOfAccount.Add( new CheckingAccount("ac004", 8,12,1999, "active",(double)890000,"cl003"));
				listOfAccount.Add( new CheckingAccount("ac005" ,21,10,2000, "active",(double)200000,"cl004"));
				listOfAccount.Add( new CurrencyAccount("ac006" , 20,10,2021, "active",(double)400000,"cl003"));
				return listOfAccount;
				//DataSource.writeToSerializedFileAc(listOfAccount);
			
		} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return null;
	}
}
