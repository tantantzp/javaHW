import java.io.File;


public class ShowDir {

	public static String separatorChar;
	
	public static void showDirection(File file, int level) {
		String space = "";
		for (int i = 1; i < level; i++) {
			space += "\t";
		}
		if (file.isDirectory()) {
			if(level > 0)
			    System.out.println(space + "<" + file.getName() + ">");
			
			String[] nextDir = file.list();
			for (int i = 0; i < nextDir.length; i++) {
				File next = new File(file.getAbsolutePath() + ShowDir.separatorChar + nextDir[i]);
				ShowDir.showDirection(next, level + 1);
			}
		}
		else {
			System.out.println(space + file.getName());
		}
	}
	
}