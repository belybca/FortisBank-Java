//DATE DEC 12 2021
package bus;

import java.util.ArrayList;
import java.util.HashMap;

public class SingletonBank {

	private static SingletonBank  singleInstance = null;

	private HashMap<Customer, ArrayList<Transaction>> listOfTransactionsPerCustomer = null  ;
	private HashMap<String,Transaction>listOfTransactions = null;
	private HashMap<String,Account>listOfAcounts = null;
	private HashMap<String,Customer>listOfCustomers= null;
	
	private SingletonBank()
	 {
		listOfTransactionsPerCustomer = new HashMap<Customer, ArrayList<Transaction>>();
		listOfTransactions = new HashMap<String,Transaction>();
		listOfAcounts = new HashMap<String,Account>();
		listOfCustomers = new HashMap<String,Customer>();
	 }
	
	public static SingletonBank getSingleInstance() {
		 if(singleInstance == null)
		 {
			 singleInstance = new SingletonBank();
		 }		 
		return singleInstance;
	}	
	
	
			//List of Checking Accounts
			public void addCheckingAcc(CheckingAccount aCheckingAccount)	{		
					
				   singleInstance.listOfAcounts.put(aCheckingAccount.getaClientNb(), aCheckingAccount);	
				   
				}	
			
			
		   public void addCheckingAcc(String customerID, CheckingAccount aCheckingAccount)	{		
				
			   singleInstance.listOfAcounts.put(customerID, aCheckingAccount);	
			   
			}	
		   
		   public void addCheckingAcc(ArrayList<CheckingAccount> CheckingAccounts){
		    	
		        for(CheckingAccount aCheckingAccount : CheckingAccounts) {
		        	
		            singleInstance.listOfAcounts.put(aCheckingAccount.getaClientNb(), aCheckingAccount);
		        }
		    }

		    public void clearCheckingAccList(){
		    	singleInstance.listOfAcounts.clear();
		    }
		    
			public  void remove(CheckingAccount newCheckingAccount)	{
				
				singleInstance.listOfAcounts.remove(newCheckingAccount.getaClientNb());	
			}


			 public Account searchCheckingAccList(String  key){
				 CheckingAccount element = null;
			        if (singleInstance.listOfAcounts.containsKey(key)){
			        	
			          element = (CheckingAccount) singleInstance.listOfAcounts.get(key);
			        }       
			        
			        return element;
			    }
			 
			 public ArrayList<Account> searchCheckingAccList2(String  key){
				 ArrayList<Account> CheckingAccountsList = new ArrayList<>();
			        if (singleInstance.listOfAcounts.containsKey(key)){
			        	
			          CheckingAccountsList.add(singleInstance.listOfAcounts.get(key));
			        }       
			        
			        return CheckingAccountsList;
			    }
	
	//List of Saving Accounts
		   
		   
			 public void addSavingAcc(SavingAccount aSavingAccount)	{		
					
				   singleInstance.listOfAcounts.put(aSavingAccount.getaClientNb(), aSavingAccount);	
				   
				}	
			
			
		   public void addSavingAcc(String customerID, SavingAccount aSavingAccount)	{		
				
			   singleInstance.listOfAcounts.put(customerID, aSavingAccount);	
			   
			}	
		   
		   public void addSavingAcc(ArrayList<SavingAccount> SavingAccounts){
		    	
		        for(SavingAccount aSavingAccount : SavingAccounts) {
		        	
		            singleInstance.listOfAcounts.put(aSavingAccount.getaClientNb(), aSavingAccount);
		        }
		    }

		    public void clearSavingAccList(){
		    	singleInstance.listOfAcounts.clear();
		    }
		    
			public  void remove(SavingAccount newSavingAccount)	{
				
				singleInstance.listOfAcounts.remove(newSavingAccount.getaClientNb());	
			}


			 public Account searchSavingAccList(String  key){
				 SavingAccount element = null;
			        if (singleInstance.listOfAcounts.containsKey(key)){
			        	
			          element = (SavingAccount) singleInstance.listOfAcounts.get(key);
			        }       
			        
			        return element;
			    }
			 
			 public ArrayList<Account> searchSavingAccList2(String  key){
				 ArrayList<Account> SavingAccountsList = new ArrayList<>();
			        if (singleInstance.listOfAcounts.containsKey(key)){
			        	
			          SavingAccountsList.add(singleInstance.listOfAcounts.get(key));
			        }       
			        
			        return SavingAccountsList;
			    }
			      
		   
	//List of Credit


				public void addCreditAcc(CreditAccount aCreditAccount)	{		
						
					   singleInstance.listOfAcounts.put(aCreditAccount.getaClientNb(), aCreditAccount);	
					   
					}	
				
				
			   public void addCreditAcc(String customerID, CreditAccount aCreditAccount)	{		
					
				   singleInstance.listOfAcounts.put(customerID, aCreditAccount);	
				   
				}	
			   
			   public void addCreditAcc(ArrayList<CreditAccount> CreditAccounts){
			    	
			        for(CreditAccount aCreditAccount : CreditAccounts) {
			        	
			            singleInstance.listOfAcounts.put(aCreditAccount.getaClientNb(), aCreditAccount);
			        }
			    }

			    public void clearCreditAccList(){
			    	singleInstance.listOfAcounts.clear();
			    }
			    
				public  void remove(CreditAccount newCreditAccount)	{
					
					singleInstance.listOfAcounts.remove(newCreditAccount.getaClientNb());	
				}


				 public Account searchCreditAccList(String  key){
					 CreditAccount element = null;
				        if (singleInstance.listOfAcounts.containsKey(key)){
				        	
				          element = (CreditAccount) singleInstance.listOfAcounts.get(key);
				        }       
				        
				        return element;
				    }
				 
				 public ArrayList<Account> searchCreditAccList2(String  key){
					 ArrayList<Account> CreditAccountsList = new ArrayList<>();
				        if (singleInstance.listOfAcounts.containsKey(key)){
				        	
				          CreditAccountsList.add(singleInstance.listOfAcounts.get(key));
				        }       
				        
				        return CreditAccountsList;
				    }
				 
	//Customer
				 
				 public void setlistOfCustomers(HashMap<String,Customer> listOfCustomers) {
					  this.listOfCustomers = listOfCustomers;
					}
					public HashMap<String,Customer>  getlistOfCustomers() {
						return singleInstance.listOfCustomers;
					}	
				   public  void addCustomer(String customerID, Customer aCustomer)	{		
						
					   singleInstance.listOfCustomers.put(customerID, aCustomer);	
					   
					}	
				   public void addCustomer(ArrayList<Customer> Customers, String custNumber){
				    	
				        for(Customer aCustomer : Customers) {
				        	
				            singleInstance. listOfCustomers.put(custNumber, aCustomer);
				        }
				    }

				    public void clearCustomerList(){
				    	singleInstance. listOfCustomers.clear();
				    }
				    
					public boolean remove(Customer newCustomer)	{
						
						singleInstance. listOfCustomers.remove(newCustomer.getcId());	
						return true;
					}


					 public Customer searchCustomerList(String  key){
						 Customer element = null;
					        if (singleInstance. listOfCustomers.containsKey(key)){
					        	
					          element = singleInstance. listOfCustomers.get(key);
					        }       
					        
					        return element;
					    }
					 
					 public ArrayList<Customer> searchCustomerList2(String  key){
						 ArrayList<Customer> CustomersList = new ArrayList<>();
					        if (singleInstance. listOfCustomers.containsKey(key)){
					        	
					          CustomersList.add(singleInstance. listOfCustomers.get(key));
					        }       
					        
					        return CustomersList;
					    }
					 
					 public void setlistOfTransactionsPerCustomer(HashMap<Customer, ArrayList<Transaction>> listOfTransactionsPerCustomer) {
						  this.listOfTransactionsPerCustomer = listOfTransactionsPerCustomer;
						}
						public HashMap<Customer, ArrayList<Transaction>>  getlistOfTransactionsPerCustomer() {
							return singleInstance.listOfTransactionsPerCustomer;
						}	
					   public  void addTransactionsPerCustomer(Customer newCustomer, ArrayList<Transaction> ListOfTransaction)	{		
							
						   singleInstance.listOfTransactionsPerCustomer.put(newCustomer,ListOfTransaction);	
						   
						}	
					   
					   //List of Transactions
						
					   public void setlistOfTransactions(HashMap<String,Transaction> listOfTransactions) {
							  this.listOfTransactions = listOfTransactions;
							}
							public HashMap<String,Transaction>  getlistOfTransactions() {
								return singleInstance.listOfTransactions;
							}	
						   public  void addTransaction(String customerID, Transaction aTransaction)	{		
								
							   singleInstance.listOfTransactions.put(customerID, aTransaction);	
							   
							}
						   
						   
						   public void addTransactions(String custNumber,ArrayList<Transaction> transactions){
						    	
						        for(Transaction aTransaction : transactions) {
						        	
						            singleInstance.listOfTransactions.put(custNumber, aTransaction);
						        }
						    }

							
						    public void clearTransactionList(){
						    	singleInstance.listOfTransactions.clear();
						    }
						    
							public  void remove(Transaction newTransaction)	{
								
								singleInstance.listOfTransactions.remove(newTransaction.getclienNb());	
							}


							 public Transaction searchTransactionList(String  key){
								 Transaction element = null;
							        if (singleInstance.listOfTransactions.containsKey(key)){
							        	
							          element = singleInstance.listOfTransactions.get(key);
							        }       
							        
							        return element;
							    }
							 
							 public ArrayList<Transaction> searchTransactionList2(String  key){
								 ArrayList<Transaction> transactionsList = new ArrayList<>();
							        if (singleInstance.listOfTransactions.containsKey(key)){
							        	
							          transactionsList.add(singleInstance.listOfTransactions.get(key));
							        }       
							        
							        return transactionsList;
							    }
						   
						   
						   
						
						
						   public void setlistOfAcounts(HashMap<String,Account> listOfAcounts) {
								  this.listOfAcounts = listOfAcounts;
								}
								public HashMap<String,Account>  getlistOfAcounts() {
									return singleInstance.listOfAcounts;
								}	
								
		
	
}
