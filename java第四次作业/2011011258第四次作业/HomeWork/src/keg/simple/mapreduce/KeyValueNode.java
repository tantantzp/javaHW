package keg.simple.mapreduce;

/**
 * save key-value
 * just a bean
 * 
 * */

public class KeyValueNode<K,V>
{
	private K key;
	private V value;
	
	public KeyValueNode(K key,V value)
	{
		this.key=key;
		this.value=value;
		
	}
	
	public K getKey()
	{
		return key;
	}
	
	public V getValue()
	{
		return value;
	}
	
}
