//DATE DEC 12 2021
package bus;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class DataReaderSingleton {


	private static String filePathCust = "src//data//CustomerSerfile.ser" ;
	private static String filePathAcc = "src//data//AccountSerfile.ser" ;
	
	public static void writeCustomerToSerializedFile(HashMap<String, Customer>  collection) throws IOException
	{		 
		ArrayList<Customer> arrayList = new ArrayList <> (     ((HashMap<String, Customer>) collection).values()    );
		
		FileOutputStream fos = new FileOutputStream(filePathCust);		
		ObjectOutputStream oos = new ObjectOutputStream(fos);	
		
		  oos.writeObject(arrayList);		  
		  fos.close();		
	}	

	@SuppressWarnings("unchecked")
	public static HashMap<String, Customer>  readCustomersFromSerializedFile() throws IOException, ClassNotFoundException
	{
		HashMap<String, Customer> customerHashMap = new HashMap<String, Customer>();		
		ArrayList< Customer> customerArrayList    = new ArrayList< Customer>();
		
		  FileInputStream fis = new FileInputStream(filePathCust);		  
		  ObjectInputStream ois = new ObjectInputStream(fis);		  
		  
		  customerArrayList  = (ArrayList<Customer>) ois.readObject();
		  
		  for(Customer aCust :  customerArrayList)  {
			  
			  customerHashMap.put(aCust.getcId(), aCust);
		  }		  
		  fis.close();			  
		  return customerHashMap;	
	} 	

	public static void writeAccountToSerializedFile(HashMap<String, Account>  collection) throws IOException
	{		 
		ArrayList<Account> arrayList = new ArrayList <> (     ((HashMap<String, Account>) collection).values()    );
		
		FileOutputStream fos = new FileOutputStream(filePathAcc);		
		ObjectOutputStream oos = new ObjectOutputStream(fos);	
		
		  oos.writeObject(arrayList);		  
		  fos.close();		
	}	

	@SuppressWarnings("unchecked")
	public static HashMap<String, Account>  readAccountFromSerializedFile() throws IOException, ClassNotFoundException
	{
		HashMap<String, Account> accountHashMap = new HashMap<String, Account>();		
		ArrayList< Account> accountArrayList    = new ArrayList<Account>();
		
		  FileInputStream fis = new FileInputStream(filePathAcc);		  
		  ObjectInputStream ois = new ObjectInputStream(fis);		  
		  
		  accountArrayList  = (ArrayList<Account>) ois.readObject();
		  
		  for(Account aAcc :  accountArrayList)  {
			  
			  accountHashMap.put(aAcc.getaClientNb(), aAcc);
		  }		  
		  fis.close();			  
		  return accountHashMap;	
	} 	
}
