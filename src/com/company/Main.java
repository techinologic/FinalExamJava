package com.company;

/**
 * Created by Paolo T. inocencion on 12/20/2015.
 * Sources:
 * Introduction to Java Programming, Comprehensive Ed. - Daniel Liang - Chapter 15
 * http://stackoverflow.com/questions/15633228/how-to-remove-all-white-spaces-in-java
 * http://code.makery.ch/blog/javafx-dialogs-official/
 * http://stackoverflow.com/questions/15159988/javafx-2-2-textfield-maxlength
 */

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    private TextField tfStudentName = new TextField();
    private TextField tfDate = new TextField();
    private TextField tfAverageHoursPerWeekCoding = new TextField();
    private TextField tfExpectedGrade = new TextField();
    private Button btnSubmit = new Button("Submit");

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create UI
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.add(new Label("Student Name:"), 0, 0);
        gridPane.add(tfStudentName, 1, 0);

        gridPane.add(new Label("Date (DDMmmYYYY)"), 0, 1);
        gridPane.add(tfDate, 1, 1);

        gridPane.add(new Label("Ave. Hours/Week Coding:"), 0, 2);
        gridPane.add(tfAverageHoursPerWeekCoding, 1, 2);

        gridPane.add(new Label("Expected Grade:"), 0, 3);
        gridPane.add(tfExpectedGrade, 1, 3);

        gridPane.add(btnSubmit, 1, 5);

        // Set properties for UI
        gridPane.setAlignment(Pos.CENTER);
        tfStudentName.setAlignment(Pos.BOTTOM_RIGHT);
        tfDate.setAlignment(Pos.BOTTOM_RIGHT);
        tfAverageHoursPerWeekCoding.setAlignment(Pos.BOTTOM_RIGHT);
        tfExpectedGrade.setAlignment(Pos.BOTTOM_RIGHT);
        GridPane.setHalignment(btnSubmit, HPos.RIGHT);

        // Process events
        btnSubmit.setOnAction(e -> submitInformation());
        // Create a scene and place it in the stage
        Scene scene = new Scene(gridPane, 400, 250);
        primaryStage.setTitle("Welcome to CSCI 398 Final!"); // Set title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        // Limits textfield tfStudentName to 30 characters only.
        // Won't allow user to input more than 30 chars.
        addTextLimiter(tfStudentName, 30);

        // Limits textfield tfDate to 20 chars only.
        // Won't allow user to input more than 20 chars.
        addTextLimiter(tfDate, 20);

        // Limits textfield tfAverageHoursPerWeekCoding to 3 chars only.
        // There are only 168 hours in a week. So MAX of 3 Hours only.
        addTextLimiter(tfAverageHoursPerWeekCoding, 3);

        // Limits textfield tfExpectedGrade to 1 char only.
        // Won't allow user to input more than 1 char1. Self Explanatory.
        addTextLimiter(tfExpectedGrade, 1);
    }

    private void submitInformation() {
        // Get values from text fields

        String checkHours = (tfAverageHoursPerWeekCoding.getText());
        String checkGrade = (tfExpectedGrade.getText());

        // Display alert if input is not a positive integer.
        if((!checkHours.matches("(.*)[0-9](.*)")) || (checkHours.matches("(.*)-(.*)"))){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText("Hours can only contain positive integers");
            alert.showAndWait();

        }
        if(checkGrade.matches("(.*)[0-9](.*)")){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText("Expected Grade can only be A, B, C, D, and F.");
            alert.showAndWait();
        }



        else {

            String studentName = (tfStudentName.getText());
            String date = (tfDate.getText());
            int hoursCoding = Integer.parseInt(tfAverageHoursPerWeekCoding.getText());

            char grade = (tfExpectedGrade.getText()).charAt(0);

            // Create student object.
            Student student = new Student(studentName, date, hoursCoding, grade);

            // Write data to file
            student.writeToFile();
        }
    }

    //Limits character entry to all textfields.
    public static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov,
                                final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
            }
        });
    }


}