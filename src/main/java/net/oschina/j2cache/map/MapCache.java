//$Id: EhCache.java 10716 2006-11-03 19:05:11Z max.andersen@jboss.com $
/**
 *  Copyright 2003-2006 Greg Luck, Jboss Inc
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package net.oschina.j2cache.map;

import java.util.List;
import java.util.Map;

import net.oschina.j2cache.Cache;
import net.oschina.j2cache.CacheException;
import net.sf.ehcache.CacheManager;

/**
 * EHCache
 */
public class MapCache implements Cache {

	private Map<Object, Object> cache;

	/**
	 * Creates a new Hibernate pluggable cache based on a cache name.
	 *
	 * @param cache
	 *            The underlying EhCache instance to use.
	 * @param listener
	 *            cache listener
	 */
	public MapCache(Map<Object, Object> cache) {
		this.cache = cache;
	}

	@SuppressWarnings("rawtypes")
	public List keys() throws CacheException {
		return (List) this.cache.keySet();
	}

	/**
	 * Gets a value of an element which matches the given key.
	 *
	 * @param key
	 *            the key of the element to return.
	 * @return The value placed into the cache with an earlier put, or null if
	 *         not found or expired
	 * @throws CacheException
	 *             cache exception
	 */
	public Object get(Object key) throws CacheException {
		try {
			if (key == null)
				return null;
			else {
				return cache.get(key);
			}
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	/**
	 * Puts an object into the cache.
	 *
	 * @param key
	 *            a key
	 * @param value
	 *            a value
	 * @throws CacheException
	 *             if the {@link CacheManager} is shutdown or another
	 *             {@link Exception} occurs.
	 */
	public void update(Object key, Object value) throws CacheException {
		put(key, value);
	}

	/**
	 * Puts an object into the cache.
	 *
	 * @param key
	 *            a key
	 * @param value
	 *            a value
	 * @throws CacheException
	 *             if the {@link CacheManager} is shutdown or another
	 *             {@link Exception} occurs.
	 */
	public void put(Object key, Object value) throws CacheException {
		try {

			cache.put(key, value);
		} catch (IllegalArgumentException e) {
			throw new CacheException(e);
		} catch (IllegalStateException e) {
			throw new CacheException(e);
		} catch (net.sf.ehcache.CacheException e) {
			throw new CacheException(e);
		}

	}

	/**
	 * Removes the element which matches the key If no element matches, nothing
	 * is removed and no Exception is thrown.
	 *
	 * @param key
	 *            the key of the element to remove
	 * @throws CacheException
	 *             cache exception
	 */
	@Override
	public void evict(Object key) throws CacheException {
		try {
			cache.remove(key);
		} catch (IllegalStateException e) {
			throw new CacheException(e);
		} catch (net.sf.ehcache.CacheException e) {
			throw new CacheException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.oschina.j2cache.Cache#batchRemove(java.util.List)
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public void evict(List keys) throws CacheException {
		for (Object key : keys) {
			cache.remove(key);
		}

	}

	/**
	 * Remove all elements in the cache, but leave the cache in a useable state.
	 *
	 * @throws CacheException
	 *             cache exception
	 */
	public void clear() throws CacheException {
		try {
			cache.clear();
		} catch (IllegalStateException e) {
			throw new CacheException(e);
		} catch (net.sf.ehcache.CacheException e) {
			throw new CacheException(e);
		}
	}

	/**
	 * Remove the cache and make it unuseable.
	 *
	 * @throws CacheException
	 *             cache exception
	 */
	public void destroy() throws CacheException {
		try {
			cache = null;
		} catch (IllegalStateException e) {
			throw new CacheException(e);
		} catch (net.sf.ehcache.CacheException e) {
			throw new CacheException(e);
		}
	}

	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	@Override
	public void put(Object key, Object value, Integer expireInSec) throws CacheException {
		try {
			cache.put(key, value);
		} catch (IllegalArgumentException e) {
			throw new CacheException(e);
		} catch (IllegalStateException e) {
			throw new CacheException(e);
		} catch (net.sf.ehcache.CacheException e) {
			throw new CacheException(e);
		}
	}

	@Override
	public void update(Object key, Object value, Integer expireInSec) throws CacheException {
		put(key, value, expireInSec);
	}

}