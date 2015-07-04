package keg.simple.mapreduce;

import java.io.IOException;
import java.util.List;


/**
 * this class implements interface Context
 * @param K,V 
 * generic types
 * 
 * the key and value will be stored in the Object of Storage
 * 
 * */
public abstract class Storage<K,V> implements Context<K,V>
{
	public abstract boolean hasNext();
	
	public abstract   KeyValueNode<K,Object> getKeyValueNode();


}
