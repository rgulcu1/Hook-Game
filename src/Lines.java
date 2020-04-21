import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

//Create a Line class for create a Line objects and combine its one group object
public class Lines extends Group {
    //Define constant values for radius
    public static final int arcRadius = 10;
    public static final int hookRaidus = 20;
    //Create  a variables required for creating line
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    //Create a line counter for count the added lines
    private int lineCounter = 0;


    //Create a constructor
    public Lines(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;


    }

    //Create a createLine method for create a Line with current x,y values and return a Line
    public Line createLine() {
        Line line = new Line(startX, startY, endX, endY);
        //Set lines property
        line.setStrokeWidth(2);
        line.setStroke(Color.valueOf("5A5653"));
        //increase a line counter and return line
        lineCounter++;
        return line;
    }

    //Create removeLastLine method for the remove line which is last added
    public void removeLastLine() {
        //remove last added line and decrease a line counter
        getChildren().remove(lineCounter - 1);
        lineCounter--;
    }

    //Setter and Getter methods for variables
    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public int getLineCounter() {
        return lineCounter;
    }

    public void setLineCounter(int lineCounter) {
        this.lineCounter = lineCounter;
    }
}
