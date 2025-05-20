import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class AdminController {
    @FXML private TextArea logArea;

    @FXML
    public void initialize() {
        try (BufferedReader br = new BufferedReader(new FileReader("log.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                logArea.appendText(line + "\n");
            }
        } catch (IOException e) {
            logArea.setText("No logs found.");
        }
    }
    @FXML
public void openNewBookForm() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/newbook.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Add New Book");
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
        // You can also show an alert dialog here for better UX
    }
}

}

