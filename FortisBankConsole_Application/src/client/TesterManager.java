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

import java.util.Scanner;
import bus.AccountList;
import bus.CheckingAccount;
import bus.CreditAccount;
import bus.CurrencyAccount;
import bus.Customer;
import bus.CustomerList;
import bus.DataReader;
import bus.InputMismatchException;
import bus.RaiseException;
import bus.SavingAccount;

public class TesterManager {

	public static void DisplayMenu() {
		System.out.println("1 - Add Customer");
		System.out.println("2 - Display Customer List");
		System.out.println("3 - Search Customer");
		System.out.println("4 - Delete Customer");
		System.out.println("5 - Exit");
	}
	
	public static void main(String[] args) throws ClassNotFoundException, IOException, InputMismatchException, RaiseException {
		
		
		Scanner scan = new Scanner(System.in);
		String accnumber,status;
		int openOtherOption;
		AccountList customerAccs = new AccountList(); //adding Data
		CustomerList customers = new CustomerList();
		customers = DataReader.readAllDataFromCustomerFile();
		Customer cust;
		
		status="Active";
		
		Boolean isExit= false;
		int choice;
		String customerNumber;
		
		

		do {
			DisplayMenu();
			
			choice = BankOperation.GetChoiceMenu(5, 1);
			
			switch(choice)
			{
				case 1:
					do {
						System.out.println("Register a new Customer: ");
						cust = new Customer();
						boolean valid = false;		
						do
						{
							try
							{
								System.out.println("Enter Customer Identification Number: ");
								cust.setcId(scan.next());
								valid =  true;				
							}
							catch(RaiseException ex)
							{
								System.out.println(ex.getMessage());				
							}	
						}while(!valid);
					
						
						valid = false;
						scan.nextLine();
						do
						{
							try
							{
							System.out.println("Enter Customer Name and Last Name: ");
								
								cust.setcName(scan.nextLine());
								
								valid =  true;				
							}
							catch(RaiseException ex)
							{
								System.out.println(ex.getMessage());				
							}
						}while(!valid);
					
						
						valid = false;
						
						do
						{
							try
							{
								System.out.println("Enter Customer Pin: ");
								cust.setcPin(scan.next());
								valid =  true;				
							}
							catch(RaiseException ex)
							{
								System.out.println(ex.getMessage());				
							}	
						}while(!valid);
					
						
						valid = false;
						

						
						System.out.println("Select Customer account type: ");

						do {
							int selectType=0;
							do {
								System.out.println("1-Checking\n2-Saving\n3-Credit\n4-Currency ");
								selectType = BankOperation.GetChoiceMenu(4, 1);
								valid=true;
							}while(!valid);
							
							valid=false;
							System.out.println("Assing Account number: ");
							accnumber=scan.next();
							switch(selectType)
							{
							case 1:
	
							customerAccs.Add(new CheckingAccount(accnumber,status,cust.getcId()));
							break;
	
							case 2:
	
							customerAccs.Add(new SavingAccount(accnumber,status,cust.getcId()));
							case 3:
	
							customerAccs.Add(new CurrencyAccount(accnumber,status,cust.getcId()));
							case 4:
	
							customerAccs.Add(new CreditAccount(accnumber,status,cust.getcId()));
							}
	
							do {
								System.out.println("Do you want to open another account for this customer? [1 open new account, 2 continue] ");
								openOtherOption=BankOperation.GetChoiceMenu(2, 1);
								valid=true;
							}while(!valid);
							valid=false;
							}while(openOtherOption==1);
							cust.setcAccounts(customerAccs);
							cust.setcStatus(status);
							if(customers.Add(cust))
							{
	
							System.out.println("Register Completed! ");
	
							}
	
							do {
							System.out.println("Do you want to register another customer? [2 add new customer, 1 exit to menu] ");
							openOtherOption=BankOperation.GetChoiceMenu(2, 1);
							valid=true;
							}while(!valid);
						}while(openOtherOption==2);
						break;
				case 2:
					
					System.out.println("List Of Customers ");
					if(customers.getCustList().isEmpty()) {
						System.out.println("There is not customers on the list\n");
					}
					else {
					System.out.println(customers.Display());}
					
					break;
				case 3:
					System.out.println("Enter Your Number for search :");
					customerNumber = scan.next();
					cust = customers.Searchs(customerNumber);
					if(cust == null)
					{
						System.out.println("No data found");
					}
					else {
						System.out.println(cust.toString());
					}
					
					break;
		
				case 4:
					String cusNumber;
					System.out.println("Remove a Customer: ");
					
					System.out.println("Enter the identifier of the customer to remove: ");
					cusNumber=scan.next();
					
					cust=customers.Searchs(cusNumber);
					if(cust!=null)
					{
						if(customers.Delete(cust))
						{
							System.out.println(" This customer has been removed");
						}
					}
					else {System.out.println("Wrong customer identifier doesn't exist");}
					
					break;
				case 5:
					isExit = true;
					//for(Account ac : customerAccs.getAccountList()) {DataReader.writeDataToAccountFile(ac);}
										
					DataReader.writeAllDataToCustomerFile(customers.getCustList());
					
					break;
			}
		}while(isExit == false);
		
		scan.close();
	}
		
		
		
		
	}
/* TESTER MANAGER SIDE ALBELIS
1 - Add Customer
2 - Display Customer List
3 - Search Customer
4 - Delete Customer
5 - Exit
Enter Your Choice
a
invalid input- value must be only Numeric
1 - Add Customer
2 - Display Customer List
3 - Search Customer
4 - Delete Customer
5 - Exit
Enter Your Choice
1
Register a new Customer: 
Enter Customer Identification Number: 
newCust566
Enter Customer Name and Last Name: 
Test Customer
Enter Customer Pin: 
8
invalid input- value must be only alphabet letter
Enter Customer Pin: 
test
Select Customer account type: 
1-Checking
2-Saving
3-Credit
4-Currency 
Enter Your Choice
1
Assing Account number: 
acTest56
Do you want to open another account for this customer? [1 open new account, 2 continue] 
Enter Your Choice
1
1-Checking
2-Saving
3-Credit
4-Currency 
Enter Your Choice
2
Assing Account number: 
acTes57
Do you want to open another account for this customer? [1 open new account, 2 continue] 
Enter Your Choice
2
Register Completed! 
Do you want to register another customer? [2 add new customer, 1 exit to menu] 
Enter Your Choice
2
Register a new Customer: 
Enter Customer Identification Number: 
otherCust531
Enter Customer Name and Last Name: 
Tester two
Enter Customer Pin: 
two
Select Customer account type: 
1-Checking
2-Saving
3-Credit
4-Currency 
Enter Your Choice
1
Assing Account number: 
ac679
Do you want to open another account for this customer? [1 open new account, 2 continue] 
Enter Your Choice
1
1-Checking
2-Saving
3-Credit
4-Currency 
Enter Your Choice
4
Assing Account number: 
acCurr3
Do you want to open another account for this customer? [1 open new account, 2 continue] 
Enter Your Choice
2
Register Completed! 
Do you want to register another customer? [2 add new customer, 1 exit to menu] 
Enter Your Choice
1
1 - Add Customer
2 - Display Customer List
3 - Search Customer
4 - Delete Customer
5 - Exit
Enter Your Choice
2
List Of Customers 
Customer [cId=cl001, cName=Bill Gates, cPin=windows, cStatus=Active]
--------------------------------------------
Customer [cId=cl002, cName=Steve Jobs, cPin=apple, cStatus=Active]
--------------------------------------------
Customer [cId=cl003, cName=You Pan, cPin=poutine, cStatus=Active]
--------------------------------------------
Customer [cId=cl004, cName=Albelis Becea, cPin=unodostres, cStatus=Active]
--------------------------------------------
Customer [cId=cl005, cName=Rames Bhoi, cPin=rangoli, cStatus=Active]
--------------------------------------------
Customer [cId=cl006, cName=Rosa Machado, cPin=rosas, cStatus=Active]
--------------------------------------------
Customer [cId=cl090834, cName=huy, cPin=ryu, cStatus=Active]
--------------------------------------------
Customer [cId=cl00458, cName=Romulo Gallegos, cPin=donabarbara, cStatus=Active]
--------------------------------------------
Customer [cId=cl09834, cName=Aquiles Nazoa, cPin=elburroflautista, cStatus=Active]
--------------------------------------------
Customer [cId=newCust566, cName=Test Customer, cPin=test, cStatus=Active]
--------------------------------------------
Customer [cId=otherCust531, cName=Tester two, cPin=two, cStatus=Active]
--------------------------------------------

1 - Add Customer
2 - Display Customer List
3 - Search Customer
4 - Delete Customer
5 - Exit
Enter Your Choice
3
Enter Your Number for search :
newCust566
Customer [cId=newCust566, cName=Test Customer, cPin=test, cStatus=Active]
1 - Add Customer
2 - Display Customer List
3 - Search Customer
4 - Delete Customer
5 - Exit
Enter Your Choice
3
Enter Your Number for search :
s
No data found
1 - Add Customer
2 - Display Customer List
3 - Search Customer
4 - Delete Customer
5 - Exit
Enter Your Choice
4
Remove a Customer: 
Enter the identifier of the customer to remove: 
a
Wrong customer identifier doesn't exist
1 - Add Customer
2 - Display Customer List
3 - Search Customer
4 - Delete Customer
5 - Exit
Enter Your Choice
4
Remove a Customer: 
Enter the identifier of the customer to remove: 
otherCust531
This customer has been removed
1 - Add Customer
2 - Display Customer List
3 - Search Customer
4 - Delete Customer
5 - Exit
Enter Your Choice
2
List Of Customers 
Customer [cId=cl001, cName=Bill Gates, cPin=windows, cStatus=Active]
--------------------------------------------
Customer [cId=cl002, cName=Steve Jobs, cPin=apple, cStatus=Active]
--------------------------------------------
Customer [cId=cl003, cName=You Pan, cPin=poutine, cStatus=Active]
--------------------------------------------
Customer [cId=cl004, cName=Albelis Becea, cPin=unodostres, cStatus=Active]
--------------------------------------------
Customer [cId=cl005, cName=Rames Bhoi, cPin=rangoli, cStatus=Active]
--------------------------------------------
Customer [cId=cl006, cName=Rosa Machado, cPin=rosas, cStatus=Active]
--------------------------------------------
Customer [cId=cl090834, cName=huy, cPin=ryu, cStatus=Active]
--------------------------------------------
Customer [cId=cl00458, cName=Romulo Gallegos, cPin=donabarbara, cStatus=Active]
--------------------------------------------
Customer [cId=cl09834, cName=Aquiles Nazoa, cPin=elburroflautista, cStatus=Active]
--------------------------------------------
Customer [cId=newCust566, cName=Test Customer, cPin=test, cStatus=Active]
--------------------------------------------

1 - Add Customer
2 - Display Customer List
3 - Search Customer
4 - Delete Customer
5 - Exit
Enter Your Choice
5


*/
