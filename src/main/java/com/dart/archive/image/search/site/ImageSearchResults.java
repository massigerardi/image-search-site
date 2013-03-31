/**
 * 
 */
package com.dart.archive.image.search.site;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @author Massimiliano.Gerardi
 *
 */
@Getter
@AllArgsConstructor
public class ImageSearchResults {

	private final long id;
	
	private final List<ImageSearchCandidate> candidates;

}
