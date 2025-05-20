import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    @FXML
    public void initialize() {
        Library.loadUsers();
        Library.loadBooks();
    }

    @FXML
    public void login(ActionEvent event) throws IOException {
        String user = usernameField.getText();
        String pass = passwordField.getText();
        for (User u : Library.users) {
            if (u.getUsername().equals(user) && u.getPassword().equals(pass)) {
                Main.currentUser = u;
                u.setLastLoginTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                if (u.getRole().equals("admin")) {
                    changeScene("fxml/admin.fxml");
                } else {
                    changeScene("fxml/dashboard.fxml");
                }
                return;
            }
        }
        errorLabel.setText("Invalid login!");
    }

    @FXML
    public void goToRegister(ActionEvent event) throws IOException {
        changeScene("fxml/register.fxml");
    }

    private void changeScene(String fxml) throws IOException {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource(fxml)));
        stage.setScene(scene);
    }
}
