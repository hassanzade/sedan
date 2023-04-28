package GUI;

import java.awt.EventQueue;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.SystemColor;

import javax.swing.border.TitledBorder;

import arq.load;
import ontology.MyMethods;

import javax.swing.UIManager;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.Label;

import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JPopupMenu;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JRadioButton;
import javax.swing.JList;

import java.awt.TextField;
import java.awt.TextArea;
import java.net.MalformedURLException;


public class MainFrame extends JFrame{

	private JFrame frmSedanSemanticsbasedData;
	private JTextField txtSparqlQuery;
	private JTextField txtSubject;
	private JTextField txtPredicate;
	private JTextField txtObject;
	private JList listDtAnswers;
	private JRadioButton rdbTripleInQuestion;
	private JRadioButton rdbSparqlQuery;
	private TextArea txtConsole;
	
	private Model ontologyModel = ModelFactory.createDefaultModel();
	private Model dataModel = ModelFactory.createDefaultModel();
	private String sparqlEndpoint;
	
	MyMethods myMethods = new MyMethods();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frmSedanSemanticsbasedData.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		
		initialize();
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSedanSemanticsbasedData = new JFrame();
		frmSedanSemanticsbasedData.setTitle("SeDan: Semantics-based Data Analytics");
		frmSedanSemanticsbasedData.setBounds(100, 100, 985, 741);
		frmSedanSemanticsbasedData.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSedanSemanticsbasedData.getContentPane().setLayout(null);
		

		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Query Panel", TitledBorder.LEFT, TitledBorder.TOP, null, SystemColor.desktop));
		panel.setBackground(SystemColor.menu);
		panel.setBounds(15, 16, 930, 173);
		frmSedanSemanticsbasedData.getContentPane().add(panel);
		
		JLabel label = new JLabel("Query:");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(620, 49, 68, 27);
		label.setVisible(false);
		panel.add(label);
		
		JButton btnProcess = new JButton("Process");
		btnProcess.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnProcess.setBounds(682, 23, 105, 29);
		btnProcess.setVisible(false);
		panel.add(btnProcess);
		
		txtSparqlQuery = new JTextField();
		txtSparqlQuery.setText("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\nPREFIX ub: <http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#>\r\nSELECT ?X\t\r\nWHERE\r\n{?X rdf:type ub:GraduateStudent .\r\n  ?X ub:takesCourse\r\n<http://www.Department0.University0.edu/GraduateCourse0>}");
		txtSparqlQuery.setHorizontalAlignment(SwingConstants.LEFT);
		txtSparqlQuery.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtSparqlQuery.setColumns(10);
		txtSparqlQuery.setBackground(Color.WHITE);
		txtSparqlQuery.setBounds(165, 25, 445, 25);
		panel.add(txtSparqlQuery);
		
		rdbSparqlQuery = new JRadioButton("SPARQL Query:");
		rdbSparqlQuery.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbSparqlQuery.setBounds(10, 24, 155, 29);
		rdbSparqlQuery.setActionCommand("SPARQL");
		rdbSparqlQuery.setSelected(true);
		panel.add(rdbSparqlQuery);
		
		rdbTripleInQuestion = new JRadioButton("Triple in question:");
		rdbTripleInQuestion.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbTripleInQuestion.setBounds(10, 64, 155, 29);
		rdbTripleInQuestion.setActionCommand("Triple");
		panel.add(rdbTripleInQuestion);
		
		ButtonGroup rdbtnGroup = new ButtonGroup();
		rdbtnGroup.add(rdbSparqlQuery);
		rdbtnGroup.add(rdbTripleInQuestion);
		
		JButton btnAsk = new JButton("Ask");
		btnAsk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String queryMode = "";
				if(rdbSparqlQuery.isSelected())
				{
					queryMode = rdbSparqlQuery.getActionCommand();
				}
				else if(rdbTripleInQuestion.isSelected())
					queryMode = rdbTripleInQuestion.getActionCommand();
				try {
					RunQuery(queryMode);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Exception: " + e.getMessage());
				}
			}
		});
		btnAsk.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAsk.setBounds(787, 124, 122, 29);
		panel.add(btnAsk);
		
		JLabel lblEquivalentTriple = new JLabel("Equivalent Triple:");
		lblEquivalentTriple.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEquivalentTriple.setBounds(630, 69, 157, 27);
		lblEquivalentTriple.setVisible(false);
		panel.add(lblEquivalentTriple);
		
		txtSubject = new JTextField();
		txtSubject.setToolTipText("Subject");
		txtSubject.setHorizontalAlignment(SwingConstants.LEFT);
		txtSubject.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtSubject.setColumns(10);
		txtSubject.setBackground(Color.WHITE);
		txtSubject.setBounds(165, 66, 144, 25);
		panel.add(txtSubject);
		
		txtPredicate = new JTextField();
		txtPredicate.setToolTipText("Subject");
		txtPredicate.setHorizontalAlignment(SwingConstants.LEFT);
		txtPredicate.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtPredicate.setColumns(10);
		txtPredicate.setBackground(Color.WHITE);
		txtPredicate.setBounds(315, 66, 144, 25);
		panel.add(txtPredicate);
		
		txtObject = new JTextField();
		txtObject.setToolTipText("Subject");
		txtObject.setHorizontalAlignment(SwingConstants.LEFT);
		txtObject.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtObject.setColumns(10);
		txtObject.setBackground(Color.WHITE);
		txtObject.setBounds(466, 66, 144, 25);
		panel.add(txtObject);
		
		JPanel panel_1 = new JPanel();
		//panel_1.disable();
		panel_1.setBorder(new TitledBorder(null, "Plausible Patterns", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 110, 762, 54);
		panel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Generalization");
		chckbxNewCheckBox.setSelected(true);
		chckbxNewCheckBox.setBounds(20, 21, 120, 23);
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_1.add(chckbxNewCheckBox);
		
		JCheckBox chckbxSpecialiation = new JCheckBox("Specialization");
		chckbxSpecialiation.setSelected(true);
		chckbxSpecialiation.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxSpecialiation.setBounds(150, 21, 120, 23);
		panel_1.add(chckbxSpecialiation);
		
		JCheckBox chckbxSimilarity = new JCheckBox("Similarity");
		chckbxSimilarity.setSelected(true);
		chckbxSimilarity.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxSimilarity.setBounds(280, 21, 90, 23);
		panel_1.add(chckbxSimilarity);
		
		JCheckBox chckbxDissimilarity = new JCheckBox("Dissimilarity");
		chckbxDissimilarity.setSelected(true);
		chckbxDissimilarity.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxDissimilarity.setBounds(380, 21, 110, 23);
		panel_1.add(chckbxDissimilarity);
		
		JCheckBox chckbxInterpolation = new JCheckBox("Interpolation");
		chckbxInterpolation.setSelected(true);
		chckbxInterpolation.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxInterpolation.setBounds(500, 21, 120, 23);
		panel_1.add(chckbxInterpolation);
		
		JCheckBox chckbxAFortiori = new JCheckBox("A fortiori");
		chckbxAFortiori.setSelected(true);
		chckbxAFortiori.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxAFortiori.setBounds(620, 21, 120, 23);
		panel_1.add(chckbxAFortiori);
		
		JButton btnExpandQuery = new JButton("");
		btnExpandQuery.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ExpandQueryEditor();
			}
		});
		btnExpandQuery.setIcon(new ImageIcon(MainFrame.class.getResource("/com/sun/javafx/scene/web/skin/AlignLeft_16x16_JFX.png")));
		btnExpandQuery.setBounds(618, 25, 27, 23);
		panel.add(btnExpandQuery);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "Answer(s)", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(15, 205, 930, 319);
		frmSedanSemanticsbasedData.getContentPane().add(panel_2);
		
		JPanel pnlDtAnswers = new JPanel();
		pnlDtAnswers.setLayout(null);
		pnlDtAnswers.setBorder(new TitledBorder(null, "Determinsitic Answer(s)", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlDtAnswers.setBounds(10, 20, 450, 292);
		panel_2.add(pnlDtAnswers);
		
		listDtAnswers = new JList();
		listDtAnswers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//CheckListDtAnswersIndex();
			}
		});
		
		JScrollPane scrollPaneDtAnswers = new JScrollPane();
		scrollPaneDtAnswers.setBounds(12, 21, 429, 260);
		pnlDtAnswers.setLayout(new BorderLayout());
		scrollPaneDtAnswers.setViewportView(listDtAnswers);
		pnlDtAnswers.add(scrollPaneDtAnswers);
		
		
		JLabel label_5 = new JLabel("---");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setForeground(SystemColor.menu);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_5.setBounds(10, 336, 273, 29);
		panel_2.add(label_5);
		
		JPanel pnlPlAnswers = new JPanel();
		pnlPlAnswers.setBounds(465, 20, 450, 292);
		panel_2.add(pnlPlAnswers);
		pnlPlAnswers.setLayout(null);
		pnlPlAnswers.setBorder(new TitledBorder(null, "Plausible Answer(s)", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		//String[] stringArray = {"Testing\nsecond line", "This", "Stuff"};
		JList listPlAnswers = new JList();
		listPlAnswers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//CheckListDtAnswersIndex();
			}
		});
		
		JScrollPane scrollPanePlAnswers = new JScrollPane();
		scrollPanePlAnswers.setBounds(15, 0, 243, 104);
		pnlPlAnswers.setLayout(new BorderLayout());
		scrollPanePlAnswers.setViewportView(listPlAnswers);	
		pnlPlAnswers.add(scrollPanePlAnswers);
		
		JPanel panel_3 = new JPanel();
		//panel_2.
		panel_3.setBounds(15, 533, 930, 137);
		panel_3.setBorder(new TitledBorder(null, "Console", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmSedanSemanticsbasedData.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		txtConsole = new TextArea();
		txtConsole.setBounds(10, 21, 910, 106);		
		panel_3.add(txtConsole);
		
		JMenuBar mbMain = new JMenuBar();
		mbMain.setForeground(UIManager.getColor("Button.shadow"));
		mbMain.setBackground(UIManager.getColor("Button.darkShadow"));
		frmSedanSemanticsbasedData.setJMenuBar(mbMain);
		
		JMenu mnFile = new JMenu("File      ");
		mbMain.add(mnFile);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mnFile.add(mntmOpen);
		
		JMenuItem mntmLoadResources = new JMenuItem("Load Resources");
		mntmLoadResources.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoadResources();
			}
			
		});
		mntmLoadResources.setIcon(new ImageIcon("C:\\Programming\\sedan\\images\\load_data2.png"));
		mnFile.add(mntmLoadResources);
		
		JMenu mnData = new JMenu("Data      ");
		mnData.setBackground(Color.LIGHT_GRAY);
		mbMain.add(mnData);
		
		JMenuItem mntmLoad = new JMenuItem("Load");
		mntmLoad.setIcon(new ImageIcon("..\\images\\load_data2.png"));
		mnData.add(mntmLoad);
	}
	
	protected void ExpandQueryEditor() {
		// TODO Auto-generated method stub
		SparqlQueryEditor queryEditor = new SparqlQueryEditor();
		queryEditor.setModalityType(Dialog.DEFAULT_MODALITY_TYPE.TOOLKIT_MODAL);
		queryEditor.setModal(true);
		queryEditor.setDefaultCloseOperation(HIDE_ON_CLOSE);
		queryEditor.setQuery(txtSparqlQuery.getText());
		queryEditor.setVisible(true);
		
//		if(!loadRes.GetDisposed())
//			JOptionPane.showMessageDialog(null, loadRes.GetOntologyAddress() + 
//					"\n" + loadRes.GetDataAddress() + "\n" + loadRes.GetSparqlEndpointAddress(), "Message", JOptionPane.OK_OPTION);
		
		if(!queryEditor.GetDisposed())
			txtSparqlQuery.setText(queryEditor.getQuery());
	}

	protected void RunQuery(String queryMode) throws MalformedURLException {
		// TODO Auto-generated method stub
		QuerySolution solution;
		DefaultListModel model = new DefaultListModel();
		listDtAnswers.setModel(model);
		
		switch(queryMode) 
		{
			case "SPARQL":
				System.out.println("Entered Sparql exceute");
				ResultSet results = myMethods.ExecuteQueryOverModel(txtSparqlQuery.getText(), dataModel);
				while(results.hasNext())
				{
					System.out.println("entered while");
					solution = results.nextSolution() ;
					model.addElement(solution);
					//Resource subj = (Resource) solution.get("X");
					//model.addElement(subj);
				}
				listDtAnswers.setModel(model);
				
				// Run SPQRQL query over the endpoint
				ResultSet resultsSparql = null;
				if(sparqlEndpoint.length() > 0 && !sparqlEndpoint.isEmpty())
					System.out.println("Run SPQRQL query over the endpoint");
					resultsSparql = myMethods.ExecuteSelectQueryOverSparqlEndpoint(txtSparqlQuery.getText(), sparqlEndpoint);
					txtConsole.setText(myMethods.ResultSetToString(resultsSparql));
				break;
			case "Triple":
				
				break;
		}
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	private void LoadResources() {
		// TODO Auto-generated method stub
		LoadResourcesD loadRes = new LoadResourcesD();
		loadRes.setModalityType(Dialog.DEFAULT_MODALITY_TYPE.TOOLKIT_MODAL);
		loadRes.setModal(true);
		loadRes.setDefaultCloseOperation(HIDE_ON_CLOSE);
		loadRes.setVisible(true);
		
//		if(!loadRes.GetDisposed())
//			JOptionPane.showMessageDialog(null, loadRes.GetOntologyAddress() + 
//					"\n" + loadRes.GetDataAddress() + "\n" + loadRes.GetSparqlEndpointAddress(), "Message", JOptionPane.OK_OPTION);
		
		if(!loadRes.GetDisposed())
			try {
				MyMethods myMethods = new MyMethods();
				//if()
				ontologyModel = myMethods.ReadModel(loadRes.GetOntologyAddress());
				dataModel = myMethods.ReadModel(loadRes.GetDataAddress());
				sparqlEndpoint = loadRes.GetSparqlEndpointAddress();
				
				String message = "";
				if(ontologyModel != null)
					message += "Ontology file is loaded successfully. \n";
				else 
					message += "Ontology file is empty. \n";
				if(dataModel != null)
					message += "Data file is loaded successfully. \n";
				else 
					message += "Data file is empty. \n";
				if(sparqlEndpoint.length() > 8 && !sparqlEndpoint.equals("http://"))
					message += "SPARQL endpoint is set successfully. \n";
				else 
					message += "SPARQL endpoint is not set. \n";
				
				JOptionPane.showMessageDialog(null, message, "Exception", JOptionPane.INFORMATION_MESSAGE);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage(), "Exception", JOptionPane.OK_OPTION);
			}
	}
	
	private void CheckListDtAnswersIndex() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, listDtAnswers.getSelectedValue() + "\n" + listDtAnswers.getSelectedIndex() + "\n" + 
										listDtAnswers.getSelectedIndices(), "Exception", JOptionPane.INFORMATION_MESSAGE);
		listDtAnswers.getSelectedValue();
	}
}
