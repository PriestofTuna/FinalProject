package StudentPackage;

import Packets.DirectoryCreator;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Created by lytte on 7/25/2016.
 * @purpose:
 * Gets a name, string of Exercise numbers, and
 */
public class Student {
    private String name;
    private double[] Exercises;
    private double sum = 0;
    private File directory; //Directory should be changed according to directory in class DirectorySelector
    private File accessed;
    //should use this to remove values from the directory's master
    private File masterFile;
    //should be based off Absolute Path of the Copyed master in the Directory
    public Student(String name, String numbers, File directory, File masterFile) {
        this.name = name +".txt";
        try {
            double translate[] = spliter(numbers);
            //takes spliter and puts it into StudentPackage object
            this.Exercises = translate;

        } catch(InputMismatchException ex) {
            try {
                new ErrorHand(ex.getMessage());
            } catch(Exception e) {
                System.out.println("Major failure:  " + e.getStackTrace());
            }

        }catch(NumberFormatException ex) {

            try{
                new ErrorHand(ex.getMessage());
            }catch(Exception ez) {
                System.out.println("Major failure: " + ez.getStackTrace());
            }

        }
        //takes directory name, builds new directory
        this.directory = directory;
        //IMPORTANT change these to DirectoryCreator,
        this.directory.mkdir();
        this.masterFile = masterFile;
        //use this to send a message to user about whether or not it already exists.
        this.accessed = new File(this.directory, name+".txt");
        //sets directory of the new file
        double sum = this.sum;
    }

    public String getName() {
        return name;
    }
    public String getDir() {return directory.getName();}
    public String getDirPath() {return directory.getAbsolutePath();}
    public double getSum() { return sum;}
    public File getAccessed() {return accessed;}
    public double[] getExercises() {
        return Exercises;
    }
    // the above are getter methods for required fields

    private double[] spliter(String delimted) throws InputMismatchException {
        String array[] = delimted.split(",");
        double translator[] = new double[array.length];
        for(int i = 0; i < array.length; i++) {
            translator[i] = Double.parseDouble(array[i]);
            //needs to be preChecked for letters.
        }
        for(int j = 0; j < translator.length; j++) {
            for(int i = 0; i < translator.length; i++) {
                if(i == j) {
                    continue;
                }
                if((int)translator[i] == (int)translator[j]) {
                    throw new InputMismatchException("Cannot select two Exercises from the same chapter!");
                }
            }
        }
        return translator;
    }

    public void writeStudent(Student newStudent) throws IOException {
        PrintWriter output = new PrintWriter(newStudent.accessed);
        output.write("StudentPackage: " + newStudent.getName() + "\n");
        output.write("In Directory: " + newStudent.directory.getName());
        try {
            for (int i = 0; i < newStudent.getExercises().length; i++) {
                output.write("\n" + newStudent.getExercises()[i]);
                //writes out the Exercises plus name and Dir to File accessed
            }
        }catch(NullPointerException e) {

        }
        output.close();
    }
    public void checkStudentFile() throws IOException {
        //the below code takes the files and puts them into ArrayLists
        ArrayList<Double> masterList =  new ArrayList<>();
        ArrayList<Integer> scoreList = new ArrayList<>();
        ArrayList<Double> accessorList = new ArrayList<>();
        if(!masterFile.exists()) {
            throw new IOException("Failure in MasterPacket, not recognised as file in Packet");
        }
        BufferedReader master = new BufferedReader(new FileReader(masterFile));
        BufferedReader accessor = new BufferedReader(new FileReader(accessed));

        while (true){ //change later
            String ooD = master.readLine();
            if(ooD == null) {
                break;
                //breaks if null
            } else if(ooD.isEmpty()) {
                continue;
            }
            String ooDArray[] = ooD.split("->");
            for(int i = 0; i < ooDArray.length; i+=2) {
                masterList.add(Double.parseDouble(ooDArray[i]));
                //uses i to traverse ooDArray
                    if(i == 0) {
                        scoreList.add(Integer.parseInt(ooDArray[i+1]));
                    }
                //takes the values from master to compare
            }
        }
        master.close();
        String nameAndDir[] = new String[2];
        nameAndDir[0] = accessor.readLine();
        nameAndDir[1] = accessor.readLine();
        while (true) {
            String ooD =(accessor.readLine());
            if(ooD == null) {
                break;
            } else if(ooD.isEmpty()) {
                continue;
            }
            double interPlay = Double.parseDouble(ooD);
            accessorList.add(interPlay);
        }
        accessor.close();
        //the above code takes the files, put them into ArrayLists

        for(int i = 0; i < accessorList.size(); i++) {
            if (!masterList.contains(accessorList.get(i))) {
                throw new IOException(accessorList.get(i) + "Not an Exercise listed in MasterPack");
                //if accessorList has a number that isn't in the master, throw Exception
            }
        }
        for(int i = 0; i < accessorList.size(); i++) {
            sum += scoreList.get(masterList.indexOf(accessorList.get(i)));
            //System.out.println(scoreList.get(masterList.indexOf(accessorList.get(i))));
        }
        sum /= accessorList.size();
        //takes the value of sum,
    }
}
