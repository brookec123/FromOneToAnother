package finalProject;

import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu extends Application {
	static final int GAP = 10;
	static String LESSON_HEADER_FONT = "50";
	static String TASK_HEADER_FONT = "40";
	static String TASK_INSTR_FONT = "25";
	static String LABEL_FONT = "20";
	static String CONTROL_FONT = "15";
	static String TEXTAREA_FONT = "15";

	@Override
	public void start(Stage myStage) throws Exception
	{
		mainMenu(myStage);

	}

	public void mainMenu(Stage myStage)
	{
		//creating the vertical box for the lesson menu screen
		VBox vbxMainMenu = new VBox(GAP);
		vbxMainMenu.setPadding(new Insets(GAP, GAP, GAP, GAP));

		//creating, setting, and adding a header Label
		AnchorPane apMainMenuHeader = new AnchorPane();
		Label lblMainMenuHeader = new Label("Lesson Menu");
		lblMainMenuHeader.setStyle("-fx-font-size: "+LESSON_HEADER_FONT);
		lblMainMenuHeader.setMaxWidth(Double.MAX_VALUE);
		AnchorPane.setLeftAnchor(lblMainMenuHeader, 0.0);
		AnchorPane.setRightAnchor(lblMainMenuHeader, 0.0);
		lblMainMenuHeader.setAlignment(Pos.CENTER);
		apMainMenuHeader.getChildren().add(lblMainMenuHeader);
		vbxMainMenu.getChildren().add(apMainMenuHeader);

		//creating, setting, and adding the first section Label
		Label lblLanguageKnown = new Label("Language Known:");
		lblLanguageKnown.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		vbxMainMenu.getChildren().add(lblLanguageKnown);

		//creating, setting, and adding the lessons from the first section in a combo box
		ComboBox<String> cmbLanguageKnown = new ComboBox<String>();
		cmbLanguageKnown.getItems().addAll("Java", "Python", "C++", "C", "None");
		cmbLanguageKnown.setStyle("-fx-font-size: "+TEXTAREA_FONT);
		vbxMainMenu.getChildren().add(cmbLanguageKnown);

		//creating, setting, and adding the first section Label
		Label lblLanguageToLearn = new Label("\n\nLanguage To Learn:");
		lblLanguageToLearn.setStyle("-fx-font-size: "+TASK_INSTR_FONT);
		vbxMainMenu.getChildren().add(lblLanguageToLearn);

		//creating, setting, and adding the lessons from the first section in a combo box
		ComboBox<String> cmbLanguageToLearn = new ComboBox<String>();
		cmbLanguageToLearn.getItems().addAll("Java", "Python", "C++", "C");
		cmbLanguageToLearn.setStyle("-fx-font-size: "+TEXTAREA_FONT);
		vbxMainMenu.getChildren().add(cmbLanguageToLearn);

		//adding an extra space between the drop down menu and buttons
		Label lblExtraSpace = new Label("\n\n\n\n\n\n\n\n");
		vbxMainMenu.getChildren().add(lblExtraSpace);

		//creating, setting, and adding the first section Label
		Label lblNotes = new Label("Notes:\nFor the language known, select the language that you are the most comfortable/proficient in. \nIf you have never coded before, do not know any of the langueges in the list, or have not coded in a very long time, choose \"None\" to get the best experience.\n\n");
		lblNotes.setStyle("-fx-font-size: "+TEXTAREA_FONT);
		vbxMainMenu.getChildren().add(lblNotes);

		//creating and adding the buttons at the bottom of the screen

		Button btnSelect = new Button("Select");
		btnSelect.setOnAction(event -> 
		{
			//trying to go to the method that will allow user to go to their selected lesson and catching when they have selected an invalid lesson
			try
			{
				goToClass(myStage, cmbLanguageKnown.getValue().toString(), cmbLanguageToLearn.getValue().toString());
			}
			catch (NullPointerException | IOException e)
			{
				error(myStage, "Sorry, but you need to choose a programming language from both of the dropdown menus.");
			}//end try catch
		});
		vbxMainMenu.getChildren().add(btnSelect);
		//creating and setting the scroll pane for this lesson
		ScrollPane spMainMenu = new ScrollPane();
		spMainMenu.setContent(vbxMainMenu);

		//creating the scene
		Scene sceneMainMenu = new Scene(spMainMenu);

		//setting/showing the stage
		myStage.getIcons().add(new Image("FromOneToAnotherLogo.png"));
		myStage.setTitle("From One to Another - Main Menu");
		myStage.setScene(sceneMainMenu);
		myStage.centerOnScreen();
		myStage.show();
	}//end mainMenu method

	
	public void goToClass(Stage myStage, String languageKnown, String languageToLearn) throws IOException
	{
		if(languageKnown.equals(languageToLearn))
		{
			error(myStage, "Sorry, but the language you know and the language that you want to learn must be different.");
		}
		else if(languageKnown.equals("Python") && languageToLearn.equals("Java"))
		{
			FromPythonToJava pyTojava = new FromPythonToJava();
			pyTojava.welcome(myStage);
		}
		else if(languageKnown.equals("C++") && languageToLearn.equals("Java"))
		{
			FromCPlusPlusToJava cppTojava = new FromCPlusPlusToJava();
			cppTojava.welcome(myStage);
		}
		else
		{
			error(myStage, "Sorry, but the combination that you have chosen has not been completed and will be coming soon.");
		}

	}//end goToClass method

	
	/* Method Author: Brooke Cronin
	 * Method Name: error
	 * Description: Creates the scene to show an error message to the user when they have chosen an invalid lesson.
	 * Parameters: Stage myStage
	 * Returns: N/A
	 */

	public void error(Stage myStage, String message)
	{
		//creating the vertical box for the error screen
		VBox vbxError = new VBox(GAP);
		vbxError.setPadding(new Insets(GAP, GAP, GAP, GAP));

		//creating, setting, and adding a message Label
		Label lblErrorMessage = new Label(message);
		lblErrorMessage.setStyle("-fx-font-size: "+LABEL_FONT);
		vbxError.getChildren().add(lblErrorMessage);

		//creating and adding the buttons at the bottom of the screen
		Button btnBack = new Button("Back");
		btnBack.setStyle("-fx-font-size: "+CONTROL_FONT);
		btnBack.setOnAction(event -> 
		{
			((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
			mainMenu(myStage); 
		});
		vbxError.getChildren().add(btnBack);

		//creating the scene
		Scene sceneError = new Scene(vbxError);

		//setting/showing the stage
		Stage errorStage = new Stage();

		errorStage.getIcons().add(new Image("FromOneToAnotherLogo.png"));
		errorStage.setTitle("From One to Another - ... - Main Menu - Error");
		errorStage.setScene(sceneError);
		errorStage.centerOnScreen();
		errorStage.show();
	}//end error method

	public static void main(String[] args) {
		launch(args);

	}

}
