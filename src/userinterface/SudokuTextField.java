package userinterface;
import javafx.scene.control.TextField;

public class SudokuTextField extends TextField {
    private final int width;
    private final int height;
    private int answer;
    
    public SudokuTextField(int x, int y) {
        this.width = x;
        this.height = y;
        setMinWidth(width);
		setMaxWidth(width);
		setMinHeight(height);
		setMaxHeight(height);
		getStyleClass().add("sudocu-field");

    }
    
    public void styleBorder(int i , int j) {
    	if(i==2 || i==5) {
    		
    		getStyleClass().add("sudocu-field-right");
    		
		}
    	if(j==2 || j==5) {
			getStyleClass().add("sudocu-field-bottom");
		}
		
		if((i==2 && (j==2 || j==5)) || (i==5 && (j==2 || j==5))) {
			getStyleClass().add("sudocu-field-bottom-right");
			
		}
    }
    public int getX() {
        return width;
    }

    public int getY() {
        return height;
    }
    
    public void setValue(int i) {
    	if(i != 0) {
    		setText(String.format("%d",i));
    		setEditable(false);
    	} else {
    		getStyleClass().add("empty-textField");
    	}
    }

    public void setAnswer(int i) {
    	answer = i;
    }
    
    public int getAnswer() {
    	return answer;
    }

    /*
    For some reason, when I override these two functions, the TextFields stop duplicating numeric inputs...
     */
    
    @Override
    public void replaceText(int start, int end, String text)
    {
        if (validate(text))
        {
            super.replaceText(0, end, text);
        }
    }

    @Override
    public void replaceSelection(String text)
    {
        if (validate(text))
        {
            super.replaceSelection(text);
            
        }
    }

    private boolean validate(String text){
        return text.matches("[0-9]*");
    }
    


}
