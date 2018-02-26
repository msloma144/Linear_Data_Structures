import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application{
    private Stage window;
    private Scene mainScene;

    private SpellCheck currSpellChecker;

    private boolean checkFieldsRequired(String dicPath, String misspellingsPath, String delineator){
        return (!dicPath.equals("") && !misspellingsPath.equals("") && (delineator.equals("\\n") || delineator.equals(" ")));
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("Spell Checker");

        // Dictionary Field
        TextField dicBox = new TextField();
        dicBox.setMinSize(300, 15);
        Label dic = new Label();
        dic.setLabelFor(dicBox);
        dic.setText("Dictionary Location:  ");
        dic.setStyle("-fx-font: 15px TimesNewRoman;");

        HBox dicBoxPane = new HBox();
        dicBoxPane.getChildren().add(dic);
        dicBoxPane.getChildren().add(dicBox);
        dicBoxPane.setAlignment(Pos.CENTER);
        dicBoxPane.setPadding(new Insets(10, 20, 10, 0));
        dicBoxPane.setStyle("-fx-background-color: gray;");

        // Misspelled Field
        TextField misspellField = new TextField();
        misspellField.setMinSize(300, 15);
        Label misspell = new Label();
        misspell.setLabelFor(misspellField);
        misspell.setText("Common Misspellings Location:  ");
        misspell.setStyle("-fx-font: 15px TimesNewRoman;");

        HBox misspellBoxPane = new HBox();
        misspellBoxPane.getChildren().add(misspell);
        misspellBoxPane.getChildren().add(misspellField);
        misspellBoxPane.setAlignment(Pos.CENTER);
        misspellBoxPane.setPadding(new Insets(10, 20, 10, 0));
        misspellBoxPane.setStyle("-fx-background-color: gray;");

        // Delineator Field
        TextField delineatorField = new TextField();
        delineatorField.setMinSize(300, 15);
        Label delineator = new Label();
        delineator.setLabelFor(delineatorField);
        delineator.setText("Delineator:  ");
        delineator.setStyle("-fx-font: 15px TimesNewRoman;");

        HBox delineatorBoxPane = new HBox();
        delineatorBoxPane.getChildren().add(delineator);
        delineatorBoxPane.getChildren().add(delineatorField);
        delineatorBoxPane.setAlignment(Pos.CENTER);
        delineatorBoxPane.setPadding(new Insets(10, 20, 10, 0));
        delineatorBoxPane.setStyle("-fx-background-color: gray;");

        // Input Field Container
        VBox inputFieldsBox = new VBox();
        inputFieldsBox.getChildren().addAll(dicBoxPane, misspellBoxPane, delineatorBoxPane);

        //Suggestions Field
        TextArea  suggField= new TextArea();
        suggField.setMaxSize(250, 450);
        Label suggName = new Label();
        suggName.setLabelFor(suggField);
        suggName.setText("Suggestions:  ");
        suggName.setStyle("-fx-font: 15px TimesNewRoman;");

        HBox suggFieldPane = new HBox();
        suggFieldPane.getChildren().add(suggName);
        suggFieldPane.getChildren().add(suggField);
        suggFieldPane.setAlignment(Pos.CENTER);
        suggFieldPane.setPadding(new Insets(10, 0, 10, 10));
        suggFieldPane.setStyle("-fx-background-color: #4d6280;");

        //Output Field
        TextArea outputField = new TextArea();
        outputField.setPrefSize(700, 450);
        outputField.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                if (checkFieldsRequired(dicBox.getText(), misspellField.getText(), delineatorField.getText())) {
                    if(currSpellChecker != null) {
                        try {
                            outputField.setText(currSpellChecker.readInput(outputField.getText()));
                            outputField.positionCaret(outputField.getLength() - 1);
                            suggField.setText(currSpellChecker.getSuggList());
                        } catch (Exception e) {
                            suggField.setText("" + e);
                        }
                    }
                    else outputField.setText("You forgot to press the Load button!");
                }
                else
                    outputField.setText("You forgot to fill in a required field!");
            }
        });

        Label outputName = new Label();
        outputName.setLabelFor(outputField);
        outputName.setText("Output:  ");
        outputName.setStyle("-fx-font: 15px TimesNewRoman;");

        HBox outputFieldPane = new HBox();
        outputFieldPane.getChildren().add(outputName);
        outputFieldPane.getChildren().add(outputField);
        outputFieldPane.setAlignment(Pos.CENTER);
        outputFieldPane.setPadding(new Insets(10, 0, 10, 0));
        outputFieldPane.setStyle("-fx-background-color: #4d6280;");


        //Output Area Container
        HBox outputAndSugg = new HBox();
        outputAndSugg.getChildren().addAll(outputFieldPane, suggFieldPane);
        outputAndSugg.setAlignment(Pos.CENTER);
        outputAndSugg.setPadding(new Insets(10, 0, 10, 0));
        outputAndSugg.setStyle("-fx-background-color: #4d6280;");

        // Load Button
        Button loadButton = new Button();
        loadButton.setPrefSize(100, 50);
        loadButton.setText("Load");
        loadButton.setOnAction(event -> {
            if(checkFieldsRequired(dicBox.getText(), misspellField.getText(), delineatorField.getText())){
                try{
                    currSpellChecker = new SpellCheck(dicBox.getText(), misspellField.getText(), delineatorField.getText());
                }
                catch (Exception e){
                    suggField.setText("" + e);
                }
            }
            else outputField.setText("You forgot to fill in a required field!");
        });

        HBox buttonPane = new HBox();
        buttonPane.getChildren().addAll(loadButton);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setPadding(new Insets(10, 0, 10, 0));
        buttonPane.setStyle("-fx-background-color: gray;");

        BorderPane pane = new BorderPane();
        pane.setTop(inputFieldsBox);
        pane.setCenter(outputAndSugg);
        pane.setBottom(buttonPane);

        mainScene = new Scene(pane, 1200, 600);
        window.setScene(mainScene);
        window.show();
    }

    public static void main(String[] args) throws Exception{
        System.out.println(Character.getNumericValue('a'));
        launch(args);
    }
}
