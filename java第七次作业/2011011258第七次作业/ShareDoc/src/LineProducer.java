import java.io.IOException;


public class LineProducer extends Thread{	
	int N;
	int num = 0;
	ShareDoc shareDoc;
	
	public LineProducer(int _N, ShareDoc doc) {
		N = _N;
		shareDoc = doc;
		num = 0;
	}

	public void writeNewLine() throws IOException {
		String text = "";
		if (num < 30) {
			num++;
			text += "line " + num + " from LineProducer " + N + "\n";
			shareDoc.writeLine(text);
		}	
	}
	
	public void run() {
		while (num < 30) {
			try {
				writeNewLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				sleep((long) (Math.random() * 500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
