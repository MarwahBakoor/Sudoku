package userinterface;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import java.io.IOException;

import constants.UIConst;
import userinterface.GameInterface;
import userinterface.sceneControler;
import userinterface.Scores;
import userinterface.HelpScreen;
public class HomeInterface {
	
	Scene mainScene;
	String name;
	boolean newPlayer = true;
	int  prevScore;
	// in case the game just started and no user have log in
	HomeInterface() {
			start();

	}
	
	// in case their is a user already playing
	
	HomeInterface(String name,int prevScore) {
		this.name = name;
		this.prevScore =  prevScore;
		newPlayer = false;
			start();
	
	}
	public void start() {
		
		// Game header
		
		HBox header = new HBox(450);
		header.setAlignment(Pos.CENTER);
		
		// Score button
		ImageView trophy = new ImageView(new Image("img//trophy.png"));
		HBox trophyButton = new HBox(5);
		trophyButton.setAlignment(Pos.CENTER);
		trophyButton.setPadding(new Insets(10));
		trophy.setFitWidth(70);
		trophy.setFitHeight(70);
		trophyButton.getChildren().add(trophy);
		trophyButton.getStyleClass().add("home-buttons");
		
		// open the score window when the Score button clicked
		trophyButton.setOnMouseClicked(e-> {
			Scores scores = new  Scores(); 
			Stage stage = new Stage();
			try {
				scores.start(stage);
			} catch(Exception i ) {
				
			}
			
		});
		
		// help button
		ImageView helpImg = new ImageView(new Image("img//help.png"));
		HBox hlepButton = new HBox(5);
		hlepButton.setAlignment(Pos.CENTER);
		helpImg.setFitWidth(43);
		helpImg.setFitHeight(70);
		hlepButton.setPadding(new Insets(10));
		hlepButton.getChildren().addAll( helpImg );
		
		hlepButton.getStyleClass().add("home-buttons");
		// open the help window when the Score button clicked

		hlepButton.setOnMouseClicked(e-> {
			HelpScreen helpScreen = new  HelpScreen(); 
			Stage stage = new Stage();
			try {
				helpScreen.start(stage);
			} catch(Exception i ) {
				
			}
			
		});
		header.getChildren().addAll(trophyButton,hlepButton);
		
		
		// Set the logo of the APP
		
		ImageView Logo = new ImageView(new Image("img//sudoku.png"));
		Logo.setFitWidth(235);
		Logo.setFitHeight(300);
		
		
		VBox infoLayout = new VBox(10);
		infoLayout.setStyle("-fx-max-width:250;");
		
		// add text field for the user name 
			Text nameLable = new Text("Name");
			Text AddnewPlayer = new Text("New Player");
			AddnewPlayer.setStyle("-fx-fill:#48488e; -fx-cursor: hand;");
			AddnewPlayer.setVisible(false);
			AddnewPlayer.setFont(Font.font("Leelawadee", FontWeight.LIGHT,  FontPosture.REGULAR, 18));
			nameLable.setFont(Font.font("Leelawadee", FontWeight.LIGHT,  FontPosture.REGULAR, 20));
			TextField nameTextField = new TextField();
			nameTextField.getStyleClass().add("name-textField");
			// in case there is already user hide text field and show username
			if(name != null && !newPlayer) {
				nameTextField.setVisible(false);
				AddnewPlayer.setVisible(true);
				nameLable.setText("Hello " + name);
				
			}
			
			// in case the user want to add new player

			AddnewPlayer.setOnMouseClicked(e -> {
				newPlayer=true;
				name = null;
				nameTextField.setVisible(true);
				AddnewPlayer.setVisible(false);
				nameLable.setText("Name");
			});
			
			
		

			// ComboBox for levels

        final ComboBox<String> levelsComboBox = new ComboBox<>();
        levelsComboBox.setPromptText("Level");
        levelsComboBox.getStyleClass().add("level-list");
        levelsComboBox.getItems().addAll(
            "Fast",
            "Easy",
            "Medium",
            "Hard",
            "Expert");
        
        Button Start = new Button("Start");
        Start.getStyleClass().add("buttons");
        
        infoLayout.getChildren().addAll(nameLable,nameTextField,AddnewPlayer,levelsComboBox);
        
        Start.setOnAction(e-> {
        	String pname = nameTextField.getText() ;
        	String level =levelsComboBox.getValue();
        	 Alert a= new Alert(AlertType.NONE);
        	 // check if the user name empty 
        	if(name == null && pname.isEmpty()) {
	        	a.setAlertType(AlertType.ERROR);
				a.setContentText("You should Provide your name please");
				a.show();
				 // check if the level not choosing
        	} else if(levelsComboBox.getSelectionModel().isEmpty()) {
        		a.setAlertType(AlertType.ERROR);
				a.setContentText("Chose Level please");
				a.show();
        	}else {
        		
        		if(!newPlayer) {
        			// if is not new player send it's prev score to GameInterface
        			GameInterface game = new GameInterface(name,level,prevScore);
                	sceneControler.window.setScene(game.gameScene);
        			
        		}else {
        			// if is not new player set prev to 0
        			GameInterface game = new GameInterface(pname,level,0);
                	sceneControler.window.setScene(game.gameScene);
        		}
        		
        	
        	}
        });
		VBox vbox = new VBox(50);
		vbox.setAlignment(Pos.CENTER);
		vbox.setStyle("-fx-background-color: #FFFFFF;");
		
        vbox.getChildren().addAll(header,Logo,infoLayout,Start);
		
		mainScene = new Scene(vbox, UIConst.WINDOW_X,UIConst.WINDOW_Y);
		mainScene.getStylesheets().add(getClass().getResource("stylesheet.css").toString());

		
	}
	
	
}
