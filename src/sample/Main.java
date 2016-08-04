package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    static Stage window;
    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../view/MMenu.fxml"));
        window.setTitle("Exercise packet Creator");
        window.setScene(new Scene(root,500, 475));
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    public static Stage getStage() {
        return window;
    }
}
