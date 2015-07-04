import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;

/**
 * Class for fetching and processing Tsinghua Course list webpage
 */

/**
 * @author mayrock
 * 
 */
public class Course {
	/**
	 * 
	 * @param urlString the address of the page
	 * @return a string containing the page response content
	 */
	public static String fetch(String urlString) {
		URL url;
		InputStream is = null;
		BufferedReader br;
		String line;
		
		StringBuffer page = new StringBuffer();

		try {
		    url = new URL(urlString);
		    is = url.openStream();  // throws an IOException
		    br = new BufferedReader(new InputStreamReader(is));

		    while ((line = br.readLine()) != null) {
		        page.append(line + System.lineSeparator());
		    }
		} catch (MalformedURLException mue) {
		     mue.printStackTrace();
		} catch (IOException ioe) {
		     ioe.printStackTrace();
		} finally {
		    try {
		        is.close();
		    } catch (IOException ioe) {
		        // nothing to see here
		    }
		}
		//System.out.println(page.toString());
		return page.toString();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String addr = "http://learn.tsinghua.edu.cn/learn/search_course.jsp";
		String page = fetch(addr);
		
		processPage(page);
	}
	/**
	 * Process the page and print the results
	 * @param page the webpage source as a string
	 */
	private static void processPage(String page) {
		// TODO implement this method.
		// You should print the result directly to console here
		// using System.out
		
		HashMap<String, Integer> courseMap = new HashMap<String, Integer>();
		String pattern1 = "MajorName\\[\\d+\\] = \\'(.+)\\'";
		String pattern2 = "StudentNum\\[\\d+\\] = \\'\\d+\\'";
		Pattern p1 = Pattern.compile(pattern1);
		Pattern p2 = Pattern.compile(pattern2);
		Matcher m1 = p1.matcher(page);
		Matcher m2 = p2.matcher(page);
		String majorName[] = new String[100];
		Integer studentNum[] = new Integer[100];
		int index = 0;
		while(m1.find()){
			String temp = m1.group();
			int i;
			for(i = 0; i < temp.length(); i++){
				char tc = temp.charAt(i);
				if(tc == '\'')
				    break;
			}
			i ++;
			majorName[index] = temp.substring(i, temp.length() - 1);
			index++;
		}
		index = 0;
		while(m2.find()){
			String temp = m2.group();
			int i;
			for(i = 0; i < temp.length(); i++){
				char tc = temp.charAt(i);
				if(tc == '\'')
				    break;
			}
			i ++;
			String temp2 = temp.substring(i, temp.length() - 1);
			studentNum[index] = Integer.parseInt(temp2);
			index++;
		}
	    for(int i = 0; i < index; i++){
	    	if(courseMap.containsKey(majorName[i])){
	    		int temp = courseMap.get(majorName[i]);
	    		temp += studentNum[i];
	    		Integer newNum = temp;
	    		courseMap.put(majorName[i],newNum);
	    	}
	    	else
	    	    courseMap.put(majorName[i], studentNum[i]);
	    }
		
	    for(Iterator iter = courseMap.entrySet().iterator();iter.hasNext();){
	    	Map.Entry element = (Map.Entry)iter.next();
	    	String cname = (String)element.getKey();
	    	Integer snum = (Integer)element.getValue();
	    	System.out.println(cname + " " + snum);
	    }
	    
	}

}
