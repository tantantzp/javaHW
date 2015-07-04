package keg.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import keg.simple.mapreduce.Context;
import keg.simple.mapreduce.Reducer;


/**
 * 
 * just a example below
 * 
 * you can modify the generic types: K2,V2,K3,V3 to meet your need
 * 
 * also you can rewrite reduce method
 * 
 * */
public class JobReducer extends Reducer<Integer,String,Character,String>
{	
	
	
	@Override
	
	public void reduce(Integer key,Iterable<String> value,Context context) throws IOException
	{
		Integer num = 0;
		String result = "";
		Hashtable<String, Integer> table = new Hashtable<String, Integer>();

		for(String tmp:value)
		{
			num ++;
			if(table.containsKey(tmp)){
				int tn = table.get(tmp);
				tn ++;
				table.put(tmp, tn);
			}
			else{
				table.put(tmp, 1);
			}
	
		}
		result += num.toString() + ",";
		Integer maxNum = 0;
		for(Iterator iter = table.entrySet().iterator();iter.hasNext();){
	    	Map.Entry element = (Map.Entry)iter.next();
       
	    	Integer wordNum = (Integer)element.getValue();
	    	if(wordNum > maxNum){
	    		maxNum = wordNum;
	    	}
	    }
	    for(Iterator iter = table.entrySet().iterator();iter.hasNext();){
	    	Map.Entry element = (Map.Entry)iter.next();
       
	    	Integer wordNum = (Integer)element.getValue();
	    	if(wordNum == maxNum){	
	    		String word = (String)element.getKey();
	    		result += word + " ";
	    	}
	    }
	    result += "," + maxNum.toString();
	    Character type = (char) (key + 'a');
		context.write(type, result);
	}
}
