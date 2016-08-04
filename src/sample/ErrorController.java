package sample;

import StudentPackage.ErrorHand;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by lytte on 8/1/2016.
 */
public class ErrorController {
//needs to take in the TextFields, and change them according to the below String
    Stage window = Main.getStage();
    @FXML Label errorMsg = new Label(ErrorHand.getMessage().toString());
    public void retry(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/MMenu.fxml"));
        window.setTitle("Exercise packet Creator");
        window.setScene(new Scene(root, 300, 275));
        window.show();
    }

}
