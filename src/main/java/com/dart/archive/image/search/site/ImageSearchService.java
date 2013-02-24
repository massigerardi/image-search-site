/**
 * 
 */
package com.dart.archive.image.search.site;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dart.archive.image.search.Candidate;
import com.dart.archive.image.search.ImageSearcher;
import com.dart.archive.image.search.surf.InterestPointsSearcher;

/**
 * @author Massimiliano.Gerardi
 *
 */
@Component
public class ImageSearchService {

	private final AtomicLong counter = new AtomicLong();

	@Value("${host}")
	protected String host;
	
	@Value(("${images}"))
	protected String images;

	protected ImageSearcher searcher;
	
	/**
	 * @return the searcher
	 */
	public ImageSearcher getSearcher() {
		if (searcher==null) {
			searcher = new InterestPointsSearcher(images);
		}
		return searcher;
	}


	public ImageSearchResults search(File file) {
		Collection<Candidate> candidates = getSearcher().search(file);
		List<String> results = new ArrayList<String>();
		for (Iterator<Candidate> iterator = candidates.iterator(); iterator.hasNext();) {
			Candidate candidate = iterator.next();
			results.add(getUrl(candidate));
		}
		return new ImageSearchResults(counter.incrementAndGet(), results);
	}
	
	private String getUrl(Candidate candidate) {
		String path = candidate.getImage().getAbsolutePath();
		path = path.replace('\\', '/').replaceAll(images, "");
		StringBuffer url = new StringBuffer();
		url.append(host);
		if (!host.endsWith("/") && !path.startsWith("/")) {
			url.append("/");
		}
		url.append(path);
		return url.toString();
	}
	
	
}
