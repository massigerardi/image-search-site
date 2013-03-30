/**
 * 
 */
package com.dart.archive.image.search.site;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.DiskStoreConfiguration;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author massimiliano.gerardi
 *
 */
@Component
public class EhCacheFactory {

	String cacheFolder;
	
	String cacheNames;

	/**
	 * @param cacheFolder
	 * @param cacheNames
	 */
	@Autowired
	public EhCacheFactory(
			@Value(("${cacheFolder}")) String cacheFolder, 
			@Value(("${cacheNames}")) String cacheNames) {
		super();
		this.cacheFolder = cacheFolder;
		this.cacheNames = cacheNames;
		init();
	}

	private void init() {
		DiskStoreConfiguration diskStoreConfiguration = new DiskStoreConfiguration();
		diskStoreConfiguration.setPath(cacheFolder);
		Configuration configuration = new Configuration();
		configuration.addDiskStore(diskStoreConfiguration);
		CacheManager singletonManager = new CacheManager(configuration );
		String[] names = StringUtils.split(cacheNames, '|');
		for (int i = 0; i < names.length; i++) {
			String name = names[i];
			CacheConfiguration config = getCacheConfiguration(name);
			singletonManager.addCache(new Cache(config));
		}
		
	}

	private CacheConfiguration getCacheConfiguration(String name) {
		CacheConfiguration config = new CacheConfiguration();
		config.setName(name);
		config.setTimeToIdleSeconds(1200);
		config.setTimeToLiveSeconds(1200);
		config.maxEntriesLocalHeap(10000);
		config.maxEntriesLocalDisk(1000000);
		config.diskExpiryThreadIntervalSeconds(1200);
		config.overflowToDisk(true);
		config.diskPersistent(true);
		return config;
	}
	
	
}
