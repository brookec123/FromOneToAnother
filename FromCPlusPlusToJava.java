/* Name: Brooke Cronin
 * Teacher: Mrs. McCaffery
 * Course: ICS 4UI
 *
 * Description: This program allows a user who already knows how to code in Python to learn how to code in Java.
 */
package finalProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.*;

public class FromCPlusPlusToJava extends Application
{
	
	public FromCPlusPlusToJava()
	{
	}
	static final int GAP = 10;
	static String LESSON_HEADER_FONT = "50";
	static String TASK_HEADER_FONT = "40";
	static String TASK_INSTR_FONT = "25";
	static String LABEL_FONT = "20";
	static String CONTROL_FONT = "15";
	static String TEXTAREA_FONT = "15";
	static int taWidth=1200;
	static int taLineHeight=30;
	static UserInfo user = new UserInfo("Brooke", "Cronin", "BrookeCroninComputerCopyProgressDONOTEDIT", 1, 100, "C:\\Users\\Brooke\\OneDrive\\Desktop\\School-RCSS-Gr. 12\\Computer Programming\\FromPythonToJava\\");
	//(null, null, null, 0, 0, null);
	static ProgressBar pbsUserProgress = new ProgressBar(1);
	static double progressIncrease=0.028571429;
	
	boolean cannotStart=true;
	String  lastScene="welcome";
	static String [][][] userResponse = new String [13][8][20];
	static int [][] trialCounter = new int [13][8];
	static int iScoreCounter=0;
	static int diceValue1=0;
	static int diceValue2=0;
	static String [] outputL7T3=new String[3];
	static String [] outputL8T4=new String[3];

	@Override
	public void start(Stage myStage) throws Exception
	{
		welcome(myStage);
	}//end start method


	/*
	 * Method Author: Brooke Cronin
	 * Method Name: welcome
	 * Description: Creates the scene to show a welcome window to the user.
	 * Parameters: Stage myStage
	 * Returns: N/A
	 */

	public void welcome(Stage myStage) 
	{
		myStage.setOnHiding( event -> {try {
			exitProgram();
		} catch (IOException e) {
			
			e.printStackTrace();
		}} );
		//creating the vertical box for the welcome screen
		VBox vbxWelcome = new VBox(GAP);
		vbxWelcome.setPadding(new Insets(GAP, GAP, GAP, GAP));

		//adding the user's progress bar to the vertical box
		pbsUserProgress.setMaxWidth(Double.MAX_VALUE);
		vbxWelcome.getChildren().add(pbsUserProgress);

		//creating, setting, and adding a header Label
		AnchorPane apWelcomeHeader = new AnchorPane();
		Label lblWelcome = new Label("Welcome");
		lblWelcome.setStyle("-fx-font-size: "+LESSON_HEADER_FONT);
		lblWelcome.setMaxWidth(Double.MAX_VALUE);
		AnchorPane.setLeftAnchor(lblWelcome, 0.0);
		AnchorPane.setRightAnchor(lblWelcome, 0.0);
		lblWelcome.setAlignment(Pos.CENTER);
		apWelcomeHeader.getChildren().add(lblWelcome);
		vbxWelcome.getChildren().add(apWelcomeHeader);

		//creating, setting, and adding a notice Label
		Label lblNotice = new Label("**Before starting, please review the instructions for this program by clicking the instructions button below");
		lblNotice.setStyle("-fx-font-size: "+LABEL_FONT);
		vbxWelcome.getChildren().add(lblNotice);

		//creating, setting, and adding a first name Label as well as the text field for the user to enter their name
		Label lblUserFName = new Label("* First Name: ");
		lblUserFName.setStyle("-fx-font-size: "+LABEL_FONT);
		TextField txtUserFName = new TextField();

		txtUserFName.setStyle("-fx-font-size: "+TEXTAREA_FONT);
		txtUserFName.setPrefWidth(500);
		txtUserFName.setDisable(cannotStart);
		HBox hbxUserFName = new HBox(20, lblUserFName, txtUserFName);
		vbxWelcome.getChildren().add(hbxUserFName);

		//creating, setting, and adding a last name Label as well as the text field for the user to enter their name
		Label lblUserLName = new Label("* Last Name: ");
		lblUserLName.setStyle("-fx-font-size: "+LABEL_FONT);
		TextField txtUserLName = new TextField();
		txtUserLName.setStyle("-fx-font-size: "+TEXTAREA_FONT);
		txtUserLName.setPrefWidth(500);
		txtUserLName.setDisable(cannotStart);
		HBox hbxUserLName = new HBox(21, lblUserLName, txtUserLName);
		vbxWelcome.getChildren().add(hbxUserLName);

		//creating and adding the buttons at the bottom of the screen
		Button btnInstructions = new Button("Instructions");
		btnInstructions.setOnAction(event -> instructions(myStage));
		Button btnBegin = new Button("Begin!");
		btnBegin.setDisable(cannotStart);
		btnBegin.setOnAction(event -> continueProgram(txtUserFName, txtUserLName, myStage));
		Button btnExit = new Button("Exit");
		btnExit.setOnAction(event -> System.exit(0));
		HBox hbxControls = new HBox(GAP, btnInstructions, btnBegin, btnExit);
		hbxControls.setStyle("-fx-font-size: "+CONTROL_FONT);
		vbxWelcome.getChildren().add(hbxControls);

		//creating the scene
		Scene sceneWelcome = new Scene(vbxWelcome);

		//setting/showing the stage
		myStage.getIcons().add(new Image("FromOneToAnotherLogo.png"));
		myStage.setTitle("From C++ to Java - Welcome");
		myStage.setScene(sceneWelcome);
		myStage.centerOnScreen();
		myStage.show();
	}//end welcome method


	/* Method Author: Brooke Cronin
	 * Method Name: continueProgram
	 * Description: Creates a new object from the Person class extended to the UserInfo class with all of the info the user entered in the welcome scene, checks if the user has already started the program, sends them to the appropriate scene/stage and enables the appropriate buttons.
	 * Parameters: TextField txtUserFName, TextField txtUserLName, Stage myStage
	 * Returns: N/A
	 */

	public void continueProgram(TextField txtUserFName, TextField txtUserLName, Stage myStage)
	{
		myStage.setOnHiding( event -> {try {
			exitProgram();
		} catch (IOException e) {
			
			e.printStackTrace();
		}} );
		//creating the vertical box for the continue screen
		VBox vbxContinue = new VBox(GAP);
		vbxContinue.setPadding(new Insets(GAP, GAP, GAP, GAP));

		//creating the buttons at the bottom of the screen
		Button btnBack = new Button("Back");
		btnBack.setOnAction(event -> 
		{
			((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
			welcome(myStage);
		});
		Button btnStartNew = new Button("Start New");
		btnStartNew.setOnAction(event -> 
		{
			((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
			createNewUser("yes", myStage);
		});
		Button btnContinueOld = new Button("Continue");
		btnContinueOld.setOnAction(event -> 
		{
			((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
			createNewUser("no", myStage);
		});

		//when the user has left the first name and/or last name text area(s) blank
		if(txtUserFName.getText().equals("") || txtUserLName.getText().equals(""))
		{
			welcome(myStage);
		}//end if

		//setting the user's first and last names, and file name
		user.setfName(txtUserFName.getText());
		user.setlName(txtUserLName.getText());
		user.setFileName(user.getfName()+user.getlName()+"ComputerCopyProgressDONOTEDIT.txt");

		//a try catch to check if the user has already started this program before
		try
		{				
			FileReader myReader = new FileReader(user.getFileName());
			BufferedReader input = new BufferedReader(myReader);
			String line = input.readLine();

			//when the user has exited the program properly
			if(line.startsWith("0."))
			{
				//creating, setting, and adding a message Label
				Label lblcanContinueMessage = new Label("You have already started this program before. \nPress the \"Continue\" button below to continue where you ended the last time. \nPress the \"Start New\" button to start again at lesson 1, task 1 (all of your current progress will be lost). \nPress the \"Back\" button to return to the welcome screen.");
				lblcanContinueMessage.setStyle("-fx-font-size: "+LABEL_FONT);
				vbxContinue.getChildren().add(lblcanContinueMessage);

				btnBack.setDisable(false);
				btnStartNew.setDisable(false);
				btnContinueOld.setDisable(false);
			}//end if

			//when the user didn't exit program properly last time or has editied the first line
			else
			{
				//creating, setting, and adding a message Label
				Label lblcannotContinueMessage = new Label("This is your first time trying this program. \nPress the \"Start New\" button to continue with the program. \nIf you know that you have already began the program and want to continue from where you left, press the \"Back\" button to return to the welcome screen. \nEnsure that you have the same spelling and capitals in both your entered first and last names as you did the last time you attempted this program.");
				lblcannotContinueMessage.setStyle("-fx-font-size: "+LABEL_FONT);
				vbxContinue.getChildren().add(lblcannotContinueMessage);

				btnBack.setDisable(false);
				btnStartNew.setDisable(false);
				btnContinueOld.setDisable(true);
			}//end else
		}
		catch (IOException e)
		{
			//creating, setting, and adding a message Label
			Label lblcannotContinueMessage = new Label("This is your first time trying this program. \nPress the \"Start New\" button to continue with the program. \nIf you know that you have already began the program and want to continue from where you left, press the \"Back\" button to return to the welcome screen. \nEnsure that you have the same spelling and capitals in both your entered first and last names as you did the last time you attempted this program.");
			lblcannotContinueMessage.setStyle("-fx-font-size: "+LABEL_FONT);
			vbxContinue.getChildren().add(lblcannotContinueMessage);

			btnBack.setDisable(false);
			btnStartNew.setDisable(false);
			btnContinueOld.setDisable(true);
		}//end try catch

		//adding the buttons at the bottom of the screen
		HBox hbxbuttons = new HBox(GAP, btnBack, btnStartNew, btnContinueOld);
		hbxbuttons.setStyle("-fx-font-size: "+CONTROL_FONT);
		vbxContinue.getChildren().add(hbxbuttons);

		//creating the scene
		Scene sceneContinue = new Scene(vbxContinue);

		//setting/showing the stage
		Stage continueStage = new Stage();

		continueStage.setOnHiding( event -> {try {
			exitProgram();
		} catch (IOException e) {
			
			e.printStackTrace();
		}} );
		
		continueStage.getIcons().add(new Image("FromOneToAnotherLogo.png"));
		continueStage.setTitle("From C++ to Java - ... - Lesson Menu - Continue");
		continueStage.setScene(sceneContinue);
		continueStage.centerOnScreen();
		continueStage.show();
	}//end continueProgram method


	/* Method Author: Brooke Cronin
	 * Method Name: createNewUser
	 * Description: If user has decided to continue in the previous scene, the info from the previous times they have tried this program will be added to the appropriate arrAys, if not everything will be reset.
	 * Parameters: String willRestart, Stage myStage
	 * Returns: N/A
	 */

	public void createNewUser(String willRestart, Stage myStage)
	{	
		myStage.setOnHiding( event -> {try {
			exitProgram();
		} catch (IOException e) {
			
			e.printStackTrace();
		}} );
		//initializing and creating counters for reading from a file
		int lessonCounter=-1;
		int taskCounter=-1;

		//initializing all elements in the userResponse method to "" and all elements in trialCounter to -1
		for(int i=0; i<userResponse.length; i++)
		{
			for(int j=0; j<userResponse[i].length; j++)
			{
				trialCounter[i][j]=-1;
				for(int k=0; k<userResponse[i][j].length; k++)
				{
					userResponse[i][j][k]="";
				}//end third for loop
			}//end second for loop
		}//end first for loop

		//when user would like to start the program from the beginning
		if(willRestart.equals("yes"))
		{
			user.setProgressLevel(0);
			user.setProgressPercent(0);
			introduction(myStage);
		}//end if

		//when user would like to continue from where they left the last time
		else if(willRestart.equals("no"))
		{
			//a try catch to ensure the file is valid
			try
			{
				FileReader myReader = new FileReader(user.getFileName());
				BufferedReader input = new BufferedReader(myReader);
				String line = input.readLine();

				//setting the user's progress level to what they had before
				pbsUserProgress.setProgress(Double.parseDouble(line));
				user.setProgressLevel(Double.parseDouble(line));
				line = input.readLine();

				//a while loop to add the correct values to the right array at the right location
				while(!line.equals("N/A"))
				{
					//when the next info will be added to the next lesson placeholder
					if(line.startsWith("lesson"))
					{
						taskCounter=-1;
						lessonCounter++;
						line = input.readLine();
					}//end if

					//when the next info will be added to the next task placeholder
					else if(line.startsWith("task"))
					{
						taskCounter++;
						line = input.readLine();
					}//end else if

					//when the next info will be added to the next trial placeholder
					else if(line.startsWith("trial"))
					{
						trialCounter[lessonCounter][taskCounter]++;
						line = input.readLine();
					}//end else if

					//when the user's previous response needs to be added to the userResponse array
					else
					{
						userResponse[lessonCounter][taskCounter][trialCounter[lessonCounter][taskCounter]]=line;
						line = input.readLine();
					}//end else
				}//end while loop

				lessonMenu(myStage);
			}
			catch (IOException e)
			{
				welcome(myStage);
			}//end try catch
		}//end else if
	}//end createNewUser method


	/*Method Author: Brooke Cronin
	 * Method Name: backScene
	 * Description: Uses a switch case statement to determine and call the appropriate method when a back button is pressed.
	 * Parameters: Stage myStage
	 * Returns: N/A
	 */

	public void backScene(Stage myStage)
	{
		myStage.setOnHiding( event -> {try {
			exitProgram();
		} catch (IOException e) {
			
			e.printStackTrace();
		}} );
		//a switch case to determine which scene to go to next
		switch (lastScene)
		{
		case "welcome":
			welcome(myStage);
			break;
		case "lessonMenu":
			lessonMenu(myStage);
			break;
		case "lesson1":
			lesson1(myStage);
			break;
		case "lesson2":
			lesson2(myStage);
			break;
		case "lesson3":
			lesson3(myStage);
			break;
		case "lesson4":
			lesson4(myStage);
			break;
		case "lesson5":
			lesson5(myStage);
			break;
		case "lesson6":
			lesson6(myStage);
			break;
		case "lesson7":
			lesson7(myStage);
			break;
		case "lesson8":
			lesson8(myStage);
			break;
		case "lesson9":
			lesson9(myStage);
			break;
		}//end switch case
	}//end backScene method


	/* Method Author: Brooke Cronin
	 * Method Name: instructions
	 * Description: Creates the scene to show the instructions for this program to the user.
	 * Parameters: Stage myStage
	 * Returns: N/A
	 */

	public void instructions(Stage myStage)
	{
		myStage.setOnHiding( event -> {try {
			exitProgram();
		} catch (IOException e) {
			
			e.printStackTrace();
		}} );
		//allowing the user to be able to continue the program after they return back to the welcome scene
		cannotStart=false;

		//creating the vertical box for the instructions screen
		VBox vbxInstr = new VBox(GAP);
		vbxInstr.setPadding(new Insets(GAP, GAP, GAP, GAP));

		//adding the user's progress bar to the vertical box
		pbsUserProgress.setMaxWidth(Double.MAX_VALUE);
		vbxInstr.getChildren().add(pbsUserProgress);

		//creating, setting, and adding the buttons to allow user to change the font sizes
		//for the smallest text size
		Button btnsmallText = new Button("A");
		btnsmallText.setStyle("-fx-font-size: 10");
		btnsmallText.setOnAction(event ->
		{
			LESSON_HEADER_FONT = "30";
			TASK_HEADER_FONT = "25";
			TASK_INSTR_FONT = "20";
			LABEL_FONT = "15";
			CONTROL_FONT = "10";
			TEXTAREA_FONT = "10";
			taWidth=1200;
			taLineHeight=25;
			instructions(myStage);
		});
		//for the medium text size
		Button btnmediumText = new Button("A");
		btnmediumText.setStyle("-fx-font-size: 15");
		btnmediumText.setOnAction(event ->
		{
			LESSON_HEADER_FONT = "50";
			TASK_HEADER_FONT = "40";
			TASK_INSTR_FONT = "25";
			LABEL_FONT = "20";
			CONTROL_FONT = "15";
			TEXTAREA_FONT = "15";
			taWidth=1200;
			taLineHeight=30;
			instructions(myStage);
		});
		//for the largest text size
		Button btnlargeText = new Button("A");
		btnlargeText.setStyle("-fx-font-size: 25");
		btnlargeText.setOnAction(event ->
		{
			LESSON_HEADER_FONT = "60";
			TASK_HEADER_FONT = "50";
			TASK_INSTR_FONT = "35";
			LABEL_FONT = "30";
			CONTROL_FONT = "25";
			TEXTAREA_FONT = "25";
			taWidth=1200;
			taLineHeight=50;
			instructions(myStage);
		});
		//adding these buttons to the top right of the screen underneath the progress bar
		HBox hbxtextSize = new HBox(GAP, btnsmallText, btnmediumText, btnlargeText);
		vbxInstr.getChildren().add(hbxtextSize);

		//creating, setting, and adding a header Label
		AnchorPane apInstructionsHeader = new AnchorPane();
		Label lblInstructionsHeader = new Label("Instructions");
		lblInstructionsHeader.setStyle("-fx-font-size: "+LESSON_HEADER_FONT);
		lblInstructionsHeader.setMaxWidth(Double.MAX_VALUE);
		AnchorPane.setLeftAnchor(lblInstructionsHeader, 0.0);
		AnchorPane.setRightAnchor(lblInstructionsHeader, 0.0);
		lblInstructionsHeader.setAlignment(Pos.CENTER);
		apInstructionsHeader.getChildren().add(lblInstructionsHeader);
		vbxInstr.getChildren().add(apInstructionsHeader);

		//creating, setting, and adding the instructions
		Text texta = new Text("- After reading all instructions, click the back button at the bottom\n" + 
				"- Click one of the buttons at the top left of this screen to choose the font size\n" +
				"- Enter your first and last name (this is used when creating the files to store your information and progress so that the next time you want to use this program, as well as user interaction for some of the tasks. Make sure that there are no spaces and that the first letter is capitalized)\n" + 
				"- Click the begin button to start learning how to code in Java\n" + 
				"- Select the lesson that you would like to begin with\n" + 
				"- In each of the lesson instructions\n" +
				"\t- text in ");
		texta.setFill(Color.BLACK);
		Text textb = new Text("purple");
		textb.setFill(Color.DARKVIOLET);
		Text textc = new Text(" font represents code in Python \n\t- text in ");
		textc.setFill(Color.BLACK);
		Text textd = new Text("blue");
		textd.setFill(Color.BLUE);
		Text texte = new Text(" font represents code in Java \n\t- text in ");
		texte.setFill(Color.BLACK);
		Text textf = new Text("Comic Sans MS");
		textf.setFill(Color.BLACK);
		textf.setStyle("-fx-font: "+TASK_INSTR_FONT+"\"Comic Sans MS\"");
		Text textg = new Text(" represents the intended output for the example/task \n\t- text in ");
		textg.setFill(Color.BLACK);
		Text texth = new Text("brown");
		texth.setFill(Color.rgb(121, 76, 52));
		Text texti = new Text(" font represents variable names (for both Python and Java)\n" +
				"- Successfully complete all tasks in the lesson to increase your progress bar that is located at the top right of your screen as well as progress to the next task/lesson\n" + 
				"- Do not leave any blank lines in your submission, or else, you may receive multiple errors for that line and the code lines following the first blank line that you leave\n" +
				"- After each lesson, choose to restart it, continue to the next lesson, or go back to the lesson menu and choose a different lesson\n" + 
				"- In order to save your progress so that you will be able to resume were you left, click the exit button located at the bottom of the window\n\n" + 
				"**NOTE: If you do not select the exit button, and instead click the x button at the top of the screen, you will lose all progress that you have done and will have to restart from the beginning or where you left off last time**");
		texti.setFill(Color.BLACK);
		TextFlow textflow = new TextFlow(texta, textb, textc, textd, texte, textf, textg, texth, texti);
		textflow.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		vbxInstr.getChildren().addAll(textflow);

		//creating and adding the buttons at the bottom of the screen
		Button btnBack = new Button("Back");
		btnBack.setOnAction(event -> welcome(myStage));
		Button btnExit = new Button("Exit");
		btnExit.setOnAction(event -> System.exit(0));
		HBox hbxButtons = new HBox(GAP, btnBack, btnExit);
		hbxButtons.setStyle("-fx-font-size: "+CONTROL_FONT);
		vbxInstr.getChildren().add(hbxButtons);

		//creating the scene
		Scene sceneInstructions = new Scene(vbxInstr);

		//setting/showing the stage
		myStage.getIcons().add(new Image("FromOneToAnotherLogo.png"));
		myStage.setTitle("From C++ to Java - Welcome - Instructions");
		myStage.setScene(sceneInstructions);
		myStage.centerOnScreen();
		myStage.show();
	}//end instructions method


	/* Method Author: Brooke Cronin
	 * Method Name: introduction
	 * Description: Creates the scene to show the lesson menu for the user and allow them to choose which lesson they would like to complete.
	 * Parameters: Stage myStage
	 * Returns: N/A
	 */

	public void introduction(Stage myStage)
	{
		myStage.setOnHiding( event -> {try {
			exitProgram();
		} catch (IOException e) {
			
			e.printStackTrace();
		}} );
		//creating the vertical box for the introduction screen
		VBox vbxIntro = new VBox(GAP);
		vbxIntro.setPadding(new Insets(GAP, GAP, GAP, GAP));

		//creating, setting, and adding a header Label
		AnchorPane apIntroductionsHeader = new AnchorPane();
		Label lblIntroductionsHeader = new Label("Introduction");
		lblIntroductionsHeader.setStyle("-fx-font-size: "+LESSON_HEADER_FONT);
		lblIntroductionsHeader.setMaxWidth(Double.MAX_VALUE);
		AnchorPane.setLeftAnchor(lblIntroductionsHeader, 0.0);
		AnchorPane.setRightAnchor(lblIntroductionsHeader, 0.0);
		lblIntroductionsHeader.setAlignment(Pos.CENTER);
		apIntroductionsHeader.getChildren().add(lblIntroductionsHeader);
		vbxIntro.getChildren().add(apIntroductionsHeader);

		//creating, setting, and adding the label to introduce the table
		Label lblDifferences = new Label("Below, is a table that outlines the main differences between Python and Java.");
		lblDifferences.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		vbxIntro.getChildren().add(lblDifferences);

		//creating, setting, and adding the table with the main/general differences between java and python
		Label lblDifferenceTitles = new Label("\n\n\tDescription\t\t\t\t\t\tPython\t\t\t\t\t\t\t\tJava");
		lblDifferenceTitles.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		Label lblDifference1 = new Label("Type of file/extention\t\t\tThe extention is .py\t\t\t\t\t\tThe extention is .java");
		lblDifference1.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		Label lblDifference2 = new Label("File set up\t\t\t\t\tAfter creating a new .py file,\t\t\t\tAll code must be inside of a class\n\t\t\t\t\t\t\tyou can begin with your code\t\t\t\t(with the same name as the file)\n\t\t\t\t\t\t\t(classes and methods are optional)\t\t\tand a method (all code at the start\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\twill be in the main method (will be\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tusing other types of methods later)");
		lblDifference2.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		Label lblDifference3 = new Label("Inline comments\t\t\t\tcode #comment\t\t\t\t\t\tcode //comment");
		lblDifference3.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		Label lblDifference4 = new Label("Block comments\t\t\t\t#comment\t\t\t\t\t\t\t//comment\n\t\t\t\t\t\t\tcode\t\t\t\t\t\t\t\t\tcode");
		lblDifference4.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		Label lblDifference5 = new Label("Header comments\t\t\t\t\"\"\" first line of comment\t\t\t\t\t/* firstline of comment\n\t\t\t\t\t\t\t second line of comment\t\t\t\t\t* second line of comment\n\t\t\t\t\t\t\t third line of comment\t\t\t\t\t* third line of comment\n\t\t\t\t\t\t\t\"\"\"\t\t\t\t\t\t\t\t\t*/");
		lblDifference5.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		Label lblFilesBelowDescription = new Label("\nBelow are two images of code for the same program that prints Hello to the screen.\n\n");
		lblFilesBelowDescription.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		vbxIntro.getChildren().addAll(lblDifferenceTitles, lblDifference1, lblDifference2, lblDifference3, lblDifference4, lblDifference5, lblFilesBelowDescription);

//		//creating the image with python example code
//		Image imagePython = new Image("IntroExamplePython.png");
//		ImageView viewPython = new ImageView(imagePython);
//		viewPython.setFitHeight(600);
//		viewPython.setFitWidth(800);
//
//		//creating the image with java example code
//		Image imageJava = new Image("IntroExampleJava.png");
//		ImageView viewJava = new ImageView(imageJava);
//		viewJava.setFitHeight(600);
//		viewJava.setFitWidth(800);
//
//		//adding the 2 above images to an HBox and the VBox
//		HBox hbxexampleImages = new HBox(GAP, viewPython, viewJava);
//		vbxIntro.getChildren().add(hbxexampleImages);

		//creating, setting, and adding some notes about the above 2 images
		Label lblNotes = new Label("\nNotes:\n"
				+ "- You will only have to set up lesson 1 task 1, and after that, it will be pre-set up for you so that you only have to do the code (until you use other kinds of methods)\n"
				+ "- The names of each file will include the specific lesson and task numbers (e.g. for lesson 1 task 1, it would be Lesson1Task1)\n"
				+ "- Commenting is optional for each task, but is highly recommended for good practice");
		lblNotes.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		vbxIntro.getChildren().add(lblNotes);

		//creating and adding the buttons at the bottom of the screen
		Button btnContinue = new Button("Continue");
		btnContinue.setOnAction(event -> 
		{
			lessonMenu(myStage);
		});
		Button btnExit = new Button("Exit");
		btnExit.setOnAction(event ->
		{
			//a try catch to try to save the user's progress, then terminate the program
			try
			{
				exitProgram();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}//end try catch
		});
		HBox hbxIntroControls = new HBox(GAP, btnContinue, btnExit);
		hbxIntroControls.setStyle("-fx-font-size: "+CONTROL_FONT);
		vbxIntro.getChildren().add(hbxIntroControls);

		//creating and setting the scroll pane for this lesson
		ScrollPane spIntro = new ScrollPane();
		spIntro.setContent(vbxIntro);

		//creating the scene
		Scene sceneIntroduction = new Scene(spIntro);

		//setting/showing the stage
		myStage.getIcons().add(new Image("FromOneToAnotherLogo.png"));
		myStage.setTitle("From C++ to Java - Introduction");
		myStage.setScene(sceneIntroduction);
		myStage.centerOnScreen();
		myStage.show();
	}//end instruction method


	/* Method Author: Brooke Cronin
	 * Method Name: lessonMenu
	 * Description: Creates the scene to show the lesson menu for the user and allow them to choose which lesson they would like to complete.
	 * Parameters: Stage myStage
	 * Returns: N/A
	 */

	public void lessonMenu(Stage myStage)
	{
		myStage.setOnHiding( event -> {try {
			exitProgram();
		} catch (IOException e) {
			
			e.printStackTrace();
		}} );
		//creating the vertical box for the lesson menu screen
		VBox vbxLessonMenu = new VBox(GAP);
		vbxLessonMenu.setPadding(new Insets(GAP, GAP, GAP, GAP));

		//adding the user's progress bar to the vertical box
		pbsUserProgress.setMaxWidth(Double.MAX_VALUE);
		vbxLessonMenu.getChildren().add(pbsUserProgress);

		//creating, setting, and adding a header Label
		AnchorPane apLessonMenuHeader = new AnchorPane();
		Label lblLessonMenuHeader = new Label("Lesson Menu");
		lblLessonMenuHeader.setStyle("-fx-font-size: "+LESSON_HEADER_FONT);
		lblLessonMenuHeader.setMaxWidth(Double.MAX_VALUE);
		AnchorPane.setLeftAnchor(lblLessonMenuHeader, 0.0);
		AnchorPane.setRightAnchor(lblLessonMenuHeader, 0.0);
		lblLessonMenuHeader.setAlignment(Pos.CENTER);
		apLessonMenuHeader.getChildren().add(lblLessonMenuHeader);
		vbxLessonMenu.getChildren().add(apLessonMenuHeader);

		//creating, setting, and adding the first section Label
		Label lblSection1 = new Label("Section #1: ");
		lblSection1.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		vbxLessonMenu.getChildren().add(lblSection1);

		//creating, setting, and adding the lessons from the first section in a combo box
		ComboBox<String> cmbS1 = new ComboBox<String>();
		cmbS1.getItems().addAll("Introduction", "Lesson #1: Print to Screen", "Lesson #2: Intro to Variables", "Lesson #3: Updating Variable Values", "Lesson #4: Exploring Basic Math Operations", 
				"Lesson #5: The Math and Random Classes", "Lesson #6: Gathering Input From The User", "Lesson #7: Decisions", "Lesson #8: Loops", "Lesson #9: Methods", "Lesson #10: Arrays");
		cmbS1.setStyle("-fx-font-size: "+TEXTAREA_FONT);
		vbxLessonMenu.getChildren().add(cmbS1);

		//adding an extra space between the drop down menu and buttons
		Label lblExtraSpace = new Label("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		vbxLessonMenu.getChildren().add(lblExtraSpace);

		//creating and adding the buttons at the bottom of the screen
		Button btnBack = new Button("Back");
		btnBack.setOnAction(event -> backScene(myStage));
		Button btnSelect = new Button("Select");
		btnSelect.setOnAction(event -> 
		{
			//trying to go to the method that will allow user to go to their selected lesson and catching when they have selected an invalid lesson
			try
			{
				goToLesson(myStage, cmbS1.getValue().toString());
			}
			catch (IOException e)
			{
				error(myStage);
			}//end try catch
		});
		Button btnExit = new Button("Exit");
		btnExit.setOnAction(event ->
		{
			//a try catch to try to save the user's progress, then terminate the program
			try
			{
				exitProgram();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}//end try catch
		});
		HBox hbxMenuControls = new HBox(GAP, btnBack, btnSelect, btnExit);
		hbxMenuControls.setStyle("-fx-font-size: "+CONTROL_FONT);
		vbxLessonMenu.getChildren().add(hbxMenuControls);

		//creating the scene
		Scene sceneLessonMenu = new Scene(vbxLessonMenu);

		//setting/showing the stage
		myStage.getIcons().add(new Image("FromOneToAnotherLogo.png"));
		myStage.setTitle("From C++ to Java - ... - Lesson Menu");
		myStage.setScene(sceneLessonMenu);
		myStage.centerOnScreen();
		myStage.show();
	}//end lessonMenu method


	/* Method Author: Brooke Cronin
	 * Method Name: goToLesson
	 * Description: Calls to the appropriate method depending on which lesson the user has selected or goes to the error method when user has chosen an invalid lesson.
	 * Parameters: Stage myStage, String lessonSelected
	 * Returns: N/A
	 * Throws: IOException
	 */

	public void goToLesson(Stage myStage, String lessonSelected) throws IOException
	{
		myStage.setOnHiding( event -> {try {
			exitProgram();
		} catch (IOException e) {
			
			e.printStackTrace();
		}} );
		//switch case to determine which method to go to based on what lesson the user has selected
		switch(lessonSelected)
		{
		case "Introduction":
			introduction(myStage);
			break;

		case "Lesson #1: Print to Screen":
			lesson1(myStage);
			break;
		case "Lesson #2: Intro to Variables":
			//when the user has completed all tasks in lesson 1
			if(user.getProgressLevel()>=(progressIncrease*4))
			{
				lesson2(myStage);
			}//end if

			//when the user still needs to complete some of the tasks in lesson 1
			else
			{
				error(myStage);
			}//end else
			break;
		case "Lesson #3: Updating Variable Values":
			if(user.getProgressLevel()>=(progressIncrease*11))
			{
				lesson3(myStage);
			}//end if

			//when the user still needs to complete some of the tasks in lesson 2
			else
			{
				error(myStage);
			}//end else
			break;
		case "Lesson #4: Exploring Basic Math Operations":
			if(user.getProgressLevel()>=(progressIncrease*15))
			{
				lesson4(myStage);
			}//end if

			//when the user still needs to complete some of the tasks in lesson 3
			else
			{
				error(myStage);
			}//end else
			break;
		case "Lesson #5: The Math and Random Classes":
			if(user.getProgressLevel()>=(progressIncrease*19))
			{
				lesson5(myStage);
			}//end if

			//when the user still needs to complete some of the tasks in lesson 4
			else
			{
				error(myStage);
			}//end else
			break;
		case "Lesson #6: Gathering Input From The User":
			if(user.getProgressLevel()>=(progressIncrease*24))
			{
				lesson6(myStage);
			}//end if

			//when the user still needs to complete some of the tasks in lesson 5
			else
			{
				error(myStage);
			}//end else
			break;
		case "Lesson #7: Decisions":
			if(user.getProgressLevel()>=(progressIncrease*28))
			{
				lesson7(myStage);
			}//end if

			//when the user still needs to complete some of the tasks in lesson 6
			else
			{
				error(myStage);
			}//end else
			break;
		case "Lesson #8: Loops":
			if(user.getProgressLevel()>=(progressIncrease*31))
			{
				lesson8(myStage);
			}//end if

			//when the user still needs to complete some of the tasks in lesson 7
			else
			{
				error(myStage);
			}//end else
			break;
		case "Lesson #9: Methods":
			if(user.getProgressLevel()>=(progressIncrease*31))
			{
				lesson9(myStage);
			}//end if

			//when the user still needs to complete some of the tasks in lesson 8
			else
			{
				error(myStage);
			}//end else
			break;
		case "Lesson #10: Arrays":
			if(user.getProgressLevel()>=(progressIncrease*31))
			{
				lesson10(myStage);
			}//end if

			//when the user still needs to complete some of the tasks in lesson 9
			else
			{
				error(myStage);
			}//end else
			break;

		default: //when no lesson was selected
			error(myStage);
			break;
		}//end switch case
	}//end goToLesson method


	/* Method Author: Brooke Cronin
	 * Method Name: error
	 * Description: Creates the scene to show an error message to the user when they have chosen an invalid lesson.
	 * Parameters: Stage myStage
	 * Returns: N/A
	 */

	public void error(Stage myStage)
	{
		myStage.setOnHiding( event -> {try {
			exitProgram();
		} catch (IOException e) {
			
			e.printStackTrace();
		}} );
		//creating the vertical box for the error screen
		VBox vbxError = new VBox(GAP);
		vbxError.setPadding(new Insets(GAP, GAP, GAP, GAP));

		//creating, setting, and adding a message Label
		Label lblErrorMessage = new Label("Sorry, but the lesson you have \nselected either is in the process of being created, \nyou have not completed the previous required lessons, \nor you have selected too many lessons");
		lblErrorMessage.setStyle("-fx-font-size: "+LABEL_FONT);
		vbxError.getChildren().add(lblErrorMessage);

		//creating and adding the buttons at the bottom of the screen
		Button btnBack = new Button("Back");
		btnBack.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnBack.setOnAction(event -> 
		{
			((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
			lessonMenu(myStage); 
		});
		vbxError.getChildren().add(btnBack);

		//creating the scene
		Scene sceneError = new Scene(vbxError);

		//setting/showing the stage
		Stage errorStage = new Stage();

		errorStage.setOnHiding( event -> {try {
			exitProgram();
		} catch (IOException e) {
			
			e.printStackTrace();
		}} );
		
		errorStage.getIcons().add(new Image("FromOneToAnotherLogo.png"));
		errorStage.setTitle("From C++ to Java - ... - Lesson Menu - Error");
		errorStage.setScene(sceneError);
		errorStage.centerOnScreen();
		errorStage.show();
	}//end error method


	/* Method Author: Brooke Cronin
	 * Method Name: lesson1
	 * Description: Creates the scene for the first lesson. Allows the user to view and attempt all of the tasks and get feedback after completing each task.
	 * Parameters: Stage myStage
	 * Returns: N/A
	 */

	public void lesson1(Stage myStage)throws NullPointerException
	{
		myStage.setOnHiding( event -> {try {
			exitProgram();
		} catch (IOException e) {
			
			e.printStackTrace();
		}} );
		//resetting the text in all of the user output text areas and disabling them so that the user can't change what appears in them
		TextArea taUserOutputL1T1 = new TextArea();
		taUserOutputL1T1.setText("Not submitted yet");
		taUserOutputL1T1.setEditable(false);
		taUserOutputL1T1.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
		TextArea taUserOutputL1T2 = new TextArea();
		taUserOutputL1T2.setText("Not submitted yet");
		taUserOutputL1T2.setEditable(false);
		taUserOutputL1T2.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
		TextArea taUserOutputL1T3 = new TextArea();
		taUserOutputL1T3.setText("Not submitted yet");
		taUserOutputL1T3.setEditable(false);
		taUserOutputL1T3.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
		TextArea taUserOutputL1T4 = new TextArea();
		taUserOutputL1T4.setText("Not submitted yet");
		taUserOutputL1T4.setEditable(false);
		taUserOutputL1T4.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");

		//creating the vertical box for lesson 1 screen
		VBox vbxL1 = new VBox(GAP);
		vbxL1.setPadding(new Insets(GAP, GAP, GAP, GAP));

		//adding the user's progress bar to the vertical box
		pbsUserProgress.setMaxWidth(Double.MAX_VALUE);
		vbxL1.getChildren().add(pbsUserProgress);

		//creating, setting, and adding a header Label
		AnchorPane apLesson1TitleHeader = new AnchorPane();
		Label lblLesson1TitleHeader = new Label("Lesson #1: Print to Screen");
		lblLesson1TitleHeader.setStyle("-fx-text-fill: black; -fx-font-size: "+LESSON_HEADER_FONT);
		lblLesson1TitleHeader.setMaxWidth(Double.MAX_VALUE);
		AnchorPane.setLeftAnchor(lblLesson1TitleHeader, 0.0);
		AnchorPane.setRightAnchor(lblLesson1TitleHeader, 0.0);
		lblLesson1TitleHeader.setAlignment(Pos.CENTER);
		apLesson1TitleHeader.getChildren().add(lblLesson1TitleHeader);
		vbxL1.getChildren().add(apLesson1TitleHeader);

		//adding the name of the current task
		Label lblL1T1Header = new Label("\nTask #1: \"Hello World!\"");
		lblL1T1Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		lblL1T1Header.setWrapText(true);
		vbxL1.getChildren().add(lblL1T1Header);

		//creating, setting, and adding the first task instructions
		Text textL1T1a = new Text("\n\nLet's start with printing ");
		textL1T1a.setFill(Color.BLACK);
		Text textL1T1out = new Text("Hello World!");
		textL1T1out.setStyle("-fx-text-fill: black; -fx-font: "+TASK_INSTR_FONT+"\"Comic Sans MS\"");
		Text textL1T1b = new Text(" to the screen. In Python, to print this to the screen you write: ");
		textL1T1b.setFill(Color.BLACK);
		Text textL1T1c = new Text("\nprint(\'Hello World!\')");
		textL1T1c.setFill(Color.DARKVIOLET);
		Text textL1T1d = new Text("\nHowever, Java is a little bit different. Instead of print(' '), Java uses: ");
		textL1T1d.setFill(Color.BLACK);
		Text textL1T1e = new Text("\nSystem.out.println(\"  \");");
		textL1T1e.setFill(Color.BLUE);
		Text textL1T1f = new Text("\nNow, try to print ");
		textL1T1f.setFill(Color.BLACK);
		Text textL1T1output = new Text("Hello World!");
		textL1T1output.setStyle("-fx-text-fill: black; -fx-font: "+TASK_INSTR_FONT+"\"Comic Sans MS\"");
		Text textL1T1g = new Text(" to the screen in Java below");
		textL1T1g.setFill(Color.BLACK);
		TextFlow textflowL1T1 = new TextFlow(textL1T1a, textL1T1out, textL1T1b, textL1T1c, textL1T1d, textL1T1e, textL1T1f, textL1T1output, textL1T1g);
		textflowL1T1.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		vbxL1.getChildren().add(textflowL1T1);

		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput1 = new Label("Your code: ");
		lblInput1.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL1T1 = new TextArea();
		taUserInputL1T1.setPrefSize(taWidth, taLineHeight);
		taUserInputL1T1.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL1T1.setText("public class L1T1Response\n{\n\tpublic static void main()\n\t{\n\n\t}//end main method\n}//end L1T1Response class");
		Button btnSubmitL1T1 = new Button("Submit");
		btnSubmitL1T1.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL1T1Input1 = new HBox(GAP, lblInput1, taUserInputL1T1, btnSubmitL1T1);
		vbxL1.getChildren().add(hbxL1T1Input1);
		//for user's output
		Label lblOutput1 = new Label("Output:     ");
		lblOutput1.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL1T1.setPrefSize(taWidth, taLineHeight);
		HBox hbxL1T1Output = new HBox(GAP, lblOutput1, taUserOutputL1T1);
		vbxL1.getChildren().add(hbxL1T1Output);
		//for user's result
		Label lblCriteria1 = new Label("Criteria");
		CheckBox cbL1T1a = new CheckBox("Begins with System.out.println(\"");
		cbL1T1a.setDisable(true);
		CheckBox cbL1T1b = new CheckBox("Output is Hello World!");
		cbL1T1b.setDisable(true);
		CheckBox cbL1T1c = new CheckBox("Ends with \");");
		cbL1T1c.setDisable(true);
		HBox hbxL1T1Checklist = new HBox(GAP, lblCriteria1, cbL1T1a, cbL1T1b, cbL1T1c);
		hbxL1T1Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria1.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		vbxL1.getChildren().add(hbxL1T1Checklist);

		//adding the name of the current task
		Label lblL1T2Header = new Label("\n\n\nTask #2: My Favourite Quote (\\\")");
		lblL1T2Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		if(user.getProgressLevel()<(progressIncrease))
		{
			lblL1T2Header.setVisible(false);
			lblL1T2Header.setManaged(false);
		}//end if
		vbxL1.getChildren().add(lblL1T2Header);

		//creating, setting, and adding the second task instructions
		Text textL1T2a = new Text("\n\nWhat about adding \"quotations marks\" to your statements? In Python, it is done by: ");
		textL1T2a.setFill(Color.BLACK);
		Text textL1T2b = new Text("\nprint('\"Your quote here\"')");
		textL1T2b.setFill(Color.DARKVIOLET);
		Text textL1T2c = new Text("\nIn Java, quotation marks are added by: ");
		textL1T2c.setFill(Color.BLACK);
		Text textL1T2d = new Text("\nSystem.out.println(\"\\\"Your quote here\\\"\");");
		textL1T2d.setFill(Color.BLUE);
		Text textL1T2e = new Text("\nBelow, try to print your favourite quote to the screen surrounded by quotation marks.");
		textL1T2e.setFill(Color.BLACK);
		TextFlow textflowL1T2 = new TextFlow(textL1T2a, textL1T2b, textL1T2c, textL1T2d, textL1T2e);
		textflowL1T2.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease))
		{
			textflowL1T2.setVisible(false);
			textflowL1T2.setManaged(false);
		}//end if
		vbxL1.getChildren().addAll(textflowL1T2);

		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput2 = new Label("Your code: ");
		lblInput2.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL1T2 = new TextArea();
		taUserInputL1T2.setPrefSize(taWidth, taLineHeight);
		taUserInputL1T2.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL1T2.setText("public class L1T2Response\n{\n\tpublic static void main()\n\t{\n\n\t}//end main method\n}//end L1T2Response class");
		Button btnSubmitL1T2 = new Button("Submit");
		btnSubmitL1T2.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL1T2Input = new HBox(GAP, lblInput2, taUserInputL1T2, btnSubmitL1T2);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease))
		{
			hbxL1T2Input.setVisible(false);
			hbxL1T2Input.setManaged(false);
		}//end if
		vbxL1.getChildren().add(hbxL1T2Input);
		//for user's output
		Label lblOutput2 = new Label("Output:     ");
		lblOutput2.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL1T2.setPrefSize(taWidth, taLineHeight);
		HBox hbxL1T2Output = new HBox(GAP, lblOutput2, taUserOutputL1T2);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease))
		{
			hbxL1T2Output.setVisible(false);
			hbxL1T2Output.setManaged(false);
		}//end if
		vbxL1.getChildren().add(hbxL1T2Output);
		//for user's result
		Label lblCriteria2 = new Label("Criteria");
		CheckBox cbL1T2a = new CheckBox("Begins with System.out.println(\"\"");
		cbL1T2a.setDisable(true);
		CheckBox cbL1T2b = new CheckBox("Output begins with \" and ends with \"");
		cbL1T2b.setDisable(true);
		CheckBox cbL1T2c = new CheckBox("Output contains a quotation");
		cbL1T2c.setDisable(true);
		CheckBox cbL1T2d = new CheckBox("Ends with \"\");");
		cbL1T2d.setDisable(true);
		HBox hbxL1T2Checklist = new HBox(GAP, lblCriteria2, cbL1T2a, cbL1T2b, cbL1T2c, cbL1T2d);
		hbxL1T2Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria2.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease))
		{
			hbxL1T2Checklist.setVisible(false);
			hbxL1T2Checklist.setManaged(false);
		}//end if
		vbxL1.getChildren().add(hbxL1T2Checklist);

		//adding the name of the current task
		Label lblL1T3Header = new Label("\n\n\nTask #3: Printing Many Lines of Text (\\n)");
		lblL1T3Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		if(user.getProgressLevel()<(progressIncrease*2))
		{
			lblL1T3Header.setVisible(false);
			lblL1T3Header.setManaged(false);
		}//end if
		vbxL1.getChildren().add(lblL1T3Header);

		//creating, setting, and adding the third task instructions
		Text textL1T3a = new Text("\n\nDid you know that inside of the ");
		textL1T3a.setFill(Color.BLACK);
		Text textL1T3b = new Text("System.out.println(\"  \");");
		textL1T3b.setFill(Color.BLUE);
		Text textL1T3c = new Text(" statement, you can add multiple lines of text on the same code line?\nYou can do this the same way as in Python by adding \\n. Try to print the following output to the screen using only using one line of code.");
		textL1T3c.setFill(Color.BLACK);
		Text textL1T3d = new Text("\nHello!\nMy name is "+user.getfName()+".\nI can print multiple lines of text to the screen using Java!");
		textL1T3d.setFill(Color.BLACK);
		textL1T3d.setStyle("-fx-font: "+TASK_INSTR_FONT+"\"Comic Sans MS\"");
		TextFlow textflowL1T3 = new TextFlow(textL1T3a, textL1T3b, textL1T3c, textL1T3d);
		textflowL1T3.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*2))
		{
			textflowL1T3.setVisible(false);
			textflowL1T3.setManaged(false);
		}//end if
		vbxL1.getChildren().addAll(textflowL1T3);

		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput3 = new Label("Your code: ");
		lblInput3.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL1T3 = new TextArea();
		taUserInputL1T3.setPrefSize(taWidth, taLineHeight);
		taUserInputL1T3.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL1T3.setText("public class L1T3Response\n{\n\tpublic static void main()\n\t{\n\n\t}//end main method\n}//end L1T3Response class");
		Button btnSubmitL1T3 = new Button("Submit");
		btnSubmitL1T3.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL1T3Input = new HBox(GAP, lblInput3, taUserInputL1T3, btnSubmitL1T3);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*2))
		{
			hbxL1T3Input.setVisible(false);
			hbxL1T3Input.setManaged(false);
		}//end if
		vbxL1.getChildren().add(hbxL1T3Input);
		//for user's output
		Label lblOutput3 = new Label("Output:     ");
		lblOutput3.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL1T3.setPrefSize(taWidth, taLineHeight);
		HBox hbxL1T3Output = new HBox(GAP, lblOutput3, taUserOutputL1T3);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*2))
		{
			hbxL1T3Output.setVisible(false);
			hbxL1T3Output.setManaged(false);
		}//end if
		vbxL1.getChildren().add(hbxL1T3Output);
		//for user's result
		Label lblCriteria3 = new Label("Criteria");
		CheckBox cbL1T3a = new CheckBox("Begins with System.out.println(\"");
		cbL1T3a.setDisable(true);
		CheckBox cbL1T3b = new CheckBox("Output matches above");
		cbL1T3b.setDisable(true);
		CheckBox cbL1T3c = new CheckBox("Code includes \\n after first !, and . only");
		cbL1T3c.setDisable(true);
		CheckBox cbL1T3d = new CheckBox("Ends with \");");
		cbL1T3d.setDisable(true);
		HBox hbxL1T3Checklist = new HBox(GAP, lblCriteria3, cbL1T3a, cbL1T3b, cbL1T3c, cbL1T3d);
		hbxL1T3Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria3.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*2))
		{
			hbxL1T3Checklist.setVisible(false);
			hbxL1T3Checklist.setManaged(false);
		}//end if
		vbxL1.getChildren().add(hbxL1T3Checklist);

		//adding the name of the current task
		Label lblL1T4Header = new Label("\n\n\nTask #4: Tabs (\\t)");
		lblL1T4Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		if(user.getProgressLevel()<(progressIncrease*3))
		{
			lblL1T4Header.setVisible(false);
			lblL1T4Header.setManaged(false);
		}//end if
		vbxL1.getChildren().add(lblL1T4Header);

		//creating, setting, and adding the fourth task instructions
		Text textL1T4a = new Text("\n\nDid you know that inside of the ");
		textL1T4a.setFill(Color.BLACK);
		Text textL1T4b = new Text("System.out.println(\"  \");");
		textL1T4b.setFill(Color.BLUE);
		Text textL1T4c = new Text(" statement, you can add tabs without just pressing the spacebar many times?\nYou can do this the same way as in Python by adding \\t. Try to print the following output to the screen using only using one line of code.");
		textL1T4c.setFill(Color.BLACK);
		Text textL1T4d = new Text("\nT\n\tA\n\t\tB\n\t\t\tS");
		textL1T4d.setFill(Color.BLACK);
		textL1T4d.setStyle("-fx-font: "+TASK_INSTR_FONT+"\"Comic Sans MS\"");
		TextFlow textflowL1T4 = new TextFlow(textL1T4a, textL1T4b, textL1T4c, textL1T4d);
		textflowL1T4.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*3))
		{
			textflowL1T4.setVisible(false);
			textflowL1T4.setManaged(false);
		}//end if
		vbxL1.getChildren().add(textflowL1T4);

		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput4 = new Label("Your code: ");
		lblInput4.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL1T4 = new TextArea();
		taUserInputL1T4.setPrefSize(taWidth, taLineHeight);
		taUserInputL1T4.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL1T4.setText("public class L1T4Response\n{\n\tpublic static void main()\n\t{\n\n\t}//end main method\n}//end L1T4Response class");
		Button btnSubmitL1T4 = new Button("Submit");
		btnSubmitL1T4.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL1T4Input = new HBox(GAP, lblInput4, taUserInputL1T4, btnSubmitL1T4);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*3))
		{
			hbxL1T4Input.setVisible(false);
			hbxL1T4Input.setManaged(false);
		}//end if
		vbxL1.getChildren().add(hbxL1T4Input);
		//for user's output
		Label lblOutput4 = new Label("Output:     ");
		lblOutput4.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL1T4.setPrefSize(taWidth, taLineHeight);
		HBox hbxL1T4Output = new HBox(GAP, lblOutput4, taUserOutputL1T4);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*3))
		{
			hbxL1T4Output.setVisible(false);
			hbxL1T4Output.setManaged(false);
		}//end if
		vbxL1.getChildren().add(hbxL1T4Output);
		//for user's result
		Label lblCriteria4 = new Label("Criteria");
		CheckBox cbL1T4a = new CheckBox("Begins with System.out.println(\"");
		cbL1T4a.setDisable(true);
		CheckBox cbL1T4b = new CheckBox("Output matches above");
		cbL1T4b.setDisable(true);
		CheckBox cbL1T4c = new CheckBox("Code includes the appropriate amount of \\t for each line of output");
		cbL1T4c.setDisable(true);
		CheckBox cbL1T4d = new CheckBox("Ends with \");");
		cbL1T4d.setDisable(true);
		HBox hbxL1T4Checklist = new HBox(GAP, lblCriteria4, cbL1T4a, cbL1T4b, cbL1T4c, cbL1T4d);
		hbxL1T4Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria4.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*3))
		{
			hbxL1T4Checklist.setVisible(false);
			hbxL1T4Checklist.setManaged(false);
		}//end if
		vbxL1.getChildren().add(hbxL1T4Checklist);

		//creating and adding a label to notify user that they have completed the current lesson
		Label lblL1Done = new Label("Congratulations, "+user.getfName()+"! You have finished Lesson #1: Print to Screen.\n" + 
				"Click one of the buttons below to either restart this lesson, choose another lesson from the menu, or continue with the next lesson.");
		lblL1Done.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*4))
		{
			lblL1Done.setVisible(false);
			lblL1Done.setManaged(false);
		}
		vbxL1.getChildren().add(lblL1Done);

		//creating and adding the buttons at the bottom of the screen
		Button btnPreviousLesson = new Button("Previous Lesson");
		btnPreviousLesson.setDisable(true);
		Button btnLessonMenu = new Button("Lesson Menu");
		btnLessonMenu.setOnAction(event -> lessonMenu(myStage));
		Button btnExit = new Button("Exit");
		btnExit.setOnAction(event -> 
		{
			//a try catch to try to save the user's progress, then terminate the program
			try
			{
				exitProgram();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}//end try catch
		});
		Button btnRestartL1 = new Button("Restart Lesson");
		btnRestartL1.setOnAction(event -> lesson1(myStage));
		Button btnNextLesson = new Button("Next Lesson");
		//when the user has not completed the current lesson successfully yet
		if(user.getProgressLevel()<(progressIncrease*4))
		{
			btnNextLesson.setDisable(true);
		}//end if
		btnNextLesson.setOnAction(event -> lesson2(myStage));
		HBox hbxL1Controls = new HBox(GAP, btnPreviousLesson, btnLessonMenu, btnExit, btnRestartL1, btnNextLesson);
		hbxL1Controls.setStyle("-fx-font-size: "+CONTROL_FONT);
		vbxL1.getChildren().add(hbxL1Controls);

		//determining what to do when the submit button for L1T1 is clicked
		btnSubmitL1T1.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			int checkboxSelectedCounterL1T1=0;
			trialCounter[0][0]++;

			//creating the array to store user's output and adding user input to the userResponse array
			String [] outputL1T1=new String[2];
			userResponse[0][0][trialCounter[0][0]]=taUserInputL1T1.getText();

			//determining and setting the output that the user will see based on their submission
			outputL1T1=CheckJava.submitL1(taUserInputL1T1, TEXTAREA_FONT, "1");
			taUserOutputL1T1.setText(outputL1T1[0]);
			taUserOutputL1T1.setStyle(outputL1T1[1]);

			//resetting all checkboxes in this task to be deselected
			cbL1T1a.setSelected(false);
			cbL1T1b.setSelected(false);
			cbL1T1c.setSelected(false);

			//if statements to check the user's inputed code and the generated output and make the correct checkboxes for this task selected
			if(taUserInputL1T1.getText().startsWith("System.out.println(\""))
			{
				cbL1T1a.setSelected(true);
				checkboxSelectedCounterL1T1++;
			}//end if for first checkbox
			if(taUserOutputL1T1.getText().equals("Hello World!"))
			{
				cbL1T1b.setSelected(true);
				checkboxSelectedCounterL1T1++;
			}//end if for second checkbox
			if(taUserInputL1T1.getText().endsWith("\");"))
			{
				cbL1T1c.setSelected(true);
				checkboxSelectedCounterL1T1++;
			}//end if for third checkbox

			//when the user has successfully completed the current task and is ready for the next task
			if(checkboxSelectedCounterL1T1==3)
			{
				//setting the next task as visible
				textflowL1T2.setVisible(true);
				textflowL1T2.setManaged(true);
				hbxL1T2Input.setVisible(true);
				hbxL1T2Input.setManaged(true);
				hbxL1T2Output.setVisible(true);
				hbxL1T2Output.setManaged(true);
				hbxL1T2Checklist.setVisible(true);
				hbxL1T2Checklist.setManaged(true);

				//changing the progress value of the progress bar if this is the user's first time doing the current task
				if(user.getProgressLevel()<progressIncrease)
				{
					pbsUserProgress.setProgress(progressIncrease);
					user.setProgressLevel(pbsUserProgress.getProgress());
				}//end inside if
			}//end outside if
		});//end setOnAction for btnSubmitL1T1

		//determining what to do when the submit button for L1T2 is clicked
		btnSubmitL1T2.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			int checkboxSelectedCounterL1T2=0;
			trialCounter[0][1]++;

			//creating the array to store user's output and adding user input to the userResponse array
			String [] outputL1T2=new String[2];
			userResponse[0][1][trialCounter[0][1]]=taUserInputL1T2.getText();

			//determining and setting the output that the user will see based on their submission
			outputL1T2=CheckJava.submitL1(taUserInputL1T2, TEXTAREA_FONT, "2");
			taUserOutputL1T2.setText(outputL1T2[0]);
			taUserOutputL1T2.setStyle(outputL1T2[1]);

			//resetting all checkboxes in this task to be deselected
			cbL1T2a.setSelected(false);
			cbL1T2b.setSelected(false);
			cbL1T2c.setSelected(false);
			cbL1T2d.setSelected(false);

			//if statements to check the user's inputed code and the generated output and make the correct checkboxes for this task selected
			if(taUserInputL1T2.getText().startsWith("System.out.println(\""))
			{
				cbL1T2a.setSelected(true);
				checkboxSelectedCounterL1T2++;
			}//end if for first checkbox
			if(taUserOutputL1T2.getText().startsWith("\"") && taUserOutputL1T2.getText().endsWith("\""))
			{
				cbL1T2b.setSelected(true);
				checkboxSelectedCounterL1T2++;
			}//end if for second checkbox
			if(taUserOutputL1T2.getText().contains(" "))
			{
				cbL1T2c.setSelected(true);
				checkboxSelectedCounterL1T2++;
			}//end if for third checkbox
			if(taUserInputL1T2.getText().endsWith("\");"))
			{
				cbL1T2d.setSelected(true);
				checkboxSelectedCounterL1T2++;
			}//end if for fourth checkbox

			//when the user has successfully completed the current task and is ready for the next task
			if(checkboxSelectedCounterL1T2==4)
			{
				//setting the next task as visible
				textflowL1T3.setVisible(true);
				textflowL1T3.setManaged(true);
				hbxL1T3Input.setVisible(true);
				hbxL1T3Input.setManaged(true);
				hbxL1T3Output.setVisible(true);
				hbxL1T3Output.setManaged(true);
				hbxL1T3Checklist.setVisible(true);
				hbxL1T3Checklist.setManaged(true);
				//changing the progress value of the progress bar if this is the user's first time doing the current task
				if(user.getProgressLevel()<(progressIncrease*2))
				{
					pbsUserProgress.setProgress((progressIncrease*2));
					user.setProgressLevel(pbsUserProgress.getProgress());
				}//end inside if
			}//end outside if
		});//end setOnAction for btnSubmitL1T2

		//determining what to do when the submit button for L1T3 is clicked
		btnSubmitL1T3.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			int checkboxSelectedCounterL1T3=0;
			trialCounter[0][2]++;

			//creating the array to store user's output and adding user input to the userResponse array
			String [] outputL1T3=new String[2];
			userResponse[0][2][trialCounter[0][2]]=taUserInputL1T3.getText();

			//determining and setting the output that the user will see based on their submission
			outputL1T3=CheckJava.submitL1(taUserInputL1T3, TEXTAREA_FONT, "3");
			taUserOutputL1T3.setText(outputL1T3[0]);
			taUserOutputL1T3.setStyle(outputL1T3[1]);

			//resetting all checkboxes in this task to be deselected
			cbL1T3a.setSelected(false);
			cbL1T3b.setSelected(false);
			cbL1T3c.setSelected(false);
			cbL1T3d.setSelected(false);

			//if statements to check the user's inputed code and the generated output and make the correct checkboxes for this task selected
			if(taUserInputL1T3.getText().startsWith("System.out.println(\""))
			{
				cbL1T3a.setSelected(true);
				checkboxSelectedCounterL1T3++;
			}//end if for first checkbox
			if(taUserOutputL1T3.getText().equals("Hello!\nMy name is "+user.getfName()+".\nI can print multiple lines of text to the screen using Java!"))
			{
				cbL1T3b.setSelected(true);
				cbL1T3c.setSelected(true);
				checkboxSelectedCounterL1T3+=2;
			}//end if for second and third checkbox
			if(taUserInputL1T3.getText().endsWith("\");"))
			{
				cbL1T3d.setSelected(true);
				checkboxSelectedCounterL1T3++;
			}//end if for fourth checkbox

			//when the user has successfully completed the current task and is ready for the next task
			if(checkboxSelectedCounterL1T3==4)
			{
				//setting the next task as visible
				textflowL1T4.setVisible(true);
				textflowL1T4.setManaged(true);
				hbxL1T4Input.setVisible(true);
				hbxL1T4Input.setManaged(true);
				hbxL1T4Output.setVisible(true);
				hbxL1T4Output.setManaged(true);
				hbxL1T4Checklist.setVisible(true);
				hbxL1T4Checklist.setManaged(true);
				//changing the progress value of the progress bar if this is the user's first time doing the current task
				if(user.getProgressLevel()<(progressIncrease*3))
				{
					pbsUserProgress.setProgress((progressIncrease*3));
					user.setProgressLevel(pbsUserProgress.getProgress());
				}//end inside if
			}//end outside if
		});//end setOnAction for btnSubmitL1T3

		//determining what to do when the submit button for L1T4 is clicked
		btnSubmitL1T4.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			int checkboxSelectedCounterL1T4=0;
			trialCounter[0][3]++;

			//creating the array to store user's output and adding user input to the userResponse array
			String [] outputL1T4=new String[2];
			userResponse[0][3][trialCounter[0][3]]=taUserInputL1T4.getText();

			//determining and setting the output that the user will see based on their submission
			outputL1T4=CheckJava.submitL1(taUserInputL1T4, TEXTAREA_FONT, "4");
			taUserOutputL1T4.setText(outputL1T4[0]);
			taUserOutputL1T4.setStyle(outputL1T4[1]);

			//resetting all checkboxes in this task to be deselected
			cbL1T4a.setSelected(false);
			cbL1T4b.setSelected(false);
			cbL1T4c.setSelected(false);
			cbL1T4d.setSelected(false);

			//if statements to check the user's inputed code and the generated output and make the correct checkboxes for this task selected
			if(taUserInputL1T4.getText().startsWith("System.out.println(\""))
			{
				cbL1T4a.setSelected(true);
				checkboxSelectedCounterL1T4++;
			}//end if for first checkbox
			if(taUserOutputL1T4.getText().equals("T\n\tA\n\t\tB\n\t\t\tS"))
			{
				cbL1T4b.setSelected(true);
				cbL1T4c.setSelected(true);
				checkboxSelectedCounterL1T4+=2;
			}//end if for second and third checkbox
			if(taUserInputL1T4.getText().endsWith("\");"))
			{
				cbL1T4d.setSelected(true);
				checkboxSelectedCounterL1T4++;
			}//end if for fourth checkbox

			//when the user has successfully completed the current task and is ready for the next lesson/task
			if(checkboxSelectedCounterL1T4==4)
			{
				//setting the lesson 1 completed label as visible
				lblL1Done.setVisible(true);
				lblL1Done.setManaged(true);
				//changing the progress value of the progress bar if this is the user's first time doing the current task
				if(user.getProgressLevel()<(progressIncrease*4))
				{
					pbsUserProgress.setProgress((progressIncrease*4));
					user.setProgressLevel(pbsUserProgress.getProgress());
				}//end inside if

				btnNextLesson.setDisable(false);
			}//end outside if
		});//end setOnAction for btnSubmitL1T4

		//creating and setting the scroll pane for this lesson
		ScrollPane spL1 = new ScrollPane();
		spL1.setContent(vbxL1);

		//creating the scene
		Scene sceneL1 = new Scene(spL1);

		lastScene="lesson1"; //setting the previous scene to this scene

		//setting/showing the stage
		myStage.getIcons().add(new Image("FromOneToAnotherLogo.png"));
		myStage.setTitle("From C++ to Java - ... - Lesson #1");
		myStage.setScene(sceneL1);
		myStage.centerOnScreen();
		myStage.show();
	}//end lesson1 method


	/* Method Author: Brooke Cronin
	 * Method Name: lesson2
	 * Description: Creates the scene for the second lesson. Allows the user to view and attempt all of the tasks and get feedback after completing each task.
	 * Parameters: Stage myStage
	 * Returns: N/A
	 * Throws: NullPointerException
	 */

	public void lesson2(Stage myStage) throws NullPointerException
	{
		myStage.setOnHiding( event -> {try {
			exitProgram();
		} catch (IOException e) {
			
			e.printStackTrace();
		}} );
		//resetting the text in all of the user output text areas and disabling them so that the user can't change what appears in them
		TextArea taUserOutputL2T1 = new TextArea();
		taUserOutputL2T1.setText("Not submitted yet");
		taUserOutputL2T1.setEditable(false);
		taUserOutputL2T1.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
		TextArea taUserOutputL2T2 = new TextArea();
		taUserOutputL2T2.setText("Not submitted yet");
		taUserOutputL2T2.setEditable(false);
		taUserOutputL2T2.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
		TextArea taUserOutputL2T3 = new TextArea();
		taUserOutputL2T3.setText("Not submitted yet");
		taUserOutputL2T3.setEditable(false);
		taUserOutputL2T3.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
		TextArea taUserOutputL2T4 = new TextArea();
		taUserOutputL2T4.setText("Not submitted yet");
		taUserOutputL2T4.setEditable(false);
		taUserOutputL2T4.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
		TextArea taUserOutputL2T5 = new TextArea();
		taUserOutputL2T5.setText("Not submitted yet");
		taUserOutputL2T5.setEditable(false);
		taUserOutputL2T5.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
		TextArea taUserOutputL2T6 = new TextArea();
		taUserOutputL2T6.setText("Not submitted yet");
		taUserOutputL2T6.setEditable(false);
		taUserOutputL2T6.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");

		//creating the vertical box for lesson 2 screen
		VBox vbxL2 = new VBox(GAP);
		vbxL2.setPadding(new Insets(GAP, GAP, GAP, GAP));

		//adding the user's progress bar to the vertical box
		pbsUserProgress.setMaxWidth(Double.MAX_VALUE);
		vbxL2.getChildren().add(pbsUserProgress);

		//creating, setting, and adding a header Label
		AnchorPane apLesson2TitleHeader = new AnchorPane();
		Label lblLesson2TitleHeader = new Label("Lesson #2: Intro to Variables");
		lblLesson2TitleHeader.setStyle("-fx-text-fill: black; -fx-font-size: "+LESSON_HEADER_FONT);
		lblLesson2TitleHeader.setMaxWidth(Double.MAX_VALUE);
		AnchorPane.setLeftAnchor(lblLesson2TitleHeader, 0.0);
		AnchorPane.setRightAnchor(lblLesson2TitleHeader, 0.0);
		lblLesson2TitleHeader.setAlignment(Pos.CENTER);
		apLesson2TitleHeader.getChildren().add(lblLesson2TitleHeader);
		vbxL2.getChildren().add(apLesson2TitleHeader);

		//adding the name of the current task
		Label lblL2T1Header = new Label("\nTask #1: Integers");
		lblL2T1Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		vbxL2.getChildren().add(lblL2T1Header);

		//creating, setting, and adding the first task instructions
		Text textL2T1a = new Text("\n\nIn Python, when you create a variable, you do not have to specify the type of variable (e.g. whether it contains an integer, double, String, character, or boolean). However, you do in Java when you first create the variable. \nIn this first task, we will begin with creating and initializing an integer variable.\nIn Python, if you want to create and set the variable x to 4, you would do something like what is below: ");
		textL2T1a.setFill(Color.BLACK);
		Text textL2T1b = new Text("\nx");
		textL2T1b.setFill(Color.rgb(121, 76, 52));
		Text textL2T1c = new Text(" = 4");
		textL2T1c.setFill(Color.DARKVIOLET);
		Text textL2T1d = new Text("\nTo create and initialize an integer variable in Java you begin with int, then the variable name (in camelCase with a lowercase letter at the start), then a = followed by the value you would like to set the variable to and a ; at the end.\nUsing the example from above, in Java it would look like something below: ");
		textL2T1d.setFill(Color.BLACK);
		Text textL2T1e = new Text("\nint ");
		textL2T1e.setFill(Color.BLUE);
		Text textL2T1f = new Text("x");
		textL2T1f.setFill(Color.rgb(121, 76, 52));
		Text textL2T1g = new Text(" = 4;");
		textL2T1g.setFill(Color.BLUE);
		Text textL2T1h = new Text("\nCreate an integer variable called favNum below and initialize it with your favourite number. (The output will be the value of favNum).");
		textL2T1h.setFill(Color.BLACK);
		TextFlow textflowL2T1 = new TextFlow(textL2T1a, textL2T1b, textL2T1c, textL2T1d, textL2T1e, textL2T1f, textL2T1g, textL2T1h);
		textflowL2T1.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		vbxL2.getChildren().add(textflowL2T1);
		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput1 = new Label("Your code: ");
		lblInput1.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL2T1 = new TextArea();
		taUserInputL2T1.setPrefSize(taWidth, taLineHeight);
		taUserInputL2T1.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL2T1.setText("public class L2T1Response\n{\n\tpublic static void main()\n\t{\nSystem.out.println(favNum); //this will print the value of favNum to the screen, do not modify this line of code\n\t}//end main method\n}//end L2T1Response class");
		Button btnSubmitL2T1 = new Button("Submit");
		btnSubmitL2T1.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL2T1Input = new HBox(GAP, lblInput1, taUserInputL2T1, btnSubmitL2T1);
		vbxL2.getChildren().add(hbxL2T1Input);
		//for user's output
		Label lblOutput1 = new Label("Output:     ");
		lblOutput1.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL2T1.setPrefSize(taWidth, taLineHeight);
		HBox hbxL2T1Output = new HBox(GAP, lblOutput1, taUserOutputL2T1);
		vbxL2.getChildren().add(hbxL2T1Output);
		//for user's result
		Label lblCriteria1 = new Label("Criteria");
		CheckBox cbL2T1a = new CheckBox("Follows format as shown above");
		cbL2T1a.setDisable(true);
		CheckBox cbL2T1b = new CheckBox("Name of variable is favNum and type of variable is integer");
		cbL2T1b.setDisable(true);
		CheckBox cbL2T1c = new CheckBox("Output is an integer number");
		cbL2T1c.setDisable(true);
		HBox hbxL2T1Checklist = new HBox(GAP, lblCriteria1, cbL2T1a, cbL2T1b, cbL2T1c);
		hbxL2T1Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria1.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		vbxL2.getChildren().add(hbxL2T1Checklist);

		//adding the name of the current task
		Label lblL2T2Header = new Label("\n\n\nTask #2: Doubles");
		lblL2T2Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		if(user.getProgressLevel()<(progressIncrease*5))
		{
			lblL2T2Header.setVisible(false);
			lblL2T2Header.setManaged(false);
		}//end if
		vbxL2.getChildren().add(lblL2T2Header);

		//creating, setting, and adding the second task instructions
		Text textL2T2a = new Text("\n\nWhat about a number that contains a decimal (or a double value)?\nIn Python, to initialize ");
		textL2T2a.setFill(Color.BLACK);
		Text textL2T2b = new Text("y");
		textL2T2b.setFill(Color.rgb(121, 76, 52));
		Text textL2T2c = new Text(" as 9.75, it would be: ");
		textL2T2c.setFill(Color.BLACK);
		Text textL2T2d = new Text("\ny");
		textL2T2d.setFill(Color.rgb(121, 76, 52));
		Text textL2T2e = new Text(" = 9.75");
		textL2T2e.setFill(Color.DARKVIOLET);
		Text textL2T2f = new Text("\nTo create and initialize a double variable in Java you begin with double, then the variable name (in camelCase with a lowercase letter at the start), then a = followed by the value you would like to set the variable to and a ; at the end.\nLet’s see what it would look like if the variable ");
		textL2T2f.setFill(Color.BLACK);
		Text textL2T2g = new Text("y");
		textL2T2g.setFill(Color.rgb(121, 76, 52));
		Text textL2T2h = new Text(" was created and initialized as 9.75: ");
		textL2T2h.setFill(Color.BLACK);		
		Text textL2T2i = new Text("\ndouble ");
		textL2T2i.setFill(Color.BLUE);
		Text textL2T2j = new Text("y");
		textL2T2j.setFill(Color.rgb(121, 76, 52));
		Text textL2T2k = new Text(" = 9.75;");
		textL2T2k.setFill(Color.BLUE);
		Text textL2T2l = new Text("\nCreate a double variable called ");
		textL2T2l.setFill(Color.BLACK);
		Text textL2T2m = new Text("myAge");
		textL2T2m.setFill(Color.rgb(121, 76, 52));
		Text textL2T2n = new Text(" below and initialize it with your age (whole number part is your age in years, decimal number part is the number of months since your last birthday, the output will be the value of ");
		textL2T2n.setFill(Color.BLACK);
		Text textL2T2o = new Text("myAge");
		textL2T2o.setFill(Color.rgb(121, 76, 52));
		Text textL2T2p = new Text(").");
		textL2T2p.setFill(Color.BLACK);
		TextFlow textflowL2T2 = new TextFlow(textL2T2a, textL2T2b, textL2T2c, textL2T2d, textL2T2e, textL2T2f, textL2T2g, textL2T2h, textL2T2i, textL2T2j, textL2T2k, textL2T2l, textL2T2m, textL2T2n, textL2T2o, textL2T2p);
		textflowL2T2.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*5))
		{
			textflowL2T2.setVisible(false);
			textflowL2T2.setManaged(false);
		}//end if
		vbxL2.getChildren().add(textflowL2T2);

		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput2 = new Label("Your code: ");
		lblInput2.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL2T2 = new TextArea();
		taUserInputL2T2.setPrefSize(taWidth, taLineHeight);
		taUserInputL2T2.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL2T2.setText("public class L2T2Response\n{\n\tpublic static void main()\n\t{\n\n\t}//end main method\n}//end L2T2Response class");
		Button btnSubmitL2T2 = new Button("Submit");
		btnSubmitL2T2.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL2T2Input = new HBox(GAP, lblInput2, taUserInputL2T2, btnSubmitL2T2);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*5))
		{
			hbxL2T2Input.setVisible(false);
			hbxL2T2Input.setManaged(false);
		}//end if
		vbxL2.getChildren().add(hbxL2T2Input);
		//for user's output
		Label lblOutput2 = new Label("Output:     ");
		lblOutput2.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL2T2.setPrefSize(taWidth, taLineHeight);
		HBox hbxL2T2Output = new HBox(GAP, lblOutput2, taUserOutputL2T2);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*5))
		{
			hbxL2T2Output.setVisible(false);
			hbxL2T2Output.setManaged(false);
		}//end if
		vbxL2.getChildren().add(hbxL2T2Output);
		//for user's result
		Label lblCriteria2 = new Label("Criteria");
		CheckBox cbL2T2a = new CheckBox("Follows format as shown above");
		cbL2T2a.setDisable(true);
		CheckBox cbL2T2b = new CheckBox("Name of variable is myAge and type of variable is double");
		cbL2T2b.setDisable(true);
		CheckBox cbL2T2c = new CheckBox("Output is a double number");
		cbL2T2c.setDisable(true);
		HBox hbxL2T2Checklist = new HBox(GAP, lblCriteria2, cbL2T2a, cbL2T2b, cbL2T2c);
		hbxL2T2Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria2.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*5))
		{
			hbxL2T2Checklist.setVisible(false);
			hbxL2T2Checklist.setManaged(false);
		}//end if
		vbxL2.getChildren().add(hbxL2T2Checklist);

		//adding the name of the current task
		Label lblL2T3Header = new Label("\n\n\nTask #3: Strings");
		lblL2T3Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		if(user.getProgressLevel()<(progressIncrease*6))
		{
			lblL2T3Header.setVisible(false);
			lblL2T3Header.setManaged(false);
		}//end if
		vbxL2.getChildren().add(lblL2T3Header);

		//creating, setting, and adding the third task instructions
		Text textL2T3a = new Text("\n\nWhat about a String variable?\nIn Python, to initialize ");
		textL2T3a.setFill(Color.BLACK);
		Text textL2T3b = new Text("z");
		textL2T3b.setFill(Color.rgb(121, 76, 52));
		Text textL2T3c = new Text(" to \"How are you today?\", it would be: ");
		textL2T3c.setFill(Color.BLACK);
		Text textL2T3d = new Text("\nz");
		textL2T3d.setFill(Color.rgb(121, 76, 52));
		Text textL2T3e = new Text(" = \"How are you today?\"");
		textL2T3e.setFill(Color.DARKVIOLET);
		Text textL2T3f = new Text("\nTo create and initialize a string variable in Java you begin with String, then the variable name (in camelCase with a lowercase letter at the start), then a = followed by the value you would like to set the variable to with \"\" surrounding it and a ; at the end.\nLet’s see what it would look like if the variable ");
		textL2T3f.setFill(Color.BLACK);
		Text textL2T3g = new Text("z");
		textL2T3g.setFill(Color.rgb(121, 76, 52));
		Text textL2T3h = new Text(" was created and initialized as How are you today?: ");
		textL2T3h.setFill(Color.BLACK);
		Text textL2T3i = new Text("\nString ");
		textL2T3i.setFill(Color.BLUE);
		Text textL2T3j = new Text("z");
		textL2T3j.setFill(Color.rgb(121, 76, 52));
		Text textL2T3k = new Text(" = \"How are you today?\";");
		textL2T3k.setFill(Color.BLUE);
		Text textL2T3l = new Text("\nCreate a string variable called ");
		textL2T3l.setFill(Color.BLACK);
		Text textL2T3m = new Text("myFirstName");
		textL2T3m.setFill(Color.rgb(121, 76, 52));
		Text textL2T3n = new Text(" below and initialize it with your first name. (The output will be the value of ");
		textL2T3n.setFill(Color.BLACK);
		Text textL2T3o = new Text("myFirstName");
		textL2T3o.setFill(Color.rgb(121, 76, 52));
		Text textL2T3p = new Text(").");
		textL2T3p.setFill(Color.BLACK);
		TextFlow textflowL2T3 = new TextFlow(textL2T3a, textL2T3b, textL2T3c, textL2T3d, textL2T3e, textL2T3f, textL2T3g, textL2T3h, textL2T3i, textL2T3j, textL2T3k, textL2T3l, textL2T3m, textL2T3n, textL2T3o, textL2T3p);
		textflowL2T3.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*6))
		{
			textflowL2T3.setVisible(false);
			textflowL2T3.setManaged(false);
		}//end if
		vbxL2.getChildren().add(textflowL2T3);

		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput3 = new Label("Your code: ");
		lblInput3.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL2T3 = new TextArea();
		taUserInputL2T3.setPrefSize(taWidth, taLineHeight);
		taUserInputL2T3.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL2T3.setText("public class L2T3Response\n{\n\tpublic static void main()\n\t{\n\n\t}//end main method\n}//end L2T3Response class");
		Button btnSubmitL2T3 = new Button("Submit");
		btnSubmitL2T3.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL2T3Input = new HBox(GAP, lblInput3, taUserInputL2T3, btnSubmitL2T3);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*6))
		{
			hbxL2T3Input.setVisible(false);
			hbxL2T3Input.setManaged(false);
		}//end if
		vbxL2.getChildren().add(hbxL2T3Input);
		//for user's output
		Label lblOutput3 = new Label("Output:     ");
		lblOutput3.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL2T3.setPrefSize(taWidth, taLineHeight);
		HBox hbxL2T3Output = new HBox(GAP, lblOutput3, taUserOutputL2T3);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*6))
		{
			hbxL2T3Output.setVisible(false);
			hbxL2T3Output.setManaged(false);
		}//end if
		vbxL2.getChildren().add(hbxL2T3Output);
		//for user's result
		Label lblCriteria3 = new Label("Criteria");
		CheckBox cbL2T3a = new CheckBox("Follows format as shown above");
		cbL2T3a.setDisable(true);
		CheckBox cbL2T3b = new CheckBox("Name of variable is myFirstName and type of variable is String");
		cbL2T3b.setDisable(true);
		CheckBox cbL2T3c = new CheckBox("Output is a String value");
		cbL2T3c.setDisable(true);
		HBox hbxL2T3Checklist = new HBox(GAP, lblCriteria3, cbL2T3a, cbL2T3b, cbL2T3c);
		hbxL2T3Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria3.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*6))
		{
			hbxL2T3Checklist.setVisible(false);
			hbxL2T3Checklist.setManaged(false);
		}//end if
		vbxL2.getChildren().add(hbxL2T3Checklist);

		//adding the name of the current task
		Label lblL2T4Header = new Label("\n\n\nTask #4: Characters");
		lblL2T4Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		if(user.getProgressLevel()<(progressIncrease*7))
		{
			lblL2T4Header.setVisible(false);
			lblL2T4Header.setManaged(false);
		}//end if
		vbxL2.getChildren().add(lblL2T4Header);

		//creating, setting, and adding the fourth task instructions
		Text textL2T4a = new Text("\n\nWhat about if you only want a single character as the value of the variable?\nIn Python, to initialize ");
		textL2T4a.setFill(Color.BLACK);
		Text textL2T4b = new Text("w");
		textL2T4b.setFill(Color.rgb(121, 76, 52));
		Text textL2T4c = new Text(" to c, it would be: ");
		textL2T4c.setFill(Color.BLACK);
		Text textL2T4d = new Text("\nw");
		textL2T4d.setFill(Color.rgb(121, 76, 52));
		Text textL2T4e = new Text(" = 'c'");
		textL2T4e.setFill(Color.DARKVIOLET);
		Text textL2T4f = new Text("\nTo create and initialize a character variable in Java you begin with char, then the variable name (in camelCase with a lowercase letter at the start), then a = followed by the value you would like to set the variable to with \'\' surrounding it and a ; at the end.\nLet’s see what it would look like if the variable ");
		textL2T4f.setFill(Color.BLACK);
		Text textL2T4g = new Text("w");
		textL2T4g.setFill(Color.rgb(121, 76, 52));
		Text textL2T4h = new Text(" was created and initialized as c: ");
		textL2T4h.setFill(Color.BLACK);
		Text textL2T4i = new Text("\nchar ");
		textL2T4i.setFill(Color.BLUE);
		Text textL2T4j = new Text("w");
		textL2T4j.setFill(Color.rgb(121, 76, 52));
		Text textL2T4k = new Text(" = 'c';");
		textL2T4k.setFill(Color.BLUE);
		Text textL2T4l = new Text("\nCreate a character variable called ");
		textL2T4l.setFill(Color.BLACK);
		Text textL2T4m = new Text("myLastInitial");
		textL2T4m.setFill(Color.rgb(121, 76, 52));
		Text textL2T4n = new Text(" below and initialize it with the initial of your last name. (The output will be the value of ");
		textL2T4n.setFill(Color.BLACK);
		Text textL2T4o = new Text("myLastInitial");
		textL2T4o.setFill(Color.rgb(121, 76, 52));
		Text textL2T4p = new Text(").");
		textL2T4p.setFill(Color.BLACK);
		TextFlow textflowL2T4 = new TextFlow(textL2T4a, textL2T4b, textL2T4c, textL2T4d, textL2T4e, textL2T4f, textL2T4g, textL2T4h, textL2T4i, textL2T4j, textL2T4k, textL2T4l, textL2T4m, textL2T4n, textL2T4o, textL2T4p);
		textflowL2T4.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*7))
		{
			textflowL2T4.setVisible(false);
			textflowL2T4.setManaged(false);
		}//end if
		vbxL2.getChildren().add(textflowL2T4);

		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput4 = new Label("Your code: ");
		lblInput4.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL2T4 = new TextArea();
		taUserInputL2T4.setPrefSize(taWidth, taLineHeight);
		taUserInputL2T4.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL2T4.setText("public class L2T4Response\n{\n\tpublic static void main()\n\t{\n\n\t}//end main method\n}//end L2T4Response class");
		Button btnSubmitL2T4 = new Button("Submit");
		btnSubmitL2T4.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL2T4Input = new HBox(GAP, lblInput4, taUserInputL2T4, btnSubmitL2T4);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*7))
		{
			hbxL2T4Input.setVisible(false);
			hbxL2T4Input.setManaged(false);
		}//end if
		vbxL2.getChildren().add(hbxL2T4Input);
		//for user's output
		Label lblOutput4 = new Label("Output:     ");
		lblOutput4.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL2T4.setPrefSize(taWidth, taLineHeight);
		HBox hbxL2T4Output = new HBox(GAP, lblOutput4, taUserOutputL2T4);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*7))
		{
			hbxL2T4Output.setVisible(false);
			hbxL2T4Output.setManaged(false);
		}//end if
		vbxL2.getChildren().add(hbxL2T4Output);
		//for user's result
		Label lblCriteria4 = new Label("Criteria");
		CheckBox cbL2T4a = new CheckBox("Follows format as shown above");
		cbL2T4a.setDisable(true);
		CheckBox cbL2T4b = new CheckBox("Name of variable is myLastInitial and type of variable is character");
		cbL2T4b.setDisable(true);
		CheckBox cbL2T4c = new CheckBox("Output is a character value");
		cbL2T4c.setDisable(true);
		HBox hbxL2T4Checklist = new HBox(GAP, lblCriteria4, cbL2T4a, cbL2T4b, cbL2T4c);
		hbxL2T4Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria4.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*7))
		{
			hbxL2T4Checklist.setVisible(false);
			hbxL2T4Checklist.setManaged(false);
		}//end if
		vbxL2.getChildren().add(hbxL2T4Checklist);

		//adding the name of the current task
		Label lblL2T5Header = new Label("\n\n\nTask #5: Booleans");
		lblL2T5Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		if(user.getProgressLevel()<(progressIncrease*8))
		{
			lblL2T5Header.setVisible(false);
			lblL2T5Header.setManaged(false);
		}//end if
		vbxL2.getChildren().add(lblL2T5Header);

		//creating, setting, and adding the fifth task instructions
		Text textL2T5a = new Text("\n\nWhat about if you want to set a variable as true or false?\nIn Python, to initialize ");
		textL2T5a.setFill(Color.BLACK);
		Text textL2T5b = new Text("isV");
		textL2T5b.setFill(Color.rgb(121, 76, 52));
		Text textL2T5c = new Text(" to true, it would be: ");
		textL2T5c.setFill(Color.BLACK);
		Text textL2T5d = new Text("\nisV");
		textL2T5d.setFill(Color.rgb(121, 76, 52));
		Text textL2T5e = new Text(" = True");
		textL2T5e.setFill(Color.DARKVIOLET);
		Text textL2T5f = new Text("\nTo do this, you in Java you begin with boolean, then the variable name (in camelCase with a lowercase letter at the start), then a = followed by the either the word true or false (whichever one you would like to initialize the variable to and must be in lowercase) and a ; at the end.\nLet’s see what it would look like if the variable ");
		textL2T5f.setFill(Color.BLACK);
		Text textL2T5g = new Text("isV");
		textL2T5g.setFill(Color.rgb(121, 76, 52));
		Text textL2T5h = new Text(" was created and initialized as true: ");
		textL2T5h.setFill(Color.BLACK);
		Text textL2T5i = new Text("\nboolean ");
		textL2T5i.setFill(Color.BLUE);
		Text textL2T5j = new Text("isV");
		textL2T5j.setFill(Color.rgb(121, 76, 52));
		Text textL2T5k = new Text(" = true;");
		textL2T5k.setFill(Color.BLUE);
		Text textL2T5l = new Text("\nCreate a boolean variable called ");
		textL2T5l.setFill(Color.BLACK);
		Text textL2T5m = new Text("likesDogs");
		textL2T5m.setFill(Color.rgb(121, 76, 52));
		Text textL2T5n = new Text(" below and initialize it with the initial it whether or not you like dogs. (The output will be the value of ");
		textL2T5n.setFill(Color.BLACK);
		Text textL2T5o = new Text("likesDogs");
		textL2T5o.setFill(Color.rgb(121, 76, 52));
		Text textL2T5p = new Text(").");
		textL2T5p.setFill(Color.BLACK);
		TextFlow textflowL2T5 = new TextFlow(textL2T5a, textL2T5b, textL2T5c, textL2T5d, textL2T5e, textL2T5f, textL2T5g, textL2T5h, textL2T5i, textL2T5j, textL2T5k, textL2T5l, textL2T5m, textL2T5n, textL2T5o, textL2T5p);
		textflowL2T5.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*8))
		{
			textflowL2T5.setVisible(false);
			textflowL2T5.setManaged(false);
		}//end if
		vbxL2.getChildren().add(textflowL2T5);

		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput5 = new Label("Your code: ");
		lblInput5.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL2T5 = new TextArea();
		taUserInputL2T5.setPrefSize(taWidth, taLineHeight);
		taUserInputL2T5.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL2T5.setText("public class L2T5Response\n{\n\tpublic static void main()\n\t{\n\n\t}//end main method\n}//end L2T5Response class");
		Button btnSubmitL2T5 = new Button("Submit");
		btnSubmitL2T5.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL2T5Input = new HBox(GAP, lblInput5, taUserInputL2T5, btnSubmitL2T5);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*8))
		{
			hbxL2T5Input.setVisible(false);
			hbxL2T5Input.setManaged(false);
		}//end if
		vbxL2.getChildren().add(hbxL2T5Input);
		//for user's output
		Label lblOutput5 = new Label("Output:     ");
		lblOutput5.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL2T5.setPrefSize(taWidth, taLineHeight);
		HBox hbxL2T5Output = new HBox(GAP, lblOutput5, taUserOutputL2T5);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*8))
		{
			hbxL2T5Output.setVisible(false);
			hbxL2T5Output.setManaged(false);
		}//end if
		vbxL2.getChildren().add(hbxL2T5Output);
		//for user's result
		Label lblCriteria5 = new Label("Criteria");
		CheckBox cbL2T5a = new CheckBox("Follows format as shown above");
		cbL2T5a.setDisable(true);
		CheckBox cbL2T5b = new CheckBox("Name of variable is likesDogs and type of variable is boolean");
		cbL2T5b.setDisable(true);
		CheckBox cbL2T5c = new CheckBox("Output is a boolean value");
		cbL2T5c.setDisable(true);
		HBox hbxL2T5Checklist = new HBox(GAP, lblCriteria5, cbL2T5a, cbL2T5b, cbL2T5c);
		hbxL2T5Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria5.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*8))
		{
			hbxL2T5Checklist.setVisible(false);
			hbxL2T5Checklist.setManaged(false);
		}//end if
		vbxL2.getChildren().add(hbxL2T5Checklist);

		//adding the name of the current task
		Label lblL2T6Header = new Label("\n\n\nTask #6: Printing Values to The Screen");
		lblL2T6Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		if(user.getProgressLevel()<(progressIncrease*9))
		{
			lblL2T6Header.setVisible(false);
			lblL2T6Header.setManaged(false);
		}//end if
		vbxL2.getChildren().add(lblL2T6Header);

		//creating, setting, and adding the sixth task instructions
		Text textL2T6a = new Text("\n\nNow, let’s put what you learned from the previous lesson and this lesson together. To print the value of a variable to the screen is very similar to in Python. In both languages, the text and variable name is separated by: ");
		textL2T6a.setFill(Color.BLACK);
		Text textL2T6b = new Text("\n\"words\"+");
		textL2T6b.setFill(Color.BLUE);
		Text textL2T6c = new Text("variableName");
		textL2T6c.setFill(Color.rgb(121, 76, 52));
		Text textL2T6d = new Text("+\"more words\"");
		textL2T6d.setFill(Color.BLUE);
		Text textL2T6e = new Text("\nUsing the variables and their values from the previous tasks, print the following statement to the screen and fill in the blanks with the appropriate variable value \n(*Note for this task only, you do not have to recreate and reinitialize the variables and their values unless this is your first time attempting any lesson 2 tasks since you have started the program).");
		textL2T6e.setFill(Color.BLACK);
		Text textL2T6f = new Text("\nHello, my name is __________.\nThe first letter of my last name is __________.\nI am __________ years old now.\nIt is __________ that I like dogs.\nMy favourite number is __________.");
		textL2T6f.setStyle("-fx-text-fill: black; -fx-font: "+TASK_INSTR_FONT+"\"Comic Sans MS\"");
		TextFlow textflowL2T6 = new TextFlow(textL2T6a, textL2T6b, textL2T6c, textL2T6d, textL2T6e, textL2T6f);
		textflowL2T6.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*9))
		{
			textflowL2T6.setVisible(false);
			textflowL2T6.setManaged(false);
		}//end if
		vbxL2.getChildren().add(textflowL2T6);

		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput6 = new Label("Your code: ");
		lblInput6.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL2T6 = new TextArea();
		taUserInputL2T6.setPrefSize(2000, 100);
		taUserInputL2T6.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL2T6.setText("public class L2T6Response\n{\n\tpublic static void main()\n\t{\n\n\t}//end main method\n}//end L2T6Response class");
		Button btnSubmitL2T6 = new Button("Submit");
		btnSubmitL2T6.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL2T6Input = new HBox(GAP, lblInput6, taUserInputL2T6, btnSubmitL2T6);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*9))
		{
			hbxL2T6Input.setVisible(false);
			hbxL2T6Input.setManaged(false);
		}//end if
		vbxL2.getChildren().add(hbxL2T6Input);
		//for user's output
		Label lblOutput6 = new Label("Output:     ");
		lblOutput6.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL2T6.setPrefSize(2000, 100);
		HBox hbxL2T6Output = new HBox(GAP, lblOutput6, taUserOutputL2T6);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*9))
		{
			hbxL2T6Output.setVisible(false);
			hbxL2T6Output.setManaged(false);
		}//end if
		vbxL2.getChildren().add(hbxL2T6Output);
		//for user's result
		Label lblCriteria6 = new Label("Criteria");
		CheckBox cbL2T6a = new CheckBox("Begins with System.out.println(\"");
		cbL2T6a.setDisable(true);
		CheckBox cbL2T6b = new CheckBox("All variable names were placed in the right spots and output text mathches above");
		cbL2T6b.setDisable(true);
		CheckBox cbL2T6c = new CheckBox("Ends with \");");
		cbL2T6c.setDisable(true);
		HBox hbxL2T6Checklist = new HBox(GAP, lblCriteria6, cbL2T6a, cbL2T6b, cbL2T6c);
		hbxL2T6Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria6.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*9))
		{
			hbxL2T6Checklist.setVisible(false);
			hbxL2T6Checklist.setManaged(false);
		}//end if
		vbxL2.getChildren().add(hbxL2T6Checklist);

		//adding the name of the current task
		Label lblL2T7Header = new Label("\n\n\nTask #7: Matching Variable Types, Names, and Values");
		lblL2T7Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		if(user.getProgressLevel()<(progressIncrease*10))
		{
			lblL2T7Header.setVisible(false);
			lblL2T7Header.setManaged(false);
		}//end if
		vbxL2.getChildren().add(lblL2T7Header);

		//creating, setting, and adding the seventh task instruction Label
		Label lblL2T7 = new Label("\n\nBelow, are some drop down lists containing variable names and values about ladybugs. Match the correct variable name and value with the correct variable type then click the submit button when you are finished.");
		lblL2T7.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*10))
		{
			lblL2T7.setVisible(false);
			lblL2T7.setManaged(false);
		}//end if
		vbxL2.getChildren().add(lblL2T7);

		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblVariableTypes = new Label("Variable Types: ");
		Label lblVariableNames = new Label("Variable Names: ");
		Label lblVariableValues = new Label("Variable Values: ");
		HBox hbxHeaders = new HBox(GAP, lblVariableTypes, lblVariableNames, lblVariableValues);
		hbxHeaders.setStyle("-fx-text-fill: black; -fx-font-size: "+CONTROL_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*10))
		{
			hbxHeaders.setVisible(false);
			hbxHeaders.setManaged(false);
		}//end if
		vbxL2.getChildren().add(hbxHeaders);
		//for the integer variable type
		Label lblint = new Label("int");
		ComboBox<String> cmbintNames = new ComboBox<String>();
		cmbintNames.getItems().addAll("numOfSpots", "animal", "colour", "lengthInmm", "isInsect");
		cmbintNames.setValue("not selected");
		ComboBox<String> cmbintValues = new ComboBox<String>();
		cmbintValues.getItems().addAll("ladybug", "r", "4.6", "5", "true");
		cmbintValues.setValue("not selected");
		HBox hbxint = new HBox(GAP, lblint, cmbintNames, cmbintValues);
		hbxint.setStyle("-fx-text-fill: black; -fx-font-size: "+CONTROL_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*10))
		{
			hbxint.setVisible(false);
			hbxint.setManaged(false);
		}//end if
		vbxL2.getChildren().add(hbxint);
		//for the double variable type
		Label lbldouble = new Label("double");
		ComboBox<String> cmbdoubleNames = new ComboBox<String>();
		cmbdoubleNames.getItems().addAll("numOfSpots", "animal", "colour", "lengthInmm", "isInsect");
		cmbdoubleNames.setValue("not selected");
		ComboBox<String> cmbdoubleValues = new ComboBox<String>();
		cmbdoubleValues.getItems().addAll("ladybug", "r", "4.6", "5", "true");
		cmbdoubleValues.setValue("not selected");
		HBox hbxdouble = new HBox(GAP, lbldouble, cmbdoubleNames, cmbdoubleValues);
		hbxdouble.setStyle("-fx-text-fill: black; -fx-font-size: "+CONTROL_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*10))
		{
			hbxdouble.setVisible(false);
			hbxdouble.setManaged(false);
		}//end if
		vbxL2.getChildren().add(hbxdouble);
		//for the String variable type
		Label lblString = new Label("String");
		ComboBox<String> cmbStringNames = new ComboBox<String>();
		cmbStringNames.getItems().addAll("numOfSpots", "animal", "colour", "lengthInmm", "isInsect");
		cmbStringNames.setValue("not selected");
		ComboBox<String> cmbStringValues = new ComboBox<String>();
		cmbStringValues.getItems().addAll("ladybug", "r", "4.6", "5", "true");
		cmbStringValues.setValue("not selected");
		HBox hbxString = new HBox(GAP, lblString, cmbStringNames, cmbStringValues);
		hbxString.setStyle("-fx-text-fill: black; -fx-font-size: "+CONTROL_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*10))
		{
			hbxString.setVisible(false);
			hbxString.setManaged(false);
		}//end if
		vbxL2.getChildren().add(hbxString);
		//for the character variable type
		Label lblchar = new Label("char");
		ComboBox<String> cmbcharNames = new ComboBox<String>();
		cmbcharNames.getItems().addAll("numOfSpots", "animal", "colour", "lengthInmm", "isInsect");
		cmbcharNames.setValue("not selected");
		ComboBox<String> cmbcharValues = new ComboBox<String>();
		cmbcharValues.getItems().addAll("ladybug", "r", "4.6", "5", "true");
		cmbcharValues.setValue("not selected");
		HBox hbxchar = new HBox(GAP, lblchar, cmbcharNames, cmbcharValues);
		hbxchar.setStyle("-fx-text-fill: black; -fx-font-size: "+CONTROL_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*10))
		{
			hbxchar.setVisible(false);
			hbxchar.setManaged(false);
		}//end if
		vbxL2.getChildren().add(hbxchar);
		//for the boolean variable type
		Label lblboolean = new Label("boolean");
		ComboBox<String> cmbbooleanNames = new ComboBox<String>();
		cmbbooleanNames.getItems().addAll("numOfSpots", "animal", "colour", "lengthInmm", "isInsect");
		cmbbooleanNames.setValue("not selected");
		ComboBox<String> cmbbooleanValues = new ComboBox<String>();
		cmbbooleanValues.getItems().addAll("ladybug", "r", "4.6", "5", "true");
		cmbbooleanValues.setValue("not selected");
		HBox hbxboolean = new HBox(GAP, lblboolean, cmbbooleanNames, cmbbooleanValues);
		hbxboolean.setStyle("-fx-text-fill: black; -fx-font-size: "+CONTROL_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*10))
		{
			hbxboolean.setVisible(false);
			hbxboolean.setManaged(false);
		}//end if
		vbxL2.getChildren().add(hbxboolean);
		Button btnSubmitL2T7 = new Button("Submit");
		btnSubmitL2T7.setStyle("-fx-font-size: "+CONTROL_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*10))
		{
			btnSubmitL2T7.setVisible(false);
			btnSubmitL2T7.setManaged(false);
		}//end if
		vbxL2.getChildren().add(btnSubmitL2T7);
		//for user's result
		Label lblCriteria7 = new Label("Criteria");
		CheckBox cbL2T7a = new CheckBox("numOfSpots is matched with the correct type of variable and value");
		cbL2T7a.setDisable(true);
		CheckBox cbL2T7b = new CheckBox("animal is matched with the correct type of variable and value");
		cbL2T7b.setDisable(true);
		CheckBox cbL2T7c = new CheckBox("colour is matched with the correct type of variable and value");
		cbL2T7c.setDisable(true);
		CheckBox cbL2T7d = new CheckBox("lengthInmm is matched with the correct type of variable and value");
		cbL2T7d.setDisable(true);
		CheckBox cbL2T7e = new CheckBox("isInsect is matched with the correct type of variable and value");
		cbL2T7e.setDisable(true);
		HBox hbxL2T7Checklist = new HBox(GAP, lblCriteria7, cbL2T7a, cbL2T7b, cbL2T7c, cbL2T7d, cbL2T7e);
		hbxL2T7Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria7.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*10))
		{
			hbxL2T7Checklist.setVisible(false);
			hbxL2T7Checklist.setManaged(false);
		}//end if
		vbxL2.getChildren().add(hbxL2T7Checklist);

		//creating and adding a label to notify user that they have completed the current lesson
		Label lblL2Done = new Label("Congratulations, "+user.getfName()+"! You have finished Lesson #2: Intro to Variables.\n" + 
				"Click one of the buttons below to either restart this lesson, choose another lesson from the menu, go to the previous lesson, or continue with the next lesson.");
		lblL2Done.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*11))
		{
			lblL2Done.setVisible(false);
			lblL2Done.setManaged(false);
		}//end if
		vbxL2.getChildren().add(lblL2Done);

		//creating and adding the buttons at the bottom of the screen
		Button btnPreviousLesson = new Button("Previous Lesson");
		btnPreviousLesson.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnPreviousLesson.setOnAction(event -> lesson1(myStage));
		Button btnLessonMenu = new Button("Lesson Menu");
		btnLessonMenu.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnLessonMenu.setOnAction(event -> lessonMenu(myStage));
		Button btnExit = new Button("Exit");
		btnExit.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnExit.setOnAction(event -> 
		{
			try
			{
				exitProgram();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		});
		Button btnRestartL2 = new Button("Restart Lesson");
		btnRestartL2.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnRestartL2.setOnAction(event -> lesson2(myStage));
		Button btnNextLesson = new Button("Next Lesson");
		btnNextLesson.setStyle("-fx-font-size: "+CONTROL_FONT);
		if(user.getProgressLevel()<(progressIncrease*11))
		{
			btnNextLesson.setDisable(true);
		}//end if
		btnNextLesson.setOnAction(event -> lesson3(myStage));
		HBox hbxL2Controls = new HBox(GAP, btnPreviousLesson, btnLessonMenu, btnExit, btnRestartL2, btnNextLesson);
		vbxL2.getChildren().add(hbxL2Controls);	

		//determining what to do when the submit button for L2T1 is clicked
		btnSubmitL2T1.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			int checkboxSelectedCounterL2T1=0;
			trialCounter[1][0]++;

			//creating the array to store user's output and adding user input to the userResponse array
			String [] outputL2T1=new String[2];
			userResponse[1][0][trialCounter[1][0]]=taUserInputL2T1.getText();

			//determining and setting the output that the user will see based on their submission
			outputL2T1=CheckJava.submitL2T1(taUserInputL2T1, TEXTAREA_FONT);
			taUserOutputL2T1.setText(outputL2T1[0]);
			taUserOutputL2T1.setStyle(outputL2T1[1]);

			//resetting all checkboxes in this task to be deselected
			cbL2T1a.setSelected(false);
			cbL2T1b.setSelected(false);
			cbL2T1c.setSelected(false);

			//if statements to check the user's inputed code and the generated output and make the correct checkboxes for this task selected
			if(taUserInputL2T1.getText().startsWith("int favNum=") || taUserInputL2T1.getText().startsWith("int favNum ="))
			{
				cbL2T1b.setSelected(true);
				checkboxSelectedCounterL2T1++;
				if(taUserInputL2T1.getText().endsWith(";") && !taUserOutputL2T1.getText().startsWith("Error!"))
				{
					cbL2T1a.setSelected(true);
					checkboxSelectedCounterL2T1++;
				}//end inside if for first checkbox
			}//end outside if for second checkbox
			if(!taUserOutputL2T1.getText().startsWith("Error!"))
			{
				cbL2T1c.setSelected(true);
				checkboxSelectedCounterL2T1++;
			}//end if for third checkbox

			//when the user has successfully completed the current task and is ready for the next task
			if(checkboxSelectedCounterL2T1==3)
			{
				//setting the next task as visible
				textflowL2T2.setVisible(true);
				textflowL2T2.setManaged(true);
				hbxL2T2Input.setVisible(true);
				hbxL2T2Input.setManaged(true);
				hbxL2T2Output.setVisible(true);
				hbxL2T2Output.setManaged(true);
				hbxL2T2Checklist.setVisible(true);
				hbxL2T2Checklist.setManaged(true);

				//changing the progress value of the progress bar if this is the user's first time doing the current task
				if(user.getProgressLevel()<(progressIncrease*5))
				{
					pbsUserProgress.setProgress((progressIncrease*5));
					user.setProgressLevel(pbsUserProgress.getProgress());
				}//end inside if
			}//end outside if
		});//end setOnAction for btnSubmitL2T1

		//determining what to do when the submit button for L2T2 is clicked
		btnSubmitL2T2.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			int checkboxSelectedCounterL2T2=0;
			trialCounter[1][1]++;

			//creating the array to store user's output and adding user input to the userResponse array
			String [] outputL2T2=new String[2];
			userResponse[1][1][trialCounter[1][1]]=taUserInputL2T2.getText();

			//determining and setting the output that the user will see based on their submission
			outputL2T2=CheckJava.submitL2T2(taUserInputL2T2, TEXTAREA_FONT);
			taUserOutputL2T2.setText(outputL2T2[0]);
			taUserOutputL2T2.setStyle(outputL2T2[1]);

			//resetting all checkboxes in this task to be deselected
			cbL2T2a.setSelected(false);
			cbL2T2b.setSelected(false);
			cbL2T2c.setSelected(false);

			//if statements to check the user's inputed code and the generated output and make the correct checkboxes for this task selected
			if(taUserInputL2T2.getText().startsWith("double myAge=") || taUserInputL2T2.getText().startsWith("double myAge ="))
			{
				cbL2T2b.setSelected(true);
				checkboxSelectedCounterL2T2++;
				if(taUserInputL2T2.getText().endsWith(";") && !taUserOutputL2T2.getText().startsWith("Error!"))
				{
					cbL2T2a.setSelected(true);
					checkboxSelectedCounterL2T2++;
				}//end inside if for first checkbox
			}//end outside if for second checkbox
			if(!taUserOutputL2T2.getText().startsWith("Error!"))
			{
				cbL2T2c.setSelected(true);
				checkboxSelectedCounterL2T2++;
			}//end if for third checkbox

			//when the user has successfully completed the current task and is ready for the next task
			if(checkboxSelectedCounterL2T2==3)
			{
				//setting the next task as visible
				textflowL2T3.setVisible(true);
				textflowL2T3.setManaged(true);
				hbxL2T3Input.setVisible(true);
				hbxL2T3Input.setManaged(true);
				hbxL2T3Output.setVisible(true);
				hbxL2T3Output.setManaged(true);
				hbxL2T3Checklist.setVisible(true);
				hbxL2T3Checklist.setManaged(true);

				//changing the progress value of the progress bar if this is the user's first time doing the current task
				if(user.getProgressLevel()<(progressIncrease*6))
				{
					pbsUserProgress.setProgress((progressIncrease*6));
					user.setProgressLevel(pbsUserProgress.getProgress());
				}//end inside if
			}//end outside if
		});//end setOnAction for btnSubmitL2T2

		//determining what to do when the submit button for L2T3 is clicked
		btnSubmitL2T3.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			int checkboxSelectedCounterL2T3=0;
			trialCounter[1][2]++;

			//creating the array to store user's output and adding user input to the userResponse array
			String [] outputL2T3=new String[2];
			userResponse[1][2][trialCounter[1][2]]=taUserInputL2T3.getText();

			//determining and setting the output that the user will see based on their submission
			outputL2T3=CheckJava.submitL2T3(taUserInputL2T3, TEXTAREA_FONT);
			taUserOutputL2T3.setText(outputL2T3[0]);
			taUserOutputL2T3.setStyle(outputL2T3[1]);

			//resetting all checkboxes in this task to be deselected
			cbL2T3a.setSelected(false);
			cbL2T3b.setSelected(false);
			cbL2T3c.setSelected(false);

			//if statements to check the user's inputed code and the generated output and make the correct checkboxes for this task selected
			if(taUserInputL2T3.getText().startsWith("String myFirstName=") || taUserInputL2T3.getText().startsWith("String myFirstName ="))
			{
				cbL2T3b.setSelected(true);
				checkboxSelectedCounterL2T3++;
				if(taUserInputL2T3.getText().endsWith(";") && !taUserOutputL2T3.getText().startsWith("Error!"))
				{
					cbL2T3a.setSelected(true);
					checkboxSelectedCounterL2T3++;
				}//end inside if for first checkbox
			}//end outside if for second checkbox
			if(!taUserOutputL2T3.getText().startsWith("Error!"))
			{
				cbL2T3c.setSelected(true);
				checkboxSelectedCounterL2T3++;
			}//end if for third checkbox

			//when the user has successfully completed the current task and is ready for the next task
			if(checkboxSelectedCounterL2T3==3)
			{
				//setting the next task as visible
				textflowL2T4.setVisible(true);
				textflowL2T4.setManaged(true);
				hbxL2T4Input.setVisible(true);
				hbxL2T4Input.setManaged(true);
				hbxL2T4Output.setVisible(true);
				hbxL2T4Output.setManaged(true);
				hbxL2T4Checklist.setVisible(true);
				hbxL2T4Checklist.setManaged(true);

				//changing the progress value of the progress bar if this is the user's first time doing the current task
				if(user.getProgressLevel()<(progressIncrease*7))
				{
					pbsUserProgress.setProgress((progressIncrease*7));
					user.setProgressLevel(pbsUserProgress.getProgress());
				}//end inside if
			}//end outside if
		});//end setOnAction for btnSubmitL2T3

		//determining what to do when the submit button for L2T4 is clicked
		btnSubmitL2T4.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			int checkboxSelectedCounterL2T4=0;
			trialCounter[1][3]++;

			//creating the array to store user's output and adding user input to the userResponse array
			String [] outputL2T4=new String[2];
			userResponse[1][3][trialCounter[1][3]]=taUserInputL2T4.getText();

			//determining and setting the output that the user will see based on their submission
			outputL2T4=CheckJava.submitL2T4(taUserInputL2T4, TEXTAREA_FONT);
			taUserOutputL2T4.setText(outputL2T4[0]);
			taUserOutputL2T4.setStyle(outputL2T4[1]);

			//resetting all checkboxes in this task to be deselected
			cbL2T4a.setSelected(false);
			cbL2T4b.setSelected(false);
			cbL2T4c.setSelected(false);

			//if statements to check the user's inputed code and the generated output and make the correct checkboxes for this task selected
			if(taUserInputL2T4.getText().startsWith("char myLastInitial=") || taUserInputL2T4.getText().startsWith("char myLastInitial ="))
			{
				cbL2T4b.setSelected(true);
				checkboxSelectedCounterL2T4++;
				if(taUserInputL2T4.getText().endsWith(";") && !taUserOutputL2T4.getText().startsWith("Error!"))
				{
					cbL2T4a.setSelected(true);
					checkboxSelectedCounterL2T4++;
				}//end inside if for first checkbox
			}//end outside if for second checkbox
			if(!taUserOutputL2T4.getText().startsWith("Error!"))
			{
				cbL2T4c.setSelected(true);
				checkboxSelectedCounterL2T4++;
			}//end if for third checkbox

			//when the user has successfully completed the current task and is ready for the next task
			if(checkboxSelectedCounterL2T4==3)
			{
				//setting the next task as visible
				textflowL2T5.setVisible(true);
				textflowL2T5.setManaged(true);
				hbxL2T5Input.setVisible(true);
				hbxL2T5Input.setManaged(true);
				hbxL2T5Output.setVisible(true);
				hbxL2T5Output.setManaged(true);
				hbxL2T5Checklist.setVisible(true);
				hbxL2T5Checklist.setManaged(true);

				//changing the progress value of the progress bar if this is the user's first time doing the current task
				if(user.getProgressLevel()<(progressIncrease*8))
				{
					pbsUserProgress.setProgress((progressIncrease*8));
					user.setProgressLevel(pbsUserProgress.getProgress());
				}//end inside if
			}//end outside if			
		});//end setOnAction for btnSubmitL2T4

		//determining what to do when the submit button for L2T5 is clicked
		btnSubmitL2T5.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			int checkboxSelectedCounterL2T5=0;
			trialCounter[1][4]++;

			//creating the array to store user's output and adding user input to the userResponse array
			String [] outputL2T5=new String[2];
			userResponse[1][4][trialCounter[1][4]]=taUserInputL2T5.getText();

			//determining and setting the output that the user will see based on their submission
			outputL2T5=CheckJava.submitL2T5(taUserInputL2T5, TEXTAREA_FONT);
			taUserOutputL2T5.setText(outputL2T5[0]);
			taUserOutputL2T5.setStyle(outputL2T5[1]);

			//resetting all checkboxes in this task to be deselected
			cbL2T5a.setSelected(false);
			cbL2T5b.setSelected(false);
			cbL2T5c.setSelected(false);

			//if statements to check the user's inputed code and the generated output and make the correct checkboxes for this task selected
			if(taUserInputL2T5.getText().startsWith("boolean likesDogs=") || taUserInputL2T5.getText().startsWith("boolean likesDogs ="))
			{
				cbL2T5b.setSelected(true);
				checkboxSelectedCounterL2T5++;
				if(taUserInputL2T5.getText().endsWith(";") && !taUserOutputL2T5.getText().startsWith("Error!"))
				{
					cbL2T5a.setSelected(true);
					checkboxSelectedCounterL2T5++;
				}//end inside if for first checkbox
			}//end outside if for second checkbox
			if(!taUserOutputL2T5.getText().startsWith("Error!"))
			{
				cbL2T5c.setSelected(true);
				checkboxSelectedCounterL2T5++;
			}//end if for third checkbox

			//when the user has successfully completed the current task and is ready for the next task
			if(checkboxSelectedCounterL2T5==3)
			{
				//setting the next task as visible
				textflowL2T6.setVisible(true);
				textflowL2T6.setManaged(true);
				hbxL2T6Input.setVisible(true);
				hbxL2T6Input.setManaged(true);
				hbxL2T6Output.setVisible(true);
				hbxL2T6Output.setManaged(true);
				hbxL2T6Checklist.setVisible(true);
				hbxL2T6Checklist.setManaged(true);

				//changing the progress value of the progress bar if this is the user's first time doing the current task
				if(user.getProgressLevel()<(progressIncrease*9))
				{
					pbsUserProgress.setProgress((progressIncrease*9));
					user.setProgressLevel(pbsUserProgress.getProgress());
				}//end inside if
			}//end outside if
		});//end setOnAction for btnSubmitL2T5

		//determining what to do when the submit button for L2T6 is clicked
		btnSubmitL2T6.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			int checkboxSelectedCounterL2T6=0;
			trialCounter[1][5]++;

			//creating the array to store user's output and adding user input to the userResponse array
			String [] outputL2T6=new String[2];
			userResponse[1][5][trialCounter[1][5]]=taUserInputL2T6.getText();

			//determining and setting the output that the user will see based on their submission
			outputL2T6=CheckJava.submitL2T6(taUserInputL2T6, taUserOutputL2T1, taUserOutputL2T2, taUserOutputL2T3, taUserOutputL2T4, taUserOutputL2T5, TEXTAREA_FONT);
			taUserOutputL2T6.setText(outputL2T6[0]);
			taUserOutputL2T6.setStyle(outputL2T6[1]);

			//resetting all checkboxes in this task to be deselected
			cbL2T6a.setSelected(false);
			cbL2T6b.setSelected(false);
			cbL2T6c.setSelected(false);

			//if statements to check the user's inputed code and the generated output and make the correct checkboxes for this task selected
			if(taUserOutputL2T6.getText().equals("Hello, my name is "+taUserOutputL2T3.getText()+".\nThe first letter of my last name is "+taUserOutputL2T4.getText()+".\nI am "+taUserOutputL2T2.getText()+" years old now.\nIt is "+taUserOutputL2T5.getText()+" that I like dogs.\nMy favourite number is "+taUserOutputL2T1.getText()+"."))
			{
				cbL2T6b.setSelected(true);
				checkboxSelectedCounterL2T6++;
			}//end if for second checkbox
			else if(taUserOutputL2T6.getText().equals("Hello, my name is "+taUserOutputL2T3.getText()+". \nThe first letter of my last name is "+taUserOutputL2T4.getText()+". \nI am "+taUserOutputL2T2.getText()+" years old now. \nIt is "+taUserOutputL2T5.getText()+" that I like dogs. \nMy favourite number is "+taUserOutputL2T1.getText()+"."))
			{
				cbL2T6b.setSelected(true);
				checkboxSelectedCounterL2T6++;
			}//end else if for second checkbox
			if(taUserInputL2T6.getText().startsWith("System.out.println(\""))
			{
				cbL2T6a.setSelected(true);
				checkboxSelectedCounterL2T6++;
			}//end if for first checkbox
			if(taUserInputL2T6.getText().endsWith("\");"))
			{
				cbL2T6c.setSelected(true);
				checkboxSelectedCounterL2T6++;
			}//end if for third checkbox

			//when the user has successfully completed the current task and is ready for the next task
			if(checkboxSelectedCounterL2T6==3)
			{
				//setting the next task as visible
				lblL2T7.setVisible(true);
				lblL2T7.setManaged(true);
				hbxint.setVisible(true);
				hbxint.setManaged(true);
				hbxdouble.setVisible(true);
				hbxdouble.setManaged(true);
				hbxString.setVisible(true);
				hbxString.setManaged(true);
				hbxchar.setVisible(true);
				hbxchar.setManaged(true);
				hbxboolean.setVisible(true);
				hbxboolean.setManaged(true);
				btnSubmitL2T7.setVisible(true);
				btnSubmitL2T7.setManaged(true);
				hbxL2T7Checklist.setVisible(true);
				hbxL2T7Checklist.setManaged(true);

				//changing the progress value of the progress bar if this is the user's first time doing the current task
				if(user.getProgressLevel()<(progressIncrease*10))
				{
					pbsUserProgress.setProgress((progressIncrease*10));
					user.setProgressLevel(pbsUserProgress.getProgress());
				}//end inside if
			}//end outside if
		});//end setOnAction for btnSubmitL2T6

		//determining what to do when the submit button for L2T7 is clicked
		btnSubmitL2T7.setOnAction(event -> 
		{
			//resetting all checkboxes in this task to be deselected
			cbL2T7a.setSelected(false);
			cbL2T7b.setSelected(false);
			cbL2T7c.setSelected(false);
			cbL2T7d.setSelected(false);
			cbL2T7e.setSelected(false);
			String iName=cmbintNames.getValue().toString();
			String iValue=cmbintValues.getValue().toString();
			String dName=cmbdoubleNames.getValue().toString();
			String dValue=cmbdoubleValues.getValue().toString();
			String sName=cmbStringNames.getValue().toString();
			String sValue=cmbStringValues.getValue().toString();
			String cName=cmbcharNames.getValue().toString();
			String cValue=cmbcharValues.getValue().toString();
			String bName=cmbbooleanNames.getValue().toString();
			String bValue=cmbbooleanValues.getValue().toString();

			//creating/initializing counters for this submission
			int checkboxSelectedCounterL2T7=0;
			trialCounter[1][6]++;
			userResponse[1][6][trialCounter[1][6]]=iName+", "+iValue+", "+dName+", "+dValue+", "+sName+", "+sValue+", "+cName+", "+cValue+", "+bName+", "+bValue;

			//if statements to check the user's inputed code and the generated output and make the correct checkboxes for this task selected
			if(iName.equals("numOfSpots") && iValue.equals("5"))
			{
				cbL2T7a.setSelected(true);
				checkboxSelectedCounterL2T7++;
			}//end if for first checkbox
			if(dName.equals("lengthInmm") && dValue.equals("4.6"))
			{
				cbL2T7b.setSelected(true);
				checkboxSelectedCounterL2T7++;
			}//end if for second checkbox
			if(sName.equals("animal") && sValue.equals("ladybug"))
			{
				cbL2T7c.setSelected(true);
				checkboxSelectedCounterL2T7++;
			}//end if for third checkbox
			if(cName.equals("colour") && cValue.equals("r"))
			{
				cbL2T7d.setSelected(true);
				checkboxSelectedCounterL2T7++;
			}//end if for fourth checkbox
			if(bName.equals("isInsect") && bValue.equals("true"))
			{
				cbL2T7e.setSelected(true);
				checkboxSelectedCounterL2T7++;
			}//end if for fifth checkbox

			//when the user has successfully completed the current task and is ready for the next task
			if(checkboxSelectedCounterL2T7==5)
			{
				//setting the lesson 2 completed label as visible
				lblL2Done.setVisible(true);
				lblL2Done.setManaged(true);
				//changing the progress value of the progress bar if this is the user's first time doing the current task
				if(user.getProgressLevel()<(progressIncrease*11))
				{
					pbsUserProgress.setProgress((progressIncrease*11));
					user.setProgressLevel(pbsUserProgress.getProgress());
				}//end inside if

				btnNextLesson.setDisable(false);
			}//end outside if
		});//end setOnAction for btnSubmitL2T7

		//creating and setting the scroll pane for this lesson
		ScrollPane spL2 = new ScrollPane();
		spL2.setContent(vbxL2);

		//creating the scene
		Scene sceneL2 = new Scene(spL2);

		lastScene="lesson2"; //setting the previous scene to this scene

		//setting/showing the stage
		myStage.getIcons().add(new Image("FromOneToAnotherLogo.png"));
		myStage.setTitle("From C++ to Java - ... - Lesson #2");
		myStage.setScene(sceneL2);
		myStage.centerOnScreen();
		myStage.show();
	}//end lesson2 method


	/* Method Author: Brooke Cronin
	 * Method Name: lesson3
	 * Description: Creates the scene for the third lesson. Allows the user to view and attempt all of the tasks and get feedback after completing each task.
	 * Parameters: Stage myStage
	 * Returns: N/A
	 */

	public void lesson3(Stage myStage)
	{
		myStage.setOnHiding( event -> {try {
			exitProgram();
		} catch (IOException e) {
			
			e.printStackTrace();
		}} );
		//resetting the text in all of the user output text areas and disabling them so that the user can't change what appears in them
		TextArea taUserOutputL3T1 = new TextArea();
		taUserOutputL3T1.setText("Not submitted yet");
		taUserOutputL3T1.setEditable(false);
		taUserOutputL3T1.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
		TextArea taUserOutputL3T2 = new TextArea();
		taUserOutputL3T2.setText("Not submitted yet");
		taUserOutputL3T2.setEditable(false);
		taUserOutputL3T2.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
		TextArea taUserOutputL3T3 = new TextArea();
		taUserOutputL3T3.setText("Not submitted yet");
		taUserOutputL3T3.setEditable(false);
		taUserOutputL3T3.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
		TextArea taUserOutputL3T4 = new TextArea();
		taUserOutputL3T4.setText("Not submitted yet");
		taUserOutputL3T4.setEditable(false);
		taUserOutputL3T4.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");

		//creating the vertical box for lesson 3 screen
		VBox vbxL3 = new VBox(GAP);
		vbxL3.setPadding(new Insets(GAP, GAP, GAP, GAP));

		//adding the user's progress bar to the vertical box
		pbsUserProgress.setMaxWidth(Double.MAX_VALUE);
		vbxL3.getChildren().add(pbsUserProgress);

		//creating, setting, and adding a header Label
		AnchorPane apLesson3TitleHeader = new AnchorPane();
		Label lblLesson3TitleHeader = new Label("Lesson #3: Updating Variable Values");
		lblLesson3TitleHeader.setStyle("-fx-font-size: "+LESSON_HEADER_FONT);
		lblLesson3TitleHeader.setMaxWidth(Double.MAX_VALUE);
		AnchorPane.setLeftAnchor(lblLesson3TitleHeader, 0.0);
		AnchorPane.setRightAnchor(lblLesson3TitleHeader, 0.0);
		lblLesson3TitleHeader.setAlignment(Pos.CENTER);
		apLesson3TitleHeader.getChildren().add(lblLesson3TitleHeader);
		vbxL3.getChildren().add(apLesson3TitleHeader);

		//adding the name of the current task
		Label lblL3T1Header = new Label("\nTask #1: Reinitializing Integers");
		lblL3T1Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		vbxL3.getChildren().add(lblL3T1Header);

		//creating, setting, and adding the first task instructions
		Text textL3T1a = new Text("\n\nNow that you know how to print to the screen, and create/initialize different types of variables, what about modifying the value of a variable? \n" +
				"To do this, after creating and initializing the variable, you can then add other code (including printing this variable to the screen), then change the value and add more code (including printing this variable again to the screen). \n" +
				"Below is an example of how to of this in Java with the integer variable called ");
		textL3T1a.setFill(Color.BLACK);
		Text textL3T1b = new Text("a");
		textL3T1b.setFill(Color.rgb(121, 76, 52));
		Text textL3T1c = new Text(".");
		textL3T1c.setFill(Color.BLACK);
		Text textL3T1d = new Text("\nint ");
		textL3T1d.setFill(Color.BLUE);
		Text textL3T1e = new Text("a");
		textL3T1e.setFill(Color.rgb(121, 76, 52));
		Text textL3T1f = new Text(" = 5; \nSystem.out.println(");
		textL3T1f.setFill(Color.BLUE);
		Text textL3T1g = new Text("a");
		textL3T1g.setFill(Color.rgb(121, 76, 52));
		Text textL3T1h = new Text(");");
		textL3T1h.setFill(Color.BLUE);
		Text textL3T1i = new Text("\na");
		textL3T1i.setFill(Color.rgb(121, 76, 52));
		Text textL3T1j = new Text(" = 3; \nSystem.out.println(");
		textL3T1j.setFill(Color.BLUE);
		Text textL3T1k = new Text("a");
		textL3T1k.setFill(Color.rgb(121, 76, 52));
		Text textL3T1l = new Text(");");
		textL3T1l.setFill(Color.BLUE);
		Text textL3T1m = new Text("\nWith an output of:\n");
		textL3T1m.setFill(Color.BLACK);
		Text textL3T1n = new Text("5\n3");
		textL3T1n.setStyle("-fx-text-fill: black; -fx-font: "+TASK_INSTR_FONT+"\"Comic Sans MS\"");
		Text textL3T1o = new Text("\nIn this task, you will do something similar to what is shown above, except using the variable ");
		textL3T1o.setFill(Color.BLACK);
		Text textL3T1p = new Text("wholeNum");
		textL3T1p.setFill(Color.rgb(121, 76, 52));
		Text textL3T1q = new Text(" instead of ");
		textL3T1q.setFill(Color.BLACK);
		Text textL3T1r = new Text("a");
		textL3T1r.setFill(Color.rgb(121, 76, 52));
		Text textL3T1s = new Text(".");
		textL3T1s.setFill(Color.BLACK);
		TextFlow textflowL3T1 = new TextFlow(textL3T1a, textL3T1b, textL3T1c, textL3T1d, textL3T1e, textL3T1f, textL3T1g, textL3T1h, textL3T1i, textL3T1j, textL3T1k, textL3T1l, textL3T1m, textL3T1n, textL3T1o, textL3T1p, textL3T1q, textL3T1r, textL3T1s);
		textflowL3T1.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		vbxL3.getChildren().addAll(textflowL3T1);

		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput1 = new Label("Your code: ");
		lblInput1.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL3T1 = new TextArea();
		taUserInputL3T1.setPrefSize(taWidth, taLineHeight*5);
		taUserInputL3T1.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL3T1.setText("public class L3T1Response\n{\n\tpublic static void main()\n\t{\n\n\t}//end main method\n}//end L3T1Response class");
		Button btnSubmitL3T1 = new Button("Submit");
		btnSubmitL3T1.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL3T1Input = new HBox(GAP, lblInput1, taUserInputL3T1, btnSubmitL3T1);
		vbxL3.getChildren().add(hbxL3T1Input);
		//for user's output
		Label lblOutput1 = new Label("Output:     ");
		lblOutput1.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL3T1.setPrefSize(taWidth, taLineHeight);
		HBox hbxL3T1Output = new HBox(GAP, lblOutput1, taUserOutputL3T1);
		vbxL3.getChildren().add(hbxL3T1Output);
		//for user's result
		Label lblCriteria1 = new Label("Criteria");
		CheckBox cbL3T1a = new CheckBox("The variable wholeNum is created as an integer variable and initialized to an integer value");
		cbL3T1a.setDisable(true);
		CheckBox cbL3T1b = new CheckBox("The first line of output is the initial value of wholeNum");
		cbL3T1b.setDisable(true);
		CheckBox cbL3T1c = new CheckBox("The value of wholeNum has changed to a new integer value");
		cbL3T1c.setDisable(true);
		CheckBox cbL3T1d = new CheckBox("The second line of output is the new value of wholeNum");
		cbL3T1d.setDisable(true);
		HBox hbxL3T1Checklist = new HBox(GAP, lblCriteria1, cbL3T1a, cbL3T1b, cbL3T1c, cbL3T1d);
		hbxL3T1Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria1.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		vbxL3.getChildren().add(hbxL3T1Checklist);

		//adding the name of the current task
		Label lblL3T2Header = new Label("\n\n\nTask #2: Reinitializing Doubles");
		lblL3T2Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		vbxL3.getChildren().add(lblL3T2Header);

		//creating, setting, and adding the second task instructions
		Text textL3T2a = new Text("\n\nCreate a double variable called ");
		textL3T2a.setFill(Color.BLACK);
		Text textL3T2b = new Text("decimalNum");
		textL3T2b.setFill(Color.rgb(121, 76, 52));
		Text textL3T2c = new Text(" and initialize it to any decimal number. \nPrint the variable (using the name, not value) to the screen. \nChange the value of ");
		textL3T2c.setFill(Color.BLACK);
		Text textL3T2d = new Text("decimalNum");
		textL3T2d.setFill(Color.rgb(121, 76, 52));
		Text textL3T2e = new Text(" to a different decimal number. \nPrint the variable (using the name, not value) to the screen a second time. \n" + 
				"To do this, it is similar as what you did in Task 1 for this lesson, except you are using a double variable instead of an integer variable.");
		textL3T2e.setFill(Color.BLACK);
		TextFlow textflowL3T2 = new TextFlow(textL3T2a, textL3T2b, textL3T2c, textL3T2d, textL3T2e);
		textflowL3T2.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*12))
		{
			textflowL3T2.setVisible(false);
			textflowL3T2.setManaged(false);
		}//end if
		vbxL3.getChildren().add(textflowL3T2);

		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput2 = new Label("Your code: ");
		lblInput2.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL3T2 = new TextArea();
		taUserInputL3T2.setPrefSize(taWidth, taLineHeight*5);
		taUserInputL3T2.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL3T2.setText("public class L3T2Response\n{\n\tpublic static void main()\n\t{\n\n\t}//end main method\n}//end L3T2Response class");
		Button btnSubmitL3T2 = new Button("Submit");
		btnSubmitL3T2.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL3T2Input = new HBox(GAP, lblInput2, taUserInputL3T2, btnSubmitL3T2);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*12))
		{
			hbxL3T2Input.setVisible(false);
			hbxL3T2Input.setManaged(false);
		}//end if
		vbxL3.getChildren().add(hbxL3T2Input);
		//for user's output
		Label lblOutput2 = new Label("Output:     ");
		lblOutput2.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL3T2.setPrefSize(taWidth, taLineHeight);
		HBox hbxL3T2Output = new HBox(GAP, lblOutput2, taUserOutputL3T2);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*12))
		{
			hbxL3T2Output.setVisible(false);
			hbxL3T2Output.setManaged(false);
		}//end if
		vbxL3.getChildren().add(hbxL3T2Output);
		//for user's result
		Label lblCriteria2 = new Label("Criteria");
		CheckBox cbL3T2a = new CheckBox("The variable wholeNum is created as an integer variable and initialized to an integer value");
		cbL3T2a.setDisable(true);
		CheckBox cbL3T2b = new CheckBox("The first line of output is the initial value of wholeNum");
		cbL3T2b.setDisable(true);
		CheckBox cbL3T2c = new CheckBox("The value of wholeNum has changed to a new integer value");
		cbL3T2c.setDisable(true);
		CheckBox cbL3T2d = new CheckBox("The second line of output is the new value of wholeNum");
		cbL3T2d.setDisable(true);
		HBox hbxL3T2Checklist = new HBox(GAP, lblCriteria2, cbL3T2a, cbL3T2b, cbL3T2c, cbL3T2d);
		hbxL3T2Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria2.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*12))
		{
			hbxL3T2Checklist.setVisible(false);
			hbxL3T2Checklist.setManaged(false);
		}//end if
		vbxL3.getChildren().add(hbxL3T2Checklist);

		//adding the name of the current task
		Label lblL3T3Header = new Label("\n\n\nTask #3: Reinitializing Strings");
		lblL3T3Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		vbxL3.getChildren().add(lblL3T3Header);
		
		//creating, setting, and adding the third task instructions
		Text textL3T3a = new Text("\n\nCreate a string variable called ");
		textL3T3a.setFill(Color.BLACK);
		Text textL3T3b = new Text("str");
		textL3T3b.setFill(Color.rgb(121, 76, 52));
		Text textL3T3c = new Text(" and initialize it to any string value. \nPrint the variable (using the name, not value) to the screen. \nChange the value of ");
		textL3T3c.setFill(Color.BLACK);
		Text textL3T3d = new Text("str");
		textL3T3d.setFill(Color.rgb(121, 76, 52));
		Text textL3T3e = new Text(" to a different string value. \nPrint the variable (using the name, not value) to the screen a second time. \n" + 
				"To do this, it is similar as what you did in Task 1 for this lesson, except you are using a sdtring variable instead of an integer variable.");
		textL3T3e.setFill(Color.BLACK);
		TextFlow textflowL3T3 = new TextFlow(textL3T3a, textL3T3b, textL3T3c, textL3T3d, textL3T3e);
		textflowL3T3.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*13))
		{
			textflowL3T3.setVisible(false);
			textflowL3T3.setManaged(false);
		}//end if
		vbxL3.getChildren().add(textflowL3T3);

		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput3 = new Label("Your code: ");
		lblInput3.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL3T3 = new TextArea();
		taUserInputL3T3.setPrefSize(taWidth, taLineHeight*5);
		taUserInputL3T3.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL3T3.setText("public class L3T3Response\n{\n\tpublic static void main()\n\t{\n\n\t}//end main method\n}//end L3T3Response class");
		Button btnSubmitL3T3 = new Button("Submit");
		btnSubmitL3T3.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL3T3Input = new HBox(GAP, lblInput3, taUserInputL3T3, btnSubmitL3T3);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*13))
		{
			hbxL3T3Input.setVisible(false);
			hbxL3T3Input.setManaged(false);
		}//end if
		vbxL3.getChildren().add(hbxL3T3Input);
		//for user's output
		Label lblOutput3 = new Label("Output:     ");
		lblOutput3.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL3T3.setPrefSize(taWidth, taLineHeight);
		HBox hbxL3T3Output = new HBox(GAP, lblOutput3, taUserOutputL3T3);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*13))
		{
			hbxL3T3Output.setVisible(false);
			hbxL3T3Output.setManaged(false);
		}//end if
		vbxL3.getChildren().add(hbxL3T3Output);
		//for user's result
		Label lblCriteria3 = new Label("Criteria");
		CheckBox cbL3T3a = new CheckBox("The variable str is created as a String variable and initialized to a String value");
		cbL3T3a.setDisable(true);
		CheckBox cbL3T3b = new CheckBox("The first line of output is the initial value of str");
		cbL3T3b.setDisable(true);
		CheckBox cbL3T3c = new CheckBox("The value of str has changed to a new String value");
		cbL3T3c.setDisable(true);
		CheckBox cbL3T3d = new CheckBox("The second line of output is the new value of str");
		cbL3T3d.setDisable(true);
		HBox hbxL3T3Checklist = new HBox(GAP, lblCriteria3, cbL3T3a, cbL3T3b, cbL3T3c, cbL3T3d);
		hbxL3T3Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria3.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*13))
		{
			hbxL3T3Checklist.setVisible(false);
			hbxL3T3Checklist.setManaged(false);
		}//end if
		vbxL3.getChildren().add(hbxL3T3Checklist);

		//adding the name of the current task
		Label lblL3T4Header = new Label("\n\n\nTask #4: Reinitializing Characters");
		lblL3T4Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		vbxL3.getChildren().add(lblL3T4Header);
		
		//creating, setting, and adding the fourth task instructions
		Text textL3T4a = new Text("\n\nCreate a character variable called ");
		textL3T4a.setFill(Color.BLACK);
		Text textL3T4b = new Text("singleCharacter");
		textL3T4b.setFill(Color.rgb(121, 76, 52));
		Text textL3T4c = new Text(" and initialize it to any one character. \nPrint the variable (using the name, not value) to the screen. \nChange the value of ");
		textL3T4c.setFill(Color.BLACK);
		Text textL3T4d = new Text("singleCharacter");
		textL3T4d.setFill(Color.rgb(121, 76, 52));
		Text textL3T4e = new Text(" to a different one character. \nPrint the variable (using the name, not value) to the screen a second time. \n" + 
				"To do this, it is similar as what you did in Task 1 for this lesson, except you are using a character variable instead of an integer variable.");
		textL3T4e.setFill(Color.BLACK);
		TextFlow textflowL3T4 = new TextFlow(textL3T4a, textL3T4b, textL3T4c, textL3T4d, textL3T4e);
		textflowL3T4.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*14))
		{
			textflowL3T4.setVisible(false);
			textflowL3T4.setManaged(false);
		}//end if
		vbxL3.getChildren().add(textflowL3T4);

		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput4 = new Label("Your code: ");
		lblInput4.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL3T4 = new TextArea();
		taUserInputL3T4.setPrefSize(taWidth, taLineHeight*5);
		taUserInputL3T4.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL3T4.setText("public class L3T4Response\n{\n\tpublic static void main()\n\t{\n\n\t}//end main method\n}//end L3T4Response class");
		Button btnSubmitL3T4 = new Button("Submit");
		btnSubmitL3T4.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL3T4Input = new HBox(GAP, lblInput4, taUserInputL3T4, btnSubmitL3T4);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*14))
		{
			hbxL3T4Input.setVisible(false);
			hbxL3T4Input.setManaged(false);
		}//end if
		vbxL3.getChildren().add(hbxL3T4Input);
		//for user's output
		Label lblOutput4 = new Label("Output:     ");
		lblOutput2.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL3T4.setPrefSize(taWidth, taLineHeight);
		HBox hbxL3T4Output = new HBox(GAP, lblOutput4, taUserOutputL3T4);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*14))
		{
			hbxL3T4Output.setVisible(false);
			hbxL3T4Output.setManaged(false);
		}//end if
		vbxL3.getChildren().add(hbxL3T4Output);
		//for user's result
		Label lblCriteria4 = new Label("Criteria");
		CheckBox cbL3T4a = new CheckBox("The variable singleCharacter is created as a string variable and initialized to a string value");
		cbL3T4a.setDisable(true);
		CheckBox cbL3T4b = new CheckBox("The first line of output is the initial value of singleCharacter");
		cbL3T4b.setDisable(true);
		CheckBox cbL3T4c = new CheckBox("The value of singleCharacter has changed to a new string value");
		cbL3T4c.setDisable(true);
		CheckBox cbL3T4d = new CheckBox("The second line of output is the new value of singleCharacter");
		cbL3T4d.setDisable(true);
		HBox hbxL3T4Checklist = new HBox(GAP, lblCriteria4, cbL3T4a, cbL3T4b, cbL3T4c, cbL3T4d);
		hbxL3T4Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria4.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*14))
		{
			hbxL3T4Checklist.setVisible(false);
			hbxL3T4Checklist.setManaged(false);
		}//end if
		vbxL3.getChildren().add(hbxL3T4Checklist);

		//creating and adding a label to notify user that they have completed the current lesson
		Label lblL3Done = new Label("Congratulations, "+user.getfName()+"! You have finished Lesson #4: Updating Variable Values.\n" + 
				"Click one of the buttons below to either restart this lesson, choose another lesson from the menu, or continue with the next lesson.");
		lblL3Done.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*15))
		{
			lblL3Done.setVisible(false);
			lblL3Done.setManaged(false);
		}//end if
		vbxL3.getChildren().add(lblL3Done);

		//creating and adding the buttons at the bottom of the screen
		Button btnPreviousLesson = new Button("Previous Lesson");
		btnPreviousLesson.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnPreviousLesson.setOnAction(event -> lesson2(myStage));
		Button btnLessonMenu = new Button("Lesson Menu");
		btnLessonMenu.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnLessonMenu.setOnAction(event -> lessonMenu(myStage));
		Button btnExit = new Button("Exit");
		btnExit.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnExit.setOnAction(event -> 
		{
			//a try catch to try to save the user's progress, then terminate the program
			try
			{
				exitProgram();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}//end try catch
		});
		Button btnRestartL3 = new Button("Restart Lesson");
		btnRestartL3.setOnAction(event -> lesson3(myStage));
		btnRestartL3.setStyle("-fx-font-size: "+CONTROL_FONT);
		Button btnNextLesson = new Button("Next Lesson");
		btnNextLesson.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnNextLesson.setOnAction(event -> lesson4(myStage));
		if(user.getProgressLevel()<(progressIncrease*15))
		{
			btnNextLesson.setDisable(true);
		}//end if
		HBox hbxL3Controls = new HBox(GAP, btnPreviousLesson, btnLessonMenu, btnExit, btnRestartL3, btnNextLesson);
		vbxL3.getChildren().add(hbxL3Controls);

		//determining what to do when the submit button for L3T1 is clicked
		btnSubmitL3T1.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			int checkboxSelectedCounterL3T1=0;
			trialCounter[2][0]++;

			//creating the array to store user's output and adding user input to the userResponse array
			String [] outputL3T1=new String[2];
			userResponse[2][0][trialCounter[2][0]]=taUserInputL3T1.getText();

			//determining and setting the output that the user will see based on their submission
			outputL3T1=CheckJava.submitL3T1(taUserInputL3T1, TEXTAREA_FONT);
			taUserOutputL3T1.setText(outputL3T1[0]);
			taUserOutputL3T1.setStyle(outputL3T1[1]);

			//resetting all checkboxes in this task to be deselected
			cbL3T1a.setSelected(false);
			cbL3T1b.setSelected(false);
			cbL3T1c.setSelected(false);

			//if statements to check the user's inputed code and the generated output and make the correct checkboxes for this task selected
			if(!taUserOutputL3T1.getText().contains("You must initialize wholeNum as an integer.")  && !taUserOutputL3T1.getText().contains("Don't forget to begin your first line of code with \nint wholeNum = (or int wholeNum=)\n and end with ;"))
			{
				cbL3T1a.setSelected(true);
				checkboxSelectedCounterL3T1++;
			}//end if for first checkbox
			if(!taUserOutputL3T1.getText().contains("Error!"))
			{
				cbL3T1b.setSelected(true);
				checkboxSelectedCounterL3T1++;
				cbL3T1d.setSelected(true);
				checkboxSelectedCounterL3T1++;
			}//end if for second checkbox
			if(!taUserOutputL3T1.getText().contains("You must reinitialize wholeNum as an integer.") && !taUserOutputL3T1.getText().contains("You must change the value of wholeNum to a different integer on the second line of your code."))
			{
				cbL3T1c.setSelected(true);
				checkboxSelectedCounterL3T1++;
			}//end if for third checkbox

			//when the user has successfully completed the current task and is ready for the next task
			if(checkboxSelectedCounterL3T1==4)
			{
				//setting the next task as visible
				textflowL3T2.setVisible(true);
				textflowL3T2.setManaged(true);
				hbxL3T2Input.setVisible(true);
				hbxL3T2Input.setManaged(true);
				hbxL3T2Output.setVisible(true);
				hbxL3T2Output.setManaged(true);
				hbxL3T2Checklist.setVisible(true);
				hbxL3T2Checklist.setManaged(true);
				if(user.getProgressLevel()<(progressIncrease*12))
				{
					pbsUserProgress.setProgress((progressIncrease*12));
					user.setProgressLevel(pbsUserProgress.getProgress());
				}//end inside if
			}//end outside if
		});//end setOnAction for btnSubmitL3T1

		//determining what to do when the submit button for L3T2 is clicked
		btnSubmitL3T2.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			int checkboxSelectedCounterL3T2=0;
			trialCounter[2][1]++;

			//creating the array to store user's output and adding user input to the userResponse array
			String [] outputL3T2=new String[2];
			userResponse[2][1][trialCounter[2][1]]=taUserInputL3T2.getText();

			//determining and setting the output that the user will see based on their submission
			outputL3T2=CheckJava.submitL3T2(taUserInputL3T2, TEXTAREA_FONT);
			taUserOutputL3T2.setText(outputL3T2[0]);
			taUserOutputL3T2.setStyle(outputL3T2[1]);

			//resetting all checkboxes in this task to be deselected
			cbL3T2a.setSelected(false);
			cbL3T2b.setSelected(false);
			cbL3T2c.setSelected(false);

			//if statements to check the user's inputed code and the generated output and make the correct checkboxes for this task selected
			if(!taUserOutputL3T2.getText().contains("You must initialize decimalNum as a double.")  && !taUserOutputL3T2.getText().contains("Don't forget to begin your first line of code with \ndouble decimalNum = (or double decimalNum=)\n and end with ;"))
			{
				cbL3T2a.setSelected(true);
				checkboxSelectedCounterL3T2++;
			}//end if for first checkbox

			if(!taUserOutputL3T2.getText().contains("Error!"))
			{
				cbL3T2b.setSelected(true);
				checkboxSelectedCounterL3T2++;
				cbL3T2d.setSelected(true);
				checkboxSelectedCounterL3T2++;
			}//end if for second checkbox

			if(!taUserOutputL3T2.getText().contains("You must reinitialize decimalNum as a double.") && !taUserOutputL3T2.getText().contains("You must change the value of decimalNum to a different double value on the second line of your code."))
			{
				cbL3T2c.setSelected(true);
				checkboxSelectedCounterL3T2++;
			}//end if for third checkbox

			//when the user has successfully completed the current task and is ready for the next task
			if(checkboxSelectedCounterL3T2==4)
			{
				textflowL3T3.setVisible(true);
				textflowL3T3.setManaged(true);
				hbxL3T3Input.setVisible(true);
				hbxL3T3Input.setManaged(true);
				hbxL3T3Output.setVisible(true);
				hbxL3T3Output.setManaged(true);
				hbxL3T3Checklist.setVisible(true);
				hbxL3T3Checklist.setManaged(true);
				if(user.getProgressLevel()<(progressIncrease*13))
				{
					pbsUserProgress.setProgress((progressIncrease*13));
					user.setProgressLevel(pbsUserProgress.getProgress());
				}//end inside if
			}//end outside if
		});//end setOnAction for btnSubmitL3T2

		//determining what to do when the submit button for L3T3 is clicked
		btnSubmitL3T3.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			int checkboxSelectedCounterL3T3=0;
			trialCounter[2][2]++;

			//creating the array to store user's output and adding user input to the userResponse array
			String [] outputL3T3=new String[2];
			userResponse[2][2][trialCounter[2][2]]=taUserInputL3T3.getText();

			//determining and setting the output that the user will see based on their submission
			outputL3T3=CheckJava.submitL3T3(taUserInputL3T3, TEXTAREA_FONT);
			taUserOutputL3T3.setText(outputL3T3[0]);
			taUserOutputL3T3.setStyle(outputL3T3[1]);

			//resetting all checkboxes in this task to be deselected
			cbL3T3a.setSelected(false);
			cbL3T3b.setSelected(false);
			cbL3T3c.setSelected(false);

			//if statements to check the user's inputed code and the generated output and make the correct checkboxes for this task selected
			if(!taUserOutputL3T3.getText().contains("Don't forget to begin your first line of code with \nstr = (or str=), surround the value with \"\", and end with ;"))
			{
				cbL3T3a.setSelected(true);
				checkboxSelectedCounterL3T3++;
			}//end if for first checkbox

			if(!taUserOutputL3T3.getText().contains("Error!"))
			{
				cbL3T3b.setSelected(true);
				checkboxSelectedCounterL3T3++;
				cbL3T3d.setSelected(true);
				checkboxSelectedCounterL3T3++;
			}//end if for second checkbox

			if(!taUserOutputL3T3.getText().contains("Don't forget to begin your third line of code with \nstr = (or str=), surround the value with \"\", and end with ;") && !taUserOutputL3T3.getText().contains("You must change the value of str to a different String value on the second line of your code."))
			{
				cbL3T3c.setSelected(true);
				checkboxSelectedCounterL3T3++;
			}//end if for third checkbox

			//when the user has successfully completed the current task and is ready for the next task
			if(checkboxSelectedCounterL3T3==4)
			{
				textflowL3T4.setVisible(true);
				textflowL3T4.setManaged(true);
				hbxL3T4Input.setVisible(true);
				hbxL3T4Input.setManaged(true);
				hbxL3T4Output.setVisible(true);
				hbxL3T4Output.setManaged(true);
				hbxL3T4Checklist.setVisible(true);
				hbxL3T4Checklist.setManaged(true);
				if(user.getProgressLevel()<(progressIncrease*14))
				{
					pbsUserProgress.setProgress((progressIncrease*14));
					user.setProgressLevel(pbsUserProgress.getProgress());
				}//end inside if
			}//end outside if
		});//end setOnAction for btnSubmitL3T3

		//determining what to do when the submit button for L3T4 is clicked
		btnSubmitL3T4.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			int checkboxSelectedCounterL3T4=0;
			trialCounter[2][3]++;

			//creating the array to store user's output and adding user input to the userResponse array
			String [] outputL3T4=new String[2];
			userResponse[2][3][trialCounter[2][3]]=taUserInputL3T4.getText();

			//determining and setting the output that the user will see based on their submission
			outputL3T4=CheckJava.submitL3T4(taUserInputL3T4, TEXTAREA_FONT);
			taUserOutputL3T4.setText(outputL3T4[0]);
			taUserOutputL3T4.setStyle(outputL3T4[1]);

			//resetting all checkboxes in this task to be deselected
			cbL3T4a.setSelected(false);
			cbL3T4b.setSelected(false);
			cbL3T4c.setSelected(false);

			//if statements to check the user's inputed code and the generated output and make the correct checkboxes for this task selected
			if(!taUserOutputL3T4.getText().contains("You must initialize singleCharacter as a char.")  && !taUserOutputL3T4.getText().contains("Don't forget to begin your first line of code with \nchar singleCharacter = (or char singleCharacter=), surround the value with '', and end with ;"))
			{
				cbL3T4a.setSelected(true);
				checkboxSelectedCounterL3T4++;
			}//end if for first checkbox

			if(!taUserOutputL3T4.getText().contains("Error!"))
			{
				cbL3T4b.setSelected(true);
				checkboxSelectedCounterL3T4++;
				cbL3T4d.setSelected(true);
				checkboxSelectedCounterL3T4++;
			}//end if for second checkbox

			if(!taUserOutputL3T4.getText().contains("You must reinitialize singleCharacter as a char.") && !taUserOutputL3T4.getText().contains("You must change the value of singleCharacter to a different char value on the second line of your code."))
			{
				cbL3T4c.setSelected(true);
				checkboxSelectedCounterL3T4++;
			}//end if for third checkbox

			//when the user has successfully completed the current task and is ready for the next task
			if(checkboxSelectedCounterL3T4==4)
			{
				lblL3Done.setVisible(true);
				lblL3Done.setManaged(true);
				if(user.getProgressLevel()<(progressIncrease*15))
				{
					pbsUserProgress.setProgress((progressIncrease*15));
					user.setProgressLevel(pbsUserProgress.getProgress());
				}//end inside if
			}//end outside if
		});//end setOnAction for btnSubmitL3T4

		//creating and setting the scroll pane for this lesson
		ScrollPane spL3 = new ScrollPane();
		spL3.setContent(vbxL3);

		//creating the scene
		Scene sceneL3 = new Scene(spL3);

		lastScene="lesson3"; //setting the previous scene to this scene

		//setting/showing the stage
		myStage.getIcons().add(new Image("FromOneToAnotherLogo.png"));
		myStage.setTitle("From C++ to Java - ... - Lesson #3");
		myStage.setScene(sceneL3);
		myStage.centerOnScreen();
		myStage.show();
	}//end lesson3 method


	/* Method Author: Brooke Cronin
	 * Method Name: lesson4
	 * Description: Creates the scene for the fourth lesson. Allows the user to view and attempt all of the tasks and get feedback after completing each task.
	 * Parameters: Stage myStage
	 * Returns: N/A
	 */

	public void lesson4(Stage myStage)
	{
		myStage.setOnHiding( event -> {try {
			exitProgram();
		} catch (IOException e) {
			
			e.printStackTrace();
		}} );
		//resetting the text in all of the user output text areas and disabling them so that the user can't change what appears in them
		TextArea taUserOutputL4T1 = new TextArea();
		taUserOutputL4T1.setText("Not submitted yet");
		taUserOutputL4T1.setEditable(false);
		taUserOutputL4T1.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
		TextArea taUserOutputL4T2 = new TextArea();
		taUserOutputL4T2.setText("Not submitted yet");
		taUserOutputL4T2.setEditable(false);
		taUserOutputL4T2.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
		TextArea taUserOutputL4T3 = new TextArea();
		taUserOutputL4T3.setText("Not submitted yet");
		taUserOutputL4T3.setEditable(false);
		taUserOutputL4T3.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
		TextArea taUserOutputL4T4 = new TextArea();
		taUserOutputL4T4.setText("Not submitted yet");
		taUserOutputL4T4.setEditable(false);
		taUserOutputL4T4.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");

		//creating the vertical box for lesson 4 screen
		VBox vbxL4 = new VBox(GAP);
		vbxL4.setPadding(new Insets(GAP, GAP, GAP, GAP));

		//adding the user's progress bar to the vertical box
		pbsUserProgress.setMaxWidth(Double.MAX_VALUE);
		vbxL4.getChildren().add(pbsUserProgress);

		//creating, setting, and adding a header Label
		AnchorPane apLesson4TitleHeader = new AnchorPane();
		Label lblLesson4TitleHeader = new Label("Lesson #4: Exploring Basic Math Operations");
		lblLesson4TitleHeader.setStyle("-fx-font-size: "+LESSON_HEADER_FONT);
		lblLesson4TitleHeader.setMaxWidth(Double.MAX_VALUE);
		AnchorPane.setLeftAnchor(lblLesson4TitleHeader, 0.0);
		AnchorPane.setRightAnchor(lblLesson4TitleHeader, 0.0);
		lblLesson4TitleHeader.setAlignment(Pos.CENTER);
		apLesson4TitleHeader.getChildren().add(lblLesson4TitleHeader);
		vbxL4.getChildren().add(apLesson4TitleHeader);

		//creating, setting, and adding the first task instructions
		Text textL4T1a = new Text("\n\nIn this task, you will be printing the the outcome of a basic math problem using BEDMAS (excluding exponents). \nBelow, is an example.");
		textL4T1a.setFill(Color.BLACK);		
		Text textL4T1b = new Text("\nSystem.out.println(5*(9-4)+7);");
		textL4T1b.setFill(Color.BLUE);
		Text textL4T1c = new Text("\nWith an output of:");
		textL4T1c.setFill(Color.BLACK);
		Text textL4T1d = new Text("\n32");
		textL4T1d.setStyle("-fx-text-fill: black; -fx-font: "+TASK_INSTR_FONT+"\"Comic Sans MS\"");
		Text textL4T1e = new Text("\nNotice how there aren't any \"\" in the line of code? This is because only the result needs to be printed and not the problem. \nThis also works with double values (not just integers). \nYou can try this task multiple times, but before continuing, you must use +, -, *, /, (), an integer value, and a double value at least once.");
		textL4T1e.setFill(Color.BLACK);
		TextFlow textflowL4T1 = new TextFlow(textL4T1a, textL4T1b, textL4T1c, textL4T1d, textL4T1e);
		textflowL4T1.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		vbxL4.getChildren().addAll(textflowL4T1);

		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput1 = new Label("Your code: ");
		lblInput1.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL4T1 = new TextArea();
		taUserInputL4T1.setPrefSize(taWidth, taLineHeight);
		taUserInputL4T1.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL4T1.setText("public class L4T1Response\n{\n\tpublic static void main()\n\t{\n\n\t}//end main method\n}//end L4T1Response class");
		Button btnSubmitL4T1 = new Button("Submit");
		btnSubmitL4T1.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL4T1Input = new HBox(GAP, lblInput1, taUserInputL4T1, btnSubmitL4T1);
		vbxL4.getChildren().add(hbxL4T1Input);
		//for user's output
		Label lblOutput1 = new Label("Output:     ");
		lblOutput1.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL4T1.setPrefSize(taWidth, taLineHeight);
		HBox hbxL4T1Output = new HBox(GAP, lblOutput1, taUserOutputL4T1);
		vbxL4.getChildren().add(hbxL4T1Output);
		//for user's result
		Label lblCriteria1 = new Label("Criteria");
		lblCriteria1.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		CheckBox cbL4T1a = new CheckBox("+ was used in at least 1 attempt");
		cbL4T1a.setDisable(true);
		cbL4T1a.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		CheckBox cbL4T1b = new CheckBox("- was used in at least 1 attempt");
		cbL4T1b.setDisable(true);
		cbL4T1b.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		CheckBox cbL4T1c = new CheckBox("* was used in at least 1 attempt");
		cbL4T1c.setDisable(true);
		cbL4T1c.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		CheckBox cbL4T1d = new CheckBox("/ was used in at least 1 attempt");
		cbL4T1d.setDisable(true);
		cbL4T1d.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		CheckBox cbL4T1e = new CheckBox("() was used in at least 1 attempt");
		cbL4T1e.setDisable(true);
		cbL4T1e.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		CheckBox cbL4T1f = new CheckBox("At least 1 integer value was used");
		cbL4T1f.setDisable(true);
		cbL4T1f.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		CheckBox cbL4T1g = new CheckBox("At least 1 double value was used");
		cbL4T1g.setDisable(true);
		cbL4T1g.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		CheckBox cbL4T1h = new CheckBox("Output is the result of the intended math problem");
		cbL4T1h.setDisable(true);
		cbL4T1h.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		HBox hbxL4T1Checklist = new HBox(GAP, lblCriteria1, cbL4T1a, cbL4T1b, cbL4T1c, cbL4T1d, cbL4T1e, cbL4T1f, cbL4T1g, cbL4T1h);
		vbxL4.getChildren().add(hbxL4T1Checklist);

		//creating, setting, and adding the second task instructions
		Text textL4T2a = new Text("\n\nNow that you know how to print to the screen, and create/initialize different types of variables, what about modifying the value of a variable? \nIn order to add, subtract, multiply, or divide doubles or integers together, you must first initialize two variables to store the values of the numbers to be added/subtracted/multiplied/divided, and one that stores the result.");
		textL4T2a.setFill(Color.BLACK);	
		Text textL4T2b = new Text("\nIn this task, you will begin with experimenting with two integer variables and basic math operations.");
		textL4T2b.setFill(Color.BLACK);	
		Text textL4T2c = new Text("\nCreate two variables called ");
		textL4T2c.setFill(Color.BLACK);
		Text textL4T2d = new Text("iNum1");
		textL4T2d.setFill(Color.rgb(121, 76, 52));
		Text textL4T2e = new Text(" and ");
		textL4T2e.setFill(Color.BLACK);
		Text textL4T2f = new Text("iNum2");
		textL4T2f.setFill(Color.rgb(121, 76, 52));
		Text textL4T2g = new Text(", then initialize both to two different whole numbers. \nCreate a third variable called ");
		textL4T2g.setFill(Color.BLACK);
		Text textL4T2h = new Text("iResult");
		textL4T2h.setFill(Color.rgb(121, 76, 52));
		Text textL4T2i = new Text(" and do not initialize it. \nThen, experiment with the two variables by changing the ");
		textL4T2i.setFill(Color.BLACK);
		Text textL4T2j = new Text("iResult");
		textL4T2j.setFill(Color.rgb(121, 76, 52));
		Text textL4T2k = new Text(" variable value based on only the values of ");
		textL4T2k.setFill(Color.BLACK);
		Text textL4T2l = new Text("iNum1");
		textL4T2l.setFill(Color.rgb(121, 76, 52));
		Text textL4T2m = new Text(" and ");
		textL4T2m.setFill(Color.BLACK);
		Text textL4T2n = new Text("iNum2");
		textL4T2n.setFill(Color.rgb(121, 76, 52));
		Text textL4T2o = new Text(".");
		textL4T2o.setFill(Color.BLACK);
		TextFlow textflowL4T2 = new TextFlow(textL4T2a, textL4T2b, textL4T2c, textL4T2d, textL4T2e, textL4T2f, textL4T2g, textL4T2h, textL4T2i, textL4T2j, textL4T2k, textL4T2l, textL4T2m, textL4T2n, textL4T2o);
		textflowL4T2.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		vbxL4.getChildren().addAll(textflowL4T2);
		if(user.getProgressLevel()<(progressIncrease*16))
		{
			textflowL4T2.setVisible(false);
			textflowL4T2.setManaged(false);
		}//end if

		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput2 = new Label("Your code: ");
		lblInput2.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL4T2 = new TextArea();
		taUserInputL4T2.setPrefSize(taWidth, taLineHeight*6);
		taUserInputL4T2.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL4T2.setText("public class L4T2Response\n{\n\tpublic static void main()\n\t{\n\n\t}//end main method\n}//end L4T2Response class");
		Button btnSubmitL4T2 = new Button("Submit");
		btnSubmitL4T2.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL4T2Input = new HBox(GAP, lblInput2, taUserInputL4T2, btnSubmitL4T2);
		vbxL4.getChildren().add(hbxL4T2Input);
		if(user.getProgressLevel()<(progressIncrease*16))
		{
			hbxL4T2Input.setVisible(false);
			hbxL4T2Input.setManaged(false);
		}//end if
		//for user's output
		Label lblOutput2 = new Label("Output:     ");
		lblOutput2.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL4T2.setPrefSize(taWidth, taLineHeight);
		HBox hbxL4T2Output = new HBox(GAP, lblOutput2, taUserOutputL4T2);
		vbxL4.getChildren().add(hbxL4T2Output);
		if(user.getProgressLevel()<(progressIncrease*16))
		{
			hbxL4T2Output.setVisible(false);
			hbxL4T2Output.setManaged(false);
		}//end if
		//for user's result
		Label lblCriteria2 = new Label("Criteria");
		lblCriteria2.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		CheckBox cbL4T2a = new CheckBox("iNum1 and iNum2 are integer variables and initialized as two different whole numbers");
		cbL4T2a.setDisable(true);
		cbL4T2a.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		CheckBox cbL4T2b = new CheckBox("iResult is created as an integer variable and not initialized");
		cbL4T2b.setDisable(true);
		cbL4T2b.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		CheckBox cbL4T2c = new CheckBox("The value of iResult is printed to the screen");
		cbL4T2c.setDisable(true);
		cbL4T2c.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		CheckBox cbL4T2d = new CheckBox("Output is the value of iResult");
		cbL4T2d.setDisable(true);
		cbL4T2d.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		CheckBox cbL4T2e = new CheckBox("Tried using addition to calculate iResult");
		cbL4T2e.setDisable(true);
		cbL4T2e.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		CheckBox cbL4T2f = new CheckBox("Tried using subtraction to calculate iResult");
		cbL4T2f.setDisable(true);
		cbL4T2f.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		CheckBox cbL4T2g = new CheckBox("Tried using multiplication to calculate iResult");
		cbL4T2g.setDisable(true);
		cbL4T2g.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		CheckBox cbL4T2h = new CheckBox("Tried using division to calculate iResult");
		cbL4T2h.setDisable(true);
		cbL4T2h.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		HBox hbxL4T2Checklist = new HBox(GAP, lblCriteria2, cbL4T2a, cbL4T2b, cbL4T2c, cbL4T2d, cbL4T2e, cbL4T2f, cbL4T2g, cbL4T2h);
		vbxL4.getChildren().add(hbxL4T2Checklist);
		if(user.getProgressLevel()<(progressIncrease*16))
		{
			hbxL4T2Checklist.setVisible(false);
			hbxL4T2Checklist.setManaged(false);
		}//end if

		//creating, setting, and adding the third task instructions
		Text textL4T3a = new Text("\n\nDid you know that you could also do something similar to task #2 in this lesson but with double values instead of integer values? Well in this task, you will do the same thing as before, except you will use ");
		textL4T3a.setFill(Color.BLACK);	
		Text textL4T3b = new Text("dNum1");
		textL4T3b.setFill(Color.rgb(121, 76, 52));
		Text textL4T3c = new Text(" instead of ");
		textL4T3c.setFill(Color.BLACK);
		Text textL4T3d = new Text("iNum1");
		textL4T3d.setFill(Color.rgb(121, 76, 52));
		Text textL4T3e = new Text(", ");
		textL4T3e.setFill(Color.BLACK);	
		Text textL4T3f = new Text("dNum2");
		textL4T3f.setFill(Color.rgb(121, 76, 52));
		Text textL4T3g = new Text(" instead of ");
		textL4T3g.setFill(Color.BLACK);
		Text textL4T3h = new Text("iNum2");
		textL4T3h.setFill(Color.rgb(121, 76, 52));
		Text textL4T3i = new Text(", and ");
		textL4T3i.setFill(Color.BLACK);	
		Text textL4T3j = new Text("dResult");
		textL4T3j.setFill(Color.rgb(121, 76, 52));
		Text textL4T3k = new Text(" instead of ");
		textL4T3k.setFill(Color.BLACK);
		Text textL4T3l = new Text("iResult");
		textL4T3l.setFill(Color.rgb(121, 76, 52));
		Text textL4T3m = new Text(".");
		textL4T3m.setFill(Color.BLACK);
		TextFlow textflowL4T3 = new TextFlow(textL4T3a, textL4T3b, textL4T3c, textL4T3d, textL4T3e, textL4T3f, textL4T3g, textL4T3h, textL4T3i, textL4T3j, textL4T3k, textL4T3l, textL4T3m);
		textflowL4T3.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		vbxL4.getChildren().addAll(textflowL4T3);
		if(user.getProgressLevel()<(progressIncrease*17))
		{
			textflowL4T3.setVisible(false);
			textflowL4T3.setManaged(false);
		}//end if

		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput3 = new Label("Your code: ");
		lblInput3.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL4T3 = new TextArea();
		taUserInputL4T3.setPrefSize(taWidth, taLineHeight*6);
		taUserInputL4T3.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL4T3.setText("public class L4T3Response\n{\n\tpublic static void main()\n\t{\n\n\t}//end main method\n}//end L4T3Response class");
		Button btnSubmitL4T3 = new Button("Submit");
		btnSubmitL4T3.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL4T3Input = new HBox(GAP, lblInput3, taUserInputL4T3, btnSubmitL4T3);
		vbxL4.getChildren().add(hbxL4T3Input);
		if(user.getProgressLevel()<(progressIncrease*17))
		{
			hbxL4T3Input.setVisible(false);
			hbxL4T3Input.setManaged(false);
		}//end if
		//for user's output
		Label lblOutput3 = new Label("Output:     ");
		lblOutput3.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL4T3.setPrefSize(taWidth, taLineHeight);
		HBox hbxL4T3Output = new HBox(GAP, lblOutput3, taUserOutputL4T3);
		vbxL4.getChildren().add(hbxL4T3Output);
		if(user.getProgressLevel()<(progressIncrease*17))
		{
			hbxL4T3Output.setVisible(false);
			hbxL4T3Output.setManaged(false);
		}//end if
		//for user's result
		Label lblCriteria3 = new Label("Criteria");
		lblCriteria3.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		CheckBox cbL4T3a = new CheckBox("dNum1 and dNum2 are double variables and initialized as two different numbers");
		cbL4T3a.setDisable(true);
		cbL4T3a.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		CheckBox cbL4T3b = new CheckBox("dResult is created as a double variable and not initialized");
		cbL4T3b.setDisable(true);
		cbL4T3b.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		CheckBox cbL4T3c = new CheckBox("The value of dResult is printed to the screen");
		cbL4T3c.setDisable(true);
		cbL4T3c.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		CheckBox cbL4T3d = new CheckBox("Output is the value of dResult");
		cbL4T3d.setDisable(true);
		cbL4T3d.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		CheckBox cbL4T3e = new CheckBox("Tried using addition to calculate dResult");
		cbL4T3e.setDisable(true);
		cbL4T3e.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		CheckBox cbL4T3f = new CheckBox("Tried using subtraction to calculate dResult");
		cbL4T3f.setDisable(true);
		cbL4T3f.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		CheckBox cbL4T3g = new CheckBox("Tried using multiplication to calculate dResult");
		cbL4T3g.setDisable(true);
		cbL4T3g.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		CheckBox cbL4T3h = new CheckBox("Tried using division to calculate dResult");
		cbL4T3h.setDisable(true);
		cbL4T3h.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		HBox hbxL4T3Checklist = new HBox(GAP, lblCriteria3, cbL4T3a, cbL4T3b, cbL4T3c, cbL4T3d, cbL4T3e, cbL4T3f, cbL4T3g, cbL4T3h);
		vbxL4.getChildren().add(hbxL4T3Checklist);
		if(user.getProgressLevel()<(progressIncrease*17))
		{
			hbxL4T3Checklist.setVisible(false);
			hbxL4T3Checklist.setManaged(false);
		}//end if

		//creating, setting, and adding the fourth task instructions
		Text textL4T4a = new Text("\n\nIn Python, you might have used the modulo (%) symbol for showing the remainder of a division question. Java uses modulo in the same way as Python. Just in case you have for gotten, below is some code in Java to print to screen the remainder of 5/2.");
		textL4T4a.setFill(Color.BLACK);	
		Text textL4T4b = new Text("\nSystem.out.println(5%2);");
		textL4T4b.setFill(Color.BLUE);
		Text textL4T4c = new Text("\nWith an output of:");
		textL4T4c.setFill(Color.BLACK);
		Text textL4T4d = new Text("\n1");
		textL4T4d.setStyle("-fx-font: "+TASK_INSTR_FONT+"\"Comic Sans MS\"");
		Text textL4T4e = new Text("\nIn this task, You will create three integer variables called ");
		textL4T4e.setFill(Color.BLACK);	
		Text textL4T4f = new Text("dividend");
		textL4T4f.setFill(Color.rgb(121, 76, 52));
		Text textL4T4g = new Text(", ");
		textL4T4g.setFill(Color.BLACK);
		Text textL4T4h = new Text("divisor");
		textL4T4h.setFill(Color.rgb(121, 76, 52));
		Text textL4T4i = new Text(", and ");
		textL4T4i.setFill(Color.BLACK);	
		Text textL4T4j = new Text("quotient");
		textL4T4j.setFill(Color.rgb(121, 76, 52));
		Text textL4T4k = new Text(". \nInitialize ");
		textL4T4k.setFill(Color.BLACK);
		Text textL4T4l = new Text("dividend");
		textL4T4l.setFill(Color.rgb(121, 76, 52));
		Text textL4T4m = new Text(" and ");
		textL4T4m.setFill(Color.BLACK);
		Text textL4T4n = new Text("divisor");
		textL4T4n.setFill(Color.rgb(121, 76, 52));
		Text textL4T4o = new Text(" to any two whole numbers. Initialize ");
		textL4T4o.setFill(Color.BLACK);
		Text textL4T4p = new Text("quotient");
		textL4T4p.setFill(Color.rgb(121, 76, 52));
		Text textL4T4q = new Text(" to ");
		textL4T4q.setFill(Color.BLACK);
		Text textL4T4r = new Text("dividend");
		textL4T4r.setFill(Color.rgb(121, 76, 52));
		Text textL4T4s = new Text("/");
		textL4T4s.setFill(Color.BLACK);	
		Text textL4T4t = new Text("divisor");
		textL4T4t.setFill(Color.rgb(121, 76, 52));
		Text textL4T4u = new Text(". \nAfter creating and initializing all three of these variables, print to screen \nquotient R remainder (using the modulo to determine the remainder).");
		textL4T4u.setFill(Color.BLACK);	
		TextFlow textflowL4T4 = new TextFlow(textL4T4a, textL4T4b, textL4T4c, textL4T4d, textL4T4e, textL4T4f, textL4T4g, textL4T4h, textL4T4i, textL4T4j, textL4T4k, textL4T4l, textL4T4m, textL4T4n, textL4T4o, textL4T4p, textL4T4q, textL4T4r, textL4T4s, textL4T4t, textL4T4u);
		textflowL4T4.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		vbxL4.getChildren().addAll(textflowL4T4);
		if(user.getProgressLevel()<(progressIncrease*18))
		{
			textflowL4T4.setVisible(false);
			textflowL4T4.setManaged(false);
		}//end if

		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput4 = new Label("Your code: ");
		lblInput4.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL4T4 = new TextArea();
		taUserInputL4T4.setPrefSize(taWidth, taLineHeight*5);
		taUserInputL4T4.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL4T4.setText("public class L4T4Response\n{\n\tpublic static void main()\n\t{\n\n\t}//end main method\n}//end L4T4Response class");
		Button btnSubmitL4T4 = new Button("Submit");
		btnSubmitL4T4.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL4T4Input = new HBox(GAP, lblInput4, taUserInputL4T4, btnSubmitL4T4);
		vbxL4.getChildren().add(hbxL4T4Input);
		if(user.getProgressLevel()<(progressIncrease*18))
		{
			hbxL4T4Input.setVisible(false);
			hbxL4T4Input.setManaged(false);
		}//end if
		//for user's output
		Label lblOutput4 = new Label("Output:     ");
		lblOutput4.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL4T4.setPrefSize(taWidth, taLineHeight);
		HBox hbxL4T4Output = new HBox(GAP, lblOutput4, taUserOutputL4T4);
		vbxL4.getChildren().add(hbxL4T4Output);
		if(user.getProgressLevel()<(progressIncrease*18))
		{
			hbxL4T4Output.setVisible(false);
			hbxL4T4Output.setManaged(false);
		}//end if
		//for user's result
		Label lblCriteria4 = new Label("Criteria");
		lblCriteria4.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		CheckBox cbL4T4a = new CheckBox("dividend and divisor are integer variables and initialized as whole numbers");
		cbL4T4a.setDisable(true);
		cbL4T4a.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		CheckBox cbL4T4b = new CheckBox("quotient is created as an integer variable and initialized properly");
		cbL4T4b.setDisable(true);
		cbL4T4b.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		CheckBox cbL4T4c = new CheckBox("Remainder is calculated using modulo");
		cbL4T4c.setDisable(true);
		cbL4T4c.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		CheckBox cbL4T4d = new CheckBox("Output is in format as described in the instructions");
		cbL4T4d.setDisable(true);
		cbL4T4d.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		HBox hbxL4T4Checklist = new HBox(GAP, lblCriteria4, cbL4T4a, cbL4T4b, cbL4T4c, cbL4T4d);
		vbxL4.getChildren().add(hbxL4T4Checklist);
		if(user.getProgressLevel()<(progressIncrease*18))
		{
			hbxL4T4Checklist.setVisible(false);
			hbxL4T4Checklist.setManaged(false);
		}//end if

		//creating and adding a label to notify user that they have completed the current lesson
		Label lblL4Done = new Label("Congratulations, "+user.getfName()+"! You have finished Lesson #5: Exploring Basic Math Operations.\n" + 
				"Click one of the buttons below to either restart this lesson, choose another lesson from the menu, or continue with the next lesson.");
		lblL4Done.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		if(user.getProgressLevel()<(progressIncrease*19))
		{
			lblL4Done.setVisible(false);
			lblL4Done.setManaged(false);
		}
		vbxL4.getChildren().add(lblL4Done);

		//creating and adding the buttons at the bottom of the screen
		Button btnPreviousLesson = new Button("Previous Lesson");
		btnPreviousLesson.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnPreviousLesson.setOnAction(event -> lesson3(myStage));
		btnPreviousLesson.setDisable(true);
		Button btnLessonMenu = new Button("Lesson Menu");
		btnLessonMenu.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnLessonMenu.setOnAction(event -> lessonMenu(myStage));
		Button btnExit = new Button("Exit");
		btnExit.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnExit.setOnAction(event -> 
		{
			//a try catch to try to save the user's progress, then terminate the program
			try
			{
				exitProgram();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}//end try catch
		});
		Button btnRestartL4 = new Button("Restart Lesson");
		btnRestartL4.setOnAction(event -> lesson4(myStage));
		btnRestartL4.setStyle("-fx-font-size: "+CONTROL_FONT);
		Button btnNextLesson = new Button("Next Lesson");
		btnNextLesson.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnNextLesson.setOnAction(event -> lesson5(myStage));
		if(user.getProgressLevel()<(progressIncrease*19))
		{
			btnNextLesson.setDisable(true);
		}//end if
		HBox hbxL4Controls = new HBox(GAP, btnPreviousLesson, btnLessonMenu, btnExit, btnRestartL4, btnNextLesson);
		vbxL4.getChildren().add(hbxL4Controls);

		//determining what to do when the submit button for L4T1
		btnSubmitL4T1.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			String [] outputL4T1=new String[2];
			trialCounter[3][0]++;
			userResponse[3][0][trialCounter[3][0]]=taUserInputL4T1.getText();

			//resetting all checkboxes in this task to be deselected
			cbL4T1h.setSelected(false);

			try
			{
				outputL4T1=CheckJava.submitL4T1(taUserInputL4T1, TEXTAREA_FONT);
				taUserOutputL4T1.setText(outputL4T1[0]);
				taUserOutputL4T1.setStyle(outputL4T1[1]);
			}
			catch (ScriptException e)
			{
				e.printStackTrace();
			}//end try catch

			//if statements to check the user's inputed code and the generated output and make the correct checkboxes for this task selected
			if(taUserInputL4T1.getText().contains("+") && !taUserOutputL4T1.getText().startsWith("Error!"))
			{
				cbL4T1a.setSelected(true);
			}//end if for first checkbox
			if(taUserInputL4T1.getText().contains("-") && !taUserOutputL4T1.getText().startsWith("Error!"))
			{
				cbL4T1b.setSelected(true);
			}//end if for second checkbox
			if(taUserInputL4T1.getText().contains("*") && !taUserOutputL4T1.getText().startsWith("Error!"))
			{
				cbL4T1c.setSelected(true);
			}//end if for third checkbox
			if(taUserInputL4T1.getText().contains("/") && !taUserOutputL4T1.getText().startsWith("Error!"))
			{
				cbL4T1d.setSelected(true);
			}//end if for fourth checkbox
			if(!taUserOutputL4T1.getText().startsWith("Error!"))
			{
				cbL4T1f.setSelected(true);
				cbL4T1h.setSelected(true);

				int bracketCounter=0;
				int decimalCounter=0;
				for(int i=19; i<taUserInputL4T1.getText().length()-2; i++)
				{
					if(taUserInputL4T1.getText().charAt(i)==('('))
					{
						bracketCounter++;
					}//end if
					else if(taUserInputL4T1.getText().charAt(i)==(')'))
					{
						bracketCounter++;
					}//end else if
					else if(taUserInputL4T1.getText().charAt(i)==('.'))
					{
						decimalCounter++;
					}//end else if
				}//end for loop
				if(bracketCounter>1)
				{
					cbL4T1e.setSelected(true);
				}//end inside if
				if(decimalCounter>0)
				{
					cbL4T1g.setSelected(true);
				}//end inside if
			}//end if for fifth, sixth, seventh, and eighth checkbox

			//when the user has successfully completed the current task and is ready for the next task
			if(cbL4T1a.isSelected() && cbL4T1b.isSelected() && cbL4T1c.isSelected() && cbL4T1d.isSelected() && cbL4T1e.isSelected() && cbL4T1f.isSelected() && cbL4T1g.isSelected() && cbL4T1h.isSelected())
			{
				textflowL4T2.setVisible(true);
				textflowL4T2.setManaged(true);
				hbxL4T2Input.setVisible(true);
				hbxL4T2Input.setManaged(true);
				hbxL4T2Output.setVisible(true);
				hbxL4T2Output.setManaged(true);
				hbxL4T2Checklist.setVisible(true);
				hbxL4T2Checklist.setManaged(true);
				if(user.getProgressLevel()<(progressIncrease*16))
				{
					pbsUserProgress.setProgress((progressIncrease*16));
					user.setProgressLevel(pbsUserProgress.getProgress());
				}//end inside if
			}//end outside if
		});

		//determining what to do when the submit button for L4T2
		btnSubmitL4T2.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			String [] outputL4T2=new String[2];
			trialCounter[3][1]++;
			userResponse[3][1][trialCounter[3][1]]=taUserInputL4T2.getText();

			//resetting all checkboxes in this task to be deselected
			cbL4T2a.setSelected(false);
			cbL4T2b.setSelected(false);
			cbL4T2c.setSelected(false);
			cbL4T2d.setSelected(false);

			outputL4T2=CheckJava.submitL4T2(taUserInputL4T2, TEXTAREA_FONT);
			taUserOutputL4T2.setText(outputL4T2[0]);
			taUserOutputL4T2.setStyle(outputL4T2[1]);

			//if statements to check the user's inputed code and the generated output and make the correct checkboxes for this task selected
			if(!taUserOutputL4T2.getText().contains("Don't forget to begin your first line of code") && !taUserOutputL4T2.getText().contains("Don't forget to begin your second line of code") && !taUserOutputL4T2.getText().contains("You must assign iNum1 as an integer") && !taUserOutputL4T2.getText().contains("You must assign iNum2 as an integer"))
			{
				cbL4T2a.setSelected(true);
			}//end if for first checkbox
			if(taUserInputL4T2.getText().contains("int iResult;"))
			{
				cbL4T2b.setSelected(true);
			}//end if for second checkbox
			if(taUserInputL4T2.getText().contains("System.out.println(iResult);") && !taUserOutputL4T2.getText().startsWith("Error!"))
			{
				cbL4T2c.setSelected(true);
			}//end if for third checkbox
			if(!taUserOutputL4T2.getText().startsWith("Error!"))
			{
				cbL4T2d.setSelected(true);
			}//end if for fourth checkbox
			if(taUserInputL4T2.getText().contains("+") && !taUserOutputL4T2.getText().startsWith("Error!"))
			{
				cbL4T2e.setSelected(true);
			}//end if for fifth checkbox
			if(taUserInputL4T2.getText().contains("-") && !taUserOutputL4T2.getText().startsWith("Error!"))
			{
				cbL4T2f.setSelected(true);
			}//end if for sixth checkbox
			if(taUserInputL4T2.getText().contains("*") && !taUserOutputL4T2.getText().startsWith("Error!"))
			{
				cbL4T2g.setSelected(true);
			}//end if for seventh checkbox
			if(taUserInputL4T2.getText().contains("/") && !taUserOutputL4T2.getText().startsWith("Error!"))
			{
				cbL4T2h.setSelected(true);
			}//end if for eighth checkbox

			//when the user has successfully completed the current task and is ready for the next task
			if(cbL4T2a.isSelected() && cbL4T2b.isSelected() && cbL4T2c.isSelected() && cbL4T2d.isSelected() && cbL4T2e.isSelected() && cbL4T2f.isSelected() && cbL4T2g.isSelected() && cbL4T2h.isSelected())
			{
				textflowL4T3.setVisible(true);
				textflowL4T3.setManaged(true);
				hbxL4T3Input.setVisible(true);
				hbxL4T3Input.setManaged(true);
				hbxL4T3Output.setVisible(true);
				hbxL4T3Output.setManaged(true);
				hbxL4T3Checklist.setVisible(true);
				hbxL4T3Checklist.setManaged(true);
				if(user.getProgressLevel()<(progressIncrease*17))
				{
					pbsUserProgress.setProgress((progressIncrease*17));
					user.setProgressLevel(pbsUserProgress.getProgress());
				}//end inside if
			}//end outside if
		});

		//determining what to do when the submit button for L4T3
		btnSubmitL4T3.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			String [] outputL4T3=new String[2];
			trialCounter[3][2]++;
			userResponse[3][2][trialCounter[3][2]]=taUserInputL4T3.getText();

			//resetting all checkboxes in this task to be deselected
			cbL4T3a.setSelected(false);
			cbL4T3b.setSelected(false);
			cbL4T3c.setSelected(false);
			cbL4T3d.setSelected(false);

			outputL4T3=CheckJava.submitL4T3(taUserInputL4T3, TEXTAREA_FONT);
			taUserOutputL4T3.setText(outputL4T3[0]);
			taUserOutputL4T3.setStyle(outputL4T3[1]);

			//if statements to check the user's inputed code and the generated output and make the correct checkboxes for this task selected
			if(!taUserOutputL4T3.getText().contains("Don't forget to begin your first line of code") && !taUserOutputL4T3.getText().contains("Don't forget to begin your second line of code") && !taUserOutputL4T3.getText().contains("You must assign dNum1 as a double") && !taUserOutputL4T3.getText().contains("You must assign dNum2 as a double"))
			{
				cbL4T3a.setSelected(true);
			}//end if for first checkbox
			if(taUserInputL4T3.getText().contains("double dResult;"))
			{
				cbL4T3b.setSelected(true);
			}//end if for second checkbox
			if(taUserInputL4T3.getText().contains("System.out.println(dResult);") && !taUserOutputL4T3.getText().startsWith("Error!"))
			{
				cbL4T3c.setSelected(true);
			}//end if for third checkbox
			if(!taUserOutputL4T3.getText().startsWith("Error!"))
			{
				cbL4T3d.setSelected(true);
			}//end if for fourth checkbox
			if(taUserInputL4T3.getText().contains("+") && !taUserOutputL4T3.getText().startsWith("Error!"))
			{
				cbL4T3e.setSelected(true);
			}//end if for fifth checkbox
			if(taUserInputL4T3.getText().contains("-") && !taUserOutputL4T3.getText().startsWith("Error!"))
			{
				cbL4T3f.setSelected(true);
			}//end if for sixth checkbox
			if(taUserInputL4T3.getText().contains("*") && !taUserOutputL4T3.getText().startsWith("Error!"))
			{
				cbL4T3g.setSelected(true);
			}//end if for seventh checkbox
			if(taUserInputL4T3.getText().contains("/") && !taUserOutputL4T3.getText().startsWith("Error!"))
			{
				cbL4T3h.setSelected(true);
			}//end if for eighth checkbox

			//when the user has successfully completed the current task and is ready for the next task
			if(cbL4T3a.isSelected() && cbL4T3b.isSelected() && cbL4T3c.isSelected() && cbL4T3d.isSelected() && cbL4T3e.isSelected() && cbL4T3f.isSelected() && cbL4T3g.isSelected() && cbL4T3h.isSelected())
			{
				textflowL4T4.setVisible(true);
				textflowL4T4.setManaged(true);
				hbxL4T4Input.setVisible(true);
				hbxL4T4Input.setManaged(true);
				hbxL4T4Output.setVisible(true);
				hbxL4T4Output.setManaged(true);
				hbxL4T4Checklist.setVisible(true);
				hbxL4T4Checklist.setManaged(true);
				if(user.getProgressLevel()<(progressIncrease*18))
				{
					pbsUserProgress.setProgress((progressIncrease*18));
					user.setProgressLevel(pbsUserProgress.getProgress());
				}//end inside if
			}//end outside if
		});

		//determining what to do when the submit button for L4T4
		btnSubmitL4T4.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			String [] outputL4T4=new String[2];
			trialCounter[3][3]++;
			userResponse[3][3][trialCounter[3][3]]=taUserInputL4T4.getText();

			//resetting all checkboxes in this task to be deselected
			cbL4T4a.setSelected(false);
			cbL4T4b.setSelected(false);
			cbL4T4c.setSelected(false);
			cbL4T4d.setSelected(false);

			outputL4T4=CheckJava.submitL4T4(taUserInputL4T4, TEXTAREA_FONT);
			taUserOutputL4T4.setText(outputL4T4[0]);
			taUserOutputL4T4.setStyle(outputL4T4[1]);

			//if statements to check the user's inputed code and the generated output and make the correct checkboxes for this task selected
			if(taUserInputL4T4.getText().contains("int dividend") && taUserInputL4T4.getText().contains("int divisor") && !taUserOutputL4T4.getText().contains("You must assign dividend as an integer.") && !taUserOutputL4T4.getText().contains("You must assign divisor as an integer."))
			{
				cbL4T4a.setSelected(true);
			}//end if for first checkbox
			if(taUserInputL4T4.getText().contains("int quotient") && !taUserOutputL4T4.getText().contains("Don't forget to initialize the integer variable quotient on the third line of code properly."))
			{
				cbL4T4b.setSelected(true);
			}//end if for second checkbox
			if(taUserInputL4T4.getText().contains("dividend%divisor") || taUserInputL4T4.getText().contains("dividend % divisor"))
			{
				cbL4T4c.setSelected(true);
			}//end if for third checkbox
			if(!taUserOutputL4T4.getText().startsWith("Error!"))
			{
				cbL4T4d.setSelected(true);
			}//end if for fourth checkbox

			//when the user has successfully completed the current task and is ready for the next task
			if(cbL4T4a.isSelected() && cbL4T4b.isSelected() && cbL4T4c.isSelected() && cbL4T4d.isSelected())
			{
				lblL4Done.setVisible(true);
				lblL4Done.setManaged(true);
				if(user.getProgressLevel()<(progressIncrease*19))
				{
					pbsUserProgress.setProgress((progressIncrease*19));
					user.setProgressLevel(pbsUserProgress.getProgress());
				}//end inside if
			}//end outside if
		});

		//creating and setting the scroll pane for this lesson
		ScrollPane spL4 = new ScrollPane();
		spL4.setContent(vbxL4);

		//creating the scene
		Scene sceneL4 = new Scene(spL4);

		lastScene="lesson4"; //setting the previous scene to this scene

		//setting/showing the stage
		myStage.getIcons().add(new Image("FromOneToAnotherLogo.png"));
		myStage.setTitle("From C++ to Java - ... - Lesson #4");
		myStage.setScene(sceneL4);
		myStage.centerOnScreen();
		myStage.show();

	}//end lesson4 method


	/* Method Author: Brooke Cronin
	 * Method Name: lesson5
	 * Description: Creates the scene for the fifth lesson. Allows the user to view and attempt all of the tasks and get feedback after completing each task.
	 * Parameters: Stage myStage
	 * Returns: N/A
	 * Throws: NullPointerException
	 */

	public void lesson5(Stage myStage) throws NullPointerException
	{
		myStage.setOnHiding( event -> {try {
			exitProgram();
		} catch (IOException e) {
			
			e.printStackTrace();
		}} );
		//resetting the text in all of the user output text areas and disabling them so that the user can't change what appears in them
		TextArea taUserOutputL5T1 = new TextArea();
		taUserOutputL5T1.setText("Not submitted yet");
		taUserOutputL5T1.setEditable(false);
		taUserOutputL5T1.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
		TextArea taUserOutputL5T2 = new TextArea();
		taUserOutputL5T2.setText("Not submitted yet");
		taUserOutputL5T2.setEditable(false);
		taUserOutputL5T2.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
		TextArea taUserOutputL5T3 = new TextArea();
		taUserOutputL5T3.setText("Not submitted yet");
		taUserOutputL5T3.setEditable(false);
		taUserOutputL5T3.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
		TextArea taUserOutputL5T4 = new TextArea();
		taUserOutputL5T4.setText("Not submitted yet");
		taUserOutputL5T4.setEditable(false);
		taUserOutputL5T4.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
		TextArea taUserOutputL5T5 = new TextArea();
		taUserOutputL5T5.setText("Not submitted yet");
		taUserOutputL5T5.setEditable(false);
		taUserOutputL5T5.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");

		//creating the vertical box for lesson 5 screen
		VBox vbxL5 = new VBox(GAP);
		vbxL5.setPadding(new Insets(GAP, GAP, GAP, GAP));

		//adding the user's progress bar to the vertical box
		pbsUserProgress.setMaxWidth(Double.MAX_VALUE);
		vbxL5.getChildren().add(pbsUserProgress);

		//creating, setting, and adding a header Label
		AnchorPane apLesson5TitleHeader = new AnchorPane();
		Label lblLesson5TitleHeader = new Label("Lesson #5: The Math and Random Classes");
		lblLesson5TitleHeader.setStyle("-fx-text-fill: black; -fx-font-size: "+LESSON_HEADER_FONT);
		lblLesson5TitleHeader.setMaxWidth(Double.MAX_VALUE);
		AnchorPane.setLeftAnchor(lblLesson5TitleHeader, 0.0);
		AnchorPane.setRightAnchor(lblLesson5TitleHeader, 0.0);
		lblLesson5TitleHeader.setAlignment(Pos.CENTER);
		apLesson5TitleHeader.getChildren().add(lblLesson5TitleHeader);
		vbxL5.getChildren().add(apLesson5TitleHeader);

		//adding the name of the current task
		Label lblL5T1Header = new Label("\nTask #1: Exponents");
		lblL5T1Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		vbxL5.getChildren().add(lblL5T1Header);

		//creating, setting, and adding the first task instructions
		Text textL5T1a = new Text("\n\nUp until now, in Java you have only been using addition, subtraction, multiplication, division, and brackets to calculate math problems. But what about exponents? In Python, you can tdo this two different ways (in both examples, it will show 5^2).");
		textL5T1a.setFill(Color.BLACK);
		Text textL5T1b = new Text("\n5**2");
		textL5T1b.setFill(Color.DARKVIOLET);
		Text textL5T1c = new Text("\n or you can use the pow() function");
		textL5T1c.setFill(Color.BLACK);
		Text textL5T1d = new Text("\npow(5,2)");
		textL5T1d.setFill(Color.DARKVIOLET);
		Text textL5T1e = new Text("\nUsing exponents in Java is similar to the second way listed above in Python. However, there is a still few differences. You must call the pow method from the Math class. Below is demonstrating the code for calculating 5^2.");
		textL5T1e.setFill(Color.BLACK);
		Text textL5T1f = new Text("\nMath.pow(5,2);");
		textL5T1f.setFill(Color.BLUE);
		Text textL5T1g = new Text("\nIn this task, print the result of an exponent to the screen without the use of variables.");
		textL5T1g.setFill(Color.BLACK);
		TextFlow textflowL5T1 = new TextFlow(textL5T1a, textL5T1b, textL5T1c, textL5T1d, textL5T1e, textL5T1f, textL5T1g);
		textflowL5T1.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		vbxL5.getChildren().add(textflowL5T1);

		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput1 = new Label("Your code: ");
		lblInput1.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL5T1 = new TextArea();
		taUserInputL5T1.setPrefSize(taWidth, taLineHeight);
		taUserInputL5T1.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL5T1.setText("public class L5T1Response\n{\n\tpublic static void main()\n\t{\n\n\t}//end main method\n}//end L5T1Response class");
		Button btnSubmitL5T1 = new Button("Submit");
		btnSubmitL5T1.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL5T1Input = new HBox(GAP, lblInput1, taUserInputL5T1, btnSubmitL5T1);
		vbxL5.getChildren().add(hbxL5T1Input);
		//for user's output
		Label lblOutput1 = new Label("Output:     ");
		lblOutput1.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL5T1.setPrefSize(taWidth, taLineHeight);
		HBox hbxL5T1Output = new HBox(GAP, lblOutput1, taUserOutputL5T1);
		vbxL5.getChildren().add(hbxL5T1Output);
		//for user's result
		Label lblCriteria1 = new Label("Criteria");
		CheckBox cbL5T1a = new CheckBox("Begins with System.out.println(");
		cbL5T1a.setDisable(true);
		CheckBox cbL5T1b = new CheckBox("Output is a decimal value of an exponent calculation");
		cbL5T1b.setDisable(true);
		CheckBox cbL5T1c = new CheckBox("Code contains a call to the Math.pow() method");
		cbL5T1c.setDisable(true);
		CheckBox cbL5T1d = new CheckBox("Ends with );");
		cbL5T1d.setDisable(true);
		HBox hbxL5T1Checklist = new HBox(GAP, lblCriteria1, cbL5T1a, cbL5T1b, cbL5T1c, cbL5T1d);
		hbxL5T1Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria1.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		vbxL5.getChildren().add(hbxL5T1Checklist);

		//adding the name of the current task
		Label lblL5T2Header = new Label("\n\n\nTask #2: Square Roots");
		lblL5T2Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		if(user.getProgressLevel()<(progressIncrease*20))
		{
			lblL5T2Header.setVisible(false);
			lblL5T2Header.setManaged(false);
		}//end if
		vbxL5.getChildren().add(lblL5T2Header);

		//creating, setting, and adding the second task instructions
		Text textL5T2a = new Text("\n\nWhat about square rooting a number? In Python, you would have to import the math module at the top of your code. Then to calculate the square root of 16 (for example) your code would look similar to below.");
		textL5T2a.setFill(Color.BLACK);
		Text textL5T2b = new Text("\nmath.sqrt(16)");
		textL5T2b.setFill(Color.DARKVIOLET);
		Text textL5T2c = new Text("\nIn Java, it is very similar to in Python except there is no import, the m in math must be a capital letter, and there must be a semicolon at the end (see below for how to calculate the square root of 16 in Java).");
		textL5T2c.setFill(Color.BLACK);
		Text textL5T2d = new Text("\nMath.sqrt(16);");
		textL5T2d.setFill(Color.BLUE);
		Text textL5T2e = new Text("\nCreate two double variables. Name one ");
		textL5T2e.setFill(Color.BLACK);
		Text textL5T2f = new Text("num");
		textL5T2f.setFill(Color.rgb(121, 76, 52));
		Text textL5T2g = new Text(" and initialize it to a number that you would like to find the square root of.");
		textL5T2g.setFill(Color.BLACK);
		Text textL5T2h = new Text("\nName the other ");
		textL5T2h.setFill(Color.BLACK);		
		Text textL5T2i = new Text("squareRoot");
		textL5T2i.setFill(Color.rgb(121, 76, 52));
		Text textL5T2j = new Text(" and initialize it to the result of square rooting ");
		textL5T2j.setFill(Color.BLACK);
		Text textL5T2k = new Text("num");
		textL5T2k.setFill(Color.rgb(121, 76, 52));
		Text textL5T2l = new Text(".");
		textL5T2l.setFill(Color.BLACK);
		Text textL5T2m = new Text("\nFinally, print the value of ");
		textL5T2m.setFill(Color.BLACK);
		Text textL5T2n = new Text("squareRoot");
		textL5T2n.setFill(Color.rgb(121, 76, 52));
		Text textL5T2o = new Text(" to the screen.");
		textL5T2o.setFill(Color.BLACK);
		TextFlow textflowL5T2 = new TextFlow(textL5T2a, textL5T2b, textL5T2c, textL5T2d, textL5T2e, textL5T2f, textL5T2g, textL5T2h, textL5T2i, textL5T2j, textL5T2k, textL5T2l, textL5T2m, textL5T2n, textL5T2o);
		textflowL5T2.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*20))
		{
			textflowL5T2.setVisible(false);
			textflowL5T2.setManaged(false);
		}//end if
		vbxL5.getChildren().add(textflowL5T2);

		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput2 = new Label("Your code: ");
		lblInput2.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL5T2 = new TextArea();
		taUserInputL5T2.setPrefSize(taWidth, taLineHeight*4);
		taUserInputL5T2.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL5T2.setText("public class L5T2Response\n{\n\tpublic static void main()\n\t{\n\n\t}//end main method\n}//end L5T2Response class");
		Button btnSubmitL5T2 = new Button("Submit");
		btnSubmitL5T2.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL5T2Input = new HBox(GAP, lblInput2, taUserInputL5T2, btnSubmitL5T2);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*20))
		{
			hbxL5T2Input.setVisible(false);
			hbxL5T2Input.setManaged(false);
		}//end if
		vbxL5.getChildren().add(hbxL5T2Input);
		//for user's output
		Label lblOutput2 = new Label("Output:     ");
		lblOutput2.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL5T2.setPrefSize(taWidth, taLineHeight);
		HBox hbxL5T2Output = new HBox(GAP, lblOutput2, taUserOutputL5T2);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*20))
		{
			hbxL5T2Output.setVisible(false);
			hbxL5T2Output.setManaged(false);
		}//end if
		vbxL5.getChildren().add(hbxL5T2Output);
		//for user's result
		Label lblCriteria2 = new Label("Criteria");
		CheckBox cbL5T2a = new CheckBox("num was created as a double variable and initialized to a double value");
		cbL5T2a.setDisable(true);
		CheckBox cbL5T2b = new CheckBox("squareRoot was created as a double variable and initialized correctly using the correct method from the Math class");
		cbL5T2b.setDisable(true);
		CheckBox cbL5T2c = new CheckBox("Output is the value of squareRoot");
		cbL5T2c.setDisable(true);
		HBox hbxL5T2Checklist = new HBox(GAP, lblCriteria2, cbL5T2a, cbL5T2b, cbL5T2c);
		hbxL5T2Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria2.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*20))
		{
			hbxL5T2Checklist.setVisible(false);
			hbxL5T2Checklist.setManaged(false);
		}//end if
		vbxL5.getChildren().add(hbxL5T2Checklist);

		//adding the name of the current task
		Label lblL5T3Header = new Label("\n\n\nTask #3: Angles and Trigonometry");
		lblL5T3Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		if(user.getProgressLevel()<(progressIncrease*21))
		{
			lblL5T3Header.setVisible(false);
			lblL5T3Header.setManaged(false);
		}//end if
		vbxL5.getChildren().add(lblL5T3Header);

		//creating, setting, and adding the third task instructions
		Text textL5T3a = new Text("\n\nNow that you know about BEDMAS and square root calculations, what about some slightly more difficult math calculations? In this task we will be exploring how to convert an angle measurement from degrees to radians, and how to calculate the sine, cosine, and tangent of that angle in radians.\nLet's begin with the conversion between degrees and radians. Below is how you would convert 30 degrees to radians in Python (with the math module imported at the top).");
		textL5T3a.setFill(Color.BLACK);
		Text textL5T3b = new Text("\nmath.radians(30)");
		textL5T3b.setFill(Color.DARKVIOLET);
		Text textL5T3c = new Text("\nIn Java, it is very similar to Python except for the import, which is the same as in the last task, and the call to the method in the Math class. See the code below that converts 30 degrees to radians in Java.");
		textL5T3c.setFill(Color.BLACK);
		Text textL5T3d = new Text("\nMath.toRadians(30);");
		textL5T3d.setFill(Color.BLUE);
		Text textL5T3e = new Text("\nWhat about determining the sine, cosine, and tangent of an angle in radians? In both languages, it is very identical except for the imports, in Java the m in math needs to be a capital letter, and there needs to be a semicolon at the end of the call to the method in Java. Below, is how you could determine the sine, cosine, and tangent of the double variable ");
		textL5T3e.setFill(Color.BLACK);
		Text textL5T3f = new Text("c");
		textL5T3f.setFill(Color.rgb(121, 76, 52));
		Text textL5T3g = new Text(" that is a measurement of an angle in radians in Java.");
		textL5T3g.setFill(Color.BLACK);
		Text textL5T3h = new Text("\nMath.sin(");
		textL5T3h.setFill(Color.BLUE);
		Text textL5T3i = new Text("c");
		textL5T3i.setFill(Color.rgb(121, 76, 52));
		Text textL5T3j = new Text(");");
		textL5T3j.setFill(Color.BLUE);
		Text textL5T3k = new Text("\nMath.cos(");
		textL5T3k.setFill(Color.BLUE);
		Text textL5T3l = new Text("c");
		textL5T3l.setFill(Color.rgb(121, 76, 52));
		Text textL5T3m = new Text(");");
		textL5T3m.setFill(Color.BLUE);
		Text textL5T3n = new Text("\nMath.tan(");
		textL5T3n.setFill(Color.BLUE);
		Text textL5T3o = new Text("c");
		textL5T3o.setFill(Color.rgb(121, 76, 52));
		Text textL5T3p = new Text(");");
		textL5T3p.setFill(Color.BLUE);
		Text textL5T3q = new Text("\nIn this task, you will first create a double variable called ");
		textL5T3q.setFill(Color.BLACK);
		Text textL5T3r = new Text("degrees");
		textL5T3r.setFill(Color.rgb(121, 76, 52));
		Text textL5T3s = new Text(" and initialize it to any double value you would like. \nNext, create a double variable called ");
		textL5T3s.setFill(Color.BLACK);
		Text textL5T3t = new Text("radians");
		textL5T3t.setFill(Color.rgb(121, 76, 52));
		Text textL5T3u = new Text(" and initialize it to the radian value of degrees using the ");
		textL5T3u.setFill(Color.BLACK);
		Text textL5T3v = new Text("Math.toRadians();");
		textL5T3v.setFill(Color.BLUE);
		Text textL5T3w = new Text(" method.");
		textL5T3w.setFill(Color.BLACK);
		Text textL5T3x = new Text("\nOn the next three lines of code, create and initialize three double variables called ");
		textL5T3x.setFill(Color.BLACK);
		Text textL5T3y = new Text("sine");
		textL5T3y.setFill(Color.rgb(121, 76, 52));
		Text textL5T3z = new Text(", ");
		textL5T3z.setFill(Color.BLACK);
		Text textL5T3aa = new Text("cosine");
		textL5T3aa.setFill(Color.rgb(121, 76, 52));
		Text textL5T3ab = new Text(", and ");
		textL5T3ab.setFill(Color.BLACK);
		Text textL5T3ac = new Text("tangent");
		textL5T3ac.setFill(Color.rgb(121, 76, 52));
		Text textL5T3ad = new Text(" using the appropriate method from the Math class. Finally, print the values of ");
		textL5T3ad.setFill(Color.BLACK);
		Text textL5T3ae = new Text("degrees");
		textL5T3ae.setFill(Color.rgb(121, 76, 52));
		Text textL5T3af = new Text(", ");
		textL5T3af.setFill(Color.BLACK);
		Text textL5T3ag = new Text("radians");
		textL5T3ag.setFill(Color.rgb(121, 76, 52));
		Text textL5T3ah = new Text(", ");
		textL5T3ah.setFill(Color.BLACK);
		Text textL5T3ai = new Text("sine");
		textL5T3ai.setFill(Color.rgb(121, 76, 52));
		Text textL5T3aj = new Text(", ");
		textL5T3aj.setFill(Color.BLACK);
		Text textL5T3ak = new Text("cosine");
		textL5T3ak.setFill(Color.rgb(121, 76, 52));
		Text textL5T3al = new Text(", and ");
		textL5T3al.setFill(Color.BLACK);
		Text textL5T3am = new Text("tangent");
		textL5T3am.setFill(Color.rgb(121, 76, 52));
		Text textL5T3an = new Text(" on one line to the screen with each value separated by a comma and a space.");
		textL5T3an.setFill(Color.BLACK);
		TextFlow textflowL5T3 = new TextFlow(textL5T3a, textL5T3b, textL5T3c, textL5T3d, textL5T3e, textL5T3f, textL5T3g, textL5T3h, textL5T3i, textL5T3j, textL5T3k, textL5T3l, textL5T3m, textL5T3n, textL5T3o, textL5T3p, textL5T3q, textL5T3r, textL5T3s, textL5T3t, textL5T3u, textL5T3v, textL5T3w, textL5T3x, textL5T3y, textL5T3z, textL5T3aa, textL5T3ab, textL5T3ac, textL5T3ad, textL5T3ae, textL5T3af, textL5T3ag, textL5T3ah, textL5T3ai, textL5T3aj, textL5T3ak, textL5T3al, textL5T3am, textL5T3an);
		textflowL5T3.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*21))
		{
			textflowL5T3.setVisible(false);
			textflowL5T3.setManaged(false);
		}//end if
		vbxL5.getChildren().add(textflowL5T3);

		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput3 = new Label("Your code: ");
		lblInput3.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL5T3 = new TextArea();
		taUserInputL5T3.setPrefSize(taWidth, taLineHeight*7);
		taUserInputL5T3.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL5T3.setText("public class L5T3Response\n{\n\tpublic static void main()\n\t{\n\n\t}//end main method\n}//end L5T3Response class");
		Button btnSubmitL5T3 = new Button("Submit");
		btnSubmitL5T3.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL5T3Input = new HBox(GAP, lblInput3, taUserInputL5T3, btnSubmitL5T3);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*21))
		{
			hbxL5T3Input.setVisible(false);
			hbxL5T3Input.setManaged(false);
		}//end if
		vbxL5.getChildren().add(hbxL5T3Input);
		//for user's output
		Label lblOutput3 = new Label("Output:     ");
		lblOutput3.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL5T3.setPrefSize(taWidth, taLineHeight);
		HBox hbxL5T3Output = new HBox(GAP, lblOutput3, taUserOutputL5T3);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*21))
		{
			hbxL5T3Output.setVisible(false);
			hbxL5T3Output.setManaged(false);
		}//end if
		vbxL5.getChildren().add(hbxL5T3Output);
		//for user's result
		Label lblCriteria3 = new Label("Criteria");
		CheckBox cbL5T3a = new CheckBox("degrees, radians, sine, cosine, and tangent are are created as double variables and initialized correctly");
		cbL5T3a.setDisable(true);
		CheckBox cbL5T3b = new CheckBox("The Math class was used to calculate the values of radians, sine, cosine, and tangent");
		cbL5T3b.setDisable(true);
		CheckBox cbL5T3c = new CheckBox("Output is the values of degrees, radians, sine, cosine, and tangent with all values separated by a comma and a space");
		cbL5T3c.setDisable(true);
		HBox hbxL5T3Checklist = new HBox(GAP, lblCriteria3, cbL5T3a, cbL5T3b, cbL5T3c);
		hbxL5T3Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria3.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*21))
		{
			hbxL5T3Checklist.setVisible(false);
			hbxL5T3Checklist.setManaged(false);
		}//end if
		vbxL5.getChildren().add(hbxL5T3Checklist);

		//adding the name of the current task
		Label lblL5T4Header = new Label("\n\n\nTask #4: Generating Random Numbers Using the Math Class");
		lblL5T4Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		if(user.getProgressLevel()<(progressIncrease*22))
		{
			lblL5T4Header.setVisible(false);
			lblL5T4Header.setManaged(false);
		}//end if
		vbxL5.getChildren().add(lblL5T4Header);

		//creating, setting, and adding the fourth task instructions
		Text textL5T4a = new Text("\n\nWith the Math class in Java, not only can you calculate more complex math problems, but you can also generate random integer and double values between certain minimum and maximum values.\nIn Python, you could generate a random number with the random module imported. In Python, to generate a random double between 0 and 1, the code would look something similar to below.");
		textL5T4a.setFill(Color.BLACK);
		Text textL5T4b = new Text("\nrandom.random()");
		textL5T4b.setFill(Color.DARKVIOLET);
		Text textL5T4c = new Text("\nTo do this in Java, the code would look similar to below.");
		textL5T4c.setFill(Color.BLACK);
		Text textL5T4d = new Text("\nMath.random();");
		textL5T4d.setFill(Color.BLUE);
		Text textL5T4e = new Text("\nWhat about an integer between 1 and 6? In Python, the code is below.");
		textL5T4e.setFill(Color.BLACK);
		Text textL5T4f = new Text("\nrandom.randint(1,6)");
		textL5T4f.setFill(Color.DARKVIOLET);
		Text textL5T4g = new Text("\nThe Java equivalence is below.");
		textL5T4g.setFill(Color.BLACK);
		Text textL5T4h = new Text("\n(int) (Math.random()*6)+1;");
		textL5T4h.setFill(Color.BLUE);
		Text textL5T4i = new Text("\nDid you notice how when a random integer is generated using the Math class in Java, that the line of code began with ");
		textL5T4i.setFill(Color.BLACK);
		Text textL5T4j = new Text("(int)");
		textL5T4j.setFill(Color.BLUE);
		Text textL5T4k = new Text("?\nThis is because the default return type of the method (and most other methods in the Math class) is a double value, so by adding ");
		textL5T4k.setFill(Color.BLACK);
		Text textL5T4l = new Text("(int)");
		textL5T4l.setFill(Color.BLUE);
		Text textL5T4m = new Text(" at the start, the returned value will be rounded to the nearest whole number and returned as an integer value.\nIn this task, you will be creating two variables.\nOne will be an integer variable called randomInt and initialized to a random integer within a given range (you can try different ones as well).\nThe other will be a double variable called randomDouble and initialized to a random decimal between 0 and 1.\nThen print both values, with one per line on the screen.");
		textL5T4m.setFill(Color.BLACK);
		TextFlow textflowL5T4 = new TextFlow(textL5T4a, textL5T4b, textL5T4c, textL5T4d, textL5T4e, textL5T4f, textL5T4g, textL5T4h, textL5T4i, textL5T4j, textL5T4k, textL5T4l, textL5T4m);
		textflowL5T4.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*22))
		{
			textflowL5T4.setVisible(false);
			textflowL5T4.setManaged(false);
		}//end if
		vbxL5.getChildren().add(textflowL5T4);

		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput4 = new Label("Your code: ");
		lblInput4.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL5T4 = new TextArea();
		taUserInputL5T4.setPrefSize(taWidth, taLineHeight*4);
		taUserInputL5T4.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL5T4.setText("public class L5T4Response\n{\n\tpublic static void main()\n\t{\n\n\t}//end main method\n}//end L5T4Response class");
		Button btnSubmitL5T4 = new Button("Submit");
		btnSubmitL5T4.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL5T4Input = new HBox(GAP, lblInput4, taUserInputL5T4, btnSubmitL5T4);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*22))
		{
			hbxL5T4Input.setVisible(false);
			hbxL5T4Input.setManaged(false);
		}//end if
		vbxL5.getChildren().add(hbxL5T4Input);
		//for user's output
		Label lblOutput4 = new Label("Output:     ");
		lblOutput4.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL5T4.setPrefSize(taWidth, taLineHeight);
		HBox hbxL5T4Output = new HBox(GAP, lblOutput4, taUserOutputL5T4);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*22))
		{
			hbxL5T4Output.setVisible(false);
			hbxL5T4Output.setManaged(false);
		}//end if
		vbxL5.getChildren().add(hbxL5T4Output);
		//for user's result
		Label lblCriteria4 = new Label("Criteria");
		CheckBox cbL5T4a = new CheckBox("randomInt was created as an integer variable, is between a specific range, and initialized using the Math class");
		cbL5T4a.setDisable(true);
		CheckBox cbL5T4b = new CheckBox("randomDouble was created as a double variable, is between 0 and 1, and initialized using the Math class");
		cbL5T4b.setDisable(true);
		CheckBox cbL5T4c = new CheckBox("Output is the values of both variables (separated by a new line)");
		cbL5T4c.setDisable(true);
		HBox hbxL5T4Checklist = new HBox(GAP, lblCriteria4, cbL5T4a, cbL5T4b, cbL5T4c);
		hbxL5T4Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria4.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*22))
		{
			hbxL5T4Checklist.setVisible(false);
			hbxL5T4Checklist.setManaged(false);
		}//end if
		vbxL5.getChildren().add(hbxL5T4Checklist);

		//adding the name of the current task
		Label lblL5T5Header = new Label("\n\n\nTask #5: Generating Random Numbers Using the Random Class");
		lblL5T5Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		if(user.getProgressLevel()<(progressIncrease*23))
		{
			lblL5T5Header.setVisible(false);
			lblL5T5Header.setManaged(false);
		}//end if
		vbxL5.getChildren().add(lblL5T5Header);

		//creating, setting, and adding the fifth task instructions
		Text textL5T5a = new Text("\n\nThere is another way in Java to generate a random integer and/or double value.\nHowever, this way does not use the Math class, and instead the Random class (as you may have guessed from the task name).\nThis way does require an import and decleration. The import is located where it said in the instructions and is ");
		textL5T5a.setFill(Color.BLACK);
		Text textL5T5b = new Text("import java.util.*;");
		textL5T5b.setFill(Color.BLUE);
		Text textL5T5c = new Text("\nThe decleration is located in the main method (and/or any other method that you choose to generate a random number in). The decleration is ");
		textL5T5c.setFill(Color.BLACK);
		Text textL5T5d = new Text("Random rand = new Random();");
		textL5T5d.setFill(Color.BLUE);
		Text textL5T5e = new Text("\nrand can be replaced with anything else that you want (but it should make sense and follow camelCase), and it must be consistant (All of the examples will be using rand, so be carefull to replace rand with whatever you chose in the decleration).");
		textL5T5e.setFill(Color.DARKVIOLET);
		Text textL5T5f = new Text("\nTo generate a random double between 0 and 1 using this method, the code would be:");
		textL5T5f.setFill(Color.BLACK);
		Text textL5T5g = new Text("\nrand.nextDouble();");
		textL5T5g.setFill(Color.BLUE);
		Text textL5T5h = new Text("\nTo generate a random integer between 1 and 6 using this method, the code would be:");
		textL5T5h.setFill(Color.BLACK);
		Text textL5T5i = new Text("\nrand.nextInt(6)+1;");
		textL5T5i.setFill(Color.BLUE);
		Text textL5T5j = new Text("\nIn this task, you will be doing the same thing as in the last task, except you will be using the Random class instead of the Math class.");
		textL5T5j.setFill(Color.BLACK);
		TextFlow textflowL5T5 = new TextFlow(textL5T5a, textL5T5b, textL5T5c, textL5T5d, textL5T5e, textL5T5f, textL5T5g, textL5T5h, textL5T5i, textL5T5j);
		textflowL5T5.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*23))
		{
			textflowL5T5.setVisible(false);
			textflowL5T5.setManaged(false);
		}//end if
		vbxL5.getChildren().add(textflowL5T5);

		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput5 = new Label("Your code: ");
		lblInput5.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL5T5 = new TextArea();
		taUserInputL5T5.setPrefSize(taWidth, taLineHeight*5);
		taUserInputL5T5.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL5T5.setText("public class L5T5Response\n{\n\tpublic static void main()\n\t{\n\n\t}//end main method\n}//end L5T5Response class");
		Button btnSubmitL5T5 = new Button("Submit");
		btnSubmitL5T5.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL5T5Input = new HBox(GAP, lblInput5, taUserInputL5T5, btnSubmitL5T5);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*23))
		{
			hbxL5T5Input.setVisible(false);
			hbxL5T5Input.setManaged(false);
		}//end if
		vbxL5.getChildren().add(hbxL5T5Input);
		//for user's output
		Label lblOutput5 = new Label("Output:     ");
		lblOutput5.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL5T5.setPrefSize(taWidth, taLineHeight);
		HBox hbxL5T5Output = new HBox(GAP, lblOutput5, taUserOutputL5T5);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*23))
		{
			hbxL5T5Output.setVisible(false);
			hbxL5T5Output.setManaged(false);
		}//end if
		vbxL5.getChildren().add(hbxL5T5Output);
		//for user's result
		Label lblCriteria5 = new Label("Criteria");
		CheckBox cbL5T5a = new CheckBox("An object from the Random class was created on the first line of code and used to initialize randomInt and randomDouble");
		cbL5T5a.setDisable(true);
		CheckBox cbL5T5b = new CheckBox("randomInt was created as an integer variable, is between a specific range, and initialized using the Random class");
		cbL5T5b.setDisable(true);
		CheckBox cbL5T5c = new CheckBox("randomDouble was created as a double variable, is between 0 and 1, and initialized using the Random class");
		cbL5T5c.setDisable(true);
		CheckBox cbL5T5d = new CheckBox("Output is the values of both variables (separated by a new line)");
		cbL5T5d.setDisable(true);
		HBox hbxL5T5Checklist = new HBox(GAP, lblCriteria5, cbL5T5a, cbL5T5b, cbL5T5c);
		hbxL5T5Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria5.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*23))
		{
			hbxL5T5Checklist.setVisible(false);
			hbxL5T5Checklist.setManaged(false);
		}//end if
		vbxL5.getChildren().add(hbxL5T5Checklist);

		//creating and adding a label to notify user that they have completed the current lesson
		Label lblL5Done = new Label("Congratulations, "+user.getfName()+"! You have finished Lesson #5: The Math and Random Classes.\n" + 
				"Click one of the buttons below to either restart this lesson, choose another lesson from the menu, go to the previous lesson, or continue with the next lesson.");
		lblL5Done.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*24))
		{
			lblL5Done.setVisible(false);
			lblL5Done.setManaged(false);
		}//end if
		vbxL5.getChildren().add(lblL5Done);

		//creating and adding the buttons at the bottom of the screen
		Button btnPreviousLesson = new Button("Previous Lesson");
		btnPreviousLesson.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnPreviousLesson.setOnAction(event -> lesson4(myStage));
		Button btnLessonMenu = new Button("Lesson Menu");
		btnLessonMenu.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnLessonMenu.setOnAction(event -> lessonMenu(myStage));
		Button btnExit = new Button("Exit");
		btnExit.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnExit.setOnAction(event -> 
		{
			try
			{
				exitProgram();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		});
		Button btnRestartL5 = new Button("Restart Lesson");
		btnRestartL5.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnRestartL5.setOnAction(event -> lesson5(myStage));
		Button btnNextLesson = new Button("Next Lesson");
		btnNextLesson.setStyle("-fx-font-size: "+CONTROL_FONT);
		if(user.getProgressLevel()<(progressIncrease*24))
		{
			btnNextLesson.setDisable(true);
		}//end if
		btnNextLesson.setOnAction(event -> lesson6(myStage));
		HBox hbxL5Controls = new HBox(GAP, btnPreviousLesson, btnLessonMenu, btnExit, btnRestartL5, btnNextLesson);
		vbxL5.getChildren().add(hbxL5Controls);	

		//determining what to do when the submit button for L5T1 is clicked
		btnSubmitL5T1.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			int checkboxSelectedCounterL5T1=0;
			trialCounter[4][0]++;

			//creating the array to store user's output and adding user input to the userResponse array
			String [] outputL5T1=new String[2];
			userResponse[4][0][trialCounter[4][0]]=taUserInputL5T1.getText();

			//determining and setting the output that the user will see based on their submission
			try
			{
				outputL5T1=CheckJava.submitL5T1(taUserInputL5T1, TEXTAREA_FONT);
			}
			catch (ScriptException e)
			{
				e.printStackTrace();
			}//end try catch
			taUserOutputL5T1.setText(outputL5T1[0]);
			taUserOutputL5T1.setStyle(outputL5T1[1]);

			//resetting all checkboxes in this task to be deselected
			cbL5T1a.setSelected(false);
			cbL5T1b.setSelected(false);
			cbL5T1c.setSelected(false);
			cbL5T1d.setSelected(false);

			//if statements to check the user's inputed code and the generated output and make the correct checkboxes for this task selected
			if(taUserInputL5T1.getText().startsWith("System.out.println("))
			{
				cbL5T1a.setSelected(true);
				checkboxSelectedCounterL5T1++;
			}//end if for first checkbox
			if(!taUserOutputL5T1.getText().startsWith("Error!") && taUserInputL5T1.getText().contains("Math.pow("))
			{
				cbL5T1b.setSelected(true);
				checkboxSelectedCounterL5T1++;
			}//end if for second checkbox
			if(!taUserOutputL5T1.getText().startsWith("Error!"))
			{
				cbL5T1c.setSelected(true);
				checkboxSelectedCounterL5T1++;
			}//end if for third checkbox
			if(taUserInputL5T1.getText().endsWith(");"))
			{
				cbL5T1d.setSelected(true);
				checkboxSelectedCounterL5T1++;
			}//end inside if for fourth checkbox
			//when the user has successfully completed the current task and is ready for the next task
			if(checkboxSelectedCounterL5T1==4)
			{
				//setting the next task as visible
				textflowL5T2.setVisible(true);
				textflowL5T2.setManaged(true);
				hbxL5T2Input.setVisible(true);
				hbxL5T2Input.setManaged(true);
				hbxL5T2Output.setVisible(true);
				hbxL5T2Output.setManaged(true);
				hbxL5T2Checklist.setVisible(true);
				hbxL5T2Checklist.setManaged(true);

				//changing the progress value of the progress bar if this is the user's first time doing the current task
				if(user.getProgressLevel()<(progressIncrease*20))
				{
					pbsUserProgress.setProgress((progressIncrease*20));
					user.setProgressLevel(pbsUserProgress.getProgress());
				}//end inside if
			}//end outside if
		});//end setOnAction for btnSubmitL5T1

		//determining what to do when the submit button for L5T2 is clicked
		btnSubmitL5T2.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			int checkboxSelectedCounterL5T2=0;
			trialCounter[4][1]++;

			//creating the array to store user's output and adding user input to the userResponse array
			String [] outputL5T2=new String[2];
			userResponse[4][1][trialCounter[4][1]]=taUserInputL5T2.getText();

			//determining and setting the output that the user will see based on their submission
			try
			{
				outputL5T2=CheckJava.submitL5T2(taUserInputL5T2, TEXTAREA_FONT);
			}
			catch (ScriptException e)
			{
				e.printStackTrace();
			}//end try catch
			taUserOutputL5T2.setText(outputL5T2[0]);
			taUserOutputL5T2.setStyle(outputL5T2[1]);

			//resetting all checkboxes in this task to be deselected
			cbL5T2a.setSelected(false);
			cbL5T2b.setSelected(false);
			cbL5T2c.setSelected(false);

			//if statements to check the user's inputed code and the generated output and make the correct checkboxes for this task selected
			if(!taUserOutputL5T2.getText().contains("Don't forget to initialize the variable num to a double value.") && !taUserOutputL5T2.getText().contains("The number you want to find the square root of must be an integer or decimal value.") && !taUserOutputL5T2.getText().contains("Don't forget to create and initialize a double variable called num on your first line of code."))
			{
				cbL5T2a.setSelected(true);
				checkboxSelectedCounterL5T2++;
			}//end outside if for first checkbox
			if(!taUserOutputL5T2.getText().contains("Please check how you initialize the variable squareRoot/calculate the square root of the variable num.") && !taUserOutputL5T2.getText().contains("Don't forget to create a double variable called squareRoot and initialize it to the square root of the value of num on the second line of code."))
			{
				cbL5T2b.setSelected(true);
				checkboxSelectedCounterL5T2++;
			}//end if for second checkbox
			if(!taUserOutputL5T2.getText().startsWith("Error!"))
			{
				cbL5T2c.setSelected(true);
				checkboxSelectedCounterL5T2++;
			}//end if for third checkbox

			//when the user has successfully completed the current task and is ready for the next task
			if(checkboxSelectedCounterL5T2==3)
			{
				//setting the next task as visible
				textflowL5T3.setVisible(true);
				textflowL5T3.setManaged(true);
				hbxL5T3Input.setVisible(true);
				hbxL5T3Input.setManaged(true);
				hbxL5T3Output.setVisible(true);
				hbxL5T3Output.setManaged(true);
				hbxL5T3Checklist.setVisible(true);
				hbxL5T3Checklist.setManaged(true);

				//changing the progress value of the progress bar if this is the user's first time doing the current task
				if(user.getProgressLevel()<(progressIncrease*21))
				{
					pbsUserProgress.setProgress((progressIncrease*21));
					user.setProgressLevel(pbsUserProgress.getProgress());
				}//end inside if
			}//end outside if
		});//end setOnAction for btnSubmitL5T2

		//determining what to do when the submit button for L5T3 is clicked
		btnSubmitL5T3.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			int checkboxSelectedCounterL5T3=0;
			trialCounter[4][2]++;

			//creating the array to store user's output and adding user input to the userResponse array
			String [] outputL5T3=new String[2];
			userResponse[4][2][trialCounter[4][2]]=taUserInputL5T3.getText();

			//determining and setting the output that the user will see based on their submission
			try
			{
				outputL5T3=CheckJava.submitL5T3(taUserInputL5T3, TEXTAREA_FONT);
			}
			catch (ScriptException e)
			{
				e.printStackTrace();
			}//end try catch
			taUserOutputL5T3.setText(outputL5T3[0]);
			taUserOutputL5T3.setStyle(outputL5T3[1]);

			//resetting all checkboxes in this task to be deselected
			cbL5T3a.setSelected(false);
			cbL5T3b.setSelected(false);
			cbL5T3c.setSelected(false);

			//if statements to check the user's inputed code and the generated output and make the correct checkboxes for this task selected
			if(!taUserOutputL5T3.getText().contains("Don't forget to initialize the variable degrees to a double value.") && !taUserOutputL5T3.getText().contains("The value you have initialized to degrees is not a double value.") && !taUserOutputL5T3.getText().contains("Please check how you initialize the variable radians/calculate the radian converted value of the variable degrees.") && !taUserOutputL5T3.getText().contains("Don't forget to create a double variable called radians and initialize it to the converted value of the variable degrees on the second line of code.") && !taUserOutputL5T3.getText().contains("Please check how you initialize the variable sine/calculate the value of sine with the variable radians.") && !taUserOutputL5T3.getText().contains("Don't forget to create a double variable called sine and initialize it to the sine value of the variable radians using the Math class on your third line of code.") && !taUserOutputL5T3.getText().contains("Please check how you initialize the variable cosine/calculate the value of cosine with the variable radians.") && !taUserOutputL5T3.getText().contains("Don't forget to create a double variable called cosine and initialize it to the cosine value of the variable radians using the Math class on your fourth line of code.") && !taUserOutputL5T3.getText().contains("Please check how you initialize the variable tangent/calculate the value of tangent with the variable radians.") && !taUserOutputL5T3.getText().contains("Don't forget to create a double variable called tangent and initialize it to the tangent value of the variable radians using the Math class on your fifth line of code.Since you did not correctly initialize the variable radians, the values of sine, cosine, and tangent were not able to be initialized and nothing was able to be printed to the screen.") && !taUserOutputL5T3.getText().contains("Since you did not correctly initialize the variable radians, the values of sine, cosine, and tangent were not able to be initialized and nothing was able to be printed to the screen.") && !taUserOutputL5T3.getText().contains("Don't forget to create and initialize a double variable called degrees on your first line of code."))
			{
				cbL5T3a.setSelected(true);
				cbL5T3b.setSelected(true);
				checkboxSelectedCounterL5T3+=2;
			}//end if for first and second checkbox
			if(!taUserOutputL5T3.getText().startsWith("Error!"))
			{
				cbL5T3c.setSelected(true);
				checkboxSelectedCounterL5T3++;
			}//end if for third checkbox

			//when the user has successfully completed the current task and is ready for the next task
			if(checkboxSelectedCounterL5T3==3)
			{
				//setting the next task as visible
				textflowL5T4.setVisible(true);
				textflowL5T4.setManaged(true);
				hbxL5T4Input.setVisible(true);
				hbxL5T4Input.setManaged(true);
				hbxL5T4Output.setVisible(true);
				hbxL5T4Output.setManaged(true);
				hbxL5T4Checklist.setVisible(true);
				hbxL5T4Checklist.setManaged(true);

				//changing the progress value of the progress bar if this is the user's first time doing the current task
				if(user.getProgressLevel()<(progressIncrease*22))
				{
					pbsUserProgress.setProgress((progressIncrease*22));
					user.setProgressLevel(pbsUserProgress.getProgress());
				}//end inside if
			}//end outside if
		});//end setOnAction for btnSubmitL5T3

		//determining what to do when the submit button for L5T4 is clicked
		btnSubmitL5T4.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			int checkboxSelectedCounterL5T4=0;
			trialCounter[4][3]++;

			//creating the array to store user's output and adding user input to the userResponse array
			String [] outputL5T4=new String[2];
			userResponse[4][3][trialCounter[4][3]]=taUserInputL5T4.getText();

			//determining and setting the output that the user will see based on their submission
			try
			{
				outputL5T4=CheckJava.submitL5T4(taUserInputL5T4, TEXTAREA_FONT);
			}
			catch (ScriptException e)
			{
				e.printStackTrace();
			}//end try catch
			taUserOutputL5T4.setText(outputL5T4[0]);
			taUserOutputL5T4.setStyle(outputL5T4[1]);

			//resetting all checkboxes in this task to be deselected
			cbL5T4a.setSelected(false);
			cbL5T4b.setSelected(false);
			cbL5T4c.setSelected(false);

			//if statements to check the user's inputed code and the generated output and make the correct checkboxes for this task selected
			if(!taUserOutputL5T4.getText().contains("integer") && !taUserOutputL5T4.getText().contains("Int"))
			{
				cbL5T4a.setSelected(true);
				checkboxSelectedCounterL5T4++;
			}//end if for first checkbox
			if(!taUserOutputL5T4.getText().contains("double"))
			{
				cbL5T4b.setSelected(true);
				checkboxSelectedCounterL5T4++;
			}//end if for second checkbox
			if(!taUserOutputL5T4.getText().startsWith("Error!"))
			{
				cbL5T4c.setSelected(true);
				checkboxSelectedCounterL5T4++;
			}//end if for third checkbox

			//when the user has successfully completed the current task and is ready for the next task
			if(checkboxSelectedCounterL5T4==3)
			{
				//setting the next task as visible
				textflowL5T5.setVisible(true);
				textflowL5T5.setManaged(true);
				hbxL5T5Input.setVisible(true);
				hbxL5T5Input.setManaged(true);
				hbxL5T5Output.setVisible(true);
				hbxL5T5Output.setManaged(true);
				hbxL5T5Checklist.setVisible(true);
				hbxL5T5Checklist.setManaged(true);

				//changing the progress value of the progress bar if this is the user's first time doing the current task
				if(user.getProgressLevel()<(progressIncrease*23))
				{
					pbsUserProgress.setProgress((progressIncrease*23));
					user.setProgressLevel(pbsUserProgress.getProgress());
				}//end inside if
			}//end outside if			
		});//end setOnAction for btnSubmitL5T4

		//determining what to do when the submit button for L5T5 is clicked
		btnSubmitL5T5.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			int checkboxSelectedCounterL5T5=0;
			trialCounter[4][4]++;

			//creating the array to store user's output and adding user input to the userResponse array
			String [] outputL5T5=new String[2];
			userResponse[4][4][trialCounter[4][4]]=taUserInputL5T5.getText();

			//determining and setting the output that the user will see based on their submission
			try
			{
				outputL5T5=CheckJava.submitL5T5(taUserInputL5T5, TEXTAREA_FONT);
			}
			catch (ScriptException e)
			{
			}//end try catch
			taUserOutputL5T5.setText(outputL5T5[0]);
			taUserOutputL5T5.setStyle(outputL5T5[1]);

			//resetting all checkboxes in this task to be deselected
			cbL5T5a.setSelected(false);
			cbL5T5b.setSelected(false);
			cbL5T5c.setSelected(false);
			cbL5T5d.setSelected(false);

			//if statements to check the user's inputed code and the generated output and make the correct checkboxes for this task selected
			if(taUserInputL5T5.getText().startsWith("Random ") && !taUserOutputL5T5.getText().startsWith("Error!"))
			{
				cbL5T5a.setSelected(true);
				checkboxSelectedCounterL5T5++;
			}//end if for first checkbox
			if(!taUserOutputL5T5.getText().contains("integer") && !taUserOutputL5T5.getText().contains("Int"))
			{
				cbL5T5b.setSelected(true);
				checkboxSelectedCounterL5T5++;
			}//end if for second checkbox
			if(!taUserOutputL5T5.getText().contains("double"))
			{
				cbL5T5c.setSelected(true);
				checkboxSelectedCounterL5T5++;
			}//end if for third checkbox
			if(!taUserOutputL5T5.getText().startsWith("Error!"))
			{
				cbL5T5d.setSelected(true);
				checkboxSelectedCounterL5T5++;
			}//end if for fourth checkbox

			//when the user has successfully completed the current task and is ready for the next task
			if(checkboxSelectedCounterL5T5==3)
			{
				//setting the lesson 5 completed label as visible
				lblL5Done.setVisible(true);
				lblL5Done.setManaged(true);

				//changing the progress value of the progress bar if this is the user's first time doing the current task
				if(user.getProgressLevel()<(progressIncrease*24))
				{
					pbsUserProgress.setProgress((progressIncrease*24));
					user.setProgressLevel(pbsUserProgress.getProgress());
				}//end inside if
			}//end outside if
		});//end setOnAction for btnSubmitL5T5

		//creating and setting the scroll pane for this lesson
		ScrollPane spL5 = new ScrollPane();
		spL5.setContent(vbxL5);

		//creating the scene
		Scene sceneL5 = new Scene(spL5);

		lastScene="lesson5"; //setting the previous scene to this scene

		//setting/showing the stage
		myStage.getIcons().add(new Image("FromOneToAnotherLogo.png"));
		myStage.setTitle("From C++ to Java - ... - Lesson #5");
		myStage.setScene(sceneL5);
		myStage.centerOnScreen();
		myStage.show();
	}//end lesson5 method


	/* Method Author: Brooke Cronin
	 * Method Name: lesson6
	 * Description: Creates the scene for the fifth lesson. Allows the user to view and attempt all of the tasks and get feedback after completing each task.
	 * Parameters: Stage myStage
	 * Returns: N/A
	 * Throws: NullPointerException
	 */

	public void lesson6(Stage myStage) throws NullPointerException
	{
		myStage.setOnHiding( event -> {try {
			exitProgram();
		} catch (IOException e) {
			
			e.printStackTrace();
		}} );
		//resetting the text in all of the user output text areas and disabling them so that the user can't change what appears in them
		TextArea taUserOutputL6T1 = new TextArea();
		taUserOutputL6T1.setText("Not submitted yet");
		taUserOutputL6T1.setEditable(false);
		taUserOutputL6T1.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
		TextArea taUserOutputL6T2 = new TextArea();
		taUserOutputL6T2.setText("Not submitted yet");
		taUserOutputL6T2.setEditable(false);
		taUserOutputL6T2.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
		TextArea taUserOutputL6T3 = new TextArea();
		taUserOutputL6T3.setText("Not submitted yet");
		taUserOutputL6T3.setEditable(false);
		taUserOutputL6T3.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
		TextArea taUserOutputL6T4a = new TextArea();
		taUserOutputL6T4a.setText("Not submitted yet");
		taUserOutputL6T4a.setEditable(false);
		taUserOutputL6T4a.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");

		//creating the vertical box for lesson 6 screen
		VBox vbxL6 = new VBox(GAP);
		vbxL6.setPadding(new Insets(GAP, GAP, GAP, GAP));

		//adding the user's progress bar to the vertical box
		pbsUserProgress.setMaxWidth(Double.MAX_VALUE);
		vbxL6.getChildren().add(pbsUserProgress);

		//creating, setting, and adding a header Label
		AnchorPane apLesson6TitleHeader = new AnchorPane();
		Label lblLesson6TitleHeader = new Label("Lesson #6: Gathering Input From The User");
		lblLesson6TitleHeader.setStyle("-fx-text-fill: black; -fx-font-size: "+LESSON_HEADER_FONT);
		lblLesson6TitleHeader.setMaxWidth(Double.MAX_VALUE);
		AnchorPane.setLeftAnchor(lblLesson6TitleHeader, 0.0);
		AnchorPane.setRightAnchor(lblLesson6TitleHeader, 0.0);
		lblLesson6TitleHeader.setAlignment(Pos.CENTER);
		apLesson6TitleHeader.getChildren().add(lblLesson6TitleHeader);
		vbxL6.getChildren().add(apLesson6TitleHeader);

		//adding the name of the current task
		Label lblL6T1Header = new Label("\nTask #1: Integer Input");
		lblL6T1Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		vbxL6.getChildren().add(lblL6T1Header);

		//creating, setting, and adding the first task instructions
		Text textL6T1a = new Text("\n\nBy now, you should be familiar with printing text and variables to the screen, as well as initializing and updating different types of variables. Now, in this lesson, you will be learning how to gather information from the user and store it in a variable.\nTo ask the user to input a whole number and assign it to the value of ");
		textL6T1a.setFill(Color.BLACK);
		Text textL6T1b = new Text("q");
		textL6T1b.setFill(Color.rgb(121, 76, 52));
		Text textL6T1c = new Text(" in Python, the code would lok similar to below.");
		textL6T1c.setFill(Color.BLACK);
		Text textL6T1d = new Text("\nq");
		textL6T1d.setFill(Color.rgb(121, 76, 52));
		Text textL6T1e = new Text("=input(\"Please enter a whole number.\"");
		textL6T1e.setFill(Color.DARKVIOLET);
		Text textL6T1f = new Text("\nThe Java equivalence is a little bit more complicated. Firstly, you will need an import (the same as for the Random class) as well as an object decleration (similar to the object decleration from the Random class), however this deceration is a bit different.");
		textL6T1f.setFill(Color.BLACK);
		Text textL6T1g = new Text("\nScanner myInput = new Scanner(System.in);");
		textL6T1g.setFill(Color.BLUE);
		Text textL6T1h = new Text("\nAgain, you can change myInput to whatever makes sense for your code, but make sure it is consistant). In addition, at the end of the method, you must close the scanner with the following line of code.");
		textL6T1h.setFill(Color.BLACK);
		Text textL6T1i = new Text("\nmyInput.close();");
		textL6T1i.setFill(Color.BLUE);
		Text textL6T1j = new Text("\nBelow is the Java equivalence of what was shown in Python above for the main method.");
		textL6T1j.setFill(Color.BLACK);
		Text textL6T1k = new Text("\nScanner myInput = new Scanner(System.in);\n"
				+ "int q;\n"
				+ "System.out.println(\"Please enter a whole number.\");\n"
				+ "q=myInput.nextInt();\n"
				+ "myInput.close();");
		textL6T1k.setFill(Color.BLUE);
		Text textL6T1l = new Text("\nIn this task, you will be asking the user what their favourite number is, store it in the in the integer variable called ");
		textL6T1l.setFill(Color.BLACK);
		Text textL6T1m = new Text("favNum");
		textL6T1m.setFill(Color.rgb(121, 76, 52));
		Text textL6T1n = new Text(" and print the value of ");
		textL6T1n.setFill(Color.BLACK);
		Text textL6T1o = new Text("favNum");
		textL6T1o.setFill(Color.rgb(121, 76, 52));
		Text textL6T1p = new Text(" to the screen ");
		textL6T1p.setFill(Color.BLACK);
		TextFlow textflowL6T1 = new TextFlow(textL6T1a, textL6T1b, textL6T1c, textL6T1d, textL6T1e, textL6T1f, textL6T1g, textL6T1h, textL6T1i, textL6T1j, textL6T1k, textL6T1l, textL6T1m, textL6T1n, textL6T1o, textL6T1p);
		textflowL6T1.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		vbxL6.getChildren().add(textflowL6T1);

		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput1 = new Label("Your code: ");
		lblInput1.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL6T1 = new TextArea();
		taUserInputL6T1.setPrefSize(taWidth, taLineHeight*7);
		taUserInputL6T1.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL6T1.setText("public class L6T1Response\n{\n\tpublic static void main()\n\t{\n\n\t}//end main method\n}//end L6T1Response class");
		Button btnSubmitL6T1 = new Button("Submit");
		btnSubmitL6T1.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL6T1Input = new HBox(GAP, lblInput1, taUserInputL6T1, btnSubmitL6T1);
		vbxL6.getChildren().add(hbxL6T1Input);
		//for user's output
		Label lblOutput1 = new Label("Output:     ");
		lblOutput1.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL6T1.setPrefSize(taWidth, taLineHeight);
		HBox hbxL6T1Output = new HBox(GAP, lblOutput1, taUserOutputL6T1);
		TextField txtRunInputL6T1 = new TextField("");
		Label lblIndentT1a = new Label("\t\t\t    ");
		HBox hbxRunInputL6T1 = new HBox(GAP, lblIndentT1a, txtRunInputL6T1);
		txtRunInputL6T1.setVisible(false);
		txtRunInputL6T1.setPrefSize(taWidth, taLineHeight);
		txtRunInputL6T1.setStyle("-fx-text-fill: green; -fx-font-size: "+TEXTAREA_FONT);
		TextField txtRunInputL6T1Value = new TextField("");
		txtRunInputL6T1Value.setPrefSize(taWidth, taLineHeight);
		if(txtRunInputL6T1Value.getText().equals(""))
		{
			txtRunInputL6T1Value.setVisible(false);
		}
		Label lblIndentT1b = new Label("\t\t\t    ");
		HBox hbxRunInputL6T1Value = new HBox(GAP, lblIndentT1b, txtRunInputL6T1Value);
		VBox vbxL6T1Output = new VBox(0);
		vbxL6T1Output.setPadding(new Insets(GAP, GAP, GAP, GAP));
		vbxL6T1Output.getChildren().addAll(hbxL6T1Output, hbxRunInputL6T1, hbxRunInputL6T1Value);
		vbxL6.getChildren().add(vbxL6T1Output);
		//for user's result
		Label lblCriteria1 = new Label("Criteria");
		CheckBox cbL6T1a = new CheckBox("Scanner object was created and used to gather input from user");
		cbL6T1a.setDisable(true);
		CheckBox cbL6T1b = new CheckBox("favNum was created as an integer and not initialized");
		cbL6T1b.setDisable(true);
		CheckBox cbL6T1c = new CheckBox("A question/statement was printed to the screen to tell user what to input");
		cbL6T1c.setDisable(true);
		CheckBox cbL6T1d = new CheckBox("User entered an integer value");
		cbL6T1d.setDisable(true);
		CheckBox cbL6T1e = new CheckBox("The value of favNum was printed to the screen");
		cbL6T1e.setDisable(true);
		CheckBox cbL6T1f = new CheckBox("The scanner object was closed");
		cbL6T1f.setDisable(true);
		HBox hbxL6T1Checklist = new HBox(GAP, lblCriteria1, cbL6T1a, cbL6T1b, cbL6T1c, cbL6T1d, cbL6T1e, cbL6T1f);
		hbxL6T1Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria1.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		vbxL6.getChildren().add(hbxL6T1Checklist);

		//adding the name of the current task
		Label lblL6T2Header = new Label("\n\n\nTask #2: Double Input");
		lblL6T2Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		if(user.getProgressLevel()<(progressIncrease*25))
		{
			lblL6T2Header.setVisible(false);
			lblL6T2Header.setManaged(false);
		}//end if
		vbxL6.getChildren().add(lblL6T2Header);

		//creating, setting, and adding the second task instructions
		Text textL6T2a = new Text("\n\nWhen you want the user to input a different type of value other than an integer, almost everything is the same (import, object decleration, and needing to close the scanner), however, you need to use a different line of code than ");
		textL6T2a.setFill(Color.BLACK);
		Text textL6T2b = new Text("myInput.nextInt();");
		textL6T2b.setFill(Color.BLUE);
		Text textL6T2c = new Text(".\nFor this task, you will be creating a double variable called ");
		textL6T2c.setFill(Color.BLACK);
		Text textL6T2d = new Text("myAge");
		textL6T2d.setFill(Color.rgb(121, 76, 52));
		Text textL6T2e = new Text("\nand asking the user to enter their age.\nTo gather a double value, you must use ");
		textL6T2e.setFill(Color.BLACK);
		Text textL6T2f = new Text("myInput.nextDouble();");
		textL6T2f.setFill(Color.BLUE);
		Text textL6T2g = new Text(" instead of ");
		textL6T2g.setFill(Color.BLACK);
		Text textL6T2h = new Text("myInput.nextInt();");
		textL6T2h.setFill(Color.BLUE);
		Text textL6T2i = new Text(".");
		textL6T2i.setFill(Color.BLACK);
		TextFlow textflowL6T2 = new TextFlow(textL6T2a, textL6T2b, textL6T2c, textL6T2d, textL6T2e, textL6T2f, textL6T2g, textL6T2h, textL6T2i);
		textflowL6T2.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*25))
		{
			textflowL6T2.setVisible(false);
			textflowL6T2.setManaged(false);
		}//end if
		vbxL6.getChildren().add(textflowL6T2);

		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput2 = new Label("Your code: ");
		lblInput2.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL6T2 = new TextArea();
		taUserInputL6T2.setPrefSize(taWidth, taLineHeight*7);
		taUserInputL6T2.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL6T2.setText("public class L6T2Response\n{\n\tpublic static void main()\n\t{\n\n\t}//end main method\n}//end L6T2Response class");
		Button btnSubmitL6T2 = new Button("Submit");
		btnSubmitL6T2.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL6T2Input = new HBox(GAP, lblInput2, taUserInputL6T2, btnSubmitL6T2);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*25))
		{
			hbxL6T2Input.setVisible(false);
			hbxL6T2Input.setManaged(false);
		}//end if
		vbxL6.getChildren().add(hbxL6T2Input);
		//for user's output
		Label lblOutput2 = new Label("Output:     ");
		lblOutput2.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL6T2.setPrefSize(taWidth, taLineHeight);
		HBox hbxL6T2Output = new HBox(GAP, lblOutput2, taUserOutputL6T2);
		TextField txtRunInputL6T2 = new TextField("");
		Label lblIndentT2a = new Label("\t\t\t    ");
		txtRunInputL6T2.setVisible(false);
		txtRunInputL6T2.setPrefSize(taWidth, taLineHeight);
		txtRunInputL6T2.setStyle("-fx-text-fill: green; -fx-font-size: "+TEXTAREA_FONT);
		HBox hbxRunInputL6T2 = new HBox(GAP, lblIndentT2a, txtRunInputL6T2);
		TextField txtRunInputL6T2Value = new TextField("");
		txtRunInputL6T2Value.setPrefSize(taWidth, taLineHeight);
		if(txtRunInputL6T2Value.getText().equals(""))
		{
			txtRunInputL6T2Value.setVisible(false);
		}//end if
		Label lblIndentT2b = new Label("\t\t\t    ");
		HBox hbxRunInputL6T2Value = new HBox(GAP, lblIndentT2b, txtRunInputL6T2Value);
		VBox vbxL6T2Output = new VBox(0);
		vbxL6T2Output.setPadding(new Insets(GAP, GAP, GAP, GAP));
		vbxL6T2Output.getChildren().addAll(hbxL6T2Output, hbxRunInputL6T2, hbxRunInputL6T2Value);
		vbxL6.getChildren().add(vbxL6T2Output);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*25))
		{
			vbxL6T2Output.setVisible(false);
			vbxL6T2Output.setManaged(false);
		}//end if
		//for user's result
		Label lblCriteria2 = new Label("Criteria");
		CheckBox cbL6T2a = new CheckBox("Scanner object was created and used to gather input from user");
		cbL6T2a.setDisable(true);
		CheckBox cbL6T2b = new CheckBox("myAge was created as a double and not initialized");
		cbL6T2b.setDisable(true);
		CheckBox cbL6T2c = new CheckBox("A question/statement was printed to the screen to tell user what to input");
		cbL6T2c.setDisable(true);
		CheckBox cbL6T2d = new CheckBox("User entered a double value");
		cbL6T2d.setDisable(true);
		CheckBox cbL6T2e = new CheckBox("The value of myAge was printed to the screen");
		cbL6T2e.setDisable(true);
		CheckBox cbL6T2f = new CheckBox("The scanner object was closed");
		cbL6T2f.setDisable(true);
		HBox hbxL6T2Checklist = new HBox(GAP, lblCriteria2, cbL6T2a, cbL6T2b, cbL6T2c, cbL6T2d, cbL6T2e, cbL6T2f);
		hbxL6T2Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria2.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*25))
		{
			hbxL6T2Checklist.setVisible(false);
			hbxL6T2Checklist.setManaged(false);
		}//end if
		vbxL6.getChildren().add(hbxL6T2Checklist);

		//adding the name of the current task
		Label lblL6T3Header = new Label("\n\n\nTask #3: String Input");
		lblL6T3Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		if(user.getProgressLevel()<(progressIncrease*26))
		{
			lblL6T3Header.setVisible(false);
			lblL6T3Header.setManaged(false);
		}//end if
		vbxL6.getChildren().add(lblL6T3Header);

		//creating, setting, and adding the third task instructions
		Text textL6T3a = new Text("\n\nWhat about String values? Again, the only difference is that for strings, you must use ");
		textL6T3a.setFill(Color.BLACK);
		Text textL6T3b = new Text("myInput.nextLine();");
		textL6T3b.setFill(Color.BLUE);
		Text textL6T3c = new Text(" instead of ");
		textL6T3c.setFill(Color.BLACK);
		Text textL6T3d = new Text("myInput.nextInt();");
		textL6T3d.setFill(Color.BLUE);
		Text textL6T3e = new Text(" for integers and ");
		textL6T3e.setFill(Color.BLACK);
		Text textL6T3f = new Text("myInput.nextDouble();");
		textL6T3f.setFill(Color.BLUE);
		Text textL6T3g = new Text(" for doubles.\nIn this task, you will be asking the user to enter their name, and assigning their input to the value of ");
		textL6T3g.setFill(Color.BLACK);
		Text textL6T3h = new Text("myFirstName");
		textL6T3h.setFill(Color.rgb(121, 76, 52));
		Text textL6T3i = new Text(".");
		textL6T3i.setFill(Color.BLACK);
		TextFlow textflowL6T3 = new TextFlow(textL6T3a, textL6T3b, textL6T3c, textL6T3d, textL6T3e, textL6T3f, textL6T3g, textL6T3h, textL6T3i);
		textflowL6T3.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*26))
		{
			textflowL6T3.setVisible(false);
			textflowL6T3.setManaged(false);
		}//end if
		vbxL6.getChildren().add(textflowL6T3);

		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput3 = new Label("Your code: ");
		lblInput3.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL6T3 = new TextArea();
		taUserInputL6T3.setPrefSize(taWidth, taLineHeight*7);
		taUserInputL6T3.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL6T3.setText("public class L6T3Response\n{\n\tpublic static void main()\n\t{\n\n\t}//end main method\n}//end L6T3Response class");
		Button btnSubmitL6T3 = new Button("Submit");
		btnSubmitL6T3.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL6T3Input = new HBox(GAP, lblInput3, taUserInputL6T3, btnSubmitL6T3);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*26))
		{
			hbxL6T3Input.setVisible(false);
			hbxL6T3Input.setManaged(false);
		}//end if
		vbxL6.getChildren().add(hbxL6T3Input);
		//for user's output
		Label lblOutput3 = new Label("Output:     ");
		lblOutput3.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL6T3.setPrefSize(taWidth, taLineHeight);
		HBox hbxL6T3Output = new HBox(GAP, lblOutput3, taUserOutputL6T3);
		TextField txtRunInputL6T3 = new TextField("");
		txtRunInputL6T3.setVisible(false);
		txtRunInputL6T3.setPrefSize(taWidth, taLineHeight);
		txtRunInputL6T3.setStyle("-fx-text-fill: green; -fx-font-size: "+TEXTAREA_FONT);
		Label lblIndentT3a = new Label("\t\t\t    ");
		HBox hbxRunInputL6T3 = new HBox(GAP, lblIndentT3a, txtRunInputL6T3);
		TextField txtRunInputL6T3Value = new TextField("");
		txtRunInputL6T3Value.setPrefSize(taWidth, taLineHeight);
		if(txtRunInputL6T3Value.getText().equals(""))
		{
			txtRunInputL6T3Value.setVisible(false);
		}//end if
		Label lblIndentT3b = new Label("\t\t\t    ");
		HBox hbxRunInputL6T3Value = new HBox(GAP, lblIndentT3b, txtRunInputL6T3Value);
		VBox vbxL6T3Output = new VBox(0);
		vbxL6T3Output.setPadding(new Insets(GAP, GAP, GAP, GAP));
		vbxL6T3Output.getChildren().addAll(hbxL6T3Output, hbxRunInputL6T3, hbxRunInputL6T3Value);
		vbxL6.getChildren().add(vbxL6T3Output);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*26))
		{
			vbxL6T3Output.setVisible(false);
			vbxL6T3Output.setManaged(false);
		}//end if
		//for user's result
		Label lblCriteria3 = new Label("Criteria");
		CheckBox cbL6T3a = new CheckBox("Scanner object was created and used to gather input from user");
		cbL6T3a.setDisable(true);
		CheckBox cbL6T3b = new CheckBox("myFirstName was created as a string and not initialized");
		cbL6T3b.setDisable(true);
		CheckBox cbL6T3c = new CheckBox("A question/statement was printed to the screen to tell user what to input");
		cbL6T3c.setDisable(true);
		CheckBox cbL6T3d = new CheckBox("The value of myFirstName was printed to the screen");
		cbL6T3d.setDisable(true);
		CheckBox cbL6T3e = new CheckBox("The scanner object was closed");
		cbL6T3e.setDisable(true);
		HBox hbxL6T3Checklist = new HBox(GAP, lblCriteria3, cbL6T3a, cbL6T3b, cbL6T3c, cbL6T3d, cbL6T3e);
		hbxL6T3Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria3.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*26))
		{
			hbxL6T3Checklist.setVisible(false);
			hbxL6T3Checklist.setManaged(false);
		}//end if
		vbxL6.getChildren().add(hbxL6T3Checklist);

		//adding the name of the current task
		Label lblL6T4Header = new Label("\n\n\nTask #4: Integer followed by a String Input");
		lblL6T4Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		if(user.getProgressLevel()<(progressIncrease*27))
		{
			lblL6T4Header.setVisible(false);
			lblL6T4Header.setManaged(false);
		}//end if
		vbxL6.getChildren().add(lblL6T4Header);

		//creating, setting, and adding the fourth task instructions
		Text textL6T4a = new Text("\n\nSo far, when gathering input, you have only done it for when there is only one type of input (integer, double, string). However, you do have to do something a little bit different when you want to gather an integer (or double) value followed by a string value.\nIn this case, after gathering the numerical value (integer or double), you must add a ");
		textL6T4a.setFill(Color.BLACK);
		Text textL6T4b = new Text("myInput.nextLine();");
		textL6T4b.setFill(Color.BLUE);
		Text textL6T4c = new Text(" before asking the user to enter a string value.\nThis is because when the program is running and the user has entered a number, the ");
		textL6T4c.setFill(Color.BLACK);
		Text textL6T4d = new Text("myInput.nextInt();");
		textL6T4d.setFill(Color.BLUE);
		Text textL6T4e = new Text(" (or ");
		textL6T4e.setFill(Color.BLACK);
		Text textL6T4f = new Text("myInput.nextDouble();");
		textL6T4f.setFill(Color.BLUE);
		Text textL6T4g = new Text(") only excepts the number and not the enter key, so when the enter key is pressed, the");
		textL6T4g.setFill(Color.BLACK);
		Text textL6T4h = new Text("myInput.nextLine();");
		textL6T4h.setFill(Color.BLUE);
		Text textL6T4i = new Text(" gets used up,\nwhich is why you need to add the extra ");
		textL6T4i.setFill(Color.BLACK);
		Text textL6T4j = new Text("myInput.nextLine();");
		textL6T4j.setFill(Color.BLUE);
		Text textL6T4k = new Text(" in between (you do not need to add this extra line of code when the string variable is entered first).\nIn this task, you will create an integer variable called ");
		textL6T4k.setFill(Color.BLACK);
		Text textL6T4l = new Text("numOfFamilyMembers");
		textL6T4l.setFill(Color.rgb(121, 76, 52));
		Text textL6T4m = new Text(" and a string variable called ");
		textL6T4m.setFill(Color.BLACK);
		Text textL6T4n = new Text("favFood");
		textL6T4n.setFill(Color.rgb(121, 76, 52));
		Text textL6T4o = new Text(", and ask the user to enter values for both of these variables.");
		textL6T4o.setFill(Color.BLACK);
		TextFlow textflowL6T4 = new TextFlow(textL6T4a, textL6T4b, textL6T4c, textL6T4d, textL6T4e, textL6T4f, textL6T4g, textL6T4h, textL6T4i, textL6T4j, textL6T4k, textL6T4l, textL6T4m, textL6T4n, textL6T4o);
		textflowL6T4.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*27))
		{
			textflowL6T4.setVisible(false);
			textflowL6T4.setManaged(false);
		}//end if
		vbxL6.getChildren().add(textflowL6T4);

		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput4 = new Label("Your code: ");
		lblInput4.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL6T4 = new TextArea();
		taUserInputL6T4.setPrefSize(taWidth, taLineHeight*10);
		taUserInputL6T4.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL6T4.setText("public class L6T4Response\n{\n\tpublic static void main()\n\t{\n\n\t}//end main method\n}//end L6T4Response class");
		Button btnSubmitL6T4 = new Button("Submit");
		btnSubmitL6T4.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL6T4Input = new HBox(GAP, lblInput4, taUserInputL6T4, btnSubmitL6T4);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*27))
		{
			hbxL6T4Input.setVisible(false);
			hbxL6T4Input.setManaged(false);
		}//end if
		vbxL6.getChildren().add(hbxL6T4Input);
		//for user's output
		Label lblOutput4 = new Label("Output:     ");
		lblOutput4.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL6T4a.setPrefSize(taWidth, taLineHeight);
		HBox hbxL6T4Output = new HBox(GAP, lblOutput4, taUserOutputL6T4a);
		TextField txtRunInputL6T4a = new TextField("");
		txtRunInputL6T4a.setVisible(false);
		txtRunInputL6T4a.setPrefSize(taWidth, taLineHeight);
		txtRunInputL6T4a.setStyle("-fx-text-fill: green; -fx-font-size: "+TEXTAREA_FONT);
		Label lblIndentT4a = new Label("\t\t\t    ");
		HBox hbxRunInputL6T4a = new HBox(GAP, lblIndentT4a, txtRunInputL6T4a);
		TextArea taUserOutputL6T4b = new TextArea("");
		taUserOutputL6T4b.setPrefSize(taWidth, taLineHeight);
		taUserOutputL6T4b.setVisible(false);
		Label lblIndentT4b = new Label("\t\t\t    ");
		HBox hbxUserOutputL6T4b = new HBox(GAP, lblIndentT4b, taUserOutputL6T4b);
		TextField txtRunInputL6T4b = new TextField("");
		txtRunInputL6T4b.setVisible(false);
		txtRunInputL6T4b.setPrefSize(taWidth, taLineHeight);
		txtRunInputL6T4b.setStyle("-fx-text-fill: green; -fx-font-size: "+TEXTAREA_FONT);
		Label lblIndentT4c = new Label("\t\t\t    ");
		HBox hbxRunInputL6T4b = new HBox(GAP, lblIndentT4c, txtRunInputL6T4b);
		VBox vbxL6T4Output = new VBox(0);
		vbxL6T4Output.setPadding(new Insets(GAP, GAP, GAP, GAP));
		vbxL6T4Output.getChildren().addAll(hbxL6T4Output, hbxRunInputL6T4a, hbxUserOutputL6T4b, hbxRunInputL6T4b);
		vbxL6.getChildren().add(vbxL6T4Output);

		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*27))
		{
			vbxL6T4Output.setVisible(false);
			vbxL6T4Output.setManaged(false);
		}//end if
		//for user's result
		Label lblCriteria4 = new Label("Criteria");
		CheckBox cbL6T4a = new CheckBox("Scanner object was created and used to gather input from user");
		cbL6T4a.setDisable(true);
		CheckBox cbL6T4b = new CheckBox("numOfFamilyMembers was created as an integer and not initialized");
		cbL6T4b.setDisable(true);
		CheckBox cbL6T4c = new CheckBox("favFood was created as a string and not initialized");
		cbL6T4c.setDisable(true);
		CheckBox cbL6T4d = new CheckBox("A question/statement was printed to the screen to tell user what to input for the integer");
		cbL6T4d.setDisable(true);
		CheckBox cbL6T4e = new CheckBox("A question/statement was printed to the screen to tell user what to input for the string");
		cbL6T4e.setDisable(true);
		CheckBox cbL6T4f = new CheckBox("Both input values were accepted");
		cbL6T4f.setDisable(true);
		CheckBox cbL6T4g = new CheckBox("The scanner object was closed");
		cbL6T4g.setDisable(true);
		HBox hbxL6T4Checklist = new HBox(GAP, lblCriteria4, cbL6T4a, cbL6T4b, cbL6T4c, cbL6T4d, cbL6T4e, cbL6T4f, cbL6T4g);
		hbxL6T4Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria4.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*27))
		{
			hbxL6T4Checklist.setVisible(false);
			hbxL6T4Checklist.setManaged(false);
		}//end if
		vbxL6.getChildren().add(hbxL6T4Checklist);

		//creating and adding a label to notify user that they have completed the current lesson
		Label lblL6Done = new Label("Congratulations, "+user.getfName()+"! You have finished Lesson #6: Gathering Input From The User.\n" + 
				"Click one of the buttons below to either restart this lesson, choose another lesson from the menu, go to the previous lesson, or continue with the next lesson.");
		lblL6Done.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*28))
		{
			lblL6Done.setVisible(false);
			lblL6Done.setManaged(false);
		}//end if
		vbxL6.getChildren().add(lblL6Done);

		//creating and adding the buttons at the bottom of the screen
		Button btnPreviousLesson = new Button("Previous Lesson");
		btnPreviousLesson.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnPreviousLesson.setOnAction(event -> lesson5(myStage));
		Button btnLessonMenu = new Button("Lesson Menu");
		btnLessonMenu.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnLessonMenu.setOnAction(event -> lessonMenu(myStage));
		Button btnExit = new Button("Exit");
		btnExit.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnExit.setOnAction(event -> 
		{
			try
			{
				exitProgram();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		});
		Button btnRestartL6 = new Button("Restart Lesson");
		btnRestartL6.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnRestartL6.setOnAction(event -> lesson6(myStage));
		Button btnNextLesson = new Button("Next Lesson");
		btnNextLesson.setStyle("-fx-font-size: "+CONTROL_FONT);
		if(user.getProgressLevel()<(progressIncrease*28))
		{
			btnNextLesson.setDisable(true);
		}//end if
		btnNextLesson.setOnAction(event -> lesson7(myStage));
		HBox hbxL6Controls = new HBox(GAP, btnPreviousLesson, btnLessonMenu, btnExit, btnRestartL6, btnNextLesson);
		vbxL6.getChildren().add(hbxL6Controls);	

		//determining what to do when the submit button for L6T1 is clicked
		btnSubmitL6T1.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			int checkboxSelectedCounterL6T1=0;
			trialCounter[5][0]++;

			//creating the array to store user's output and adding user input to the userResponse array
			String [] outputL6T1=new String[3];
			userResponse[5][0][trialCounter[5][0]]=taUserInputL6T1.getText();
			String [] runInputL6T1 = new String [2];
			txtRunInputL6T1.setText("");
			txtRunInputL6T1.setEditable(true);
			txtRunInputL6T1Value.setText("");
			txtRunInputL6T1Value.setVisible(false);
			int iRunInputL6T1=0;


			//determining and setting the output that the user will see based on their submission
			try
			{
				outputL6T1=CheckJava.submitL6T1(taUserInputL6T1, TEXTAREA_FONT);
			}
			catch (ScriptException e)
			{
				e.printStackTrace();
			}//end try catch
			taUserOutputL6T1.setText(outputL6T1[0]);
			taUserOutputL6T1.setStyle(outputL6T1[1]);

			//resetting all checkboxes in this task to be deselected
			cbL6T1a.setSelected(false);
			cbL6T1b.setSelected(false);
			cbL6T1c.setSelected(false);
			cbL6T1d.setSelected(false);
			cbL6T1e.setSelected(false);
			cbL6T1f.setSelected(false);

			if(!taUserOutputL6T1.getText().startsWith("Error!"))
			{
				txtRunInputL6T1.setVisible(true);

				txtRunInputL6T1.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event)
					{
						boolean isIntL6T1=ErrorChecks.checkIfInt(txtRunInputL6T1.getText());

						if(!taUserOutputL6T1.getText().contains("Don't forget to create the object from the Scanner class in order to gather input from the user for this task."))
						{
							cbL6T1a.setSelected(true);
						}//end if for first checkbox
						if(taUserInputL6T1.getText().contains("int favNum;"))
						{
							cbL6T1b.setSelected(true);
						}//end if for second checkbox
						if(!taUserOutputL6T1.getText().contains("Don't forget to print to the screen and tell/ask user what they should input"))
						{
							cbL6T1c.setSelected(true);
						}//end if for third checkbox
						if(taUserInputL6T1.getText().contains("System.out.println(favNum);"))
						{
							cbL6T1e.setSelected(true);
						}//end if for fifth checkbox
						if(!taUserOutputL6T1.getText().contains("Don't forget to close your scanner object on the sixth line of code."))
						{
							cbL6T1f.setSelected(true);
						}//end if for sixth checkbox

						if(isIntL6T1)
						{
							txtRunInputL6T1Value.setText(txtRunInputL6T1.getText());
							txtRunInputL6T1Value.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
							txtRunInputL6T1Value.setVisible(true);
							txtRunInputL6T1.setEditable(false);
							cbL6T1d.setSelected(true);
						}//end if

						if(cbL6T1a.isSelected() && cbL6T1b.isSelected() && cbL6T1c.isSelected() && cbL6T1d.isSelected() && cbL6T1e.isSelected() && cbL6T1f.isSelected())
						{
							//setting the next task as visible
							textflowL6T2.setVisible(true);
							textflowL6T2.setManaged(true);
							hbxL6T2Input.setVisible(true);
							hbxL6T2Input.setManaged(true);
							vbxL6T2Output.setVisible(true);
							vbxL6T2Output.setManaged(true);
							hbxL6T2Checklist.setVisible(true);
							hbxL6T2Checklist.setManaged(true);

							//changing the progress value of the progress bar if this is the user's first time doing the current task
							if(user.getProgressLevel()<(progressIncrease*25))
							{
								pbsUserProgress.setProgress((progressIncrease*25));
								user.setProgressLevel(pbsUserProgress.getProgress());
							}//end inside if
						}//end outside if

						else
						{
							taUserOutputL6T1.setText("Error!\n- You must assign favNum as an integer.");
							taUserOutputL6T1.setStyle("-fx-text-fill: red; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
							txtRunInputL6T1.setVisible(false);
						}//end else
					}//end handle method
				});
			}//end if
		});//end setOnAction for btnSubmitL6T1

		//determining what to do when the submit button for L6T2 is clicked
		btnSubmitL6T2.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			int checkboxSelectedCounterL6T2=0;
			trialCounter[5][1]++;

			//creating the array to store user's output and adding user input to the userResponse array
			String [] outputL6T2=new String[3];
			userResponse[5][1][trialCounter[5][1]]=taUserInputL6T2.getText();
			String [] runInputL6T2 = new String [2];
			txtRunInputL6T2.setText("");
			txtRunInputL6T2.setEditable(true);
			txtRunInputL6T2Value.setText("");
			txtRunInputL6T2Value.setVisible(false);
			double dRunInputL6T2=0;

			//determining and setting the output that the user will see based on their submission
			try
			{
				outputL6T2=CheckJava.submitL6T2(taUserInputL6T2, TEXTAREA_FONT);
			}
			catch (ScriptException e)
			{
				e.printStackTrace();
			}//end try catch
			taUserOutputL6T2.setText(outputL6T2[0]);
			taUserOutputL6T2.setStyle(outputL6T2[1]);

			//resetting all checkboxes in this task to be deselected
			cbL6T2a.setSelected(false);
			cbL6T2b.setSelected(false);
			cbL6T2c.setSelected(false);
			cbL6T2d.setSelected(false);
			cbL6T2e.setSelected(false);
			cbL6T2f.setSelected(false);

			if(!taUserOutputL6T2.getText().startsWith("Error!"))
			{
				txtRunInputL6T2.setVisible(true);

				txtRunInputL6T2.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event)
					{
						boolean isDoubleL6T2=ErrorChecks.checkIfDouble(txtRunInputL6T2.getText());

						if(!taUserOutputL6T2.getText().contains("Don't forget to create the object from the Scanner class in order to gather input from the user for this task."))
						{
							cbL6T2a.setSelected(true);
						}//end if for first checkbox
						if(taUserInputL6T2.getText().contains("double myAge;"))
						{
							cbL6T2b.setSelected(true);
						}//end if for second checkbox
						if(!taUserOutputL6T2.getText().contains("Don't forget to print to the screen and tell/ask user what they should input"))
						{
							cbL6T2c.setSelected(true);
						}//end if for third checkbox
						if(taUserInputL6T2.getText().contains("System.out.println(myAge);"))
						{
							cbL6T2e.setSelected(true);
						}//end if for fifth checkbox
						if(!taUserOutputL6T2.getText().contains("Don't forget to close your scanner object on the sixth line of code."))
						{
							cbL6T2f.setSelected(true);
						}//end if for sixth checkbox

						if(isDoubleL6T2)
						{
							txtRunInputL6T2Value.setText(txtRunInputL6T2.getText());
							txtRunInputL6T2Value.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
							txtRunInputL6T2Value.setVisible(true);
							txtRunInputL6T2.setEditable(false);
							cbL6T2d.setSelected(true);
						}//end if

						if(cbL6T2a.isSelected() && cbL6T2b.isSelected() && cbL6T2c.isSelected() && cbL6T2d.isSelected() && cbL6T2e.isSelected() && cbL6T2f.isSelected())
						{
							//setting the next task as visible
							textflowL6T2.setVisible(true);
							textflowL6T2.setManaged(true);
							hbxL6T2Input.setVisible(true);
							hbxL6T2Input.setManaged(true);
							vbxL6T2Output.setVisible(true);
							vbxL6T2Output.setManaged(true);
							hbxL6T2Checklist.setVisible(true);
							hbxL6T2Checklist.setManaged(true);

							//changing the progress value of the progress bar if this is the user's first time doing the current task
							if(user.getProgressLevel()<(progressIncrease*26))
							{
								pbsUserProgress.setProgress((progressIncrease*26));
								user.setProgressLevel(pbsUserProgress.getProgress());
							}//end inside if
						}//end outside if

						else
						{
							taUserOutputL6T2.setText("Error!\n- You must assign myAge as a double.");
							taUserOutputL6T2.setStyle("-fx-text-fill: red; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
							txtRunInputL6T2.setVisible(false);
						}//end else
					}//end handle method
				});
			}//end if
		});//end setOnAction for btnSubmitL6T2

		//determining what to do when the submit button for L6T3 is clicked
		btnSubmitL6T3.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			int checkboxSelectedCounterL6T3=0;
			trialCounter[5][2]++;

			//creating the array to store user's output and adding user input to the userResponse array
			String [] outputL6T3=new String[3];
			userResponse[5][2][trialCounter[5][2]]=taUserInputL6T3.getText();
			String [] runInputL6T3 = new String [2];
			txtRunInputL6T3.setText("");
			txtRunInputL6T3.setEditable(true);
			txtRunInputL6T3Value.setText("");
			txtRunInputL6T3Value.setVisible(false);
			String sRunInputL6T3="";

			//determining and setting the output that the user will see based on their submission
			try
			{
				outputL6T3=CheckJava.submitL6T3(taUserInputL6T3, TEXTAREA_FONT);
			}
			catch (ScriptException e)
			{
				e.printStackTrace();
			}//end try catch
			taUserOutputL6T3.setText(outputL6T3[0]);
			taUserOutputL6T3.setStyle(outputL6T3[1]);

			//resetting all checkboxes in this task to be deselected
			cbL6T3a.setSelected(false);
			cbL6T3b.setSelected(false);
			cbL6T3c.setSelected(false);
			cbL6T3d.setSelected(false);
			cbL6T3e.setSelected(false);

			if(!taUserOutputL6T3.getText().startsWith("Error!"))
			{
				txtRunInputL6T3.setVisible(true);
				txtRunInputL6T3Value.setText(txtRunInputL6T3.getText());
				txtRunInputL6T3Value.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");

				txtRunInputL6T3.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event)
					{
						txtRunInputL6T3Value.setText(txtRunInputL6T3.getText());
						txtRunInputL6T3Value.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
						txtRunInputL6T3Value.setVisible(true);
						if(!taUserOutputL6T3.getText().contains("Don't forget to create the object from the Scanner class in order to gather input from the user for this task."))
						{
							cbL6T3a.setSelected(true);
						}//end if for first checkbox
						if(taUserInputL6T3.getText().contains("String myFirstName;"))
						{
							cbL6T3b.setSelected(true);
						}//end if for second checkbox
						if(!taUserOutputL6T3.getText().contains("Don't forget to print to the screen and tell/ask user what they should input"))
						{
							cbL6T3c.setSelected(true);
						}//end if for third checkbox
						if(taUserInputL6T3.getText().contains("System.out.println(myFirstName);"))
						{
							cbL6T3d.setSelected(true);
						}//end if for fourth checkbox
						if(!taUserOutputL6T3.getText().contains("Don't forget to close your scanner object on the sixth line of code."))
						{
							cbL6T3e.setSelected(true);
						}//end if for fifth checkbox

						if(cbL6T3a.isSelected() && cbL6T3b.isSelected() && cbL6T3c.isSelected() && cbL6T3d.isSelected() && cbL6T3e.isSelected())
						{
							//setting the next task as visible
							textflowL6T4.setVisible(true);
							textflowL6T4.setManaged(true);
							hbxL6T4Input.setVisible(true);
							hbxL6T4Input.setManaged(true);
							hbxL6T4Output.setVisible(true);
							hbxL6T4Output.setManaged(true);
							hbxL6T4Checklist.setVisible(true);
							hbxL6T4Checklist.setManaged(true);

							//changing the progress value of the progress bar if this is the user's first time doing the current task
							if(user.getProgressLevel()<(progressIncrease*26))
							{
								pbsUserProgress.setProgress((progressIncrease*26));
								user.setProgressLevel(pbsUserProgress.getProgress());
							}//end inside if
						}//end outside if
					}//end handle method
				});
			}//end if
		});//end setOnAction for btnSubmitL6T3

		//determining what to do when the submit button for L6T4 is clicked
		btnSubmitL6T4.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			int checkboxSelectedCounterL6T4=0;
			trialCounter[5][3]++;

			//creating the array to store user's output and adding user input to the userResponse array
			String [] outputL6T4=new String[3];
			userResponse[5][3][trialCounter[5][3]]=taUserInputL6T4.getText();
			String [] runInputL6T4 = new String [2];
			txtRunInputL6T4a.setVisible(false);
			txtRunInputL6T4a.setText("");
			txtRunInputL6T4a.setEditable(true);
			taUserOutputL6T4b.setVisible(false);
			txtRunInputL6T4b.setVisible(false);
			txtRunInputL6T4b.setText("");
			txtRunInputL6T4b.setEditable(true);

			//determining and setting the output that the user will see based on their submission
			try
			{
				outputL6T4=CheckJava.submitL6T4(taUserInputL6T4, TEXTAREA_FONT);
			}
			catch (ScriptException e)
			{
				e.printStackTrace();
			}//end try catch
			taUserOutputL6T4a.setText(outputL6T4[0]);
			taUserOutputL6T4a.setStyle(outputL6T4[2]);
			taUserOutputL6T4b.setText(outputL6T4[1]);
			taUserOutputL6T4b.setStyle(outputL6T4[2]);

			//resetting all checkboxes in this task to be deselected
			cbL6T4a.setSelected(false);
			cbL6T4b.setSelected(false);
			cbL6T4c.setSelected(false);
			cbL6T4d.setSelected(false);
			cbL6T4e.setSelected(false);
			cbL6T4f.setSelected(false);

			if(!taUserOutputL6T4a.getText().startsWith("Error!"))
			{
				txtRunInputL6T4a.setVisible(true);

				txtRunInputL6T4a.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event)
					{
						boolean isIntL6T4=ErrorChecks.checkIfInt(txtRunInputL6T4a.getText());

						if(!taUserOutputL6T4a.getText().contains("Don't forget to create the object from the Scanner class in order to gather input from the user for this task."))
						{
							cbL6T4a.setSelected(true);
						}//end if for first checkbox
						if(taUserInputL6T4.getText().contains("int numOfFamilyMembers;"))
						{
							cbL6T4b.setSelected(true);
						}//end if for second checkbox
						if(taUserInputL6T4.getText().contains("String favFood;"))
						{
							cbL6T4c.setSelected(true);
						}//end if for third checkbox
						if(!taUserOutputL6T4a.getText().contains("Don't forget to print to the screen and tell/ask user what they should input (the "))
						{
							cbL6T4d.setSelected(true);
						}//end if for fourth checkbox
						if(!taUserOutputL6T4a.getText().contains("Don't forget to print to the screen and tell/ask user what they should input (their "))
						{
							cbL6T4e.setSelected(true);
						}//end if for fifth checkbox

						if(isIntL6T4)
						{
							taUserOutputL6T4b.setVisible(true);
							txtRunInputL6T4b.setEditable(true);
							txtRunInputL6T4b.setVisible(true);
							txtRunInputL6T4b.setOnAction(new EventHandler<ActionEvent>()
							{
								@Override
								public void handle(ActionEvent event)
								{
									cbL6T4f.setSelected(true);
								}//end inside handle method
							});//end setOnAction method for txtRunInputL6T4b
						}//end if

						else
						{
							taUserOutputL6T4a.setText("Error!\n- You must assign numOfFamilyMembers as an integer.");
							taUserOutputL6T4a.setStyle("-fx-text-fill: red; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
							txtRunInputL6T4a.setVisible(false);
						}//end else

						if(!taUserOutputL6T4a.getText().contains("Don't forget to close your scanner object on the sixth line of code."))
						{
							cbL6T4g.setSelected(true);
						}//end if for seventh checkbox

						if(cbL6T4a.isSelected() && cbL6T4b.isSelected() && cbL6T4c.isSelected() && cbL6T4d.isSelected() && cbL6T4e.isSelected() && cbL6T4f.isSelected() && cbL6T4g.isSelected())
						{
							//setting the next task as visible
							textflowL6T2.setVisible(true);
							textflowL6T2.setManaged(true);
							hbxL6T2Input.setVisible(true);
							hbxL6T2Input.setManaged(true);
							hbxL6T2Output.setVisible(true);
							hbxL6T2Output.setManaged(true);
							hbxL6T2Checklist.setVisible(true);
							hbxL6T2Checklist.setManaged(true);

							//changing the progress value of the progress bar if this is the user's first time doing the current task
							if(user.getProgressLevel()<(progressIncrease*25))
							{
								pbsUserProgress.setProgress((progressIncrease*25));
								user.setProgressLevel(pbsUserProgress.getProgress());
							}//end inside if
						}//end outside if
					}//end outside handle method
				});//end setOnAction method for txtRunInputL6T4a
			}//end if
		});//end setOnAction for btnSubmitL6T4

		//creating and setting the scroll pane for this lesson
		ScrollPane spL6 = new ScrollPane();
		spL6.setContent(vbxL6);

		//creating the scene
		Scene sceneL6 = new Scene(spL6);

		lastScene="lesson6"; //setting the previous scene to this scene

		//setting/showing the stage
		myStage.getIcons().add(new Image("FromOneToAnotherLogo.png"));
		myStage.setTitle("From C++ to Java - ... - Lesson #6");
		myStage.setScene(sceneL6);
		myStage.centerOnScreen();
		myStage.show();
	}//end lesson6 method

	
	/* Method Author: Brooke Cronin
	 * Method Name: lesson7
	 * Description: Creates the scene for the seventh lesson. Allows the user to view and attempt all of the tasks and get feedback after completing each task.
	 * Parameters: Stage myStage
	 * Returns: N/A
	 * Throws: NullPointerException
	 */

	public void lesson7(Stage myStage) throws NullPointerException
	{
		myStage.setOnHiding( event -> {try {
			exitProgram();
		} catch (IOException e) {
			
			e.printStackTrace();
		}} );
		//resetting the text in all of the user output text areas and disabling them so that the user can't change what appears in them
		TextArea taUserOutputL7T1 = new TextArea();
		taUserOutputL7T1.setText("Not submitted yet");
		taUserOutputL7T1.setEditable(false);
		taUserOutputL7T1.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
		TextArea taUserOutputL7T2a = new TextArea();
		taUserOutputL7T2a.setText("Not submitted yet");
		taUserOutputL7T2a.setEditable(false);
		taUserOutputL7T2a.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
		TextArea taUserOutputL7T3 = new TextArea();
		taUserOutputL7T3.setText("Not submitted yet");
		taUserOutputL7T3.setEditable(false);
		taUserOutputL7T3.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");

		//creating the vertical box for lesson 7 screen
		VBox vbxL7 = new VBox(GAP);
		vbxL7.setPadding(new Insets(GAP, GAP, GAP, GAP));

		//adding the user's progress bar to the vertical box
		pbsUserProgress.setMaxWidth(Double.MAX_VALUE);
		vbxL7.getChildren().add(pbsUserProgress);

		//creating, setting, and adding a header Label
		AnchorPane apLesson5TitleHeader = new AnchorPane();
		Label lblLesson5TitleHeader = new Label("Lesson #7: Decisions");
		lblLesson5TitleHeader.setStyle("-fx-text-fill: black; -fx-font-size: "+LESSON_HEADER_FONT);
		lblLesson5TitleHeader.setMaxWidth(Double.MAX_VALUE);
		AnchorPane.setLeftAnchor(lblLesson5TitleHeader, 0.0);
		AnchorPane.setRightAnchor(lblLesson5TitleHeader, 0.0);
		lblLesson5TitleHeader.setAlignment(Pos.CENTER);
		apLesson5TitleHeader.getChildren().add(lblLesson5TitleHeader);
		vbxL7.getChildren().add(apLesson5TitleHeader);

		//adding the name of the current task
		Label lblL7T1Header = new Label("\nTask #1: Comparing Two Integer Values");
		lblL7T1Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		vbxL7.getChildren().add(lblL7T1Header);

		//creating, setting, and adding the first task instructions
		Text textL7T1a = new Text("\n\nNow that you know how to output variable values/text to the screen, process values, and gather input from the user in Java, you can now learn how to compare values.\nThere are two ways to do this; if statements and switch case statements.\nIn Python, you would use ");
		textL7T1a.setFill(Color.BLACK);
		Text textL7T1b = new Text(" if");
		textL7T1b.setFill(Color.DARKVIOLET);
		Text textL7T1c = new Text(" (or ");
		textL7T1c.setFill(Color.BLACK);
		Text textL7T1d = new Text("elif");
		textL7T1d.setFill(Color.DARKVIOLET);
		Text textL7T1e = new Text(") followed by the comparison and a :, or ");
		textL7T1e.setFill(Color.BLACK);
		Text textL7T1f = new Text("else:");
		textL7T1f.setFill(Color.DARKVIOLET);
		Text textL7T1g = new Text(" with the code that can only be reached when this comparison is true indented. Java is a little bit different. The lines begin with either ");
		textL7T1g.setFill(Color.BLACK);
		Text textL7T1h = new Text("if");
		textL7T1h.setFill(Color.BLUE);
		Text textL7T1i = new Text(", ");
		textL7T1i.setFill(Color.BLACK);
		Text textL7T1j = new Text("else if");
		textL7T1j.setFill(Color.BLUE);
		Text textL7T1k = new Text(", or ");
		textL7T1k.setFill(Color.BLACK);
		Text textL7T1l = new Text("else");
		textL7T1l.setFill(Color.BLUE);
		Text textL7T1m = new Text(". The comparisons are surrounded by parenthesis, and the code for each is surrounded by curly brackets. For integers and double values the comparison operators (e.g. ==, !=, <, >, <=, >=) are the same in Python and Java. \nIn this task, you will create and initialize two integer variables (called ");
		textL7T1m.setFill(Color.BLACK);
		Text textL7T1n = new Text("a");
		textL7T1n.setFill(Color.rgb(121, 76, 52));
		Text textL7T1o = new Text(" and ");
		textL7T1o.setFill(Color.BLACK);
		Text textL7T1p = new Text("b");
		textL7T1p.setFill(Color.rgb(121, 76, 52));
		Text textL7T1q = new Text("), then print one of the following statements to the screen using if/else if/else statements;\n\"Since ");
		textL7T1q.setFill(Color.BLACK);
		Text textL7T1r = new Text("a");
		textL7T1r.setFill(Color.rgb(121, 76, 52));
		Text textL7T1s = new Text(" is greater than ");
		textL7T1s.setFill(Color.BLACK);
		Text textL7T1t = new Text("b");
		textL7T1t.setFill(Color.rgb(121, 76, 52));
		Text textL7T1u = new Text(", a is greater than b.\", ");
		textL7T1u.setFill(Color.BLACK);
		Text textL7T1v = new Text("\n\"Since ");
		textL7T1v.setFill(Color.BLACK);
		Text textL7T1w = new Text("a");
		textL7T1w.setFill(Color.rgb(121, 76, 52));
		Text textL7T1x = new Text(" is less than ");
		textL7T1x.setFill(Color.BLACK);
		Text textL7T1y = new Text("b");
		textL7T1y.setFill(Color.rgb(121, 76, 52));
		Text textL7T1z = new Text(", a is less than b.\", and ");
		textL7T1z.setFill(Color.BLACK);
		Text textL7T1aa = new Text("\n\"Since ");
		textL7T1aa.setFill(Color.BLACK);
		Text textL7T1ab = new Text("a");
		textL7T1ab.setFill(Color.rgb(121, 76, 52));
		Text textL7T1ac = new Text(" is equal to ");
		textL7T1ac.setFill(Color.BLACK);
		Text textL7T1ad = new Text("b");
		textL7T1ad.setFill(Color.rgb(121, 76, 52));
		Text textL7T1ae = new Text(", a is equal to b.\".\n");
		textL7T1ae.setFill(Color.BLACK);
		Text textL7T1af = new Text("You can also try again with different values for ");
		textL7T1af.setFill(Color.BLACK);
		Text textL7T1ag = new Text("a");
		textL7T1ag.setFill(Color.rgb(121, 76, 52));
		Text textL7T1ah = new Text(" and ");
		textL7T1ah.setFill(Color.BLACK);
		Text textL7T1ai = new Text("b");
		textL7T1ai.setFill(Color.rgb(121, 76, 52));
		Text textL7T1aj = new Text(".");
		textL7T1aj.setFill(Color.BLACK);
		TextFlow textflowL7T1 = new TextFlow(textL7T1a, textL7T1b, textL7T1c, textL7T1d, textL7T1e, textL7T1f, textL7T1g, textL7T1h, textL7T1i, textL7T1j, textL7T1k, textL7T1l, textL7T1m, textL7T1n, textL7T1o, textL7T1p, textL7T1q, textL7T1r, textL7T1s, textL7T1t, textL7T1u, textL7T1v, textL7T1w, textL7T1x, textL7T1y, textL7T1z, textL7T1aa, textL7T1ab, textL7T1ac, textL7T1ad, textL7T1ae, textL7T1af, textL7T1ag, textL7T1ah, textL7T1ai, textL7T1aj);
		textflowL7T1.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		vbxL7.getChildren().add(textflowL7T1);

		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput1 = new Label("Your code: ");
		lblInput1.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL7T1 = new TextArea();
		taUserInputL7T1.setPrefSize(taWidth, taLineHeight*15);
		taUserInputL7T1.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL7T1.setText("public class L7T1Response\n{\n\tpublic static void main()\n\t{\n\n\t}//end main method\n}//end L7T1Response class");
		Button btnSubmitL7T1 = new Button("Submit");
		btnSubmitL7T1.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL7T1Input = new HBox(GAP, lblInput1, taUserInputL7T1, btnSubmitL7T1);
		vbxL7.getChildren().add(hbxL7T1Input);
		//for user's output
		Label lblOutput1 = new Label("Output:     ");
		lblOutput1.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL7T1.setPrefSize(taWidth, taLineHeight);
		HBox hbxL7T1Output = new HBox(GAP, lblOutput1, taUserOutputL7T1);
		vbxL7.getChildren().add(hbxL7T1Output);
		//for user's result
		Label lblCriteria1 = new Label("Criteria");
		CheckBox cbL7T1a = new CheckBox("a and b were created and initialized as integers");
		cbL7T1a.setDisable(true);
		CheckBox cbL7T1b = new CheckBox("If statements were used to compare the values of a and b");
		cbL7T1b.setDisable(true);
		CheckBox cbL7T1c = new CheckBox("Output is the correct one of the three statements listed above");
		cbL7T1c.setDisable(true);
		HBox hbxL7T1Checklist = new HBox(GAP, lblCriteria1, cbL7T1a, cbL7T1b, cbL7T1c);
		hbxL7T1Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria1.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		vbxL7.getChildren().add(hbxL7T1Checklist);

		//adding the name of the current task
		Label lblL7T2Header = new Label("\n\n\nTask #2: Scoring");
		lblL7T2Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		if(user.getProgressLevel()<(progressIncrease*29))
		{
			lblL7T2Header.setVisible(false);
			lblL7T2Header.setManaged(false);
		}//end if
		vbxL7.getChildren().add(lblL7T2Header);

		//creating, setting, and adding the second task instructions
		Text textL7T2a = new Text("\n\nWhat about comparing string values? For this, the comparison operators are a little bit different in Java than Python. \nIn Python, the comparison operators are the same for all types of values. \nIn Java, comparing strings is not the same as comparing integers and doubles.");
		textL7T2a.setFill(Color.BLACK);
		Text textL7T2b = new Text("\n\nPython\t\t\t\t\t\t\t\tJava\n");
		textL7T2b.setFill(Color.BLACK);
		Text textL7T2c = new Text("var1");
		textL7T2c.setFill(Color.rgb(121, 76, 52));
		Text textL7T2d = new Text(" == ");
		textL7T2d.setFill(Color.DARKVIOLET);
		Text textL7T2e = new Text("var2\t\t\t\t\t\t\t");
		textL7T2e.setFill(Color.rgb(121, 76, 52));
		Text textL7T2f = new Text("var1");
		textL7T2f.setFill(Color.rgb(121, 76, 52));
		Text textL7T2g = new Text(".equals(");
		textL7T2g.setFill(Color.BLUE);
		Text textL7T2h = new Text("var2");
		textL7T2h.setFill(Color.rgb(121, 76, 52));
		Text textL7T2i = new Text(")\n");
		textL7T2i.setFill(Color.BLUE);
		Text textL7T2j = new Text("var1");
		textL7T2j.setFill(Color.rgb(121, 76, 52));
		Text textL7T2k = new Text(" != ");
		textL7T2k.setFill(Color.DARKVIOLET);
		Text textL7T2l = new Text("var2\t\t\t\t\t\t\t");
		textL7T2l.setFill(Color.rgb(121, 76, 52));
		Text textL7T2m = new Text("!");
		textL7T2m.setFill(Color.BLUE);
		Text textL7T2n = new Text("var1");
		textL7T2n.setFill(Color.rgb(121, 76, 52));
		Text textL7T2o = new Text(".equals(");
		textL7T2o.setFill(Color.BLUE);
		Text textL7T2p = new Text("var2");
		textL7T2p.setFill(Color.rgb(121, 76, 52));
		Text textL7T2q = new Text(")\n");
		textL7T2q.setFill(Color.BLUE);
		Text textL7T2r = new Text("var1");
		textL7T2r.setFill(Color.rgb(121, 76, 52));
		Text textL7T2s = new Text(".lower()");
		textL7T2s.setFill(Color.DARKVIOLET);
		Text textL7T2t = new Text(" == ");
		textL7T2t.setFill(Color.DARKVIOLET);
		Text textL7T2u = new Text("var2");
		textL7T2u.setFill(Color.rgb(121, 76, 52));
		Text textL7T2v = new Text(".lower()\t\t\t\t");
		textL7T2v.setFill(Color.DARKVIOLET);
		Text textL7T2w = new Text("var1");
		textL7T2w.setFill(Color.rgb(121, 76, 52));
		Text textL7T2x = new Text(".equalsIgnoreCase(");
		textL7T2x.setFill(Color.BLUE);
		Text textL7T2y = new Text("var2");
		textL7T2y.setFill(Color.rgb(121, 76, 52));
		Text textL7T2z = new Text(")\n\n\n");
		textL7T2z.setFill(Color.BLUE);
		Text textL7T2aa = new Text("In this task you will create an integer variable called scoreCounter and initialize it to 0.\nNext, create 6 string variables called ");
		textL7T2aa.setFill(Color.BLACK);
		Text textL7T2ab = new Text("userResponse1");
		textL7T2ab.setFill(Color.rgb(121, 76, 52));
		Text textL7T2ac = new Text(", ");
		textL7T2ac.setFill(Color.BLACK);
		Text textL7T2ad = new Text("correctResponse1");
		textL7T2ad.setFill(Color.rgb(121, 76, 52));
		Text textL7T2ae = new Text(", ");
		textL7T2ae.setFill(Color.BLACK);
		Text textL7T2af = new Text("userResponse2");
		textL7T2af.setFill(Color.rgb(121, 76, 52));
		Text textL7T2ag = new Text(", ");
		textL7T2ag.setFill(Color.BLACK);
		Text textL7T2ah = new Text("correctResponse2");
		textL7T2ah.setFill(Color.rgb(121, 76, 52));
		Text textL7T2ai = new Text(", ");
		textL7T2ai.setFill(Color.BLACK);
		Text textL7T2aj = new Text("userResponse3");
		textL7T2aj.setFill(Color.rgb(121, 76, 52));
		Text textL7T2ak = new Text(", and ");
		textL7T2ak.setFill(Color.BLACK);
		Text textL7T2al = new Text("correctResponse3");
		textL7T2al.setFill(Color.rgb(121, 76, 52));
		Text textL7T2am = new Text(". \nInitialize ");
		textL7T2am.setFill(Color.BLACK);
		Text textL7T2an = new Text("correctResponse1");
		textL7T2an.setFill(Color.rgb(121, 76, 52));
		Text textL7T2ao = new Text(" to the captial of Canada, ");
		textL7T2ao.setFill(Color.BLACK);
		Text textL7T2ap = new Text("correctResponse2");
		textL7T2ap.setFill(Color.rgb(121, 76, 52));
		Text textL7T2aq = new Text(" to the captial of France, and ");
		textL7T2aq.setFill(Color.BLACK);
		Text textL7T2ar = new Text("correctResponse3");
		textL7T2ar.setFill(Color.rgb(121, 76, 52));
		Text textL7T2as = new Text(" to the captial of Ireland. Ask the user what the capital of Canada is, assign it to the value of ");
		textL7T2as.setFill(Color.BLACK);
		Text textL7T2at = new Text("userResponse1");
		textL7T2at.setFill(Color.rgb(121, 76, 52));
		Text textL7T2au = new Text(" and using an if statement, determine of the user has gotten the correct answer (if they have, increase the value of ");
		textL7T2au.setFill(Color.BLACK);
		Text textL7T2av = new Text("scoreCounter");
		textL7T2av.setFill(Color.rgb(121, 76, 52));
		Text textL7T2aw = new Text(" (you can do this by ");
		textL7T2aw.setFill(Color.BLACK);
		Text textL7T2ax = new Text("scoreCounter");
		textL7T2ax.setFill(Color.rgb(121, 76, 52));
		Text textL7T2ay = new Text("++;");
		textL7T2ay.setFill(Color.BLUE);
		Text textL7T2az = new Text("). \nRepeat for the capital of France and Ireland using the appropriate variables. \nPrint the user's score to the screen in the following format:\n");
		textL7T2az.setFill(Color.BLACK);
		Text textL7T2ba = new Text("scoreCounter");
		textL7T2ba.setFill(Color.rgb(121, 76, 52));
		textL7T2ba.setStyle("-fx-font: "+TASK_INSTR_FONT+"\"Comic Sans MS\"");
		Text textL7T2bb = new Text("/3");
		textL7T2bb.setStyle("-fx-text-fill: black; -fx-font: "+TASK_INSTR_FONT+"\"Comic Sans MS\"");
		TextFlow textflowL7T2 = new TextFlow(textL7T2a, textL7T2b, textL7T2c, textL7T2d, textL7T2e, textL7T2f, textL7T2g, textL7T2h, textL7T2i, textL7T2j, textL7T2k, textL7T2l, textL7T2m, textL7T2n, textL7T2o, textL7T2p, textL7T2q, textL7T2r, textL7T2s, textL7T2t, textL7T2u, textL7T2v, textL7T2w, textL7T2x, textL7T2y, textL7T2z, textL7T2aa, textL7T2ab, textL7T2ac, textL7T2ad, textL7T2ae, textL7T2af, textL7T2ag, textL7T2ah, textL7T2ai, textL7T2aj, textL7T2ak, textL7T2al, textL7T2am, textL7T2an, textL7T2ao, textL7T2ap, textL7T2aq, textL7T2ar, textL7T2as, textL7T2at, textL7T2au, textL7T2av, textL7T2aw, textL7T2ax, textL7T2ay, textL7T2az, textL7T2ba, textL7T2bb);
		textflowL7T2.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*29))
		{
			textflowL7T2.setVisible(false);
			textflowL7T2.setManaged(false);
		}//end if
		vbxL7.getChildren().add(textflowL7T2);

		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput2 = new Label("Your code: ");
		lblInput2.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL7T2 = new TextArea();
		taUserInputL7T2.setPrefSize(taWidth, taLineHeight*29);
		taUserInputL7T2.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL7T2.setText("public class L7T2Response\n{\n\tpublic static void main()\n\t{\n\n\t}//end main method\n}//end L7T2Response class");
		Button btnSubmitL7T2 = new Button("Submit");
		btnSubmitL7T2.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL7T2Input = new HBox(GAP, lblInput2, taUserInputL7T2, btnSubmitL7T2);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*29))
		{
			hbxL7T2Input.setVisible(false);
			hbxL7T2Input.setManaged(false);
		}//end if
		vbxL7.getChildren().add(hbxL7T2Input);
		//for user's output
		Label lblOutput2 = new Label("Output:     ");
		lblOutput2.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL7T2a.setPrefSize(taWidth, taLineHeight);
		HBox hbxL7T2Output = new HBox(GAP, lblOutput2, taUserOutputL7T2a);
		TextField txtRunInputL7T2a = new TextField("");
		txtRunInputL7T2a.setVisible(false);
		txtRunInputL7T2a.setPrefSize(taWidth, taLineHeight);
		txtRunInputL7T2a.setStyle("-fx-text-fill: green; -fx-font-size: "+TEXTAREA_FONT);
		Label lblIndentT2a = new Label("\t\t\t    ");
		HBox hbxRunInputL7T2a = new HBox(GAP, lblIndentT2a, txtRunInputL7T2a);
		TextArea taUserOutputL7T2b = new TextArea("");
		taUserOutputL7T2b.setPrefSize(taWidth, taLineHeight);
		taUserOutputL7T2b.setVisible(false);
		Label lblIndentT2b = new Label("\t\t\t    ");
		HBox hbxUserOutputL7T2b = new HBox(GAP, lblIndentT2b, taUserOutputL7T2b);
		TextField txtRunInputL7T2b = new TextField("");
		txtRunInputL7T2b.setVisible(false);
		txtRunInputL7T2b.setPrefSize(taWidth, taLineHeight);
		txtRunInputL7T2b.setStyle("-fx-text-fill: green; -fx-font-size: "+TEXTAREA_FONT);
		Label lblIndentT2c = new Label("\t\t\t    ");
		HBox hbxRunInputL7T2b = new HBox(GAP, lblIndentT2c, txtRunInputL7T2b);
		TextArea taUserOutputL7T2c = new TextArea("");
		taUserOutputL7T2c.setPrefSize(taWidth, taLineHeight);
		taUserOutputL7T2c.setVisible(false);
		Label lblIndentT2d = new Label("\t\t\t    ");
		HBox hbxUserOutputL7T2c = new HBox(GAP, lblIndentT2d, taUserOutputL7T2c);
		TextField txtRunInputL7T2c = new TextField("");
		txtRunInputL7T2c.setVisible(false);
		txtRunInputL7T2c.setPrefSize(taWidth, taLineHeight);
		txtRunInputL7T2c.setStyle("-fx-text-fill: green; -fx-font-size: "+TEXTAREA_FONT);
		Label lblIndentT2e = new Label("\t\t\t    ");
		HBox hbxRunInputL7T2c = new HBox(GAP, lblIndentT2e, txtRunInputL7T2c);
		TextArea taUserOutputL7T2d = new TextArea("");
		taUserOutputL7T2d.setPrefSize(taWidth, taLineHeight);
		taUserOutputL7T2d.setVisible(false);
		Label lblIndentT2f = new Label("\t\t\t    ");
		HBox hbxUserOutputL7T2d = new HBox(GAP, lblIndentT2f, taUserOutputL7T2d);
		VBox vbxL7T2Output = new VBox(0);
		vbxL7T2Output.setPadding(new Insets(GAP, GAP, GAP, GAP));
		vbxL7T2Output.getChildren().addAll(hbxL7T2Output, hbxRunInputL7T2a, hbxUserOutputL7T2b, hbxRunInputL7T2b, hbxUserOutputL7T2c, hbxRunInputL7T2c, hbxUserOutputL7T2d);
		vbxL7.getChildren().add(vbxL7T2Output);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*29))
		{
			vbxL7T2Output.setVisible(false);
			vbxL7T2Output.setManaged(false);
		}//end if
		//for user's result
		Label lblCriteria2 = new Label("Criteria");
		CheckBox cbL7T2a = new CheckBox("userResponse1, userResponse2, and userResponse3 were created as string variables and not initialized");
		cbL7T2a.setDisable(true);
		CheckBox cbL7T2b = new CheckBox("correctResponse1, correctResponse2, and correctResponse3 were created as string variables and initialized to the correct answer for each question");
		cbL7T2b.setDisable(true);
		CheckBox cbL7T2c = new CheckBox("scoreCounter is created as an integer, in itialized to 0 is updated correctly");
		cbL7T2c.setDisable(true);
		CheckBox cbL7T2d = new CheckBox("Three questions were printed to the screen to let the user know what they have to input");
		cbL7T2d.setDisable(true);
		CheckBox cbL7T2e = new CheckBox("The user is able to enter their response in any case and can still get it correct");
		cbL7T2e.setDisable(true);
		HBox hbxL7T2Checklist = new HBox(GAP, lblCriteria2, cbL7T2a, cbL7T2b, cbL7T2c, cbL7T2d, cbL7T2e);
		hbxL7T2Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria2.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*29))
		{
			hbxL7T2Checklist.setVisible(false);
			hbxL7T2Checklist.setManaged(false);
		}//end if
		vbxL7.getChildren().add(hbxL7T2Checklist);

		//adding the name of the current task
		Label lblL7T3Header = new Label("\n\n\nTask #3: What month is it?");
		lblL7T3Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		if(user.getProgressLevel()<(progressIncrease*30))
		{
			lblL7T3Header.setVisible(false);
			lblL7T3Header.setManaged(false);
		}//end if
		vbxL7.getChildren().add(lblL7T3Header);

		//creating, setting, and adding the third task instructions
		Text textL7T3a = new Text("\n\nThe next three tasks in this lesson will be requiring switch case statements to make decisions rather than if/else if/else statements. \nThe closest thing to this in Python is a switcher.");
		textL7T3a.setFill(Color.BLACK);
		Text textL7T3b = new Text("\nmath.radians(30)");
		textL7T3b.setFill(Color.DARKVIOLET);
		Text textL7T3c = new Text("\nIn this task, you will create an integer variable called ");
		textL7T3c.setFill(Color.BLACK);
		Text textL7T3d = new Text("month");
		textL7T3d.setFill(Color.rgb(121, 76, 52));
		Text textL7T3e = new Text(" and initialize it to 0. \nNext, ask the user what month number it is (1=January, 6=June, 12=December, etc.). \nAfter that, use a switch case statement to print out the month the user has chosen. If the user has chosen a number less than 1 or greater than 12, by default print an error statement to the screen.");
		textL7T3e.setFill(Color.BLACK);
		TextFlow textflowL7T3 = new TextFlow(textL7T3a, textL7T3b, textL7T3c, textL7T3d, textL7T3e);
		textflowL7T3.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*30))
		{
			textflowL7T3.setVisible(false);
			textflowL7T3.setManaged(false);
		}//end if
		vbxL7.getChildren().add(textflowL7T3);

		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput3 = new Label("Your code: ");
		lblInput3.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL7T3 = new TextArea();
		taUserInputL7T3.setPrefSize(taWidth, taLineHeight*48);
		taUserInputL7T3.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL7T3.setText("public class L7T3Response\n{\n\tpublic static void main()\n\t{\n\n\t}//end main method\n}//end L7T3Response class");
		Button btnSubmitL7T3 = new Button("Submit");
		btnSubmitL7T3.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL7T3Input = new HBox(GAP, lblInput3, taUserInputL7T3, btnSubmitL7T3);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*30))
		{
			hbxL7T3Input.setVisible(false);
			hbxL7T3Input.setManaged(false);
		}//end if
		vbxL7.getChildren().add(hbxL7T3Input);
		//for user's output
		Label lblOutput3 = new Label("Output:     ");
		lblOutput3.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL7T3.setPrefSize(taWidth, taLineHeight);
		HBox hbxL7T3Output = new HBox(GAP, lblOutput3, taUserOutputL7T3);
		TextField txtRunInputL7T3 = new TextField("");
		Label lblIndentT1a = new Label("\t\t\t    ");
		HBox hbxRunInputL7T3 = new HBox(GAP, lblIndentT1a, txtRunInputL7T3);
		txtRunInputL7T3.setVisible(false);
		txtRunInputL7T3.setPrefSize(taWidth, taLineHeight);
		txtRunInputL7T3.setStyle("-fx-text-fill: green; -fx-font-size: "+TEXTAREA_FONT);
		TextField txtRunInputL7T3Value = new TextField("");
		txtRunInputL7T3Value.setPrefSize(taWidth, taLineHeight);
		if(txtRunInputL7T3Value.getText().equals(""))
		{
			txtRunInputL7T3Value.setVisible(false);
		}
		Label lblIndentT1b = new Label("\t\t\t    ");
		HBox hbxRunInputL7T3Value = new HBox(GAP, lblIndentT1b, txtRunInputL7T3Value);
		VBox vbxL7T3Output = new VBox(0);
		vbxL7T3Output.setPadding(new Insets(GAP, GAP, GAP, GAP));
		vbxL7T3Output.getChildren().addAll(hbxL7T3Output, hbxRunInputL7T3, hbxRunInputL7T3Value);
		vbxL7.getChildren().add(vbxL7T3Output);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*30))
		{
			vbxL7T3Output.setVisible(false);
			vbxL7T3Output.setManaged(false);
		}//end if
		//for user's result
		Label lblCriteria3 = new Label("Criteria");
		CheckBox cbL7T3a = new CheckBox("month is created as an integer variable and initialized to 0");
		cbL7T3a.setDisable(true);
		CheckBox cbL7T3b = new CheckBox("A question telling the user what to input is printed to the screen");
		cbL7T3b.setDisable(true);
		CheckBox cbL7T3c = new CheckBox("Switch case statement was used correctly and includes each case and the default for all values of month");
		cbL7T3c.setDisable(true);
		CheckBox cbL7T3d = new CheckBox("Output is the month based off of what month the user selected or the default error statement");
		cbL7T3d.setDisable(true);
		HBox hbxL7T3Checklist = new HBox(GAP, lblCriteria3, cbL7T3a, cbL7T3b, cbL7T3c, cbL7T3d);
		hbxL7T3Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria3.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*30))
		{
			hbxL7T3Checklist.setVisible(false);
			hbxL7T3Checklist.setManaged(false);
		}//end if
		vbxL7.getChildren().add(hbxL7T3Checklist);

		//creating and adding a label to notify user that they have completed the current lesson
		Label lblL7Done = new Label("Congratulations, "+user.getfName()+"! You have finished Lesson #5: The Math and Random Classes.\n" + 
				"Click one of the buttons below to either restart this lesson, choose another lesson from the menu, go to the previous lesson, or continue with the next lesson.");
		lblL7Done.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*31))
		{
			lblL7Done.setVisible(false);
			lblL7Done.setManaged(false);
		}//end if
		vbxL7.getChildren().add(lblL7Done);

		//creating and adding the buttons at the bottom of the screen
		Button btnPreviousLesson = new Button("Previous Lesson");
		btnPreviousLesson.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnPreviousLesson.setOnAction(event -> lesson6(myStage));
		Button btnLessonMenu = new Button("Lesson Menu");
		btnLessonMenu.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnLessonMenu.setOnAction(event -> lessonMenu(myStage));
		Button btnExit = new Button("Exit");
		btnExit.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnExit.setOnAction(event -> 
		{
			try
			{
				exitProgram();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		});
		Button btnRestartL7 = new Button("Restart Lesson");
		btnRestartL7.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnRestartL7.setOnAction(event -> lesson7(myStage));
		Button btnNextLesson = new Button("Next Lesson");
		btnNextLesson.setStyle("-fx-font-size: "+CONTROL_FONT);
		if(user.getProgressLevel()<(progressIncrease*31))
		{
			btnNextLesson.setDisable(true);
		}//end if
		btnNextLesson.setOnAction(event -> lesson8(myStage));
		HBox hbxL7Controls = new HBox(GAP, btnPreviousLesson, btnLessonMenu, btnExit, btnRestartL7, btnNextLesson);
		vbxL7.getChildren().add(hbxL7Controls);	

		//determining what to do when the submit button for L7T1 is clicked
		btnSubmitL7T1.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			int checkboxSelectedCounterL7T1=0;
			trialCounter[6][0]++;

			//creating the array to store user's output and adding user input to the userResponse array
			String [] outputL7T1=new String[2];
			userResponse[6][0][trialCounter[6][0]]=taUserInputL7T1.getText();

			//determining and setting the output that the user will see based on their submission
			try
			{
				outputL7T1=CheckJava.submitL7T1(taUserInputL7T1, TEXTAREA_FONT);
			}
			catch (ScriptException e)
			{
				e.printStackTrace();
			}//end try catch
			taUserOutputL7T1.setText(outputL7T1[0]);
			taUserOutputL7T1.setStyle(outputL7T1[1]);

			//resetting all checkboxes in this task to be deselected
			cbL7T1a.setSelected(false);
			cbL7T1b.setSelected(false);
			cbL7T1c.setSelected(false);

			//if statements to check the user's inputed code and the generated output and make the correct checkboxes for this task selected
			if(!taUserOutputL7T1.getText().contains("You must create and initialize an integer variable called a on the second line of code.") && !taUserOutputL7T1.getText().contains("You must create and initialize an integer variable called b on the third line of code."))
			{
				cbL7T1a.setSelected(true);
				checkboxSelectedCounterL7T1++;
			}//end if for first checkbox
			if(!taUserOutputL7T1.getText().contains("Don't forget to add the if statement with a comparison for the values of a and b starting on the fourth line of code.") && !taUserOutputL7T1.getText().contains("Don't forget to add the else if statement with a comparison for the values of a and b starting on the eighth line of code.") && !taUserOutputL7T1.getText().contains("Don't forget to add the else statement with a comparison for the values of a and b starting on the twelfth line of code."))
			{
				cbL7T1b.setSelected(true);
				checkboxSelectedCounterL7T1++;
			}//end if for second checkbox
			if(!taUserOutputL7T1.getText().startsWith("Error!"))
			{
				cbL7T1c.setSelected(true);
				checkboxSelectedCounterL7T1++;
			}//end if for third checkbox
			//when the user has successfully completed the current task and is ready for the next task
			if(checkboxSelectedCounterL7T1==3)
			{
				//setting the next task as visible
				textflowL7T2.setVisible(true);
				textflowL7T2.setManaged(true);
				hbxL7T2Input.setVisible(true);
				hbxL7T2Input.setManaged(true);
				hbxL7T2Output.setVisible(true);
				hbxL7T2Output.setManaged(true);
				hbxL7T2Checklist.setVisible(true);
				hbxL7T2Checklist.setManaged(true);

				//changing the progress value of the progress bar if this is the user's first time doing the current task
				if(user.getProgressLevel()<(progressIncrease*29))
				{
					pbsUserProgress.setProgress((progressIncrease*29));
					user.setProgressLevel(pbsUserProgress.getProgress());
				}//end inside if
			}//end outside if
		});//end setOnAction for btnSubmitL7T1

		//determining what to do when the submit button for L7T2 is clicked
		btnSubmitL7T2.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			int checkboxSelectedCounterL7T2=0;
			trialCounter[6][1]++;
			iScoreCounter=0;

			//creating the array to store user's output and adding user input to the userResponse array
			String [] outputL7T2=new String[3];
			userResponse[6][1][trialCounter[6][1]]=taUserInputL7T2.getText();
			String [] runInputL7T2 = new String [2];
			txtRunInputL7T2a.setVisible(false);
			txtRunInputL7T2a.setText("");
			txtRunInputL7T2a.setEditable(true);
			taUserOutputL7T2b.setVisible(false);
			taUserOutputL7T2b.setEditable(false);
			txtRunInputL7T2b.setVisible(false);
			txtRunInputL7T2b.setText("");
			txtRunInputL7T2b.setEditable(true);
			taUserOutputL7T2c.setVisible(false);
			taUserOutputL7T2c.setEditable(false);
			txtRunInputL7T2c.setVisible(false);
			txtRunInputL7T2c.setText("");
			txtRunInputL7T2c.setEditable(true);
			taUserOutputL7T2d.setVisible(false);
			taUserOutputL7T2d.setEditable(false);

			//determining and setting the output that the user will see based on their submission
			try
			{
				outputL7T2=CheckJava.submitL7T2(taUserInputL7T2, TEXTAREA_FONT);
			}
			catch (ScriptException e)
			{
				e.printStackTrace();
			}//end try catch
			taUserOutputL7T2a.setText(outputL7T2[0]);
			taUserOutputL7T2a.setStyle(outputL7T2[3]);
			taUserOutputL7T2b.setText(outputL7T2[1]);
			taUserOutputL7T2b.setStyle(outputL7T2[3]);
			taUserOutputL7T2c.setText(outputL7T2[2]);
			taUserOutputL7T2c.setStyle(outputL7T2[3]);
			taUserOutputL7T2d.setStyle(outputL7T2[3]);

			//resetting all checkboxes in this task to be deselected
			cbL7T2a.setSelected(false);
			cbL7T2b.setSelected(false);
			cbL7T2c.setSelected(false);
			cbL7T2d.setSelected(false);
			cbL7T2e.setSelected(false);

			if(taUserInputL7T2.getText().contains("String userResponse1;") && taUserInputL7T2.getText().contains("String userResponse2;") && taUserInputL7T2.getText().contains("String userResponse3;"))
			{
				cbL7T2a.setSelected(true);
			}//end if for first checkbox
			if(!taUserOutputL7T2a.getText().startsWith("Error!") && !taUserOutputL7T2a.getText().contains("correctResponse"))
			{
				cbL7T2b.setSelected(true);
			}//end if for second checkbox
			if(!taUserOutputL7T2a.getText().startsWith("Error!") && !taUserOutputL7T2a.getText().contains("scoreCounter"))
			{
				cbL7T2c.setSelected(true);
			}//end if for third checkbox
			if(!taUserOutputL7T2a.getText().contains("Don't forget to tell/ask user what they should input") && !taUserOutputL7T2a.getText().contains("Don't forget to print to the screen and tell/ask user what they should input")) 
			{
				cbL7T2d.setSelected(true);
			}//end if for fourth checkbox
			if(!taUserOutputL7T2a.getText().contains("Don't forget to check if the user has answered the")) 
			{
				cbL7T2e.setSelected(true);
			}//end if for fifth checkbox

			if(!taUserOutputL7T2a.getText().startsWith("Error!"))
			{
				txtRunInputL7T2a.setVisible(true);

				txtRunInputL7T2a.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event)
					{
						if(txtRunInputL7T2a.getText().equalsIgnoreCase("Ottawa"))
						{
							iScoreCounter++;
						}


						if(!taUserOutputL7T2a.getText().startsWith("Error!"))
						{
							taUserOutputL7T2b.setVisible(true);
							txtRunInputL7T2b.setEditable(true);
							txtRunInputL7T2b.setVisible(true);
							txtRunInputL7T2b.setOnAction(new EventHandler<ActionEvent>()
							{
								@Override
								public void handle(ActionEvent event)
								{
									if(txtRunInputL7T2b.getText().equalsIgnoreCase("Paris"))
									{
										iScoreCounter++;
									}
									taUserOutputL7T2c.setVisible(true);
									txtRunInputL7T2c.setEditable(true);
									txtRunInputL7T2c.setVisible(true);
									txtRunInputL7T2c.setOnAction(new EventHandler<ActionEvent>()
									{
										@Override
										public void handle(ActionEvent event)
										{
											if(txtRunInputL7T2c.getText().equalsIgnoreCase("Dublin"))
											{
												iScoreCounter++;
											}
											taUserOutputL7T2d.setVisible(true);
											taUserOutputL7T2d.setText(iScoreCounter+"/3");
										}//end inside handle method
									});//end setOnAction method for txtRunInputL7T2c
								}//end inside handle method
							});//end setOnAction method for txtRunInputL7T2b
						}//end if

						else
						{
							taUserOutputL7T2a.setText("Error!\n- You must assign numOfFamilyMembers as an integer.");
							taUserOutputL7T2a.setStyle("-fx-text-fill: red; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
							txtRunInputL7T2a.setVisible(false);
						}//end else

						if(cbL7T2a.isSelected() && cbL7T2b.isSelected() && cbL7T2c.isSelected() && cbL7T2d.isSelected() && cbL7T2e.isSelected())
						{
							//setting the next task as visible
							textflowL7T3.setVisible(true);
							textflowL7T3.setManaged(true);
							hbxL7T3Input.setVisible(true);
							hbxL7T3Input.setManaged(true);
							hbxL7T3Output.setVisible(true);
							hbxL7T3Output.setManaged(true);
							hbxL7T3Checklist.setVisible(true);
							hbxL7T3Checklist.setManaged(true);

							//changing the progress value of the progress bar if this is the user's first time doing the current task
							if(user.getProgressLevel()<(progressIncrease*30))
							{
								pbsUserProgress.setProgress((progressIncrease*30));
								user.setProgressLevel(pbsUserProgress.getProgress());
							}//end inside if
						}//end outside if
					}//end outside handle method
				});//end setOnAction method for txtRunInputL7T2a
			}//end if

		});//end setOnAction for btnSubmitL7T2

		//determining what to do when the submit button for L7T3 is clicked
		btnSubmitL7T3.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			int checkboxSelectedCounterL7T3=0;
			trialCounter[6][2]++;

			//creating the array to store user's output and adding user input to the userResponse array
			userResponse[6][2][trialCounter[6][2]]=taUserInputL7T3.getText();
			String [] runInputL7T3 = new String [2];
			txtRunInputL7T3.setText("");
			txtRunInputL7T3.setEditable(true);
			txtRunInputL7T3Value.setText("");
			txtRunInputL7T3Value.setVisible(false);
			String sRunInputL7T3="";


			//determining and setting the output that the user will see based on their submission
			try
			{
				outputL7T3=CheckJava.submitL7T3(taUserInputL7T3, TEXTAREA_FONT);
			}
			catch (ScriptException e)
			{
				e.printStackTrace();
			}//end try catch
			taUserOutputL7T3.setText(outputL7T3[0]);
			taUserOutputL7T3.setStyle(outputL7T3[2]);

			//resetting all checkboxes in this task to be deselected
			cbL7T3a.setSelected(false);
			cbL7T3b.setSelected(false);
			cbL7T3c.setSelected(false);

			if(!taUserOutputL7T3.getText().startsWith("Error!"))
			{
				txtRunInputL7T3.setVisible(true);
				txtRunInputL7T3Value.setText(txtRunInputL7T3.getText());
				txtRunInputL7T3Value.setStyle(outputL7T3[2]);

				txtRunInputL7T3.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event)
					{
						int iMonth=Integer.parseInt(txtRunInputL7T3.getText());

						switch(iMonth)
						{
						case 1:
							txtRunInputL7T3Value.setText("January");
							break;
						case 2:
							txtRunInputL7T3Value.setText("February");
							break;
						case 3:
							txtRunInputL7T3Value.setText("March");
							break;
						case 4:
							txtRunInputL7T3Value.setText("April");
							break;
						case 5:
							txtRunInputL7T3Value.setText("May");
							break;
						case 6:
							txtRunInputL7T3Value.setText("June");
							break;
						case 7:
							txtRunInputL7T3Value.setText("July");
							break;
						case 8:
							txtRunInputL7T3Value.setText("August");
							break;
						case 9:
							txtRunInputL7T3Value.setText("September");
							break;
						case 10:
							txtRunInputL7T3Value.setText("October");
							break;
						case 11:
							txtRunInputL7T3Value.setText("November");
							break;
						case 12:
							txtRunInputL7T3Value.setText("December");
							break;
						default:
							txtRunInputL7T3Value.setText(outputL7T3[1]);
							break;
						}

						txtRunInputL7T3Value.setStyle(outputL7T3[2]);
						txtRunInputL7T3Value.setVisible(true);
						if(!taUserOutputL7T3.getText().contains("You must create an integer variable called month on the second line of code and initialize it to 0."))
						{
							cbL7T3a.setSelected(true);
						}//end if for first checkbox
						if(!taUserOutputL7T3.getText().contains("tell/ask user what they should input (the month number)"))
						{
							cbL7T3b.setSelected(true);
						}//end if for second checkbox
						if(!taUserOutputL7T3.getText().contains("Don't forget to add the case") && !taUserOutputL7T3.getText().contains("Don't forget to print the correct month") && !taUserOutputL7T3.getText().contains("Don't forget to print an error statement when the user has entered an invalid number for the variable month.") && !taUserOutputL7T3.getText().contains("Don't forget to add the default to print an error statement when the user has entered a number >1 or <12 for the month number.") && !taUserOutputL7T3.getText().contains("\"Don't forget to begin your switch case statement on the fifth line of code."))
						{
							cbL7T3c.setSelected(true);
						}//end if for third checkbox

						if(!taUserOutputL7T3.getText().startsWith("Error!"))
						{
							cbL7T3d.setSelected(true);
						}//end if for third checkbox

						if(cbL7T3a.isSelected() && cbL7T3b.isSelected() && cbL7T3c.isSelected() && cbL7T3d.isSelected())
						{
							//setting the next task as visible
							lblL7Done.setVisible(true);
							lblL7Done.setManaged(true);

							//changing the progress value of the progress bar if this is the user's first time doing the current task
							if(user.getProgressLevel()<(progressIncrease*31))
							{
								pbsUserProgress.setProgress((progressIncrease*31));
								user.setProgressLevel(pbsUserProgress.getProgress());
							}//end inside if
						}//end outside if
					}//end handle method
				});
			}//end if
		});//end setOnAction for btnSubmitL7T3

		//creating and setting the scroll pane for this lesson
		ScrollPane spL7 = new ScrollPane();
		spL7.setContent(vbxL7);

		//creating the scene
		Scene sceneL7 = new Scene(spL7);

		lastScene="lesson7"; //setting the previous scene to this scene

		//setting/showing the stage
		myStage.getIcons().add(new Image("FromOneToAnotherLogo.png"));
		myStage.setTitle("From C++ to Java - ... - Lesson #7");
		myStage.setScene(sceneL7);
		myStage.centerOnScreen();
		myStage.show();
	}//end lesson7 method


	/* Method Author: Brooke Cronin
	 * Method Name: lesson8
	 * Description: Creates the scene for the fifth lesson. Allows the user to view and attempt all of the tasks and get feedback after completing each task.
	 * Parameters: Stage myStage
	 * Returns: N/A
	 * Throws: NullPointerException
	 */
	
	public void lesson8(Stage myStage) throws NullPointerException
	{
		myStage.setOnHiding( event -> {try {
			exitProgram();
		} catch (IOException e) {
			
			e.printStackTrace();
		}} );
		Random rand = new Random();
		
		//resetting the text in all of the user output text areas and disabling them so that the user can't change what appears in them
		TextArea taUserOutputL8T1 = new TextArea();
		taUserOutputL8T1.setText("Not submitted yet");
		taUserOutputL8T1.setEditable(false);
		taUserOutputL8T1.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
		TextArea taUserOutputL8T2 = new TextArea();
		taUserOutputL8T2.setText("Not submitted yet");
		taUserOutputL8T2.setEditable(false);
		taUserOutputL8T2.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
		TextArea taUserOutputL8T3 = new TextArea();
		taUserOutputL8T3.setText("Not submitted yet");
		taUserOutputL8T3.setEditable(false);
		taUserOutputL8T3.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
		TextArea taUserOutputL8T4a = new TextArea();
		taUserOutputL8T4a.setText("Not submitted yet");
		taUserOutputL8T4a.setEditable(false);
		taUserOutputL8T4a.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
	
		//creating the vertical box for lesson 5 screen
		VBox vbxL8 = new VBox(GAP);
		vbxL8.setPadding(new Insets(GAP, GAP, GAP, GAP));
	
		//adding the user's progress bar to the vertical box
		pbsUserProgress.setMaxWidth(Double.MAX_VALUE);
		vbxL8.getChildren().add(pbsUserProgress);
	
		//creating, setting, and adding a header Label
		AnchorPane apLesson8TitleHeader = new AnchorPane();
		Label lblLesson8TitleHeader = new Label("Lesson #8: Loops");
		lblLesson8TitleHeader.setStyle("-fx-text-fill: black; -fx-font-size: "+LESSON_HEADER_FONT);
		lblLesson8TitleHeader.setMaxWidth(Double.MAX_VALUE);
		AnchorPane.setLeftAnchor(lblLesson8TitleHeader, 0.0);
		AnchorPane.setRightAnchor(lblLesson8TitleHeader, 0.0);
		lblLesson8TitleHeader.setAlignment(Pos.CENTER);
		apLesson8TitleHeader.getChildren().add(lblLesson8TitleHeader);
		vbxL8.getChildren().add(apLesson8TitleHeader);
	
		//adding the name of the current task
		Label lblL8T1Header = new Label("\nTask #1: Printing 1-10");
		lblL8T1Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		vbxL8.getChildren().add(lblL8T1Header);
	
		//creating, setting, and adding the first task instructions
		Text textL8T1a = new Text("\n\nYou know of two ways to print every number from 0 to 5 to the screen in Java.");
		textL8T1a.setFill(Color.BLACK);
		Text textL8T1b = new Text("\nSystem.out.println(\"0\");\nSystem.out.println(\"1\");\nSystem.out.println(\"2\");\nSystem.out.println(\"3\");\\nSystem.out.println(\"4\");\\nSystem.out.println(\"5\");");
		textL8T1b.setFill(Color.BLUE);
		Text textL8T1c = new Text("\nor");
		textL8T1c.setFill(Color.BLACK);
		Text textL8T1d = new Text("\nSystem.out.println(\"0\\n1\\n2\\n3\\n4\\n5\");");
		textL8T1d.setFill(Color.BLUE);
		Text textL8T1e = new Text("\nThis does work, but what about when you have to print more numbers (e.g. from 0 to 10000)? Doing either of the two ways listed above is doable, but would take a long time. \nIn this case, you would probably use a for loop. There are also for loops in Python, but they work a little bit differently in Java. \nBelow is how you could print numbers from 0 to 5 (each number on a seperate line) in Python.");
		textL8T1e.setFill(Color.BLACK);
		Text textL8T1f = new Text("\nfor ");
		textL8T1f.setFill(Color.DARKVIOLET);
		Text textL8T1g = new Text("i");
		textL8T1g.setFill(Color.rgb(121, 76, 52));
		Text textL8T1h = new Text(" in range(0,6,+1): \n\tprint(");
		textL8T1h.setFill(Color.DARKVIOLET);
		Text textL8T1i = new Text("i");
		textL8T1i.setFill(Color.rgb(121, 76, 52));
		Text textL8T1j = new Text(")");
		textL8T1j.setFill(Color.DARKVIOLET);
		Text textL8T1k = new Text("\nIn Java, it is slightly different.");
		textL8T1k.setFill(Color.BLACK);
		Text textL8T1l = new Text("\nfor(int ");
		textL8T1l.setFill(Color.BLUE);
		Text textL8T1m = new Text("i");
		textL8T1m.setFill(Color.rgb(121, 76, 52));
		Text textL8T1n = new Text("=0; ");
		textL8T1n.setFill(Color.BLUE);
		Text textL8T1o = new Text("i");
		textL8T1o.setFill(Color.rgb(121, 76, 52));
		Text textL8T1p = new Text("<=5; ");
		textL8T1p.setFill(Color.BLUE);
		Text textL8T1q = new Text("i");
		textL8T1q.setFill(Color.rgb(121, 76, 52));
		Text textL8T1r = new Text("++)\n{\nSystem.out.println(");
		textL8T1r.setFill(Color.BLUE);
		Text textL8T1s = new Text("i");
		textL8T1s.setFill(Color.rgb(121, 76, 52));
		Text textL8T1t = new Text(");\n}");
		textL8T1t.setFill(Color.BLUE);
		Text textL8T1u = new Text("\nFor this kind of loop, the first part (");
		textL8T1u.setFill(Color.BLACK);
		Text textL8T1v = new Text("int ");
		textL8T1v.setFill(Color.BLUE);
		Text textL8T1w = new Text("i");
		textL8T1w.setFill(Color.rgb(121, 76, 52));
		Text textL8T1x = new Text("=0; ");
		textL8T1x.setFill(Color.BLUE);
		Text textL8T1y = new Text("), it represents the starting position of the variable that will change after each iteration (in this case, ");
		textL8T1y.setFill(Color.BLACK);
		Text textL8T1z = new Text("i");
		textL8T1z.setFill(Color.rgb(121, 76, 52));
		Text textL8T1aa= new Text("). \nFor the next part (");
		textL8T1aa.setFill(Color.BLACK);
		Text textL8T1ab = new Text("i");
		textL8T1ab.setFill(Color.rgb(121, 76, 52));
		Text textL8T1ac = new Text("<=5;");
		textL8T1ac.setFill(Color.BLUE);
		Text textL8T1ad = new Text("), it represents what the conditions on the variable to stay inside the loop (in this case, ");
		textL8T1ad.setFill(Color.BLACK);
		Text textL8T1ae = new Text("i");
		textL8T1ae.setFill(Color.rgb(121, 76, 52));
		Text textL8T1af = new Text(" must be less than or equal to 5). \nThe last part (");
		textL8T1af.setFill(Color.BLACK);
		Text textL8T1ag = new Text("i");
		textL8T1ag.setFill(Color.rgb(121, 76, 52));
		Text textL8T1ah = new Text("++");
		textL8T1ah.setFill(Color.BLUE);
		Text textL8T1ai = new Text(") shows how much the variable should increase (or decrease) at the end of the iteration. In this task, you will use a for loop to print all numbers from 1 to 10 (each number is on a line).");
		textL8T1ai.setFill(Color.BLACK);
		TextFlow textflowL8T1 = new TextFlow(textL8T1a, textL8T1b, textL8T1c, textL8T1d, textL8T1e, textL8T1f, textL8T1g, textL8T1h, textL8T1i, textL8T1j, textL8T1k, textL8T1l, textL8T1m, textL8T1n, textL8T1o, textL8T1p, textL8T1q, textL8T1r, textL8T1s, textL8T1t, textL8T1u, textL8T1v, textL8T1w, textL8T1x, textL8T1y, textL8T1z, textL8T1aa, textL8T1ab, textL8T1ac, textL8T1ad, textL8T1ae, textL8T1af, textL8T1ag, textL8T1ah, textL8T1ai);
		textflowL8T1.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		vbxL8.getChildren().add(textflowL8T1);
	
		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput1 = new Label("Your code: ");
		lblInput1.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL8T1 = new TextArea();
		taUserInputL8T1.setPrefSize(taWidth, taLineHeight);
		taUserInputL8T1.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL8T1.setText("public class L8T1Response\n{\n\tpublic static void main()\n\t{\n\n\t}//end main method\n}//end L8T1Response class");
		Button btnSubmitL8T1 = new Button("Submit");
		btnSubmitL8T1.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL8T1Input = new HBox(GAP, lblInput1, taUserInputL8T1, btnSubmitL8T1);
		vbxL8.getChildren().add(hbxL8T1Input);
		//for user's output
		Label lblOutput1 = new Label("Output:     ");
		lblOutput1.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL8T1.setPrefSize(taWidth, taLineHeight);
		HBox hbxL8T1Output = new HBox(GAP, lblOutput1, taUserOutputL8T1);
		vbxL8.getChildren().add(hbxL8T1Output);
		//for user's result
		Label lblCriteria1 = new Label("Criteria");
		CheckBox cbL8T1a = new CheckBox("Begins with System.out.println(");
		cbL8T1a.setDisable(true);
		CheckBox cbL8T1b = new CheckBox("Output is a decimal value of an exponent calculation");
		cbL8T1b.setDisable(true);
		CheckBox cbL8T1c = new CheckBox("Code contains a call to the Math.pow() method");
		cbL8T1c.setDisable(true);
		CheckBox cbL8T1d = new CheckBox("Ends with );");
		cbL8T1d.setDisable(true);
		HBox hbxL8T1Checklist = new HBox(GAP, lblCriteria1, cbL8T1a, cbL8T1b, cbL8T1c, cbL8T1d);
		hbxL8T1Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria1.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		vbxL8.getChildren().add(hbxL8T1Checklist);
	
		//adding the name of the current task
		Label lblL8T2Header = new Label("\n\n\nTask #2: For loops and Seperators");
		lblL8T2Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		if(user.getProgressLevel()<(progressIncrease*32))
		{
			lblL8T2Header.setVisible(false);
			lblL8T2Header.setManaged(false);
		}//end if
		vbxL8.getChildren().add(lblL8T2Header);
	
		//creating, setting, and adding the second task instructions
		Text textL8T2a = new Text("\n\nWhat about if you want to start with a larger number and end with a smaller number? What about only printing certain numbers within a specific range and seperated by a space rather than a new line? \nIn Python, to print all even numbers from 10 to 0, it would look like something similar to below.");
		textL8T2a.setFill(Color.BLACK);
		Text textL8T2b = new Text("\nfor ");
		textL8T2b.setFill(Color.DARKVIOLET);
		Text textL8T2c = new Text("i");
		textL8T2c.setFill(Color.rgb(121, 76, 52));
		Text textL8T2d = new Text(" in range(10, -1, -2):\nprint(");
		textL8T2d.setFill(Color.DARKVIOLET);
		Text textL8T2e = new Text("i");
		textL8T2e.setFill(Color.rgb(121, 76, 52));
		Text textL8T2f = new Text(", end=\" \")");
		textL8T2f.setFill(Color.DARKVIOLET);
		Text textL8T2g = new Text("\nIn Java, it is a little bit different than in Python.");
		textL8T2g.setFill(Color.BLACK);
		Text textL8T2h = new Text("\nfor(int ");
		textL8T2h.setFill(Color.BLUE);
		Text textL8T2i = new Text("i");
		textL8T2i.setFill(Color.rgb(121, 76, 52));
		Text textL8T2j = new Text("=10;");
		textL8T2j.setFill(Color.BLUE);
		Text textL8T2k = new Text("i");
		textL8T2k.setFill(Color.rgb(121, 76, 52));
		Text textL8T2l = new Text(">=0;");
		textL8T2l.setFill(Color.BLUE);
		Text textL8T2m = new Text("i");
		textL8T2m.setFill(Color.rgb(121, 76, 52));
		Text textL8T2n = new Text("-=2)\n{\nSystem.out.print(");
		textL8T2n.setFill(Color.BLUE);
		Text textL8T2o = new Text("i");
		textL8T2o.setFill(Color.rgb(121, 76, 52));
		Text textL8T2p = new Text("+\" \")\n}");
		textL8T2p.setFill(Color.BLUE);
		Text textL8T2q = new Text("\nIn this task, you will print all odd numbers between 100 and 70, and separate the numbers with a comma and a space.");
		textL8T2q.setFill(Color.BLACK);
		TextFlow textflowL8T2 = new TextFlow(textL8T2a, textL8T2b, textL8T2c, textL8T2d, textL8T2e, textL8T2f, textL8T2g, textL8T2h, textL8T2i, textL8T2j, textL8T2k, textL8T2l, textL8T2m, textL8T2n, textL8T2o, textL8T2p, textL8T2q);
		textflowL8T2.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*32))
		{
			textflowL8T2.setVisible(false);
			textflowL8T2.setManaged(false);
		}//end if
		vbxL8.getChildren().add(textflowL8T2);
	
		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput2 = new Label("Your code: ");
		lblInput2.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL8T2 = new TextArea();
		taUserInputL8T2.setPrefSize(taWidth, taLineHeight*4);
		taUserInputL8T2.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL8T2.setText("public class L8T2Response\n{\n\tpublic static void main()\n\t{\n\n\t}//end main method\n}//end L8T2Response class");
		Button btnSubmitL8T2 = new Button("Submit");
		btnSubmitL8T2.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL8T2Input = new HBox(GAP, lblInput2, taUserInputL8T2, btnSubmitL8T2);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*32))
		{
			hbxL8T2Input.setVisible(false);
			hbxL8T2Input.setManaged(false);
		}//end if
		vbxL8.getChildren().add(hbxL8T2Input);
		//for user's output
		Label lblOutput2 = new Label("Output:     ");
		lblOutput2.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL8T2.setPrefSize(taWidth, taLineHeight);
		HBox hbxL8T2Output = new HBox(GAP, lblOutput2, taUserOutputL8T2);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*32))
		{
			hbxL8T2Output.setVisible(false);
			hbxL8T2Output.setManaged(false);
		}//end if
		vbxL8.getChildren().add(hbxL8T2Output);
		//for user's result
		Label lblCriteria2 = new Label("Criteria");
		CheckBox cbL8T2a = new CheckBox("num was created as a double variable and initialized to a double value");
		cbL8T2a.setDisable(true);
		CheckBox cbL8T2b = new CheckBox("squareRoot was created as a double variable and initialized correctly using the correct method from the Math class");
		cbL8T2b.setDisable(true);
		CheckBox cbL8T2c = new CheckBox("Output is the value of squareRoot");
		cbL8T2c.setDisable(true);
		HBox hbxL8T2Checklist = new HBox(GAP, lblCriteria2, cbL8T2a, cbL8T2b, cbL8T2c);
		hbxL8T2Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria2.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*32))
		{
			hbxL8T2Checklist.setVisible(false);
			hbxL8T2Checklist.setManaged(false);
		}//end if
		vbxL8.getChildren().add(hbxL8T2Checklist);
	
		//adding the name of the current task
		Label lblL8T3Header = new Label("\n\n\nTask #3: Where is the 5?");
		lblL8T3Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		if(user.getProgressLevel()<(progressIncrease*33))
		{
			lblL8T3Header.setVisible(false);
			lblL8T3Header.setManaged(false);
		}//end if
		vbxL8.getChildren().add(lblL8T3Header);
	
		//creating, setting, and adding the third task instructions
		Text textL8T3a = new Text("\n\nFor loops are not the only type of loop. Another type is a while loop, which is sometimes better than a for loop, depending on the code. \nWhile loops in Java are very similar to while loops in Python, except for Java, the condition after the word while is surrounded in parenthesis, and the code for the while loop is surrounded in curly brackets. \nFor this task, you will be using a while loop. \nIn this task, you will create an integer variable called ");
		textL8T3a.setFill(Color.BLACK);
		Text textL8T3b = new Text("dice");
		textL8T3b.setFill(Color.rgb(121, 76, 52));
		Text textL8T3c = new Text(" and initialize it to a random number between 1 and 6.\nAfter that you will create a while loop that checks if the value of ");
		textL8T3c.setFill(Color.BLACK);
		Text textL8T3d = new Text("dice");
		textL8T3d.setFill(Color.rgb(121, 76, 52));
		Text textL8T3e = new Text(" is 5. If the value is not 5, the value will be printed to the screen in the following format \n\"You have rolled a \"+");
		textL8T3e.setFill(Color.BLACK);
		Text textL8T3f = new Text("dice");
		textL8T3f.setFill(Color.rgb(121, 76, 52));
		Text textL8T3g = new Text("\nThen the dice will be rerolled. If the value is 5, then the code will exit the loop.");
		textL8T3g.setFill(Color.BLACK);
		TextFlow textflowL8T3 = new TextFlow(textL8T3a, textL8T3b, textL8T3c, textL8T3d, textL8T3e, textL8T3f, textL8T3g);
		textflowL8T3.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*33))
		{
			textflowL8T3.setVisible(false);
			textflowL8T3.setManaged(false);
		}//end if
		vbxL8.getChildren().add(textflowL8T3);
	
		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput3 = new Label("Your code: ");
		lblInput3.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL8T3 = new TextArea();
		taUserInputL8T3.setPrefSize(taWidth, taLineHeight*7);
		taUserInputL8T3.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL8T3.setText("public class L8T3Response\n{\n\tpublic static void main()\n\t{\n\n\t}//end main method\n}//end L8T3Response class");
		Button btnSubmitL8T3 = new Button("Submit");
		btnSubmitL8T3.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL8T3Input = new HBox(GAP, lblInput3, taUserInputL8T3, btnSubmitL8T3);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*33))
		{
			hbxL8T3Input.setVisible(false);
			hbxL8T3Input.setManaged(false);
		}//end if
		vbxL8.getChildren().add(hbxL8T3Input);
		//for user's output
		Label lblOutput3 = new Label("Output:     ");
		lblOutput3.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL8T3.setPrefSize(taWidth, taLineHeight);
		HBox hbxL8T3Output = new HBox(GAP, lblOutput3, taUserOutputL8T3);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*33))
		{
			hbxL8T3Output.setVisible(false);
			hbxL8T3Output.setManaged(false);
		}//end if
		vbxL8.getChildren().add(hbxL8T3Output);
		//for user's result
		Label lblCriteria3 = new Label("Criteria");
		CheckBox cbL8T3a = new CheckBox("degrees, radians, sine, cosine, and tangent are are created as double variables and initialized correctly");
		cbL8T3a.setDisable(true);
		CheckBox cbL8T3b = new CheckBox("The Math class was used to calculate the values of radians, sine, cosine, and tangent");
		cbL8T3b.setDisable(true);
		CheckBox cbL8T3c = new CheckBox("Output is the values of degrees, radians, sine, cosine, and tangent with all values separated by a comma and a space");
		cbL8T3c.setDisable(true);
		HBox hbxL8T3Checklist = new HBox(GAP, lblCriteria3, cbL8T3a, cbL8T3b, cbL8T3c);
		hbxL8T3Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria3.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*33))
		{
			hbxL8T3Checklist.setVisible(false);
			hbxL8T3Checklist.setManaged(false);
		}//end if
		vbxL8.getChildren().add(hbxL8T3Checklist);
	
		//adding the name of the current task
		Label lblL8T4Header = new Label("\n\n\nTask #4: Roll Again?");
		lblL8T4Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		if(user.getProgressLevel()<(progressIncrease*34))
		{
			lblL8T4Header.setVisible(false);
			lblL8T4Header.setManaged(false);
		}//end if
		vbxL8.getChildren().add(lblL8T4Header);
	
		//creating, setting, and adding the fourth task instructions
		Text textL8T4a = new Text("\n\nThere is also a third type of loop called a do while loop. This is very similar to a while loop, except the code inside the loop is always executed at least once and the boolean condition is only checked at the end of the loop. \nBelow is an example, in Java of how to print numbers from 10 to 1 in a do while loop.");
		textL8T4a.setFill(Color.BLACK);
		Text textL8T4b = new Text("\nint ");
		textL8T4b.setFill(Color.BLUE);
		Text textL8T4c = new Text("l");
		textL8T4c.setFill(Color.rgb(121, 76, 52));
		Text textL8T4d = new Text("=10;\ndo\n{\nSystem.out.println(");
		textL8T4d.setFill(Color.BLUE);
		Text textL8T4e = new Text("l");
		textL8T4e.setFill(Color.rgb(121, 76, 52));
		Text textL8T4f = new Text(");\n}\nwhile(");
		textL8T4f.setFill(Color.BLUE);
		Text textL8T4g = new Text("l");
		textL8T4g.setFill(Color.rgb(121, 76, 52));
		Text textL8T4h = new Text(">1);");
		textL8T4h.setFill(Color.BLUE);
		Text textL8T4i = new Text("\nIn this task, you will create two integer variables called ");
		textL8T4i.setFill(Color.BLACK);
		Text textL8T4j = new Text("d1");
		textL8T4j.setFill(Color.rgb(121, 76, 52));
		Text textL8T4k = new Text(" and ");
		textL8T4k.setFill(Color.BLACK);
		Text textL8T4l = new Text("d2");
		textL8T4l.setFill(Color.rgb(121, 76, 52));
		Text textL8T4m = new Text(" (don't initialize them). \nThen, create a third integer variable calledThen, create a character variable called ");
		textL8T4m.setFill(Color.BLACK);
		Text textL8T4n = new Text("rollCounter");
		textL8T4n.setFill(Color.rgb(121, 76, 52));
		Text textL8T4o = new Text("and initialize it to 0. \nAfter that, create a String variable called ");
		textL8T4o.setFill(Color.BLACK);
		Text textL8T4p = new Text("rollAgain");
		textL8T4p.setFill(Color.rgb(121, 76, 52));
		Text textL8T4q = new Text(" and initialize it to \"y\".\nAfter that, create a do while loop to continue until ");
		textL8T4q.setFill(Color.BLACK);
		Text textL8T4r = new Text("rollAgain");
		textL8T4r.setFill(Color.rgb(121, 76, 52));
		Text textL8T4s = new Text("=\"n\" or the user has rolled 3 times. Inside the do while loop, increase the value of ");
		textL8T4s.setFill(Color.BLACK);
		Text textL8T4t = new Text("rollCounter");
		textL8T4t.setFill(Color.rgb(121, 76, 52));
		Text textL8T4u = new Text(" by 1 and then roll");
		textL8T4u.setFill(Color.BLACK);
		Text textL8T4v = new Text("d1");
		textL8T4v.setFill(Color.rgb(121, 76, 52));
		Text textL8T4w = new Text(" and ");
		textL8T4w.setFill(Color.BLACK);
		Text textL8T4x = new Text("d2");
		textL8T4x.setFill(Color.rgb(121, 76, 52));
		Text textL8T4y = new Text(" (must be an integer between 1 and 6), print both values to the screen in the following format,");
		textL8T4y.setFill(Color.BLACK);
		Text textL8T4z = new Text("\n\"You have rolled \"+");
		textL8T4z.setFill(Color.BLUE);
		Text textL8T4aa = new Text("d1");
		textL8T4aa.setFill(Color.rgb(121, 76, 52));
		Text textL8T4ab = new Text("+\" and \"+");
		textL8T4ab.setFill(Color.BLACK);
		Text textL8T4ac= new Text("d2");
		textL8T4ac.setFill(Color.rgb(121, 76, 52));
		Text textL8T4ad = new Text("\nThen ask the user if they would like to roll again (y=yes, anything else=no).");
		textL8T4ad.setFill(Color.BLACK);
		TextFlow textflowL8T4 = new TextFlow(textL8T4a, textL8T4b, textL8T4c, textL8T4d, textL8T4e, textL8T4f, textL8T4g, textL8T4h, textL8T4i, textL8T4j, textL8T4k, textL8T4l, textL8T4m, textL8T4n, textL8T4o, textL8T4p, textL8T4q, textL8T4r, textL8T4s, textL8T4t, textL8T4u, textL8T4v, textL8T4w, textL8T4x, textL8T4y, textL8T4z, textL8T4aa, textL8T4ab, textL8T4ac, textL8T4ad);
		textflowL8T4.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*34))
		{
			textflowL8T4.setVisible(false);
			textflowL8T4.setManaged(false);
		}//end if
		vbxL8.getChildren().add(textflowL8T4);
	
		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput4 = new Label("Your code: ");
		lblInput4.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL8T4 = new TextArea();
		taUserInputL8T4.setPrefSize(taWidth, taLineHeight*4);
		taUserInputL8T4.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL8T4.setText("public class L8T4Response\n{\n\tpublic static void main()\n\t{\n\n\t}//end main method\n}//end L8T4Response class");
		Button btnSubmitL8T4 = new Button("Submit");
		btnSubmitL8T4.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL8T4Input = new HBox(GAP, lblInput4, taUserInputL8T4, btnSubmitL8T4);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*34))
		{
			hbxL8T4Input.setVisible(false);
			hbxL8T4Input.setManaged(false);
		}//end if
		vbxL8.getChildren().add(hbxL8T4Input);
		//for user's output
		Label lblOutput4 = new Label("Output:     ");
		lblOutput4.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL8T4a.setPrefSize(taWidth, taLineHeight);
		HBox hbxL8T4Output = new HBox(GAP, lblOutput4, taUserOutputL8T4a);
		TextField txtRunInputL8T4a = new TextField("");
		txtRunInputL8T4a.setVisible(false);
		txtRunInputL8T4a.setPrefSize(taWidth, taLineHeight);
		txtRunInputL8T4a.setStyle("-fx-text-fill: green; -fx-font-size: "+TEXTAREA_FONT);
		Label lblIndentT4a = new Label("\t\t\t    ");
		HBox hbxRunInputL8T4a = new HBox(GAP, lblIndentT4a, txtRunInputL8T4a);
		TextArea taUserOutputL8T4b = new TextArea("");
		taUserOutputL8T4b.setPrefSize(taWidth, taLineHeight);
		taUserOutputL8T4b.setVisible(false);
		Label lblIndentT4b = new Label("\t\t\t    ");
		HBox hbxUserOutputL8T4b = new HBox(GAP, lblIndentT4b, taUserOutputL8T4b);
		TextField txtRunInputL8T4b = new TextField("");
		txtRunInputL8T4b.setVisible(false);
		txtRunInputL8T4b.setPrefSize(taWidth, taLineHeight);
		txtRunInputL8T4b.setStyle("-fx-text-fill: green; -fx-font-size: "+TEXTAREA_FONT);
		Label lblIndentT4c = new Label("\t\t\t    ");
		HBox hbxRunInputL8T4b = new HBox(GAP, lblIndentT4c, txtRunInputL8T4b);
		TextArea taUserOutputL8T4c = new TextArea("");
		taUserOutputL8T4c.setPrefSize(taWidth, taLineHeight);
		taUserOutputL8T4c.setVisible(false);
		Label lblIndentT4d = new Label("\t\t\t    ");
		HBox hbxUserOutputL8T4c = new HBox(GAP, lblIndentT4d, taUserOutputL8T4c);
		VBox vbxL8T4Output = new VBox(0);
		vbxL8T4Output.setPadding(new Insets(GAP, GAP, GAP, GAP));
		vbxL8T4Output.getChildren().addAll(hbxL8T4Output, hbxRunInputL8T4a, hbxUserOutputL8T4b, hbxRunInputL8T4b, hbxUserOutputL8T4c);
		vbxL8.getChildren().add(vbxL8T4Output);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*34))
		{
			vbxL8T4Output.setVisible(false);
			vbxL8T4Output.setManaged(false);
		}//end if
		//for user's result
		Label lblCriteria4 = new Label("Criteria");
		CheckBox cbL8T4a = new CheckBox("All variables were created and initialized correctly");
		cbL8T4a.setDisable(true);
		CheckBox cbL8T4b = new CheckBox("Both conditiaons for the do while loop were added");
		cbL8T4b.setDisable(true);
		CheckBox cbL8T4c = new CheckBox("Everytime after the die are rolled, their values are printed to the screen in proper format");
		cbL8T4c.setDisable(true);
		CheckBox cbL8T4d = new CheckBox("User is able to reroll both dice up to 3 times");
		cbL8T4d.setDisable(true);
		CheckBox cbL8T4e = new CheckBox("After the first and second roll, a question asking the user wheter or not they want to roll the die again is printed to the screen");
		cbL8T4e.setDisable(true);
		CheckBox cbL8T4f = new CheckBox("The value of rollCounter is updated after every roll");
		cbL8T4f.setDisable(true);
		HBox hbxL8T4Checklist = new HBox(GAP, lblCriteria4, cbL8T4a, cbL8T4b, cbL8T4c, cbL8T4d, cbL8T4e, cbL8T4f);
		hbxL8T4Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria4.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*34))
		{
			hbxL8T4Checklist.setVisible(false);
			hbxL8T4Checklist.setManaged(false);
		}//end if
		vbxL8.getChildren().add(hbxL8T4Checklist);
	
		//creating and adding a label to notify user that they have completed the current lesson
		Label lblL8Done = new Label("Congratulations, "+user.getfName()+"! You have finished Lesson #5: The Math and Random Classes.\n" + 
				"Click one of the buttons below to either restart this lesson, choose another lesson from the menu, go to the previous lesson, or continue with the next lesson.");
		lblL8Done.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*35))
		{
			lblL8Done.setVisible(false);
			lblL8Done.setManaged(false);
		}//end if
		vbxL8.getChildren().add(lblL8Done);
	
		//creating and adding the buttons at the bottom of the screen
		Button btnPreviousLesson = new Button("Previous Lesson");
		btnPreviousLesson.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnPreviousLesson.setOnAction(event -> lesson7(myStage));
		Button btnLessonMenu = new Button("Lesson Menu");
		btnLessonMenu.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnLessonMenu.setOnAction(event -> lessonMenu(myStage));
		Button btnExit = new Button("Exit");
		btnExit.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnExit.setOnAction(event -> 
		{
			try
			{
				exitProgram();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		});
		Button btnRestartL8 = new Button("Restart Lesson");
		btnRestartL8.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnRestartL8.setOnAction(event -> lesson8(myStage));
		Button btnNextLesson = new Button("Next Lesson");
		btnNextLesson.setStyle("-fx-font-size: "+CONTROL_FONT);
		if(user.getProgressLevel()<(progressIncrease*35))
		{
			btnNextLesson.setDisable(true);
		}//end if
		//		btnNextLesson.setOnAction(event -> lesson9(myStage));
		HBox hbxL8Controls = new HBox(GAP, btnPreviousLesson, btnLessonMenu, btnExit, btnRestartL8, btnNextLesson);
		vbxL8.getChildren().add(hbxL8Controls);	
	
		//determining what to do when the submit button for L8T1 is clicked
		btnSubmitL8T1.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			int checkboxSelectedCounterL8T1=0;
			trialCounter[7][0]++;
	
			//creating the array to store user's output and adding user input to the userResponse array
			String [] outputL8T1=new String[2];
			userResponse[7][0][trialCounter[7][0]]=taUserInputL8T1.getText();
	
			//determining and setting the output that the user will see based on their submission
			try
			{
				outputL8T1=CheckJava.submitL8T1(taUserInputL8T1, TEXTAREA_FONT);
			}
			catch (ScriptException e)
			{
				e.printStackTrace();
			}//end try catch
			taUserOutputL8T1.setText(outputL8T1[0]);
			taUserOutputL8T1.setStyle(outputL8T1[1]);
	
			//resetting all checkboxes in this task to be deselected
			cbL8T1a.setSelected(false);
			cbL8T1b.setSelected(false);
			cbL8T1c.setSelected(false);
			cbL8T1d.setSelected(false);
	
			//if statements to check the user's inputed code and the generated output and make the correct checkboxes for this task selected
			if(taUserInputL8T1.getText().startsWith("System.out.println("))
			{
				cbL8T1a.setSelected(true);
				checkboxSelectedCounterL8T1++;
			}//end if for first checkbox
			if(!taUserOutputL8T1.getText().startsWith("Error!") && taUserInputL8T1.getText().contains("Math.pow("))
			{
				cbL8T1b.setSelected(true);
				checkboxSelectedCounterL8T1++;
			}//end if for second checkbox
			if(!taUserOutputL8T1.getText().startsWith("Error!"))
			{
				cbL8T1c.setSelected(true);
				checkboxSelectedCounterL8T1++;
			}//end if for third checkbox
			if(taUserInputL8T1.getText().endsWith(");"))
			{
				cbL8T1d.setSelected(true);
				checkboxSelectedCounterL8T1++;
			}//end inside if for fourth checkbox
			//when the user has successfully completed the current task and is ready for the next task
			if(checkboxSelectedCounterL8T1==4)
			{
				//setting the next task as visible
				textflowL8T2.setVisible(true);
				textflowL8T2.setManaged(true);
				hbxL8T2Input.setVisible(true);
				hbxL8T2Input.setManaged(true);
				hbxL8T2Output.setVisible(true);
				hbxL8T2Output.setManaged(true);
				hbxL8T2Checklist.setVisible(true);
				hbxL8T2Checklist.setManaged(true);
	
				//changing the progress value of the progress bar if this is the user's first time doing the current task
				if(user.getProgressLevel()<(progressIncrease*20))
				{
					pbsUserProgress.setProgress((progressIncrease*20));
					user.setProgressLevel(pbsUserProgress.getProgress());
				}//end inside if
			}//end outside if
		});//end setOnAction for btnSubmitL8T1
	
		//determining what to do when the submit button for L8T2 is clicked
		btnSubmitL8T2.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			int checkboxSelectedCounterL8T2=0;
			trialCounter[7][1]++;
	
			//creating the array to store user's output and adding user input to the userResponse array
			String [] outputL8T2=new String[2];
			userResponse[7][1][trialCounter[7][1]]=taUserInputL8T2.getText();
	
			//determining and setting the output that the user will see based on their submission
			try
			{
				outputL8T2=CheckJava.submitL8T2(taUserInputL8T2, TEXTAREA_FONT);
			}
			catch (ScriptException e)
			{
				e.printStackTrace();
			}//end try catch
			taUserOutputL8T2.setText(outputL8T2[0]);
			taUserOutputL8T2.setStyle(outputL8T2[1]);
	
			//resetting all checkboxes in this task to be deselected
			cbL8T2a.setSelected(false);
			cbL8T2b.setSelected(false);
			cbL8T2c.setSelected(false);
	
			//if statements to check the user's inputed code and the generated output and make the correct checkboxes for this task selected
			if(!taUserOutputL8T2.getText().contains("Don't forget to initialize the variable num to a double value.") && !taUserOutputL8T2.getText().contains("The number you want to find the square root of must be an integer or decimal value.") && !taUserOutputL8T2.getText().contains("Don't forget to create and initialize a double variable called num on your first line of code."))
			{
				cbL8T2a.setSelected(true);
				checkboxSelectedCounterL8T2++;
			}//end outside if for first checkbox
			if(!taUserOutputL8T2.getText().contains("Please check how you initialize the variable squareRoot/calculate the square root of the variable num.") && !taUserOutputL8T2.getText().contains("Don't forget to create a double variable called squareRoot and initialize it to the square root of the value of num on the second line of code."))
			{
				cbL8T2b.setSelected(true);
				checkboxSelectedCounterL8T2++;
			}//end if for second checkbox
			if(!taUserOutputL8T2.getText().startsWith("Error!"))
			{
				cbL8T2c.setSelected(true);
				checkboxSelectedCounterL8T2++;
			}//end if for third checkbox
	
			//when the user has successfully completed the current task and is ready for the next task
			if(checkboxSelectedCounterL8T2==3)
			{
				//setting the next task as visible
				textflowL8T3.setVisible(true);
				textflowL8T3.setManaged(true);
				hbxL8T3Input.setVisible(true);
				hbxL8T3Input.setManaged(true);
				hbxL8T3Output.setVisible(true);
				hbxL8T3Output.setManaged(true);
				hbxL8T3Checklist.setVisible(true);
				hbxL8T3Checklist.setManaged(true);
	
				//changing the progress value of the progress bar if this is the user's first time doing the current task
				if(user.getProgressLevel()<(progressIncrease*21))
				{
					pbsUserProgress.setProgress((progressIncrease*21));
					user.setProgressLevel(pbsUserProgress.getProgress());
				}//end inside if
			}//end outside if
		});//end setOnAction for btnSubmitL8T2
	
		//determining what to do when the submit button for L8T3 is clicked
		btnSubmitL8T3.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			int checkboxSelectedCounterL8T3=0;
			trialCounter[7][2]++;
	
			//creating the array to store user's output and adding user input to the userResponse array
			String [] outputL8T3=new String[2];
			userResponse[7][2][trialCounter[7][2]]=taUserInputL8T3.getText();
	
			//determining and setting the output that the user will see based on their submission
			try
			{
				outputL8T3=CheckJava.submitL8T3(taUserInputL8T3, TEXTAREA_FONT);
			}
			catch (ScriptException e)
			{
				e.printStackTrace();
			}//end try catch
			taUserOutputL8T3.setText(outputL8T3[0]);
			taUserOutputL8T3.setStyle(outputL8T3[1]);
	
			//resetting all checkboxes in this task to be deselected
			cbL8T3a.setSelected(false);
			cbL8T3b.setSelected(false);
			cbL8T3c.setSelected(false);
	
			//if statements to check the user's inputed code and the generated output and make the correct checkboxes for this task selected
			if(!taUserOutputL8T3.getText().contains("Don't forget to initialize the variable degrees to a double value.") && !taUserOutputL8T3.getText().contains("The value you have initialized to degrees is not a double value.") && !taUserOutputL8T3.getText().contains("Please check how you initialize the variable radians/calculate the radian converted value of the variable degrees.") && !taUserOutputL8T3.getText().contains("Don't forget to create a double variable called radians and initialize it to the converted value of the variable degrees on the second line of code.") && !taUserOutputL8T3.getText().contains("Please check how you initialize the variable sine/calculate the value of sine with the variable radians.") && !taUserOutputL8T3.getText().contains("Don't forget to create a double variable called sine and initialize it to the sine value of the variable radians using the Math class on your third line of code.") && !taUserOutputL8T3.getText().contains("Please check how you initialize the variable cosine/calculate the value of cosine with the variable radians.") && !taUserOutputL8T3.getText().contains("Don't forget to create a double variable called cosine and initialize it to the cosine value of the variable radians using the Math class on your fourth line of code.") && !taUserOutputL8T3.getText().contains("Please check how you initialize the variable tangent/calculate the value of tangent with the variable radians.") && !taUserOutputL8T3.getText().contains("Don't forget to create a double variable called tangent and initialize it to the tangent value of the variable radians using the Math class on your fifth line of code.Since you did not correctly initialize the variable radians, the values of sine, cosine, and tangent were not able to be initialized and nothing was able to be printed to the screen.") && !taUserOutputL8T3.getText().contains("Since you did not correctly initialize the variable radians, the values of sine, cosine, and tangent were not able to be initialized and nothing was able to be printed to the screen.") && !taUserOutputL8T3.getText().contains("Don't forget to create and initialize a double variable called degrees on your first line of code."))
			{
				cbL8T3a.setSelected(true);
				cbL8T3b.setSelected(true);
				checkboxSelectedCounterL8T3+=2;
			}//end if for first and second checkbox
			if(!taUserOutputL8T3.getText().startsWith("Error!"))
			{
				cbL8T3c.setSelected(true);
				checkboxSelectedCounterL8T3++;
			}//end if for third checkbox
	
			//when the user has successfully completed the current task and is ready for the next task
			if(checkboxSelectedCounterL8T3==3)
			{
				//setting the next task as visible
				textflowL8T4.setVisible(true);
				textflowL8T4.setManaged(true);
				hbxL8T4Input.setVisible(true);
				hbxL8T4Input.setManaged(true);
				hbxL8T4Output.setVisible(true);
				hbxL8T4Output.setManaged(true);
				hbxL8T4Checklist.setVisible(true);
				hbxL8T4Checklist.setManaged(true);
	
				//changing the progress value of the progress bar if this is the user's first time doing the current task
				if(user.getProgressLevel()<(progressIncrease*22))
				{
					pbsUserProgress.setProgress((progressIncrease*22));
					user.setProgressLevel(pbsUserProgress.getProgress());
				}//end inside if
			}//end outside if
		});//end setOnAction for btnSubmitL8T3
		
		//determining what to do when the submit button for L8T4 is clicked
		btnSubmitL8T4.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			int checkboxSelectedCounterL8T4=0;
			trialCounter[7][3]++;
			diceValue1=rand.nextInt(6)+1;
			diceValue2=rand.nextInt(6)+1;
	
			//creating the array to store user's output and adding user input to the userResponse array
			userResponse[7][3][trialCounter[7][3]]=taUserInputL8T4.getText();
			String [] runInputL8T4 = new String [2];
			txtRunInputL8T4a.setVisible(false);
			txtRunInputL8T4a.setText("");
			txtRunInputL8T4a.setEditable(true);
			taUserOutputL8T4b.setVisible(false);
			taUserOutputL8T4b.setEditable(false);
			txtRunInputL8T4b.setVisible(false);
			txtRunInputL8T4b.setText("");
			txtRunInputL8T4b.setEditable(true);
			taUserOutputL8T4c.setVisible(false);
			taUserOutputL8T4c.setEditable(false);
	
			//determining and setting the output that the user will see based on their submission
			try
			{
				outputL8T4=CheckJava.submitL8T4(taUserInputL8T4, TEXTAREA_FONT);
			}
			catch (ScriptException e)
			{
				e.printStackTrace();
			}//end try catch
			
			if(outputL8T4[0].startsWith("Error!"))
			{
				taUserOutputL8T4a.setText(outputL8T4[0]);
			}//end if
			else
			{
				taUserOutputL8T4a.setText("You have rolled "+diceValue1+" and "+diceValue2+"\n"+outputL8T4[1]);
			}//end else
			
			
			taUserOutputL8T4a.setStyle(outputL8T4[2]);
			taUserOutputL8T4b.setStyle(outputL8T4[2]);
			taUserOutputL8T4c.setStyle(outputL8T4[2]);
	
			//resetting all checkboxes in this task to be deselected
			cbL8T4a.setSelected(false);
			cbL8T4b.setSelected(false);
			cbL8T4c.setSelected(false);
			cbL8T4d.setSelected(false);
			cbL8T4e.setSelected(false);
			cbL8T4f.setSelected(false);
	
			if(!taUserOutputL8T4a.getText().contains("Don't forget to create an integer variable called") && !taUserOutputL8T4a.getText().contains("Don't forget to create a String variable called"))
			{
				cbL8T4a.setSelected(true);
			}//end if for first checkbox
			if(!taUserOutputL8T4a.getText().contains("You have forgotten to add one or more of the conditions in your do while loop"))
			{
				cbL8T4b.setSelected(true);
			}//end if for second checkbox
			if(!taUserOutputL8T4a.getText().contains("Don't forget to print to screen the values of d1 and d2 in the proper format"))
			{
				cbL8T4c.setSelected(true);
			}//end if for third checkbox
			if(!taUserOutputL8T4a.getText().contains("Don't forget to allow the user to initialize the variable rollAgain on the fourteenth line of code."))
			{
				cbL8T4d.setSelected(true);
			}//end if for fourth checkbox
			if(!taUserOutputL8T4a.getText().contains("Don't forget to tell/ask user what they should input (whether or not they want to roll the dice again)."))
			{
				cbL8T4e.setSelected(true);
			}//end if for fifth checkbox
			if(!taUserOutputL8T4a.getText().contains("Don't forget to increase the value of rollCounter by 1 on the ninth line of code"))
			{
				cbL8T4f.setSelected(true);
			}//end if for sixth checkbox
			
			if(!taUserOutputL8T4a.getText().startsWith("Error!"))
			{
				txtRunInputL8T4a.setVisible(true);
	
				txtRunInputL8T4a.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event)
					{
						if(txtRunInputL8T4a.getText().equals("y"))
						{
							diceValue1=rand.nextInt(6)+1;
							diceValue2=rand.nextInt(6)+1;
							taUserOutputL8T4b.setText("You have rolled "+diceValue1+" and "+diceValue2+"\n"+outputL8T4[1]);
							taUserOutputL8T4b.setVisible(true);
							txtRunInputL8T4b.setEditable(true);
							txtRunInputL8T4b.setVisible(true);
						}
						txtRunInputL8T4b.setOnAction(new EventHandler<ActionEvent>()
						{
							@Override
							public void handle(ActionEvent event)
							{
								if(txtRunInputL8T4b.getText().equals("y"))
								{
									diceValue1=rand.nextInt(6)+1;
									diceValue2=rand.nextInt(6)+1;
									taUserOutputL8T4c.setText("You have rolled "+diceValue1+" and "+diceValue2+"\n"+outputL8T4[1]);
									taUserOutputL8T4c.setVisible(true);
								}
							}//end inside handle method
						});//end setOnAction method for txtRunInputL8T4b
	
						if(cbL8T4a.isSelected() && cbL8T4b.isSelected() && cbL8T4c.isSelected())
						{
							//setting the next task as visible
							lblL8Done.setVisible(true);
							lblL8Done.setManaged(true);
	
							//changing the progress value of the progress bar if this is the user's first time doing the current task
							if(user.getProgressLevel()<(progressIncrease*34))
							{
								pbsUserProgress.setProgress((progressIncrease*34));
								user.setProgressLevel(pbsUserProgress.getProgress());
							}//end inside if
						}//end outside if
					}//end outside handle method
				});//end setOnAction method for txtRunInputL8T4a
			}//end if
	
		});//end setOnAction for btnSubmitL8T4
	
		//creating and setting the scroll pane for this lesson
		ScrollPane spL8 = new ScrollPane();
		spL8.setContent(vbxL8);
	
		//creating the scene
		Scene sceneL8 = new Scene(spL8);
	
		lastScene="lesson8"; //setting the previous scene to this scene
	
		//setting/showing the stage
		myStage.getIcons().add(new Image("FromOneToAnotherLogo.png"));
		myStage.setTitle("From C++ to Java - ... - Lesson #8");
		myStage.setScene(sceneL8);
		myStage.centerOnScreen();
		myStage.show();
	}//end lesson8 method
	

	/* Method Author: Brooke Cronin
	 * Method Name: lesson9
	 * Description: Creates the scene for the fifth lesson. Allows the user to view and attempt all of the tasks and get feedback after completing each task.
	 * Parameters: Stage myStage
	 * Returns: N/A
	 * Throws: NullPointerException
	 */
	
	public void lesson9(Stage myStage) throws NullPointerException
	{
		myStage.setOnHiding( event -> {try {
			exitProgram();
		} catch (IOException e) {
			
			e.printStackTrace();
		}} );
		Random rand = new Random();
		
		//resetting the text in all of the user output text areas and disabling them so that the user can't change what appears in them
		TextArea taUserOutputL9T1 = new TextArea();
		taUserOutputL9T1.setText("Not submitted yet");
		taUserOutputL9T1.setEditable(false);
		taUserOutputL9T1.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
		TextArea taUserOutputL9T2 = new TextArea();
		taUserOutputL9T2.setText("Not submitted yet");
		taUserOutputL9T2.setEditable(false);
		taUserOutputL9T2.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
		TextArea taUserOutputL9T3 = new TextArea();
		taUserOutputL9T3.setText("Not submitted yet");
		taUserOutputL9T3.setEditable(false);
		taUserOutputL9T3.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
	
		//creating the vertical box for lesson 5 screen
		VBox vbxL9 = new VBox(GAP);
		vbxL9.setPadding(new Insets(GAP, GAP, GAP, GAP));
	
		//adding the user's progress bar to the vertical box
		pbsUserProgress.setMaxWidth(Double.MAX_VALUE);
		vbxL9.getChildren().add(pbsUserProgress);
	
		//creating, setting, and adding a header Label
		AnchorPane aplesson9TitleHeader = new AnchorPane();
		Label lbllesson9TitleHeader = new Label("Lesson #9: Methods");
		lbllesson9TitleHeader.setStyle("-fx-text-fill: black; -fx-font-size: "+LESSON_HEADER_FONT);
		lbllesson9TitleHeader.setMaxWidth(Double.MAX_VALUE);
		AnchorPane.setLeftAnchor(lbllesson9TitleHeader, 0.0);
		AnchorPane.setRightAnchor(lbllesson9TitleHeader, 0.0);
		lbllesson9TitleHeader.setAlignment(Pos.CENTER);
		aplesson9TitleHeader.getChildren().add(lbllesson9TitleHeader);
		vbxL9.getChildren().add(aplesson9TitleHeader);
	
		//adding the name of the current task
		Label lblL9T1Header = new Label("\nTask #1: printValue");
		lblL9T1Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		vbxL9.getChildren().add(lblL9T1Header);
	
		//creating, setting, and adding the first task instructions
		Text textL9T1a = new Text("\n\nNow that you have got some of the basics down, what about making your code a little bit more efficient? Well one way that you can do this is with the use of methods "
				+ "(very similar to functions in Python). Methods allow you to store parts of code and reuse it throughout the rest of your program. There are three different types of methods; void, parameter, and function. "
				+ "A void method is generally used only for printing something to the screen, to do a calculation with only variables created in that method and print the result to the screen, or update global vriables. "
				+ "In this task, you will be beginning with a void method. First in your main function, create a variable called ");
		textL9T1a.setFill(Color.BLACK);
		Text textL9T1b = new Text("value");
		textL9T1b.setFill(Color.rgb(121, 76, 52));
		Text textL9T1c = new Text(" and initialize it to 0. Then print ");
		textL9T1c.setFill(Color.BLACK);
		Text textL9T1d = new Text("value");
		textL9T1d.setFill(Color.rgb(121, 76, 52));
		Text textL9T1e = new Text("to the screen. After this, call to the printValue method. \nAfter the call, print ");
		textL9T1e.setFill(Color.BLACK);
		Text textL9T1f = new Text("value");
		textL9T1f.setFill(Color.rgb(121, 76, 52));
		Text textL9T1g = new Text(" once again in the main method. \nIn the printValue method, reinitialize ");
		textL9T1g.setFill(Color.BLACK);
		Text textL9T1h = new Text("value");
		textL9T1h.setFill(Color.rgb(121, 76, 52));
		Text textL9T1i = new Text(" to 1. Then print ");
		textL9T1i.setFill(Color.BLACK);
		Text textL9T1j = new Text("value");
		textL9T1j.setFill(Color.rgb(121, 76, 52));
		Text textL9T1k = new Text(" to the screen.");
		textL9T1k.setFill(Color.BLACK);
		TextFlow textflowL9T1 = new TextFlow(textL9T1a, textL9T1b, textL9T1c, textL9T1d, textL9T1e, textL9T1f, textL9T1g, textL9T1h, textL9T1i, textL9T1j, textL9T1k);
		textflowL9T1.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		vbxL9.getChildren().add(textflowL9T1);
	
		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput1 = new Label("Your code: ");
		lblInput1.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL9T1 = new TextArea();
		taUserInputL9T1.setPrefSize(taWidth, taLineHeight);
		taUserInputL9T1.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL9T1.setText("public class L9T1Response\n{\n\n}//end L9T1Response class");
		Button btnSubmitL9T1 = new Button("Submit");
		btnSubmitL9T1.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL9T1Input = new HBox(GAP, lblInput1, taUserInputL9T1, btnSubmitL9T1);
		vbxL9.getChildren().add(hbxL9T1Input);
		//for user's output
		Label lblOutput1 = new Label("Output:     ");
		lblOutput1.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL9T1.setPrefSize(taWidth, taLineHeight);
		HBox hbxL9T1Output = new HBox(GAP, lblOutput1, taUserOutputL9T1);
		vbxL9.getChildren().add(hbxL9T1Output);
		//for user's result
		Label lblCriteria1 = new Label("Criteria");
		CheckBox cbL9T1a = new CheckBox("Begins with System.out.println(");
		cbL9T1a.setDisable(true);
		CheckBox cbL9T1b = new CheckBox("Output is a decimal value of an exponent calculation");
		cbL9T1b.setDisable(true);
		CheckBox cbL9T1c = new CheckBox("Code contains a call to the Math.pow() method");
		cbL9T1c.setDisable(true);
		CheckBox cbL9T1d = new CheckBox("Ends with );");
		cbL9T1d.setDisable(true);
		HBox hbxL9T1Checklist = new HBox(GAP, lblCriteria1, cbL9T1a, cbL9T1b, cbL9T1c, cbL9T1d);
		hbxL9T1Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria1.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		vbxL9.getChildren().add(hbxL9T1Checklist);
	
		//adding the name of the current task
		Label lblL9T2Header = new Label("\n\n\nTask #2: increment");
		lblL9T2Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		if(user.getProgressLevel()<(progressIncrease*32))
		{
			lblL9T2Header.setVisible(false);
			lblL9T2Header.setManaged(false);
		}//end if
		vbxL9.getChildren().add(lblL9T2Header);
	
		//creating, setting, and adding the second task instructions
		Text textL9T2a = new Text("\n\nNext, let's try a parameter method. This type of method allows you to send some "
				+ "variables (or values) to the method (from the method that calls to your parameter method), and changes "
				+ "these values as you have programmed. \nIn this task, you will begin with in the main method, call to the "
				+ "method increment with two parameters (a double value and an integer value) twice, both times with "
				+ "different parameter values. In the increment definition, include the double parameter ");
		textL9T2a.setFill(Color.BLACK);
		Text textL9T2b = new Text("increment");
		textL9T2b.setFill(Color.rgb(121, 76, 52));
		Text textL9T2c = new Text(" and the integer variable ");
		textL9T2c.setFill(Color.BLACK);
		Text textL9T2d = new Text("by");
		textL9T2d.setFill(Color.rgb(121, 76, 52));
		Text textL9T2e = new Text(". Inside of the increment method, print the value of ");
		textL9T2e.setFill(Color.BLACK);
		Text textL9T2f = new Text("increment");
		textL9T2f.setFill(Color.rgb(121, 76, 52));
		Text textL9T2g = new Text("+");
		textL9T2g.setFill(Color.BLACK);
		Text textL9T2h = new Text("by");
		textL9T2h.setFill(Color.rgb(121, 76, 52));
		Text textL9T2i = new Text(" to the screen.");
		textL9T2i.setFill(Color.BLACK);
		TextFlow textflowL9T2 = new TextFlow(textL9T2a, textL9T2b, textL9T2c, textL9T2d, textL9T2e, textL9T2f, textL9T2g, textL9T2h, textL9T2i);
		textflowL9T2.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*32))
		{
			textflowL9T2.setVisible(false);
			textflowL9T2.setManaged(false);
		}//end if
		vbxL9.getChildren().add(textflowL9T2);
	
		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput2 = new Label("Your code: ");
		lblInput2.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL9T2 = new TextArea();
		taUserInputL9T2.setPrefSize(taWidth, taLineHeight*4);
		taUserInputL9T2.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL9T2.setText("public class L9T2Response\n{\n\n}//end L9T2Response class");
		Button btnSubmitL9T2 = new Button("Submit");
		btnSubmitL9T2.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL9T2Input = new HBox(GAP, lblInput2, taUserInputL9T2, btnSubmitL9T2);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*32))
		{
			hbxL9T2Input.setVisible(false);
			hbxL9T2Input.setManaged(false);
		}//end if
		vbxL9.getChildren().add(hbxL9T2Input);
		//for user's output
		Label lblOutput2 = new Label("Output:     ");
		lblOutput2.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL9T2.setPrefSize(taWidth, taLineHeight);
		HBox hbxL9T2Output = new HBox(GAP, lblOutput2, taUserOutputL9T2);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*32))
		{
			hbxL9T2Output.setVisible(false);
			hbxL9T2Output.setManaged(false);
		}//end if
		vbxL9.getChildren().add(hbxL9T2Output);
		//for user's result
		Label lblCriteria2 = new Label("Criteria");
		CheckBox cbL9T2a = new CheckBox("num was created as a double variable and initialized to a double value");
		cbL9T2a.setDisable(true);
		CheckBox cbL9T2b = new CheckBox("squareRoot was created as a double variable and initialized correctly using the correct method from the Math class");
		cbL9T2b.setDisable(true);
		CheckBox cbL9T2c = new CheckBox("Output is the value of squareRoot");
		cbL9T2c.setDisable(true);
		HBox hbxL9T2Checklist = new HBox(GAP, lblCriteria2, cbL9T2a, cbL9T2b, cbL9T2c);
		hbxL9T2Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria2.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*32))
		{
			hbxL9T2Checklist.setVisible(false);
			hbxL9T2Checklist.setManaged(false);
		}//end if
		vbxL9.getChildren().add(hbxL9T2Checklist);
	
		//adding the name of the current task
		Label lblL9T3Header = new Label("\n\n\nTask #3: rectangularPrismSA");
		lblL9T3Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		if(user.getProgressLevel()<(progressIncrease*33))
		{
			lblL9T3Header.setVisible(false);
			lblL9T3Header.setManaged(false);
		}//end if
		vbxL9.getChildren().add(lblL9T3Header);
	
		//creating, setting, and adding the third task instructions
		Text textL9T3a = new Text("\n\nThe final type of method that you will be attempting in this lesson is a function method. A function method allows you to return a value to the "
				+ "method that was called from. In this task, you will begin in your main method. Create three double variables called ");
		textL9T3a.setFill(Color.BLACK);
		Text textL9T3b = new Text("length");
		textL9T3b.setFill(Color.rgb(121, 76, 52));
		Text textL9T3c = new Text(", ");
		textL9T3c.setFill(Color.BLACK);
		Text textL9T3d = new Text("height");
		textL9T3d.setFill(Color.rgb(121, 76, 52));
		Text textL9T3e = new Text(", and");
		textL9T3e.setFill(Color.BLACK);
		Text textL9T3f = new Text("width");
		textL9T3f.setFill(Color.rgb(121, 76, 52));
		Text textL9T3g = new Text(" and initialize each to double values of your choice. \nThen, you create another double variable called ");
		textL9T3g.setFill(Color.BLACK);
		Text textL9T3h = new Text("SA");
		textL9T3h.setFill(Color.rgb(121, 76, 52));
		Text textL9T3i = new Text(" and initialize it to the value returned from calling the rectangularPrismSA method using the three variables used above as your parameters. \nAfter this, print the value of ");
		textL9T3i.setFill(Color.BLACK);
		Text textL9T3j = new Text("SA");
		textL9T3j.setFill(Color.rgb(121, 76, 52));
		Text textL9T3k = new Text(" to the screen. Next, Using a System.out.println() statement, call to the rectangularPrismSA method again, but with values instead of the variables. \nAfter this, you will call to a method called rectangularPrismSA with a return type of double and the parameters are the variables that you just created above. Make sure that the order of parameters in your method decleration is the same as the order when you call to this method in your main method."
				+ "\nInside of the rectangluarPrismSA method, you will create a double variable called ");
		textL9T3k.setFill(Color.BLACK);
		Text textL9T3l = new Text("surfaceArea");
		textL9T3l.setFill(Color.rgb(121, 76, 52));
		Text textL9T3m = new Text(" and initialize it to the surface area of a rectangular prism with the following formula. \nSA=2(wl+hl+hw)\nAfter this, return the variable ");
		textL9T3m.setFill(Color.BLACK);
		Text textL9T3n = new Text("surfaceArea");
		textL9T3n.setFill(Color.rgb(121, 76, 52));
		Text textL9T3o = new Text(".");
		textL9T3o.setFill(Color.BLACK);
		TextFlow textflowL9T3 = new TextFlow(textL9T3a, textL9T3b, textL9T3c, textL9T3d, textL9T3e, textL9T3f, textL9T3g, textL9T3h, textL9T3i, textL9T3j, textL9T3k, textL9T3l, textL9T3m, textL9T3n, textL9T3o);
		textflowL9T3.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*33))
		{
			textflowL9T3.setVisible(false);
			textflowL9T3.setManaged(false);
		}//end if
		vbxL9.getChildren().add(textflowL9T3);
	
		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput3 = new Label("Your code: ");
		lblInput3.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL9T3 = new TextArea();
		taUserInputL9T3.setPrefSize(taWidth, taLineHeight*7);
		taUserInputL9T3.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL9T3.setText("public class L9T3Response\n{\n\n}//end L9T3Response class");
		Button btnSubmitL9T3 = new Button("Submit");
		btnSubmitL9T3.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL9T3Input = new HBox(GAP, lblInput3, taUserInputL9T3, btnSubmitL9T3);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*33))
		{
			hbxL9T3Input.setVisible(false);
			hbxL9T3Input.setManaged(false);
		}//end if
		vbxL9.getChildren().add(hbxL9T3Input);
		//for user's output
		Label lblOutput3 = new Label("Output:     ");
		lblOutput3.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL9T3.setPrefSize(taWidth, taLineHeight);
		HBox hbxL9T3Output = new HBox(GAP, lblOutput3, taUserOutputL9T3);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*33))
		{
			hbxL9T3Output.setVisible(false);
			hbxL9T3Output.setManaged(false);
		}//end if
		vbxL9.getChildren().add(hbxL9T3Output);
		//for user's result
		Label lblCriteria3 = new Label("Criteria");
		CheckBox cbL9T3a = new CheckBox("degrees, radians, sine, cosine, and tangent are are created as double variables and initialized correctly");
		cbL9T3a.setDisable(true);
		CheckBox cbL9T3b = new CheckBox("The Math class was used to calculate the values of radians, sine, cosine, and tangent");
		cbL9T3b.setDisable(true);
		CheckBox cbL9T3c = new CheckBox("Output is the values of degrees, radians, sine, cosine, and tangent with all values separated by a comma and a space");
		cbL9T3c.setDisable(true);
		HBox hbxL9T3Checklist = new HBox(GAP, lblCriteria3, cbL9T3a, cbL9T3b, cbL9T3c);
		hbxL9T3Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria3.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*33))
		{
			hbxL9T3Checklist.setVisible(false);
			hbxL9T3Checklist.setManaged(false);
		}//end if
		vbxL9.getChildren().add(hbxL9T3Checklist);
	
		//creating and adding a label to notify user that they have completed the current lesson
		Label lblL9Done = new Label("Congratulations, "+user.getfName()+"! You have finished Lesson #5: The Math and Random Classes.\n" + 
				"Click one of the buttons below to either restart this lesson, choose another lesson from the menu, go to the previous lesson, or continue with the next lesson.");
		lblL9Done.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*35))
		{
			lblL9Done.setVisible(false);
			lblL9Done.setManaged(false);
		}//end if
		vbxL9.getChildren().add(lblL9Done);
	
		//creating and adding the buttons at the bottom of the screen
		Button btnPreviousLesson = new Button("Previous Lesson");
		btnPreviousLesson.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnPreviousLesson.setOnAction(event -> lesson7(myStage));
		Button btnLessonMenu = new Button("Lesson Menu");
		btnLessonMenu.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnLessonMenu.setOnAction(event -> lessonMenu(myStage));
		Button btnExit = new Button("Exit");
		btnExit.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnExit.setOnAction(event -> 
		{
			try
			{
				exitProgram();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		});
		Button btnRestartL9 = new Button("Restart Lesson");
		btnRestartL9.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnRestartL9.setOnAction(event -> lesson9(myStage));
		Button btnNextLesson = new Button("Next Lesson");
		btnNextLesson.setStyle("-fx-font-size: "+CONTROL_FONT);
		if(user.getProgressLevel()<(progressIncrease*35))
		{
			btnNextLesson.setDisable(true);
		}//end if
		//		btnNextLesson.setOnAction(event -> lesson9(myStage));
		HBox hbxL9Controls = new HBox(GAP, btnPreviousLesson, btnLessonMenu, btnExit, btnRestartL9, btnNextLesson);
		vbxL9.getChildren().add(hbxL9Controls);	
	
		//determining what to do when the submit button for L9T1 is clicked
		btnSubmitL9T1.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			int checkboxSelectedCounterL9T1=0;
			trialCounter[7][0]++;
	
			//creating the array to store user's output and adding user input to the userResponse array
			String [] outputL9T1=new String[2];
			userResponse[7][0][trialCounter[7][0]]=taUserInputL9T1.getText();
	
			//determining and setting the output that the user will see based on their submission
			try
			{
				outputL9T1=CheckJava.submitL9T1(taUserInputL9T1, TEXTAREA_FONT);
			}
			catch (ScriptException e)
			{
				e.printStackTrace();
			}//end try catch
			taUserOutputL9T1.setText(outputL9T1[0]);
			taUserOutputL9T1.setStyle(outputL9T1[1]);
	
			//resetting all checkboxes in this task to be deselected
			cbL9T1a.setSelected(false);
			cbL9T1b.setSelected(false);
			cbL9T1c.setSelected(false);
			cbL9T1d.setSelected(false);
	
			//if statements to check the user's inputed code and the generated output and make the correct checkboxes for this task selected
			if(taUserInputL9T1.getText().startsWith("System.out.println("))
			{
				cbL9T1a.setSelected(true);
				checkboxSelectedCounterL9T1++;
			}//end if for first checkbox
			if(!taUserOutputL9T1.getText().startsWith("Error!") && taUserInputL9T1.getText().contains("Math.pow("))
			{
				cbL9T1b.setSelected(true);
				checkboxSelectedCounterL9T1++;
			}//end if for second checkbox
			if(!taUserOutputL9T1.getText().startsWith("Error!"))
			{
				cbL9T1c.setSelected(true);
				checkboxSelectedCounterL9T1++;
			}//end if for third checkbox
			if(taUserInputL9T1.getText().endsWith(");"))
			{
				cbL9T1d.setSelected(true);
				checkboxSelectedCounterL9T1++;
			}//end inside if for fourth checkbox
			//when the user has successfully completed the current task and is ready for the next task
			if(checkboxSelectedCounterL9T1==4)
			{
				//setting the next task as visible
				textflowL9T2.setVisible(true);
				textflowL9T2.setManaged(true);
				hbxL9T2Input.setVisible(true);
				hbxL9T2Input.setManaged(true);
				hbxL9T2Output.setVisible(true);
				hbxL9T2Output.setManaged(true);
				hbxL9T2Checklist.setVisible(true);
				hbxL9T2Checklist.setManaged(true);
	
				//changing the progress value of the progress bar if this is the user's first time doing the current task
				if(user.getProgressLevel()<(progressIncrease*20))
				{
					pbsUserProgress.setProgress((progressIncrease*20));
					user.setProgressLevel(pbsUserProgress.getProgress());
				}//end inside if
			}//end outside if
		});//end setOnAction for btnSubmitL9T1
	
		//determining what to do when the submit button for L9T2 is clicked
		btnSubmitL9T2.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			int checkboxSelectedCounterL9T2=0;
			trialCounter[7][1]++;
	
			//creating the array to store user's output and adding user input to the userResponse array
			String [] outputL9T2=new String[2];
			userResponse[7][1][trialCounter[7][1]]=taUserInputL9T2.getText();
	
			//determining and setting the output that the user will see based on their submission
			try
			{
				outputL9T2=CheckJava.submitL9T2(taUserInputL9T2, TEXTAREA_FONT);
			}
			catch (ScriptException e)
			{
				e.printStackTrace();
			}//end try catch
			taUserOutputL9T2.setText(outputL9T2[0]);
			taUserOutputL9T2.setStyle(outputL9T2[1]);
	
			//resetting all checkboxes in this task to be deselected
			cbL9T2a.setSelected(false);
			cbL9T2b.setSelected(false);
			cbL9T2c.setSelected(false);
	
			//if statements to check the user's inputed code and the generated output and make the correct checkboxes for this task selected
			if(!taUserOutputL9T2.getText().contains("Don't forget to initialize the variable num to a double value.") && !taUserOutputL9T2.getText().contains("The number you want to find the square root of must be an integer or decimal value.") && !taUserOutputL9T2.getText().contains("Don't forget to create and initialize a double variable called num on your first line of code."))
			{
				cbL9T2a.setSelected(true);
				checkboxSelectedCounterL9T2++;
			}//end outside if for first checkbox
			if(!taUserOutputL9T2.getText().contains("Please check how you initialize the variable squareRoot/calculate the square root of the variable num.") && !taUserOutputL9T2.getText().contains("Don't forget to create a double variable called squareRoot and initialize it to the square root of the value of num on the second line of code."))
			{
				cbL9T2b.setSelected(true);
				checkboxSelectedCounterL9T2++;
			}//end if for second checkbox
			if(!taUserOutputL9T2.getText().startsWith("Error!"))
			{
				cbL9T2c.setSelected(true);
				checkboxSelectedCounterL9T2++;
			}//end if for third checkbox
	
			//when the user has successfully completed the current task and is ready for the next task
			if(checkboxSelectedCounterL9T2==3)
			{
				//setting the next task as visible
				textflowL9T3.setVisible(true);
				textflowL9T3.setManaged(true);
				hbxL9T3Input.setVisible(true);
				hbxL9T3Input.setManaged(true);
				hbxL9T3Output.setVisible(true);
				hbxL9T3Output.setManaged(true);
				hbxL9T3Checklist.setVisible(true);
				hbxL9T3Checklist.setManaged(true);
	
				//changing the progress value of the progress bar if this is the user's first time doing the current task
				if(user.getProgressLevel()<(progressIncrease*21))
				{
					pbsUserProgress.setProgress((progressIncrease*21));
					user.setProgressLevel(pbsUserProgress.getProgress());
				}//end inside if
			}//end outside if
		});//end setOnAction for btnSubmitL9T2
	
		//determining what to do when the submit button for L9T3 is clicked
		btnSubmitL9T3.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			int checkboxSelectedCounterL9T3=0;
			trialCounter[7][2]++;
	
			//creating the array to store user's output and adding user input to the userResponse array
			String [] outputL9T3=new String[2];
			userResponse[7][2][trialCounter[7][2]]=taUserInputL9T3.getText();
	
			//determining and setting the output that the user will see based on their submission
			try
			{
				outputL9T3=CheckJava.submitL9T3(taUserInputL9T3, TEXTAREA_FONT);
			}
			catch (ScriptException e)
			{
				e.printStackTrace();
			}//end try catch
			taUserOutputL9T3.setText(outputL9T3[0]);
			taUserOutputL9T3.setStyle(outputL9T3[1]);
	
			//resetting all checkboxes in this task to be deselected
			cbL9T3a.setSelected(false);
			cbL9T3b.setSelected(false);
			cbL9T3c.setSelected(false);
	
			//if statements to check the user's inputed code and the generated output and make the correct checkboxes for this task selected
			if(!taUserOutputL9T3.getText().contains("Don't forget to initialize the variable degrees to a double value.") && !taUserOutputL9T3.getText().contains("The value you have initialized to degrees is not a double value.") && !taUserOutputL9T3.getText().contains("Please check how you initialize the variable radians/calculate the radian converted value of the variable degrees.") && !taUserOutputL9T3.getText().contains("Don't forget to create a double variable called radians and initialize it to the converted value of the variable degrees on the second line of code.") && !taUserOutputL9T3.getText().contains("Please check how you initialize the variable sine/calculate the value of sine with the variable radians.") && !taUserOutputL9T3.getText().contains("Don't forget to create a double variable called sine and initialize it to the sine value of the variable radians using the Math class on your third line of code.") && !taUserOutputL9T3.getText().contains("Please check how you initialize the variable cosine/calculate the value of cosine with the variable radians.") && !taUserOutputL9T3.getText().contains("Don't forget to create a double variable called cosine and initialize it to the cosine value of the variable radians using the Math class on your fourth line of code.") && !taUserOutputL9T3.getText().contains("Please check how you initialize the variable tangent/calculate the value of tangent with the variable radians.") && !taUserOutputL9T3.getText().contains("Don't forget to create a double variable called tangent and initialize it to the tangent value of the variable radians using the Math class on your fifth line of code.Since you did not correctly initialize the variable radians, the values of sine, cosine, and tangent were not able to be initialized and nothing was able to be printed to the screen.") && !taUserOutputL9T3.getText().contains("Since you did not correctly initialize the variable radians, the values of sine, cosine, and tangent were not able to be initialized and nothing was able to be printed to the screen.") && !taUserOutputL9T3.getText().contains("Don't forget to create and initialize a double variable called degrees on your first line of code."))
			{
				cbL9T3a.setSelected(true);
				cbL9T3b.setSelected(true);
				checkboxSelectedCounterL9T3+=2;
			}//end if for first and second checkbox
			if(!taUserOutputL9T3.getText().startsWith("Error!"))
			{
				cbL9T3c.setSelected(true);
				checkboxSelectedCounterL9T3++;
			}//end if for third checkbox
	
			//when the user has successfully completed the current task and is ready for the next task
			if(checkboxSelectedCounterL9T3==3)
			{
				//setting the next task as visible
				lblL9Done.setVisible(true);
				lblL9Done.setManaged(true);
	
				//changing the progress value of the progress bar if this is the user's first time doing the current task
				if(user.getProgressLevel()<(progressIncrease*22))
				{
					pbsUserProgress.setProgress((progressIncrease*22));
					user.setProgressLevel(pbsUserProgress.getProgress());
				}//end inside if
			}//end outside if
		});//end setOnAction for btnSubmitL9T3
		
		//creating and setting the scroll pane for this lesson
		ScrollPane spL9 = new ScrollPane();
		spL9.setContent(vbxL9);
	
		//creating the scene
		Scene sceneL9 = new Scene(spL9);
	
		lastScene="lesson9"; //setting the previous scene to this scene
	
		//setting/showing the stage
		myStage.getIcons().add(new Image("FromOneToAnotherLogo.png"));
		myStage.setTitle("From C++ to Java - ... - Lesson #9");
		myStage.setScene(sceneL9);
		myStage.centerOnScreen();
		myStage.show();
	}//end lesson9 method

	
	/* Method Author: Brooke Cronin
	 * Method Name: lesson10
	 * Description: Creates the scene for the fifth lesson. Allows the user to view and attempt all of the tasks and get feedback after completing each task.
	 * Parameters: Stage myStage
	 * Returns: N/A
	 * Throws: NullPointerException
	 */
	
	public void lesson10(Stage myStage) throws NullPointerException
	{
		myStage.setOnHiding( event -> {try {
			exitProgram();
		} catch (IOException e) {
			
			e.printStackTrace();
		}} );
		//resetting the text in all of the user output text areas and disabling them so that the user can't change what appears in them
		TextArea taUserOutputL10T1 = new TextArea();
		taUserOutputL10T1.setText("Not submitted yet");
		taUserOutputL10T1.setEditable(false);
		taUserOutputL10T1.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
		TextArea taUserOutputL10T2 = new TextArea();
		taUserOutputL10T2.setText("Not submitted yet");
		taUserOutputL10T2.setEditable(false);
		taUserOutputL10T2.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
		TextArea taUserOutputL10T3 = new TextArea();
		taUserOutputL10T3.setText("Not submitted yet");
		taUserOutputL10T3.setEditable(false);
		taUserOutputL10T3.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
		TextArea taUserOutputL10T4 = new TextArea();
		taUserOutputL10T4.setText("Not submitted yet");
		taUserOutputL10T4.setEditable(false);
		taUserOutputL10T4.setStyle("-fx-text-fill: black; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
	
		//creating the vertical box for lesson 5 screen
		VBox vbxL10 = new VBox(GAP);
		vbxL10.setPadding(new Insets(GAP, GAP, GAP, GAP));
	
		//adding the user's progress bar to the vertical box
		pbsUserProgress.setMaxWidth(Double.MAX_VALUE);
		vbxL10.getChildren().add(pbsUserProgress);
	
		//creating, setting, and adding a header Label
		AnchorPane aplesson10TitleHeader = new AnchorPane();
		Label lbllesson10TitleHeader = new Label("Lesson #10: Arrays");
		lbllesson10TitleHeader.setStyle("-fx-text-fill: black; -fx-font-size: "+LESSON_HEADER_FONT);
		lbllesson10TitleHeader.setMaxWidth(Double.MAX_VALUE);
		AnchorPane.setLeftAnchor(lbllesson10TitleHeader, 0.0);
		AnchorPane.setRightAnchor(lbllesson10TitleHeader, 0.0);
		lbllesson10TitleHeader.setAlignment(Pos.CENTER);
		aplesson10TitleHeader.getChildren().add(lbllesson10TitleHeader);
		vbxL10.getChildren().add(aplesson10TitleHeader);
	
		//adding the name of the current task
		Label lblL10T1Header = new Label("\nTask #1: oneToTen");
		lblL10T1Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		vbxL10.getChildren().add(lblL10T1Header);
	
		//creating, setting, and adding the first task instructions
		Text textL10T1a = new Text("\n\nThis lesson will focus on how to create/use arrays. Arrays are a type of data structure (you will learn more about different data structures in future lessons, or you have probably already learned about them in Python). "
				+ "\nThey can store multiple values of the same data type in one location. "
				+ "To access each value in an array, you use indexes (that go from 0 to the number of values-1). \\nIn Python, to create an integer array, you would first have to include the following import ");
		textL10T1a.setFill(Color.BLACK);
		Text textL10T1b = new Text("from array import *");
		textL10T1b.setFill(Color.DARKVIOLET);
		Text textL10T1c = new Text("\nand then to create an array of size 5, called ");
		textL10T1c.setFill(Color.BLACK);
		Text textL10T1d = new Text("a");
		textL10T1d.setFill(Color.rgb(121, 76, 52));
		Text textL10T1e = new Text(" with the values 4, 65, 3, 7, 2 would be");
		textL10T1e.setFill(Color.BLACK);
		Text textL10T1f = new Text("\na");
		textL10T1f.setFill(Color.rgb(121, 76, 52));
		Text textL10T1g = new Text("=array('i',[4, 65, 3, 7, 2])");
		textL10T1g.setFill(Color.DARKVIOLET);
		Text textL10T1h = new Text("\nBut how is this different than in Java. Well, first of all, no import is required. To create the actual array is shown below.");
		textL10T1h.setFill(Color.BLACK);
		Text textL10T1i = new Text("\nint [] ");
		textL10T1i.setFill(Color.BLUE);
		Text textL10T1j = new Text("a");
		textL10T1j.setFill(Color.rgb(121, 76, 52));
		Text textL10T1k = new Text(" = new int [5] {4, 65, 3, 7, 2};");
		textL10T1k.setFill(Color.BLUE);
		Text textL10T1l = new Text("\nIn this task, you will first create an integer array of size ten with the following values; 1,2,3,4,5,6,7,8,9,10 and called ");
		textL10T1l.setFill(Color.BLACK);
		Text textL10T1m = new Text("oneToTen");
		textL10T1m.setFill(Color.rgb(121, 76, 52));
		Text textL10T1n = new Text(". Then using a for loop, you will print the first nine elements with a comma seperating them, and then the last element will be printed outside of the loo at the end. The output should be \n");
		textL10T1n.setFill(Color.BLACK);
		Text textL10T1o = new Text("1, 2, 3, 4, 5, 6, 7, 8, 9, 10");
		textL10T1o.setStyle("-fx-text-fill: black; -fx-font: "+TASK_INSTR_FONT+"\"Comic Sans MS\"");
		Text textL10T1p = new Text("\nTo access the value at an index in an array is the same in Java as in Python. \nTo get the length of array ");
		textL10T1p.setFill(Color.BLACK);
		Text textL10T1q = new Text("a");
		textL10T1q.setFill(Color.rgb(121, 76, 52));
		Text textL10T1r = new Text(" can be done by using");
		textL10T1r.setFill(Color.BLACK);
		Text textL10T1s = new Text(" a.length");
		textL10T1s.setFill(Color.BLUE);
		TextFlow textflowL10T1 = new TextFlow(textL10T1a, textL10T1b, textL10T1c, textL10T1d, textL10T1e, textL10T1f, textL10T1g, textL10T1h, textL10T1i, textL10T1j, textL10T1k, textL10T1l, textL10T1m, textL10T1n, textL10T1o, textL10T1p, textL10T1q, textL10T1r, textL10T1s);
		textflowL10T1.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		vbxL10.getChildren().add(textflowL10T1);
	
		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput1 = new Label("Your code: ");
		lblInput1.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL10T1 = new TextArea();
		taUserInputL10T1.setPrefSize(taWidth, taLineHeight);
		taUserInputL10T1.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL10T1.setText("public class L10T1Response\n{\n\tpublic static void main()\n\t{\n\n\t}//end main method\n}//end L10T1Response class");
		Button btnSubmitL10T1 = new Button("Submit");
		btnSubmitL10T1.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL10T1Input = new HBox(GAP, lblInput1, taUserInputL10T1, btnSubmitL10T1);
		vbxL10.getChildren().add(hbxL10T1Input);
		//for user's output
		Label lblOutput1 = new Label("Output:     ");
		lblOutput1.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL10T1.setPrefSize(taWidth, taLineHeight);
		HBox hbxL10T1Output = new HBox(GAP, lblOutput1, taUserOutputL10T1);
		vbxL10.getChildren().add(hbxL10T1Output);
		//for user's result
		Label lblCriteria1 = new Label("Criteria");
		CheckBox cbL10T1a = new CheckBox("Begins with System.out.println(");
		cbL10T1a.setDisable(true);
		CheckBox cbL10T1b = new CheckBox("Output is a decimal value of an exponent calculation");
		cbL10T1b.setDisable(true);
		CheckBox cbL10T1c = new CheckBox("Code contains a call to the Math.pow() method");
		cbL10T1c.setDisable(true);
		CheckBox cbL10T1d = new CheckBox("Ends with );");
		cbL10T1d.setDisable(true);
		HBox hbxL10T1Checklist = new HBox(GAP, lblCriteria1, cbL10T1a, cbL10T1b, cbL10T1c, cbL10T1d);
		hbxL10T1Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria1.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		vbxL10.getChildren().add(hbxL10T1Checklist);
	
		//adding the name of the current task
		Label lblL10T2Header = new Label("\n\n\nTask #2: Reassigning Elements");
		lblL10T2Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		if(user.getProgressLevel()<(progressIncrease*32))
		{
			lblL10T2Header.setVisible(false);
			lblL10T2Header.setManaged(false);
		}//end if
		vbxL10.getChildren().add(lblL10T2Header);
	
		//creating, setting, and adding the second task instructions
		Text textL10T2a = new Text("\n\nNow that you know how to create an integer array in Java, what changing the values of elements in an integer array? "
				+ "Well, this is also done very similar bto as it would be done in Python. Using the array called ");
		textL10T2a.setFill(Color.BLACK);
		Text textL10T2b = new Text("a");
		textL10T2b.setFill(Color.rgb(121, 76, 52));
		Text textL10T2c = new Text(" (created in the instructions of the previous task), let's say that you wanted to change the element at index 3 to 43. You could complete this change by using the following line of code.");
		textL10T2c.setFill(Color.BLACK);
		Text textL10T2d = new Text("\na");
		textL10T2d.setFill(Color.rgb(121, 76, 52));
		Text textL10T2e = new Text("[3]=43;");
		textL10T2e.setFill(Color.BLUE);
		Text textL10T2f = new Text("\nWith the new array elements changing from 4, 65, 3, 7, 2 to 4, 65, 3, 43, 2.");
		textL10T2f.setFill(Color.BLACK);
		Text textL10T2g = new Text("\nIn this task, you will be modifying some of the elements from the ");
		textL10T2g.setFill(Color.BLACK);
		Text textL10T2h = new Text("oneToTen");
		textL10T2h.setFill(Color.rgb(121, 76, 52));
		Text textL10T2i = new Text(" array that you created in the previous task. Change the first element to 83 and the ninth element to -4. After this, print each element from the ");
		textL10T2i.setFill(Color.BLACK);
		Text textL10T2j = new Text("oneToTen");
		textL10T2j.setFill(Color.rgb(121, 76, 52));
		Text textL10T2k = new Text(" array with one element per line. However, instead of using a for loop, you will need to use a while loop. Hint: create an integer variable called ");
		textL10T2k.setFill(Color.BLACK);
		Text textL10T2l = new Text("idx");
		textL10T2l.setFill(Color.rgb(121, 76, 52));
		Text textL10T2m = new Text(" on the first line of code and initialize it to 0. Then use this variable to keep track of the current index and in the while loop condition.");
		textL10T2m.setFill(Color.BLACK);
		TextFlow textflowL10T2 = new TextFlow(textL10T2a, textL10T2b, textL10T2c, textL10T2d, textL10T2e, textL10T2f, textL10T2g, textL10T2h, textL10T2i, textL10T2j, textL10T2k, textL10T2l, textL10T2m);
		textflowL10T2.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*32))
		{
			textflowL10T2.setVisible(false);
			textflowL10T2.setManaged(false);
		}//end if
		vbxL10.getChildren().add(textflowL10T2);
	
		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput2 = new Label("Your code: ");
		lblInput2.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL10T2 = new TextArea();
		taUserInputL10T2.setPrefSize(taWidth, taLineHeight*4);
		taUserInputL10T2.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL10T2.setText("public class L10T2Response\n{\n\tpublic static void main()\n\t{\nint [] oneToTen = new int [10] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; //initialization of the array from the last task, do not modify this line of code\n\n\t}//end main method\n}//end L10T2Response class");
		Button btnSubmitL10T2 = new Button("Submit");
		btnSubmitL10T2.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL10T2Input = new HBox(GAP, lblInput2, taUserInputL10T2, btnSubmitL10T2);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*32))
		{
			hbxL10T2Input.setVisible(false);
			hbxL10T2Input.setManaged(false);
		}//end if
		vbxL10.getChildren().add(hbxL10T2Input);
		//for user's output
		Label lblOutput2 = new Label("Output:     ");
		lblOutput2.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL10T2.setPrefSize(taWidth, taLineHeight);
		HBox hbxL10T2Output = new HBox(GAP, lblOutput2, taUserOutputL10T2);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*32))
		{
			hbxL10T2Output.setVisible(false);
			hbxL10T2Output.setManaged(false);
		}//end if
		vbxL10.getChildren().add(hbxL10T2Output);
		//for user's result
		Label lblCriteria2 = new Label("Criteria");
		CheckBox cbL10T2a = new CheckBox("num was created as a double variable and initialized to a double value");
		cbL10T2a.setDisable(true);
		CheckBox cbL10T2b = new CheckBox("squareRoot was created as a double variable and initialized correctly using the correct method from the Math class");
		cbL10T2b.setDisable(true);
		CheckBox cbL10T2c = new CheckBox("Output is the value of squareRoot");
		cbL10T2c.setDisable(true);
		HBox hbxL10T2Checklist = new HBox(GAP, lblCriteria2, cbL10T2a, cbL10T2b, cbL10T2c);
		hbxL10T2Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria2.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*32))
		{
			hbxL10T2Checklist.setVisible(false);
			hbxL10T2Checklist.setManaged(false);
		}//end if
		vbxL10.getChildren().add(hbxL10T2Checklist);
	
		//adding the name of the current task
		Label lblL10T3Header = new Label("\n\n\nTask #3: playerNames");
		lblL10T3Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		if(user.getProgressLevel()<(progressIncrease*33))
		{
			lblL10T3Header.setVisible(false);
			lblL10T3Header.setManaged(false);
		}//end if
		vbxL10.getChildren().add(lblL10T3Header);
		
		//creating, setting, and adding the third task instructions
		Text textL10T3a = new Text("\n\nNow that you know how to create an integer array in Java, what about other types of arrays? \nTo create any type of array in Java, they are all very similar "
				+ "(see previous task for integer array), the only difference is using the correct element type. \nFor example, for a boolean array of size 7 called ");
		textL10T3a.setFill(Color.BLACK);
		Text textL10T3b = new Text("boolArray");
		textL10T3b.setFill(Color.rgb(121, 76, 52));
		Text textL10T3c = new Text(" could be initialized as follows.");
		textL10T3c.setFill(Color.BLACK);
		Text textL10T3d = new Text("\nboolean []");
		textL10T3d.setFill(Color.BLUE);
		Text textL10T3e = new Text(" boolArray");
		textL10T3e.setFill(Color.rgb(121, 76, 52));
		Text textL10T3f = new Text(" = new boolean [7];");
		textL10T3f.setFill(Color.BLUE);
		Text textL10T3g = new Text("\nAs shown above, you only need to change the array type for different element value types. \n"
				+ "Notice that I did not add any {} before the ;? This just means that all elements in that array will be initialized to the default value of that type (in this case, the default value "
				+ "of boolean is false. \nIn addition, the default value of an integer is 0, the defalut value of a double is 0.0, the default value of a String is null, and the default value of a "
				+ "character is the null character (or '\\u0000')). \n"
				+ "The {} used in the first task for an integer array was just an easier way to initialize the element values at the start (an alternative could be to do .");
		textL10T3g.setFill(Color.BLACK);
		Text textL10T3h = new Text("\na");
		textL10T3h.setFill(Color.rgb(121, 76, 52));
		Text textL10T3i = new Text("[0]=4;");
		textL10T3i.setFill(Color.BLUE);
		Text textL10T3j = new Text("\na");
		textL10T3j.setFill(Color.rgb(121, 76, 52));
		Text textL10T3k = new Text("[1]=65;");
		textL10T3k.setFill(Color.BLUE);
		Text textL10T3l = new Text("\na");
		textL10T3l.setFill(Color.rgb(121, 76, 52));
		Text textL10T3m = new Text("[2]=3;");
		textL10T3m.setFill(Color.BLUE);
		Text textL10T3n = new Text("\na");
		textL10T3n.setFill(Color.rgb(121, 76, 52));
		Text textL10T3o = new Text("[3]=7;");
		textL10T3o.setFill(Color.BLUE);
		Text textL10T3p = new Text("\na");
		textL10T3p.setFill(Color.rgb(121, 76, 52));
		Text textL10T3q = new Text("[4]=2;");
		textL10T3q.setFill(Color.BLUE);
		Text textL10T3r = new Text("\nAs you can tell, this would be very tedious compared to the {}, especially if the array was larger. \nNow, for this task, you will be creating a String "
				+ "array called ");
		textL10T3r.setFill(Color.BLACK);
		Text textL10T3s = new Text("playerNames");
		textL10T3s.setFill(Color.rgb(121, 76, 52));
		Text textL10T3t = new Text(" of size 5 (do not initialize any elements in the array yet). After this, use a for loop to ask the user to enter each of the players' names. In the for loop, instead of using <5 or <=4, use <playerNames.length \n"
				+ "playerName.length is a variable predefined in Java that stores an array's size (in this case 5), and can be really helpful, especially if the size of the array is user-defiend and thus unknown before compile time, \n"
				+ "or even if you think that you might need to add/remove an index from the array and change the size, then you will only need to change it in one location (at the initialization of the array)..\n"
				+ "After the user enters each name, print ");
		textL10T3t.setFill(Color.BLACK);
		Text textL10T3u = new Text("Hello, name!");
		textL10T3u.setStyle("-fx-text-fill: black; -fx-font: "+TASK_INSTR_FONT+"\"Comic Sans MS\"");
		Text textL10T3v = new Text(" to the screen (replace name with the players' actual name).");
		textL10T3v.setFill(Color.BLACK);
		TextFlow textflowL10T3 = new TextFlow(textL10T3a, textL10T3b, textL10T3c, textL10T3d, textL10T3e, textL10T3f, textL10T3g, textL10T3h, textL10T3i, textL10T3j, textL10T3k, textL10T3l, textL10T3m, textL10T3n, textL10T3o, textL10T3p, textL10T3q, textL10T3r, textL10T3s, textL10T3t, textL10T3u, textL10T3v);
		textflowL10T3.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*33))
		{
			textflowL10T3.setVisible(false);
			textflowL10T3.setManaged(false);
		}//end if
		vbxL10.getChildren().add(textflowL10T3);
	
		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput3 = new Label("Your code: ");
		lblInput3.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL10T3 = new TextArea();
		taUserInputL10T3.setPrefSize(taWidth, taLineHeight*7);
		taUserInputL10T3.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL10T3.setText("public class L10T3Response\n{\n\tpublic static void main()\n\t{\n\n\t}//end main method\n}//end L10T3Response class");
		Button btnSubmitL10T3 = new Button("Submit");
		btnSubmitL10T3.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL10T3Input = new HBox(GAP, lblInput3, taUserInputL10T3, btnSubmitL10T3);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*33))
		{
			hbxL10T3Input.setVisible(false);
			hbxL10T3Input.setManaged(false);
		}//end if
		vbxL10.getChildren().add(hbxL10T3Input);
		//for user's output
		Label lblOutput3 = new Label("Output:     ");
		lblOutput3.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL10T3.setPrefSize(taWidth, taLineHeight);
		HBox hbxL10T3Output = new HBox(GAP, lblOutput3, taUserOutputL10T3);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*33))
		{
			hbxL10T3Output.setVisible(false);
			hbxL10T3Output.setManaged(false);
		}//end if
		vbxL10.getChildren().add(hbxL10T3Output);
		//for user's result
		Label lblCriteria3 = new Label("Criteria");
		CheckBox cbL10T3a = new CheckBox("degrees, radians, sine, cosine, and tangent are are created as double variables and initialized correctly");
		cbL10T3a.setDisable(true);
		CheckBox cbL10T3b = new CheckBox("The Math class was used to calculate the values of radians, sine, cosine, and tangent");
		cbL10T3b.setDisable(true);
		CheckBox cbL10T3c = new CheckBox("Output is the values of degrees, radians, sine, cosine, and tangent with all values separated by a comma and a space");
		cbL10T3c.setDisable(true);
		HBox hbxL10T3Checklist = new HBox(GAP, lblCriteria3, cbL10T3a, cbL10T3b, cbL10T3c);
		hbxL10T3Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria3.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*33))
		{
			hbxL10T3Checklist.setVisible(false);
			hbxL10T3Checklist.setManaged(false);
		}//end if
		vbxL10.getChildren().add(hbxL10T3Checklist);

		//adding the name of the current task
		Label lblL10T4Header = new Label("\n\n\nTask #4: numValues");
		lblL10T4Header.setStyle("-fx-text-fill: black; -fx-font-size: "+TASK_HEADER_FONT);
		if(user.getProgressLevel()<(progressIncrease*33))
		{
			lblL10T4Header.setVisible(false);
			lblL10T4Header.setManaged(false);
		}//end if
		vbxL10.getChildren().add(lblL10T4Header);

		//creating, setting, and adding the third task instructions
		Text textL10T4a = new Text("\n\nThis task will be combining some things that you have learned so far, but with a main focus on lessons 9 and 10. \n"
				+ "In this task, you will first create an integer array called ");
		textL10T4a.setFill(Color.BLACK);
		Text textL10T4b = new Text("numValues");
		textL10T4b.setFill(Color.rgb(121, 76, 52));
		Text textL10T4c = new Text(" with the following element values \n5, 43, 68, 4, -2, 93, 3 \nAfter this, using a loop (choose the type of loop that you feel is the best in this case, if you do choose to have a counter variable not initialized on the first line of the for loop, add this after your creation of the array), "
				+ "print each element to the screen (one element per line). \nNext, call to a void method called printNumValues (which you will implement after the main method) with ");
		textL10T4c.setFill(Color.BLACK);
		Text textL10T4d = new Text("numValues");
		textL10T4d.setFill(Color.rgb(121, 76, 52));
		Text textL10T4e = new Text(" as the parameter. \nInside of the printNumValues, change the elements individually "
				+ "and then print the array elements to the screen so that you get the following output from this method. \n");
		textL10T4e.setFill(Color.BLACK);
		Text textL10T4f = new Text("\n\n6 -43 68 9 -2 84 3 \n\n");
		textL10T4f.setStyle("-fx-text-fill: black; -fx-font: "+TASK_INSTR_FONT+"\"Comic Sans MS\"");
		Text textL10T4g = new Text("\nNote that there is a space after the 3, and there are two empty lines at the start and end.");
		textL10T4g.setFill(Color.BLACK);
		TextFlow textflowL10T4 = new TextFlow(textL10T4a, textL10T4b, textL10T4c, textL10T4d, textL10T4e, textL10T4f, textL10T4g);
		textflowL10T4.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*33))
		{
			textflowL10T4.setVisible(false);
			textflowL10T4.setManaged(false);
		}//end if
		vbxL10.getChildren().add(textflowL10T4);

		//creating/setting/adding all text fields/labels/buttons/check boxes for user to attempt the current task and view their result
		//for user's input
		Label lblInput4 = new Label("Your code: ");
		lblInput4.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		TextArea taUserInputL10T4 = new TextArea();
		taUserInputL10T4.setPrefSize(taWidth, taLineHeight*7);
		taUserInputL10T4.setStyle("-fx-text-fill: black; -fx-font-size: "+TEXTAREA_FONT);
		taUserInputL10T4.setText("public class L10T4Response\n{\n\n}//end L10T4Response class");
		Button btnSubmitL10T4 = new Button("Submit");
		btnSubmitL10T4.setStyle("-fx-font-size: "+CONTROL_FONT);
		HBox hbxL10T4Input = new HBox(GAP, lblInput4, taUserInputL10T4, btnSubmitL10T4);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*33))
		{
			hbxL10T4Input.setVisible(false);
			hbxL10T4Input.setManaged(false);
		}//end if
		vbxL10.getChildren().add(hbxL10T4Input);
		//for user's output
		Label lblOutput4 = new Label("Output:     ");
		lblOutput4.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		taUserOutputL10T4.setPrefSize(taWidth, taLineHeight);
		HBox hbxL10T4Output = new HBox(GAP, lblOutput4, taUserOutputL10T4);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*33))
		{
			hbxL10T4Output.setVisible(false);
			hbxL10T4Output.setManaged(false);
		}//end if
		vbxL10.getChildren().add(hbxL10T4Output);
		//for user's result
		Label lblCriteria4 = new Label("Criteria");
		CheckBox cbL10T4a = new CheckBox("degrees, radians, sine, cosine, and tangent are are created as double variables and initialized correctly");
		cbL10T4a.setDisable(true);
		CheckBox cbL10T4b = new CheckBox("The Math class was used to calculate the values of radians, sine, cosine, and tangent");
		cbL10T4b.setDisable(true);
		CheckBox cbL10T4c = new CheckBox("Output is the values of degrees, radians, sine, cosine, and tangent with all values separated by a comma and a space");
		cbL10T4c.setDisable(true);
		HBox hbxL10T4Checklist = new HBox(GAP, lblCriteria4, cbL10T4a, cbL10T4b, cbL10T4c);
		hbxL10T4Checklist.setStyle("-fx-opacity: 1; -fx-font-size: "+CONTROL_FONT);
		lblCriteria4.setStyle("-fx-text-fill: black; -fx-font-size: "+LABEL_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*33))
		{
			hbxL10T4Checklist.setVisible(false);
			hbxL10T4Checklist.setManaged(false);
		}//end if
		vbxL10.getChildren().add(hbxL10T4Checklist);

		//creating and adding a label to notify user that they have completed the current lesson
		Label lblL10Done = new Label("Congratulations, "+user.getfName()+"! You have finished Lesson #5: The Math and Random Classes.\n" + 
				"Click one of the buttons below to either restart this lesson, choose another lesson from the menu, go to the previous lesson, or continue with the next lesson.");
		lblL10Done.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		//determining whether or not the current task will be shown, based on whether or not user has completed all of the previous tasks
		if(user.getProgressLevel()<(progressIncrease*35))
		{
			lblL10Done.setVisible(false);
			lblL10Done.setManaged(false);
		}//end if
		vbxL10.getChildren().add(lblL10Done);
	
		//creating and adding the buttons at the bottom of the screen
		Button btnPreviousLesson = new Button("Previous Lesson");
		btnPreviousLesson.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnPreviousLesson.setOnAction(event -> lesson7(myStage));
		Button btnLessonMenu = new Button("Lesson Menu");
		btnLessonMenu.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnLessonMenu.setOnAction(event -> lessonMenu(myStage));
		Button btnExit = new Button("Exit");
		btnExit.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnExit.setOnAction(event -> 
		{
			try
			{
				exitProgram();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		});
		Button btnRestartL10 = new Button("Restart Lesson");
		btnRestartL10.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnRestartL10.setOnAction(event -> lesson10(myStage));
		Button btnNextLesson = new Button("Next Lesson");
		btnNextLesson.setStyle("-fx-font-size: "+CONTROL_FONT);
		if(user.getProgressLevel()<(progressIncrease*35))
		{
			btnNextLesson.setDisable(true);
		}//end if
		//		btnNextLesson.setOnAction(event -> lesson10(myStage));
		HBox hbxL10Controls = new HBox(GAP, btnPreviousLesson, btnLessonMenu, btnExit, btnRestartL10, btnNextLesson);
		vbxL10.getChildren().add(hbxL10Controls);	
	
		//determining what to do when the submit button for L10T1 is clicked
		btnSubmitL10T1.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			int checkboxSelectedCounterL10T1=0;
			trialCounter[7][0]++;

			//creating the array to store user's output and adding user input to the userResponse array
			String [] outputL10T1=new String[2];
			userResponse[7][0][trialCounter[7][0]]=taUserInputL10T1.getText();

			outputL10T1=CheckJava.submitL10T1(taUserInputL10T1, TEXTAREA_FONT);
			
			taUserOutputL10T1.setText(outputL10T1[0]);
			taUserOutputL10T1.setStyle(outputL10T1[1]);

			//resetting all checkboxes in this task to be deselected
			cbL10T1a.setSelected(false);
			cbL10T1b.setSelected(false);
			cbL10T1c.setSelected(false);
			cbL10T1d.setSelected(false);

			//if statements to check the user's inputed code and the generated output and make the correct checkboxes for this task selected
			if(taUserInputL10T1.getText().startsWith("System.out.println("))
			{
				cbL10T1a.setSelected(true);
				checkboxSelectedCounterL10T1++;
			}//end if for first checkbox
			if(!taUserOutputL10T1.getText().startsWith("Error!") && taUserInputL10T1.getText().contains("Math.pow("))
			{
				cbL10T1b.setSelected(true);
				checkboxSelectedCounterL10T1++;
			}//end if for second checkbox
			if(!taUserOutputL10T1.getText().startsWith("Error!"))
			{
				cbL10T1c.setSelected(true);
				checkboxSelectedCounterL10T1++;
			}//end if for third checkbox
			if(taUserInputL10T1.getText().endsWith(");"))
			{
				cbL10T1d.setSelected(true);
				checkboxSelectedCounterL10T1++;
			}//end inside if for fourth checkbox
			//when the user has successfully completed the current task and is ready for the next task
			if(checkboxSelectedCounterL10T1==4)
			{
				//setting the next task as visible
				textflowL10T2.setVisible(true);
				textflowL10T2.setManaged(true);
				hbxL10T2Input.setVisible(true);
				hbxL10T2Input.setManaged(true);
				hbxL10T2Output.setVisible(true);
				hbxL10T2Output.setManaged(true);
				hbxL10T2Checklist.setVisible(true);
				hbxL10T2Checklist.setManaged(true);
	
				//changing the progress value of the progress bar if this is the user's first time doing the current task
				if(user.getProgressLevel()<(progressIncrease*20))
				{
					pbsUserProgress.setProgress((progressIncrease*20));
					user.setProgressLevel(pbsUserProgress.getProgress());
				}//end inside if
			}//end outside if
		});//end setOnAction for btnSubmitL10T1
	
		//determining what to do when the submit button for L10T2 is clicked
		btnSubmitL10T2.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			int checkboxSelectedCounterL10T2=0;
			trialCounter[7][1]++;
	
			//creating the array to store user's output and adding user input to the userResponse array
			String [] outputL10T2=new String[2];
			userResponse[7][1][trialCounter[7][1]]=taUserInputL10T2.getText();
	
			outputL10T2=CheckJava.submitL10T2(taUserInputL10T2, TEXTAREA_FONT);
			taUserOutputL10T2.setText(outputL10T2[0]);
			taUserOutputL10T2.setStyle(outputL10T2[1]);
	
			//resetting all checkboxes in this task to be deselected
			cbL10T2a.setSelected(false);
			cbL10T2b.setSelected(false);
			cbL10T2c.setSelected(false);
	
			//if statements to check the user's inputed code and the generated output and make the correct checkboxes for this task selected
			if(!taUserOutputL10T2.getText().contains("Don't forget to initialize the variable num to a double value.") && !taUserOutputL10T2.getText().contains("The number you want to find the square root of must be an integer or decimal value.") && !taUserOutputL10T2.getText().contains("Don't forget to create and initialize a double variable called num on your first line of code."))
			{
				cbL10T2a.setSelected(true);
				checkboxSelectedCounterL10T2++;
			}//end outside if for first checkbox
			if(!taUserOutputL10T2.getText().contains("Please check how you initialize the variable squareRoot/calculate the square root of the variable num.") && !taUserOutputL10T2.getText().contains("Don't forget to create a double variable called squareRoot and initialize it to the square root of the value of num on the second line of code."))
			{
				cbL10T2b.setSelected(true);
				checkboxSelectedCounterL10T2++;
			}//end if for second checkbox
			if(!taUserOutputL10T2.getText().startsWith("Error!"))
			{
				cbL10T2c.setSelected(true);
				checkboxSelectedCounterL10T2++;
			}//end if for third checkbox
	
			//when the user has successfully completed the current task and is ready for the next task
			if(checkboxSelectedCounterL10T2==3)
			{
				//setting the next task as visible
				textflowL10T3.setVisible(true);
				textflowL10T3.setManaged(true);
				hbxL10T3Input.setVisible(true);
				hbxL10T3Input.setManaged(true);
				hbxL10T3Output.setVisible(true);
				hbxL10T3Output.setManaged(true);
				hbxL10T3Checklist.setVisible(true);
				hbxL10T3Checklist.setManaged(true);
	
				//changing the progress value of the progress bar if this is the user's first time doing the current task
				if(user.getProgressLevel()<(progressIncrease*21))
				{
					pbsUserProgress.setProgress((progressIncrease*21));
					user.setProgressLevel(pbsUserProgress.getProgress());
				}//end inside if
			}//end outside if
		});//end setOnAction for btnSubmitL10T2
	
		//determining what to do when the submit button for L10T3 is clicked
		btnSubmitL10T3.setOnAction(event -> 
		{
			//creating/initializing counters for this submission
			int checkboxSelectedCounterL10T3=0;
			trialCounter[7][2]++;
	
			//creating the array to store user's output and adding user input to the userResponse array
			String [] outputL10T3=new String[2];
			userResponse[7][2][trialCounter[7][2]]=taUserInputL10T3.getText();
	
			outputL10T3=CheckJava.submitL10T3(taUserInputL10T3, TEXTAREA_FONT);
			taUserOutputL10T3.setText(outputL10T3[0]);
			taUserOutputL10T3.setStyle(outputL10T3[1]);
	
			//resetting all checkboxes in this task to be deselected
			cbL10T3a.setSelected(false);
			cbL10T3b.setSelected(false);
			cbL10T3c.setSelected(false);
	
			//if statements to check the user's inputed code and the generated output and make the correct checkboxes for this task selected
			if(!taUserOutputL10T3.getText().contains("Don't forget to initialize the variable degrees to a double value.") && !taUserOutputL10T3.getText().contains("The value you have initialized to degrees is not a double value.") && !taUserOutputL10T3.getText().contains("Please check how you initialize the variable radians/calculate the radian converted value of the variable degrees.") && !taUserOutputL10T3.getText().contains("Don't forget to create a double variable called radians and initialize it to the converted value of the variable degrees on the second line of code.") && !taUserOutputL10T3.getText().contains("Please check how you initialize the variable sine/calculate the value of sine with the variable radians.") && !taUserOutputL10T3.getText().contains("Don't forget to create a double variable called sine and initialize it to the sine value of the variable radians using the Math class on your third line of code.") && !taUserOutputL10T3.getText().contains("Please check how you initialize the variable cosine/calculate the value of cosine with the variable radians.") && !taUserOutputL10T3.getText().contains("Don't forget to create a double variable called cosine and initialize it to the cosine value of the variable radians using the Math class on your fourth line of code.") && !taUserOutputL10T3.getText().contains("Please check how you initialize the variable tangent/calculate the value of tangent with the variable radians.") && !taUserOutputL10T3.getText().contains("Don't forget to create a double variable called tangent and initialize it to the tangent value of the variable radians using the Math class on your fifth line of code.Since you did not correctly initialize the variable radians, the values of sine, cosine, and tangent were not able to be initialized and nothing was able to be printed to the screen.") && !taUserOutputL10T3.getText().contains("Since you did not correctly initialize the variable radians, the values of sine, cosine, and tangent were not able to be initialized and nothing was able to be printed to the screen.") && !taUserOutputL10T3.getText().contains("Don't forget to create and initialize a double variable called degrees on your first line of code."))
			{
				cbL10T3a.setSelected(true);
				cbL10T3b.setSelected(true);
				checkboxSelectedCounterL10T3+=2;
			}//end if for first and second checkbox
			if(!taUserOutputL10T3.getText().startsWith("Error!"))
			{
				cbL10T3c.setSelected(true);
				checkboxSelectedCounterL10T3++;
			}//end if for third checkbox
	
			//when the user has successfully completed the current task and is ready for the next task
			if(checkboxSelectedCounterL10T3==3)
			{
				//setting the next task as visible
				lblL10Done.setVisible(true);
				lblL10Done.setManaged(true);
	
				//changing the progress value of the progress bar if this is the user's first time doing the current task
				if(user.getProgressLevel()<(progressIncrease*22))
				{
					pbsUserProgress.setProgress((progressIncrease*22));
					user.setProgressLevel(pbsUserProgress.getProgress());
				}//end inside if
			}//end outside if
		});//end setOnAction for btnSubmitL10T3
		

		//determining what to do when the submit button for L10T4 is clicked
		btnSubmitL10T4.setOnAction(event -> 
		{
//			//creating/initializing counters for this submission
//			int checkboxSelectedCounterL10T4=0;
//			trialCounter[5][3]++;
//
//			//creating the array to store user's output and adding user input to the userResponse array
//			String [] outputL10T4=new String[3];
//			userResponse[5][3][trialCounter[5][3]]=taUserInputL10T4.getText();
//			String [] runInputL10T4 = new String [2];
//			txtRunInputL10T4a.setVisible(false);
//			txtRunInputL10T4a.setText("");
//			txtRunInputL10T4a.setEditable(true);
//			taUserOutputL10T4b.setVisible(false);
//			txtRunInputL10T4b.setVisible(false);
//			txtRunInputL10T4b.setText("");
//			txtRunInputL10T4b.setEditable(true);
//
//			//determining and setting the output that the user will see based on their submission
//			try
//			{
//				outputL10T4=CheckJava.submitL10T4(taUserInputL10T4, TEXTAREA_FONT);
//			}
//			catch (ScriptException e)
//			{
//				e.printStackTrace();
//			}//end try catch
//			taUserOutputL10T4a.setText(outputL10T4[0]);
//			taUserOutputL10T4a.setStyle(outputL10T4[2]);
//			taUserOutputL10T4b.setText(outputL10T4[1]);
//			taUserOutputL10T4b.setStyle(outputL10T4[2]);
//
//			//resetting all checkboxes in this task to be deselected
//			cbL10T4a.setSelected(false);
//			cbL10T4b.setSelected(false);
//			cbL10T4c.setSelected(false);
//			cbL10T4d.setSelected(false);
//			cbL10T4e.setSelected(false);
//			cbL10T4f.setSelected(false);
//
//			if(!taUserOutputL10T4a.getText().startsWith("Error!"))
//			{
//				txtRunInputL10T4a.setVisible(true);
//
//				txtRunInputL10T4a.setOnAction(new EventHandler<ActionEvent>()
//				{
//					@Override
//					public void handle(ActionEvent event)
//					{
//						boolean isIntL10T4=ErrorChecks.checkIfInt(txtRunInputL10T4a.getText());
//
//						if(!taUserOutputL10T4a.getText().contains("Don't forget to create the object from the Scanner class in order to gather input from the user for this task."))
//						{
//							cbL10T4a.setSelected(true);
//						}//end if for first checkbox
//						if(taUserInputL10T4.getText().contains("int numOfFamilyMembers;"))
//						{
//							cbL10T4b.setSelected(true);
//						}//end if for second checkbox
//						if(taUserInputL10T4.getText().contains("String favFood;"))
//						{
//							cbL10T4c.setSelected(true);
//						}//end if for third checkbox
//						if(!taUserOutputL10T4a.getText().contains("Don't forget to print to the screen and tell/ask user what they should input (the "))
//						{
//							cbL10T4d.setSelected(true);
//						}//end if for fourth checkbox
//						if(!taUserOutputL10T4a.getText().contains("Don't forget to print to the screen and tell/ask user what they should input (their "))
//						{
//							cbL10T4e.setSelected(true);
//						}//end if for fifth checkbox
//
//						if(isIntL10T4)
//						{
//							taUserOutputL10T4b.setVisible(true);
//							txtRunInputL10T4b.setEditable(true);
//							txtRunInputL10T4b.setVisible(true);
//							txtRunInputL10T4b.setOnAction(new EventHandler<ActionEvent>()
//							{
//								@Override
//								public void handle(ActionEvent event)
//								{
//									cbL10T4f.setSelected(true);
//								}//end inside handle method
//							});//end setOnAction method for txtRunInputL10T4b
//						}//end if
//
//						else
//						{
//							taUserOutputL10T4a.setText("Error!\n- You must assign numOfFamilyMembers as an integer.");
//							taUserOutputL10T4a.setStyle("-fx-text-fill: red; -fx-font: "+TEXTAREA_FONT+"\"Comic Sans MS\"");
//							txtRunInputL10T4a.setVisible(false);
//						}//end else
//
//						if(!taUserOutputL10T4a.getText().contains("Don't forget to close your scanner object on the sixth line of code."))
//						{
//							cbL10T4g.setSelected(true);
//						}//end if for seventh checkbox
//
//						if(cbL10T4a.isSelected() && cbL10T4b.isSelected() && cbL10T4c.isSelected() && cbL10T4d.isSelected() && cbL10T4e.isSelected() && cbL10T4f.isSelected() && cbL10T4g.isSelected())
//						{
//							//setting the next task as visible
//							lblL10Done.setVisible(true);
//							lblL10Done.setManaged(true);
//				
//							//changing the progress value of the progress bar if this is the user's first time doing the current task
//							if(user.getProgressLevel()<(progressIncrease*22))
//							{
//								pbsUserProgress.setProgress((progressIncrease*22));
//								user.setProgressLevel(pbsUserProgress.getProgress());
//							}//end inside if
//						}//end outside if
//					}//end outside handle method
//				});//end setOnAction method for txtRunInputL10T4a
//			}//end if
		});//end setOnAction for btnSubmitL10T4
		
		//creating and setting the scroll pane for this lesson
		ScrollPane spL10 = new ScrollPane();
		spL10.setContent(vbxL10);
	
		//creating the scene
		Scene sceneL10 = new Scene(spL10);
	
		lastScene="lesson10"; //setting the previous scene to this scene
	
		//setting/showing the stage
		myStage.getIcons().add(new Image("FromOneToAnotherLogo.png"));
		myStage.setTitle("From C++ to Java - ... - Lesson #10");
		myStage.setScene(sceneL10);
		myStage.centerOnScreen();
		myStage.show();
	}//end lesson10 method

	
	/* Method Author: Brooke Cronin
	 * Method Name: exitProgram
	 * Description: Saves all of the user's information to a .txt file so that they will be able to continue the next time, then terminates the program.
	 * Parameters: N/A
	 * Returns: N/A
	 * Throws: IOException
	 */

	public static void exitProgram() throws IOException, NullPointerException
	{
		FileWriter myWriter = new FileWriter(user.getSaveLocation()+user.getFileName());
		PrintWriter myOutput = new PrintWriter(myWriter);

		myOutput.println(user.getProgressLevel());

		//a for loop to check the user's response for each lesson
		for(int i=0; i<userResponse.length; i++)
		{
			myOutput.println("lesson "+(i+1));

			//a for loop to check the user's response for each task
			for(int j=0; j<userResponse[i].length; j++)
			{
				//when all of the tasks in the first lesson has been checked
				if(i==0 && j==4)
				{
					break;
				}//end if
				//when all of the tasks in the second lesson has been checked
				if(i==1 && j==7)
				{
					break;
				}//end if
				//when all of the tasks in the third lesson has been checked
				if(i==2 && j==4)
				{
					break;
				}//end if
				//when all of the tasks in the fourth lesson has been checked
				if(i==3 && j==4)
				{
					break;
				}//end if
				//when all of the tasks in the fifth lesson has been checked
				if(i==4 && j==5)
				{
					break;
				}//end if
				myOutput.println("task "+(j+1));

				//a for loop to check the user's response for each trial
				for(int k=0; k<userResponse[i][j].length; k++)
				{
					System.out.println("k:"+k);
					//when user has not attempted the current task at all
					if(userResponse[i][j][0].equals(""))
					{
						myOutput.println("N/A");
						myOutput.close();
						System.exit(0);
					}//end if

					//when all of user's attempts for the current task has been recorded
					else if(userResponse[i][j][k].equals(""))
					{
						break;
					}//end if

					//when user has attempted the current task at least once
					else
					{
						myOutput.println("trial "+(k+1));
						myOutput.println(userResponse[i][j][k]);
					}//end else
				}//end third for loop
			}//end second for loop
		}//end first for loop
	}//end exitProgram method


	public static void main(String[] args)
	{
		launch(args);
	}//end main method
}//end class
