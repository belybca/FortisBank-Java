/*
Group
Rameswariben Bhoi
You Pan
Albelis Becea
Description: Fortis Bank application
Date: Nov 21 2021


*/
package bus;

public class DataValidator {
	
	public static int IntValidator(int value) throws  InputMismatchException, RaiseException
	{
		
		if( value <= 0)
		{			
			throw new RaiseException("\"invalid input - data must be positive integer\"");
			
		}
		else if(Character.isAlphabetic(value))
		{
			throw new InputMismatchException("\"\"invalid input- value must be only digit\"\"");
		}
		else 
			return value;
	}
	public static double DoubleValidator(double value) throws  InputMismatchException, RaiseException
	{
		
		if( value <= 0)
		{			
			throw new RaiseException("\"invalid input - data must be positive Double Number\"");
			
		}
		else 
			return value;
	}
	public static void isAlphabetic(String value) throws RaiseException
	{
		for(int i = 0 ; i != value.length() ; i++)
		{
			
			if(  !Character.isAlphabetic(value.charAt(i)))
			{
				throw new RaiseException("invalid input- value must be only alphabet letter");	
				
			}		
		}		
	}
	public static void isNumeric(String value) throws RaiseException
	{
		for(int i = 0 ; i != value.length() ; i++)
		{
			
			if(  Character.isAlphabetic(value.charAt(i)))
			{
				throw new RaiseException("invalid input- value must be only Numeric");	
				
			}		
		}		
	}
	public static void isNULL(String value) throws RaiseException
	{
		if(value == "" || value == null) {
			throw new RaiseException("invalid input- value should not be null or empty");
		}
		
	}

}
