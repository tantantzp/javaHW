package keg.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import keg.simple.mapreduce.Context;
import keg.simple.mapreduce.Mapper;

/**
 * 
 * Just a example below.
 * 
 * You can modify the generic types: K2,V2 to meet your need.
 * 
 * Note: K1,V1 must be Integer,String .
 * 
 * You can search superClass for some useful information.
 * 
 * Also you can rewrite map method.
 * 
 * */

public class JobMapper extends Mapper<Integer,String,Integer,String>
{
	
	@Override 
	public void map(Integer key,String value,Context context) throws IOException
	{
  
		String pt = "[a-zA-Z]+((\\'[a-zA-Z]+)|(\\-[a-zA-Z]+))?";//|[a-zA-Z]+\\'[a-zA-z]{1,2}|[a-zA-Z]+\\-[a-zA-Z]+ ";
		Pattern pattern = Pattern.compile(pt);
		Matcher matcher = pattern.matcher(value);
		
		while(matcher.find()){
			String tmp = matcher.group().toLowerCase();
						
			if(tmp.length() >= 1){
				if(tmp.length() == 1){
					char ct = tmp.charAt(0);
					if(ct != 'a' && ct != 'i'){
						continue;
					}
				}
				//System.out.println(tmp);
				char start = tmp.charAt(0);
				int kind = start - 'a';
				if(kind >= 0 && kind < 26)
				    context.write(kind, tmp);
			}
		}

	}
		
}
