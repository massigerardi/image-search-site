/**
 * 
 */
package com.dart.archive.image.search.site;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dart.archive.image.search.Candidate;
import com.dart.archive.image.search.ImageSearcher;

/**
 * @author Massimiliano.Gerardi
 *
 */
@Component
@Setter
@Getter
public class ImageSearchService {

	private Logger logger = LoggerFactory.getLogger(ImageSearchService.class);
	
	private final AtomicLong counter = new AtomicLong();
	
	@Value(("${images}")) 
	protected String images;

	@Value(("${searchFolder}")) 
	protected String searchFolder;

	@Autowired
	ImageSearcherFactory searcherFactory;

	public ImageSearchResults search(File file) {
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		try {
			ImageSearchResults candidates = search(searcherFactory.getInterestPointsSearcher(), file);
			if (candidates.getContent().isEmpty()) {
				candidates = search(searcherFactory.getNaiveColorImageSearcher(), file);
			}
			return candidates;
		} catch (Exception e) {
			logger.error("while searching", e);
			return new ImageSearchResults(counter.incrementAndGet(), new ArrayList<String>());
		} finally {
			Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
		}
	}
	
	private ImageSearchResults search(ImageSearcher searcher, File file) {
		Collection<Candidate> candidates = searcher.search(file);
		List<String> results = new ArrayList<String>();
		for (Iterator<Candidate> iterator = candidates.iterator(); iterator.hasNext();) {
			Candidate candidate = iterator.next();
			results.add(getPath(candidate));
		}
		return new ImageSearchResults(counter.incrementAndGet(), results);
	}	
	
	private String getPath(Candidate candidate) {
		String path = candidate.getImage().getAbsolutePath().replace('\\', '/');
		return StringUtils.replace(path, searchFolder, images);
	}

	Executor executor = Executors.newFixedThreadPool(5);
	
	public void reload() {
		executor.execute(new ReloadJob(searcherFactory.getInterestPointsSearcher()));
		executor.execute(new ReloadJob(searcherFactory.getNaiveColorImageSearcher()));
	}
	
	class ReloadJob implements Runnable {
		
		ImageSearcher searcher;
		public ReloadJob(ImageSearcher searcher) {
			this.searcher = searcher;
		}
		public void run() {
			searcher.reload();
		}
		
	}
}
