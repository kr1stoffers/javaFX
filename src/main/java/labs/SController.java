package labs;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class SController implements Initializable {
    @FXML
    private Button button1;
    @FXML
    private TextArea textArea;
    @FXML
    private CheckBox cbox;
    @FXML
    private ListView<String> listView;
    // @FXML
    // RadioButton rButton;

    String[] it = { "razdva", "t2x2", "mzlff", "brff", "lagoda" };

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        listView.getItems().addAll(it);
        cbox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                textArea.appendText("HelloWorld!!");
            }
        });
    }

    @FXML
    private void handleRadioButton(ActionEvent event) {
        RadioButton rButton = (RadioButton) event.getSource();
        rButton.getScene().getStylesheets().clear();
        rButton.getScene().getRoot().getStylesheets().clear();
        switch (rButton.getText()) {
            case "Base Style":
                rButton.getScene().getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
                break;

            case "New Style":
                rButton.getScene().getStylesheets().add(getClass().getResource("newStyle.css").toExternalForm());
                break;
        }
    }

    @FXML
    private void handleButtonOnPress(ActionEvent event) {
        System.out.println("Hello");
    }

    @FXML
    private void handle(MouseEvent event) {
        textArea.appendText("Hello!!");
    }
}
