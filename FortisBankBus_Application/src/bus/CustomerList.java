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
import java.util.Collections;

public  class CustomerList{

private  static ArrayList<Customer> listOfCustomer = new ArrayList<Customer> ();
	

	public  ArrayList<Customer> getCustList() {
		return listOfCustomer;
	}

	public Boolean Delete(Customer customer)
	{		
		return listOfCustomer.remove(customer);
		
	}
	public boolean Add(Customer cust) {
		if(listOfCustomer.contains(cust)==false)
		{
			listOfCustomer.add(cust);
			return true;
		}
		else return false;
	}
	public void update(Customer customer,String number) throws RaiseException
	{
		for(Customer cust :listOfCustomer)
		{
			if(cust.getcId().compareTo(number) == 0)
				try {
					cust.setcName(customer.getcName());
					cust.setcPin(customer.getcPin());
					cust.setcStatus(customer.getcStatus());
						
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	public Customer Searchs(String key)
	{
		Customer cusSearch=null;
		
		for(Customer cus : listOfCustomer)
		{
			if(cus.getcId().compareTo(key)==0)
			{
				cusSearch=cus;
			}
		}
		return cusSearch;
	}
	
	public String Display() 
	{
		String info="";
		for(Customer customer : listOfCustomer)
		{
			info=info + customer.toString()+"\n--------------------------------------------\n";
		}
		return info;
	}
	public static void sortByCustomerName() {
		
		Collections.sort(listOfCustomer, new CustomerNameComparator());
		
	}
	public static void sortByCustomerId() {
		
		Collections.sort(listOfCustomer, new CustomerIDComparator());
	}

}
