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


public class TransactionList {


static private ArrayList<Transaction> transList = new ArrayList<>();
	
	public int NumberOfTransactions()
	{
		
		return transList.size();
		
	}
	
	public static ArrayList<Transaction> getTransactionList()
	{
		return transList;
	}
	
	public Boolean Add(Transaction trans)
	{
		if(transList.contains(trans)==false)
		{
			transList.add(trans);
			return true;
		}
		else return false;
	}
	static public Boolean Delete(Transaction transaction)
	{		
		return transList.remove(transaction);
		
	}
	static public Transaction Searchs(String key)
	{
		Transaction transacSearch=null;
		
		for(Transaction tsc : transList)
		{
			if(tsc.getclienNb().equals(key))
			{
				transacSearch=tsc;
			}
		}
		return transacSearch;
	}
	
	//Display
	public String Display() 
	{
		String info="";
		for(Transaction transaction : transList)
		{
			info=info + transaction.toString()+"\n--------------------------------------------\n";
		}
		return info;
	}
}
