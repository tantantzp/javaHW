package keg.simple.mapreduce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Reducer<K2, V2, K3, V3>
{
	
	// innerClass
	private class Node implements Comparable<K3>
	{
		
		K3 key;
		V3 value;
		
		public Node(K3 key, V3 value)
		{
			this.key = key;
			this.value = value;
		}
		@Override
		public String toString()
		{
			return key.toString();
		}
		
		@Override
		public int compareTo(K3 o)
		{
			return toString().compareTo(o.toString());
		}
	}
	/**
	 * InnerClass
	 * ReduceStorage is a  subClass of Storage
	 * 
	 * */
	public class ReduceStorage extends Storage<K3, V3>
	{
		private List<Node> list = null;
		private Object[] os = null;
		private boolean isWritable = true;
		private boolean isSort = false;
		private int index = -1;
		private K3 k;
		private V3 v;
		
		ReduceStorage()
		{
			list = new ArrayList<Node>(10000);
		}
		
		@Override
		public synchronized void write(K3 key, V3 value) throws IOException
		{
			if (!isWritable)
			{
				throw new IOException("can't write data into Object Storage!");
				
			}
			list.add(new Node(key, value));
		}
		
		@Override
		@SuppressWarnings("unchecked")
		public boolean hasNext()
		{
			if (isWritable)
			{
				isWritable = false;
			}
			
			// SORT
			if (!isSort)
			{
				isSort = true;
				
				os = list.toArray();
				
				Arrays.sort(os);
				
			}
			
			index++;
			
			if (index < os.length)
			{
				k = ((Node) os[index]).key;
				v = ((Node) os[index]).value;
				return true;
				
			}
			k = null;
			v = null;
			
			return false;
		}
		

		@Override
		public synchronized KeyValueNode<K3, Object> getKeyValueNode()
		{
			if(this.hasNext())
			{
				return new KeyValueNode(k,v);

			}
			return null;
		}
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void reduce(K2 key, Iterable<V2> value, Context context)
			throws IOException
	{
		
		for (V2 temp : value)
		{
			context.write(key, temp);
		}
		
	}
	
}
