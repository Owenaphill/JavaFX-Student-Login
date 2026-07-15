package com.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {

            /**
             * Sets up the window for the project
             */
            BorderPane root = new BorderPane();
            primaryStage.setTitle("Student Information");
            VBox vbox = new VBox();
            vbox.setPadding(new Insets(12, 12, 12, 12));
            vbox.setSpacing(1);
            vbox.setStyle("-fx-background-color: #135b2b");

            /**
             * Adds the text and textfield for the student to enter their full name
             */
            HBox nameHbox = new HBox();
            nameHbox.setPadding(new Insets(12, 12, 12, 12));
            nameHbox.setSpacing(5);

            Text name = new Text("Student Full Name:");
            TextField nameText = new TextField();
            name.setFont(Font.font("Verdana", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 18));
            nameText.setPromptText("Insert Full Name");
            nameHbox.getChildren().addAll(name, nameText);
            nameHbox.setAlignment(Pos.CENTER_LEFT);
            vbox.getChildren().add(nameHbox);

            /**
             * Adds the text and textfield for the student to enter their email address
             */
            HBox emailHbox = new HBox();
            emailHbox.setPadding(new Insets(12, 12, 12, 12));
            emailHbox.setSpacing(5);

            Text email = new Text("Student Email Address:");
            TextField emailText = new TextField();
            email.setFont(Font.font("Verdana", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 18));
            emailText.setPromptText("Insert Email Address");
            emailHbox.getChildren().addAll(email, emailText);
            emailHbox.setAlignment(Pos.CENTER_LEFT);
            vbox.getChildren().add(emailHbox);

            /**
             * Adds the text and combobox for the student to pick their nationality from a drop down menu
             */
            HBox nationHBox = new HBox();
            nationHBox.setPadding(new Insets(12, 12, 12, 12));
            nationHBox.setSpacing(5);

            Text nation = new Text("Student Nationality:");
            ComboBox<String> nationBox = new ComboBox();
            nation.setFont(Font.font("Verdana", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 18));
            loadItemsFromFile(nationBox);
            nationBox.setPromptText("United States");
            nationBox.setValue("United States");
            nationHBox.getChildren().addAll(nation, nationBox);
            nationHBox.setAlignment(Pos.CENTER_LEFT);
            vbox.getChildren().add(nationHBox);

            /**
             * Adds the text radio buttons to pick if the student is an undergraduate or a graduate
             */
            HBox gradBox = new HBox();
            gradBox.setPadding(new Insets(12, 12, 12, 12));
            gradBox.setSpacing(5);

            ToggleGroup toggleGroup = new ToggleGroup();
            RadioButton undergrad = new RadioButton("Undergraduate");
            RadioButton grad = new RadioButton("Graduate");
            undergrad.setFont(Font.font("Verdana", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 18));
            undergrad.setTextFill(Color.BLACK);
            grad.setFont(Font.font("Verdana", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 18));
            grad.setTextFill(Color.BLACK);
            undergrad.setToggleGroup(toggleGroup);
            grad.setToggleGroup(toggleGroup);
            toggleGroup.selectToggle(undergrad);
            undergrad.setSelected(true);
            gradBox.getChildren().addAll(undergrad, grad);
            gradBox.setAlignment(Pos.CENTER_LEFT);
            vbox.getChildren().add(gradBox);

            /**
             * Adds the text and checkbox for the student to check if they are a transfer student or not
             */
            HBox transferBox = new HBox();
            transferBox.setPadding(new Insets(12, 12, 12, 12));
            transferBox.setSpacing(5);

            CheckBox transferCheck = new CheckBox("Transfer Student");
            transferCheck.setFont(Font.font("Verdana", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 18));
            transferCheck.setTextFill(Color.BLACK);
            transferCheck.setSelected(false);
            transferBox.getChildren().add(transferCheck);
            transferBox.setAlignment(Pos.CENTER_LEFT);
            vbox.getChildren().add(transferBox);

            /**
             * Adds the buttons to either reset or save the program
             */
            HBox endHBox = new HBox();
            endHBox.setPadding(new Insets(12, 12, 12, 12));
            endHBox.setSpacing(5);

            Button saveButton = new Button("Save");
            Button resetButton = new Button("Reset");
            saveButton.setFont(Font.font("Verdana", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 18));
            resetButton.setFont(Font.font("Verdana", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 18));
            saveButton.setTextFill(Color.BLACK);
            resetButton.setTextFill(Color.BLACK);
            endHBox.getChildren().addAll(resetButton, saveButton);
            endHBox.setAlignment(Pos.CENTER_LEFT);
            vbox.getChildren().add(endHBox);

            root.setCenter(vbox);

            /**
             * function to save the data chosen once the save button is pressed
             */
            saveButton.setOnAction(e -> {
                try {
                    int transferInt;
                    String gradOrUndergrad;
                    if (transferCheck.isSelected()) {
                        transferInt = 1;
                    } else {
                        transferInt = 0;
                    }
                    if (undergrad.isSelected()) {
                        gradOrUndergrad = "Undergraduate";
                    } else {
                        gradOrUndergrad = "Graduate";
                    }
                    saveDataFileWriter(nameText.getText(), emailText.getText(), nationBox.getValue(),
                            gradOrUndergrad, transferInt);

                    nameText.clear();
                    emailText.clear();
                    nationBox.setValue("United States");
                    undergrad.setSelected(true);
                    transferCheck.setSelected(false);

                    Alert savedAlert = new Alert(Alert.AlertType.INFORMATION);
                    savedAlert.setTitle("Data Saved");
                    savedAlert.setHeaderText("Data has been saved");
                    Optional<ButtonType> result = savedAlert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        savedAlert.hide();
                        savedAlert.close();
                    }
                } catch (IOException ex) {
                    System.getLogger(App.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            });

            /**
             * function to clear and reset all data fields once the save button is pressed
             */
            resetButton.setOnAction(e -> {
                nameText.clear();
                emailText.clear();
                nationBox.setValue("United States");
                undergrad.setSelected(true);
                transferCheck.setSelected(false);

                Alert savedAlert = new Alert(Alert.AlertType.INFORMATION);
                savedAlert.setTitle("Data Reset");
                savedAlert.setHeaderText("Data has been reset");
                Optional<ButtonType> result = savedAlert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    savedAlert.hide();
                    savedAlert.close();
                }
            });

            Scene scene = new Scene(root, 500, 350);
            // scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads items from a given file into a combobox
     * @param comboBox adds every item in the file to the selected combobox
     */
    public void loadItemsFromFile(ComboBox<String> comboBox) {
        try {
            File file = new File("nations-1.txt");
            Scanner scnr = new Scanner(file);
            while (scnr.hasNextLine()) {
                comboBox.getItems().add(scnr.nextLine());
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Saves the data given to a file called records.txt
     * @param name The students full name
     * @param email The students email
     * @param nationality The students nationality
     * @param graduate Undergraduate or graduate
     * @param transferred Transferred or not
     * @throws IOException Throws exception because its FileWriter
     */
    public void saveDataFileWriter(String name, String email, String nationality, String graduate, int transferred)
            throws IOException {
        File file = new File("records.txt");
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(name + "\n");
        bw.write(email + "\n");
        bw.write(nationality + "\n");
        bw.write(graduate + "\n");
        bw.write(transferred + "\n");
        bw.close();
    }

    public static void main(String[] args) {
        launch(args);
    }

}