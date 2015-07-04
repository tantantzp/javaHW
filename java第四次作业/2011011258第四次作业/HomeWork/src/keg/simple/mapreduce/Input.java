package keg.simple.mapreduce;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * method Input
 * main function: read a line String from a file
 * the destination directory can contain many files
 * but the directory can't be included
 * 
 * @author keg.cs.tsinghua
 * */


public class Input
{
	
	private int lineNumber;
	private String content;
	private Reader reader=null;
	
	/**
	 * @param dir 
	 * the directory has many files that will be read and proceeded
	 * it also can be empty
	 * */

	public Input(Reader reader)
	{
		this.reader=reader;
	}
	

	/**
	 * read result by line 
	 * @return boolean
	 * if it has reached end of files'content ,the method returns false 
	 * 
	 */
	public synchronized boolean hasNextLine() throws IOException
	{
		content = reader.readLine();
		
		if (content != null)
		{
			lineNumber++;
			return true;
		}
		
		return false;
	}
	
	/**
	 * get current Content 
	 * 
	 * */
	public String getContent()
	{
		return this.content;
	}
	
	/**
	 * get current LineNumber
	 * 
	 * */
	
	public int getLineNumber()
	{
		return this.lineNumber;
	}
	
	
}
