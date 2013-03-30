/**
 * 
 */
package com.dart.archive.image.search.site;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dart.archive.image.search.color.NaiveColorImageSearcher;
import com.dart.archive.image.search.surf.InterestPointsSearcher;

/**
 * @author massimiliano.gerardi
 *
 */
@Component
public class ImageSearcherFactory {

	@Value(("${searchFolder}"))
	private String searchFolder;	
	
	private  InterestPointsSearcher interestPointsSearcher;
	
	private NaiveColorImageSearcher naiveColorImageSearcher;

	public InterestPointsSearcher getInterestPointsSearcher() {
		if (interestPointsSearcher==null) {
			interestPointsSearcher = new InterestPointsSearcher(searchFolder);
		}
		return interestPointsSearcher;
	}


	public NaiveColorImageSearcher getNaiveColorImageSearcher() {
		if (naiveColorImageSearcher==null) {
			naiveColorImageSearcher = new NaiveColorImageSearcher(searchFolder);
		}
		return naiveColorImageSearcher;
	}
	
	

	
	
	
	
}
