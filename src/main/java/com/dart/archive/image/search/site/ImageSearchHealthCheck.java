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
	
	@Autowired
	ImageSearcherFactory searcherFactory;
	
	public ImageSearchHealthCheck() {
        super("images");
    }

	@Override
	protected Result check() throws Exception {
		StringBuffer result = new StringBuffer();
		result.append("Site: "+site+"\n");
		boolean isHealthy = 
				checkDirectory(images, result)
				&& checkDirectory(searchFolder, result);
		if (isHealthy) {
			result.append("Images:      "+images+"\n");
			result.append("Site Folder: "+searchFolder+"\n");
			result.append("Searcher 1:  "+searcherFactory.getInterestPointsSearcher()+"\n");
			result.append("Searcher 2:  "+searcherFactory.getNaiveColorImageSearcher()+"\n");
			return Result.healthy(result.toString());
		}
		return Result.unhealthy(result.toString());
	}

	private boolean checkDirectory(String path, StringBuffer result) {
		File file = new File(path);
		boolean isHealthy = true;
		if (!file.exists()) {
			isHealthy = false;
			result.append("file "+images+" doesn't exist\n");
        } else if (!file.isDirectory()) {
			isHealthy = false;
			result.append("file "+images+" is not a directory\n");
		} else if (file.listFiles().length == 0) {
			isHealthy = false;
			result.append("file "+images+" is an empty directory\n");
		}
		return isHealthy;
	}

	
}
