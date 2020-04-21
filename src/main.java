import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;


public class main extends Application {
    //Creat levelChoice variable for changing levels
    public static int levelChoice = 0;
    //Create gridPane
    public static Pane pane = new Pane();
    public static LevelSelect mainPane;
    //Creat a Levels array and fill it 5 levels
    static Levels[] levels = {new Level1(), new Level2(), new Level3(), new Level4(), new Level5()};

    public static void main(String[] args) {
        launch(args);
    }

    //Creat changePane method for changing the pane
    public static void changePane(Pane pane2) {
        //First clear the pane and add the new pane to pane
        pane.getChildren().clear();
        pane.getChildren().add(pane2);
    }

    //Check the levelChoice and change the level depends on levelChoice
    public static void changeLevels() {
        switch (levelChoice) {
            case 0:
                changePane(mainPane);
                break;
            case 1:
                changePane(levels[0]);
                break;
            case 2:
                changePane(levels[1]);
                break;
            case 3:
                changePane(levels[2]);
                break;
            case 4:
                changePane(levels[3]);
                break;
            case 5:
                changePane(levels[4]);
                break;

        }
    }

    private void addInıtialScrren(Pane pane){
        Text text=new Text(320,150,"H  O  O  K");
        text.setFont(Font.font("",FontWeight.EXTRA_LIGHT,75));
        text.setFill(Color.valueOf("5A5653"));

        Hole hole =new Hole(675,300);
        ThinLine thinLine=new ThinLine(675,300,600,300);
        Holder holder=new Holder(600,300,1);
        ThickLine thickLine=new ThickLine(600,300,320,300);

        Rectangle rect1=new Rectangle(425,400,150,50);
        rect1.setStroke(Color.valueOf("5A5653"));
        rect1.setFill(Color.valueOf("5A5653"));
        rect1.setArcHeight(30);
        rect1.setArcWidth(30);

        Text textStart=new Text(460,435,"Start");
        textStart.setFont(Font.font(35));
        textStart.setFill(Color.valueOf("F0F0E8"));

        Rectangle rect2=new Rectangle(425,500,150,50);
        rect2.setFill(Color.valueOf("5A5653"));
        rect2.setArcHeight(30);
        rect2.setArcWidth(30);

        Text textExit=new Text(468,535,"Exit");
        textExit.setFont(Font.font(35));
        textExit.setFill(Color.valueOf("F0F0E8"));

        pane.getChildren().addAll(text,hole,thinLine,holder,thickLine,rect1,rect2,textStart,textExit);

        rect1.setOnMousePressed(event -> {
            rect1.setFill(Color.valueOf("F0F0E8"));
            textStart.setFill(Color.valueOf("5A5653"));
        });
        rect1.setOnMouseReleased(event -> {
            rect1.setFill(Color.valueOf("5A5653"));
            textStart.setFill(Color.valueOf("F0F0E8"));
            changeLevels();
        });
        textStart.setOnMousePressed(event -> {
            rect1.setFill(Color.valueOf("F0F0E8"));
            textStart.setFill(Color.valueOf("5A5653"));
        });
        textStart.setOnMouseReleased(event -> {
            rect1.setFill(Color.valueOf("5A5653"));
            textStart.setFill(Color.valueOf("F0F0E8"));
            changeLevels();
        });

        rect2.setOnMousePressed(event -> {
            rect2.setFill(Color.valueOf("F0F0E8"));
            textExit.setFill(Color.valueOf("5A5653"));
        });
        rect2.setOnMouseReleased(event -> {
            rect2.setFill(Color.valueOf("5A5653"));
            textExit.setFill(Color.valueOf("F0F0E8"));
            System.exit(0);
        });
        textExit.setOnMousePressed(event -> {
            rect2.setFill(Color.valueOf("F0F0E8"));
            textExit.setFill(Color.valueOf("5A5653"));
        });
        textExit.setOnMouseReleased(event -> {
            rect2.setFill(Color.valueOf("5A5653"));
            textExit.setFill(Color.valueOf("F0F0E8"));
            System.exit(0);
        });

    }

    //override start method
    @Override
    public void start(Stage primaryStage) throws Exception {

        //Creat Level select pane with 1 button
        mainPane = new LevelSelect(1);
        addInıtialScrren(pane);


        //Creat scene and set stage scene and show stage
        Scene scene = new Scene(pane, 1000, 1000);
        scene.setFill(Color.valueOf("F0F0E8"));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hook");
        primaryStage.show();


    }


}
