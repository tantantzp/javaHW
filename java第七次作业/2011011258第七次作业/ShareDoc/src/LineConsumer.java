import java.io.IOException;


public class LineConsumer extends Thread {

	ShareDoc shareDoc;
	int N;
	
	public LineConsumer(ShareDoc doc) {
		shareDoc = doc;
		N = 0;
	}

	public void consume() throws IOException {
		if (N < 60) {
			String tmp = shareDoc.readLine();
			if(tmp != null){
				N++;
				String content = "LineConsumer£º" + tmp;
				System.out.println(content);
			}

		}
	}
	
	public void run() {	
		while (true) {
			try {
				consume();
				sleep((long) (Math.random() * 300));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
