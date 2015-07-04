package keg.simple.mapreduce;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * it represent a location where  resultSet will be stored
 * NOTE:the output path can't be a existing file or directory
 * 
 * @author keg.cs.tsinghua
 * */


public class Output
{
	private String nextLine="\n";
	private FileWriter fw;
	private String delimiter=",";
	
	public Output(File dir) throws IOException
	{
		
		fw=new FileWriter(dir+File.separator+"output.txt");
	}
	
	public void writeln(Object key,Object value) throws IOException
	{
		fw.write(key.toString());
		fw.write(delimiter);
		fw.write(value.toString());
		fw.write(nextLine);

	}
	
	
	
	public void close() throws IOException
	{
		fw.close();
	}
	
	public void setDelimiter(String delimiter)
	{
		this.delimiter=delimiter;
	}
}
