package ex3i1;

import javax.swing.DefaultListModel;

/*
Driver Exam Class model:
-answers : char[]
-responses : char[]
-requiredPct : double = 0.7
---------------------------
+DriverExam(responses : char[])
+DriverExam(responses : DefaultListModel)
+setResponses(responses : DefaultListModel)
+getAnswers() : DefaultListModel
+validate() : int
+passed() : boolean
+totalCorrect() : int
+totalIncorrect() : int
+questionsMissed() : int[]
 */

public class DriverExam
{
	private char[] answers;
	private char[] responses;
	//private char[] responses = {'B', 'D', 'Z', 'Z', 'C', 'A', 'B', 'A', 'C', 'D'};
	private final double requiredPct = 0.7;

//	public DriverExam(char[] answers) {
//		this.answers = new char[answers.length];
//		for(int i = 0; i < answers.length; i++) {
//			this.answers[i] = answers[i];
//	    }
//	}
	
	//Asks the size of the list model, then creates an array of that same size. It converts the string into an array.
		public DriverExam(DefaultListModel answers) {
		 	this.answers = new char[answers.getSize()];
			for (int i = 0; i < answers.getSize(); i++) {
				String r = String.valueOf(answers.get(i));
				this.answers[i] = r.charAt(0);
			}
		}
	
	//Asks the size of the list model, then creates an array of that same size. It converts the string into an array.
	public void setResponses(DefaultListModel responses) {
	 	this.responses = new char[responses.getSize()];
		for (int i = 0; i < responses.getSize(); i++) {
			String r = String.valueOf(responses.get(i));
			this.responses[i] = r.charAt(0);
		}
	}
	
//	public DriverExam(char[] answers) {
//		this.answers = new char[answers.length];
//		for(int i = 0; i < answers.length; i++) {
//			this.answers[i] = answers[i];
//	    }
//	}
	
	public int validate() {
		int i = 0;
		int error = -1;
		while (i < 10) {
			if (this.responses[i] != 'A' && this.responses[i] != 'B' && this.responses[i] != 'C' && this.responses[i] != 'D'){
				error = i;
			}i++;
		} return error;
	}


	public boolean passed()
	{
		if(totalCorrect() >= (requiredPct * answers.length)){
			return true;
		} else {
			return false;
		}
		
	}

	public int totalCorrect()
	{
		// count up how many right answers you have.
		int count = 0;
		for (int i = 0; i < answers.length; i++){
			if(this.responses[i] == answers[i]){
				count++;
			}
		}return count;
	}

	public int totalIncorrect()
	{
		// count up how many right answers you have.
		int count = 0;
		for (int i = 0; i < responses.length; i++){
			if(answers[i] != responses[i]){
				count++;
			}
		}return count;
	}

	public int[] questionsMissed()
	{
		int[] missed = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int m = 0;
	    for (int i = 0; i < answers.length; i++)
	    {
	    	if(answers[i] != responses[i]){
	    		missed[m] += i + 1;
	    		m++;
	    	}
	    }
	    //txtResult.setText(result);
		return missed; 
	}

	public DefaultListModel getAnswers() {
		DefaultListModel answersListModel = new DefaultListModel();
		// loop that grabs each character from the answers array.
		for(int i =0; i < answers.length; i++){
			String a = String.valueOf(answers[i]);
			answersListModel.addElement(a);
		}
		return answersListModel; 
	}
}
