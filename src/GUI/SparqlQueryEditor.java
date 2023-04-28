package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SparqlQueryEditor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Boolean disposed = false;
	private JTextPane txtSparqlQuery;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SparqlQueryEditor dialog = new SparqlQueryEditor();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SparqlQueryEditor() {
		setBounds(100, 100, 617, 407);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel pnlMain = new JPanel();
			pnlMain.setLayout(new BorderLayout());
			contentPanel.add(pnlMain, BorderLayout.CENTER);
			{
				txtSparqlQuery = new JTextPane();
				pnlMain.add(txtSparqlQuery, BorderLayout.CENTER);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
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
					public void mouseClicked(MouseEvent e) {
						disposed = true;
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);
		}
	}
	
	public String getQuery()
	{
		return txtSparqlQuery.getText().replaceAll("\n", " \n");
	}
	
	public void setQuery(String query)
	{
		txtSparqlQuery.setText(query);
	}
	
	public Boolean GetDisposed()
	{
		return disposed;
	}

}
