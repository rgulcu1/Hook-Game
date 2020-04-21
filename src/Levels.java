import javafx.animation.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

//Create a abstract Levels class and extends Pane for combine with my groups and add the pane
public abstract class Levels extends Pane {

    //Create a no-arg constructor
    public Levels() {
        //setpref size and set backgorund color pane
        setPrefSize(1000, 1000);
        setStyle("-fx-background-color: F0F0E8");
        //add objects my pane
        addObjects();
    }

    //create addObjects abstract method for add the objects to the pane
    public abstract void addObjects();

    //create createSwitch method for create switch with given position
    public Switch createSwitch(int centerX, int centerY, String position) {
        //Creat switch and add to Pane
        Switch swtch = new Switch(centerX, centerY, position);
        return swtch;
    }

    //create creatHolder method for the creat Holder with given posiiton
    public Holder createHolder(int startX, int startY, String position) {
        Holder holder;
        if (position.equalsIgnoreCase("vertical")) holder = new Holder(startX, startY, 1);
        else holder = new Holder(startX, startY, 0);

        return holder;
    }
    //creat add Rectangle method for creat and add Rectangle which is same color with my Pane's background
    public void addRectangle(int startX, int startY, int height, int width) {
        Rectangle rectangle = new Rectangle(startX, startY, width, height);
        rectangle.setFill(Color.valueOf("F0F0E8"));
        getChildren().add(rectangle);
    }

    //Create changeLevel method for change the level with the current levelChoice
    public void changeLevel(String choice) {
        //First check the choice
        if (choice.equalsIgnoreCase("homepage")) {
            main.levelChoice = 0;
            main.mainPane = new LevelSelect(5);
        } else {
            main.levelChoice++;
        }
        //add my old object before the leave level
        addObjects();
       //call changeLevels method
       main.changeLevels();
    }

    //create createTrueAnimation method for create a required aimations for true clicks
    public SequentialTransition createTrueAnimation(Line path, int Time, ThickLine thickLine, Hole hole, Holder holder, ThinLine thinLine) {

        //Create path transition for move my objects to my Holder.
        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.millis(Time));
        pt.setPath(path);
        pt.setNode(thickLine);
        pt.setCycleCount(1);

        //Create scale transition for enlarge my Holder
        ScaleTransition st1 = new ScaleTransition(Duration.millis(300), holder);
        if (holder.getStartX() == holder.getEndX()) st1.setByY(2);
        else st1.setByX(2);
        st1.setCycleCount(1);

        //Create Fade transition for disappear my holder from pane
        FadeTransition ftHolder = new FadeTransition(Duration.millis(300), holder);
        ftHolder.setFromValue(1.0);
        ftHolder.setToValue(0.0);
        ftHolder.setCycleCount(1);

        //Create Fade transition for disappear my thinLine from pane
        FadeTransition ftThinLine = new FadeTransition(Duration.millis(300), thinLine);
        ftThinLine.setFromValue(1.0);
        ftThinLine.setToValue(0.0);
        ftThinLine.setCycleCount(1);

        //Create scale transition for shrink my Holder
        ScaleTransition st2 = new ScaleTransition(Duration.millis(300), holder);
        if (holder.getStartX() == holder.getEndX()) st2.setByY(-2.5);
        else st2.setByX(-2.5);
        st2.setCycleCount(1);

        //Creat scale transition shrink and disappear my hole from pane
        ScaleTransition st3 = new ScaleTransition(Duration.millis(300), hole);
        st3.setByX(-1);
        st3.setByY(-1);
        st3.setCycleCount(1);

        //Create Parallel Transition for my animations which is occurs simultaneous
        ParallelTransition p1 = new ParallelTransition(clickEffect(hole), pt, st1);
        ParallelTransition p2 = new ParallelTransition(ftHolder, ftThinLine, st2, st3);

        //Creat Sequantial transition for my animations which is  occurs consecutive
        SequentialTransition s = new SequentialTransition(p1, p2);

        //return the total animation which is hold all animation
        return s;
    }
    //create SequentialTransition method for create a required aimations for false clicks
    public SequentialTransition createFalseAnimation(Line path, ThickLine thickLine, Hole hole, Holder holder) {
        //set disable no permission click after a mistake
        setDisable(true);
        //remove current circle and add again because of to be able to pass on other objects.
        Hole hole2 = new Hole(hole.getCenterX(), hole.getCenterY());
        getChildren().add(hole2);
        getChildren().remove(hole);

        //Create path Transition to move my objects
        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.millis(300));
        pt.setPath(path);
        pt.setNode(thickLine);
        pt.setAutoReverse(true);
        pt.setCycleCount(2);

        //Create scale Transition to enlar ge my holder
        ScaleTransition st = new ScaleTransition(Duration.millis(300), holder);
        if (holder.getStartX() == holder.getEndX()) st.setByY(2);
        else st.setByX(2);
        st.setCycleCount(1);

        //Create Parallel Transition for my animations which is occurs simultaneous
        ParallelTransition ptClick = new ParallelTransition(clickEffect(hole2), pt, st);

        //Creat PathTransition for show a error massage
        Line lineError = new Line(hole2.getCenterX(), hole2.getCenterY(), 525, 350);
        PathTransition ptError = new PathTransition(Duration.millis(1500), lineError, hole2);

        //Creat Scale Transition for enlarge  hole
        ScaleTransition scaleError = new ScaleTransition(Duration.millis(1500), hole2);
        scaleError.setByX(8);
        scaleError.setByY(8);

        //Create Parallel Transition for my animations which is occurs simultaneous
        ParallelTransition error = new ParallelTransition(ptError, scaleError);
        //Creat text for error massage
        Text text = new Text(410, 350, "Try Again!");
        text.setFont(Font.font(50));
        text.setFill(Color.valueOf("F0F0E8"));
        //Set the text appear time
        Timeline tlError = new Timeline(new KeyFrame(Duration.millis(10), e -> getChildren().add(text)));
        Timeline tlErrorWait = new Timeline(new KeyFrame(Duration.millis(1500)));
        SequentialTransition sqError = new SequentialTransition(error, tlError, tlErrorWait);

        //Creat Fade Transition for disapper this level pane
        FadeTransition ft = new FadeTransition(Duration.millis(1000), this);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setCycleCount(1);

        //Create timelines for cleat tihs pane and add objects again the pane and set Disable of pane
        Timeline timelineClear = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> getChildren().clear()));

        Timeline timelineAdd = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> addObjects()));

        Timeline tlDisable = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> setDisable(false)));

        //Creat Fade Transition for apper again pane
        FadeTransition ft2 = new FadeTransition(Duration.millis(500), this);
        ft2.setFromValue(0.0);
        ft2.setToValue(1.0);
        ft2.setCycleCount(1);

        //Creat Sequantial transition for my animations which is  occurs consecutive
        SequentialTransition s = new SequentialTransition(ptClick, sqError, ft, timelineClear, timelineAdd, tlDisable, ft2);
        //reset the check values
        resetCheckProperty();

        //return the total animation which is hold all animation
        return s;


    }
    //creat removeSwitch method for disapper switch from pane with fade Trasition
    public FadeTransition removeSwitch(Switch swtch) {
        FadeTransition ft = new FadeTransition(Duration.millis(300), swtch);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setCycleCount(1);

        return ft;
    }
    //Creat resetCheckProperty abstract method for reset check values the default.
    abstract void resetCheckProperty();

    //Creat addNumber method for add Level number top of the pane
    public void addNumber(int number) {
        Text text = new Text(500, 100, number + "");
        text.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 75));
        text.setFill(Color.valueOf("5A5653"));
        getChildren().add(text);

    }

    //Create clickEffect for occur click effet when the click the circle(hole)
    public ScaleTransition clickEffect(Hole hole) {
        ScaleTransition st = new ScaleTransition(Duration.millis(100), hole);
        st.setByX(0.25);
        st.setByY(0.25);
        st.setCycleCount(2);
        st.setAutoReverse(true);

        return st;
    }


}
