import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.*;

public class DashboardController {
    @FXML private ListView<String> bookList;
    @FXML private TextArea bookContent;

    @FXML
    public void initialize() {
        for (Book b : Library.books) {
            bookList.getItems().add(b.getTitle());
        }

        bookList.setOnMouseClicked(e -> {
            String selected = bookList.getSelectionModel().getSelectedItem();
            for (Book b : Library.books) {
                if (b.getTitle().equals(selected)) {
                    readBook(b);
                    break;
                }
            }
        });
    }

    private void readBook(Book book) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(book.getPath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            content.append("Error reading file.");
        }
        bookContent.setText(content.toString());
        Library.logActivity(Main.currentUser.getUsername(), book.getTitle());
    }
}
