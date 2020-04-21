import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
//Create Holder class and extends Lines for creating a Holder in game
public class Holder extends Lines {

    //Create a constructor for creating holder with the given type
    public Holder(int startX, int startY, int type) {
        super(startX - 10, startY, startX + 10, startY);

        if (type == 1) {
            setStartX(startX);
            setStartY(startY - 10);
            setEndX(startX);

            setEndY(startY + 10);
        }

        getChildren().add(createLine());
        setLineCounter(getLineCounter() + 1);

    }
    //Override CreateLine method
    @Override
    public Line createLine() {
        Line line = super.createLine();
        line.setStrokeWidth(6);
        line.setStroke(Color.valueOf("5A5653"));
        line.setStrokeLineCap(StrokeLineCap.ROUND);

        return line;
    }

}
