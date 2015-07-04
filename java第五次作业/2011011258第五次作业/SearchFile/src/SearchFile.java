import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SearchFile {

	public static String separatorChar;
	public String key;
	public Pattern pattern;
	public Matcher matcher;
	
	SearchFile() throws IOException{
		InputStreamReader isr;
		BufferedReader in;
		isr = new InputStreamReader(System.in);
		in = new BufferedReader(isr);
	    System.out.println("search content:");	
	    key = in.readLine();
	    pattern = Pattern.compile(key);
	}
	
	public void search(File file) {
		if (file.isDirectory()) {		
			String[] nextDir = file.list();
			for (int i = 0; i < nextDir.length; i++) {
				File next = new File(file.getAbsolutePath() + separatorChar + nextDir[i]);
				search(next);
			}
		}
		else {
			String fileName = file.getName();
			matcher = pattern.matcher(fileName);
			
			if(matcher.find()){
				System.out.print(fileName + "  ");
				System.out.println(file.getAbsolutePath());
			}
			
		}
	}
	
}