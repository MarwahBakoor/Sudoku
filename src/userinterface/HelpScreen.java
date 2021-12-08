package userinterface;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


import constants.UIConst;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.text.Font;



public class HelpScreen extends Application{
	int index = 0;
	
public void start(Stage primaryStage) {

	Text Title = new Text("How to Play Sudoku?");
	Title.setFont(Font.font("Leelawadee", FontWeight.BOLD,  FontPosture.REGULAR, 30));
	ImageView sudokuimage = new ImageView(new Image("img\\sudokuboard.png"));
	
	// text for the slides saved in arrays
	
	String[] STitleList = {
		"Sudoku Rule 1: Use Numbers 1-9",
		"Sudoku Rule 2: Don’t Repeat Any Numbers",
		"Sudoku Rule 3: Don’t Guess",
		"Sudoku Rule 4: Use Process of Elimination"
	};
	
	String[] TextInstructionList = {
			"Sudoku is played on a grid of 9 x 9 spaces. Within the rows and columns are 9 “squares” (made up of 3 x 3 spaces). Each row, column and square (9 spaces each) needs to be filled out with the numbers 1-9, without repeating any numbers within the row, column or square. Does it sound complicated? As you can see from the image of an actual Sudoku grid, each Sudoku grid comes with a few spaces already filled in; the more spaces filled in, the easier the game – the more difficult Sudoku puzzles have very few spaces that are already filled in.",
			"Every square has to contain a single number\r\n" + "Only the numbers from 1 through to 9 can be used\r\n" + "Each 3×3 box can only contain each number from 1 to 9 once\r\n" + "Each vertical column can only contain each number from 1 to 9 once\r\n" + "Each horizontal row can only contain each number from 1 to 9 once \r\n \r\n \r\n",
			"Sudoku is a game of logic and reasoning, so you shouldn’t have to guess. If you don’t know what number to put in a certain space, keep scanning the other areas of the grid until you seen an opportunity to place a number. But don’t try to “force” anything – Sudoku rewards patience, insights, and recognition of patterns, not blind luck or guessing. \r\n \r\n \r\n",
			"to figure out which numbers can go in each space is to use “process of elimination” by checking to see which other numbers are already included within each square – since there can be no duplication of numbers 1-9 within each square (or row or column). his is how the process of elimination works in Sudoku – you find out which spaces are available, which numbers are missing – and then deduce, based on the position of those numbers within the grid, which numbers fit into each space."
		};
	
	
	
	sudokuimage.setFitHeight(350);
	sudokuimage.setFitWidth(350);
	Text secoundryTitle = new Text(STitleList[0]);
	secoundryTitle.setStyle("-fx-fill:#33304E;");
	secoundryTitle.setFont(Font.font("Leelawadee", FontWeight.BOLD,  FontPosture.REGULAR, 20));
	Text TextInstruction1 = new Text(TextInstructionList[0]);
	TextInstruction1.setFont(Font.font("Leelawadee UI Semilight", FontWeight.LIGHT,  FontPosture.REGULAR, 18));
	TextInstruction1.setWrappingWidth(UIConst.WINDOW_X-100);
	
	
	
	VBox ruleLayout = new VBox(10);
	ruleLayout.prefHeight(5000);
	ruleLayout.getChildren().addAll(secoundryTitle,TextInstruction1);

	
	//Next Button 
	
	Button NextButton = new Button(">");
	
	
	Button PrevButton = new Button("<");
	NextButton.getStyleClass().add("circle-button");
	PrevButton.getStyleClass().add("circle-button");
	HBox NPbuttonsLayout = new HBox(10);
	NPbuttonsLayout.getChildren().addAll(PrevButton,NextButton);
	// when press next button
	NextButton.setOnAction(e-> {
		if(index < 3) {
		index++;
		secoundryTitle.setText( STitleList[index]);
		TextInstruction1.setText(TextInstructionList[index]);
		if(index == 1) {
			sudokuimage.setImage(new Image("img\\Sudokuboard2.png"));
		}
		}
	});
	
	// when press prev button
	PrevButton.setOnAction(e-> {
		if(index > 0) {
			index--;
			secoundryTitle.setText( STitleList[index]);
			TextInstruction1.setText(TextInstructionList[index]);
			if(index == 0) {
				sudokuimage.setImage(new Image("img\\Sudokuboard.png"));
			}
		}
	});
	
	
	//Close Button 
	
	Button closeButton = new Button("Get It 👌");
	closeButton.getStyleClass().add("get-it-button");
	closeButton.setOnAction(e -> {
		primaryStage.close();
	});
	
	HBox AllbuttonsLayout = new HBox(300);
	AllbuttonsLayout.getChildren().addAll(closeButton, NPbuttonsLayout);

	
	VBox helpLayout = new VBox(30);
	helpLayout.setPadding(new Insets(40));
	helpLayout.setAlignment(Pos.TOP_CENTER);
	helpLayout.getChildren().addAll(Title,sudokuimage, ruleLayout, AllbuttonsLayout);
	helpLayout.setStyle("-fx-background-color:white;");
	
	
	Scene HelpScene = new Scene(helpLayout,UIConst.WINDOW_X,UIConst.WINDOW_Y);
	HelpScene.getStylesheets().add(getClass().getResource("stylesheet.css").toString());
	primaryStage.setScene(HelpScene);
	primaryStage.getIcons().add(new Image("img//sudokuLogo.png"));
	primaryStage.setTitle("Sudoku Help");
	primaryStage.show();

}

public static void main(String[] args) {
	Application.launch(args);
}


}