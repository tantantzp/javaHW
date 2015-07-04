package keg.simple.mapreduce;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


/**
 * just read a line String 
 * you don't care of multiple files
 * it will read all files
 * 
 * 
 * 
 */
public class Reader
{
	private File[] files;
	private int time=0;
	private int index=0;
	private BufferedReader br=null;
	private boolean isEnd=false;
	
	public Reader(File dir)
	{
		files = dir.listFiles();

		
	}
	
	//if all files have been read, it will return null
	public synchronized String readLine() throws IOException
	{
		if(files.length==0)
		{
			return null;
		}
		
		if(time==0&&index==0)
		{
			time++;
			if(files[index].isFile())
			{
				br=new BufferedReader(new FileReader(files[index]));
			}else
			{
				throw new IOException(files[index].toString()+" is not a file");
			}
			
		}
		
		while(!isEnd)
		{
			String str=br.readLine();
			if(str!=null)
			{
				return str;
			}
			br.close();
			
			index++;
			if(index<files.length)
			{
				if(files[index].isFile())
				{
					br=new BufferedReader(new FileReader(files[index]));
				}else
				{
					throw new IOException(files[index].toString()+" is not a file");
				}
			}else
			{
				isEnd=true;
				break;
			}
			
		}
		
		return null;
		
	}
}
