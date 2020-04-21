import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

//Create LevelSelect class for creat pane for level select screen
public class LevelSelect extends Pane {
    //creat a variables x and values for locations buttons.
    private int centerX = 150;
    private int centerY = 50;
    //define maxlevelsize
    private int maxLevelSize = 5;
    //Create Circle and Text arrays for my button
    Circle[] circles = new Circle[maxLevelSize];
    Text[] texts = new Text[maxLevelSize];

    //Create constructor for creating buttons of the total number of levels
    public LevelSelect(int totalLevelNumber) {
        //Setpref size and set backround color my pane
        setPrefSize(1000, 1000);
        setStyle("-fx-background-color: F0F0E8");

        //Create and add my buttons on my pane
        for (int i = 1; i <= totalLevelNumber; i++) {
            circles[i - 1] = new Circle(centerX * i, centerY, 30);
            circles[i - 1].setFill(Color.valueOf("5A5653"));
            circles[i - 1].setStroke(Color.valueOf("5A5653"));
            texts[i - 1] = new Text(i * centerX - 7, centerY + 9, i + "");
            texts[i - 1].setFill(Color.valueOf("F0F0E8"));
            texts[i - 1].setFont(Font.font(30));
            //add event my buttons
            addEvent(i - 1);
            //Add lines my under Buttons
            ThinLine thinLine = new ThinLine(centerX * i, centerY + 30, centerX * i, centerY + 100);
            Holder holder = new Holder(centerX * i, centerY + 100, 0);
            ThickLine thickLine = new ThickLine(centerX * i, centerY + 100, centerX * i, 800);

            getChildren().addAll(circles[i - 1], texts[i - 1], thinLine, holder, thickLine);
        }


    }

    //Create addEvent method for add event my buttons.The input num is the required for show which button
    public void addEvent(int num) {
        //when the pressed button change the color my button because of push effect
        circles[num].setOnMousePressed(event -> {
            circles[num].setFill(Color.valueOf("F0F0E8"));
            texts[num].setFill(Color.valueOf("5A5653"));
        });
        //When the released on mouse check the ıs cursor still on my button.If cursor still on my button change the levelCoice
        // if cursor not still on my button just the button will back old color.
        circles[num].setOnMouseReleased(event -> {
            circles[num].setFill(Color.valueOf("5A5653"));
            texts[num].setFill(Color.valueOf("F0F0E8"));
            if (circles[num].getCenterX() - 30 < event.getX() && event.getX() < circles[num].getCenterX() + 30 &&
                    circles[num].getCenterY() - 30 < event.getY() && event.getY() < circles[num].getCenterY() + 30) {
                main.levelChoice = num + 1;
                //When the click Button My level is move underr to top with path transition
                getChildren().add(main.levels[num]);
                Line linePath = new Line(500, 1200, 500, 500);
                PathTransition pt = new PathTransition(Duration.millis(3000), linePath);
                pt.setNode(main.levels[num]);
                pt.setCycleCount(1);
                Timeline tl = new Timeline(new KeyFrame(Duration.millis(0.1), e -> main.changeLevels()));
                SequentialTransition s = new SequentialTransition(pt, tl);
                s.play();
                circles[num].setDisable(true);
            }
        });
        //same operations for text which is the on button
        texts[num].setOnMousePressed(event -> {
            circles[num].setFill(Color.valueOf("F0F0E8"));
            texts[num].setFill(Color.valueOf("5A5653"));
        });
        texts[num].setOnMouseReleased(event -> {
            circles[num].setFill(Color.valueOf("5A5653"));
            texts[num].setFill(Color.valueOf("F0F0E8"));
            if (circles[num].getCenterX() - 30 < event.getX() && event.getX() < circles[num].getCenterX() + 30 &&
                    circles[num].getCenterY() - 30 < event.getY() && event.getY() < circles[num].getCenterY() + 30)
                main.levelChoice = num + 1;
            //When the click Button My level is move underr to top with path transition
            getChildren().add(main.levels[num]);
            Line linePath = new Line(500, 1200, 500, 500);
            PathTransition pt = new PathTransition(Duration.millis(3000), linePath);
            pt.setNode(main.levels[num]);
            pt.setCycleCount(1);
            Timeline tl = new Timeline(new KeyFrame(Duration.millis(0.1), e -> main.changeLevels()));
            SequentialTransition s = new SequentialTransition(pt, tl);
            s.play();
            circles[num].setDisable(true);
        });

    }


}
