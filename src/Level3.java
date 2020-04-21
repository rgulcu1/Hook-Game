import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.shape.Line;
import javafx.util.Duration;

//Creat Level3 class for creat pane of level3
public class Level3 extends Levels {
    //Create check variable for check object is clicked before or not.I use this variable for collisions.
    boolean checkobject1 = false;
    boolean checkobject2 = false;
    boolean checkobject3 = false;
    SequentialTransition s;

    //Creat addobject1 method for creat and add object1 on my pane
    private void addObject1() {
        Hole hole = new Hole(275, 400);
        ThinLine thinLine = new ThinLine(275, 400, 675, 400);
        thinLine.addLine(675, 375);
        ThickLine thickLine = new ThickLine(675, 375, 675, 170);
        thickLine.addHalfCircle(675, 275, "right");
        Holder holder = createHolder(675, 375, "horizontal");
        getChildren().add(thickLine);
        addRectangle(650, 375, 300, 50);
        getChildren().addAll(hole, thinLine, holder);
        Line truePath = new Line(685, 273, 685, 480);
        Line falsePath = new Line(685, 273, 685, 288);

        hole.setOnMouseClicked(event -> {
            if (checkobject2) {
                s = createTrueAnimation(truePath, 2200, thickLine, hole, holder, thinLine);
                checkobject1 = true;
                s.play();
            } else {
                s = createFalseAnimation(falsePath, thickLine, hole, holder);
                s.play();
            }
            hole.setDisable(true);
        });

        //addAnimation(685,273,685,480,2200,thickLine,hole,holder,thinLine);
    }

    //Creat addobject2 method for creat and add object2 on my pane
    private void addObject2() {
        Hole hole = new Hole(350, 450);
        ThinLine thinLine = new ThinLine(350, 450, 525, 450);
        thinLine.addLine(525, 275);
        thinLine.addLine(550, 275);
        ThickLine thickLine = new ThickLine(550, 275, 680, 275);
        Holder holder = createHolder(550, 275, "vertical");
        getChildren().add(thickLine);
        addRectangle(350, 250, 50, 200);
        getChildren().addAll(hole, thinLine, holder);

        Line truePath = new Line(615, 275, 480, 275);
        hole.setOnMouseClicked(event -> {
            checkobject2 = true;
            s = createTrueAnimation(truePath, 1500, thickLine, hole, holder, thinLine);
            s.play();
            hole.setDisable(true);
        });

    }

    //Creat addobject3 method for creat and add object3 on my pane
    private void addObject3() {
        Hole hole = new Hole(425, 500);
        ThinLine thinLine = new ThinLine(425, 500, 425, 175);
        thinLine.addLine(450, 175);
        ThickLine thickLine = new ThickLine(450, 175, 775, 175);
        thickLine.addHalfCircle(675, 175, "up");
        Holder holder = createHolder(450, 175, "vertical");
        getChildren().add(thickLine);
        addRectangle(50, 150, 50, 400);
        getChildren().addAll(hole, thinLine, holder);

        Line truePath = new Line(612, 165, 285, 165);
        Line falsePath = new Line(612, 165, 597, 165);
        hole.setOnMouseClicked(event -> {
            if (checkobject1) {
                s = createTrueAnimation(truePath, 3000, thickLine, hole, holder, thinLine);
                checkobject3 = true;
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

    //Override the addobjects Method
    @Override
    public void addObjects() {
        //add object1 , object2 and object3  on my addobjects method
        addObject1();
        addObject2();
        addObject3();
        addNumber(3);
    }

    @Override
    void resetCheckProperty() {
        checkobject1 = false;
        checkobject2 = false;
        checkobject3 = false;
    }
}
