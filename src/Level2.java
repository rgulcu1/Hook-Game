import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.shape.Line;
import javafx.util.Duration;

//Creat Level2 class for creat pane of level2
public class Level2 extends Levels {
    //Create check variable for check object is clicked before or not.I use this variable for collisions.
    boolean checkObject1 = false;
    boolean checkObject2 = false;
    boolean checkObject3 = false;
    boolean checkObject4 = false;
    SequentialTransition s;

    //Creat addobject1 method for creat and add object1 on my pane
    private void addObject1() {
        Hole hole = new Hole(275, 525);
        ThinLine thinLine = new ThinLine(275, 525, 275, 200);
        thinLine.addLine(300, 200);
        ThickLine thickLine = new ThickLine(300, 200, 395, 200);
        Holder holder = createHolder(300, 200, "vertical");
        getChildren().add(thickLine);
        addRectangle(150, 175, 50, 150);
        getChildren().addAll(hole, thinLine, holder);

        //Create a one true path because of no flase path fot this object.
        Line truePath = new Line(347, 200, 250, 200);
        hole.setOnMouseClicked(event -> {
            //Assign the true value and play
            checkObject1 = true;
            s = createTrueAnimation(truePath, 1500, thickLine, hole, holder, thinLine);
            s.play();
            hole.setDisable(true);
        });

    }

    //Same operations for my other objects.Create paths for my object.When the clicked circle
    // check the other objects clicked before or not.And choose the path and play animations for this check.

    //Creat addobject2 method for creat and add object2 on my pane
    private void addObject2() {
        Hole hole = new Hole(390, 525);
        ThinLine thinLine = new ThinLine(390, 525, 390, 425);
        ThickLine thickLine = new ThickLine(390, 425, 390, 100);
        thickLine.addHalfCircle(390, 313, "left");
        thickLine.addHalfCircle(390, 200, "right");
        Holder holder = createHolder(390, 425, "horizontal");
        getChildren().add(thickLine);
        addRectangle(365, 425, 400, 50);
        getChildren().addAll(hole, thinLine, holder);

        Line truePath = new Line(390, 263, 390, 590);
        Line falsePath = new Line(390, 263, 390, 278);
        hole.setOnMouseClicked(event -> {
            if (checkObject4 && checkObject1) {
                s = createTrueAnimation(truePath, 3200, thickLine, hole, holder, thinLine);
                checkObject2 = true;
                Timeline changeLevel = new Timeline(new KeyFrame(Duration.millis(0.1), e -> changeLevel("")));
                SequentialTransition sq = new SequentialTransition(s, changeLevel);
                sq.play();
                resetCheckProperty();
            } else {
                s = createFalseAnimation(falsePath, thickLine, hole, holder);
                s.play();
            }
            hole.setDisable(true);
        });
    }

    //Creat addobject3 method for creat and add object3 on my pane
    private void addObject3() {
        Hole hole = new Hole(470, 525);
        ThinLine thinLine = new ThinLine(470, 525, 470, 425);
        ThickLine thickLine = new ThickLine(470, 425, 470, 308);
        Holder holder = createHolder(470, 425, "horizontal");
        getChildren().add(thickLine);
        addRectangle(445, 425, 200, 50);
        getChildren().addAll(hole, thinLine, holder);

        Line truePath = new Line(470, 367, 470, 490);
        hole.setOnMouseClicked(event -> {
            checkObject3 = true;
            s = createTrueAnimation(truePath, 1500, thickLine, hole, holder, thinLine);
            s.play();
            hole.setDisable(true);
        });

    }

    //Creat addobject4 method for creat and add object4 on my pane
    private void addObject4() {
        Hole hole = new Hole(700, 525);
        ThinLine thinLine = new ThinLine(700, 525, 700, 313);
        thinLine.addLine(675, 313);
        ThickLine thickLine = new ThickLine(675, 313, 385, 313);
        thickLine.addHalfCircle(470, 313, "up");
        Holder holder = createHolder(675, 313, "vertical");
        getChildren().add(thickLine);
        addRectangle(675, 288, 50, 400);
        getChildren().addAll(hole, thinLine, holder);

        Line truePath = new Line(530, 303, 825, 303);
        Line falsePath = new Line(530, 303, 545, 303);

        hole.setOnMouseClicked(event -> {
            if (checkObject3) {
                s = createTrueAnimation(truePath, 3000, thickLine, hole, holder, thinLine);
                checkObject4 = true;
                s.play();
            } else {
                s = createFalseAnimation(falsePath, thickLine, hole, holder);
                s.play();
            }
            hole.setDisable(true);
        });


    }

    //Override the addobjects Method
    @Override
    public void addObjects() {
        //add object1 , object2 ,object3 and object4 on my addobjects method
        addObject1();
        addObject2();
        addObject3();
        addObject4();
        addNumber(2);
    }

    @Override
    void resetCheckProperty() {
        checkObject1 = false;
        checkObject2 = false;
        checkObject3 = false;
        checkObject4 = false;
    }
}
