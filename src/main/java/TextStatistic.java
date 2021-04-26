/**@file TextStatistic.java main program file. Entry point.*/
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/** @mainpage TextStatistic
  * Simple text statistics.
  * <p><img src = "mainwindow.png"></p>
  */


/**@class TextStatistic JavaFX Application.*/
public class TextStatistic extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainwindow.fxml"));
        primaryStage.setTitle("Text analysis");
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);

        Scene sc = new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.show();
    }
}
