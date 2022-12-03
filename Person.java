package finalProject;
public class Person
{
	private String fName;
	private String lName;
	private int age;


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: Person
	 * Description: Calls to the setter methods to set the values of name and age to the parameter values.
	 * Parameters: String fN, String lN, int a
	 * Returns: N/A
	 */

	public Person(String fN, String lN, int a)
	{
		this.setfName(fN);
		this.setlName(lN);
		this.setAge(a);
	}//end Person method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: Person
	 * Description: Calls to the setter method to set the values of name to the parameter value.
	 * Parameters: String fN, String lN
	 * Returns: N/A
	 */

	public Person(String fN, String lN)
	{
		this.setfName(fN);
		this.setlName(lN);
	}//end Person method

	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: getfName
	 * Description: Returns the value of fName.
	 * Parameters: N/A
	 * Returns: String fName
	 */
	
	public String getfName()
	{
		return fName;
	}//end getfName method

	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: setfName
	 * Description: Sets the value of fName to the given parameter value.
	 * Parameters: String fName
	 * Returns: N/A
	 */
	
	public void setfName(String fName)
	{
		this.fName = fName;
	}//end setfName method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: getlName
	 * Description: Returns the value of lName.
	 * Parameters: N/A
	 * Returns: String lName
	 */
	
	public String getlName()
	{
		return lName;
	}//end getlName method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: setlName
	 * Description: Sets the value of lName to the given parameter value.
	 * Parameters: String lName
	 * Returns: N/A
	 */
	
	public void setlName(String lName)
	{
		this.lName = lName;
	}//end setlName method

	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: getAge
	 * Description: Returns the value of age.
	 * Parameters: N/A
	 * Returns: int age
	 */
	
	public int getAge()
	{
		return age;
	}//end getAge method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: setAge
	 * Description: Sets the value of age to the given parameter value.
	 * Parameters: int age
	 * Returns: N/A
	 */

	public void setAge(int age)
	{
		this.age = age;
	}//end setAge method

}//end class