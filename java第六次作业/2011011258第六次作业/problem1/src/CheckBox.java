import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;


public class CheckBox implements ActionListener{
	
	SimpleAttributeSet blue;
	SimpleAttributeSet red;
	
	JFrame frame;
	JTextPane text;
	JScrollPane scrollPane;
	JCheckBox checkBox1;
	JCheckBox checkBox2;
	JRadioButton blueRadio;
	JRadioButton redRadio;
	JButton button;
	
	//创建组建
	public CheckBox() {
		blue = new SimpleAttributeSet();
		red = new SimpleAttributeSet();
		StyleConstants.setForeground(blue, Color.blue);
		StyleConstants.setForeground(red, Color.red);
		
		frame = new JFrame();
		
		frame.setTitle("My Frame");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		text = new JTextPane();	
		
		text.setEditable(false);
		text.setCharacterAttributes(blue, true);
		text.setAutoscrolls(true);
		scrollPane = new JScrollPane(text);
		scrollPane.setAutoscrolls(true);
		scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	
		checkBox1 = new JCheckBox("Check Box1");
		checkBox2 = new JCheckBox("Check Box2");
		checkBox1.addActionListener(this);
		checkBox2.addActionListener(this);
		
		blueRadio = new JRadioButton("blue");
		redRadio = new JRadioButton("red");
		blueRadio.addActionListener(this);
		redRadio.addActionListener(this);
		
		ButtonGroup colorGroup = new ButtonGroup();
		colorGroup.add(blueRadio);
		colorGroup.add(redRadio);
		colorGroup.setSelected(blueRadio.getModel(), true);

		JPanel panel = new JPanel();
		
		panel.setLayout(new GridLayout(2, 2));
		panel.add(checkBox1, 0);
		panel.add(blueRadio, 1);
		panel.add(checkBox2, 2);
		panel.add(redRadio, 3);

		button = new JButton("OK");
		button.addActionListener(this);

		frame.getContentPane().add("North", panel);
		frame.getContentPane().add("Center", scrollPane);
		frame.getContentPane().add("South", button);
		frame.setSize(300, 200);
		frame.setVisible(true);
	}
	
   //负责事件处理
	public void actionPerformed(ActionEvent e) {
		String str = text.getText();
		if (e.getSource() == button) {
			str += "button is beated.\n";
			text.setText(str);
		}
		else if (e.getSource() == checkBox1 || e.getSource() == checkBox2) {
			if (((JCheckBox)e.getSource()).isSelected())
				str += ((JCheckBox)e.getSource()).getText() + " is selected.\n";
			else
				str += ((JCheckBox)e.getSource()).getText() + " is not selected.\n";
			text.setText(str);
		}
		else if (blueRadio.isSelected()) {
			text.setCharacterAttributes(blue, true);
			text.setText(str);
		}
		else {
			text.setCharacterAttributes(red, true);
			text.setText(str);
		}
		
	}
	
	public static void main(String[] args) {
		CheckBox checkBox = new CheckBox();
		
	}

}
