package userinterface;

import java.io.File;

import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import constants.UIConst;

public class Scores {
public void start(Stage secoundryStage) throws Exception {
// TODO Auto-generated method stub
	Text t = new Text();
	t.setText("Players Scores");
	t.setFont(Font.font("Times New Roman", 50));
	t.setFill(Color.BLACK);
	t.setX(100);
	t.setY(100);
	
	TextArea text = new TextArea();
	text.setEditable(false);
	text.setPrefSize(100.0, 100.0);

	try {
		File file = new File("C:\\Users\\moora\\eclipse-workspace\\Sudoku-Puzzle\\src\\Score.txt");
		Scanner scan = new Scanner(file);

		while (scan.hasNext()) {
			String s = scan.nextLine();
			text.appendText(s + "\n");
		}
		} catch (Exception e) {
			System.out.println("error");
		}

	VBox layout = new VBox();
	layout.getChildren().addAll(t, text);
	layout.setAlignment(Pos.CENTER);
	
	Scene scene = new Scene(layout, UIConst.WINDOW_X, UIConst.WINDOW_Y);
	secoundryStage.setScene(scene);
	secoundryStage.setTitle("Sudocu");
	secoundryStage.getIcons().add(new Image("img//sudokuLogo.png"));
	secoundryStage.show();
	}


}
