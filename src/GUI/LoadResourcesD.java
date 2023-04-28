package GUI;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FileChooserUI;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;

public class LoadResourcesD extends JDialog {

	private JFrame frame;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtAddressOntology;
	private Boolean disposed = false;
	private JTextField txtAddressData;
	private JTextField txtAddressSparqlEndpoint;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LoadResourcesD dialog = new LoadResourcesD();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LoadResourcesD() {
		setTitle("Load Resources");
		setBounds(100, 100, 698, 428);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel pnlOntology = new JPanel();
		pnlOntology.setBounds(15, 16, 620, 75);
		pnlOntology.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ontolgy File(s)", TitledBorder.LEFT, TitledBorder.TOP, null, SystemColor.desktop));
		contentPanel.add(pnlOntology);
		pnlOntology.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("File 1:");
		lblNewLabel.setBounds(15, 28, 69, 20);
		pnlOntology.add(lblNewLabel);
		
		txtAddressOntology = new JTextField();
		txtAddressOntology.setBounds(100, 25, 375, 26);
		pnlOntology.add(txtAddressOntology);
		txtAddressOntology.setColumns(10);
		
		JButton btnBrowseOnt = new JButton("Browse ...");
		btnBrowseOnt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FileDialog fd = new FileDialog(frame, "Select ontology file ...", FileDialog.LOAD);
				//fd.setFile("*.rdf;*.rdfs;*.owl;*.plowl");
				fd.setVisible(true);// show();				
				txtAddressOntology.setText(fd.getDirectory() + fd.getFile());				
			}
		});
		btnBrowseOnt.setBounds(490, 22, 115, 29);
		pnlOntology.add(btnBrowseOnt);
		
		JPanel pnlData = new JPanel();
		pnlData.setLayout(null);
		pnlData.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Data File(s)", TitledBorder.LEFT, TitledBorder.TOP, null, SystemColor.desktop));
		pnlData.setBounds(15, 107, 620, 75);
		contentPanel.add(pnlData);
		
		JLabel label = new JLabel("File 1:");
		label.setBounds(15, 28, 69, 20);
		pnlData.add(label);
		
		txtAddressData = new JTextField();
		txtAddressData.setText("C:\\Programming\\sedan\\DataFiles\\University0_0.owl");
		txtAddressData.setColumns(10);
		txtAddressData.setBounds(100, 25, 375, 26);
		pnlData.add(txtAddressData);
		
		JButton btnBrowseData = new JButton("Browse ...");
		btnBrowseData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				FileDialog fd = new FileDialog(frame, "Select data file ...", FileDialog.LOAD);
				//fd.setFile("*.rdf;*.rdfs;*.owl;*.plowl");
				fd.setVisible(true);// show();				
				txtAddressData.setText(fd.getDirectory() + fd.getFile());
			}
		});
		btnBrowseData.setBounds(490, 22, 115, 29);
		pnlData.add(btnBrowseData);
		
		JPanel pnlSparqlEndpoint = new JPanel();
		pnlSparqlEndpoint.setLayout(null);
		pnlSparqlEndpoint.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "SPARQL Endpoin", TitledBorder.LEFT, TitledBorder.TOP, null, SystemColor.desktop));
		pnlSparqlEndpoint.setBounds(15, 198, 620, 96);
		contentPanel.add(pnlSparqlEndpoint);
		
		JLabel lblAddress = new JLabel("Address 1:");
		lblAddress.setBounds(15, 28, 89, 20);
		pnlSparqlEndpoint.add(lblAddress);
		
		txtAddressSparqlEndpoint = new JTextField();
		txtAddressSparqlEndpoint.setText("http://localhost:2020/sparql");
		txtAddressSparqlEndpoint.setColumns(10);
		txtAddressSparqlEndpoint.setBounds(100, 25, 505, 26);
		pnlSparqlEndpoint.add(txtAddressSparqlEndpoint);
		
		JLabel lblHttp = new JLabel("* http://localhost:2020/sparql");
		lblHttp.setForeground(Color.GRAY);
		lblHttp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHttp.setBounds(100, 60, 236, 20);
		pnlSparqlEndpoint.add(lblHttp);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						disposed = false;
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						disposed = true;
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public String GetOntologyAddress()
	{
		return txtAddressOntology.getText();
	}
	
	public String GetDataAddress()
	{
		return txtAddressData.getText();
	}
	
	public String GetSparqlEndpointAddress()
	{
		return txtAddressSparqlEndpoint.getText();
	}
	
	public Boolean GetDisposed()
	{
		return disposed;
	}
}
