/**
 * Created by Michael Sloma on 9/2/2017.
 */
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage window;
    private Scene mainScene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("EECS 1570");

        Button javaButton = new Button();
        javaButton.setText("Java");
        HBox javaPane = new HBox();
        javaPane.getChildren().add(javaButton);
        javaPane.setAlignment(Pos.TOP_CENTER);
        javaPane.setPadding(new Insets(10, 0, 0, 10));
        javaPane.setStyle("-fx-background-color: salmon;");

        Button programmingButton = new Button();
        programmingButton.setText("Programming");
        HBox programmingPane = new HBox();
        programmingPane.getChildren().add(programmingButton);
        programmingPane.setAlignment(Pos.TOP_CENTER);
        programmingPane.setPadding(new Insets(10, 0, 0, 0));
        programmingPane.setStyle("-fx-background-color: salmon;");

        Button isNotSoEasyButton = new Button();
        isNotSoEasyButton.setText("Is Not So Easy.");
        HBox isNotSoEasyPane = new HBox();
        isNotSoEasyPane.getChildren().add(isNotSoEasyButton);
        isNotSoEasyPane.setAlignment(Pos.TOP_CENTER);
        isNotSoEasyPane.setPadding(new Insets(10, 10, 0, 120));
        isNotSoEasyPane.setStyle("-fx-background-color: yellow;");

        Button linearDataStructuresButton = new Button();
        linearDataStructuresButton.setText("Linear Data Structures!");
        HBox linearDataStructuresPane = new HBox();
        linearDataStructuresPane.getChildren().add(linearDataStructuresButton);
        linearDataStructuresPane.setAlignment(Pos.CENTER);
        linearDataStructuresPane.setPadding(new Insets(10, 0, 10, 0));
        linearDataStructuresPane.setStyle("-fx-background-color: blue;");

        BorderPane pane = new BorderPane();
        pane.setLeft(javaPane);
        pane.setCenter(programmingPane);
        pane.setRight(isNotSoEasyPane);
        pane.setBottom(linearDataStructuresPane);

        mainScene = new Scene(pane, 400, 200);
        window.setScene(mainScene);
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}