import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
		SearchFile searcher = new SearchFile();
		SearchFile.separatorChar = String.valueOf(File.separatorChar);
		File currentDir = new File(System.getProperty("user.dir"));
		
		searcher.search(currentDir);
		System.out.println("finish");
	}
}
