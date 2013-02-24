/**
 * 
 */
package com.dart.archive.image.search.site;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;

/**
 * @author Massimiliano.Gerardi
 *
 */
public class ImageSearchConfiguration extends Configuration {
	
	@NotEmpty
    @JsonProperty
    private String images;

	public String getImages() {
		return images;
	}
	
	@NotEmpty
    @JsonProperty
    private String host;

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}
	
}
