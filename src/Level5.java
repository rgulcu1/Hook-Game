import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.shape.Line;
import javafx.util.Duration;


//Same operations of other levels
public class Level5 extends Levels {
    //Create check variable for check object is clicked before or not.I use this variable for collisions.
    boolean checkObject1 = false;
    boolean checkObject2 = false;
    boolean checkObject3 = false;
    boolean checkObject4 = false;
    int checkSwitch1 = 1;
    int checkSwitch2 = 0;
    Switch swtch1;
    Switch swtch2;
    SequentialTransition s;

    //Unlike the other levels ı dont move the other level when no objects left he pane.I return the my change Level pane for choose the any level

    private void addObject1() {
        Hole hole = new Hole(450, 500);
        ThinLine thinLine = new ThinLine(450, 500, 450, 150);
        thinLine.addLine(475, 150);
        ThickLine thickLine = new ThickLine(475, 150, 680, 150);
        thickLine.addHalfCircle(575, 150, "up");
        Holder holder = createHolder(475, 150, "vertical");
        getChildren().add(thickLine);
        addRectangle(175, 125, 50, 300);
        getChildren().addAll(hole, thinLine, holder);

        Line truePath = new Line(577, 140, 360, 140);
        Line falsePath = new Line(577, 140, 562, 140);
        hole.setOnMouseClicked(event -> {
            if (checkSwitch1 == 1 && checkSwitch2 == 1) {
                if (checkObject4) {
                    s = createTrueAnimation(truePath, 3000, thickLine, hole, holder, thinLine);
                    checkObject1 = true;
                    FadeTransition fRemove = removeSwitch(swtch1);
                    SequentialTransition sRemove = new SequentialTransition(s, fRemove);
                    sRemove.play();
                } else {
                    s = createFalseAnimation(falsePath, thickLine, hole, holder);
                    s.play();
                }
                hole.setDisable(true);
            }
        });

    }

    private void addObject2() {
        Hole hole = new Hole(340, 425);
        ThinLine thinLine = new ThinLine(340, 425, 675, 425);
        thinLine.addLine(675, 400);
        ThickLine thickLine = new ThickLine(675, 400, 675, 100);
        thickLine.addHalfCircle(675, 150, "right");
        Holder holder = createHolder(675, 400, "horizzontal");
        getChildren().add(thickLine);
        addRectangle(650, 400, 400, 50);
        getChildren().addAll(hole, thinLine, holder);


        Line truePath = new Line(685, 250, 685, 555);
        Line falsePath = new Line(685, 250, 685, 265);
        hole.setOnMouseClicked(event -> {
            if (checkSwitch2 == 0) {
                if (checkObject1) {
                    s = createTrueAnimation(truePath, 3000, thickLine, hole, holder, thinLine);
                    checkObject2 = false;
                    //remove the switch from my pane
                    FadeTransition fRemove = removeSwitch(swtch2);
                    //Change the pane for return my level Select pane
                    Timeline changeLevel = new Timeline(new KeyFrame(Duration.millis(0.1), e -> changeLevel("homepage")));
                    SequentialTransition sRemove = new SequentialTransition(s, fRemove, changeLevel);
                    sRemove.play();
                    resetCheckProperty();
                } else {
                    s = createFalseAnimation(falsePath, thickLine, hole, holder);
                    s.play();
                }
                hole.setDisable(true);
            }
        });

    }

    private void addObject3() {
        Hole hole = new Hole(640, 500);
        ThinLine thinLine = new ThinLine(640, 500, 640, 190);
        thinLine.addLine(615, 190);
        ThickLine thickLine = new ThickLine(615, 190, 570, 190);
        Holder holder = createHolder(615, 190, "vertical");
        getChildren().add(thickLine);
        addRectangle(613, 165, 50, 57);
        getChildren().addAll(hole, thinLine, holder);

        Line truePath = new Line(595, 190, 645, 190);
        hole.setOnMouseClicked(event -> {
            s = createTrueAnimation(truePath, 1000, thickLine, hole, holder, thinLine);
            s.play();
            checkObject3 = true;
            hole.setDisable(true);
        });

    }

    private void addObject4() {
        Hole hole = new Hole(340, 360);
        ThinLine thinLine = new ThinLine(340, 360, 575, 360);
        thinLine.addLine(575, 335);
        Holder holder = createHolder(575, 335, "horizontal");
        ThickLine thickLine = new ThickLine(575, 335, 575, 145);
        thickLine.addHalfCircle(575, 190, "left");
        getChildren().add(thickLine);
        addRectangle(550, 335, 250, 50);
        getChildren().addAll(hole, thinLine, holder);

        Line truePath = new Line(565, 240, 565, 435);
        Line falsePath = new Line(565, 240, 565, 255);
        hole.setOnMouseClicked(event -> {
            if (checkSwitch1 == 0) {
                if (checkObject3) {
                    s = createTrueAnimation(truePath, 3000, thickLine, hole, holder, thinLine);
                    s.play();
                    checkObject4 = true;
                } else {
                    s = createFalseAnimation(falsePath, thickLine, hole, holder);
                    s.play();
                }
                hole.setDisable(true);
            }
        });


    }
    // //Create addSwitch1 method for add Switch1 my pane with given position
    private void addSwitch1() {
        swtch1 = createSwitch(450, 360, "vertical");
        getChildren().add(swtch1);
        swtch1.setOnMouseClicked(event -> {
            getChildren().remove(swtch1);
            if (swtch1.getRotate() == 90) {
                swtch1.setRotate(0);
                checkSwitch1 = 1;
            } else {
                swtch1.setRotate(90);
                checkSwitch1 = 0;
            }

            getChildren().add(swtch1);
        });
    }
    // //Create addSwitch2 method for add Switch2 my pane with given position
    private void addSwitch2() {
        swtch2 = createSwitch(450, 425, "horizontal");
        getChildren().add(swtch2);
        swtch2.setOnMouseClicked(event -> {
            getChildren().remove(swtch2);
            if (swtch2.getRotate() == 90) {
                swtch2.setRotate(0);
                checkSwitch2 = 0;
            } else {
                swtch2.setRotate(90);
                checkSwitch2 = 1;
            }

            getChildren().add(swtch2);
        });
    }

    @Override
    public void addObjects() {
        addObject1();
        addObject4();
        addObject2();
        addObject3();

        //add the two switch initial position vertical and horizontal
        addSwitch1();
        addSwitch2();
        addNumber(5);
        //just add left arrow because of my last level
        //addArrow("left");

    }

    @Override
    void resetCheckProperty() {
        checkObject1 = false;
        checkObject2 = false;
        checkObject3 = false;
        checkObject4 = false;
        checkSwitch1 = 1;
        checkSwitch2 = 0;
    }
}
