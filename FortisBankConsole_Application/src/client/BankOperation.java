/*
Group
Rameswariben Bhoi
You Pan
Albelis Becea
Description: Fortis Bank application
Date: Nov 21 2021


*/
package client;


import java.util.Scanner;

import bus.Account;
import bus.AccountList;
import bus.CheckingAccount;
import bus.CreditAccount;
import bus.Customer;
import bus.DataValidator;
import bus.InputMismatchException;
import bus.RaiseException;
import bus.SavingAccount;
import bus.Transaction;
import bus.TransactionList;

public class BankOperation {

	
	
	static Scanner scan = new Scanner(System.in);
	static AccountList accList = new AccountList();
	
	public static void DisplayMessage(String message)
	{
		System.out.println(message);
	}
	public static Double getAmount(String msg) throws InputMismatchException
	{
		double amt=0;
		boolean valid =false;
		try {
			
			do {
				BankOperation.DisplayMessage(msg);
				String num= scan.next();
				DataValidator.isNumeric(num);
				amt=Double.parseDouble(num);
				DataValidator.DoubleValidator(amt);
				valid=true;
			}while(!valid);
			return amt;
		}
		catch(RaiseException ex) {
			System.out.println(ex.getMessage());
		}
		return amt;
	}
	public static Double get_AmountSwing(String num) throws InputMismatchException
	{
		double amt=0;
		boolean valid =false;
		try {
			
			do {
				DataValidator.isNumeric(num);
				amt=Double.parseDouble(num);
				DataValidator.DoubleValidator(amt);
				valid=true;
			}while(!valid);
			return amt;
		}
		catch(RaiseException ex) {
			System.out.println(ex.getMessage());
		}
		return amt;
	}
	public static int GetChoiceMenu(int maxNumber,int minNumber) throws InputMismatchException, RaiseException
	{
		int choice=0;
		try {
			
			do {
				BankOperation.DisplayMessage("Enter Your Choice");
				String num= scan.next();
				DataValidator.isNumeric(num);
				choice=Integer.parseInt(num);
				DataValidator.IntValidator(choice);
			}while(choice > maxNumber && choice >= minNumber);
			return choice;
		}
		catch(RaiseException ex) {
			System.out.println(ex.getMessage());
		}
		return choice;
		
	}
	public static Customer getCustomerDetail() throws InputMismatchException, RaiseException
	{	Customer temp=null;
		int openOtherOption;
		String cId, name, pin, accnumber, status = "Active";
		AccountList accountList = new AccountList();
		DisplayMessage("Enter Customer Identification Number: ");
		cId = scan.next();
		scan.nextLine();
		DisplayMessage("Enter Customer Name: ");
		name = scan.next();
		DisplayMessage("Enter Customer Pin: ");
		pin = scan.next();
		DisplayMessage("Select Customer account type: ");
		do {
			DisplayMessage("1-Checking\n2-Saving\n3-Credit\n4-Currency ");
			int selectType=GetChoiceMenu(1,4);
			
			switch (selectType) {
				case 1:
					DisplayMessage("Assing Account number: ");
					accnumber = scan.next();
					accountList.Add(new CheckingAccount(accnumber, status, cId));
					break;
				case 2:
					DisplayMessage("Assign Account number: ");
					accnumber = scan.next();
					accountList.Add(new SavingAccount(accnumber, status, cId));
				case 3:
					DisplayMessage("Assign Account number: ");
					accnumber = scan.next();
					accountList.Add(new CreditAccount(accnumber, status, cId));
				case 4:
					DisplayMessage("Assign Account number: ");
					accnumber = scan.next();
					accountList.Add(new CreditAccount(accnumber, status, cId));	
			}
			DisplayMessage("Do you want to open another account for this customer? [1 open new account, 0 continue] ");
			openOtherOption = scan.nextInt();
			
			temp =new Customer(cId, name, pin, accountList);
			
		} while (openOtherOption == 1);
		return temp;
	}
	
	public static void DisplayAllaccounts(AccountList currentAccounts)
	{
		for(Account item: currentAccounts.getAccountList())
		{
			BankOperation.DisplayMessage(item.toString());
		}
	}
	public static void DisplayTransactions(AccountList accountlist,int choice)
	{

		switch(choice)
		{
		case 1:
			for(Account item:accountlist.getAccountList())
			{
				if(item.getaType() == "CheckingAccount")
				{
					for(Transaction transitem: TransactionList.getTransactionList())
					{
						BankOperation.DisplayMessage(transitem.toString());
					}
				}
	
			}
			break;
		case 2:
			for(Account item:accountlist.getAccountList())
			{
				if(item.getaType() == "SavingAccount")
				{
					for(Transaction transitem: TransactionList.getTransactionList())
					{
						BankOperation.DisplayMessage(transitem.toString());
					}
				}
			}
			break;
		case 3:
			for(Account item:accountlist.getAccountList())
			{
				if(item.getaType() == "CreditAccount")
				{
					for(Transaction transitem: TransactionList.getTransactionList())
					{
						BankOperation.DisplayMessage(transitem.toString());
					}
				}
			}
			break;
		case 4:
			for(Account item:accountlist.getAccountList())
			{
				if(item.getaType() == "CurrencyAccount")
				{
					for(Transaction transitem: TransactionList.getTransactionList())
					{
						BankOperation.DisplayMessage(transitem.toString());
					}
				}	
			}
			break;
		}
	}
}
