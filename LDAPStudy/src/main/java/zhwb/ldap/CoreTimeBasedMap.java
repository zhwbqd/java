package zhwb.ldap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CoreTimeBasedMap
{
	/** Duration of Storage */
	private long maxAge;

	/** Cache for the Object Store */
	private Map<Object, Object> store;
	
	/** Time of last cleanup */
	private long cleanupLast;
	
	/** Time period of cleanup */
	private long cleanupPeriod;
	
	/** Ratio of the cleanup period to the max age */
	private static final int CLEANUP_TO_MAXAGE_RATIO = 4;

	/**
	 * Timed Constructor
	 * Create a cache with max age of 4 hour and a WeakHashMap as store.
	 * @param maxAge Duration of Storage
	 * @see java.util.WeakHashMap
	 */
	public CoreTimeBasedMap( final long maxAge )
	{
		this.cleanupLast = System.currentTimeMillis();
		this.maxAge = maxAge;
		this.cleanupPeriod = maxAge / CLEANUP_TO_MAXAGE_RATIO;
		this.store = new ConcurrentHashMap<Object, Object>();
	}

	/**
	 * Cache an object (put into store).
	 * 
	 * @param key
	 *            unique identifier to retrieve object
	 * @param value
	 *            object to cache
	 */
	public final void put( final Object key, final Object value )
	{
		store.put( key, new CacheEntry( value ) );
	}

	/**
	 * Fetch an object (get from store).
	 * 
	 * @param key
	 *            unique identifier to retrieve object
	 * @return an object or null in case it isn't stored or it expired
	 */
	public final Object get( final Object key )
	{
		// Return the item's payload or null if not found
		CacheEntry item = getItem( key );
		return item == null ? null : item.payload;
	}

	/**
	 * Remove an object from cache.
	 * 
	 * @param key
	 *            unique identifier to retrieve object
	 */
	public final void remove( final Object key )
	{
		store.remove( key );
	}

	/**
	 * Get an item, if it expired remove it from cache and return null.
	 * 
	 * @param key
	 *            unique identifier to retrieve object
	 * @return an item or null
	 */
	private CacheEntry getItem( final Object key )
	{
		// Trigger periodic cleanup
		cleanup();
		
		// Check for an entry with the specified key
		CacheEntry item = (CacheEntry) store.get( key );
		
		// Check if the last updated timestamp exceeds the maximum age
		if ( item != null && System.currentTimeMillis() - item.entryTime > maxAge ) {
			// If so, remove the stale entry
			store.remove( key );
			return null;
		}
		
		// Update the last usage and return the item
		return item;
	}
	
	/**
	 * Cleanup Cache
	 */
	private void cleanup() {
		// Capture the current time
		long current = System.currentTimeMillis();
		
		// Only perform after a certain period of time
		if( this.cleanupLast >= current - this.cleanupPeriod) {
			return;
		}
		
		// Loop over each key in the store
		for( Object key : store.keySet().toArray()) {
			// Skip null objects
			if( key == null ) {
				continue;
			}
			
			// Check for an entry with the specified key
			CacheEntry item = (CacheEntry) store.get( key );
			
			// Check if the last updated timestamp exceeds the maximum age
			if ( item != null && current - item.entryTime > maxAge ) {
				// If so, remove the stale entry
				store.remove( key );
			}
		}
		
		//Mark the last cleanup
		this.cleanupLast = System.currentTimeMillis();
	}

	/**
	 * Cache Entry Container
	 */
	private static class CacheEntry
	{
		/** Time of Storage */
		private long entryTime;

		/** Object */
		private Object payload;

		/**
		 * Constructor
		 * @param payload Object
		 */
		protected CacheEntry( final Object payload )
		{
			this.entryTime = System.currentTimeMillis();
			this.payload = payload;
		}
	}
}
