import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

//Create Switch class and extends Circles for creating switch
public class Switch extends Circles {

    //Create a constructor
    public Switch(int centerX, int centerY, String position) {
        super(centerX, centerY);
        Line line;
        //Check the position and create a horizontal or vertical initial position switch
        if (position.equalsIgnoreCase("horizontal"))
            line = new Line(centerX - radius, centerY, centerX + radius, centerY);
        else line = new Line(centerX, centerY - radius, centerX, centerY + radius);

        //set the line properties and add on my group
        line.setStrokeWidth(2);
        line.setStroke(Color.valueOf("5A5653"));
        getChildren().addAll(createCircle(), line);
    }

    //Override createCircle method for change set fill property
    @Override
    public Circle createCircle() {
        Circle circle = super.createCircle();
        circle.setFill(Color.valueOf("F0F0E8"));
        circle.setStrokeWidth(3.5);

        return circle;
    }
}

