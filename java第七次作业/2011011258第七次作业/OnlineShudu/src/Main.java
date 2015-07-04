
public class Main {
	public static void main(String args[]){
		MainUI server=new MainUI("Server","127.0.0.1",2009,true);
		
		MainUI client=new MainUI("Client","127.0.0.1",2009,false);
		
	}
}
