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
	
	public void start () {
		//Pane pane = new Pane();
		VBox screenLayout= new VBox(20);
		
		screenLayout.setAlignment(Pos.CENTER);	
		//First Text
		Text congrats= new Text("Congratulations !!");
		congrats.setFont(Font.font("Raleway", FontWeight.BOLD,  FontPosture.REGULAR, 50));
		//congrats.setFont(Font.font("Times New Roman", FontWeight.BOLD,  FontPosture.ITALIC, 20));
		
		//Second Text 
		HBox scorelayout = new HBox(20);
		scorelayout.setAlignment(Pos.CENTER);	
		Text theScore = new Text("your score is : ");
		//TextField score= new TextField ();
		theScore.setFont(Font.font("Raleway", FontWeight.LIGHT,  FontPosture.REGULAR, 40));
		Text scoreView = new Text(""+score);
		scorelayout.getChildren().addAll(theScore,scoreView);
		//Third Text 
		// The event is to show the level of the player
		
		
//		Text arrangement = new Text(40,40,"Your arrangment is : ");
//		Text r = new Text (valueOfScore(0)); 
		
		
		//arrangement.setFont(Font.font("Times New Roman", FontWeight.BOLD,  FontPosture.ITALIC, 20));
		//TextField r= new TextField ();
		
		Button playAgain= new Button ("play again");
		playAgain.setAlignment(Pos.CENTER);
		
		//event: trans to another page
		
		//playAgain.setOnAction(e -> {}); 
	
		
		
		
		screenLayout.getChildren().addAll(congrats, scorelayout,playAgain);
	
		
			SucssesScene = new Scene(screenLayout,UIConst.WINDOW_X,UIConst.WINDOW_Y);
			SucssesScene.getStylesheets().add(getClass().getResource("stylesheet.css").toString());
		}
			
	
			private String valueOfScore(int s) { //Object Score 
				Scorevalue= String.valueOf(s);
		
		return Scorevalue;
	}
			
		
			
}
