package org.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;  // FXMLLoader class for loading FXML files
import javafx.scene.Parent;    // Import Parent class
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class KampusUczen {

    @FXML private Label welcomeLabel;
    @FXML private Button logoutButton;
    private Stage stage;
    public void setWelcomeText(String login) {
        welcomeLabel.setText("Welcome " + login + "!");
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public Scene createKampusScene(Stage stage, String login) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/kampus.fxml"));
            Parent root = loader.load();

            KampusUczen controller = loader.getController(); // Use the loaded controller
            controller.setStage(stage);
            controller.setWelcomeText(login);

            stage.setFullScreen(true);
            return new Scene(root, 300, 400);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @FXML
        private void handleLogout() {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
                Parent root = loader.load();


                LoginController logincontroller = loader.getController();
                logincontroller.setPrimaryStage(stage);


                stage.setScene(new Scene(root, 600, 400));
                stage.setFullScreen(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    @FXML
    private void handleCourses(){

    }
    @FXML
    private void handleHomework(){

    }
    @FXML
    private void handleOgloszenia(){

    }




}

