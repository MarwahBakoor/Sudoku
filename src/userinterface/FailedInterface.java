package userinterface;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.HBox;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
	boolean d;
	
	FailedInterface(String n,String l){
		name = n;
		level =l;
		start();
	}
	
	
	public void start() {
		VBox screenLayout= new VBox(20);		
		screenLayout.setAlignment(Pos.CENTER);	
		
		screenLayout.setStyle("-fx-background-color:white");
		
		
		ImageView conImage = new ImageView(new Image("img//sad.gif"));
		conImage.setFitHeight(400);
		conImage.setFitWidth(500);
		Text hardLuckText= new Text("Sorry " + this.name);
		hardLuckText.setFont(Font.font("Leelawadee", FontWeight.BOLD,  FontPosture.REGULAR, 40));
		hardLuckText.setStyle("-fx-fill:#151515;");

		d= true;
		Timeline TextAnimation = new Timeline(
				new KeyFrame(Duration.seconds(0.3),e-> {
					if(d) {
						hardLuckText.setFill(Color.rgb(160,196, 207));
					}else {
						hardLuckText.setFill(Color.rgb(185, 180, 232));
					}
					d=!d;
				}
				));
		TextAnimation.setAutoReverse(true);
		TextAnimation.setCycleCount(Animation.INDEFINITE);
		TextAnimation.play();
		
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
		
		
		
		screenLayout.getChildren().addAll(conImage, hardLuckText,tryAgain,playAgainButton);
	
		
			FaliedScene = new Scene(screenLayout,UIConst.WINDOW_X,UIConst.WINDOW_Y);
			FaliedScene.getStylesheets().add(getClass().getResource("stylesheet.css").toString());

		}
			
			
		
			
}
