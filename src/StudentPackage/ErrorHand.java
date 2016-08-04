package StudentPackage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Main;
import java.io.IOException;

/**
 * Created by lytte on 8/1/2016.
 */
public class ErrorHand {
    private static String message;
    Stage window = Main.getStage();

    public ErrorHand(String message) throws IOException {
        this.message = message;
        //calls the error message
        Parent root = FXMLLoader.load(getClass().getResource("../view/Failure.fxml"));
        window.setTitle(message);
        window.setScene(new Scene(root, 300, 275));
        window.show();

    }
    public static String getMessage() {
        return message;
    }
    public static void setMessage(String message) {
        ErrorHand.message = message;
    }
}




