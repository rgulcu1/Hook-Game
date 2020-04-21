import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.shape.Line;
import javafx.util.Duration;

//Same operations of other levels
public class Level4 extends Levels {
    //Create check variable for check object is clicked before or not.I use this variable for collisions.
    boolean checkObject1 = false;
    boolean checkObject2 = false;
    int checkSwitch = 1;
    Switch swtch;
    SequentialTransition s;


//Unlike the other levels ı check the switch before choose path and play animation for my object clicks
    private void addObject1() {
        Hole hole = new Hole(350, 400);
        ThinLine thinLine = new ThinLine(350, 400, 675, 400);
        thinLine.addLine(675, 375);
        ThickLine thickLine = new ThickLine(675, 375, 675, 245);
        Holder holder = createHolder(675, 375, "horizontal");
        getChildren().add(thickLine);
        addRectangle(650, 375, 200, 50);
        getChildren().addAll(hole, thinLine, holder);

        Line truePath = new Line(675, 310, 675, 450);
        hole.setOnMouseClicked(event -> {
            if (checkSwitch == 0) {
                checkObject1 = true;
                s = createTrueAnimation(truePath, 2000, thickLine, hole, holder, thinLine);
                s.play();
                hole.setDisable(true);
            }
        });

    }

    private void addObject2() {
        Hole hole = new Hole(450, 500);
        ThinLine thinLine = new ThinLine(450, 500, 450, 250);
        thinLine.addLine(475, 250);
        ThickLine thickLine = new ThickLine(475, 250, 715, 250);
        thickLine.addHalfCircle(675, 250, "up");
        Holder holder = createHolder(475, 250, "vertical");
        getChildren().add(thickLine);
        addRectangle(150, 225, 50, 325);
        getChildren().addAll(hole, thinLine, holder);

        Line truePath = new Line(595, 240, 350, 240);
        Line falsePath = new Line(595, 240, 580, 240);
        hole.setOnMouseClicked(event -> {
            if (checkSwitch == 1) {
                if (checkObject1) {
                    checkObject2 = true;
                    s = createTrueAnimation(truePath, 2500, thickLine, hole, holder, thinLine);
                    FadeTransition fRemove = removeSwitch(swtch);
                    Timeline changeLevel = new Timeline(new KeyFrame(Duration.millis(0.1), e -> changeLevel("")));
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
    //Create addSwitch method for add Switch my pane with given position
    private void addSwitch() {
        //Creat switch and add the pane
        swtch = createSwitch(450, 400, "vertical");
        getChildren().add(swtch);
        //When the click the switch
        swtch.setOnMouseClicked(event -> {
            //Remove switch from Pane ,rotate the switch and add again
            getChildren().remove(swtch);
            if (swtch.getRotate() == 90) {
                swtch.setRotate(0);
                //Change the check value when the rotate
                checkSwitch = 1;
            } else {
                swtch.setRotate(90);
                checkSwitch = 0;
            }

            getChildren().add(swtch);
        });
    }


    @Override
    public void addObjects() {
        addObject1();
        addObject2();
        //add Switch initial verical position and add right and left arrow
        addSwitch();
        addNumber(4);

    }

    @Override
    void resetCheckProperty() {
        checkObject1 = false;
        checkObject2 = false;
        checkSwitch = 1;
    }
}
