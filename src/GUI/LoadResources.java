package GUI;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LoadResources  extends JDialog{

	private JFrame frame;
	private JTextField txtAddress;

	/**
	 * Launch the application.
	 */
	public static void OpenLoadResources() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoadResources window = new LoadResources();
					//window.setModalityType(DEFAULT_MODALITY_TYPE.TOOLKIT_MODAL);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoadResources() {
		initialize();
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(15, 16, 398, 113);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(31, 36, 69, 20);
		panel.add(lblNewLabel);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(129, 33, 146, 26);
		panel.add(txtAddress);
		txtAddress.setColumns(10);
	}
	
	public String GetAddress()
	{
		return txtAddress.getText();
	}
}
