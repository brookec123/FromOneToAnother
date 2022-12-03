/*Name: Brooke Cronin
 * Teacher: Mrs. McCaffery
 * Course: ICS 3UI
 * 
 * Description: This program allows other programs to use these methods to make checking if an 
 * input is an integer, check if an integer input is within the given range, and terminating programs with integer 
 * and string inputs easier and more efficiently.
 */




package finalProject;

import java.util.*;

public class ErrorChecks 
{

	public static void main(String[] args) 
	{
	}//end main method


	/*
	 * Method Name: checkIfInt
	 * Description: Checks if an input is an integer, if it is then, it will return true, if not, then false will be returned.
	 * Method Author: Brooke Cronin
	 * Parameters: String variable (the variable the needs to be checked if it is an integer)
	 * Returns: boolean isInt
	 */

	public static boolean checkIfInt(String variable) 
	{
		//try catch to see if the specified variable is an integer
		try
		{
			Integer.parseInt(variable);
		}
		catch (NumberFormatException e)
		{
			return false;
		}//end try catch
		
		return true;
		
	}//end checkIfInt method


	/*
	 * Method Name: checkIfDouble
	 * Description: Checks if an input is a double, if it is then, it will return true, if not, then false will be returned.
	 * Method Author: Brooke Cronin
	 * Parameters: String variable (the variable the needs to be checked if it is a double)
	 * Returns: boolean isDouble
	 */

	public static boolean checkIfDouble(String variable) 
	{
		//try catch to see if the specified variable is a double
		try
		{
			Double.parseDouble(variable);
		}
		catch (NumberFormatException e)
		{
			return false;
		}//end try catch
		
		return true;
		
	}//end checkIfDouble method
	
	
	/*
	 * Method Name: checkIfChar
	 * Description: Checks if an input is a character, if it is then, it will return true, if not, then false will be returned.
	 * Method Author: Brooke Cronin
	 * Parameters: String variable (the variable the needs to be checked if it is a character)
	 * Returns: boolean isChar
	 */

	public static boolean checkIfChar(String variable) 
	{
		//when the string variable contains only one character
		if(variable.length()==1)
		{
			return true;
		}//end if
		return false;
	}//end checkIfChar method

}//end class
