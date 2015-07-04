package keg.test;

import java.io.File;
import keg.simple.mapreduce.Job;
import keg.simple.mapreduce.Job;

/**
 * 
 *	You can start the hadoop like programming 
 *	by running the main method in this Class
 * 
 * 
 * @author keg.cs.tsinghua
 * @version 1.0.0
 * 
 * 
 * */

public class JobStart
{
	
	public static void main(String[] args) throws IllegalAccessException, InstantiationException,Exception
	{
		//a job name ,it can be everything
		Job job=new Job("test");
		
		// input directory path
		job.setInputPath(new File("./homeworkInput"));
		
		
		//output directory Path (Location of resultSet )
		//NOTE:the output path can't be a existing file or directory
		job.setOutputPath(new File("./homeworkOutput"));
		
		
		//delimiter between key and value
		job.setDelimiter(",");
		
		
		job.setMapperClass(JobMapper.class);
		job.setReducerClass(JobReducer.class);
		
		//job starts
		job.waitForCompletion();
		
	}
	
}
