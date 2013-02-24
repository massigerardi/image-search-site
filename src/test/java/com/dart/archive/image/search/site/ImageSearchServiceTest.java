/**
 * 
 */
package com.dart.archive.image.search.site;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.dart.archive.image.search.Candidate;
import com.dart.archive.image.search.ImageSearcher;

/**
 * @author Massimiliano.Gerardi
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ImageSearchServiceTest {
	
	@InjectMocks
	private ImageSearchService imageSearchService;
	
	@Mock
	private ImageSearcher imageSearcher;

	@Mock
	private Candidate candidate;
	
	Collection<Candidate> candidates = new ArrayList<Candidate>();
	
	/**
	 * test method for {@link ImageSearchService#search(java.io.File)}
	 */
	@Test
	public void testSearch() {
		imageSearchService.host = "http://host/images/service";
		imageSearchService.images = "/src/test/resources/images/service";
		candidates.add(candidate);
		given(imageSearcher.search(any(File.class))).willReturn(candidates);
		given(candidate.getImage()).willReturn(new File("/src/test/resources/images/service/image.jpg"));
		ImageSearchResults results = imageSearchService.search(new File("/src/test/resources/image.jpg"));
		assertNotNull(results);
		assertThat(results.getContent().size(), is(1));
		String url = results.getContent().get(0);
		assertThat(url, is("http://host/images/service/image.jpg"));
	}

}
