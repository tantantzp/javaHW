package keg.simple.mapreduce;

import java.io.IOException;

public class ReducerThread extends Thread
{
	private String name=null;
	private Reducer reducer=null;
	private Storage storage=null;
	private Context context=null;
	
	public ReducerThread(String name, Reducer reducer,Storage storage, Context context)
	{
		super(name);
		this.reducer=reducer;
		this.storage=storage;
		this.context=context;
	}
	
	
	public void run()
	{
		KeyValueNode kv=null;
		try{
		while(true)
		{
			kv=storage.getKeyValueNode();
			if(kv==null)
			{
				break;
			}
			
				reducer.reduce(kv.getKey(),(Iterable)kv.getValue(),context);

			
						
		}
		}catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
}
