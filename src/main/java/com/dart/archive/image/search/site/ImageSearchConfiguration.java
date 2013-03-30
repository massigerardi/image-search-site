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

	@NotEmpty
    @JsonProperty
    private String cacheFolder;

	@NotEmpty
    @JsonProperty
    private String cacheNames;

	@NotEmpty
    @JsonProperty
    private String searchFolder;

	@NotEmpty
    @JsonProperty
    private String site;

	public String getSearchFolder() {
		return searchFolder;
	}

	public String getSite() {
		return site;
	}
	
	public String getImages() {
		return images;
	}
	
}
