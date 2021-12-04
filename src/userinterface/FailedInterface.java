package userinterface;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import  javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight; 
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import userinterface.sceneControler;

import constants.UIConst;

//CSS by Marwah 

public class FailedInterface {
	
	String Scorevalue; 
	String Levelvalue;
	
	String name;
	String level;
	Scene FaliedScene;
	int Score = 0;
	
	FailedInterface(String n,String l){
		name = n;
		level =l;
		start();
	}
	
	
	public void start() {
		VBox screenLayout= new VBox(20);		
		screenLayout.setAlignment(Pos.CENTER);	
		
		screenLayout.setStyle("-fx-background-color:white");
		
		
		ImageView conImage = new ImageView(new Image("img//TryAgain.png"));
		conImage.setFitHeight(400);
		conImage.setFitWidth(500);
		Text excellentText= new Text("Sorry " + this.name);
		excellentText.setFont(Font.font("Leelawadee", FontWeight.BOLD,  FontPosture.REGULAR, 40));
		excellentText.setStyle("-fx-fill:#151515;");

		Text tryAgain = new Text("It's not the correct answer.");
		 tryAgain.setFont(Font.font("Leelawadee", FontWeight.LIGHT,  FontPosture.REGULAR, 22));
		 tryAgain.setStyle("-fx-fill:#898989;");
		
		Button playAgainButton= new Button ("Play Again");
		playAgainButton.setAlignment(Pos.CENTER);
		playAgainButton.getStyleClass().add("buttons");
		
		playAgainButton.setOnAction(e-> {
			HomeInterface home =  new HomeInterface(name,0); 
			sceneControler.window.setScene(home.mainScene);
		});
		
		
		
		screenLayout.getChildren().addAll(conImage, excellentText,tryAgain,playAgainButton);
	
		
			FaliedScene = new Scene(screenLayout,UIConst.WINDOW_X,UIConst.WINDOW_Y);
			FaliedScene.getStylesheets().add(getClass().getResource("stylesheet.css").toString());

		}
			
			
		
			
}
