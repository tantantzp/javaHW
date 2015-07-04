import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
		
		ShowDir.separatorChar = String.valueOf(File.separatorChar);
		File currentDir = new File(System.getProperty("user.dir"));
		
		ShowDir.showDirection(currentDir, 0);
			
	}
}
