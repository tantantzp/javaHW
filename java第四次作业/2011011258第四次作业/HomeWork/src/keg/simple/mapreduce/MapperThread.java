package keg.simple.mapreduce;

import java.io.IOException;

public class MapperThread extends Thread
{
	private Input input = null;
	private Mapper mapper = null;
	private Context context = null;
	private Class<?> K;
	private Class<?> V;
	
	public MapperThread(String name,Input input, Mapper mapper,Class<?> K,Class<?> V, Context context)
	{
		super(name);
		this.mapper = mapper;
		this.input = input;
		this.context = context;
		this.K=K;
		this.V=V;
		
	}
	
	public void run()
	{
		try
		{
			while (input.hasNextLine())
			{
				mapper.map(K.cast(input.getLineNumber()), V.cast(input.getContent()), context);
				
			
			}
		} catch (IOException ex)
		{
			ex.printStackTrace();
		}
		
	}
}
