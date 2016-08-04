package sample;

import Packets.DirectoryCreator;
import StudentPackage.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * Created by lytte on 8/2/2016.
 */
public class StudentCreatorController {
    DirectoryCreator directoryCreator;
    @FXML TextField inputStudentName;
    @FXML TextField inputStudentDirectory;
    @FXML TextArea inputOfExercises;
    @FXML Label sumLbl;
    @FXML Label nameLbl;
    public void CreateNewStudent(ActionEvent actionEvent) {
        directoryCreator = Controller.getDirector();
        boolean sustain = true;
        boolean successDecider = true;
        if(inputStudentName.getText().isEmpty() || inputStudentDirectory.getText().isEmpty() || inputOfExercises.getText().isEmpty() ||
                inputStudentName.getText()==null || inputStudentDirectory.getText()==null || inputOfExercises.getText()==null) {
            successDecider = false;
            try {
                new StudentPackage.ErrorHand("A TextField is empty");
            }catch(IOException e) {
                System.out.println("Major failure");
            }
            }
            if(Controller.getDirector() == null) {
                try {
                    directoryCreator = new DirectoryCreator(inputStudentDirectory.getText(), true);
                    }catch(IOException ex) {

                    }
            }
            while(sustain) {
                String name = inputStudentName.getText();
                if (name.equals("newMaster.txt")) {
                    nameLbl.setText("Cannot override the Directory's master file!");
                }
                String exercises = inputOfExercises.getText();
                File directory = directoryCreator.getDirectoryFile();
                File newMaster = directoryCreator.getNewMaster();
                if (name == null | exercises == null | directory == null | newMaster == null) {
                    sustain = false;
                }
                Student student = new Student(name, exercises, directory, newMaster);
                //Student student = new Student();
                try {
                    student.writeStudent(student);
                    student.checkStudentFile();
//                    if (student.getSum() < 2.4) {
//                        System.out.println(student.getSum());
//                        sumLbl.setText("Combined difficulties is less then 2.4! current sum: " + student.getSum());
//                        student.getAccessed().delete();
//                    }
                    sumLbl.setText("Current Sum " + student.getSum());
                    if (student.getSum() < 2.4) {
                        File temp = student.getAccessed();
                        temp.delete();
                        sustain = false;
                    }
                    if (student.getAccessed().exists()) {
                        sustain = false;
                    }
                } catch (IOException e) {

                }
                if(student.getSum() > 2.4 && successDecider == true) {
                    try {
                        Stage window = Main.getStage();
                        Parent root = FXMLLoader.load(getClass().getResource("../view/MMenu.fxml"));
                        window.setTitle("Success!");
                        window.setScene(new Scene(root, 500, 475));
                        window.show();
                    } catch (IOException e) {

                    }
                }
            }
            }
}
