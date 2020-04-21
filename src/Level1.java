import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.shape.Line;
import javafx.util.Duration;


//Creat Level1 class for creat pane of level1
public class Level1 extends Levels {
    //Create check variable for check object is clicked before or not.I use this variable for collisions.
    boolean checkObject1 = false;
    boolean checkObject2 = false;
    SequentialTransition s;

    //Creat addobject1 method for creat and add object1 on my pane
    private void addObject1() {
        Hole hole = new Hole(700, 450);
        ThinLine thinLine = new ThinLine(700, 450, 275, 450);
        thinLine.addLine(275, 250);
        thinLine.addLine(300, 250);
        ThickLine thickLine = new ThickLine(300, 250, 650, 250);
        thickLine.addHalfCircle(550, 250, "up");
        Holder holder = createHolder(300, 250, "vertical");
        getChildren().add(thickLine);
        addRectangle(-60, 225, 50, 360);
        getChildren().addAll(hole, thinLine, holder);

        //Create a two path for my Object.I use one of this fot true clicks an the other for false clicks.
        Line truePath = new Line(475, 240, 120, 240);
        Line falsePath = new Line(475, 240, 460, 240);

        //When the click my objecct's circle
        hole.setOnMouseClicked(event -> {
            //Check object2 clicked before or not
            if (checkObject2) {
                //Choose the true path
                s = createTrueAnimation(truePath, 3500, thickLine, hole, holder, thinLine);
                checkObject1 = true;
                //Change the level because of no other object left the pane
                Timeline changeLevel = new Timeline(new KeyFrame(Duration.millis(0.1), e -> changeLevel("")));
                SequentialTransition sq = new SequentialTransition(s, changeLevel);
                sq.play();
                //Reset the my check properties
                resetCheckProperty();
            } else {
                //Choose the false path
                s = createFalseAnimation(falsePath, thickLine, hole, holder);
                s.play();
            }
            //When the click circke one set disable because of no permission for other clicks
            hole.setDisable(true);
        });

    }

    //Creat addobject2 method for creat and add object2 on my pane
    private void addObject2() {

        Hole hole = new Hole(550, 525);
        ThinLine thinLine = new ThinLine(550, 525, 550, 425);
        ThickLine thickLine = new ThickLine(550, 425, 550, 245);
        Holder holder = createHolder(550, 425, "horizontal");
        getChildren().add(thickLine);
        addRectangle(525, 425, 250, 50);
        getChildren().addAll(hole, thinLine, holder);

        //Create a one true path because of no flase path fot this object.
        Line truePath = new Line(550, 335, 550, 520);
        hole.setOnMouseClicked(event -> {
            //Assign the true value and play
            checkObject2 = true;
            s = createTrueAnimation(truePath, 3200, thickLine, hole, holder, thinLine);
            s.play();
            hole.setDisable(true);
        });

    }


    //Override addObjects method
    @Override
    public void addObjects() {
        //add object1 and object2  on my addobjects method
        addObject2();
        addObject1();
        //add the level number my level
        addNumber(1);


    }
    //Arrange my resetCheckProperty method
    @Override
    void resetCheckProperty() {
        checkObject1 = false;
        checkObject2 = false;
    }
}
