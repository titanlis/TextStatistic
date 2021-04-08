import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class MainwindowController {
    @FXML
    Button cancelButton;

    @FXML
    Button okButton;

    @FXML
    TextArea startText;

    @FXML
    Label textSize;


    @FXML
    private void clickOkButton(){
        String sText = startText.getText();
        textSize.setText( "Text size = " + sText.length() + " symbols.\n");
    }

    @FXML
    private void clickCancelButton(){
        ((Stage) cancelButton.getScene().getWindow()).close();
    }

    @FXML
    public void initialize() {

    }
}

