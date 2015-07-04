import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;


public class OpenFile implements ActionListener {		
	JFrame frame;
	JTextPane text;
	JScrollPane scrollPane;
	JButton openButton;
	JButton saveButton;
	
	public OpenFile() {
		frame = new JFrame();
		frame.setTitle("file dialog");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		openButton = new JButton("Open File");
		saveButton = new JButton("Save File");
		openButton.addActionListener(this);		
		text = new JTextPane();
		
		JPanel panel = new JPanel();
	    panel.setLayout(new FlowLayout());
		panel.add(openButton);
		panel.add(saveButton);
		scrollPane = new JScrollPane(text);
		scrollPane.setAutoscrolls(true);
		scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        frame.add("North", panel);
        frame.add("Center", scrollPane);
        frame.setSize(300, 200);
        frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int select = jfc.showOpenDialog(frame);
		if (select ==JFileChooser.APPROVE_OPTION){
			  String str = text.getText();
			  String path = jfc.getSelectedFile().getAbsolutePath();  
			  str += path + "\n";	  
	 		  text.setText(str);
		}

	}
	
	public static void main(String[] args) {
		OpenFile openFile = new OpenFile();
	}

}
