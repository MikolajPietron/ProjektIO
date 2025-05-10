package org.example;

import javafx.fxml.FXMLLoader;  // FXMLLoader class for loading FXML files
import javafx.scene.Parent;    // Import Parent class
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class KampusUczen {

    public Scene createKampusScene(Stage stage, String login) {
        // Create a label to display the welcome message
        Label welcomeLabel = new Label("Welcome " + login + "!");

        // Create a logout button
        Button logoutButton = new Button("Wyloguj");

        // Set the logout button action to navigate back to the login screen
        logoutButton.setOnAction(e -> {
            // Load the login scene again using the primaryStage
            try {
                // Reuse the Main class to get the login scene without creating a new instance
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
                Parent root = loader.load();  // Load the FXML file into a Parent object

                // Retrieve the controller for the login screen
                LoginController controller = loader.getController();
                controller.setPrimaryStage(stage);

                // Set the scene of the stage to the login screen
                stage.setScene(new Scene(root, 600, 400)); // Adjust the scene size as needed
                stage.setFullScreen(true); // You can also set the stage fullscreen based on requirements
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Set up the layout for the Kampus screen
        GridPane kampusLayout = new GridPane();
        kampusLayout.setAlignment(Pos.CENTER);
        kampusLayout.setVgap(10);
        kampusLayout.add(welcomeLabel, 0, 0);
        kampusLayout.add(logoutButton, 0, 1);

        // Return the scene for the Kampus screen
        return new Scene(kampusLayout, 400, 300);
    }
}
