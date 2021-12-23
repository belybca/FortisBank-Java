/*
Group
Rameswariben Bhoi
You Pan
Albelis Becea
Description: Fortis Bank application
Date: Nov 21 2021


*/
package bus;

import java.util.Comparator;



public class CustomerIDComparator implements Comparator<Customer>{
	
	public int compare(Customer c1, Customer c2) {
		if(c1.getcId().compareTo(c2.getcId()) < 0) return -1;
		else if (c1.getcId().compareTo(c2.getcId()) >0) return 1;
			else return 0;
	}
}
