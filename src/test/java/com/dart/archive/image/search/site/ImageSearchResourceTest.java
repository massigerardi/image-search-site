/**
 * 
 */
package com.dart.archive.image.search.site;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

import java.io.File;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
	private ImageSearchServiceAdapter searchService;
	
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
		Response response = imageSearchResource.search("image.jpg");
		assertNotNull(response);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		assertEquals(results, response.getEntity());
		
	}

}
