package keg.simple.mapreduce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * abstract Class for mapreduce job
 * 
 * */

public abstract class Mapper<K1,V1,K2,V2>
{
	
	/**
	 * Class MapStorage is a subClass of Storage
	 * it is innerBuilded in Class Mapper
	 * 
	 * */
	public class MapStorage extends Storage<K2,V2>
	{
		private Map<K2,List<V2>> map;
		private boolean isWritable;
		private List<K2> keyList;
		private int keyIndex;
		private K2 k;
		private List<V2> v;
		
		public MapStorage()
		{
			map=new HashMap<K2,List<V2>>();
			keyList=new ArrayList<K2>();
			keyIndex=-1;
			isWritable=true;
		}
		
		@Override
		public synchronized void write(K2 key,V2 value) throws IOException
		{
			
			if(!isWritable)
			{
				
					throw new IOException("can't write data into Object Storage!");
						
			}

			
			List<V2> temp=map.get(key);
			if(temp==null)
			{
				temp=new ArrayList<V2>();
				map.put(key, temp);
			}
			temp.add(value);
			
			
		}

		@Override
		public boolean hasNext()
		{
			
			//System.out.println(keyList.size());
			if(isWritable)
			{
				isWritable=false;
				keyList.addAll(map.keySet());
				
			}
			
			keyIndex++;
			if(keyIndex<keyList.size())
			{
				k=keyList.get(keyIndex);
				v=map.get(k);
				return true;
			}
			
			k=null;
			v=null;
			
			return false;
		}

	
		
		public synchronized  KeyValueNode<K2,Object> getKeyValueNode()
		{
			if(this.hasNext())
			{
				return new KeyValueNode<K2,Object>(k,v);
			}
			return null;
		}
		
		
	}
	
	@SuppressWarnings({"unchecked","rawtypes"})
	public void map(K1 key,V1 value,Context context) throws IOException 
	{
		
		context.write(key,value);
		
	}
	
}
