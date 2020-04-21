import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

//Creat ThinLine class and extend Lines for creat thin lines and combine  them on one object
public class ThinLine extends Lines {

    //Create a constructor
    public ThinLine(int startX, int startY, int endX, int endY) {
        //set x and y values with call super
        super(startX, startY, endX, endY);
        //after set the x and y values create a line with call call createline method and add it on my Group
        getChildren().add(createLine());

    }

    //Create addLine method for add a corner and add a line which extending to given x ,y values
    public void addLine(int newEndX, int newEndY) {
        //first remove last added line becasuse ı will add shorter line because of corner
        removeLastLine();

        //Check the coordinates of joint and check which direction of new line.After this check call the addCorner method
        // with proper choice and give proper inputs and add the required corner.
        if (newEndX > getStartX() && newEndY > getStartY()) {
            if (getEndX() > getStartX())
                addCorner(0, arcRadius, 0, 90);
            else
                addCorner(3, arcRadius, 180, 270);
        } else if (newEndX > getStartX() && newEndY < getStartY()) {
            if (getEndX() > getStartX())
                addCorner(1, arcRadius, 270, 360);
            else
                addCorner(2, -arcRadius, 90, 180);
        } else if (newEndX < getStartX() && newEndY > getStartY()) {
            if (getEndX() < getStartX())
                addCorner(1, -arcRadius, 90, 180);
            else
                addCorner(2, arcRadius, 270, 360);
        } else if (newEndX < getStartX() && newEndY < getStartY()) {
            if (getEndX() < getStartX())
                addCorner(0, -arcRadius, 180, 270);
            else
                addCorner(3, -arcRadius, 0, 90);
        }

        //After the add line set the new endx points.
        setEndX(newEndX);
        setEndY(newEndY);
        //Already startx and starty values set on addCorner method and end values set above.Now we can create and add the line
        getChildren().add(createLine());

    }

    //Create addCorner method for firstly add a line shorter than removed line and add a different direction corners depends on given inputs
    private void addCorner(int choice, int k, int start, int end) {
        //set the x and y coordinates depends on choice
        if (choice == 0) {
            //set the endx thw new line will be shorter because of corner
            setEndX(getEndX() - k);
            //create a new line and add the group
            getChildren().add(createLine());
            //Create a corner with call createCorner method and add the group
            getChildren().add(createCorner(getEndX(), getEndY() + k, start, end));
            //set the new start values end of the corner velues
            setStartX(getEndX() + k);
            setStartY(getEndY() + k);

            //Same operations for different coordinates and directions
        } else if (choice == 1) {
            setEndX(getEndX() - k);
            getChildren().add(createLine());
            getChildren().add(createCorner(getEndX(), getEndY() - k, start, end));
            setStartX(getEndX() + k);
            setStartY(getEndY() - k);
        } else if (choice == 2) {
            setEndY(getEndY() - k);
            getChildren().add(createLine());
            getChildren().add(createCorner(getEndX() - k, getEndY(), start, end));
            setStartX(getEndX() - k);
            setStartY(getEndY() + k);
        } else if (choice == 3) {
            setEndY(getEndY() - k);
            getChildren().add(createLine());
            getChildren().add(createCorner(getEndX() + k, getEndY(), start, end));
            setStartX(getEndX() + k);
            setStartY(getEndY() + k);
        }

    }


    //Create a createCorner Method for create corner with given values
    public Arc createCorner(int centerX, int centerY, int startAngle, int endAngle) {
        //Create arc with given inputs
        Arc arc = new Arc(centerX, centerY, arcRadius, arcRadius, startAngle, endAngle - startAngle);
        //Set the property of arcs
        arc.setStrokeWidth(2);
        arc.setFill(Color.valueOf("F0F0E8"));
        arc.setStroke(Color.valueOf("5A5653"));
        arc.setType(ArcType.OPEN);
        //Increase the lne counter
        setLineCounter(getLineCounter() + 1);

        //return the arc
        return arc;
    }


}