package userinterface;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

import java.io.IOException;

import constants.UIConst;
import userinterface.GameInterface;
import userinterface.sceneControler;
import userinterface.Scores;



public class HomeInterface {
	
	Scene mainScene;
	
	HomeInterface() {
		try {
			start();
		} catch( IOException i) {
			System.out.print(i);
		}
	}
	public void start() throws IOException {
		
		HBox header = new HBox(500);
		header.setAlignment(Pos.CENTER);
		ImageView trophy = new ImageView(new Image("img//trophy.png"));
		trophy.setFitWidth(50);
		trophy.setFitHeight(50);
		
		trophy.setOnMouseClicked(e-> {
			Scores scores = new  Scores(); 
			Stage stage = new Stage();
			try {
				scores.start(stage);
			} catch(Exception i ) {
				
			}
			
		});
		
		ImageView help = new ImageView(new Image("img//help.png"));
		help.setFitWidth(60);
		help.setFitHeight(60);
		
		header.getChildren().addAll(trophy,help);
		
		
		ImageView Logo = new ImageView(new Image("img//sudoku.png"));
		Logo.setFitWidth(250);
		Logo.setFitHeight(300);
		
		VBox infoLayout = new VBox(10);
		infoLayout.setStyle("-fx-max-width:250;");
		Label nameLable = new Label("Name");
		//Font font = Font.loadFont( Main.class.getClassLoader().getResourceAsStream( "Tajawal-Bold.ttf"), 10);
		//nameLable.setFont(Font.font("Tajawal-Bold.ttf"));
		nameLable.getStyleClass().add("name-lable");
		TextField nameTextField = new TextField();
		nameTextField.getStyleClass().add("name-textField");

	
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
        Start.getStyleClass().add("start-button");
        
        infoLayout.getChildren().addAll(nameLable,nameTextField,levelsComboBox);
        
        Start.setOnAction(e-> {
        	String name = nameTextField.getText();
        	String level =levelsComboBox.getValue();
        	 Alert a= new Alert(AlertType.NONE);
        	if(name.isEmpty()) {
	        	a.setAlertType(AlertType.ERROR);
				a.setContentText("You should Provide your name please");
				a.show();
        	} else if(levelsComboBox.getSelectionModel().isEmpty()) {
        		a.setAlertType(AlertType.ERROR);
				a.setContentText("Chose Level please");
				a.show();
        	}else {
        		
        	GameInterface game = new GameInterface(nameTextField.getText(), levelsComboBox.getValue());
        	sceneControler.window.setScene(game.gameScene);
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
