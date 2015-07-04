package keg.simple.mapreduce;

import java.io.IOException;

/**
 * interface for mapreduce save the result of map or reduce method
 * 
 * 
 * @version 1.0.0
 */
public interface Context<K, V>
{
	/**
	 * method write
	 * 
	 * @param key
	 *            input key
	 * 
	 * @param value
	 *            input value
	 * 
	 * */
	void write(K key, V value) throws IOException;
}
