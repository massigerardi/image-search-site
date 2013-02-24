/**
 * 
 */
package com.dart.archive.image.search.site;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Massimiliano.Gerardi
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ImageSearchResourceTest {
	
	@Mock
	private ImageSearchService searchService;
	
	@InjectMocks
	private ImageSearchResource imageSearchResource;

	@Mock
	private ImageSearchResults results;
	

	/**
	 * Test method for {@link com.dart.archive.image.search.site.ImageSearchResource#search(java.lang.String)}.
	 */
	@Test
	public void testSearch() {
		given(searchService.search(any(File.class))).willReturn(results);
		ImageSearchResults results = imageSearchResource.search("image.jpg");
		assertNotNull(results);
	}

}
