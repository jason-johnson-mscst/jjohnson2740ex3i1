package ex3i1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ExamForm extends JFrame {

	private JPanel contentPane;
	private JList responsesList;
	private DefaultListModel responsesListModel;
	private JLabel resultLabel;
	private JLabel questNumLabel;
	private JTextField inputAnswerTextField;
	private JButton prevButton;
	private DriverExam exam;
	private JButton nextButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExamForm frame = new ExamForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ExamForm() {
		setTitle("JJohnson 2740 Exercise 3i1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 317, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblResponses = new JLabel("Responses:");
		lblResponses.setBounds(10, 11, 71, 14);
		contentPane.add(lblResponses);
		
		JLabel lblResult = new JLabel("Result:");
		lblResult.setBounds(123, 11, 46, 14);
		contentPane.add(lblResult);
		
		JList list = new JList();
		list.setBackground(SystemColor.control);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setEnabled(false);
		list.setBounds(20, 39, 30, 187);
		contentPane.add(list);
		
		responsesList = new JList();
		responsesList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				do_responsesList_valueChanged(arg0);
			}
		});
		responsesList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		responsesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		responsesList.setBounds(60, 36, 30, 187);
		contentPane.add(responsesList);
		
		resultLabel = new JLabel("");
		resultLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		resultLabel.setBounds(123, 33, 150, 23);
		contentPane.add(resultLabel);
		
		JButton calcPassButton = new JButton("Pass");
		calcPassButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_calcPassButton_actionPerformed(e);
			}
		});
		calcPassButton.setBounds(123, 67, 95, 23);
		contentPane.add(calcPassButton);
		
		JButton calcCorrectButton = new JButton("Correct");
		calcCorrectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_calcCorrectButton_actionPerformed(e);
			}
		});
		calcCorrectButton.setBounds(123, 101, 95, 23);
		contentPane.add(calcCorrectButton);
		
		JButton calcIncorrectButton = new JButton("Incorrect");
		calcIncorrectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnIncorrect_actionPerformed(e);
			}
		});
		calcIncorrectButton.setBounds(123, 135, 95, 23);
		contentPane.add(calcIncorrectButton);
		
		JButton calcListIncorrectButton = new JButton("List Incorrect");
		calcListIncorrectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_listIncorrectButton_actionPerformed(e);
			}
		});
		calcListIncorrectButton.setBounds(123, 169, 95, 23);
		contentPane.add(calcListIncorrectButton);
		
		questNumLabel = new JLabel("#0:");
		questNumLabel.setBounds(10, 234, 30, 14);
		contentPane.add(questNumLabel);
		
		inputAnswerTextField = new JTextField();
		inputAnswerTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				do_inputAnswerTextField_focusGained(arg0);
			}
		});
		inputAnswerTextField.setBounds(60, 231, 30, 20);
		contentPane.add(inputAnswerTextField);
		inputAnswerTextField.setColumns(10);
		
		prevButton = new JButton("Previous");
		prevButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_prevButton_actionPerformed(e);
			}
		});
		prevButton.setEnabled(false);
		prevButton.setBounds(100, 230, 73, 23);
		contentPane.add(prevButton);
		
		nextButton = new JButton("Next");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_nextButton_actionPerformed(e);
			}
		});
		nextButton.setBounds(100, 261, 71, 23);
		contentPane.add(nextButton);
		
		//create new objectMapper and pass the constructor the file name of the text file.
		DriverExamObjMapper mapper = new DriverExamObjMapper("DriverExam.txt"); 
		/* In mapper, we call the getDriver Exam function. The getDriverExam function 
		 * opens the file, reads line by line, and adds elements to the collection and returns 
		 * the ListModel here. */
		this.exam = mapper.getDriverExam();
		//display sample responses/answers as default for testing:
		this.responsesListModel = exam.getAnswers();
		//takes answers above and displays in JList.
		responsesList.setModel(this.responsesListModel);
	}
	
	protected void do_calcPassButton_actionPerformed(ActionEvent e) {
		this.exam.setResponses((DefaultListModel) responsesList.getModel());
		int invalid = this.exam.validate();
		if (invalid >= 0){
			resultLabel.setText("Invalid response #" + Integer.toString(invalid+1));
			responsesList.setSelectedIndex(invalid);
			
		} else {
			if (exam.passed()) resultLabel.setText("You Passed");
			else resultLabel.setText("You failed");
		}
	}
	
	protected void do_calcCorrectButton_actionPerformed(ActionEvent e) {
		this.exam.setResponses((DefaultListModel) responsesList.getModel());
		int invalid = this.exam.validate();
		if (invalid >= 0){
			resultLabel.setText("Invalid response #" + Integer.toString(invalid+1));
			responsesList.setSelectedIndex(invalid);
			
		} else {
			resultLabel.setText("You have " + exam.totalCorrect() + " correct.");
		}
	} 
	
	
	protected void do_btnIncorrect_actionPerformed(ActionEvent e) {
		this.exam.setResponses((DefaultListModel) responsesList.getModel());
		int invalid = this.exam.validate();
		if (invalid >= 0){
			resultLabel.setText("Invalid response #" + Integer.toString(invalid+1));
			responsesList.setSelectedIndex(invalid);
			
		} else {
			resultLabel.setText("You have " + exam.totalIncorrect() + " incorrect.");
		}	
	}
	
	protected void do_listIncorrectButton_actionPerformed(ActionEvent e) {
		this.exam.setResponses((DefaultListModel) responsesList.getModel());
		int invalid = this.exam.validate();
		int [] missed = this.exam.questionsMissed();
		int i = 0;
		String msg = "";
		if (invalid >= 0){
			resultLabel.setText("Invalid response #" + Integer.toString(invalid+1));
			responsesList.setSelectedIndex(invalid);
			
		} else {
			while (i < missed.length && missed[i] > 0) {
			msg += " " + missed[i];
			i++;
			} resultLabel.setText("Missed item(s): " + msg);
		}
	}	
	
	protected void do_prevButton_actionPerformed(ActionEvent e) {
		this.responsesListModel.setElementAt(
                inputAnswerTextField.getText().toUpperCase(), 
                responsesList.getSelectedIndex());
        responsesList.setSelectedIndex(responsesList.getSelectedIndex() - 1);
        questNumLabel.setText("#" + Integer.toString((responsesList.getSelectedIndex() + 1)));
        inputAnswerTextField.setText((String)responsesList.getSelectedValue());    

        nextButton.setEnabled(true);
        if (responsesList.getSelectedIndex() == 0) 
            prevButton.setEnabled(false);
        inputAnswerTextField.requestFocus();
	}
	
	protected void do_nextButton_actionPerformed(ActionEvent e) {
        this.responsesListModel.setElementAt(
                inputAnswerTextField.getText().toUpperCase(), 
                responsesList.getSelectedIndex());
        responsesList.setSelectedIndex(responsesList.getSelectedIndex() + 1);
        questNumLabel.setText("#" + Integer.toString((responsesList.getSelectedIndex() + 1)));
        inputAnswerTextField.setText((String)responsesList.getSelectedValue());
        
        prevButton.setEnabled(true);
        if (responsesList.getSelectedIndex() == responsesListModel.getSize() - 1)
            nextButton.setEnabled(false);
        inputAnswerTextField.requestFocus();
	}
	
	
	protected void do_responsesList_valueChanged(ListSelectionEvent arg0) {
		//Select item in list, this enables the Previous button and changes the label and text box info to match selection in List.
		questNumLabel.setText("#" + Integer.toString((responsesList.getSelectedIndex() + 1)));
        inputAnswerTextField.setText((String)responsesList.getSelectedValue());    

        prevButton.setEnabled(true);
        nextButton.setEnabled(true);
        if (responsesList.getSelectedIndex() == responsesListModel.getSize() - 1)
            nextButton.setEnabled(false);
        if (responsesList.getSelectedIndex() == 0) 
            prevButton.setEnabled(false);
        inputAnswerTextField.requestFocus();  
        inputAnswerTextField.selectAll();
	}
	
	protected void do_inputAnswerTextField_focusGained(FocusEvent arg0) {
		inputAnswerTextField.selectAll();
	}
}
