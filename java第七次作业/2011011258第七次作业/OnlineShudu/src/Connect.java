import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class Connect {
	
	MainUI parent;
	String IP;
	int port;
    boolean isServer;
	ServerThread serverTd;
	ClientThread clientTd;
		
	public Connect(MainUI ui, String IP, int port, boolean isServer){	
		parent = ui;
		this.port = port;
	    this.IP = IP;
	    this.isServer = isServer;
		if (isServer){		
			serverTd = new ServerThread("Server Thread", this, port);
			serverTd.start();
	    }
	    else {
	    	clientTd = new ClientThread("Client Thread", this, IP, port);
			clientTd.start();
	    }
	}
	
	public void receive(String in){
		
		parent.receive(in);
		
	}
    public void send(String out){
    	
    	if(isServer){
    		serverTd.send(out);
    	}
    	else{
    		clientTd.send(out);
    	}
    }
}
