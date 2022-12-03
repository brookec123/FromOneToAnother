package finalProject;
public class UserInfo extends Person
{
	private String fileName;
	private double progressLevel;
	private int progressPercent;
	private String saveLocation;
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: UserInfo
	 * Description: Calls to the setter methods to set the values of fName and lName from the superclass to the parameter values, and the values of fileName, progressLevel, and progressPercent to the parameter values.
	 * Parameters: String fN, String lN, String fileN, double pL, int pP
	 * Returns: N/A
	 */
	
	public UserInfo(String fN, String lN, String fileN, double pL, int pP, String sL)
	{
		super(fN, lN);
		this.setFileName(fileN);
		this.setProgressLevel(pL);
		this.setProgressPercent(pP);
		this.setSaveLocation(sL);
	}//end UserInfo method
	
	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: UserInfo
	 * Description: Calls to the setter methods to set the values of fName and lName from the superclass to the parameter values, and the value of fileName to the parameter value.
	 * Parameters: String fN, String lN, String fileN
	 * Returns: N/A
	 */
	
	public UserInfo(String fN, String lN, String fileN)
	{
		super(fN, lN);
		this.setFileName(fileN);
	}//end UserInfo method

	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: getFileName
	 * Description: Returns the value of fileName.
	 * Parameters: N/A
	 * Returns: String fileName
	 */
	
	public String getFileName()
	{
		return fileName;
	}//end getFileName method

	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: setFileName
	 * Description: Sets the value of fileName to the given parameter value.
	 * Parameters: String fileName
	 * Returns: N/A
	 */
	
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}//end setFileName method

	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: getProgressLevel
	 * Description: Returns the value of progressLevel.
	 * Parameters: N/A
	 * Returns: double progressLevel
	 */
	
	public double getProgressLevel()
	{
		return progressLevel;
	}//end getProgressLevel method

	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: setProgressLevel
	 * Description: Sets the value of progressLevel to the given parameter value.
	 * Parameters: double progressLevel
	 * Returns: N/A
	 */
	
	public void setProgressLevel(double progressLevel)
	{
		this.progressLevel = progressLevel;
	}//end setProgressLevel method

	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: getProgressPercent
	 * Description: Returns the value of progressPercent.
	 * Parameters: N/A
	 * Returns: int progressPercent
	 */
	
	public int getProgressPercent()
	{
		return progressPercent;
	}//end getProgressPercent method

	
	/*
	 * Method Author: Brooke Cronin
	 * Method Name: setProgressPercent
	 * Description: Sets the value of progressPercent to the given parameter value.
	 * Parameters: int progressPercent
	 * Returns: N/A
	 */
	
	public void setProgressPercent(int progressPercent)
	{
		this.progressPercent = progressPercent;
	}//end setProgressPercent method


	public String getSaveLocation() {
		return saveLocation;
	}


	public void setSaveLocation(String saveLocation) {
		this.saveLocation = saveLocation;
	}
	
}//end class

