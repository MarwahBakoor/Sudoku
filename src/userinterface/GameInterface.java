package userinterface;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.geometry.Pos;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.geometry.Insets;

import Logic.PuzzleGenerator;
import constants.UIConst;
import userinterface.sceneControler;
import userinterface.SucssesInterface;
import userinterface.FailedInterface;
import userinterface.HomeInterface;

public class GameInterface {
    // Generate Sudoku puzzle 
	int[][] mat;
	int[][] ans;
	long sec = 0;
	boolean isGameStop;
	public Scene gameScene;
	String level;
	String palyerName;
	Duration gameTime;
	Timeline animation;
	static int HighestScore;
	
	// constractors Take palyer name , level of the game, and in case the player have plaied before
	
	public GameInterface(String Name, String level,int s) { 
		this.palyerName = Name;
		this.level = level;
		if(s > HighestScore) {
			HighestScore= s;
		}
		start();
	}
	public void start(){
		PuzzleGenerator puzzle = new PuzzleGenerator(getLevel(level));
		
	    mat =  puzzle.getPuzle();
		ans =  puzzle.getAnswer();
		SudokuTextField[][] sudokuFields = new SudokuTextField [9][9];
		GridPane sudukoGrid = new GridPane();		
		sudukoGrid.setStyle("-fx-border-width: 2; -fx-border-color: #3f3f3f;");
		
		for(int i=0; i<9 ; i++) {
			for (int j=0; j<9 ; j++) {
				SudokuTextField backtext = new SudokuTextField(74,74);
				 backtext.setEditable(false);
				 backtext.styleBorder(i, j);
				sudukoGrid.add(backtext,i,j);
				
				
			}}
		
		for(int i=0; i<9 ; i++) {
			for (int j=0; j<9 ; j++) {
				sudokuFields[i][j] = new SudokuTextField(74,74);
				sudokuFields[i][j].setValue(mat[i][j]);
				sudokuFields[i][j].setAnswer(ans[i][j]);
				sudokuFields[i][j].styleBorder(i, j);
				sudukoGrid.add(sudokuFields[i][j],i,j);
			}
		}
		
		
		
		//Timer
		ImageView pauseImage= new ImageView( new Image("img\\pause.png"));
		pauseImage.setFitWidth(25);
		pauseImage.setFitHeight(25);		
		Text timerText = new Text();
		
		animation = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
			sec++;
        	int minute = (int)Math.floor(sec/60);
        	int secounds = (int)(sec - minute*60 );
        	timerText.setText(String.format("%d : %02d", minute, secounds));
			
		}));
		isGameStop = false;
		
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();
		
		
		pauseImage.setOnMouseClicked(e ->{ 
			
			if(isGameStop) {
				animation.play();
				pauseImage.setImage(new Image("img\\pause.png"));
				for(int i=0; i<9 ; i++) {
					for (int j=0; j<9 ; j++) {
						sudokuFields[i][j].setVisible(true);
					}}
				isGameStop= false;
			}else {
				animation.pause();
				pauseImage.setImage(new Image("img//start.png"));
				for(int i=0; i<9 ; i++) {
					for (int j=0; j<9 ; j++) {
						sudokuFields[i][j].setVisible(false);
					}}
				isGameStop = true;
				
			}
			
		});
		
		
		
		
		Text playerName = new Text("Player Name: " + palyerName);
		Text levelName = new Text("Level: " + level);
		Text HighestPlayerScore= new Text("Highest Score: " + HighestScore);
		 
		// Timer layout
		pauseImage.getStyleClass().add("pause-Button");
		HBox HLayout = new HBox(10);
		HLayout.alignmentProperty().set(Pos.CENTER_RIGHT);
		HLayout.getChildren().addAll(timerText, pauseImage);
		HBox header = new HBox(40);
		header.getChildren().addAll(playerName,levelName,HighestPlayerScore,HLayout);
		header.setPadding(new Insets(10,0,0,10));
		header.alignmentProperty().set(Pos.CENTER_LEFT);
		
		
		// undo button
		
		VBox undoButton = new VBox();
		ImageView undoImag = new ImageView(new Image("img//undo.png"));
		undoImag.setFitWidth(50);
		undoImag.setFitHeight(50);
		Text undoText = new Text("Undo");
		undoButton.alignmentProperty().set(Pos.CENTER);
		undoButton.getChildren().addAll(undoImag,undoText);
		undoButton.getStyleClass().add("undo-button");
		
		
		undoButton.setOnMouseClicked(e-> {
			for(int i=0; i<9 ; i++) {
				for (int j=0; j<9 ; j++) {
					if(mat[i][j] == 0) {
						sudokuFields[i][j].setText("");
					}
				}}
		});
		
		// Check Button 
		

		
		VBox checkButton = new VBox();
		
		ImageView checkImag = new ImageView(new Image("img//finish.png"));
		checkImag.setFitWidth(50);
		checkImag.setFitHeight(50);
		Text checkText = new Text("Finish");
		checkButton.alignmentProperty().set(Pos.CENTER);
		checkButton.getChildren().addAll(checkImag,checkText);
		checkButton.getStyleClass().add("undo-button");
		
		checkButton.setOnMouseClicked(e-> 
		{
		try {
			checkAnswer(sudokuFields);
		} catch( IOException i) {
			
		}});
		
		// Home Button 
		
		VBox homeButton = new VBox();
		ImageView homeImag = new ImageView(new Image("img//home.png"));
		homeImag.setFitWidth(50);
		homeImag.setFitHeight(50);
		Text homeText = new Text("Home");
		homeButton.alignmentProperty().set(Pos.CENTER);
		homeButton.getChildren().addAll(homeImag,homeText);
		homeButton.getStyleClass().add("undo-button");
		homeButton.setOnMouseClicked(e->{
			HomeInterface backHome = new HomeInterface(palyerName,0);
			sceneControler.window.setScene(backHome.mainScene);
		});		
		// Undo and finish buttons layout
		
		HBox buttomLayout =  new HBox(150);
		buttomLayout.getChildren().addAll(undoButton,homeButton,checkButton);
		buttomLayout.alignmentProperty().set(Pos.CENTER);
		
		// THE GAME LAYOUT
		
		VBox vbox = new VBox(10);
		vbox.getChildren().addAll(header,sudukoGrid, buttomLayout);
		
		
		gameScene = new Scene(vbox, UIConst.WINDOW_X,UIConst.WINDOW_Y);
		gameScene .getStylesheets().add(getClass().getResource("stylesheet.css").toString());
		
	}
	public int getLevel(String level) {
		switch(level) {
		case "Fast":
			return 1;
		case "Easy":
			return 20;
		case "Medium":
			return 30;
		case  "Hard":
			return 40;
		case  "Expert":
			return 50;
		default : 
			return 0;
		}
	}
	
	// check the answer method
	public void checkAnswer(SudokuTextField[][] sudokuFields) throws IOException {
		 Alert a= new Alert(AlertType.NONE);
		int[][] userAnswer = new int[9][9];
		for(int i=0; i<9 ; i++) {
			for (int j=0; j<9 ; j++) {
				if(sudokuFields[i][j].getText().isEmpty()) {
					a.setAlertType(AlertType.ERROR);
					a.setContentText("You should complete the sudoko field before finish the game");
					a.show();
					return;
				}
				else {
					userAnswer[i][j] = Integer.parseInt(sudokuFields[i][j].getText());
				}
				}}
		animation.pause();
		if(compareing(userAnswer,ans)) {
			int levelWeight = getLevel(level);
			SucssesInterface sucWindow = new SucssesInterface(palyerName,level,Score(sec,levelWeight));
			sceneControler.window.setScene(sucWindow.SucssesScene);
//			a.setAlertType(AlertType.CONFIRMATION);
			addNameAndScore(palyerName,levelWeight);
//			a.setContentText("Good job you have solve the sudoku");
//			a.show();
		}else {
			FailedInterface FailedWindow = new FailedInterface(palyerName,level);
			sceneControler.window.setScene(FailedWindow.FaliedScene);

		}
	}
	
	
	//Compareing Answers method
	
	static boolean compareing(int[][] matrixInput, int[][] matrixSudoku) {
		 boolean result=true;
		for (int i=0;i<matrixInput.length;++i) {
			for (int j=0;j<matrixInput[i].length;j++) {
				if (matrixInput[i][j]!=matrixSudoku[i][j]) {
					result=false;
				break;
				}
			}
			if (result==false)
				break;
			}
			return result;
	}
	
	
	public int Score(long time, int level) {
		double min = (double)sec/120;
		return (int)Math.ceil(level/min);
	}
	
	
	public void addNameAndScore(String N, int levelpoint) throws IOException {
		String name = N;
		int score = Score(sec, levelpoint);
		//store name and score in txt file
		File file = new File("C:\\Users\\moora\\eclipse-workspace\\Sudoku-Puzzle\\src\\Score.txt");
		//System.out.println(file.exists());
		FileWriter fw = new FileWriter(file, true);
		BufferedWriter print = new BufferedWriter(fw);
		print.write("\n" + name + " " + score + " " + sec/60.0);
		print.close();
	}
	
}
