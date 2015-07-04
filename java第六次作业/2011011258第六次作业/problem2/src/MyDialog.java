import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;

//输入密码的对话框
public class MyDialog implements ActionListener{
	String[] information;
	NamePass parent;
	JFrame frame;
	JTextField nameText;
	JPasswordField passwordText;
	JButton confirmButton;

	public MyDialog(NamePass parent) {
		information = new String[2];
		this.parent = parent;		
		
		frame = new JFrame();
		frame.setTitle("Name and Pass");
		JLabel nameLabel = new JLabel("Name:");
		JLabel passwordLabel = new JLabel("Password:");
		confirmButton = new JButton("OK");
		confirmButton.addActionListener(this);
		
		nameText = new JTextField();
		passwordText = new JPasswordField();
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2));
		panel.add(nameLabel, 0);
		panel.add(nameText, 1);
		panel.add(passwordLabel, 2);
		panel.add(passwordText, 3);
		
		frame.add("Center", panel);
		frame.add("South", confirmButton);
		frame.setSize(200, 150);
	}
	public void setFrame(boolean visible){
		frame.setVisible(visible);
	}
	public void clear() {
		nameText.setText("");
		passwordText.setText("");
	}
	
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent arg0) {
		parent.setInfo(nameText.getText(), passwordText.getText());
	}
}
