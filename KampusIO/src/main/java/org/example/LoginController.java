package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class LoginController {

    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;

    private Stage primaryStage;

    // Called by Main to set the stage reference
    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }


    @FXML
    public void handleLogin() {
        String username = usernameTextField.getText();
        String password = passwordField.getText();

        try {
            String postData = "login=" + URLEncoder.encode(username, "UTF-8")
                    + "&haslo=" + URLEncoder.encode(password, "UTF-8");

            URL url = new URL("http://localhost/login.php");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postData.length()));

            try (OutputStream os = conn.getOutputStream()) {
                os.write(postData.getBytes());
                os.flush();
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response = in.readLine();
            in.close();

            if ("success".equals(response)) {
                KampusUczen kampusUczen = new KampusUczen();
                Scene kampusScene = kampusUczen.createKampusScene(primaryStage, username);
                primaryStage.setScene(kampusScene);
                primaryStage.setFullScreen(true);
            } else {
                messageLabel.setText("Login failed");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            messageLabel.setText("Error: " + ex.getMessage());
        }
    }
}
