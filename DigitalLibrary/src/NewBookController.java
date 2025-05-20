import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class NewBookController {

    @FXML private TextField titleField;
    @FXML private TextArea contentArea;
    @FXML private Label messageLabel;

    @FXML
    private void saveBook() {
        String title = titleField.getText().trim();
        String content = contentArea.getText().trim();

        if (title.isEmpty() || content.isEmpty()) {
            messageLabel.setText("Title and Content cannot be empty.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        // Clean title for filename (remove spaces and special chars)
        String fileName = title.replaceAll("[^a-zA-Z0-9]", "_") + ".csv";

        File bookFile = new File("books", fileName);
        if (bookFile.exists()) {
            messageLabel.setText("A book with this title already exists.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(bookFile))) {
            // Save content line by line
            for (String line : content.split("\n")) {
                writer.write(line);
                writer.newLine();
            }
            messageLabel.setText("Book saved successfully!");
            messageLabel.setStyle("-fx-text-fill: green;");

            // Optionally clear fields after save
            titleField.clear();
            contentArea.clear();
        } catch (IOException e) {
            messageLabel.setText("Failed to save the book.");
            messageLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void cancel() {
        // Close the window
        Stage stage = (Stage) titleField.getScene().getWindow();
        stage.close();
    }
}
