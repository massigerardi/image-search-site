/**
 * 
 */
package com.dart.archive.image.search.site;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
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
import com.dart.archive.image.search.service.ImageSearchService;

/**
 * @author Massimiliano.Gerardi
 *
 */
@Component
@Setter
@Getter
public class ImageSearchServiceAdapter {

	private Logger logger = LoggerFactory.getLogger(ImageSearchServiceAdapter.class);
	
	private final AtomicLong counter = new AtomicLong();
	
	@Value(("${images}")) 
	protected String images;

	@Value(("${searchFolder}")) 
	protected String searchFolder;

	@Autowired
	ImageSearcherServiceFactory searcherFactory;

	public ImageSearchResults search(File file) {
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		try {
			return search(searcherFactory.getSearchService(), file);
		} catch (Exception e) {
			logger.error("while searching", e);
			return new ImageSearchResults(counter.incrementAndGet(), new ArrayList<ImageSearchCandidate>());
		} finally {
			Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
		}
	}
	
	private ImageSearchResults search(ImageSearchService searcher, File file) {
		Collection<Candidate> candidates = searcher.search(file);
		List<ImageSearchCandidate> results = new ArrayList<ImageSearchCandidate>();
		for (Candidate candidate : candidates) {
			ImageSearchCandidate searchCandidate = getCandidate(candidate);
			results.add(searchCandidate);
		}
		return new ImageSearchResults(counter.incrementAndGet(), results);
	}	
	
	private ImageSearchCandidate getCandidate(Candidate other) {
		String path = getPath(other);
		Double score = other.getScore();
		return new ImageSearchCandidate(score, path);
	}

	private String getPath(Candidate candidate) {
		String path = candidate.getImage().getAbsolutePath().replace('\\', '/');
		return StringUtils.replace(path, searchFolder, images);
	}

	Executor executor = Executors.newFixedThreadPool(5);
	
	public void reload() {
		searcherFactory.getSearchService().reload();
	}
	
}
