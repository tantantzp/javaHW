import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class MouseInfo implements MouseListener{
	JFrame frame;
	JTextArea text;
	JScrollPane scrollPane;
	Integer n = 0;
	String str1 = "";
	String str2 = "";	
	int oldx = -1;
	int oldy = -1;
	public MouseInfo() {
		frame = new JFrame();
		frame.setTitle("MouseInfo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		text = new JTextArea();
		text.addMouseListener(this);
		text.setEditable(false);
		scrollPane = new JScrollPane(text);
		scrollPane.setAutoscrolls(true);
		scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	
		frame.getContentPane().add(scrollPane);
		frame.setSize(400, 400);
		frame.setVisible(true);
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int nx = e.getX();
		int ny = e.getY();
		String str = text.getText();
        //如果仍然在原来的位置,为了处理滚动条滑动,只检查x值
		if(nx == oldx){
			n++;	
			str2 = "点击次数：" + n.toString() + "\n";
			
		}
		//否则就是在一个新的点点击
		else{
            n = 1;
            str1 = str;
    		str2 = "";	
			switch (e.getButton()) {
				case MouseEvent.BUTTON1: str1 += "点击了左键\n"; break;
				case MouseEvent.BUTTON2: str1 += "点击了滚轮\n"; break;
				case MouseEvent.BUTTON3: str1 += "点击了右键\n"; break;
			}
			str1 += "点击相对位置: x = " + nx + "  " + "y = " + ny + "\n";
			str2 = "点击次数：1\n";
			oldx = nx;
			oldy = ny;
		}
		str = str1 + str2;
        text.setText(str);

	}
	@Override
	public void mouseClicked(MouseEvent arg0) {	}
	@Override
	public void mouseEntered(MouseEvent arg0) { }
	@Override
	public void mouseExited(MouseEvent arg0) { }
	@Override
	public void mouseReleased(MouseEvent arg0) { }
	
	
	public static void main(String[] args) {
		MouseInfo mouseInfo = new MouseInfo();
	}
}
