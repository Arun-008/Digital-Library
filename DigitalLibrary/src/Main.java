import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static User currentUser = null;
    @Override
public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));
    primaryStage.setTitle("Digital Library Login");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
}

    public static void main(String[] args) {
        launch(args);
    }
}
