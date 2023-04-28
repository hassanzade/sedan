package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.TextArea;

import javax.swing.JTextPane;
import javax.management.StandardEmitterMBean;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

import ontology.MyMethods;
import settings.Settings;
import sparql.QueryTransformation;
import sparql.SparqlQuery;

import org.apache.jena.query.ResultSet;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.border.TitledBorder;
import javax.swing.JCheckBox;

import java.awt.Color;

import javax.swing.border.BevelBorder;
import javax.swing.JRadioButton;
import javax.swing.UIManager;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.DropMode;
import javax.swing.JSpinner;

public class SparqlQueryWindow extends JFrame {

	long starttime01, starttime02, endtime, duration;
	
	private JPanel contentPane;
	private TextArea txt_Query;
	private TextArea txt_Console;
	private JComboBox cmb_SparqlEndpointAddress;
	private JTextField txt_Subject;
	private JTextField txt_Object;
	private JComboBox cmb_Predicate;
	private JButton btn_ValidateSubject;
	private JButton btn_ValidateObject;
	private JCheckBox chk_Gen;
	private JCheckBox chk_Spec;
	private JCheckBox chk_Sim;
	private JCheckBox chk_DisSim;
	private JCheckBox chk_Intp;
	private JCheckBox chk_Afort;
	private JRadioButton rdb_SubjectToRwr;
	private JRadioButton rdb_ObjectToRwr;
	QueryTransformation queryTrans = new QueryTransformation();
	JSpinner txt_MaxDepthofPlausibility;
	
	MyMethods myMethods = new MyMethods();
	
	private String queryType;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SparqlQueryWindow frame = new SparqlQueryWindow();	
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
	public SparqlQueryWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1248, 676);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SPARQL Endpoint:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 25, 119, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblSparqlQuery = new JLabel("SPARQL Query:");
		lblSparqlQuery.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSparqlQuery.setBounds(10, 61, 94, 14);
		contentPane.add(lblSparqlQuery);
		
		txt_Query = new TextArea();
		txt_Query.setFont(new Font("Calibri Light", Font.BOLD, 14));
		txt_Query.setText("SELECT * \r\nWHERE {?Subject ?Predicate ?Object}\r\nLIMIT 10\r\n");
		txt_Query.setBounds(118, 180, 605, 128);
		contentPane.add(txt_Query);
		
		JButton btn_Submit = new JButton("Submit");
		btn_Submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RunQuery();
			}
		});
		btn_Submit.setBounds(633, 312, 90, 25);
		contentPane.add(btn_Submit);
		
		cmb_SparqlEndpointAddress = new JComboBox();
		cmb_SparqlEndpointAddress.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		cmb_SparqlEndpointAddress.setEditable(true);
		cmb_SparqlEndpointAddress.setBounds(119, 20, 604, 25);
		contentPane.add(cmb_SparqlEndpointAddress);
		
		cmb_Predicate = new JComboBox();
		cmb_Predicate.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		cmb_Predicate.setEditable(true);
		cmb_Predicate.setBounds(118, 85, 573, 26);
		contentPane.add(cmb_Predicate);
		
		txt_Subject = new JTextField();
		txt_Subject.setText(Settings.LAST_QUERIED_SUBJECT);
		txt_Subject.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				ElementToChangeActivation();
			}
		});
		txt_Subject.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txt_Subject.setBounds(119, 56, 572, 26);
		contentPane.add(txt_Subject);
		txt_Subject.setColumns(10);
		
		txt_Object = new JTextField();
		txt_Object.setText(Settings.LAST_QUERIED_OBJECT);
		txt_Object.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				ElementToChangeActivation();
			}
		});
		txt_Object.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txt_Object.setColumns(10);
		txt_Object.setBounds(118, 114, 573, 26);
		contentPane.add(txt_Object);
		
		JButton btn_ProcessQuery = new JButton("Process");
		btn_ProcessQuery.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BuildInitialQuery();
			}
		});
		btn_ProcessQuery.setBounds(633, 146, 90, 25);
		contentPane.add(btn_ProcessQuery);
		
		btn_ValidateSubject = new JButton("@");
		btn_ValidateSubject.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					txt_Subject.setText(ValidateSubject(txt_Subject.getText(), "Subject"));
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btn_ValidateSubject.setBounds(696, 56, 25, 25);
		contentPane.add(btn_ValidateSubject);
		
		btn_ValidateObject = new JButton("@");
		btn_ValidateObject.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					txt_Object.setText(ValidateSubject(txt_Object.getText(), "Object"));
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btn_ValidateObject.setBounds(696, 114, 25, 25);
		contentPane.add(btn_ValidateObject);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Plausible Reasoning Settings", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(118, 342, 605, 146);
		contentPane.add(panel);
		
		JButton btn_Rewrite = new JButton("Rewrite");
		btn_Rewrite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_Rewrite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					Settings.MAX_DEPTH_OF_PLAUSIBILITY = Integer.parseInt(txt_MaxDepthofPlausibility.getValue().toString());
					//ElementToChangeActivation();
					starttime01 = System.nanoTime(); 
					RewriteQuery();
					starttime02 = System.nanoTime();
					myMethods.StoreExecutionTime(starttime01, starttime02, "TotalRewriteTime_WithoutPrint");
					
					System.out.println("Printing Plausible Results ...");
					PrintPlausibleResults();
					starttime02 = System.nanoTime();
					myMethods.StoreExecutionTime(starttime01, starttime02, "TotalRewriteTime_WithPrint");
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btn_Rewrite.setBounds(500, 110, 90, 25);
		panel.add(btn_Rewrite);
		
		ButtonGroup bG = new ButtonGroup();
	     
	     JPanel panel_2 = new JPanel();
	     panel_2.setBounds(10, 23, 349, 80);
	     panel.add(panel_2);
	     panel_2.setBorder(new TitledBorder(null, "Patterns", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	     panel_2.setLayout(null);
	     
	     chk_Gen = new JCheckBox("Generalization");
	     chk_Gen.setBounds(7, 22, 120, 23);
	     panel_2.add(chk_Gen);
	     chk_Gen.setSelected(true);
	     chk_Gen.setFont(new Font("Tahoma", Font.BOLD, 12));
	     
	     chk_DisSim = new JCheckBox("Dissimilarity");
	     chk_DisSim.setBounds(7, 48, 110, 23);
	     chk_DisSim.setSelected(true);
	     panel_2.add(chk_DisSim);
	     chk_DisSim.setFont(new Font("Tahoma", Font.BOLD, 12));
	     
	     chk_Spec = new JCheckBox("Specialization");
	     chk_Spec.setBounds(128, 22, 120, 23);
	     chk_Spec.setSelected(true);
	     panel_2.add(chk_Spec);
	     chk_Spec.setFont(new Font("Tahoma", Font.BOLD, 12));
	     
	     chk_Intp = new JCheckBox("Interpolation");
	     chk_Intp.setBounds(128, 48, 120, 23);
	     chk_Intp.setSelected(true);
	     panel_2.add(chk_Intp);
	     chk_Intp.setFont(new Font("Tahoma", Font.BOLD, 12));
	     
	     chk_Sim = new JCheckBox("Similarity");
	     chk_Sim.setSelected(true);
	     chk_Sim.setBounds(248, 22, 90, 23);
	     panel_2.add(chk_Sim);
	     chk_Sim.setFont(new Font("Tahoma", Font.BOLD, 12));
	     
	     chk_Afort = new JCheckBox("A fortiori");
	     chk_Afort.setBounds(248, 48, 90, 23);
	     chk_Afort.setSelected(true);
	     panel_2.add(chk_Afort);
	     chk_Afort.setFont(new Font("Tahoma", Font.BOLD, 12));
	     
	     JPanel panel_3 = new JPanel();
	     panel_3.setBounds(362, 23, 216, 55);
	     panel.add(panel_3);
	     panel_3.setBorder(new TitledBorder(null, "Element to Change", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	     panel_3.setLayout(null);
	     
	     rdb_SubjectToRwr = new JRadioButton("Subject");
	     rdb_SubjectToRwr.setBounds(11, 20, 85, 29);
	     panel_3.add(rdb_SubjectToRwr);
	     rdb_SubjectToRwr.setFont(new Font("Tahoma", Font.BOLD, 12));
	     bG.add(rdb_SubjectToRwr);
	     
	     rdb_ObjectToRwr = new JRadioButton("Object");
	     rdb_ObjectToRwr.setBounds(103, 20, 80, 29);
	     panel_3.add(rdb_ObjectToRwr);
	     rdb_ObjectToRwr.setSelected(true);
	     
	     	rdb_ObjectToRwr.setFont(new Font("Tahoma", Font.BOLD, 12));
	     	bG.add(rdb_ObjectToRwr);
	     	
	     	JLabel lblNewLabel_1 = new JLabel("Max Depth of Plausibility:");
	     	lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
	     	lblNewLabel_1.setBounds(365, 83, 170, 20);
	     	panel.add(lblNewLabel_1);
	     	
	     	txt_MaxDepthofPlausibility = new JSpinner();
	     	txt_MaxDepthofPlausibility.setFont(new Font("Tahoma", Font.BOLD, 12));
	     	txt_MaxDepthofPlausibility.setBounds(530, 83, 35, 20);
	     	txt_MaxDepthofPlausibility.setValue((Integer.valueOf(Settings.MAX_DEPTH_OF_PLAUSIBILITY)));
	     	panel.add(txt_MaxDepthofPlausibility);
	     
		TextArea textArea = new TextArea();
		textArea.setFont(new Font("Calibri Light", Font.BOLD, 14));
		textArea.setBounds(118, 494, 605, 88);
		contentPane.add(textArea);
		
		JLabel lblPlausiblyExpandedQuery = new JLabel("Expanded Query:");
		lblPlausiblyExpandedQuery.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPlausiblyExpandedQuery.setBounds(10, 498, 102, 14);
		contentPane.add(lblPlausiblyExpandedQuery);
		
		JButton btn_Submit_Plausbile = new JButton("Submit");
		btn_Submit_Plausbile.setBounds(633, 585, 90, 25);
		contentPane.add(btn_Submit_Plausbile);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Console", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(733, 10, 480, 594);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		txt_Console = new TextArea();
		txt_Console.setBounds(10, 39, 460, 545);
		panel_1.add(txt_Console);
		txt_Console.setFont(new Font("Calibri", Font.BOLD, 13));
		
		JButton btn_MaximizeConsole = new JButton("<->");
		btn_MaximizeConsole.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ConsoleScreen console = new ConsoleScreen();
				console.setConsole(txt_Console.getText());
				console.setSize(new Dimension(600, 400));
				console.setVisible(true);
			}
		});
		btn_MaximizeConsole.setFont(new Font("Tahoma", Font.BOLD, 8));
		btn_MaximizeConsole.setBounds(362, 12, 65, 23);
		panel_1.add(btn_MaximizeConsole);
		
		JButton btn_ClearConsole = new JButton("\u00D7");
		btn_ClearConsole.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txt_Console.setText(null);
			}
		});
		btn_ClearConsole.setFont(new Font("Tahoma", Font.BOLD, 8));
		btn_ClearConsole.setBounds(430, 12, 40, 23);
		panel_1.add(btn_ClearConsole);
		
		this.load_SparqlEndpointAddresses();
		this.load_Predicates();
	}


	private void load_SparqlEndpointAddresses(){
		cmb_SparqlEndpointAddress.addItem(Settings.SPARQL_ENDPOINT_ADDRESSONLY + "/repositories/SeDan");
		cmb_SparqlEndpointAddress.addItem(Settings.SPARQL_ENDPOINT_ADDRESSONLY + "/repositories/tempstorage");
		cmb_SparqlEndpointAddress.addItem(Settings.SPARQL_ENDPOINT_ADDRESSONLY + "/repositories/DrugBank");
		cmb_SparqlEndpointAddress.addItem(Settings.SPARQL_ENDPOINT_ADDRESSONLY + "/repositories/DiseaseOntology");

		
		cmb_SparqlEndpointAddress.addItem("http://localhost:7200/repositories/SeDan");
		cmb_SparqlEndpointAddress.addItem("http://localhost:7200/repositories/tempstorage");
		cmb_SparqlEndpointAddress.addItem("http://localhost:7200/repositories/DrugBank");
		cmb_SparqlEndpointAddress.addItem("http://localhost:7200/repositories/DiseaseOntology");
		cmb_SparqlEndpointAddress.addItem("http://localhost:2020/sparql");
		cmb_SparqlEndpointAddress.addItem("http://localhost:7200/sparql");
		

	};
	
	private void load_Predicates(){
		cmb_Predicate.addItem("http://semmed.org/property/TREATS");
		cmb_Predicate.addItem("http://semmed.org/property/CAUSES");
		//cmb_Predicate.setSelectedItem(Settings.LAST_QUERIED_PREDICATE);
	};
	
	private void RunQuery() {
		// TODO Auto-generated method stub
		//txt_SparqlEndpointAddress.setText(cmb_SparqlEndpointAddress.getSelectedItem().toString());
		ElementToChangeActivation();
		MyMethods myMethods = new MyMethods();
		Settings.LAST_QUERIED_SUBJECT = txt_Subject.getText();
		Settings.LAST_QUERIED_OBJECT = txt_Object.getText();
		Settings.LAST_QUERIED_PREDICATE = cmb_Predicate.getSelectedItem().toString();
		
		try
		{
			if(queryType.equals("Select"))
			{
				starttime01 = System.nanoTime();
				ResultSet resultsSparql = myMethods.ExecuteSelectQueryOverSparqlEndpoint(txt_Query.getText(), cmb_SparqlEndpointAddress.getSelectedItem().toString());
				endtime = System.nanoTime();
				myMethods.StoreExecutionTime(starttime01, endtime, "Select;No;QueryExecutionTime");
				if(resultsSparql != null)
					txt_Console.append("Deductively inferred results: \n" + myMethods.ResultSetToString(resultsSparql));
				else 
					txt_Console.append("No Result.");
			}
			else if(queryType.equals("Ask"))
			{
				starttime01 = System.nanoTime();
				Boolean resultsSparql = myMethods.ExecuteAskQueryOverSparqlEndpoint(txt_Query.getText(), cmb_SparqlEndpointAddress.getSelectedItem().toString());
				endtime = System.nanoTime();
				myMethods.StoreExecutionTime(starttime01, endtime, "Ask;No;QueryExecutionTime");
				if(resultsSparql != null && resultsSparql)
					txt_Console.append("Result is: Yes \n");
				else if(resultsSparql != null && !resultsSparql)
					txt_Console.append("Result is: No \n");
				else 
					txt_Console.append("No Result.");
			}
		}
		catch(Exception ex)
		{
			txt_Console.append("Exception in SparqlQueryWindow->RunQuery: \n" + ex.getMessage());
		}
	}
	
	private String ValidateSubject(String EntityInQuery, String Location) throws MalformedURLException {
		// TODO Auto-generated method stub
		String result = "";		
		StatementValidator statementValidator = new StatementValidator();
		statementValidator.entityInQuery = EntityInQuery;
		statementValidator.location = Location;
		statementValidator.RunQuery(statementValidator.QueryBuilder(EntityInQuery, Location), cmb_SparqlEndpointAddress.getSelectedItem().toString());
		statementValidator.setSize(600, 500);
		statementValidator.setModal(true);
		statementValidator.setVisible(true);
		result = statementValidator.selectedEntity;
		
		return result;
	}
	
	private void BuildInitialQuery()
	{
		ElementToChangeActivation();
		//txtConsole.setText("hello");
		String subject = txt_Subject.getText();
		String predicate = cmb_Predicate.getSelectedItem().toString();
		String object = txt_Object.getText();
		
		if(!subject.contains("?") && !object.contains("?") && !predicate.contains("?"))
		{
			// it is an ASK question
			SparqlQuery sparqlQuery = new SparqlQuery();
			sparqlQuery.Statements = new String[]{"<" + subject + "> <" + predicate + "> <" + object + ">"};
			
			txt_Query.setText(sparqlQuery.BuildSimpleAskQeuryString());
			queryType = "Ask";
		}
		else
		{
			// it is a SELECT question					
			String vars = "";
			if(subject.contains("?"))
				vars += txt_Subject.getText() + ",";
			else 
				subject = "<" + subject + ">";
			
			if(predicate.contains("?"))
				vars += cmb_Predicate.getSelectedItem().toString() + ",";
			else 
				predicate = "<" + predicate + ">";
			
			if(object.contains("?"))
				vars += txt_Object.getText();
			else
				object = "<" + object + ">";
			
			if(vars.length() == 0)
				vars += "*";
			
			SparqlQuery sparqlQuery = new SparqlQuery();
			sparqlQuery.Variables = vars.split(",");
			sparqlQuery.Statements = new String[]{subject + " " + predicate + " " + object};
			
			txt_Query.setText(sparqlQuery.BuildSimpleSelectQueryString());
			queryType = "Select";
		}
	}
	
	private void RewriteQuery() throws IOException {
		// TODO Auto-generated method stub
		queryTrans = new QueryTransformation();
		queryTrans.setOriginalQuery(txt_Query.getText());
		queryTrans.setQueryType(queryType);
		queryTrans.setOriginalQueryObject(txt_Object.getText());
		queryTrans.setOriginalQuerySubject(txt_Subject.getText());
		queryTrans.setOriginalQueryPredicate(cmb_Predicate.getSelectedItem().toString());
		queryTrans.setOriginalSparqlEndpoint(cmb_SparqlEndpointAddress.getSelectedItem().toString());
		queryTrans.setSelectedPlausiblePatterns(this.SelectedPlausiblePatterns());
		settings.Settings.SPARQL_ENDPOINT = cmb_SparqlEndpointAddress.getSelectedItem().toString();
		if(rdb_SubjectToRwr.isSelected())
			queryTrans.setElementToChange("Subject");
		else if(rdb_ObjectToRwr.isSelected())
			queryTrans.setElementToChange("Object");
		
		myMethods.StoreExecutionTime(0, 0, queryType +";" + txt_Subject.getText() + " " + cmb_Predicate.getSelectedItem().toString() + " " + txt_Object.getText() + ";" +
										queryTrans.getElementToChange() + ";" + this.SelectedPlausiblePatterns() + ";" + Settings.MAX_DEPTH_OF_PLAUSIBILITY);
		queryTrans.transformQuery();
	}
	
	private String SelectedPlausiblePatterns()
	{
		String Patterns = "";
		
		if(chk_Gen.isSelected())
			Patterns += "Gen,";
		if(chk_Spec.isSelected())
			Patterns += "Spec,";
		if(chk_Sim.isSelected())
			Patterns += "Sim,";
		if(chk_DisSim.isSelected())
			Patterns += "Dis,";
		if(chk_Intp.isSelected())
			Patterns += "Intp,";
		if(chk_Afort.isSelected())
			Patterns += "Afort,";
		
		return Patterns;
	}
	
	private void ElementToChangeActivation()
	{
		if(txt_Subject.getText().contains("?"))
		{
			rdb_SubjectToRwr.setEnabled(false);
			rdb_ObjectToRwr.setSelected(true);
		}
		else
		{
			rdb_SubjectToRwr.setEnabled(true);
			rdb_SubjectToRwr.setSelected(true);
		}
		
		if(txt_Object.getText().contains("?"))
		{
			rdb_ObjectToRwr.setEnabled(false);
			rdb_SubjectToRwr.setSelected(true);
		}
		else
		{
			rdb_ObjectToRwr.setEnabled(true);
			rdb_ObjectToRwr.setSelected(true);
		}
		
		if(txt_Subject.getText().contains("?") && txt_Object.getText().contains("?"))
		{
			rdb_SubjectToRwr.setSelected(false);
			rdb_ObjectToRwr.setSelected(false);
		}
			
	}
	

	private void PrintPlausibleResults() throws IOException {
		// TODO Auto-generated method stub
		String plausibleResults = new String(Files.readAllBytes(Paths.get(settings.Settings.OUTPUT_NEW_QUERIES_RESULTS_ADDRESS)));
		txt_Console.append(plausibleResults);
		
//		String curDepth = "0";
//		String curDepthAnswers = "";
//		String TotalAnswers = "";
//		Boolean readResults = true;
//		String[] results = plausibleResults.split("\n");
//		for(int i = 0; i < results.length; i++) // String result : plausibleResults.split("\n"))
//		{
//			if(results[i].contains("Depth of Plausibility"))
//			{
//				readResults = false;
//				String tmpDepth = results[i].replace(" ", "").split(":")[1];
//				if(!curDepth.trim().equals(tmpDepth.trim()))
//				{
//					if(curDepthAnswers.length() > 0)
//						TotalAnswers += curDepthAnswers;
//					curDepthAnswers = "";
//					curDepth = results[i].replace(" ", "").split(":")[1];
//				}
//			}
//			
//			if(results[i].contains("Results:"))
//			{
//				readResults = true;
//			}
//			else if(readResults && results[i].length() > 0)
//			{
//				if(!curDepthAnswers.trim().contains(curDepth + ";" + results[i] + ";"))
//				{
//					curDepthAnswers += curDepth + ";" + results[i] + ";\n";
//					
//				}
//			}
//		}
//		TotalAnswers += curDepthAnswers;
		
		//commenting prints
//		Files.write(Paths.get(settings.Settings.OUTPUT_NEW_QUERIES_RESULTS_ANSWERSONLY_ADDRESS), TotalAnswers.getBytes());
		
	}
}
