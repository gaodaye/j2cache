/**
 *  Copyright 2014-2015 Oschina
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

import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import net.oschina.j2cache.CacheException;
import net.oschina.j2cache.CacheExpiredListener;
import net.oschina.j2cache.CacheProvider;

/**
 * EhCache Provider plugin
 * 
 * Taken from EhCache 1.3 distribution
 * 
 * @author liudong
 * @author wendal
 */
public class MapCacheProvider implements CacheProvider {

	private ConcurrentHashMap<String, MapCache> _CacheManager;

	@Override
	public String name() {
		return "map";
	}

	/**
	 * Builds a Cache. Even though this method provides properties, they are not
	 * used. Properties for EHCache are specified in the ehcache.xml file.
	 * Configuration will be read from ehcache.xml for a cache declaration where
	 * the name attribute matches the name parameter in this builder.
	 *
	 * @param name
	 *            the name of the cache. Must match a cache configured in
	 *            ehcache.xml
	 * @param autoCreate
	 *            auto create cache region ?
	 * @param listener
	 *            cache listener
	 * @return a newly built cache will be built and initialised
	 * @throws CacheException
	 *             inter alia, if a cache of the same name already exists
	 */
	public MapCache buildCache(String name, boolean autoCreate, CacheExpiredListener listener) throws CacheException {
		MapCache mapcache = _CacheManager.get(name);
		if (mapcache == null) {
			try {
				synchronized (_CacheManager) {
					mapcache = _CacheManager.get(name);
					if (mapcache == null) {
						mapcache = new MapCache(new ConcurrentHashMap<>());
						_CacheManager.put(name, mapcache);
					}
				}
			} catch (Exception e) {
				throw new CacheException(e);
			}
		}
		return mapcache;
	}

	/**
	 * Callback to perform any necessary initialization of the underlying cache
	 * implementation during SessionFactory construction.
	 *
	 * @param props
	 *            current configuration settings.
	 */
	public void start(Properties props) throws CacheException {
		_CacheManager = new ConcurrentHashMap<String, MapCache>();
	}

	/**
	 * Callback to perform any necessary cleanup of the underlying cache
	 * implementation.
	 */
	public void stop() {
	}

}
