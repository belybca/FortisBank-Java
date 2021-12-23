/*
Group
Rameswariben Bhoi
You Pan
Albelis Becea
Description: Fortis Bank application
Date: Nov 21 2021


*/
package bus;

public class RaiseException extends Exception{

	private static final long serialVersionUID = 1L;
	private static String message = "RAISE EXCEPTION : Invalid input";
	
	public RaiseException() {
		super(message);
	}
	public RaiseException(String msg) {
		super(msg);
	}
}
