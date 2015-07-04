import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class ClientThread extends Thread{

	private Connect parent;
	private Socket socket = null;
	private int port;
	private String serverIP;
	
	BufferedReader is;
	PrintWriter os;
	String line;
    String sendLine;

	public ClientThread(String str, Connect parent, String serverIP, int serverPort) {
		super(str);
		this.parent = parent;
		this.port = serverPort;
		this.serverIP = serverIP;
	}

	public void run() {		
		try{
			socket=new Socket(serverIP,port);
			os=new PrintWriter(socket.getOutputStream());
			is=new BufferedReader(new
			InputStreamReader(socket.getInputStream()));
			}catch(Exception e){
			    System.out.println("Error: in client socket"+e);
			}				
		while (true) {
			try {
				line=is.readLine();	
				//System.out.println("Client Thread received!");
				parent.receive(line);
			} catch (IOException e) {
				 System.out.println("Error: in receive remote information"+e);
			}								
		}
	}
	
	public void send(String out) {
		os.println(out);
		os.flush();
	}
	
	public void terminate() {
		try {
			os.close();
			is.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}