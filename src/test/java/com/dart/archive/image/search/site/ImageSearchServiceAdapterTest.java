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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.dart.archive.image.search.Candidate;
import com.dart.archive.image.search.service.ImageSearchService;

/**
 * @author Massimiliano.Gerardi
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ImageSearchServiceAdapterTest {
	
	@InjectMocks
	private ImageSearchServiceAdapter imageSearchServiceAdapter;
	
	@Mock
	private ImageSearcherServiceFactory searcherServiceFactory;

	@Mock
	private ImageSearchService searchService;
	
	String images = "/images/service";
	
	String searchFolder = "/src/test/resources/images/service/site01";
	
	@Mock
	private Candidate candidate1;
	
	@Mock
	private Candidate candidate2;
	
	@Mock
	private Candidate candidate3;
	
	Collection<Candidate> candidates = new ArrayList<Candidate>();
	String expectedPath = new File("/images/service/image1.jpg").getAbsolutePath().replace('\\', '/');
	
	@Before
	public void setUp() {
		imageSearchServiceAdapter.images=images;
		imageSearchServiceAdapter.searchFolder = searchFolder;
		given(searcherServiceFactory.getSearchService()).willReturn(searchService);
		given(searchService.search(any(File.class))).willReturn(candidates);
		given(candidate1.getImage()).willReturn(new File("/src/test/resources/images/service/site01/image1.jpg"));
		given(candidate2.getImage()).willReturn(new File("/src/test/resources/images/service/site01/image1.jpg"));
		given(candidate3.getImage()).willReturn(new File("/src/test/resources/images/service/site01/image1.jpg"));
	}
	
	/**
	 * test method for {@link ImageSearchServiceAdapter#search(java.io.File)}
	 */
	@Test
	public void testSearch() {
		candidates.add(candidate1);
		ImageSearchResults results = imageSearchServiceAdapter.search(new File("/src/test/resources/image.jpg"));
		assertNotNull(results);
		assertThat(results.getCandidates().size(), is(1));
		ImageSearchCandidate candidate = results.getCandidates().get(0);
		assertThat(candidate.getPath(), is(expectedPath));
	}

	/**
	 * test method for {@link ImageSearchServiceAdapter#search(java.io.File)}
	 */
	@Test
	public void testSearchMultipleResults() {
		candidates.add(candidate1);
		candidates.add(candidate2);
		candidates.add(candidate3);
		ImageSearchResults results = imageSearchServiceAdapter.search(new File("/src/test/resources/image.jpg"));
		assertNotNull(results);
		assertThat(results.getCandidates().size(), is(3));
		ImageSearchCandidate candidate = results.getCandidates().get(0);
		assertThat(candidate.getPath(), is(expectedPath));
		candidate = results.getCandidates().get(1);
		assertThat(candidate.getPath(), is(expectedPath));
		candidate = results.getCandidates().get(2);
		assertThat(candidate.getPath(), is(expectedPath));
	}

}
