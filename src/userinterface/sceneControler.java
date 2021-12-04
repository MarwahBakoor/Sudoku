package userinterface;
import javafx.application.Application;

import javafx.stage.Stage;
import javafx.scene.image.Image;
import userinterface.HomeInterface;

public class sceneControler extends Application{
	public static Stage window;
	public void start(Stage primaryStage) throws Exception  {
		
		window = primaryStage;
		HomeInterface home =  new HomeInterface(); 
		primaryStage.setScene( home.mainScene);
		primaryStage.setTitle("Sudoku");
		primaryStage.getIcons().add(new Image("img//sudokuLogo.png"));
		primaryStage.show();
		
		
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
