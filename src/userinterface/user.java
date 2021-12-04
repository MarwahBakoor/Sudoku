package userinterface;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class user {

	String name;
	int score;
	double time;

	public user() {
	
	}

	public user(String name, int scorem, double Time) throws IOException {
	this.name = name;
	this.score = score;
	this.time = Time;
	//store name and score in txt file
	File file = new File("C:\\Users\\moora\\eclipse-workspace\\Sudoku-Puzzle\\src\\Score.txt");
	FileWriter fw = new FileWriter(file, true);
	BufferedWriter print = new BufferedWriter(fw);
	print.write("\n"+name+" "+score);
	print.close();
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public int getScore() {
	return score;
}

public void setScore(int score) {
	this.score = score;
}


public double getTime() {
	return time;
}

public void setTime(double time) {
	this.time = time;
}

}