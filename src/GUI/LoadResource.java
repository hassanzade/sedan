package GUI;

import java.awt.EventQueue;
import java.security.spec.ECField;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class LoadResource extends JFrame {
	public LoadResource() {
		setTitle("Load Resources");
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(15, 16, 600, 176);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(56, 36, 69, 20);
		panel.add(lblNewLabel);
	
	}
	
	public String testStr = "";
	
	public static void openLoadResources(){
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try{
					LoadResource loadRes = new LoadResource();
					loadRes.frameInit();
					loadRes.setVisible(true);
					loadRes.show();
				} catch (Exception ex){
					ex.printStackTrace();
				}
				
			}
		});
	}
}
