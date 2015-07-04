import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class GetInfo {
	private Vector<String> names;
	private Vector<String> ids;
	private String filePath;
	
	private InputStreamReader isr;
	private BufferedReader in;
	
	public GetInfo(){
		isr = new InputStreamReader(System.in);
		in = new BufferedReader(isr);
		names = new Vector<String>();
		ids = new Vector<String>();
	}
	
	public void readInfo(){
			String name;
			String id;
		try {
			System.out.println("Please input the content:");
			while (true) {
				System.out.println("enter StudentName");
				while(true){
					name = in.readLine();
					if(name.length() >= 2 && name.length() <= 20 ){
						break;
					}
					else{
						System.out.println("the name should be no less than 2 letters and no more than 20 letters");
						System.out.println("please input the name agaion");
					}
				}
                System.out.println("enter StudentNo");
                while(true){
                	id = in.readLine();
                	if(id.length() == 8){
                		boolean flag = true;
                		for(int i = 0; i < id.length(); i++){
                		    char tmp = id.charAt(i);
                		    if(!Character.isDigit(tmp)){
                		    	flag = false;
                		    	break;
                		    }
                		}
                		if(flag){
                			break;
                		}
                		else{
                			System.out.println("the StudentNo should be 8 numbers");
                    		System.out.println("please input the StudentNo again");
                		}
                	}
                	else{
                		System.out.println("the StudentNo should be 8 numbers");
                		System.out.println("please input the StudentNo again");
                	}
                }
            	names.addElement(name);
				ids.addElement(id);
				System.out.println("ok,info:" + name + " " + id + " written successfully!");
				System.out.println("continue?(y or n)");
				String choice;
				choice = in.readLine();
				if(choice.charAt(0) == 'n'){
					break;
				}
			}
			} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void saveToFile(){
		System.out.print("Please input the file name you want to save:");
		try {
			filePath = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		File target;
		FileOutputStream fw;
		try {
			target = new File(filePath);
			fw = new FileOutputStream(target, true);
			
			int size = names.size();
			for (int i = 0; i < size; i++) {
				String line = names.get(i);
				line += " " + ids.get(i) + "\n";
				fw.write(line.getBytes());
			}
			
			System.out.println("Successfully save the input");
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
	public Vector<String> getNameVector() {
		return names;
	}
	public Vector<String> getIdVector() {
		return ids;
	}
}

