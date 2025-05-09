import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class KampusUczen {
    public Scene createKampusScene(Stage stage, String Login) {
        Label welcomeLabel = new Label("Welcome" + " "+  Login + "!");
        Button logoutButton = new Button("Wyloguj");
        stage.setFullScreen(true);

        logoutButton.setOnAction(e ->{
            Main loginPage = new Main();
            stage.setScene(loginPage.createLoginScene(stage));
        });
        GridPane kapusLayout = new GridPane();
        kapusLayout.setAlignment(Pos.CENTER);
        kapusLayout.setVgap(10);
        kapusLayout.add(welcomeLabel,0,0);
        kapusLayout.add(logoutButton, 0, 1);


        return new Scene(kapusLayout,400, 300);
    }
}

