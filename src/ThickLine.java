import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Line;

///Creat ThickLine class and extend Lines for creat thick lines and combine  them on one object
public class ThickLine extends Lines {
    //create variables for the half circle x,y values
    private int CircleX;
    private int CircleY;

    //Create a constructor
    public ThickLine(int startX, int startY, int endX, int endY) {
        //set x and y values with call super
        super(startX, startY, endX, endY);
        //After add the holder now add the thick line on my group
        getChildren().add(createLine());

    }

    //Create addHalfCircle method for create and add the half circle on my group
    public void addHalfCircle(int CircleX, int CircleY, String position) {
        //hold the end and start values an set the circle x,y values
        int endX = getEndX();
        int endY = getEndY();
        int startX = getStartX();
        int startY = getStartY();
        this.CircleX = CircleX;
        this.CircleY = CircleY;

        //remove last line because ı add shorter line and add half circle after add one more line so half circle between two lines
        removeLastLine();
        //Check the position and coordinates of halfCircle and call the create the proper half circle and add my group
        if (position.equalsIgnoreCase("right")) {
            if (endY > startY) createHalfCircle(0, hookRaidus, 270);
            else createHalfCircle(0, -hookRaidus, 270);

        } else if (position.equalsIgnoreCase("left")) {
            if (endY > startY) createHalfCircle(0, hookRaidus, 90);
            else createHalfCircle(0, -hookRaidus, 90);

        } else if (position.equalsIgnoreCase("down")) {
            if (endX > startX) createHalfCircle(1, hookRaidus, 180);
            else createHalfCircle(1, -hookRaidus, 180);

        } else {
            if (endX > startX) createHalfCircle(1, hookRaidus, 0);
            else createHalfCircle(1, -hookRaidus, 0);

        }
        //After the add half circle set the new endx points.
        setEndX(endX);
        setEndY(endY);
        //Already start values set on createHalfCricle method and end values set above.Now we can create and add the line
        getChildren().add(createLine());


    }

    //override createLine method for change the width value
    @Override
    public Line createLine() {
        Line line = super.createLine();
        line.setStrokeWidth(5);
        return line;

    }

    //create createHalfCircle method for firstly add line after add a halfcircle different directions depends on choice
    public void createHalfCircle(int choice, int k, int startAngle) {
        Arc arc;
        if (choice == 0) {
            //set the end coordinates and create arc and create line and add group
            setEndY(CircleY - k);
            arc = new Arc(CircleX, CircleY, hookRaidus, hookRaidus, startAngle, 180);
            getChildren().addAll(createLine(), arc);
            //set the start coordinates
            setStartY(CircleY + k);
        } else {
            setEndX(CircleX - k);
            arc = new Arc(CircleX, CircleY, hookRaidus, hookRaidus, startAngle, 180);
            getChildren().addAll(createLine(), arc);
            setStartX(CircleX + k);
        }
        //set the arc properties and increase the linecounter
        setLineCounter(getLineCounter() + 1);
        arc.setStrokeWidth(6);
        arc.setFill(Color.TRANSPARENT);
        arc.setStroke(Color.valueOf("5A5653"));
        arc.setType(ArcType.OPEN);


    }


}
