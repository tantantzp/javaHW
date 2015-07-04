
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

//负责主要的UI实现，实现了按钮和菜单
public class MainUI implements ActionListener{	
	ChessTable table;
	JFrame frame;	
	JButton checkButton;
	JMenuItem generate;
	JMenuItem answer;
	JMenuItem exit;
    Connect connect;
    boolean isServer;
	
	public MainUI(String ID,String IP, int port, boolean isServer){
		this.isServer = isServer;
		frame = new JFrame();
		frame.setTitle(ID);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		table = new ChessTable(this);
		connect = new Connect(this, IP, port, isServer);

		JMenuBar menubar = new JMenuBar();	
		JMenu game = new JMenu("Game");
		
		generate = new JMenuItem("Generate");
		answer = new JMenuItem("Show Answer");
		exit = new JMenuItem("Exit");
		
		generate.addActionListener(this);
		answer.addActionListener(this);
		exit.addActionListener(this);
		
		game.add(generate);
		game.add(answer);
		game.addSeparator();
		game.addSeparator();
		game.add(exit);
		
		menubar.add(game);
		checkButton = new JButton();
		checkButton.setText("check");
		checkButton.addActionListener(this);
		frame.setJMenuBar(menubar);	
		frame.setLayout(new BorderLayout());
		frame.add("West", table.table);
		frame.add("South", checkButton);
		//frame.setSize(400, 400);		
		frame.pack();

		frame.setVisible(true);
		
	}

	public void setInitial(int[][] s) {
		table.setInit(s);
		
	}
    public void receive(String in){
    	table.set(in);
    }
    public void send(String out){
    	connect.send(out);
    }
	
	//事件响应函数
	public void actionPerformed(ActionEvent e) {
		//处理按钮事件, 检查解答是否正确
		if(e.getSource() == checkButton){
			int result = table.check();
			if(result == -2){
				JOptionPane.showConfirmDialog(frame, "Not Finished!","Warn", JOptionPane.CLOSED_OPTION);
			}
			else if(result == -1){
				JOptionPane.showConfirmDialog(frame, "Your answer is true!","Success", JOptionPane.CLOSED_OPTION);
			}
			else{
				int row = result / 9 + 1;
				int col = result % 9 + 1;
				String info = "number at row:" + row +" col:" + col + " is invalid.";
	        	JOptionPane.showConfirmDialog(frame, info, "Failed", JOptionPane.OK_OPTION);
			}
		}
		//处理菜单事件,负责生成游戏, 显示解答, 退出
		else if (((JMenuItem)e.getSource()).getText().equalsIgnoreCase("generate")) {
			String result = null;
			while (result == null)
				result = JOptionPane.showInputDialog(frame, "Input a integer from 1 to 8:", 
					"Set Difficulty", JOptionPane.OK_OPTION);
			
			int difficulty = Integer.valueOf(result);
			if (difficulty < 1 || difficulty > 8) {
				JOptionPane.showConfirmDialog(frame, "Illegal number!", 
						"Error", JOptionPane.OK_OPTION);
				return;
			}

			table.generate(difficulty);
		}
		else if (((JMenuItem)e.getSource()).getText().equalsIgnoreCase("show answer")) {
			table.getAnswer();
		}
		else if (((JMenuItem)e.getSource()).getText().equalsIgnoreCase("exit")) {
			System.exit(0);
		}
	}
	
}
