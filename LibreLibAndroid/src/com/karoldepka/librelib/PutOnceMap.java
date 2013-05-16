package com.karoldepka.librelib;

import java.util.HashMap;

/**
 * A HashMap for canonical mapping which ensures fail-fast behavior if someone tries
 * to change the mapping of an already "canonized" key+value.
 * 
 * @author Karol Depka Pradzinski
 * @license LGPL
 */
public class PutOnceMap<TKey, TValue> extends HashMap<TKey, TValue> {
	
	@Override
	public TValue put(TKey key, TValue value) {
		TValue currentVal = get(key);
		if ( currentVal != null ) {
			if ( currentVal != value ) {
				throw new RuntimeException("Cannot change mapping for key " +key + " to value " +
						value + " from value " + get(key));
			} else {
				// unnecessary put
				return currentVal;
			}
		} else {
			return super.put(key,value);
		}
		
	}
	
}
