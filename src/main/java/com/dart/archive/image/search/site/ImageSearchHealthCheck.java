/**
 * 
 */
package com.dart.archive.image.search.site;

import java.io.File;

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
	
	public ImageSearchHealthCheck() {
        super("images");
    }

	@Override
	protected Result check() throws Exception {
		File file = new File(images);
		if (!file.exists()) {
			return Result.unhealthy("file "+images+" doesn't exist");
        }
		if (!file.isDirectory()) {
			return Result.unhealthy("file "+images+" is not a directory");
		}
		if (file.listFiles().length == 0) {
			return Result.unhealthy("file "+images+" is an empty directory");
		}
        return Result.healthy();
	}

	
}
