/*
Group
Rameswariben Bhoi
You Pan
Albelis Becea
Description: Fortis Bank application
Date: Nov 21 2021


*/
package bus;




import java.text.ParseException;
import java.time.LocalDate;





public abstract class Account  implements java.io.Serializable
{

	//Declaration of private variables
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String aNumber;
	private String aType;
	private	LocalDate aOpenedDate;
	private String aStatus;
	private Double aBalance;
	private String aClientNb;
	private TransactionList transactions;
	
	//Declaration of default and parameter constructors
	//Assigning the default values
	public Account() {}
	public Account(String aNumber, String aType, LocalDate aOpenedDate, String aStatus, Double aBalance, String aClientNb) 
	{
		this.setaNumber(aNumber);
		this.setaType(aType);
		this.setaOpenedDate(aOpenedDate);
		this.setaStatus(aStatus);
		this.aBalance=aBalance;
		this.setaClientNb(aClientNb);
	
		
	}	
	public Account(String aNumber,String aStatus,String aClientNb) 
	{
		this.setaNumber(aNumber);
		this.setaType("Undefined");
		this.setaOpenedDate(LocalDate.now());
		this.setaStatus(aStatus);
		this.aBalance=(double)0;
		this.setaClientNb(aClientNb);
	}	
	
	public Account(String aNumber, LocalDate aOpenedDate, String aStatus, Double aBalance,String aClientNb) {
		super();
		this.setaNumber(aNumber);
		this.setaType("Undefined");
		this.setaOpenedDate(LocalDate.now());
		this.setaStatus(aStatus);
		this.aBalance=aBalance;
		this.setaClientNb(aClientNb);
		
		
	}
	
	public Account(String aNumber, int day,int month, int year, String aStatus, Double aBalance,String aClientNb) throws ParseException {
		super();
		this.setaNumber(aNumber);
		this.setaType("Undefined");
		this.setaOpenedDate(LocalDate.of(year, month, day));
		this.setaStatus(aStatus);
		this.aBalance=aBalance;
		this.setaClientNb(aClientNb);
	
	}
	// getters and setters methods
	

	public String getaNumber() {
		return aNumber;
	}

	public void setaNumber(String aNumber) {
		this.aNumber = aNumber;
	}

	public String getaType() {
		return aType;
	}

	public void setaType(String aType) {
		this.aType = aType;
	}

	public LocalDate getaOpenedDate() {
		return aOpenedDate;
	}

	public void setaOpenedDate(LocalDate aOpenedDate) {
		this.aOpenedDate = aOpenedDate;
	}

	public String getaStatus() {
		return aStatus;
	}

	public void setaStatus(String aStatus) {
		this.aStatus = aStatus;
	}

	public Double getaBalance() {
		return aBalance;
	}
	// For the balance, the only way for the user to change the balance will be through depositing or withdrawing money
    // For this reason we excluded the set because the user will not have the opportunity to WRITE in this property, only to READ (get)
	public String getaClientNb() {
		return aClientNb;
	}

	public void setaClientNb(String aClientNb) {
		this.aClientNb = aClientNb;
	}
	public TransactionList getTransactions() {
		return transactions;
	}
	public void setTransactions(TransactionList transactions) {
		this.transactions = transactions;
	}
	
	@Override
	public String toString() {
		return "Account [aNumber=" + aNumber + ", aType=" + aType + ", aOpenedDate=" + aOpenedDate + ", aStatus="
				+ aStatus + ", aBalance=" + aBalance + ", aClientNb=" + aClientNb + "]";
	}

	public void Open(String Number, String Type, String aClientNb)
	{
		 this.aNumber = Number; 
         this.aType = Type;     
         this.aBalance = (double)0;         
         this.aStatus = "active";
         this.aClientNb=aClientNb;
	}
	
	

	public   Boolean Deposit(Double amount)
    {
        if (amount >= 2 && amount <= 20000)
        {
            aBalance = aBalance + amount; 
            return true;
        }
        else { return false; }
    }
	public  int Withdraw(Double amount)
    {
        if (amount < 20) { return 2; }
        else if (amount > 500) { return 1; }
        else if ((amount % 20) != 0) { return -1; }
        else if (amount > aBalance) { return -2; }
        else
        {
            aBalance = aBalance - amount;
            return 0;
        }
    }
	
	
}
