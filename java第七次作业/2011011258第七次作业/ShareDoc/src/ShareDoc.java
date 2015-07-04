import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ShareDoc {
	File file;
	int contents = 0;	
	
	BufferedReader reader;	
	OutputStreamWriter writer;
	
	public ShareDoc() throws IOException, FileNotFoundException {
		file = new File("log.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		reader = new BufferedReader(new FileReader(file));
		writer = new OutputStreamWriter(new FileOutputStream(file));
		
	}
	public synchronized String readLine() throws IOException {
        if(contents > 0){
        	contents --;
			String temp = reader.readLine();
			return temp;
        }
        else return null;

	}
	public synchronized void writeLine(String str) throws IOException {
		if (str != null) {
			writer.write(str);
			writer.flush();
			contents ++;
			this.notify();
		}
	}
}
