import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

//ChessTable利用JTable实现数独的网格
public class ChessTable implements TableModelListener {
	MainUI parent;
	JTable table = new JTable();
	Shudu chess;
	public ChessTable(MainUI _parent) {
		parent = _parent;
		chess = new Shudu();
		setInit(chess.original);
	}
	
	//根据难度产生数独,并且向对方发送数据
	public void generate(int difficulty){
		chess.generate(difficulty);
		setInit(chess.original);
		table.getModel().addTableModelListener(this);
		MyCellRender render = new MyCellRender();
		table.setDefaultRenderer(Object.class, render); 
		int[][] data = ((MyModel)table.getModel()).getData();
		String info = "";
		for(int i = 0; i < 9; i ++){
			for(int j = 0; j < 9; j ++){
				Integer tint = data[i][j];
				info += tint.toString() + ",";
			}
		}
    	parent.send(info);
	}
	//利用初始数组创建数独
	public void generate(int[][] data){
		chess.generate(data);
		setInit(chess.original);
		table.getModel().addTableModelListener(this);
		MyCellRender render = new MyCellRender();
		table.setDefaultRenderer(Object.class, render); 
	}
    public int check(){
    	return chess.check();
    }
	public void getAnswer(){
		setInit(chess.answer);		

	}
	//初始化棋盘
	public void setInit(int[][] s) {	
		MyModel model = new MyModel(s);	
		table.setModel(model);
		for (int i = 0; i < 9; i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(40);
			table.setRowHeight(40);
			table.setFont(new Font("Dialog", Font.BOLD, 30));
		}
		table.setSize(400, 400);
		table.setRowSelectionAllowed(false);
	}
	
	public void set(String in){
		String[] info = in.split(",");
		//摆放一个棋子
        if(info.length == 3){				
			int row = Integer.parseInt(info[0]);
			int col = Integer.parseInt(info[1]);
			int nv = Integer.parseInt(info[2]);		
			String str = (String)table.getValueAt(row,col);
			if(str != null){	
				String tmp = (String) table.getValueAt(row, col);
				int ov = Integer.parseInt(tmp);				
				if(nv == ov){	
				}
				else{
					table.setValueAt(info[2],row, col);
					chess.setWithoutCheck(row, col, nv);
				}
			}
			else{
			    table.setValueAt(info[2],row, col);
			    chess.setWithoutCheck(row, col, nv);
			}
		}
        //初始化棋盘
        else if(info.length == 81){
        	int[][] data = new int[9][9];
        	for(int i = 0; i < 9; i++){
        		for(int j = 0; j < 9; j++){
        			data[i][j] = Integer.parseInt(info[9 * i + j]);
        		}
        	}
            generate(data);
        }
	}
	
    //时间响应处理函数，当table某一位改变了会触发
	public void tableChanged(TableModelEvent e) {    			
		int row = e.getFirstRow();  
        int col = e.getColumn();        
        TableModel model = (TableModel)e.getSource();  
        String val = (String)model.getValueAt(row, col);
        if(val.length() == 0)
        {
        	chess.setWithoutCheck(row, col, 0);
        	return;
        }
        int value = Integer.valueOf(val);

        if (value < 1 || value > 9) {  	
        	if(value == 0) {
        		chess.setWithoutCheck(row, col, 0);
        		table.setValueAt("", row, col);
        		return;
        	}
        	String info = value + " is invalid.";
        	JOptionPane.showConfirmDialog(table, info, "Error", JOptionPane.CLOSED_OPTION);
        	table.setValueAt("", row, col);
        }
        else{
    		String info = row + "," + col + "," + value;
        	parent.send(info);
            chess.setWithoutCheck(row, col, value);
        }
	}	
}
//用于处理JTable的网格的颜色显示，区分能否被编辑
class MyCellRender extends DefaultTableCellRenderer{      
       public Component getTableCellRendererComponent(JTable table,Object value,
               boolean isSelected,boolean hasFocus,int row,int column)
		{   
    	    TableModel model = table.getModel();
    	    if(!model.isCellEditable(row, column)){ 
    	    	this.setForeground(Color.gray);	    	
    	    }
    	    else{
    	    	this.setForeground(Color.black);
    	    }
		    return super.getTableCellRendererComponent(table, value,isSelected, hasFocus, row, column);			
		}  
}  

//用于JTable的数据存储，决定哪些位不能被编辑
class MyModel extends DefaultTableModel {
	boolean[][] editable;
	int[][] data;
	public MyModel(int[][] s) {
		super(9, 9);  
		editable = new boolean[9][9];
		data = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				data[i][j] = s[i][j];
				if (s[i][j] == 0) {
					editable[i][j] = true;
				}
				else {
					editable[i][j] = false;
					super.setValueAt(String.valueOf(s[i][j]), i, j);    
				}
			}
		}
	}
	public int[][] getData(){
		return data;
	}
	public boolean isCellEditable(int row, int column) {
		return editable[row][column];
	}
};