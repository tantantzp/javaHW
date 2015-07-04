import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException {
		GetInfo reader = new GetInfo();
        reader.readInfo();
        reader.saveToFile();
	}

}
