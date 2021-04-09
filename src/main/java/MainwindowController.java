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
    Label textWords;

    @FXML
    Label textLetters;

    @FXML
    Label textNumeric;

    @FXML
    Label textSpecial;


    @FXML
    private void clickOkButton(){
        TextInfo txt = new TextInfo(startText.getText());
        textSize.setText(   "Text size = " + txt.getCountSymbols() + " symbols.\n");
        textWords.setText(  "Words     = " + txt.getCountWorlds());
        textLetters.setText("Letters    = " + txt.getCountLetters());
        textNumeric.setText("Numeric  = " + txt.getCountNumeric());
        textSpecial.setText("Special symbols = " + txt.getCountSpecialSymbols());
    }

    @FXML
    private void clickCancelButton(){
        ((Stage) cancelButton.getScene().getWindow()).close();
    }

    @FXML
    public void initialize() {

    }
}

