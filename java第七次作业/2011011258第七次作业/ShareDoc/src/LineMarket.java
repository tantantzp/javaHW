import java.io.FileNotFoundException;
import java.io.IOException;

public class LineMarket {

	LineProducer producer1;
	LineProducer producer2;
	LineConsumer consumer;
	ShareDoc shareDoc;
	
	public LineMarket() throws FileNotFoundException, IOException {
		shareDoc = new ShareDoc();
		producer1 = new LineProducer(1, shareDoc);
		producer2 = new LineProducer(2, shareDoc);
		consumer = new LineConsumer(shareDoc);
		
		producer1.start();
		producer2.start();
		consumer.start();
	}
	
	public static void main(String[] args){
	    try {
			LineMarket market = new LineMarket();
		} catch (FileNotFoundException e) {	
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
