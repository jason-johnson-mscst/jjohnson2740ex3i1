package ex3i1;

import javax.swing.DefaultListModel;

public class Main {

	public static void main(String[] args) {
		// create arrays:
		// char[] answers = {'B', 'D', 'A', 'A', 'C', 'A', 'B', 'A', 'C', 'D'};
		
		
		// create new DriversExam object and pass answers char array to constructor;
		
		
//		//Create default list model
//		DefaultListModel answersListModel = new DefaultListModel();
//		answersListModel.addElement('B');
//		answersListModel.addElement('D');
//		answersListModel.addElement('A');
//		answersListModel.addElement('A');
//		answersListModel.addElement('C');
//		answersListModel.addElement('A');
//		answersListModel.addElement('B');
//		answersListModel.addElement('A');
//		answersListModel.addElement('C');
//		answersListModel.addElement('D');
//		DriverExam exam = new DriverExam(answersListModel);
		
		//create new objectMapper and pass the constructor the file name of the text file.
		DriverExamObjMapper mapper = new DriverExamObjMapper("DriverExam.txt"); 
		/* In mapper, we call the getDriver Exam function. The getDriverExam function 
		 * opens the file, reads line by line, and adds elements to the collection and returns 
		 * the ListModel here. */
		DriverExam exam = mapper.getDriverExam();
		
		//Create default list model
		DefaultListModel responsesListModel = new DefaultListModel();
		responsesListModel.addElement('B');
		responsesListModel.addElement('D');
		responsesListModel.addElement('A');
		responsesListModel.addElement('A');
		responsesListModel.addElement('C');
		responsesListModel.addElement('A');
		responsesListModel.addElement('B');
		responsesListModel.addElement('A');
		responsesListModel.addElement('C');
		responsesListModel.addElement('D');
		//pass to the setResponses method.
		exam.setResponses(responsesListModel);
		
		System.out.println("First invalid response: " + exam.validate());
		System.out.println("Correct " + exam.totalCorrect());
		System.out.println("Incorrect " + exam.totalIncorrect());

		if (exam.passed()){
			System.out.println("Passed test");
		} else {
			System.out.println("Try again");
		}

		System.out.print("Questions missed:");
		int [] missed = exam.questionsMissed();
		int i = 0;
		while (i < missed.length && missed[i] > 0) {
			System.out.print(" " + missed[i]);
			i++;
		}
		return;
	}
}
