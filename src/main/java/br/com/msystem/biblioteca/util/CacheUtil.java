package br.com.msystem.biblioteca.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public final class CacheUtil {
	
	private static CacheManager CACHE_MANAGER = CacheManager.create();
	
	private CacheUtil() {
		
	}

	public static final Cache getCache(final String cacheName) {
		
		if(CACHE_MANAGER.getCache(cacheName) == null) {
			Cache cache = new Cache(cacheName, 100, false, false, 600, 600, false, 0);
			CACHE_MANAGER.addCache(cache);
		}
		
		return CACHE_MANAGER.getCache(cacheName);
		
	}
	
	public static final void addCache(final Cache cache) {
		CACHE_MANAGER.addCache(cache);
	}
		
	public static final Object getCacheValue(final String cacheName, final Object key) {
		
		if(getCache(cacheName).get(key) != null)
			return getCache(cacheName).get(key).getObjectValue();
		
		return null;
		
	}
	
	public static final Object getCacheValue(final String cacheName, final Object key, CacheAction actionOnNotFound) {
		
//    return actionOnNotFound.execute();

    Object value = null;
		
		if(getCache(cacheName).get(key) != null) {
			value = getCache(cacheName).get(key).getObjectValue();
		} else {
		
			value = actionOnNotFound.execute();
			
			if (value != null) {
				addCacheValue(cacheName, key, value);
			}
			
		}
		
		return value;
	}
	
	public static final void addCacheValue(final String cacheName, final Object key, final Object value) {
		getCache(cacheName).put(new Element(key, value));
	}
	
	public static final void removeCacheValue(final String cacheName, final Object key) {
		getCache(cacheName).remove(key);
	}
	
	public static abstract class CacheAction {
		public abstract Object execute();
	}
	
}
