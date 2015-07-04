package keg.simple.mapreduce;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * it allows users configure arguments eg. input path,output
 * path,delimiter,Mapper.class ..
 * 
 * it also control the executation of job
 * 
 * job starts from here
 * 
 * NOTE:************************** if you want to learn more, you can visit
 * baidu to search the keyword "hadoop"
 * 
 * 
 * @author keg.cs.tsinghua
 * @version single Thread Version 1.0.0
 */

public class Job
{
	@SuppressWarnings("rawtypes")
	private Class<? extends Mapper> mapperClass;
	
	@SuppressWarnings("rawtypes")
	private Class<? extends Reducer> reduceClass;
	
	private File outputDir;
	private File inputDir;
	
	private String delimiter = ",";
	
	private int mapNum=4;
	private int reduceNum=4;
	
	/**
	 * indicate a name to job
	 * 
	 * @param name
	 *            a name of a job
	 * */
	public Job(String name)
	{
		System.out.println("job:" + name + " init...");
	}
	
	/**
	 * set Mapper.class of the job
	 * 
	 * @param mapper
	 *            it must be subClass of Mapper
	 * 
	 * */
	public void setMapperClass(Class<? extends Mapper> mapper)
			throws IllegalAccessException, InstantiationException
	{
		mapperClass = mapper;
		// TypeVariable[] types=mapper.getTypeParameters();
		
	}
	
	public void setDelimiter(String delimiter)
	{
		this.delimiter = delimiter;
	}
	
	public void setReducerClass(Class<? extends Reducer> reducer)
			throws IllegalAccessException, InstantiationException
	{
		reduceClass = reducer;
		
	}
	
	public void setInputPath(File inputDir)
	{
		this.inputDir = inputDir;
	}
	
	public void setOutputPath(File outputDir)
	{
		this.outputDir = outputDir;
	}
	
	
	/**
	 * default mapper Number:4
	 * @param mapNum
	 * */
	public void setMapperNumber(int mapNum)
	{
		if(mapNum<1)
		{
			throw new IllegalArgumentException("mapNum can't be <1");
		}
		this.mapNum=mapNum;
	}
	
	/**
	 * default reducer number :4
	 * @param reduceNum 
	 * user can set number of reducer
	 * */
	public void setReducerNumber(int reduceNum)
	{
		if(reduceNum<1)
		{
			throw new IllegalArgumentException("reduceNum can't be <1");
		}
		this.reduceNum=reduceNum;
	}
		
	
	@SuppressWarnings(
	{ "unchecked", "rawtypes" })
	public void waitForCompletion() throws ClassNotFoundException, IOException,
			InstantiationException, IllegalAccessException
	{
		if (!checkArgs())
		{
			return;
		}
		
		System.out.println("input: " + inputDir);
		System.out.println("output: " + outputDir);
		
		System.out.println("create directory : " + outputDir);
		
		outputDir.mkdir();
		System.out.println("job start ...");
		
		// load data into mapper
		// Input in = new Input(reader);
		
		// get two types
		Method[] m = mapperClass.getMethods();
		Class<?>[] types = m[0].getParameterTypes();
		
		Class<?> K0 = Class.forName(types[0].getName());
		Class<?> V0 = Class.forName(types[1].getName());
		
		Storage mapStorage = mapperClass.newInstance().new MapStorage();
		Reader reader = new Reader(inputDir);
		
		
		Thread[] threads = new Thread[mapNum];
		for (int i = 0; i < mapNum; i++)
		{
			threads[i] = new MapperThread("" + i, new Input(reader),
					mapperClass.newInstance(), K0, V0, mapStorage);
			
		}
		
		System.out.println("Mappers Start");
		
		for (Thread t : threads)
		{
			t.start();
			
		}
		
		try
		{
			for (Thread t : threads)
			{
				t.join();
				
			}
		} catch (InterruptedException ex)
		{
			ex.printStackTrace();
		}
		
		// load data into reducer
		Storage reduceContext = reduceClass.newInstance().new ReduceStorage();
		
		Output out = new Output(this.outputDir);
		out.setDelimiter(delimiter);
		
		// in fact, the reducer's result can be written into files directly
		
		Thread[] reducerThreads = new Thread[reduceNum];
		
		for (int i = 0; i < reduceNum; i++)
		{
			reducerThreads[i] = new ReducerThread(i + "reducer",
					reduceClass.newInstance(), mapStorage, reduceContext);
		}
		
		System.out.println("Reducers Start");

		
		for (Thread thread : reducerThreads)
		{
			thread.start();
		}
		
		try
		{
			for (Thread thread : reducerThreads)
			{
				thread.join();
			}
		} catch (InterruptedException ex)
		{
			ex.printStackTrace();
		}
		
		KeyValueNode temp=null;
		while (true)
		{
			temp=reduceContext.getKeyValueNode();
			if(temp==null)
			{
				break;
			}
			
			out.writeln(temp.getKey(), temp.getValue());
		}
		out.close();
		
		System.out.println("job has been completed!");
		
	}
	
	/**
	 * @param delimiter
	 *            a delimiter between key and value
	 * 
	 *            default format: key,value default delimiter: ,
	 * 
	 * */
	public void setOutputDelimiter(String delimiter)
	{
		this.delimiter = delimiter;
	}
	
	/**
	 * check basic arguments of job before job starting
	 * 
	 * */
	private boolean checkArgs()
	{
		try
		{
			if (!inputDir.isDirectory())
			{
				throw new IOException(inputDir.getAbsolutePath()
						+ " is not a directory !");
			}
		} catch (IOException ex)
		{
			ex.printStackTrace();
			return false;
			
		}
		
		try
		{
			if (outputDir.exists())
			{
				throw new IOException(outputDir.getAbsolutePath()
						+ " has existed !");
			}
		} catch (IOException ex)
		{
			ex.printStackTrace();
			return false;
			
		}
		
		return true;
	}
	
}
