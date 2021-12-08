package userinterface;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
	boolean d;
	
	SucssesInterface(String n,String l,int s){
		name = n;
		level =l;
		score = s;
		start();
	}
	
	public void start() {
		//screen layout 
		VBox screenLayout= new VBox(20);		
		screenLayout.setAlignment(Pos.CENTER);	
		screenLayout.setStyle("-fx-background-color:white");
		
		
		ImageView conImage = new ImageView(new Image("img//good.gif"));
		conImage.setFitHeight(400);
		conImage.setFitWidth(500);
		Text excellentText= new Text("YOU DID IT " + name + " !!");
		excellentText.setFont(Font.font("Leelawadee", FontWeight.BOLD,  FontPosture.REGULAR, 40));
		excellentText.setStyle("-fx-fill:#151515;");
		
		d= true;
		Timeline TextAnimation = new Timeline(
				new KeyFrame(Duration.seconds(0.3),e-> {
					if(d) {
						excellentText.setFill(Color.rgb(160,196, 207));
					}else {
						excellentText.setFill(Color.rgb(185, 180, 232));
					}
					d=!d;
				}
				));
		
		TextAnimation.setAutoReverse(true);
		TextAnimation.setCycleCount(Animation.INDEFINITE);
		TextAnimation.play();
		
		

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
