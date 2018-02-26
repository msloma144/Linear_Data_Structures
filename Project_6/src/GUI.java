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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application{
    private Stage window;
    private Scene mainScene;

    private Simulation currSimStepper;

    private boolean checkFieldsRequired(String hours, String minutes, String priorCars){
        return (!hours.equals(null) && !minutes.equals(null) && !priorCars.equals(null));
    }

    private boolean checkStepField(String stepValue){
        return !stepValue.equals(null);
    }

    private void initStepper(int hours, int minutes, int priorCars, int stepValue){
        currSimStepper = new Simulation(hours, minutes, priorCars, stepValue);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("Car Wash");

        // Hours Field
        TextField hoursBox = new TextField();
        hoursBox.setMinSize(300, 15);
        Label hours = new Label();
        hours.setLabelFor(hoursBox);
        hours.setText("Hours in shift:  ");
        hours.setStyle("-fx-font: 15px TimesNewRoman;");

        HBox hoursBoxPane = new HBox();
        hoursBoxPane.getChildren().add(hours);
        hoursBoxPane.getChildren().add(hoursBox);
        hoursBoxPane.setAlignment(Pos.CENTER);
        hoursBoxPane.setPadding(new Insets(10, 20, 10, 0));
        hoursBoxPane.setStyle("-fx-background-color: green;");

        // Minutes Field
        TextField minutesField = new TextField();
        minutesField.setMinSize(300, 15);
        Label minutes = new Label();
        minutes.setLabelFor(minutesField);
        minutes.setText("Minutes in shift: ");
        minutes.setStyle("-fx-font: 15px TimesNewRoman;");

        HBox minutesBoxPane = new HBox();
        minutesBoxPane.getChildren().add(minutes);
        minutesBoxPane.getChildren().add(minutesField);
        minutesBoxPane.setAlignment(Pos.CENTER);
        minutesBoxPane.setPadding(new Insets(10, 20, 10, 0));
        minutesBoxPane.setStyle("-fx-background-color: green;");

        // Cars Field
        TextField carsBox = new TextField();
        carsBox.setMinSize(300, 15);
        Label cars = new Label();
        cars.setLabelFor(carsBox);
        cars.setText("Cars in waiting: ");
        cars.setStyle("-fx-font: 15px TimesNewRoman;");

        HBox carsBoxPane = new HBox();
        carsBoxPane.getChildren().add(cars);
        carsBoxPane.getChildren().add(carsBox);
        carsBoxPane.setAlignment(Pos.CENTER);
        carsBoxPane.setPadding(new Insets(10, 20, 10, 0));
        carsBoxPane.setStyle("-fx-background-color: green;");

        // Input Field Container
        VBox inputFieldsBox = new VBox();
        inputFieldsBox.getChildren().addAll(hoursBoxPane, minutesBoxPane, carsBoxPane);


        //Output Field
        TextArea outputField = new TextArea();
        outputField.setMaxSize(400, 200);
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

        //Stats Field
        TextArea statsField = new TextArea();
        statsField.setMaxSize(325, 200);
        Label statsName = new Label();
        statsName.setLabelFor(statsField);
        statsName.setText("Stats:  ");
        statsName.setStyle("-fx-font: 15px TimesNewRoman;");

        HBox statsFieldPane = new HBox();
        statsFieldPane.getChildren().add(statsName);
        statsFieldPane.getChildren().add(statsField);
        statsFieldPane.setAlignment(Pos.CENTER);
        statsFieldPane.setPadding(new Insets(10, 0, 10, 10));
        statsFieldPane.setStyle("-fx-background-color: yellow;");

        //Output Area Container
        HBox outputAndStats = new HBox();
        outputAndStats.getChildren().addAll(outputFieldPane, statsFieldPane);
        outputAndStats.setAlignment(Pos.CENTER);
        outputAndStats.setPadding(new Insets(10, 0, 10, 0));
        outputAndStats.setStyle("-fx-background-color: yellow;");


        //Step Box
        TextArea stepField = new TextArea();
        stepField.setMaxSize(50, 10);
        Label stepValue = new Label();
        stepValue.setLabelFor(stepField);
        stepValue.setText("Step Value:  ");
        stepValue.setStyle("-fx-font: 15px TimesNewRoman;");

        HBox stepFieldPane = new HBox();
        stepFieldPane.getChildren().addAll(stepValue, stepField);
        stepFieldPane.setAlignment(Pos.CENTER);

        //Run Button
        Button runButton = new Button();
        runButton.setText("Run!");
        runButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                if(checkFieldsRequired(hoursBox.getText(), minutesField.getText(), carsBox.getText())){
                    try{
                        int hours = Integer.parseInt(hoursBox.getText());
                        int minutes = Integer.parseInt(minutesField.getText());
                        int priorCars = Integer.parseInt(carsBox.getText());

                        Simulation currSim = new Simulation(hours, minutes, priorCars, 0);
                        currSim.runSimulation();
                        statsField.setText(currSim.getStats());
                        outputField.setText("");
                    }
                    catch (Exception e){
                        outputField.setText("You have entered a non integer value!");
                    }
                }
                else outputField.setText("You forgot to fill in a required field!");
            }
        });

        Button initButton = new Button();
        initButton.setText("Init Step");
        initButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                if(checkFieldsRequired(hoursBox.getText(), minutesField.getText(), carsBox.getText())
                        && checkStepField(stepField.getText())){
                    try{
                        int hours = Integer.parseInt(hoursBox.getText());
                        int minutes = Integer.parseInt(minutesField.getText());
                        int priorCars = Integer.parseInt(carsBox.getText());
                        int stepValue = Integer.parseInt(stepField.getText());

                        initStepper(hours, minutes, priorCars, stepValue);
                    }
                    catch (Exception e){
                        outputField.setText("You have entered a non integer value!");
                    }
                }
                else outputField.setText("You forgot to fill in a required field!");
            }
        });

        Button stepButton = new Button();
        stepButton.setText("Step");
        stepButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                if(checkFieldsRequired(hoursBox.getText(), minutesField.getText(), carsBox.getText())
                        && checkStepField(stepField.getText())){
                    try{
                        currSimStepper.runStep(Integer.parseInt(stepField.getText()));
                        statsField.setText(currSimStepper.getStats());
                        outputField.setText(currSimStepper.queueStatus());
                    }
                    catch (Exception e){
                        outputField.setText("You have entered a non integer value!");
                    }
                }
                else outputField.setText("You forgot to fill in a required field!");
            }
        });

        HBox buttonPane = new HBox();
        buttonPane.setSpacing(50);
        buttonPane.getChildren().addAll(stepFieldPane, stepButton, initButton, runButton);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setPadding(new Insets(10, 0, 10, 0));
        buttonPane.setStyle("-fx-background-color: gray;");

        BorderPane pane = new BorderPane();
        pane.setTop(inputFieldsBox);
        pane.setCenter(outputAndStats);
        pane.setBottom(buttonPane);

        mainScene = new Scene(pane, 900, 400);
        window.setScene(mainScene);
        window.show();
    }

    public static void main(String[] args) throws Exception{
        launch(args);
    }
}
