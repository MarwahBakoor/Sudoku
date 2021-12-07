package userinterface;
import javafx.application.Application;
import javafx.stage.Stage;
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

public class SucssesInterface {
	
	String Scorevalue; 
	String Levelvalue;
	
	String name;
	String level;
	int score;
	Scene SucssesScene;
	
	SucssesInterface(String n,String l,int s){
		name = n;
		level =l;
		score = s;
		start();
	}
	
	public void start() {
		VBox screenLayout= new VBox(20);		
		screenLayout.setAlignment(Pos.CENTER);	
		
		screenLayout.setStyle("-fx-background-color:white");
		
		
		ImageView conImage = new ImageView(new Image("img//good.gif"));
		conImage.setFitHeight(400);
		conImage.setFitWidth(500);
		Text excellentText= new Text("YOU DID IT " + name + " !!");
		excellentText.setFont(Font.font("Leelawadee", FontWeight.BOLD,  FontPosture.REGULAR, 40));
		excellentText.setStyle("-fx-fill:#151515;");
		

		Text scoreText = new Text("Your score is : " + score);
		scoreText.setFont(Font.font("Leelawadee", FontWeight.LIGHT,  FontPosture.REGULAR, 22));
		scoreText.setStyle("-fx-fill:#898989;");
		
		Button playAgainButton= new Button ("Play Again");
		playAgainButton.setAlignment(Pos.CENTER);
		playAgainButton.getStyleClass().add("buttons");
		
		playAgainButton.setOnAction(e-> {
			HomeInterface home =  new HomeInterface(name,score); 
			sceneControler.window.setScene(home.mainScene);
		});
		
		
		
		screenLayout.getChildren().addAll(conImage, excellentText, scoreText,playAgainButton);
	
		
			SucssesScene = new Scene(screenLayout,UIConst.WINDOW_X,UIConst.WINDOW_Y);
			SucssesScene.getStylesheets().add(getClass().getResource("stylesheet.css").toString());

		}
			
			
		
			
}
