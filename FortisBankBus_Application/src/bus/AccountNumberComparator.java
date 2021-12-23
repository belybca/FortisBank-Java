/*
Group
Rameswariben Bhoi
You Pan
Albelis Becea
Description: Fortis Bank application
Date: Dec 12 2021


*/
package bus;

import java.util.Comparator;



public class AccountNumberComparator implements Comparator<Account>{
	
	public int compare(Account ac1, Account ac2) {
		if(ac1.getaNumber().compareTo(ac2.getaNumber()) < 0) return -1;
		else if (ac1.getaNumber().compareTo(ac2.getaNumber()) >0) return 1;
			else return 0;
	}

	
}
