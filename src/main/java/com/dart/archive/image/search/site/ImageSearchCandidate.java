/**
 * 
 */
package com.dart.archive.image.search.site;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author massimiliano.gerardi
 *
 */
@Getter
@AllArgsConstructor
public class ImageSearchCandidate {

	Double score;
	
	String path;
}
