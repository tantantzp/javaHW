import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class Manage implements ActionListener {
	JFrame frame;
	JTextPane text;
	SimpleAttributeSet blueSet;
	JScrollPane scrollPane;
	SimpleAttributeSet redSet;
	
	JMenuBar menubar;
	JMenu manage;
	JMenu help;	
	JMenu color;
	
	JCheckBoxMenuItem check;
	JMenuItem exit;
	JMenuItem red;
	JMenuItem blue;
	JMenuItem helpItem;
	//创建组件
	public Manage() {
		frame = new JFrame();
		blueSet = new SimpleAttributeSet();
		redSet = new SimpleAttributeSet();
		StyleConstants.setForeground(blueSet, Color.blue);
		StyleConstants.setForeground(redSet, Color.red);
		
		frame.setTitle("menu example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		text = new JTextPane();
		text.setCharacterAttributes(blueSet, true);
		scrollPane = new JScrollPane(text);
		scrollPane.setAutoscrolls(true);
		scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
		menubar = new JMenuBar();
		manage = new JMenu("Manage");
		help = new JMenu("Help");	
		color = new JMenu("Color");	
		check = new JCheckBoxMenuItem("Check");
		exit = new JMenuItem("Exit");
		red = new JMenuItem("Red");
		blue = new JMenuItem("Blue");
		helpItem = new JMenuItem("Help");
		
		color.add(red);
		color.add(blue);
		help.add(helpItem);
		manage.add(color);
		manage.addSeparator();
		manage.add(check);
		manage.add(exit);
		menubar.add(manage);
		menubar.add(help);	
	
		helpItem.addActionListener(this);		
		red.addActionListener(this);
		blue.addActionListener(this);
	    check.addActionListener(this);
		exit.addActionListener(this);
		
		frame.setJMenuBar(menubar);
		frame.add(scrollPane);
		frame.setSize(400, 400);
		frame.setVisible(true);
		
	}
	//事件处理,响应菜单事件
	public void actionPerformed(ActionEvent e) {
		String str = ((JMenuItem)(e.getSource())).getText();
		String content = text.getText();
      
		if (str.equals("Red")) {
			text.setCharacterAttributes(redSet, true);
		}
		else if (str.equals("Blue")) {
			text.setCharacterAttributes(blueSet, true);
		}
		else if (str.equals("Help")) {
			content += "this is help message:\n";
			content += "manage->color button can change text color\n";
			content += "after check is check or un-checked, text will display result\n";
		}
		else if(str.equals("Check")){
			JCheckBoxMenuItem check = (JCheckBoxMenuItem)e.getSource();
		    boolean selected = check.isSelected();
		    if(selected){
		    	content += "Check Box is checked.\n";
		    }
		    else{
		    	content += "Check Box is un-checked.\n";
		    }
		}
		else if(str.equals("Exit")){
			System.exit(0);
		}
		
		text.setText(content);
	}
	
	public static void main(String[] args) {
		Manage manage = new Manage();
	}
}
