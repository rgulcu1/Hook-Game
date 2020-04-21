import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

//Create Circles class for create circles and combine with line etc
public class Circles extends Group {
    //Define a constant value for radius
    public static final int radius = 20;
    //Create variables required for creating circle
    private int centerX;
    private int centerY;

    //Create a constructor
    public Circles(int centerX, int centerY) {
        this.centerX = centerX;
        this.centerY = centerY;
    }

    //Create a createCircle method for create a circle with current x,y values and return a circle
    public Circle createCircle() {
        Circle circle = new Circle(centerX, centerY, radius);
        //set the properties of circles
        circle.setFill(Color.valueOf("5A5653"));
        circle.setStroke(Color.valueOf("5A5653"));
        //return the circle
        return circle;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }
}
