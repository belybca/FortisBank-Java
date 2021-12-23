/*
Group
Rameswariben Bhoi
You Pan
Albelis Becea
Description: Fortis Bank application
Date: Nov 21 2021


*/
package bus;

import java.util.ArrayList;

public class AccountList  implements java.io.Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
static private ArrayList<Account> accountList = new ArrayList<>();
	
	static public int NumberOfAccounts()
	{
		
		return accountList.size();
		
	}
	
	public ArrayList<Account> getAccountList()
	{
		return accountList;
	}
	
	public Boolean Add(Account account)
	{
		if(accountList.contains(account)==false)
		{
			accountList.add(account);
			return true;
		}
		else return false;
	}
	public Boolean Delete(Account account)
	{		
		return accountList.remove(account);
		
	}
	public Account Searchs(String key)
	{
		Account accountSearch=null;
		
		for(Account acc : accountList)
		{
			if(acc.getaNumber().equals(key))
			{
				accountSearch=acc;
			}
		}
		return  accountSearch;
	}
	public Account searchsByType(String type)
	{
		Account accountSearch=null;
		
		for(Account acc : accountList)
		{
			if(acc.getaType().equals(type))
			{
				accountSearch=acc;
			}
		}
		return  accountSearch;
	}
	
	//Display
	public String Display() 
	{
		String info="";
		for(Account account : accountList)
		{
			info=info + account.toString()+"\n--------------------------------------------\n";
		}
		return info;
	}
}

