/**
 * 
 */
package com.dart.archive.image.search.site;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yammer.metrics.annotation.Timed;

/**
 * @author Massimiliano.Gerardi
 *
 */
@Path("/search")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class ImageSearchResource {

	@Autowired
	ImageSearchService searchService;

	@GET
	@Timed
	public ImageSearchResults search(@QueryParam("image") String image) {
		System.out.println("search "+image);
		try {
			return searchService.search(new File(image));
		} finally {
			
		}
	}

}
