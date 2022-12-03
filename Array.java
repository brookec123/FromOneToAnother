/* Name: Brooke Cronin
 * Teacher: Mrs. McCaffery
 * Course: ICS 4U
 *
 * Description: The Array class contains static methods for basic operations on and with arrays.
 * The ability to quickly access methods such as print will improve the readability of your code and allow for easier testing of programs.
 * It has no permanent fields but can create and manipulate the contents of arrays.
 */

package finalProject;

import java.io.*;
import java.util.*;

public class Array
{
	
	
	/* Method Author: Mrs. McCaffery
	 * Method Name: printArrayToFile
	 * Description: Prints each element in an array to a file on a single line, separated by commas
	 * Parameters: PrintWriter, int []
	 * Returns: N/A
	 */

	public static void printArrayToFile(PrintWriter myOutput, int [] array)
	{	//prints first line
		myOutput.print(array[0]);
		// prints the rest of the elements separated by commas and spaces
		for (int i = 1; i<=array.length-1; i++)
		{
			myOutput.print(", "+ array[i]);
		}
	}// ends printArrayToFile method


	/* Method Author: Mrs. McCaffery
	 * Method Name: printArrayToFile
	 * Description: Prints each element in an array to a file on a single line, separated by commas
	 * Parameters: PrintWriter, String []
	 * Returns: N/A
	 */

	public static void printArrayToFile(PrintWriter myOutput, String [] array) 
	{	//prints first line
		myOutput.print(array[0]);
		// prints the rest of the elements separated by commas and spaces
		for (int i = 1; i<=array.length-1; i++)
		{
			myOutput.print(", "+ array[i]);
		}
	}// ends printArrayToFile method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: createEmptyIntArray
	 * Description: Returns empty array of size indicated.
	 * Parameters: int size
	 * Returns: int [] newArray
	 * Throws: NegativeArraySizeException when the size parameter is a negative integer
	 */

	public static int[] createEmptyIntArray(int size) 
	{
		//creating a new integer array with indicated size
		int[] newArray=new int[size];

		//returning the new empty array of indicated size
		return newArray;

	}//end createEmptyIntArray method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: createEmptyStringArray
	 * Description: Returns empty array of size indicated.
	 * Parameters: int size
	 * Returns: String [] newArray
	 * Throws: NegativeArraySizeException when the size parameter is a negative integer
	 */

	public static String[] createEmptyStringArray(int size) 
	{
		//creating a new string array with indicated size
		String[] newArray=new String[size];

		//returning the new empty array of indicated size
		return newArray;

	}//end createEmptyStringArray method
	
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: createEmptyDoubleArray
	 * Description: Returns empty array of size indicated.
	 * Parameters: int size
	 * Returns: double [] newArray
	 * Throws: NegativeArraySizeException when the size parameter is a negative integer
	 */

	public static double[] createEmptyDoubleArray(int size) 
	{
		//creating a new double array with indicated size
		double[] newArray=new double[size];

		//returning the new empty array of indicated size
		return newArray;

	}//end createEmptyDoubleArray method
	
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: createEmptyCharArray
	 * Description: Returns empty array of size indicated.
	 * Parameters: int size
	 * Returns: char [] newArray
	 * Throws: NegativeArraySizeException when the size parameter is a negative integer
	 */

	public static char[] createEmptyCharArray(int size) 
	{
		//creating a new character array with indicated size
		char[] newArray=new char[size];

		//returning the new empty array of indicated size
		return newArray;

	}//end createEmptyCharArray method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: createRandomIntArray
	 * Description: Returns array of size indicated filled with random integers between min and max.
	 * Parameters: int size, int min, int max
	 * Returns: int [] newArray
	 * Throws: IllegalArgumentException when the minimum parameter is larger than the maximum parameter, NegativeArraySizeException when the size parameter is a negative integer
	 */

	public static int[] createRandomIntArray(int size, int min, int max) 
	{
		Random generator=new Random();

		//creating a new integer array with indicated size
		int[] newArray=new int[size];

		//a for loop to generate random integer values for each index in the new array
		for (int j=0; j<=newArray.length-1; j++)
		{
			newArray[j]=generator.nextInt(max-min)+min;
		}//end for loop to generate random integer values for each index in the new array

		//returning the new array of indicated size with random integer values
		return newArray;

	}//end createRandomIntArray method
	
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: createRandomDoubleArray
	 * Description: Returns array of size indicated filled with random doubles between min and max.
	 * Parameters: int size, int min, int max
	 * Returns: double [] newArray
	 * Throws: IllegalArgumentException when the minimum parameter is larger than the maximum parameter, NegativeArraySizeException when the size parameter is a negative integer
	 */

	public static double[] createRandomDoubleArray(int size, int min, int max) 
	{
		//creating a new double array with indicated size
		double[] newArray=new double[size];

		//a for loop to generate random double values for each index in the new array
		for (int j=0; j<=newArray.length-1; j++)
		{
			newArray[j]=Math.random()*(max-min+1)+min;
		}//end for loop to generate random double values for each index in the new array

		//returning the new array of indicated size with random double values
		return newArray;

	}//end createRandomDoubleArray method
	
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: createUserDefinedArray
	 * Description: Returns array of size indicated filled with information entered by the user.
	 * Parameters: int size
	 * Returns: int [] newArray
	 * Throws: NegativeArraySizeException when the size parameter is a negative integer
	 */

	public static int[] createUserDefinedArray(int size) 
	{

		Scanner myInput=new Scanner(System.in);

		//creating a new integer array with indicated size
		int[] newArray=new int[size];

		//a for loop to ask user to enter a value for each index individually in the array
		for (int j=0; j<=newArray.length-1; j++)
		{
			//asking user to input the value of the current index number
			System.out.println("Please enter an interger for index # "+j+":");
			newArray[j]=myInput.nextInt();

		}//end for loop to ask user to enter a value for each index individually in the array

		myInput.close();

		//returning the new array of indicated size with user inputed integer values
		return newArray;

	}//end createUserDefinedArray method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: createUserDefinedStringArray
	 * Description: Returns array of size indicated filled with information entered by the user.
	 * Parameters: int size
	 * Returns: String [] newArray
	 * Throws: NegativeArraySizeException when the size parameter is a negative integer
	 */

	public static String[] createUserDefinedStringArray(int size) 
	{

		Scanner myInput=new Scanner(System.in);

		//creating a new string array with indicated size
		String[] newArray=new String[size];

		//a for loop to ask user to enter a value for each index individually in the array
		for (int j=0; j<=newArray.length-1; j++)
		{
			//asking user to input the value of the current index number
			System.out.println("Please enter a string for index # "+j+":");
			newArray[j]=myInput.next();

		}//end for loop to ask user to enter a value for each index individually in the array

		myInput.close();

		//returning the new array of indicated size with user inputed string values
		return newArray;

	}//end createUserDefinedStringArray method
	
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: createUserDefinedDoubleArray
	 * Description: Returns array of size indicated filled with information entered by the user.
	 * Parameters: int size
	 * Returns: double [] newArray
	 * Throws: NegativeArraySizeException when the size parameter is a negative integer
	 */

	public static double[] createUserDefinedDoubleArray(int size) 
	{

		Scanner myInput=new Scanner(System.in);

		//creating a new double array with indicated size
		double[] newArray=new double[size];

		//a for loop to ask user to enter a value for each index individually in the array
		for (int j=0; j<=newArray.length-1; j++)
		{
			//asking user to input the value of the current index number
			System.out.println("Please enter a number for index # "+j+":");
			newArray[j]=myInput.nextDouble();

		}//end for loop to ask user to enter a value for each index individually in the array

		myInput.close();

		//returning the new array of indicated size with user inputed double values
		return newArray;

	}//end createUserDefinedDoubleArray method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: createUserDefinedCharArray
	 * Description: Returns array of size indicated filled with information entered by the user.
	 * Parameters: int size
	 * Returns: char [] newArray
	 * Throws: NegativeArraySizeException when the size parameter is a negative integer
	 */

	public static char[] createUserDefinedCharArray(int size) 
	{

		Scanner myInput=new Scanner(System.in);

		//creating a new character array with indicated size
		char[] newArray=new char[size];

		//a for loop to ask user to enter a value for each index individually in the array
		for (int j=0; j<=newArray.length-1; j++)
		{
			//asking user to input the value of the current index number
			System.out.println("Please enter a character for index # "+j+":");
			newArray[j]=myInput.next().charAt(0);

		}//end for loop to ask user to enter a value for each index individually in the array

		myInput.close();

		//returning the new array of indicated size with user inputed character values
		return newArray;

	}//end createUserDefinedCharArray method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: print
	 * Description: Prints each element in the array to the screen one element per line.
	 * Parameters: int varargs array
	 * Returns: N/A
	 */

	public static void print(int ... array) 
	{
		//a for loop to go through each index of the array and print the value to screen
		for (int item: array)
		{
			//printing value of current index to screen
			System.out.println(item);

		}//end for loop to go through each index of the array and print the value to screen

	}//end print method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: print
	 * Description: Prints each element in the array to the screen one element per line.
	 * Parameters: String varargs array
	 * Returns: N/A
	 */

	public static void print(String ... array) 
	{
		//a for loop to go through each index of the array and print the value to screen
		for (String item: array)
		{
			//printing value of current index to screen
			System.out.println(item);

		}//end for loop to go through each index of the array and print the value to screen

	}//end print method
	
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: print
	 * Description: Prints each element in the array to the screen one element per line.
	 * Parameters: double varargs array
	 * Returns: N/A
	 */

	public static void print(double ... array) 
	{
		//a for loop to go through each index of the array and print the value to screen
		for (double item: array)
		{
			//printing value of current index to screen
			System.out.println(item);

		}//end for loop to go through each index of the array and print the value to screen

	}//end print method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: print
	 * Description: Prints each element in the array to the screen one element per line.
	 * Parameters: char varargs array
	 * Returns: N/A
	 */

	public static void print(char ... array) 
	{
		//a for loop to go through each index of the array and print the value to screen
		for (char item: array)
		{
			//printing value of current index to screen
			System.out.println(item);

		}//end for loop to go through each index of the array and print the value to screen

	}//end print method
	

	/*
	 * Method Author: Brooke Cronin
	 * Method Name: findMax
	 * Description: Returns the position of the maximum value in the array.
	 * Parameters: int [] array
	 * Returns: int position
	 */

	public static int findMax(int [] array)
	{
		//initializing variables to be used in this method
		int position=0;
		int currentMax=array[0];

		//a for loop to check the values of each index of the array 
		for (int j=0; j<=array.length-1; j++)
		{
			//an if statement to determine whether the value of the current index is larger than the previous largest index/assigning new values to currentMax and position if it is
			if (array[j]>currentMax)
			{
				currentMax=array[j];
				position=j;

			}//end if statement to determine whether the value of the current index is larger than the previous largest index/assigning new values to currentMax and position if it is

		}//end for loop to check the values of each index of the array 

		//returning the index of the maximum value in the array
		return position;

	}//end findMax method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: findMax
	 * Description: Returns the position of the value where the first letter(s) is last to occur alphabetically. Only checks the first four letters of each word.
	 * Parameters: String [] array
	 * Returns: int position
	 */

	public static int findMax(String [] array)
	{
		//initializing variables to be used in this method
		int position=0;
		char [] firstLetter = new char [array.length];
		char [] secondLetter = new char [array.length];
		char [] thirdLetter = new char [array.length];
		char [] fourthLetter = new char [array.length];

		//a for loop to set the first character of each index in the array to the corresponding index in a character array
		for (int i=0; i<array.length; i++)
		{
			firstLetter[i]=array[i].charAt(0);

		}//end for loop to set the first character of each index in the array to the corresponding index in a character array

		char currentMax=firstLetter[0];

		//a for loop to check the values of each index of the array 
		for (int j=1; j<=array.length-1; j++)
		{
			//an if statement when the first character of current index/value is greater than the old largest string
			if(firstLetter[j]>currentMax)
			{
				currentMax=firstLetter[j];
				position=j;
			}//end if statement when the first character of current index/value is greater than the old largest string

			//an else if statement when the first character in both strings are equal
			else if(firstLetter[j]==currentMax)
			{
				secondLetter[j]=array[j].charAt(1);
				secondLetter[position]=array[position].charAt(1);

				//an if statement when the second character of current index/value is greater than the old largest string's second character
				if(secondLetter[j]>secondLetter[position])
				{
					currentMax=firstLetter[j];
					position=j;
				}//end if statement when the second character of current index/value is greater than the old largest string's second character

				//an else if statement when the second character in both strings are equal
				else if(secondLetter[j]==secondLetter[position])
				{
					thirdLetter[j]=array[j].charAt(2);
					thirdLetter[position]=array[position].charAt(2);

					//an if statement when the third character of current index/value is greater than the old largest string's third character
					if(thirdLetter[j]>thirdLetter[position])
					{
						currentMax=firstLetter[j];
						position=j;
					}//end if statement when the third character of current index/value is greater than the old largest string's third character

					//an else if statement when the third character in both strings are equal
					else if(thirdLetter[j]==thirdLetter[position])
					{
						fourthLetter[j]=array[j].charAt(3);
						fourthLetter[position]=array[position].charAt(3);

						//an if statement when the fourth character of current index/value is greater than the old largest string's fourth character
						if(fourthLetter[j]>fourthLetter[position])
						{
							currentMax=firstLetter[j];
							position=j;
						}//end if statement when the fourth character of current index/value is greater than the old largest string's fourth character
					}//end else if statement when the third character in both strings are equal
				}//end else if statement when the second character in both strings are equal
			}//end else if statement when the first character in both strings are equal

		}//end for loop to check the values of each index of the array 

		//returning the index of the maximum value in the array
		return position;

	}//end findMax method
	
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: findMax
	 * Description: Returns the position of the maximum value in the array.
	 * Parameters: double [] array
	 * Returns: int position
	 */

	public static int findMax(double [] array)
	{
		//initializing variables to be used in this method
		int position=0;
		double currentMax=array[0];

		//a for loop to check the values of each index of the array 
		for (int j=0; j<=array.length-1; j++)
		{
			//an if statement to determine whether the value of the current index is larger than the previous largest index/assigning new values to currentMax and position if it is
			if (array[j]>currentMax)
			{
				currentMax=array[j];
				position=j;

			}//end if statement to determine whether the value of the current index is larger than the previous largest index/assigning new values to currentMax and position if it is

		}//end for loop to check the values of each index of the array 

		//returning the index of the maximum value in the array
		return position;

	}//end findMax method
	
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: findMax
	 * Description: Returns the position of the maximum value in the array.
	 * Parameters: char [] array
	 * Returns: int position
	 */

	public static int findMax(char [] array)
	{
		//initializing variables to be used in this method
		int position=0;
		char currentMax=array[0];

		//a for loop to check the values of each index of the array 
		for (int j=0; j<array.length; j++)
		{
			//an if statement to determine whether the value of the current index is larger than the previous largest index/assigning new values to currentMax and position if it is
			
			if (array[j]>currentMax)
			{
				currentMax=array[j];
				position=j;

			}//end if statement to determine whether the value of the current index is larger than the previous largest index/assigning new values to currentMax and position if it is

		}//end for loop to check the values of each index of the array 

		//returning the index of the maximum value in the array
		return position;

	}//end findMax method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: findMin
	 * Description: Returns the position of the minimum value in the array.
	 * Parameters: int [] array
	 * Returns: int position
	 */

	public static int findMin(int [] array)
	{
		int position=0;
		int currentMin=array[0];

		//a for loop to check the values of each index of the array 
		for (int j=0; j<=array.length-1; j++)
		{
			//an if statement to determine whether the value of the current index is smaller than the previous smallest index/assigning new values to currentMin and position if it is
			if (array[j]<currentMin)
			{
				currentMin=array[j];
				position=j;
			}//end if statement to determine whether the value of the current index is smaller than the previous smallest index/assigning new values to currentMin and position if it is

		}//end for loop to check the values of each index of the array 

		//returning the index of the minimum value in the array
		return position;

	}//end findMin method

	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: findMin
	 * Description: Returns the position of the value where the first letter(s) is first to occur alphabetically. Only checks the first four letters of each word.
	 * Parameters: String [] array
	 * Returns: int position
	 */

	public static int findMin(String [] array)
	{
		//initializing variables to be used in this method
		int position=0;
		char [] firstLetter = new char [array.length];
		char [] secondLetter = new char [array.length];
		char [] thirdLetter = new char [array.length];
		char [] fourthLetter = new char [array.length];

		//a for loop to set the first character of each index in the array to the corresponding index in a character array
		for (int i=0; i<array.length; i++)
		{
			firstLetter[i]=array[i].charAt(0);

		}//end for loop to set the first character of each index in the array to the corresponding index in a character array

		char currentMin=firstLetter[0];

		//a for loop to check the values of each index of the array 
		for (int j=1; j<=array.length-1; j++)
		{
			//an if statement when the first character of current index/value is less than the old largest string
			if(firstLetter[j]<currentMin)
			{
				currentMin=firstLetter[j];
				position=j;
			}//end if statement when the first character of current index/value is less than the old largest string

			//an else if statement when the first character in both strings are equal
			else if(firstLetter[j]==currentMin)
			{
				secondLetter[j]=array[j].charAt(1);
				secondLetter[position]=array[position].charAt(1);

				//an if statement when the second character of current index/value is less than the old largest string's second character
				if(secondLetter[j]<secondLetter[position])
				{
					currentMin=firstLetter[j];
					position=j;
				}//end if statement when the second character of current index/value is less than the old largest string's second character

				//an else if statement when the second character in both strings are equal
				else if(secondLetter[j]==secondLetter[position])
				{
					thirdLetter[j]=array[j].charAt(2);
					thirdLetter[position]=array[position].charAt(2);

					//an if statement when the third character of current index/value is less than the old largest string's third character
					if(thirdLetter[j]<thirdLetter[position])
					{
						currentMin=firstLetter[j];
						position=j;
					}//end if statement when the third character of current index/value is less than the old largest string's third character

					//an else if statement when the third character in both strings are equal
					else if(thirdLetter[j]==thirdLetter[position])
					{
						fourthLetter[j]=array[j].charAt(3);
						fourthLetter[position]=array[position].charAt(3);

						//an if statement when the fourth character of current index/value is less than the old largest string's fourth character
						if(fourthLetter[j]<fourthLetter[position])
						{
							currentMin=firstLetter[j];
							position=j;
						}//end if statement when the fourth character of current index/value is less than the old largest string's fourth character
					}//end else if statement when the third character in both strings are equal
				}//end else if statement when the second character in both strings are equal
			}//end else if statement when the first character in both strings are equal

		}//end for loop to check the values of each index of the array 

		//returning the index of the minimum value in the array
		return position;

	}//end findMin method
	
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: findMin
	 * Description: Returns the position of the minimum value in the array.
	 * Parameters: double [] array
	 * Returns: int position
	 */

	public static int findMin(double [] array)
	{
		int position=0;
		double currentMin=array[0];

		//a for loop to check the values of each index of the array 
		for (int j=0; j<=array.length-1; j++)
		{
			//an if statement to determine whether the value of the current index is smaller than the previous smallest index/assigning new values to currentMin and position if it is
			if (array[j]<currentMin)
			{
				currentMin=array[j];
				position=j;
			}//end if statement to determine whether the value of the current index is smaller than the previous smallest index/assigning new values to currentMin and position if it is

		}//end for loop to check the values of each index of the array 

		//returning the index of the minimum value in the array
		return position;

	}//end findMin method
	
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: findMin
	 * Description: Returns the position of the minimum value in the array.
	 * Parameters: char [] array
	 * Returns: int position
	 */

	public static int findMin(char [] array)
	{
		int position=0;
		char currentMin=array[0];

		//a for loop to check the values of each index of the array 
		for (int j=0; j<=array.length-1; j++)
		{
			//an if statement to determine whether the value of the current index is smaller than the previous smallest index/assigning new values to currentMin and position if it is
			if (array[j]<currentMin)
			{
				currentMin=array[j];
				position=j;
			}//end if statement to determine whether the value of the current index is smaller than the previous smallest index/assigning new values to currentMin and position if it is

		}//end for loop to check the values of each index of the array 

		//returning the index of the minimum value in the array
		return position;

	}//end findMin method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: swapElements
	 * Description: Swaps the values at index position1 with index position2.
	 * Parameters: int [] array, int position1, int position2
	 * Returns: N/A
	 */

	public static void swapElements(int [] array, int position1, int position2)
	{
		//creating variables to store original value of position1
		int position1Value=array[position1];

		//switching values of position 1 and 2 in array
		array[position1]=array[position2];
		array[position2]=position1Value;

	}//end swapElements method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: swapElements
	 * Description: Swaps the values at index position1 with index position2.
	 * Parameters: String [] array, int position1, int position2
	 * Returns: N/A
	 */

	public static void swapElements(String [] array, int position1, int position2)
	{
		//creating variables to store original value of position1
		String position1Value=array[position1];

		//switching values of position 1 and 2 in array
		array[position1]=array[position2];
		array[position2]=position1Value;

	}//end swapElements method
	
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: swapElements
	 * Description: Swaps the values at index position1 with index position2.
	 * Parameters: double [] array, int position1, int position2
	 * Returns: N/A
	 */

	public static void swapElements(double [] array, int position1, int position2)
	{
		//creating variables to store original value of position1
		double position1Value=array[position1];

		//switching values of position 1 and 2 in array
		array[position1]=array[position2];
		array[position2]=position1Value;

	}//end swapElements method
	
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: swapElements
	 * Description: Swaps the values at index position1 with index position2.
	 * Parameters: char [] array, int position1, int position2
	 * Returns: N/A
	 */

	public static void swapElements(char [] array, int position1, int position2)
	{
		//creating variables to store original value of position1
		char position1Value=array[position1];

		//switching values of position 1 and 2 in array
		array[position1]=array[position2];
		array[position2]=position1Value;

	}//end swapElements method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: findElement
	 * Description: Returns the position of the first instance of the indicated value. Returns -1 if the ‘value’ is not found.
	 * Parameters: int [] array, int value
	 * Returns: int position
	 */

	public static int findElement(int [] array, int value)
	{
		//a for loop to check each index for the specified value
		for (int j=0; j<=array.length-1; j++)
		{
			//an if statement to determine if the value of the current index is the same as the desired value
			if (array[j]==value)
			{
				//returning the position of the current index
				return j;

			}//end if statement to determine if the value of the current index is the same as the desired value

		}//end for loop to check each index for the specified value

		//the indicated value is not in the array, -1 will be returned
		return -1;

	}//end findElement method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: findElement
	 * Description: Returns the position of the first instance of the indicated value. Returns -1 if the ‘value’ is not found.
	 * Parameters: String [] array, int value
	 * Returns: int position
	 */

	public static int findElement(String [] array, String value)
	{
		//a for loop to check each index for the specified value
		for (int j=0; j<=array.length-1; j++)
		{
			//an if statement to determine if the value of the current index is the same as the desired value
			if (array[j].equals(value))
			{
				//returning the position of the current index
				return j;

			}//end if statement to determine if the value of the current index is the same as the desired value

		}//end for loop to check each index for the specified value

		//the indicated value is not in the array, -1 will be returned
		return -1;

	}//end findElement method
	
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: findElement
	 * Description: Returns the position of the first instance of the indicated value. Returns -1 if the ‘value’ is not found.
	 * Parameters: double [] array, int value
	 * Returns: int position
	 */

	public static int findElement(double [] array, double value)
	{
		//a for loop to check each index for the specified value
		for (int j=0; j<=array.length-1; j++)
		{
			//an if statement to determine if the value of the current index is the same as the desired value
			if (array[j]==value)
			{
				//returning the position of the current index
				return j;

			}//end if statement to determine if the value of the current index is the same as the desired value

		}//end for loop to check each index for the specified value

		//the indicated value is not in the array, -1 will be returned
		return -1;

	}//end findElement method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: findElement
	 * Description: Returns the position of the first instance of the indicated value. Returns -1 if the ‘value’ is not found.
	 * Parameters: char [] array, int value
	 * Returns: int position
	 */

	public static int findElement(char [] array, char value)
	{
		//a for loop to check each index for the specified value
		for (int j=0; j<=array.length-1; j++)
		{
			//an if statement to determine if the value of the current index is the same as the desired value
			if (array[j]==value)
			{
				//returning the position of the current index
				return j;

			}//end if statement to determine if the value of the current index is the same as the desired value

		}//end for loop to check each index for the specified value

		//the indicated value is not in the array, -1 will be returned
		return -1;

	}//end findElement method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: countElements
	 * Description: Returns the number of times the ‘value’ is found in the array.
	 * Parameters: int value, int varargs array
	 * Returns: int numOfElements
	 */

	public static int countElements(int value, int ... array)
	{
		//creating and initializing the variable to count how many times the specified value occurs in the array
		int numOfElements=0;

		//a for loop to update the numOfElements value as every index in the array is checked
		for (int item: array)
		{
			//an if statement to update numOfElements when the value of the current index equals the specified value
			if (item==value)
			{
				numOfElements++;
			}//end if statement to update numOfElements when the value of the current index equals the specified value

		}//end for loop to update the numOfElements value as every index in the array is checked

		//returning the number of times the specified value was found in the array
		return numOfElements;

	}//end countElements method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: countElements
	 * Description: Returns the number of times the ‘value’ is found in the array.
	 * Parameters: String value, String varargs array
	 * Returns: int numOfElements
	 */

	public static int countElements(String value, String ... array)
	{
		//creating and initializing the variable to count how many times the specified value occurs in the array
		int numOfElements=0;

		//a for loop to update the numOfElements value as every index in the array is checked
		for (String item: array)
		{
			//an if statement to update numOfElements when the value of the current index equals the specified value
			if (item.equals(value))
			{
				numOfElements++;
			}//end if statement to update numOfElements when the value of the current index equals the specified value

		}//end for loop to update the numOfElements value as every index in the array is checked

		//returning the number of times the specified value was found in the array
		return numOfElements;

	}//end countElements method
	
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: countElements
	 * Description: Returns the number of times the ‘value’ is found in the array.
	 * Parameters: double value, double varargs array
	 * Returns: int numOfElements
	 */

	public static int countElements(double value, double ... array)
	{
		//creating and initializing the variable to count how many times the specified value occurs in the array
		int numOfElements=0;

		//a for loop to update the numOfElements value as every index in the array is checked
		for (double item: array)
		{
			//an if statement to update numOfElements when the value of the current index equals the specified value
			if (item==value)
			{
				numOfElements++;
			}//end if statement to update numOfElements when the value of the current index equals the specified value

		}//end for loop to update the numOfElements value as every index in the array is checked

		//returning the number of times the specified value was found in the array
		return numOfElements;

	}//end countElements method
	
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: countElements
	 * Description: Returns the number of times the ‘value’ is found in the array.
	 * Parameters: char value, char varargs array
	 * Returns: int numOfElements
	 */

	public static int countElements(char value, char ... array)
	{
		//creating and initializing the variable to count how many times the specified value occurs in the array
		int numOfElements=0;

		//a for loop to update the numOfElements value as every index in the array is checked
		for (char item: array)
		{
			//an if statement to update numOfElements when the value of the current index equals the specified value
			if (item==value)
			{
				numOfElements++;
			}//end if statement to update numOfElements when the value of the current index equals the specified value

		}//end for loop to update the numOfElements value as every index in the array is checked

		//returning the number of times the specified value was found in the array
		return numOfElements;

	}//end countElements method
	

	/*
	 * Method Author: Brooke Cronin
	 * Method Name: copyArray
	 * Description: Makes a copy of the original array and returns the copy.
	 * Parameters: int [] originalArray
	 * Returns: int [] newArray
	 */

	public static int[] copyArray(int [] originalArray)
	{
		//creating the new array with the same length as the original array
		int[] newArray=new int[originalArray.length];

		//a for loop to make all values in the original array the same as in the new array
		for (int j=0; j<=originalArray.length-1; j++)
		{
			newArray[j]=originalArray[j];

		}//end for loop to make all values in the original array the same as in the new array

		//returning the array that is a copy of the original array
		return newArray;

	}//end copyArray method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: copyArray
	 * Description: Makes a copy of the original array and returns the copy.
	 * Parameters: String [] originalArray
	 * Returns: String [] newArray
	 */

	public static String[] copyArray(String [] originalArray)
	{
		//creating the new array with the same length as the original array
		String[] newArray=new String[originalArray.length];

		//a for loop to make all values in the original array the same as in the new array
		for (int j=0; j<=originalArray.length-1; j++)
		{
			newArray[j]=originalArray[j];

		}//end for loop to make all values in the original array the same as in the new array

		//returning the array that is a copy of the original array
		return newArray;

	}//end copyArray method
	
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: copyArray
	 * Description: Makes a copy of the original array and returns the copy.
	 * Parameters: double [] originalArray
	 * Returns: double [] newArray
	 */

	public static double[] copyArray(double [] originalArray)
	{
		//creating the new array with the same length as the original array
		double[] newArray=new double[originalArray.length];

		//a for loop to make all values in the original array the same as in the new array
		for (int j=0; j<=originalArray.length-1; j++)
		{
			newArray[j]=originalArray[j];

		}//end for loop to make all values in the original array the same as in the new array

		//returning the array that is a copy of the original array
		return newArray;

	}//end copyArray method
	
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: copyArray
	 * Description: Makes a copy of the original array and returns the copy.
	 * Parameters: char [] originalArray
	 * Returns: char [] newArray
	 */

	public static char[] copyArray(char [] originalArray)
	{
		//creating the new array with the same length as the original array
		char[] newArray=new char[originalArray.length];

		//a for loop to make all values in the original array the same as in the new array
		for (int j=0; j<=originalArray.length-1; j++)
		{
			newArray[j]=originalArray[j];

		}//end for loop to make all values in the original array the same as in the new array

		//returning the array that is a copy of the original array
		return newArray;

	}//end copyArray method
	

	/*
	 * Method Author: Brooke Cronin
	 * Method Name: checkIfCopy
	 * Description: Compares the individual elements in the array and returns true if the arrays are the same.
	 * Parameters: int [] array1, int [] array2
	 * Returns: boolean isCopy
	 */

	public static boolean checkIfCopy(int [] array1, int [] array2)
	{
		//an if statement to make sure that the arrays aren't the same
		if(array1==array2)
		{
			//returning false since the arrays are the same one and can't be copies of one another
			return false;

		}//end if statement to make sure that the arrays aren't the same

		//an if statement to check of both arrays are the same length
		if (array1.length==array2.length)
		{
			//a for loop to check if the current index in both arrays have the same value
			for (int j=0; j<=array1.length-1; j++)
			{
				//an if statement for when the values are not the same and returning false
				if (array1[j]!=array2[j])
				{
					return false;

				}//end if statement for when the values are not the same and returning false

			}//end for loop to check if the current index in both arrays have the same value

		}//end if statement to check of both arrays are the same length

		//an else statement when the two arrays aren't the same length and returning false
		else 
		{
			return false;

		}//end else statement when the two arrays aren't the same length and returning false

		//returning true when both arrays are the same length and have the same values
		return true;

	}//end checkIfCopy method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: checkIfCopy
	 * Description: Compares the individual elements in the array and returns true if the arrays are the same.
	 * Parameters: String [] array1, String [] array2
	 * Returns: boolean isCopy
	 */

	public static boolean checkIfCopy(String [] array1, String [] array2)
	{
		//an if statement to make sure that the arrays aren't the same
		if(array1==array2)
		{
			//returning false since the arrays are the same one and can't be copies of one another
			return false;

		}//end if statement to make sure that the arrays aren't the same

		//an if statement to check of both arrays are the same length
		if (array1.length==array2.length)
		{
			//a for loop to check if the current index in both arrays have the same value
			for (int j=0; j<=array1.length-1; j++)
			{
				//an if statement for when the values are not the same and returning false
				if (array1[j]!=array2[j])
				{
					return false;

				}//end if statement for when the values are not the same and returning false

			}//end for loop to check if the current index in both arrays have the same value

		}//end if statement to check of both arrays are the same length

		//an else statement when the two arrays aren't the same length and returning false
		else 
		{
			return false;

		}//end else statement when the two arrays aren't the same length and returning false

		//returning true when both arrays are the same length and have the same values
		return true;

	}//end checkIfCopy method
	
	
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: checkIfCopy
	 * Description: Compares the individual elements in the array and returns true if the arrays are the same.
	 * Parameters: double [] array1, double [] array2
	 * Returns: boolean isCopy
	 */

	public static boolean checkIfCopy(double [] array1, double [] array2)
	{
		//an if statement to make sure that the arrays aren't the same
		if(array1==array2)
		{
			//returning false since the arrays are the same one and can't be copies of one another
			return false;

		}//end if statement to make sure that the arrays aren't the same

		//an if statement to check of both arrays are the same length
		if (array1.length==array2.length)
		{
			//a for loop to check if the current index in both arrays have the same value
			for (int j=0; j<=array1.length-1; j++)
			{
				//an if statement for when the values are not the same and returning false
				if (array1[j]!=array2[j])
				{
					return false;

				}//end if statement for when the values are not the same and returning false

			}//end for loop to check if the current index in both arrays have the same value

		}//end if statement to check of both arrays are the same length

		//an else statement when the two arrays aren't the same length and returning false
		else 
		{
			return false;

		}//end else statement when the two arrays aren't the same length and returning false

		//returning true when both arrays are the same length and have the same values
		return true;

	}//end checkIfCopy method
	
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: checkIfCopy
	 * Description: Compares the individual elements in the array and returns true if the arrays are the same.
	 * Parameters: char [] array1, char [] array2
	 * Returns: boolean isCopy
	 */

	public static boolean checkIfCopy(char [] array1, char [] array2)
	{
		//an if statement to make sure that the arrays aren't the same
		if(array1==array2)
		{
			//returning false since the arrays are the same one and can't be copies of one another
			return false;

		}//end if statement to make sure that the arrays aren't the same

		//an if statement to check of both arrays are the same length
		if (array1.length==array2.length)
		{
			//a for loop to check if the current index in both arrays have the same value
			for (int j=0; j<=array1.length-1; j++)
			{
				//an if statement for when the values are not the same and returning false
				if (array1[j]!=array2[j])
				{
					return false;

				}//end if statement for when the values are not the same and returning false

			}//end for loop to check if the current index in both arrays have the same value

		}//end if statement to check of both arrays are the same length

		//an else statement when the two arrays aren't the same length and returning false
		else 
		{
			return false;

		}//end else statement when the two arrays aren't the same length and returning false

		//returning true when both arrays are the same length and have the same values
		return true;

	}//end checkIfCopy method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: checkIfIdentical
	 * Description: Compares the location of each array and identifies if they point to the same array.
	 * Parameters: int [] array1, int [] array2
	 * Returns: boolean isIdentical
	 */

	public static boolean checkIfIdentical(int [] array1, int [] array2)
	{
		//an if statement to determine whether or not the specified arrays are identical and returning true if they are
		if(array1==array2)
		{
			return true;

		}//end if statement to determine whether or not the specified arrays are identical and returning true if they are

		//when the arrays are not identical
		return false;

	}//end checkIfIdentical method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: checkIfIdentical
	 * Description: Compares the location of each array and identifies if they point to the same array.
	 * Parameters: String [] array1, String [] array2
	 * Returns: boolean isIdentical
	 */

	public static boolean checkIfIdentical(String [] array1, String [] array2)
	{
		//an if statement to determine whether or not the specified arrays are identical and returning true if they are
		if(array1==array2)
		{
			return true;

		}//end if statement to determine whether or not the specified arrays are identical and returning true if they are

		//when the arrays are not identical
		return false;

	}//end checkIfIdentical method
	
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: checkIfIdentical
	 * Description: Compares the location of each array and identifies if they point to the same array.
	 * Parameters: double [] array1, double [] array2
	 * Returns: boolean isIdentical
	 */

	public static boolean checkIfIdentical(double [] array1, double [] array2)
	{
		//an if statement to determine whether or not the specified arrays are identical and returning true if they are
		if(array1==array2)
		{
			return true;

		}//end if statement to determine whether or not the specified arrays are identical and returning true if they are

		//when the arrays are not identical
		return false;

	}//end checkIfIdentical method
	
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: checkIfIdentical
	 * Description: Compares the location of each array and identifies if they point to the same array.
	 * Parameters: char [] array1, char [] array2
	 * Returns: boolean isIdentical
	 */

	public static boolean checkIfIdentical(char [] array1, char [] array2)
	{
		//an if statement to determine whether or not the specified arrays are identical and returning true if they are
		if(array1==array2)
		{
			return true;

		}//end if statement to determine whether or not the specified arrays are identical and returning true if they are

		//when the arrays are not identical
		return false;

	}//end checkIfIdentical method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: replaceElement
	 * Description: Replaces the element at index ‘position’ with the indicated ‘value’.
	 * Parameters: int [] array, int value, int position
	 * Returns: N/A
	 */

	public static void replaceElement(int [] array, int value, int position)
	{
		//changing the value of the specified position to the specified value
		array[position]=value;

	}//end replaceElement method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: replaceElement
	 * Description: Replaces the element at index ‘position’ with the indicated ‘value’.
	 * Parameters: String [] array, String value, int position
	 * Returns: N/A
	 */

	public static void replaceElement(String [] array, String value, int position)
	{
		//changing the value of the specified position to the specified value
		array[position]=value;

	}//end replaceElement method
	
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: replaceElement
	 * Description: Replaces the element at index ‘position’ with the indicated ‘value’.
	 * Parameters: double [] array, double value, int position
	 * Returns: N/A
	 */

	public static void replaceElement(double [] array, double value, int position)
	{
		//changing the value of the specified position to the specified value
		array[position]=value;

	}//end replaceElement method
	
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: replaceElement
	 * Description: Replaces the element at index ‘position’ with the indicated ‘value’.
	 * Parameters: char [] array, char value, int position
	 * Returns: N/A
	 */

	public static void replaceElement(char [] array, char value, int position)
	{
		//changing the value of the specified position to the specified value
		array[position]=value;

	}//end replaceElement method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: replaceElements
	 * Description: Replaces all elements of the original value with the new value.
	 * Parameters: int [] array, int originalValue, int newValue
	 * Returns: N/A
	 */

	public static void replaceElements(int [] array, int originalValue, int newValue)
	{
		//a for loop to find and replace all indexes in the array that has the original specified value to the new specified value
		for (int j=0; j<=array.length-1; j++)
		{
			//an if statement for when the current index's value is the original specified value and changing this value to the new specified value
			if (array[j]==originalValue)
			{
				array[j]=newValue;

			}//end if statement for when the current index's value is the original specified value and changing this value to the new specified value

		}//end for loop to find and replace all indexes in the array that has the original specified value to the new specified value

	}//end replaceElements method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: replaceElements
	 * Description: Replaces all elements of the original value with the new value.
	 * Parameters: String [] array, String originalValue, String newValue
	 * Returns: N/A
	 */

	public static void replaceElements(String [] array, String originalValue, String newValue)
	{
		//a for loop to find and replace all indexes in the array that has the original specified value to the new specified value
		for (int j=0; j<=array.length-1; j++)
		{
			//an if statement for when the current index's value is the original specified value and changing this value to the new specified value
			if (array[j].equals(originalValue))
			{
				array[j]=newValue;

			}//end if statement for when the current index's value is the original specified value and changing this value to the new specified value

		}//end for loop to find and replace all indexes in the array that has the original specified value to the new specified value

	}//end replaceElements method
	
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: replaceElements
	 * Description: Replaces all elements of the original value with the new value.
	 * Parameters: double [] array, double originalValue, double newValue
	 * Returns: N/A
	 */

	public static void replaceElements(double [] array, double originalValue, double newValue)
	{
		//a for loop to find and replace all indexes in the array that has the original specified value to the new specified value
		for (int j=0; j<=array.length-1; j++)
		{
			//an if statement for when the current index's value is the original specified value and changing this value to the new specified value
			if (array[j]==originalValue)
			{
				array[j]=newValue;

			}//end if statement for when the current index's value is the original specified value and changing this value to the new specified value

		}//end for loop to find and replace all indexes in the array that has the original specified value to the new specified value

	}//end replaceElements method

	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: replaceElements
	 * Description: Replaces all elements of the original value with the new value.
	 * Parameters: char [] array, char originalValue, char newValue
	 * Returns: N/A
	 */

	public static void replaceElements(char [] array, char originalValue, char newValue)
	{
		//a for loop to find and replace all indexes in the array that has the original specified value to the new specified value
		for (int j=0; j<=array.length-1; j++)
		{
			//an if statement for when the current index's value is the original specified value and changing this value to the new specified value
			if (array[j]==originalValue)
			{
				array[j]=newValue;

			}//end if statement for when the current index's value is the original specified value and changing this value to the new specified value

		}//end for loop to find and replace all indexes in the array that has the original specified value to the new specified value

	}//end replaceElements method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: insertElementByIndex
	 * Description: Creates an array with size originalArray.length +1 and inserts the element ‘value’ at index ‘position’. All other values >position, are shifted to index+1.
	 * Parameters: int [] originalArray, int value, int position
	 * Returns: int [] newArray
	 */

	public static int[] insertElementByIndex(int [] originalArray, int value, int position)
	{
		//creating a new array that has one more index than the original array
		int[] newArray=new int[originalArray.length+1];

		//initializing the specified position in the new array to the specified value
		newArray[position]=value;

		//a for loop to assign the same value from the original array to all indexes less than the position where the new index is getting added
		for(int j=0; j<position; j++)
		{
			newArray[j]=originalArray[j];

		}//end for loop to assign the same value from the original array to all indexes less than the position where the new index is getting added

		//a for loop to add the values for the indexes after the added index in the new array
		for(int i=position+1; i<=newArray.length-1; i++)
		{
			newArray[i]=originalArray[i-1];

		}//end for loop to add the values for the indexes after the added index in the new array

		//returning the new array with the added value
		return newArray;

	}//end insertElementByIndex method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: insertElementByIndex
	 * Description: Creates an array with size originalArray.length +1 and inserts the element ‘value’ at index ‘position’. All other values >position, are shifted to index+1.
	 * Parameters: String [] originalArray, String value, int position
	 * Returns: String [] newArray
	 */

	public static String[] insertElementByIndex(String [] originalArray, String value, int position)
	{
		//creating a new array that has one more index than the original array
		String[] newArray=new String[originalArray.length+1];

		//initializing the specified position in the new array to the specified value
		newArray[position]=value;

		//a for loop to assign the same value from the original array to all indexes less than the position where the new index is getting added
		for(int j=0; j<position; j++)
		{
			newArray[j]=originalArray[j];

		}//end for loop to assign the same value from the original array to all indexes less than the position where the new index is getting added

		//a for loop to add the values for the indexes after the added index in the new array
		for(int i=position+1; i<=newArray.length-1; i++)
		{
			newArray[i]=originalArray[i-1];

		}//end for loop to add the values for the indexes after the added index in the new array

		//returning the new array with the added value
		return newArray;

	}//end insertElementByIndex method
	
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: insertElementByIndex
	 * Description: Creates an array with size originalArray.length +1 and inserts the element ‘value’ at index ‘position’. All other values >position, are shifted to index+1.
	 * Parameters: double [] originalArray, double value, int position
	 * Returns: double [] newArray
	 */

	public static double[] insertElementByIndex(double [] originalArray, double value, int position)
	{
		//creating a new array that has one more index than the original array
		double[] newArray=new double[originalArray.length+1];

		//initializing the specified position in the new array to the specified value
		newArray[position]=value;

		//a for loop to assign the same value from the original array to all indexes less than the position where the new index is getting added
		for(int j=0; j<position; j++)
		{
			newArray[j]=originalArray[j];

		}//end for loop to assign the same value from the original array to all indexes less than the position where the new index is getting added

		//a for loop to add the values for the indexes after the added index in the new array
		for(int i=position+1; i<=newArray.length-1; i++)
		{
			newArray[i]=originalArray[i-1];

		}//end for loop to add the values for the indexes after the added index in the new array

		//returning the new array with the added value
		return newArray;

	}//end insertElementByIndex method
	
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: insertElementByIndex
	 * Description: Creates an array with size originalArray.length +1 and inserts the element ‘value’ at index ‘position’. All other values >position, are shifted to index+1.
	 * Parameters: char [] originalArray, char value, int position
	 * Returns: char [] newArray
	 */

	public static char[] insertElementByIndex(char [] originalArray, char value, int position)
	{
		//creating a new array that has one more index than the original array
		char[] newArray=new char[originalArray.length+1];

		//initializing the specified position in the new array to the specified value
		newArray[position]=value;

		//a for loop to assign the same value from the original array to all indexes less than the position where the new index is getting added
		for(int j=0; j<position; j++)
		{
			newArray[j]=originalArray[j];

		}//end for loop to assign the same value from the original array to all indexes less than the position where the new index is getting added

		//a for loop to add the values for the indexes after the added index in the new array
		for(int i=position+1; i<=newArray.length-1; i++)
		{
			newArray[i]=originalArray[i-1];

		}//end for loop to add the values for the indexes after the added index in the new array

		//returning the new array with the added value
		return newArray;

	}//end insertElementByIndex method
	

	/*
	 * Method Author: Brooke Cronin
	 * Method Name: deleteElementByValue
	 * Description: Creates an array with size originalArray.length -1 and removes the first element of the indicated ‘value’. All other values >position, are shifted to index-1. Otherwise returns the original array.
	 * Parameters: int [] originalArray, int value
	 * Returns: int [] newArray
	 */

	public static int[] deleteElementByValue(int[] originalArray, int value)
	{
		//creating a new array that has one less index than the original array
		int[] newArray=new int[originalArray.length-1];

		//call to findElement method to determine the position of the specified value
		int position=Array.findElement(originalArray, value);

		//an if statement for when the specified value does not exist in the original array
		if(position==-1)
		{
			return originalArray;

		}//end if statement for when the specified value does not exist in the original array

		//a for loop to assign the same value from the original array to all indexes less than the position where the new index is getting taken away
		for(int j=0; j<position; j++)
		{
			newArray[j]=originalArray[j];

		}//end for loop to assign the same value from the original array to all indexes less than the position where the new index is getting taken away

		//a for loop to add the values for the indexes after the subtracted index in the new array
		for(int i=position+1; i<=newArray.length-1; i++)
		{
			newArray[i]=originalArray[i+1];

		}//end for loop to add the values for the indexes after the subtracted index in the new array

		//returning the new array with the subtracted value
		return newArray;

	}//end deleteElementByValue method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: deleteElementByValue
	 * Description: Creates an array with size originalArray.length -1 and removes the first element of the indicated ‘value’. All other values >position, are shifted to index-1. Otherwise returns the original array.
	 * Parameters: String [] originalArray, int value
	 * Returns: String [] newArray
	 */

	public static String[] deleteElementByValue(String[] originalArray, String value)
	{
		//creating a new array that has one less index than the original array
		String[] newArray=new String[originalArray.length-1];

		//call to findElement method to determine the position of the specified value
		int position=Array.findElement(originalArray, value);

		//an if statement for when the specified value does not exist in the original array
		if(position==-1)
		{
			return originalArray;

		}//end if statement for when the specified value does not exist in the original array

		//a for loop to assign the same value from the original array to all indexes less than the position where the new index is getting taken away
		for(int j=0; j<position; j++)
		{
			newArray[j]=originalArray[j];

		}//end for loop to assign the same value from the original array to all indexes less than the position where the new index is getting taken away

		//a for loop to add the values for the indexes after the subtracted index in the new array
		for(int i=position+1; i<=newArray.length-1; i++)
		{
			newArray[i]=originalArray[i+1];

		}//end for loop to add the values for the indexes after the subtracted index in the new array

		//returning the new array with the subtracted value
		return newArray;

	}//end deleteElementByValue method
	
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: deleteElementByValue
	 * Description: Creates an array with size originalArray.length -1 and removes the first element of the indicated ‘value’. All other values >position, are shifted to index-1. Otherwise returns the original array.
	 * Parameters: double [] originalArray, double value
	 * Returns: double [] newArray
	 */

	public static double[] deleteElementByValue(double[] originalArray, double value)
	{
		//creating a new array that has one less index than the original array
		double[] newArray=new double[originalArray.length-1];

		//call to findElement method to determine the position of the specified value
		int position=Array.findElement(originalArray, value);

		//an if statement for when the specified value does not exist in the original array
		if(position==-1)
		{
			return originalArray;

		}//end if statement for when the specified value does not exist in the original array

		//a for loop to assign the same value from the original array to all indexes less than the position where the new index is getting taken away
		for(int j=0; j<position; j++)
		{
			newArray[j]=originalArray[j];

		}//end for loop to assign the same value from the original array to all indexes less than the position where the new index is getting taken away

		//a for loop to add the values for the indexes after the subtracted index in the new array
		for(int i=position+1; i<=newArray.length-1; i++)
		{
			newArray[i]=originalArray[i+1];

		}//end for loop to add the values for the indexes after the subtracted index in the new array

		//returning the new array with the subtracted value
		return newArray;

	}//end deleteElementByValue method
	
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: deleteElementByValue
	 * Description: Creates an array with size originalArray.length -1 and removes the first element of the indicated ‘value’. All other values >position, are shifted to index-1. Otherwise returns the original array.
	 * Parameters: char [] originalArray, char value
	 * Returns: char [] newArray
	 */

	public static char[] deleteElementByValue(char[] originalArray, char value)
	{
		//creating a new array that has one less index than the original array
		char[] newArray=new char[originalArray.length-1];

		//call to findElement method to determine the position of the specified value
		int position=Array.findElement(originalArray, value);

		//an if statement for when the specified value does not exist in the original array
		if(position==-1)
		{
			return originalArray;

		}//end if statement for when the specified value does not exist in the original array

		//a for loop to assign the same value from the original array to all indexes less than the position where the new index is getting taken away
		for(int j=0; j<position; j++)
		{
			newArray[j]=originalArray[j];

		}//end for loop to assign the same value from the original array to all indexes less than the position where the new index is getting taken away

		//a for loop to add the values for the indexes after the subtracted index in the new array
		for(int i=position+1; i<=newArray.length-1; i++)
		{
			newArray[i]=originalArray[i+1];

		}//end for loop to add the values for the indexes after the subtracted index in the new array

		//returning the new array with the subtracted value
		return newArray;

	}//end deleteElementByValue method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: deleteElementByIndex
	 * Description: Creates an array with size originalArray.length -1 and removes the element at index ‘position’. All other values >position, are shifted to index-1.
	 * Parameters: int [] originalArray, int position
	 * Returns: int [] newArray
	 */

	public static int[] deleteElementByIndex(int[] originalArray, int position)
	{
		//creating a new array that has one less index than the original array
		int[] newArray=new int[originalArray.length-1];

		//a for loop to assign the same value from the original array to all indexes less than the position where the new index is getting taken away
		for(int j=0; j<position; j++)
		{
			newArray[j]=originalArray[j];

		}//end for loop to assign the same value from the original array to all indexes less than the position where the new index is getting taken away

		//a for loop to add the values for the indexes after the subtracted index in the new array
		for(int i=position; i<=newArray.length-1; i++)
		{
			newArray[i]=originalArray[i+1];

		}//end for loop to add the values for the indexes after the subtracted index in the new array

		//returning the new array with the subtracted value
		return newArray;

	}//end deleteElementByIndex method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: deleteElementByIndex
	 * Description: Creates an array with size originalArray.length -1 and removes the element at index ‘position’. All other values >position, are shifted to index-1.
	 * Parameters: int [] originalArray, int position
	 * Returns: int [] newArray
	 */

	public static String[] deleteElementByIndex(String[] originalArray, int position)
	{
		//creating a new array that has one less index than the original array
		String[] newArray=new String[originalArray.length-1];

		//a for loop to assign the same value from the original array to all indexes less than the position where the new index is getting taken away
		for(int j=0; j<position; j++)
		{
			newArray[j]=originalArray[j];

		}//end for loop to assign the same value from the original array to all indexes less than the position where the new index is getting taken away

		//a for loop to add the values for the indexes after the subtracted index in the new array
		for(int i=position; i<=newArray.length-1; i++)
		{
			newArray[i]=originalArray[i+1];

		}//end for loop to add the values for the indexes after the subtracted index in the new array

		//returning the new array with the subtracted value
		return newArray;

	}//end deleteElementByIndex method
	
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: deleteElementByIndex
	 * Description: Creates an array with size originalArray.length -1 and removes the element at index ‘position’. All other values >position, are shifted to index-1.
	 * Parameters: double [] originalArray, int position
	 * Returns: double [] newArray
	 */

	public static double[] deleteElementByIndex(double[] originalArray, int position)
	{
		//creating a new array that has one less index than the original array
		double[] newArray=new double[originalArray.length-1];

		//a for loop to assign the same value from the original array to all indexes less than the position where the new index is getting taken away
		for(int j=0; j<position; j++)
		{
			newArray[j]=originalArray[j];

		}//end for loop to assign the same value from the original array to all indexes less than the position where the new index is getting taken away

		//a for loop to add the values for the indexes after the subtracted index in the new array
		for(int i=position; i<=newArray.length-1; i++)
		{
			newArray[i]=originalArray[i+1];

		}//end for loop to add the values for the indexes after the subtracted index in the new array

		//returning the new array with the subtracted value
		return newArray;

	}//end deleteElementByIndex method

	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: deleteElementByIndex
	 * Description: Creates an array with size originalArray.length -1 and removes the element at index ‘position’. All other values >position, are shifted to index-1.
	 * Parameters: char [] originalArray, int position
	 * Returns: char [] newArray
	 */

	public static char[] deleteElementByIndex(char[] originalArray, int position)
	{
		//creating a new array that has one less index than the original array
		char[] newArray=new char[originalArray.length-1];

		//a for loop to assign the same value from the original array to all indexes less than the position where the new index is getting taken away
		for(int j=0; j<position; j++)
		{
			newArray[j]=originalArray[j];

		}//end for loop to assign the same value from the original array to all indexes less than the position where the new index is getting taken away

		//a for loop to add the values for the indexes after the subtracted index in the new array
		for(int i=position; i<=newArray.length-1; i++)
		{
			newArray[i]=originalArray[i+1];

		}//end for loop to add the values for the indexes after the subtracted index in the new array

		//returning the new array with the subtracted value
		return newArray;

	}//end deleteElementByIndex method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: sortHighToLow
	 * Description: Creates a new array where the values from the original array are sorted from the Highest value to the Lowest value.
	 * Parameters: int [] originalArray
	 * Returns: int [] newArray
	 */

	public static int[] sortHighToLow(int [] originalArray)
	{
		//creating a new array that is a copy of the original array
		int[] newArray=Array.copyArray(originalArray);

		//a for loop to go through the array multiple times and check every combination of pairs if values
		for(int i=0; i<=newArray.length-1; i++)
		{
			//a for loop to check which value in the pair of indexes is larger
			for(int j=0; j<=newArray.length-2; j++)
			{
				//an if statement to change the positions of the values of the pairs
				if(newArray[j]<newArray[j+1])
				{
					Array.swapElements(newArray, j, j+1);

				}//end if statement to change the positions of the values of the pairs

			}//end for loop to check which value in the pair of indexes is larger

		}//end for loop to go through the array multiple times and check every combination of pairs if values

		//returning the newly sorted array
		return newArray;

	}//end sortHighToLow method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: sortHighToLow
	 * Description: Creates a new array where the values from the original array are sorted from the Highest value to the Lowest value. Only checks the first four letters of each word.
	 * Parameters: String [] originalArray
	 * Returns: String [] newArray
	 */

	public static String[] sortHighToLow(String [] originalArray)
	{
		//creating a new array that is a copy of the original array
		String[] newArray=Array.copyArray(originalArray);

		//initializing variables to be used in this method
		char [] firstLetter = new char [newArray.length];
		char [] secondLetter = new char [newArray.length];
		char [] thirdLetter = new char [newArray.length];
		char [] fourthLetter = new char [newArray.length];
		char positionHolder;

		//a for loop to set the first character of each index in the array to the corresponding index in a character array
		for (int i=0; i<newArray.length; i++)
		{
			firstLetter[i]=newArray[i].charAt(0);

		}//end for loop to set the first character of each index in the array to the corresponding index in a character array

		//a for loop to go through the array multiple times and check every combination of pairs if values
		for(int i=0; i<newArray.length+1; i++)
		{
			//a for loop to check the values of each index of the array 
			for (int j=0; j<newArray.length-1; j++)
			{
				//an if statement when the first character of current index/value is greater than the next index's first character
				if(firstLetter[j]<firstLetter[j+1])
				{
					positionHolder=firstLetter[j];
					firstLetter[j]=firstLetter[j+1];
					firstLetter[j+1]=positionHolder;
				}//end if statement when the first character of current index/value is greater than the next index's first character

				//an else if statement when the first character in both strings are equal
				else if(firstLetter[j]==firstLetter[j+1])
				{
					secondLetter[j]=newArray[j].charAt(1);
					secondLetter[j+1]=newArray[j+1].charAt(1);

					//an if statement when the second character of current index/value is greater than the next index's second character
					if(secondLetter[j]<secondLetter[j+1])
					{
						positionHolder=firstLetter[j];
						firstLetter[j]=firstLetter[j+1];
						firstLetter[j+1]=positionHolder;
					}//end if statement when the second character of current index/value is greater than the next index's second character

					//an else if statement when the second character in both strings are equal
					else if(secondLetter[j]==secondLetter[j+1])
					{
						thirdLetter[j]=newArray[j].charAt(2);
						thirdLetter[j+1]=newArray[j+1].charAt(2);

						//an if statement when the third character of current index/value is greater than the next index's third character
						if(thirdLetter[j]<thirdLetter[j+1])
						{
							positionHolder=firstLetter[j];
							firstLetter[j]=firstLetter[j+1];
							firstLetter[j+1]=positionHolder;
						}//end if statement when the third character of current index/value is greater than the next index's third character

						//an else if statement when the third character in both strings are equal
						else if(thirdLetter[j]==thirdLetter[j+1])
						{
							fourthLetter[j]=newArray[j].charAt(3);
							fourthLetter[j+1]=newArray[j+1].charAt(3);

							//an if statement when the fourth character of current index/value is greater than the next index's fourth character
							if(fourthLetter[j]<fourthLetter[j+1])
							{
								positionHolder=firstLetter[j];
								firstLetter[j]=firstLetter[j+1];
								firstLetter[j+1]=positionHolder;
							}//end if statement when the fourth character of current index/value is greater than the next index's fourth character
						}//end else if statement when the third character in both strings are equal
					}//end else if statement when the second character in both strings are equal
				}//end else if statement when the first character in both strings are equal
			}//end for loop to check the values of each index of the array 
		}//end for loop to go through the array multiple times and check every combination of pairs if values

		//returning the newly sorted array
		return newArray;

	}//end sortHighToLow method
	
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: sortHighToLow
	 * Description: Creates a new array where the values from the original array are sorted from the Highest value to the Lowest value.
	 * Parameters: double [] originalArray
	 * Returns: double [] newArray
	 */

	public static double[] sortHighToLow(double [] originalArray)
	{
		//creating a new array that is a copy of the original array
		double[] newArray=Array.copyArray(originalArray);

		//a for loop to go through the array multiple times and check every combination of pairs if values
		for(int i=0; i<=newArray.length-1; i++)
		{
			//a for loop to check which value in the pair of indexes is larger
			for(int j=0; j<=newArray.length-2; j++)
			{
				//an if statement to change the positions of the values of the pairs
				if(newArray[j]<newArray[j+1])
				{
					Array.swapElements(newArray, j, j+1);

				}//end if statement to change the positions of the values of the pairs

			}//end for loop to check which value in the pair of indexes is larger

		}//end for loop to go through the array multiple times and check every combination of pairs if values

		//returning the newly sorted array
		return newArray;

	}//end sortHighToLow method
	
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: sortHighToLow
	 * Description: Creates a new array where the values from the original array are sorted from the Highest value to the Lowest value.
	 * Parameters: char [] originalArray
	 * Returns: char [] newArray
	 */

	public static char[] sortHighToLow(char [] originalArray)
	{
		//creating a new array that is a copy of the original array
		char[] newArray=Array.copyArray(originalArray);

		//a for loop to go through the array multiple times and check every combination of pairs if values
		for(int i=0; i<=newArray.length-1; i++)
		{
			//a for loop to check which value in the pair of indexes is larger
			for(int j=0; j<=newArray.length-2; j++)
			{
				//an if statement to change the positions of the values of the pairs
				if(newArray[j]<newArray[j+1])
				{
					Array.swapElements(newArray, j, j+1);

				}//end if statement to change the positions of the values of the pairs

			}//end for loop to check which value in the pair of indexes is larger

		}//end for loop to go through the array multiple times and check every combination of pairs if values

		//returning the newly sorted array
		return newArray;

	}//end sortHighToLow method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: sortLowToHigh
	 * Description: Creates a new array where the values from the original array are sorted from the Lowest value to the Highest value.
	 * Parameters: int [] originalArray
	 * Returns: int [] newArray
	 */

	public static int[] sortLowToHigh(int [] originalArray)
	{
		//creating a new array that is a copy of the original array
		int[] newArray=Array.copyArray(originalArray);

		//a for loop to go through the array multiple times and check every combination of pairs if values
		for(int i=0; i<=newArray.length-1; i++)
		{
			//a for loop to check which value in the pair of indexes is smaller
			for(int j=0; j<=newArray.length-2; j++)
			{
				//an if statement to change the positions of the values of the pairs
				if(newArray[j]>newArray[j+1])
				{
					Array.swapElements(newArray, j, j+1);

				}//end if statement to change the positions of the values of the pairs

			}//end for loop to check which value in the pair of indexes is smaller

		}//end for loop to go through the array multiple times and check every combination of pairs if values

		//returning the newly sorted array
		return newArray;

	}//end sortLowToHigh method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: sortLowToHigh
	 * Description: Creates a new array where the values from the original array are sorted from the Lowest value to the Highest value. Only checks the first four letters of each word.
	 * Parameters: String [] originalArray
	 * Returns: String [] newArray
	 */

	public static String[] sortLowToHigh(String [] originalArray)
	{
		//creating a new array that is a copy of the original array
		String[] newArray=Array.copyArray(originalArray);

		//initializing variables to be used in this method
		char [] firstLetter = new char [newArray.length];
		char [] secondLetter = new char [newArray.length];
		char [] thirdLetter = new char [newArray.length];
		char [] fourthLetter = new char [newArray.length];
		char positionHolder;

		//a for loop to set the first character of each index in the array to the corresponding index in a character array
		for (int i=0; i<newArray.length; i++)
		{
			firstLetter[i]=newArray[i].charAt(0);

		}//end for loop to set the first character of each index in the array to the corresponding index in a character array

		//a for loop to go through the array multiple times and check every combination of pairs if values
		for(int i=0; i<newArray.length+1; i++)
		{
			//a for loop to check the values of each index of the array 
			for (int j=0; j<newArray.length-1; j++)
			{
				//an if statement when the first character of current index/value is less than the next index's first character
				if(firstLetter[j]>firstLetter[j+1])
				{
					positionHolder=firstLetter[j];
					firstLetter[j]=firstLetter[j+1];
					firstLetter[j+1]=positionHolder;
				}//end if statement when the first character of current index/value is less than the next index's first character

				//an else if statement when the first character in both strings are equal
				else if(firstLetter[j]==firstLetter[j+1])
				{
					secondLetter[j]=newArray[j].charAt(1);
					secondLetter[j+1]=newArray[j+1].charAt(1);

					//an if statement when the second character of current index/value is less than the next index's second character
					if(secondLetter[j]>secondLetter[j+1])
					{
						positionHolder=firstLetter[j];
						firstLetter[j]=firstLetter[j+1];
						firstLetter[j+1]=positionHolder;
					}//end if statement when the second character of current index/value is less than the next index's second character

					//an else if statement when the second character in both strings are equal
					else if(secondLetter[j]==secondLetter[j+1])
					{
						thirdLetter[j]=newArray[j].charAt(2);
						thirdLetter[j+1]=newArray[j+1].charAt(2);

						//an if statement when the third character of current index/value is less than the next index's third character
						if(thirdLetter[j]>thirdLetter[j+1])
						{
							positionHolder=firstLetter[j];
							firstLetter[j]=firstLetter[j+1];
							firstLetter[j+1]=positionHolder;
						}//end if statement when the third character of current index/value is less than the next index's third character

						//an else if statement when the third character in both strings are equal
						else if(thirdLetter[j]==thirdLetter[j+1])
						{
							fourthLetter[j]=newArray[j].charAt(3);
							fourthLetter[j+1]=newArray[j+1].charAt(3);

							//an if statement when the fourth character of current index/value is less than the next index's fourth character
							if(fourthLetter[j]>fourthLetter[j+1])
							{
								positionHolder=firstLetter[j];
								firstLetter[j]=firstLetter[j+1];
								firstLetter[j+1]=positionHolder;
							}//end if statement when the fourth character of current index/value is less than the next index's fourth character
						}//end else if statement when the third character in both strings are equal
					}//end else if statement when the second character in both strings are equal
				}//end else if statement when the first character in both strings are equal
			}//end for loop to check the values of each index of the array 
		}//end for loop to go through the array multiple times and check every combination of pairs if values

		//returning the newly sorted array
		return newArray;

	}//end sortLowToHigh method
	
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: sortLowToHigh
	 * Description: Creates a new array where the values from the original array are sorted from the Lowest value to the Highest value.
	 * Parameters: double [] originalArray
	 * Returns: double [] newArray
	 */

	public static double[] sortLowToHigh(double [] originalArray)
	{
		//creating a new array that is a copy of the original array
		double[] newArray=Array.copyArray(originalArray);

		//a for loop to go through the array multiple times and check every combination of pairs if values
		for(int i=0; i<=newArray.length-1; i++)
		{
			//a for loop to check which value in the pair of indexes is smaller
			for(int j=0; j<=newArray.length-2; j++)
			{
				//an if statement to change the positions of the values of the pairs
				if(newArray[j]>newArray[j+1])
				{
					Array.swapElements(newArray, j, j+1);

				}//end if statement to change the positions of the values of the pairs

			}//end for loop to check which value in the pair of indexes is smaller

		}//end for loop to go through the array multiple times and check every combination of pairs if values

		//returning the newly sorted array
		return newArray;

	}//end sortLowToHigh method
	
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: sortLowToHigh
	 * Description: Creates a new array where the values from the original array are sorted from the Lowest value to the Highest value.
	 * Parameters: char [] originalArray
	 * Returns: char [] newArray
	 */

	public static char[] sortLowToHigh(char [] originalArray)
	{
		//creating a new array that is a copy of the original array
		char[] newArray=Array.copyArray(originalArray);

		//a for loop to go through the array multiple times and check every combination of pairs if values
		for(int i=0; i<=newArray.length-1; i++)
		{
			//a for loop to check which value in the pair of indexes is smaller
			for(int j=0; j<=newArray.length-2; j++)
			{
				//an if statement to change the positions of the values of the pairs
				if(newArray[j]>newArray[j+1])
				{
					Array.swapElements(newArray, j, j+1);

				}//end if statement to change the positions of the values of the pairs

			}//end for loop to check which value in the pair of indexes is smaller

		}//end for loop to go through the array multiple times and check every combination of pairs if values

		//returning the newly sorted array
		return newArray;

	}//end sortLowToHigh method

	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: reverse
	 * Description: Creates a copy of an array, then reverses the index values of all elements an the new array, and then prints all of the elements to screen.
	 * Parameters: int [] originalArray
	 * Returns: int [] newArray
	 */

	public static int [] reverse(int [] originalArray)
	{

		//creating a new array that is a copy of the original array
		int[] newArray = new int [originalArray.length];
		int total=originalArray.length-1;
	
		//a for loop to reinitialize all variables in the new array
		for(int i=0; i<newArray.length; i++)
		{
			newArray[total-i]=originalArray[i];
		
		}//end for loop to reinitialize all variables in the new array
		
		//returning the newly arranged array
		return newArray;
		
	}//end reverse method
	

	/*
	 * Method Author: Brooke Cronin
	 * Method Name: reverse
	 * Description: Creates a copy of an array, then reverses the index values of all elements an the new array, and then prints all of the elements to screen.
	 * Parameters: String [] originalArray
	 * Returns: String [] newArray
	 */

	public static String[] reverse(String[] originalArray)
	{

		//creating a new array that is a copy of the original array
		String[] newArray = new String [originalArray.length];
		int total=originalArray.length-1;
	
		//a for loop to reinitialize all variables in the new array
		for(int i=0; i<newArray.length; i++)
		{
			newArray[total-i]=originalArray[i];
		
		}//end for loop to reinitialize all variables in the new array
		
		//returning the newly arranged array
		return newArray;
		
	}//end reverse method
	
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: reverse
	 * Description: Creates a copy of an array, then reverses the index values of all elements an the new array, and then prints all of the elements to screen.
	 * Parameters: double [] originalArray
	 * Returns: double [] newArray
	 */

	public static double [] reverse(double [] originalArray)
	{

		//creating a new array that is a copy of the original array
		double[] newArray = new double [originalArray.length];
		int total=originalArray.length-1;
	
		//a for loop to reinitialize all variables in the new array
		for(int i=0; i<newArray.length; i++)
		{
			newArray[total-i]=originalArray[i];
		
		}//end for loop to reinitialize all variables in the new array
		
		//returning the newly arranged array
		return newArray;
		
	}//end reverse method
	
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: reverse
	 * Description: Creates a copy of an array, then reverses the index values of all elements an the new array, and then prints all of the elements to screen.
	 * Parameters: char [] originalArray
	 * Returns: char [] newArray
	 */

	public static char[] reverse(char [] originalArray)
	{

		//creating a new array that is a copy of the original array
		char[] newArray = new char [originalArray.length];
		int total=originalArray.length-1;
	
		//a for loop to reinitialize all variables in the new array
		for(int i=0; i<newArray.length; i++)
		{
			newArray[total-i]=originalArray[i];
		
		}//end for loop to reinitialize all variables in the new array
		
		//returning the newly arranged array
		return newArray;
		
	}//end reverse method

}//end class                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             