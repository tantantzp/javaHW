import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class NamePass implements ActionListener{
	JFrame frame;
	JButton button;
	JTextPane text;
	JScrollPane scroll;
	MyDialog dialog;
	
	public NamePass() {
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Dialog");
		
		button = new JButton("Dialog");
		text = new JTextPane();
		dialog = new MyDialog(this);
		
		frame.getContentPane().add("North", button);
		scroll = new JScrollPane(text);
		scroll.setAutoscrolls(true);
		scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add("Center", scroll);
		frame.setSize(300, 300);
		frame.setVisible(true);
		
		button.addActionListener(this);
	}
	
	public void setInfo(String name, String password) {
		String str = text.getText();
		str += "name:" + name + "\n";
		str += "passWord:" + password + "\n";
		text.setText(str);
		//隐藏对话框
		dialog.setFrame(false);
		dialog.clear();
	}
	
	public void actionPerformed(ActionEvent e) {
		//显示对话框
	    dialog.setFrame(true);
	}
	
	public static void main(String[] args) {
		NamePass getInfo = new NamePass();
	}

}
