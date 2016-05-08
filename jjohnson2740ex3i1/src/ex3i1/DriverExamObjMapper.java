package ex3i1;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.DefaultListModel;

public class DriverExamObjMapper {
	// create fields
	private String fileName;
	private Scanner inputFile;
	private File file;
	
	//Constructor that receives the name of the file
	public DriverExamObjMapper(String fileName) {
		super();
		this.fileName = fileName;
	}
	
	public boolean openInputFile(){
		boolean fileOpened = false;
		
		// Open the file. Partial copy from project 3D.
	    try {
	    	//create new File object from field name using name from constructor.
	    	File file = new File(this.fileName);
	    	// checks to see if file exists, if true sets fileOpened to true.
			fileOpened = file.exists();
			// if fileOpened = true.
			if (fileOpened) {
				// Create new Scanner object from filed name and names it inputFile 
			    this.inputFile = new Scanner(file);
			}
	    }
	    catch (IOException e) {}
		
		
		return fileOpened;
	}
	
	public void closeInputFile(){
		//first checks to see if file is open, if true, closes it.
		if (this.inputFile != null) this.inputFile.close();
	}
	
	// input file is open at this point, now going to read the file and create a DriverExam object.
	
	/*Example of a method that passes an object reference. Has return type of DefaultListModel.
	 * Creates a new DefaultListodel Object and assigns it the reference variable of DriverExamCollection.
	 * it then returns DriverExamCollection. */ 
	public DriverExam getDriverExam(){
		//create variable for DriverExam variable
		DriverExam exam = null;
		//create default list model
		DefaultListModel driverExamCollection = new DefaultListModel();
		// try to open the input file, if it opens proceed to loop.
		if (this.openInputFile()){
			// set loop to check if there is more info (hasNext checks for next line/row), if so then proceed.
			while (this.inputFile.hasNext()){
				// take the line from the file and put it in the answer string.
				String answer = inputFile.nextLine();
				// take the answer string and create a new element for the driversExamCollection.
				driverExamCollection.addElement(answer);
			}// send collection to the DriverExam Constructor, and assign that to exam. This now contains the answer array.
			exam = new DriverExam(driverExamCollection);
		}
		//close the file
		this.closeInputFile();
		//return the whole collection
		return exam;
	}
	
}
