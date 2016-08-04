package sample;
import Packets.DirectoryCreator;
import StudentPackage.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    static DirectoryCreator director;
    @FXML TextField getDirName;
    public void newStudentCreate(ActionEvent actionEvent) {
        try {
            Stage window = Main.getStage();
            Parent root = FXMLLoader.load(getClass().getResource("../view/StudentCreator.fxml"));
            window.setTitle("Student Creator");
            window.setScene(new Scene(root, 500, 475));
            window.show();
        }catch(IOException ex) {
            System.out.println("Fatal error: " + ex.getStackTrace());
        }
    }

    public void CreateDirCheck(ActionEvent actionEvent) {
        if(getDirName.getText().isEmpty()) {

        } else {
            String directoryName = getDirName.getText();
            try{
                DirectoryCreator director = new DirectoryCreator(directoryName);
                setDirector(director);
            }catch(IOException ex) {

            }
        }
    }
    public static DirectoryCreator getDirector() {
        return director;
    }
    public void setDirector(DirectoryCreator director) {
        this.director = director;
    }
}