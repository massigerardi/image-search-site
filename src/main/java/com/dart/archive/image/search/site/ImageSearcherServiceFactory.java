/**
 * 
 */
package com.dart.archive.image.search.site;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dart.archive.image.search.service.ImageSearchService;
import com.dart.archive.image.search.service.PreFilteringImageSearchServiceImpl;

/**
 * @author massimiliano.gerardi
 *
 */
@Component
@Getter
public class ImageSearcherServiceFactory {
	
	private ImageSearchService searchService;

	/**
	 * @param searchFolder
	 */
	@Autowired
	public ImageSearcherServiceFactory(@Value(("${searchFolder}")) String searchFolder) {
		this.searchService = new PreFilteringImageSearchServiceImpl(searchFolder);
	}

	
	
	

	
	
	
	
}
