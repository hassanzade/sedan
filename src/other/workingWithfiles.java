package other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.TextArea;

import javax.swing.JButton;

import GUI.SparqlQueryWindow;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class workingWithfiles extends JFrame {
	private JFrame frame;
	JPanel firstPanel = new JPanel();
	JPanel secondPanel = new JPanel();
	DecimalFormat f = new DecimalFormat("##.00");
	
	TextArea txt_FilesToRead;
	TextArea txt_Console;
	TextArea txt_Console_02;
	TextArea txt_ItemToFind;
	JCheckBox chk_Average;
	JCheckBox chk_MathCase;
	JCheckBox chk_MatchWholeWord;
	JCheckBox chk_CountInBetween;
	TextArea txt_AnswerBlocks;
	
	private JCheckBox chk_Count;
	private JTextField txt_CountInBetween;
	private JCheckBox chk_Contains;
	private JCheckBox chk_NotContains;
	private JTextField txt_NotContains;

	List<String> lst_AnswersToCount;
	private JTextField txt_AnswerToFind;
	private JLabel lblResultsFile;
	private JTextField txt_ResultsFile;
	
	public workingWithfiles() {
		getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 830, 580);
		tabbedPane.add("First panel", firstPanel);
		firstPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Find:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(9, 14, 46, 14);
		firstPanel.add(lblNewLabel);
		
		chk_MathCase = new JCheckBox("Match case");
		chk_MathCase.setFont(new Font("Tahoma", Font.BOLD, 11));
		chk_MathCase.setBounds(16, 109, 97, 23);
		firstPanel.add(chk_MathCase);
		
		chk_MatchWholeWord = new JCheckBox("Match whole word only");
		chk_MatchWholeWord.setFont(new Font("Tahoma", Font.BOLD, 11));
		chk_MatchWholeWord.setBounds(16, 135, 163, 23);
		firstPanel.add(chk_MatchWholeWord);
		
		txt_FilesToRead = new TextArea();
		txt_FilesToRead.setText("C:\\Users\\Hosein\\OneDrive - Dalhousie University\\Thesis (OneDrive)\\Experiments\\Plausible Query Results\\22- x cause Black Lung - d.5 - all pat - obj - some answers\\results.txt");
		txt_FilesToRead.setFont(new Font("Calibri Light", Font.BOLD, 12));
		txt_FilesToRead.setBounds(21, 180, 794, 120);
		firstPanel.add(txt_FilesToRead);
		
		JLabel lblFilesToRead = new JLabel("Files to read:");
		lblFilesToRead.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFilesToRead.setBounds(21, 165, 97, 14);
		firstPanel.add(lblFilesToRead);
		
		txt_Console = new TextArea();
		txt_Console.setFont(new Font("Calibri Light", Font.BOLD, 12));
		txt_Console.setBounds(21, 340, 794, 91);
		firstPanel.add(txt_Console);
		
		JButton btn_Find = new JButton("Find");
		btn_Find.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					findItemInTexts();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_Find.setBounds(726, 310, 89, 23);
		firstPanel.add(btn_Find);
		
		chk_Average = new JCheckBox("Average");
		chk_Average.setFont(new Font("Tahoma", Font.BOLD, 11));
		chk_Average.setBounds(197, 135, 97, 23);
		firstPanel.add(chk_Average);
		
		chk_Count = new JCheckBox("Count");
		chk_Count.setFont(new Font("Tahoma", Font.BOLD, 11));
		chk_Count.setBounds(197, 109, 97, 23);
		firstPanel.add(chk_Count);
		
		JButton btn_Clear = new JButton("Clear texts");
		btn_Clear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txt_Console.setText("");
				txt_FilesToRead.setText("");
			}
		});
		btn_Clear.setBounds(632, 310, 89, 23);
		firstPanel.add(btn_Clear);
		
		txt_ItemToFind = new TextArea();
		txt_ItemToFind.setText("Depth of Plausibility: 1\r\nDepth of Plausibility: 2\r\nDepth of Plausibility: 3\r\nDepth of Plausibility: 4\r\nDepth of Plausibility: 5");
		txt_ItemToFind.setFont(new Font("Calibri Light", Font.BOLD, 12));
		txt_ItemToFind.setBounds(60, 15, 700, 80);
		firstPanel.add(txt_ItemToFind);
		
		txt_Console_02 = new TextArea();
		txt_Console_02.setFont(new Font("Calibri Light", Font.BOLD, 12));
		txt_Console_02.setBounds(21, 440, 794, 91);
		firstPanel.add(txt_Console_02);
		
		chk_CountInBetween = new JCheckBox("Count In Between");
		chk_CountInBetween.setSelected(true);
		chk_CountInBetween.setFont(new Font("Tahoma", Font.BOLD, 11));
		chk_CountInBetween.setBounds(315, 109, 130, 23);
		firstPanel.add(chk_CountInBetween);
		
		txt_CountInBetween = new JTextField();
		txt_CountInBetween.setText("http://semmed.org/");
		txt_CountInBetween.setBounds(391, 136, 143, 20);
		firstPanel.add(txt_CountInBetween);
		txt_CountInBetween.setColumns(10);
		
		chk_Contains = new JCheckBox("Contains");
		chk_Contains.setSelected(true);
		chk_Contains.setFont(new Font("Tahoma", Font.BOLD, 11));
		chk_Contains.setBounds(315, 135, 75, 23);
		firstPanel.add(chk_Contains);
		
		chk_NotContains = new JCheckBox("!Contains");
		chk_NotContains.setSelected(true);
		chk_NotContains.setFont(new Font("Tahoma", Font.BOLD, 11));
		chk_NotContains.setBounds(541, 135, 89, 23);
		firstPanel.add(chk_NotContains);
		
		txt_NotContains = new JTextField();
		txt_NotContains.setText("<");
		txt_NotContains.setColumns(10);
		txt_NotContains.setBounds(630, 136, 130, 20);
		firstPanel.add(txt_NotContains);
		tabbedPane.add("Second panel", secondPanel);
		tabbedPane.setSelectedIndex(1);
		secondPanel.setLayout(null);
		
		txt_AnswerToFind = new JTextField();
		txt_AnswerToFind.setText("SCHISTOSOMA_SP");
		txt_AnswerToFind.setBounds(101, 50, 609, 20);
		secondPanel.add(txt_AnswerToFind);
		txt_AnswerToFind.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Answer to find:");
		lblNewLabel_1.setBounds(10, 52, 85, 14);
		secondPanel.add(lblNewLabel_1);
		
		lblResultsFile = new JLabel("Results file:");
		lblResultsFile.setBounds(10, 26, 89, 14);
		secondPanel.add(lblResultsFile);
		
		txt_ResultsFile = new JTextField();
		txt_ResultsFile.setText("C:\\Users\\Hosein\\OneDrive - Dalhousie University\\Thesis (OneDrive)\\Experiments\\Plausible Query Results\\03- Q46 with total time, without queries printed\\1- X causes Katayama_fever - d.4- all pat - obj\\results.txt");
		txt_ResultsFile.setColumns(10);
		txt_ResultsFile.setBounds(101, 23, 609, 20);
		secondPanel.add(txt_ResultsFile);
		
		JButton btn_Browse = new JButton("Browse");
		btn_Browse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FileDialog fd = new FileDialog(frame, "Select ontology file ...", FileDialog.LOAD);
				//fd.setFile("*.rdf;*.rdfs;*.owl;*.plowl");
				fd.setVisible(true);// show();				
				txt_ResultsFile.setText(fd.getDirectory() + fd.getFile());
			}
		});
		btn_Browse.setBounds(720, 22, 89, 23);
		secondPanel.add(btn_Browse);
		
		txt_AnswerBlocks = new TextArea();
		txt_AnswerBlocks.setFont(new Font("Calibri Light", Font.BOLD, 12));
		txt_AnswerBlocks.setBounds(10, 88, 799, 435);
		secondPanel.add(txt_AnswerBlocks);
		
		JButton btn_FindTheAnswer = new JButton("Find");
		btn_FindTheAnswer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					findTheAnswerDetails();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_FindTheAnswer.setBounds(720, 49, 89, 23);
		secondPanel.add(btn_FindTheAnswer);
		
		getContentPane().add(tabbedPane);
		
	}

	public static void main(String[] args) throws IOException {
		
		workingWithfiles workWithFile = new workingWithfiles();
//		workWithFile.PrintPlausibleResults();
//		workWithFile.splitLargeFiles();
		
		//workWithFile.ReadFileAndReplace_SemMedline();		
		//workWithFile.CheckLineAt(314679);		
		//workWithFile.ReadFileAndReplace_DrugBank();		
		//workWithFile.ReadFileAndRetrieveSome_SemMedline();
//		workWithFile.testFile();
		
//		String test = "{Gen,(<http://semmed.org/resource/Noninfiltrating_Intraductal_Carcinoma> <http://semmed.org/property/ISA> <http://semmed.org/resource/Malignant_Neoplasms>)}";
//
//		String[] testArr = test.replace(" ", "").replace("}", "").split("\\{");
//		for(String t : testArr)
//			System.out.println(t);
//		
//		
//		String test3 = "3;http://semmed.org/resource/Phosphodiesterase_5_inhibitor;\n" +
//						"3;http://semmed.org/resource/strontium_ranelate;\n" +
//						"3;http://semmed.org/resource/imatinib;\n" +
//						"3;http://semmed.org/resource/bortezomib;\n" +
//						"3;http://semmed.org/resource/Inosine_5-Phosphate_Dehydrogenase_Inhibitor;\n";
//		String test2 = "http://semmed.org/resource/imatinib";
//		System.out.println(test3.contains(test2));
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					workingWithfiles frame = new workingWithfiles();
					frame.setSize(new Dimension(845, 595));
					frame.setVisible(true);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void ReadFileAndReplace_SemMedline() throws IOException
	{
		workingWithfiles workWithFile = new workingWithfiles();
		// TODO Auto-generated method stub
		String fileAddress = "C:\\Programming\\TripleStores\\Dump Files\\SemMedDB_Ntiples_DrugDisease.csv";
		FileInputStream fstream = new FileInputStream(fileAddress);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream)); 
		
		String fileOutAddress = "C:\\Programming\\TripleStores\\Dump Files\\SemMedDB_Ntiples_DrugDisease_2.nt";
		FileOutputStream fos = new FileOutputStream(fileOutAddress);	 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
//		
		
		String strLine;
		int CountLine_Read = 0;
		int CountLine_Write = 0;
		
		while ((strLine = br.readLine()) != null)   {
				CountLine_Read++;
				
				if(CountLine_Read > 1500000)
					if(!strLine.contains("subject,predicate,object,dot"))
					{
						strLine = workWithFile.TextReplacement_SemMedline(strLine);
						bw.write(strLine);
						bw.newLine();
						CountLine_Write++;
					}
				
		}
		br.close();		
		bw.close();
		System.out.println("Count Read: " + CountLine_Read + "   Count Write: " + CountLine_Write);
	}
	
	public String TextReplacement_SemMedline(String strLine)
	{
		strLine = strLine.replace("\"", "");
		strLine = strLine.replace("'", "");
		strLine = strLine.replace(">,<", "> <");
		strLine = strLine.replace(">,.", ">.");
		strLine = strLine.replace("Third_heart_sound,_S>3<", "Third_heart_sound_S3");                         
		strLine = strLine.replace("Fourth_heart_sound,_S>4<", "Fourth_heart_sound_S4");
		strLine = strLine.replace("Deficiency_of_cytochrome-b>5<_reductase", "Deficiency_of_cytochrome_b5_reductase");
		strLine = strLine.replace("T>3<_thyrotoxicosis", "T3_thyrotoxicosis");
		strLine = strLine.replace("Infantile_GM>2<_gangliosidosis", "Infantile_GM2_gangliosidosis");
		strLine = strLine.replace("Juvenile_GM>2<_gangliosidosis", "Juvenile_GM2_gangliosidosis");
		strLine = strLine.replace("Megaloblastic_anemia_due_to_vitamin_B>12<_deficiency", "Megaloblastic_anemia_due_to_vitamin_B12_deficiency");
		strLine = strLine.replace("Gestational_diabetes_mellitus,_class_A>1<", "Gestational_diabetes_mellitus,_class_A1");
		strLine = strLine.replace("Genital_Diseases,_Female", "Genital_Diseases_Female");
		//strLine = strLine.replace("<http://semmed.org/resource/Complication>.", "<http://semmed.org/resource/Complication>.\n");
		
		strLine = strLine.replace("F>1<", "F1");
		strLine = strLine.replace("A>1<", "A1");
		strLine = strLine.replace("A>2<", "A2");
		strLine = strLine.replace("A>3<", "A3");
		strLine = strLine.replace("alpha>1<", "alpha1");
		strLine = strLine.replace("alpha>2<", "alpha2");
		strLine = strLine.replace("5-HT>3<", "5-HT3");
		strLine = strLine.replace("5-HT>1<", "5-HT1");
		strLine = strLine.replace("/>5>", "/5>");
		strLine = strLine.replace("FADH>2<", "FADH2");


		return strLine;
	}
	
	public void CheckLineAt(int index) throws IOException
	{		
		//String fileAddress = "C:\\Programming\\TripleStores\\Dump Files\\SemMedDB_Ntiples_DrugDisease_1.nt";		
		String fileAddress = "C:\\Users\\Hosein\\Downloads\\drugbank.nq\\drugbank.nq";

		FileInputStream fstream = new FileInputStream(fileAddress);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		
		String strLine;
		int CountLine = 0;
		
		while ((strLine = br.readLine()) != null)   {
			CountLine++;
			// Print the content on the console
			if(CountLine == index)
				System.out.println (strLine);
			if(strLine.contains("Migalastat"))
				System.out.println ("long line: " + strLine);
		}
		System.out.println("Count is: " + CountLine);
	}
	
	
	// For Drugbank File
	public void ReadFileAndReplace_DrugBank() throws IOException
	{
		workingWithfiles workWithFile = new workingWithfiles();
		// TODO Auto-generated method stub
		String fileAddress = "C:\\Users\\Hosein\\Downloads\\drugbank.nq\\drugbank.nq";
		FileInputStream fstream = new FileInputStream(fileAddress);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream)); 
		
		String fileOutAddress = "C:\\Users\\Hosein\\Downloads\\drugbank.nq\\drugbank_09.nq";
		FileOutputStream fos = new FileOutputStream(fileOutAddress);	 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
//		
		
		String strLine;
		int CountLine_Read = 0;
		int CountLine_Write = 0;
		
		while ((strLine = br.readLine()) != null)   {
				CountLine_Read++;
				
				if(CountLine_Read > (8 * 500000) && CountLine_Read <= (9 * 500000))
					//if(!strLine.contains("subject,predicate,object,dot"))
					{
						strLine = workWithFile.TextReplacement_DrugBank(strLine);
						bw.write(strLine);
						bw.newLine();
						CountLine_Write++;
					}
				
		}
		br.close();		
		bw.close();
		System.out.println("Count Read: " + CountLine_Read + "   Count Write: " + CountLine_Write);
	}
	
	public String TextReplacement_DrugBank(String strLine)
	{
		strLine = strLine.replace("\\'", "'");
		strLine = strLine.replace("ABL fusion", "ABL-fusion");
//		strLine = strLine.replace(">,<", "> <");
//		strLine = strLine.replace(">,.", ">.");
//		strLine = strLine.replace("Third_heart_sound,_S>3<", "Third_heart_sound_S3");                         
//		strLine = strLine.replace("Fourth_heart_sound,_S>4<", "Fourth_heart_sound_S4");
//		strLine = strLine.replace("Deficiency_of_cytochrome-b>5<_reductase", "Deficiency_of_cytochrome_b5_reductase");
//		strLine = strLine.replace("T>3<_thyrotoxicosis", "T3_thyrotoxicosis");
//		strLine = strLine.replace("Infantile_GM>2<_gangliosidosis", "Infantile_GM2_gangliosidosis");
//		strLine = strLine.replace("Juvenile_GM>2<_gangliosidosis", "Juvenile_GM2_gangliosidosis");
//		strLine = strLine.replace("Megaloblastic_anemia_due_to_vitamin_B>12<_deficiency", "Megaloblastic_anemia_due_to_vitamin_B12_deficiency");
//		strLine = strLine.replace("Gestational_diabetes_mellitus,_class_A>1<", "Gestational_diabetes_mellitus,_class_A1");
//		strLine = strLine.replace("Genital_Diseases,_Female", "Genital_Diseases_Female");
//		//strLine = strLine.replace("<http://semmed.org/resource/Complication>.", "<http://semmed.org/resource/Complication>.\n");
//		
//		strLine = strLine.replace("F>1<", "F1");
//		strLine = strLine.replace("A>1<", "A1");
//		strLine = strLine.replace("A>2<", "A2");
//		strLine = strLine.replace("A>3<", "A3");
//		strLine = strLine.replace("alpha>1<", "alpha1");
//		strLine = strLine.replace("alpha>2<", "alpha2");
//		strLine = strLine.replace("5-HT>3<", "5-HT3");
//		strLine = strLine.replace("5-HT>1<", "5-HT1");
//		strLine = strLine.replace("/>5>", "/5>");
//		strLine = strLine.replace("FADH>2<", "FADH2");


		return strLine;
	}
	
	// a sample dataset for loading into Laptop GraphDB
	public void ReadFileAndRetrieveSome_SemMedline() throws IOException
	{
		workingWithfiles workWithFile = new workingWithfiles();
		// TODO Auto-generated method stub
		String fileAddress = "C:\\Programming\\TripleStores\\SemMedDB\\SemMedDB_Ntiples_DrugDisease_1.nt";
		FileInputStream fstream = new FileInputStream(fileAddress);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream)); 
		
		String fileOutAddress = "C:\\Programming\\TripleStores\\SemMedDB\\SemMedDB_Ntiples_DrugDisease_1_small01.nt";
		FileOutputStream fos = new FileOutputStream(fileOutAddress);	 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
//		
		
		String strLine;
		int CountLine_Read = 0;
		int CountLine_Write = 0;
		
		while ((strLine = br.readLine()) != null)   {
				CountLine_Read++;
				
				if(Math.random() <= 0.1)
					{
						bw.write(strLine);
						bw.newLine();
						CountLine_Write++;
					}
				
		}
		br.close();		
//		bw.close();
		System.out.println("Count Read: " + CountLine_Read + "   Count Write: " + CountLine_Write);
	}
	
	public void testFile() throws IOException
	{
		FileOutputStream fos = new FileOutputStream(settings.Settings.OUTPUT_TRANSFORMED_QUERIES_ADDRESS);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		bw.write("hello");
		bw.newLine();
		bw.close();
	}
	
	private void PrintPlausibleResults() throws IOException {
		// TODO Auto-generated method stub
		String plausibleResults = new String(Files.readAllBytes(Paths.get(settings.Settings.OUTPUT_NEW_QUERIES_RESULTS_ADDRESS)));
		//txt_Console.append(plausibleResults);
		
		String curDepth = "0";
		String curDepthAnswers = "";
		String TotalAnswers = "";
		Boolean readResults = true;
		String[] results = plausibleResults.split("\n");
		for(int i = 0; i < results.length; i++) // String result : plausibleResults.split("\n"))
		{
			if(results[i].contains("Depth of Plausibility"))
			{
				readResults = false;
				String tmpDepth = results[i].replace(" ", "").split(":")[1];
				if(!curDepth.trim().equals(tmpDepth.trim()))
				{
					if(curDepthAnswers.length() > 0)
						TotalAnswers += curDepthAnswers;
					curDepthAnswers = "";
					curDepth = results[i].replace(" ", "").split(":")[1];
				}
			}
			
			if(results[i].contains("Results:"))
			{
				readResults = true;
			}
			else if(readResults && results[i].length() > 0)
			{
				if(!curDepthAnswers.trim().contains(curDepth + ";" + results[i] + ";"))
				{
					curDepthAnswers += curDepth + ";" + results[i] + ";\n";
					
				}
			}
		}
		TotalAnswers += curDepthAnswers;
		
		Files.write(Paths.get(settings.Settings.OUTPUT_NEW_QUERIES_RESULTS_ANSWERSONLY_ADDRESS), TotalAnswers.getBytes());
		
	}
	

	private void findItemInTexts() throws IOException {
//		System.out.println("true\n".contains("\\b" + "true" + "\\b"));
//		System.out.println("true_blue".contains("true"));
		
		double x = 2667126842.00;

		String curLine = "", path = "", itemToFind = "", nextItemToFind = "", itemToCount = txt_CountInBetween.getText();
		String[] itemsToFind = txt_ItemToFind.getText().split("\n");
		Boolean startCounting = false;
		
		String[] filesToRead = txt_FilesToRead.getText().split("\n");
		String[] lineItems;
		String curFile = "", columnsHeaders_01 = ";", columnsHeaders_02 = "Query;", strStatistics = "", strCount = "";
		int count = 0, sum = 0;
		double average = 0, variance = 0, stdDev = 0;
		List<Double> lst_ExecutionTimes;
				
		
		for(int i = 0; i < filesToRead.length; i++)
		{
			curLine = ""; path = "";			
			path = filesToRead[i].replaceAll("(\\r|\\n)", "");		
			curFile = path.split("\\\\")[path.split("\\\\").length-2];
			strStatistics += curFile + ";";
			strCount += curFile + ";";
						
			for(int item = 0; item < itemsToFind.length; item++)
			{	
				InputStreamReader inpStream = new InputStreamReader(new FileInputStream(path));
				BufferedReader buffReader = new BufferedReader(inpStream);
				
				lst_ExecutionTimes = new ArrayList<Double>();
				count = 0; sum = 0; average = 0; variance = 0; stdDev = 0;
				
				itemToFind = itemsToFind[item].replaceAll("(\\r|\\n)", "");
				nextItemToFind = (item < itemsToFind.length - 1) ? itemsToFind[item+1].replaceAll("(\\r|\\n)", "") : null;
				
				if(chk_Average.isSelected() && !columnsHeaders_01.contains(itemToFind))
				{
					columnsHeaders_01 += itemToFind + ";" + itemToFind + ";";
					columnsHeaders_02 += "Average;StdDev;";
				}
				
				if(chk_Count.isSelected() && !columnsHeaders_01.contains(itemToFind))
				{
					columnsHeaders_01 += itemToFind + ";";
				}
				
				if(chk_CountInBetween.isSelected() && !columnsHeaders_01.contains(itemToFind))
				{
					columnsHeaders_01 += itemToFind + ";" + itemToFind + ";";
					columnsHeaders_02 += "Total;Unique;";
				}
				
				if(chk_Count.isSelected()) // for counting the item to find (the input text) 
				{
					while((curLine = buffReader.readLine()) != null)
					{
						curLine = curLine.replaceAll("(\\r|\\n)", "");

						if(chk_MathCase.isSelected() && chk_MatchWholeWord.isSelected())
						{
							if(curLine.contains(itemToFind))
							{
								count++;
							}
						}
						else if(chk_MathCase.isSelected() && !chk_MatchWholeWord.isSelected())
						{
							if(curLine.contains(itemToFind))
							{
								count++;
							}
						}
						else if (!chk_MathCase.isSelected() && chk_MatchWholeWord.isSelected())
						{
							if(curLine.toLowerCase().contains("\\b" + itemToFind.toLowerCase() + "\\b"))
							{
								count++;
							}
						}
						else if (!chk_MathCase.isSelected() && !chk_MatchWholeWord.isSelected())
						{
							if(curLine.toLowerCase().contains(itemToFind.toLowerCase()))
							{
								count++;
							}
						}
							
					}
					txt_Console.setText(txt_Console.getText() + "\n" + path + "\nCount \"" + itemToFind + "\": " + count + "\n");
					//txt_Console_02.setText(txt_Console_02.getText() + count
					strCount += count + ";";
				}
				
				if(chk_CountInBetween.isSelected()) // for counting the item to find (the input text) 
				{
					startCounting = false;
					lst_AnswersToCount = new ArrayList<String>();
					lst_AnswersToCount.clear();
					
					while((curLine = buffReader.readLine()) != null)
					{
						curLine = curLine.replaceAll("(\\r|\\n)", "");
						if(curLine.contains(itemToFind))
							startCounting = true;
						else if (nextItemToFind != null && curLine.contains(nextItemToFind))
							startCounting = false;
						if(startCounting)
//							if(chk_MathCase.isSelected() && chk_MatchWholeWord.isSelected())
//							{
//								if(curLine.contains(itemToFind))
//								{
//									count++;
//								}
//							}
//							else if(chk_MathCase.isSelected() && !chk_MatchWholeWord.isSelected())
//							{
//								if(curLine.contains(itemToFind))
//								{
//									count++;
//								}
//							}
//							else if (!chk_MathCase.isSelected() && chk_MatchWholeWord.isSelected())
//							{
//								if(curLine.toLowerCase().contains("\\b" + itemToFind.toLowerCase() + "\\b"))
//								{
//									count++;
//								}
//							}
//							else if (!chk_MathCase.isSelected() && !chk_MatchWholeWord.isSelected())
//							{
//								if(curLine.toLowerCase().contains(itemToFind.toLowerCase()))
//								{
//									count++;
//								}
//							}
							if (chk_Contains.isSelected() && chk_NotContains.isSelected())
							{
								if(curLine.toLowerCase().contains(itemToCount.toLowerCase()) && !curLine.toLowerCase().contains(txt_NotContains.getText().toLowerCase()))
								{
									AddAnswerToList(curLine);
									count++;
								}
							}
					}
					txt_Console.setText(txt_Console.getText() + "\n" + path + "\nCount \"" + itemToFind + "\": " + count + "\nUnique: " + lst_AnswersToCount.size() + "\n");
					//txt_Console_02.setText(txt_Console_02.getText() + count
					strCount += count + ";" + lst_AnswersToCount.size() + ";";
				}
				
				if(chk_Average.isSelected()) // for counting and calculating the execution time average of a step (the input text) 
				{
					lst_ExecutionTimes.clear();
					while((curLine = buffReader.readLine()) != null)
					{
						curLine = curLine.replaceAll("(\\r|\\n)", "");
						if(curLine.contains(itemToFind))
						{
							count++;
							lineItems = curLine.split(";");
							sum += Double.parseDouble(lineItems[3]);
							lst_ExecutionTimes.add(Double.parseDouble(lineItems[3]));
						}
					}
					average = ((count != 0) ? sum/count : 0);
					
					for(double exItem : lst_ExecutionTimes)
					{
						variance += (exItem-average)*(exItem-average);
					}
					variance = ((count > 1) ? variance / (count-1) : 0); ;
					
					stdDev = Math.sqrt(variance);
					strStatistics += f.format(average) + ";" + f.format(stdDev) + ";";
					
					txt_Console.setText(txt_Console.getText() + "\n" + path + "\nCount \"" + itemToFind + "\":" + count +
											"\nSum of execution time: " + sum + 
											"\nAverage of execution time: " + f.format(average) +
											"\nVariance of execution time: " + f.format(variance) +
											"\nStdDev of execution time: " + f.format(stdDev) + "\n");
				}
				
				
			}
			txt_Console.setText(txt_Console.getText() + "\n------------------------------------------------------------------\n");
			strStatistics += "\n";
			strCount += "\n";
		}
		if(chk_Average.isSelected())
		{
			txt_Console_02.setText(txt_Console_02.getText() + columnsHeaders_01 + "\n" + columnsHeaders_02 + "\n" + strStatistics);
		}
		if(chk_Count.isSelected())
		{
			txt_Console_02.setText(txt_Console_02.getText() + columnsHeaders_01 + "\n" + strCount);
		}
		if(chk_CountInBetween.isSelected())
		{
			txt_Console_02.setText(txt_Console_02.getText() + columnsHeaders_01 + "\n" + columnsHeaders_02 +  "\n" + strCount);
		}
	}
	
	private void AddAnswerToList(String curLine)
	{
		Boolean add = true;
		for(String answer : lst_AnswersToCount)
		{
			if(curLine.equals(answer))
			{
				add = false;
				break;
			}
		}
		if(add)
			lst_AnswersToCount.add(curLine);
	}
	
	private void findTheAnswerDetails() throws IOException {
		// TODO Auto-generated method stub
		InputStreamReader inpStream = new InputStreamReader(new FileInputStream(txt_ResultsFile.getText()));
		BufferedReader buffReader = new BufferedReader(inpStream);
		
		txt_AnswerBlocks.setText("");
		
		String answerToFind = txt_AnswerToFind.getText(), curLine = "", depth = "", 
								seqOfPlPatts = "", newQuery = "", supportingSemantics = "", 
									totalRewritingTime = "", answerBlock = "";
		Boolean answerFound = false;
		
		while((curLine = buffReader.readLine()) != null)
		{			
			if(curLine.contains("Depth of Plausibility"))
				depth = curLine.split(":")[1].trim();
			else if (curLine.contains("Sequence of Plausible Patterns"))
				seqOfPlPatts = curLine.split(":")[1].trim();
			else if (curLine.contains("New Query"))
				newQuery = buffReader.readLine();
			else if (curLine.contains("Supporting Semantics"))
				supportingSemantics = buffReader.readLine();
			else if (curLine.contains("Results"))
			{
				while(!curLine.contains("Total rewriting time"))
				{
					curLine = buffReader.readLine();
					if(curLine.contains(answerToFind))
						answerFound = true;
				}
				totalRewritingTime = curLine.split(":")[1].trim();
			}
			
			if(answerFound) // means the block contians the answer, so we are gonna print the block 
			{
//				System.out.println("here");
				answerBlock = totalRewritingTime + ";" + depth + ";" + seqOfPlPatts + ";" + newQuery + ";" + supportingSemantics;
//				System.out.println(answerBlock);
				txt_AnswerBlocks.setText(txt_AnswerBlocks.getText() + replaceNamespaces(answerBlock) + "\n");
				answerFound = false;
			}
		}
	}
	
	private String replaceNamespaces(String text)
	{
		String strText = "";
		
		strText = text.replace(">", "").replace("<http://semmed.org/property/", "semp:").replace("<http://semmed.org/resource/", "semr:").replace("<http://purl.obolibrary.org/obo/", "obo:").
					replace("<http://www.geneontology.org/formats/oboInOwl#", "oboInOwl:").replace("<http://www.w3.org/2002/07/owl#", "owl:").
					replace("<http://purl.org/dc/elements/1.1/", "dc:").replace("<http://purl.obolibrary.org/obo/doid#", "doid:").
					replace("<http://bio2rdf.org/drugbank:", "drugbank:");
		
		
		return strText;
	}
	
	private void splitLargeFiles() throws IOException
	{
		String strPath = "C:\\Users\\Hosein\\OneDrive - Dalhousie University\\Thesis (OneDrive)\\Experiments\\Plausible Query Results\\03- Q46 with total time, without queries printed\\7- x treats acute_myocarditis - d.4- all pat - obj\\results_total lines is = 6756361.txt";
		InputStreamReader inpStream = new InputStreamReader(new FileInputStream(strPath));
		BufferedReader buffReader = new BufferedReader(inpStream);
		
		int divideInto = 2, lineCount = 0, curCount = 0;
		String curLine = "";
		
		while((curLine = buffReader.readLine()) != null)
		{
			lineCount++;
		}
//		buffReader.close();
		System.out.println(lineCount);
		
		String fileOutAddress1 = "C:\\Users\\Hosein\\OneDrive - Dalhousie University\\Thesis (OneDrive)\\Experiments\\Plausible Query Results\\03- Q46 with total time, without queries printed\\7- x treats acute_myocarditis - d.4- all pat - obj\\results_part01.txt";
		FileOutputStream fos1 = new FileOutputStream(fileOutAddress1);	 
		BufferedWriter bw1 = new BufferedWriter(new OutputStreamWriter(fos1));
		
		String fileOutAddress2 = "C:\\Users\\Hosein\\OneDrive - Dalhousie University\\Thesis (OneDrive)\\Experiments\\Plausible Query Results\\03- Q46 with total time, without queries printed\\7- x treats acute_myocarditis - d.4- all pat - obj\\results_part02.txt";;
		FileOutputStream fos2 = new FileOutputStream(fileOutAddress2);	 
		BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(fos2));
		
		String fileOutAddress3 = "C:\\Users\\Hosein\\OneDrive - Dalhousie University\\Thesis (OneDrive)\\Experiments\\Plausible Query Results\\03- Q46 with total time, without queries printed\\7- x treats acute_myocarditis - d.4- all pat - obj\\results_part03.txt";;
		FileOutputStream fos3 = new FileOutputStream(fileOutAddress3);	 
		BufferedWriter bw3 = new BufferedWriter(new OutputStreamWriter(fos3));
		
		String fileOutAddress4 = "C:\\Users\\Hosein\\OneDrive - Dalhousie University\\Thesis (OneDrive)\\Experiments\\Plausible Query Results\\03- Q46 with total time, without queries printed\\7- x treats acute_myocarditis - d.4- all pat - obj\\results_part04.txt";;
		FileOutputStream fos4 = new FileOutputStream(fileOutAddress4);	 
		BufferedWriter bw4 = new BufferedWriter(new OutputStreamWriter(fos4));
		
		InputStreamReader inpStream2 = new InputStreamReader(new FileInputStream(strPath));
		BufferedReader buffReader2 = new BufferedReader(inpStream2);
//		System.out.println(buffReader2.lines().count());
		while((curLine = buffReader2.readLine()) != null)
		{
//			System.out.println(curLine);
			curCount++;
			
			if(curCount < (lineCount/4))
			{
				bw1.write(curLine + "\n");
			}
			else if((curCount >= lineCount/4) && (curCount < (lineCount/2)))
			{
				bw2.write(curLine + "\n");
			}
			else if((curCount >= lineCount/2) && (curCount < ((3*lineCount)/4)))
			{
				bw3.write(curLine + "\n");
			}
			else 
			{
				bw4.write(curLine + "\n");
			}
		}
		bw1.close();
		bw2.close();
		bw3.close();
		bw4.close();
	}
}
