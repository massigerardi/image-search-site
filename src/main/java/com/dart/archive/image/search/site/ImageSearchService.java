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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	private Logger logger = LoggerFactory.getLogger(ImageSearchService.class);
	
	private final AtomicLong counter = new AtomicLong();

	@Value("${host}")
	protected String host;
	
	@Value(("${images}"))
	protected String images;

	@Autowired
	protected ImageSearcher imageSearcher;
	
	public ImageSearchResults search(File file) {
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		try {
			long start = System.currentTimeMillis();
			Collection<Candidate> candidates = imageSearcher.search(file);
			long middle = System.currentTimeMillis();
			System.out.println("search in "+(middle - start));
			List<String> results = new ArrayList<String>();
			for (Iterator<Candidate> iterator = candidates.iterator(); iterator.hasNext();) {
				Candidate candidate = iterator.next();
				results.add(getUrl(candidate));
			}
			long end = System.currentTimeMillis();
			System.out.println("search in "+(end - middle) +" total "+(end - start));
			return new ImageSearchResults(counter.incrementAndGet(), results);
		} catch (Exception e) {
			logger.error("while searching", e);
			return new ImageSearchResults(counter.incrementAndGet(), new ArrayList<String>());
		} finally {
			Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
		}
	}
	
	private String getUrl(Candidate candidate) {
		String path = candidate.getImage().getAbsolutePath().replace('\\', '/');
		String imagesPath = new File(images).getAbsolutePath().replace('\\', '/');
		path = path.replaceAll(imagesPath, "");
		StringBuffer url = new StringBuffer();
		url.append(host);
		if (!host.endsWith("/") && !path.startsWith("/")) {
			url.append("/");
		}
		url.append(path);
		return url.toString();
	}
	
	
}
