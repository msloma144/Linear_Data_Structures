import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GUI extends Application{
    private Stage window;
    private Scene mainScene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("Stable Matching");

        // File Name Field
        TextField fileNameBox = new TextField();
        fileNameBox.setMinSize(300, 15);
        Label fileName = new Label();
        fileName.setLabelFor(fileNameBox);
        fileName.setText("File Name:  ");
        fileNameBox.setPromptText("Please enter full file location");
        fileName.setStyle("-fx-font: 15px TimesNewRoman;");

        HBox fileNameBoxPane = new HBox();
        fileNameBoxPane.getChildren().add(fileName);
        fileNameBoxPane.getChildren().add(fileNameBox);
        fileNameBoxPane.setAlignment(Pos.CENTER);
        fileNameBoxPane.setPadding(new Insets(10, 20, 10, 0));
        fileNameBoxPane.setStyle("-fx-background-color: green;");

        //Output Field Name
        TextArea outputField = new TextArea();
        outputField.setMaxSize(300, 200);
        Label outputName = new Label();
        outputName.setLabelFor(outputField);
        outputName.setText("Output:  ");
        outputName.setStyle("-fx-font: 15px TimesNewRoman;");

        HBox outputFieldPane = new HBox();
        outputFieldPane.getChildren().add(outputName);
        outputFieldPane.getChildren().add(outputField);
        outputFieldPane.setAlignment(Pos.CENTER);
        outputFieldPane.setPadding(new Insets(10, 0, 10, 0));
        outputFieldPane.setStyle("-fx-background-color: yellow;");

        //Load Button
        Button loadButton = new Button();
        loadButton.setText("Load File");
        loadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                try{
                    outputField.setText(GaleShapley.outputFile(fileNameBox.getText()));
                }
                catch (Exception e){
                    outputField.setText("You have not entered a file!");
                }
            }
        });

        //Run Button
        Button runButton = new Button();
        runButton.setText("Run!");
        runButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                try{
                    outputField.setText(GaleShapley.runGaleShapley(fileNameBox.getText()));
                }
                catch (Exception e){
                    outputField.setText("You have not entered a file!");
                }
            }
        });

        HBox buttonPane = new HBox();
        buttonPane.setSpacing(50);
        buttonPane.getChildren().add(loadButton);
        buttonPane.getChildren().add(runButton);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setPadding(new Insets(10, 0, 10, 0));
        buttonPane.setStyle("-fx-background-color: gray;");

        BorderPane pane = new BorderPane();
        pane.setTop(fileNameBoxPane);
        pane.setCenter(outputFieldPane);
        pane.setBottom(buttonPane);

        mainScene = new Scene(pane, 500, 300);
        window.setScene(mainScene);
        window.show();
    }

    public static void main(String[] args) throws Exception{
        launch(args);
    }
}
