/*
Group
Rameswariben Bhoi
You Pan
Albelis Becea
Description: Fortis Bank application
Date: Nov 21 2021


*/
package bus;


import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;


public class DataReader {
	public static String filePathCustomers = "src//data//Customer.txt";
	public static String filePathAccounts = "src//data//Account.txt";

	// Customer data

	public static void writeAllDataToCustomerFile(ArrayList<Customer> custList) throws IOException
	{
		PrintWriter pw = new PrintWriter(filePathCustomers);
		
        for(Customer cust :custList) {
        	pw.println(cust.getcId());
        	pw.println(cust.getcName());
        	pw.println(cust.getcPin());
        	pw.println(cust.getcStatus());
        	
        }
        pw.close(); 
	}
	
	public static void writeDataToCustomerFile(Customer cust) throws IOException
	{
		PrintWriter pw = new PrintWriter(filePathCustomers);
		
		pw.append(cust.getcId());
    	pw.append(cust.getcName());
    	pw.append(cust.getcPin());
    	pw.append(cust.getcStatus());
    	
        pw.close(); 
	}

	
	public static CustomerList readAllDataFromCustomerFile() throws IOException, ClassNotFoundException, RaiseException, InputMismatchException
	{
		CustomerList custList = new CustomerList();
		
		Scanner br = new Scanner(new FileReader(filePathCustomers));
		while(br.hasNextLine())
         {
        	 String num = br.nextLine();
             String name = br.nextLine();
             String pin = br.nextLine();
             String stat =br.nextLine();
             Customer temp = new Customer(num,name,pin,stat);
             custList.Add(temp);
         }
         br.close();
		return custList;
	}
	
	// Account Data
	public static void writeDataToAccountFile(Account newacc) throws IOException
	{
		PrintWriter pw = new PrintWriter(filePathAccounts);
		
       
        	pw.append(newacc.getaNumber());
        	pw.append(newacc.getaType());
        	pw.append(String.valueOf(newacc.getaOpenedDate().getDayOfMonth()));
        	pw.append(String.valueOf(newacc.getaOpenedDate().getMonth().getValue()));
        	pw.append(String.valueOf(newacc.getaOpenedDate().getYear()));
        	pw.append(newacc.getaBalance().toString());
        	pw.append(newacc.getaStatus());
        	pw.append(newacc.getaClientNb());
        
        	pw.close();
	}
	public static void writeAllDataToAccountFileEdit(Account currAcc) throws IOException
	{
		Scanner br = new Scanner(new FileReader(filePathAccounts));
		PrintWriter pw = new PrintWriter(filePathAccounts);
		while(br.hasNextLine())
         {
       	 String numAc = br.nextLine();
               
        
         if(numAc.compareTo(currAcc.getaNumber())==0) {
        	

             
             	pw.println(currAcc.getaNumber());
             	pw.println(currAcc.getaType());
             	pw.println(currAcc.getaOpenedDate().getDayOfMonth());
             	pw.println(currAcc.getaOpenedDate().getMonth().getValue());
             	pw.println(currAcc.getaOpenedDate().getYear());
             	pw.println(currAcc.getaBalance());
             	pw.println(currAcc.getaStatus());
             	pw.println(currAcc.getaClientNb());
             
		   	 
        
     }
         pw.close();
     
     }
		 
     br.close();
		
		
       
	}
	public static AccountList readAllDataFromAccountFile(String clientnumber) throws IOException, ClassNotFoundException, ParseException
	{
		AccountList accountList = new AccountList();
		Scanner br = new Scanner(new FileReader(filePathAccounts));
         
		while(br.hasNextLine())
         {
        	 String numAc = br.nextLine();
             String type = br.nextLine();
             int day = Integer.parseInt(br.nextLine());
             int month = Integer.parseInt(br.nextLine());
             int year =Integer.parseInt(br.nextLine());
             Double balance =Double.parseDouble(br.nextLine());
             String status =br.nextLine();
             String cNumber = br.nextLine();
            
            
             if(cNumber.compareTo(clientnumber)==0 && type.compareTo("Checking")==0) {
            	
	             	CheckingAccount chkAcc = new CheckingAccount(numAc,day,month,year,status,balance,cNumber);
					accountList.Add(chkAcc);
             }
             else if(cNumber.compareTo(clientnumber)==0 && type.compareTo("Saving")==0) {
            	           
	            	 SavingAccount saveAcc = new SavingAccount(numAc,day,month,year,status,balance,cNumber);
	            	 accountList.Add(saveAcc);
	            	 }
             else if(cNumber.compareTo(clientnumber)==0 && type.compareTo("Credit")==0) {
            	 	CreditAccount creditAcc = new CreditAccount(numAc,day,month,year,status,balance,cNumber);
	            	 accountList.Add(creditAcc);
             }
             else if(cNumber.compareTo(clientnumber)==0 && type.compareTo("Currency")==0) {
            	 	CurrencyAccount currencyAccs = new CurrencyAccount(numAc,day,month,year,status,balance,cNumber);
	            	 accountList.Add(currencyAccs);
	            	 
            
         }
             
         
         }
         br.close();
		return accountList;
	}
	
}
