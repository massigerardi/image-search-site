/**
 * 
 */
package com.dart.archive.image.search.site;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.yammer.metrics.core.HealthCheck;

/**
 * @author Massimiliano.Gerardi
 *
 */
@Component
public class ImageSearchHealthCheck extends HealthCheck {

	@Value(("${images}"))
	private String images;
	
	@Value(("${searchFolder}")) 
	protected String searchFolder;
	
	@Value(("${site}")) 
	protected String site;
	
	@Value(("${cacheFolder}")) 
	protected String cacheFolder;
	
	@Autowired
	ImageSearcherServiceFactory searcherServiceFactory;
	
	public ImageSearchHealthCheck() {
        super("images");
    }

	@Override
	protected Result check() throws Exception {
		StringBuffer result = new StringBuffer();
		result.append("Site: "+site+"\n");
		if (checkDirectory(searchFolder, result)) {
			result.append("Images:          "+images+"\n");
			result.append("Site Folder:     "+searchFolder+"\n");
			result.append("Cache Folder:    "+cacheFolder+"\n");
			result.append("Search Service:  "+searcherServiceFactory.getSearchService()+"\n");
			return Result.healthy(result.toString());
		}
		return Result.unhealthy(result.toString());
	}

	private boolean checkDirectory(String path, StringBuffer result) {
		File file = new File(path);
		boolean isHealthy = true;
		if (!file.exists()) {
			isHealthy = false;
			result.append("file "+path+" doesn't exist\n");
        } else if (!file.isDirectory()) {
			isHealthy = false;
			result.append("file "+path+" is not a directory\n");
		} else if (file.listFiles().length == 0) {
			isHealthy = false;
			result.append("file "+path+" is an empty directory\n");
		}
		return isHealthy;
	}

	
}
