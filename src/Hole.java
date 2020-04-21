//Create Hole class and extends Circles for creating a Hole represent Button in game
public class Hole extends Circles {

    //Create a constructor for creating and add a default hole
    public Hole(int centerX, int centerY) {
        super(centerX, centerY);
        getChildren().add(createCircle());
    }
}
