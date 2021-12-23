/*
Group
Rameswariben Bhoi
You Pan
Albelis Becea
Description: Fortis Bank application
Date: Nov 21 2021


*/
package bus;

public class InputMismatchException extends Exception {

	private static final long serialVersionUID = 1L;
	private static String message = "INPUT MISMATCH EXCEPTION : Invalid input";
	
	public InputMismatchException() {
		super(message);
	}
	public InputMismatchException(String msg) {
		super(msg);
	}
}
