import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;


public class ShowInfo {
	private File file;
	private BufferedReader reader;
	private String fileName;
	private Vector<String> names;
	private Vector<String> ids;
	
	public ShowInfo() {
		names = new Vector<String>();
		ids = new Vector<String>();
		
		while (true) {
			System.out.println("Please input the name of the file:");
			fileName = getFileName();
			file = new File(fileName);
			if (file.exists())
				break;
			else
				System.out.println("File not found! Please input again!");
		}
		
		readInfo();
	}
	private String getFileName() {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String fileName = null;
		try {
			fileName = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
    }
	public void readInfo(){
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			
			while (true) {
				line = reader.readLine();
				if (line == null)
					break;
				else {
					String[] temp = line.split(" ");
					if (temp.length == 2) {
						names.addElement(temp[0]);
						ids.addElement(temp[1]);
					}
					else {
						System.out.println("The file format is wrong!");
						break;
					}
				}
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	
	public void print() {	
		System.out.println("StudentName  StudentNo");
		for (int i = 0; i < names.size(); i++) {
			System.out.print(names.get(i) + "    ");
			System.out.println(ids.get(i));
		}
		System.out.println();
	}
	
}
