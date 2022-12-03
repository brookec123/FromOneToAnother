package finalProject;

import javafx.application.Application;
import javafx.stage.Stage;
import java.util.Random;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import javafx.scene.control.TextArea;

public class CheckJava extends Application {
	static String LESSON_HEADER_FONT = "50";
	static String TASK_HEADER_FONT = "40";
	static String TASK_INSTR_FONT = "25";
	static String LABEL_FONT = "20";
	static String CONTROL_FONT = "15";
	static String TEXTAREA_FONT = "15";

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/* Method Author: Brooke Cronin
	 * Method Name: submitL1
	 * Description: Modifies user input from the specified task in lesson 1 to remove all parts of it that does not include the intended output, then assigns and returns the output/error messages and text colour in an array for the current task.
	 * Parameters: TextArea taUserInputL1
	 * Returns: String [] userOutputL1
	 */

	public static String [] submitL1(TextArea taUserInputL1, String fontSize, String taskNum)
	{
		//creating and initializing the array to store the user's output and the text style
		String [] lineInput = splitInputByLine(taUserInputL1.getText(), 2, "L1T"+taskNum);
		String [] userOutputL1 = new String[2];
		userOutputL1[0]="";
		userOutputL1[1]="";
		
		if(lineInput[0].equals("not valid"))
		{
			userOutputL1[0]=addErrorMessage(userOutputL1[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL1[1]=outputFontFormat(userOutputL1[0], fontSize);

			//returning the array containing the text and style for the output text area in L1
			return userOutputL1;
		}
		
		//when the user input starts and ends with the appropriate code to be able to print words to the screen
		if(lineInput[0].startsWith("System.out.println(\"") && lineInput[0].endsWith("\");"))
		{
			int start=0;
			String initial=lineInput[0].substring(20, lineInput[0].length()-3);
			String beginning="";
			String end=initial;
			String output="";

			//for loop to find and determine what to do with each \ located in the user's code
			for(int i=0; i<initial.length()-1; i++)
			{
				//when the user forgot to add the \ before "
				if(initial.charAt(i)=='\"' && initial.charAt(i-1)!='\\')
				{
					userOutputL1[0]=addErrorMessage(userOutputL1[0], "You must add  a \\ before a \"");
				}//end if

				//when the user is trying to add a new line, tab, ", or \
				if(initial.charAt(i)=='\\')
				{
					//switch case to determine what the user's entered \ is supposed to do
					switch (initial.charAt(i+1))
					{
					case 'n':
						beginning=initial.substring(start, i);
						output+=beginning+"\n";
						end=initial.substring(i+2, initial.length());
						break;

					case '"':
						beginning=initial.substring(start, i);
						output+=beginning+"\"";
						end=initial.substring(i+2, initial.length());
						break;
					case 't':
						beginning=initial.substring(start, i);
						output+=beginning+"\t";
						end=initial.substring(i+2, initial.length());
						break;
					case '\\':
						beginning=initial.substring(start, i);
						output+=beginning+"\\";
						end=initial.substring(i+2, initial.length());
						break;
					default:
						userOutputL1[0]=addErrorMessage(userOutputL1[0], "You must add one of the following characters directly after a \\ n, t, \", or \\");
						break;
					};//end switch case

					//increasing the character location that the next substring should begin with
					start=i+2;
				}//end outside if
			}//end for

			//adding the rest of the user's intended output after all \ have been checked
			output+=end;

			//setting the output text for L1 to the output generated from the user's code if there is not already an error in code
			if(userOutputL1[0].equals(""))
			{
				userOutputL1[0]=output;
			}//end inside if
		}//end outside if

		//when user forgot part or all of the start/end code to be able to print words to the screen 
		else
		{
			userOutputL1[0]=addErrorMessage(userOutputL1[0], "Don't forget to begin your code with \nSystem.out.println(\"\n and end with \n\");");
		}//end else


		userOutputL1[1]=outputFontFormat(userOutputL1[0], fontSize);

		//returning the array containing the text and style for the output text area in the correct task
		return userOutputL1;
	}//end submitL1

	/* Method Author: Brooke Cronin
	 * Method Name: submitL2T1
	 * Description: Searches for what the user has assigned favNum to, makes sure that it is an integer, then assigns it to the output text area.
	 * Parameters: TextArea taUserInputL2T1
	 * Returns: String [] userOutputL2T1
	 */

	public static String [] submitL2T1(TextArea taUserInputL2T1, String fontSize)
	{
		//creating and initializing the array to store the user's output and the text style as well as all variables to be used in this method
		String [] lineInput = splitInputByLine(taUserInputL2T1.getText(), 3, "L2T1");
		String output="";
		boolean isInt;
		String [] userOutputL2T1=new String[2];
		userOutputL2T1[0]="";
		userOutputL2T1[1]="";

		if(lineInput[0].equals("not valid"))
		{
			userOutputL2T1[0]=addErrorMessage(userOutputL2T1[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL2T1[1]=outputFontFormat(userOutputL2T1[0], fontSize);

			//returning the array containing the text and style for the output text area in L2T1
			return userOutputL2T1;
		}
		
		if(!lineInput[1].equals("System.out.println(favNum); //this will print the value of favNum to the screen, do not modify this line of code"))
		{
			userOutputL2T1[0]=addErrorMessage(userOutputL2T1[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL2T1[1]=outputFontFormat(userOutputL2T1[0], fontSize);

			//returning the array containing the text and style for the output text area in L2T1
			return userOutputL2T1;
		}
			
		//when user's code starts with int favNum
		if(lineInput[0].startsWith("int favNum=") || lineInput[0].startsWith("int favNum ="))
		{
			//when user's code ends with a ;
			if(lineInput[0].endsWith(";"))
			{
				//a for loop to assign the value of output all of the integer numbers that the user assigned to the variable in their code
				for(int i=11; i<lineInput[0].length()-1; i++)
				{
					if(lineInput[0].charAt(i)!=' ' && lineInput[0].charAt(i)!='=')
					{
						output+=lineInput[0].charAt(i);
					}//end if
				}//end for	
			}//end second if

			//when user's code doesn't end with a ;
			else
			{
				userOutputL2T1[0]=addErrorMessage(userOutputL2T1[0], "You must add a ; at the end of your creation and initialization of the favNum variable.");
			}//end else
		}//end first if

		//when user's code doesn't start with int favNum= or int favNum =
		else
		{
			userOutputL2T1[0]=addErrorMessage(userOutputL2T1[0], "You must create an integer variable called favNum and set it equal to an integer value.");
		}//end else

		//when there has been no errors detected in the user's code yet
		if(userOutputL2T1[0].equals(""))
		{
			isInt=ErrorChecks.checkIfInt(output);

			//when the value of favNum isn't an integer
			if(!isInt)
			{
				userOutputL2T1[0]=addErrorMessage(userOutputL2T1[0], "You must assign favNum as an integer.");
			}//end inside if
		}//end outside if

		//when there has been no errors detected in the user's code yet
		if(userOutputL2T1[0].equals(""))
		{
			userOutputL2T1[0]=output;
		}//end if

		userOutputL2T1[1]=outputFontFormat(userOutputL2T1[0], fontSize);

		//returning the array containing the text and style for the output text area in L2T1
		return userOutputL2T1;
	}//end submitL2T1


	/* Method Author: Brooke Cronin
	 * Method Name: submitL2T2
	 * Description: Searches for what the user has assigned myAge to, makes sure that it is a double, then assigns it to the output text area.
	 * Parameters: TextArea taUserInputL2T2
	 * Returns: String [] userOutputL2T2
	 */

	public static String [] submitL2T2(TextArea taUserInputL2T2, String fontSize)
	{
		//creating and initializing the array to store the user's output and the text style as well as all variables to be used in this method
		String [] lineInput = splitInputByLine(taUserInputL2T2.getText(), 3, "L2T2");
		String output="";
		boolean isDouble;
		String [] userOutputL2T2=new String[2];
		userOutputL2T2[0]="";
		userOutputL2T2[1]="";

		if(lineInput[0].equals("not valid"))
		{
			userOutputL2T2[0]=addErrorMessage(userOutputL2T2[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL2T2[1]=outputFontFormat(userOutputL2T2[0], fontSize);

			//returning the array containing the text and style for the output text area in L2T2
			return userOutputL2T2;
		}
		
		if(!lineInput[0].equals("System.out.println(myAge); //this will print the value of myAge to the screen, do not modify this line of code"))
		{
			userOutputL2T2[0]=addErrorMessage(userOutputL2T2[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL2T2[1]=outputFontFormat(userOutputL2T2[0], fontSize);

			//returning the array containing the text and style for the output text area in L2T1
			return userOutputL2T2;
		}
		
		//when user's code starts with double myAge
		if(lineInput[0].startsWith("double myAge=") || lineInput[0].startsWith("double myAge ="))
		{
			//when user's code ends with a ;
			if(lineInput[0].endsWith(";"))
			{
				//a for loop to assign the value of output all of the double numbers that the user assigned to the variable in their code
				for(int i=13; i<lineInput[0].length()-1; i++)
				{
					if(lineInput[0].charAt(i)!=' ' && lineInput[0].charAt(i)!='=')
					{
						output+=lineInput[0].charAt(i);
					}//end if inside for loop
				}//end for	
			}//end second if

			//when user's code doesn't end with a ;
			else
			{
				userOutputL2T2[0]=addErrorMessage(userOutputL2T2[0], "You must add a ; at the end of your creation and initialization of the myAge variable.");
			}//end else
		}//end first if

		//when user's code doesn't start with double myAge
		else
		{
			userOutputL2T2[0]=addErrorMessage(userOutputL2T2[0], "You must create a double variable called myAge and set it equal to an double value.");
		}//end else

		//when there has been no errors detected in the user's code yet
		if(userOutputL2T2[0].equals(""))
		{

			isDouble=ErrorChecks.checkIfDouble(output);

			//when the value if myAge isn't a double
			if(!isDouble)
			{
				userOutputL2T2[0]=addErrorMessage(userOutputL2T2[0], "You must assign myAge as a double.");
			}//end if
		}//end if

		//when there has been no errors detected in the user's code yet
		if(userOutputL2T2[0].equals(""))
		{
			userOutputL2T2[0]=output;
		}//end if

		userOutputL2T2[1]=outputFontFormat(userOutputL2T2[0], fontSize);

		//returning the array containing the text and style for the output text area in L2T2
		return userOutputL2T2;
	}//end submitL2T2


	/* Method Author: Brooke Cronin
	 * Method Name: submitL2T3
	 * Description: Searches for what the user has assigned myFirstName to, then assigns it to the output text area.
	 * Parameters: TextArea taUserInputL2T3
	 * Returns: String [] userOutputL2T3
	 */

	public static String [] submitL2T3(TextArea taUserInputL2T3, String fontSize)
	{
		//creating and initializing the array to store the user's output and the text style as well as all variables to be used in this method
		String [] lineInput = splitInputByLine(taUserInputL2T3.getText(), 3, "L2T3");
		String output="";
		String [] userOutputL2T3=new String[2];
		userOutputL2T3[0]="";
		userOutputL2T3[1]="";

		if(lineInput[0].equals("not valid"))
		{
			userOutputL2T3[0]=addErrorMessage(userOutputL2T3[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL2T3[1]=outputFontFormat(userOutputL2T3[0], fontSize);

			//returning the array containing the text and style for the output text area in L2T3
			return userOutputL2T3;
		}
		
		if(!lineInput[1].equals("System.out.println(myFirstName); //this will print the value of myFirstName to the screen, do not modify this line of code"))
		{
			userOutputL2T3[0]=addErrorMessage(userOutputL2T3[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL2T3[1]=outputFontFormat(userOutputL2T3[0], fontSize);

			//returning the array containing the text and style for the output text area in L2T3
			return userOutputL2T3;
		}
		
		//when user's code starts with String myFirstName
		if(lineInput[0].startsWith("String myFirstName=") || lineInput[0].startsWith("String myFirstName ="))
		{
			//when user's code ends with a ;
			if(lineInput[0].endsWith(";"))
			{
				//a for loop to assign the value of output all of the String that the user assigned to the variable in their code
				for(int i=19; i<lineInput[0].length()-1; i++)
				{
					if(lineInput[0].charAt(i)!=' ' && lineInput[0].charAt(i)!='=')
					{
						output+=lineInput[0].charAt(i);
					}//end else
				}//end for	
			}//end second if

			//when user's code doesn't end with a ;
			else
			{
				userOutputL2T3[0]=addErrorMessage(userOutputL2T3[0], "You must add a ; at the end of your creation and initialization of the myFirstName variable.");
			}//end else
		}//end first if

		//when user's code doesn't start with String myFirstName
		else
		{
			userOutputL2T3[0]=addErrorMessage(userOutputL2T3[0], "You must create an String variable called myFirstName and set it equal to a String value.");
		}//end else

		//when there has been no errors detected in the user's code yet
		if(userOutputL2T3[0].equals(""))
		{
			//when the user has remembered to surround their assigned variable with ""
			if(output.startsWith("\"") && output.endsWith("\""))
			{
				userOutputL2T3[0]=output.substring(1, output.length()-1);
			}//end inside if
			else
			{
				userOutputL2T3[0]=addErrorMessage(userOutputL2T3[0], "You must surround your assigned value with \" \".");
			}//end inside else
		}//end outside if

		userOutputL2T3[1]=outputFontFormat(userOutputL2T3[0], fontSize);

		//returning the array containing the text and style for the output text area in L2T3
		return userOutputL2T3;
	}//end submitL2T3


	/* Method Author: Brooke Cronin
	 * Method Name: submitL2T4
	 * Description: Searches for what the user has assigned myLastInitial to, makes sure that it is one character in length only, then assigns it to the output text area.
	 * Parameters: TextArea taUserInputL2T4
	 * Returns: String [] userOutputL2T4
	 */

	public static String [] submitL2T4(TextArea taUserInputL2T4, String fontSize)
	{
		//creating and initializing the array to store the user's output and the text style as well as all variables to be used in this method
		String [] lineInput = splitInputByLine(taUserInputL2T4.getText(), 3, "L2T4");
		String output="";
		boolean isChar;
		String [] userOutputL2T4=new String[2];
		userOutputL2T4[0]="";
		userOutputL2T4[1]="";

		if(lineInput[0].equals("not valid"))
		{
			userOutputL2T4[0]=addErrorMessage(userOutputL2T4[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL2T4[1]=outputFontFormat(userOutputL2T4[0], fontSize);

			//returning the array containing the text and style for the output text area in L2T4
			return userOutputL2T4;
		}
		
		if(!lineInput[1].equals("System.out.println(myLastInitial); //this will print the value of myLastInitial to the screen, do not modify this line of code"))
		{
			userOutputL2T4[0]=addErrorMessage(userOutputL2T4[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL2T4[1]=outputFontFormat(userOutputL2T4[0], fontSize);

			//returning the array containing the text and style for the output text area in L2T4
			return userOutputL2T4;
		}
		
		//when user's code starts with char myLastInitial
		if(lineInput[0].startsWith("char myLastInitial=") || lineInput[0].startsWith("char myLastInitial ="))
		{
			//when user's code ends with a ;
			if(lineInput[0].endsWith(";"))
			{
				//a for loop to assign the value of output all of the character numbers that the user assigned to the variable in their code
				for(int i=19; i<lineInput[0].length()-1; i++)
				{
					if(lineInput[0].charAt(i)!=' ' && lineInput[0].charAt(i)!='=')
					{
						output+=lineInput[0].charAt(i);
					}//end else
				}//end for	
			}//end second if

			//when user's code doesn't end with a ;
			else
			{
				userOutputL2T4[0]=addErrorMessage(userOutputL2T4[0], "You must add a ; at the end of your creation and initialization of the myLastInitial variable.");
			}//end else
		}//end first if

		//when user's code doesn't start with char myLastInitial
		else
		{
			userOutputL2T4[0]=addErrorMessage(userOutputL2T4[0], "You must create an character variable called myLastInitial and set it equal to a character value.");
		}//end else

		//when the user has remembered to surround their assigned variable with ''
		if(output.startsWith("\'") && output.endsWith("\'"))
		{
			output=output.substring(1, output.length()-1);
		}//end if
		else
		{
			userOutputL2T4[0]=addErrorMessage(userOutputL2T4[0], "You must surround your assigned value with \' \'.");
		}//end inside else

		//when the value of myLast Initial is not a single character
		isChar=ErrorChecks.checkIfChar(output);
		if(isChar=false)
		{
			userOutputL2T4[0]=addErrorMessage(userOutputL2T4[0], "The value of myLastInitial can only contain one character.");
		}//end if

		//when there has been no errors detected in the user's code yet
		else if(userOutputL2T4[0].equals(""))
		{
			userOutputL2T4[0]=output;
		}//end if

		userOutputL2T4[1]=outputFontFormat(userOutputL2T4[0], fontSize);

		//returning the array containing the text and style for the output text area in L2T4
		return userOutputL2T4;
	}//end submitL2T4


	/* Method Author: Brooke Cronin
	 * Method Name: submitL2T5
	 * Description: Searches for what the user has assigned likesDogs to, makes sure that it is either true or false, then assigns it to the output text area.
	 * Parameters: TextArea taUserInputL2T5
	 * Returns: String [] userOutputL2T5
	 */

	public static String [] submitL2T5(TextArea taUserInputL2T5, String fontSize)
	{
		//creating and initializing the array to store the user's output and the text style as well as all variables to be used in this method
		String [] lineInput = splitInputByLine(taUserInputL2T5.getText(), 3, "L2T5");
		String output="";
		String [] userOutputL2T5=new String[2];
		userOutputL2T5[0]="";
		userOutputL2T5[1]="";

		if(lineInput[0].equals("not valid"))
		{
			userOutputL2T5[0]=addErrorMessage(userOutputL2T5[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL2T5[1]=outputFontFormat(userOutputL2T5[0], fontSize);

			//returning the array containing the text and style for the output text area in L2T5
			return userOutputL2T5;
		}
		
		if(!lineInput[1].equals("System.out.println(likesDogs); //this will print the value of likesDogs to the screen, do not modify this line of code"))
		{
			userOutputL2T5[0]=addErrorMessage(userOutputL2T5[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL2T5[1]=outputFontFormat(userOutputL2T5[0], fontSize);

			//returning the array containing the text and style for the output text area in L2T5
			return userOutputL2T5;
		}
		
		//when user's code starts with boolean likesDogs
		if(lineInput[0].startsWith("boolean likesDogs=") || lineInput[0].startsWith("boolean likesDogs ="))
		{
			//when user's code ends with a ;
			if(lineInput[0].endsWith(";"))
			{
				//a for loop to assign the value of output all of the boolean numbers that the user assigned to the variable in their code
				for(int i=17; i<lineInput[0].length()-1; i++)
				{
					if(lineInput[0].charAt(i)!=' ' && lineInput[0].charAt(i)!='=')
					{
						output+=lineInput[0].charAt(i);
					}//end else
				}//end for	
			}//end second if

			//when user's code doesn't end with a ;
			else
			{
				userOutputL2T5[0]=addErrorMessage(userOutputL2T5[0], "You must add a ; at the end of your creation and initialization of the likesDogs variable.");
			}//end else
		}//end first if

		//when user's code doesn't start with boolean likesDogs
		else
		{
			userOutputL2T5[0]=addErrorMessage(userOutputL2T5[0], "You must create an boolean variable called likesDogs and set it equal to a boolean value.");
		}//end else

		//when there has been no errors detected in the user's code yet
		if(userOutputL2T5[0].equals(""))
		{
			//when there has been no errors detected in the user's code yet
			if(output.equals("true") || output.equals("false"))
			{
				userOutputL2T5[0]=output;
			}//end second if

			//when the user has not assigned likesDogs to a boolean value
			else
			{
				userOutputL2T5[0]=addErrorMessage(userOutputL2T5[0], "You must assign likesDogs to either true or false (make sure that there are no capitals).");
			}//end else
		}//end first if

		userOutputL2T5[1]=outputFontFormat(userOutputL2T5[0], fontSize);

		//returning the array containing the text and style for the output text area in L2T5
		return userOutputL2T5;
	}//end submitL2T5


	/* Method Author: Brooke Cronin
	 * Method Name: submitL2T6
	 * Description: Modifies user input to remove all parts of it that does not include the intended output, replaces the user's inputed variable names with the inputed value from previous tasks then assigns the result to the output text area.
	 * Parameters: TextArea taUserInputL2T6
	 * Returns: String [] userOutputL2T6
	 */

	public static String [] submitL2T6(TextArea taUserInputL2T6, TextArea taUserOutputL2T1, TextArea taUserOutputL2T2, TextArea taUserOutputL2T3, TextArea taUserOutputL2T4, TextArea taUserOutputL2T5, String fontSize)
	{
		//creating and initializing the array to store the user's output and the text style as well as all variables to be used in this method
		String [] lineInput = splitInputByLine(taUserInputL2T6.getText(), 2, "L2T6");
		int start=0;
		String initial=lineInput[0].substring(20, lineInput[0].length()-3);
		String beginning="";
		String end=initial;
		String output="";
		String [] userOutputL2T6=new String[2];
		userOutputL2T6[0]="";
		userOutputL2T6[1]="";

		if(lineInput[0].equals("not valid"))
		{
			userOutputL2T6[0]=addErrorMessage(userOutputL2T6[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL2T6[1]=outputFontFormat(userOutputL2T6[0], fontSize);

			//returning the array containing the text and style for the output text area in L2T6
			return userOutputL2T6;
		}
		
		//when the user input starts and ends with the appropriate code to be able to print words to the screen
		if(lineInput[0].startsWith("System.out.println(\"") && lineInput[0].endsWith("\");"))
		{
			//when user has not selected an option from one or more of the drop down menus in this task
			if(taUserOutputL2T1.getText().equals("Not submitted yet") || taUserOutputL2T2.getText().equals("Not submitted yet") || taUserOutputL2T3.getText().equals("Not submitted yet") || taUserOutputL2T4.getText().equals("Not submitted yet") || taUserOutputL2T5.getText().equals("Not submitted yet"))
			{
				userOutputL2T6[0]=addErrorMessage(userOutputL2T6[0], "You have not yet submitted anything for the above tasks. Please complete them and try again");
			}//end if

			//for loop to edit the user's input so that the output will appear like it should on the console to the screen
			for(int i=0; i<initial.length()-1; i++)
			{
				//when the user has added a variable in their code and switching it with the value they entered in the previous tasks
				if(initial.charAt(i)=='+' && initial.charAt(i-1)=='\"')
				{
					if(initial.charAt(i+1)=='f' && initial.charAt(i+2)=='a' && initial.charAt(i+3)=='v' && initial.charAt(i+4)=='N' && initial.charAt(i+5)=='u' && initial.charAt(i+6)=='m' && initial.charAt(i+7)=='+' && initial.charAt(i+8)=='\"')
					{
						beginning=initial.substring(start, i-1);
						output+=beginning+taUserOutputL2T1.getText();
						end=initial.substring(i+9, initial.length());
						start=i+9;
					}//end inside if
					else if(initial.charAt(i+1)=='m' && initial.charAt(i+2)=='y' && initial.charAt(i+3)=='A' && initial.charAt(i+4)=='g' && initial.charAt(i+5)=='e' && initial.charAt(i+6)=='+' && initial.charAt(i+7)=='\"')
					{
						beginning=initial.substring(start, i-1);
						output+=beginning+taUserOutputL2T2.getText();
						end=initial.substring(i+8, initial.length());
						start=i+8;
					}//end inside else if
					else if(initial.charAt(i+1)=='m' && initial.charAt(i+2)=='y' && initial.charAt(i+3)=='F' && initial.charAt(i+4)=='i' && initial.charAt(i+5)=='r' && initial.charAt(i+6)=='s' && initial.charAt(i+7)=='t' && initial.charAt(i+8)=='N' && initial.charAt(i+9)=='a' && initial.charAt(i+10)=='m' && initial.charAt(i+11)=='e' && initial.charAt(i+12)=='+' && initial.charAt(i+13)=='\"')
					{
						beginning=initial.substring(start, i-1);
						output+=beginning+taUserOutputL2T3.getText();
						end=initial.substring(i+14, initial.length());
						start=i+14;
					}//end inside else if
					else if(initial.charAt(i+1)=='m' && initial.charAt(i+2)=='y' && initial.charAt(i+3)=='L' && initial.charAt(i+4)=='a' && initial.charAt(i+5)=='s' && initial.charAt(i+6)=='t' && initial.charAt(i+7)=='I' && initial.charAt(i+8)=='n' && initial.charAt(i+9)=='i' && initial.charAt(i+10)=='t' && initial.charAt(i+11)=='i' && initial.charAt(i+12)=='a' && initial.charAt(i+13)=='l' && initial.charAt(i+14)=='+' && initial.charAt(i+15)=='\"')
					{
						beginning=initial.substring(start, i-1);
						output+=beginning+taUserOutputL2T4.getText();
						end=initial.substring(i+16, initial.length());
						start=i+16;
					}//end inside else if
					else if(initial.charAt(i+1)=='l' && initial.charAt(i+2)=='i' && initial.charAt(i+3)=='k' && initial.charAt(i+4)=='e' && initial.charAt(i+5)=='s' && initial.charAt(i+6)=='D' && initial.charAt(i+7)=='o' && initial.charAt(i+8)=='g' && initial.charAt(i+9)=='s' && initial.charAt(i+10)=='+' && initial.charAt(i+11)=='\"')
					{
						beginning=initial.substring(start, i-1);
						output+=beginning+taUserOutputL2T5.getText();
						end=initial.substring(i+12, initial.length());
						start=i+12;
					}//end inside else if
					else
					{
						userOutputL2T6[0]=addErrorMessage(userOutputL2T6[0], "You have tried to print the value of a variable that you have not yet created.");
					}
				}//end outside if

				//when the user is trying to add a new line, tab, ", or \
				if(initial.charAt(i)=='\\')
				{
					//switch case to determine what the user's entered \ is supposed to do
					switch (initial.charAt(i+1))
					{
					case 'n':
						beginning=initial.substring(start, i);
						output+=beginning+"\n";
						end=initial.substring(i+2, initial.length());
						start=i+2;
						break;

					case '"':
						if(initial.charAt(i+2)!='+' && initial.charAt(i-1)!='+')
						{
							beginning=initial.substring(start, i);
							output+=beginning+"\"";
							end=initial.substring(i+2, initial.length());
							start=i+2;
						}
						break;
					case 't':
						beginning=initial.substring(start, i);
						output+=beginning+"\t";
						end=initial.substring(i+2, initial.length());
						start=i+2;
						break;
					case '\\':
						beginning=initial.substring(start, i);
						output+=beginning+"\\";
						end=initial.substring(i+2, initial.length());
						start=i+2;
						break;
					default:
						userOutputL2T6[0]=addErrorMessage(userOutputL2T6[0], "You must add one of the following characters directly after a \\\n n, t, \", or \\");
					};//end switch case

				}//end if
			}//end for
			output+=end;

			//set the output text area for L2T6 to the output generated from the user's code if there is not already an error in code
			if(userOutputL2T6[0].equals(""))
			{
				userOutputL2T6[0]=output;
			}//end inside if
		}//end outside if

		//when user forgot part or all of the start/end code to be able to print words to the screen 
		else
		{
			userOutputL2T6[0]=addErrorMessage(userOutputL2T6[0], "Don't forget to begin your code with \nSystem.out.println(\"\n and end with \n\");");
		}//end else

		userOutputL2T6[1]=outputFontFormat(userOutputL2T6[0], fontSize);

		//returning the array containing the text and style for the output text area in L2T6
		return userOutputL2T6;
	}


	/* Method Author: Brooke Cronin
	 * Method Name: submitL3T1
	 * Description: Breaks apart user's input by line, checks to make sure all variables are created and initialized correctly, checks that user printed the result variable to screen, and prints all errors found in user's code or the value of result;
	 * Parameters: TextArea taUserInputL3T1
	 * Returns: String [] userOutputL3T1
	 */

	public static String [] submitL3T1(TextArea taUserInputL3T1, String fontSize)
	{
		//creating all arrays and variables required for this method
		String [] lineInput = splitInputByLine(taUserInputL3T1.getText(), 5, "L3T1");
		String [] userOutputL3T1=new String[2];
		userOutputL3T1[0]="";
		userOutputL3T1[1]="";
		String wholeNum1="";
		String wholeNum2="";
		boolean isInt1;
		boolean isInt2;
		
		if(lineInput[0].equals("not valid"))
		{
			userOutputL3T1[0]=addErrorMessage(userOutputL3T1[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL3T1[1]=outputFontFormat(userOutputL3T1[0], fontSize);

			//returning the array containing the text and style for the output text area in L3T1
			return userOutputL3T1;
		}
		
		//when user's code is more or less than 4 lines
		if(lineInput[4].startsWith("Your code should contain exactly"))
		{
			userOutputL3T1[0]=addErrorMessage(userOutputL3T1[0],lineInput[4]);
		}

		//checking user's creation and initialization of wholeNum
		if(lineInput[0].startsWith("int wholeNum=") || lineInput[0].startsWith("int wholeNum ="))
		{
			if(lineInput[0].endsWith(";"))
			{
				for(int i=13; i<lineInput[0].length()-1; i++)
				{
					if(!(lineInput[0].charAt(i)==' ') && !(lineInput[0].charAt(i)=='='))
					{
						wholeNum1+=lineInput[0].charAt(i);
					}//end if inside for loop
				}//end for loop

				isInt1=ErrorChecks.checkIfInt(wholeNum1);

				//when the value of num1 is not an integer
				if(!isInt1)
				{
					userOutputL3T1[0]=addErrorMessage(userOutputL3T1[0], "You must initialize wholeNum as an integer.");
				}//end if
				else
				{
					//when the user has not properly printed the value of wholeNum to the screen
					if(!lineInput[1].equals("System.out.println(wholeNum);"))
					{
						userOutputL3T1[0]=addErrorMessage(userOutputL3T1[0], "Don't forget to print wholeNum to the screen after creating and initializing the variable.");
					}//end if
				}//end else
			}//end inside if

			//when user has not created/initialized the variable wholeNum properly
			else
			{
				userOutputL3T1[0]=addErrorMessage(userOutputL3T1[0], "Don't forget to begin your first line of code with int wholeNum = (or int wholeNum=) and end with ;");
			}//end inside else
		}//end outside if

		//when user has not created/initialized the variable wholeNum properly
		else
		{
			userOutputL3T1[0]=addErrorMessage(userOutputL3T1[0], "Don't forget to begin your first line of code with int wholeNum = (or int wholeNum=) and end with ;");
		}//end outside else

		//checking user's reinitialization of wholeNum
		if(lineInput[2].startsWith("wholeNum=") || lineInput[2].startsWith("wholeNum ="))
		{
			if(lineInput[2].endsWith(";"))
			{
				for(int i=8; i<lineInput[2].length()-1; i++)
				{
					if(!(lineInput[2].charAt(i)==' ') && !(lineInput[2].charAt(i)=='='))
					{
						wholeNum2+=lineInput[2].charAt(i);
					}//end if inside for loop
				}

				isInt2=ErrorChecks.checkIfInt(wholeNum2);

				//when the value of wholeNum2 is not an integer
				if(!isInt2)
				{
					userOutputL3T1[0]=addErrorMessage(userOutputL3T1[0], "You must reinitialize wholeNum as an integer.");
				}//end if

				//checking for any errors in the user's last line of code, if no errors have been found in user's code, the intended output will be assigned to userOutputL3T1[0]
				else
				{		
					if(lineInput[3].equals("System.out.println(wholeNum);") && userOutputL3T1[0].equals("") && !wholeNum1.equals(wholeNum2))
					{
						userOutputL3T1[0]=wholeNum1+"\n"+wholeNum2; 
					}//end if
					else if(wholeNum1.equals(wholeNum2))
					{
						userOutputL3T1[0]=addErrorMessage(userOutputL3T1[0], "You must change the value of wholeNum to a different integer on the second line of your code.");
					}//end else if
					else if (!lineInput[3].equals("System.out.println(wholeNum);"))
					{
						userOutputL3T1[0]=addErrorMessage(userOutputL3T1[0], "Don't forget to print wholeNum to the screen after creating and initializing the variable.");
					}//end else if
					else if(userOutputL3T1[0].contains("Don't forget to begin your third line of code with int wholeNum = (or int wholeNum=) and end with ;")) 
					{
						userOutputL3T1[0]=addErrorMessage(userOutputL3T1[0], "Since you didn't create the variable wholeNum properly, you can not print it to the screen.");
					}//end else if
				}//end else
			}//end inside if

			//when user has not reinitialized the variable wholeNum properly
			else
			{
				userOutputL3T1[0]=addErrorMessage(userOutputL3T1[0], "Don't forget to begin your third line of code with wholeNum = (or wholeNum=) and end with ;");
			}//end inside else
		}//end outside if

		//when user has not reinitialized the variable wholeNum properly
		else
		{
			userOutputL3T1[0]=addErrorMessage(userOutputL3T1[0], "Don't forget to begin your third line of code with wholeNum = (or wholeNum=) and end with ;");
		}//end outside else

		userOutputL3T1[1]=outputFontFormat(userOutputL3T1[0], fontSize);

		//returning the array containing the text and style for the output text area in the correct task
		return userOutputL3T1;		
	}//end submitL3T1 method


	/* Method Author: Brooke Cronin
	 * Method Name: submitL3T2
	 * Description: Breaks apart user's input by line, checks to make sure all variables are created and initialized correctly, checks that user printed the result variable to screen, and prints all errors found in user's code or the value of result;
	 * Parameters: TextArea taUserInputL3T2
	 * Returns: String [] userOutputL3T2
	 */

	public static String [] submitL3T2(TextArea taUserInputL3T2, String fontSize)
	{
		//creating all arrays and variables required for this method
		String [] lineInput = splitInputByLine(taUserInputL3T2.getText(), 5, "L3T2");
		String [] userOutputL3T2=new String[2];
		userOutputL3T2[0]="";
		userOutputL3T2[1]="";
		String decimalNum1="";
		String decimalNum2="";
		boolean isDouble1;
		boolean isDouble2;

		if(lineInput[0].equals("not valid"))
		{
			userOutputL3T2[0]=addErrorMessage(userOutputL3T2[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL3T2[1]=outputFontFormat(userOutputL3T2[0], fontSize);

			//returning the array containing the text and style for the output text area in L3T2
			return userOutputL3T2;
		}
		
		//when user's code is more or less than 4 lines
		if(lineInput[4].startsWith("Your code should contain exactly"))
		{
			userOutputL3T2[0]=addErrorMessage(userOutputL3T2[0],lineInput[4]);
		}

		//checking user's creation and initialization of decimalNuNum
		if(lineInput[0].startsWith("double decimalNum=") || lineInput[0].startsWith("double decimalNum ="))
		{
			if(lineInput[0].endsWith(";"))
			{
				for(int i=17; i<lineInput[0].length()-1; i++)
				{
					if(!(lineInput[0].charAt(i)==' ') && !(lineInput[0].charAt(i)=='='))
					{
						decimalNum1+=lineInput[0].charAt(i);
					}//end if inside for loop
				}//end for loop

				isDouble1=ErrorChecks.checkIfDouble(decimalNum1);

				//when the value of decimalNum1 is not a double
				if(!isDouble1)
				{
					userOutputL3T2[0]=addErrorMessage(userOutputL3T2[0], "You must initialize decimalNum as a double.");
				}//end if

				else
				{
					if(!lineInput[1].equals("System.out.println(decimalNum);"))
					{
						userOutputL3T2[0]=addErrorMessage(userOutputL3T2[0], "Don't forget to print decimalNum to the screen after creating and initializing the variable.");
					}//end inside if
				}//end else
			}//end inside if
		}//end outside if

		else
		{
			userOutputL3T2[0]=addErrorMessage(userOutputL3T2[0], "Don't forget to begin your first line of code with \ndouble decimalNum = (or double decimalNum=)\n and end with ;");
		}//end outside else

		//checking user's creation and initialization of wholeNum
		if(lineInput[2].startsWith("decimalNum=") || lineInput[2].startsWith("decimalNum ="))
		{
			if(lineInput[2].endsWith(";"))
			{
				for(int i=10; i<lineInput[2].length()-1; i++)
				{
					if(!(lineInput[2].charAt(i)==' ') && !(lineInput[2].charAt(i)=='='))
					{
						decimalNum2+=lineInput[2].charAt(i);
					}//end if inside for loop
				}//end for loop

				isDouble2=ErrorChecks.checkIfDouble(decimalNum2);

				//when the value of decimalNum2 is not a double
				if(!isDouble2)
				{
					userOutputL3T2[0]=addErrorMessage(userOutputL3T2[0], "You must reinitialize decimalNum as a double.");
				}//end if

				else
				{	
					//checking if there are any errors in user's code and assigning userOutputL3T2[0] the correct output or adding the right error statements
					if(lineInput[3].equals("System.out.println(decimalNum);") && !decimalNum1.equals(decimalNum2))
					{
						userOutputL3T2[0]=decimalNum1+"\n"+decimalNum2; 
					}//end if
					else if(decimalNum1.equals(decimalNum2))
					{
						userOutputL3T2[0]=addErrorMessage(userOutputL3T2[0], "You must change the value of decimalNum to a different double value on the second line of your code.");
					}//end else if
					else if (!lineInput[3].equals("System.out.println(decimalNum);"))
					{
						userOutputL3T2[0]=addErrorMessage(userOutputL3T2[0], "Don't forget to print decimalNum to the screen after creating and initializing the variable.");
					}//end else if
					else if(userOutputL3T2[0].contains("Don't forget to begin your first line of code with \ndouble decimalNum = (or double decimalNum=)\n and end with ;")) 
					{
						userOutputL3T2[0]=addErrorMessage(userOutputL3T2[0], "Since you didn't create the variable decimalNum properly, you can not print it to the screen.");
					}//end else if
				}//end else
			}//end inside if
		}//end outside if

		else
		{
			userOutputL3T2[0]=addErrorMessage(userOutputL3T2[0], "Don't forget to begin your third line of code with \ndecimalNum = (or decimalNum=)\n and end with ;");
		}//end outside else

		userOutputL3T2[1]=outputFontFormat(userOutputL3T2[0], fontSize);

		//returning the array containing the text and style for the output text area in the correct task
		return userOutputL3T2;	
	}//end submitL3T2 method


	/* Method Author: Brooke Cronin
	 * Method Name: submitL3T3
	 * Description: Breaks apart user's input by line, checks to make sure all variables are created and initialized correctly, checks that user printed the result variable to screen, and prints all errors found in user's code or the value of result;
	 * Parameters: TextArea taUserInputL3T3
	 * Returns: String [] userOutputL3T3
	 */

	public static String [] submitL3T3(TextArea taUserInputL3T3, String fontSize)
	{
		//creating all arrays and variables required for this method
		String [] lineInput = splitInputByLine(taUserInputL3T3.getText(), 5, "L3T3");
		String [] userOutputL3T3=new String[2];
		userOutputL3T3[0]="";
		userOutputL3T3[1]="";
		String str1="";
		String str2="";

		if(lineInput[0].equals("not valid"))
		{
			userOutputL3T3[0]=addErrorMessage(userOutputL3T3[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL3T3[1]=outputFontFormat(userOutputL3T3[0], fontSize);

			//returning the array containing the text and style for the output text area in L3T3
			return userOutputL3T3;
		}
		
		//when user's code is more or less than 4 lines
		if(lineInput[4].startsWith("Your code should contain exactly"))
		{
			userOutputL3T3[0]=addErrorMessage(userOutputL3T3[0],lineInput[4]);
		}

		//checking user's creation and initialization of decimalNuNum
		if(lineInput[0].startsWith("String str=\"") || lineInput[0].startsWith("String str = \"") || lineInput[0].startsWith("String str= \"") || lineInput[0].startsWith("String str =\""))
		{
			if(lineInput[0].endsWith("\";"))
			{
				for(int i=11; i<lineInput[0].length()-1; i++)
				{
					if(lineInput[0].charAt(i)==' ' && lineInput[0].charAt(i-1)=='=')
					{

					}//end if inside for loop
					else if(lineInput[0].charAt(i)==' ' && lineInput[0].charAt(i+1)=='=')
					{

					}//end else if inside for loop
					else if(lineInput[0].charAt(i)!='\"')
					{
						str1+=lineInput[0].charAt(i);
					}//end else if inside for loop
				}//end for loop
			}//end inside if
		}//end outside if

		else
		{
			userOutputL3T3[0]=addErrorMessage(userOutputL3T3[0], "Don't forget to begin your first line of code with \nString str = (or String str=), surround the value with \"\", and end with ;");
		}//end outside else

		//checking user's creation and initialization of str
		if(lineInput[2].startsWith("str=\"") || lineInput[2].startsWith("str = \"") || lineInput[2].startsWith("str= \"") || lineInput[2].startsWith("str =\""))
		{
			if(lineInput[2].endsWith("\";"))
			{
				for(int i=4; i<lineInput[2].length()-1; i++)
				{
					if(lineInput[2].charAt(i)==' ' && lineInput[2].charAt(i-1)=='=')
					{

					}//end if inside for loop
					else if(lineInput[2].charAt(i)==' ' && lineInput[2].charAt(i+1)=='=')
					{

					}//end else if inside for loop
					else if(!(lineInput[2].charAt(i)=='\"'))
					{
						str2+=lineInput[2].charAt(i);
					}//end else if inside for loop
				}//end for loop

				//checking if there are any errors in user's code and assigning userOutputL3T3[0] the correct output or adding the right error statements
				if(lineInput[3].equals("System.out.println(str);") && !str1.equals(str2) && !userOutputL3T3[0].startsWith("Error!"))
				{
					userOutputL3T3[0]=str1+"\n"+str2; 
				}//end if
				else if(str1.equals(str2))
				{
					userOutputL3T3[0]=addErrorMessage(userOutputL3T3[0], "You must change the value of str to a different String value on the second line of your code.");
				}//end else if
				else if(!lineInput[3].equals("System.out.println(str);"))
				{
					userOutputL3T3[0]=addErrorMessage(userOutputL3T3[0], "Don't forget to print str to the screen after creating and initializing the variable.");
				}//end else if
				else if(userOutputL3T3[0].contains("Don't forget to begin your first line of code with \nString str = (or String str=), surround the value with \"\", and end with ;")) 
				{
					userOutputL3T3[0]=addErrorMessage(userOutputL3T3[0], "Since you didn't create the variable str properly, you can not print it to the screen.");
				}//end else if
			}//end inside if
		}//end outside if

		else
		{
			userOutputL3T3[0]=addErrorMessage(userOutputL3T3[0], "Don't forget to begin your third line of code with \nstr = (or str=), surround the value with \"\", and end with ;");
		}//end outside else

		userOutputL3T3[1]=outputFontFormat(userOutputL3T3[0], fontSize);

		//returning the array containing the text and style for the output text area in the correct task
		return userOutputL3T3;	
	}//end submitL3T3 method


	/* Method Author: Brooke Cronin
	 * Method Name: submitL3T4
	 * Description: Breaks apart user's input by line, checks to make sure all variables are created and initialized correctly, checks that user printed the result variable to screen, and prints all errors found in user's code or the value of result;
	 * Parameters: TextArea taUserInputL3T4
	 * Returns: String [] userOutputL3T4
	 */

	public static String [] submitL3T4(TextArea taUserInputL3T4, String fontSize)
	{
		//creating all arrays and variables required for this method
		String [] lineInput = splitInputByLine(taUserInputL3T4.getText(), 5, "L3T4");
		String [] userOutputL3T4=new String[2];
		userOutputL3T4[0]="";
		userOutputL3T4[1]="";
		String singleCharacter1="";
		String singleCharacter2="";
		boolean isChar1;
		boolean isChar2;

		if(lineInput[0].equals("not valid"))
		{
			userOutputL3T4[0]=addErrorMessage(userOutputL3T4[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL3T4[1]=outputFontFormat(userOutputL3T4[0], fontSize);

			//returning the array containing the text and style for the output text area in L3T4
			return userOutputL3T4;
		}
		
		//when user's code is more or less than 4 lines
		if(lineInput[4].startsWith("Your code should contain exactly"))
		{
			userOutputL3T4[0]=addErrorMessage(userOutputL3T4[0],lineInput[4]);
		}

		//checking user's creation and initialization of decimalNuNum
		if(lineInput[0].startsWith("char singleCharacter='") || lineInput[0].startsWith("char singleCharacter = '") || lineInput[0].startsWith("char singleCharacter= '") || lineInput[0].startsWith("char singleCharacter ='"))
		{

			if(lineInput[0].endsWith("';"))
			{
				for(int i=20; i<lineInput[0].length()-1; i++)
				{
					if(!(lineInput[0].charAt(i)==' ') && !(lineInput[0].charAt(i)=='=') && !(lineInput[0].charAt(i)=='\''))
					{
						singleCharacter1+=lineInput[0].charAt(i);
					}//end if inside for loop
				}//end for loop
				System.out.println(singleCharacter1);
				isChar1=ErrorChecks.checkIfChar(singleCharacter1);

				//when the value of singleCharacter1 is not a character
				if(!isChar1)
				{
					userOutputL3T4[0]=addErrorMessage(userOutputL3T4[0], "You must initialize singleCharacter as a char.");
				}//end if

				else
				{
					if(!lineInput[1].equals("System.out.println(singleCharacter);"))
					{
						userOutputL3T4[0]=addErrorMessage(userOutputL3T4[0], "Don't forget to print singleCharacter to the screen after creating and initializing the variable.");
					}//end if
				}//end else
			}//end inside if
		}//end outside if

		else
		{
			userOutputL3T4[0]=addErrorMessage(userOutputL3T4[0], "Don't forget to begin your first line of code with \nchar singleCharacter = (or char singleCharacter=), surround the value with '', and end with ;");
		}//end outside else

		//checking user's creation and initialization of wholeNum
		if(lineInput[2].startsWith("singleCharacter='") || lineInput[2].startsWith("singleCharacter = '") || lineInput[2].startsWith("singleCharacter= '") || lineInput[2].startsWith("singleCharacter ='"))
		{
			if(lineInput[2].endsWith("';"))
			{
				for(int i=16; i<lineInput[2].length()-1; i++)
				{
					if(!(lineInput[2].charAt(i)==' ') && !(lineInput[2].charAt(i)=='=') && !(lineInput[2].charAt(i)=='\''))
					{
						singleCharacter2+=lineInput[2].charAt(i);
					}//end if inside for loop
				}//end for loop
				System.out.println(singleCharacter2);
				isChar2=ErrorChecks.checkIfChar(singleCharacter2);

				//when the value of singleCharacter2 is not a character
				if(!isChar2)
				{
					userOutputL3T4[0]=addErrorMessage(userOutputL3T4[0], "You must reinitialize singleCharacter as a char.");
				}//end if

				else
				{	
					//checking for any errors in the user's last line of code, if no errors have been found in user's code, the intended output will be assigned to userOutputL3T1[0]
					if(lineInput[3].equals("System.out.println(singleCharacter);") && !singleCharacter1.equals(singleCharacter2))
					{
						userOutputL3T4[0]=singleCharacter1+"\n"+singleCharacter2; 
					}//end if
					else if(singleCharacter1.equals(singleCharacter2))
					{
						userOutputL3T4[0]=addErrorMessage(userOutputL3T4[0], "You must change the value of singleCharacter to a different char value on the second line of your code.");
					}//end else if
					else if (!lineInput[3].equals("System.out.println(singleCharacter);"))
					{
						userOutputL3T4[0]=addErrorMessage(userOutputL3T4[0], "Don't forget to print singleCharacter to the screen after creating and initializing the variable.");
					}//end else if
					else if(userOutputL3T4[0].contains("Don't forget to begin your first line of code with \nchar singleCharacter = (or char singleCharacter=), surround the value with '', and end with ;")) 
					{
						userOutputL3T4[0]=addErrorMessage(userOutputL3T4[0], "Since you didn't create the variable singleCharacter properly, you can not print it to the screen.");
					}//end else if

				}//end else
			}//end inside if
		}//end outside if

		else
		{
			userOutputL3T4[0]=addErrorMessage(userOutputL3T4[0], "Don't forget to begin your third line of code with \nsingleCharacter = (or singleCharacter=), surround the value with '', and end with ;");
		}//end outside else

		userOutputL3T4[1]=outputFontFormat(userOutputL3T4[0], fontSize);

		//returning the array containing the text and style for the output text area in the correct task
		return userOutputL3T4;	
	}//end submitL3T4 method
	
	
	/* Method Author: Brooke Cronin
	 * Method Name: submitL4T1
	 * Description: Breaks apart user's input by line, checks to make sure all variables are created and initialized correctly, checks that user printed the result variable to screen, and prints all errors found in user's code or the value of result;
	 * Parameters: TextArea taUserInputL4T1
	 * Returns: String [] userOutputL4T1
	 * Throws: ScriptException
	 */

	public static String [] submitL4T1(TextArea taUserInputL4T1, String fontSize) throws ScriptException
	{
		String [] lineInput = splitInputByLine(taUserInputL4T1.getText(), 2, "L4T1");
		String expression=lineInput[0].substring(19, lineInput[0].length()-2);
		String [] userOutputL4T1=new String[2];
		userOutputL4T1[0]="";
		userOutputL4T1[1]="";

		if(lineInput[0].equals("not valid"))
		{
			userOutputL4T1[0]=addErrorMessage(userOutputL4T1[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL4T1[1]=outputFontFormat(userOutputL4T1[0], fontSize);

			//returning the array containing the text and style for the output text area in L4T1
			return userOutputL4T1;
		}
		
		//when the user input starts and ends with the appropriate code to be able to print words to the screen
		if(lineInput[0].startsWith("System.out.println(") && lineInput[0].endsWith(");"))
		{
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("js");
			Object result = engine.eval(expression);
			userOutputL4T1[0]=String.valueOf(result);
		}//end if

		//when user forgot part or all of the start/end code to be able to print words to the screen 
		else
		{
			userOutputL4T1[0]=addErrorMessage(userOutputL4T1[0], "Don't forget to begin your code with \nSystem.out.println(\n and end with \n);");
		}//end else

		if(userOutputL4T1[0].equals(""))
		{
			userOutputL4T1[0]=addErrorMessage(userOutputL4T1[0], "Check your math problem to make sure that it is correct/makes sense");
		}//end if

		userOutputL4T1[1]=outputFontFormat(userOutputL4T1[0], fontSize);

		//returning the array containing the text and style for the output text area in the correct task
		return userOutputL4T1;
	}//end submitL4T1 method


	/* Method Author: Brooke Cronin
	 * Method Name: submitL4T2
	 * Description: Breaks apart user's input by line, checks to make sure all variables are created and initialized correctly, checks that user printed the result variable to screen, and prints all errors found in user's code or the value of result;
	 * Parameters: TextArea taUserInputL4T2
	 * Returns: String [] userOutputL4T2
	 */

	public static String [] submitL4T2(TextArea taUserInputL4T2, String fontSize)
	{
		String [] lineInput = splitInputByLine(taUserInputL4T2.getText(), 6, "L4T2");
		String string1="";
		String string2="";
		int iNum1=0;
		int iNum2=0;
		int iResult=0;
		boolean isInt1;
		boolean isInt2;
		
		String [] userOutputL4T2=new String[2];
		userOutputL4T2[0]="";
		userOutputL4T2[1]="";

		if(lineInput[0].equals("not valid"))
		{
			userOutputL4T2[0]=addErrorMessage(userOutputL4T2[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL4T2[1]=outputFontFormat(userOutputL4T2[0], fontSize);

			//returning the array containing the text and style for the output text area in L4T2
			return userOutputL4T2;
		}
		
		if(lineInput[5].startsWith("Your code should contain exactly"))
		{
			userOutputL4T2[0]=addErrorMessage(userOutputL4T2[0],lineInput[5]);
		}

		if(lineInput[0].startsWith("int iNum1 =") || lineInput[0].startsWith("int iNum1="))
		{
			if(lineInput[0].endsWith(";"))
			{
				//a for loop to assign the value of output all of the integer numbers that the user assigned to the variable in their code
				for(int i=9; i<lineInput[0].length()-1; i++)
				{
					if(!(lineInput[0].charAt(i)==' ') && !(lineInput[0].charAt(i)=='='))
					{
						string1+=lineInput[0].charAt(i);
					}//end if inside for loop
				}//end for

				isInt1=ErrorChecks.checkIfInt(string1);

				//when the value of iNum1 is not an integer
				if(!isInt1)
				{
					userOutputL4T2[0]=addErrorMessage(userOutputL4T2[0], "You must assign iNum1 as an integer.");
				}//end third if
				else
				{
					iNum1=Integer.parseInt(string1);
				}//end else
			}//end second if
		}//end first if

		else
		{
			userOutputL4T2[0]=addErrorMessage(userOutputL4T2[0], "Don't forget to begin your first line of code with \nint iNum1 = (or int iNum1=)\n and end with ;");
		}//end outside else

		if(lineInput[1].startsWith("int iNum2 =") || lineInput[1].startsWith("int iNum2="))
		{
			if(lineInput[1].endsWith(";"))
			{
				//a for loop to assign the value of output all of the integer numbers that the user assigned to the variable in their code
				for(int i=9; i<lineInput[1].length()-1; i++)
				{
					if(!(lineInput[1].charAt(i)==' ') && !(lineInput[1].charAt(i)=='='))
					{
						string2+=lineInput[1].charAt(i);
					}//end if inside for loop
				}//end for

				isInt2=ErrorChecks.checkIfInt(string2);

				if(!isInt2)
				{
					userOutputL4T2[0]=addErrorMessage(userOutputL4T2[0], "You must assign iNum2 as an integer.");
				}//end third if
				else
				{
					iNum2=Integer.parseInt(string2);
				}//end else
			}//end second if
		}//end first if

		else
		{
			userOutputL4T2[0]=addErrorMessage(userOutputL4T2[0], "Don't forget to begin your second line of code with \nint iNum2 = (or int iNum2=)\n and end with );");
		}//end outside else

		if(!lineInput[2].equals("int iResult;"))
		{
			userOutputL4T2[0]=addErrorMessage(userOutputL4T2[0], "Don't forget to create the integer variable iResult on the third line of code");
		}//end outside if

		if(!(lineInput[4].equals("System.out.println(iResult);")))
		{
			userOutputL4T2[0]=addErrorMessage(userOutputL4T2[0], "Please check the fifth line of your code to make sure that you have printed the varialbe iResult to screen");
		}//end if

		if(userOutputL4T2[0].equals("") && lineInput[4].equals("System.out.println(iResult);"));	
		{
			lineInput[3]=lineInput[3].replaceAll("\\s+",""); //removing all spaces in user's initialization of iResult

			//a switch case to determine the value of iResult based on the user's fourth line of code
			switch(lineInput[3])
			{
			case "iResult=iNum1+iNum2;":
				iResult=iNum1+iNum2;
				userOutputL4T2[0]=String.valueOf(iResult);
				break;
			case "iResult=iNum2+iNum1;":
				iResult=iNum2+iNum1;
				userOutputL4T2[0]=String.valueOf(iResult);
				break;
			case "iResult=iNum1+iNum1;":
				iResult=iNum1+iNum1;
				userOutputL4T2[0]=String.valueOf(iResult);
				break;
			case "iResult=iNum2+iNum2;":
				iResult=iNum2+iNum2;
				userOutputL4T2[0]=String.valueOf(iResult);
				break;
			case "iResult=iNum1-iNum2;":
				iResult=iNum1-iNum2;
				userOutputL4T2[0]=String.valueOf(iResult);
				break;
			case "iResult=iNum2-iNum1;":
				iResult=iNum2-iNum1;
				userOutputL4T2[0]=String.valueOf(iResult);
				break;
			case "iResult=iNum1-iNum1;":
				iResult=0;
				userOutputL4T2[0]=String.valueOf(iResult);
				break;
			case "iResult=iNum2-iNum2;":
				iResult=0;
				userOutputL4T2[0]=String.valueOf(iResult);
				break;
			case "iResult=iNum1*iNum2;":
				iResult=iNum1*iNum2;
				userOutputL4T2[0]=String.valueOf(iResult);
				break;
			case "iResult=iNum2*iNum1;":
				iResult=iNum2*iNum1;
				userOutputL4T2[0]=String.valueOf(iResult);
				break;
			case "iResult=iNum1*iNum1;":
				iResult=iNum1*iNum1;
				userOutputL4T2[0]=String.valueOf(iResult);
				break;
			case "iResult=iNum2*iNum2;":
				iResult=iNum2*iNum2;
				userOutputL4T2[0]=String.valueOf(iResult);
				break;
			case "iResult=iNum1/iNum2;":
				iResult=iNum1/iNum2;
				userOutputL4T2[0]=String.valueOf(iResult);
				break;
			case "iResult=iNum2/iNum1;":
				iResult=iNum2/iNum1;
				userOutputL4T2[0]=String.valueOf(iResult);
				break;
			case "iResult=iNum1/iNum1;":
				iResult=1;
				userOutputL4T2[0]=String.valueOf(iResult);
				break;
			case "iResult=iNum2/iNum2;":
				iResult=1;
				userOutputL4T2[0]=String.valueOf(iResult);
				break;
			default:
				userOutputL4T2[0]=addErrorMessage(userOutputL4T2[0], "Please check the fourth line of your code to make sure that you initialized the variable iResult properly (this includes starting with iResult=, ending with ;, using only +, -, *, or / between the variables num1 and num2, and only using one of the specified math operations)");
				break;
			}//end switch case
		}//end if

		userOutputL4T2[1]=outputFontFormat(userOutputL4T2[0], fontSize);

		//returning the array containing the text and style for the output text area in L4T2
		return userOutputL4T2;
	}//end submitL4T2 method


	/* Method Author: Brooke Cronin
	 * Method Name: submitL4T3
	 * Description: Breaks apart user's input by line, checks to make sure all variables are created and initialized correctly, checks that user printed the result variable to screen, and prints all errors found in user's code or the value of result;
	 * Parameters: TextArea taUserInputL4T3
	 * Returns: String [] userOutputL4T3
	 */

	public static String [] submitL4T3(TextArea taUserInputL4T3, String fontSize)
	{
		String [] lineInput = splitInputByLine(taUserInputL4T3.getText(), 6, "L4T3");
		String string1="";
		String string2="";
		double dNum1=0;
		double dNum2=0;
		double dResult=0;
		boolean isDouble1;
		boolean isDouble2;

		String [] userOutputL4T3=new String[2];
		userOutputL4T3[0]="";
		userOutputL4T3[1]="";

		if(lineInput[0].equals("not valid"))
		{
			userOutputL4T3[0]=addErrorMessage(userOutputL4T3[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL4T3[1]=outputFontFormat(userOutputL4T3[0], fontSize);

			//returning the array containing the text and style for the output text area in L4T3
			return userOutputL4T3;
		}
		
		if(lineInput[5].startsWith("Your code should contain exactly"))
		{
			userOutputL4T3[0]=addErrorMessage(userOutputL4T3[0],lineInput[5]);
		}

		if(lineInput[0].startsWith("double dNum1 =") || lineInput[0].startsWith("double dNum1="))
		{
			if(lineInput[0].endsWith(";"))
			{
				//a for loop to assign the value of output all of the double numbers that the user assigned to the variable in their code
				for(int i=12; i<lineInput[0].length()-1; i++)
				{
					if(!(lineInput[0].charAt(i)==' ') && !(lineInput[0].charAt(i)=='='))
					{
						string1+=lineInput[0].charAt(i);
					}//end if inside for loop
				}//end for

				isDouble1=ErrorChecks.checkIfDouble(string1);

				//when the value of dNum1 is not a double
				if(!isDouble1)
				{
					userOutputL4T3[0]=addErrorMessage(userOutputL4T3[0], "You must assign dNum1 as a double.");
				}//end third if
				else
				{
					dNum1=Double.parseDouble(string1);
				}//end else
			}//end second if
		}//end first if

		else
		{
			userOutputL4T3[0]=addErrorMessage(userOutputL4T3[0], "Don't forget to begin your first line of code with \ndouble dNum1 = (or double dNum1=)\n and end with ;");
		}//end outside else

		if(lineInput[1].startsWith("double dNum2 =") || lineInput[1].startsWith("double dNum2="))
		{
			if(lineInput[1].endsWith(";"))
			{
				//a for loop to assign the value of output all of the double numbers that the user assigned to the variable in their code
				for(int i=12; i<lineInput[1].length()-1; i++)
				{
					if(!(lineInput[1].charAt(i)==' ') && !(lineInput[1].charAt(i)=='='))
					{
						string2+=lineInput[1].charAt(i);
					}//end if inside for loop
				}//end for

				isDouble2=ErrorChecks.checkIfDouble(string2);

				if(!isDouble2)
				{
					userOutputL4T3[0]=addErrorMessage(userOutputL4T3[0], "You must assign dNum2 as a double.");
				}//end third if
				else
				{
					dNum2=Double.parseDouble(string2);
				}//end else
			}//end second if
		}//end first if

		else
		{
			userOutputL4T3[0]=addErrorMessage(userOutputL4T3[0], "Don't forget to begin your second line of code with \ndouble dNum2 = (or double dNum2=)\n and end with );");
		}//end else

		if(!lineInput[2].equals("double dResult;"))
		{
			userOutputL4T3[0]=addErrorMessage(userOutputL4T3[0], "Don't forget to create the double variable dResult on the third line of code");
		}//end if

		if(!(lineInput[4].equals("System.out.println(dResult);")))
		{
			userOutputL4T3[0]=addErrorMessage(userOutputL4T3[0], "Please check the fifth line of your code to make sure that you have printed the varialbe dResult to screen");
		}//end if

		if(userOutputL4T3[0].equals("") && lineInput[4].equals("System.out.println(dResult);"));	
		{
			lineInput[3]=lineInput[3].replaceAll("\\s+",""); //removing all spaces in user's initialization of dResult

			//a switch case to determine the value of dResult based on the user's fourth line of code
			switch(lineInput[3])
			{
			case "dResult=dNum1+dNum2;":
				dResult=dNum1+dNum2;
				userOutputL4T3[0]=String.valueOf(dResult);
				break;
			case "dResult=dNum2+dNum1;":
				dResult=dNum2+dNum1;
				userOutputL4T3[0]=String.valueOf(dResult);
				break;
			case "dResult=dNum1+dNum1;":
				dResult=dNum1+dNum1;
				userOutputL4T3[0]=String.valueOf(dResult);
				break;
			case "dResult=dNum2+dNum2;":
				dResult=dNum2+dNum2;
				userOutputL4T3[0]=String.valueOf(dResult);
				break;
			case "dResult=dNum1-dNum2;":
				dResult=dNum1-dNum2;
				userOutputL4T3[0]=String.valueOf(dResult);
				break;
			case "dResult=dNum2-dNum1;":
				dResult=dNum2-dNum1;
				userOutputL4T3[0]=String.valueOf(dResult);
				break;
			case "dResult=dNum1-dNum1;":
				dResult=0;
				userOutputL4T3[0]=String.valueOf(dResult);
				break;
			case "dResult=dNum2-dNum2;":
				dResult=0;
				userOutputL4T3[0]=String.valueOf(dResult);
				break;
			case "dResult=dNum1*dNum2;":
				dResult=dNum1*dNum2;
				userOutputL4T3[0]=String.valueOf(dResult);
				break;
			case "dResult=dNum2*dNum1;":
				dResult=dNum2*dNum1;
				userOutputL4T3[0]=String.valueOf(dResult);
				break;
			case "dResult=dNum1*dNum1;":
				dResult=dNum1*dNum1;
				userOutputL4T3[0]=String.valueOf(dResult);
				break;
			case "dResult=dNum2*dNum2;":
				dResult=dNum2*dNum2;
				userOutputL4T3[0]=String.valueOf(dResult);
				break;
			case "dResult=dNum1/dNum2;":
				dResult=dNum1/dNum2;
				userOutputL4T3[0]=String.valueOf(dResult);
				break;
			case "dResult=dNum2/dNum1;":
				dResult=dNum2/dNum1;
				userOutputL4T3[0]=String.valueOf(dResult);
				break;
			case "dResult=dNum1/dNum1;":
				dResult=1;
				userOutputL4T3[0]=String.valueOf(dResult);
				break;
			case "dResult=dNum2/dNum2;":
				dResult=1;
				userOutputL4T3[0]=String.valueOf(dResult);
				break;
			default:
				userOutputL4T3[0]=addErrorMessage(userOutputL4T3[0], "Please check the fourth line of your code to make sure that you initialized the variable dResult properly (this includes starting with dResult=, ending with ;, using only +, -, *, or / between the variables num1 and num2, and only using one of the specified math operations)");
				break;
			}//end switch case
		}//end if

		userOutputL4T3[1]=outputFontFormat(userOutputL4T3[0], fontSize);

		//returning the array containing the text and style for the output text area in L4T3
		return userOutputL4T3;
	}//end submitL4T3 method


	/* Method Author: Brooke Cronin
	 * Method Name: submitL4T4
	 * Description: Breaks apart user's input by line, checks to make sure all variables are created and initialized correctly, checks that user printed the result variable to screen, and prints all errors found in user's code or the value of result;
	 * Parameters: TextArea taUserInputL4T4
	 * Returns: String [] userOutputL4T4
	 */

	public static String [] submitL4T4(TextArea taUserInputL4T4, String fontSize)
	{
		String [] lineInput = splitInputByLine(taUserInputL4T4.getText(), 5, "L4T4");
		String stringDividend="";
		String stringDivisor="";
		int dividend=1;
		int divisor=1;
		int quotient=0;
		boolean isInt1;
		boolean isInt2;
		String output="";

		String [] userOutputL4T4=new String[2];
		userOutputL4T4[0]="";
		userOutputL4T4[1]="";

		if(lineInput[0].equals("not valid"))
		{
			userOutputL4T4[0]=addErrorMessage(userOutputL4T4[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL4T4[1]=outputFontFormat(userOutputL4T4[0], fontSize);

			//returning the array containing the text and style for the output text area in L4T4
			return userOutputL4T4;
		}
		
		if(lineInput[4].startsWith("Your code should contain exactly"))
		{
			userOutputL4T4[0]=addErrorMessage(userOutputL4T4[0],lineInput[4]);
		}

		if(lineInput[0].startsWith("int dividend =") || lineInput[0].startsWith("int dividend="))
		{
			if(lineInput[0].endsWith(";"))
			{
				//a for loop to assign the value of output all of the integer numbers that the user assigned to the variable in their code
				for(int i=12; i<lineInput[0].length()-1; i++)
				{
					if(!(lineInput[0].charAt(i)==' ') && !(lineInput[0].charAt(i)=='='))
					{
						stringDividend+=lineInput[0].charAt(i);
					}//end if inside for loop
				}//end for

				isInt1=ErrorChecks.checkIfInt(stringDividend);

				//when the value of dividend is not an integer
				if(!isInt1)
				{
					userOutputL4T4[0]=addErrorMessage(userOutputL4T4[0], "You must assign dividend as an integer.");
				}//end third if
				else
				{
					dividend=Integer.parseInt(stringDividend);
				}//end else
			}//end second if
		}//end first if

		else
		{
			userOutputL4T4[0]=addErrorMessage(userOutputL4T4[0], "Don't forget to begin your first line of code with \nint dividend = (or int dividend=)\n and end with ;");
		}//end outside else

		if(lineInput[1].startsWith("int divisor =") || lineInput[1].startsWith("int divisor="))
		{
			if(lineInput[1].endsWith(";"))
			{
				//a for loop to assign the value of output all of the integer numbers that the user assigned to the variable in their code
				for(int i=11; i<lineInput[1].length()-1; i++)
				{
					if(!(lineInput[1].charAt(i)==' ') && !(lineInput[1].charAt(i)=='='))
					{
						stringDivisor+=lineInput[1].charAt(i);
					}//end if inside for loop
				}//end for

				isInt2=ErrorChecks.checkIfInt(stringDivisor);

				if(!isInt2)
				{
					userOutputL4T4[0]=addErrorMessage(userOutputL4T4[0], "You must assign divisor as an integer.");
				}//end third if
				else
				{
					divisor=Integer.parseInt(stringDivisor);
				}//end else
			}//end second if
		}//end first if

		else
		{
			userOutputL4T4[0]=addErrorMessage(userOutputL4T4[0], "Don't forget to begin your second line of code with \nint divisor = (or int divisor=)\n and end with ;");
		}//end outside else

		if(lineInput[2].startsWith("int quotient") && lineInput[2].contains("dividend") && lineInput[2].contains("divisor"))
		{
			lineInput[2]=lineInput[2].replaceAll("\\s+","");
			if(lineInput[2].endsWith("=dividend/divisor;"))
			{
				quotient=dividend/divisor;
			}
			else
			{
				userOutputL4T4[0]=addErrorMessage(userOutputL4T4[0], "Don't forget to initialize the integer variable quotient on the third line of code properly.");
			}
		}//end outside if
		else
		{
			userOutputL4T4[0]=addErrorMessage(userOutputL4T4[0], "Don't forget to create the integer variable quotient on the third line of code.");
		}

		if(lineInput[3].startsWith("System.out.println(") && lineInput[3].contains("quotient") && lineInput[3].contains("dividend") && lineInput[3].contains("divisor"))
		{
			if(lineInput[3].endsWith(");"))
			{
				lineInput[3]=lineInput[3].replaceAll("\\s+","");
				if(lineInput[3].equals("System.out.println(quotient+\"R\"+dividend%divisor);") && taUserInputL4T4.getText().contains("+\" R \"+"))
				{
					output=quotient+" R "+dividend%divisor;
				}//end third if
				else if(lineInput[3].equals("System.out.println(quotient+\"R\"+dividend%divisor);"))
				{
					output=quotient+"R"+dividend%divisor;
				}//end else if
				else
				{
					userOutputL4T4[0]=addErrorMessage(userOutputL4T4[0], "Don't forget to print the value of the quotient and remainder as described in the instructions on the fourth line of your code.");
				}//end else
			}//end second if
			else
			{
				userOutputL4T4[0]=addErrorMessage(userOutputL4T4[0], "Don't forget to end your fourth line of code with );");
			}//end else
		}//end first if
		else
		{
			userOutputL4T4[0]=addErrorMessage(userOutputL4T4[0], "Don't forget to start your fourth line of code with System.out.println(");
		}//end else

		//setting the intended output when there are no errors detected in the user's code
		if(userOutputL4T4[0].equals(""))
		{
			userOutputL4T4[0]=output;
		}//end if

		userOutputL4T4[1]=outputFontFormat(userOutputL4T4[0], fontSize);

		//returning the array containing the text and style for the output text area in L4T4
		return userOutputL4T4;
	}//end submitL4T4 method
	
	
	/* Method Author: Brooke Cronin
	 * Method Name: submitL5T1
	 * Description: Breaks apart user's input by line, checks to make sure all variables are created and initialized correctly, checks that user printed the result variable to screen, and prints all errors found in user's code or the value of result;
	 * Parameters: TextArea taUserInputL5T1
	 * Returns: String [] userOutputL5T1
	 * Throws: ScriptException
	 */

	public static String [] submitL5T1(TextArea taUserInputL5T1, String fontSize) throws ScriptException
	{
		String [] lineInput = splitInputByLine(taUserInputL5T1.getText(), 2, "L5T1");
		String calculation=lineInput[0].substring(19, lineInput[0].length()-2);
		String sBase="";
		String sPower="";
		boolean isDoubleBase;
		boolean isDoublePower;
		double dBase=0;
		double dPower=0;
		double exponentResult=0;
		int comma=0;
		String [] userOutputL5T1=new String[2];
		userOutputL5T1[0]="";
		userOutputL5T1[1]="";

		if(lineInput[0].equals("not valid"))
		{
			userOutputL5T1[0]=addErrorMessage(userOutputL5T1[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL5T1[1]=outputFontFormat(userOutputL5T1[0], fontSize);

			//returning the array containing the text and style for the output text area in L5T1
			return userOutputL5T1;
		}
		
		//when the user input starts and ends with the appropriate code to be able to print to the screen
		if(lineInput[0].startsWith("System.out.println(") && lineInput[0].endsWith(");"))
		{
			if(calculation.startsWith("Math.pow(") && calculation.endsWith(")") && calculation.contains(","))
			{
				for(int i=9; i<calculation.length()-1; i++)
				{
					if(!(calculation.charAt(i)==' ') && !(calculation.charAt(i)==',') && comma==0)
					{
						sBase+=calculation.charAt(i);
					}//end if
					else if(calculation.charAt(i)==',' && comma==0)
					{
						comma++;
					}//end first else if
					else if(calculation.charAt(i)==',' && comma==1)
					{
						userOutputL5T1[0]=addErrorMessage(userOutputL5T1[0], "Please check the parameters listed above for the Math.pow() method. It seems like you have added too many.");
					}//end second else if
					else if(!(calculation.charAt(i)==' ') && comma==1)
					{
						sPower+=calculation.charAt(i);
					}//end third else if
				}//end for loop

				//checking if user added values for both parameters in the Math.pow() method
				if(sBase.equals(""))
				{
					userOutputL5T1[0]=addErrorMessage(userOutputL5T1[0], "Don't forget to add the base as the first parameter in your call to the Math.pow() method.");
				}//end if
				if(sPower.equals(""))
				{
					userOutputL5T1[0]=addErrorMessage(userOutputL5T1[0], "Don't forget to add the power as the second parameter in your call to the Math.pow() method.");
				}//end if
			}//end inside if
		}//end outside if

		//when user forgot part or all of the start/end code to be able to print to the screen 
		else
		{
			userOutputL5T1[0]=addErrorMessage(userOutputL5T1[0], "Don't forget to begin your code with System.out.println( and end with );");
		}//end else

		//when there has been no errors detected in the user's code yet
		if(userOutputL5T1[0].equals(""))
		{
			//checking if the base parameter is a double value
			isDoubleBase=ErrorChecks.checkIfDouble(sBase);

			//when the value if sBase isn't a double
			if(!isDoubleBase)
			{
				userOutputL5T1[0]=addErrorMessage(userOutputL5T1[0], "The base of the expression must be an integer or decimal value.");
			}//end if
			else
			{
				dBase=Double.parseDouble(sBase);
			}//end else

			//checking if the power parameter is a double value
			isDoublePower=ErrorChecks.checkIfDouble(sPower);

			//when the value if sPower isn't a double
			if(!isDoublePower)
			{
				userOutputL5T1[0]=addErrorMessage(userOutputL5T1[0], "The power of the expression must be an integer or decimal value.");
			}//end if
			else
			{
				dPower=Double.parseDouble(sPower);
			}//end else

			//calculating and assigning the result of the exponent expression the user wishes to calculate
			if(isDoubleBase && isDoublePower)
			{
				exponentResult=Math.pow(dBase, dPower);
			}//end if
			else
			{
				userOutputL5T1[0]=addErrorMessage(userOutputL5T1[0], "Since you did not call to the Math.pow() method properly, your exponent expression was unable to be calculated.");
			}//end else
		}//end if

		if(userOutputL5T1[0].equals(""))
		{
			userOutputL5T1[0]=Double.toString(exponentResult);
		}//end if

		userOutputL5T1[1]=outputFontFormat(userOutputL5T1[0], fontSize);

		//returning the array containing the text and style for the output text area in the correct task
		return userOutputL5T1;
	}//end submitL5T1 method


	/* Method Author: Brooke Cronin
	 * Method Name: submitL5T2
	 * Description: Breaks apart user's input by line, checks to make sure all variables are created and initialized correctly, checks that user printed the result variable to screen, and prints all errors found in user's code or the value of result;
	 * Parameters: TextArea taUserInputL5T2
	 * Returns: String [] userOutputL5T2
	 * Throws: ScriptException
	 */

	public static String [] submitL5T2(TextArea taUserInputL5T2, String fontSize) throws ScriptException
	{
		String [] lineInput = splitInputByLine(taUserInputL5T2.getText(), 4, "L5T2");
		String calculation=lineInput[1].substring(18, lineInput[1].length());
		String [] userOutputL5T2=new String[2];
		userOutputL5T2[0]="";
		userOutputL5T2[1]="";
		String sNum="";
		double dNum=0;
		double dSquareRoot=0;
		boolean isDouble;

		if(lineInput[0].equals("not valid"))
		{
			userOutputL5T2[0]=addErrorMessage(userOutputL5T2[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL5T2[1]=outputFontFormat(userOutputL5T2[0], fontSize);

			//returning the array containing the text and style for the output text area in L5T2
			return userOutputL5T2;
		}
		
		if(lineInput[3].startsWith("Your code should contain exactly"))
		{
			userOutputL5T2[0]=addErrorMessage(userOutputL5T2[0],lineInput[3]);
		}

		if(lineInput[0].startsWith("double num=") || lineInput[0].startsWith("double num ="))
		{
			for(int i=11; i<lineInput[0].length()-1; i++)
			{
				if(lineInput[0].charAt(i)!=' ' || lineInput[0].charAt(i)!='=')
				{
					sNum+=lineInput[0].charAt(i);
				}//end if inside for loop
			}//end for loop

			if(sNum.equals(""))
			{
				userOutputL5T2[0]=addErrorMessage(userOutputL5T2[0], "Don't forget to initialize the variable num to a double value.");
			}//end if
			else
			{
				//checking if the base parameter is a double value
				isDouble=ErrorChecks.checkIfDouble(sNum);

				//when the value if sBase isn't a double
				if(!isDouble)
				{
					userOutputL5T2[0]=addErrorMessage(userOutputL5T2[0], "The number you want to find the square root of must be an integer or decimal value.");
				}//end if
				else
				{
					dNum=Double.parseDouble(sNum);

					//when the user input starts and ends with the appropriate code to be able to print to the screen
					if(lineInput[1].startsWith("double squareRoot=") || lineInput[1].startsWith("double squareRoot ="))
					{
						if(calculation.equals("Math.sqrt(num);") || calculation.equals(" Math.sqrt(num);"))
						{
							dSquareRoot=Math.sqrt(dNum);
						}//end inside if
						else
						{
							userOutputL5T2[0]=addErrorMessage(userOutputL5T2[0], "Please check how you initialize the variable squareRoot/calculate the square root of the variable num.");
						}//end else
					}//end outside if
					else
					{
						userOutputL5T2[0]=addErrorMessage(userOutputL5T2[0], "Don't forget to create a double variable called squareRoot and initialize it to the square root of the value of num on the second line of code.");
					}//end else
				}//end else
			}//end else
		}//end if

		else
		{
			userOutputL5T2[0]=addErrorMessage(userOutputL5T2[0], "Don't forget to create and initialize a double variable called num on your first line of code.");
		}//end else

		if(lineInput[2].equals("System.out.println(squareRoot);") && userOutputL5T2[0].equals(""))
		{
			userOutputL5T2[0]=Double.toString(dSquareRoot);
		}//end inside if

		else if(!lineInput[2].equals("System.out.println(squareRoot);"))
		{
			userOutputL5T2[0]=addErrorMessage(userOutputL5T2[0], "Don't forget to print the value of squareRoot to the screen on your third line of code.");
		}//end else if

		userOutputL5T2[1]=outputFontFormat(userOutputL5T2[0], fontSize);

		//returning the array containing the text and style for the output text area in the correct task
		return userOutputL5T2;
	}//end submitL5T2 method


	/* Method Author: Brooke Cronin
	 * Method Name: submitL5T3
	 * Description: Breaks apart user's input by line, checks to make sure all variables are created and initialized correctly, checks that user printed the result variable to screen, and prints all errors found in user's code or the value of result;
	 * Parameters: TextArea taUserInputL5T3
	 * Returns: String [] userOutputL5T3
	 * Throws: ScriptException
	 */

	public static String [] submitL5T3(TextArea taUserInputL5T3, String fontSize) throws ScriptException
	{
		String [] lineInput = splitInputByLine(taUserInputL5T3.getText(), 7, "L5T3");
		String radiansCalculation=lineInput[1].substring(15, lineInput[1].length());
		String sineCalculation=lineInput[2].substring(12, lineInput[2].length());
		String cosineCalculation=lineInput[3].substring(14, lineInput[3].length());
		String tangentCalculation=lineInput[4].substring(15, lineInput[4].length());
		String [] userOutputL5T3=new String[2];
		userOutputL5T3[0]="";
		userOutputL5T3[1]="";
		String sDegrees="";
		double dDegrees=0;
		double dRadians=-2;
		double dSine=0;
		double dCosine=0;
		double dTangent=0;
		boolean isDouble;

		if(lineInput[0].equals("not valid"))
		{
			userOutputL5T3[0]=addErrorMessage(userOutputL5T3[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL5T3[1]=outputFontFormat(userOutputL5T3[0], fontSize);

			//returning the array containing the text and style for the output text area in L5T3
			return userOutputL5T3;
		}
		
		if(lineInput[6].startsWith("Your code should contain exactly"))
		{
			userOutputL5T3[0]=addErrorMessage(userOutputL5T3[0],lineInput[6]);
		}

		for(int i=0; i<=3; i++)
		{
			if(radiansCalculation.charAt(i)=='=' || radiansCalculation.charAt(i)==' ')
			{
				radiansCalculation=radiansCalculation.substring(i, lineInput[1].length());
			}//end if for radiansCalculation
			if(sineCalculation.charAt(i)=='=' || sineCalculation.charAt(i)==' ')
			{
				sineCalculation=sineCalculation.substring(i, lineInput[2].length());
			}//end if for sineCalculation
			if(cosineCalculation.charAt(i)=='=' || cosineCalculation.charAt(i)==' ')
			{
				cosineCalculation=cosineCalculation.substring(i, lineInput[3].length());
			}//end if for cosineCalculation
			if(tangentCalculation.charAt(i)=='=' || tangentCalculation.charAt(i)==' ')
			{
				tangentCalculation=tangentCalculation.substring(i, lineInput[4].length());
			}//end if for tangentCalculation
		}//end for loop

		if(lineInput[0].startsWith("double degrees=") || lineInput[0].startsWith("double degrees ="))
		{
			for(int i=15; i<lineInput[0].length()-1; i++)
			{
				if(lineInput[0].charAt(i)!=' ' || lineInput[0].charAt(i)!='=')
				{
					sDegrees+=lineInput[0].charAt(i);
				}//end if inside for loop
			}//end for loop

			if(sDegrees.equals(""))
			{
				userOutputL5T3[0]=addErrorMessage(userOutputL5T3[0], "Don't forget to initialize the variable degrees to a double value.");
			}//end if
			else
			{
				//checking if the base parameter is a double value
				isDouble=ErrorChecks.checkIfDouble(sDegrees);

				//when the value if sDegrees isn't a double
				if(!isDouble)
				{
					userOutputL5T3[0]=addErrorMessage(userOutputL5T3[0], "The value you have initialized to degrees is not a double value.");
				}//end if
				else
				{
					dDegrees=Double.parseDouble(sDegrees);

					//when the user input starts and ends with the appropriate code to be able to print to the screen
					if(lineInput[1].startsWith("double radians=") || lineInput[1].startsWith("double radians ="))
					{
						if(radiansCalculation.equals("Math.toRadians(degrees);"))
						{
							dRadians=Math.toRadians(dDegrees);
						}//end inside if
						else
						{
							userOutputL5T3[0]=addErrorMessage(userOutputL5T3[0], "Please check how you initialize the variable radians/calculate the radian converted value of the variable degrees.");
						}//end else
					}//end outside if
					else
					{
						userOutputL5T3[0]=addErrorMessage(userOutputL5T3[0], "Don't forget to create a double variable called radians and initialize it to the converted value of the variable degrees on the second line of code.");
					}//end else

					if(dRadians!=-2)
					{
						if(lineInput[2].startsWith("double sine=") || lineInput[2].startsWith("double sine ="))
						{
							if(sineCalculation.equals("Math.sin(radians);"))
							{
								dSine=Math.sin(dRadians);
							}//end inside if
							else
							{
								userOutputL5T3[0]=addErrorMessage(userOutputL5T3[0], "Please check how you initialize the variable sine/calculate the value of sine with the variable radians.");
							}//end else
						}//end outside if
						else
						{
							userOutputL5T3[0]=addErrorMessage(userOutputL5T3[0], "Don't forget to create a double variable called sine and initialize it to the sine value of the variable radians using the Math class on your third line of code.");
						}//end else
						if(lineInput[3].startsWith("double cosine=") || lineInput[3].startsWith("double cosine ="))
						{
							if(cosineCalculation.equals("Math.cos(radians);"))
							{
								dCosine=Math.cos(dRadians);
							}//end inside if
							else
							{
								userOutputL5T3[0]=addErrorMessage(userOutputL5T3[0], "Please check how you initialize the variable cosine/calculate the value of cosine with the variable radians.");
							}//end else
						}//end outside if
						else
						{
							userOutputL5T3[0]=addErrorMessage(userOutputL5T3[0], "Don't forget to create a double variable called cosine and initialize it to the cosine value of the variable radians using the Math class on your fourth line of code.");
						}//end else
						if(lineInput[4].startsWith("double tangent=") || lineInput[4].startsWith("double tangent ="))
						{
							if(tangentCalculation.equals("Math.tan(radians);"))
							{
								dTangent=Math.tan(dRadians);
							}//end inside if
							else
							{
								userOutputL5T3[0]=addErrorMessage(userOutputL5T3[0], "Please check how you initialize the variable tangent/calculate the value of tangent with the variable radians.");
							}//end else
						}//end outside if
						else
						{
							userOutputL5T3[0]=addErrorMessage(userOutputL5T3[0], "Don't forget to create a double variable called tangent and initialize it to the tangent value of the variable radians using the Math class on your fifth line of code.");
						}//end else

						if(lineInput[5].equals("System.out.println(degrees+\", \"+radians+\", \"+sine+\", \"+cosine+\", \"+tangent);") && userOutputL5T3[0].equals(""))
						{
							userOutputL5T3[0]=dDegrees+", "+dRadians+", "+dSine+", "+dCosine+", "+dTangent;
						}//end if
						else if(!lineInput[5].equals("System.out.println(degrees+\", \"+radians+\", \"+sine+\", \"+cosine+\", \"+tangent);"))
						{
							userOutputL5T3[0]=addErrorMessage(userOutputL5T3[0], "Don't forget to print all five variables to the screen in the correct format on the sixth line of code");
						}//end else if
					}//end if

					else
					{
						userOutputL5T3[0]=addErrorMessage(userOutputL5T3[0], "Since you did not correctly initialize the variable radians, the values of sine, cosine, and tangent were not able to be initialized and nothing was able to be printed to the screen.");
					}//end else
				}//end else
			}//end else
		}//end if

		else
		{
			userOutputL5T3[0]=addErrorMessage(userOutputL5T3[0], "Don't forget to create and initialize a double variable called degrees on your first line of code.");
		}//end else

		userOutputL5T3[1]=outputFontFormat(userOutputL5T3[0], fontSize);

		//returning the array containing the text and style for the output text area in the correct task
		return userOutputL5T3;
	}//end submitL5T3 method


	/* Method Author: Brooke Cronin
	 * Method Name: submitL5T4
	 * Description: Breaks apart user's input by line, checks to make sure all variables are created and initialized correctly, checks that user printed the result variable to screen, and prints all errors found in user's code or the value of result;
	 * Parameters: TextArea taUserInputL5T4
	 * Returns: String [] userOutputL5T4
	 * Throws: ScriptException
	 */

	public static String [] submitL5T4(TextArea taUserInputL5T4, String fontSize) throws ScriptException
	{
		String [] lineInput = splitInputByLine(taUserInputL5T4.getText(), 4, "L5T4");
		String randomIntCalculation1=lineInput[0].substring(14, lineInput[0].length());
		String randomIntCalculation="";
		String randomDoubleCalculation="";
		String [] userOutputL5T4=new String[2];
		userOutputL5T4[0]="";
		userOutputL5T4[1]="";
		String sIntMin="";
		int iIntMin=-5;
		String sIntMax="";
		int iIntMax=-5;
		int iPlusCounter=0;
		int iRandomInt=0;
		double dRandomDouble=0;
		boolean isInt1;
		boolean isInt2;

		if(lineInput[0].equals("not valid"))
		{
			userOutputL5T4[0]=addErrorMessage(userOutputL5T4[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL5T4[1]=outputFontFormat(userOutputL5T4[0], fontSize);

			//returning the array containing the text and style for the output text area in L5T4
			return userOutputL5T4;
		}
		
		if(lineInput[3].startsWith("Your code should contain exactly"))
		{
			userOutputL5T4[0]=addErrorMessage(userOutputL5T4[0],lineInput[3]);
		}

		for(int i=0; i<=3; i++)
		{
			if(randomIntCalculation1.charAt(i)=='=' || randomIntCalculation1.charAt(i)==' ')
			{
				randomIntCalculation=randomIntCalculation1.substring(i+1, lineInput[0].length()-1);
			}//end if for randomIntCalculation
			else
			{
				randomIntCalculation=randomIntCalculation1;
			}
		}//end for loop

		//when the user has created an integer variable called randomInt
		if(lineInput[0].startsWith("int randomInt=") || lineInput[0].startsWith("int randomInt ="))
		{
			if(randomIntCalculation.startsWith("(int) (Math.random()") && randomIntCalculation.endsWith(";"))
			{
				if(randomIntCalculation.contains("*"))
				{
					for(int i=20; i<randomIntCalculation.length(); i++)
					{
						if(randomIntCalculation.charAt(i)==')')
						{
							break;
						}//end if
						else if(randomIntCalculation.charAt(i)!='*' && randomIntCalculation.charAt(i)!=' ' && randomIntCalculation.charAt(i)!=';')
						{
							sIntMax+=randomIntCalculation.charAt(i);
						}//end else if
					}//end for loop

					isInt1=ErrorChecks.checkIfInt(sIntMax);

					//when the value of dividend is not an integer
					if(!isInt1)
					{
						userOutputL5T4[0]=addErrorMessage(userOutputL5T4[0], "You must make the maximium for the range of randomInt an integer.");
					}//end third if
					else
					{
						iIntMax=Integer.parseInt(sIntMax);
					}//end else

				}//end if
				else
				{
					iIntMax=1;
				}//end else

				if(randomIntCalculation.contains("+"))
				{
					for(int i=19; i<randomIntCalculation.length(); i++)
					{
						if(randomIntCalculation.charAt(i)=='+')
						{
							iPlusCounter++;
						}//end if
						else if(randomIntCalculation.charAt(i)!='+' && randomIntCalculation.charAt(i)!=' ' && randomIntCalculation.charAt(i)!=';' && iPlusCounter==1)
						{
							sIntMin+=randomIntCalculation.charAt(i);
						}//end else if
					}//end for loop

					isInt2=ErrorChecks.checkIfInt(sIntMin);

					//when the value of dividend is not an integer
					if(!isInt2)
					{
						userOutputL5T4[0]=addErrorMessage(userOutputL5T4[0], "You must make the minimium for the range of randomInt an integer.");
					}//end third if
					else
					{
						iIntMin=Integer.parseInt(sIntMin);
					}//end else
				}//end if
				else
				{
					iIntMin=0;
				}//end else
			}//end inside if
			if(iIntMax<iIntMin)
			{
				userOutputL5T4[0]=addErrorMessage(userOutputL5T4[0], "The maximum value that the random generated number can be must be greater than the minimum value of randomInt.");
			}//end if
			else
			{
				iRandomInt=(int) (Math.random()*iIntMax+iIntMin);
			}//end else
		}//end outside if

		else
		{
			userOutputL5T4[0]=addErrorMessage(userOutputL5T4[0], "Don't forget to create and initialize an integer variable called randomInt to a random number using the Math class on your first line of code.");
		}//end else

		//when the user has created a double variable called randomDouble
		if(lineInput[1].startsWith("double randomDouble=") || lineInput[1].startsWith("double randomrandomDouble ="))
		{
			randomDoubleCalculation=lineInput[1].replaceAll("\\s+","");
			if(randomDoubleCalculation.equals("doublerandomDouble=Math.random();"))
			{
				dRandomDouble=Math.random();
			}//end inside if
		}//end outside if

		else
		{
			userOutputL5T4[0]=addErrorMessage(userOutputL5T4[0], "Don't forget to create and initialize a double variable called randomDouble to a random number using the Math class on your second line of code.");
		}//end else

		if(userOutputL5T4[0].equals(""))
		{
			if(lineInput[2].startsWith("System.out.println(") && lineInput[2].endsWith(");"))
			{
				lineInput[2]=lineInput[2].replaceAll("\\s+","");
				if(lineInput[2].equals("System.out.println(randomInt+\"\\n\"+randomDouble);"))
				{
					userOutputL5T4[0]=iRandomInt+"\n"+dRandomDouble;
				}//end third if

				else
				{
					userOutputL5T4[0]=addErrorMessage(userOutputL5T4[0], "Don't forget to separate the values of randomInt and randomDouble with one line.");
				}//end else
			}//end second if

			else
			{
				userOutputL5T4[0]=addErrorMessage(userOutputL5T4[0], "Don't forget to print the values of randomInt and randomDouble with one value per line on the third line of code.");
			}//end else
		}//end first if
		else
		{
			userOutputL5T4[0]=addErrorMessage(userOutputL5T4[0], "Since you did not correctly create and/or initialize randomInt and/or randomDouble, these values can't be printed to the screen.");
		}//end else

		userOutputL5T4[1]=outputFontFormat(userOutputL5T4[0], fontSize);

		//returning the array containing the text and style for the output text area in the correct task
		return userOutputL5T4;
	}//end submitL5T4 method


	/* Method Author: Brooke Cronin
	 * Method Name: submitL5T5
	 * Description: Breaks apart user's input by line, checks to make sure all variables are created and initialized correctly, checks that user printed the result variable to screen, and prints all errors found in user's code or the value of result;
	 * Parameters: TextArea taUserInputL5T5
	 * Returns: String [] userOutputL5T5
	 * Throws: ScriptException
	 */

	public static String [] submitL5T5(TextArea taUserInputL5T5, String fontSize) throws ScriptException
	{
		Random rand = new Random();
		String [] lineInput = splitInputByLine(taUserInputL5T5.getText(), 5, "L5T5");
		String randomIntCalculation1=lineInput[1].substring(14, lineInput[1].length());
		String randomIntCalculation="";
		String randomDoubleCalculation="";
		String [] userOutputL5T5=new String[2];
		userOutputL5T5[0]="";
		userOutputL5T5[1]="";
		String randomObject="";
		String sIntMin="";
		int iIntMin=-5;
		String sIntMax="";
		int iIntMax=-5;
		int iRandomInt=0;
		int iBracketCounter=0;
		int iPlusCounter=0;
		int dBracketCounter=0;
		double dRandomDouble=0;
		boolean isInt1;
		boolean isInt2;

		if(lineInput[0].equals("not valid"))
		{
			userOutputL5T5[0]=addErrorMessage(userOutputL5T5[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL5T5[1]=outputFontFormat(userOutputL5T5[0], fontSize);

			//returning the array containing the text and style for the output text area in L5T5
			return userOutputL5T5;
		}
		
		if(lineInput[4].startsWith("Your code should contain exactly"))
		{
			userOutputL5T5[0]=addErrorMessage(userOutputL5T5[0],lineInput[4]);
		}
		for(int i=0; i<=3; i++)
		{
			if(randomIntCalculation1.charAt(i)=='=' || randomIntCalculation1.charAt(i)==' ')
			{
				randomIntCalculation=randomIntCalculation1.substring(i+1, lineInput[1].length()-1);
			}//end if for randomIntCalculation
			else
			{
				randomIntCalculation=randomIntCalculation1;
			}//end else
		}//end for loop

		randomObject=randomObjectName(lineInput[0]);

		if(randomObject.equals(""))
		{
			userOutputL5T5[0]=addErrorMessage(userOutputL5T5[0], "Don't forget to create the object from the Random class in order to generate random numbers for this task.");
		}//end else

		//when the user has created an integer variable called randomInt
		if(lineInput[1].startsWith("int randomInt=") || lineInput[1].startsWith("int randomInt ="))
		{
			if(randomIntCalculation.startsWith(randomObject+".nextInt(") && randomIntCalculation.endsWith(";"))
			{
				for(int i=0; i<randomIntCalculation.length(); i++)
				{
					if(randomIntCalculation.charAt(i)==')')
					{
						break;
					}//end if
					else if(randomIntCalculation.charAt(i)=='(')
					{
						iBracketCounter=1;
					}//end else if
					else if(iBracketCounter==1)
					{
						sIntMax+=randomIntCalculation.charAt(i);
					}//end else if
				}//end for loop

				if(!sIntMax.equals(""))
				{
					isInt1=ErrorChecks.checkIfInt(sIntMax);

					//when the value of sIntMax is not an integer
					if(!isInt1)
					{
						userOutputL5T5[0]=addErrorMessage(userOutputL5T5[0], "You must make the maximium for the range of randomInt an integer.");
					}//end third if
					else
					{
						iIntMax=Integer.parseInt(sIntMax);
					}//end else
				}//end if

				else
				{
					iIntMax=1;
				}//end else

				if(randomIntCalculation.contains("+"))
				{
					for(int i=0; i<randomIntCalculation.length()-1; i++)
					{
						if(randomIntCalculation.charAt(i)=='+')
						{
							iPlusCounter++;
						}//end if
						else if(iPlusCounter==1 && randomIntCalculation.charAt(i)!=' ')
						{
							sIntMin+=randomIntCalculation.charAt(i);
						}//end else if
					}//end for loop

					if(!sIntMin.equals(""))
					{
						isInt2=ErrorChecks.checkIfInt(sIntMin);

						//when the value of dividend is not an integer
						if(!isInt2)
						{
							userOutputL5T5[0]=addErrorMessage(userOutputL5T5[0], "You must make the minimium for the range of randomInt an integer.");
						}//end third if
						else
						{
							iIntMin=Integer.parseInt(sIntMin);
						}//end else
					}//end if
				}//end if
				else
				{
					iIntMin=0;
				}//end else
			}//end inside if

			if(iIntMax<iIntMin)
			{
				userOutputL5T5[0]=addErrorMessage(userOutputL5T5[0], "The maximum value that the random generated number can be must be greater than the minimum value of randomInt.");
			}//end if
			else
			{
				iRandomInt=rand.nextInt(iIntMax-iIntMin+1)+iIntMin;
			}//end else
		}//end outside if

		else
		{
			userOutputL5T5[0]=addErrorMessage(userOutputL5T5[0], "Don't forget to create and initialize an integer variable called randomInt to a random number using the Random class on your second line of code.");
		}//end else

		//when the user has created a double variable called randomDouble
		if(lineInput[2].startsWith("double randomDouble=") || lineInput[2].startsWith("double randomrandomDouble ="))
		{
			randomDoubleCalculation=lineInput[2].replaceAll("\\s+","");
			if(randomDoubleCalculation.equals("doublerandomDouble="+randomObject+".nextDouble();"))
			{
				dRandomDouble=rand.nextDouble();
			}//end inside if
		}//end outside if

		else
		{
			userOutputL5T5[0]=addErrorMessage(userOutputL5T5[0], "Don't forget to create and initialize a double variable called randomDouble to a random number using the Random class on your third line of code.");
		}//end else

		if(userOutputL5T5[0].equals(""))
		{
			if(lineInput[3].startsWith("System.out.println(") && lineInput[3].endsWith(");"))
			{
				lineInput[3]=lineInput[3].replaceAll("\\s+","");
				if(lineInput[3].equals("System.out.println(randomInt+\"\\n\"+randomDouble);"))
				{
					userOutputL5T5[0]=iRandomInt+"\n"+dRandomDouble;
				}//end third if

				else
				{
					userOutputL5T5[0]=addErrorMessage(userOutputL5T5[0], "Don't forget to separate the values of randomInt and randomDouble with one line.");
				}//end else
			}//end second if

			else
			{
				userOutputL5T5[0]=addErrorMessage(userOutputL5T5[0], "Don't forget to print the values of randomInt and randomDouble with one value per line on the third line of code.");
			}//end else
		}//end first if
		else
		{
			userOutputL5T5[0]=addErrorMessage(userOutputL5T5[0], "Since you did not correctly create and/or initialize randomInt and/or randomDouble, these values can't be printed to the screen.");
		}//end else

		userOutputL5T5[1]=outputFontFormat(userOutputL5T5[0], fontSize);

		//returning the array containing the text and style for the output text area in the correct task
		return userOutputL5T5;
	}//end submitL5T5 method
	
	
	/* Method Author: Brooke Cronin
	 * Method Name: submitL6T1
	 * Description: Breaks apart user's input by line, checks to make sure all variables are created and initialized correctly, checks that user printed the result variable to screen, and prints all errors found in user's code or the value of result;
	 * Parameters: TextArea taUserInputL6T1
	 * Returns: String [] userOutputL6T1
	 * Throws: ScriptException
	 */

	public static String [] submitL6T1(TextArea taUserInputL6T1, String fontSize) throws ScriptException
	{
		//creating and initializing the array to store the user's output and the text style as well as all variables to be used in this method
		String [] lineInput = splitInputByLine(taUserInputL6T1.getText(), 7, "L6T1");
		String question="";
		String scannerObject="";
		String [] userOutputL6T1=new String[2];
		userOutputL6T1[0]="";
		userOutputL6T1[1]="";

		if(lineInput[0].equals("not valid"))
		{
			userOutputL6T1[0]=addErrorMessage(userOutputL6T1[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL6T1[1]=outputFontFormat(userOutputL6T1[0], fontSize);

			//returning the array containing the text and style for the output text area in L6T1
			return userOutputL6T1;
		}
		
		if(lineInput[6].startsWith("Your code should contain exactly"))
		{
			userOutputL6T1[0]=addErrorMessage(userOutputL6T1[0],lineInput[6]);
		}

		scannerObject=scannerObjectName(lineInput[0]);

		//when user has not correctly created the scanner object
		if(scannerObject.equals(""))
		{
			userOutputL6T1[0]=addErrorMessage(userOutputL6T1[0], "Don't forget to create the object from the Scanner class in order to gather input from the user for this task.");
		}//end if

		//when user doesn't create the integer variable called favNum on the second line of code
		if(!lineInput[1].equals("int favNum;"))
		{
			userOutputL6T1[0]=addErrorMessage(userOutputL6T1[0], "You must create an integer variable called favNum on the second line of code.");
		}//end if

		if(lineInput[2].startsWith("System.out.println(\"") && lineInput[2].endsWith("\");"))
		{
			for(int i=20; i<lineInput[2].length()-3; i++)
			{
				question+=lineInput[2].charAt(i);
			}//end for loop

			if(question.equals(""))
			{
				userOutputL6T1[0]=addErrorMessage(userOutputL6T1[0], "Don't forget to tell/ask user what they should input (their favourite number).");
			}//end inside if
		}//end outside if
		else
		{
			userOutputL6T1[0]=addErrorMessage(userOutputL6T1[0], "Don't forget to print to the screen and tell/ask user what they should input (their favourite number) on the third line of code.");
		}//end else

		lineInput[3]=lineInput[3].replaceAll("\\s+","");

		if(!lineInput[3].equals("favNum="+scannerObject+".nextInt();"))
		{
			userOutputL6T1[0]=addErrorMessage(userOutputL6T1[0], "Don't forget to allow the user to initialize favNum on the fourth line of code.");
		}//end if

		if(!lineInput[4].equals("System.out.println(favNum);"))
		{
			userOutputL6T1[0]=addErrorMessage(userOutputL6T1[0], "Don't forget to print the value of favNum on the fifth line of code.");
		}//end if

		if(!lineInput[5].equals(scannerObject+".close();"))
		{
			userOutputL6T1[0]=addErrorMessage(userOutputL6T1[0], "Don't forget to close your scanner object on the sixth line of code.");
		}//end if

		//when there has been no errors detected in the user's code yet
		if(userOutputL6T1[0].equals(""))
		{
			userOutputL6T1[0]=question;
		}//end if

		userOutputL6T1[1]=outputFontFormat(userOutputL6T1[0], fontSize);

		//returning the array containing the text and style for the output text area in the correct task
		return userOutputL6T1;
	}//end submitL6T1 method


	/* Method Author: Brooke Cronin
	 * Method Name: submitL6T2
	 * Description: Breaks apart user's input by line, checks to make sure all variables are created and initialized correctly, checks that user printed the result variable to screen, and prints all errors found in user's code or the value of result;
	 * Parameters: TextArea taUserInputL6T2
	 * Returns: String [] userOutputL6T2
	 * Throws: ScriptException
	 */

	public static String [] submitL6T2(TextArea taUserInputL6T2, String fontSize) throws ScriptException
	{
		//creating and initializing the array to store the user's output and the text style as well as all variables to be used in this method
		String [] lineInput = splitInputByLine(taUserInputL6T2.getText(), 7, "L6T2");
		String question="";
		String scannerObject="";
		String [] userOutputL6T2=new String[2];
		userOutputL6T2[0]="";
		userOutputL6T2[1]="";

		if(lineInput[0].equals("not valid"))
		{
			userOutputL6T2[0]=addErrorMessage(userOutputL6T2[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL6T2[1]=outputFontFormat(userOutputL6T2[0], fontSize);

			//returning the array containing the text and style for the output text area in L6T2
			return userOutputL6T2;
		}
		
		if(lineInput[6].startsWith("Your code should contain exactly"))
		{
			userOutputL6T2[0]=addErrorMessage(userOutputL6T2[0],lineInput[6]);
		}

		scannerObject=scannerObjectName(lineInput[0]);

		//when user has not correctly created the scanner object
		if(scannerObject.equals(""))
		{
			userOutputL6T2[0]=addErrorMessage(userOutputL6T2[0], "Don't forget to create the object from the Scanner class in order to gather input from the user for this task.");
		}//end if

		//when user doesn't create the double variable called myAge on the second line of code
		if(!lineInput[1].equals("double myAge;"))
		{
			userOutputL6T2[0]=addErrorMessage(userOutputL6T2[0], "You must create a double variable called myAge on the second line of code.");
		}//end if

		if(lineInput[2].startsWith("System.out.println(\"") && lineInput[2].endsWith("\");"))
		{
			for(int i=20; i<lineInput[2].length()-3; i++)
			{
				question+=lineInput[2].charAt(i);
			}//end for loop

			if(question.equals(""))
			{
				userOutputL6T2[0]=addErrorMessage(userOutputL6T2[0], "Don't forget to tell/ask user what they should input (their age).");
			}//end inside if
		}//end outside if
		else
		{
			userOutputL6T2[0]=addErrorMessage(userOutputL6T2[0], "Don't forget to print to the screen and tell/ask user what they should input (their age) on the third line of code.");
		}//end else

		lineInput[3]=lineInput[3].replaceAll("\\s+","");

		if(!lineInput[3].equals("myAge="+scannerObject+".nextDouble();"))
		{
			userOutputL6T2[0]=addErrorMessage(userOutputL6T2[0], "Don't forget to allow the user to initialize myAge on the fourth line of code.");
		}//end if

		if(!lineInput[4].equals("System.out.println(myAge);"))
		{
			userOutputL6T2[0]=addErrorMessage(userOutputL6T2[0], "Don't forget to print the value of myAge on the fifth line of code.");
		}//end if

		if(!lineInput[5].equals(scannerObject+".close();"))
		{
			userOutputL6T2[0]=addErrorMessage(userOutputL6T2[0], "Don't forget to close your scanner object on the sixth line of code.");
		}//end if

		//when there has been no errors detected in the user's code yet
		if(userOutputL6T2[0].equals(""))
		{
			userOutputL6T2[0]=question;
		}//end if

		userOutputL6T2[1]=outputFontFormat(userOutputL6T2[0], fontSize);

		//returning the array containing the text and style for the output text area in the correct task
		return userOutputL6T2;
	}//end submitL6T2 method


	/* Method Author: Brooke Cronin
	 * Method Name: submitL6T3
	 * Description: Breaks apart user's input by line, checks to make sure all variables are created and initialized correctly, checks that user printed the result variable to screen, and prints all errors found in user's code or the value of result;
	 * Parameters: TextArea taUserInputL6T3
	 * Returns: String [] userOutputL6T3
	 * Throws: ScriptException
	 */

	public static String [] submitL6T3(TextArea taUserInputL6T3, String fontSize) throws ScriptException
	{
		//creating and initializing the array to store the user's output and the text style as well as all variables to be used in this method
		String [] lineInput = splitInputByLine(taUserInputL6T3.getText(), 7, "L6T3");
		String question="";
		String scannerObject="";
		String [] userOutputL6T3=new String[2];
		userOutputL6T3[0]="";
		userOutputL6T3[1]="";

		if(lineInput[0].equals("not valid"))
		{
			userOutputL6T3[0]=addErrorMessage(userOutputL6T3[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL6T3[1]=outputFontFormat(userOutputL6T3[0], fontSize);

			//returning the array containing the text and style for the output text area in L6T3
			return userOutputL6T3;
		}
		
		if(lineInput[6].startsWith("Your code should contain exactly"))
		{
			userOutputL6T3[0]=addErrorMessage(userOutputL6T3[0],lineInput[6]);
		}

		scannerObject=scannerObjectName(lineInput[0]);

		//when user has not correctly created the scanner object
		if(scannerObject.equals(""))
		{
			userOutputL6T3[0]=addErrorMessage(userOutputL6T3[0], "Don't forget to create the object from the Scanner class in order to gather input from the user for this task.");
		}//end if

		//when user doesn't create the string variable called myFirstName on the second line of code
		if(!lineInput[1].equals("String myFirstName;"))
		{
			userOutputL6T3[0]=addErrorMessage(userOutputL6T3[0], "You must create a string variable called myFirstName on the second line of code.");
		}//end if

		if(lineInput[2].startsWith("System.out.println(\"") && lineInput[2].endsWith("\");"))
		{
			for(int i=20; i<lineInput[2].length()-3; i++)
			{
				question+=lineInput[2].charAt(i);
			}//end for loop

			if(question.equals(""))
			{
				userOutputL6T3[0]=addErrorMessage(userOutputL6T3[0], "Don't forget to tell/ask user what they should input (their first name).");
			}//end inside if
		}//end outside if
		else
		{
			userOutputL6T3[0]=addErrorMessage(userOutputL6T3[0], "Don't forget to print to the screen and tell/ask user what they should input (their first name) on the third line of code.");
		}//end else

		lineInput[3]=lineInput[3].replaceAll("\\s+","");

		if(!lineInput[3].equals("myFirstName="+scannerObject+".nextLine();"))
		{
			userOutputL6T3[0]=addErrorMessage(userOutputL6T3[0], "Don't forget to allow the user to initialize myFirstName on the fourth line of code.");
		}//end if

		if(!lineInput[4].equals("System.out.println(myFirstName);"))
		{
			userOutputL6T3[0]=addErrorMessage(userOutputL6T3[0], "Don't forget to print the value of myFirstName on the fifth line of code.");
		}//end if

		if(!lineInput[5].equals(scannerObject+".close();"))
		{
			userOutputL6T3[0]=addErrorMessage(userOutputL6T3[0], "Don't forget to close your scanner object on the sixth line of code.");
		}//end if

		//when there has been no errors detected in the user's code yet
		if(userOutputL6T3[0].equals(""))
		{
			userOutputL6T3[0]=question;
		}//end if

		userOutputL6T3[1]=outputFontFormat(userOutputL6T3[0], fontSize);

		//returning the array containing the text and style for the output text area in the correct task
		return userOutputL6T3;
	}//end submitL6T3 method


	/* Method Author: Brooke Cronin
	 * Method Name: submitL6T4
	 * Description: Breaks apart user's input by line, checks to make sure all variables are created and initialized correctly, checks that user printed the result variable to screen, and prints all errors found in user's code or the value of result;
	 * Parameters: TextArea taUserInputL6T4
	 * Returns: String [] userOutputL6T4
	 * Throws: ScriptException
	 */

	public static String [] submitL6T4(TextArea taUserInputL6T4, String fontSize) throws ScriptException
	{
		//creating and initializing the array to store the user's output and the text style as well as all variables to be used in this method
		String [] lineInput = splitInputByLine(taUserInputL6T4.getText(), 10, "L6T4");
		String question1="";
		String question2="";
		String scannerObject="";
		String [] userOutputL6T4=new String[3];
		userOutputL6T4[0]="";
		userOutputL6T4[1]="";
		userOutputL6T4[2]="";

		if(lineInput[0].equals("not valid"))
		{
			userOutputL6T4[0]=addErrorMessage(userOutputL6T4[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL6T4[1]=outputFontFormat(userOutputL6T4[0], fontSize);

			//returning the array containing the text and style for the output text area in L6T4
			return userOutputL6T4;
		}
		
		if(lineInput[9].startsWith("Your code should contain exactly"))
		{
			userOutputL6T4[0]=addErrorMessage(userOutputL6T4[0],lineInput[9]);
		}

		scannerObject=scannerObjectName(lineInput[0]);

		//when user has not correctly created the scanner object
		if(scannerObject.equals(""))
		{
			userOutputL6T4[0]=addErrorMessage(userOutputL6T4[0], "Don't forget to create the object from the Scanner class in order to gather input from the user for this task.");
		}//end if

		//when user doesn't create the integer variable called favNum on the second line of code
		if(!lineInput[1].equals("int numOfFamilyMembers;"))
		{
			userOutputL6T4[0]=addErrorMessage(userOutputL6T4[0], "You must create an integer variable called numOfFamilyMembers on the second line of code.");
		}//end if

		//when user doesn't create the integer variable called favNum on the second line of code
		if(!lineInput[2].equals("String favFood;"))
		{
			userOutputL6T4[0]=addErrorMessage(userOutputL6T4[0], "You must create a string variable called favFood on the third line of code.");
		}//end if

		if(lineInput[3].startsWith("System.out.println(\"") && lineInput[3].endsWith("\");"))
		{
			for(int i=20; i<lineInput[3].length()-3; i++)
			{
				question1+=lineInput[3].charAt(i);
			}//end for loop

			if(question1.equals(""))
			{
				userOutputL6T4[0]=addErrorMessage(userOutputL6T4[0], "Don't forget to tell/ask user what they should input (the number of people in their family).");
			}//end inside if
		}//end outside if
		else
		{
			userOutputL6T4[0]=addErrorMessage(userOutputL6T4[0], "Don't forget to print to the screen and tell/ask user what they should input (the number of people in their family) on the third line of code.");
		}//end else

		lineInput[4]=lineInput[4].replaceAll("\\s+","");
		lineInput[7]=lineInput[7].replaceAll("\\s+","");

		if(!lineInput[4].equals("numOfFamilyMembers="+scannerObject+".nextInt();"))
		{
			userOutputL6T4[0]=addErrorMessage(userOutputL6T4[0], "Don't forget to allow the user to initialize numOfFamilyMembers on the fourth line of code.");
		}//end if

		if(!lineInput[5].equals(scannerObject+".nextLine();"))
		{
			userOutputL6T4[0]=addErrorMessage(userOutputL6T4[0], "Don't forget accept the enter after the user enters the integer and before they enter a string value.");
		}//end if

		if(lineInput[6].startsWith("System.out.println(\"") && lineInput[6].endsWith("\");"))
		{
			for(int i=20; i<lineInput[6].length()-3; i++)
			{
				question2+=lineInput[6].charAt(i);
			}//end for loop

			if(question2.equals(""))
			{
				userOutputL6T4[0]=addErrorMessage(userOutputL6T4[0], "Don't forget to tell/ask user what they should input (their favourite food).");
			}//end inside if
		}//end outside if
		else
		{
			userOutputL6T4[0]=addErrorMessage(userOutputL6T4[0], "Don't forget to print to the screen and tell/ask user what they should input (their favourite food) on the seventh line of code.");
		}//end else

		if(!lineInput[7].equals("favFood="+scannerObject+".nextLine();"))
		{
			userOutputL6T4[0]=addErrorMessage(userOutputL6T4[0], "Don't forget to allow the user to initialize favFood on the eighth line of code.");
		}//end if

		if(!lineInput[8].equals(scannerObject+".close();"))
		{
			userOutputL6T4[0]=addErrorMessage(userOutputL6T4[0], "Don't forget to close your scanner object on the sixth line of code.");
		}//end if

		//when there has been no errors detected in the user's code yet
		if(userOutputL6T4[0].equals(""))
		{
			userOutputL6T4[0]=question1;
			userOutputL6T4[1]=question2;
		}//end if

		userOutputL6T4[2]=outputFontFormat(userOutputL6T4[0], fontSize);

		//returning the array containing the text and style for the output text area in the correct task
		return userOutputL6T4;
	}//end submitL6T4 method
	
	
	/* Method Author: Brooke Cronin
	 * Method Name: submitL7T1
	 * Description: Breaks apart user's input by line, checks to make sure all variables are created and initialized correctly, checks that user printed the result variable to screen, and prints all errors found in user's code or the value of result;
	 * Parameters: TextArea taUserInputL7T1
	 * Returns: String [] userOutputL7T1
	 * Throws: ScriptException
	 */

	public static String [] submitL7T1(TextArea taUserInputL7T1, String fontSize) throws ScriptException
	{
		//creating and initializing the array to store the user's output and the text style as well as all variables to be used in this method
		String [] lineInput = splitInputByLine(taUserInputL7T1.getText(), 15, "L7T1");
		String output="";
		String sA="";
		String sB="";
		int iA=0;
		int iB=0;
		boolean isInta;
		boolean isIntb;
		String scannerObject="";
		String [] userOutputL7T1=new String[2];
		userOutputL7T1[0]="";
		userOutputL7T1[1]="";

		if(lineInput[0].equals("not valid"))
		{
			userOutputL7T1[0]=addErrorMessage(userOutputL7T1[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL7T1[1]=outputFontFormat(userOutputL7T1[0], fontSize);

			//returning the array containing the text and style for the output text area in L7T1
			return userOutputL7T1;
		}
		
		if(lineInput[14].startsWith("Your code should contain exactly"))
		{
			userOutputL7T1[0]=addErrorMessage(userOutputL7T1[0],lineInput[14]);
		}

		//when user doesn't create the integer variable called a on the second line of code
		if(lineInput[0].startsWith("int a=") || lineInput[0].startsWith("int a ="))
		{
			if(lineInput[0].endsWith(";"))
			{
				for(int i=5; i<lineInput[1].length()-1; i++)
				{
					if(lineInput[0].charAt(i)!=' ' && lineInput[0].charAt(i)!='=')
					{
						sA+=lineInput[0].charAt(i);
					}//end if
				}//end for loop
			}//end inside if
		}//end outside if
		else
		{
			userOutputL7T1[0]=addErrorMessage(userOutputL7T1[0], "You must create and initialize an integer variable called a on the first line of code.");
		}//end else

		if(userOutputL7T1[0].equals(""))
		{
			isInta=ErrorChecks.checkIfInt(sA);
			if(!isInta)
			{
				userOutputL7T1[0]=addErrorMessage(userOutputL7T1[0], "Don't forget to initialize a as an integer on the first line of code.");
			}//end inside if
			else
			{
				iA=Integer.parseInt(sA);

				//when user doesn't create the integer variable called b on the third line of code
				if(lineInput[1].startsWith("int b=") || lineInput[1].startsWith("int b ="))
				{
					if(lineInput[1].endsWith(";"))
					{
						for(int i=5; i<lineInput[1].length()-1; i++)
						{
							if(lineInput[1].charAt(i)!=' ' && lineInput[1].charAt(i)!='=')
							{
								sB+=lineInput[1].charAt(i);
							}//end if
						}//end for loop
					}//end inside if
				}//end outside if
				else
				{
					userOutputL7T1[0]=addErrorMessage(userOutputL7T1[0], "You must create and initialize an integer variable called b on the second line of code.");
				}//end else

				if(userOutputL7T1[0].equals(""))
				{
					isIntb=ErrorChecks.checkIfInt(sB);
					if(!isIntb)
					{
						userOutputL7T1[0]=addErrorMessage(userOutputL7T1[0], "Don't forget to initialize b as an integer on the second line of code.");
					}//end inside if
					else
					{
						iB=Integer.parseInt(sB);
					}//end else
				}//end outside if
			}//end else
		}//end outside if

		if(lineInput[2].startsWith("if(") && lineInput[2].endsWith(")") && lineInput[3].equals("{") && lineInput[5].equals("}"))
		{
			lineInput[2]=lineInput[2].replaceAll("\\s+","");
			if(lineInput[2].equals("if(a>b)") || lineInput[2].equals("if(b<a)"))
			{
				if(lineInput[4].equals("System.out.println(\"Since \"+a+\" is greater than \"+b+\", a is greater than b.\");") && userOutputL7T1[0].equals(""))
				{
					if(iA>iB)
					{
						output="Since "+iA+" is greater than "+iB+", a is greater than b.";
					}//end if
				}//end if
				else
				{
					userOutputL7T1[0]=addErrorMessage(userOutputL7T1[0], "Don't forget to add the code to print to the correct statement when the value of a is greater than b in the first if statement.");
				}//end else
			}//end if
			else if(lineInput[2].equals("if(a<b)") || lineInput[2].equals("if(b>a)"))
			{
				if(lineInput[4].equals("System.out.println(\"Since \"+a+\" is less than \"+b+\", a is less than b.\");") && userOutputL7T1[0].equals(""))
				{
					if(iA<iB)
					{
						output="Since "+iA+" is less than "+iB+", a is less than b.";
					}//end if
				}//end if
				else
				{
					userOutputL7T1[0]=addErrorMessage(userOutputL7T1[0], "Don't forget to add the code to print to the correct statement when the value of a is less than b in the first if statement.");
				}//end else
			}//end if
			else if(lineInput[2].equals("if(a==b)") || lineInput[2].equals("if(b==a)"))
			{
				if(lineInput[4].equals("System.out.println(\"Since \"+a+\" is equal to \"+b+\", a is equal to b.\");") && userOutputL7T1[0].equals(""))
				{
					if(iA==iB)
					{
						output="Since "+iA+" is equal to "+iB+", a is equal to b.";
					}//end if
				}//end if
				else
				{
					userOutputL7T1[0]=addErrorMessage(userOutputL7T1[0], "Don't forget to add the code to print to the correct statement when the value of a is equal to b in the first if statement.");
				}//end else
			}//end else if
		}//end if
		else
		{
			userOutputL7T1[0]=addErrorMessage(userOutputL7T1[0], "Don't forget to add the if statement with a comparison for the values of a and b starting on the third line of code.");
		}//end else

		if(lineInput[6].startsWith("else if(") && lineInput[6].endsWith(")") && lineInput[7].equals("{") && lineInput[9].equals("}"))
		{
			lineInput[6]=lineInput[6].replaceAll("\\s+","");
			if(lineInput[6].equals("elseif(a>b)") || lineInput[2].equals("elseif(b<a)"))
			{
				if(!lineInput[2].equals("if(a>b)") && !lineInput[2].equals("if(b<a)"))
				{
					if(lineInput[8].equals("System.out.println(\"Since \"+a+\" is greater than \"+b+\", a is greater than b.\");") && userOutputL7T1[0].equals(""))
					{
						if(iA>iB)
						{
							output="Since "+iA+" is greater than "+iB+", a is greater than b.";
						}//end if
					}//end if
					else
					{
						userOutputL7T1[0]=addErrorMessage(userOutputL7T1[0], "Don't forget to add the code to print to the correct statement when the value of a is greater than b in the first if statement.");
					}//end else
				}//end if
				else
				{
					userOutputL7T1[0]=addErrorMessage(userOutputL7T1[0], "You have already compared when the value of a is greater than the value of b.");
				}//end else
			}//end if
			else if(lineInput[6].equals("elseif(a<b)") || lineInput[6].equals("elseif(b>a)"))
			{
				if(!lineInput[2].equals("if(a<b)") && !lineInput[2].equals("if(b>a)"))
				{
					if(lineInput[8].equals("System.out.println(\"Since \"+a+\" is less than \"+b+\", a is less than b.\");") && userOutputL7T1[0].equals(""))
					{
						if(iA<iB)
						{
							output="Since "+iA+" is less than "+iB+", a is less than b.";
						}//end if
					}//end if
					else
					{
						userOutputL7T1[0]=addErrorMessage(userOutputL7T1[0], "Don't forget to add the code to print to the correct statement when the value of a is less than b in the first if statement.");
					}//end else
				}//end if
				else
				{
					userOutputL7T1[0]=addErrorMessage(userOutputL7T1[0], "You have already compared when the value of a is less than the value of b.");
				}//end else
			}//end else if
			else if(lineInput[6].equals("elseif(a==b)") || lineInput[6].equals("elseif(b==a)"))
			{
				if(!lineInput[2].equals("if(a==b)") && !lineInput[2].equals("if(b==a)"))
				{
					if(lineInput[8].equals("System.out.println(\"Since \"+a+\" is equal to \"+b+\", a is equal to b.\");") && userOutputL7T1[0].equals(""))
					{
						if(iA==iB)
						{
							output="Since "+iA+" is equal to "+iB+", a is equal to b.";
						}//end if
					}//end if

					else
					{
						userOutputL7T1[0]=addErrorMessage(userOutputL7T1[0], "Don't forget to add the code to print to the correct statement when the value of a is equal to b in the first if statement.");
					}//end else
				}//end if
				else
				{
					userOutputL7T1[0]=addErrorMessage(userOutputL7T1[0], "You have already compared when the value of a is equal to the value of b.");
				}//end else
			}//end else if
		}//end if

		else
		{
			userOutputL7T1[0]=addErrorMessage(userOutputL7T1[0], "Don't forget to add the else if statement with a comparison for the values of a and b starting on the seventh line of code.");
		}//end else

		if(lineInput[10].equals("else") && lineInput[11].equals("{") && lineInput[13].equals("}"))
		{
			if(!lineInput[2].equals("if(a>b)") && !lineInput[2].equals("if(b<a)") && !lineInput[6].equals("elseif(a>b)") && !lineInput[6].equals("elseif(b<a)"))
			{
				if(lineInput[12].equals("System.out.println(\"Since \"+a+\" is greater than \"+b+\", a is greater than b.\");") && userOutputL7T1[0].equals(""))
				{
					if(iA>iB)
					{
						output="Since "+iA+" is greater than "+iB+", a is greater than b.";
					}//end if
				}//end if
				else
				{
					userOutputL7T1[0]=addErrorMessage(userOutputL7T1[0], "Don't forget to add the code to print to the correct statement when the value of a is greater than b in the else statement.");
				}//end else
			}//end if

			if(!lineInput[2].equals("if(a<b)") && !lineInput[2].equals("if(b>a)") && !lineInput[6].equals("elseif(a<b)") && !lineInput[6].equals("elseif(b>a)"))
			{
				if(lineInput[12].equals("System.out.println(\"Since \"+a+\" is less than \"+b+\", a is less than b.\");") && userOutputL7T1[0].equals(""))
				{
					if(iA<iB)
					{
						output="Since "+iA+" is less than "+iB+", a is less than b.";
					}//end if
				}//end if
				else
				{
					userOutputL7T1[0]=addErrorMessage(userOutputL7T1[0], "Don't forget to add the code to print to the correct statement when the value of a is less than b in the else statement.");
				}//end else
			}//end if

			if(!lineInput[2].equals("if(a==b)") && !lineInput[2].equals("if(b==a)") && !lineInput[6].equals("elseif(a==b)") && !lineInput[6].equals("elseif(b==a)"))
			{
				if(lineInput[12].equals("System.out.println(\"Since \"+a+\" is equal to \"+b+\", a is equal to b.\");") && userOutputL7T1[0].equals(""))
				{
					if(iA==iB)
					{
						output="Since "+iA+" is equal to "+iB+", a is equal to b.";
					}//end if
				}//end if

				else
				{
					userOutputL7T1[0]=addErrorMessage(userOutputL7T1[0], "Don't forget to add the code to print to the correct statement when the value of a is equal to b in the else statement.");
				}//end else
			}//end if
		}//end if

		else
		{
			userOutputL7T1[0]=addErrorMessage(userOutputL7T1[0], "Don't forget to add the else statement with a comparison for the values of a and b starting on the eleventh line of code.");
		}//end else

		//when there has been no errors detected in the user's code yet
		if(userOutputL7T1[0].equals(""))
		{
			userOutputL7T1[0]=output;
		}//end if

		userOutputL7T1[1]=outputFontFormat(userOutputL7T1[0], fontSize);

		//returning the array containing the text and style for the output text area in the correct task
		return userOutputL7T1;
	}//end submitL7T1 method


	/* Method Author: Brooke Cronin
	 * Method Name: submitL7T2
	 * Description: Breaks apart user's input by line, checks to make sure all variables are created and initialized correctly, checks that user printed the result variable to screen, and prints all errors found in user's code or the value of result;
	 * Parameters: TextArea taUserInputL7T2
	 * Returns: String [] userOutputL7T2
	 * Throws: ScriptException
	 */

	public static String [] submitL7T2(TextArea taUserInputL7T2, String fontSize) throws ScriptException
	{
		//creating and initializing the array to store the user's output and the text style as well as all variables to be used in this method
		String [] lineInput = splitInputByLine(taUserInputL7T2.getText(), 29, "L7T2");
		String question1="";
		String question2="";
		String question3="";
		String scannerObject="";
		String [] userOutputL7T2=new String[4];
		userOutputL7T2[0]="";
		userOutputL7T2[1]="";
		userOutputL7T2[2]="";
		userOutputL7T2[3]="";
		
		if(lineInput[0].equals("not valid"))
		{
			userOutputL7T2[0]=addErrorMessage(userOutputL7T2[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL7T2[1]=outputFontFormat(userOutputL7T2[0], fontSize);

			//returning the array containing the text and style for the output text area in L7T2
			return userOutputL7T2;
		}
		
		if(lineInput[28].startsWith("Your code should contain exactly"))
		{
			userOutputL7T2[0]=addErrorMessage(userOutputL7T2[0],lineInput[28]);
		}

		scannerObject=scannerObjectName(lineInput[0]);

		//when user has not correctly created the scanner object
		if(scannerObject.equals(""))
		{
			userOutputL7T2[0]=addErrorMessage(userOutputL7T2[0], "Don't forget to create the object from the Scanner class in order to gather input from the user for this task.");
		}//end if

		//when user doesn't create the integer variable called scoreCounter on the second line of code
		if(!lineInput[1].startsWith("int scoreCounter") || !lineInput[1].endsWith("0;"))
		{
			lineInput[1]=lineInput[1].replaceAll("\\s+","");
			if(!lineInput[1].equals("intscoreCounter=0;"))
			{
				userOutputL7T2[0]=addErrorMessage(userOutputL7T2[0], "You must create an integer variable called scoreCounter on the second line of code and initialize it to 0.");
			}
		}//end if

		//when user doesn't create the string variable called userResponse1 on the third line of code
		if(!lineInput[2].equals("String userResponse1;"))
		{
			userOutputL7T2[0]=addErrorMessage(userOutputL7T2[0], "You must create a string variable called userResponse1 on the third line of code.");
		}//end if

		if(lineInput[3].startsWith("String correctResponse1") && lineInput[3].endsWith(";"))
		{
			lineInput[3]=lineInput[3].replaceAll("\\s+","");
			if(!lineInput[3].equalsIgnoreCase("StringcorrectResponse1=\"Ottawa\";"))
			{
				userOutputL7T2[0]=addErrorMessage(userOutputL7T2[0], "You must create a string variable called correctResponse1 on the fourth line of code and initialize it to the capital of Canada.");
			}//end inside if
		}//end outside if

		//when user doesn't create the string variable called userResponse2 on the fifth line of code
		if(!lineInput[4].equals("String userResponse2;"))
		{
			userOutputL7T2[0]=addErrorMessage(userOutputL7T2[0], "You must create a string variable called userResponse2 on the fifth line of code.");
		}//end if

		if(lineInput[5].startsWith("String correctResponse2") && lineInput[5].endsWith(";"))
		{
			lineInput[5]=lineInput[5].replaceAll("\\s+","");
			if(!lineInput[5].equalsIgnoreCase("StringcorrectResponse2=\"Paris\";"))
			{
				userOutputL7T2[0]=addErrorMessage(userOutputL7T2[0], "You must create a string variable called correctResponse2 on the sixth line of code and initialize it to the capital of France.");
			}//end inside if
		}//end outside if

		//when user doesn't create the string variable called userResponse3 on the seventh line of code
		if(!lineInput[6].equals("String userResponse3;"))
		{
			userOutputL7T2[0]=addErrorMessage(userOutputL7T2[0], "You must create a string variable called userResponse3 on the seventh line of code.");
		}//end if

		if(lineInput[7].startsWith("String correctResponse3") && lineInput[7].endsWith(";"))
		{
			lineInput[7]=lineInput[7].replaceAll("\\s+","");
			if(!lineInput[7].equalsIgnoreCase("StringcorrectResponse3=\"Dublin\";"))
			{
				userOutputL7T2[0]=addErrorMessage(userOutputL7T2[0], "You must create a string variable called correctResponse3 on the eighth line of code and initialize it to the capital of Ireland.");
			}//end inside if
		}//end outside if

		if(lineInput[8].startsWith("System.out.println(\"") && lineInput[8].endsWith("\");"))
		{
			for(int i=20; i<lineInput[8].length()-3; i++)
			{
				question1+=lineInput[8].charAt(i);
			}//end for loop

			if(question1.equals(""))
			{
				userOutputL7T2[0]=addErrorMessage(userOutputL7T2[0], "Don't forget to tell/ask user what they should input (the capital of Canada).");
			}//end inside if
		}//end outside if
		else
		{
			userOutputL7T2[0]=addErrorMessage(userOutputL7T2[0], "Don't forget to print to the screen and tell/ask user what they should input (the capital of Canada) on the ninth line of code.");
		}//end else

		lineInput[9]=lineInput[9].replaceAll("\\s+","");

		if(!lineInput[9].equals("userResponse1="+scannerObject+".nextLine();"))
		{
			userOutputL7T2[0]=addErrorMessage(userOutputL7T2[0], "Don't forget to allow the user to initialize userResponse1 on the tenth line of code.");
		}//end if

		if(lineInput[10].equals("if(userResponse1.equalsIgnoreCase(correctResponse1))") && lineInput[11].equals("{") && lineInput[13].equals("}"))
		{
			if(lineInput[12].startsWith("scoreCounter") && lineInput[12].endsWith(";"))
			{
				lineInput[12]=lineInput[12].replaceAll("\\s+","");
				if(lineInput[12].equals("scoreCounter++;") || lineInput[12].equals("scoreCounter+=1;") || lineInput[12].equals("scoreCounter=scoreCounter+1;"))
				{
				}//end inside if
				else
				{
					userOutputL7T2[0]=addErrorMessage(userOutputL7T2[0], "Don't forget to update the value of scoreCounter when the user has correctly answered the first question.");
				}//end else
			}//end outside if
			else
			{
				userOutputL7T2[0]=addErrorMessage(userOutputL7T2[0], "Don't forget to update the value of scoreCounter when the user has correctly answered the first question.");
			}//end else
		}//end if
		else
		{
			userOutputL7T2[0]=addErrorMessage(userOutputL7T2[0], "Don't forget to check if the user has answered the first question correctly.");
		}//end else

		if(lineInput[14].startsWith("System.out.println(\"") && lineInput[14].endsWith("\");"))
		{
			for(int i=20; i<lineInput[14].length()-3; i++)
			{
				question2+=lineInput[14].charAt(i);
			}//end for loop

			if(question2.equals(""))
			{
				userOutputL7T2[0]=addErrorMessage(userOutputL7T2[0], "Don't forget to tell/ask user what they should input (the capital of France).");
			}//end inside if
		}//end outside if
		else
		{
			userOutputL7T2[0]=addErrorMessage(userOutputL7T2[0], "Don't forget to print to the screen and tell/ask user what they should input (the capital of France) on the fifteenth line of code.");
		}//end else

		lineInput[15]=lineInput[15].replaceAll("\\s+","");

		if(!lineInput[15].equals("userResponse2="+scannerObject+".nextLine();"))
		{
			userOutputL7T2[0]=addErrorMessage(userOutputL7T2[0], "Don't forget to allow the user to initialize userResponse2 on the sixteenth line of code.");
		}//end if

		if(lineInput[16].equals("if(userResponse2.equalsIgnoreCase(correctResponse2))") && lineInput[17].equals("{") && lineInput[19].equals("}"))
		{
			if(lineInput[18].startsWith("scoreCounter") && lineInput[18].endsWith(";"))
			{
				lineInput[18]=lineInput[18].replaceAll("\\s+","");
				if(lineInput[18].equals("scoreCounter++;") || lineInput[18].equals("scoreCounter+=1;") || lineInput[18].equals("scoreCounter=scoreCounter+1;"))
				{
				}//end inside if
				else
				{
					userOutputL7T2[0]=addErrorMessage(userOutputL7T2[0], "Don't forget to update the value of scoreCounter when the user has correctly answered the second question.");
				}//end else
			}//end outside if
			else
			{
				userOutputL7T2[0]=addErrorMessage(userOutputL7T2[0], "Don't forget to update the value of scoreCounter when the user has correctly answered the second question.");
			}//end else
		}//end if
		else
		{
			userOutputL7T2[0]=addErrorMessage(userOutputL7T2[0], "Don't forget to check if the user has answered the second question correctly.");
		}//end else

		if(lineInput[20].startsWith("System.out.println(\"") && lineInput[20].endsWith("\");"))
		{
			for(int i=20; i<lineInput[20].length()-3; i++)
			{
				question3+=lineInput[20].charAt(i);
			}//end for loop

			if(question3.equals(""))
			{
				userOutputL7T2[0]=addErrorMessage(userOutputL7T2[0], "Don't forget to tell/ask user what they should input (the capital of Ireland).");
			}//end inside if
		}//end outside if
		else
		{
			userOutputL7T2[0]=addErrorMessage(userOutputL7T2[0], "Don't forget to print to the screen and tell/ask user what they should input (the capital of Ireland) on the twentyith line of code.");
		}//end else

		lineInput[21]=lineInput[21].replaceAll("\\s+","");

		if(!lineInput[21].equals("userResponse3="+scannerObject+".nextLine();"))
		{
			userOutputL7T2[0]=addErrorMessage(userOutputL7T2[0], "Don't forget to allow the user to initialize userResponse3 on the twenty-first line of code.");
		}//end if

		if(lineInput[22].equals("if(userResponse3.equalsIgnoreCase(correctResponse3))") && lineInput[23].equals("{") && lineInput[25].equals("}"))
		{
			if(lineInput[24].startsWith("scoreCounter") && lineInput[24].endsWith(";"))
			{
				lineInput[24]=lineInput[24].replaceAll("\\s+","");
				if(lineInput[24].equals("scoreCounter++;") || lineInput[24].equals("scoreCounter+=1;") || lineInput[24].equals("scoreCounter=scoreCounter+1;"))
				{
				}//end inside if
				else
				{
					userOutputL7T2[0]=addErrorMessage(userOutputL7T2[0], "Don't forget to update the value of scoreCounter when the user has correctly answered the third question.");
				}//end else
			}//end outside if
			else
			{
				userOutputL7T2[0]=addErrorMessage(userOutputL7T2[0], "Don't forget to update the value of scoreCounter when the user has correctly answered the third question.");
			}//end else
		}//end if
		else
		{
			userOutputL7T2[0]=addErrorMessage(userOutputL7T2[0], "Don't forget to check if the user has answered the third question correctly.");
		}//end else

		lineInput[26]=lineInput[26].replaceAll("\\s+","");
		if(!lineInput[26].equals("System.out.println(scoreCounter+\"/3\");"))
		{
			userOutputL7T2[0]=addErrorMessage(userOutputL7T2[0], "Don't forget to print the user's score in the proper format at the end.");
		}


		if(!lineInput[27].equals(scannerObject+".close();"))
		{
			userOutputL7T2[0]=addErrorMessage(userOutputL7T2[0], "Don't forget to close your scanner object on the twenty-seventh line of code.");
		}//end if

		//when there has been no errors detected in the user's code yet
		if(userOutputL7T2[0].equals(""))
		{
			userOutputL7T2[0]=question1;
			userOutputL7T2[1]=question2;
			userOutputL7T2[2]=question3;
		}//end if

		userOutputL7T2[3]=outputFontFormat(userOutputL7T2[0], fontSize);

		//returning the array containing the text and style for the output text area in the correct task
		return userOutputL7T2;
	}//end submitL7T2 method


	/* Method Author: Brooke Cronin
	 * Method Name: submitL7T3
	 * Description: Breaks apart user's input by line, checks to make sure all variables are created and initialized correctly, checks that user printed the result variable to screen, and prints all errors found in user's code or the value of result;
	 * Parameters: TextArea taUserInputL7T3
	 * Returns: String [] userOutputL7T3
	 * Throws: ScriptException
	 */

	public static String [] submitL7T3(TextArea taUserInputL7T3, String fontSize) throws ScriptException
	{
		//creating and initializing the array to store the user's output and the text style as well as all variables to be used in this method
		String [] lineInput = splitInputByLine(taUserInputL7T3.getText(), 48, "L7T3");
		String question="";
		String errorStatement="";
		String scannerObject="";
		String [] userOutputL7T3=new String[3];
		userOutputL7T3[0]="";
		userOutputL7T3[1]="";
		userOutputL7T3[2]="";

		if(lineInput[0].equals("not valid"))
		{
			userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL7T3[1]=outputFontFormat(userOutputL7T3[0], fontSize);

			//returning the array containing the text and style for the output text area in L7T3
			return userOutputL7T3;
		}
		
		if(lineInput[47].startsWith("Your code should contain exactly"))
		{
			userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0],lineInput[47]);
		}

		scannerObject=scannerObjectName(lineInput[0]);

		//when user has not correctly created the scanner object
		if(scannerObject.equals(""))
		{
			userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "Don't forget to create the object from the Scanner class in order to gather input from the user for this task.");
		}//end if

		//when user doesn't create the string variable called myFirstName on the second line of code
		if(lineInput[1].startsWith("int month") && lineInput[1].endsWith("0;"))
		{
			lineInput[1]=lineInput[1].replaceAll("\\s+","");
			if(!lineInput[1].equals("intmonth=0;"))
			{
				userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "You must create an integer variable called month on the second line of code and initialize it to 0.");
			}//end inside if
		}//end outside if
		else
		{
			userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "You must create an integer variable called month on the second line of code and initialize it to 0.");
		}//end else

		if(lineInput[2].startsWith("System.out.println(\"") && lineInput[2].endsWith("\");"))
		{
			for(int i=20; i<lineInput[2].length()-3; i++)
			{
				question+=lineInput[2].charAt(i);
			}//end for loop

			if(question.equals(""))
			{
				userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "Don't forget to tell/ask user what they should input (the month number).");
			}//end inside if
		}//end outside if
		else
		{
			userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "Don't forget to print to the screen and tell/ask user what they should input (the month number) on the third line of code.");
		}//end else

		lineInput[3]=lineInput[3].replaceAll("\\s+","");

		if(!lineInput[3].equals("month="+scannerObject+".nextInt();"))
		{
			userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "Don't forget to allow the user to initialize month on the fourth line of code.");
		}//end if

		if(lineInput[4].startsWith("switch") && lineInput[4].endsWith(")"))
		{
			lineInput[4]=lineInput[4].replaceAll("\\s+","");
			if(lineInput[4].equals("switch(month)"))
			{
				if(lineInput[5].equals("{") && lineInput[45].equals("}"))
				{
					if(lineInput[6].equals("case 1:") && lineInput[8].equals("break;"))
					{
						if(!lineInput[7].equals("System.out.println(\"January\");"))
						{
							userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "Don't forget to print the correct month (with a capital at the start) when the user has entered 1 for the month number.");
						}
					}

					else
					{
						userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "Don't forget to add the case to print the month name user has entered 1 for the month number.");
					}//end else

					if(lineInput[9].equals("case 2:") && lineInput[11].equals("break;"))
					{
						if(!lineInput[10].equals("System.out.println(\"February\");"))
						{
							userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "Don't forget to print the correct month (with a capital at the start) when the user has entered 2 for the month number.");
						}
					}

					else
					{
						userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "Don't forget to add the case to print the month name user has entered 2 for the month number.");
					}//end else

					if(lineInput[12].equals("case 3:") && lineInput[14].equals("break;"))
					{
						if(!lineInput[13].equals("System.out.println(\"March\");"))
						{
							userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "Don't forget to print the correct month (with a capital at the start) when the user has entered 3 for the month number.");
						}
					}

					else
					{
						userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "Don't forget to add the case to print the month name user has entered 3 for the month number.");
					}//end else

					if(lineInput[15].equals("case 4:") && lineInput[17].equals("break;"))
					{
						if(!lineInput[16].equals("System.out.println(\"April\");"))
						{
							userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "Don't forget to print the correct month (with a capital at the start) when the user has entered 4 for the month number.");
						}
					}

					else
					{
						userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "Don't forget to add the case to print the month name user has entered 4 for the month number.");
					}//end else

					if(lineInput[18].equals("case 5:") && lineInput[20].equals("break;"))
					{
						if(!lineInput[19].equals("System.out.println(\"May\");"))
						{
							userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "Don't forget to print the correct month (with a capital at the start) when the user has entered 5 for the month number.");
						}
					}

					else
					{
						userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "Don't forget to add the case to print the month name user has entered 5 for the month number.");
					}//end else

					if(lineInput[21].equals("case 6:") && lineInput[23].equals("break;"))
					{
						if(!lineInput[22].equals("System.out.println(\"June\");"))
						{
							userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "Don't forget to print the correct month (with a capital at the start) when the user has entered 6 for the month number.");
						}
					}

					else
					{
						userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "Don't forget to add the case to print the month name user has entered 6 for the month number.");
					}//end else

					if(lineInput[24].equals("case 7:") && lineInput[26].equals("break;"))
					{
						if(!lineInput[25].equals("System.out.println(\"July\");"))
						{
							userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "Don't forget to print the correct month (with a capital at the start) when the user has entered 7 for the month number.");
						}
					}

					else
					{
						userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "Don't forget to add the case to print the month name user has entered 7 for the month number.");
					}//end else

					if(lineInput[27].equals("case 8:") && lineInput[29].equals("break;"))
					{
						if(!lineInput[28].equals("System.out.println(\"August\");"))
						{
							userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "Don't forget to print the correct month (with a capital at the start) when the user has entered 8 for the month number.");
						}
					}

					else
					{
						userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "Don't forget to add the case to print the month name user has entered 8 for the month number.");
					}//end else

					if(lineInput[30].equals("case 9:") && lineInput[32].equals("break;"))
					{
						if(!lineInput[31].equals("System.out.println(\"September\");"))
						{
							userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "Don't forget to print the correct month (with a capital at the start) when the user has entered 9 for the month number.");
						}
					}

					else
					{
						userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "Don't forget to add the case to print the month name user has entered 9 for the month number.");
					}//end else

					if(lineInput[33].equals("case 10:") && lineInput[35].equals("break;"))
					{
						if(!lineInput[34].equals("System.out.println(\"October\");"))
						{
							userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "Don't forget to print the correct month (with a capital at the start) when the user has entered 10 for the month number.");
						}
					}

					else
					{
						userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "Don't forget to add the case to print the month name user has entered 10 for the month number.");
					}//end else

					if(lineInput[36].equals("case 11:") && lineInput[38].equals("break;"))
					{
						if(!lineInput[37].equals("System.out.println(\"November\");"))
						{
							userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "Don't forget to print the correct month (with a capital at the start) when the user has entered 11 for the month number.");
						}
					}

					else
					{
						userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "Don't forget to add the case to print the month name user has entered 11 for the month number.");
					}//end else

					if(lineInput[39].equals("case 12:") && lineInput[41].equals("break;"))
					{
						if(!lineInput[40].equals("System.out.println(\"December\");"))
						{
							userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "Don't forget to print the correct month (with a capital at the start) when the user has entered 12 for the month number.");
						}
					}

					else
					{
						userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "Don't forget to add the case to print the month name user has entered 12 for the month number.");
					}//end else

					if(lineInput[42].equals("default:") && lineInput[44].equals("break;"))
					{
						if(lineInput[43].startsWith("System.out.println(\"") && lineInput[43].endsWith("\");"))
						{
							for(int i=20; i<lineInput[43].length()-3; i++)
							{
								errorStatement+=lineInput[43].charAt(i);
							}//end for loop

							if(errorStatement.equals(""))
							{
								userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "Don't forget to print an error statement when the user has entered an invalid number for the variable month.");
							}//end inside if
						}//end outside if
						else
						{
							userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "Don't forget to print an error statement when the user has entered an invalid number for the variable month.");
						}//end else
					}//end if

					else
					{
						userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "Don't forget to add the default to print an error statement when the user has entered a number >1 or <12 for the month number.");
					}//end else

				}//end if

				else
				{
					userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "Don't forget to add the curly brackets on the sixth and/or the fourty-sixth line of code.");
				}//end else

			}//end if

			else
			{
				userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "Don't forget to begin your switch case statement on the fifth line of code.");
			}//end else
		}//end if

		else
		{
			userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "Don't forget to begin your switch case statement on the fifth line of code.");
		}//end else

		if(!lineInput[46].equals(scannerObject+".close();"))
		{
			userOutputL7T3[0]=addErrorMessage(userOutputL7T3[0], "Don't forget to close your scanner object on the fourty-seventh line of code.");
		}//end if

		//when there has been no errors detected in the user's code yet
		if(userOutputL7T3[0].equals(""))
		{
			userOutputL7T3[0]=question;
			userOutputL7T3[1]=errorStatement;
		}//end if

		userOutputL7T3[2]=outputFontFormat(userOutputL7T3[0], fontSize);

		//returning the array containing the text and style for the output text area in the correct task
		return userOutputL7T3;
	}//end submitL7T3 method


	public static String [] submitL8T1(TextArea taUserInputL8T1, String fontSize) throws ScriptException
	{
		//creating and initializing the array to store the user's output and the text style as well as all variables to be used in this method
		String [] lineInput = splitInputByLine(taUserInputL8T1.getText(), 5, "L8T1");
		String [] userOutputL8T1=new String[2];
		userOutputL8T1[0]="";
		userOutputL8T1[1]="";
		String counter=forLoopInitialization(lineInput[0], 1, 10, 1);
		
		if(lineInput[0].equals("not valid"))
		{
			userOutputL8T1[0]=addErrorMessage(userOutputL8T1[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL8T1[1]=outputFontFormat(userOutputL8T1[0], fontSize);

			//returning the array containing the text and style for the output text area in L8T1
			return userOutputL8T1;
		}
		
		if(lineInput[4].startsWith("Your code should contain exactly"))
		{
			userOutputL8T1[0]=addErrorMessage(userOutputL8T1[0],lineInput[4]);
		}
		
		if(!counter.equals("invalid counter name") && lineInput[1].equals("{") && lineInput[3].equals("}"))
		{
			if(lineInput[2].equals("System.out.println("+counter+");"))
			{
				if(userOutputL8T1[0].equals(""))
				{
					userOutputL8T1[0]="1\n2\n3\n4\n5\n6\n7\n8\n9\n10";
				}//end third if
			}//end second if
		}//end first if
		
		userOutputL8T1[1]=outputFontFormat(userOutputL8T1[0], fontSize);

		return userOutputL8T1;
	}//end submitL8T1 method


	/* Method Author: Brooke Cronin
	 * Method Name: submitL8T2
	 * Description: Breaks apart user's input by line, checks to make sure all variables are created and initialized correctly, checks that user printed the result variable to screen, and prints all errors found in user's code or the value of result;
	 * Parameters: TextArea taUserInputL8T1
	 * Returns: String [] userOutputL8T1
	 * Throws: ScriptException
	 */

	public static String [] submitL8T2(TextArea taUserInputL8T2, String fontSize) throws ScriptException
	{
		//creating and initializing the array to store the user's output and the text style as well as all variables to be used in this method
		String [] lineInput = splitInputByLine(taUserInputL8T2.getText(), 6, "L8T2");
		String [] userOutputL8T2=new String[2];
		userOutputL8T2[0]="";
		userOutputL8T2[1]="";
		String counter=forLoopInitialization(lineInput[0], 99, 73, 2);
		
		if(lineInput[0].equals("not valid"))
		{
			userOutputL8T2[0]=addErrorMessage(userOutputL8T2[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL8T2[1]=outputFontFormat(userOutputL8T2[0], fontSize);

			//returning the array containing the text and style for the output text area in L8T2
			return userOutputL8T2;
		}
		
		if(lineInput[5].startsWith("Your code should contain exactly"))
		{
			userOutputL8T2[0]=addErrorMessage(userOutputL8T2[0],lineInput[5]);
		}
		

		if(counter.equals("invalid counter name"))
		{
			counter=forLoopInitialization(lineInput[0], 99, 72, 2);
		}
		
		
		if(!counter.equals("invalid counter name") && lineInput[1].equals("{") && lineInput[3].equals("}"))
		{
			if(lineInput[2].equals("System.out.print("+counter+"\", \");") && lineInput[4].equals("System.out.print(\"71\");"))
			{
				if(userOutputL8T2[0].equals(""))
				{
					userOutputL8T2[0]="99, 97, 95, 93, 91, 89, 87, 85, 83, 81, 79, 77, 75, 73, 71";
				}//end third if
			}//end second if
		}//end first if

		userOutputL8T2[1]=outputFontFormat(userOutputL8T2[0], fontSize);

		return userOutputL8T2;
	}//end submitL8T1 method
	
	
	/* Method Author: Brooke Cronin
	 * Method Name: submitL8T3
	 * Description: Breaks apart user's input by line, checks to make sure all variables are created and initialized correctly, checks that user printed the result variable to screen, and prints all errors found in user's code or the value of result;
	 * Parameters: TextArea taUserInputL8T3
	 * Returns: String [] userOutputL8T3
	 * Throws: ScriptException
	 */

	public static String [] submitL8T3(TextArea taUserInputL8T3, String fontSize) throws ScriptException
	{
		Random rand = new Random();
		
		//creating and initializing the array to store the user's output and the text style as well as all variables to be used in this method
		String [] lineInput = splitInputByLine(taUserInputL8T3.getText(), 8, "L8T3");
		String [] userOutputL8T3=new String[2];
		String randomObject="";
		userOutputL8T3[0]="";
		userOutputL8T3[1]="";
		int diceValue=rand.nextInt(6)+1;
		
		if(lineInput[0].equals("not valid"))
		{
			userOutputL8T3[0]=addErrorMessage(userOutputL8T3[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL8T3[1]=outputFontFormat(userOutputL8T3[0], fontSize);

			//returning the array containing the text and style for the output text area in L8T3
			return userOutputL8T3;
		}
		
		if(lineInput[7].startsWith("Your code should contain exactly"))
		{
			userOutputL8T3[0]=addErrorMessage(userOutputL8T3[0],lineInput[7]);
		}
		
		randomObject=randomObjectName(lineInput[0]);

		//when user has not correctly created the scanner object
		if(randomObject.equals(""))
		{
			userOutputL8T3[0]=addErrorMessage(userOutputL8T3[0], "Don't forget to create the object from the Random class in order to generate random numbers for this task.");
		}//end if
		
		if(lineInput[1].startsWith("int dice") && lineInput[1].contains(randomObject+".nextInt"))
		{
			lineInput[1]=lineInput[1].replaceAll("\\s+","");
			if(lineInput[1].equals("intdice="+randomObject+".nextInt(6)+1;"))
			{
				
			}//end inside if
		}//end outside if
		
		if(lineInput[2].startsWith("while") && lineInput[2].contains("dice") && lineInput[3].equals("{") && lineInput[6].equals("}"))
		{
			lineInput[2]=lineInput[2].replaceAll("\\s+","");
			if(lineInput[2].equals("while(dice!=5)") || lineInput[2].equals("while(5!=dice)"))
			{
				if(lineInput[4].startsWith("System.out.println(\"You have rolled a \"") && lineInput[4].endsWith(");"))
				{
					lineInput[4]=lineInput[4].replaceAll("\\s+","");
					if(lineInput[4].equals("System.out.println(\"Youhaverolleda\"+dice);"))
					{
						if(lineInput[5].startsWith("dice") && lineInput[5].contains(randomObject+".nextInt"))
						{
							lineInput[5]=lineInput[5].replaceAll("\\s+","");
							if(lineInput[5].equals("dice="+randomObject+".nextInt(6)+1;"))
							{
								if(userOutputL8T3[0].equals(""))
								{
									if(diceValue!=5)
									{
										userOutputL8T3[0]="You have rolled a "+diceValue;
									}
									
									while(diceValue!=5)
									{
										userOutputL8T3[0]+="\nYou have rolled a "+diceValue;
										diceValue=rand.nextInt(6)+1;
									}//end while loop
								}//end if
							}//end if
						}//end if
					}//end if
				}//end if
			}//end if
		}//end if
		
		userOutputL8T3[1]=outputFontFormat(userOutputL8T3[0], fontSize);

		return userOutputL8T3;
	}//end submitL8T3 method
	
	
	/* Method Author: Brooke Cronin
	 * Method Name: submitL8T4
	 * Description: Breaks apart user's input by line, checks to make sure all variables are created and initialized correctly, checks that user printed the result variable to screen, and prints all errors found in user's code or the value of result;
	 * Parameters: TextArea taUserInputL8T4
	 * Returns: String [] userOutputL8T4
	 * Throws: ScriptException
	 */

	public static String [] submitL8T4(TextArea taUserInputL8T4, String fontSize) throws ScriptException
	{
		//creating and initializing the array to store the user's output and the text style as well as all variables to be used in this method
		String [] lineInput = splitInputByLine(taUserInputL8T4.getText(), 18, "L8T4");
		String [] userOutputL8T4=new String[3];
		String scannerObject="";
		String randomObject="";
		String question="";
		userOutputL8T4[0]="";
		userOutputL8T4[1]="";
		userOutputL8T4[2]="";
		
		if(lineInput[0].equals("not valid"))
		{
			userOutputL8T4[0]=addErrorMessage(userOutputL8T4[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL8T4[1]=outputFontFormat(userOutputL8T4[0], fontSize);

			//returning the array containing the text and style for the output text area in L8T4
			return userOutputL8T4;
		}
		
		if(lineInput[17].startsWith("Your code should contain exactly"))
		{
			userOutputL8T4[0]=addErrorMessage(userOutputL8T4[0],lineInput[17]);
		}
		
		randomObject=randomObjectName(lineInput[0]);
		scannerObject=scannerObjectName(lineInput[1]);
		
		if(randomObject.equals("") && scannerObject.equals(""))
		{
			randomObject=randomObjectName(lineInput[1]);
			scannerObject=scannerObjectName(lineInput[0]);
		}//end if
		
		//when user has not correctly created the random object
		if(randomObject.equals(""))
		{
			userOutputL8T4[0]=addErrorMessage(userOutputL8T4[0], "Don't forget to create the object from the Random class in order to generate random numbers for this task.");
		}//end if
		
		//when user has not correctly created the scanner object
		if(scannerObject.equals(""))
		{
			userOutputL8T4[0]=addErrorMessage(userOutputL8T4[0], "Don't forget to create the object from the Scanner class in order to gather input from the user for this task.");
		}//end if
		
		if(!lineInput[2].equals("int d1;"))
		{
			userOutputL8T4[0]=addErrorMessage(userOutputL8T4[0], "Don't forget to create an integer variable called d1 to store the value of the first dice, and don't initialize it on the third line of code.");
		}//end if
		
		if(!lineInput[3].equals("int d2;"))
		{
			userOutputL8T4[0]=addErrorMessage(userOutputL8T4[0], "Don't forget to create an integer variable called d2 to store the value of the second dice, and don't initialize it on the fourth line of code.");
		}//end if
		
		if(lineInput[4].startsWith("int rollCounter") && lineInput[4].endsWith("0;"))
		{
			lineInput[4]=lineInput[4].replaceAll("\\s+","");
			if(!lineInput[4].equals("introllCounter=0;"))
			{
				userOutputL8T4[0]=addErrorMessage(userOutputL8T4[0], "Don't forget to create an integer variable called rollCounter and initialize it to 0 on the fifth line of code.");
			}//end inside if
		}
		
		if(lineInput[5].startsWith("String rollAgain") && lineInput[5].endsWith("\"y\";"))
		{
			lineInput[5]=lineInput[5].replaceAll("\\s+","");
			if(!lineInput[5].equals("StringrollAgain=\"y\";"))
			{
				userOutputL8T4[0]=addErrorMessage(userOutputL8T4[0], "Don't forget to create a String variable called rollAgain and initialize it to \"y\" on the sixth line of code.");
			}//end inside if
		}//end outside if

		if(lineInput[6].equals("do") && lineInput[7].equals("{") && lineInput[14].equals("}") && lineInput[15].startsWith("while") && lineInput[15].endsWith(");"))
		{
			
			if(lineInput[15].contains("rollAgain.equals(\"y\")") && lineInput[15].contains(" && "))
			{
				if(!lineInput[15].contains("rollCounter<=3") && !lineInput[15].contains("rollCounter<4") && !lineInput[15].contains("3>=rollCounter") && !lineInput[15].contains("4>rollCounter"))
				{
					userOutputL8T4[0]=addErrorMessage(userOutputL8T4[0], "You have forgotten to add one or more of the conditions in your do while loop.");
				}//end if
			}//end if
			
			else
			{
				userOutputL8T4[0]=addErrorMessage(userOutputL8T4[0], "You have forgotten to add one or more of the conditions in your do while loop.");
			}//end else
			
			if(lineInput[8].startsWith("rollCounter") && !lineInput[8].contains("roll Counter"))
			{
				lineInput[8]=lineInput[8].replaceAll("\\s+","");
				if(!lineInput[8].equals("rollCounter++;") && !lineInput[8].equals("rollCounter+=1;") && !lineInput[8].equals("rollCounter=rollCounter+1;"))
				{
					userOutputL8T4[0]=addErrorMessage(userOutputL8T4[0], "Don't forget to increase the value of rollCounter by 1 on the ninth line of code.");
				}//end if
			}//end if
			
			else
			{
				userOutputL8T4[0]=addErrorMessage(userOutputL8T4[0], "Don't forget to increase the value of rollCounter by 1 on the ninth line of code.");
			}//end else
			
			if(lineInput[9].startsWith("d1") && lineInput[9].contains(".nextInt"))
			{
				lineInput[9]=lineInput[9].replaceAll("\\s+","");
				if(!lineInput[9].equals("d1="+randomObject+".nextInt(6)+1;"))
				{
					userOutputL8T4[0]=addErrorMessage(userOutputL8T4[0], "Don't forget to initialize d1 on the tenth line of code to a random integer between 1 and 6.");
				}//end if
			}//end if
			
			else
			{
				userOutputL8T4[0]=addErrorMessage(userOutputL8T4[0], "Don't forget to initialize d1 on the tenth line of code to a random integer between 1 and 6.");
			}//end else
			
			if(lineInput[10].startsWith("d2") && lineInput[10].contains(".nextInt"))
			{
				lineInput[10]=lineInput[10].replaceAll("\\s+","");
				if(!lineInput[10].equals("d2="+randomObject+".nextInt(6)+1;"))
				{
					userOutputL8T4[0]=addErrorMessage(userOutputL8T4[0], "Don't forget to initialize d2 on the elevennth line of code to a random integer between 1 and 6.");
				}//end if
			}//end else if
			
			else
			{
				userOutputL8T4[0]=addErrorMessage(userOutputL8T4[0], "Don't forget to initialize d2 on the eleventh line of code to a random integer between 1 and 6.");
			}//end else
			
			if(lineInput[11].startsWith("System.out.println(\"You have rolled \"") && lineInput[11].contains("\" and \"") && lineInput[11].endsWith(");"))
			{
				lineInput[11]=lineInput[11].replaceAll("\\s+","");
				if(!lineInput[11].equals("System.out.println(\"Youhaverolled\"+d1+\"and\"+d2);"))
				{
					userOutputL8T4[0]=addErrorMessage(userOutputL8T4[0], "Don't forget to print to screen the values of d1 and d2 in the proper format.");
				}//end if
			}//end if
			
			else
			{
				userOutputL8T4[0]=addErrorMessage(userOutputL8T4[0], "Don't forget to print to screen the values of d1 and d2 in the proper format.");
			}
			
			if(lineInput[12].startsWith("System.out.println(\"") && lineInput[12].endsWith("\");"))
			{
				for(int i=20; i<lineInput[12].length()-3; i++)
				{
					question+=lineInput[12].charAt(i);
				}//end for loop
			}//end if
			if(question.equals(""))
			{
				userOutputL8T4[0]=addErrorMessage(userOutputL8T4[0], "Don't forget to tell/ask user what they should input (whether or not they want to roll the dice again).");
			}//end inside if

			if(lineInput[13].endsWith(".nextLine();"))
			{
				if(!lineInput[13].equals("rollAgain="+scannerObject+".nextLine();"))
				{
					userOutputL8T4[0]=addErrorMessage(userOutputL8T4[0], "Don't forget to allow the user to initialize the variable rollAgain on the fourteenth line of code.");
				}//end if
			}//end if
		}//end if
		
		if(!lineInput[16].equals(scannerObject+".close();"))
		{
			userOutputL8T4[0]=addErrorMessage(userOutputL8T4[0], "Don't forget to close your scanner object.");
		}//end if

		else if(userOutputL8T4[0].equals(""))
		{
			userOutputL8T4[1]=question;
		}//end else if
		
		userOutputL8T4[2]=outputFontFormat(userOutputL8T4[0], fontSize);

		return userOutputL8T4;
	}//end submitL8T4 method
	
	
	/* Method Author: Brooke Cronin
	 * Method Name: addErrorMessage
	 * Description: Adds the specified error message to the user's output. If it is the first error message detected on the current trial, "Error!" will be added first.
	 * Parameters: String userOutput, String errorMessage
	 * Returns: String userOutput
	 */

	public static String [] submitL9T1(TextArea taUserInputL9T1, String fontSize) throws ScriptException
	{
		//creating and initializing the array to store the user's output and the text style as well as all variables to be used in this method
		String [] lineInput = splitInputByLine(taUserInputL9T1.getText(), 13, "L9T1");
		String [] userOutputL9T1=new String[2];
		userOutputL9T1[0]="";
		userOutputL9T1[1]="";
	
		if(lineInput[0].equals("not valid"))
		{
			userOutputL9T1[0]=addErrorMessage(userOutputL9T1[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL9T1[1]=outputFontFormat(userOutputL9T1[0], fontSize);

			//returning the array containing the text and style for the output text area in L9T1
			return userOutputL9T1;
		}
		
		if(lineInput[12].startsWith("Your code should contain exactly"))
		{
			userOutputL9T1[0]=addErrorMessage(userOutputL9T1[0],lineInput[12]);
		}
	
		if(lineInput[0].equals("public static void main()"))
		{
			if(lineInput[1].equals("{") && lineInput[6].equals("}"))
			{
				if(lineInput[2].startsWith("int value"))
				{
					lineInput[2]=lineInput[2].replaceAll("\\s+","");
					if(lineInput[2].equals("intvalue=0;"))
					{
						if(lineInput[3].startsWith("System.out.println") && lineInput[3].contains("value"))
						{
							lineInput[3]=lineInput[3].replaceAll("\\s+","");
							if(lineInput[3].equals("System.out.println(value);"))
							{
								if(lineInput[4].startsWith("printValue") &&lineInput[4].contains("value"))
								{
									lineInput[4]=lineInput[4].replaceAll("\\s+","");
									if(lineInput[4].equals("printValue(value);"))
									{
										if(lineInput[5].startsWith("System.out.println") && lineInput[5].contains("value"))
										{
											lineInput[5]=lineInput[5].replaceAll("\\s+","");
											if(!lineInput[5].equals("System.out.println(value);"))
											{
												userOutputL9T1[0]=addErrorMessage(userOutputL9T1[0], "Don't forget to print value to the screen on the sixth line of code.");
											}
										}
										else
										{
											userOutputL9T1[0]=addErrorMessage(userOutputL9T1[0], "Don't forget to print value to the screen on the sixth line of code.");
										}
									}
									else
									{
										userOutputL9T1[0]=addErrorMessage(userOutputL9T1[0], "Don't forget to call to the printValue method on the fifth line of code with value as the parameter.");
									}
								}
								else
								{
									userOutputL9T1[0]=addErrorMessage(userOutputL9T1[0], "Don't forget to call to the printValue method on the fifth line of code with value as the parameter.");
								}
							}
							else
							{
								userOutputL9T1[0]=addErrorMessage(userOutputL9T1[0], "Don't forget to print value to the screen on the fourth line of code.");
							}
						}
						else
						{
							userOutputL9T1[0]=addErrorMessage(userOutputL9T1[0], "Don't forget to print value to the screen on the fourth line of code.");
						}
					}
					else
					{
						userOutputL9T1[0]=addErrorMessage(userOutputL9T1[0], "Don't forget to create an integer variable called value and initialize it to 0 on your third line of code.");
					}
				}
				else
				{
					userOutputL9T1[0]=addErrorMessage(userOutputL9T1[0], "Don't forget to create an integer variable called value and initialize it to 0 on your third line of code.");
				}
			}
			else
			{
				userOutputL9T1[0]=addErrorMessage(userOutputL9T1[0], "Don't forget to surround your main method with {}.");
			}
		}
		else
		{
			userOutputL9T1[0]=addErrorMessage(userOutputL9T1[0], "Don't forget to add a main method decleration on the first line of your code.");
		}
		if(lineInput[7].startsWith("public static void printValue") && lineInput[7].contains("int value"))
		{
			lineInput[7]=lineInput[7].replaceAll("\\s+","");
			if(lineInput[7].equals("publicstaticvoidprintValue(intvalue);"))
			{
				if(lineInput[8].equals("{") && lineInput[11].equals("}"))
				{
					if(lineInput[9].startsWith("value"))
					{
						lineInput[9]=lineInput[9].replaceAll("\\s+","");
						if(lineInput[9].equals("value=1;"))
						{
							if(lineInput[10].startsWith("System.out.println") && lineInput[10].contains("value"))
							{
								lineInput[10]=lineInput[10].replaceAll("\\s+","");
								if(!lineInput[10].equals("System.out.println(value);"))
								{
									userOutputL9T1[0]=addErrorMessage(userOutputL9T1[0], "Don't forget to print value to the screen on the eleventh line of code.");
								}
							}
							else
							{
								userOutputL9T1[0]=addErrorMessage(userOutputL9T1[0], "Don't forget to print value to the screen on the eleventh line of code.");
							}
						}
						else
						{
							userOutputL9T1[0]=addErrorMessage(userOutputL9T1[0], "Don't forget to reinitialize value to 1 on your tenth line of code.");
						}
					}
					else
					{
						userOutputL9T1[0]=addErrorMessage(userOutputL9T1[0], "Don't forget to reinitialize value to 1 on your tenth line of code.");
					}
				}
				else
				{
					userOutputL9T1[0]=addErrorMessage(userOutputL9T1[0], "Don't forget to surround your printValue method with {}.");
				}
			}
			else
			{
				userOutputL9T1[0]=addErrorMessage(userOutputL9T1[0], "Don't forget to add a printValue method decleration on the eighth line of your code.");
			}
		}
		else
		{
			userOutputL9T1[0]=addErrorMessage(userOutputL9T1[0], "Don't forget to add a printValue method decleration on the eighth line of your code.");
		}
		if(userOutputL9T1[0].equals(""))
		{
			userOutputL9T1[0]="0\n1\n0";
		}
	
		userOutputL9T1[1]=outputFontFormat(userOutputL9T1[0], fontSize);
	
		return userOutputL9T1;
	}//end submitL9T1 method

	/* Method Author: Brooke Cronin
	 * Method Name: submitL9T2
	 * Description: Breaks apart user's input by line, checks to make sure all variables are created and initialized correctly, checks that user printed the result variable to screen, and prints all errors found in user's code or the value of result;
	 * Parameters: TextArea taUserInputL9T1
	 * Returns: String [] userOutputL9T1
	 * Throws: ScriptException
	 */
	
	public static String [] submitL9T2(TextArea taUserInputL9T2, String fontSize) throws ScriptException
	{
		//creating and initializing the array to store the user's output and the text style as well as all variables to be used in this method
		String [] lineInput = splitInputByLine(taUserInputL9T2.getText(), 11, "L9T2");
		String [] userOutputL9T2=new String[2];
		userOutputL9T2[0]="";
		userOutputL9T2[1]="";
		String sIncrement="";
		String sBy="";
		double dIncrement=0.0;
		int iBy=0;
		int counter=0;
	
		if(lineInput[0].equals("not valid"))
		{
			userOutputL9T2[0]=addErrorMessage(userOutputL9T2[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL9T2[1]=outputFontFormat(userOutputL9T2[0], fontSize);

			//returning the array containing the text and style for the output text area in L9T2
			return userOutputL9T2;
		}
		
		if(lineInput[10].startsWith("Your code should contain exactly"))
		{
			userOutputL9T2[0]=addErrorMessage(userOutputL9T2[0], lineInput[10]);
		}

		if(lineInput[0].equals("public static void main()"))
		{
			if(lineInput[1].equals("{") && lineInput[6].equals("}"))
			{
				if(lineInput[2].startsWith("increment") && lineInput[2].contains("(") && lineInput[2].contains(",") && lineInput[2].contains(")") && lineInput[2].contains(";"))
				{

				}
				else
				{
					userOutputL9T2[0]=addErrorMessage(userOutputL9T2[0], "Don't forget to call to the printValue method on the fifth line of code with value as the parameter.");
				}
			}

			else
			{
				userOutputL9T2[0]=addErrorMessage(userOutputL9T2[0], "Don't forget to surround your main method with {}.");
			}
		}
		else
		{
			userOutputL9T2[0]=addErrorMessage(userOutputL9T2[0], "Don't forget to add a main method decleration on the first line of your code.");
		}
		if(lineInput[7].startsWith("public static void increment") && lineInput[7].contains("double increment") && lineInput[7].contains("int by"))
		{
			lineInput[7]=lineInput[7].replaceAll("\\s+","");
			if(lineInput[7].equals("publicstaticvoidincrement(doubleincrement,intby);"))
			{
				//a for loop to assign the value of output all of the double numbers that the user assigned to the variable in their code
				for(int i=11; i<lineInput[2].length()-1; i++)
				{
					if(lineInput[2].charAt(i)!=' ' && lineInput[2].charAt(i)!=',')
					{
						sIncrement+=lineInput[2].charAt(i);
						++counter;
					}//end if inside for loop
					else if(lineInput[2].charAt(i)!=',')
					{
						break;
					}
				}//end for	
				//a for loop to assign the value of output all of the double numbers that the user assigned to the variable in their code
				for(int i=11+counter; i<lineInput[2].length()-1; i++)
				{
					if(lineInput[2].charAt(i)!=' ' && lineInput[2].charAt(i)!=')' && lineInput[2].charAt(i)!=';')
					{
						sBy+=lineInput[2].charAt(i);
					}//end if inside for loop
					else if(lineInput[2].charAt(i)!=')')
					{
						break;
					}
				}//end for	
			}
			if(lineInput[7].equals("voidincrement(intby,doubleincrement);"))
			{
				//a for loop to assign the value of output all of the double numbers that the user assigned to the variable in their code
				for(int i=11; i<lineInput[2].length()-1; i++)
				{
					if(lineInput[2].charAt(i)!=' ' && lineInput[2].charAt(i)!=',')
					{
						sBy+=lineInput[2].charAt(i);
						++counter;
					}//end if inside for loop
					else if(lineInput[2].charAt(i)!=',')
					{
						break;
					}
				}//end for	
				//a for loop to assign the value of output all of the double numbers that the user assigned to the variable in their code
				for(int i=11+counter; i<lineInput[2].length()-1; i++)
				{
					if(lineInput[2].charAt(i)!=' ' && lineInput[2].charAt(i)!=')')
					{
						sIncrement+=lineInput[2].charAt(i);
					}//end if inside for loop
					else if(lineInput[2].charAt(i)!=')')
					{
						break;
					}
				}//end for
				//when there has been no errors detected in the user's code yet
				if(userOutputL9T2[0].equals(""))
				{

					//when the value if myAge isn't a double
					if(!ErrorChecks.checkIfDouble(sIncrement))
					{
						userOutputL9T2[0]=addErrorMessage(userOutputL9T2[0], "You must assign increment as a double.");
					}//end if
					else
					{
						dIncrement=Double.parseDouble(sIncrement);
					}
					//when the value if myAge isn't a double
					if(!ErrorChecks.checkIfInt(sBy))
					{
						userOutputL9T2[0]=addErrorMessage(userOutputL9T2[0], "You must assign by as an integer.");
					}//end if
					else
					{
						iBy=Integer.parseInt(sBy);
					}
				}//end if
				if(lineInput[8].equals("{") && lineInput[11].equals("}"))
				{
					if(lineInput[9].startsWith("System.out.println") && lineInput[10].contains("increment") && lineInput[10].contains("by"))
					{
						lineInput[9]=lineInput[9].replaceAll("\\s+","");
						if(lineInput[9].equals("System.out.printn(increment+by);") || lineInput[9].equals("System.out.printn(by+increment);"))
						{

						}
						else
						{
							userOutputL9T2[0]=addErrorMessage(userOutputL9T2[0], "Don't forget to print the sum of increment and by to the screen.");
						}
					}
					else
					{
						userOutputL9T2[0]=addErrorMessage(userOutputL9T2[0], "Don't forget to print the sum of increment and by to the screen.");
					}
				}
				else
				{
					userOutputL9T2[0]=addErrorMessage(userOutputL9T2[0], "Don't forget to surround your increment method with {}.");
				}
			}
			else
			{
				userOutputL9T2[0]=addErrorMessage(userOutputL9T2[0], "Don't forget to add an increment method decleration on the ninth line of your code.");
			}
		}
		else
		{
			userOutputL9T2[0]=addErrorMessage(userOutputL9T2[0], "Don't forget to add an increment method decleration on the ninth line of your code.");
		}

		if(userOutputL9T2[0].equals(""))
		{
			userOutputL9T2[0]=String.valueOf(dIncrement+iBy);
		}

		userOutputL9T2[1]=outputFontFormat(userOutputL9T2[0], fontSize);

		return userOutputL9T2;
	}//end submitL9T2 method

	
	/* Method Author: Brooke Cronin
	 * Method Name: submitL9T3
	 * Description: Breaks apart user's input by line, checks to make sure all variables are created and initialized correctly, checks that user printed the result variable to screen, and prints all errors found in user's code or the value of result;
	 * Parameters: TextArea taUserInputL9T3
	 * Returns: String [] userOutputL9T3
	 * Throws: ScriptException
	 */
	
	public static String [] submitL9T3(TextArea taUserInputL9T3, String fontSize) throws ScriptException
	{
		//creating and initializing the array to store the user's output and the text style as well as all variables to be used in this method
		String [] lineInput = splitInputByLine(taUserInputL9T3.getText(), 11, "L9T3");
		String [] userOutputL9T3=new String[2];
		userOutputL9T3[0]="";
		userOutputL9T3[1]="";
		String sLength="";
		String sHeight="";
		String sWidth="";
		String sSA="";
		String s1="";
		String s2="";
		String s3="";
		double dLength=0.0;
		double dHeight=0.0;
		double dWidth=0.0;
		double dSA=0.0;
		double d1=0.0;
		double d2=0.0;
		double d3=0.0;
		int lengthCreated=-1;
		int heightCreated=-1;
		int widthCreated=-1;
		String [] parameters=new String[3];
		int counter1=0;
		int counter2=0;
		double outputLine1=0.0;
		double outputLine2=0.0;
		
		if(lineInput[0].equals("not valid"))
		{
			userOutputL9T3[0]=addErrorMessage(userOutputL9T3[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL9T3[1]=outputFontFormat(userOutputL9T3[0], fontSize);

			//returning the array containing the text and style for the output text area in L9T3
			return userOutputL9T3;
		}
		
		if(lineInput[10].startsWith("Your code should contain exactly"))
		{
			userOutputL9T3[0]=addErrorMessage(userOutputL9T3[0], lineInput[10]);
		}

		if(lineInput[0].equals("public static void main()"))
		{
			if(lineInput[1].equals("{") && lineInput[7].equals("}"))
			{
				if(lineInput[2].startsWith("double length") && lineInput[2].contains("=") && lineInput[2].endsWith(";"))
				{
					
					lengthCreated=2;
					parameters[0]="length";
				}
				if(lineInput[3].startsWith("double length")&& lineInput[3].contains("=") && lineInput[3].endsWith(";"))
				{
					
					lengthCreated=3;
					parameters[1]="length";
				}
				if(lineInput[4].startsWith("double length") && lineInput[4].contains("=") && lineInput[4].endsWith(";"))
				{
					
					lengthCreated=4;
					parameters[2]="length";
				}
				if(lineInput[2].startsWith("double height")&& lineInput[2].contains("=") && lineInput[2].endsWith(";"))
				{
					
					heightCreated=2;
					parameters[0]="height";
				}
				if(lineInput[3].startsWith("double height")&& lineInput[3].contains("=") && lineInput[3].endsWith(";"))
				{
					
					heightCreated=3;
					parameters[1]="height";
				}
				if(lineInput[4].startsWith("double height") && lineInput[4].contains("=") && lineInput[4].endsWith(";"))
				{
					
					heightCreated=4;
					parameters[2]="height";
				}
				if(lineInput[2].startsWith("double width")&& lineInput[2].contains("=") && lineInput[2].endsWith(";"))
				{
					
					widthCreated=2;
					parameters[0]="width";
				}
				if(lineInput[3].startsWith("double width")&& lineInput[3].contains("=") && lineInput[3].endsWith(";"))
				{
					
					widthCreated=3;
					parameters[1]="width";
				}
				if(lineInput[4].startsWith("double width") && lineInput[4].contains("=") && lineInput[4].endsWith(";"))
				{
					
					widthCreated=4;
					parameters[2]="width";
				}

				if(lengthCreated!=-1)
				{
					for(int i=12; i<lineInput[lengthCreated].length(); i++)
					{
						if(lineInput[lengthCreated].charAt(i)!=' ' && lineInput[lengthCreated].charAt(i)!='=' && lineInput[lengthCreated].charAt(i)!=';')
						{
							sLength+=lineInput[lengthCreated].charAt(i);
						}
					}
					//when the value if myAge isn't a double
					if(!ErrorChecks.checkIfDouble(sLength))
					{
						userOutputL9T3[0]=addErrorMessage(userOutputL9T3[0], "You must assign length as a double.");
					}//end if
					else
					{
						dLength=Double.parseDouble(sLength);
					}
				}

				if(heightCreated!=-1)
				{
					for(int i=12; i<lineInput[heightCreated].length(); i++)
					{
						if(lineInput[heightCreated].charAt(i)!=' ' && lineInput[heightCreated].charAt(i)!='=' && lineInput[heightCreated].charAt(i)!=';')
						{
							sHeight+=lineInput[heightCreated].charAt(i);
						}
					}

					//when the value if myAge isn't a double
					if(!ErrorChecks.checkIfDouble(sHeight))
					{
						userOutputL9T3[0]=addErrorMessage(userOutputL9T3[0], "You must assign height as a double.");
					}//end if
					else
					{
						dHeight=Double.parseDouble(sHeight);
					}
				}

				if(widthCreated!=-1)
				{
					for(int i=11; i<lineInput[widthCreated].length(); i++)
					{
						if(lineInput[widthCreated].charAt(i)!=' ' && lineInput[widthCreated].charAt(i)!='=' && lineInput[widthCreated].charAt(i)!=';')
						{
							sWidth+=lineInput[widthCreated].charAt(i);
						}
					}

					//when the value if myAge isn't a double
					if(!ErrorChecks.checkIfDouble(sWidth))
					{
						userOutputL9T3[0]=addErrorMessage(userOutputL9T3[0], "You must assign width as a double.");
					}//end if
					else
					{
						dWidth=Double.parseDouble(sWidth);
					}
				}
				if(lineInput[5].startsWith("double SA") && lineInput[5].contains("=") && lineInput[5].contains("rectangularPrism") && lineInput[5].contains("length")&& lineInput[5].contains("height")&& lineInput[5].contains("width") && lineInput[5].endsWith(";"))
				{
					for(int i=12; i<lineInput[lengthCreated].length(); i++)
					{
						if(lineInput[lengthCreated].charAt(i)!=' ' && lineInput[lengthCreated].charAt(i)!='=' && lineInput[lengthCreated].charAt(i)!=';')
						{
							sLength+=lineInput[lengthCreated].charAt(i);
						}
					}
					//when the value if myAge isn't a double
					if(!ErrorChecks.checkIfDouble(sLength))
					{
						userOutputL9T3[0]=addErrorMessage(userOutputL9T3[0], "You must assign length as a double.");
					}//end if
					else
					{
						dLength=Double.parseDouble(sLength);
					}
					lineInput[5]=lineInput[5].replaceAll("\\s+","");
					if(lineInput[5].equals("doubleSA=rectangularPrismSA("+parameters[0]+","+parameters[1]+","+parameters[2]+");"))
					{
						outputLine1=2*(dWidth*dLength+dHeight*dLength+dHeight*dWidth);
					}
					else
					{
						userOutputL9T3[0]=addErrorMessage(userOutputL9T3[0], "Don't forget to create and initialize properly the double variable SA on the sixth line of code.");
					}
				}
				else
				{
					userOutputL9T3[0]=addErrorMessage(userOutputL9T3[0], "Don't forget to create and initialize properly the double variable SA on the sixth line of code.");
				}
				if(lineInput[6].startsWith("System.out.println")&&lineInput[6].contains("rectangularPrism") && lineInput[5].contains("length")&& lineInput[6].contains("height")&& lineInput[6].contains("width") && lineInput[6].endsWith(";"))
				{
					for(int i=38; i<lineInput[6].length(); i++)
					{
						if(lineInput[6].charAt(i)!=' ' && lineInput[6].charAt(i)!='('&& lineInput[6].charAt(i)!=',')
						{
							s1+=lineInput[6].charAt(i);
							++counter1;
						}
						else if(lineInput[6].charAt(i)==',')
						{
							++counter1;
							break;
						}
					}
					//when the value if myAge isn't a double
					if(!ErrorChecks.checkIfDouble(s1))
					{
						userOutputL9T3[0]=addErrorMessage(userOutputL9T3[0], "You must assign your first parameter as a double value on the call to method line.");
					}//end if
					else
					{
						d1=Double.parseDouble(s1);
					}
					for(int i=38+counter1; i<lineInput[6].length(); i++)
					{
						if(lineInput[6].charAt(i)!=' ' && lineInput[6].charAt(i)!=',')
						{
							s2+=lineInput[6].charAt(i);
							++counter1;
						}
						else if(lineInput[6].charAt(i)==',')
						{
							++counter1;
							break;
						}
					}
					//when the value if myAge isn't a double
					if(!ErrorChecks.checkIfDouble(s2))
					{
						userOutputL9T3[0]=addErrorMessage(userOutputL9T3[0], "You must assign your second parameter as a double value on the call to method line.");
					}//end if
					else
					{
						d2=Double.parseDouble(s2);
					}
					for(int i=38+counter1; i<lineInput[6].length(); i++)
					{
						if(lineInput[6].charAt(i)!=' ' && lineInput[6].charAt(i)!=')')
						{
							s3+=lineInput[6].charAt(i);
						}
						else if(lineInput[6].charAt(i)==')')
						{
							break;
						}
					}
					//when the value if myAge isn't a double
					if(!ErrorChecks.checkIfDouble(s3))
					{
						userOutputL9T3[0]=addErrorMessage(userOutputL9T3[0], "You must assign your second parameter as a double value on the call to method line.");
					}//end if
					else
					{
						d3=Double.parseDouble(s3);
					}
					lineInput[6]=lineInput[6].replaceAll("\\s+","");
					if(lineInput[6].equals("System.out.println(rectangularPrismSA("+s1+","+s2+","+s3+"));"))
					{
						outputLine2=2*(d3*d1+d2*d1+d2*d3);
					}
					else
					{
						userOutputL9T3[0]=addErrorMessage(userOutputL9T3[0], "Don't forget to use a System.out.println(); statement to call and print the returned value from the rectangularPrismSA method using double values as the parameters instead of variables.");
						
					}
				}
				else
				{
					userOutputL9T3[0]=addErrorMessage(userOutputL9T3[0], "Don't forget to use a System.out.println(); statement to call and print the returned value from the rectangularPrismSA method using double values as the parameters instead of variables.");
					
				}
			}

			else
			{
				userOutputL9T3[0]=addErrorMessage(userOutputL9T3[0], "Don't forget to surround your main method with {}.");
			}
		}
		else
		{
			userOutputL9T3[0]=addErrorMessage(userOutputL9T3[0], "Don't forget to add a main method decleration on the first line of your code.");
		}
		if(userOutputL9T3[0].equals("")&&lineInput[8].startsWith("public static double rectangularPrismSA") && lineInput[8].contains("double length") && lineInput[8].contains("double height") && lineInput[8].contains("double width"))
		{
			lineInput[8]=lineInput[8].replaceAll("\\s+","");
			if(lineInput[8].equals("publicstaticdoublerectangularPrismSA(double"+parameters[0]+",double"+parameters[1]+",double"+parameters[2]+");"))
			{

			}

			if(lineInput[9].equals("{") && lineInput[11].equals("}"))
			{
				if(lineInput[10].startsWith("double surfaceArea"))
				{
					if(lineInput[10].contains("width*length") || lineInput[10].contains("width *length") || lineInput[10].contains("width * length") || lineInput[10].contains("width* length"))
					{
						++counter2;
					}
					if(lineInput[10].contains("height*length") || lineInput[10].contains("height *length") || lineInput[10].contains("height * length") || lineInput[10].contains("height* length"))
					{
						++counter2;
					}
					if(lineInput[10].contains("height*width") || lineInput[10].contains("height *width") || lineInput[10].contains("height * width") || lineInput[10].contains("height* width"))
					{
						++counter2;
					}
					lineInput[10]=lineInput[10].replaceAll("\\s+","");
					if(counter2==3 && lineInput[10].equals("doublesurfaceArea=2*(width*length+height*length+height*width);") || lineInput[10].equals("doublesurfaceArea=2(width*length+height*length+height*width);"))
					{
						
					}
					else
					{
						userOutputL9T3[0]=addErrorMessage(userOutputL9T3[0], "Don't forget to properly create and initialize the double variable called surfaceArea to the surface area of the rectangular prism (formula is 2(wl+hl+hw)) with the dimensions passed as the parameters for this method.");
					}
				}
				else
				{
					userOutputL9T3[0]=addErrorMessage(userOutputL9T3[0], "Don't forget to properly create and initialize the double variable called surfaceArea to the surface area of the rectangular prism (formula is 2(wl+hl+hw)) with the dimensions passed as the parameters for this method.");
				}
				if(!lineInput[11].equals("return surfaceArea;"))
				{
					userOutputL9T3[0]=addErrorMessage(userOutputL9T3[0], "Don't forget to return the variable surfaceArea back to the method called from on the last line of the rectangularPrismSA method.");
				}
			}
			else
			{
				userOutputL9T3[0]=addErrorMessage(userOutputL9T3[0], "Don't forget to surround your increment method with {}.");
			}
		}
		else
		{
			userOutputL9T3[0]=addErrorMessage(userOutputL9T3[0], "Don't forget to add an increment method decleration on the ninth line of your code.");
		}
		if(userOutputL9T3[0].equals(""))
		{
			userOutputL9T3[0]=String.valueOf(outputLine1+"\n"+outputLine2);
		}

		userOutputL9T3[1]=outputFontFormat(userOutputL9T3[0], fontSize);

		return userOutputL9T3;
	}//end submitL9T3 method

	
	/* Method Author: Brooke Cronin
	 * Method Name: submitL10T1
	 * Description: Searches for what the user has assigned favNum to, makes sure that it is an integer, then assigns it to the output text area.
	 * Parameters: TextArea taUserInputL10T1
	 * Returns: String [] userOutputL10T1
	 */

	public static String [] submitL10T1(TextArea taUserInputL10T1, String fontSize)
	{
		//creating and initializing the array to store the user's output and the text style as well as all variables to be used in this method
		String [] lineInput = splitInputByLine(taUserInputL10T1.getText(), 13, "L10T1");
		String [] userOutputL10T1=new String[2];
		userOutputL10T1[0]="";
		userOutputL10T1[1]="";
		String counter=forLoopInitialization(lineInput[1], 0, 8, 1);

		if(lineInput[0].equals("not valid"))
		{
			userOutputL10T1[0]=addErrorMessage(userOutputL10T1[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL10T1[1]=outputFontFormat(userOutputL10T1[0], fontSize);

			//returning the array containing the text and style for the output text area in L10T1
			return userOutputL10T1;
		}
		
		if(lineInput[12].startsWith("Your code should contain exactly"))
		{
			userOutputL10T1[0]=addErrorMessage(userOutputL10T1[0],lineInput[12]);
		}
		
		if(!arrayInitializationChecker(lineInput[0], "int", "oneToTen", 10, "1,2,3,4,5,6,7,8,9,10"))
		{
			userOutputL10T1[0]=addErrorMessage(userOutputL10T1[0],"Don't forget to properly create and initialize the integer array called oneToTen on the fourth line of code.");
		}
				
		if(!counter.equals("invalid counter name") && lineInput[2].equals("{") && lineInput[4].equals("}"))
		{
			if(lineInput[3].startsWith("System.out.print(") || lineInput[3].startsWith("System.out.print ("))
			{
				if(lineInput[3].contains("oneToTen") && lineInput[3].contains("counter") && lineInput[3].contains("\", \""))
				{
					lineInput[3]=lineInput[3].replaceAll("\\s+","");
					if(lineInput[3].equals("System.out.print(oneToTen[counter]+\", \");"))
					{
						
					}
					else
					{
						userOutputL10T1[0]=addErrorMessage(userOutputL10T1[0],"Don't forget to use a System.out.print(); statement inside of your for loop to print the first 9 indices in the array with a comma and space separating them.");
					}
				}
				else
				{
					userOutputL10T1[0]=addErrorMessage(userOutputL10T1[0],"Don't forget to use a System.out.print(); statement inside of your for loop to print the first 9 indices in the array with a comma and space separating them.");
				}
			}//end second if
			else
			{
				userOutputL10T1[0]=addErrorMessage(userOutputL10T1[0],"Don't forget to use a System.out.print(); statement inside of your for loop to print the first 9 indices in the array with a comma and space separating them.");
			}
		}//end first if
		else
		{
			userOutputL10T1[0]=addErrorMessage(userOutputL10T1[0],"Don't forget to properly create your for loop conditions and use the {} surrounding your for loop code.");
		}
		
		if(!lineInput[5].equals("System.out.print(oneToTen[9]);"))
		{
			userOutputL10T1[0]=addErrorMessage(userOutputL10T1[0],"Don't forget to use a System.out.print(); statement to print the last index in the array");
		}
		
		//System.out.print(oneToTen[counter]+", ");

		//when there has been no errors detected in the user's code yet
		if(userOutputL10T1[0].equals(""))
		{
			userOutputL10T1[0]="1, 2, 3, 4, 5, 6, 7, 8, 9, 10";
		}//end if

		userOutputL10T1[1]=outputFontFormat(userOutputL10T1[0], fontSize);

		//returning the array containing the text and style for the output text area in L10T1
		return userOutputL10T1;
	}//end submitL10T1


	/* Method Author: Brooke Cronin
	 * Method Name: submitL10T2
	 * Description: Searches for what the user has assigned myAge to, makes sure that it is a double, then assigns it to the output text area.
	 * Parameters: TextArea taUserInputL10T2
	 * Returns: String [] userOutputL10T2
	 */

	public static String [] submitL10T2(TextArea taUserInputL10T2, String fontSize)
	{
		//creating and initializing the array to store the user's output and the text style as well as all variables to be used in this method
		String [] lineInput = splitInputByLine(taUserInputL10T2.getText(), 9, "L10T2");
		String [] userOutputL10T2=new String[2];
		userOutputL10T2[0]="";
		userOutputL10T2[1]="";
		boolean idx0=false;
		boolean idx8=false;

		if(lineInput[0].equals("not valid"))
		{
			userOutputL10T2[0]=addErrorMessage(userOutputL10T2[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL10T2[1]=outputFontFormat(userOutputL10T2[0], fontSize);

			//returning the array containing the text and style for the output text area in L10T2
			return userOutputL10T2;
		}
		else if(!lineInput[0].equals("int [] oneToTen = new int [10] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; //initialization of the array from the last task, do not modify this line of code"))
		{
			userOutputL10T2[0]=addErrorMessage(userOutputL10T2[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL10T2[1]=outputFontFormat(userOutputL10T2[0], fontSize);

			//returning the array containing the text and style for the output text area in L10T2
			return userOutputL10T2;
		}
		
		if(lineInput[8].startsWith("Your code should contain exactly"))
		{
			userOutputL10T2[0]=addErrorMessage(userOutputL10T2[0],lineInput[8]);
		}
		
		if(lineInput[1].startsWith("int idx"))
		{
			lineInput[1]=lineInput[1].replaceAll("\\s+","");
			if(lineInput[1].equals("intidx=0;"))
			{
				if(lineInput[2].startsWith("oneToTen") && lineInput[2].contains("83"))
				{
					lineInput[2]=lineInput[2].replaceAll("\\s+","");
					if(lineInput[2].equals("oneToTen[0]=83;"))
					{
						idx0=true;
					}
				}
				else if(lineInput[2].startsWith("oneToTen") && lineInput[2].contains("-4"))
				{
					lineInput[2]=lineInput[2].replaceAll("\\s+","");
					if(lineInput[2].equals("oneToTen[8]=-4;"))
					{
						idx8=true;
					}
				}
				if(lineInput[3].startsWith("oneToTen") && lineInput[3].contains("83"))
				{
					lineInput[3]=lineInput[3].replaceAll("\\s+","");
					if(lineInput[3].equals("oneToTen[0]=83;"))
					{
						idx0=true;
					}
				}
				else if(lineInput[3].startsWith("oneToTen") && lineInput[3].contains("-4"))
				{
					lineInput[3]=lineInput[3].replaceAll("\\s+","");
					if(lineInput[3].equals("oneToTen[8]=-4;"))
					{
						idx8=true;
					}
				}
				if(idx0==false || idx8==false)
				{
					userOutputL10T2[0]=addErrorMessage(userOutputL10T2[0],"Don't forget to modify the first and ninth element in the oneToTen array properly.");
				}
				else
				{
					if(lineInput[4].startsWith("while") && lineInput[4].contains("idx"))
					{
						if(lineInput[4].contains("10") || lineInput[4].contains("9"))
						{
							lineInput[4]=lineInput[4].replaceAll("\\s+","");
							if(lineInput[4].equals("while(idx<10)")||lineInput[4].equals("while(idx<=9)"))
							{
								if(lineInput[5].equals("{") && lineInput[7].equals("}"))
								{
									if(lineInput[6].startsWith("System.out.println") && lineInput[6].contains("oneToTen") && lineInput[6].contains("idx"))
									{
										lineInput[6]=lineInput[6].replaceAll("\\s+","");
										if(!lineInput[6].equals("System.out.println(oneToTen[idx]);"))
										{
											userOutputL10T2[0]=addErrorMessage(userOutputL10T2[0],"Don't forget to print each element of the oneToTen array on a separate line inside of the while loop.");
										}
									}
									else
									{
										userOutputL10T2[0]=addErrorMessage(userOutputL10T2[0],"Don't forget to print each element of the oneToTen array on a separate line inside of the while loop.");
									}
								}
								else
								{
									userOutputL10T2[0]=addErrorMessage(userOutputL10T2[0],"Don't forget to add the while loop condition so that idx is never equal to an index out of bounds in the oneToTen array.");
								}
							}
							else
							{
								userOutputL10T2[0]=addErrorMessage(userOutputL10T2[0],"Don't forget to add the while loop condition so that idx is never equal to an index out of bounds in the oneToTen array.");
							}
						}
						else
						{
							userOutputL10T2[0]=addErrorMessage(userOutputL10T2[0],"Don't forget to add the while loop condition so that idx is never equal to an index out of bounds in the oneToTen array.");
						}
					}
					else
					{
						userOutputL10T2[0]=addErrorMessage(userOutputL10T2[0],"Don't forget to add the while loop condition so that idx is never equal to an index out of bounds in the oneToTen array.");
					}
				}
			}
			else
			{
				userOutputL10T2[0]=addErrorMessage(userOutputL10T2[0],"Don't forget to create an integer variable called idx and initialize it to 0 on your first line to keep track of the current index value for the while loop.");
			}
		}
		else
		{
			userOutputL10T2[0]=addErrorMessage(userOutputL10T2[0],"Don't forget to create an integer variable called idx and initialize it to 0 on your first line to keep track of the current index value for the while loop.");
		}

		//when there has been no errors detected in the user's code yet
		if(userOutputL10T2[0].equals(""))
		{
			userOutputL10T2[0]="83\n2\n3\n4\n5\n6\n7\n-4\n9\n10";
		}//end if

		userOutputL10T2[1]=outputFontFormat(userOutputL10T2[0], fontSize);

		//returning the array containing the text and style for the output text area in L10T2
		return userOutputL10T2;
	}//end submitL10T2


	/* Method Author: Brooke Cronin
	 * Method Name: submitL10T3
	 * Description: Searches for what the user has assigned myFirstName to, then assigns it to the output text area.
	 * Parameters: TextArea taUserInputL10T3
	 * Returns: String [] userOutputL10T3
	 */

	public static String [] submitL10T3(TextArea taUserInputL10T3, String fontSize)
	{
		//creating and initializing the array to store the user's output and the text style as well as all variables to be used in this method
		String [] lineInput = splitInputByLine(taUserInputL10T3.getText(), 9, "L10T3");
		String [] userOutputL10T3=new String[2];
		userOutputL10T3[0]="";
		userOutputL10T3[1]="";
		String counter=forLoopInitializationArraySize(lineInput[2], 0, "playerNames", 1);
		String scannerObject="";

		if(lineInput[0].equals("not valid"))
		{
			userOutputL10T3[0]=addErrorMessage(userOutputL10T3[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL10T3[1]=outputFontFormat(userOutputL10T3[0], fontSize);

			//returning the array containing the text and style for the output text area in L10T3
			return userOutputL10T3;
		}
		
		if(lineInput[8].startsWith("Your code should contain exactly"))
		{
			userOutputL10T3[0]=addErrorMessage(userOutputL10T3[0],lineInput[8]);
		}
		
		scannerObject=scannerObjectName(lineInput[0]);

		//when user has not correctly created the scanner object
		if(scannerObject.equals(""))
		{
			userOutputL10T3[0]=addErrorMessage(userOutputL10T3[0], "Don't forget to create the object from the Scanner class in order to gather input from the user for this task.");
		}//end if
		
		if(!arrayInitializationChecker(lineInput[1],"String", "playerNames", 5, "none"))
		{
			userOutputL10T3[0]=addErrorMessage(userOutputL10T3[0],"Don't forget to properly create and initialize the String array called playerNames on the fifth line of code.");
		}
		
		if(counter.equals("invalid counter name"))
		{
			
		}
		else if(lineInput[3].equals("{") && lineInput[6].equals("}"))
		{
			if(lineInput[4].contains("nextLine"))
			{
				lineInput[4]=lineInput[4].replaceAll("\\s+","");
				if(!lineInput[4].equals(counter+"="+scannerObject+".nextLine();")) 
				{
					
				}
			}
			if(lineInput[5].startsWith("System.out.println") && lineInput[5].contains("Hello, ") && lineInput[5].contains("playerNames") && lineInput[5].contains("\"!\""))
			{
				lineInput[5]=lineInput[5].replaceAll("\\s+","");
				if(!lineInput[5].equals("System.out.println(\"Hello,\"+playerNames["+counter+"]+\"!\");")) 
				{
					
				}
			}
		}

		if(!lineInput[7].equals(scannerObject+".close();"))
		{
			userOutputL10T3[0]=addErrorMessage(userOutputL10T3[0], "Don't forget to close your scanner object on the sixth line of code.");
		}//end if

		userOutputL10T3[1]=outputFontFormat(userOutputL10T3[0], fontSize);

		//returning the array containing the text and style for the output text area in L10T3
		return userOutputL10T3;
	}//end submitL10T3
	
	
	/* Method Author: Brooke Cronin
	 * Method Name: submitL10T4
	 * Description: Searches for what the user has assigned myAge to, makes sure that it is a double, then assigns it to the output text area.
	 * Parameters: TextArea taUserInputL10T4
	 * Returns: String [] userOutputL10T4
	 */

	public static String [] submitL10T4(TextArea taUserInputL10T4, String fontSize)
	{
		//creating and initializing the array to store the user's output and the text style as well as all variables to be used in this method
		String [] lineInput = splitInputByLine(taUserInputL10T4.getText(), 13, "L10T4");
		String [] userOutputL10T4=new String[2];
		userOutputL10T4[0]="";
		userOutputL10T4[1]="";
		String counter1=forLoopInitialization(lineInput[4], 0, 8, 1);
		String count1="";
		boolean idx0=false;
		boolean idx8=false;

		if(lineInput[0].equals("not valid"))
		{
			userOutputL10T4[0]=addErrorMessage(userOutputL10T4[0],"You have modified some of the given code. Try the task again and do NOT modify any of the given lines of code.");
			userOutputL10T4[1]=outputFontFormat(userOutputL10T4[0], fontSize);

			//returning the array containing the text and style for the output text area in L10T4
			return userOutputL10T4;
		}
		
		if(lineInput[12].startsWith("Your code should contain exactly"))
		{
			userOutputL10T4[0]=addErrorMessage(userOutputL10T4[0],lineInput[12]);
		}


		if(lineInput[0].equals("public static void main()"))
		{
			if(lineInput[1].equals("{") && lineInput[6].equals("}"))
			{
				if(arrayInitializationChecker(lineInput[3], "int", "numValues", 7, "5,43,68,4,-2,93,3"))
				{
					if(counter1.equals("invalid counter name"))
					{
						if(lineInput[4].startsWith("int ")&& lineInput[4].endsWith(";"))
						{
							for(int i=3; i<lineInput[4].length(); i++)
							{
								if(lineInput[4].charAt(i)==' ' || lineInput[4].charAt(i)=='=')
								{
									break;
								}
								else
								{
									count1+=lineInput[4].charAt(i);
								}
							}
							lineInput[4]=lineInput[4].replaceAll("\\s+","");
							if(lineInput[4].equals("int"+count1+"=0;")&&!count1.equals(""))
							{
								if(lineInput[5].startsWith("while"))
								{
									lineInput[5]=lineInput[5].replaceAll("\\s+","");
									if(lineInput[5].equals("while("+count1+"<7)") || lineInput[5].equals("while("+count1+"<=6)"))
									{
										if(lineInput[6].equals("{")&& lineInput[8].equals("}"))
										{
											if(lineInput[7].startsWith("System.out.println")&&lineInput[7].contains("numValues"))
											{
												lineInput[7]=lineInput[7].replaceAll("\\s+","");
												if(lineInput[7].equals("System.out.println(numValues["+count1+"]);"))
												{

												}
											}
										}
									}
								}
							}
						}
					}
					else if(lineInput[5].equals("{")&& lineInput[7].equals("}"))
					{
						if(lineInput[6].startsWith("System.out.println")&&lineInput[6].contains("numValues"))
						{
							lineInput[6]=lineInput[6].replaceAll("\\s+","");
							if(lineInput[6].equals("System.out.println(numValues["+counter1+"]);"))
							{
								if(lineInput[8].startsWith(""))
								{
									
								}
							}
						}
					}
				}
				else
				{

				}
			}
		}

		//when there has been no errors detected in the user's code yet
		if(userOutputL10T4[0].equals(""))
		{
			userOutputL10T4[0]="83\n2\n3\n4\n5\n6\n7\n-4\n9\n10";
		}//end if

		userOutputL10T4[1]=outputFontFormat(userOutputL10T4[0], fontSize);

		//returning the array containing the text and style for the output text area in L10T4
		return userOutputL10T4;
	}//end submitL10T4

	
	/* Method Author: Brooke Cronin
	 * Method Name: addErrorMessage
	 * Description: Adds an error message from a submit task method.
	 * Parameters: String userOutput, String errorMessage
	 * Returns: String 
	 */
	
	public static String addErrorMessage(String userOutput, String errorMessage)
	{
		if(userOutput.equals(""))
		{
			return "Error!\n- "+errorMessage;
		}//end inside if

		else
		{
			return userOutput+"\n- "+errorMessage;
		}//end else
	}//end addErrorMessage method


	/* Method Author: Brooke Cronin
	 * Method Name: splitInputByLine
	 * Description: Creates a new array, assigns each line of code from user's input to a seperate index in the array.
	 * Parameters: String userInput
	 * Returns: String [] lineInput
	 */

	public static String [] splitInputByLine(String userInput, int max, String lessonAndTask)
	{
		String [] lineInput = new String [100];
		int counter=0;

		for (int i=0; i<lineInput.length; i++)
		{
			lineInput[i]="empty";
		}//end for loop

		for (String lineOfInput: userInput.split("\\n"))
		{			
			lineInput[counter]=lineOfInput;
			counter++;
		}//end for loop
		
		
		
		if(lineInput[0].equals("public class "+lessonAndTask+"Response"))
		{
			lineInput=Array.deleteElementByIndex(lineInput, 0);
		}
		else
		{
			lineInput[0]="not valid";
			return lineInput;
		}
		if(lineInput[0].equals("{"))
		{
			lineInput=Array.deleteElementByIndex(lineInput, 0);
		}
		else
		{
			lineInput[0]="not valid";
			return lineInput;
		}
		if(lineInput[0].equals("\tpublic static void main()") && !lessonAndTask.equals("L9T1") && !lessonAndTask.equals("L9T2") && !lessonAndTask.equals("L9T3") && !lessonAndTask.equals("L10T4"))
		{
			lineInput=Array.deleteElementByIndex(lineInput, 0);
		}
		else if(lessonAndTask.equals("L9T1") || lessonAndTask.equals("L9T2") || lessonAndTask.equals("L9T3") || lessonAndTask.equals("L10T4"))
		{
			
		}
		else
		{
			lineInput[0]="not valid";
			return lineInput;
		}
		if(lineInput[0].equals("\t{"))
		{
			lineInput=Array.deleteElementByIndex(lineInput, 0);
		}
		else
		{
			lineInput[0]="not valid";
			return lineInput;
		}
		if(lineInput[max].equals("}//end "+lessonAndTask+"Response class"))
		{
			lineInput=Array.deleteElementByIndex(lineInput, max);
		}
		else
		{
			lineInput[0]="not valid";
			return lineInput;
		}
		if(lineInput[max-1].equals("\t}//end main method") && !lessonAndTask.equals("L9T1") && !lessonAndTask.equals("L9T2") && !lessonAndTask.equals("L9T3") && !lessonAndTask.equals("L10T4"))
		{
			lineInput=Array.deleteElementByIndex(lineInput, max-1);
		}
		else if(lessonAndTask.equals("L9T1") || lessonAndTask.equals("L9T2") || lessonAndTask.equals("L9T3") || lessonAndTask.equals("L10T4"))
		{
			
		}
		else
		{
			lineInput[0]="not valid";
			return lineInput;
		}
		
		for(int i=0; i<max-1; i++)
		{
			if(lineInput[i].equals("empty"))
			{
				lineInput[max-1]="Your code should contain exactly "+(max-1)+" lines of code.";
				break;
			}
		}
		if(!lineInput[max-1].equals("empty"))
		{
			lineInput[max-1]="Your code should contain exactly "+(max-1)+" lines of code.";
		}
		
		return lineInput;
	}//end splitInputByLine method


	/* Method Author: Brooke Cronin
	 * Method Name: outputFontFormat
	 * Description: Determines what colour the output font should be based on whether or not the user has made any mistakes, then returns the text colour and size for the output.
	 * Parameters: String userOutput
	 * Returns: String fontFormat
	 */

	public static String outputFontFormat(String userOutput, String fontSize)
	{
		//creating the variable to store the output font colour and size
		String fontFormat="";

		//determining the font colour of the user's output
		if(userOutput.startsWith("Error!"))
		{
			fontFormat="-fx-text-fill: red; -fx-font: "+fontSize+"\"Comic Sans MS\"";
		}//end if
		else
		{
			fontFormat="-fx-text-fill: black; -fx-font: "+fontSize+"\"Comic Sans MS\"";
		}//end else

		//returning the correct font colour and size for the user's attempt
		return fontFormat;
	}//end outputFontFormat method


	/* Method Author: Brooke Cronin
	 * Method Name: scannerObjectName
	 * Description: Determines and returns the name that the user has chosen for the scanner object.
	 * Parameters: String codeLine
	 * Returns: String scannerObject
	 */

	public static String scannerObjectName(String codeLine)
	{
		//creating the variable to store the object name of the scanner
		String scannerObject="";

		if(codeLine.startsWith("Scanner "))
		{
			if(codeLine.endsWith("new Scanner(System.in);"))
			{
				for(int i=7; i<codeLine.length(); i++)
				{
					if(codeLine.charAt(i)=='n' && codeLine.charAt(i+1)=='e' && codeLine.charAt(i+2)=='w'  && codeLine.charAt(i+3)==' ' && codeLine.charAt(i+4)=='S')
					{
						break;
					}//end inside if
					else if(codeLine.charAt(i)!=' ' && codeLine.charAt(i)!='=')
					{
						scannerObject+=codeLine.charAt(i);
					}//end inside else if
				}//end for loop
			}//end inside if
		}//end outside if

		//returning the object name of the scanner
		return scannerObject;
	}//end scannerObjectName method


	/* Method Author: Brooke Cronin
	 * Method Name: randomObjectName
	 * Description: Determines and returns the name that the user has chosen for the scanner object.
	 * Parameters: String codeLine
	 * Returns: String randomObject
	 */

	public static String randomObjectName(String codeLine)
	{
		String randomObject="";
		if(codeLine.startsWith("Random "))
		{
			if(codeLine.endsWith("new Random();") || codeLine.endsWith("new Random ();"))
			{
				for(int i=6; i<codeLine.length(); i++)
				{
					if(codeLine.charAt(i)=='n' && codeLine.charAt(i+1)=='e' && codeLine.charAt(i+2)=='w'  && codeLine.charAt(i+3)==' ' && codeLine.charAt(i+4)=='R')
					{
						break;
					}//end inside if
					else if(codeLine.charAt(i)!=' ' && codeLine.charAt(i)!='=')
					{
						randomObject+=codeLine.charAt(i);
					}//end inside else if
				}//end for loop
			}//end inside if
		}//end outside if

		return randomObject;
	}//end randomObjectName method


	/* Method Author: Brooke Cronin
	 * Method Name: forLoopInitialization
	 * Description: Determines if the initialization for a for loop was done correctly, and returns the counter variable name.
	 * Parameters: String codeLine, int initial, int end, int increment
	 * Returns: String counter
	 */

	public static String forLoopInitialization(String codeLine, int initial, int end, int increment)
	{
		String counter="";
		String part1="";
		String part2="";
		String part3="";

		for(int i=8; i<codeLine.length(); i++)
		{
			if(codeLine.charAt(i)!=' ' && codeLine.charAt(i)!='=')
			{
				counter+=codeLine.charAt(i);
			}//end if
			else
			{
				break;
			}//end if
		}//end for loop

		for(int i=0; i<codeLine.length(); i++)
		{
			if(codeLine.charAt(i)!=';')
			{
				part1+=codeLine.charAt(i);
			}//end if
			else
			{
				break;
			}
		}//end for loop
		part1+=";";

		for(int i=part1.length(); i<codeLine.length(); i++)
		{
			if(codeLine.charAt(i)!=';')
			{
				part2+=codeLine.charAt(i);
			}//end if
			else
			{
				break;
			}
		}//end for loop
		part2+=";";

		for(int i=(part1.length()+part2.length()); i<codeLine.length(); i++)
		{
			part3+=codeLine.charAt(i);
		}//end for loop

		if(part1.contains("int "+counter))
		{
			part1=part1.replaceAll("\\s+","");
			part2=part2.replaceAll("\\s+","");
			part3=part3.replaceAll("\\s+","");
			if(part1.equals("for(int"+counter+"="+initial+";"))
			{
				if(initial<end)
				{

					if(part2.equals(counter+"<="+end+";") || part2.equals(counter+"<"+(end+1)+";"))
					{
						if(increment==1)
						{
							if(part3.equals(counter+"++)"))
							{
								return counter;
							}
						}
						else if(part3.equals(counter+"+="+increment+")") || part3.equals(counter+"="+counter+"+"+increment+")"))
						{
							return counter;
						}

					}
				}
				if(end<initial)
				{

					if(part2.equals(counter+">="+end+";") || part2.equals(counter+">"+(end-1)+";"))
					{

						if(increment==1)
						{
							if(part3.equals(counter+"--)"))
							{
								return counter;
							}
						}

						if(part3.equals(counter+"-="+increment+")") || part3.equals(counter+"="+counter+"-"+increment+")"))
						{
							return counter;
						}
					}
				}
			}
		}

		return "invalid counter name";
	}//end forLoopInitialization method
	
	
	/* Method Author: Brooke Cronin
	 * Method Name: forLoopInitializationArraySize
	 * Description: Determines if the initialization for a for loop was done correctly, and returns the counter variable name.
	 * Parameters: String codeLine, int initial, int end, int increment
	 * Returns: String counter
	 */

	public static String forLoopInitializationArraySize(String codeLine, int initial, String arrayName, int increment)
	{
		String counter="";
		String part1="";
		String part2="";
		String part3="";

		for(int i=8; i<codeLine.length(); i++)
		{
			if(codeLine.charAt(i)!=' ' && codeLine.charAt(i)!='=')
			{
				counter+=codeLine.charAt(i);
			}//end if
			else
			{
				break;
			}//end if
		}//end for loop

		for(int i=0; i<codeLine.length(); i++)
		{
			if(codeLine.charAt(i)!=';')
			{
				part1+=codeLine.charAt(i);
			}//end if
			else
			{
				break;
			}
		}//end for loop
		part1+=";";

		for(int i=part1.length(); i<codeLine.length(); i++)
		{
			if(codeLine.charAt(i)!=';')
			{
				part2+=codeLine.charAt(i);
			}//end if
			else
			{
				break;
			}
		}//end for loop
		part2+=";";

		for(int i=(part1.length()+part2.length()); i<codeLine.length(); i++)
		{
			part3+=codeLine.charAt(i);
		}//end for loop

		if(part1.contains("int "+counter))
		{
			part1=part1.replaceAll("\\s+","");
			part2=part2.replaceAll("\\s+","");
			part3=part3.replaceAll("\\s+","");
			if(part1.equals("for(int"+counter+"="+initial+";"))
			{

				if(part2.equals(counter+"<="+arrayName+".length;"))
				{
					if(increment==1)
					{
						if(part3.equals(counter+"++)"))
						{
							return counter;
						}
					}
					else if(part3.equals(counter+"+="+increment+")") || part3.equals(counter+"="+counter+"+"+increment+")"))
					{
						return counter;
					}

				}

			}
		}

		return "invalid counter name";
	}//end forLoopInitializationArraySize method
	
	public static boolean arrayInitializationChecker(String codeLine, String type, String arrayName, int size, String values)
	{
		if(codeLine.contains("=new ") || codeLine.contains("= new "))
		{
			codeLine=codeLine.replaceAll("\\s+","");
			if(codeLine.equals(type+"[]"+arrayName+"=new"+type+"["+size+"];") && values.equals("none"))
			{
				return true;
			}
			else if(codeLine.equals(type+"[]"+arrayName+"=new"+type+"["+size+"]{"+values+"};"))
			{
				return true;
			}
		}
		return false;
	}
}
