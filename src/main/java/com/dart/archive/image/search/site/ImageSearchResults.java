/**
 * 
 */
package com.dart.archive.image.search.site;

import java.util.List;


/**
 * @author Massimiliano.Gerardi
 *
 */
public class ImageSearchResults {

	private final long id;
	
	private final List<String> content;

	/**
	 * @param id
	 * @param content
	 */
	public ImageSearchResults(long id, List<String> content) {
		super();
		this.id = id;
		this.content = content;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the content
	 */
	public List<String> getContent() {
		return content;
	}
	
	
	
}
