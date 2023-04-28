package GUI;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import java.awt.TextArea;

public class ConsoleScreen extends JFrame {
	private TextArea txt_Console;
	
	public ConsoleScreen() {
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		txt_Console = new TextArea();
		getContentPane().add(txt_Console);
	}
	
	
	public void setConsole(String text)
	{
		txt_Console.setText(text);
	}
}
