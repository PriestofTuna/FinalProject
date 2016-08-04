package Packets;
import StudentPackage.ErrorHand;

import java.io.*;
/**
 * Created by lytte on 8/2/2016.
 */
public class DirectoryCreator {
    File directoryFile;
    File baseMaster = new File("C:\\Users\\lytte\\IdeaProjects\\FinalProject\\src\\Packets\\MasterPack.txt");
    //pulls from base MasterPack.txt
    File newMaster;
    //creates the new Master file for the directory
    public DirectoryCreator(String directoryName) throws IOException{
        directoryFile = new File(directoryName);
        directoryFile.mkdir();
        newMaster = new File(directoryFile, "NewMaster.txt");
        PrintWriter output = new PrintWriter(newMaster);
        BufferedReader reader = new BufferedReader(new FileReader(baseMaster));
        while(true) {
            String ooD = reader.readLine();
            //reads a line from MasterPack.txt
            if(ooD == null ) {
                break;
                //creates a new line if Empty
            }else if(ooD.isEmpty()) {
                //if its null, break out of the loop.
                output.write("\n");
            }
            output.write(ooD + "\n");
            //writes out the next line of masterfile if ooD is equal to a value other than null.
        }
        output.close();
        reader.close();
    }
    public DirectoryCreator(String directoryName, boolean changer) throws IOException {
        directoryFile = new File(directoryName);
        if(!directoryFile.exists()) {
            directoryFile.delete();
            new ErrorHand("Directory doesn't Exist!");
        } else {
            setDirectoryFile(directoryFile);
            newMaster = new File(directoryFile, "NewMaster.txt");
        }

    }
    public File getNewMaster() {
        return newMaster;
    }
    public void setDirectoryFile(File director) {
        this.directoryFile = director;
    }
    public File getDirectoryFile() {
        return directoryFile;
    }
}