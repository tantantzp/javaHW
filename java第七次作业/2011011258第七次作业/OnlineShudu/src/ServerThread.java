import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;



public class ServerThread extends Thread {	
	Connect parent;

	ServerSocket server = null;
	Socket socket = null;
	int port;
	
	BufferedReader is;
	PrintWriter os;
	String line;
    String sendLine;
	public ServerThread(String str, Connect parent, int serverPort) {
		super(str);
		this.parent = parent;
		this.port = serverPort;
	}

	public void run() {
		try{
		    server=null;
		try{
		    server=new ServerSocket(port);
		}catch(Exception e) {
		    System.out.println("can not listen to:"+e);
		}
		socket=null;
		try{
		    socket=server.accept();
			}catch(Exception e) {
			    System.out.println("Error."+e);
			}
			is=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			os=new PrintWriter(socket.getOutputStream());
			}catch(Exception e){
		    System.out.println("Error: in server client socket"+e);
		    }
		while(true){
			try{
				line=is.readLine();		
				//System.out.println("Server Thread received!");
				parent.receive(line); 
				}catch(Exception e){
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
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	
}
