package other;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.List;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

import java.awt.TextArea;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.SystemColor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class expertVerification extends JFrame {
	JLabel txt_QueryNo;
	JTextArea txt_Query;
	JLabel txt_StandardAnswer;
	JLabel txt_SedanAnswer;
	List list_Queries;
	JLabel lbl_Queires;
	JLabel lblStandardAnswer;
	JLabel lblSedanAnswer;
	JButton btn_Acceptable;
	JButton btn_NotAcceptable;
	java.util.List<String> lstQueries;
	
	public String operatingSystem = "";
	public String input_Queries_file = "\\input\\queries.txt";	
	public String input_Plausible_Results_Folder = "\\input\\Plausible_answers";
	public String output_Approved_Folder = "\\output\\"; 
	private JLabel lblNewLabel_2;
	private JLabel txt_CountPlausibleAnswer;
	private JLabel lblPlausibleQuery;
	private JLabel txt_PlausibleQuery;
	private JLabel lblPlausbilePatterns;
	private JLabel txt_PlausiblePattSeq;
	private JLabel lblSemanticsConductingThe;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel txt_Patt01;
	private JLabel txt_Patt02;
	private JLabel txt_Patt03;
	private JLabel txt_Patt04;
	private JButton btn_decline_Sem01;
	private JButton btn_decline_Sem02;
	private JButton btn_decline_Sem03;
	private JButton btn_decline_Sem04;
	private JLabel txt_Semantic01;
	private JLabel txt_Semantic02;
	private JLabel txt_Semantic03;
	private JLabel txt_Semantic04;
	private List list_PlausibleAnswers;
	private List list_ApprovedAnswers;
	private List list_Dismissed;
	private JLabel label_4;
	private JLabel txt_Patt05;
	private JLabel txt_Semantic05;
	private JButton btn_decline_Sem05;
	private JButton btnApprove;
	private JButton btn_NotApprove;
	private JButton btn_SaveApprovedResults;
	JFileChooser chooser;
	
	public expertVerification() {
		getContentPane().setLayout(null);
		
		list_Queries = new List();
		list_Queries.setMultipleSelections(false);
		list_Queries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if(list_ApprovedAnswers.getItemCount() > 0 || list_Dismissed.getItemCount() > 0)
				{
					int dialogResult = JOptionPane.showConfirmDialog (null, "There are approved/dismissed results that are not save yet.\n" +
																				"Are you sure you want to choose another question?","Check",JOptionPane.YES_NO_OPTION);		
					if (dialogResult ==  JOptionPane.YES_OPTION) 
					{
						loadQuery(list_Queries.getSelectedItem());
					}
				}
				else
				{
					loadQuery(list_Queries.getSelectedItem());
				}
				
			}
		});
		list_Queries.setFont(new Font("Tahoma", Font.PLAIN, 11));
		list_Queries.setBounds(15, 58, 130, 543);
		getContentPane().add(list_Queries);
		
		lbl_Queires = new JLabel("Queries:");
		lbl_Queires.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_Queires.setBounds(15, 38, 69, 14);
		getContentPane().add(lbl_Queires);
		
		JLabel lblNewLabel = new JLabel("Number:");
		lblNewLabel.setBounds(152, 15, 51, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Query:");
		lblNewLabel_1.setBounds(152, 40, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		lblStandardAnswer = new JLabel("Standard Answer:");
		lblStandardAnswer.setBounds(152, 85, 102, 14);
		getContentPane().add(lblStandardAnswer);
		
		lblSedanAnswer = new JLabel("SeDan's Answer:");
		lblSedanAnswer.setBounds(152, 105, 114, 14);
		getContentPane().add(lblSedanAnswer);
		
		btn_Acceptable = new JButton("Yes");
		btn_Acceptable.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_Acceptable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					loadPlausibleResults();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_Acceptable.setBounds(810, 101, 55, 23);
		getContentPane().add(btn_Acceptable);
		
		btn_NotAcceptable = new JButton("No");
		btn_NotAcceptable.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_NotAcceptable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					notAcceptableAnswer();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_NotAcceptable.setBounds(870, 101, 50, 23);
		getContentPane().add(btn_NotAcceptable);
		
		JLabel lblAcceptable = new JLabel("Acceptable?");
		lblAcceptable.setBounds(708, 105, 100, 14);
		getContentPane().add(lblAcceptable);
		
		txt_QueryNo = new JLabel("---");
		txt_QueryNo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_QueryNo.setBounds(205, 15, 114, 14);
		getContentPane().add(txt_QueryNo);
		
		txt_Query = new JTextArea();
		txt_Query.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_Query.setBackground(SystemColor.controlHighlight);
		txt_Query.setEditable(false);
		txt_Query.setLineWrap(true);
		txt_Query.setWrapStyleWord(true);
		txt_Query.setBorder(new LineBorder(new Color(0, 0, 0)));
		txt_Query.setText("---");
		txt_Query.setBounds(205, 40, 719, 40);
		getContentPane().add(txt_Query);
		
		txt_StandardAnswer = new JLabel("---");
		txt_StandardAnswer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_StandardAnswer.setBounds(265, 85, 655, 14);
		getContentPane().add(txt_StandardAnswer);
		
		txt_SedanAnswer = new JLabel("---");
		txt_SedanAnswer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_SedanAnswer.setBounds(265, 105, 445, 14);
		getContentPane().add(txt_SedanAnswer);
		
		lblNewLabel_2 = new JLabel("Count:");
		lblNewLabel_2.setBounds(151, 280, 46, 14);
		getContentPane().add(lblNewLabel_2);
		
		txt_CountPlausibleAnswer = new JLabel("---");
		txt_CountPlausibleAnswer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_CountPlausibleAnswer.setBounds(205, 280, 84, 14);
		getContentPane().add(txt_CountPlausibleAnswer);
		
		lblPlausibleQuery = new JLabel("Plausible query:");
		lblPlausibleQuery.setBounds(151, 300, 95, 14);
		getContentPane().add(lblPlausibleQuery);
		
		txt_PlausibleQuery = new JLabel("---");
		txt_PlausibleQuery.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_PlausibleQuery.setBounds(271, 300, 653, 14);
		getContentPane().add(txt_PlausibleQuery);
		
		lblPlausbilePatterns = new JLabel("Plausbile Patterns:");
		lblPlausbilePatterns.setBounds(151, 320, 115, 14);
		getContentPane().add(lblPlausbilePatterns);
		
		txt_PlausiblePattSeq = new JLabel("---");
		txt_PlausiblePattSeq.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_PlausiblePattSeq.setBounds(271, 320, 649, 14);
		getContentPane().add(txt_PlausiblePattSeq);
		
		lblSemanticsConductingThe = new JLabel("Semantics conducting the reasoning:");
		lblSemanticsConductingThe.setBounds(151, 340, 280, 14);
		getContentPane().add(lblSemanticsConductingThe);
		
		label = new JLabel("1.");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(151, 360, 23, 14);
		getContentPane().add(label);
		
		label_1 = new JLabel("2.");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(151, 380, 23, 14);
		getContentPane().add(label_1);
		
		label_2 = new JLabel("3.");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(151, 400, 23, 14);
		getContentPane().add(label_2);
		
		label_3 = new JLabel("4.");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_3.setBounds(151, 420, 23, 14);
		getContentPane().add(label_3);
		
		txt_Patt01 = new JLabel("---");
		txt_Patt01.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_Patt01.setBounds(170, 360, 84, 14);
		getContentPane().add(txt_Patt01);
		
		txt_Patt02 = new JLabel("---");
		txt_Patt02.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_Patt02.setBounds(170, 380, 84, 14);
		getContentPane().add(txt_Patt02);
		
		txt_Patt03 = new JLabel("---");
		txt_Patt03.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_Patt03.setBounds(170, 400, 84, 14);
		getContentPane().add(txt_Patt03);
		
		txt_Patt04 = new JLabel("---");
		txt_Patt04.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_Patt04.setBounds(170, 420, 84, 14);
		getContentPane().add(txt_Patt04);
		
		txt_Semantic01 = new JLabel("---");
		txt_Semantic01.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, txt_Semantic01.getText());
			}
		});
		txt_Semantic01.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_Semantic01.setBounds(262, 360, 618, 14);
		getContentPane().add(txt_Semantic01);
		
		txt_Semantic02 = new JLabel("---");
		txt_Semantic02.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, txt_Semantic02.getText());
			}
		});
		txt_Semantic02.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_Semantic02.setBounds(262, 380, 618, 14);
		getContentPane().add(txt_Semantic02);
		
		txt_Semantic03 = new JLabel("---");
		txt_Semantic03.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, txt_Semantic03.getText());
			}
		});
		txt_Semantic03.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_Semantic03.setBounds(262, 400, 618, 14);
		getContentPane().add(txt_Semantic03);
		
		txt_Semantic04 = new JLabel("---");
		txt_Semantic04.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, txt_Semantic04.getText());
			}
		});
		txt_Semantic04.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_Semantic04.setBounds(262, 420, 618, 14);
		getContentPane().add(txt_Semantic04);
		
		list_PlausibleAnswers = new List();
		list_PlausibleAnswers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ExpandPlausibleResultDetails();
			}
		});
		list_PlausibleAnswers.setMultipleSelections(false);
		list_PlausibleAnswers.setFont(new Font("Tahoma", Font.PLAIN, 11));
		list_PlausibleAnswers.setBounds(151, 141, 773, 134);
		getContentPane().add(list_PlausibleAnswers);
		
		list_ApprovedAnswers = new List();
		list_ApprovedAnswers.setMultipleSelections(false);
		list_ApprovedAnswers.setFont(new Font("Tahoma", Font.PLAIN, 11));
		list_ApprovedAnswers.setBounds(151, 495, 773, 77);
		getContentPane().add(list_ApprovedAnswers);
		
		btn_decline_Sem01 = new JButton("\u00D7");
		btn_decline_Sem01.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deleteSemantics(txt_Semantic01.getText());
			}
		});
		btn_decline_Sem01.setForeground(Color.BLACK);
		btn_decline_Sem01.setFont(new Font("Tahoma", Font.BOLD, 10));
		btn_decline_Sem01.setBounds(885, 360, 43, 15);
		btn_decline_Sem01.setBackground(Color.RED);
		getContentPane().add(btn_decline_Sem01);
		
		btn_decline_Sem02 = new JButton("\u00D7");
		btn_decline_Sem02.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deleteSemantics(txt_Semantic02.getText());
			}
		});
		btn_decline_Sem02.setForeground(Color.BLACK);
		btn_decline_Sem02.setFont(new Font("Tahoma", Font.BOLD, 10));
		btn_decline_Sem02.setBackground(Color.RED);
		btn_decline_Sem02.setBounds(885, 380, 43, 15);
		getContentPane().add(btn_decline_Sem02);
		
		btn_decline_Sem03 = new JButton("\u00D7");
		btn_decline_Sem03.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deleteSemantics(txt_Semantic03.getText());
			}
		});
		btn_decline_Sem03.setForeground(Color.BLACK);
		btn_decline_Sem03.setFont(new Font("Tahoma", Font.BOLD, 10));
		btn_decline_Sem03.setBackground(Color.RED);
		btn_decline_Sem03.setBounds(885, 400, 43, 15);
		getContentPane().add(btn_decline_Sem03);
		
		btn_decline_Sem04 = new JButton("\u00D7");
		btn_decline_Sem04.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deleteSemantics(txt_Semantic04.getText());
			}
		});
		btn_decline_Sem04.setForeground(Color.BLACK);
		btn_decline_Sem04.setFont(new Font("Tahoma", Font.BOLD, 10));
		btn_decline_Sem04.setBackground(Color.RED);
		btn_decline_Sem04.setBounds(885, 420, 43, 15);
		getContentPane().add(btn_decline_Sem04);
		
		label_4 = new JLabel("5.");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_4.setBounds(151, 440, 23, 14);
		getContentPane().add(label_4);
		
		txt_Patt05 = new JLabel("---");
		txt_Patt05.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_Patt05.setBounds(170, 440, 84, 14);
		getContentPane().add(txt_Patt05);
		
		txt_Semantic05 = new JLabel("---");
		txt_Semantic05.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, txt_Semantic05.getText());
			}
		});
		txt_Semantic05.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_Semantic05.setBounds(263, 440, 618, 14);
		getContentPane().add(txt_Semantic05);
		
		btn_decline_Sem05 = new JButton("\u00D7");
		btn_decline_Sem05.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deleteSemantics(txt_Semantic05.getText());
			}
		});
		btn_decline_Sem05.setForeground(Color.BLACK);
		btn_decline_Sem05.setFont(new Font("Tahoma", Font.BOLD, 10));
		btn_decline_Sem05.setBackground(Color.RED);
		btn_decline_Sem05.setBounds(885, 440, 43, 15);
		getContentPane().add(btn_decline_Sem05);
		
		btnApprove = new JButton("Approve");
		btnApprove.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnApprove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ApprovePlausibleResult();
			}
		});
		btnApprove.setBounds(700, 465, 100, 23);
		getContentPane().add(btnApprove);
		
		btn_NotApprove = new JButton("Not Approve");
		btn_NotApprove.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_NotApprove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deletePlausibleResult();
			}
		});
		btn_NotApprove.setBounds(805, 465, 119, 23);
		getContentPane().add(btn_NotApprove);
		
		btn_SaveApprovedResults = new JButton("Save");
		btn_SaveApprovedResults.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_SaveApprovedResults.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					saveResults();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_SaveApprovedResults.setBounds(820, 578, 100, 23);
		getContentPane().add(btn_SaveApprovedResults);
		
		list_Dismissed = new List();
		list_Dismissed.setBackground(SystemColor.control);
		list_Dismissed.setMultipleSelections(false);
		list_Dismissed.setFont(new Font("Tahoma", Font.PLAIN, 11));
		list_Dismissed.setBounds(151, 586, 15, 15);
		getContentPane().add(list_Dismissed);
		
		JButton btnBrowse = new JButton("Browse & Load");
		btnBrowse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openBrowseDialoge();
			}			
		});
		btnBrowse.setBounds(15, 11, 130, 23);
		getContentPane().add(btnBrowse);
		
		JLabel lblPlausbileResults = new JLabel("Plausbile Results:");
		lblPlausbileResults.setBounds(151, 125, 114, 14);
		getContentPane().add(lblPlausbileResults);
		
		JLabel lblApprovedResults = new JLabel("Approved Results:");
		lblApprovedResults.setBounds(152, 475, 114, 14);
		getContentPane().add(lblApprovedResults);
	}

	private void openBrowseDialoge() {
		// TODO Auto-generated method stub
		chooser = new JFileChooser(); 
	    chooser.setCurrentDirectory(new java.io.File("."));
	    chooser.setDialogTitle("Choose folder...");
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);
	    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) 
	    {	    	
	    	System.out.println("getCurrentDirectory(): " +  chooser.getCurrentDirectory());
	    	System.out.println("getSelectedFile() : " +  chooser.getSelectedFile());
	      	input_Queries_file = chooser.getSelectedFile() + "/input/queries.txt";	
	  		input_Plausible_Results_Folder = chooser.getSelectedFile() + "/input/Plausible_answers";
	  		output_Approved_Folder = chooser.getSelectedFile() + "/output/";
	  		try 
	  		{
	  			loadQueirs();
	  		}
	  		catch (Exception ex)
	  		{
	  			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	  		}
	  	}
	    else
	    {
	      System.out.println("No Selection ");
	    }
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(System.getProperty("os.name"));
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					expertVerification frame = new expertVerification();
					//frame.loadQueirs();
					frame.setSize(new Dimension(980, 670));
					frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
					frame.setVisible(true);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}
	
	public void loadQueirs() throws IOException
	{
		ResetAll("all");
		list_Queries.removeAll();
		
		FileInputStream fstream = new FileInputStream(input_Queries_file);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream)); 
		String strLine = "";
		lstQueries = new ArrayList<String>();
		
		while ((strLine = br.readLine()) != null)
		{
			list_Queries.add(strLine.split(";")[0] + " " + strLine.split(";")[1]);
			lstQueries.add(strLine);
		}
		br.close();	
	}
	
	public void loadQuery(String selectedQuery)
	{
		ResetAll("all");
		for(String strQuery : lstQueries)
			if(strQuery.startsWith(selectedQuery.split(" ")[0] + ";"))
			{
				txt_QueryNo.setText(strQuery.split(";")[0]);
				txt_Query.setText(strQuery.split(";")[1]);
				txt_StandardAnswer.setText(strQuery.split(";")[2]);
				txt_SedanAnswer.setText(strQuery.split(";")[3]);
			}
	}
	
	public void notAcceptableAnswer() throws IOException
	{
		if(txt_QueryNo.getText().equals("---"))
		{ 
			   JOptionPane.showMessageDialog(null, "Please load/select a plausible results first.");
			   return;
		}
		
		int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure the SeDan's answer is not acceptable?","Check",JOptionPane.YES_NO_OPTION);
		if (dialogResult ==  JOptionPane.YES_OPTION) 
		{
			String fileOutAddress = output_Approved_Folder + txt_QueryNo.getText() + ".txt";
			FileOutputStream fos = new FileOutputStream(fileOutAddress);	 
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			bw.write("Plausible answer(" + txt_SedanAnswer.getText() + ") was not acceptable.");
			bw.flush();
			bw.close();
		}
	}
	
	public void deleteSemantics(String semanticToDelete)
	{
		if(semanticToDelete.equals("---"))
			return;
		
		int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure the semantic below is not acceptable? \n" + semanticToDelete,"Check",JOptionPane.YES_NO_OPTION);
		if (dialogResult ==  JOptionPane.YES_OPTION) 
		{
			for(String item : list_PlausibleAnswers.getItems())
			{
				if(item.contains(semanticToDelete))
				{
					list_PlausibleAnswers.remove(item);
					list_Dismissed.add(item);
				}
			}	
		}		
		ResetAll("half");
		txt_CountPlausibleAnswer.setText(String.valueOf(list_PlausibleAnswers.getItemCount()));
	}
	
	public void ExpandPlausibleResultDetails()
	{
		if(list_PlausibleAnswers.getItemCount() == 0)
			return;
			
		String[] plausibleResult = list_PlausibleAnswers.getSelectedItem().split(";");
		String[] plausibleSemantics = plausibleResult[4].replace("{", "").split("}");
				
		txt_PlausiblePattSeq.setText(plausibleResult[2]);
		txt_PlausibleQuery.setText(plausibleResult[3]);
		if(plausibleSemantics.length >= 1)
		{
			txt_Patt01.setText(plausibleSemantics[0].split(",")[0].trim());
			txt_Semantic01.setText(plausibleSemantics[0].split(",",2)[1]);
		}
		if(plausibleSemantics.length >= 2)
		{
			txt_Patt02.setText(plausibleSemantics[1].split(",")[0].trim());
			txt_Semantic02.setText(plausibleSemantics[1].split(",",2)[1]);
		}
		if(plausibleSemantics.length >= 3)
		{
			txt_Patt03.setText(plausibleSemantics[2].split(",")[0].trim());
			txt_Semantic03.setText(plausibleSemantics[2].split(",",2)[1]);
		}
		if(plausibleSemantics.length >= 4)
		{
			txt_Patt04.setText(plausibleSemantics[3].split(",")[0].trim());
			txt_Semantic04.setText(plausibleSemantics[3].split(",",2)[1]);
		}	
		if(plausibleSemantics.length >= 5)
		{
			txt_Patt05.setText(plausibleSemantics[4].split(",")[0].trim());
			txt_Semantic05.setText(plausibleSemantics[4].split(",",2)[1]);
		}	
	}
	
	public void loadPlausibleResults() throws IOException
	{
		if(txt_QueryNo.getText().equals("---"))
		{ 
			   JOptionPane.showMessageDialog(null, "Please load/select a plausible results first.");
			   return;
		}
		
		File f = new File(input_Plausible_Results_Folder);
		File[] matchingFiles = f.listFiles(new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return name.startsWith(txt_QueryNo.getText() + "_");
		    }
		});
		
		if(matchingFiles.length == 0 || matchingFiles.length > 1)
		{
			JOptionPane.showMessageDialog(null, "Error in loading palsuible results. Please choose another question and give the information below to Hossein. Thanks. \n" +
					"Query no: " + txt_QueryNo.getText(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		else
		{		
			list_PlausibleAnswers.removeAll();
			list_ApprovedAnswers.removeAll();
			ResetAll("half");
			
			FileInputStream fstream = new FileInputStream(matchingFiles[0].toString());
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream)); 
			String strLine = "";
			int count = 0;
			
			while ((strLine = br.readLine()) != null)
			{
				list_PlausibleAnswers.add(strLine);
				count++;
			}
			br.close();	
			txt_CountPlausibleAnswer.setText("" + count);
		}
	}
	
	public void ApprovePlausibleResult()
	{
		if(list_PlausibleAnswers.getSelectedItem() == null)
		{ 
			   JOptionPane.showMessageDialog(null, "Please load/select a plausible result first (by axxpeting the plausible answer).");
			   return;
		}
		list_ApprovedAnswers.add(list_PlausibleAnswers.getSelectedItem());
		list_PlausibleAnswers.remove(list_PlausibleAnswers.getSelectedItem());
		ResetAll("half");
		txt_CountPlausibleAnswer.setText(String.valueOf(list_PlausibleAnswers.getItemCount()));
	}
	
	public void ResetAll(String settings)
	{
		if(settings.equals("all"))
		{
			list_PlausibleAnswers.removeAll();
			list_PlausibleAnswers.clear();
			
			list_ApprovedAnswers.removeAll();
			list_ApprovedAnswers.clear();
			
			list_Dismissed.removeAll();
			list_Dismissed.clear();
			
			txt_Query.setText("---");
			txt_QueryNo.setText("---");
			txt_StandardAnswer.setText("---");
			txt_SedanAnswer.setText("---");
		}
		
		txt_CountPlausibleAnswer.setText("---");
		txt_Patt01.setText("---");
		txt_Patt02.setText("---");
		txt_Patt03.setText("---");
		txt_Patt04.setText("---");
		txt_Patt05.setText("---");
		txt_PlausiblePattSeq.setText("---");
		txt_PlausibleQuery.setText("---");		
		txt_Semantic01.setText("---");
		txt_Semantic02.setText("---");
		txt_Semantic03.setText("---");
		txt_Semantic04.setText("---");
		txt_Semantic05.setText("---");
	}
	
	public void saveResults() throws IOException
	{		
		String results = "";		
		
		int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to save the answer(s)?","Check",JOptionPane.YES_NO_OPTION);
		if (dialogResult ==  JOptionPane.YES_OPTION) 
		{
			// Save approved answers
			for(int i = 0; i < list_ApprovedAnswers.getItemCount(); i++)
			{
				results += list_ApprovedAnswers.getItem(i) + "\n";
			}
			String fileOutAddress = output_Approved_Folder + txt_QueryNo.getText() + "_Approved.txt";
			FileOutputStream fos = new FileOutputStream(fileOutAddress);	 
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			bw.write(results);
			bw.flush();
			bw.close();
		
		
			// Save dismissed answers
			results = "";
			for(int i = 0; i < list_Dismissed.getItemCount(); i++)
			{
				results += list_Dismissed.getItem(i) + "\n";
			}
			
			if (dialogResult ==  JOptionPane.YES_OPTION) 
			{
				fileOutAddress = output_Approved_Folder + txt_QueryNo.getText() + "_Dismissed.txt";
				FileOutputStream fos1 = new FileOutputStream(fileOutAddress);	 
				BufferedWriter bw1 = new BufferedWriter(new OutputStreamWriter(fos1));
				bw1.write(results);
				bw1.flush();
				bw1.close();
			}
			ResetAll("half");
			list_ApprovedAnswers.removeAll();
			list_Dismissed.removeAll();
		}
	}
	
	public void deletePlausibleResult()
	{
		if(list_PlausibleAnswers.getSelectedItem() == null)
		{ 
			   JOptionPane.showMessageDialog(null, "Please load/select a plausible result first (by axxpeting the plausible answer).");
			   return;
		}
		
		int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to dismiss the plausible answer(s)?","Check",JOptionPane.YES_NO_OPTION);
		if (dialogResult ==  JOptionPane.YES_OPTION) 
		{
			list_Dismissed.add(list_PlausibleAnswers.getSelectedItem());
			list_PlausibleAnswers.remove(list_PlausibleAnswers.getSelectedItem());
		}
		ResetAll("half");
		txt_CountPlausibleAnswer.setText(String.valueOf(list_PlausibleAnswers.getItemCount()));
	}
}
