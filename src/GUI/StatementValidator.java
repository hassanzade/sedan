package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import ontology.MyMethods;

import com.jgoodies.forms.layout.FormSpecs;

import javax.swing.JList;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.awt.Font;

public class StatementValidator extends JDialog {
	public int tempInt = 0;
	public static String entityInQuery = "";
	public static String location = "";
	public String selectedEntity = "";
	public JList list_PossibleValues;
	JScrollPane scrollPane = new JScrollPane();
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			StatementValidator dialog = new StatementValidator();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			//dialog.RunQuery(dialog.QueryBuilder(entityInQuery, location));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public StatementValidator() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(
			new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		{
			list_PossibleValues = new JList();
			scrollPane.setViewportView(list_PossibleValues);
			list_PossibleValues.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			//contentPanel.add(list_PossibleValues, "1, 1, 8, 6, fill, fill");
			contentPanel.add(scrollPane, "1, 1, 8, 6, fill, fill");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Select");
				okButton.setFont(new Font("Tahoma", Font.BOLD, 16));
				okButton.setActionCommand("Select");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						if(list_PossibleValues.getSelectedValue() != null)
							selectedEntity = list_PossibleValues.getSelectedValue().toString();
						else
							selectedEntity = entityInQuery;
						dispose();
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setFont(new Font("Tahoma", Font.BOLD, 16));
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						selectedEntity = entityInQuery;
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public String QueryBuilder(String EntityInQuery, String Location)
	{
		//System.out.println("vars are:" + EntityInQuery + " " + location);
		String str_FILTER = "\n";
		String[] EntityInQueryAr = EntityInQuery.toLowerCase().replace("_", " ").replace("-", " ").split(" ");
		String Filter = "FILTER regex(lcase(str(?X)), str_LIKE).\n";
		String Query = "";
		for(int i = 0; i < EntityInQueryAr.length; i++)
		{
			str_FILTER += Filter.replace("str_LIKE", "\"" + EntityInQueryAr[i] + "\"");
		}
		
		if(Location == "Predicate")
		{
			
			Query = " SELECT DISTINCT ?X WHERE { ?s ?p ?o. str_FILTER} ";
		
			Query = Query.replace("str_FILTER", str_FILTER);
			
			if(Location == "Subject")
				Query = Query.replace("?X", "?s");
			else if(Location == "Object")
				Query = Query.replace("?X", "?o");
			else if(Location == "Predicate")
				Query = Query.replace("?X", "?p");
		}
		else
		{			
			Query = " SELECT DISTINCT ?X WHERE { {?X ?p ?o. str_FILTER} UNION {?s ?p ?X. str_FILTER} } ";		
			Query = Query.replace("str_FILTER", str_FILTER);
		}
		
//		System.out.println("Query is: " + Query);
		
		
		
		return Query;
	}

	public void RunQuery(String Query, String Endpoint) throws MalformedURLException
	{
		//System.out.println("Query is: " + Query);
		DefaultListModel model = new DefaultListModel();
		MyMethods myMethods = new MyMethods();
		ResultSet resultsSparql = myMethods.ExecuteSelectQueryOverSparqlEndpoint(Query, Endpoint);
		
		if(resultsSparql != null)
		{
			while(resultsSparql.hasNext())
			{
				QuerySolution qs = resultsSparql.next();
				Iterator<String> itVars = qs.varNames();
				String szVar = itVars.next().toString();
                String szVal = qs.get(szVar).toString();
                model.addElement(szVal);
			}
			list_PossibleValues.setModel(model);
		}
			//System.out.println(myMethods.ResultSetToString(resultsSparql));
		else 
		{
			model.addElement("No mathces found.");
			list_PossibleValues.setModel(model);
		}
	}
}
