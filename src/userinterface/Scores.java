package userinterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.geometry.Pos;
import javafx.scene.text.Text;

import constants.UIConst;


public class Scores extends Application {

private TableView<user> table = new TableView<user>();

public static void main(String[] args) {
	launch(args);
}

@Override
public void start(Stage stage) throws Exception {

	
	ImageView trophy =new ImageView(new Image("img\\HighestScores.png"));
	
	trophy.setFitWidth(250);
	trophy.setFitHeight(250);
	Text winner = new Text("The Winner is");
	winner.setFont(Font.font("Leelawadee", FontWeight.BOLD,  FontPosture.REGULAR, 30));
	Label label = new Label("Players Scores");
	winner.setStyle("-fx-font-size:40;-fx-font-weight:bold");
	label.setFont(new Font("Arial", 25));
	
	table.setEditable(true);
	
	
	
	TableColumn<user, String> Namecol = new TableColumn<user, String>("Name");
	Namecol.setMinWidth(220);
	TableColumn<user, String> Scorecol = new TableColumn<user, String>("Score");
	Scorecol.setMinWidth(215);
	TableColumn<user, String> Timecol = new TableColumn<user, String>("Time(m)");
	Timecol.setMinWidth(210);
	table.getColumns().addAll(Namecol, Scorecol,Timecol);
	Scorecol.setSortable(false);
	Timecol.setSortable(false);
	Namecol.setSortable(false);


	final VBox vbox = new VBox();
	vbox.setSpacing(5);
	vbox.setPadding(new Insets(10, 0, 0, 10));
	vbox.setAlignment(Pos.CENTER);
	vbox.setStyle("-fx-background-color:white");
	vbox.getChildren().addAll(trophy,winner,label,table);
	
	
	Scene scoreScene = new Scene(vbox,UIConst.WINDOW_X,UIConst.WINDOW_Y);
	scoreScene.getStylesheets().add(getClass().getResource("tableStyle.css").toString());
	stage.setTitle("Players Scores");
	stage.getIcons().add(new Image("img//sudokuLogo.png"));
	stage.setScene(scoreScene);
	stage.show();
	
	Namecol.setCellValueFactory(new PropertyValueFactory<>("Name"));
	Scorecol.setCellValueFactory(new PropertyValueFactory<>("Score"));
	Timecol.setCellValueFactory(new PropertyValueFactory<>("Time"));
	
	
	
	SortedList<user> winnersList = getUserList();
	
	winner.setText(winner.getText()+ " " + winnersList.get(0).getName() );
	table.setItems(winnersList);

}

private  SortedList<user> getUserList() throws NumberFormatException, IOException {

	///////takes name and scores from txt file created in class user
	BufferedReader br = null;
	try {
		File file = new File("C:\\Users\\moora\\eclipse-workspace\\Sudoku-Puzzle\\src\\Score.txt");
		br = new BufferedReader(new FileReader(file));
	} catch (Exception e) {
		System.out.println("This error because of me");
	}
	user userArray[] = new user[100];
	if (br != null) {
		String st;
		int i = 0;
		while ((st = br.readLine()) != null) {
			Scanner scan = new Scanner(st).useDelimiter(" ");
			while (scan.hasNext()) {
				userArray[i] = new user();
				userArray[i].setName(scan.next());
				userArray[i].setScore(scan.nextInt());
				userArray[i].setTime(scan.nextDouble());
				i++; }
		}
	}
	
	

	ObservableList<user> list = FXCollections.observableArrayList(userArray);
    SortedList<user> sortedList = new SortedList<>(list, (user user1, user user2) -> {
    	if(user1 == null || user2 == null) {
    		 return -1;
    	}else if( user1.getScore() <  user2.getScore()  ) {
          return 1;
      } else if(  user1.getScore()  >  user2.getScore()  ) {
          return -1;
      } else {
          return 0;
      }
  });
    return sortedList;
}

}