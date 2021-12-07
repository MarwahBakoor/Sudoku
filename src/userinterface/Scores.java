package userinterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.geometry.Pos;
import javafx.animation.Timeline;
import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.animation.KeyFrame;
import javafx.scene.text.Text;
import javafx.util.Duration;

import constants.UIConst;


public class Scores extends Application {

// The JavaFX TableView enables you to display table views inside your JavaFX applications
// the user btween <objectType> is the generic type or parameterized type that will be showing inside the table view 
	
private TableView<user> table = new TableView<user>();
boolean d;

public static void main(String[] args) {
	launch(args);
}

@Override
public void start(Stage stage) throws Exception {

	// The header gif
	ImageView trophy =new ImageView(new Image("img/trophy.gif"));
	trophy.setFitWidth(300);
	trophy.setFitHeight(200);
	Text winner = new Text("The Winner is");
	winner.setFont(Font.font("Leelawadee", FontWeight.LIGHT,  FontPosture.REGULAR, 25));
	// The name of the winner and it's score will be added after get the order list
	Text winnerName =new Text();
	winnerName.setFont(Font.font("Leelawadee", FontWeight.BOLD,  FontPosture.REGULAR, 40));
	Label label = new Label("Players Scores");
	label.setFont(Font.font("Leelawadee", FontWeight.BOLD,  FontPosture.REGULAR, 30));
	
	d= true;
	Timeline TextAnimation = new Timeline(
			new KeyFrame(Duration.seconds(0.3),e-> {
				if(d) {
					winnerName.setFill(Color.rgb(160,196, 207));
				}else {
					winnerName.setFill(Color.rgb(185, 180, 232));
				}
				d=!d;
			}
			));
	 TextAnimation.setAutoReverse(true);
	TextAnimation.setCycleCount(Animation.INDEFINITE);
	TextAnimation.play();
	 

		
	
	// Here we create columns For player name, score and time 
	TableColumn<user, String> Namecol = new TableColumn<user, String>("Name");
	Namecol.setMinWidth(220);
	
	TableColumn<user, String> Scorecol = new TableColumn<user, String>("Score");
	Scorecol.setMinWidth(215);
	
	TableColumn<user, String> Timecol = new TableColumn<user, String>("Time(m)");
	Timecol.setMinWidth(210);
	
	// add columns to the table
	table.getColumns().addAll(Namecol, Scorecol,Timecol);
	//colse the table sortableity
	Scorecol.setSortable(false);
	Timecol.setSortable(false);
	Namecol.setSortable(false);
	
	// here we give every instant varible in class user to it's  column
	Namecol.setCellValueFactory(new PropertyValueFactory<>("Name"));
	Scorecol.setCellValueFactory(new PropertyValueFactory<>("Score"));
	Timecol.setCellValueFactory(new PropertyValueFactory<>("Time"));
	
	// get the sorted list from the methdo get user List
	SortedList<user> winnersList = getUserList();
	// add the winner name at the begin
	winnerName.setText(winnersList.get(0).getName() + " with " + winnersList.get(0).getScore() + " points");
	// add the list to the table
	table.setItems(winnersList);

	
	// Screen layout
	VBox vbox = new VBox();
	vbox.setSpacing(5);
	vbox.setPadding(new Insets(10, 0, 0, 10));
	vbox.setAlignment(Pos.CENTER);
	vbox.setStyle("-fx-background-color:white");
	vbox.getChildren().addAll(trophy,winner,winnerName,label,table);
	
	
	
	
	// add Layout to the scene
	Scene scoreScene = new Scene(vbox,UIConst.WINDOW_X,UIConst.WINDOW_Y);
	// add the stylesheet to the scene
	scoreScene.getStylesheets().add(getClass().getResource("tableStyle.css").toString());
	stage.setTitle("Players Scores");
	stage.getIcons().add(new Image("img//sudokuLogo.png"));
	stage.setScene(scoreScene);
	stage.show();

	
	
	


}

private  SortedList<user> getUserList() throws NumberFormatException, IOException {

	///////takes name and scores from txt file created in class user
	BufferedReader br = null;
	try {
		File file = new File("C:\\Users\\moora\\eclipse-workspace\\Sudoku-Puzzle\\src\\Score.txt");
		br = new BufferedReader(new FileReader(file));
	} catch (Exception e) {
		System.out.println(e);
	}
	//creat an array with 100 user
	user userArray[] = new user[100];
	// if br have a cotnet
	if (br != null) {
		String st;
		int i = 0;
		// while ther is an line
		while ((st = br.readLine()) != null) {
			// we spreate this lines by space
			Scanner scan = new Scanner(st).useDelimiter(" ");
			while (scan.hasNext()) {
				// first create an instanst user and add to the array
				userArray[i] = new user();
				// add the name score and time to the user
				userArray[i].setName(scan.next());
				userArray[i].setScore(scan.nextInt());
				userArray[i].setTime(scan.nextDouble());
				i++; 
			}
		}
	}
	// create an sorted list 
    SortedList<user> sortedList = new SortedList<>(FXCollections.observableArrayList(userArray), (user user1, user user2) -> {
    	//in order to sort the list 
    	// this case doesn't matter it just to not have an error in case the object is empty
    	if(user1 == null || user2 == null) {
    		 return -1;
    		 // if the score of the user1 is less than the secound the switch them 
    	}else if( user1.getScore() <  user2.getScore()  ) {
          return 1;
      } else {
          return 0;
          }
  });
    return sortedList;
    }

}