import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainwindowController {
    @FXML
    Button cancelButton;

    @FXML
    Button okButton;

    @FXML
    private void clickOkButton(){

    }

    @FXML
    private void clickCancelButton(){
        ((Stage) cancelButton.getScene().getWindow()).close();
    }
}
