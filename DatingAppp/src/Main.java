import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Login");
        primaryStage.setScene(createLoginScene(primaryStage));
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
    public Scene createLoginScene(Stage primaryStage){
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

        Label userLabel = new Label("Username: ");
        TextField userField = new TextField();
        Label passLabel = new Label("Password: ");
        PasswordField passField = new PasswordField();
        Button loginButton = new Button("Login");
        Label messageLabel = new Label();


        grid.add(userLabel, 0, 0);
        grid.add(userField, 1, 0);
        grid.add(passLabel, 0, 1);
        grid.add(passField, 1, 1);
        grid.add(loginButton, 0, 2, 2, 1); // Span across two columns
        grid.add(messageLabel, 0, 3, 2, 1);


        loginButton.setOnAction(e -> {
            String username = userField.getText();
            String password = passField.getText();

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
        });
        return new Scene(grid, 400, 300);






    }

    public static void main(String[] args) {
        launch(args);
    }
}
